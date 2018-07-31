/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.inlinedate;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.v7.ui.InlineDateField;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkInlineDateComponent implements GtnUIFrameworkComponent {

    @Override
    public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) throws GtnFrameworkGeneralException {
        InlineDateField dateField = new InlineDateField(componentConfig.getComponentName());
        loadStyles(dateField, componentConfig.getComponentStyle());
        dateField.setVisible(componentConfig.isVisible());
        dateField.setId(componentConfig.getComponentId());
        dateField.setResolution(Resolution.MONTH);
        dateField.setEnabled(componentConfig.isEnable());
        dateField.setValue((Date) componentConfig.getComponentValue());
        GtnUIFrameworkInlineDateFieldConfig dateFieldConfig = componentConfig.getGtnUIFrameworkInlineDateFieldConfig();
        if (dateFieldConfig != null) {
            dateField.setRequired(dateFieldConfig.isRequired());
            dateField.setRequiredError(dateFieldConfig.getRequiredMessage());
            dateField.setEnabled(dateFieldConfig.isEnable());
            dateField.setImmediate(dateFieldConfig.isImmediate());
        }
        return dateField;
    }

    private void loadStyles(final Component component, final List<String> styles) {
        if (styles != null) {
            for (String style : styles) {
                component.addStyleName(style);
            }
        }
    }

    @Override
    public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId, Object reloadInput) {
        return;
    }

    @Override
    public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
        InlineDateField dateField = (InlineDateField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
        dateField.setVisible(componentConfig.isVisible());
        dateField.setDateFormat("MM/dd/yyyy");
        dateField.setEnabled(componentConfig.isEnable());
        GtnUIFrameworkInlineDateFieldConfig dateFieldConfig = componentConfig.getGtnUIFrameworkInlineDateFieldConfig();
        if (dateFieldConfig != null) {
            dateField.setRequired(dateFieldConfig.isRequired());
            dateField.setRequiredError(dateFieldConfig.getRequiredMessage());
            dateField.setEnabled(dateFieldConfig.isEnable());
            dateField.setImmediate(dateFieldConfig.isImmediate());
            dateField.removeAllValidators();
        }
        dateField.setValue((Date) componentConfig.getComponentValue());
    }
}
