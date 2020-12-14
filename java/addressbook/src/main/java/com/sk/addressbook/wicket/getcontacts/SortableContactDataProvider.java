package com.sk.addressbook.wicket.getcontacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.bean.SKContact;
import com.sk.addressbook.db.DBUtil;

public class SortableContactDataProvider<String> extends SortableDataProvider<SKContact, String> {
	Logger logger = LoggerFactory.getLogger(SortableContactDataProvider.class);
	private List<SKContact> contacts = new ArrayList<SKContact>();

	public SortableContactDataProvider() {
		//setSort("firstName", SortOrder.ASCENDING);

		contacts = DBUtil.getAllContacts();

		logger.info("Size of collection:" + contacts.size());

	}

	@Override
	public Iterator<? extends SKContact> iterator(long first, long count) {
		// TODO Auto-generated method stub

		List<SKContact> data = new ArrayList<SKContact>(contacts);
		Collections.sort(data, new Comparator<SKContact>() {
			public int compare(SKContact c1, SKContact c2) {
				return c1.getFirstName().compareTo(c2.getFirstName());
			}

		});

		return data.subList((int) first, (int) data.size()).iterator();
	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		return contacts.size();
	}

	@Override
	public IModel model(SKContact object) {
		// TODO Auto-generated method stub
		return Model.of(object);
	}

}
