package com.sk.addressbook.wicket.addcontact;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.AddressBookApplication;
import com.sk.addressbook.bean.SKContact;
import com.sk.addressbook.db.DBUtil;
import com.sk.addressbook.wicket.validators.EmailValidator;
import com.sk.addressbook.wicket.validators.FirstNameValidator;
import com.sk.addressbook.wicket.validators.PhoneValidator;

public class AddPanel extends Panel {
	Logger logger = LoggerFactory.getLogger(AddPanel.class);

	public AddPanel(String id) {
		super(id);
		init();
	}

	private void init() {
		Form addForm = new AddForm("addForm");
		add(addForm);
	}

	public class AddForm extends Form {
		private TextField<String> firstNameField;
		private TextField<String> lastNameField;
		private TextField<String> emailField;
		private TextField<String> phoneField;

		private String addStatus = null;

		public AddForm(String id) {
			super(id);

			setDefaultModel(new CompoundPropertyModel(this));

			firstNameField = new TextField<String>("firstName", Model.of(""));
			lastNameField = new TextField<String>("lastName", Model.of(""));
			emailField = new TextField<String>("email", Model.of(""));
			phoneField = new TextField<String>("phone", Model.of(""));

			firstNameField.setRequired(true);
			firstNameField.add(new FirstNameValidator());
			lastNameField.add(new FirstNameValidator());
			emailField.add(new EmailValidator());
			phoneField.add(new PhoneValidator());

			
			add(firstNameField);
			add(lastNameField);
			add(emailField);
			add(phoneField);
			add(new Label("addStatus"));

		}

		public final void onSubmit() {
			long startTime=System.currentTimeMillis();

			final String firstNameValue = firstNameField.getModelObject();
			final String lastNameValue = lastNameField.getModelObject();
			final String emailValue = emailField.getModelObject();
			final String phoneValue = phoneField.getModelObject();

			logger.info("Adding contact:" + firstNameValue);

			SKContact contact = new SKContact();
			contact.setFirstName(firstNameValue);
			contact.setLastName(lastNameValue);
			contact.setEmail(emailValue);
			contact.setPhone(phoneValue);
			
			DBUtil.saveContact(firstNameValue,contact);

			long endTime=System.currentTimeMillis();

			logger.info("Contacted saved successfully:" + firstNameValue +" Timetaken:"+ (endTime-startTime));
			addStatus = "Congratulations: Contact added Successfully!";

		
		}
	}
}
