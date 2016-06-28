/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.ui.view;

import com.stpl.app.adminconsole.projnameconfig.ui.form.ProjNameConfigAddForm;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjNameConfigAddView.
 *
 * @author santanukumar
 */
public class ProjNameConfigAddView extends VerticalLayout implements View{
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProjNameConfigAddView.class);
     /**
     * The Constant NAME.
     */
    public static final String NAME = "Add";
    
    /** The add form reference. */
    private ProjNameConfigAddForm configAddForm;
    
    /**
     * Instantiates a new  view.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ProjNameConfigAddView() throws SystemException, PortalException, Exception {
        
        super();
        LOGGER.info("ProjNameConfigAddView Constructor method is started");
        configAddForm = new ProjNameConfigAddForm();
        addComponent(configAddForm);
        setSpacing(true);
        setStyleName("bootstrap");
        LOGGER.info("ProjNameConfigAddView Constructor method is started");

    }

    /* (non-Javadoc)
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setStyleName("bootstrap");
        try {
            this.removeAllComponents();
            configAddForm = new ProjNameConfigAddForm();
            addComponent(configAddForm);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
