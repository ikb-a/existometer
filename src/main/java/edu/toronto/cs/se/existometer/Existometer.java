package edu.toronto.cs.se.existometer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import edu.toronto.cs.se.ci.CI;
import edu.toronto.cs.se.ci.Contracts;
import edu.toronto.cs.se.ci.Estimate;
import edu.toronto.cs.se.ci.aggregators.VoteAggregator;
import edu.toronto.cs.se.ci.budget.Allowance;
import edu.toronto.cs.se.ci.budget.basic.Dollars;
import edu.toronto.cs.se.ci.budget.basic.Flag;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.data.Result;
import edu.toronto.cs.se.ci.selectors.AllSelector;
import edu.toronto.cs.se.existometer.sources.BingHits;
import edu.toronto.cs.se.existometer.sources.GoogleHits;
import edu.toronto.cs.se.existometer.sources.NameEmailMatch;
import edu.toronto.cs.se.existometer.sources.NameEmailMatchSearch;
import edu.toronto.cs.se.existometer.sources.NameEmailOnGithub;

public class Existometer {
	
	public static void registerSources() {
		Contracts.register(new BingHits());
		Contracts.register(new GoogleHits());
		Contracts.register(new NameEmailMatchSearch());
		Contracts.register(new NameEmailOnGithub());
	}

	public static NameAndEmail promptForNameAndEmail() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your name: ");
		String name = br.readLine();
		System.out.print("Enter your email: ");
		String email = br.readLine();
		
		return new NameAndEmail(name, email);
	}
	
	public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException {
		registerSources(); // Sources have to be registered before the system can be run...

		NameAndEmail nameAndEmail = promptForNameAndEmail();
		
		Allowance[] budget = {
			new Time(10, TimeUnit.SECONDS),
			new Dollars(new BigDecimal(1)),
			new Flag("AnyString")
		};
		
		CI<NameAndEmail, Boolean, Double, Double> ci = new CI<>(NameEmailMatch.class, new VoteAggregator<Boolean>(), new AllSelector<NameAndEmail, Boolean, Double>());

		Estimate<Boolean, Double> estimate = ci.apply(nameAndEmail, budget);
		Result<Boolean, Double> res = estimate.get();

		System.out.println(res.getValue());
		System.out.println(res.getQuality());
	}

}
