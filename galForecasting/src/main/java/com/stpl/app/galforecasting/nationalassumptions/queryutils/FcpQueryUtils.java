/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.queryutils;

import com.stpl.app.galforecasting.dao.NACommonResultsDAO;
import com.stpl.app.galforecasting.dao.impl.NACommonResultsDAOImpl;
import com.stpl.app.galforecasting.nationalassumptions.dto.SessionDTO;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SESSION_ID;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.server.VaadinSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;

import org.apache.commons.lang.StringUtils;

/**
 *
 * The Fcp Query Utils .
 */
public class FcpQueryUtils {

    private static final NACommonResultsDAO DAO = new NACommonResultsDAOImpl();

    private static final DecimalFormat CUR_FOUR = new DecimalFormat("$0.0000");
    /**
     * The Constant LOGGER.
     */
    private final Logger LOGGER = Logger.getLogger(FcpQueryUtils.class);

    public List loadFcpResultsTable(int projMasterId, int brandSid, String queryName, int parentLevelId, int itemMasterSID, int therapeuticSid) {
        List fcpList = new ArrayList();
        Map<String, Object> input = new HashMap<String, Object>();
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

        String customSql = CustomSQLUtil.get(queryName);

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }
        if (parentLevelId != 0) {
            customSql += " AND IM.ITEM_MASTER_SID = " + parentLevelId;
        }
        try {
            fcpList = (List) DAO.executeSelectQuery(customSql);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return fcpList;
    }

    public List loadFcpResultsChild(int projMasterId, int parentSid, List<String> priceTypeList, boolean percentFlag) throws PortalException, SystemException {
        Map<String, Object> input = new HashMap<String, Object>();
        List fcpList;
        String customSql;
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
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

        input.put("?PID", projMasterId);
        input.put("?IMID", parentSid);
        input.put("?PT", priceType);
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);

