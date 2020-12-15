package com.sk.addressbook.wicket.login;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.wicket.validators.ExactErrorLevelFilter;
import com.sk.addressbook.wicket.validators.UserNameValidator;

public class LoginPanel extends Panel {
	Logger logger = LoggerFactory.getLogger(LoginPanel.class);
	
	public LoginPanel(String id) {
		super(id);		
		init();
	}
	
	private void init(){
		Form loginForm = new LoginForm("loginForm");
		add(loginForm);
	}
	
	public class LoginForm extends Form{
		private String username;
		private String password;

		
		
		public LoginForm(String id) {
			super(id);
			RequiredTextField<String> usernameField=new RequiredTextField<String>("username");
			setDefaultModel(new CompoundPropertyModel(this));
			
			usernameField.add(new UserNameValidator());
			
			add(usernameField);
			add(new PasswordTextField("password"));

			add(new FeedbackPanel("feedbackMessage", new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
			add(new FeedbackPanel("succesMessage", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));
		}

		public final void onSubmit() {		
			boolean authNResult=AuthenticatedWebSession.get().signIn(username, password);
			
			if(authNResult) {
				success("Success: You are authenticated.");
				continueToOriginalDestination();
			}else {
				error("Failure: Invalid userid or password.");
			}
		}
	}
}
