package edu.toronto.cs.se.ci.playground.sources.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

import org.json.JSONObject;

import edu.toronto.cs.se.ci.UnknownException;

public class BingSearchJSON {

	/**
	 * Note, the Required Bing API is "Bing Search API – Web Results Only", not
	 * "Bing Search API"
	 */
	private static final String API_KEY = System.getenv("BING_KEY");

	/**
	 * Returns the JSON produced by Bing searching {@code searchFor}. This
	 * method requires that the environment variable {@code API_KEY} be set to a
	 * valid API key for the "Bing Search API – Web Results Only" api.
	 * 
	 * @param searchFor
	 *            The String to search for on the Bing API
	 * @return A JSON object representing the Bing search results
	 * @throws UnknownException
	 *             If it is unable to return the JSON
	 */
	public static synchronized JSONObject search(String searchFor) throws UnknownException {
		if (API_KEY == null || API_KEY.length() < 1)
			throw new RuntimeException("BING_KEY for BingHits is not present");

		try {

			// Connect to the remote URL
			URL url = new URL("https://api.datamarket.azure.com/Bing/SearchWeb/v1/Composite?Query="
					+ URLEncoder.encode("'" + searchFor + "'", "UTF-8"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization",
					"Basic " + Base64.getEncoder().encodeToString((API_KEY + ":" + API_KEY).getBytes()));
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
			return obj;

		} catch (Exception e) {
			throw new UnknownException(e);
		}
	}
}
