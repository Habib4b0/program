/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui.view;

import com.stpl.app.adminconsole.calendar.createcalendar.logic.dto.CalendarDetailsDTO;
import com.stpl.app.adminconsole.calendar.ui.CalendarSaveIndex;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Abishek.Ram
 */
public class CalendarSaveView extends VerticalLayout implements View {

    final SessionDTO sessionDTO;
    public static String NAME = "save";
    public CalendarDetailsDTO calendarDetailsDTO;
    String mod;

    public CalendarSaveView(final SessionDTO sessionDTO, CalendarDetailsDTO calendarDetailsDTO, String mode) {
        this.sessionDTO = sessionDTO;
        this.calendarDetailsDTO = calendarDetailsDTO;
        this.mod = mode;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new CalendarSaveIndex(sessionDTO, calendarDetailsDTO, mod));
    }

}
