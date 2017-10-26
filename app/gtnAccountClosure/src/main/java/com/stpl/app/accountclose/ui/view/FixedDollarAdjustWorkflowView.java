/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.view;

import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.FixedDollarAdjustIndex;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_FIXED_DOLLAR_INDEX;
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
public class FixedDollarAdjustWorkflowView extends VerticalLayout implements View {

    /**
     * View name for navigation
     */
    public static final String NAME = "workflow";
    SessionDTO sessionDTO;
    /**
     * DTO object for DataSelection
     */
    FixedDollarDTO fixedDollarDTO = new FixedDollarDTO();
    private CustomFieldGroup fixedDollarBinder = new CustomFieldGroup(new BeanItem<FixedDollarDTO>(fixedDollarDTO));

    /**
     * Default constructor
     */
    public FixedDollarAdjustWorkflowView(SessionDTO sessionDTO) throws Exception {
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        this.sessionDTO = sessionDTO;
        addComponent(new FixedDollarAdjustIndex(INDICATOR_FIXED_DOLLAR_INDEX.getConstant(), fixedDollarDTO, fixedDollarBinder, sessionDTO));
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
         fixedDollarDTO = new FixedDollarDTO();
         fixedDollarBinder = new CustomFieldGroup(new BeanItem<FixedDollarDTO>(fixedDollarDTO));
    }
}
