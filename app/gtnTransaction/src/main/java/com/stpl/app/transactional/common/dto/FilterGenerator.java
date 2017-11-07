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
 * @author sooriya.lakshmanan
 */
public class FilterGenerator implements ExtFilterGenerator {
   public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(FilterGenerator.class);
    CommonLogic commonLogic = new CommonLogic();
    String tableName;

    public FilterGenerator(String tableName) {
        this.tableName = tableName;
    }

    public Container.Filter generateFilter(Object propertyId, Object value) {
        if (ConstantUtil.IS_ACTIVE.equals(propertyId)||(ConstantUtil.POSTING_INDICATOR).equals(propertyId)) {
            if (!String.valueOf(value).equals(ConstantUtil.SHOW_ALL)) {
                return new SimpleStringFilter(propertyId, value.toString(), false, false);
            } else {
                return new SimpleStringFilter(propertyId, "%", false, false);
            }
        }
        return null;
    }

    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        SimpleStringFilter s=null;
        if (ConstantUtil.IS_ACTIVE.equals(propertyId)||(ConstantUtil.POSTING_INDICATOR).equals(propertyId)) {
            if (!String.valueOf(originatingField.getValue()).equals(ConstantUtil.SHOW_ALL)) {
               s= new SimpleStringFilter(propertyId, originatingField.getValue().toString(), false, false);
            } else {
               s= new SimpleStringFilter(propertyId, "%", false, false);
            }
            return s;
        }
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {
                if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy")) {
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
        if ("status".equals(propertyId)) {
            if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
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
        if ("isActive".equals(propertyId)||(ConstantUtil.POSTING_INDICATOR).equals(propertyId)) {
            final ComboBox isActive = new ComboBox();
            isActive.setNullSelectionAllowed(true);
            isActive.setNullSelectionItemId(ConstantUtil.SHOW_ALL);
            isActive.addItem(ConstantUtil.SHOW_ALL);
            isActive.addItem(ConstantUtil.YES);
            isActive.addItem(ConstantUtil.NO);
            isActive.select(ConstantUtil.SHOW_ALL);
            isActive.setImmediate(true);
            return isActive;
        } else if ("deductionCategory".equals(propertyId)) {

            try {
                final ComboBox deductionCategory = new ComboBox();
                deductionCategory.setNullSelectionAllowed(true);
                deductionCategory.setNullSelectionItemId(ConstantUtil.SHOW_ALL);
                commonLogic.loadComboBox(deductionCategory, "RS_CATEGORY");
                deductionCategory.select(ConstantUtil.SHOW_ALL);
                deductionCategory.setImmediate(true);
                return deductionCategory;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else if ("deductionType".equals(propertyId)) {
            try {
                final ComboBox deductionType = new ComboBox();
                deductionType.setNullSelectionAllowed(true);
                deductionType.setNullSelectionItemId(ConstantUtil.SHOW_ALL);
                commonLogic.loadComboBox(deductionType, "RS_TYPE");
                deductionType.select(ConstantUtil.SHOW_ALL);
                deductionType.setImmediate(true);
                return deductionType;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else if ("deductionProgramType".equals(propertyId)) {
            try {
                final ComboBox deductionProgramType = new ComboBox();
                deductionProgramType.setNullSelectionAllowed(true);
                deductionProgramType.setNullSelectionItemId(ConstantUtil.SHOW_ALL);
                commonLogic.loadComboBox(deductionProgramType, "REBATE_PROGRAM_TYPE");
                deductionProgramType.select(ConstantUtil.SHOW_ALL);
                deductionProgramType.setImmediate(true);
                return deductionProgramType;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }

        }else if ("status".equals(propertyId)) {
            try {
                final ComboBox status = new ComboBox();
                status.setNullSelectionAllowed(true);
                status.setNullSelectionItemId(ConstantUtil.SHOW_ALL);
                commonLogic.loadComboBox(status, "STATUS");
                status.select(ConstantUtil.SHOW_ALL);
                status.setImmediate(true);
                return status;
            } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("createdBy".equals(propertyId)) {
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
                    if ("Invalidrecordcount".equals(tableName)) {
                        comboBox1.addItem(ConstantUtil.INACTIVE_USER); 
                    }
                    return comboBox1;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("modifiedBy".equals(propertyId) && (!tableName.equals("Invalidrecordcount"))) {
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
