/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author mohamed.hameed
 */
public class ComponentInfo extends AbstractComponentInfo {

    @UiField("itemSearchGrid")
    GridLayout itemSearchGrid;
    @UiField("rsType")
    ComboBox rsType;
    @UiField("rsProgramType")
    public ComboBox rsProgramType;
    @UiField("rsCategory")
    public ComboBox rsCategory;
    @UiField("paymentFrequency")
    public ComboBox paymentFrequency;
    @UiField("rebatePlanLevel")
    public ComboBox rebatePlanLevel;

    public ComponentInfo(String indicator, SelectionDTO selection) {
        super(indicator, selection);
        setVisibleContent();
    }

    private void setVisibleContent() {
        itemSearchGrid.removeComponent(0, 0);
        itemSearchGrid.removeComponent(1, 0);
        List<Component> componentList = new ArrayList<>();
        java.util.Iterator<Component> itr = itemSearchGrid.iterator();
        while (itr.hasNext()) {
            componentList.add(itr.next());
        }
        itemSearchGrid.removeAllComponents();
        java.util.Iterator<Component> itrList = componentList.iterator();
        while (itrList.hasNext()) {
            itemSearchGrid.addComponent(itrList.next());
        }
        itemSearchGrid.replaceComponent(rsType_DTO, rsTypeText);
        itemSearchGrid.replaceComponent(rsProgramType_DTO, rsProgramTypeText);
        itemSearchGrid.replaceComponent(rsCategory_DTO, rsCategoryText);
        itemSearchGrid.replaceComponent(paymentFrequency_DTO, paymentFrequencyText);
        itemSearchGrid.replaceComponent(rebatePlanLevel_DTO, rebatePlanLevelText);
        setRsReadOnlyField(false);
        ComponentInfoDTO rsDTO = getComponentDto();
        rsTypeText.setValue(rsDTO.getRsType_Value());
        rsProgramTypeText.setValue(rsDTO.getRsProgramType_Value());
        rsCategoryText.setValue(rsDTO.getRsCategory_Value());
        paymentFrequencyText.setValue(rsDTO.getPaymentFrequency_Value());
        rebatePlanLevelText.setValue(rsDTO.getRebatePlanLevel_Value());
        setRsReadOnlyField(true);
    }

    private void setRsReadOnlyField(boolean isReadOnly) {
        rsTypeText.setReadOnly(isReadOnly);
        rsProgramTypeText.setReadOnly(isReadOnly);
        rsCategoryText.setReadOnly(isReadOnly);
        paymentFrequencyText.setReadOnly(isReadOnly);
        rebatePlanLevelText.setReadOnly(isReadOnly);
    }
}
