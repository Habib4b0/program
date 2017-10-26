package com.stpl.ifs.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;

/**
 * Gives abstract design of Tabbed view
 * @author shrihariharan
 */
public interface AbstractDetailForm  extends  View{
        /**
         * Adds the component
         */
        public void init();
        /**
         * Constructs the Tabs
         * @return TabSheet 
         */
        public abstract TabSheet addTabSheet();
        /**
         * Defines logic for save, delete and back button.
         * @return AbstractActionButtonLayout
         */
	public abstract AbstractActionButtonLayout addActionBtn();
        /**
         * Assign properties to the component added 
         */
	public abstract void configureFields();
        /**
         * Assigns values to the Component while entering the view
         * @param event 
         */
	public void enter(ViewChangeEvent event);
}
