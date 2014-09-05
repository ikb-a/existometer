package edu.toronto.cs.se.existometer.sources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.utils.BasicSource;
import edu.toronto.cs.se.existometer.NameAndEmail;

public class NameEmailOnGithub extends BasicSource<NameAndEmail, Boolean, Double> implements NameEmailMatch {

	@Override
	public Expenditure[] getCost(NameAndEmail args) throws Exception {
		return new Expenditure[] {
			new Time(500, TimeUnit.MILLISECONDS)
		};
	}

	@Override
	public Double getTrust(NameAndEmail args, Optional<Boolean> value) {
		if (value.isPresent())
			return value.get() ? 0.8 : 0;
		else
			return 0.5;
	}

	@Override
	public Boolean getResponse(NameAndEmail input) throws UnknownException {
		try {
			URL url = new URL("https://api.github.com/search/users?q=" + URLEncoder.encode(input.getEmail(), "UTF-8"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			// Read in the entire file
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			// Parse the JSON
			JSONObject obj = new JSONObject(sb.toString());	
			
			try {
				URL userURL = new URL(obj.getJSONArray("items").getJSONObject(0).getString("url"));
				BufferedReader userReader = new BufferedReader(new InputStreamReader(userURL.openStream(), "UTF-8"));
				
				StringBuilder userSB = new StringBuilder();
				String userLine = userReader.readLine();
				
				while (userLine != null) {
					userSB.append(userLine);
					userLine = userReader.readLine();
				}
				
				JSONObject userObj = new JSONObject(userSB.toString());
				
				if (userObj.getString("name").toLowerCase().equals(input.getName().toLowerCase()) && userObj.getString("email").toLowerCase().equals(input.getEmail().toLowerCase()))
					return true;
				else
					return false;
				
			} catch (JSONException e) {
				// We probably don't have any items in the search results - just return false
				// For this Source, False is equivalent to unknown
				return false;
			}
		} catch (Exception e) {
			throw new UnknownException(e);
		}
	}

}
