package edu.toronto.cs.se.ci.playground.sources;

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
import edu.toronto.cs.se.ci.playground.contracts.SearchHits;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class GoogleHits extends BasicSource<String, Integer, Void> implements SearchHits {

	@Override
	public Expenditure[] getCost(String args) throws Exception {
		return new Expenditure[] {
			new Time(500, TimeUnit.MILLISECONDS)
		};
	}

	@Override
	public Void getTrust(String input, Optional<Integer> response) {
		return null;
	}

	@Override
	public Integer getResponse(String input) throws UnknownException {
		try {
			// Connect to the remote URL
			URL url = new URL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + URLEncoder.encode(input, "UTF-8"));
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
			int resultCount;

			try {
				resultCount = Integer.parseInt(obj.getJSONObject("responseData").getJSONObject("cursor").getString("resultCount"));
			} catch(JSONException e) {
				resultCount = 0;
			}
			
			// Return an answer
			return resultCount;
		} catch (Exception e) {
			throw new UnknownException(e);
		}
	}

}
