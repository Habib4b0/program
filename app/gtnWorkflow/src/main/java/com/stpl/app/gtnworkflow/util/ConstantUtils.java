/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.util;

import static com.stpl.app.gtnworkflow.util.CommonUtils.merge;
import java.util.Arrays;

/**
 *
 * @author gopinath
 */
public class ConstantUtils {

    private static ConstantUtils object;

    /**
     * Constructor
     */
    private ConstantUtils() {
        /*
            Constructor
         */
    }
    public static final String USER_ID = "userId";
    public static final String DATEFORMATM_MMDDYY = "MM/dd/yyyy";
    public final Object[] wfHistoryLookupColumns = new Object[]{
        CommonUtils.STATUS, CommonUtils.MODIFIED_DATE, CommonUtils.MODIFIED_BY, CommonUtils.NOTES, "attachmentLink"};
    public final String[] wfHistoryLookupHeader = new String[]{
        CommonUtils.STATUS_HEADER, CommonUtils.MODIFIED_DATE_HEADER, CommonUtils.MODIFIED_BY_HEADER, CommonUtils.NOTES_HEADER, "Attachement"};
    public final Object[] wfHistoryLookupColumnsArm = new Object[]{
        CommonUtils.STATUS, CommonUtils.MODIFIED_DATE, CommonUtils.MODIFIED_BY, CommonUtils.NOTES};
    public final String[] wfHistoryLookupHeaderArm = new String[]{
        CommonUtils.STATUS_HEADER, CommonUtils.MODIFIED_DATE_HEADER, CommonUtils.MODIFIED_BY_HEADER, CommonUtils.NOTES_HEADER};
    /**
     * Visible columns for Workflow Dashboard. If this is changed,
     * getDbColumnName() method should be changed in workflowlogic.java
     */
    public final Object[] inboxDashboardColumns = new Object[]{
        CommonUtils.WORKFLOW_ID, CommonUtils.WORKFLOW_NAME, CommonUtils.WORKFLOW_DESCRIPTION, CommonUtils.WORK_FLOW_STATUS, "company", CommonUtils.BUSINESS_UNIT, CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.APPROVED_BY, CommonUtils.APPROVED_DATE};

    public final String[] inboxDashboardHeader = new String[]{
        "Workflow ID", CommonUtils.WORKFLOW_NAME_HEADER, CommonUtils.WORKFLOW_DESCRIPTION_HEADER, CommonUtils.STATUS_HEADER, "Company Name", "Business Unit Name", CommonUtils.CREATED_BY_HEADER, CommonUtils.CREATION_DATE_HEADER, CommonUtils.APPROVED_BY_HEADER, CommonUtils.APPROVED_DATE_HEADER};
    public final Object[] inboxDashboardColumnsOthers = new Object[]{
        CommonUtils.WORKFLOW_ID, CommonUtils.WORKFLOW_NAME, CommonUtils.WORKFLOW_DESCRIPTION, CommonUtils.WORK_FLOW_STATUS, CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.APPROVED_BY, CommonUtils.APPROVED_DATE};

    public final String[] inboxDashboardHeaderOthers = new String[]{
        "Workflow Id", CommonUtils.WORKFLOW_NAME_HEADER, CommonUtils.WORKFLOW_DESCRIPTION_HEADER, CommonUtils.STATUS_HEADER, CommonUtils.CREATED_BY_HEADER, CommonUtils.CREATION_DATE_HEADER, CommonUtils.APPROVED_BY_HEADER, CommonUtils.APPROVED_DATE_HEADER};

    public final Object[] inboxDashboardColumnsSearch = new Object[]{
        CommonUtils.WORKFLOW_ID, CommonUtils.WORKFLOW_NAME, "adjustmentTypeValue", "company", CommonUtils.BUSINESS_UNIT, CommonUtils.WORK_FLOW_STATUS, CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.APPROVED_BY, CommonUtils.APPROVED_DATE};

    public final String[] inboxDashboardHeaderSearch = new String[]{
        "Workflow ID", CommonUtils.WORKFLOW_NAME_HEADER, "Adjustment Type", "Company", "Business Unit", CommonUtils.STATUS_HEADER, CommonUtils.CREATED_BY_HEADER, CommonUtils.CREATION_DATE_HEADER, CommonUtils.APPROVED_BY_HEADER, CommonUtils.APPROVED_DATE_HEADER};

    public final Object[] inboxDashboardColumnsArm = new Object[]{
        "viewType", CommonUtils.CREATED_DATE, CommonUtils.CREATED_BY, CommonUtils.MODIFIED_DATE, CommonUtils.MODIFIED_BY,};

    public final String[] inboxDashboardHeaderArm = new String[]{
        "View Type", CommonUtils.CREATION_DATE_HEADER, CommonUtils.CREATED_BY_HEADER, CommonUtils.MODIFIED_DATE_HEADER, CommonUtils.MODIFIED_BY_HEADER,};

    public final Object[] viewSearchLookupAddColumns = new Object[]{"viewName"};
    public final String[] viewSearchLookupAddHeader = new String[]{"View Name"};

    public final Object[] viewSearchLookupColumns = merge(viewSearchLookupAddColumns, inboxDashboardColumns);
    private final Object[] tempViewSearchLookupHeader = merge(viewSearchLookupAddHeader, inboxDashboardHeader);

    public final Object[] viewSearchLookupColumnsArm = merge(viewSearchLookupAddColumns, inboxDashboardColumnsArm);
    private final Object[] tempViewSearchLookupHeaderArm = merge(viewSearchLookupAddHeader, inboxDashboardHeaderArm);

    public final String[] viewSearchLookupHeader = Arrays.copyOf(tempViewSearchLookupHeader, tempViewSearchLookupHeader.length, String[].class);
    public final String[] viewSearchLookupHeaderArm = Arrays.copyOf(tempViewSearchLookupHeaderArm, tempViewSearchLookupHeaderArm.length, String[].class);

    public final Object[] viewVisibleColumn = new Object[]{CommonUtils.LAST_NAME, CommonUtils.FIRST_NAME};
    public final String[] viewHeader = new String[]{"Last Name", "First Name"};

    public final Object[] viewVisibleColumnArm = new Object[]{"fullName", CommonUtils.FIRST_NAME, CommonUtils.LAST_NAME};
    public final String[] viewHeaderArm = new String[]{"User Name", "User First Name", "User Last Name"};

    public final Object[] wfHistoryLookupAttachmentsColumns = new Object[]{"documentName", "dateAdded", "userName"};

    public final String[] wfHistoryLookupAttachmentsHeader = new String[]{"Document Name", "Date Added", "User Name"};

    public static ConstantUtils getInstance() {
        if (object == null) {
            object = new ConstantUtils();
        }
        return object;
    }
}
