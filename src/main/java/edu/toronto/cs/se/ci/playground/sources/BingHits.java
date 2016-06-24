package edu.toronto.cs.se.ci.playground.sources;

import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.playground.contracts.SearchHits;
import edu.toronto.cs.se.ci.playground.sources.util.BingSearchJSON;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class BingHits extends BasicSource<String, Integer, Void> implements SearchHits {

	@Override
	public Expenditure[] getCost(String args) throws Exception {
		return new Expenditure[] { new Time(500, TimeUnit.MILLISECONDS) };
	}

	@Override
	public Void getTrust(String input, Optional<Integer> response) {
		// We have complete trust that this is, in fact, the number of bing hits
		// :D
		return null;
	}

	@Override
	public Integer getResponse(String input) throws UnknownException {

		try {
			JSONObject obj = BingSearchJSON.search(input);

			int resultCount;
			try {
				resultCount = Integer.parseInt(
						obj.getJSONObject("d").getJSONArray("results").getJSONObject(0).getString("WebTotal"));
			} catch (JSONException e) {
				resultCount = 0;
			}

			return resultCount;
		} catch (Exception e) {
			throw new UnknownException(e);
		}
	}

}
