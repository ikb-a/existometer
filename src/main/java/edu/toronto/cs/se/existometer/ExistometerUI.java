package edu.toronto.cs.se.existometer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import layout.SpringUtilities;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;

import edu.toronto.cs.se.ci.CI;
import edu.toronto.cs.se.ci.Estimate;
import edu.toronto.cs.se.ci.aggregators.VoteAggregator;
import edu.toronto.cs.se.ci.budget.Allowance;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.data.Result;
import edu.toronto.cs.se.ci.selectors.AllSelector;
import edu.toronto.cs.se.existometer.sources.NameEmailMatch;

public class ExistometerUI extends JFrame {
	
	public static final Color TRUE_COLOR = new Color(86, 130, 3);
	public static final Color FALSE_COLOR = new Color(220, 20, 60);
	public static final Color UNK_COLOR = Color.DARK_GRAY;

	private JTextField nameField;
	private JTextField emailField;
	private JButton submit;

	private JLabel resultLabel;

	private JLabel qualityLabel;
	
	public ExistometerUI() {
		super("Exist-o-meter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout mainLayout = new BorderLayout();
		setLayout(mainLayout);
		
		nameField = new JTextField("");
		JLabel nameLabel = new JLabel("Name: ");
		emailField = new JTextField("");
		JLabel emailLabel = new JLabel("Email: ");
		submit = new JButton("Submit");
		submit.addActionListener(new SubmitListener());
		getRootPane().setDefaultButton(submit);
		
		GridLayout fieldLayout = new GridLayout(1, 0);
		fieldLayout.setHgap(10);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);

		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new BorderLayout());
		emailPanel.add(emailLabel, BorderLayout.WEST);
		emailPanel.add(emailField, BorderLayout.CENTER);
		
		JLabel title = new JLabel("Exist-o-meter");
		title.setFont(new Font("Helvetica", Font.BOLD, 90));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		resultLabel = new JLabel("-");
		resultLabel.setFont(new Font("Helvetica", Font.BOLD, 64));
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setForeground(UNK_COLOR);
		
		qualityLabel = new JLabel("-");
		qualityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qualityLabel.setForeground(Color.GRAY);

		JPanel panel = new JPanel();
		// GridLayout layout = new GridLayout(0, 1);
		panel.setLayout(new SpringLayout());
		panel.add(title);
		panel.add(namePanel);
		panel.add(emailPanel);
		panel.add(submit);
		panel.add(resultLabel);
		panel.add(qualityLabel);
		panel.setMaximumSize(new Dimension(780, 100000));
		
		SpringUtilities.makeCompactGrid(panel, 6, 1, 5, 5, 5, 5);
		
		// add(panel, BorderLayout.NORTH);
		
		Box box = new Box(BoxLayout.Y_AXIS);
		box.setAlignmentX(CENTER_ALIGNMENT); 
		box.add(panel); 
		
		add(box, BorderLayout.NORTH);


		pack();
		setLocationRelativeTo(null);
	}
	
	public static void main( String[] args ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) { /* Nimbus is not available - use default */ }

		UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Helvetica", Font.PLAIN, 20));
		new ExistometerUI().setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			String email = emailField.getText();

			if (name.length() == 0 || email.length() == 0)
				return;

			NameAndEmail nameAndEmail = new NameAndEmail(name, email);
			
			CI<NameAndEmail, Boolean, Double, Double> ci = new CI<>(NameEmailMatch.class, new VoteAggregator<Boolean>(), new AllSelector<NameAndEmail, Boolean, Double>());
			
			Allowance[] budget = {
				new Time(10, TimeUnit.MINUTES)
			};
			
			Estimate<Boolean, Double> estimate = ci.apply(nameAndEmail, budget);
			Futures.addCallback(estimate, new FutureCallback<Result<Boolean, Double>>() {

				@Override
				public void onSuccess(Result<Boolean, Double> result) {
					synchronized(ExistometerUI.this) {
						boolean value = result.getValue();
						resultLabel.setText(value ? "True" : "False");
						resultLabel.setForeground(value ? TRUE_COLOR : FALSE_COLOR);

						qualityLabel.setText(Double.toString(result.getQuality()));
					}
				}
				
				@Override
				public void onFailure(Throwable t) {
					synchronized(ExistometerUI.this) {
						resultLabel.setText("Unknown");
						resultLabel.setForeground(UNK_COLOR);
						qualityLabel.setText("-");

						JOptionPane.showMessageDialog(null, t);
					}
				}

			});
			
			estimate.addPartialListener(() -> {
				synchronized(ExistometerUI.this) {
					Optional<Result<Boolean, Double>> partial = estimate.getCurrent();
					if (partial.isPresent()) {
						Result<Boolean, Double> result = partial.get();
						boolean value = result.getValue();
						resultLabel.setText(value ? "True?" : "False?");
						resultLabel.setForeground(value ? TRUE_COLOR : FALSE_COLOR);
						
						qualityLabel.setText(Double.toString(result.getQuality()));
					} else {
						resultLabel.setText("Unknown");
						resultLabel.setForeground(UNK_COLOR);
						
						qualityLabel.setText("-");
					}
				}
			}, MoreExecutors.sameThreadExecutor());
			
			resultLabel.setText("-");
			resultLabel.setForeground(UNK_COLOR);
			qualityLabel.setText("-");
		}
		
	}

}
