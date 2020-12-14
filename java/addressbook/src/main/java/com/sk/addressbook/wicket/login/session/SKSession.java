package com.sk.addressbook.wicket.login.session;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.db.DBUtil;

public class SKSession extends AuthenticatedWebSession {
	Logger logger = LoggerFactory.getLogger(SKSession.class);

	public SKSession(Request request) {

		super(request);
		logger.info("inside SKsession constr"); ;

	}

	@Override
	protected boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		logger.info("inside SKsession authenticate"); ;

		return DBUtil.validateCreds(username, password);
	}

	@Override
	public Roles getRoles() {
		// TODO Auto-generated method stub
		if (isSignedIn()) {
			return new Roles(Roles.ADMIN);
		}
		return null;
	}
	
}
