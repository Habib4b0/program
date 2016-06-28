package com.stpl.ifs.util.constants;

import com.stpl.app.util.service.PropertiesReader;

/**
 *
 * @author sriram
 */
public final class GlobalConstants {

    private static final PropertiesReader.ExtProperties properties = PropertiesReader.getInstance().getConfirmationMessages();

    public static String getGovernmentConstant() {
        return properties.getProperty("GOVERNMENT");
    }

    public static String getCommercialConstant() {
        return properties.getProperty("COMMERCIAL");
    }

    public static String getSelectOne() {
        return properties.getProperty("SELECT_ONE");
    }

    public static String getCommonDateFormat() {
        return properties.getProperty("COMMON_DATE_FORMAT");
    }
    
    public static String getAccrualConstant() {
        return properties.getProperty("ACCRUAL");
    }
     public static String getContractConstant() {
        return properties.getProperty("CONTRACT");
    }
}
