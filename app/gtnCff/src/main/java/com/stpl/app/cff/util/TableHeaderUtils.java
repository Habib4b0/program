/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

/**
 *
 * @author Manasa
 */
public class TableHeaderUtils {

    /**
     * Result Tables Columns and Headers in Consolidated Financial Forecast
     * Search Form
     */
    public final Object[] resultTableVisibleColumn = new Object[]{"financialForecastId", "financialForecastName", "typeDesc", "statusDesc",
        "finalApprovalDate", StringConstantsUtil.APPROVED_BY_PROPERTY};
    public final Object[] resultTableVisibleColumnExcel = new Object[]{"financialForecastId", "financialForecastName", "typeDesc", "statusDesc",
        "finalApprovalDateExcel", StringConstantsUtil.APPROVED_BY_PROPERTY};

    public final String[] resultTableHeader = new String[]{"Financial Forecast ID", "Financial Forecast Name", "Type", "Status",
        "Final Approval Date", StringConstantsUtil.APPROVED_BY_HEADER};

    /**
     * Approval Table Columns and Headers
     */
    public final Object[] approvalTableVisibleColumn = new Object[]{StringConstantsUtil.APPROVED_BY_PROPERTY, "approvedDate", "approvalSequence"};
    public final String[] approvalTableHeader = new String[]{StringConstantsUtil.APPROVED_BY_HEADER, "Approved Date", "Approval Sequence"};

    /**
     * Approved Result Table Columns and Headers
     */
    public final Object[] approvedResultTableVisibleColumn = new Object[]{"workflowId", "projectionName", "createdDate",
        "createdBy", "approvalDate", StringConstantsUtil.APPROVED_BY_PROPERTY, "priorLatestEstimate", "priorUpdateCycle"};
    public final String[] approvedResultTableHeader = new String[]{"Projection ID", "Projection Name", "Creation Date", "Created By",
        "Approval Date", StringConstantsUtil.APPROVED_BY_HEADER, "Prior Latest Estimate", "Prior Update Cycle"};

    /**
     * The Constant ATTACHMENT_COLUMNS.
     */
    public final Object[] attachmentColumns = new Object[]{"documentName", "dateAdded", "userName"};
    /**
     * The Constant ATTACHMENT_HEADER.
     */
    public final String[] attachmentHeader = new String[]{"Document Name", "Date Added", "User Name"};
    
    private TableHeaderUtils(){
        /*
        Constructor
        */
    }
    private static TableHeaderUtils object;

    public static TableHeaderUtils getInstance() {
        if (object == null) {
            object = new TableHeaderUtils();
        }
        return object;
    }

}
