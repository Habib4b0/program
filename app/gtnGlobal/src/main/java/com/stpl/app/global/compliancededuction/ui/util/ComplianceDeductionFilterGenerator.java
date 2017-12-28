/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.compliancededuction.ui.util;

import com.stpl.app.global.abstractsearch.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author hazi.s
 */
public class ComplianceDeductionFilterGenerator implements ExtFilterGenerator {

    private static Logger LOGGER = Logger.getLogger(ComplianceDeductionFilterGenerator.class);

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

            if ("ruleType".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, UIUtils.RULE_TYPE, true);
                return comboBox;
            } else if ("ruleCategory".equals(propertyId)) {
                comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, UIUtils.RULE_CATEGORY, true);
                return comboBox;
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

        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;

    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;

    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {

        return null;
    }

}
