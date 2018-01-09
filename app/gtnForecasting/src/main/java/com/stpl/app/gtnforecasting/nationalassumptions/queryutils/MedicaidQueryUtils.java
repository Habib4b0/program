package com.stpl.app.gtnforecasting.nationalassumptions.queryutils;

/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonResultsDAOImpl;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.vaadin.server.VaadinSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class MedicaidQueryUtils {

    private static final CommonResultsDAO DAO = new CommonResultsDAOImpl();

    private static final DecimalFormat CUR_FOUR = new DecimalFormat("$0.0000");

    public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    /**
     * The Constant LOGGER.
     */
    private final Logger LOGGER = LoggerFactory.getLogger(MedicaidQueryUtils.class);

    public List loadMedicaidResultsTable(int projMasterId, int brandSid, String queryName, String ndc9Level, int therapeuticSid) {
        List medicaidList = new ArrayList();
        try {
            Map<String, Object> input = new HashMap<>();
            ndc9Level = "'" + ndc9Level + "'";
            input.put("?PID", projMasterId);
            if (brandSid == 0) {
                input.put("?BID", Constant.NULL_CAPS);
            } else {
                input.put("?BID", brandSid);
            }
            if (therapeuticSid == 0) {
                input.put("?TID", Constant.NULL_CAPS);
            } else {
                input.put("?TID", therapeuticSid);
            }
            input.put(Constant.NDC_NINE_QUESTION, ndc9Level);

            String customSql = SQlUtil
                    .getQuery(getClass(),queryName);
            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
            medicaidList = (List) DAO.executeSelectQuery(customSql);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return medicaidList;
    }

    public List loadMedicaidResultsChild(SessionDTO session, String parentSid, List<String> priceTypeList, boolean percentFlag) throws PortalException, SystemException {
        Map<String, Object> input = new HashMap<>();
        List medicaidList;
        String customSql;
        String priceType = StringUtils.EMPTY;
        int size = priceTypeList.size();
        int lastOne = size - 1;
        for (int i = 0; i < size; i++) {
            if (i == lastOne) {
                priceType += priceTypeList.get(i).toUpperCase();
            } else {
                priceType += priceTypeList.get(i).toUpperCase() + ",";
            }
        }

        input.put("?PID", session.getProjectionId());
        input.put(Constant.NDC_NINE_QUESTION, "'" + parentSid + "'");
        input.put("?PT", priceType);

        if (percentFlag) {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getMedicaidPercentageForView" : "getMedicaidPercentage");
        } else {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getMedicaidAmountForView" : "getMedicaidAmount");
        }
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        medicaidList = (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

        return medicaidList;

    }

    public void saveNotes(Map<String, String> editedValues, SessionDTO session, String ndc9, String pricetype) throws PortalException, SystemException {
        List<StringBuilder> queryList = new ArrayList<>();
        StringBuilder queryBuilder1 = null;
        if (!editedValues.isEmpty()) {
            for (String values : editedValues.keySet()) {
                queryBuilder1 = new StringBuilder(StringUtils.EMPTY);

                String formatedValue = editedValues.get(values);

                String tempValue[] = values.split("~");
                String propertyId = tempValue[0];
                String rowId = tempValue[1];
                String qValue = propertyId.substring(1, NumericConstants.TWO);
                String yearValue = propertyId.substring(NumericConstants.TWO, NumericConstants.SIX);

                int year = Integer.parseInt(yearValue);
                int quarter = Integer.parseInt(qValue);
                Double finalvalue;

                if (rowId.equals(Constant.ADJUSTMENT)) {
                    formatedValue = formatedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                    formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
                    formatedValue = formatedValue.trim();
                    if (StringUtils.isNotBlank(formatedValue)) {
                        Double value = Double.valueOf(formatedValue);
                        finalvalue = value;
                        if (value == 0) {
                            queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT =").append(Constant.NULL_CAPS);
                        } else {
                            queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT='").append(finalvalue).append("' ");
                        }
                        if (Constant.CPIURA.equals(pricetype)) {
                            queryBuilder1.append(" , ADJUSTMENT_PRICE = '").append(finalvalue).append("'");
                        }
                    } else {
                        queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                    }
                } else if (rowId.equals(Constant.NOTES)) {

                    queryBuilder1.append("UPDATE dbo.ST_MEDICAID_URA_PROJ SET NOTES='").append(formatedValue).append("' ");

                }

                queryBuilder1.append(" where NA_PROJ_DETAILS_SID  ");

                queryBuilder1.append("  in ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS NPD INNER JOIN ITEM_MASTER IM ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID WHERE  NA_PROJ_MASTER_SID =").append(session.getProjectionId());

                queryBuilder1.append("  AND NDC9 = '").append(ndc9.trim());

                queryBuilder1.append("') AND PRICE_TYPE ='").append(pricetype).append("'");

                queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quarter).append("' ) ");

                String replacedQuery = QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames());
                queryBuilder1 = new StringBuilder(replacedQuery);
                queryList.add(queryBuilder1);

            }
            DAO.executeUpdateQuery(queryList);
            queryList.clear();
        }
    }

    public String[] getTextValue(String propertyId, SessionDTO session, int itemSid, String pricetype) throws PortalException, SystemException {
        List<StringBuilder> queryList = new ArrayList<>();
        StringBuilder queryBuilder1 = null;

        queryBuilder1 = new StringBuilder(StringUtils.EMPTY);

        String qValue = propertyId.substring(1, NumericConstants.TWO);
        String yearValue = propertyId.substring(NumericConstants.TWO, NumericConstants.SIX);

        int year = Integer.parseInt(yearValue);
        int quarter = Integer.parseInt(qValue);

        queryBuilder1.append(" SELECT ADJUSTMENT,NOTES from ST_MEDICAID_URA_PROJ");

        queryBuilder1.append(" where NA_PROJ_DETAILS_SID  ");

        queryBuilder1.append("  in ( ");

        queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS WHERE  NA_PROJ_MASTER_SID=" + session.getProjectionId());

        queryBuilder1.append(" AND ITEM_MASTER_SID=" + itemSid);

        queryBuilder1.append(" ) AND PRICE_TYPE='" + pricetype + "'");

        queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quarter).append("' ) ");

        String replacedQuery = QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames());
        queryBuilder1 = new StringBuilder(replacedQuery);
        queryList.add(queryBuilder1);

        List list = (List) DAO.executeSelectQuery(String.valueOf(queryBuilder1));
        String notesText[] = new String[NumericConstants.TWO];
        if (list.isEmpty()) {
            notesText[0] = StringUtils.EMPTY;
            notesText[1] = StringUtils.EMPTY;
        } else {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                notesText[0] = String.valueOf(obj[0] == null ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CUR_FOUR, String.valueOf(obj[0])));

                notesText[1] = String.valueOf(obj[1] == null ? StringUtils.EMPTY : obj[1]);

            }
        }

        return notesText;
    }

    public void saveBaseYearNotes(Map<String, String> editedValues, SessionDTO session, String ndc9, String pricetype) throws PortalException, SystemException {
        List<StringBuilder> queryList = new ArrayList<>();
        StringBuilder queryBuilder1 = null;
        if (!editedValues.isEmpty()) {
            for (String values : editedValues.keySet()) {
                queryBuilder1 = new StringBuilder(StringUtils.EMPTY);

                String formatedValue = editedValues.get(values);

                String tempValue[] = values.split("~");
                String rowId = tempValue[1];
                Double finalvalue;

                if (rowId.equals(Constant.ADJUSTMENT)) {
                    formatedValue = formatedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                    formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
                    formatedValue = formatedValue.trim();
                    if (StringUtils.isNotBlank(formatedValue)) {
                        Double value = Double.valueOf(formatedValue);
                        finalvalue = value;
                        queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT='").append(finalvalue).append("' ");
                    } else {
                        queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT=").append(0);
                    }
                } else if (rowId.equals(Constant.NOTES)) {

                    queryBuilder1.append("UPDATE dbo.ST_MEDICAID_URA_PROJ SET NOTES='").append(formatedValue).append("' ");

                }

                queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

                queryBuilder1.append("  in  ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS NPD INNER JOIN ITEM_MASTER  IM ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID WHERE  NA_PROJ_MASTER_SID=" + session.getProjectionId());

                queryBuilder1.append("  AND NDC9='").append(ndc9.trim());

                queryBuilder1.append("') AND PRICE_TYPE='").append(pricetype).append("'");

                String replacedQuery = QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames());
                queryBuilder1 = new StringBuilder(replacedQuery);
                queryList.add(queryBuilder1);

            }
            DAO.executeUpdateQuery(queryList);
            queryList.clear();
        }

    }

    public List loadMedicaidWorksheet(SessionDTO session, String ndc9, boolean adjustFlag) throws PortalException, SystemException {
        List phsWSList;
        String queryName;
        ndc9 = "'" + ndc9 + "'";
        if (adjustFlag) {
            queryName = Constant.VIEW.equalsIgnoreCase(mode) ? "getMedicaidWorkSheetAdjustmentForView" : "getMedicaidWorkSheetAdjustment";
        } else {
            queryName = Constant.VIEW.equalsIgnoreCase(mode) ? "getMedicaidWorkSheetForView" : "getMedicaidWorkSheet";
        }
        Map<String, Object> input = new HashMap<>();
        input.put("?PID", session.getProjectionId());
        input.put(Constant.NDC_NINE_QUESTION, ndc9);
        String customSql = SQlUtil.getQuery(getClass(),queryName);

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        phsWSList = (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

        return phsWSList;
    }

    public Map<String, String> getPriceTypeNameDynamic(String screenName) throws PortalException, SystemException {
        List<Object[]> phsWSList;
        Map<String, String> priceType = new HashMap<>();

        String customSql = SQlUtil.getQuery("Medicaid_Ura_Worsheet_Helper_table");
        customSql = customSql.replace("SCREEN_NAME", screenName);
        phsWSList = (List<Object[]>) DAO.executeSelectQuery(customSql);
        for (int i = 0; i < phsWSList.size(); i++) {
            Object[] obj = phsWSList.get(i);
            priceType.put(String.valueOf(obj[1]), String.valueOf(obj[0]));
        }
        return priceType;
    }

    public List loadMedicaidParent(int projMasterId, int brandSid, String ndc9LevelFilter, com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO session, int therapeuticSid) throws PortalException, SystemException {
        List medicaidList;
        Map<String, Object> input = new HashMap<>();
        input.put("?PID", projMasterId);
        if (brandSid == 0) {
            input.put("?BID", Constant.NULL_CAPS);
        } else {
            input.put("?BID", brandSid);
        }
        if (therapeuticSid == 0) {
            input.put("?TID", Constant.NULL_CAPS);
        } else {
            input.put("?TID", therapeuticSid);
        }
        if (session.isPageFlag()) {
            input.put("?OFFSET", +session.getStart() + 1);
            input.put("?LIMIT", session.getOffset());
        }

        String customSql;

        if (StringUtils.isNotBlank(ndc9LevelFilter)) {
            customSql = SQlUtil.getQuery("getMedicaidLevelFilter");
            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
            customSql += " AND IM.NDC9 = '" + ndc9LevelFilter + "'";
            customSql += " GROUP  BY IM.NDC9,IM.ITEM_DESC ORDER  BY IM.NDC9 ";
        } else {
            customSql = SQlUtil.getQuery("getMedicaidParent");
            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
        }

        medicaidList = (List) DAO.executeSelectQuery(customSql);
        return medicaidList;
    }

    public void updateAdjustment(String ndc9, String queryName, SessionDTO session) throws PortalException, SystemException {
        List<StringBuilder> queryList = new ArrayList<>();
        Map<String, Object> input = new HashMap<>();
        ndc9 = "'" + ndc9 + "'";
        input.put(Constant.NDC_NINE_QUESTION, ndc9);

        String customSql = SQlUtil.getQuery(getClass(),queryName);

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }
        DAO.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
        queryList.clear();
    }

    public List loadMedicaidDdlb(int projMasterId, int brandSid, int therapeuticSid, String filterText, int start, int end) throws PortalException, SystemException {
        List medicaidList;
        Map<String, Object> input = new HashMap<>();
        input.put("?PID", projMasterId);
        if (brandSid == 0) {
            input.put("?BID", Constant.NULL_CAPS);
        } else {
            input.put("?BID", brandSid);
        }
        if (therapeuticSid == 0) {
            input.put("?TID", Constant.NULL_CAPS);
        } else {
            input.put("?TID", therapeuticSid);
        }
        input.put("?OFFSET", start + 1);
        input.put("?LIMIT", end);
        input.put("?TEXT", filterText);
        String customSql = SQlUtil.getQuery(getClass(),"getMedicaidDropDown");
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        medicaidList = (List) DAO.executeSelectQuery(customSql);
        return medicaidList;
    }

    public void saveBaseYear(Map<String, String> editedValues, SessionDTO session, String ndc9, String priceType) throws PortalException, SystemException {
        List<StringBuilder> queryList = new ArrayList<>();
        StringBuilder queryBuilder1 = null;
        if (!editedValues.isEmpty()) {
            for (String values : editedValues.keySet()) {
                queryBuilder1 = new StringBuilder(StringUtils.EMPTY);
                String formatedValue = editedValues.get(values);
                String tempValue[] = values.split("~");
                String rowId = tempValue[1];
                Double finalvalue;

                if (rowId.equals(Constant.ADJUSTMENT)) {
                    formatedValue = formatedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                    formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
                    formatedValue = formatedValue.trim();
                    if (StringUtils.isNotBlank(formatedValue)) {
                        Double value = Double.valueOf(formatedValue);
                        finalvalue = value;
                        queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_ACTUALS SET ALT_BASE_YEAR='").append(finalvalue).append("' ");
                    } else {
                        queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_ACTUALS SET ALT_BASE_YEAR=").append(0);
                    }
                } else if (rowId.equals(Constant.NOTES)) {

                    queryBuilder1.append("UPDATE dbo.ST_MEDICAID_URA_ACTUALS SET NOTES='").append(formatedValue).append("' ");

                }

                queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

                queryBuilder1.append("  in  ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS NPD INNER JOIN ITEM_MASTER IM ON NPD.ITEM_MASTER_SID =  IM.ITEM_MASTER_SID WHERE  NA_PROJ_MASTER_SID= " + session.getProjectionId());

                queryBuilder1.append("  AND NDC9='" + ndc9.trim());

                queryBuilder1.append("') AND PRICE_TYPE='" + (priceType.isEmpty() ? tempValue[2] : priceType) + "'");

                String replacedQuery = QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames());
                queryBuilder1 = new StringBuilder(replacedQuery);
                queryList.add(queryBuilder1);

            }
            DAO.executeUpdateQuery(queryList);
            queryList.clear();
        }

    }
    
    /**ALG-3140	**/
    public void removeOverrideOnClose(SessionDTO session) throws PortalException, SystemException {
        String customSql = "UPDATE ST_MEDICAID_URA_PROJ SET ADJUSTMENT = null";
        DAO.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
    }

}
