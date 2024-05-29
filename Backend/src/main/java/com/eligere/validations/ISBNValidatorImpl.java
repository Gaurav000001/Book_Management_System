package com.eligere.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ISBNValidatorImpl implements ConstraintValidator<ISBN, String> {

	@Override
	public boolean isValid(String isbn, ConstraintValidatorContext context) {
		log.info("Validating ISBN: {}", isbn);
		return ISBNValidator.isValidISBN(isbn);
	}
}
