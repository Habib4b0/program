/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.dto;

import com.stpl.app.arm.accountconfiguration.logic.AccountConfigLogic;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import static com.stpl.app.utils.CommonUtils.userMap;
import com.stpl.app.utils.HelperDTOFilter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author Mohamed.Shahul
 */
public class SearchResultsTableGenerator implements ExtFilterGenerator {

    AccountConfigLogic logic = AccountConfigLogic.getInstance();
    /**
     * The Constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(SearchResultsTableGenerator.class);

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        if ((propertyId.toString().equals("createdBy")) && (value != null)) {
            return new SimpleStringFilter(propertyId, String.valueOf(value), false, false);
        }
        if ((propertyId.toString().equals("brand")) && (value != null)) {
            return new SimpleStringFilter(propertyId, String.valueOf(value), false, false);
        }
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        String value = null;
        if (((originatingField instanceof ComboBox) && (originatingField.getValue() != null))) {
            if ((originatingField.getValue() != null) && ((propertyId.toString().equals("companyNoHelperDto")) || (propertyId.toString().equals("companyNo")) || (propertyId.toString().equals("businessNoHelperDto"))) || (propertyId.toString().equals("businessUnitNo"))) {
                HelperDTO helperDto = (HelperDTO) originatingField.getValue();
                return new HelperDTOFilter(propertyId, helperDto.getId());
            }
            return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        }
        return generateFilter(propertyId, value);
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

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            final ComboBox comboBox = new ComboBox();
            comboBox.setImmediate(true);
            switch (propertyId.toString()) {
                case "checkRecord":
                    CheckBox text = new CheckBox();
                    text.setVisible(false);
                    text.setImmediate(true);
                    return text;
                case "createdBy":
                    comboBox.addItem(NumericConstants.ZERO);
                    comboBox.setItemCaption(NumericConstants.ZERO, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(NumericConstants.ZERO);
                    return comboBox;
                case "brand":
                    logic.loadBrandDdlb(comboBox, new ArrayList<Object[]>(), Boolean.FALSE, Boolean.TRUE);
                    return comboBox;
                case "brandDdlb":
                    logic.loadBrandDdlb(comboBox, new ArrayList<Object[]>(), Boolean.FALSE, Boolean.TRUE);
                    return comboBox;
                case "account":
                    comboBox.addItem(ConstantsUtils.SHOW_ALL);
                    comboBox.setItemCaption(ConstantsUtils.SHOW_ALL, ConstantsUtils.SHOW_ALL);
                    List<Object[]> accountList = QueryUtils.getItemData(new ArrayList(), "loadAccountFilterDdlb", null);
                    for (Object[] obj : accountList) {
                        if (obj[1] != null) {
                            comboBox.addItem((String) obj[NumericConstants.ONE]);
                            comboBox.setItemCaption((String) obj[1], obj[1] + "");
                        }
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    return comboBox;
                case "companyNoHelperDto":
                    logic.loadCompanyOrBusinessUnitDdlb(comboBox, new ArrayList<Object[]>(), "getCompanyQuery", Boolean.FALSE, Boolean.TRUE);
                    return comboBox;
                case "businessNoHelperDto":
                    logic.loadCompanyOrBusinessUnitDdlb(comboBox, new ArrayList<Object[]>(), "getBusinessQuery", Boolean.FALSE, Boolean.TRUE);
                    return comboBox;
                case "companyNo":
                    logic.loadCompanyOrBusinessUnitDdlb(comboBox, new ArrayList<Object[]>(), "getCompanyQuery", Boolean.FALSE, Boolean.TRUE);
                    return comboBox;
                case "businessUnitNo":
                    logic.loadCompanyOrBusinessUnitDdlb(comboBox, new ArrayList<Object[]>(), "getBusinessQuery", Boolean.FALSE, Boolean.TRUE);
                    return comboBox;
                default:
                    return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }
}
