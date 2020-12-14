package com.sk.addressbook.wicket.addcontact;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import com.sk.addressbook.wicket.template.JugTemplate;

@AuthorizeInstantiation("ADMIN")
public class AddContactPage extends JugTemplate {

	public AddContactPage() {
		super();
		replace(new AddPanel(CONTENT_ID));
		getMenuPanel().setVisible(true);
	}
}
