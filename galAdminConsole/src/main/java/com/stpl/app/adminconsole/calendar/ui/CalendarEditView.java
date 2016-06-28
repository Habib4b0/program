/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;


/**
 *
 * @author SYSBIZ\abishekram.r
 */
public class CalendarEditView extends VerticalLayout implements View {
    static String NAME="Edit";
    SessionDTO sessionDTO;

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    
    }

    public CalendarEditView(SessionDTO sessionDTO) throws PortalException, SystemException {
        this.sessionDTO =  sessionDTO;
         System.gc();
        CalendarIndex editCalendar=new CalendarIndex(sessionDTO,true);
        addComponent(editCalendar);
        editCalendar.calendarNameLbel.setVisible(false);
        editCalendar.calendarName.setVisible(false);
        editCalendar.resetBtn.setVisible(false);
        editCalendar.editBtn.setVisible(false);
        editCalendar.deleteBtn.setVisible(false);
        editCalendar.backBtn.setVisible(true);
        editCalendar.isFirst=true;
        editCalendar.viewLogic();
        
    }
    
    
}
