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
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Mohamed.Shahul
 */
public class AccountConfigView extends VerticalLayout implements View {

    /**
     * The AccountConfigView for Search Mode.
     */
    SearchAccountConfig searchAccountConfig;

    /**
     * View Name For UI
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The SessionDTO
     */
    SessionDTO sessionDTO = new SessionDTO();
    /**
     * The Logger For AdjustmentReserveUI Class
     */
    private static final Logger LOGGER = Logger.getLogger(AccountConfigView.class);

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
            LOGGER.info("Enters in the View with mode" + sessionDTO.getMode());
            this.removeAllComponents();
            searchAccountConfig = new SearchAccountConfig(sessionDTO);
            addComponent(searchAccountConfig);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

}
