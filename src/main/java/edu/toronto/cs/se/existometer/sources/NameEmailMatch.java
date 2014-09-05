package edu.toronto.cs.se.existometer.sources;

import edu.toronto.cs.se.ci.Contract;
import edu.toronto.cs.se.existometer.NameAndEmail;

public interface NameEmailMatch extends Contract<NameAndEmail, Boolean, Double> {
	/* Returns True if the NameAndEmail cooccur - False otherwise */
}