        if (percentFlag) {
            customSql = CustomSQLUtil.get("getFcpPercentage");
        } else {
            customSql = CustomSQLUtil.get("getFcpAmount");
        }
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }
        fcpList = (List) DAO.executeSelectQuery(customSql);

        return fcpList;

    }

    public List getNonFamp(int projMasterId, int brandMasterSid, int therapeuticSid, int parentLevelId, boolean percentFlag) throws PortalException, SystemException {
        List fcpList;
        Map<String, Object> input = new HashMap<String, Object>();
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        String customSql;
        String query;
        if (parentLevelId == 0) {
            query = "SELECT ITEM_MASTER_SID FROM ITEM_MASTER WHERE (BRAND_MASTER_SID = @BRAND_MASTER_SID OR @BRAND_MASTER_SID IS NULL) AND (THERAPEUTIC_CLASS = @TH_CLASS OR @TH_CLASS IS NULL)";
        } else {
            query = String.valueOf(parentLevelId);
        }
        input.put("?PID", projMasterId);
        input.put("?BID", brandMasterSid == 0 ? Constant.NULL_CAPS : brandMasterSid);
        input.put("?TID", therapeuticSid == 0 ? Constant.NULL_CAPS : therapeuticSid);
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);
        input.put("?LID", query);

        if (percentFlag) {
            customSql = CustomSQLUtil.get("getNonFampPercentage");
        } else {
            customSql = CustomSQLUtil.get("getNonFampAmount");
        }
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        fcpList = (List) DAO.executeSelectQuery(customSql);
        return fcpList;
    }

    public void saveNotes(Map<String, String> editedValues, int projId, int itemSid, String pricetype) throws PortalException, SystemException, Exception {
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        List<StringBuilder> queryList = new ArrayList<StringBuilder>();
        StringBuilder queryBuilder1 = null;
        if (!editedValues.isEmpty()) {
            for (String values : editedValues.keySet()) {
                queryBuilder1 = new StringBuilder(StringUtils.EMPTY);

                String formatedValue = editedValues.get(values);

                String tempValue[] = values.split("~");
                String propertyId = tempValue[0];
                String rowId = tempValue[1];
                String qValue = propertyId.substring(1, 2);
                String yearValue = propertyId.substring(2, 6);

                int year = Integer.parseInt(yearValue);
                int quarter = Integer.parseInt(qValue);
                Double finalvalue = 0.0;

                if (rowId.equals(Constant.ADJUSTMENT)) {
                    formatedValue = formatedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                    formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
                    formatedValue = formatedValue.trim();
                    if (StringUtils.isNotBlank(formatedValue)) {
                        Double value = Double.valueOf(formatedValue);
                        finalvalue = value;
                        if (value == 0) {
                            queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                        } else {
                            queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT='").append(finalvalue).append("' ");
                        }
                    } else {
                        queryBuilder1.append(" UPDATE dbo.ST_FCP_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                    }
                } else if (rowId.equals(Constant.NOTES)) {

                    queryBuilder1.append("UPDATE dbo.ST_FCP_PROJ SET NOTES='").append(formatedValue).append("' ");

                }

                queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

                queryBuilder1.append("  in ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS WHERE  NA_PROJ_MASTER_SID=" + projId);

                queryBuilder1.append(" AND ITEM_MASTER_SID=" + itemSid);

                queryBuilder1.append(" ) AND PRICE_TYPE='" + pricetype + "'");

                queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quarter).append("' ) ");

                queryBuilder1.append(" AND USER_ID = '" + userId.intValue() + "'  AND SESSION_ID ='" + sessionId + "' ");

                queryList.add(queryBuilder1);

            }
            DAO.executeUpdateQuery(queryList);
            queryList.clear();
        }
    }

    public String[] getTextValue(String propertyId, int projId, int itemSid, String pricetype) throws PortalException, SystemException, Exception {
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        List<StringBuilder> queryList = new ArrayList<StringBuilder>();
        StringBuilder queryBuilder1 = null;

        queryBuilder1 = new StringBuilder(StringUtils.EMPTY);

        String qValue = propertyId.substring(1, 2);
        String yearValue = propertyId.substring(2, 6);

        int year = Integer.parseInt(yearValue);
        int quarter = Integer.parseInt(qValue);

        queryBuilder1.append(" SELECT ADJUSTMENT,NOTES from ST_FCP_PROJ");

        queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

        queryBuilder1.append("  in ( ");

        queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS WHERE  NA_PROJ_MASTER_SID=" + projId);

        queryBuilder1.append(" AND ITEM_MASTER_SID=" + itemSid);

        queryBuilder1.append(" ) AND PRICE_TYPE='" + pricetype + "'");

        queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quarter).append("' ) ");

        queryBuilder1.append(" AND USER_ID = '" + userId.intValue() + "'  AND SESSION_ID ='" + sessionId + "' ");

        queryList.add(queryBuilder1);

        List list = (List) DAO.executeSelectQuery(String.valueOf(queryBuilder1));
        String notesText[] = new String[2];
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

    public List loadFCPWorksheet(int projMasterId, int ndcSid, boolean annualFlag, boolean adjustFlag) throws PortalException, SystemException {
        List fcpList;
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projMasterId);
        input.put("?IMID", ndcSid);
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);
        String customSql = StringUtils.EMPTY;

        if (annualFlag) {
            customSql = CustomSQLUtil.get("getFcpWorkSheetYearValues");
        } else {
            if (adjustFlag) {
                customSql = CustomSQLUtil.get("getFcpWorkSheetQtrAdjustment");
            } else {
                customSql = CustomSQLUtil.get("getFcpWorkSheetQtrValues");
            }
        }
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        fcpList = (List) DAO.executeSelectQuery(customSql);

        return fcpList;
    }

    public List loadFcpParent(int projMasterId, int brandSid, int parentLevelId, SessionDTO session, int therapeuticSid) throws PortalException, SystemException {

        List fcpList;
        Map<String, Object> input = new HashMap<String, Object>();
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
            customSql = CustomSQLUtil.get("getFCPLevelFilter");
            customSql += " AND IM.ITEM_MASTER_SID = " + parentLevelId;
        } else {
            customSql = CustomSQLUtil.get("getFcpParent");
        }
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        fcpList = (List) DAO.executeSelectQuery(customSql);
        return fcpList;
    }

    public void updateAdjustment(int itemSid, String queryName) throws PortalException, SystemException, Exception {
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        List<StringBuilder> queryList = new ArrayList<StringBuilder>();
        Map<String, Object> input = new HashMap<String, Object>();

        input.put("?IMID", itemSid);
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);

        String customSql = CustomSQLUtil.get(queryName);

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        DAO.executeUpdateQuery(customSql);
        queryList.clear();
    }
}
