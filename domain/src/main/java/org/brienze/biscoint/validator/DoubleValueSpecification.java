package org.brienze.biscoint.validator;

import java.util.function.Predicate;

public class DoubleValueSpecification implements Predicate<String> {

	@Override
	public boolean test(String stringDouble) {
		try {
			Double.valueOf(stringDouble);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
