package com.sk.addressbook.wicket.getcontacts;

import com.sk.addressbook.wicket.template.JugTemplate;

public class GetAllContactPage extends JugTemplate {

	public GetAllContactPage() {
		super();
		replace(new GetAllPanel(CONTENT_ID));
		getMenuPanel().setVisible(true);
	}
}
