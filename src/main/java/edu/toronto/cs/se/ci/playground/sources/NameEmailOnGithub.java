package edu.toronto.cs.se.ci.playground.sources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.playground.contracts.ContactValidate;
import edu.toronto.cs.se.ci.playground.data.Contact;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class NameEmailOnGithub extends BasicSource<Contact, Boolean, ContactTrust> implements ContactValidate {

	@Override
	public Expenditure[] getCost(Contact args) throws Exception {
		return new Expenditure[] {
			new Time(500, TimeUnit.MILLISECONDS)
		};
	}

	@Override
	public ContactTrust getTrust(Contact args, Optional<Boolean> value) {
		boolean val = value.or(false);
		double conf = val ? 0.7 : 0.4;
		return new ContactTrust(val ? conf : 0.3, val ? conf : 0.3, 0, conf, 0, 0);
	}

	@Override
	public Boolean getResponse(Contact input) throws UnknownException {
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
		} catch (Exception e) {
			// This will also be thrown if there are no items in the search results
			// In this case, we want the return value to be unknown - so we throw an unknown exception
			// This also happens if the network connection is lost etc.
			throw new UnknownException(e);
		}
	}

}
