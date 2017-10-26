/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui.view;

import com.stpl.app.adminconsole.calendar.ui.CalendarSearchIndex;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Abishek.Ram
 */
public class CalendarSearchView extends VerticalLayout implements View {

    final SessionDTO sessionDTO;
    public static String NAME = "";
    CalendarSearchIndex csi;

    public CalendarSearchView(final SessionDTO sessionDTO) {
        csi = new CalendarSearchIndex(sessionDTO);
        this.sessionDTO = sessionDTO;
        addComponent(csi);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        csi.setToDefault();
    }

}
