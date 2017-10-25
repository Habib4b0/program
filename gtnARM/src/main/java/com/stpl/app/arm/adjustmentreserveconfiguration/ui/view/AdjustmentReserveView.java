/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.view;

import com.stpl.app.arm.adjustmentreserveconfiguration.ui.form.AdjustmentReserveSearchForm;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;


/**
 * The View Class for the Adjustment Reserve Configuration.
 *
 * @author sathyaseelan.v
 */
public class AdjustmentReserveView extends VerticalLayout implements View {

    /**
     * The Adjustment Reserve Configuration Form for Search Mode.
     */
    AdjustmentReserveSearchForm adjustmentReserveSearchForm;
  
    /**
     * View Name For UI
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The SessionDTO
     */
    SessionDTO sessionDTO;
    /**
     * The Logger For AdjustmentReserveUI Class
     */
    private static final Logger LOGGER = Logger.getLogger(AdjustmentReserveView.class);

    /**
     * The Adjustment Reserve View Constructor
     *
     * @param sessionDTO
     */
    public AdjustmentReserveView(SessionDTO sessionDTO) {
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
        LOGGER.debug("Enters in the View with mode" + sessionDTO.getMode());
        this.removeAllComponents();
        adjustmentReserveSearchForm = new AdjustmentReserveSearchForm(sessionDTO);
        addComponent(adjustmentReserveSearchForm);
    }

}
