package com.sk.addressbook.wicket.logout;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.wicket.getcontacts.GetAllContactPage;

public class LogoutPanel extends Panel {
	Logger logger = LoggerFactory.getLogger(LogoutPanel.class);

	public LogoutPanel(String id) {
		super(id);
		init();
	}

	private void init() {
		AuthenticatedWebSession.get().invalidate();
		logger.info("Invalidated Session, sending user to signin page");
		setResponsePage(GetAllContactPage.class);
	}

}
