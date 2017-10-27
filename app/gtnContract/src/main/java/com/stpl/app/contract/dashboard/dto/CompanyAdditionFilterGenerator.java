/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Arrays;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class CompanyAdditionFilterGenerator implements ExtFilterGenerator {

    private static Logger LOGGER = Logger.getLogger(CompanyAdditionFilterGenerator.class);
    CommonUtil commonsUtil = CommonUtil.getInstance();

    List propertyList = Arrays.asList(Constants.COMPANY_STATUS, Constants.COMPANY_TYPE, Constants.COMPANY_CATEGORY, Constants.COMPANY_GROUP, Constants.DISPLAY_COM_STATUS, "displayCompanyType");

    public CompanyAdditionFilterGenerator() {

    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {

        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {
                if (propertyList.contains(propertyId)) {
                    return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO)originatingField.getValue()).getId()), false, false);
                } else {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()).replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape()), false, false);
                }
            }

        } else {
            return null;
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {

        if (Constants.COMPANY_STATUS.equals(propertyId) || Constants.DISPLAY_COM_STATUS.equals(propertyId)) {

            ComboBox status = new ComboBox();

            status = commonsUtil.loadComboBox(status, UIUtils.STATUS, true);

            return status;

        } else if (Constants.COMPANY_TYPE.equals(propertyId) || "displayCompanyType".equals(propertyId)) {
            ComboBox type = new ComboBox();

            type = commonsUtil.loadComboBox(type, UIUtils.COMPANY_TYPE_LIST_NAME, true);

            return type;

        }  else if (Constants.COMPANY_CATEGORY.equals(propertyId)) {

            ComboBox category = new ComboBox();

            category = commonsUtil.loadComboBox(category, UIUtils.COMPANY_CATEGORY_LIST_NAME, true);

            return category;

        } else if (Constants.COMPANY_GROUP.equals(propertyId)) {
            ComboBox group = new ComboBox();

            group = commonsUtil.loadComboBox(group, "COMPANY_GROUP", true);

            return group;

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
