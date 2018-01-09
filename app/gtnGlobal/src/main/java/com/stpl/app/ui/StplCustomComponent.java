package com.stpl.app.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import org.jboss.logging.Logger;


/**
 * Class contains modified custom component.
 *
 * @author 
 */
public class StplCustomComponent extends CustomComponent implements View {

    /**
     * method over ridded while implementing View.
     *
     * @param event ViewChangeEvent
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        Logger vLog = Logger.getLogger(StplCustomComponent.class);
        vLog.debug ("StplCustomCponent: Inside overriden method");
    }

}
