/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.v7.ui.AbstractField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkPopupInlineDateSelectAction implements GtnUIFrameWorkAction {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> selectParam = gtnUIFrameWorkActionConfig.getActionParameterList();
        List<String> resultTableId = (List<String>) selectParam.get(0);
        List<String> inputColumIds = (List<String>) selectParam.get(1);
        String outputFieldId = selectParam.get(2).toString();
        StringBuilder newValue = new StringBuilder();
        for (int i = 0; i < inputColumIds.size(); i++) {
            AbstractField<Object> vaadinField = (AbstractField<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(resultTableId.get(i), inputColumIds.get(i));
            newValue.append(getDate((Date) vaadinField.getValue()));
        }
        String dateValue = newValue.substring(2);
        AbstractField<Object> vaadinField = (AbstractField<Object>) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponentFromParent(outputFieldId, componentId).getComponent();
        if (vaadinField != null && dateValue != null && !"null".equals(String.valueOf(dateValue))) {
            boolean isReadOnly = vaadinField.isReadOnly();
            vaadinField.setReadOnly(false);
            vaadinField.setValue(String.valueOf(dateValue));
            vaadinField.setReadOnly(isReadOnly);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

    private String getDate(Date value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (value != null) {
            return " - " + simpleDateFormat.format(value);
        }
        return StringUtils.EMPTY;
    }
}
