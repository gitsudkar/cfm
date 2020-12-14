package com.sk.addressbook.wicket.login.session;

import java.util.concurrent.atomic.AtomicBoolean;

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
		logger.debug("inside SKsession constr"); ;

	}
	
	protected boolean authenticate(final String username,final String password) {
		// TODO Auto-generated method stub
		long startTime=System.currentTimeMillis();
		boolean authNResult=false;
		logger.debug("inside SKsession authenticate");
		
		authNResult=DBUtil.validateCreds(username, password);
		long endTime=System.currentTimeMillis();
		logger.info("AuthN call for user:" + username +""+ (authNResult?": Succeeded":":Failed ") + "TimeTaken:"+ (endTime-startTime));

		return authNResult;
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
