/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.logic;

import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualDataSelectionDTO;
import static com.stpl.app.gtnforecasting.logic.NonMandatedLogic.LOGGER;
import static com.stpl.app.gtnforecasting.logic.NonMandatedLogic.dataSelection;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.utils.Constants;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class DSLogic {

    public void updateRebateValue(AccrualDataSelectionDTO dtoValue) {
        try {
            StringBuilder updateQuery = new StringBuilder(StringUtils.EMPTY);
            if (Constant.UPDATE_SMALL.equalsIgnoreCase(dtoValue.getActionFlag())) {
                updateQuery.append("Update ACCRUAL_PROJ_SELECTION\n"
                        + "SET  SCREEN_NAME='" + dtoValue.getScreenName() + "',FIELD_NAME='" + dtoValue.getDeductionType() + "',FIELD_VALUES='" + dtoValue.getDeductionValue() + "'\n"
                        + "where Projection_Master_Sid=" + dtoValue.getProjectionId() + ";");

            } else {
                updateQuery.append("Insert ACCRUAL_PROJ_SELECTION (SCREEN_NAME,FIELD_NAME,FIELD_VALUES,PROJECTION_MASTER_SID) \n"
                        + "values('" + dtoValue.getScreenName() + "','" + dtoValue.getDeductionType() + "','" + dtoValue.getDeductionValue() + "'," + dtoValue.getProjectionId() + ")");
            }

            HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery.toString());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public int saveProjection(final DataSelectionDTO dataSelectionDTO, String screenName) throws SystemException, ParseException {
        String fromDateValue = "01-01-2016";
        String toDateValue = "01-01-2018";
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM);

        String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        projectionMaster.setProjectionName(dataSelectionDTO.getProjectionName());
        projectionMaster.setProjectionDescription(dataSelectionDTO.getDescription());
        projectionMaster.setForecastingType(screenName);
        projectionMaster.setCreatedBy(UiUtils.parseStringToInteger(userId));
        projectionMaster.setCreatedDate(new Date());
        projectionMaster.setBrandType(true);
        projectionMaster.setCustomerHierarchySid(dataSelectionDTO.getCustomerHierSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerHierSid()));
        projectionMaster.setProductHierarchySid(dataSelectionDTO.getProdHierSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getProdHierSid()));
        projectionMaster.setCustomerHierarchyLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyLevel()));
        projectionMaster.setProductHierarchyLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyLevel()));
        projectionMaster.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
        projectionMaster.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));
        projectionMaster.setCustomerHierVersionNo(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyVer()));
        projectionMaster.setProductHierVersionNo(Integer.parseInt(dataSelectionDTO.getProductHierarchyVer()));
        projectionMaster.setCompanyGroupSid(dataSelectionDTO.getCustomerGrpSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
        projectionMaster.setItemGroupSid(dataSelectionDTO.getProdGrpSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getProdGrpSid()));
        projectionMaster.setCompanyMasterSid(dataSelectionDTO.getCompanySid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCompanySid()));
        projectionMaster.setFromDate(dateFormat.parse(fromDateValue));   //Obtain from Admin Console
        projectionMaster.setToDate(dateFormat.parse(toDateValue));
        projectionMaster.setSaveFlag(false);
        projectionMaster.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
        projectionMaster.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
        projectionMaster.setDiscountType(dataSelectionDTO.getDiscountSid());
        projectionMaster.setForecastingType(screenName);
        projectionMaster.setBusinessUnit(dataSelectionDTO.getBusinessUnitSystemId());
        projectionMaster = dataSelection.addProjectionMaster(projectionMaster);
        return projectionMaster.getProjectionMasterSid();

    }

    /**
     * Update projection.
     *
     * @param dataSelectionDTO the data selection dto
     * @param projectionId
     * @param markAsSaved
     * @return the string
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public int updateProjection(final DataSelectionDTO dataSelectionDTO, int projectionId, final boolean markAsSaved, String screenName) throws PortalException, SystemException, ParseException {

        String fromDateValue = "01-01-2016";
        String toDateValue = "01-01-2018";
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM);
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        LOGGER.debug("Entering updateProjection ");
        if (!StringUtils.isEmpty(String.valueOf(projectionId)) && !Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
            projectionMaster = dataSelection.getProjectionMaster(projectionId);
        }
        projectionMaster.setProjectionName(dataSelectionDTO.getProjectionName());
        projectionMaster.setProjectionDescription(dataSelectionDTO.getDescription());
        projectionMaster.setForecastingType(screenName);
        projectionMaster.setModifiedBy(Integer.parseInt(userId));
        projectionMaster.setModifiedDate(new Date());
        projectionMaster.setBrandType(true);
        projectionMaster.setCustomerHierarchySid(dataSelectionDTO.getCustomerHierSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerHierSid()));
        projectionMaster.setProductHierarchySid(dataSelectionDTO.getProdHierSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getProdHierSid()));
        projectionMaster.setCustomerHierarchyLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyLevel()));
        projectionMaster.setProductHierarchyLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyLevel()));

        projectionMaster.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
        projectionMaster.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));

        projectionMaster.setCustomerHierVersionNo(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyVer()));
        projectionMaster.setProductHierVersionNo(Integer.parseInt(dataSelectionDTO.getProductHierarchyVer()));
        projectionMaster.setCompanyGroupSid(dataSelectionDTO.getCustomerGrpSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
        projectionMaster.setItemGroupSid(dataSelectionDTO.getProdGrpSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getProdGrpSid()));
        projectionMaster.setCompanyMasterSid(dataSelectionDTO.getCompanySid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCompanySid()));
        projectionMaster.setFromDate(dateFormat.parse(fromDateValue));   //Obtain from Admin Console
        projectionMaster.setToDate(dateFormat.parse(toDateValue));
        projectionMaster.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
        projectionMaster.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid().equals(Constant.DASH) ? null : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
        if (markAsSaved) {
            projectionMaster.setSaveFlag(true);
        }

        projectionMaster = dataSelection.updateProjectionMaster(projectionMaster);
        return projectionMaster.getProjectionMasterSid();
    }

    public List getCcpMap(final Map<String, Object> parameters) {

        try {
            LOGGER.debug("Entering getCcpMap method ");

            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get(Constant.INDICATOR) != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get(Constant.INDICATOR)))) {
                customSql.append(" MERGE PROJECTION_DETAILS AS TARGET\n"
                        + "                   USING (SELECT distinct ? PROJECTION_MASTER_SID, CCP1.CCP_DETAILS_SID FROM \n"
                        + "                    (SELECT CCP.CCP_DETAILS_SID\n"
                        + "                    FROM RELATIONSHIP_LEVEL_DEFINITION RLD, \n"
                        + "                    CCP_MAP CCP \n"
                        + "                    WHERE RLD.HIERARCHY_NO like '?%' \n"
                        + "                    AND CCP.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID) CCP1, \n"
                        + "                    (SELECT distinct CCPD.CCP_DETAILS_SID \n"
                        + "                    FROM CCP_MAP CCP JOIN RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                        + "                    ON CCP.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID \n"
                        + "                    JOIN PROJECTION_PROD_HIERARCHY PPH \n"
                        + "                    ON PPH.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID AND PPH.PROJECTION_MASTER_SID=?\n"
                        + "                    JOIN CCP_DETAILS CCPD ON CCP.CCP_DETAILS_SID=CCPD.CCP_DETAILS_SID) CCP2 \n"
                        + "                    WHERE CCP1.CCP_DETAILS_SID=CCP2.CCP_DETAILS_SID) AS SOURCE\n"
                        + "                   ON ( TARGET.PROJECTION_MASTER_SID = SOURCE.PROJECTION_MASTER_SID\n"
                        + "                   AND TARGET.CCP_DETAILS_SID = SOURCE.CCP_DETAILS_SID)\n"
                        + "                   WHEN NOT MATCHED THEN\n"
                        + "                   INSERT (PROJECTION_MASTER_SID,CCP_DETAILS_SID)\n"
                        + "                   VALUES (SOURCE.PROJECTION_MASTER_SID, SOURCE.CCP_DETAILS_SID); ");

                if (parameters.get(Constant.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                }

                if (parameters.get(Constant.HIERARACHY_NO) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
                }

                if (parameters.get(Constant.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(Constant.PROJECTION_ID)));
                }

            }

            HelperTableLocalServiceUtil.executeUpdateQuery(customSql.toString());
            return Collections.emptyList();

        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            LOGGER.debug("End of getCcpMap method");
        }
    }

    public void saveCcp(final List<Leveldto> customerEndLevels, final String productHierarchyEndLevelsHierNos, final String indicator, final String projectionId, final AccrualDataSelectionDTO dtoValue) {
        String dedLevel = "Deduction Program Type".equalsIgnoreCase(dtoValue.getDeductionType()) ? "REBATE_PROGRAM_TYPE" : "Deduction Category".equalsIgnoreCase(dtoValue.getDeductionType()) ? "RS_CATEGORY" : "Deduction Schedule Type".equalsIgnoreCase(dtoValue.getDeductionType()) ? Constant.RS_TYPE : StringUtils.EMPTY;
        if (customerEndLevels != null && !customerEndLevels.isEmpty() && StringUtils.isNotBlank(dedLevel)) {
            String filter = " AND R1." + dedLevel + " = " + dtoValue.getDeductionValueId();
            Map<String, Object> parameters;
            for (Leveldto dto : customerEndLevels) {
                parameters = new HashMap<>();
                parameters.put(Constant.PROJECTION_ID, projectionId);
                parameters.put(Constant.INDICATOR, indicator);
                parameters.put("relationshipLevelSid", dto.getRelationshipLevelSid());
                parameters.put(Constant.HIERARACHY_NO, dto.getHierarchyNo() + Constant.PERCENT);
                parameters.put("productHierarchyEndLevelsHierNos", productHierarchyEndLevelsHierNos);
                parameters.put(Constant.INDICATOR, "saveCcp");
                parameters.put("SELECTION", filter);
                getExecuteQuery(parameters);
            }
        }
    }

    public void getExecuteQuery(Map<String, Object> parameters) {
        try {
            String insertQuery = SQlUtil.getQuery("InsertAccrualCCPValue");
            insertQuery = insertQuery.replace(Constant.AT_PROJECTION_MASTER_SID, String.valueOf(parameters.get(Constant.PROJECTION_ID)));
            insertQuery = insertQuery.replace("@HIERARCHY_NO", String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
            insertQuery = insertQuery.replace("@SELECTION", String.valueOf(parameters.get("SELECTION")));
            HelperTableLocalServiceUtil.executeUpdateQuery(insertQuery);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void loadDDLBValue(ComboBox deductionLevel, ComboBox deductionValue, String projectionId) {
        try {
            List<Object> list;
            StringBuilder insertQuery = new StringBuilder(StringUtils.EMPTY);
            insertQuery.append("select FIELD_NAME,FIELD_VALUES from ACCRUAL_PROJ_SELECTION \n"
                    + "where PROJECTION_MASTER_SID=" + projectionId + "\n"
                    + "AND SCREEN_NAME='View'");
            list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(insertQuery.toString());
            deductionLevel.select(String.valueOf(list.get(0)));
            deductionLevel.setImmediate(true);
            deductionValue.select(String.valueOf(list.get(1)));
            deductionValue.setImmediate(true);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public void updateSaveFlag(final int projectionId, String userId) throws SystemException, PortalException {
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        if (!StringUtils.isEmpty(String.valueOf(projectionId)) && !Constant.NULL.equalsIgnoreCase(String.valueOf(projectionId))) {
            projectionMaster = ProjectionMasterLocalServiceUtil.getProjectionMaster(projectionId);
        }
        projectionMaster.setSaveFlag(true);
        projectionMaster.setModifiedDate(new Date());
        projectionMaster.setModifiedBy(UiUtils.parseStringToInteger(userId));
        ProjectionMasterLocalServiceUtil.updateProjectionMaster(projectionMaster);
    }

    /**
     *
     * @param userId
     * @param sessionId
     * @param latch
     */
    public void saveAccrualTab(SessionDTO session, final CountDownLatch latch) {

        String[] queryIds = {"save-accrual-sales-actuals", "save-accrual-sales-details", "save-accrual-rate-actuals", "save-accrual-rate-details", "save-accrual-details-actuals", "save-accrual-details-info", "save-accrual-exclusion-details"};

        for (String queryId : queryIds) {
            Thread thread = new Thread(createRunnable(session, queryId, latch));
            thread.start();
        }

    }

    /**
     *
     * @param userId
     * @param sessionId
     * @param queryId
     * @param latch
     * @return
     */
    private Runnable createRunnable(final SessionDTO session, final String queryId, final CountDownLatch latch) {
        Runnable discountProcedureRunnable = new Runnable() {

            @Override
            public void run() {
                String saveQuery = SQlUtil.getQuery(queryId).replace(Constant.AT_PROJECTION_MASTER_SID, String.valueOf(session.getProjectionId()));
                saveQuery = QueryUtil.replaceTableNames(saveQuery, session.getCurrentTableNames());
                HelperTableLocalServiceUtil.executeUpdateQuery(saveQuery);
                latch.countDown();
            }
        };
        return discountProcedureRunnable;
    }

    /**
     * To insert the temp tables
     *
     * @param sessionDto
     */
    public void insertAccrualTemp(SessionDTO sessionDto) {
        //Sales Tab is initial tab so we need this to be merge 1st in main thread
        String query = QueryUtil.replaceTableNames(accrualMainToTemp(sessionDto.getCurrentTableNames(), String.valueOf(sessionDto.getProjectionId()), "InsertAccrualTempSale"), sessionDto.getCurrentTableNames());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

        //To insert the merge table in trigger
        for (String queryName : new String[]{"InsertAccrualTempExclusion", "InsertAccrualTempRateActuals", "InsertAccrualTempRateDetails", "InsertAccrualTempDetails"}) {
            triggerThreadForMainTotempInsert(sessionDto, queryName);
        }
    }
    /**
     * To insert the temp tables
     *
     * @param sessionDto
     */
    public void insertAccrualProjTemp(SessionDTO sessionDto) {
        //Sales Tab is initial tab so we need this to be merge 1st in main thread
        String query = accrualMainToTemp(sessionDto.getCurrentTableNames(), String.valueOf(sessionDto.getProjectionId()), "InsertAccrualProjTemp");
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    public void saveScreenSelection(int projectionID, Map map, String screenName, String actionValue) {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();
        if (Constant.SAVE.equalsIgnoreCase(actionValue)) {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("INSERT INTO ").append("ACCRUAL_PROJ_SELECTION").append(" (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {

            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE ").append("ACCRUAL_PROJ_SELECTION").append(" SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append("'");
                queryBuilder.append(" WHERE PROJECTION_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(queryBuilder.toString());

    }

    public void submitLogic(int projectionId) {
        String submitQuery;
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM);
        submitQuery = "\n"
                + "Update PROJECTION_MASTER\n"
                + "set  IS_APPROVED='Y',MODIFIED_DATE='" + dateFormat.format(new Date()) + "' \n"
                + "where PROJECTION_MASTER_SID=" + projectionId + " ;";
        HelperTableLocalServiceUtil.executeUpdateQuery(submitQuery);
    }

    public static Map<Object, Object> getProjectionSelection(final int projectionId, final String screenName) {
        List list;
        String query;
        Map<Object, Object> map = new HashMap<>();

        query = "select Field_Name,Field_Values from ACCRUAL_PROJ_SELECTION\n"
                + "where Projection_Master_Sid=" + projectionId + " AND    SCREEN_NAME = '" + screenName + "'" + ";";
        try {
            list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.debug(ex);
        }
        return null;
    }

    public void deleteSelectedProjection(int projId) {
        String deleteQuery = ""
                //                + "delete from ST_ACCRUAL_SALES_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                //                + "delete from ST_ACCRUAL_SALES_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                //                + "delete from ST_ACCRUAL_RATE_ACTUALS where ACCRUAL_PROJ_DETAILS_SID IN (select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                //                + "delete from ST_ACCRUAL_RATE_DETAILS where ACCRUAL_PROJ_DETAILS_SID IN (select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                //                + "delete from ST_ACCRUAL_DETAILS_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                //                + "delete from ST_ACCRUAL_DETAILS_INFO where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_SALES_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_SALES_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_RATE_ACTUALS where ACCRUAL_PROJ_DETAILS_SID IN(select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                + "delete from ACCRUAL_RATE_DETAILS where ACCRUAL_PROJ_DETAILS_SID IN(select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                + "delete from ACCRUAL_DETAILS_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_DETAILS_INFO where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from EXCLUSION_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                //                + "delete from ST_EXCLUSION_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from PROJECTION_PROD_HIERARCHY where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_PROJ_SELECTION where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from PROJECTION_MASTER where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;";
        deleteQuery = deleteQuery.replace(Constant.AT_PROJECTION_MASTER_SID, StringUtils.EMPTY + projId);

        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);

    }

    public void saveCurrentFile(int projId) {
        String saveFileQuery = "Insert INTO ACCRUAL_PROJ_SELECTION (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES)\n"
                + "SELECT projID,FM,FILE_TYPE,FILE_MANAGEMENT_SID\n"
                + "FROM   (SELECT     @PROJECTION_MASTER_SID AS ProjID,'File Management' AS FM,FT.FORECAST_NAME,FT.[VERSION],FILE_MANAGEMENT_SID,\n"
                + "		   HT.[DESCRIPTION] AS FILE_TYPE,\n"
                + "                   ROW_NUMBER()\n"
                + "                     OVER(\n"
                + "                       PARTITION BY FILE_TYPE\n"
                + "                       ORDER BY FILE_MANAGEMENT_SID DESC) AS RN\n"
                + "        FROM       FILE_MANAGEMENT FT\n"
                + "        INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FT.FILE_TYPE\n"
                + "        WHERE      ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())\n"
                + "                     AND FT.FROM_PERIOD IS NOT NULL )\n"
                + "                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())\n"
                + "                          OR FT.TO_PERIOD IS NULL )\n"
                + "                   AND HT.LIST_NAME = 'FILE_TYPE'\n"
                + "                   AND HT.[DESCRIPTION] IN ('Demand', 'Inventory Withdrawal - Forecast Summary','Ex-Factory Sales', 'Adjusted Demand' ,'Inventory Withdrawal - Forecast Summary', 'Customer Sales')\n"
                + ")A\n"
                + "WHERE  RN = 1";
        saveFileQuery = saveFileQuery.replace(Constant.AT_PROJECTION_MASTER_SID, StringUtils.EMPTY + projId);

        HelperTableLocalServiceUtil.executeUpdateQuery(saveFileQuery);
    }

    public static boolean getFileStatus(int projId) {
        try {
            boolean fileFlag = false;
            List<Object> list;
            String saveFileQuery = "SELECT projID,FM,FILE_TYPE,FILE_MANAGEMENT_SID\n"
                    + "FROM   (SELECT     @PROJECTION_MASTER_SID AS ProjID,'File Management' AS FM,FT.FORECAST_NAME,FT.[VERSION],FILE_MANAGEMENT_SID,\n"
                    + "		   HT.[DESCRIPTION] AS FILE_TYPE,\n"
                    + "                   ROW_NUMBER()\n"
                    + "                     OVER(\n"
                    + "                       PARTITION BY FILE_TYPE\n"
                    + "                       ORDER BY FILE_MANAGEMENT_SID DESC) AS RN\n"
                    + "        FROM       FILE_MANAGEMENT FT\n"
                    + "        INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FT.FILE_TYPE\n"
                    + "        WHERE      ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())\n"
                    + "                     AND FT.FROM_PERIOD IS NOT NULL )\n"
                    + "                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())\n"
                    + "                          OR FT.TO_PERIOD IS NULL )\n"
                    + "                   AND HT.LIST_NAME = 'FILE_TYPE'\n"
                    + "                   AND HT.[DESCRIPTION] IN ('Demand', 'Inventory Withdrawal - Forecast Summary','Ex-Factory Sales', 'Adjusted Demand' ,'Inventory Withdrawal - Forecast Summary', 'Customer Sales')\n"
                    + ")A\n"
                    + "WHERE  RN = 1 \n"
                    + "AND FILE_MANAGEMENT_SID NOT IN(select FIELD_VALUES from ACCRUAL_PROJ_SELECTION where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID  "
                    + "  AND SCREEN_NAME='File Management' );";
            saveFileQuery = saveFileQuery.replace(Constant.AT_PROJECTION_MASTER_SID, StringUtils.EMPTY + projId);

            list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(saveFileQuery);

            if (list.size() == 0) {
                fileFlag = true;
            }
            return fileFlag;
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    public void callARPApprovalProcedure(int projectionId) {
        LOGGER.debug("Procedure Name: PRC_ARP_OUTBOUND");
        LOGGER.debug("Projection Id:" + projectionId);
        final Object[] parameters = {projectionId};
        AccrualRateProjectionLogic logic = AccrualRateProjectionLogic.getInstance();
        logic.callARPProcedure(parameters, "PRC_ARP_OUTBOUND");
    }

    /**
     * Build accrual query for main to temp insert
     *
     * @param userId
     * @param sessionId
     * @param projectionId
     */
    private String accrualMainToTemp(GtnSmallHashMap tableNames, String projectionId, String queryName) {

        return QueryUtil.replaceTableNames(SQlUtil.getQuery(queryName), tableNames)
                .replace(Constant.AT_PROJECTION_MASTER_SID, projectionId);
    }

    /**
     * Used to trigger the thread for accrual merge
     *
     * @param sessionDto
     * @param queryName
     */
    private void triggerThreadForMainTotempInsert(SessionDTO sessionDto, final String queryName) {
        Thread t = new Thread(CommonUtil.getInstance().createRunnable(Constant.MERGE_QUERY, accrualMainToTemp(sessionDto.getCurrentTableNames(), String.valueOf(sessionDto.getProjectionId()), queryName), sessionDto));
        t.setName(queryName);
        sessionDto.addThreadInThreadList(t);
        t.start();
    }

}
