package com.sk.addressbook.wicket.validators;

import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.validation.CompoundValidator;

public class EmailValidator extends CompoundValidator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailValidator() {
		
		//MailAddress address = new MailAddress();
		//add(RfcCompliantEmailAddressValidator.getInstance());
		
	}
}
