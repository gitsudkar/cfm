package com.sk.addressbook.wicket.login;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.wicket.login.session.SKSession;

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
		private String loginStatus;
		
		public LoginForm(String id) {
			super(id);
			
			setDefaultModel(new CompoundPropertyModel(this));
			
			add(new TextField("username"));
			add(new PasswordTextField("password"));
			
		}

		public final void onSubmit() {		
			SKSession.get();
			
		}
	}
}
