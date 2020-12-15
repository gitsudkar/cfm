package com.sk.addressbook.wicket.editcontact;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.AddressBookApplication;
import com.sk.addressbook.bean.SKContact;
import com.sk.addressbook.db.DBUtil;
import com.sk.addressbook.wicket.validators.EmailValidator;
import com.sk.addressbook.wicket.validators.ExactErrorLevelFilter;
import com.sk.addressbook.wicket.validators.FirstNameValidator;
import com.sk.addressbook.wicket.validators.LastNameValidator;
import com.sk.addressbook.wicket.validators.PhoneValidator;

public class EditPanel extends Panel {
	Logger logger = LoggerFactory.getLogger(AddressBookApplication.class);

	public EditPanel(String id) {
		super(id);
		init();
	}

	private void init() {
		AuthenticatedWebApplication app= (AuthenticatedWebApplication) Application.get();
		if(! AuthenticatedWebSession.get().isSignedIn()) {
			logger.info("invalid Session, sending user to signin page");
			app.restartResponseAtSignInPage();
		}
		
		Form editForm = new EditForm("editForm");
		add(editForm);
	}

	public class EditForm extends Form {
		
		

		private TextField<String> firstNameField;
		private TextField<String> lastNameField;
		private TextField<String> emailField;
		private TextField<String> phoneField;

		private String editStatus = null;

		public EditForm(String id) {
			super(id);
			String firstName = RequestCycle.get().getRequest().getQueryParameters().getParameterValue("firstName").toString();
			logger.debug("editform recieved:"+ firstName);

			setDefaultModel(new CompoundPropertyModel(this));
			

			firstNameField = new TextField<String>("firstName", Model.of(""));
			lastNameField = new TextField<String>("lastName", Model.of(""));
			emailField = new TextField<String>("email", Model.of(""));
			phoneField = new TextField<String>("phone", Model.of(""));

			SKContact skContact = DBUtil.getContact(firstName);
			
			firstNameField.setModelValue(new String[] { skContact.getFirstName() });
			lastNameField.setModelValue(new String[] { skContact.getLastName() });
			emailField.setModelValue(new String[] { skContact.getEmail() });
			phoneField.setModelValue(new String[] { skContact.getPhone() });

			firstNameField.add(new FirstNameValidator());
			lastNameField.add(new LastNameValidator());
			emailField.add(new EmailValidator());
			phoneField.add(new PhoneValidator());

			
			Button saveButton = new Button("save") {
				@Override
				public void onSubmit() {
					logger.debug("Save pressed");
					savePressed();
				}
			};

			Button deleteButton = new Button("delete") {
				@Override
				public void onSubmit() {
					logger.debug("Delete pressed");
					deletePressed();
				}
			};

			saveButton.setDefaultFormProcessing(false);
			deleteButton.setDefaultFormProcessing(false);

			add(firstNameField);
			add(lastNameField);
			add(emailField);
			add(phoneField);
			add(saveButton);
			add(deleteButton);
			add(new FeedbackPanel("feedbackMessage", new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
			add(new FeedbackPanel("succesMessage", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));

		}

		public final void savePressed() {
			long startTime=System.currentTimeMillis();

			final String firstNameValue = firstNameField.getValue();
			logger.debug("editing contact:" + firstNameValue);

			final String lastNameValue = lastNameField.getValue();
			final String emailValue = emailField.getValue();
			final String phoneValue = phoneField.getValue();
			
			SKContact contact = new SKContact();
			contact.setFirstName(firstNameValue);
			contact.setLastName(lastNameValue);
			contact.setEmail(emailValue);
			contact.setPhone(phoneValue);

			DBUtil.saveContact(firstNameValue,contact);

			long endTime=System.currentTimeMillis();

			logger.info("Contact saved successfully:" + firstNameValue +" Timetaken:"+ (endTime-startTime));
			success("Congratulations contact:" + firstNameValue + " saved successfully!");

		}

		public final void deletePressed() {
			long startTime=System.currentTimeMillis();
			final String firstNameValue = firstNameField.getModelObject();
	
			logger.debug("Deleting contact:" + firstNameValue);
			long deleteStatus=DBUtil.deleteContact(firstNameValue);
			long endTime=System.currentTimeMillis();

			if(deleteStatus == 0) {
				error("Error: contact " + firstNameValue + " does not exist anymore!");
				logger.error("Delete operation on non-existant contact:" + firstNameValue +" Timetaken:"+ (endTime-startTime));

			}else {
				success("Congratulations: contact " + firstNameValue + " removed successfully!");
				logger.info("Contact deleted successfully:" + firstNameValue +" Timetaken:"+ (endTime-startTime));

			}
		}
	}
}
