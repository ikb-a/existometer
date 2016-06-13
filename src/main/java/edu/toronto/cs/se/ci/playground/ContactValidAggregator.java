package edu.toronto.cs.se.ci.playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;

import edu.toronto.cs.se.ci.Aggregator;
import edu.toronto.cs.se.ci.aggregators.ProbBeliefAggregator;
import edu.toronto.cs.se.ci.data.Opinion;
import edu.toronto.cs.se.ci.data.Result;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;

public class ContactValidAggregator implements Aggregator<Boolean, ContactTrust, Double> {

	private List<Opinion<Boolean, Double>> filterZeros(List<Opinion<Boolean, Double>> items) {
		/*
		 * A lamda expression create a predicate that takes an item and returns
		 * whether the items's trust is not 0. Guava filter then returns a list
		 * with items failing the predicate removed
		 */
		return new ArrayList<>(Collections2.filter(items, (item) -> item.getTrust() != 0));
	}

	@Override
	public Optional<Result<Boolean, Double>> aggregate(List<Opinion<Boolean, ContactTrust>> opinions) {
		int size = opinions.size();

		List<Opinion<Boolean, Double>> nameOpinions = new ArrayList<>(size);
		List<Opinion<Boolean, Double>> emailOpinions = new ArrayList<>(size);
		List<Opinion<Boolean, Double>> addressOpinions = new ArrayList<>(size);
		List<Opinion<Boolean, Double>> nameEmailOpinions = new ArrayList<>(size);
		List<Opinion<Boolean, Double>> emailAddressOpinions = new ArrayList<>(size);
		List<Opinion<Boolean, Double>> nameAddressOpinions = new ArrayList<>(size);

		for (Opinion<Boolean, ContactTrust> opinion : opinions) {
			Boolean value = opinion.getValue();
			ContactTrust trust = opinion.getTrust();
			nameOpinions.add(new Opinion<Boolean, Double>(value, trust.getName()));
			emailOpinions.add(new Opinion<Boolean, Double>(value, trust.getEmail()));
			addressOpinions.add(new Opinion<Boolean, Double>(value, trust.getAddress()));
			nameEmailOpinions.add(new Opinion<Boolean, Double>(value, trust.getNameEmail()));
			emailAddressOpinions.add(new Opinion<Boolean, Double>(value, trust.getEmailAddress()));
			nameAddressOpinions.add(new Opinion<Boolean, Double>(value, trust.getNameAddress()));
		}

		ProbBeliefAggregator<Boolean> agg = new ProbBeliefAggregator<Boolean>();
		Optional<Result<Boolean, Double>> nameResult = agg.aggregate(filterZeros(nameOpinions));
		Optional<Result<Boolean, Double>> emailResult = agg.aggregate(filterZeros(emailOpinions));
		Optional<Result<Boolean, Double>> addressResult = agg.aggregate(filterZeros(addressOpinions));

		Optional<Result<Boolean, Double>> nameEmailResult = agg.aggregate(filterZeros(nameEmailOpinions));
		Optional<Result<Boolean, Double>> emailAddressResult = agg.aggregate(filterZeros(emailAddressOpinions));
		Optional<Result<Boolean, Double>> nameAddressResult = agg.aggregate(filterZeros(nameAddressOpinions));

		System.out.println(nameResult);
		System.out.println(emailResult);
		System.out.println(addressResult);
		System.out.println(nameEmailResult);
		System.out.println(emailAddressResult);
		System.out.println(nameAddressResult);

		Result<Boolean, Double> nullResult = new Result<>(false, 0.0);

		// Any two cooccurances implies the third cooccurance, we thus take the
		// middle one
		List<Optional<Result<Boolean, Double>>> cooccurances = Arrays.asList(nameEmailResult, emailAddressResult,
				nameAddressResult);
		cooccurances.sort((a, b) -> (int) Math.signum(a.or(nullResult).getQuality() - b.or(nullResult).getQuality()));

		Comparator<Optional<Result<Boolean, Double>>> comparator = (oa, ob) -> {
			Result<Boolean, Double> a = oa.or(nullResult);
			Result<Boolean, Double> b = ob.or(nullResult);

			if (!a.getValue() && b.getValue())
				return -1;
			else if (a.getValue() && !b.getValue())
				return 1;
			else if (!a.getValue() && !b.getValue())
				return (int) Math.signum(b.getQuality() - a.getQuality());
			else
				return (int) Math.signum(a.getQuality() - b.getQuality());
		};

		// I'm not sure if we want to choose _exactly_ the "worst" result that
		// we have from all sources, but it is _an_ option
		Optional<Result<Boolean, Double>> worst = Collections
				.min(ImmutableList.of(nameResult, emailResult, addressResult, cooccurances.get(1)), comparator);

		return worst;
	}

}
