package com.sk.addressbook.wicket.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LastNameValidator  implements IValidator<String> {
	private static Logger logger = LoggerFactory.getLogger(LastNameValidator.class);


	@Override
	public void validate(IValidatable<String> validatable) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9]*$";

		String username = validatable.getValue();

		// Check if username is atleast 5 characters long
		if (username.length() < 3) {
			ValidationError error = new ValidationError(this);
			error.setMessage("Error: lastname should be atleast 3 chars");
			validatable.error(error);
		}

		// Check if username is at max 5 characters long
		if (username.length() > 15) {
			ValidationError error = new ValidationError(this);
			error.setMessage("Error: lastname should be at max 15 chars");
			validatable.error(error);
		}

		// Check if username contains any special chars
		if (!username.matches(regex)) {
			ValidationError error = new ValidationError(this);
			error.setMessage("Error: No special characters allowed in lastname. Can only contain alphabets and numerals");
			validatable.error(error);
		}

	}


}
