/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.bpm.util;

/**
 *
 * @author harlin
 */
public class MessageUtils {

    public static String CH_ERROR = "Customer Hierarchy has been changed from Version [$OV] to Version [$NV].\n";
    public static String PH_ERROR = "Product Hierarchy has been changed from Version [$OV] to Version [$NV].\n";
    public static String WFP_START_ERROR = "You dont have permission to create a projection.";
    public static String WFP_EDIT_ERROR = "You dont have permission to edit a projection.";
    public static String WFP_SUBMIT_PER_ERROR = "You dont have permission to submit a projection.";
    public static String WFP_APPROVE_PER_ERROR = "You dont have permission to approve a projection.";
    public static String WFP_REJECT_PER_ERROR = "You dont have permission to approve a projection.";
    public static String WFP_SUBMIT_ERROR = "The workflow cannot be submitted for approval.  Not all required fields have been completed.";
    public static String WFP_SUBMIT_SUCCESS = "Workflow succesfully submitted for Approval process.";

}
