/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.view;

import com.stpl.app.accountclose.dto.ProcessMonitorDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.ProcessMonitorIndex;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PROCESS_SCHEDULER_INDEX;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mihirkumar.b
 */
public class ProcessMonitorMainView extends VerticalLayout implements View{
      /**
     * View name for navigation
     */
    public static final String NAME = StringUtils.EMPTY;
    SessionDTO sessionDTO;
    /**
     * DTO object for DataSelection
     */
    ProcessMonitorDTO processMonitorDTO = new ProcessMonitorDTO();
    private CustomFieldGroup processMonitorBinder = new CustomFieldGroup(new BeanItem<ProcessMonitorDTO>(processMonitorDTO));

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        processMonitorDTO = new ProcessMonitorDTO();
        processMonitorBinder = new CustomFieldGroup(new BeanItem<ProcessMonitorDTO>(processMonitorDTO));
    }

    /**
     * Default constructor
     */
    public ProcessMonitorMainView() throws Exception {
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addComponent(new ProcessMonitorIndex(processMonitorDTO, processMonitorBinder));
    }
}
