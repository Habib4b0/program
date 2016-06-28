/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util.constants;

import com.stpl.app.util.service.PropertiesReader;
import com.stpl.app.util.service.PropertiesReader.ExtProperties;

/**
 *
 * @author sriram
 */
public final class WorkflowConstants {

    private static final ExtProperties properties = PropertiesReader.getInstance().getConfirmationMessages();

    public static String getGovWorkflowParameter() {
        return properties.getProperty("GOV_WORKFLOW_PARAMETER");
    }

    public static String getComWorkflowParameter() {
        return properties.getProperty("COM_WORKFLOW_PARAMETER");
    }

    public static String getCreatorConstant() {
        return properties.getProperty("WI_CREATOR");
    }

    public static String getApproverConstant() {
        return properties.getProperty("WI_APPROVER");
    }

    public static String getApprovedStatus() {
        return properties.getProperty("W_APPROVED");
    }

    public static String getPendingStatus() {
        return properties.getProperty("W_PENDING");
    }

    public static String getWithdrawnStatus() {
        return properties.getProperty("W_WITHDRAWN");
    }

    public static String getCancelledStatus() {
        return properties.getProperty("W_CANCELLED");
    }

    public static String getRejectedStatus() {
        return properties.getProperty("W_REJECTED");
    }
    
    public static String getAccrualWorkflowParameter() {
        return properties.getProperty("ACCRUAL_WORKFLOW_PARAMETER");
    }
     public static String getContractWorkflowParameter() {
        return properties.getProperty("CONTRACT_WORKFLOW_PARAMETER");
    }
     public static String getBusinessProcessNameContract() {
        return properties.getProperty("BP_CONTRACT");
    }
     public static String getBusinessProcessNameForecast() {
        return properties.getProperty("BP_FORECASTING");
    }
     public static String getBusinessProcessNameAccurals() {
        return properties.getProperty("BP_ACCURALS");
    }
}
