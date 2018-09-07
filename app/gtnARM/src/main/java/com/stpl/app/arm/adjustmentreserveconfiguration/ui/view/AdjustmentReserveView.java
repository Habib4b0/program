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
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The View Class for the Adjustment Reserve Configuration.
 *
 * @author 
 */
public class AdjustmentReserveView extends VerticalLayout implements View {

    /**
     * View Name For UI
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The SessionDTO
     */
    private SessionDTO sessionDTO;
    /**
     * The Logger For AdjustmentReserveUI Class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentReserveView.class);

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
     * @param reserveViewEvent
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent reserveViewEvent) {
        LOGGER.debug("Enters in the View with mode{}", sessionDTO.getMode());
        this.removeAllComponents();
        AdjustmentReserveSearchForm adjResSearchForm = new AdjustmentReserveSearchForm(sessionDTO);
        addComponent(adjResSearchForm);
    }

    @Override
    public boolean equals(Object adjResObj) {
        return super.equals(adjResObj);
    }

    @Override
    public int hashCode() {
        LOGGER.debug("Inside Adjustment Reserve View");
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream adjResOut) throws IOException {
        adjResOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream adjResIn) throws IOException, ClassNotFoundException {
        adjResIn.defaultReadObject();
    }

}
