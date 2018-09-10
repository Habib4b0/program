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
        itemSrGrid.replaceComponent(rsTypeDTO, rsTypeText);
        itemSrGrid.replaceComponent(rsProgramTypeDTO, rsProgramTypeText);
        itemSrGrid.replaceComponent(rsCategoryDTO, rsCategoryText);
        itemSrGrid.replaceComponent(paymentFrequencyDTO, paymentFrequencyText);
        itemSrGrid.replaceComponent(rebatePlanLevelDTO, rebatePlanLevelText);
        setRsReadOnlyField(false);
        ComponentInfoDTO rsDTO = getComponentDto();
        rsTypeText.setValue(rsDTO.getRsTypeValue());
        rsProgramTypeText.setValue(rsDTO.getRsProgramTypeValue());
        rsCategoryText.setValue(rsDTO.getRsCategoryValue());
        paymentFrequencyText.setValue(rsDTO.getPaymentFrequencyValue());
        rebatePlanLevelText.setValue(rsDTO.getRebatePlanLevelValue());
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
