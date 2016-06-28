package com.stpl.app.galforecasting.nationalassumptions.queryutils;

/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.stpl.app.galforecasting.dao.CommonResultsDAO;
import com.stpl.app.galforecasting.dao.impl.CommonResultsDAOImpl;
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
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class MedicaidQueryUtils {

    private static final CommonResultsDAO DAO = new CommonResultsDAOImpl();

    private static final DecimalFormat CUR_FOUR = new DecimalFormat("$0.0000");
    /**
     * The Constant LOGGER.
     */
    private final Logger LOGGER = Logger.getLogger(MedicaidQueryUtils.class);

    public List loadMedicaidResultsTable(int projMasterId, int brandSid, String queryName, String ndc9Level, int therapeuticSid) {
        List medicaidList = new ArrayList();
        try {
            Map<String, Object> input = new HashMap<String, Object>();
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
            input.put("?NDC9", ndc9Level);

            String customSql = CustomSQLUtil
                    .get(queryName);
            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
            medicaidList = (List) DAO.executeSelectQuery(customSql);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return medicaidList;
    }

    public List loadMedicaidResultsChild(int projMasterId, String parentSid, List<String> priceTypeList, boolean percentFlag) throws PortalException, SystemException {
        Map<String, Object> input = new HashMap<String, Object>();
        List medicaidList;
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
        input.put("?NDC9", "'" + parentSid + "'");
        input.put("?PT", priceType);
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);

        if (percentFlag) {
            customSql = CustomSQLUtil.get("getMedicaidPercentage");
        } else {
            customSql = CustomSQLUtil.get("getMedicaidAmount");
        }
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        medicaidList = (List) DAO.executeSelectQuery(customSql);

        return medicaidList;

    }

    public void saveNotes(Map<String, String> editedValues, int projId, String ndc9, String pricetype) throws PortalException, SystemException, Exception {
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
                            queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                        } else {
                            queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT='").append(finalvalue).append("' ");
                        }
                    } else {
                        queryBuilder1.append(" UPDATE dbo.ST_MEDICAID_URA_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                    }
                } else if (rowId.equals(Constant.NOTES)) {

                    queryBuilder1.append("UPDATE dbo.ST_MEDICAID_URA_PROJ SET NOTES='").append(formatedValue).append("' ");

                }

                queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

                queryBuilder1.append("  in ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS NPD INNER JOIN ITEM_MASTER IM ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID WHERE  NA_PROJ_MASTER_SID=" + projId);

                queryBuilder1.append("  AND NDC9='" + ndc9.trim());

                queryBuilder1.append("') AND PRICE_TYPE='" + pricetype + "'");

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

        queryBuilder1.append(" SELECT ADJUSTMENT,NOTES from ST_MEDICAID_URA_PROJ");

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

    public void saveBaseYearNotes(Map<String, String> editedValues, int projId, String ndc9, String pricetype) throws PortalException, SystemException, Exception {
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
                String rowId = tempValue[1];
                Double finalvalue = 0.0;

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

                queryBuilder1.append("  in ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS NPD INNER JOIN ITEM_MASTER IM ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID WHERE  NA_PROJ_MASTER_SID=" + projId);

                queryBuilder1.append("  AND NDC9='" + ndc9.trim());

                queryBuilder1.append("') AND PRICE_TYPE='" + pricetype + "'");

                queryBuilder1.append(" AND USER_ID = '" + userId.intValue() + "'  AND SESSION_ID ='" + sessionId + "' ");

                queryList.add(queryBuilder1);

            }
            DAO.executeUpdateQuery(queryList);
            queryList.clear();
        }

    }

    public List loadMedicaidWorksheet(int projMasterId, String ndc9, boolean adjustFlag) throws PortalException, SystemException {
        List phsWSList;
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        String queryName;
        ndc9 = "'" + ndc9 + "'";
        if (adjustFlag) {
            queryName = "getMedicaidWorkSheetAdjustment";
        } else {
            queryName = "getMedicaidWorkSheet";
        }
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projMasterId);
        input.put("?NDC9", ndc9);
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);
        String customSql = CustomSQLUtil.get(queryName);

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        phsWSList = (List) DAO.executeSelectQuery(customSql);

        return phsWSList;
    }

    public List loadMedicaidParent(int projMasterId, int brandSid, String ndc9LevelFilter, SessionDTO session, int therapeuticSid) throws PortalException, SystemException {
        List medicaidList;
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

        if (StringUtils.isNotBlank(ndc9LevelFilter)) {
            customSql = CustomSQLUtil.get("getMedicaidLevelFilter");
            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
            customSql += " AND IM.NDC9 = '" + ndc9LevelFilter + "'";
            customSql += " GROUP  BY IM.NDC9,IM.ITEM_DESC ORDER  BY IM.NDC9 ";
        } else {
            customSql = CustomSQLUtil.get("getMedicaidParent");
            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
        }

        medicaidList = (List) DAO.executeSelectQuery(customSql);
        return medicaidList;
    }

    public void updateAdjustment(String ndc9, String queryName) throws PortalException, SystemException, Exception {
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        List<StringBuilder> queryList = new ArrayList<StringBuilder>();
        Map<String, Object> input = new HashMap<String, Object>();
        ndc9 = "'" + ndc9 + "'";
        input.put("?NDC9", ndc9);
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);

        String customSql = CustomSQLUtil.get(queryName);

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }
        DAO.executeUpdateQuery(customSql);
        queryList.clear();
    }

    public List loadMedicaidDdlb(int projMasterId, int brandSid, int therapeuticSid, String filterText, int start, int end) throws PortalException, SystemException {
        List medicaidList;
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
        input.put("?OFFSET", start + 1);
        input.put("?LIMIT", end);
        input.put("?TEXT", filterText);
        String customSql = CustomSQLUtil.get("getMedicaidDropDown");
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        medicaidList = (List) DAO.executeSelectQuery(customSql);
        return medicaidList;
    }

}
