package com.stpl.gtn.gtn2o.ui.framework.component.grid.paged;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

@Title("GridExtensionPack Add-on Demo")
@SuppressWarnings("serial")

public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.vaadin.DefaultWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		TabSheet tabSheet = new TabSheet();
		tabSheet.addTab(new PagedGridUI(), "PagedGrid");
		// tabSheet.addTab(new GridWithMapObjects(), "Grid using Map");
		tabSheet.addTab(new PagedTreeGridUI(), "PagedTreeGrid");

		tabSheet.setSizeFull();
		setContent(tabSheet);
	}
}
