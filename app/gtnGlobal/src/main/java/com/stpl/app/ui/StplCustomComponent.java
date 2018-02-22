package com.stpl.app.ui;

import org.slf4j.LoggerFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;

/**
 * Class contains modified custom component.
 *
 * @author
 */
public class StplCustomComponent extends CustomComponent implements View {

	/**
	 * method over ridded while implementing View.
	 *
	 * @param event
	 *            ViewChangeEvent
	 */
	@Override
	public void enter(final ViewChangeEvent event) {

		LoggerFactory.getLogger(this.getClass()).debug("StplCustomCponent: Inside overriden method");
	}

}
