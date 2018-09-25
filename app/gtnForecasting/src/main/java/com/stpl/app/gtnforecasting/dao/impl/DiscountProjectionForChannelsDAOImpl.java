/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.stpl.app.gtnforecasting.dao.DiscountProjectionForChannelsDAO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.queryUtils.DPQueryUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.StChDiscountProjMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vigneshkanna
 */
public class DiscountProjectionForChannelsDAOImpl extends BasePersistenceImpl<StChDiscountProjMaster> implements DiscountProjectionForChannelsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountProjectionForChannelsDAOImpl.class);
    protected CommonUtils commonUtils = new CommonUtils();
    protected DPQueryUtils queryUtils = new DPQueryUtils();
    public final SimpleDateFormat dBDateImpl = new SimpleDateFormat(Constant.DATE_FORMAT);

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
           LOGGER.error(e.getMessage());
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
            LOGGER.error(e.getMessage());
            return 0;
        } finally {
            LOGGER.debug(" exiting getLevelIndex");
        }

        if (list != null && !list.isEmpty()) {
            return Integer.parseInt(String.valueOf(list.get(0)));
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
                    LOGGER.warn("startFreq is not valid= {} " , startFreq);
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
                    LOGGER.warn("endFreq is not valid= {} " , endFreq);
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
                    LOGGER.warn("startFreq is not valid= {} " , startFreq);
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
                    LOGGER.warn("endFreq is not valid= {} " , endFreq);
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

    /**
     * Query for the alternate contract history
     *
     * @param contractquery
     * @return
     */
    @Override
    public List getAlternateContract(String contractquery) {

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(contractquery);
        LOGGER.debug(" getAlternate values= {}" , list.size());
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
        LOGGER.debug(" getCompany values= {}" , list.size());
        return list;
    }

    /*This method is to update the required values before calculation*/
    @Override
    public void getCalculation(ProjectionSelectionDTO projectionSelectionDTO) {
        LOGGER.debug(" Entering getCalculation");
        LOGGER.debug(" getCompany values= {}" , projectionSelectionDTO.getMethodology());
        LOGGER.debug(" projectionSelectionDTO.getProjectionType()= {}" , projectionSelectionDTO.getProjectionType());
        LOGGER.debug(" projectionSelectionDTO.getDiscountType()= {}" , projectionSelectionDTO.getDiscountType());
        LOGGER.debug(" projectionSelectionDTO.getDiscountTypeId()= {}" , projectionSelectionDTO.getDiscountTypeId());
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
            LOGGER.debug("Query Name= {} " , queryName);
            String customSql = SQlUtil.getQuery(getClass(),queryName);

            for (Map.Entry<String, Object> key : input.entrySet()) {
                LOGGER.debug("Key= {} " , key.getKey());
                customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
            }
           HelperTableLocalServiceUtil.executeUpdateQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
