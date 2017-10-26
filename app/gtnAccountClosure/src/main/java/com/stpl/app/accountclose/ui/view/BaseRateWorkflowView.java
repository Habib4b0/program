/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.view;

import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.BaseRateIndex;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.*;

/**
 *
 * @author alok.v
 */
public class BaseRateWorkflowView extends VerticalLayout implements View {

    /**
     * View name for navigation
     */
    public static final String NAME = "workflow";
    SessionDTO sessionDTO;
    /**
     * DTO object for DataSelection
     */
    BaseRateDTO baseRateDTO = new BaseRateDTO();
    private CustomFieldGroup baseRateBinder = new CustomFieldGroup(new BeanItem<BaseRateDTO>(baseRateDTO));


    public BaseRateWorkflowView(SessionDTO sessionDTO) throws Exception {
        this.sessionDTO = sessionDTO;
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addComponent(new BaseRateIndex(INDICATOR_BASE_RATE_INDEX.getConstant(), baseRateDTO, baseRateBinder, sessionDTO));
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        baseRateDTO = new BaseRateDTO();
        baseRateBinder = new CustomFieldGroup(new BeanItem<BaseRateDTO>(baseRateDTO));
    }

}
