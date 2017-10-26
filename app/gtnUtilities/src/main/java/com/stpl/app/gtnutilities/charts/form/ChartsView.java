/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.form;

import com.stpl.app.gtnutilities.util.Constants;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 *
 * @author Karthik.Raja
 */
public class ChartsView extends VerticalLayout implements View {

    /**
     * String name.
     */
    public static final String NAME = Constants.EMPTY;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor reference for chartsForm.
     */
    private ChartsForm chartsForm;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ChartsForm.class); 

    /**
     * constructor charts view.
     */
    public ChartsView()  {
      
        super();
        chartsForm = new ChartsForm();
        setSpacing(true);
        addComponent(chartsForm);
        setStyleName(Constants.BOOTSTRAP);

    }

    /**
     * enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        try {
            this.removeAllComponents();
            chartsForm = new ChartsForm();
            addComponent(chartsForm);

        } catch (Exception ex) {
            LOGGER.debug(ex);
        }
    }
}
