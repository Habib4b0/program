package com.stpl.gtn.gtn2o.sharedlibrary;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class GtnNewArchSharedLibraryUI extends UI {
	protected static BeanItemContainer<GTNSharedLibraryInfoDTO> container = new BeanItemContainer<>(
			GTNSharedLibraryInfoDTO.class);

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(false);
		setContent(layout);
		Table table = new Table("GTN Shared Library Information");
		table.setImmediate(true);
		table.setContainerDataSource(container);
		table.setVisibleColumns("libraryName", "jarName", "jarPath");
		table.setColumnHeaders("Library Name", "Jar Name", "Jar Path");
		layout.addComponent(table);
		table.setPageLength(20);
		table.setSizeFull();
	}
}
