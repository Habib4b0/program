package com.stpl.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.stpl.app.security.permission.model.AppPermission;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
/**
 * Class contains the constants and method related to security.
 *
 * @author
 */
public final class UISecurityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(UISecurityUtil.class);

    /**
     * The Constant ITEM_MASTER_FIELD_SECURE_HM.
     */
    public static final String ITEM_MASTER_FIELD_SECURE_HM = "permissionItemMasterField";

    /**
     * The Constant ITEM_MASTER_FUNCTION_SECURE_HM.
     */
    public static final String ITEM_MASTER_FUNCTION_SECURE_HM = "permissionItemMasterFunction";

    /**
     * The Constant ITEM_MASTER_TAB_SECURE_HM.
     */
    public static final String ITEM_MASTER_TAB_SECURE_HM = "permissionItemMasterTab";

    /**
     * The Constant CFP_FIELD_SECURE_HM.
     */
    public static final String CFP_FIELD_SECURE_HM = "permissionCfpField";

    /**
     * The Constant CFP_FUNCTION_SECURE_HM.
     */
    public static final String CFP_FUNCTION_SECURE_HM = "permissionCfpFunction";

    /**
     * The Constant CFP_TAB_SECURE_HM.
     */
    public static final String CFP_TAB_SECURE_HM = "permissionCfpTab";

    /**
     * The Constant PS_FIELD_SECURE_HM.
     */
    public static final String PS_FIELD_SECURE_HM = "permissionPsField";

    /**
     * The Constant PS_FUNCTION_SECURE_HM.
     */
    public static final String PS_FUNCTION_SECURE_HM = "permissionPsFunction";

    /**
     * The Constant PS_TAB_SECURE_HM.
     */
    public static final String PS_TAB_SECURE_HM = "permissionPsTab";

    /**
     * The Constant IFP_FIELD_SECURE_HM.
     */
    public static final String IFP_FIELD_SECURE_HM = "permissionIfpField";

    /**
     * The Constant IFP_FUNCTION_SECURE_HM.
     */
    public static final String IFP_FUNCTION_SECURE_HM = "permissionIfpFunction";

    /**
     * The Constant IFP_TAB_SECURE_HM.
     */
    public static final String IFP_TAB_SECURE_HM = "permissionIfpTab";

    /**
     * The Constant RS_FIELD_SECURE_HM.
     */
    public static final String RS_FIELD_SECURE_HM = "permissionRsField";

    /**
     * The Constant RS_FUNCTION_SECURE_HM.
     */
    public static final String RS_FUNCTION_SECURE_HM = "permissionRsFunction";

    /**
     * The Constant RS_TAB_SECURE_HM.
     */
    public static final String RS_TAB_SECURE_HM = "permissionRsTab";

    /**
     * The Constant COMPANY_FIELD_SECURE_HM.
     */
    public static final String COMPANY_FIELD_SECURE_HM = "permissionCompanyField";

    /**
     * The Constant COMPANY_FUNCTION_SECURE_HM.
     */
    public static final String COMPANY_FUNCTION_SECURE_HM = "permissionCompanyFunction";

    /**
     * The Constant COMPANY_TAB_SECURE_HM.
     */
    public static final String COMPANY_TAB_SECURE_HM = "permissionCompanyTab";

    /**
     * The Constant RP_FIELD_SECURE_HM.
     */
    public static final String RP_FIELD_SECURE_HM = "permissionRpField";

    /**
     * The Constant RP_FUNCTION_SECURE_HM.
     */
    public static final String RP_FUNCTION_SECURE_HM = "permissionRpFunction";

    /**
     * The Constant RP_TAB_SECURE_HM.
     */
    public static final String RP_TAB_SECURE_HM = "permissionRpTab";

    /**
     * The Constant ITEM_MASTER.
     */
    public static final String ITEM_MASTER = "Item Master";

    /**
     * The Constant COMPANY_MASTER.
     */
    public static final String COMPANY_MASTER = "Company Master";

    /**
     * The Constant COMPANY_FAMILY_PLAN.
     */
    public static final String COMPANY_FAMILY_PLAN = "Company Family Plan";

    /**
     * The Constant ITEM_FAMILY_PLAN.
     */
    public static final String ITEM_FAMILY_PLAN = "Item Family Plan";

    /**
     * The Constant PRICE_SCHEDULE.
     */
    public static final String PRICE_SCHEDULE = "Price Schedule";

    /**
     * The Constant REBATE_SCHEDULE.
     */
    public static final String REBATE_SCHEDULE = "Rebate Schedule";

    /**
     * The Constant REBATE_PLAN.
     */
    public static final String REBATE_PLAN = "Rebate Plan";

    /**
     * The Constant REBATE_PLAN.
     */
    public static final String CALCULATION_HEADER = "Calculations Header";
    /**
     * The Constant DEDUCTION_DETAILS.
     */
    public static final String DEDUCTION_DETAILS = "Deduction Details";
    /**
     * The Constant Net Sales Formula
     *
     */
    public static final String NET_SALES_FORMULA = "Net Sales Formula";

    /**
     * The Constant COMPLIANCE_DEDUCTION_RULES.
     */
    public static final String COMPLIANCE_DEDUCTION_RULES = "Compliance Deduction Rules";
    
    /**
     * private constructor to make the class as utility class.
     */
    public UISecurityUtil() {
//Empty
    }

    public static TableResultCustom getTableColumnsPermission(List<Object> resultList, Object[] obj, Map<String, AppPermission> fieldIfpHM, String mode) {
        TableResultCustom tableResultCustom = new TableResultCustom();
        List<Object> strList = Arrays.asList(obj);
        List<String> columnList = new ArrayList<>();
        List<Object> columnList1 = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> headerList2 = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i++) {
            Object[] objResult = (Object[]) resultList.get(i);
            String value = objResult[1].toString();
            if (strList.contains(value)) {
                columnList.add(value);
                headerList.add(objResult[0].toString());
            }
        }
        for (Object headerList1 : strList) {
            if (columnList.contains(headerList1.toString())) {
                columnList1.add(headerList1);
                headerList2.add(headerList.get(columnList.indexOf(headerList1.toString())));
            }
        }
        String[] headerArray = new String[headerList2.size()];
        headerArray = headerList2.toArray(headerArray);
        for (int i = 0; i < headerList2.size(); i++) {
            LOGGER.debug("headerArray----------- {}" , headerArray[i]);
        }
        tableResultCustom = modifyTableResultSecurity(columnList1.toArray(), headerArray, fieldIfpHM, mode);
        return tableResultCustom;
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
    public static TableResultCustom modifyTableResult(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM)  {

        final TableResultCustom tblResultCustom = new TableResultCustom();

        String str = "";
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

    public static TableResultCustom modifyTableResultSecurity(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM, String mode) {

        final TableResultCustom tblResultCustom = new TableResultCustom();
        boolean appPerm = false;

        String str = "";
        final List<Object> objResultList = new ArrayList();
        final List<String> objResultHeaderList = new ArrayList();

        for (int i = 0; i < obj.length; i++) {
            str = String.valueOf(obj[i]);
            if (fieldHM.get(str) != null) {
                final AppPermission appPermission = fieldHM.get(str);
                if (mode.equals(ConstantsUtils.ADD)) {
                    appPerm = appPermission.isAddFlag();
                }
                if (mode.equals(ConstantsUtils.EDIT)) {
                    appPerm = appPermission.isEditFlag();
                }
                if (mode.equals(ConstantsUtils.VIEW)) {
                    appPerm = appPermission.isViewFlag();
                }
                if (appPerm == true) {
                    objResultList.add(obj[i]);
                    objResultHeaderList.add(header[i]);
                }
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
