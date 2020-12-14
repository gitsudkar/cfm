package com.sk.addressbook.wicket.logout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.wicket.template.JugTemplate;

public class LogoutPage extends JugTemplate{
	Logger logger = LoggerFactory.getLogger(LogoutPage.class);

	public LogoutPage() {
		super();
		replace(new LogoutPanel(CONTENT_ID));
		getMenuPanel().setVisible(true);
	}
	
}
