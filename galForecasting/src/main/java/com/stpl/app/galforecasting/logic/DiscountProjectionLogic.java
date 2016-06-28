/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dao.CommonDAO;
import com.stpl.app.galforecasting.dao.DiscountProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.dao.impl.DiscountProjectionDAOImpl;
import com.stpl.app.galforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.galforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SaveDTO;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil;
import static com.stpl.app.util.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.util.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.util.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.util.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class DiscountProjectionLogic {

    /**
     * The Discount Data Access Layer
     */
    DiscountProjectionDAO discountDao = new DiscountProjectionDAOImpl();
    CommonDAO dao = new CommonDAOImpl();
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(DiscountProjectionLogic.class);

    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    QueryUtils utils = new QueryUtils();

    /**
     * To load Discount Programs in discount selection lookup
     *
     * @param session
     * @param discountNameList
     * @param programType
     * @return
     */
    public List<DiscountSelectionDTO> loadDiscountPrograms(SessionDTO session, List<String> discountNameList, String programType) {
        LOGGER.info("Entering loadDiscountPrograms");
        List<DiscountSelectionDTO> selectedDiscountList = new ArrayList<DiscountSelectionDTO>();
        List discountList = discountDao.getDiscountProgramList(session, programType);

        DiscountSelectionDTO discountSelectionDto;
        if (discountList != null && discountList.size() != 0) {
            for (int i = 0; i < discountList.size(); i++) {
                final Object[] obj = (Object[]) discountList.get(i);
                discountSelectionDto = new DiscountSelectionDTO();
                discountSelectionDto.setDiscountNo(StringUtils.EMPTY + obj[0]);
                discountSelectionDto.setDiscountName(StringUtils.EMPTY + obj[1]);
                discountSelectionDto.setCheckRecord(discountNameList.contains(discountSelectionDto.getDiscountName()));
                selectedDiscountList.add(discountSelectionDto);
            }
        }
        LOGGER.info("Ending loadDiscountPrograms  ::::");
        return selectedDiscountList;
    }

    /**
     * To get the discount Projection
     *
     * @param session
     * @param startAndEndPeriods
     * @param frequency
     * @param hierarchyIndicator
     * @param history
     * @param isProgram
     * @param userGroup
     * @param projectionPeriodorder
     * @param detailsList
     * @param isParent
     * @param isCustom
     * @param discountList
     * @param year
     * @param rightDto
     * @param start
     * @param offset
     * @param isCount
     * @param customViewDetails
     * @param isParentChecked
     * @param isExcelExport
     * @param isRefresh
     * @param refreshHierarchyNumbers
     * @return
     */
    public List getDiscountProjection(SessionDTO session, String frequency, List<Integer> startAndEndPeriods,
            String history, String hierarchyIndicator, String projectionPeriodorder, String userGroup,
            boolean isProgram, List<String> discountList, String year, List detailsList, boolean isParent, boolean isCustom,
            CustomTableHeaderDTO rightDto, int start, int offset, boolean isCount, Boolean isParentChecked, List<String> customViewDetails,
            boolean isExcelExport, boolean isRefresh, String refreshHierarchyNumbers, String relationshipBuilderSid, boolean isAltHistory, List<DiscountProjectionDTO> pivotList, boolean isTotal, String isAltView, String totalDetailList,List<String> ahPeriodList,Map<String,String> hashMapValues) {

        DiscountProjectionDTO discountDto = new DiscountProjectionDTO();
        int levelNo = 0;
        int treeLevelNo = 0;
        String hierarchyNo = StringUtils.EMPTY;

        levelNo = Integer.valueOf(String.valueOf(detailsList.get(0)));
        hierarchyNo = String.valueOf(detailsList.get(1));
        treeLevelNo = Integer.valueOf(String.valueOf(detailsList.get(2)));
        LOGGER.info(" isCount " + isCount);
        LOGGER.info(" level no " + levelNo);
        LOGGER.info(" customTreeLevelNo " + treeLevelNo);
        LOGGER.info(" Hierarchy No " + hierarchyNo);
        LOGGER.info(" Hierarchy Indicator " + hierarchyIndicator);
        LOGGER.info("Start:::::::::::::::::::::::::" + start);
        LOGGER.info("Offset:::::::::::::::::::::::::" + offset);
        List discountProjectionList =Collections.EMPTY_LIST; 
        if(levelNo != 0 ){
            discountProjectionList = discountDao.getDiscountProjection(session, frequency, startAndEndPeriods, hierarchyNo, isProgram,
                    discountList, year, 0, levelNo, hierarchyIndicator, userGroup, start, offset, isCount, isCustom, customViewDetails, isRefresh, refreshHierarchyNumbers, relationshipBuilderSid, isAltHistory);
        }
        //To Return the list as it is for the getCount method of Pagination table
        if (isCount) {
            return discountProjectionList;
        }

        String levelId = StringUtils.EMPTY;
        String discountName = StringUtils.EMPTY;
        List<DiscountProjectionDTO> discountProjList = new ArrayList<DiscountProjectionDTO>();
        try {

            if (isAltHistory && isTotal ) {
                discountProjList = getProjectionTotal(session.getUserId(), session.getSessionId(),"Variable".equalsIgnoreCase(isAltView)?"pivot":isAltView,frequency, discountDto,startAndEndPeriods,totalDetailList);
            }
            if (isAltHistory && "Variable".equalsIgnoreCase(isAltView)) {
                discountProjList = getPivotView(start, offset, pivotList, discountProjList, treeLevelNo, levelNo, customViewDetails, session, levelId, discountProjectionList, discountDto, discountName, isParentChecked, hierarchyIndicator, frequency, isExcelExport, isCustom,ahPeriodList,hashMapValues);
            } else {
                if (discountProjectionList != null && discountProjectionList.size() != 0) {
                    for (int i = 0; i < discountProjectionList.size(); i++) {
                        final Object[] obj = (Object[]) discountProjectionList.get(i);

                        if (!levelId.equals(String.valueOf(obj[3]))) {
                            if (i != 0) {
                                discountProjList.add(discountDto);
                            }
                            discountDto = new DiscountProjectionDTO();
                            discountName = StringUtils.EMPTY;
                            levelId = String.valueOf(obj[3]);
                            discountDto.setHierarchyNo(String.valueOf(obj[2]));
                            discountDto.setLevelName(session.getLevelValueDiscription(discountDto.getHierarchyNo(), hierarchyIndicator));

                            if (isCustom) {
                                discountDto.setTreeLevelNo(treeLevelNo);
                                discountDto.setProductHierarchyNo(customViewDetails.get(4));
                                discountDto.setCustomerHierarchyNo(customViewDetails.get(2));
                            } else {
                                discountDto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));
                            }

                            String level = String.valueOf(obj[4]);
                            discountDto.setLevel(level);
                            discountDto.setLevelNo(levelNo);
                            boolean checkValue = isParentChecked;
                            if (!isParentChecked) {
                                checkValue = String.valueOf(obj[0]).equals(Constant.STRING_ONE);
                            }
                            discountDto.addBooleanProperties(Constant.CHECKRECORD, checkValue);
                            discountDto.setHierarchyIndicator(hierarchyIndicator);
                            if (Constant.TRADINGPARTNER.equals(level) || Constant.TRADING_PARTNER.equals(level)) {
                                String group = Constant.NULL.equals(String.valueOf(obj[11])) ? StringUtils.EMPTY : String.valueOf(obj[11]);
                                discountDto.addStringProperties(Constant.GROUP, group);
                            }
                        }

                        // To handle count for various discounts
                        if (!discountName.equals(String.valueOf(obj[7]))) {
                            discountName = String.valueOf(obj[7]);
                            discountDto.setCcpCount(discountDto.getCcpCount() + Integer.valueOf(String.valueOf(obj[12])));
                            discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.valueOf(String.valueOf(obj[13])));
                        }

                        String commonColumn = StringUtils.EMPTY;
                        if (frequency.equals(QUARTERLY.getConstant())) {
                            commonColumn = Constant.Q_SMALL + obj[6] + obj[5];
                        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                            commonColumn = Constant.S_SMALL + obj[6] + obj[5];
                        } else if (frequency.equals(MONTHLY.getConstant())) {
                            String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[6]) - 1);
                            commonColumn = monthName.toLowerCase() + obj[5];
                        } else if (frequency.equals(ANNUALLY.getConstant())) {
                            commonColumn = StringUtils.EMPTY + obj[5];
                        }
                        // For Triple Header
                        commonColumn = discountName.replaceAll(" ", StringUtils.EMPTY) + commonColumn;

                        String ActualObject = Constant.NULL.equals(String.valueOf(obj[8])) ? DASH : String.valueOf(obj[8]);
                        String ProjectedObject = Constant.NULL.equals(String.valueOf(obj[9])) ? DASH : String.valueOf(obj[9]);
                        String ActualAmtObject = Constant.NULL.equals(String.valueOf(obj[14])) ? DASH : String.valueOf(obj[14]);
                        String ProjectedAmtObject = Constant.NULL.equals(String.valueOf(obj[16])) ? DASH : String.valueOf(obj[16]);
                        String ActualRPObject = Constant.NULL.equals(String.valueOf(obj[15])) ? DASH : String.valueOf(obj[15]);
                        String ProjectedRPObject = Constant.NULL.equals(String.valueOf(obj[17])) ? DASH : String.valueOf(obj[17]);
                        String GrowthObject = Constant.NULL.equals(String.valueOf(obj[18])) ? DASH : String.valueOf(obj[18]);

                        if (isExcelExport) {
                            ActualObject = getFormattedValue(PERCENTAGE_FORMAT, ActualObject);
                            ProjectedObject = getFormattedValue(PERCENTAGE_FORMAT, ProjectedObject);
                        }
                        int APIndicator = Integer.valueOf(String.valueOf(obj[10]));
                        if (APIndicator == 0) {
                            discountDto.addStringProperties(commonColumn + "ActualRate", ActualObject);
                            discountDto.addStringProperties(commonColumn + "ActualAmount", ActualAmtObject);
                            discountDto.addStringProperties(commonColumn + Constant.ActualRPU, ActualRPObject);
                        }
                        discountDto.addStringProperties(commonColumn + "ProjectedRate", ProjectedObject);
                        discountDto.addStringProperties(commonColumn + "ProjectedAmount", ProjectedAmtObject);
                        discountDto.addStringProperties(commonColumn + Constant.ProjectedRPU, ProjectedRPObject);
                        discountDto.addStringProperties(commonColumn + Constant.GROWTH, GrowthObject);

                        if (i == discountProjectionList.size() - 1) {
                            discountProjList.add(discountDto);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Exit getDiscountProjection");
        return discountProjList;
    }

    /**
     * To adjust the discount projection
     *
     * @param session
     * @return
     */
    public boolean adjustDiscountProjection(SessionDTO session) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.info("Failed to lookup datasource.");
            }
            if (connection != null) {

                LOGGER.info("Got Connection " + connection.toString() + ", ");
                statement = connection.prepareCall("{call PRC_DISCOUNT_MANUAL_ADJUSTMENT (?,?,?,?)}");

                statement.setInt(1, session.getProjectionId());
                statement.setString(2, session.getFrequency());
                statement.setInt(3, Integer.parseInt(session.getUserId()));
                statement.setInt(4, Integer.parseInt(session.getSessionId()));
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return true;
    }

    /**
     * To Update data related to adjustment prior to adjustment
     *
     * @param session
     * @param frequency
     * @param levelType
     * @param adjustmentType
     * @param adjustmentBasis
     * @param adjustmentValue
     * @param allocationMethodology
     * @param selectedPeriods
     * @return
     */
    public boolean adjustmentDataUpdate(SessionDTO session, String frequency, String levelType, String adjustmentType, String adjustmentBasis,
            String adjustmentValue, String allocationMethodology, Map<String, Map<String, List<String>>> selectedPeriods) {

        return discountDao.updateInputsForAdjustment(session, frequency, levelType, adjustmentType, adjustmentBasis,
                adjustmentValue, allocationMethodology, selectedPeriods);
    }

    /**
     * To update the check record during mass update or adjustment
     *
     * @param session
     * @param uncheckedHierarchyNo
     * @param checkedHierarchyNo
     * @param hierarchyIndicator
     * @param isCustomView
     * @param relationshipBuilderSid
     * @param customViewDetails
     */
    public int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo, String userGroup, String hierarchyIndicator,
            boolean isCustomView, List<String> customViewDetails, String relationshipBuilderSid, boolean isProgram, List<String> discountNamesList) {

        return discountDao.updateCheckRecord(session, checkValue, hierarchyNo, userGroup, hierarchyIndicator,
                isCustomView, customViewDetails, relationshipBuilderSid, isProgram, discountNamesList);
    }

    /**
     * To get the level Index for Expand and Collapse functionality
     *
     * @param projectionId
     * @param hierarchy
     * @param hierarchyNo
     * @param selectedHiearchyNo
     * @return
     */
    public int getLevelIndex(int projectionId, String hierarchy, String hierarchyNo, String selectedHiearchyNo) {

        return discountDao.getLevelIndex(projectionId, hierarchy, hierarchyNo, selectedHiearchyNo);
    }

    /**
     * To perform mass update
     *
     * @param session
     * @param frequency
     * @param startAndEndPeriods
     * @param fieldValue
     * @param selectedField
     * @param selectedDiscounts
     * @param isProgram
     */
    public void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
            List<String> selectedDiscounts, boolean isProgram) {

        discountDao.massUpdate(session, frequency, startAndEndPeriods, selectedField, fieldValue, selectedDiscounts, isProgram);
    }

    /**
     *
     * @param session
     * @param hierarchyNo
     * @param fieldValue
     * @return
     */
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String fieldValue, boolean isProgram, boolean isCustom,
            List<String> customViewDetails, List<String> discountList, String relationshipBuilderSid) {

        return discountDao.saveGroupValues(session, hierarchyNo, fieldValue, isProgram, isCustom,
                customViewDetails, discountList, relationshipBuilderSid);
    }

    /**
     * To save the discount projection list view
     *
     * @param session
     * @param frequency
     * @param saveList
     * @param customId
     * @param relationshipBuilderSid
     * @param isProgram
     * @param isCustomHierarchy
     */
    public void saveDiscountProjectionListView(SessionDTO session, String frequency, List<SaveDTO> saveList, int customId, boolean isProgram, boolean isCustomHierarchy, String relationshipBuilderSid) {
        LOGGER.info("Inside saveDiscountProjectionListView");
        if (saveList != null && !saveList.isEmpty()) {
            for (SaveDTO dto : saveList) {
                List<String> customViewDetails = new ArrayList<String>();
                String customerLevelNo = StringUtils.EMPTY;
                String productLevelNo = StringUtils.EMPTY;
                String customerHierarchyNo = StringUtils.EMPTY;
                String productHierachyNo = StringUtils.EMPTY;
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerLevelNo = StringUtils.EMPTY + dto.getTreeLevelNo();
                    productLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = StringUtils.EMPTY + dto.getTreeLevelNo();
                }
                customerHierarchyNo = dto.getCustomerHierarchyNo();
                productHierachyNo = dto.getProductHierarchyNo();

                LOGGER.info(" SaveDTO - Custom hierarchy --- \n customId " + customId);
                LOGGER.info(" SaveDTO - Hierarchy indicator " + dto.getHierarchyIndicator());
                LOGGER.info(" SaveDTO - customerLevelNo " + customerLevelNo);
                LOGGER.info(" SaveDTO - customerHierarchyNo " + customerHierarchyNo);
                LOGGER.info(" SaveDTO - productLevelNo " + productLevelNo);
                LOGGER.info(" SaveDTO - productHierarchyNo " + productHierachyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierachyNo);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
                customViewDetails.add(dto.getRefreshName());
                boolean saveSuccess = discountDao.saveDiscountProjectionListView(session, frequency, dto.getPeriodNo(), dto.getYear(), dto.getHierarchyIndicator(), 0,
                        dto.getHirarechyNo(), dto.getDiscountName(), String.valueOf(dto.getValue()), isProgram, isCustomHierarchy, customViewDetails, relationshipBuilderSid);
                if (!saveSuccess) {
                    break;
                }
            }
        } else {
            LOGGER.info(" Discount projection - No records to save ");
        }
        LOGGER.info(" Exiting saveDiscountProjectionListView");
    }

    /**
     * To get the rebate details from rebate schedule
     *
     * @param projectionId
     * @return
     */
    public List getRebateDetails(int projectionId) {
        List rebateList = discountDao.getGlobalRebateDetails(projectionId);
        // TODO Auto-generated method stub
        return rebateList;
    }

    /**
     * The Discount Projection procedure
     *
     * @param session
     * @return
     */
    public boolean callDiscountProjectionProcedure(SessionDTO session) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_DISCOUNT_INSERT (?,?,?)}");

                statement.setInt(1, session.getProjectionId());
                statement.setInt(2, Integer.parseInt(session.getUserId()));
                statement.setInt(3, Integer.parseInt(session.getSessionId()));
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return false;
    }

    /**
     * To load the level vales for level Ddlb
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @param startLevelNo
     * @param endLevelNo
     * @param customId
     * @param isCustomHierarchy
     * @param isLevelFilter
     * @return
     */
