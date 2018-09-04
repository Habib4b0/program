/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.ui.view;

import com.stpl.app.adminconsole.projnameconfig.ui.form.ProjNameConfigAddForm;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
/**
 * The Class ProjNameConfigAddView.
 *
 * @author santanukumar
 */
public class ProjNameConfigAddView extends VerticalLayout implements View{
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjNameConfigAddView.class);
     /**
     * The Constant NAME.
     */
    public static final String NAME = "Add";
    
    /** The add form reference. */
    private ProjNameConfigAddForm configAddForm = new ProjNameConfigAddForm();
    
    /**
     * Instantiates a new  view.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ProjNameConfigAddView() throws PortalException {
        
        super();
        LOGGER.debug("ProjNameConfigAddView Constructor method is started");
        addComponent(configAddForm);
        setSpacing(true);
        setStyleName("bootstrap");
        LOGGER.debug("ProjNameConfigAddView Constructor method is started");

    }

    /* (non-Javadoc)
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setStyleName("bootstrap");
        try {
            this.removeAllComponents();
            addComponent(configAddForm);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
