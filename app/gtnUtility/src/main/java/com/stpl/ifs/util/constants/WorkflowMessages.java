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
 * @author karthikeyans
 */
public class WorkflowMessages {

    private static final ExtProperties properties = PropertiesReader.getInstance().getConfirmationMessages();

    public static String getCW_SubmitConfirmationHeader() {
        return properties.getProperty("CW_SUBMIT_1");
    }

    public static String getCW_SubmitConfirmationMsg() {
        return properties.getProperty("CW_SUBMIT_2");
    }

    public static String getCW_SubmitMandoryValidationHeader() {
        return properties.getProperty("CW_SUBMIT_3");
    }

    public static String getCW_SubmitMandoryValidationMsg() {
        return properties.getProperty("CW_SUBMIT_4");
    }

    public static String getCW_SubmitSuccessHeader() {
        return properties.getProperty("CW_SUBMIT_5");
    }

    public static String getCW_SubmitSuccessMsg() {
        return properties.getProperty("CW_SUBMIT_6");
    }

    public static String getCW_ApproveConfirmationHeader() {
        return properties.getProperty("CW_APPROVE_1");
    }

    public static String getCW_ApproveConfirmationMsg() {
        return properties.getProperty("CW_APPROVE_2");
    }

}
