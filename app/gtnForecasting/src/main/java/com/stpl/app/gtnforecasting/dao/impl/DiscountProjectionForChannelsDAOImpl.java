/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.stpl.app.gtnforecasting.dao.DiscountProjectionForChannelsDAO;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.queryUtils.DPQueryUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.StChDiscountProjMaster;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author vigneshkanna
 */
public class DiscountProjectionForChannelsDAOImpl extends BasePersistenceImpl<StChDiscountProjMaster> implements DiscountProjectionForChannelsDAO {

    private static final Logger LOGGER = Logger.getLogger(DiscountProjectionForChannelsDAOImpl.class);
    protected CommonUtils commonUtils = new CommonUtils();
    protected DPQueryUtils queryUtils = new DPQueryUtils();
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(Constant.DATE_FORMAT);

    @Override
    public List<DiscountProjectionDTO> getDiscountProjection(
            SessionDTO session, String parentName, String year, int historyNumber, int levelNo, String hierarchyIndicator, int start, int offset, boolean isCount, boolean isCustom, List<String> customViewDetails, boolean isRefresh, String refreshHierarchyNumbers, ProjectionSelectionDTO projectionSelection) {
        LOGGER.debug("Entering getDiscountProjection");

        String hierarchyNo = parentName;
        int projectionId = session.getProjectionId();
        String selecthistoryQuery = StringUtils.EMPTY;
        String selectfutureQuery = StringUtils.EMPTY;
        LOGGER.debug(" Entering getDiscountProjection with discount Type" + projectionSelection.getDiscountName());
        try {

            String freq = StringUtils.EMPTY;
            String customQuery;

            String hierarchy = StringUtils.EMPTY;
            // C indicates customer, P indicates product
            if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_CUST_HIERARCHY;
            } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_PROD_HIERARCHY;
            }

            String levelSelectionStatement = StringUtils.EMPTY;
            if (levelNo != 0) {
                levelSelectionStatement = " HLD.LEVEL_NO = " + levelNo;
            }

