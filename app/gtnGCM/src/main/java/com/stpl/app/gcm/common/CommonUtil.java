/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.common;

import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.Constants.IndicatorConstants;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.app.gcm.util.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pvinoth
 */
public class CommonUtil {

    /**
     * The object.
     */
    private static CommonUtil object;
    public static HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    public static HelperDTO ddlbShowAllValue = new HelperDTO(0, Constants.SHOW_ALL);
    /**
     * The helper list util.
     */
    private static final HelperListUtil helperListUtil = HelperListUtil.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * Instantiates a new common util.
     */
    private CommonUtil() {

    }

    /**
     * Gets the single instance of CommonUtil.
     *
     * @return single instance of CommonUtil
     */
    public static CommonUtil getInstance() {
        if (object == null) {
            object = new CommonUtil();
        }
        return object;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) {
        select.removeAllItems();
        final String defaultValue = isFilter ? ConstantsUtils.SHOW_ALL : Constants.SELECT_ONE;
        select.setValidationVisible(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<>();
        select.addItem(defaultValue);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        for (HelperDTO dto : helperList) {
            select.addItem(String.valueOf(dto.getId()));
            select.setItemCaption(String.valueOf(dto.getId()), dto.getDescription());
        }
        select.select(defaultValue);
        select.markAsDirty();
        return select;
    }

    public static ComboBox loadComboBoxForGCM(final ComboBox select, String listName, boolean isFilter) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        select.removeAllItems();
        final HelperDTO defaultValue = isFilter ? ddlbShowAllValue : ddlbDefaultValue;
        select.setValidationVisible(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setContainerDataSource(container);
        container.addBean(defaultValue);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            container.addAll(helperListUtil.getListNameMap().get(listName));
        }
        select.select(defaultValue);
        select.setItemCaptionPropertyId("description");
        return select;
    }

    public static ComboBox getComboBoxByListName(ComboBox columnName, String listName, Boolean isFilter) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        final HelperDTO defaultValue = isFilter ? ddlbShowAllValue : ddlbDefaultValue;
        String comboboxName = listName;
        columnName.setValidationVisible(true);
        columnName.setNullSelectionAllowed(true);
        columnName.setNullSelectionItemId(defaultValue);
        columnName.setContainerDataSource(container);
        container.addBean(defaultValue);
        if (helperListUtil.getListNameMap().get(comboboxName) == null) {
            List input = new ArrayList();
            input.add(listName);
            List<Object[]> list = ItemQueries.getItemData(input, "ComboBox List Name Query", null);
            List<HelperDTO> resultList = new ArrayList<>();
            resultList.add(defaultValue);
            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(IndicatorConstants.SELECT_ONE.getConstant()))) {
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                    dto.setDescription(str[1] == null ? Constants.ZEROSTRING : String.valueOf(str[1]));
                    resultList.add(dto);
                }
            }

            helperListUtil.getListNameMap().put(comboboxName, resultList);
        } else {
            container.addAll(helperListUtil.getListNameMap().get(listName));
        }
        columnName.select(defaultValue);
        columnName.setItemCaptionPropertyId("description");
        container.addAll(helperListUtil.getListNameMap().get(listName));
        return columnName;
    }

    /**
     * To Validate Text Fields
     *
     * @param obj
     * @param key
     */
    public void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !Constants.NULL.equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(",");
                if (rules[0] != null && ValidationUtils.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtils.getMessage(rules[0]))) {
                    String[] temp = ValidationUtils.getMessage(rules[0]).split(",");
                    tempObj.addValidator(new StringLengthValidator(ValidationUtils.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[NumericConstants.TWO])));
                }
                if (rules[NumericConstants.TWO] != null && ValidationUtils.getMessage(rules[NumericConstants.TWO]) != null && StringUtils.isNotEmpty(ValidationUtils.getMessage(rules[NumericConstants.TWO]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtils.getMessage(rules[NumericConstants.TWO]), ValidationUtils.getMessage(rules[NumericConstants.THREE])));
                }
            }
        } catch (Exception e) {
            logger.error("",e);
        }
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadYesNoDDLB(final ComboBox select,
            boolean isFilter) {

        final String defaultValue = isFilter ? ConstantsUtils.SHOW_ALL : Constants.SELECT_ONE;
        select.setValidationVisible(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.addItem(defaultValue);
        select.addItem(ConstantsUtils.YES_VARIABLE);
        select.addItem(ConstantsUtils.NO_VARIABLE);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? Constants.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? Constants.SELECT_ONE : select.getValue()));
            }
        });
        return select;
    }
}
