package edu.toronto.cs.se.existometer.sources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.utils.BasicAdaptor;
import edu.toronto.cs.se.existometer.NameAndEmail;

public class NameEmailMatchSearch extends
		BasicAdaptor<NameAndEmail, Boolean, Double, String, Integer, Void> implements NameEmailMatch {

	public NameEmailMatchSearch() {
		super(SearchHits.class);
	}

	@Override
	public String transformArgs(NameAndEmail args) {
		return "+\"" + args.getName() + "\" +\"" + args.getEmail() + "\"";
	}

	@Override
	public Boolean transformResult(Integer result) {
		System.out.println(result);
		return result > 0;
	}

	@Override
	public Double transformTrust(Void trust, Optional<Boolean> result, Optional<Integer> origResult) {
		return result.or(false)
				? Math.min(origResult.or(1), 10) * 0.05 + 0.4
				: 0.5;
	}

}
