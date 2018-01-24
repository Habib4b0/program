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
import com.vaadin.v7.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileMgmtCustomerView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "CustView";
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(FileManagementIndexView.class);
    private SessionDTO sessionDTO;

    /**
     * Instantiates a new file management index view.
     */
    public FileMgmtCustomerView(final SessionDTO sessionDTO) {
        super();
        LOGGER.debug("FileManagementIndexView Constructor Entered");
        addComponent(new FileMgmtCustomer());
          this.setSessionDTO(sessionDTO);     
        LOGGER.debug("FileManagementIndexView Constructor Ended");
    }

    /**
     * Event received by the listener for attempted and executed view changes.
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        markAsDirty();
    }

	public SessionDTO getSessionDTO() {
		return sessionDTO;
	}

	public void setSessionDTO(SessionDTO sessionDTO) {
		this.sessionDTO = sessionDTO;
	}
}
