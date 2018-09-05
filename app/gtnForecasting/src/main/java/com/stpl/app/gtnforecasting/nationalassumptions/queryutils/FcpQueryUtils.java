/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.queryutils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.NACommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.impl.NACommonResultsDAOImpl;
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
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * The Fcp Query Utils .
 */
public class FcpQueryUtils {

    private static final NACommonResultsDAO DAO = new NACommonResultsDAOImpl();

    private static final DecimalFormat CUR_FOUR = new DecimalFormat("$0.0000");

    private final String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FcpQueryUtils.class);

    public List loadFcpResultsTable(int projMasterId, int brandSid, String queryName, int parentLevelId, int itemMasterSID, int therapeuticSid) {
        List fcpList = new ArrayList();
        Map<String, Object> input = new HashMap<>();
        input.put("?PID", projMasterId);

        if (brandSid == 0) {
            input.put("?BID", Constant.NULL_CAPS);
        } else {
            input.put("?BID", brandSid);
        }
        input.put("?IMD", itemMasterSID);

        if (therapeuticSid == 0) {
            input.put("?TID", Constant.NULL_CAPS);
        } else {
            input.put("?TID", therapeuticSid);
        }

        String customSql = SQlUtil.getQuery(getClass(),queryName);

        for (Map.Entry<String, Object> key : input.entrySet()) {
            customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
        }
        if (parentLevelId != 0) {
            customSql += " AND IM.ITEM_MASTER_SID = " + parentLevelId;
        }
        try {
            fcpList = (List) DAO.executeSelectQuery(customSql);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return fcpList;
    }

    public List loadFcpResultsChild(SessionDTO session, int parentSid, List<String> priceTypeList, boolean percentFlag) throws SystemException {
        Map<String, Object> input = new HashMap<>();
        List fcpList;
        String customSql;
        String priceType;
        StringBuilder priceTypeBuilder = new StringBuilder();
        int size = priceTypeList.size();
        int lastOne = size - 1;
        for (int i = 0; i < size; i++) {
            if (i == lastOne) {
                priceTypeBuilder.append(priceTypeList.get(i).toUpperCase(Locale.ENGLISH));
            } else {
                priceTypeBuilder.append(priceTypeList.get(i).toUpperCase(Locale.ENGLISH) ).append( ',');
            }
        }
        priceType = priceTypeBuilder.toString();
        input.put("?PID", session.getProjectionId());
        input.put(Constant.IMID1, parentSid);
        input.put("?PT", priceType);

        if (percentFlag) {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getFcpPercentageForView" : "getFcpPercentage");
        } else {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getFcpAmountForView" : "getFcpAmount");
        }
        for (Map.Entry<String, Object> key : input.entrySet()) {
            customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
        }
        fcpList = (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

        return fcpList;

    }

    public List getNonFamp(SessionDTO session, int brandMasterSid, int therapeuticSid, int parentLevelId, boolean percentFlag) throws SystemException {
        List fcpList;
        Map<String, Object> input = new HashMap<>();
        String customSql;
        String query;
        if (parentLevelId == 0) {
            query = "SELECT ITEM_MASTER_SID FROM ITEM_MASTER WHERE (BRAND_MASTER_SID = @BRAND_MASTER_SID OR @BRAND_MASTER_SID IS NULL) AND (THERAPEUTIC_CLASS = @TH_CLASS OR @TH_CLASS IS NULL)";
        } else {
            query = String.valueOf(parentLevelId);
        }
        input.put("?PID", session.getProjectionId());
        input.put("?BID", brandMasterSid == 0 ? Constant.NULL_CAPS : brandMasterSid);
        input.put("?TID", therapeuticSid == 0 ? Constant.NULL_CAPS : therapeuticSid);
        input.put("?LID", query);

        if (percentFlag) {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getNonFampPercentageForView" : "getNonFampPercentage");
        } else {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getNonFampAmountForView" : "getNonFampAmount");
        }
        for (Map.Entry<String, Object> key : input.entrySet()) {
            customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
        }

        fcpList = (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
        return fcpList;
    }

    public void saveNotes(Map<String, String> editedValues, int projId, int itemSid, String pricetype, SessionDTO session) throws SystemException {
        List<StringBuilder> queryList = new ArrayList<>();
        StringBuilder queryBuilder1 = null;
        if (!editedValues.isEmpty()) {
            for (Map.Entry<String, String> values : editedValues.entrySet()) {
                queryBuilder1 = new StringBuilder();

                String formatedValue = values.getValue();

                String tempValue[] = values.getKey().split("~");
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
                            // Added for CEL-370 CR
                            if (pricetype.equals("QFSS")) {
                                queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT =").append(Constant.NULL_CAPS).append(" , ADJUSTMENT_PRICE=").append(Constant.NULL_CAPS);
                            } else {
                                queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT= ").append(Constant.NULL_CAPS);
                            }
                        } else if (Constant.QFSS.equals(pricetype) 
                                || Constant.QNONFAMP.equals(pricetype)) {
                            queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT='").append(finalvalue).append("' ")
                                    .append(" , ADJUSTMENT_PRICE='").append(finalvalue).append("' ");
                        } else {
                            queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT='").append(finalvalue).append("' ");
                        }
                    } else if (pricetype.equals("QFSS")) {
                        queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS)
                                .append(" , ADJUSTMENT_PRICE=").append(Constant.NULL_CAPS);
                    } else {
                        queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                    }
                } else if (rowId.equals(Constant.NOTES)) {

                    queryBuilder1.append("UPDATE dbo.ST_FCP_PROJ SET NOTES='").append(formatedValue).append("' ");

                }

                queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

                queryBuilder1.append("  in ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS WHERE  NA_PROJ_MASTER_SID=" ).append( projId);

                queryBuilder1.append(" AND ITEM_MASTER_SID=" ).append( itemSid);

                queryBuilder1.append(" ) AND PRICE_TYPE='" ).append( pricetype ).append( '\'');

                queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quarter).append("' ) ");
                String replacedQuery = QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames());
                queryBuilder1 = new StringBuilder(replacedQuery);
                queryList.add(queryBuilder1);

            }
            DAO.executeUpdateQuery(queryList);
            queryList.clear();
        }
    }

    public String[] getTextValue(String propertyId, int itemSid, String pricetype, SessionDTO session) throws SystemException {
        StringBuilder queryBuilder1 = null;

        queryBuilder1 = new StringBuilder();

        String qValue = propertyId.substring(1, NumericConstants.TWO);
        String yearValue = propertyId.substring(NumericConstants.TWO, NumericConstants.SIX);

        int year = Integer.parseInt(yearValue);
        int quarter = Integer.parseInt(qValue);

        queryBuilder1.append(" SELECT ADJUSTMENT,NOTES from ST_FCP_PROJ");

        queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

        queryBuilder1.append("  in ( ");

        queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS WHERE  NA_PROJ_MASTER_SID=" ).append( session.getProjectionId());

        queryBuilder1.append(" AND ITEM_MASTER_SID=" ).append( itemSid);

        queryBuilder1.append(" ) AND PRICE_TYPE='" ).append( pricetype ).append( '\'');

        queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quarter).append("' ) ");
        String replacedQuery = QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames());
        queryBuilder1 = new StringBuilder(replacedQuery);

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

    public List loadFCPWorksheet(SessionDTO session, int ndcSid, boolean annualFlag, boolean adjustFlag) throws SystemException {
        List fcpList;
        Map<String, Object> input = new HashMap<>();
        input.put("?PID", session.getProjectionId());
        input.put(Constant.IMID1, ndcSid);
        String customSql;

        if (annualFlag) {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getFcpWorkSheetYearValuesForView" : "getFcpWorkSheetYearValues");
        } else if (adjustFlag) {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getFcpWorkSheetQtrAdjustmentForView" : "getFcpWorkSheetQtrAdjustment");
        } else {
            customSql = SQlUtil.getQuery(getClass(),Constant.VIEW.equalsIgnoreCase(mode) ? "getFcpWorkSheetQtrValuesForView" : "getFcpWorkSheetQtrValues");
        }
        for (Map.Entry<String, Object> key : input.entrySet()) {
            customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
        }

        fcpList = (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

        return fcpList;
    }

    public List loadFcpParent(int projMasterId, int brandSid, int parentLevelId, com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO session, int therapeuticSid) throws SystemException {

        List fcpList;
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

        if (parentLevelId != 0) {
            customSql = SQlUtil.getQuery(getClass(),"getFCPLevelFilter");
            customSql += " AND IM.ITEM_MASTER_SID = " + parentLevelId;
        } else {
            customSql = SQlUtil.getQuery(getClass(),"getFcpParent");
        }
        for (Map.Entry<String, Object> key : input.entrySet()) {
            customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
        }

        fcpList = (List) DAO.executeSelectQuery(customSql);
        return fcpList;
    }

    public void updateAdjustment(int itemSid, String queryName, SessionDTO sessionDTO) throws PortalException {
        Map<String, Object> input = new HashMap<>();

        input.put(Constant.IMID1, itemSid);

        String customSql = SQlUtil.getQuery(getClass(),queryName);

        for (Map.Entry<String, Object> key : input.entrySet()) {
            customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
        }

        DAO.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, sessionDTO.getCurrentTableNames()));
    }

    public Map<String, String> getFcpPriceTypeNameDynamic(String screenName) throws SystemException {

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
    
    public void updateBeforeAdjustment(String queryName, SessionDTO sessionDTO) throws PortalException {
        String customSql = SQlUtil.getQuery(getClass(),queryName);
        DAO.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, sessionDTO.getCurrentTableNames()));
    }
}
