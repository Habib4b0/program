package com.stpl.app.ui;

import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.event.ItemClickEvent.ItemClickListener;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;

public abstract class AbstractSearchResults  extends CustomComponent{
	private static final long serialVersionUID = 1L;
	private final Label space = new Label("&nbsp;", ContentMode.HTML);
	private final BeanItemContainer<?> searchResultbeans;
	private final Table table ;
	private final Object[] defaultColumns;
	protected VerticalLayout content;
	public AbstractSearchResults(BeanItemContainer<?> searchResultbeans,Table table,Object[] defaultColumns) {
		this.searchResultbeans = searchResultbeans;
		this.table = table;
		this.defaultColumns = defaultColumns == null ? defaultColumns : defaultColumns.clone();
		init();
	}

	private void init() {
		space.setHeight("20");
	}

	protected VerticalLayout addToContentAbstract() {
		final VerticalLayout vContent = new VerticalLayout();
		vContent.addComponentAsFirst(space);
		vContent.addComponent(space);
		vContent.addComponent(addToTable());
		vContent.addComponent(space);
		
		
		setCompositionRoot(vContent);
		return vContent;
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

                        @Override
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
