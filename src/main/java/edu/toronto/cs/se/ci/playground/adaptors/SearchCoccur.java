package edu.toronto.cs.se.ci.playground.adaptors;

import java.util.List;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.playground.contracts.Cooccur;
import edu.toronto.cs.se.ci.playground.contracts.SearchHits;
import edu.toronto.cs.se.ci.utils.BasicAdaptor;

public class SearchCoccur extends BasicAdaptor<List<String>, Boolean, Double, String, Integer, Void> implements Cooccur {

	public SearchCoccur() {
		super(SearchHits.class);
	}

	@Override
	public String transformArgs(List<String> args) {
		StringBuilder sb = new StringBuilder();
		
		for (String arg : args) {
			sb.append("+\"");
			sb.append(arg);
			sb.append("\" ");
		}
		
		return sb.toString();
	}

	@Override
	public Boolean transformResult(Integer result) {
		return result >= 1;
	}

	@Override
	public Double transformTrust(Void trust, Optional<Boolean> result,
			Optional<Integer> originalResult) {
		return result.or(false)
				? Math.min(originalResult.or(1), 10) * 0.03 + 0.4
				: 0.5;
	}

}
