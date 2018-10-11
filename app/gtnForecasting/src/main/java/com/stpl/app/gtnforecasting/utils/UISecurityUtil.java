package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.security.permission.model.AppPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Class contains the constants and method related to security.
 *
 * @author
 */
public final class UISecurityUtil {
    public static final String DATA_SELECTION_INDEX = "Data Selection Index";
    public static final String DATA_SELECTION = "Data Selection";
    public static final String DISCOUNT_PROJECTION = "Discount Projection";
    public static final String DISCOUNT_PROJECTION_RESULTS = "Discount Projection Results";
    public static final String SALES_PROJECTION = "Sales Projection";
    public static final String SALES_PROJECTION_RESULTS = "Sales Projection Results";
    public static final String COMMON = "Common";
    
    public static final String PPA_PROJECTION = "PPA Projection";
    public static final String PPA_PROJECTION_RESULTS = "Ppa Projection Results";
    public static final String PROJECTION_RESULTS = "Projection Results";
    public static final String PROJECTION_VARIANCE = "Projection Variance";
    public static final String SUPPLEMENTAL_DISCOUNT = "Supplemental Discount Projection";
    public static final String MANDATED = "Mandated";
    public static final String NON_MANDATED = "Non Mandated";
    /**
     * private constructor to make the class as utility class.
     */
    private UISecurityUtil() {
//Empty
    }

    /**
     * Modifies the table based on the user id and permission granted for the
     * fields.
     *
     * @param obj - Object array
     * @param header - String array
     * @param fieldHM - HashMap contain userId and description.
     * @return the table result custom
     */
    public static TableResultCustom modifyTableResult(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM) {

        final TableResultCustom tblResultCustom = new TableResultCustom();


        String str;
        final List<Object> objResultList = new ArrayList();
        final List<String> objResultHeaderList = new ArrayList();

        for (int i = 0; i < obj.length; i++) {
            str = String.valueOf(obj[i]);
            if (fieldHM.get(str) != null) {

                    objResultList.add(obj[i]);
                    objResultHeaderList.add(header[i]);
            }
        }
        Object[] objResult = new Object[objResultList.size()];
        String[] objResultHeader = new String[objResultList.size()];
        for (int i = 0; i < objResultList.size(); i++) {
            objResult[i] = objResultList.get(i);
            objResultHeader[i] = objResultHeaderList.get(i);
        }
        tblResultCustom.setObjResult(objResult);
        tblResultCustom.setObjResultHeader(objResultHeader);
        return tblResultCustom;
    }

}