            if (!isCount) {


                if (projectionSelection.getFrequency().equals(QUARTERLY.getConstant())) {
                    freq = "I.QUARTER, ";

                }
                if (projectionSelection.getFrequency().equals(ANNUALLY.getConstant())) {
                    freq = "'' as ANNUAL, ";

                }
                if (projectionSelection.getFrequency().equals(SEMI_ANNUALLY.getConstant())) {
                    freq = "I.SEMI_ANNUAL, ";

                }
                if (projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                    freq = "I.\"MONTH\", ";

                }


                String hierarchyNumbers = StringUtils.EMPTY;
                if (!isCustom) {
                    if (isRefresh) {
                        hierarchyNumbers = refreshHierarchyNumbers;
                        hierarchyNo = StringUtils.EMPTY;
                    } else {
                        List<String> hierarchyNoList = getHierarchyList(projectionId, hierarchy, hierarchyNo, levelNo, start, offset);
                        hierarchyNumbers = CommonUtils.CollectionToString(hierarchyNoList, true);
                    }

                    if (hierarchyNumbers.isEmpty()) {
                        return new ArrayList<>();
                    }
                }

                String ccpDetails;
                if (customViewDetails.isEmpty()) {

                    String connector = StringUtils.EMPTY;

                    if (!levelSelectionStatement.isEmpty()) {
                        connector = " AND ";
                    }
                    ccpDetails = queryUtils.getCCPDetailsQuery(hierarchy, projectionId, connector, hierarchyNo, hierarchyNumbers, levelSelectionStatement, isCount);
                } else {
                    ccpDetails = queryUtils.getCustomCCPDetailsQuery(hierarchyIndicator, projectionId, customViewDetails, false, StringUtils.EMPTY);
                }

                if (Constant.PROJECTED.toString().equalsIgnoreCase(projectionSelection.getProjectionType())) {
                    if (Constant.PERCENT.equals(projectionSelection.getDiscountType())) {
                        selecthistoryQuery = "COALESCE(Sum(CP.ACTUAL_SALES) / NULLIF(Sum(CHAS.CONTRACT_SALES), 0), 0) * 100 AS ACTUAL_RATE,\n NULL AS DISCOUNT_RATE,";
                        selectfutureQuery = " NULL AS ACTUAL_RATE,\n  COALESCE(Sum(CP.DISCOUNT_AMOUNT) / NULLIF(Sum(CHSP.CONTRACT_SALES), 0), 0) * 100 AS DISCOUNT_RATE,";

                    } else {
                        selecthistoryQuery = "\n sum(CP.ACTUAL_SALES)as ACTUAL_SALES, NULL as DISCOUNT_AMOUNT,";
                        selectfutureQuery = "\n NULL as ACTUAL_SALES, sum(CP.DISCOUNT_AMOUNT) as DISCOUNT_AMOUNT,";
                    }
                } else {
                    if (Constant.PERCENT.equals(projectionSelection.getDiscountType())) {
                        selecthistoryQuery = "COALESCE(Sum(CP.ACTUAL_SALES) / NULLIF(Sum(CHAS.CONTRACT_SALES), 0), 0) * 100 AS ACTUAL_RATE,\n NULL AS DISCOUNT_RATE,";
                        selectfutureQuery = " NULL AS ACTUAL_RATE,\n  COALESCE(Sum(CP.DISCOUNT_AMOUNT) / NULLIF(Sum(CHSP.CONTRACT_SALES), 0), 0) * 100 AS DISCOUNT_RATE,";

                    } else {
                        selecthistoryQuery = "\n sum(CP.ACTUAL_SALES)as ACTUAL_SALES, NULL as DISCOUNT_AMOUNT,";
                        selectfutureQuery = "\n NULL as ACTUAL_SALES, sum(CP.DISCOUNT_AMOUNT) as DISCOUNT_AMOUNT,";

                    }
                }
                Map<String, Object> input = new HashMap<>();
                LOGGER.debug("?SID" + session.getSessionId());
                LOGGER.debug("?PM" + session.getProjectionId());
                LOGGER.debug("?HIE" + hierarchyNo);
                LOGGER.debug("?HIST" + selecthistoryQuery);
                LOGGER.debug("?FUT" + selectfutureQuery);
                LOGGER.debug("?EP" + DBDate.format(projectionSelection.getToDates()));
                LOGGER.debug("?SP" + DBDate.format(projectionSelection.getStartDate()));
                LOGGER.debug("?FRE" + freq);

                input.put("?UID", session.getUserId());
                input.put("?SID", session.getSessionId());
                input.put("?PM", session.getProjectionId());
                input.put("?CCPQUERY", ccpDetails);
                input.put("?HIST", selecthistoryQuery);
                input.put("?FUT", selectfutureQuery);
                input.put("?EP", DBDate.format(projectionSelection.getToDates()));
                input.put("?SP", DBDate.format(projectionSelection.getStartDate()));
                input.put("?FRE", freq);

                String custSql = SQlUtil.getQuery(getClass(),"ch.generateQuery");

                for (String key : input.keySet()) {
                    LOGGER.debug("Key : " + key);
                    custSql = custSql.replace(key, String.valueOf(input.get(key)));
                }
                customQuery = custSql;

            } else {

                String ccpDetails = queryUtils.getCCPDetailsQuery(hierarchy, projectionId, StringUtils.EMPTY, hierarchyNo, StringUtils.EMPTY, levelSelectionStatement, isCount);

                customQuery = " Select count(DISTINCT CCP.HIERARCHY_NO ) \n "
                        + "        FROM " + ccpDetails;
            }
            List<DiscountProjectionDTO> list = (List<DiscountProjectionDTO>) HelperTableLocalServiceUtil.executeSelectQuery(customQuery);
            LOGGER.debug(" Fetching Discount Data" + list.size());
            return list;
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            LOGGER.debug(" exiting getDiscountProjection");
        }
    }

    private List getHierarchyList(int projectionId, String hierarchy, String hierarchyNo, int levelNo, int startIndex, int endIndex) {
        LOGGER.debug(" Inside getHierarchyList");
        try {
            String levelSelectionStatement = StringUtils.EMPTY;
            if (levelNo != 0) {
                levelSelectionStatement = " HLD.LEVEL_NO = " + levelNo;
            }
            String ccpDetails = queryUtils.getCCPDetailsQuery(hierarchy, projectionId, StringUtils.EMPTY, hierarchyNo, StringUtils.EMPTY, levelSelectionStatement, true);

            String customSql = "SELECT DISTINCT CCP.HIERARCHY_NO  \n"
                    + " FROM " + ccpDetails + "\n"
                    + " order by CCP.HIERARCHY_NO OFFSET " + startIndex + Constant.ROWS_FETCH_NEXT_SPACE + endIndex + " ROWS ONLY ";

            List<String> list = (List<String>) HelperTableLocalServiceUtil.executeSelectQuery(customSql);
            return list;
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            LOGGER.debug(" exiting getHierarchyList");
        }
    }

    @Override
    public int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo, String hierarchyIndicator,
            boolean isCustomView, List<String> customViewDetails, String relationshipBuilderSid) {
        LOGGER.debug("Entering updateCheckRecord");
        try {
            String hierarchy = StringUtils.EMPTY;
            String ccpDetails;
            String customSql;
            int projectionId = session.getProjectionId();
            String userId = session.getUserId();
            String sessionId = session.getSessionId();
            int check = checkValue ? 1 : 0;

            // C indicates customer, P indicates product
            if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_CUST_HIERARCHY;
            } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_PROD_HIERARCHY;
            }

            if (isCustomView && customViewDetails != null && !customViewDetails.isEmpty()) {
                String customId = customViewDetails.get(0);
                String customerLevelNo = customViewDetails.get(1);
                String customerHierarchyNo = customViewDetails.get(NumericConstants.TWO);
                String productLevelNo = customViewDetails.get(NumericConstants.THREE);
                String productHierarchyNo = customViewDetails.get(NumericConstants.FOUR);

                ccpDetails = " (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM \n"
                        + "    (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                        + "    FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                        + "    JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                        + "    JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAPC\n"
                        + "    JOIN (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                        + "        FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                        + "        JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                        + "        JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAPP \n"
                        + "    ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                        + "    JOIN \n"
                        + "    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                        + "        JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                        + "        JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                        + "        JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                        + "        JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                        + "        WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                        + "    JOIN \n"
                        + "    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                        + "        JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                        + "        JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                        + "        JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                        + "        JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                        + "        WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "%') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                        + "    WHERE HLD" + hierarchyIndicator + ".HIERARCHY_NO = '" + hierarchyNo + "'"
                        + ") CCP";

                customSql = Constant.UPDATE_M_SET_CHECK_RECORD + check + " FROM ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B, \n" + ccpDetails
                        + " WHERE M.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID  "
                        + " and M.USER_ID = " + userId + "\n"
                        + Constant.AND_MSESSION_ID + sessionId + "\n"
                        + " AND B.PROJECTION_MASTER_SID='" + projectionId + "'  \n"
                        + " AND CCP.CCP_DETAILS_SID = B.CCP_DETAILS_SID ";

            } else {

                ccpDetails = "(SELECT CCPMAP.CCP_DETAILS_SID,\n"
                        + "                         HLD.HIERARCHY_NO,\n"
                        + "                         HLD.RELATIONSHIP_LEVEL_SID\n"
                        + "                  FROM   (SELECT RLD.HIERARCHY_NO,\n"
                        + "                                 CCP.CCP_DETAILS_SID\n"
                        + "                          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                        + "                          JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID = " + relationshipBuilderSid + "\n"
                        + "                          JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " + projectionId + "\n"
                        + "                          JOIN    " + hierarchy + "  PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID \n"
                        + "                                 AND PCH.PROJECTION_MASTER_SID=" + projectionId + "\n"
                        + "                          ) CCPMAP JOIN \n"
                        + "                         (SELECT RLD1.HIERARCHY_NO,\n"
                        + "                                 RLD1.RELATIONSHIP_LEVEL_SID,\n"
                        + "                                 RLD1.LEVEL_NO\n"
                        + "                          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                        + "                          JOIN   " + hierarchy + "   PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID AND PCH.PROJECTION_MASTER_SID =  " + projectionId + " \n"
                        + "                         AND  RLD1.HIERARCHY_NO LIKE '%') HLD ON CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%' \n"
                        + "  WHERE HLD.HIERARCHY_NO in('" + hierarchyNo + "'))CCP";

                customSql = Constant.UPDATE_M_SET_CHECK_RECORD + check + "\n"
                        + " From ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS E \n"
                        + " Where M.USER_ID = " + userId + " \n"
                        + Constant.AND_MSESSION_ID + sessionId + "\n"
                        + " AND E.PROJECTION_MASTER_SID ='" + projectionId + "' \n"
                        + " AND E.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID \n"
                        + " AND M.PROJECTION_DETAILS_SID  \n"
                        + " in (SELECT M.PROJECTION_DETAILS_SID FROM ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B, \n" + ccpDetails
                        + " WHERE M.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID  "
                        + " and M.USER_ID = " + userId + "\n"
                        + Constant.AND_MSESSION_ID + sessionId + "\n"
                        + " AND B.PROJECTION_MASTER_SID='" + projectionId + "'  \n"
                        + " AND CCP.CCP_DETAILS_SID = B.CCP_DETAILS_SID \n"
                        + " GROUP  BY  M.PROJECTION_DETAILS_SID) \n";

            }

            return HelperTableLocalServiceUtil.executeUpdateQueryCount(customSql);
        } catch (Exception e) {
           LOGGER.error(e);
            return 0;
        } finally {
            LOGGER.debug(" exiting updateCheckRecord");
        }
    }

    @Override
    public int getLevelIndex(int projectionId, String hierarchyIndicator, String hierarchyNo, String selectedHiearchyNo) {
        List list = new ArrayList();
        LOGGER.debug(" inside getLevelIndex");
        try {
            String customSql = "select TEMP.TEMP_INDEX from(SELECT RLD.HIERARCHY_NO, RLD.RELATIONSHIP_LEVEL_VALUES, \n"
                    + " ROW_NUMBER() OVER (ORDER BY RLD.RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX"
                    + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                    + " JOIN " + CommonUtils.getViewTableName(hierarchyIndicator) + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID\n"
                    + " AND PCH.PROJECTION_MASTER_SID = " + projectionId + " \n"
                    + " WHERE RLD.HIERARCHY_NO LIKE '" + hierarchyNo + "%' and RLD.LEVEL_NO = " + (selectedHiearchyNo.length() - selectedHiearchyNo.replace(".", StringUtils.EMPTY).length() + 1) + ") "
                    + " TEMP where TEMP.HIERARCHY_NO='" + selectedHiearchyNo + "'";
            list = (List) HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e);
            return 0;
        } finally {
            LOGGER.debug(" exiting getLevelIndex");
        }

        if (list != null && !list.isEmpty()) {
            return Integer.valueOf(String.valueOf(list.get(0)));
        } else {
            return 0;
        }
    }

    @Override
    public void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
            List<String> selectedDiscounts) {
        LOGGER.debug(" inside massUpdate");
        int startFreq = 0;
        int endFreq = 0;
        int startYear = 0;
        int endYear = 0;
        String query;
        if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
            startFreq = startAndEndPeriods.get(0);
            if (startAndEndPeriods.size() > 1) {
                startYear = startAndEndPeriods.get(1);
            }
            if (startAndEndPeriods.size() > NumericConstants.TWO) {
                endFreq = startAndEndPeriods.get(NumericConstants.TWO);
            }
            if (startAndEndPeriods.size() > NumericConstants.THREE) {
                endYear = startAndEndPeriods.get(NumericConstants.THREE);
            }
        }

        int startMonth = 0;
        int endMonth = 0;
        if (frequency.equals(ANNUALLY.getConstant())) {
            startMonth = 1;
            endMonth = NumericConstants.TWELVE;
        }
        if (frequency.equals(QUARTERLY.getConstant())) {
            switch (startFreq) {
                case 1:
                    startMonth = 1;
                    break;
                case NumericConstants.TWO:
                    startMonth = NumericConstants.FOUR;
                    break;
                case NumericConstants.THREE:
                    startMonth = NumericConstants.SEVEN;
                    break;
                case NumericConstants.FOUR:
                    startMonth = NumericConstants.TEN;
                    break;
                default:
                    LOGGER.warn("startFreq is not valid: " + startFreq);
                    break;
            }
            switch (endFreq) {
                case 1:
                    endMonth = NumericConstants.THREE;
                    break;
                case NumericConstants.TWO:
                    endMonth = NumericConstants.SIX;
                    break;
                case NumericConstants.THREE:
                    endMonth = NumericConstants.NINE;
                    break;
                case NumericConstants.FOUR:
                    endMonth = NumericConstants.TWELVE;
                    break;
                default:
                    LOGGER.warn("endFreq is not valid: " + endFreq);
                    break;
            }
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            switch (startFreq) {
                case 1:
                    startMonth = 1;
                    break;
                case NumericConstants.TWO:
                    startMonth = NumericConstants.SEVEN;
                    break;
                default:
                    LOGGER.warn("startFreq is not valid: " + startFreq);
                    break;
            }
            switch (endFreq) {
                case 1:
                    endMonth = NumericConstants.SIX;
                    break;
                case NumericConstants.TWO:
                    endMonth = NumericConstants.TWELVE;
                    break;
                default:
                    LOGGER.warn("endFreq is not valid: " + endFreq);
                    break;
            }
        }
        if (frequency.equals(MONTHLY.getConstant())) {
            startMonth = startFreq;
            endMonth = endFreq;
        }
        Date sDate = commonUtils.getDate(01, startMonth - 1, startYear);

        Date eDate = commonUtils.getLastDate(endMonth, endYear);
        query = queryUtils.massUpdateQuery(sDate, eDate,  fieldValue, session, selectedField);
       HelperTableLocalServiceUtil.executeUpdateQuery(query);
        LOGGER.debug(" Ending massUpdate");
    }

    @Override
    public boolean updateInputsForAdjustment(SessionDTO session, String frequency, String adjustmentType, String adjustmentBasis,
            String adjustmentValue, String allocationMethodology, List<String> baselinePeriodsList, List<String> selectedPeriodsList) {
        int projectionId = session.getProjectionId();
        String userId = session.getUserId();
        String sessionId = session.getSessionId();
        int discountName = session.getDiscountTypeId();
        LOGGER.debug(" entering updateInputsForAdjustment");
        try {

            // To updated DISCOUNT_PROJ_MASTER Table
            String baselinePeriods;
            String selectedPeriods;
            String masterTableUpdateQuery;

            baselinePeriods = CommonUtils.CollectionToString(baselinePeriodsList, false, true);
            selectedPeriods = CommonUtils.CollectionToString(selectedPeriodsList, false, true);

            LOGGER.debug(" Baseline Periods " + baselinePeriods);
            LOGGER.debug(" Selected Periods " + selectedPeriods);
            masterTableUpdateQuery = queryUtils.masterTableUpdateQuery(baselinePeriods, projectionId, discountName, selectedPeriods);

           HelperTableLocalServiceUtil.executeUpdateQuery(masterTableUpdateQuery);
            // For updating DISCOUNT_PROJECTION Table 
            String period = StringUtils.EMPTY;
            if (frequency.equals(QUARTERLY.getConstant())) {
                period = " CAST(PR.QUARTER AS CHAR(1)) + CAST(PR.\"YEAR\" AS char(4))";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                period = " CAST(PR.SEMI_ANNUAL AS CHAR(1)) + CAST(PR.\"YEAR\" AS char(4))";
            }
            if (frequency.equals(MONTHLY.getConstant())) {
                period = " CAST(\"MONTH\"AS CHAR(2)) + CAST(PR.\"YEAR\" AS char(4))";
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                period = "CAST(PR.\"YEAR\" AS char(4))";
            }

            String selectedPeriodsToUpdate = CommonUtils.CollectionToString(selectedPeriodsList, false);
            selectedPeriodsToUpdate = CommonUtils.replaceIntegerForMonth(selectedPeriodsToUpdate);
            selectedPeriodsToUpdate = selectedPeriodsToUpdate.replace(Constant.Q, StringUtils.EMPTY).replace(Constant.S, StringUtils.EMPTY).replace(" ", StringUtils.EMPTY);
            String DiscountProjectionTableUpdateQuery = queryUtils.discountProjectionTableUpdateQuery(adjustmentType, adjustmentBasis, allocationMethodology, adjustmentValue, projectionId, discountName, userId, sessionId, period, selectedPeriodsToUpdate);
           HelperTableLocalServiceUtil.executeUpdateQuery(DiscountProjectionTableUpdateQuery);

        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        } finally {
            LOGGER.debug(" exiting updateInputsForAdjustment");
        }
        return true;
    }

    @Override
    public boolean saveDiscountProjectionListView(SessionDTO session, String frequency, int period, int year, String hierarchy,
            int levelNo, String hierarchyNo, String discountName, String fieldValue, boolean isCustomHierarchy, List<String> customViewDetails, String selectedField) {

        LOGGER.debug(" entering saveDiscountProjectionListView");
        try {

            int periodToSave = period;
            int yearToSave = year;
            int startMonth = 0;
            int endMonth = 0;
            if (frequency.equals(ANNUALLY.getConstant())) {
                startMonth = 1;
                endMonth = NumericConstants.TWELVE;
            }
            if (frequency.equals(QUARTERLY.getConstant())) {
                switch (periodToSave) {
                    case 1:
                        startMonth = 1;
                        endMonth = NumericConstants.THREE;
                        break;
                    case NumericConstants.TWO:
                        startMonth = NumericConstants.FOUR;
                        endMonth = NumericConstants.SIX;
                        break;
                    case NumericConstants.THREE:
                        startMonth = NumericConstants.SEVEN;
                        endMonth = NumericConstants.NINE;
                        break;
                    case NumericConstants.FOUR:
                        startMonth = NumericConstants.TEN;
                        endMonth = NumericConstants.TWELVE;
                        break;
                }
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                switch (periodToSave) {
                    case 1:
                        startMonth = 1;
                        endMonth = NumericConstants.SIX;
                        break;
                    case NumericConstants.TWO:
                        startMonth = NumericConstants.SEVEN;
                        endMonth = NumericConstants.TWELVE;
                        break;
                }
            }
            if (frequency.equals(MONTHLY.getConstant())) {
                startMonth = periodToSave;
                endMonth = periodToSave;
            }
            Date sDate = commonUtils.getDate(01, startMonth - 1, yearToSave);
            Date eDate = commonUtils.getLastDate(endMonth, yearToSave);
            String query = queryUtils.saveDiscountProjectionListView(sDate, eDate, isCustomHierarchy, customViewDetails, hierarchy, session, hierarchyNo, fieldValue, selectedField);
           HelperTableLocalServiceUtil.executeUpdateQuery(query);
            return true;
        } catch (Exception e) {
              LOGGER.error(e);
            return false;
        } finally {
            LOGGER.debug(" exiting saveDiscountProjectionListView");
        }

    }

    public List getLevelvalues(int projectionId, String hierarchyIndicator, int startLevelNo, int endLevelNo, boolean isCustomHierarchy, boolean isLevelFilter) {
        Session session = null;
        LOGGER.debug(" entering getLevelvalues");
        try {
            session = openSession();
            String hierarchy = StringUtils.EMPTY;
            isCustomHierarchy = true;
            // C indicates customer, P indicates product
            if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_CUST_HIERARCHY;
                isCustomHierarchy = false;
            } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_PROD_HIERARCHY;
                isCustomHierarchy = false;
            }

            String customSql = queryUtils.getLevelvalues(isLevelFilter, endLevelNo, isCustomHierarchy, projectionId, hierarchy, startLevelNo);
            List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(customSql);
            return list;
        } catch (ORMException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            LOGGER.debug(" exiting getLevelvalues");
            closeSession(session);
        }

    }

    @Override
    public List loadLevels(int projectionId, String hierarchyIndicator, int startLevelNo, int endLevelNo, int customId, boolean isCustomHierarchy, boolean isLevelFilter) {
        LOGGER.debug(" entering getLevelvalues");
        try {
            String hierarchy = StringUtils.EMPTY;
            isCustomHierarchy = true;
            // C indicates customer, P indicates product
            if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_CUST_HIERARCHY;
                isCustomHierarchy = false;
            } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                hierarchy = Constant.PROJECTION_PROD_HIERARCHY;
                isCustomHierarchy = false;
            }

            String customSql = queryUtils.getLevelvalues(isLevelFilter, endLevelNo, isCustomHierarchy, projectionId, hierarchy, startLevelNo);
            List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(customSql);
            return list;
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            LOGGER.debug(" exiting getLevelvalues");

        }
    }

    /**
     * Query for the alternate contract history
     *
     * @param contractquery
     * @return
     */
    @Override
    public List getAlternateContract(String contractquery) {

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(contractquery);
        LOGGER.debug(" getAlternate values" + list.size());
        return list;
    }

    /**
     * Query for the retrieving company in alternate contract history
     *
     * @param query
     * @return
     */
    @Override
    public List<String> getCompanyForAlternate(String query) {
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug(" getCompany values" + list.size());
        return list;
    }

    /*This method is to update the required values before calculation*/
    @Override
    public void getCalculation(ProjectionSelectionDTO projectionSelectionDTO) {
        LOGGER.debug(" Entering getCalculation");
        LOGGER.debug(" getCompany values" + projectionSelectionDTO.getMethodology());
        LOGGER.debug(" projectionSelectionDTO.getProjectionType()" + projectionSelectionDTO.getProjectionType());
        LOGGER.debug(" projectionSelectionDTO.getDiscountType()" + projectionSelectionDTO.getDiscountType());
        LOGGER.debug(" projectionSelectionDTO.getDiscountTypeId()" + projectionSelectionDTO.getDiscountTypeId());
        String updateQuery = "UPDATE CHDP \n"
                + "SET CHDP.PROJECTED_TYPE = '" + projectionSelectionDTO.getProjectionType() + "',\n"
                + "    CHDP.DISCOUNT_TYPE = '" + projectionSelectionDTO.getDiscountType() + "',\n"
                + "CHDP.METHODOLOGY = '" + projectionSelectionDTO.getMethodology() + "',\n"
                + "CHDP.CALCULATION_PERIODS = '" + projectionSelectionDTO.getCalcPeriods() + "',\n"
                + "CHDP.CHECK_RECORD = 1" + "\n"
                + "FROM ST_CH_DISCOUNT_PROJ_MASTER CHDP\n"
                + "JOIN PROJECTION_DETAILS B ON CHDP.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                + "WHERE B.PROJECTION_MASTER_SID='" + projectionSelectionDTO.getProjectionId() + "' "
                + "AND CHDP.USER_ID='" + projectionSelectionDTO.getUserId()
                + "' AND CHDP.SESSION_ID='" + projectionSelectionDTO.getSessionId() + "'\n";

       HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
        LOGGER.debug(" Ending getCalculation");
    }

    @Override
    public void discountPopulate(final Map<String, Object> input, final String queryName) throws SystemException {
        LOGGER.debug(" Entering discountPopulate");

        try {
            LOGGER.debug("Query Name : " + queryName);
            String customSql = SQlUtil.getQuery(getClass(),queryName);

            for (String key : input.keySet()) {
                LOGGER.debug("Key : " + key);
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
           HelperTableLocalServiceUtil.executeUpdateQuery(customSql.toString());
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            LOGGER.debug(" Ending discountPopulate");
        }
    }

    @Override
    public void checkClearAll(int projectionId, String userId, String sessionId, boolean checkValue) {

        int check = checkValue ? 1 : 0;
        String query = Constant.UPDATE_M_SET_CHECK_RECORD + check
                + " From ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS E \n"
                + " Where M.USER_ID = " + userId + " \n"
                + Constant.AND_MSESSION_ID + sessionId + "\n"
                + " AND E.PROJECTION_MASTER_SID ='" + projectionId + "' \n"
                + " AND E.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID ";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
}
