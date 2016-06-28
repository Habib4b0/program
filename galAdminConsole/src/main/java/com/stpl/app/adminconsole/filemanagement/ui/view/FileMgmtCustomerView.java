/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.filemanagement.ui.form.FileMgmtCustomer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

public class FileMgmtCustomerView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "CustView";
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(FileManagementIndexView.class);
    SessionDTO sessionDTO;

    /**
     * Instantiates a new file management index view.
     */
    public FileMgmtCustomerView(final SessionDTO sessionDTO) {
        super();
        LOGGER.info("FileManagementIndexView Constructor Entered");
        addComponent(new FileMgmtCustomer());
          this.sessionDTO = sessionDTO;     
        LOGGER.info("FileManagementIndexView Constructor Ended");
    }

    /**
     * Event received by the listener for attempted and executed view changes.
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        markAsDirty();
    }
}
