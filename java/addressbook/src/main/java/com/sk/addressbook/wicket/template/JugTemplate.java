package com.sk.addressbook.wicket.template;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class JugTemplate extends WebPage{
	public static final String CONTENT_ID = "contentComponent";

	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;

	
	public JugTemplate() {
		headerPanel = new HeaderPanel("headerPanel");
		menuPanel = new MenuPanel("menuPanel");
		footerPanel = new FooterPanel("footerPanel");
		
		add(headerPanel);
		add(menuPanel);
		add(footerPanel);
		add(new Label(CONTENT_ID, "put your content here"));
	}

	// getters for layout areas
	protected Component getHeaderPanel() {
		return headerPanel;
	}

	protected Component getMenuPanel() {
		return menuPanel;
	}

	protected Component getFooterPanel() {
		return footerPanel;
	}
}
