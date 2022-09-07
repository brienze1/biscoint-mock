package org.brienze.biscoint.validator;

import java.util.Optional;

import org.brienze.biscoint.exception.ValidationException;

public class Validators {

	public static <T> T validateNotNull(T object, String message) {
		return Optional.ofNullable(object).orElseThrow(() -> new ValidationException(message));
	}

	public static void validateNull(Object object, String message) {
		Optional.ofNullable(object).ifPresent(obj -> { throw new ValidationException(message); });
	}

	public static String validateNotEmpty(String string, String message) {
		return Optional.ofNullable(string).filter(s -> !s.isBlank()).orElseThrow(() -> new ValidationException(message));	
	}
	
	public static <T extends Number> T validateGreaterThanZero(T value, String message) {
		return Optional.ofNullable(value).filter(v -> v.doubleValue() > 0).orElseThrow(() -> new ValidationException(message));
	}

	public static <T extends Number> T validatePositive(T value, String message) {
		return Optional.ofNullable(value).filter(v -> v.doubleValue() >= 0).orElseThrow(() -> new ValidationException(message));
	}

	public static String validateDouble(String doubleValue, String message) {
		return Optional.ofNullable(doubleValue).filter(new DoubleValueSpecification()).orElseThrow(() -> new ValidationException(message));
	}

}
