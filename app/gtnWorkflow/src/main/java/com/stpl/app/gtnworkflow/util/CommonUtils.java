/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.util;

import com.stpl.app.gtnworkflow.dto.InboxDashboardDTO;
import com.stpl.app.gtnworkflow.dto.LevelDTO;
import com.stpl.app.gtnworkflow.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.jboss.logging.Logger;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author satheesh
 */
public class CommonUtils {

    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
    public final static String WORKFLOW_STATUS = "WorkFlowStatus";
    public final static String WORKFLOW_INBOX = "Workflow Inbox";
    public final static String YES = "YES";
    public final static String USER_ID = "userId";
    public static final char CHAR_PERCENT = '%';
    public static final char CHAR_ASTERISK = '*';
    public static final String IS_DOUBLE_TWO_DIGIT = "^[-+]?[0-9]{0,3}(?:\\.[0-9]{1,2})?$";
    public static final double ZERO_DOUBLE = 0.00;
    public static final String IS_ALPHABET = "([0-9]|[a-z|A-Z])*";
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    public static final String PRIVATE = "Private";
    public static final String PUBLIC = "Public";
    private static HashMap<Long, String> userMap = new HashMap<Long, String>();
    /**
     *
     * The list name bundle. 
     */
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    public static final String BUSINESS_PROCESS_TYPE_NONMANDATED = "Non Mandated";
    public static final String BUSINESS_PROCESS_TYPE_MANDATED = "Mandated";
    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";
    public static final String RS_TYPE = "RS_TYPE";
    public static final String RS_CATEGORY = "RS_CATEGORY";
    public static final  String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";
    public static final  String DEDUCTION_TYPE = "Deduction Schedule Type";
    public static final  String DEDUCTION_CATEGORY = "Deduction Category";
    public static final  String DEDUCTION_PROGRAM_TYPE = "Deduction Program Type";
    public static final String DASH = "0";
    public static final String SEARCH = "search";
    public static final String LANDING_SCREEN = "Landing screen";
    Map<String, String[]> levelMap = new HashMap<>();
    /**
     * The Constant MMDDYYYY.
     */
    public static final String MMDDYYYY = "MM/dd/yyyy";

    public static Object[] merge(Object[] array1, Object[] array2) {
        List<Object> list1 = new ArrayList<>(Arrays.asList(array1));
        List<Object> list2 = new ArrayList<>(Arrays.asList(array2));
        list1.addAll(list2);
        Object[] newArray = list1.toArray();
        return newArray;
    }

    public static List<InboxDashboardDTO> getCustomizedWorkflowInboxResults(List resultList, List<String> columnsList) {
        InboxDashboardDTO inboxDashboardDTO;
        List<InboxDashboardDTO> inboxDashboardDTOs = new ArrayList<>();
        try {
            for (int i = 0; i < resultList.size(); i++) {
                Object objects[] = (Object[]) resultList.get(i);
                inboxDashboardDTO = new InboxDashboardDTO();
                for (int j = 0; j < objects.length; j++) {
                    if (objects[j] == null) {
                        if (columnsList.get(j).equals(CommonUtils.APPROVED_DATE)) {
                            inboxDashboardDTO.setApprovedDate(null);
                        }
                    } else {
                        BeanUtils.setProperty(inboxDashboardDTO, columnsList.get(j), objects[j]);
                    }
                }
                inboxDashboardDTO.setApprovedBy(getUserInfo(inboxDashboardDTO.getApprovedById()));
                inboxDashboardDTO.setCreatedBy(getUserInfo(inboxDashboardDTO.getCreatedById()));
                inboxDashboardDTOs.add(inboxDashboardDTO);
            }
        } catch (Exception e) {
            LOGGER.error("Exception at customizing results" + e);
        }
        return inboxDashboardDTOs;
    }

