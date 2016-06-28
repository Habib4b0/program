/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.common.util;

import com.stpl.app.global.abstractsearch.util.ValidationUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class CommonUtil.
 *
 * @author pvinoth
 */
public class CommonUtil {

    /**
     * The object.
     */
    private static CommonUtil object;

    /**
     * The helper list util.
     */
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    private static Logger logger = Logger.getLogger(CommonUtil.class);

    /**
     *  
     *      * The list name bundle.      
     */
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");

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
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        if (listName.equals(GeneralCommonUtils.CALENDAR) && helperList.size() > 1) {
            select.setNullSelectionAllowed(false);
            select.setValue(helperList.get(1));
            select.select(helperList.get(1));
            select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));

        }
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }

    /**
     * To Validate Text Fields
     *
     * @param obj
     * @param key
     */
    public void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !"null".equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(",");
                if (rules[0] != null && ValidationUtil.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[0]))) {
                    String[] temp = ValidationUtil.getMessage(rules[0]).split(",");
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[2])));
                }
                if (rules[2] != null && ValidationUtil.getMessage(rules[2]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[2]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[2]), ValidationUtil.getMessage(rules[3])));
                }
            }
        } catch (Exception e) {
            logger.error(e);
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
            boolean isFilter) throws Exception {

        final String defaultValue = isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE;
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.addItem(defaultValue);
        select.addItem(ConstantsUtils.YES_VARIABLE);
        select.addItem(ConstantsUtils.NO_VARIABLE);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
            }
        });
        return select;
    }

    public String getHeaderMessage() {
        return confirmationMessage.getString("MSG_ID_019");
    }

    public String getAccessDeniedHeaderMessage() {
        return confirmationMessage.getString("MSG_ID_015");
    }

    public String getPermissionDeniedToDelete() {
        return confirmationMessage.getString("MSG_ID_026");
    }

    public String getPermissionDeniedToEdit() {
        return confirmationMessage.getString("MSG_ID_027");
    }

    public String getSaveMessage(String recordName) {
        return confirmationMessage.getString("MSG_ID_021") + ConstantsUtils.BLANK_SPACE + recordName + ConstantsUtils.QUSTN_MARK;
    }

    public String getResetMessage() {
        return confirmationMessage.getString("MSG_ID_020");
    }

    public String getDeleteMessage(String recordName) {
        return confirmationMessage.getString("MSG_ID_023") + ConstantsUtils.BLANK_SPACE + recordName + ConstantsUtils.QUSTN_MARK;
    }

    public String getBackMessage() {
        return confirmationMessage.getString("MSG_ID_025");
    }

    public String getSavedSuccessfulMessage(String recordId, String recordName) {
        return recordId + ConstantsUtils.COMMA + recordName + ConstantsUtils.BLANK_SPACE + confirmationMessage.getString("MSG_ID_022");
    }

    public String getDeletedSuccessfulMessage(String recordId, String recordName) {
        return recordId + ConstantsUtils.COMMA + recordName + ConstantsUtils.BLANK_SPACE + confirmationMessage.getString("MSG_ID_024");
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
    public ComboBox loadUserComboBox(final ComboBox select, Map<Integer, String> userMap) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, ConstantsUtils.SHOW_ALL);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (userMap != null) {
            for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                HelperDTO dto = new HelperDTO();
                dto.setId(entry.getKey());
                dto.setDescription(entry.getValue());
                helperList.add(dto);
            }
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }
}
