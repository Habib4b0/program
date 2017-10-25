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

    public static String getSelectVariable() {
        return properties.getProperty("SELECT_VARIABLE");
    }

    public static String getGTNDetail() {
        return properties.getProperty("GTN_DETAIL");
    }

    public static String getReserveDetail() {
        return properties.getProperty("RESERVE_DETAIL");
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

    public static String getReturnsConstant() {
        return properties.getProperty("RETURNS");
    }

    public static String getCompanyNo() {
        return properties.getProperty("COMPANY_NO");
    }

    public static String getDivision() {
        return properties.getProperty("DIVISION");
    }

    public static String getBusinessUnit() {
        return properties.getProperty("BUSINESS_UNIT");
    }

    public static String getGLCompanyNo() {
        return properties.getProperty("GL_COMPANY_NO");
    }

    public static String getHistory() {
        return properties.getProperty("HISTORY");
    }

    public static String getCurrent() {
        return properties.getProperty("CURRENT");
    }

    public static String getShowAll() {
        return properties.getProperty("SHOWALL");
    }

    public static String getTimeConstant() {
        return properties.getProperty("DELETETIME");

    }

    public static String getSysSchemaJndicontsant() {
        return properties.getProperty("SYS_SCHEMA_JNDI");
    }
    public static String getArmconstant() {
        return properties.getProperty("ARM");
    }
    public static String getPercent() {
        return properties.getProperty("PERCENT");
    }

    public static String getSlashPercent() {
        return properties.getProperty("SLASH_PERCENT");
    }

    public static String getEscapeSlash() {
        return properties.getProperty("ESCAPE_SLASH");
    }
    
    public static String getPercentForEscape() {
        return properties.getProperty("PERCENT_FOR_ESCAPE");
    }
    
    public static String getArsterisk() {
        return properties.getProperty("ARSTERISK");
    }
    
    public static String getTableNames(final String screenName) {
        return properties.getProperty(screenName);
    }
}
