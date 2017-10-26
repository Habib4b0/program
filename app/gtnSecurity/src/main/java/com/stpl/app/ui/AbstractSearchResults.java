package com.stpl.app.ui;

import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractSearchResults  extends CustomComponent{
	private static final long serialVersionUID = 1L;
	final Label space = new Label("&nbsp;", ContentMode.HTML);
	BeanItemContainer<?> searchResultbeans;
	Table table ;
	Object[] defaultColumns;
	protected VerticalLayout content;
	public AbstractSearchResults(BeanItemContainer<?> searchResultbeans,Table table,Object[] defaultColumns) {
		this.searchResultbeans = searchResultbeans;
		this.table = table;
		this.defaultColumns = defaultColumns;
		init();
	}

	private void init() {
		space.setHeight("20");
	}

	protected VerticalLayout addToContentAbstract() {
		final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		content.addComponent(space);
		content.addComponent(addToTable());
		content.addComponent(space);
		
		
		setCompositionRoot(content);
		return content;
	}

	protected Table addToTable() {
		table.markAsDirty();
		table.setContainerDataSource(searchResultbeans);
		table.setVisibleColumns(defaultColumns);

		table.setPageLength(NumericConstants.SEVEN);
		table.setImmediate(true);
		table.setSelectable(true);
		table.addItemClickListener(new ItemClickListener() {

			private static final long serialVersionUID = 1L;

			public void itemClick(ItemClickEvent event) {
				itemSelectLogic(event);
			}
		});

		return table;
	}
	/**
	 * Logic when clicking a row in the table
	 */

	protected abstract void itemSelectLogic(ItemClickEvent event);

}
