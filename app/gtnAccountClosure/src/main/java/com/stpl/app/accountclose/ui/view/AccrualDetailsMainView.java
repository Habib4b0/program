/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.view;

import com.stpl.app.accountclose.dto.AccrualDetailsDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.AccrualDetailsIndex;
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
public class AccrualDetailsMainView extends VerticalLayout implements View {
    /**
     * View name for navigation
     */
    public static final String NAME = StringUtils.EMPTY;
    SessionDTO sessionDTO;
    /**
     * DTO object for DataSelection
     */
    AccrualDetailsDTO accrualDetailsDTO = new AccrualDetailsDTO();
    private CustomFieldGroup accrualDetailsBinder = new CustomFieldGroup(new BeanItem<AccrualDetailsDTO>(accrualDetailsDTO));

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        accrualDetailsDTO = new AccrualDetailsDTO();
        accrualDetailsBinder = new CustomFieldGroup(new BeanItem<AccrualDetailsDTO>(accrualDetailsDTO));
    }

    /**
     * Default constructor
     */
    public AccrualDetailsMainView() throws Exception {
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addComponent(new AccrualDetailsIndex(INDICATOR_PROCESS_SCHEDULER_INDEX.getConstant(), accrualDetailsDTO, accrualDetailsBinder));
       
    }
}
