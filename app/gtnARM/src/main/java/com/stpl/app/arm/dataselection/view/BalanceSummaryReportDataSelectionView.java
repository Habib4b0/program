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
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportDataSelectionView extends VerticalLayout implements View {

    private SessionDTO sessionDTO;
    public static final String NAME = StringUtils.EMPTY;
    private String screenName;

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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
