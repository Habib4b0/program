/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.util;

import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.transactional.common.util.xmlparser.SQlUtil;
import com.stpl.app.util.ConstantUtil;
import com.vaadin.server.VaadinSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author nadhiya
 */
public class QueryUtils {
    
    private static final Logger LOGGER = Logger.getLogger(QueryUtils.class);

    public static String getPivotQuery(Map<Object, Object> searchValues, int start, int end, boolean isExcel) {

        String value = "null";
        String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID_ARP));
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        if (searchValues.containsKey(ConstantUtil.ACCOUNT)) {
            value = searchValues.get(ConstantUtil.ACCOUNT).toString();
        }
        if (searchValues.containsKey(ConstantUtil.ACCOUNT_TYPE_COL)) {
            value = searchValues.get(ConstantUtil.ACCOUNT_TYPE_COL).toString();
        }
        if (!value.equals("null")) {
            if (value.contains("Sales Dollars")) {
                value = "'EX-FACTORY SALES DOLLARS'";
            } else if (value.contains("Sales Units")) {
                value = "'EX-FACTORY SALES UNITS'";
            } else if (value.contains("Accrual Dollars")){
                value = "'Accrual Dollars'";
            }else if(value.contains("Accrual Rate")){
                 value = "'Accrual Rate'";
            }else if(value.contains("Deduction")){ 
                 value = "'Accrual Rate,Accrual Dollars'";
            }
        }

        String pivotQuery = SQlUtil.getQuery(isExcel ? "arp-outbound-excel-query" : "arp-outbound-search-query");
        pivotQuery = pivotQuery.replace("[?USER_ID]", userId);
        pivotQuery = pivotQuery.replace("[?SESSION_ID]", sessionId);
        pivotQuery = pivotQuery.replace("[?ACCOUNT_TYPE]", value);
        pivotQuery = pivotQuery.replace("[?START]", StringUtils.EMPTY + start);
        pivotQuery = pivotQuery.replace("[?END]", StringUtils.EMPTY + end);
        
        return pivotQuery;
    }

    public static String getExcelQuery() {
        return "SELECT \n"
                + "    FINANCIAL_FORECAST_ID,\n"
                + "    FINANCIAL_FORECAST_NAME,\n"
                + "    CFT.DESCRIPTION AS TYPE,\n"
                + "    PROJECT_ID,\n"
                + "    PROJECTION_NAME,\n"
                + "    YEAR,\n"
                + "    MONTH,\n"
                + "    QUOTENAME(CONTRACT_ID,CHAR(34)) as CONTRACT_ID,\n"
                + "    QUOTENAME(CONTRACT_NO,CHAR(34)) as CONTRACT_NO,\n"
                + "    QUOTENAME(CONTRACT_NAME,CHAR(34)) as CONTRACT_NAME,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT1.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT1.DESCRIPTION\n"
                + "    END AS CONTRACT_TYPE,\n"
                + "    QUOTENAME(CONTRACT_HOLDER_ID,CHAR(34)) as CONTRACT_HOLDER_ID,\n"
                + "    QUOTENAME(CONTRACT_HOLDER_NO,CHAR(34)) as CONTRACT_HOLDER_NO,\n"
                + "    QUOTENAME(CONTRACT_HOLDER_NAME,CHAR(34)) as CONTRACT_HOLDER_NAME,\n"
                + "    QUOTENAME(CUSTOMER_ID,CHAR(34)) as CUSTOMER_ID,\n"
                + "    QUOTENAME(CUSTOMER_NO,CHAR(34)) as CUSTOMER_NO,\n"
                + "    QUOTENAME(CUSTOMER_NAME,CHAR(34)) as CUSTOMER_NAME,\n"
                + "    QUOTENAME(ITEM_ID,CHAR(34)) as ITEM_ID,\n"
                + "    QUOTENAME(ITEM_NO,CHAR(34)) as ITEM_NO,\n"
                + "    QUOTENAME(ITEM_NAME,CHAR(34)) as ITEM_NAME,\n"
                + "    SALES_DOLLARS,\n"
                + "    SALES_UNITS,\n"
                + "    SALES_INCLUSION,\n"
                + "    QUOTENAME(DEDUCTION_ID,CHAR(34)) as DEDUCTION_ID,\n"
                + "    QUOTENAME(DEDUCTION_NO,CHAR(34)) as DEDUCTION_NO,\n"
                + "    QUOTENAME(DEDUCTION_NAME,CHAR(34)) as DEDUCTION_NAME,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT2.DESCRIPTION = '-Select One-'\n"
                + "        THEN NULL \n"
                + "        ELSE HT2.DESCRIPTION\n"
                + "    END AS DEDUCTION_CATEGORY,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT3.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT3.DESCRIPTION\n"
                + "    END AS DEDUCTION_TYPE,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT4.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT4.DESCRIPTION\n"
                + "    END AS DEDUCTION_PROGRAM,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT5.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT5.DESCRIPTION\n"
                + "    END AS DEDUCTION_INCLUSION,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT6.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT6.DESCRIPTION\n"
                + "    END AS DEDUCTION_CATEGORY1,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT7.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT7.DESCRIPTION\n"
                + "    END AS DEDUCTION_CATEGORY2,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT8.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT8.DESCRIPTION\n"
                + "    END AS DEDUCTION_CATEGORY3,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT9.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT9.DESCRIPTION\n"
                + "    END AS DEDUCTION_CATEGORY4,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT10.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT10.DESCRIPTION\n"
                + "    END AS DEDUCTION_CATEGORY5,\n"
                + ConstantUtil.CASE1
                + "        WHEN HT11.DESCRIPTION = '-Select One-'\n"
                + ConstantUtil.THEN_NULL
                + "        ELSE HT11.DESCRIPTION\n"
                + "    END AS DEDUCTION_CATEGORY6,\n"
                + "    DEDUCTION_DOLLARS,\n"
                + "    DEDUCTION_RATE,\n"
                + "    DEDUCTION_PER_UNIT,\n"
                + "    NET_SALES_DOLLAR,\n"
                + "    COGS_AMOUNT,\n"
                + "    COGS_PER_UNIT,\n"
                + "    NET_PROFIT_DOLLARS,\n"
                + "    NET_PROFIT_PER_UNIT,\n"
                + "    QUOTENAME(COMPANY_ID,CHAR(34)) as COMPANY_ID,\n"
                + "    QUOTENAME(COMPANY_NO,CHAR(34)) as COMPANY_NO,\n"
                + "    QUOTENAME(COMPANY_NAME,CHAR(34)) as COMPANY_NAME,\n"
                + "    QUOTENAME(BUSINESS_UNIT_ID,CHAR(34)) as BUSINESS_UNIT_ID,\n"
                + "    QUOTENAME(BUSINESS_UNIT_NO,CHAR(34)) as BUSINESS_UNIT_NO,\n"
                + "    QUOTENAME(BUSINESS_UNIT_NAME,CHAR(34)) as BUSINESS_UNIT_NAME,\n"
                + "    FORMAT(FINANCIAL_FORECAST_CREATION_DATE, 'MM/dd/yyyy') as FINANCIAL_FORECAST_CREATION_DATE ,\n" 
                + "    FORMAT(FINANCIAL_FORECAST_APPROVAL_DATE, 'MM/dd/yyyy') as FINANCIAL_FORECAST_APPROVAL_DATE ,\n"
                + "    OUTBOUND_STATUS,\n"
                + "    ORIGINAL_BATCH_ID\n"
                + "    FROM\n"
                + "    ST_CFF_OUTBOUND_MASTER ST\n"
                + "    JOIN CFF_OUTBOUND_MASTER  B ON ST.CFF_DETAILS_SID = B.CFF_DETAILS_SID\n"
                + "    AND ST.RS_MODEL_SID = B.RS_MODEL_SID\n"
                + "    AND ST.PERIOD_SID = B.PERIOD_SID\n"
                + "\n"
                + "    LEFT JOIN HELPER_TABLE CFT\n"
                + "        ON CFT.HELPER_TABLE_SID = B.\"TYPE\" LEFT JOIN HELPER_TABLE HT1\n"
                + "        ON HT1.HELPER_TABLE_SID = B.CONTRACT_TYPE LEFT JOIN HELPER_TABLE HT2\n"
                + "        ON HT2.HELPER_TABLE_SID = B.DEDUCTION_CATEGORY LEFT JOIN HELPER_TABLE HT3\n"
                + "        ON HT3.HELPER_TABLE_SID = B.DEDUCTION_TYPE LEFT JOIN HELPER_TABLE HT4\n"
                + "        ON HT4.HELPER_TABLE_SID = B.DEDUCTION_PROGRAM LEFT JOIN HELPER_TABLE HT5\n"
                + "        ON HT5.HELPER_TABLE_SID = B.DEDUCTION_INCLUSION LEFT JOIN HELPER_TABLE HT6\n"
                + "        ON HT6.HELPER_TABLE_SID = B.DEDUCTION_CATEGORY1 LEFT JOIN HELPER_TABLE HT7\n"
                + "        ON HT7.HELPER_TABLE_SID = B.DEDUCTION_CATEGORY2 LEFT JOIN HELPER_TABLE HT8\n"
                + "        ON HT8.HELPER_TABLE_SID = B.DEDUCTION_CATEGORY3 LEFT JOIN HELPER_TABLE HT9\n"
                + "        ON HT9.HELPER_TABLE_sid = B.DEDUCTION_CATEGORY4 LEFT JOIN HELPER_TABLE HT10\n"
                + "        ON HT10.HELPER_TABLE_SID = B.DEDUCTION_CATEGORY5 LEFT JOIN HELPER_TABLE HT11\n"
                + "        ON HT11.HELPER_TABLE_sid = B.DEDUCTION_CATEGORY6";
    }

    public static List loadDeductionsValues(List input, String queryName) {
        String query2 = "JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID =  ? AND HT.LIST_NAME like '?'";
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                for (Object temp : input) {
                    if (sql.toString().contains(ConstantUtil.JOIN_2)) {
                        if (temp instanceof String && "A.RS_ID".equals(temp)) {
                            sql.replace(sql.indexOf(ConstantUtil.DESC), sql.indexOf(ConstantUtil.DESC) + ConstantUtil.DESC.length() + 1, " A.RS_MODEL_SID, A.RS_ID ");
                            sql.replace(sql.indexOf(JOIN_N2), sql.indexOf(JOIN_N2) + JOIN_N2.length() + 1, "");
                        } else {
                            sql.replace(sql.indexOf(ConstantUtil.DESC), sql.indexOf(ConstantUtil.DESC) + ConstantUtil.DESC.length() + 1, " ?, HT.DESCRIPTION ");
                            sql.replace(sql.indexOf(JOIN_N2), sql.indexOf(JOIN_N2) + JOIN_N2.length() + 1, String.valueOf(query2));
                        }
                    }
                    if (sql.toString().contains("?")) {
                        sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                    } else {
                        break;
                    }

                }
                list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        return list;
    }
    public static final String JOIN_N2 = "~JOIN2~";

    public static String splitter(String input) {
        String splitted = "";
        String[] split = input.split("\\.");
        splitted = split[split.length - 1];
        if (!splitted.startsWith("UDC")) {
            splitted = "%";
        } else {
            splitted = "RS_" + splitted;
        }
        return splitted;
    }
    public static String getGTSExcelQuery() {
        
      return "SELECT GTS.FORECAST_NAME,\n" +
                "GTS.FORECAST_VER,\n" +
                "GTS.COUNTRY,\n" +
                "FORECAST_YEAR,\n" +
                "FORECAST_MONTH,\n" +
                "ITEM_ID,\n" +
                "COMPANY_ID,\n" +
                "DEDUCTION_ID,\n" +
                ConstantUtil.CASE +
                "    WHEN HT1.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT1.DESCRIPTION\n" +
                "END AS DEDUCTION_CATEGORY,\n" +
                "BRAND,\n" +
                ConstantUtil.CASE+
                "    WHEN HT2.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT2.DESCRIPTION\n" +
                "END AS DEDUCTION_TYPE,\n" +
                ConstantUtil.CASE+
                "    WHEN HT3.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT3.DESCRIPTION\n" +
                "END AS DEDUCTION_PROGRAM_TYPE,\n" +
                "UNITS,\n" +
                "PRICE_TYPE,\n" +
                "ISNULL(PRICE,0) as PRICE,\n" +
                "ISNULL(SALES_AMOUNT,0) as SALES_AMOUNT,\n" +
                "SALES_INCLUSION,\n" +
                "ADJUSTMENT_CODE,\n" +
                "ISNULL(DEDUCTION_RATE,0) as DEDUCTION_RATE,\n" +
                "ISNULL(DEDUCTION_AMOUNT,0) as DEDUCTION_AMOUNT,\n" +
                "DEDUCTION_INCLUSION,\n" +
                "FORECAST_VALUE_TYPE,\n" +
                "SEGMENT,\n" +
                "GTS.\"SOURCE\",\n" +
                "GTS.BATCH_ID,\n" +
                "ADD_CHG_DEL_INDICATOR,\n" +
                "CONCAT(US.FIRSTNAME,' ',US.LASTNAME)  as CREATED_BY,\n" +
                ConstantUtil.CASE+
                "    WHEN HT4.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT4.DESCRIPTION\n" +
                "END AS UDC1,\n" +
                ConstantUtil.CASE+
                "    WHEN HT5.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT5.DESCRIPTION\n" +
                "END AS UDC2,\n" +
                "CONVERT(varchar(20),GTS.CREATED_DATE,110) as CREATED_DATE,\n" +
                "CASE WHEN CONVERT(VARCHAR(20),GTS.MODIFIED_BY)='1' then '' ELSE CONVERT(VARCHAR(20),GTS.MODIFIED_BY) END as MODIFIED_BY,\n" +
                ConstantUtil.CASE+
                "    WHEN HT6.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT6.DESCRIPTION\n" +
                "END AS UDC3,\n" +
                ConstantUtil.CASE+
                "    WHEN HT7.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT7.DESCRIPTION\n" +
                "END AS UDC4,\n" +
                "CONVERT(varchar(20),GTS.MODIFIED_DATE,110)as MODIFIED_DATE,\n"+
                ConstantUtil.CASE+
                "    WHEN HT8.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT8.DESCRIPTION\n" +
                "END AS UDC5,\n" +
                ConstantUtil.CASE+
                "    WHEN HT9.DESCRIPTION = '-Select One-'\n" +
                ConstantUtil.THEN_NULL +
                "    ELSE HT9.DESCRIPTION\n" +
                "END AS UDC6,\n" +
                "CONVERT(varchar(20),GTS.FORECAST_DATE,110)as FORECAST_DATE,\n" +
                "GTS.CUSTOMER_GTS_FORECAST_INTF_ID FROM VW_CUSTOMER_GTS_FORECAST GTS\n" +
                "LEFT JOIN HELPER_TABLE HT1 ON HT1.DESCRIPTION  = GTS.DEDUCTION_CATEGORY\n" +
                "LEFT JOIN HELPER_TABLE HT2 ON HT2.DESCRIPTION = GTS.DEDUCTION_TYPE\n" +
                "LEFT JOIN HELPER_TABLE HT3 ON HT3.DESCRIPTION = GTS.DEDUCTION_PROGRAM_TYPE\n" +
                "LEFT JOIN HELPER_TABLE HT4 ON HT4.HELPER_TABLE_SID = GTS.UDC1\n" +
                "LEFT JOIN HELPER_TABLE HT5 ON HT5.HELPER_TABLE_SID = GTS.UDC2\n" +
                "LEFT JOIN HELPER_TABLE HT6 ON HT6.HELPER_TABLE_SID = GTS.UDC3\n" +
                "LEFT JOIN HELPER_TABLE HT7 ON HT7.HELPER_TABLE_SID = GTS.UDC4\n" +
                "LEFT JOIN HELPER_TABLE HT8 ON HT8.HELPER_TABLE_SID = GTS.UDC5\n" +
                "LEFT JOIN HELPER_TABLE HT9 ON HT9.HELPER_TABLE_SID = GTS.UDC6\n"+
                "JOIN ?.dbo.USER_ US  ON US.UserId = GTS.CREATED_BY WHERE";
    }
}
