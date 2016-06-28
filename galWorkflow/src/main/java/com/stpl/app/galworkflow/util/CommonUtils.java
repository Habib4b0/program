/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.util;

import com.stpl.app.galworkflow.dto.InboxDashboardDTO;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author satheesh
 */
public class CommonUtils {

    private static final Logger LOGGER = LogManager.getLogger(CommonUtils.class);
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
    public static final Object[] WF_HISTORY_LOOKUP_COLUMNS = new Object[]{
        "status", "modifiedDate", "modifiedBy", "notes", "attachmentLink"};
    public static final String[] WF_HISTORY_LOOKUP_HEADER = new String[]{
        "Status", "Modified Date", "Modified By", "Notes", "Attachement"};
    public static final String PRIVATE = "Private";
    public static final String PUBLIC = "Public";
    private static HashMap<Long, String> userMap = new HashMap<Long, String>();

    /**
     * Visible columns for Workflow Dashboard. If this is changed,
     * getDbColumnName() method should be changed in workflowlogic.java
     */
    public static final Object[] INBOX_DASHBOARD_COLUMNS = new Object[]{
        "workflowId", "workflowName", "workflowDescription", "workFlowStatus", "createdBy", "creationDate", "approvedBy", "approvedDate"};

    public static final String[] INBOX_DASHBOARD_HEADER = new String[]{
        "Workflow Id", "Workflow Name", "Workflow Description", "Status", "Created By", "Creation Date", "Approved By", "Approved Date"};

    public static final Object[] VIEW_SEARCH_LOOKUP_ADD_COLUMNS = new Object[]{"viewName"};
    public static final String[] VIEW_SEARCH_LOOKUP_ADD_HEADER = new String[]{"View Name"};

    public static final Object[] VIEW_SEARCH_LOOKUP_COLUMNS = merge(VIEW_SEARCH_LOOKUP_ADD_COLUMNS, INBOX_DASHBOARD_COLUMNS);
    private static final Object[] TEMP_VIEW_SEARCH_LOOKUP_HEADER = merge(VIEW_SEARCH_LOOKUP_ADD_HEADER, INBOX_DASHBOARD_HEADER);
    public static final String[] VIEW_SEARCH_LOOKUP_HEADER = Arrays.copyOf(TEMP_VIEW_SEARCH_LOOKUP_HEADER, TEMP_VIEW_SEARCH_LOOKUP_HEADER.length, String[].class);

    public static final Object[] VIEW_VISIBLE_COLUMN = new Object[]{"lastName", "firstName"};
    public static final String[] VIEW_HEADER = new String[]{"Last Name", "First Name"};

    public static final Object[] WF_HISTORY_LOOKUP_ATTACHMENTS_COLUMNS = new Object[]{"documentName", "dateAdded", "userName"};

    public static final String[] WF_HISTORY_LOOKUP_ATTACHMENTS_HEADER = new String[]{"Document Name", "Date Added", "User Name"};
    /**
     *
     * The list name bundle. 
     */
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    public static final String BUSINESS_PROCESS_TYPE_NONMANDATED = "Non Mandated";
    public static final String BUSINESS_PROCESS_TYPE_MANDATED = "Mandated";
    public final static String CONTRACT_TYPE = "CONTRACT_TYPE";
    public final static String RS_TYPE = "RS_TYPE";
    public final static String RS_CATEGORY = "RS_CATEGORY";
    public final static String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";
    public final static String DEDUCTION_TYPE = "Deduction Schedule Type";
    public final static String DEDUCTION_CATEGORY = "Deduction Category";
    public final static String DEDUCTION_PROGRAM_TYPE = "Deduction Program Type";

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
                        if (columnsList.get(j).equals("approvedDate")) {
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
        DynamicQuery userGroupDynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
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
        for (int index = 0; index < 8; index++) {
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
            notif.setDelayMsec(1000);
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
}
