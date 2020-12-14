package com.sk.addressbook.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ErrorPage404 extends WebPage{

		public ErrorPage404(final PageParameters params) {
			add( new Label("404", "Page Not Found"));
		}
}
