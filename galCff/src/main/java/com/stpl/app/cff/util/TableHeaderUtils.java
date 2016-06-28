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
     * Result Tables Columns and Headers in Consolidated Financial Forecast Search Form
     */
    public static final Object[] RESULT_TABLE_VISIBLE_COLUMN=new Object[]{"financialForecastId","financialForecastName","typeDesc","statusDesc",
        "finalApprovalDate","approvedBy"};
     public static final Object[] RESULT_TABLE_VISIBLE_COLUMN_EXCEL=new Object[]{"financialForecastId","financialForecastName","typeDesc","statusDesc",
        "finalApprovalDateExcel","approvedBy"};
    public static final String[] RESULT_TABLE_HEADER=new String[]{"Financial Forecast ID","Financial Forecast Name","Type","Status",
        "Final Approval Date","Approved By"};
        
    /**
     * Approval Table Columns and Headers
     */
    public static final Object[] APPROVAL_TABLE_VISIBLE_COLUMN=new Object[]{"approvedBy","approvedDate","approvalSequence"};
    public static final String[] APPROVAL_TABLE_HEADER=new String[]{"Approved By","Approved Date","Approval Sequence"};
    
    /**
     * Approved Result Table Columns and Headers
     */
    public static final Object[] APPROVED_RESULT_TABLE_VISIBLE_COLUMN=new Object[]{"workflowId","projectionName","createdDate",
        "createdBy","approvalDate","approvedBy","priorLatestEstimate","priorUpdateCycle"};
    public static final String[] APPROVED_RESULT_TABLE_HEADER=new String[]{"Projection ID","Projection Name","Creation Date","Created By",
        "Approval Date","Approved By","Prior Latest Estimate","Prior Update Cycle"};
    
    /**
    * The Constant ATTACHMENT_COLUMNS.
    */
    public static final Object[] ATTACHMENT_COLUMNS = new Object[] {"documentName", "dateAdded", "userName"};
    /**
    * The Constant ATTACHMENT_HEADER.
    */
    public static final String[] ATTACHMENT_HEADER = new String[] { "Document Name", "Date Added", "User Name"};
    
}
