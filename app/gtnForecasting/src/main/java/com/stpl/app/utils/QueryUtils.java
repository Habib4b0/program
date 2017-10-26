/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.Constants.CommonConstants.CONTRACT_DETAILS;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author vigneshkanna
 */
public class QueryUtils {

    private static final CommonDAOImpl commonDao = new CommonDAOImpl();
    private static final Logger LOGGER = Logger.getLogger(QueryUtils.class);
    public static final String TILT = "~";

    /*
     This method will return the period sid for the selected period and frequency
     */
    public String periodQuery(String period, String fre, String order) {
        String startYearValue = period.substring(period.length() - NumericConstants.FOUR);
        String startFreqNoValue = period.substring(1, NumericConstants.TWO);
        int startYear = isInteger(startYearValue) ? Integer.valueOf(startYearValue) : 0;
        String where = StringUtils.EMPTY;

        if (fre.equals(MONTHLY.getConstant())) {
            String startMonthValue = period.substring(0, period.length() - NumericConstants.FIVE);
            int startFreqNo = CommonUtils.getIntegerForMonth(startMonthValue);
            where = "where \"MONTH\" = '" + startFreqNo + "' and \"YEAR\" = '" + startYear + "'";
        } else if (fre.equals(QUARTERLY.getConstant())) {
            where = "where QUARTER = '" + startFreqNoValue + "' and \"YEAR\" = '" + startYear + "'";
        } else if (fre.equals(ANNUALLY.getConstant())) {
            where = "where  \"YEAR\" = '" + startYear + "'";
        } else {
            where = "where SEMI_ANNUAL = '" + startFreqNoValue + "' and \"YEAR\" = '" + startYear + "'";
        }
        String query = "select " + order + "(PERIOD_SID) from \"PERIOD\" " + where;
        return query;
    }

