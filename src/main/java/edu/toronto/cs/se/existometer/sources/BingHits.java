package edu.toronto.cs.se.existometer.sources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.utils.BasicSource;

// @Fulfils(value="SearchHits", from=String.class, to=Integer.class)
public class BingHits extends BasicSource<String, Integer, Void> implements SearchHits {

	private String API_KEY = "API_KEY_HERE";
	
	@Override
	public Expenditure[] getCost(String args) throws Exception {
		return new Expenditure[] {
			new Time(500, TimeUnit.MILLISECONDS)
		};
	}


	@Override
	public Void getTrust(String input, Optional<Integer> response) {
		// We have complete trust that this is, in fact, the number of bing hits :D
		return null;
	}


	@Override
	public Integer getResponse(String input) throws UnknownException {
		try {
			// Connect to the remote URL
			URL url = new URL("https://api.datamarket.azure.com/Bing/SearchWeb/v1/Composite?Query=" + URLEncoder.encode("'" + input + "'", "UTF-8"));
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":" + API_KEY).getBytes()));
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
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
				resultCount = Integer.parseInt(obj.getJSONObject("d").getJSONArray("results").getJSONObject(0).getString("WebTotal"));
			} catch (JSONException e) {
				resultCount = 0;
			}

			return resultCount;
		} catch (Exception e) {
			throw new UnknownException(e);
		}
	}

}
