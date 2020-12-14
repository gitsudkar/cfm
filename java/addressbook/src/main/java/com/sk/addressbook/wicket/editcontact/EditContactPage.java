package com.sk.addressbook.wicket.editcontact;

import com.sk.addressbook.wicket.template.JugTemplate;

public class EditContactPage extends JugTemplate {

	public EditContactPage() {
		super();
		replace(new EditPanel(CONTENT_ID));
		getMenuPanel().setVisible(true);
	}
}
