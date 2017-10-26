/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.view;
import com.stpl.app.accountclose.dto.ProcessDTO;
import com.stpl.app.accountclose.dto.ProcessSchedulerDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.ProcessSchedulerIndex;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PROCESS_SCHEDULER_INDEX;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class ProcessSchedulerMainView extends VerticalLayout implements View {

    /**
     * View name for navigation
     */
    public static final String NAME = StringUtils.EMPTY;
    SessionDTO sessionDTO;
    /**
     * DTO object for DataSelection
     */
    ProcessDTO processDTO = new ProcessDTO();
    private CustomFieldGroup processSchedulerBinder = new CustomFieldGroup(new BeanItem<ProcessDTO>(processDTO));

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        processDTO = new ProcessDTO();
        processSchedulerBinder = new CustomFieldGroup(new BeanItem<ProcessDTO>(processDTO));
    }

    /**
     * Default constructor
     */
     
    public ProcessSchedulerMainView() throws Exception {
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addComponent(new ProcessSchedulerIndex(INDICATOR_PROCESS_SCHEDULER_INDEX.getConstant(), processDTO, processSchedulerBinder));
    }
}
