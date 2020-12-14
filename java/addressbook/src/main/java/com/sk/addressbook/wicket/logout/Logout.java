package com.sk.addressbook.wicket.logout;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.wicket.login.LoginPanel;

public class Logout extends WebPage{
	Logger logger = LoggerFactory.getLogger(LoginPanel.class);

	public Logout(final PageParameters parameters)
	{
		logger.info("Invalidating session");
		getSession().invalidate();
	}
}
