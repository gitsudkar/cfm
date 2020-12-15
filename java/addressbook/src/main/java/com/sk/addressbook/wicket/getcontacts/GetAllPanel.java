package com.sk.addressbook.wicket.getcontacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.bean.SKContact;
import com.sk.addressbook.db.DBUtil;
import com.sk.addressbook.wicket.editcontact.EditContactPage;
import com.sk.addressbook.wicket.validators.ExactErrorLevelFilter;

public class GetAllPanel extends Panel {
	private String filter;
	Logger logger = LoggerFactory.getLogger(GetAllPanel.class);

	public GetAllPanel(String id) {
		super(id);
		init();
	}

	private void init() {
		long startTime = System.currentTimeMillis();

		SearchableContactDataProvider searchableDataProvider = new SearchableContactDataProvider();

		ArrayList<IColumn> columns = new ArrayList<IColumn>();

		columns.add(new ClickablePropertyColumn<SKContact>(Model.of("FirstName"), "firstName") {
			@Override
			protected void onClick(IModel<SKContact> clicked) {
				logger.debug("you clicked :" + clicked.getObject().getFirstName());
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("firstName", clicked.getObject().getFirstName());
				setResponsePage(EditContactPage.class, pageParameters);
			}
		});
		columns.add(new PropertyColumn<SKContact, String>(Model.of("LastName"), "lastName", "lastName"));
		columns.add(new PropertyColumn<SKContact, String>(Model.of("Email"), "email", "email"));
		columns.add(new PropertyColumn<SKContact, String>(Model.of("Phone"), "phone", "phone"));
		columns.add(new PropertyColumn<SKContact, String>(Model.of("Address"), "address", "address"));

		Form<?> form = new Form<Void>("getAllForm");
		DataTable table = new DefaultDataTable("getAllTable", columns, searchableDataProvider, 10);
		TextField filterTextField = new TextField<String>("filter", new PropertyModel<String>(this, "filter"));

		form.add(filterTextField);

		add(form);
		add(table);
		add(new FeedbackPanel("succesMessage", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));

		long endTime = System.currentTimeMillis();
		logger.info("Search page initialized successfully. Timetaken:" + (endTime - startTime));

	}

	public class SearchableContactDataProvider extends SortableDataProvider<SKContact, String> {
		Logger logger = LoggerFactory.getLogger(SearchableContactDataProvider.class);
		private transient List<SKContact> filtered = new ArrayList<SKContact>();
		private List<SKContact> contacts = new ArrayList<SKContact>();

		private List<SKContact> getFiltered() {
			if (filtered == null) {
				filtered = filter();
			}
			
			return filtered;
		}

		private List<SKContact> filter() {
			// TODO Auto-generated method stub
			long startTime = System.currentTimeMillis();

			List<SKContact> filtered = new ArrayList<SKContact>(contacts);
			logger.debug("pre filteredList:" + filtered);

			if (filter == null) {
				filter = "";
			}

			String lower = filter.toLowerCase();

			Iterator<SKContact> itr = filtered.iterator();
			while (itr.hasNext()) {
				SKContact contact = itr.next();
				if (!(contact.getFirstName().toLowerCase().startsWith(lower))) {
					itr.remove();
				}
			}

			long endTime = System.currentTimeMillis();
			logger.info("Search filter completed successfully. Timetaken:" + (endTime - startTime));
			success("Click on FirstName to edit or delete the contact");
			
			return filtered;
		}

		public SearchableContactDataProvider() {
			long startTime = System.currentTimeMillis();

			setSort("firstName", SortOrder.ASCENDING);
			contacts = DBUtil.getAllContacts();

			long endTime = System.currentTimeMillis();
			logger.info("All contacts pulled successfully. Timetaken:" + (endTime - startTime));

		}

		@Override
		public void detach() {
			filtered = null;
			super.detach();
		}

		@Override
		public Iterator<? extends SKContact> iterator(long first, long count) {
			// TODO Auto-generated method stub
			List<? extends SKContact> filteredList = getFiltered();

			List<SKContact> data = new ArrayList<SKContact>(filteredList);

			Collections.sort(data, new Comparator<SKContact>() {
				public int compare(SKContact c1, SKContact c2) {
					return c1.getFirstName().compareTo(c2.getFirstName());
				}

			});

			return (Iterator<? extends SKContact>) data.subList((int) first, (int) Math.min(first + count, data.size()))
					.iterator();

		}

		@Override
		public long size() {
			// TODO Auto-generated method stub
			long size = getFiltered().size();
			logger.debug("getFiltered().size():" + size);

			return size;
		}

		@Override
		public IModel model(SKContact object) {
			// TODO Auto-generated method stub
			return Model.of(object);
		}

	}

}
