package org.brienze.biscoint.validator;

import java.time.LocalDateTime;
import java.util.Optional;

import org.brienze.biscoint.exception.ValidationException;

public class Validators {

	public static <T> void validateNotNull(T object, String message) {
		Optional.ofNullable(object).orElseThrow(() -> new ValidationException(message));
	}

	public static void validateNull(Object object, String message) {
		Optional.ofNullable(object).ifPresent(obj -> { throw new ValidationException(message); });
	}

	public static void validateNotEmpty(String string, String message) {
		Optional.ofNullable(string).filter(s -> !s.isBlank()).orElseThrow(() -> new ValidationException(message));
	}
	
	public static <T extends Number> void validateGreaterThanZero(T value, String message) {
		Optional.ofNullable(value).filter(v -> v.doubleValue() > 0).orElseThrow(() -> new ValidationException(message));
	}


	public static <T extends LocalDateTime> void validateTime(T before, T after, String message) {
		validateNotNull(after, "Missing value to compare.");
		Optional.ofNullable(before).filter(v -> before.isBefore(after)).orElseThrow(() -> new ValidationException(message));
	}

	public static <T extends Number> void validatePositive(T value, String message) {
		Optional.ofNullable(value).filter(v -> v.doubleValue() >= 0).orElseThrow(() -> new ValidationException(message));
	}

	public static void validateDouble(String doubleValue, String message) {
		Optional.ofNullable(doubleValue).filter(new DoubleValueSpecification()).orElseThrow(() -> new ValidationException(message));
	}

}
