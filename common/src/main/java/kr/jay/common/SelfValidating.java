package kr.jay.common;

import java.util.Set;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * SelfValidating
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
public abstract class SelfValidating<T> {

	private Validator validator;

	public SelfValidating(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	protected void validateSelf(){
		Set<ConstraintViolation<T>> violations = validator.validate((T)this);
		if(!violations.isEmpty()){
			throw new ConstraintViolationException(violations);
		}
	}
}
