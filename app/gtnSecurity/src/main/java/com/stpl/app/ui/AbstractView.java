package com.stpl.app.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.v7.ui.VerticalLayout;

public abstract class AbstractView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	public AbstractView(){		
		setSpacing(true);
		setComponentError(new UserError(""));
	}
        @Override
	public void enter(ViewChangeEvent event) {
		
	}
	protected abstract void addComponents();
	

}
