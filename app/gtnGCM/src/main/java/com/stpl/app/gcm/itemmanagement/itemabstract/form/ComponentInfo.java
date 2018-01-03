/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.vaadin.ui.ComboBox;
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
    private GridLayout itemSrGrid;
    @UiField("rsType")
    private ComboBox rsType;
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
        itemSrGrid.removeComponent(0, 0);
        itemSrGrid.removeComponent(1, 0);
        List<Component> componentList = new ArrayList<>();
        java.util.Iterator<Component> itr = itemSrGrid.iterator();
        while (itr.hasNext()) {
            componentList.add(itr.next());
        }
        itemSrGrid.removeAllComponents();
        java.util.Iterator<Component> itrList = componentList.iterator();
        while (itrList.hasNext()) {
            itemSrGrid.addComponent(itrList.next());
        }
        itemSrGrid.replaceComponent(rsType_DTO, rsTypeText);
        itemSrGrid.replaceComponent(rsProgramType_DTO, rsProgramTypeText);
        itemSrGrid.replaceComponent(rsCategory_DTO, rsCategoryText);
        itemSrGrid.replaceComponent(paymentFrequency_DTO, paymentFrequencyText);
        itemSrGrid.replaceComponent(rebatePlanLevel_DTO, rebatePlanLevelText);
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
