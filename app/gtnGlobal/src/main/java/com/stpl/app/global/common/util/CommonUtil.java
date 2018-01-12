/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.common.util;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.util.ValidationUtil;
import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.global.dao.impl.CommonDaoImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
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
    private HelperListUtil helperListUtil = HelperListUtil.getInstance();

    private static final Logger logger = Logger.getLogger(CommonUtil.class);
    public static final CommonDao DAO = CommonDaoImpl.getInstance();
    
    public static final String PRICE_TOLERANCE_FRERQUENCY = "PRICE_TOLERANCE_FREQUENCY";
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
     * @param customMenuItem
     * @param listName the list name
     */
    public void loadCustomMenu(CustomMenuBar.CustomMenuItem customMenuItem, String listName) {
        List<HelperDTO> helperList = new ArrayList<>();
        
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }  
        
        if (!helperList.isEmpty()) {
            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[helperList.size()];
            for (int i = 0; i < helperList.size(); i++) {
                if (!helperList.get(i).getDescription().contains(",")) {
                    MenuItemDTO dto = new MenuItemDTO(helperList.get(i).getId(), helperList.get(i).getDescription());
                    customItem[i] = customMenuItem.addItem(dto, null);
                    customItem[i].setCheckable(true);
                    customItem[i].setItemClickable(true);
                    customItem[i].setItemClickNotClosable(true);
                }
            }
        }
    }
    public static List<List> getSelectedVariables(CustomMenuBar.CustomMenuItem customMenuItem) {
        List<List> list = new ArrayList<>();
        List<Object> id = new ArrayList();
        List<String> description = new ArrayList();
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem menuItem : customMenuItem.getChildren()) {
                if (menuItem.isChecked()) {
                    id.add(menuItem.getMenuItem().getId());
                    description.add(menuItem.getMenuItem().getCaption());
                }
            }
            list.add(id);
            list.add(description);
        }
        return list;
    }
    
    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter)  {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        if(listName.equals(PRICE_TOLERANCE_FRERQUENCY)){
        int hsid = HelperListUtil.getInstance().getIdByDesc(listName, "Annually");
        resultContainer.removeItem(helperListUtil.getIdHelperDTOMap().get(hsid));
        }
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
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[NumericConstants.TWO])));
                }
                if (!ConstantUtil.NULL.equalsIgnoreCase(rules[NumericConstants.TWO].trim()) && ValidationUtil.getMessage(rules[NumericConstants.TWO]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[NumericConstants.TWO]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[NumericConstants.TWO]), ValidationUtil.getMessage(rules[NumericConstants.THREE])));
                }
            }
        } catch (NumberFormatException e) {
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
            boolean isFilter) {

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
    public ComboBox loadUserComboBox(final ComboBox select, Map<Integer, String> userMap)  {
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

    /**
     * Load combox method
     *
     * @param select
     * @param listName
     * @param isFilter
     * @return
     * @throws Exception
     */
    public ComboBox loadComboBoxForFilters(final ComboBox select,
            String listName, boolean isFilter) {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
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

    public List<HelperDTO> getHelperResult(final String listType) throws SystemException, PortalException {

        final List<HelperDTO> helperList = new ArrayList<>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }
        return helperList;
    }
}
