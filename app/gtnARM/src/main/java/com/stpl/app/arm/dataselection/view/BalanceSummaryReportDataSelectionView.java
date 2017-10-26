/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.view;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.ui.form.BalanceSummaryReportDataSelection;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportDataSelectionView extends VerticalLayout implements View {

    SessionDTO sessionDTO;
    public static final String NAME = StringUtils.EMPTY;
    public String screenName;

    public BalanceSummaryReportDataSelectionView(SessionDTO sessionDTO, String screenName) {
        super();
        this.sessionDTO = sessionDTO;
        this.screenName = screenName;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        setSpacing(true);
        BalanceSummaryReportDataSelection dataSelection = new BalanceSummaryReportDataSelection(screenName, sessionDTO);
        addComponent(dataSelection);
    }

}
