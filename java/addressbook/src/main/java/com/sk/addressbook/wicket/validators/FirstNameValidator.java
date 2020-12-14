package com.sk.addressbook.wicket.validators;

import org.apache.wicket.validation.CompoundValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.wicket.addcontact.AddPanel;

public class FirstNameValidator extends CompoundValidator<String> {
	private static Logger logger = LoggerFactory.getLogger(FirstNameValidator.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FirstNameValidator() {
		add(StringValidator.lengthBetween(3, 10));
		add(new PatternValidator("^[a-z0-9]*$"));
		
	}
}
