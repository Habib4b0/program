/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.view;

import com.stpl.app.arm.accountconfiguration.form.SearchAccountConfig;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class AccountConfigView extends VerticalLayout implements View {

    /**
     * View Name For UI
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The SessionDTO
     */
    private SessionDTO sessionDTO = new SessionDTO();
    /**
     * The Logger For AdjustmentReserveUI Class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountConfigView.class);

    /**
     * The AccountConfigView Constructor
     *
     * @param sessionDTO
     */
    public AccountConfigView(SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
    }

    /**
     * The Override Method for the View Change
     *
     * @param event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            LOGGER.info("Enters in the View with mode{}", sessionDTO.getMode());
            this.removeAllComponents();
            SearchAccountConfig searchAccountConfig = new SearchAccountConfig(sessionDTO);
            addComponent(searchAccountConfig);
        } catch (Exception ex) {
            LOGGER.error("Error in AccountConfigView :", ex);
        }
    }

    @Override
    public boolean equals(Object viewAccObj) {
        return super.equals(viewAccObj);
    }

    @Override
    public int hashCode() {
        LOGGER.debug("Enters the AccountConfig View Hashcode");
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream viewAccOut) throws IOException {
        viewAccOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream viewAccountIn) throws IOException, ClassNotFoundException {
        viewAccountIn.defaultReadObject();
    }
}
