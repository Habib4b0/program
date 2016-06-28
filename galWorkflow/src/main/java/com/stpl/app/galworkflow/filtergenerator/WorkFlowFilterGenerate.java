/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.filtergenerator;

import com.stpl.app.galworkflow.util.CommonUtils;
import static com.stpl.app.galworkflow.util.CommonUtils.getUserMap;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author manikandaprabu.g
 */
public class WorkFlowFilterGenerate implements ExtFilterGenerator {

    private static final Logger LOGGER = Logger.getLogger(WorkFlowFilterGenerate.class);
    CommonUtils commonUtil = new CommonUtils();

    public WorkFlowFilterGenerate() {
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        try {
            if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy") || propertyId.toString().equals("approvedBy")) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                } else {
                    return null;
                }
            } else if (originatingField instanceof ComboBox) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
                } else {
                    return null;
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {

        try {
            final ComboBox comboBox = new ComboBox();
            switch (propertyId.toString()) {
                case "createdBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Long, String> entry : getUserMap().entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                case "modifiedBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Long, String> entry : getUserMap().entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                case "approvedBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Long, String> entry : getUserMap().entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                case "workFlowStatus":
                    commonUtil.loadComboBox(comboBox, CommonUtils.WORKFLOW_STATUS, true);
                    return comboBox;
                default:
                    return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

}
