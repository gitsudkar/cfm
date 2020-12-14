package com.sk.addressbook.wicket.login;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		FeedbackPanel feedbackPanel=new FeedbackPanel("feedback");

		public LoginForm(String id) {
			super(id);
			
			setDefaultModel(new CompoundPropertyModel(this));
			add(new RequiredTextField<String>("username"));
			add(new PasswordTextField("password"));
			add(feedbackPanel);
		}

		public final void onSubmit() {		
			boolean authNResult=AuthenticatedWebSession.get().signIn(username, password);
			
			if(authNResult) {
				info("Success: You are authenticated.");

				continueToOriginalDestination();
			}else {
				//loginStatus="Failure: Invalid userid/pwd.";
				error("Failure: Invalid userid/pwd.");
			}
		}
	}
}
