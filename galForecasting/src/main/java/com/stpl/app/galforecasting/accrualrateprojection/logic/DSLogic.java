/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.accrualrateprojection.logic;

import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualDataSelectionDTO;
import static com.stpl.app.galforecasting.logic.NonMandatedLogic.LOGGER;
import static com.stpl.app.galforecasting.logic.NonMandatedLogic.dataSelection;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.utils.Constants;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                        + "SET  SCREEN_NAME='" + dtoValue.getScreenName() + "',FIELD_NAME='" + dtoValue.getDeductionType() + "',FIELD_VALUES='" + dtoValue.getDeductionValue()+ "'\n"
                        + "where Projection_Master_Sid=" + dtoValue.getProjectionId() + ";");

            } else {
                updateQuery.append("Insert ACCRUAL_PROJ_SELECTION (SCREEN_NAME,FIELD_NAME,FIELD_VALUES,PROJECTION_MASTER_SID) \n"
                        + "values('" + dtoValue.getScreenName() + "','" + dtoValue.getDeductionType() + "','" + dtoValue.getDeductionValue()+ "'," + dtoValue.getProjectionId() + ")");
            }

            HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery.toString());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void insertRSValue(int projectionId) {
        try {

            StringBuilder updateQuery = new StringBuilder(StringUtils.EMPTY);
            updateQuery.append("MERGE ACCRUAL_PROJ_DETAILS AS TARGET\n"
                    + "USING (SELECT CCP_PROJ.PROJECTION_MASTER_SID,\n"
                    + "              CCP_PROJ.CCP_DETAILS_SID,\n"
                    + "              R1.RS_MODEL_SID\n"
                    + "       FROM   (SELECT DISTINCT " + projectionId + " PROJECTION_MASTER_SID,\n"
                    + "                               CCP1.CCP_DETAILS_SID\n"
                    + "               FROM   (SELECT CCP.CCP_DETAILS_SID\n"
                    + "                       FROM   RELATIONSHIP_LEVEL_DEFINITION RLD,\n"
                    + "                              CCP_MAP CCP\n"
                    + "                       WHERE  RLD.HIERARCHY_NO LIKE '41-1.1.1.%'\n"
                    + "                              AND CCP.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID) CCP1\n"
                    + "               JOIN   (SELECT DISTINCT CCPD.CCP_DETAILS_SID\n"
                    + "                       FROM   CCP_MAP CCP\n"
                    + "                       JOIN   RELATIONSHIP_LEVEL_DEFINITION RLD ON CCP.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID\n"
                    + "                       JOIN   PROJECTION_PROD_HIERARCHY PPH ON PPH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID\n"
                    + "                                                           AND PPH.PROJECTION_MASTER_SID = " + projectionId + "\n"
                    + "                       JOIN   CCP_DETAILS CCPD ON CCP.CCP_DETAILS_SID = CCPD.CCP_DETAILS_SID) CCP2 ON CCP1.CCP_DETAILS_SID = CCP2.CCP_DETAILS_SID)CCP_PROJ\n"
                    + "       JOIN   CCP_DETAILS B ON CCP_PROJ.CCP_DETAILS_SID = B.CCP_DETAILS_SID\n"
                    + "       JOIN   RS_CONTRACT R1 ON R1.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID\n"
                    + "       JOIN   RS_CONTRACT_DETAILS R2 ON R2.RS_CONTRACT_SID = R1.RS_CONTRACT_SID\n"
                    + "                                    AND R2.ITEM_MASTER_SID = B.ITEM_MASTER_SID\n"
                    + "       WHERE  R1.INBOUND_STATUS <> 'D'\n"
                    + "              AND R2.INBOUND_STATUS <> 'D'\n"
                    + "              AND EXISTS(SELECT 1\n"
                    + "                         FROM   CFP_CONTRACT CFP1\n"
                    + "                         JOIN   CFP_CONTRACT_DETAILS CFP2 ON CFP1.CFP_CONTRACT_SID = CFP2.CFP_CONTRACT_SID\n"
                    + "                                                         AND R1.CFP_CONTRACT_SID = CFP1.CFP_CONTRACT_SID\n"
                    + "                         WHERE  COMPANY_MASTER_SID = B.COMPANY_MASTER_SID\n"
                    + "                                AND CFP1.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID)) AS SOURCE\n"
                    + "ON ( TARGET.PROJECTION_MASTER_SID = SOURCE.PROJECTION_MASTER_SID\n"
                    + "     AND TARGET.CCP_DETAILS_SID = SOURCE.CCP_DETAILS_SID\n"
                    + "     AND TARGET.RS_MODEL_SID = SOURCE.RS_MODEL_SID )\n"
                    + "WHEN NOT MATCHED THEN\n"
                    + "  INSERT (PROJECTION_MASTER_SID,\n"
                    + "          CCP_DETAILS_SID,\n"
                    + "          RS_MODEL_SID)\n"
                    + "  VALUES (SOURCE.PROJECTION_MASTER_SID,\n"
                    + "          SOURCE.CCP_DETAILS_SID,\n"
                    + "          SOURCE.RS_MODEL_SID);");
            HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery.toString());

        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public int saveProjection(final DataSelectionDTO dataSelectionDTO, String screenName) throws SystemException, Exception {
        String fromDateValue = "01-01-2016";
        String toDateValue = "01-01-2018";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

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
    public int updateProjection(final DataSelectionDTO dataSelectionDTO, int projectionId, final boolean markAsSaved) throws PortalException, SystemException, Exception {

        String fromDateValue = "01-01-2016";
        String toDateValue = "01-01-2018";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        LOGGER.info("Entering updateProjection ");
        if (!StringUtils.isEmpty(String.valueOf(projectionId)) && !Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
            projectionMaster = dataSelection.getProjectionMaster(projectionId);
        }
        projectionMaster.setProjectionName(dataSelectionDTO.getProjectionName());
        projectionMaster.setProjectionDescription(dataSelectionDTO.getDescription());
        projectionMaster.setForecastingType(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
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
            LOGGER.info("Entering getCcpMap method ");

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
            return null;

        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " in getCcpMap");
            return null;
        } finally {
            LOGGER.info("End of getCcpMap method");
        }
    }

    public void saveCcp(final List<Leveldto> customerEndLevels, final String productHierarchyEndLevelsHierNos, final String indicator, final String projectionId) throws Exception {
        DSLogic dsLogic = new DSLogic();
        if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
            Map<String, Object> parameters;
            for (Leveldto dto : customerEndLevels) {
                parameters = new HashMap<String, Object>();
                parameters.put(Constant.PROJECTION_ID, projectionId);
                parameters.put(Constant.INDICATOR, indicator);
                parameters.put("relationshipLevelSid", dto.getRelationshipLevelSid());
                parameters.put(Constant.HIERARACHY_NO, dto.getHierarchyNo() + Constant.PERCENT);
                parameters.put("productHierarchyEndLevelsHierNos", productHierarchyEndLevelsHierNos);
                parameters.put(Constant.INDICATOR, "saveCcp");
                getExecuteQuery(parameters);
            }
        }
    }

    public void getExecuteQuery(Map<String, Object> parameters) {
        try {
            String insertQuery = CustomSQLUtil.get("InsertAccrualCCPValue");
            insertQuery = insertQuery.replace("@PROJECTION_MASTER_SID", String.valueOf(parameters.get(Constant.PROJECTION_ID)));
            insertQuery = insertQuery.replace("@HIERARCHY_NO", String.valueOf(parameters.get(Constant.HIERARACHY_NO)));
            HelperTableLocalServiceUtil.executeUpdateQuery(insertQuery);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void loadDDLBValue(ComboBox deductionLevel, ComboBox deductionValue, String projectionId) {
        try {
            List<Object> list = new ArrayList<Object>();
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

    public void saveAccrualTab(String userId, String sessionId, String queryName) {
        String saveQuery = CustomSQLUtil.get(queryName);
        saveQuery = saveQuery.replace("@USER_ID", userId);
        saveQuery = saveQuery.replace("@SESSION_ID", sessionId);
        HelperTableLocalServiceUtil.executeUpdateQuery(saveQuery.toString());

    }

    public void insertAccrualTemp(String userId, String sessionId, String queryName, String projectionId) {
        String saveQuery = CustomSQLUtil.get(queryName);
        saveQuery = saveQuery.replace("@USER_ID", userId);
        saveQuery = saveQuery.replace("@SESSION_ID", sessionId);
        saveQuery = saveQuery.replace("@PROJECTION_MASTER_SID", projectionId);
        HelperTableLocalServiceUtil.executeUpdateQuery(saveQuery);
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
        String submitQuery = StringUtils.EMPTY;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        submitQuery = "\n"
                + "Update PROJECTION_MASTER\n"
                + "set  IS_APPROVED='Y',MODIFIED_DATE='" + dateFormat.format(new Date()) + "' \n"
                + "where PROJECTION_MASTER_SID=" + projectionId + " ;";
        HelperTableLocalServiceUtil.executeUpdateQuery(submitQuery);
    }

    public static Map<Object, Object> getProjectionSelection(final int projectionId, final String screenName) {
        List list = new ArrayList<>();
        String query = StringUtils.EMPTY;
        Map<Object, Object> map = new HashMap<Object, Object>();

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
            LOGGER.info(ex.getCause());
        }
        return null;
    }

    public void deleteSelectedProjection(int projId) {
        String deleteQuery = "delete from ST_ACCRUAL_SALES_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ST_ACCRUAL_SALES_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ST_ACCRUAL_RATE_ACTUALS where ACCRUAL_PROJ_DETAILS_SID IN (select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                + "delete from ST_ACCRUAL_RATE_DETAILS where ACCRUAL_PROJ_DETAILS_SID IN (select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                + "delete from ST_ACCRUAL_DETAILS_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ST_ACCRUAL_DETAILS_INFO where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_SALES_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_SALES_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_RATE_ACTUALS where ACCRUAL_PROJ_DETAILS_SID IN(select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                + "delete from ACCRUAL_RATE_DETAILS where ACCRUAL_PROJ_DETAILS_SID IN(select ACCRUAL_PROJ_DETAILS_SID from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID );\n"
                + "delete from ACCRUAL_DETAILS_ACTUALS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_DETAILS_INFO where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from EXCLUSION_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ST_EXCLUSION_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from PROJECTION_PROD_HIERARCHY where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_PROJ_DETAILS where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from ACCRUAL_PROJ_SELECTION where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;\n"
                + "delete from PROJECTION_MASTER where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID;";
        deleteQuery = deleteQuery.replace("@PROJECTION_MASTER_SID", StringUtils.EMPTY + projId);

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
                + "                   AND HT.[DESCRIPTION] IN ('Demand', 'Inventory Withdrawal - Forecast Summary','Ex-Factory Sales', 'Adjusted Demand')\n"
                + ")A\n"
                + "WHERE  RN = 1";
        saveFileQuery = saveFileQuery.replace("@PROJECTION_MASTER_SID", StringUtils.EMPTY + projId);

        HelperTableLocalServiceUtil.executeUpdateQuery(saveFileQuery);
    }

    public static boolean getFileStatus(int projId) {
        try {
            boolean fileFlag = false;
            List<Object> list = new ArrayList<Object>();
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
                    + "                   AND HT.[DESCRIPTION] IN ('Demand', 'Inventory Withdrawal - Forecast Detail', 'Ex-Factory Sales', 'Adjusted Demand')\n"
                    + ")A\n"
                    + "WHERE  RN = 1 \n"
                    + "AND FILE_MANAGEMENT_SID NOT IN(select FIELD_VALUES from ACCRUAL_PROJ_SELECTION where PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID  "
                    + "  AND SCREEN_NAME='File Management' );";
            saveFileQuery = saveFileQuery.replace("@PROJECTION_MASTER_SID", StringUtils.EMPTY + projId);

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

}
