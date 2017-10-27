
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author Rohit.Vignesh
 */
public class AdjustmentDetailsFilterGenerator implements ExtFilterGenerator {

    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AdjustmentDetailsFilterGenerator.class);
    CommonLogic commonLogic = new CommonLogic();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
            return null;
        }

    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField.getValue() != null) {
        if (!String.valueOf(originatingField.getValue()).equals(ConstantUtil.SHOW_ALL)) {
            new SimpleStringFilter(propertyId, originatingField.getValue().toString(), false, false);
        } else {
            new SimpleStringFilter(propertyId, "%", false, false);
        }
        } else {
            return null;
        }
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {
                if (propertyId.toString().equals("workflowCreatedBy") || propertyId.toString().equals("workflowApprovedBy")) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
        return null;
    }

    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            Map<Integer, String> userMap = StplSecurity.getUserName();
            ComboBox comboBox1;
            if ("workflowCreatedBy".equals(propertyId)) {
                try {
                    comboBox1 = new ComboBox();
                    comboBox1.addItem(0);
                    comboBox1.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox1.addItem(entry.getKey());
                        comboBox1.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox1.setNullSelectionAllowed(true);
                    comboBox1.setNullSelectionItemId(0);
                    return comboBox1;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("workflowApprovedBy".equals(propertyId)) {
                try {
                    comboBox1 = new ComboBox();
                    comboBox1.addItem(0);
                    comboBox1.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox1.addItem(entry.getKey());
                        comboBox1.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox1.setNullSelectionAllowed(true);
                    comboBox1.setNullSelectionItemId(0);
                    return comboBox1;

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

                    }

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return null;

    }

    public void filterRemoved(Object propertyId) {
        //To change body of generated methods, choose Tools | Templates.
    }

    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        //To change body of generated methods, choose Tools | Templates.
    }

    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        //To change body of generated methods, choose Tools | Templates.
        return null;
    }

}
