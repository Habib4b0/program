/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.common.util;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.abstractsearch.util.ValidationUtil;
import com.stpl.app.contract.global.dto.RsItemDetailsDTO;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Role;
import com.stpl.portal.service.RoleLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class CommonUtil.
 *
 * @author pvinoth
 */
public class CommonUtil {

    private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);
    /**
     * The object.
     */
    private static CommonUtil object;

    /**
     * The helper list util.
     */
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    RsItemDetailsDTO rsItemDetailsDTO = new RsItemDetailsDTO();
    private static Logger logger = Logger.getLogger(CommonUtil.class);
    /**
     *  
     *      * The list name bundle.      
     */
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");

    /**
     * Instantiates a new common util.
     */
    public CommonUtil() {

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
            final String listName, boolean isFilter) throws SystemException {
        final HelperDTO defaultValue = new HelperDTO(Constants.ZERO, isFilter ? Constants.SHOW_ALL : Constants.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(Constants.DESCRIPTION);
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
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? Constants.SELECT_ONE : select.getValue()));
        if (listName.equals(CommonUtils.CALENDAR)) {
            select.setValue(helperListUtil.getListNameMap().get(listName).get(Constants.ZERO));
            select.select(helperListUtil.getListNameMap().get(listName).get(Constants.ZERO));
            select.setDescription(select.getValue() == null ? Constants.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription());
        }
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || Constants.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? Constants.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
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
                if (rules[Constants.ZERO] != null && ValidationUtil.getMessage(rules[Constants.ZERO]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[Constants.ZERO]))) {
                    String[] temp = ValidationUtil.getMessage(rules[Constants.ZERO]).split(",");
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[NumericConstants.ONE]), Integer.valueOf(temp[Constants.ZERO]), Integer.valueOf(temp[NumericConstants.ONE]), Boolean.valueOf(temp[NumericConstants.TWO])));
                }
                if (rules[NumericConstants.TWO] != null && ValidationUtil.getMessage(rules[NumericConstants.TWO]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[NumericConstants.TWO]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[NumericConstants.TWO]), ValidationUtil.getMessage(rules[NumericConstants.THREE])));
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
    
    /**
     * 
     * @param userId
     * @return 
     */
    public boolean checkETLUser(int userId) {
    
        try {
            return checkETL(String.valueOf(userId));
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return false;
    
    }
    public boolean checkETL(String userId) throws SystemException {
        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(Long.valueOf(userId));
        List roleList = new ArrayList();
        for (Role role : userRoles) {
            roleList.add(role.getRoleId());
        }
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(Role.class);
        query.add(RestrictionsFactoryUtil.eq("name", "ETL"));
        query.add(RestrictionsFactoryUtil.in("roleId", roleList));
        List<Role> userList = RoleLocalServiceUtil.dynamicQuery(query);
        if (!userList.isEmpty() && userList.size() > Constants.ZERO) {
            return true;
        }
        return false;
    }

    public String getHeaderMessage() {
        return confirmationMessage.getString("MSG_ID_001");
    }

    public String getSaveMessage(String recordName) {
        return confirmationMessage.getString("MSG_ID_003") + ConstantUtil.BLANK_SPACE + recordName + ConstantUtil.QUSTN_MARK;
    }

    public String getResetMessage() {
        return confirmationMessage.getString("MSG_ID_002");
    }

    public String getDeleteMessage(String recordName) {
        return confirmationMessage.getString("MSG_ID_005") + ConstantUtil.BLANK_SPACE + recordName + ConstantUtil.QUSTN_MARK;
    }

    public String getBackMessage() {
        return confirmationMessage.getString("MSG_ID_007");
    }

    public String getSavedSuccessfulMessage(String recordId, String recordName) {
        return recordId + ConstantUtil.COMMA + recordName + ConstantUtil.BLANK_SPACE + confirmationMessage.getString("MSG_ID_004");
    }

    public String getDeletedSuccessfulMessage(String recordId, String recordName) {
        return recordId + ConstantUtil.COMMA + recordName + ConstantUtil.BLANK_SPACE + confirmationMessage.getString("MSG_ID_006");
    }
    
    public String getRebuildMessage() {
        return confirmationMessage.getString("MSG_ID_008");
    }
    
    public String getDashboardUpdateMessage() {
        return confirmationMessage.getString("MSG_ID_009");
    }
    
    public String getRemoveComponentMessage() {
        return confirmationMessage.getString("MSG_ID_010");
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
            boolean isFilter) throws SystemException {
        
        final String defaultValue=isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE;
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.addItem(defaultValue);
        select.addItem(ConstantsUtils.YES_VARIABLE);
        select.addItem(ConstantsUtils.NO_VARIABLE);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue()==null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue()==null ? ConstantsUtils.SELECT_ONE : select.getValue()));
            }
        });
        return select;
    }
        }
