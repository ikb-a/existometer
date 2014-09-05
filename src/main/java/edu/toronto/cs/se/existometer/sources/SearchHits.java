package edu.toronto.cs.se.existometer.sources;

import edu.toronto.cs.se.ci.Contract;

public interface SearchHits extends Contract<String, Integer, Void> {
	/* Returns the # of hits for the given input query string */
}
