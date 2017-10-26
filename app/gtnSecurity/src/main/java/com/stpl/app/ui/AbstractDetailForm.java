package com.stpl.app.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;

public interface AbstractDetailForm  extends  View{
	public void init();
	public abstract TabSheet addTabSheet();
	public abstract AbstractActionButtonLayout addActionBtn();
	public abstract void configureFields();
	public void enter(ViewChangeEvent event);
}
