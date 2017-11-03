/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util.constants;

import com.stpl.app.util.service.PropertiesReader;

/**
 *
 * @author Mohamed.Shahul
 */
public class ForecastingConstants {

    private static final PropertiesReader.ExtProperties PROPERTIES = PropertiesReader.getInstance().getConfirmationMessages();

    public static String getDeleteTimeLimit() {
        return PROPERTIES.getProperty("DELETE_TIME_LIMIT","12");
    }
    
    /**
     * Used to get the temp table Names according to the screenName
     * Screen Name can be Commercial, ARP, Government and Returns
     * Values will be return from GTNproperties from forecasting constant file
     * 
     * @param screenName
     * @return 
     */
    public static String getTableNames(final String screenName) {
        return PROPERTIES.getProperty(screenName);
    }
    
     public static String getPassword() {
        return PROPERTIES.getProperty("PassWord");
    }
}
