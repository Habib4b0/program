/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.rebateplan.util;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author Sibi
 */
public class RPFilterGenerator implements ExtFilterGenerator {

    private static Logger LOGGER = Logger.getLogger(RPFilterGenerator.class);

    private final CommonUtil commonUtil = CommonUtil.getInstance();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {

        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
       
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            ComboBox comboBox;

            if ("rebatePlanStatus".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, UIUtils.STATUS, true);
                return comboBox;
            } else if ("rebatePlanType".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, "REBATE_PLAN_TYPE", true);
                return comboBox;
            } else if ("rebateBasedOn".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, "REBATE_BASED_ON", true);
                return comboBox;
            } else if ("rebateStructure".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, "REBATE_STRUCTURE", true);
                return comboBox;
            } else if ("rebateRangeBasedOn".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, "REBATE_RANGE_BASED_ON", true);
                return comboBox;
            } else if ("formulaType".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, "FORMULA_TYPE", true);
                return comboBox;
            }
            if ("createdDate".equals(propertyId)) {
                final DateField createdDate = new DateField();
                createdDate.setDescription(ConstantsUtils.DATE_DES);
                createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                createdDate.setImmediate(true);
                createdDate.setEnabled(false);
                return createdDate;
            }
            Map<Integer, String> userMap = StplSecurity.getUserName();
            if ("createdBy".equals(propertyId)) {
               
                comboBox = new ComboBox();
                commonUtil.loadUserComboBox(comboBox, userMap);
                return comboBox;
            }
            if ("modifiedBy".equals(propertyId)) {
                
                comboBox = new ComboBox();
                commonUtil.loadUserComboBox(comboBox, userMap);
                return comboBox;
            }

            if ("modifiedDate".equals(propertyId)) {
                final DateField modifiedDate = new DateField();
                modifiedDate.setDescription(ConstantsUtils.DATE_DES);
                modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                modifiedDate.setImmediate(true);
                modifiedDate.setEnabled(false);
                return modifiedDate;
            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
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
