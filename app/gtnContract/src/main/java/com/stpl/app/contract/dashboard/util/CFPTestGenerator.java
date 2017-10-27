/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.util;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class CFPTestGenerator implements ExtFilterGenerator {

    private static Logger LOGGER = Logger.getLogger(CFPTestGenerator.class);
    private final CommonUtil commonUtil = CommonUtil.getInstance();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {

                if ("parentCompanyFamilyPlanId".equals(propertyId)) {

                    ComboBox box = (ComboBox) originatingField;
                    HelperDTO dto = (HelperDTO) box.getValue();
                    return new SimpleStringFilter(propertyId, String.valueOf(dto.getId()), false, false);

                } else {

                    return new Compare.Equal(propertyId, originatingField.getValue());
                }
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
            if ("companyFamilyPlanStatus".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.CFP_STATUS, true);
                return comboBox;
            } else if ("companyFamilyPlanDesignation".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.CFP_DESIGNATION, true);
                return comboBox;

            } else if ("companyFamilyPlanType".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.CFP_TYPE, true);
                return comboBox;

            } else if ("companyFamilyPlanTradeClass".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.TRADE_CLASS, true);
                return comboBox;

            } else if ("companyCategoryValue".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.COMPANY_CATEGORY_LIST_NAME, true);
                return comboBox;

            } else if ("tradeClassValue".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.COMPANY_TRADE_CLASS, true);
                return comboBox;

            }
            if ("companyGroupValue".equals(propertyId)) {
                ComboBox group = new ComboBox();
                commonUtil.loadComboBox(group, UIUtils.COMPANY_GROUP, true);
                return group;
            } else if ("companyFamilyPlanCategory".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.CFP_CATAGORY, true);
                return comboBox;

            } else if (ConstantsUtils.COMPANY_STATUS_VALUE.equals(propertyId)) {
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.CFP_STATUS, true);
                return comboBox;
            } else if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(propertyId)) {
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonUtil.loadComboBox(comboBox, UIUtils.COMPANY_TYPE_LIST_NAME, true);
                return comboBox;
            }

        } catch (Exception e) {
            LOGGER.error(e);
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