//    public List<DiscountProjectionDTO> loadLevels(int projectionId, String hierarchyIndicator, int startLevelNo, int endLevelNo, int customId, String relationshipBuilderSid, boolean isCustomHierarchy, boolean isLevelFilter) {
//
//        List levelValuesList = discountDao.loadLevels(projectionId, hierarchyIndicator, startLevelNo, endLevelNo, customId, relationshipBuilderSid, isCustomHierarchy, isLevelFilter);
//        List<DiscountProjectionDTO> levelvalueslist = new ArrayList<DiscountProjectionDTO>();
//        DiscountProjectionDTO discountDto;
//        if (levelValuesList != null && !levelValuesList.isEmpty()) {
//            for (Object levelValuesList1 : levelValuesList) {
//                Object[] obj = (Object[]) levelValuesList1;
//                discountDto = new DiscountProjectionDTO();
//
//                if (isLevelFilter) {
//                    discountDto.setTreeLevelNo(CommonUtils.isInteger(obj[0] + "") ? Integer.valueOf(obj[0] + "") : 0);
//                    discountDto.setLevel(obj[1] + "");
//                } else {
//                    discountDto.setHierarchyNo(String.valueOf(obj[0]));
//                    discountDto.setLevelValue(String.valueOf(obj[1]));
//                }
//                levelvalueslist.add(discountDto);
//            }
//        }
//        return levelvalueslist;
//    }
    /**
     * To load the values for group Ddlb
     *
     * @param session
     * @param discountList
     * @return
     */
    public List<String> loadGroupValues(SessionDTO session, List<String> discountList, String relationshipBuilderSid) {
        return discountDao.loadGroupValues(session.getProjectionId(), session.getUserId(), session.getSessionId(), "ST_NM_DISCOUNT_PROJ_MASTER", discountList, relationshipBuilderSid);
    }

    /**
     * To get the formatted value for Excel
     *
     * @param FORMAT
     * @param value
     * @return
     */
    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constant.NULL) || value.equals("-")) {
            value = DASH;
        } else {
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / 100;
            }
            value = FORMAT.format(newValue);
        }
        return value;
    }

    /**
     * To call Discount Actuals Insert procedure
     *
     * @param projectionId
     * @param userId
     * @param sessionId
     * @return
     */
    public boolean callDiscountActualsInsertProcedure(int projectionId, String userId, String sessionId) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                LOGGER.info(" Executing Discount Insert procedure ");
                statement = connection.prepareCall("{call PRC_NM_DISCOUNT_INSERT(?,?,?)}");

                statement.setInt(1, projectionId);
                statement.setInt(2, Integer.parseInt(userId));
                statement.setInt(3, Integer.parseInt(sessionId));
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return true;
    }

    public void checkClearAll(SessionDTO session, String userGroup, boolean checkClear, boolean isProgram, List<String> discountList) {
        discountDao.checkClearAll(session.getProjectionId(), session.getUserId(), session.getSessionId(), userGroup, checkClear, isProgram, discountList);
    }

    public boolean isAnyRecordChecked(SessionDTO session, boolean isProgram, List<String> discountProgramsList) {
        int count = discountDao.getCheckedRecordCount(session.getProjectionId(), session.getUserId(), session.getSessionId(), isProgram, discountProgramsList);
        if (count != 0) {
            if (count == -1) {
                LOGGER.error("Check Count is not retrieved properly");
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
   public void checkUncheckRebateBeforeAdjust(boolean isCheck, List<String> selectedDiscount, SessionDTO session, boolean isCheckList) {
        if (selectedDiscount != null && !selectedDiscount.isEmpty()) {
            String discountIds = StringUtils.EMPTY;
            String query = StringUtils.EMPTY;
            for (String discountName : selectedDiscount) {
                discountIds += "'" + discountName + "',";
            }

            discountIds = discountIds.substring(0, discountIds.length() - 1);
            query = "UPDATE ST_NM_DISCOUNT_PROJ_MASTER \n"
                    + "SET CHECK_RECORD = " + (isCheck ? Constant.STRING_ONE : DASH) + "\n"
                    + "WHERE RS_MODEL_SID IN (SELECT RS_MODEL_SID FROM RS_MODEL WHERE ";
            if (isCheckList) {
                query += " RS_NAME NOT IN (" + discountIds + ")) ";
            } else {
                query += " RS_NAME IN (" + discountIds + ")) ";
            }
            query += "  AND USER_ID= " + session.getUserId() + " and SESSION_ID = " + session.getSessionId() + " ;";

            MSalesProjectionMasterLocalServiceUtil.executeUpdateQuery(query, null, null);
        }
    }

    /**
     /**
     * The Discount Projection procedure for calculation and adjustment
     *
     * @param session
     * @return
     */
    public boolean callDPProcedure(SessionDTO session) {
        LOGGER.info("Entering callDPProcedure "+session.getProjectionId());
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_DISCOUNT_PROJECTION (?,?,?,?)}");

                statement.setInt(1, session.getProjectionId());
                statement.setString(2, String.valueOf(session.getFrequency()));
                statement.setInt(3, Integer.parseInt(session.getUserId()));
                statement.setInt(4, Integer.parseInt(session.getSessionId()));
                statement.execute();
            }
        } catch (Exception ex) {           
            LOGGER.info(ex.getCause());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return false;
    }

    public String getPeriodSid(String period, String fre, String order) {
        List periodSid = (List) dao.executeSelectQuery(utils.periodQuery(period, fre, order), null, null);
        return periodSid.get(0).toString();
    }

       /**
     * To Update data related to calculation prior to calculation
     *
     * @param session
     * @param projectionSelection
     * @param levelType
     * @param methodology
     * @param selectedPeriods
     * @return
     */
    public void calcDataUpdate(SessionDTO session, ProjectionSelectionDTO projectionSelection, String levelType,
            String methodology, Map<String, Map<String, List<String>>> selectedPeriods,List selectedDiscount) {
       utils.updateDiscProjMasterCalc(session.getProjectionId(),session.getUserId(),session.getSessionId(),
                projectionSelection, levelType, methodology, selectedPeriods,selectedDiscount);
//        dao.executeBulkUpdateQuery(query,null,null);
        
    }
    public List<DiscountProjectionDTO> getProjectionTotal(String userId, String sesionId, String view, String frequency, DiscountProjectionDTO totalDTO,List<Integer> startAndEndPeriods,String detailsValue) {
        List<DiscountProjectionDTO> discountProjList = new ArrayList<DiscountProjectionDTO>();
        totalDTO = new DiscountProjectionDTO();
        discountProjList.add(getTotalProjectionList(userId, sesionId, view, frequency, totalDTO,startAndEndPeriods, detailsValue));
        return discountProjList;
    }

    public DiscountProjectionDTO getTotalProjectionList(String userId, String sesionId, String view, String frequency, DiscountProjectionDTO totalDTO, List<Integer> startAndEndPeriods, String detailsValue) {
       LOGGER.info("Details Id:==========>"+detailsValue);
        String queryList = SQlUtil.getQuery("getAlternateHistoryProjectionTotal");
        queryList = queryList.replace("[@PROJECTION_DETAILS_SID]", detailsValue);
        queryList = queryList.replace("[@Frequency]", frequency);
        queryList = queryList.replace("[@@VIEW]", view);
        queryList = queryList.replace("[@USER_ID]", userId);
        queryList = queryList.replace("[@@SESSION_ID]", sesionId);
        queryList = queryList.replace("[@START_MONTH]", "" + startAndEndPeriods.get(0));
        queryList = queryList.replace("[@START_YEAR]", "" + startAndEndPeriods.get(2));
        queryList = queryList.replace("[@END_MONTH]", "" + startAndEndPeriods.get(1));
        queryList = queryList.replace("[@END_YEAR]", "" + startAndEndPeriods.get(3));
        List list = new ArrayList();
        list = HelperTableLocalServiceUtil.executeSelectQuery(queryList);
        totalDTO.setLevelName("Total Alternate History");
        totalDTO.setParentAlternatePivot("Pivot");

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);

                if ("Pivot".equalsIgnoreCase(view)) {
                    totalDTO.addStringProperties("payment" + "Actuals",  getFormattedValue(AMOUNT,String.valueOf(obj[0])));
                    totalDTO.addStringProperties("payment" + "Projections",getFormattedValue(AMOUNT,String.valueOf(obj[1])));
                } else {
                    String discountName = Constant.NULL.equals(String.valueOf(obj[2])) ? "" : String.valueOf(obj[2]);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequency.equals(QUARTERLY.getConstant())) {
                        commonColumn = Constant.Q_SMALL + obj[1] + obj[0];
                    } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                        commonColumn = Constant.S_SMALL + obj[1] + obj[0];
                    } else if (frequency.equals(MONTHLY.getConstant())) {
                        String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    } else if (frequency.equals(ANNUALLY.getConstant())) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    }
                    // For Triple Header
                    commonColumn = discountName.replaceAll(" ", StringUtils.EMPTY) + commonColumn;
                    String ActualAmtObject = Constant.NULL.equals(String.valueOf(obj[3])) ? DASH : String.valueOf(obj[3]);
                    String ProjectedAmtObject = Constant.NULL.equals(String.valueOf(obj[4])) ? DASH : String.valueOf(obj[4]);
                    totalDTO.addStringProperties(commonColumn + "ActualAmount", getFormattedValue(AMOUNT,ActualAmtObject));
                    totalDTO.addStringProperties(commonColumn + "ProjectedAmount",getFormattedValue(AMOUNT,ProjectedAmtObject));
                }
            }
        }

        return totalDTO;
    }

    public List<DiscountProjectionDTO> getPivotView(int start, int offset, List<DiscountProjectionDTO> pivotList, List<DiscountProjectionDTO> discountProjList, int treeLevelNo, int levelNo, List<String> customViewDetails, SessionDTO session, String levelId, List discountProjectionList, DiscountProjectionDTO discountDto, String discountName, boolean isParentChecked, String hierarchyIndicator, String frequency, boolean isExcelExport, boolean isCustom,List<String> ahPeriodList,Map<String,String> hashMapValues) {
        try{
        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        if (pivotList.size() > 0) {
            getParentGroupValue(start, offset, frequency, pivotList, discountDto, isExcelExport, discountProjList,ahPeriodList,hashMapValues);
        }
        if (discountProjectionList != null && discountProjectionList.size() != 0) {
            for (int i = 0; i < discountProjectionList.size(); i++) {
                final Object[] obj = (Object[]) discountProjectionList.get(i);

                if (!levelId.equals(String.valueOf(obj[3]))) {
                    if (i != 0) {
                        discountProjList.add(discountDto);
                    }
                    discountDto = new DiscountProjectionDTO();
                    discountName = StringUtils.EMPTY;
                    levelId = String.valueOf(obj[3]);
                    discountDto.setHierarchyNo(String.valueOf(obj[2]));
                    discountDto.setLevelName(session.getLevelValueDiscription(discountDto.getHierarchyNo(), hierarchyIndicator));
                    discountDto.setAlternatePivotList(discountProjectionList);
                    if (isCustom) {
                        discountDto.setTreeLevelNo(treeLevelNo);
                        discountDto.setProductHierarchyNo(customViewDetails.get(4));
                        discountDto.setCustomerHierarchyNo(customViewDetails.get(2));
                    } else {
                        discountDto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));

                    }

                    String level = String.valueOf(obj[4]);
                    discountDto.setLevel(level);
                    discountDto.setLevelNo(levelNo);
                    boolean checkValue = isParentChecked;
                    if (!isParentChecked) {
                        checkValue = String.valueOf(obj[0]).equals(Constant.STRING_ONE);
                    }
                    discountDto.addBooleanProperties(Constant.CHECKRECORD, checkValue);
                    discountDto.setHierarchyIndicator(hierarchyIndicator);
                    if (Constant.TRADINGPARTNER.equals(level) || Constant.TRADING_PARTNER.equals(level)) {
                        String group = Constant.NULL.equals(String.valueOf(obj[11])) ? StringUtils.EMPTY : String.valueOf(obj[11]);
                        discountDto.addStringProperties(Constant.GROUP, group);
                    }
                }
                // To handle count for various discounts
                if (!discountName.equals(String.valueOf(obj[7]))) {
                    discountName = String.valueOf(obj[7]);
                    discountDto.setCcpCount(discountDto.getCcpCount() + Integer.valueOf(String.valueOf(obj[12])));
                    discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.valueOf(String.valueOf(obj[13])));
                }

                if (i == discountProjectionList.size() - 1) {
                    discountProjList.add(discountDto);
                }
            }
        }
        int mayBeAddedRecord = started - mayBeAdded;
        List<DiscountProjectionDTO> discountProj = new ArrayList<DiscountProjectionDTO>();
        if (mayBeAddedRecord < 0) {
            mayBeAddedRecord = 0;
        }
        for (int k = mayBeAddedRecord; k < discountProjList.size() && neededRecord > 0; k++) {
            discountProj.add(discountProjList.get(k));
            started++;
            neededRecord--;
        }
        return discountProj;
       }catch(Exception e){
       LOGGER.error(e.getMessage());
       return Collections.EMPTY_LIST;
       }
    }

    public void getParentGroupValue(int start, int offset, String frequency, List discountProjectionList, DiscountProjectionDTO discountDto, boolean isExcelExport, List<DiscountProjectionDTO> discountProjList, List<String> ahPeriodList, Map<String, String> hashMapValues) {
        try {
            List<DiscountProjectionDTO> pivotProjList = new ArrayList<DiscountProjectionDTO>();
            Map<String, Object> mapValue = new HashMap<String, Object>();
            List<String> periodList = new ArrayList<String>();
            if (discountProjectionList != null && discountProjectionList.size() != 0) {
                for (int i = 0; i < discountProjectionList.size(); i++) {
                    final Object[] obj = (Object[]) discountProjectionList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String headerColumn = StringUtils.EMPTY;
                    if (frequency.equals(QUARTERLY.getConstant())) {
                        commonColumn = Constant.Q + obj[6] + " " + obj[5];
                        headerColumn = Constant.Q_SMALL + obj[6] + StringUtils.EMPTY + obj[5];
                    } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                        commonColumn = Constant.S + obj[6] + " " + obj[5];
                        headerColumn = Constant.S_SMALL + obj[6] + StringUtils.EMPTY + obj[5];
                    } else if (frequency.equals(MONTHLY.getConstant())) {
                        String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[6]) - 1);
                        commonColumn = monthName.toUpperCase() + " " + obj[5];
                        headerColumn = monthName + StringUtils.EMPTY + obj[5];
                    } else if (frequency.equals(ANNUALLY.getConstant())) {
                        commonColumn = StringUtils.EMPTY + obj[5];
                        headerColumn = StringUtils.EMPTY + obj[5];
                    }
                    if (!periodList.contains(headerColumn)) {
                        periodList.add(headerColumn);
                    }

                    String ActualAmtObject = Constant.NULL.equals(String.valueOf(obj[14])) ? DASH : String.valueOf(obj[14]);
                    String ProjectedAmtObject = Constant.NULL.equals(String.valueOf(obj[16])) ? DASH : String.valueOf(obj[16]);
                    if (mapValue.containsKey(headerColumn)) {
                        discountDto = (DiscountProjectionDTO) mapValue.get(headerColumn);
                        int APIndicator = Integer.valueOf(String.valueOf(obj[10]));
                        if (APIndicator == 0) {
                            discountDto.addStringProperties("payment" + "Actuals", getFormattedValue(AMOUNT, ActualAmtObject));
                        }
                        discountDto.addStringProperties("payment" + "Projections", getFormattedValue(AMOUNT, ProjectedAmtObject));
                    } else {
                        discountDto = new DiscountProjectionDTO();
                        int APIndicator = Integer.valueOf(String.valueOf(obj[10]));
                        if (APIndicator == 0) {
                            discountDto.addStringProperties("payment" + "Actuals", getFormattedValue(AMOUNT, ActualAmtObject));
                        }
                        discountDto.addStringProperties("payment" + "Projections", getFormattedValue(AMOUNT, ProjectedAmtObject));
                        mapValue.put(headerColumn, discountDto);
                    }
                }
            }
            for (String periodValue : ahPeriodList) {
                if (mapValue.containsKey(periodValue)) {
                    discountDto = (DiscountProjectionDTO) mapValue.get(periodValue);
                } else {
                    discountDto = new DiscountProjectionDTO();
                }
                discountDto.setLevelName(String.valueOf(hashMapValues.get(periodValue)));
                discountDto.setParentAlternatePivot("Pivot");
                discountProjList.add(discountDto);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    public String getDetailsId(String userId, String SessionId) {
        String detailsSid = StringUtils.EMPTY;
        try {
            String query = "Select Distinct PROJECTION_DETAILS_SID from ST_DISC_ALTERNATE_HIST_ALLOCATION"
                    + " where USER_ID=" + userId + " AND SESSION_ID=" + SessionId + "";
            List list = new ArrayList();
            list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            for (int i = 0; i < list.size(); i++) {
                Object obj = (Object) list.get(i);
                if (!StringUtils.EMPTY.equalsIgnoreCase(String.valueOf(obj)) && !"null".equalsIgnoreCase(String.valueOf(obj))) {
                    detailsSid += StringUtils.EMPTY + Integer.valueOf(String.valueOf(obj)) + ",";
                }
            }
            detailsSid.substring(0, detailsSid.length() - 1);
            return detailsSid;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return detailsSid;
        }
    }
    
    
}