    /**
     * This method will update the table before calling calculation procedure
     * for commercial
     *
     * @param projectionId
     * @param userId
     * @param sessionId
     * @param projectionSelection
     * @param levelType
     * @param allocationMethodology
     * @param periodsMap
     */
    public void updateDiscProjMasterCalc(int projectionId, String userId, String sessionId, ProjectionSelectionDTO projectionSelection, String levelType,
            String allocationMethodology, Map<String, Map<String, List<String>>> periodsMap,List<String> selectedDiscount,Boolean isProgram) {
        LOGGER.debug(" entering updateInputsForCalc");
        try {

            String baselinePeriods = StringUtils.EMPTY;
            List<String> baselinePeriodsList;
            List<String> selectedPeriodsList;
            List<String> periodsList = new ArrayList<>();
            if (projectionSelection.getMethodology().equals(CONTRACT_DETAILS.getConstant())) {
                baselinePeriods = " ";
                for (String discountName : selectedDiscount) {
                     updateBaseLinePeriods(baselinePeriods, projectionSelection, levelType, discountName, projectionId, userId, sessionId,isProgram);
                }
            } else {
                for (String discountName : periodsMap.keySet()) {

                    baselinePeriodsList = periodsMap.get(discountName).get("H");
                    selectedPeriodsList = periodsMap.get(discountName).get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    periodsList.clear();
                    if (baselinePeriodsList != null) {
                        periodsList.addAll(baselinePeriodsList);
                    }
                    if (selectedPeriodsList != null) {
                        periodsList.addAll(selectedPeriodsList);
                    }
                    baselinePeriods = CommonUtils.collectionToString(periodsList, false, true);

                    baselinePeriods = baselinePeriods.replace(", ", ",");

                    if (projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                        baselinePeriods = CommonUtils.replaceShortMonthForMonth(baselinePeriods);
                    }

                    updateBaseLinePeriods(baselinePeriods, projectionSelection, levelType, discountName, projectionId, userId, sessionId,isProgram);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            LOGGER.debug(" exiting updateInputsForCalc");
        }
    }
    
    public int companyCount(Map<String, Object> parameters,SessionDTO session) {

        int count = 0;
        String query = "SELECT COUNT (*) FROM (";
        query += SQlUtil.getQuery("CUSTOMER_SELECTION_AVAILABLE_SEARCH_QUERY");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());

        String customer = String.valueOf(parameters.get(Constant.CUSTOMER_NO));

         String whereClause=" WHERE ";
        String andClause = " AND ";
        boolean isWhereClaues = true;
        
      
        if (!customer.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause) + " CM1.COMPANY_NO like '" + customer + "'";
            isWhereClaues = false;
        }

        String contractHolder = String.valueOf(parameters.get("contractHolder"));

        if (!contractHolder.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CM.COMPANY_NAME like '" + contractHolder + "'";
             isWhereClaues = false;
        }

        String contractNo = String.valueOf(parameters.get("contractNo"));

        if (!contractNo.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CON_M.CONTRACT_NO like '" + contractNo + "'";
             isWhereClaues = false;
        }

        String contractName = String.valueOf(parameters.get("contractName"));

        if (!contractName.equals(Constant.NULL)) {
            query = query +  (isWhereClaues ? whereClause : andClause) +"  CON_M.CONTRACT_NAME like '" + contractName + "'";
             isWhereClaues = false;
        }
        String customerName = String.valueOf(parameters.get(Constant.CUSTOMER_NAME));

        if (!customerName.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CM1.COMPANY_NAME like '" + customerName + "'";
             isWhereClaues = false;
        }
        String marketType = String.valueOf(parameters.get("marketType"));

        if (!marketType.equals(Constant.NULL) && !marketType.equals(Constant.SELECT_ONE)) {
            query = query +  (isWhereClaues ? whereClause : andClause) +"  HT.DESCRIPTION = '" + marketType + "'";
             isWhereClaues = false;
        }

        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
            query = query +  (isWhereClaues ? whereClause : andClause) +"  CM1.COMPANY_NO like '" + String.valueOf(parameters.get("filter~customerNo")) + "'";
             isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractHolder") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractHolder")))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~contractHolder")) + "'";
             isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            query = query +  (isWhereClaues ? whereClause : andClause) +"  CON_M.CONTRACT_NO like '" + String.valueOf(parameters.get("filter~contractNo")) + "'";
             isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            query = query +  (isWhereClaues ? whereClause : andClause) +"  CON_M.CONTRACT_NAME like '" + String.valueOf(parameters.get("filter~contractName")) + "'";
             isWhereClaues = false;
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            query = query +  (isWhereClaues ? whereClause : andClause) +"  CM1.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~customerName")) + "'";
             isWhereClaues = false;
        }

        if (parameters.containsKey("filter~marketType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~marketType"))) && !Constant.SELECT_ONE.equals(String.valueOf(parameters.get("filter~marketType")))) {
            query = query +  (isWhereClaues ? whereClause : andClause) +"  HT.DESCRIPTION like '" + String.valueOf(parameters.get("filter~marketType")) + "'";
             isWhereClaues = false;
        }
        if (parameters.containsKey("filter~check") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~check")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CS.AVAILABLE_CHECKBOX like '" + String.valueOf(parameters.get("filter~check")) + "'";
        }
        query = query + " ) TMP_COUNT";

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

        if (list != null && list.size() > 0) {
            Object obj = list.get(0);
            String countValue = String.valueOf(obj);

            count = Integer.valueOf(countValue);

        }
        return count;
    }

    public String companyValues(Map<String, Object> parameters, int start, int offset, SessionDTO session) {

        String query = StringUtils.EMPTY;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_AVAILABLE_SEARCH_QUERY");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        
          String whereClause=" WHERE ";
        String andClause = " AND ";
        boolean isWhereClaues = true;
        
      
        String customer = String.valueOf(parameters.get(Constant.CUSTOMER_NO));

        if (!customer.equals(Constant.NULL)) {
            query = query +(isWhereClaues ? whereClause : andClause)+ "  CM1.COMPANY_NO like '" + customer + "'";
            isWhereClaues = false;
        }

        String contractHolder = String.valueOf(parameters.get("contractHolder"));
        if (!contractHolder.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause)+"  CM.COMPANY_NAME like '" + contractHolder + "'";
            isWhereClaues = false;
        }

        String contractNo = String.valueOf(parameters.get("contractNo"));
        if (!contractNo.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CON_M.CONTRACT_NO like '" + contractNo + "'";
            isWhereClaues = false;
        }

        String contractName = String.valueOf(parameters.get("contractName"));
        if (!contractName.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CON_M.CONTRACT_NAME like '" + contractName + "'";
            isWhereClaues = false;
        }
        String customerName = String.valueOf(parameters.get(Constant.CUSTOMER_NAME));
        if (!customerName.equals(Constant.NULL)) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CM1.COMPANY_NAME like '" + customerName + "'";
            isWhereClaues = false;
        }

        String marketType = String.valueOf(parameters.get("marketType"));
        if (!marketType.equals(Constant.NULL) && !marketType.equals(Constant.SELECT_ONE)) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  HT.DESCRIPTION = '" + marketType + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CM1.COMPANY_NO like '" + String.valueOf(parameters.get("filter~customerNo")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractHolder") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractHolder")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~contractHolder")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CON_M.CONTRACT_NO like '" + String.valueOf(parameters.get("filter~contractNo")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CON_M.CONTRACT_NAME like '" + String.valueOf(parameters.get("filter~contractName")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CM1.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~customerName")) + "'";
            isWhereClaues = false;
        }
        if (parameters.containsKey("filter~check") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~check")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CS.AVAILABLE_CHECKBOX like '" + String.valueOf(parameters.get("filter~check")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~marketType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~marketType"))) && !Constant.SELECT_ONE.equals(String.valueOf(parameters.get("filter~marketType")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  HT.DESCRIPTION like '" + String.valueOf(parameters.get("filter~marketType")) + "'";
            isWhereClaues = false;
        }
      

            query += "ORDER BY CON_M.CONTRACT_NO OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";

        return query;
    }



    public List itemValues(Map<String, Object> parameters, int start, int offset, SessionDTO session, boolean isAddAll) {
        String query = StringUtils.EMPTY;
        String addAllCondtion = StringUtils.EMPTY;
        if (isAddAll) {
            query = "INSERT INTO DBO.AH_TEMP_DETAILS(BUSINESS_UNIT_NO,BUSINESS_UNIT_NAME,THERAPUTIC_CLASS,"
                    + "BRAND_NAME,ITEM_NO,ITEM_NAME,ITEM_IDENTIFIER_TYPE,ITEM_IDENTIFIER,"
                    + "ITEM_MASTER_SID,COMPONENT_MASTER_SID,COMPANY_SID,"
                    + "CHECK_RECORD,SCREEN_NAME,USER_ID,SESSION_ID,CREATED_BY,CREATED_DATE) (";

            addAllCondtion = ", '1', 'Item_Selection', '" + session.getUserId() + "', '" + session.getSessionId() + "', '" + session.getUserId() + "', '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "' ";
        } else {
            addAllCondtion = ",ccpd.CCP_DETAILS_SID \n";
        }

        query = query + "SELECT distinct \n"
                + "        CM.COMPANY_NO AS BUSINESS_UNIT_NO\n"
                + "       , CM.COMPANY_NAME AS BUSINESS_UNIT_NAME\n"
                + "       , HT1.DESCRIPTION\n"
                + "       ,BM.BRAND_NAME\n"
                + "       , IM.ITEM_NO\n"
                + "       , IM.ITEM_NAME\n"
                + "       , II.ITEM_IDENTIFIER_VALUE AS ITEM_IDENTIFIER_TYPE\n"
                + "       ,IQ.ITEM_QUALIFIER_NAME AS ITEM_IDENTIFIER\n"
                + "       ,IM.ITEM_MASTER_SID\n"
                + "      ,con_m.CONTRACT_MASTER_SID\n"
                + ",cm.COMPANY_MASTER_SID\n"
                + addAllCondtion
                + "FROM dbo.CONTRACT_MASTER CON_M\n"
                + "left JOIN dbo.COMPANY_MASTER CM\n"
                + "       ON CON_M.CONT_HOLD_COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
                + "INNER JOIN dbo.HELPER_TABLE HT\n"
                + "       ON CON_M.CONTRACT_TYPE = HT.HELPER_TABLE_SID\n"
                + "INNER JOIN dbo.CFP_CONTRACT CFP_CON\n"
                + "       ON CON_M.CONTRACT_MASTER_SID = CFP_CON.CONTRACT_MASTER_SID\n"
                + "INNER JOIN dbo.CFP_CONTRACT_DETAILS CFP_CD\n"
                + "       ON CFP_CON.CFP_CONTRACT_SID = CFP_CD.CFP_CONTRACT_SID\n"
                + "       JOIN dbo.COMPANY_MASTER CM1 \n"
                + "       on CM1.company_master_sid=CFP_CD.company_master_sid\n"
                + "INNER JOIN DBO.CCP_DETAILS CCPD\n"
                + "       ON CCPD.CONTRACT_MASTER_SID = CON_M.CONTRACT_MASTER_SID\n"
                + "              AND CCPD.COMPANY_MASTER_SID = CM1.COMPANY_MASTER_SID\n"
                + "INNER JOIN ITEM_MASTER IM\n"
                + "       ON CCPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                + "INNER JOIN HELPER_TABLE HT1\n"
                + "       ON HT1.HELPER_TABLE_SID = IM.THERAPEUTIC_CLASS\n"
                + "       INNER JOIN BRAND_MASTER BM\n"
                + "       ON IM.BRAND_MASTER_SID = BM.BRAND_MASTER_SID\n"
                + "INNER JOIN ITEM_IDENTIFIER II\n"
                + "       ON II.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                + "              INNER JOIN ITEM_QUALIFIER IQ\n"
                + "       ON II.ITEM_QUALIFIER_SID=IQ.ITEM_QUALIFIER_SID ";

        query = query + "AND CON_M.CONTRACT_MASTER_SID in (" + session.getContractIds() + ")\n"
                + "AND CM1.COMPANY_MASTER_SID IN(" + session.getCompanyIds() + ")";
        String itemNo = String.valueOf(parameters.get(Constant.ITEM_NO));

        if (!itemNo.equals(StringUtils.EMPTY) && !itemNo.equals(Constant.NULL)) {
            query = query + " AND IM.ITEM_NO like '" + itemNo + "'";
        }
        String itemName = String.valueOf(parameters.get("itemName"));

        if (itemName != null && !itemName.equals(StringUtils.EMPTY) && !itemName.equals(Constant.NULL)) {
            query = query + " AND IM.ITEM_NAME like '" + itemName + "'";
        }

        String businessUnitName = String.valueOf(parameters.get("businessUnitName"));

        if (!businessUnitName.equals(StringUtils.EMPTY) && !businessUnitName.equals(Constant.NULL)) {
            query = query + " AND CM.COMPANY_NAME like '" + businessUnitName + "'";
        }

        String businessUnitNo = String.valueOf(parameters.get("businessUnitNo"));

        if (!businessUnitNo.equals(StringUtils.EMPTY) && !businessUnitNo.equals(Constant.NULL)) {
            query = query + " AND CM.COMPANY_NO like '" + businessUnitNo + "'";
        }
        String theraputicClass = String.valueOf(parameters.get("theraputicClass"));

        if (!theraputicClass.equals(StringUtils.EMPTY) && !theraputicClass.equals(Constant.NULL)) {
            query = query + " AND HT1.DESCRIPTION like '" + theraputicClass + "'";
        }

        String brand = String.valueOf(parameters.get(Constant.BRAND));

        if (!brand.equals(StringUtils.EMPTY) && !brand.equals(Constant.NULL)) {
            query = query + " AND BM.BRAND_NAME like '" + brand + "'";
        }

        String itemIdentifierType = String.valueOf(parameters.get("itemIdentifierType"));

        if (!itemIdentifierType.equals(StringUtils.EMPTY) && !itemIdentifierType.equals(Constant.NULL)) {
            query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + itemIdentifierType + "'";
        }
        String itemIdentifier = String.valueOf(parameters.get("itemIdentifier"));

        if (!itemIdentifier.equals(StringUtils.EMPTY) && !itemIdentifier.equals(Constant.NULL)) {
            query = query + " AND IQ.ITEM_QUALIFIER_NAME like '" + itemIdentifier + "'";
        }

        /* Used for Filter */
        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            query = query + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get("filter~itemNo")) + "'";
        }

        if (parameters.containsKey("filter~businessUnitName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitName")))) {
            query = query + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~businessUnitName")) + "'";
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            query = query + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get("filter~itemName")) + "'";
        }

        if (parameters.containsKey("filter~businessUnitNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitNo")))) {
            query = query + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get("filter~businessUnitNo")) + "'";
        }
        if (parameters.containsKey("filter~brand") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~brand")))) {
            query = query + " AND BM.BRAND_NAME like '" + String.valueOf(parameters.get("filter~brand")) + "'";
        }

        if (parameters.containsKey("filter~itemIdentifierType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifierType")))) {
            query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + String.valueOf(parameters.get("filter~itemIdentifierType")) + "'";
        }

        if (parameters.containsKey("filter~theraputicClass") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~theraputicClass")))) {
            query = query + " AND HT1.DESCRIPTION like '" + String.valueOf(parameters.get("filter~theraputicClass")) + "'";
        }
        if (parameters.containsKey("filter~itemIdentifier") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifier")))) {
            query = query + " AND IQ.ITEM_QUALIFIER_NAME like '" + String.valueOf(parameters.get("filter~itemIdentifier")) + "'";
        }
        if (!isAddAll) {
            query += " ORDER BY ITEM_NAME OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";
        } else {
            query += ")";
        }
        
        List list = null;
        if (isAddAll) {
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } else {
            list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            return list;
        }
        return list;
    }

    public static String getQueryFromApp(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        return queryString.toString();
    }
    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }
        /**
     * get Count method
     *
     * @param list
     * @return
     */
    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }
        /**
     * get Count method
     *
     * @param session
     * @param parameters
     * @param list
     * @return
     */
    public  boolean insertOrUpdate_customerSelection(SessionDTO session, Map<String, Object> parameters,boolean values) {
         String  query = SQlUtil.getQuery("CUSTOMER_SELECTION_ADD_ALL_QUERY");
            query = query.replace("[$AVAILABLE_CHECKBOX]", values ? "1" : "0");
            query = query.replace("[$SELECTED_RECORDS]", "0");

            String sourceTableQuery = SQlUtil.getQuery("CUSTOMER_SELECTION_AVAILABLE_SEARCH_ADD_ALL_QUERY");
            
            String customer = String.valueOf(parameters.get(Constant.CUSTOMER_NO));
        
        if (!customer.equals(Constant.NULL)) {
            sourceTableQuery = sourceTableQuery + " WHERE CM1.COMPANY_NO like '" + customer + "'";
        }

        String contractHolder = String.valueOf(parameters.get("contractHolder"));
        if (!contractHolder.equals(Constant.NULL)) {
            sourceTableQuery = sourceTableQuery + " AND CM.COMPANY_NAME like '" + contractHolder + "'";
        }

        String contractNo = String.valueOf(parameters.get("contractNo"));
        if (!contractNo.equals(Constant.NULL)) {
            sourceTableQuery = sourceTableQuery + " AND CON_M.CONTRACT_NO like '" + contractNo + "'";
        }

        String contractName = String.valueOf(parameters.get("contractName"));
        if (!contractName.equals(Constant.NULL)) {
            sourceTableQuery = sourceTableQuery + " AND CON_M.CONTRACT_NAME like '" + contractName + "'";
        }
        String customerName = String.valueOf(parameters.get(Constant.CUSTOMER_NAME));
        if (!customerName.equals(Constant.NULL)) {
            sourceTableQuery = sourceTableQuery + " AND CM1.COMPANY_NAME like '" + customerName + "'";
        }

        String marketType = String.valueOf(parameters.get("marketType"));
        if (!marketType.equals(Constant.NULL) && !marketType.equals(Constant.SELECT_ONE)) {
            sourceTableQuery = sourceTableQuery + " AND HT.DESCRIPTION = '" + marketType + "'";
        }

        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
            sourceTableQuery = sourceTableQuery + " AND CM1.COMPANY_NO like '" + String.valueOf(parameters.get("filter~customerNo")) + "'";
        }

        if (parameters.containsKey("filter~contractHolder") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractHolder")))) {
            sourceTableQuery = sourceTableQuery + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~contractHolder")) + "'";
        }

        if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            sourceTableQuery = sourceTableQuery + " AND CON_M.CONTRACT_NO like '" + String.valueOf(parameters.get("filter~contractNo")) + "'";
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            sourceTableQuery = sourceTableQuery + " AND CON_M.CONTRACT_NAME like '" + String.valueOf(parameters.get("filter~contractName")) + "'";
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            sourceTableQuery = sourceTableQuery + " AND CM1.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~customerName")) + "'";
        }

        if (parameters.containsKey("filter~marketType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~marketType"))) && !Constant.SELECT_ONE.equals(String.valueOf(parameters.get("filter~marketType")))) {
            sourceTableQuery = sourceTableQuery + " AND HT.DESCRIPTION like '" + String.valueOf(parameters.get("filter~marketType")) + "'";
        }
         query = query.replace("[$SOURCE_TABLE]", sourceTableQuery);
          
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            return true;
    }

    public int itemCount(Map<String, Object> parameters, SessionDTO session) {

        String query = StringUtils.EMPTY;
        String itemIdentifierType = String.valueOf(parameters.get("itemIdentifierType"));
        String itemIdentifier = String.valueOf(parameters.get("itemIdentifier"));
        if (!itemIdentifierType.equals(StringUtils.EMPTY) && !itemIdentifierType.equals(Constant.NULL)
                && !itemIdentifier.equals(StringUtils.EMPTY) && !itemIdentifier.equals(Constant.NULL)) {
            query += SQlUtil.getQuery("items-count-with-identifier");
        } else {
            query += SQlUtil.getQuery("items-count");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = customizeItemSearchQuery(query, parameters);
        query = query + ") TMP_COUNT;";
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return list != null && list.size() > 0 ? (Integer) list.get(0) : 0;
    }

    public List itemsSearch(Map<String, Object> parameters, int start, int offset, SessionDTO session) {

        String query = StringUtils.EMPTY;
        String itemIdentifierType = String.valueOf(parameters.get("itemIdentifierType"));
        String itemIdentifier = String.valueOf(parameters.get("itemIdentifier"));

        if (!itemIdentifierType.equals(StringUtils.EMPTY) && !itemIdentifierType.equals(Constant.NULL)
                && !itemIdentifier.equals(StringUtils.EMPTY) && !itemIdentifier.equals(Constant.NULL)) {
            query += SQlUtil.getQuery("items-search-with-identifier");
        } else {
            query += SQlUtil.getQuery("items-search");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = customizeItemSearchQuery(query, parameters);
        query += " ORDER BY ITEM_NAME OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return list;
    }

    private String customizeItemSearchQuery(String query, final Map<String, Object> parameters) {

        String itemNo = String.valueOf(parameters.get(Constant.ITEM_NO));

        if (!itemNo.equals(StringUtils.EMPTY) && !itemNo.equals(Constant.NULL)) {
            query = query + " AND IM.ITEM_NO like '" + itemNo + "'";
        }
        String itemName = String.valueOf(parameters.get("itemName"));

        if (itemName != null && !itemName.equals(StringUtils.EMPTY) && !itemName.equals(Constant.NULL)) {
            query = query + " AND IM.ITEM_NAME like '" + itemName + "'";
        }

        String businessUnitName = String.valueOf(parameters.get("businessUnitName"));

        if (!businessUnitName.equals(StringUtils.EMPTY) && !businessUnitName.equals(Constant.NULL)) {
            query = query + " AND CM.COMPANY_NAME like '" + businessUnitName + "'";
        }

        String businessUnitNo = String.valueOf(parameters.get("businessUnitNo"));

        if (!businessUnitNo.equals(StringUtils.EMPTY) && !businessUnitNo.equals(Constant.NULL)) {
            query = query + " AND CM.COMPANY_NO like '" + businessUnitNo + "'";
        }
        String theraputicClass = String.valueOf(parameters.get("theraputicClass"));

        if (!theraputicClass.equals(StringUtils.EMPTY) && !theraputicClass.equals(Constant.NULL)) {
            query = query + " AND HT1.DESCRIPTION like '" + theraputicClass + "'";
        }

        String brand = String.valueOf(parameters.get(Constant.BRAND));

        if (!brand.equals(StringUtils.EMPTY) && !brand.equals(Constant.NULL)) {
            query = query + " AND BM.BRAND_NAME like '" + brand + "'";
        }

        String itemIdentifierType = String.valueOf(parameters.get("itemIdentifierType"));

        if (!itemIdentifierType.equals(StringUtils.EMPTY) && !itemIdentifierType.equals(Constant.NULL)) {
            
             query = query + " AND IQ.ITEM_QUALIFIER_SID = " + itemIdentifierType + " ";
        }
        String itemIdentifier = String.valueOf(parameters.get("itemIdentifier"));

        if (!itemIdentifier.equals(StringUtils.EMPTY) && !itemIdentifier.equals(Constant.NULL)) {
            
           query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + itemIdentifier + "'";
           
        }

        /* Used for Filter */
        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            query = query + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get("filter~itemNo")) + "'";
        }

        if (parameters.containsKey("filter~businessUnitName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitName")))) {
            query = query + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~businessUnitName")) + "'";
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            query = query + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get("filter~itemName")) + "'";
        }

        if (parameters.containsKey("filter~businessUnitNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitNo")))) {
            query = query + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get("filter~businessUnitNo")) + "'";
        }
        if (parameters.containsKey("filter~brand") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~brand")))) {
            query = query + " AND BM.BRAND_NAME like '" + String.valueOf(parameters.get("filter~brand")) + "'";
        }

        if (parameters.containsKey("filter~itemIdentifierType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifierType")))) {            
            query = query + " AND IQ.ITEM_QUALIFIER_NAME like '" + String.valueOf(parameters.get("filter~itemIdentifierType")) + "'";
        }

        if (parameters.containsKey("filter~theraputicClass") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~theraputicClass")))) {
            query = query + " AND HT1.DESCRIPTION like '" + String.valueOf(parameters.get("filter~theraputicClass")) + "'";
        }
        if (parameters.containsKey("filter~itemIdentifier") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifier")))) {
            query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + String.valueOf(parameters.get("filter~itemIdentifier")) + "'";
        }
        return query;
    }

    /**
     *
     * @param parameters
     * @param start
     * @param offset
     * @param session
     * @param isAddAllOrCheckAll
     */
    public void mergeForAddOrCheckAll(Map<String, Object> parameters, int start, int offset, SessionDTO session, final boolean isAddAllOrCheckAll,final String checkValue) {

        String tempTableQuery = StringUtils.EMPTY;
        String itemIdentifierType = String.valueOf(parameters.get("itemIdentifierType"));
        String itemIdentifier = String.valueOf(parameters.get("itemIdentifier"));
        if (!itemIdentifierType.equals(StringUtils.EMPTY) && !itemIdentifierType.equals(Constant.NULL)
                && !itemIdentifier.equals(StringUtils.EMPTY) && !itemIdentifier.equals(Constant.NULL)) {
            tempTableQuery += SQlUtil.getQuery("temp-insert-merge-with-identifier");
        } else {
            tempTableQuery += SQlUtil.getQuery("temp-insert-merge-without-identifier");
        }

        tempTableQuery = customizeItemSearchQuery(tempTableQuery, parameters);
        String mergeQuery = isAddAllOrCheckAll ? SQlUtil.getQuery("merge-add-all") : SQlUtil.getQuery("merge-check-all");
        String finalQuery = tempTableQuery + mergeQuery;
        finalQuery = finalQuery.replace("[$AVAILABLE_CHECKBOX]", isAddAllOrCheckAll ? "0" : checkValue);
        finalQuery = finalQuery.replace("[$SELECTED_RECORDS]", "1");

        
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(finalQuery, session.getCurrentTableNames()));

    }

    private void updateBaseLinePeriods(String baselinePeriods, ProjectionSelectionDTO projectionSelection, String levelType, String discountName, int projectionId,String userId, String sessionId,Boolean isProgram) {
       LOGGER.debug(" Baseline Periods " + baselinePeriods);
       String masterTableUpdateQuery;
                masterTableUpdateQuery = SQlUtil.getQuery("BASELINE_UPDATE_QUERY");
                masterTableUpdateQuery = masterTableUpdateQuery.replace("@CALCULATION_PERIODS", baselinePeriods);
                masterTableUpdateQuery = masterTableUpdateQuery.replace("@FORECAST_START_PERIOD_SID", projectionSelection.getFromDateDdlb());
                masterTableUpdateQuery = masterTableUpdateQuery.replace("@FORECAST_END_PERIOD_SID", projectionSelection.getToDateDdlb());
                masterTableUpdateQuery = masterTableUpdateQuery.replace("@CALCULATION_BASED", projectionSelection.getCalcBased());
                masterTableUpdateQuery = masterTableUpdateQuery.replace("@METHODOLOGY", projectionSelection.getMethodology());
                masterTableUpdateQuery = masterTableUpdateQuery.replace("$COLUMN_NAME", isProgram ? "RS.RS_CONTRACT_SID":"DPM.PRICE_GROUP_TYPE");
                masterTableUpdateQuery = masterTableUpdateQuery.replace("@PRICE_GROUP_TYPE", discountName.contains(TILT) ? discountName.split(TILT)[1] : discountName );
               commonDao.executeBulkUpdateQuery(QueryUtil.replaceTableNames(masterTableUpdateQuery, projectionSelection.getSessionDTO().getCurrentTableNames()), null, null);
    }
    public static List getAppData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside getPPAData");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        try {
          
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                sql.append(" ");
                sql.append(SQlUtil.getQuery(quaryName2));
            }

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of getPPAData");
        return list;
    }

    public static Boolean updateAppData(List input, String queryName) {
        LOGGER.debug("Inside updateAppData");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            int count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(sql.toString());
            return count > 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of updateAppData");
        return Boolean.FALSE;
    }
     public static Boolean updateDataFromMap(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        int count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
    
    /**
     * To create temp tables dynamically. It will return the tables created with
     * the user id and session id
     *
     * @param session
     */
    public static void createTempTables(SessionDTO session) {
        List<Object> createdTablesList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.buildDynamicTempTableCreationQuery(session.getScreenName(), session.getProjectionId(), session.getUserId(), session.getSessionId()));
        for (int i = 0; i < createdTablesList.size(); i++) {
            Object[] ob = (Object[]) createdTablesList.get(i);
            session.addCurrentTableNames(ob[0].toString(), ob[1].toString());
        }
    }
    public static String getAppQuery(Map<String, Object> input, String queryName) {
       String sql=StringUtils.EMPTY;
        try {
             sql = SQlUtil.getQuery(queryName);
//            sql = CustomSQLUtil.get(queryName);
            if(input !=null){
            for (String key : input.keySet()) {
                sql = sql.replace(key, String.valueOf(input.get(key)));
            }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql;
    }
}