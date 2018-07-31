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
        String newValue = StringUtils.EMPTY;
        for (int i = 0; i < inputColumIds.size(); i++) {
            AbstractField<Object> vaadinField = (AbstractField<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(resultTableId.get(i), inputColumIds.get(i));
            newValue = newValue + getDate(vaadinField.getValue());
        }
        newValue = newValue.substring(2);
        AbstractField<Object> vaadinField = (AbstractField<Object>) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponentFromParent(outputFieldId, componentId).getComponent();
        if (vaadinField != null && newValue != null && !"null".equals(String.valueOf(newValue))) {
            boolean isReadOnly = vaadinField.isReadOnly();
            vaadinField.setReadOnly(false);
            vaadinField.setValue(String.valueOf(newValue));
            vaadinField.setReadOnly(isReadOnly);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

    private String getDate(Object value) {
        Date date = (Date) value;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (date != null) {
            return " - " + simpleDateFormat.format(date);
        }
        return StringUtils.EMPTY;
    }
}