    public static HashMap<Long, String> setUserInfo() {

        List<User> users = new ArrayList<User>();
        DynamicQuery userGroupDynamicQuery = UserLocalServiceUtil.dynamicQuery();
        try {
            users = UserLocalServiceUtil.dynamicQuery(userGroupDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        for (User user : users) {
            userMap.put(user.getUserId(), user.getLastName() + " " + user.getFirstName());
        }
        return userMap;
    }

    public static String getUserInfo(Long userId) {
        return userMap.get(userId) != null ? userMap.get(userId) : StringUtils.EMPTY;
    }

    public static HashMap<Long, String> getUserMap() {
        return userMap;
    }

    public static String getUserInfo(String userId) {
        try {
            Long.valueOf(userId);
        } catch (NumberFormatException nfe) {
            return StringUtils.EMPTY;
        }
        return getUserInfo(Long.valueOf(userId));
    }

    public static void getMessageNotification(String message) {
        Notification notif = new Notification(message, Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setStyleName("mystyle");
        notif.show(Page.getCurrent());

    }

    public static void getResponsiveControls(HorizontalLayout tempLayout, HorizontalLayout controlBar) {

        controlBar.setStyleName("responsivePagedTable");
        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);

    }

    public static void successNotification(final String message) {
        try {
            final Notification notif = new Notification(message,
                    Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName("mystyle");
            notif.setDelayMsec(NumericConstants.THOUSAND);
            notif.show(Page.getCurrent());
        } catch (Exception e) {

            LOGGER.error(e);
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
    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) {
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
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class
        );
        if (helperListUtil.getListNameMap()
                .get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }

        resultContainer.addAll(helperList);

        select.setContainerDataSource(resultContainer);

        select.select(defaultValue);

        select.addValueChangeListener(
                new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event
            ) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        }
        );
        return select;
    }

    public String getErrorHeaderMessage() {
        return confirmationMessage.getString("WI_MSG_TITLE_ERROR");
    }

    public String getSearchErrorMessage() {
        return confirmationMessage.getString("WI_MSG_SEARCH_FAILED");
    }

    public String getNoSearchCriteria() {
        return confirmationMessage.getString("WI_MSG_NO_SEARCH_CRITERIA");
    }

    public String getSearchMessageBusinessProcessError() {
        return confirmationMessage.getString("WI_MSG_SEARCH_BUSINESS_PROCESS_NOT_SELECTED");
    }

    public String getSearchMessageDeductionError() {
        return confirmationMessage.getString("WI_MSG_SEARCH_DEDUCTION_NOT_SELECTED");
    }

    public String getResetMessage() {
        return confirmationMessage.getString("WI_MSG_RESET_PAGE");
    }

    public String getConfirmResetMessage() {
        return confirmationMessage.getString("WI_TITLE_CONFIRM_RESET");
    }

    public String getNoResultMessage() {
        return confirmationMessage.getString("WI_MSG_NO_RESULT");
    }

    public String getSearchComplete() {
        return confirmationMessage.getString("WI_MSG_SEARCH_CMPT");
    }

    public String getProjSelect() {
        return confirmationMessage.getString("WI_MSG_PRJ_VIEW");
    }

    public String getProjSelectView() {
        return confirmationMessage.getString("WI_MSG_PROJ_SEL");
    }

    public String getProjSelectOpen() {
        return confirmationMessage.getString("WI_MSG_PROJ_SEL_OPEN");
    }

    public String getPermDenied() {
        return confirmationMessage.getString("WI_MSG_PER_DEN");
    }
    
     public String getResetConfirmation() {
        return confirmationMessage.getString("WI_MSG_TITLE_CONFIRMATION");
    }
     
       public String getSearchCriteriaConfirmation() {
        return confirmationMessage.getString("WI_MSG_TITLE_SEARCH_CRITERIA");
    }

    /**
     * For Loading Drop downs based on the SQL Query Results. The
     * companyBusiness Flag is for loading company / Business Drop down in setup
     * and configuration of ARM
     *
     * @param comboBox
     * @param sqlID
     * @param companyBusinessFlag
     */
    public static void configureDropDowns(ComboBox comboBox, String sqlID, boolean companyBusinessFlag) {
        comboBox.setImmediate(true);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(false);
        loadReserveRateDropdowns(comboBox, companyBusinessFlag, sqlID);
        comboBox.select(0);
    }

    /**
     * Logic For Loading Drop downs based on the SQL Query Results. The
     * companyBusiness Flag is for loading company / Business Drop down in setup
     * and configuration of ARM
     *
     * @param comboBox
     * @param companyBusinessUnitFlag
     * @param sqlID
     */
    public static void loadReserveRateDropdowns(ComboBox comboBox, boolean companyBusinessUnitFlag, String sqlID) {
        String sqlQuery = SQlUtil.getQuery(sqlID);
        if (companyBusinessUnitFlag) {
            List<Object[]> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            for (Object[] obj : arr) {
                if (obj[1] != null && obj[NumericConstants.TWO] != null) {
                    comboBox.addItem((int) obj[0]);
                    comboBox.setItemCaption((int) obj[0], obj[1] + " - " + obj[NumericConstants.TWO]);
                }
            }
        } else {
            List<Object> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            comboBox.addItems(arr);
        }
    }

    public final void loadDeductionsDdlb(final ComboBox deductionLevel, final ComboBox deductionValue) {
        deductionLevel.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (Integer.parseInt(String.valueOf(deductionLevel.getValue())) != 0) {
                    int id = (int) deductionLevel.getValue();
                    HelperDTO deductionLevelval = HelperListUtil.getInstance().getIdHelperDTOMap().get(id);
                    List<LevelDTO> list = loadAvailableDeductions(deductionLevelval);
                    for (int i = 0; i < list.size(); i++) {
                        deductionValue.addItem(list.get(i).getDisplayValue());
                    }
                }
            }
        });
    }

    public static ComboBox loadComboBoxWithIntegerForComboBox(final ComboBox select, String listName, boolean isFilter) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.debug("Error while loading Drop down :" + listName + " with :" + e);
        }
        return select;
    }

    public List loadAvailableDeductions(HelperDTO valueLevel) {
        Map<Integer, HelperDTO> descriptionMap = helperListUtil.getIdHelperDTOMap();
        loadLevelMap();
        List<LevelDTO> resultList = new ArrayList<>();
        String sqlQuery = SQlUtil.getQuery("getDeductionAvailableLevels");
        String query = sqlQuery;
        String filterQuery;
        String finalQuery;
        if (!valueLevel.getDescription().equals(ARMConstants.getDeduction())) {
            filterQuery = query.replace("$$$",
                    (levelMap.get((descriptionMap.get(valueLevel.getId())).getDescription()))[0] + " "
                    + (levelMap.get((descriptionMap.get(valueLevel.getId())).getDescription()))[1]);
            finalQuery = filterQuery + levelMap.get((descriptionMap.get(valueLevel.getId())).getDescription())[1];
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
            for (Object obj : list) {
                if (valueLevel.getDescription().equals(ARMConstants.getDeductionCategory())
                        || valueLevel.getDescription().equals(ARMConstants.getDeductionType())
                        || valueLevel.getDescription().equals(ARMConstants.getDeductionProgram())) {
                    if (!obj.equals(GlobalConstants.getSelectOne())) {
                        LevelDTO level = new LevelDTO();
                        level.setDisplayValue(obj + "");
                        level.setActualValue(obj + "");
                        resultList.add(level);
                    }
                } else {
                    LevelDTO level = new LevelDTO();
                    level.setDisplayValue(obj + "");
                    level.setActualValue(obj + "");
                    resultList.add(level);
                }
            }
        } else {
            filterQuery = query.replace("$$$",
                    (levelMap.get((descriptionMap.get(valueLevel.getId())).getDescription()))[0] + " , "
                    + (levelMap.get((descriptionMap.get(valueLevel.getId())).getDescription()))[1]);
            finalQuery = filterQuery + levelMap.get((descriptionMap.get(valueLevel.getId())).getDescription())[0];
            List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
            for (Object[] obj : list) {
                LevelDTO level = new LevelDTO();
                level.setDisplayValue(obj[0] + "");
                level.setRsModelSID(obj[1] + "");
                resultList.add(level);
            }
        }
        return resultList;
    }

    private void loadLevelMap() {
        levelMap.clear();
        levelMap.put(ARMConstants.getDeductionCategory(), new String[]{"H1.DESCRIPTION", "RS_CATEGORY"});
        levelMap.put(ARMConstants.getDeductionType(), new String[]{"H2.DESCRIPTION", "RS_TYPE"});
        levelMap.put(ARMConstants.getDeductionProgram(), new String[]{"H3.DESCRIPTION", "REBATE_PROGRAM_TYPE"});
        levelMap.put(ARMConstants.getDeductionCategory2(), new String[]{"H5.DESCRIPTION", "UDC2"});
        levelMap.put(ARMConstants.getDeductionCategory3(), new String[]{"H6.DESCRIPTION", "UDC3"});
        levelMap.put(ARMConstants.getDeductionCategory4(), new String[]{"H7.DESCRIPTION", "UDC4"});
        levelMap.put(ARMConstants.getDeductionCategory5(), new String[]{"H8.DESCRIPTION", "UDC5"});
        levelMap.put(ARMConstants.getDeductionCategory6(), new String[]{"H9.DESCRIPTION", "UDC6"});
        levelMap.put(ARMConstants.getDeduction(), new String[]{"RS_ID", "RS_MODEL_SID"});
    }

    public static final CustomMenuBar.CustomMenuItem loadAdjustmentTypeDdlb(final CustomMenuBar menuBar, CustomMenuBar.CustomMenuItem customMenuItemDed) {
        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.TEN);

        List<Object[]> list = QueryUtils.getServiceData(Collections.EMPTY_LIST, "LoadAdjustmentType", null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
        customMenuItemDed.removeChildren();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if (obj[NumericConstants.TWO] != null) {
                MenuItemDTO dto = new MenuItemDTO(obj[0], obj[NumericConstants.TWO].toString());
                customItem[i] = customMenuItemDed.addItem(dto, null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);
                customItem[i].setItemClickNotClosable(true);
            }
        }
        return customMenuItemDed;
    }

    /**
     * <h1>Before Unload function</h1>
     * <p>
     * Purpose : Fix for the Communication Error <br>
     * The Before Unload function is used to Close the Vaadin UI when the UI is
     * unloaded from the browser , so that the data will not retained when the
     * user navigates back to same UI. <br>
     *
     * Comments about Code : Action performed after the listener is fix for the
     * Refresh problem <br>
     * Refresh Problems Faced : As the Vaadin Session Values has been changed to
     * Sesison DTO, on refresh the Id gets flushed and Screen collapse. The
     * Actions performed after the Unload listener is to maintain the required
     * ID
     *
     *
     * @param uI - UI Object
     * @param sessionDTO - SessionDTO Object
     * @return
     */
    public static final void beforeUnloadCloseUi(final UI uI) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {
            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                uI.close();
            }
        });

    }

    public static List<List> getSelectedVariables(CustomMenuBar.CustomMenuItem customMenuItem, Boolean isPropertyRequired) {
        List<List> list = new ArrayList<List>();
        List<Object> column = new ArrayList();
        List<String> header = new ArrayList();
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            int i = 0;
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.isChecked()) {
                    column.add(isPropertyRequired ? object.getMenuItem().getWindow() + "." + i : object.getMenuItem().getWindow());
                    header.add(object.getMenuItem().getCaption());
                    i++;
                }
            }
            list.add(column);
            list.add(header);
        }
        return list;
    }

    public static void setSelectedVariables(CustomMenuBar.CustomMenuItem customMenuItem, List adjType) {
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (int i = 0; i < adjType.size(); i++) {
                for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                    if (adjType.get(i).toString().trim().equals(object.getMenuItem().getCaption())) {
                        object.setChecked(true);
                    }
                }
            }
        }
    }
    
    /**
     * Load adjustment type with value as caption and sid as itemID
     * 
     * @param comboBox
     * @param companyBusinessUnitFlag
     * @param sqlID 
     */
    public static void loadAdjustmentTypeDropdowns(ComboBox comboBox,String sqlID) {
        comboBox.setImmediate(true);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(false);
        String sqlQuery = SQlUtil.getQuery(sqlID);
            List<Object[]> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            for (Object[] obj : arr) {
                if (obj[0] != null && obj[NumericConstants.TWO] != null) {
                    comboBox.addItem((int) obj[0]);
                    comboBox.setItemCaption(obj[0],  obj[NumericConstants.TWO].toString());
                }
            }
       comboBox.select(0);
    }
    
    /**
     * Convert the date to "MM/dd/yyyy" format.
     *
     * @param aDate - The Date object to be converted to string
     * @return String - date in string format in the format of "MM/dd/yyyy"
     */
    public static final String convertDateToString(final Date aDate) {

        return getDateTime(MMDDYYYY, aDate);
    }
    
    /**
     * Get the date in string on given format.
     *
     * @param aMask - Date format input
     * @param aDate - The Date object to be converted to string
     * @return String - date in string format
     */
    public static final String getDateTime(final String aMask, final Date aDate) {
        if (aDate == null) {
        } else {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            final String returnValue = dateFormat.format(aDate);
            return returnValue;
        }
        return StringUtils.EMPTY;
    }
    public static final String CONTRACT_MANAGEMENT = "Contract Management";
    public static final String WFNAME = "@WFNAME";
    public static final String PM_PROJECTION_DESCRIPTION = "PM.PROJECTION_DESCRIPTION";
    public static final String FORECASTING = "Forecasting";
    public static final String ACCRUAL_RATE_PROJECTION = "Accrual Rate Projection";
    public static final String RETURNS = "Returns";
    public static final String CREATED_DATE = "creationDate";
    public static final String APPROVED_DATE = "approvedDate";
    public static final String CREATED_BY = "createdBy";
    public static final String APPROVED_BY = "approvedBy";
    public static final String BUSINESS_UNIT = "businessUnit";
    public static final String STATUS = "status";
    public static final String MODIFIED_BY = "modifiedBy";
    public static final String NOTES = "notes";
    public static final String NOTES_HEADER = "Notes";
    public static final String MODIFIED_DATE_FROM = "modifiedDatefrom";
    public static final String MODIFIED_DATE_TO = "modifiedDateto";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String SEARCH_ICON = "searchicon";
    public static final String SELECT_ONE = "-Select One-";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String MODIFIED_BY_HEADER = "Modified By";
    public static final String MODIFIED_DATE_HEADER = "Modified Date";
    public static final String STATUS_HEADER = "Status";
    public static final String WORK_FLOW_STATUS = "workFlowStatus";
    public static final String WORKFLOW_ID = "workflowId";
    public static final String WORKFLOW_NAME = "workflowName";
    public static final String WORKFLOW_DESCRIPTION = "workflowDescription";
    public static final String APPROVED_BY_HEADER = "Approved By";
    public static final String CREATION_DATE_HEADER = "Creation Date";
    public static final String WORKFLOW_NAME_HEADER = "Workflow Name";
    public static final String WORKFLOW_DESCRIPTION_HEADER = "Workflow Description";
    public static final String CREATED_BY_HEADER = "Created By";
    public static final String APPROVED_DATE_HEADER = "Approved Date";
    public static final String QUERY_NAME = "Query Name - - >>";
}
