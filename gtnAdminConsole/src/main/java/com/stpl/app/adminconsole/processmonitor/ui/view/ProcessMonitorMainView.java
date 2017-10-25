/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.processmonitor.dto.ProcessMonitorDTO;
import com.stpl.app.adminconsole.processmonitor.ui.form.ProcessMonitorIndex;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha
 */
public class ProcessMonitorMainView extends VerticalLayout implements View {
    
  
    public static final String NAME = StringUtils.EMPTY;
    SessionDTO sessionDTO;
   
    ProcessMonitorDTO processMonitorDTO = new ProcessMonitorDTO();
    private CustomFieldGroup processMonitorBinder = new CustomFieldGroup(new BeanItem<ProcessMonitorDTO>(processMonitorDTO));

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        processMonitorDTO = new ProcessMonitorDTO();
        processMonitorBinder = new CustomFieldGroup(new BeanItem<ProcessMonitorDTO>(processMonitorDTO));
    }

    
    public ProcessMonitorMainView(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addComponent(new ProcessMonitorIndex(processMonitorDTO, processMonitorBinder, sessionDTO));
       

    }
    
}
