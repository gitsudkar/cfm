package com.sk.addressbook.wicket.login;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.WebPage;

import com.sk.addressbook.wicket.editcontact.EditPanel;
import com.sk.addressbook.wicket.template.JugTemplate;

public class LoginPage extends JugTemplate {

	public LoginPage() {
		super();
		replace(new LoginPanel(CONTENT_ID));
		getMenuPanel().setVisible(true);
	}
}
