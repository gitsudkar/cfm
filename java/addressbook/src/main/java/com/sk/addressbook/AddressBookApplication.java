package com.sk.addressbook;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import com.sk.addressbook.wicket.ErrorPage404;
import com.sk.addressbook.wicket.addcontact.AddContactPage;
import com.sk.addressbook.wicket.editcontact.EditContactPage;
import com.sk.addressbook.wicket.getcontacts.GetAllContactPage;
import com.sk.addressbook.wicket.login.LoginPage;
import com.sk.addressbook.wicket.login.session.SKSession;
import com.sk.addressbook.wicket.logout.LogoutPage;

@SpringBootApplication
@ComponentScan("com.cfm.addressbook")
public class AddressBookApplication extends AuthenticatedWebApplication {
	Logger logger = LoggerFactory.getLogger(AddressBookApplication.class);

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(AddressBookApplication.class).run(args);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return GetAllContactPage.class;
	}

	@Override
	public void init() {
		super.init();

		logger.info("**********************************initializing wicket**********************************");
		getCspSettings().blocking().disabled();
		logger.info("**********************************CSP disabled**********************************");

		mountPage("/addContact", AddContactPage.class);
		mountPage("/getAllContacts", GetAllContactPage.class);
		mountPage("/editContact", EditContactPage.class);
		mountPage("/login", LoginPage.class);
		mountPage("/logout", LogoutPage.class);
		mountPage("/404",ErrorPage404.class);
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		// TODO Auto-generated method stub
		logger.debug("inside getWebSessionClass");
		return SKSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		// TODO Auto-generated method stub
		logger.debug("inside getSignInPageClass");
		return LoginPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		// TODO Auto-generated method stub
		logger.debug("inside newSession");
		return new SKSession(request);
	}

}