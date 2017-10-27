/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.discountProjection.logic.DiscountQueryBuilder;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.QueryUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    DiscountQueryBuilder queryBuilderAndExecutor = new DiscountQueryBuilder();
    CommonDAO dao = new CommonDAOImpl();
    public static final String PAYMENT1 = "payment";
    public static final String PIVOT_LABEL = "Pivot";
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(DiscountProjectionLogic.class);
    public static final String JBOSS_DATA_POOL = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat PERCENTAGE_FORMAT_THREE_DECIMAL = new DecimalFormat("#,##0.000%");
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    QueryUtils utils = new QueryUtils();
    List<String> projectionPeriodList;
    String baselinePeriods = "";
    String selectedPeriods = "";
    
    CommonLogic commonLogic = new CommonLogic();
    
    /**
     * To load Discount Programs in discount selection lookup
     *
     * @param session
     * @param discountNameList
     * @param programType
     * @return
     */
    public List<DiscountSelectionDTO> loadDiscountPrograms(SessionDTO session, List<String> discountNameList, String programType) {
        LOGGER.debug("Entering loadDiscountPrograms");
        List<DiscountSelectionDTO> selectedDiscountList = new ArrayList<>();
        List discountList = queryBuilderAndExecutor.getDiscountPrograms(session, programType);
        DiscountSelectionDTO discountSelectionDto;
        if (discountList != null && discountList.size() != 0) {
            if ("DiscountType".equals(programType)) {
                Set<String> programGroupList = new HashSet<>();
                for (int i = 0; i < discountList.size(); i++) {
                    final Object[] obj = (Object[]) discountList.get(i);
                    programGroupList.add(StringUtils.EMPTY + obj[1]);
                }
                for (String programGroup : programGroupList) {
                    List<List<String>> discountRSlist = new ArrayList<>();
                    List<String> rsIdList = new ArrayList<>();
                    List<String> rsNameList = new ArrayList<>();
                    for (int i = 0; i < discountList.size(); i++) {
                        final Object[] obj = (Object[]) discountList.get(i);
                        if (programGroup.equals(StringUtils.EMPTY + obj[1])) {
                            rsIdList.add(StringUtils.EMPTY + obj[NumericConstants.TWO]);
                            rsNameList.add(StringUtils.EMPTY + obj[NumericConstants.THREE]);
                        }
                    }
                    discountSelectionDto = new DiscountSelectionDTO();
                    discountSelectionDto.setDiscountNo(StringUtils.EMPTY);
                    discountSelectionDto.setDiscountName(programGroup);
                    discountRSlist.add(rsIdList);
                    discountRSlist.add(rsNameList);
                    discountSelectionDto.setDiscountRSlist(discountRSlist);
                    selectedDiscountList.add(discountSelectionDto);
                    discountSelectionDto.setCheckRecord(discountNameList.contains(discountSelectionDto.getDiscountName()));
                }
            } else {
                for (int i = 0; i < discountList.size(); i++) {
                    final Object[] obj = (Object[]) discountList.get(i);

                    discountSelectionDto = new DiscountSelectionDTO();
                    discountSelectionDto.setDiscountNo(StringUtils.EMPTY + obj[0]);
                    discountSelectionDto.setDiscountName(StringUtils.EMPTY + obj[1]);
                    discountSelectionDto.setRsId(StringUtils.EMPTY + obj[NumericConstants.TWO]);
                    discountSelectionDto.setRsName(StringUtils.EMPTY + obj[NumericConstants.THREE]);
                    discountSelectionDto.setCheckRecord(discountNameList.toString().contains("~"+discountSelectionDto.getRsId()));
                    selectedDiscountList.add(discountSelectionDto);
                }
            }
        }
        LOGGER.debug("Ending loadDiscountPrograms  ::::");
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
            boolean isExcelExport, boolean isRefresh, String refreshHierarchyNumbers, String relationshipBuilderSid, boolean isAltHistory, List<DiscountProjectionDTO> pivotList, 
            boolean isTotal, String isAltView, String totalDetailList,List<String> ahPeriodList,Map<String,String> hashMapValues, List<String> forecastConfigList) {

        DiscountProjectionDTO discountDto = new DiscountProjectionDTO();
        int levelNo = 0;
        int treeLevelNo = 0;
        String hierarchyNo;

        levelNo = Integer.valueOf(String.valueOf(detailsList.get(0)));
        hierarchyNo = String.valueOf(detailsList.get(1));
        treeLevelNo = Integer.valueOf(String.valueOf(detailsList.get(NumericConstants.TWO)));
        LOGGER.debug(" isCount " + isCount);
        LOGGER.debug(" level no " + levelNo);
        LOGGER.debug(" customTreeLevelNo " + treeLevelNo);
        LOGGER.debug(" Hierarchy No " + hierarchyNo);
        LOGGER.debug(" Hierarchy Indicator " + hierarchyIndicator);
        LOGGER.debug("Start:" + start);
        LOGGER.debug(" history " + history);
        LOGGER.debug(" rightDto " + rightDto);
        LOGGER.debug(" relationshipBuilderSid " + relationshipBuilderSid);
        LOGGER.debug(" isRefresh " + isRefresh);
        LOGGER.debug("Offset:" + offset);
        List discountProjectionList = Collections.EMPTY_LIST;
        if(levelNo != 0 ){
        discountProjectionList = queryBuilderAndExecutor.getDiscountProjection(isProgram,frequency,discountList,session,hierarchyNo,
                hierarchyIndicator,levelNo,isCustom,customViewDetails,treeLevelNo,start,offset,userGroup);
        }
        //To Return the list as it is for the getCount method of Pagination table
        if (isCount) {
            return discountProjectionList;
        }

        String levelId = StringUtils.EMPTY;
        String discountName = StringUtils.EMPTY;
         GtnSmallHashMap ccpCountForDiscount=new GtnSmallHashMap();
        List<DiscountProjectionDTO> discountProjList = new ArrayList<>();
        try {

            if (isAltHistory && isTotal ) {
                discountProjList = getProjectionTotal(session.getUserId(), session.getSessionId(),"Variable".equalsIgnoreCase(isAltView)?"pivot":isAltView,frequency, discountDto,startAndEndPeriods,totalDetailList);
            }
            if (isAltHistory && "Variable".equalsIgnoreCase(isAltView)) {
                discountProjList = getPivotView(start, offset, pivotList, discountProjList, treeLevelNo, levelNo, customViewDetails, session, levelId, discountProjectionList, discountDto, discountName, isParentChecked, hierarchyIndicator, frequency, isCustom,ahPeriodList,hashMapValues);
            } else {
                hierarchyNo = StringUtils.EMPTY;
                if (discountProjectionList != null && !discountProjectionList.isEmpty()) {
                    for (int i = 0; i < discountProjectionList.size(); i++) {
                        final Object[] obj = (Object[]) discountProjectionList.get(i);

                        if (!hierarchyNo.equals(String.valueOf(obj[1]))) {
                            if (i != 0) {
                                discountProjList.add(discountDto);
                            }
                            discountDto = new DiscountProjectionDTO();
                            discountName = StringUtils.EMPTY;
                            hierarchyNo = String.valueOf(obj[1]);
                            discountDto.setHierarchyNo(String.valueOf(obj[1]));
                            discountDto.setLevelName(session.getHierarchyLevelDetails().get(discountDto.getHierarchyNo()).get(0).toString());
                            discountDto.setHierarchyIndicator(hierarchyIndicator);
                            if (isCustom) {
                                discountDto.setTreeLevelNo(treeLevelNo);
                                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(discountDto.getHierarchyIndicator())) {
                                    discountDto.setCustomerHierarchyNo(discountDto.getHierarchyNo());
                                    discountDto.setProductHierarchyNo(customViewDetails.get(NumericConstants.FOUR));
                                } else {
                                    discountDto.setCustomerHierarchyNo(customViewDetails.get(NumericConstants.TWO));
                                    discountDto.setProductHierarchyNo(discountDto.getHierarchyNo());
                                }
                            } else {
                                discountDto.setTreeLevelNo(Integer.valueOf(session.getHierarchyLevelDetails().get(discountDto.getHierarchyNo()).get(NumericConstants.TWO).toString()));
                            }

                            String level = String.valueOf(session.getHierarchyLevelDetails().get(discountDto.getHierarchyNo()).get(1));
                            discountDto.setLevel(level);
                            discountDto.setLevelNo(levelNo);
                            boolean checkValue = isParentChecked;
                            if (!isParentChecked) {
                                checkValue = String.valueOf(obj[0]).equals(Constant.STRING_ONE);
                            }
                            discountDto.addBooleanProperties(Constant.CHECKRECORD, checkValue);
                            discountDto.setHierarchyIndicator(hierarchyIndicator);
                            if (Constant.TRADINGPARTNER.equals(level) || Constant.TRADING_PARTNER.equals(level)) {
                                String group = Constant.NULL.equals(String.valueOf(obj[NumericConstants.EIGHT])) ? StringUtils.EMPTY : String.valueOf(obj[NumericConstants.EIGHT]);
                                discountDto.addStringProperties(Constant.GROUP, group);
                            }
                        }
                       
                        // To handle count for various discounts
                        if (!discountName.equals(String.valueOf(obj[NumericConstants.FOUR]))) {
                            discountName =isProgram? String.valueOf(obj[NumericConstants.FOUR])+"~"+Integer.valueOf(""+obj[NumericConstants.SIXTEEN]): String.valueOf(obj[NumericConstants.FOUR]);
                            
                            ccpCountForDiscount.put(discountName, Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                            discountDto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                            discountDto.setCcpCountForDiscount(ccpCountForDiscount);
                            discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.valueOf(String.valueOf(obj[NumericConstants.TEN])));
                        }
                        
                        
                        String column = StringUtils.EMPTY;
                        if (frequency.equals(QUARTERLY.getConstant())) {
                            column = Constant.Q_SMALL + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
                        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                            column = Constant.S_SMALL + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
                        } else if (frequency.equals(MONTHLY.getConstant())) {
                            String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[NumericConstants.THREE]) - 1);
                            column = monthName.toLowerCase() + obj[NumericConstants.TWO];
                        } else if (frequency.equals(ANNUALLY.getConstant())) {
                            column = StringUtils.EMPTY + obj[NumericConstants.TWO];
                        }
                        // For Triple Header
                          String commonColumn = discountName.replaceAll(" ", StringUtils.EMPTY) + column;

                        String ActualObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FIVE])) ? DASH : String.valueOf(obj[NumericConstants.FIVE]);
                        String ProjectedObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.SIX])) ? DASH : String.valueOf(obj[NumericConstants.SIX]);
                        String ActualAmtObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.ELEVEN])) ? DASH : String.valueOf(obj[NumericConstants.ELEVEN]);
                        String ProjectedAmtObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.THIRTEEN])) ? DASH : String.valueOf(obj[NumericConstants.THIRTEEN]);
                        String ActualRPObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWELVE])) ? DASH : String.valueOf(obj[NumericConstants.TWELVE]);
                        String ProjectedRPObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]);
                        String GrowthObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FIFTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FIFTEEN]);

                        if (isExcelExport) {
                            ActualObject = getFormattedValue(PERCENTAGE_FORMAT, ActualObject);
                            ProjectedObject = getFormattedValue(PERCENTAGE_FORMAT, ProjectedObject);
                        }
                        int APIndicator = Integer.valueOf(String.valueOf(obj[NumericConstants.SEVEN]));
                        if (APIndicator == 0) {
                            discountDto.addStringProperties(commonColumn + "ActualRate", ActualObject);
                            discountDto.addStringProperties(commonColumn + "ActualAmount", ActualAmtObject);
                            discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU, ActualRPObject);
                        }
                        ProjectedObject = CommonUtils.forecastConfigDataHide(frequency,forecastConfigList, column, ProjectedObject);
                        ProjectedAmtObject = CommonUtils.forecastConfigDataHide(frequency,forecastConfigList, column, ProjectedAmtObject);
                        ProjectedRPObject = CommonUtils.forecastConfigDataHide(frequency,forecastConfigList, column, ProjectedRPObject);
                        discountDto.addStringProperties(commonColumn + "ProjectedRate", ProjectedObject);
                        discountDto.addStringProperties(commonColumn + "ProjectedAmount", ProjectedAmtObject);
                        discountDto.addStringProperties(commonColumn + Constant.PROJECTEDRPU, ProjectedRPObject);
                        discountDto.addStringProperties(commonColumn + Constant.GROWTH, GrowthObject);

                        if (i == discountProjectionList.size() - 1) {
                            discountProjList.add(discountDto);
                        }
                    }
                }
            }
        LOGGER.debug(" projectionPeriodorder " + projectionPeriodorder);
        LOGGER.debug(" year " + year);
        LOGGER.debug(" refreshHierarchyNumbers " + refreshHierarchyNumbers);
        LOGGER.debug(" isParent " + isParent);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Exit getDiscountProjection");
        return discountProjList;
    }
    
    /**
     * To adjust the discount projection
     *
     * @param session
     * @return
     */
    public boolean adjustDiscountProjection(SessionDTO session, final String adjustmentType,
                                        final String adjustmentBasis, final String adjustmentValue, final String allocationMethodology) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.debug("Failed to lookup datasource.");
            }
            if (connection != null) {

                LOGGER.debug("Got Connection " + connection.toString() + ", ");
                LOGGER.debug("Frequency      " + session.getFrequency());
                LOGGER.debug("UserId         " + session.getUserId());
                LOGGER.debug("SessionId      " + session.getSessionId());
                LOGGER.debug("adjustmentType  "+adjustmentType);
                LOGGER.debug("adjustmentBasis "+adjustmentBasis);
                LOGGER.debug("allocationMethodology "+allocationMethodology);
                LOGGER.debug("adjustmentValue "+adjustmentValue);
                LOGGER.debug("baselinePeriods "+baselinePeriods);
                LOGGER.debug("selectedPeriods "+selectedPeriods);
                statement = connection.prepareCall("{call PRC_DISCOUNT_MANUAL_ADJUSTMENT (?,?,?,?,?,?,?,?,?,?)}");

                statement.setInt(1, session.getProjectionId());
                statement.setString(NumericConstants.TWO, session.getFrequency());
                statement.setInt(NumericConstants.THREE, Integer.parseInt(session.getUserId()));
                statement.setString(NumericConstants.FOUR, session.getSessionId());
                statement.setString(NumericConstants.FIVE, adjustmentType);
                statement.setString(NumericConstants.SIX,adjustmentBasis);
                statement.setString(NumericConstants.SEVEN, allocationMethodology);
                statement.setString(NumericConstants.EIGHT, adjustmentValue);
                statement.setString(NumericConstants.NINE, baselinePeriods);
                statement.setString(NumericConstants.TEN, selectedPeriods);
             
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return true;
    }

    /**
     * To Update data related to adjustment prior to adjustment
     *
     * @param frequency
     * @param allocationMethodology
     * @return
     */
    public boolean adjustmentDataUpdate(String frequency, String allocationMethodology, Map<String, Map<String, List<String>>> periodsMap) {
            List<String> baselinePeriodsList;
            List<String> selectedPeriodsList;
            String baselineIndicator = "";

            if ("Historical % of Business".equals(allocationMethodology)) {
                baselineIndicator = "H";
            } else {
                baselineIndicator = "P";
    }

            for (String discountName : periodsMap.keySet()) {
                baselinePeriodsList = periodsMap.get(discountName).get(baselineIndicator);
                selectedPeriodsList = periodsMap.get(discountName).get("P");

                baselinePeriods = CommonUtils.CollectionToString(baselinePeriodsList, false, true);
                selectedPeriods = CommonUtils.CollectionToString(selectedPeriodsList, false, true);

                baselinePeriods = baselinePeriods.replace(", ", ",");
                selectedPeriods = selectedPeriods.replace(", ", ",");

                if (frequency.equals(MONTHLY.getConstant())) {
                    baselinePeriods = CommonUtils.replaceShortMonthForMonth(baselinePeriods);
                    selectedPeriods = CommonUtils.replaceShortMonthForMonth(selectedPeriods);
                }
                
            }
            return true;
                
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
    public int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo,String hierarchyIndicator,
            boolean isCustomView, List<String> customViewDetails, boolean isProgram, List<String> discountNamesList) {

        return queryBuilderAndExecutor.updateCheckRecord(session, checkValue, hierarchyNo, hierarchyIndicator, 
                isCustomView, customViewDetails, isProgram, discountNamesList);
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
     * @param massUpdateData
     */
    public void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
              List<String> selectedDiscounts, boolean isProgram,List<String[]> massUpdateData, List<String> selectedPeriods) {
        queryBuilderAndExecutor.massUpdate(session, frequency, startAndEndPeriods, selectedField, fieldValue, selectedDiscounts, isProgram,massUpdateData, selectedPeriods);
    }

    /**
     *
     * @param session
     * @param hierarchyNo
     * @param fieldValue
     * @return
     */
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String fieldValue, boolean isProgram,List<String> discountList) {

        return queryBuilderAndExecutor.saveGroupValues(session, hierarchyNo, fieldValue, isProgram,  discountList);       
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
    public void saveDiscountProjectionListView(SessionDTO session, String frequency, List<SaveDTO> saveList, int customId, boolean isProgram, boolean isCustomHierarchy) {
        LOGGER.debug("Inside saveDiscountProjectionListView");
        if (saveList != null && !saveList.isEmpty()) {
            for (SaveDTO dto : saveList) {
                List<String> customViewDetails = new ArrayList<>();
                String customerLevelNo;
                String productLevelNo;
                String customerHierarchyNo;
                String productHierachyNo;
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerLevelNo = StringUtils.EMPTY + dto.getTreeLevelNo();
                    productLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = StringUtils.EMPTY + dto.getTreeLevelNo();
                }
                customerHierarchyNo = dto.getCustomerHierarchyNo();
                productHierachyNo = dto.getProductHierarchyNo();

                LOGGER.debug(" SaveDTO - Custom hierarchy --- \n customId " + customId);
                LOGGER.debug(" SaveDTO - Hierarchy indicator " + dto.getHierarchyIndicator());
                LOGGER.debug(" SaveDTO - customerLevelNo " + customerLevelNo);
                LOGGER.debug(" SaveDTO - customerHierarchyNo " + customerHierarchyNo);
                LOGGER.debug(" SaveDTO - productLevelNo " + productLevelNo);
                LOGGER.debug(" SaveDTO - productHierarchyNo " + productHierachyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierachyNo);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
                customViewDetails.add(dto.getRefreshName());
                boolean saveSuccess = queryBuilderAndExecutor.saveDiscountProjectionListView(session, frequency,  dto.getPeriodNo(), dto.getYear(), dto.getHierarchyIndicator(), 
                        dto.getHirarechyNo(),isProgram ? dto.getDiscountName().contains("~")?dto.getDiscountName().split("~")[1]:dto.getDiscountName() : dto.getDiscountName().contains("~")?dto.getDiscountName().split("~")[0]:dto.getDiscountName(), String.valueOf(dto.getValue()), isProgram, isCustomHierarchy, customViewDetails);
                
                if (!saveSuccess) {
                    break;
                }
            }
        } else {
            LOGGER.debug(" Discount projection - No records to save ");
        }
        LOGGER.debug(" Exiting saveDiscountProjectionListView");
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
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_DISCOUNT_INSERT (?,?,?)}");
                statement.setInt(1, session.getProjectionId());
                statement.setInt(NumericConstants.TWO, Integer.parseInt(session.getUserId()));
                statement.setString(NumericConstants.THREE, session.getSessionId());
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return false;
    }

    /**
     * To load the values for group Ddlb
     *
     * @param session
     * @param discountList
     * @return
     */
    public List<String> loadGroupValues(SessionDTO session, List<String> discountList) {
        return queryBuilderAndExecutor.getGroupValues(session, discountList);
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
                newValue = newValue / NumericConstants.HUNDRED;
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
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                LOGGER.debug(" Executing Discount Insert procedure ");
                statement = connection.prepareCall("{call PRC_NM_DISCOUNT_INSERT(?,?,?)}");

                statement.setInt(1, projectionId);
                statement.setInt(NumericConstants.TWO, Integer.parseInt(userId));
                statement.setInt(NumericConstants.THREE, Integer.parseInt(sessionId));
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        return true;
    }

    public void checkClearAll(SessionDTO session, String userGroup, boolean checkClear) {
        queryBuilderAndExecutor.checkClearAll(session, userGroup, checkClear);
    }

    public boolean isAnyRecordChecked(SessionDTO session, boolean isProgram, List<String> discountProgramsList) {
        int count = queryBuilderAndExecutor.getCheckedRecordCount(session, isProgram, discountProgramsList);
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
    
   public void checkUncheckRebateBeforeAdjust(boolean isCheck, List<String> selectedDiscount, SessionDTO session, boolean isCheckList, boolean isProgram) {
        if (selectedDiscount != null && !selectedDiscount.isEmpty()) {
           String discountIds = StringUtils.EMPTY;
           String query;
           for (String discountName : selectedDiscount) {
               discountIds += "'" + discountName + "',";
           }

           discountIds = discountIds.substring(0, discountIds.length() - 1);
           query = "UPDATE ST_NM_DISCOUNT_PROJ_MASTER \n"
                   + "SET CHECK_RECORD = " + (isCheck ? Constant.STRING_ONE : DASH) + "\n";
           if (isCheckList) {
               query += isProgram ? " WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT WHERE RS_NAME NOT IN (" + discountIds + "))"
                       :" WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT RC JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE WHERE HT.DESCRIPTION NOT IN (" + discountIds + "))";
           } else {
               query += isProgram ? "WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT WHERE RS_NAME IN (" + discountIds + "))" 
                       :" WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT RC JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE WHERE HT.DESCRIPTION NOT IN (" + discountIds + ")) ";
           }

           MSalesProjectionMasterLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()), null, null);
       }
    }
  
    public List getRSDiscountSids(String projectionId) {
        try {
            String rsContractQuery = "Declare @rs_contract varchar(50)\n"
                    + "select @rs_contract=FIELD_VALUES from NM_PROJECTION_SELECTION where SCREEN_NAME='Discount Projection' \n"
                    + "AND PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID \n"
                    + "AND FIELD_NAME='SelectedDiscountsSids'\n"
                    + ""
                    + "select RS_NAME,RS_CONTRACT_SID from RS_CONTRACT where RS_CONTRACT_SID IN(select token from udf_splitstring(@rs_contract,','))\n"
                    + "ORDER BY RS_NAME,RS_CONTRACT_SID";
            rsContractQuery = rsContractQuery.replace("@PROJECTION_MASTER_SID", projectionId);
            List<Object[]> rsContList = (List<Object[]>) MSalesProjectionMasterLocalServiceUtil.executeSelectQuery(rsContractQuery, null, null);
            List rawList = new ArrayList<>();
            List<String> rsNameSids = new ArrayList<>();
            List<String> rsNoSids = new ArrayList<>();
            for (Object[] rsList : rsContList) {
                rsNoSids.add(StringUtils.EMPTY + Integer.valueOf(rsList[1].toString()));
                rsNameSids.add(String.valueOf(rsList[0]) + "~" + Integer.valueOf(rsList[1].toString()));
            }
            rawList.add(rsNameSids);
            rawList.add(rsNoSids);
            return rawList;
        } catch (Exception ex) {

            LOGGER.error(ex);
            return Collections.emptyList();

        }
    }

    /**
     /**
     * The Discount Projection procedure for calculation and adjustment
     *
     * @param session
     * @return
     */
    public boolean callDPProcedure(SessionDTO session,ProjectionSelectionDTO projectionSelection ) {
        LOGGER.debug("Entering callDPProcedure  : PRC_NM_DISCOUNT_PROJECTION" );
        LOGGER.debug("Projection ID " + session.getProjectionId());
        LOGGER.debug("Frequency                " + String.valueOf(session.getFrequency()));
        LOGGER.debug("UserId                   " + session.getUserId());
        LOGGER.debug("Session Id               " + session.getSessionId());
        LOGGER.debug("Forecast Start               " + projectionSelection.getFromDateDdlb());
        LOGGER.debug("Forecast End              " +  projectionSelection.getToDateDdlb());
        LOGGER.debug("Calc Based              " +  projectionSelection.getCalcBased());

        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_DISCOUNT_PROJECTION (?,?,?,?,?,?,?)}");

                statement.setInt(1, session.getProjectionId());
                statement.setString(NumericConstants.TWO, String.valueOf(session.getFrequency()));
                statement.setInt(NumericConstants.THREE, Integer.parseInt(session.getUserId()));
                statement.setString(NumericConstants.FOUR, session.getSessionId());
                statement.setString(NumericConstants.FIVE, projectionSelection.getFromDateDdlb());
                statement.setString(NumericConstants.SIX, projectionSelection.getToDateDdlb());
                statement.setString(NumericConstants.SEVEN, projectionSelection.getCalcBased());
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
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
             Map<String, Map<String, List<String>>> selectedPeriods,List selectedDiscount,Boolean isProgram) {
       projectionSelection.setSessionDTO(session);
       utils.updateDiscProjMasterCalc(projectionSelection, selectedPeriods,selectedDiscount,isProgram);
        
    }
    public List<DiscountProjectionDTO> getProjectionTotal(String userId, String sesionId, String view, String frequency, DiscountProjectionDTO totalDTO,List<Integer> startAndEndPeriods, String detailsValue) {
        List<DiscountProjectionDTO> discountProjList = new ArrayList<>();
        totalDTO = new DiscountProjectionDTO();
        discountProjList.add(getTotalProjectionList(userId, sesionId, view, frequency, totalDTO, startAndEndPeriods, detailsValue));
        return discountProjList;
    }

    public DiscountProjectionDTO getTotalProjectionList(String userId, String sesionId, String view, String frequency, DiscountProjectionDTO totalDTO, List<Integer> startAndEndPeriods, String detailsValue) {
       LOGGER.debug("Details Id:==========>"+detailsValue);
        String queryList = SQlUtil.getQuery("getAlternateHistoryProjectionTotal");
        queryList = queryList.replace("[@PROJECTION_DETAILS_SID]", detailsValue);
        queryList = queryList.replace("[@Frequency]", frequency);
        queryList = queryList.replace("[@@VIEW]", view);
        queryList = queryList.replace("[@USER_ID]", userId);
        queryList = queryList.replace("[@@SESSION_ID]", sesionId);
        queryList = queryList.replace("[@START_MONTH]", "" + startAndEndPeriods.get(0));
        queryList = queryList.replace("[@START_YEAR]", "" + startAndEndPeriods.get(NumericConstants.TWO));
        queryList = queryList.replace("[@END_MONTH]", "" + startAndEndPeriods.get(1));
        queryList = queryList.replace("[@END_YEAR]", "" + startAndEndPeriods.get(NumericConstants.THREE));
        List list;
        list = HelperTableLocalServiceUtil.executeSelectQuery(queryList);
        totalDTO.setLevelName("Total Alternate History");
        totalDTO.setParentAlternatePivot(PIVOT_LABEL);

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);

                if (PIVOT_LABEL.equalsIgnoreCase(view)) {
                    totalDTO.addStringProperties(PAYMENT1 + Constant.ACTUALS,  getFormattedValue(AMOUNT,String.valueOf(obj[0])));
                    totalDTO.addStringProperties(PAYMENT1 + Constant.PROJECTIONS,getFormattedValue(AMOUNT,String.valueOf(obj[1])));
                } else {
                    String discountName = Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWO])) ? "" : String.valueOf(obj[NumericConstants.TWO]);
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
                    String ActualAmtObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.THREE])) ? DASH : String.valueOf(obj[NumericConstants.THREE]);
                    String ProjectedAmtObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOUR])) ? DASH : String.valueOf(obj[NumericConstants.FOUR]);
                    totalDTO.addStringProperties(commonColumn + "ActualAmount", getFormattedValue(AMOUNT,ActualAmtObject));
                    totalDTO.addStringProperties(commonColumn + "ProjectedAmount",getFormattedValue(AMOUNT,ProjectedAmtObject));
                }
            }
        }

        return totalDTO;
    }

    public List<DiscountProjectionDTO> getPivotView(int start, int offset, List<DiscountProjectionDTO> pivotList, List<DiscountProjectionDTO> discountProjList, int treeLevelNo, int levelNo, List<String> customViewDetails, SessionDTO session, String levelId, List discountProjectionList, DiscountProjectionDTO discountDto, String discountName, boolean isParentChecked, String hierarchyIndicator, String frequency, boolean isCustom,List<String> ahPeriodList,Map<String,String> hashMapValues) {
        try{
        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        GtnSmallHashMap ccpCountForDiscount=new GtnSmallHashMap();
        if (pivotList.size() > 0) {
            getParentGroupValue(frequency, pivotList, discountDto, discountProjList,ahPeriodList,hashMapValues);
        }
        if (discountProjectionList != null && discountProjectionList.size() != 0) {
            for (int i = 0; i < discountProjectionList.size(); i++) {
                final Object[] obj = (Object[]) discountProjectionList.get(i);

                if (!levelId.equals(String.valueOf(obj[NumericConstants.THREE]))) {
                    if (i != 0) {
                        discountProjList.add(discountDto);
                    }
                    discountDto = new DiscountProjectionDTO();
                    discountName = StringUtils.EMPTY;
                    levelId = String.valueOf(obj[NumericConstants.THREE]);
                    discountDto.setHierarchyNo(String.valueOf(obj[NumericConstants.TWO]));
                    discountDto.setLevelName(session.getLevelValueDiscription(discountDto.getHierarchyNo(), hierarchyIndicator));
                    discountDto.setAlternatePivotList(discountProjectionList);
                    if (isCustom) {
                        discountDto.setTreeLevelNo(treeLevelNo);
                        discountDto.setProductHierarchyNo(customViewDetails.get(NumericConstants.FOUR));
                        discountDto.setCustomerHierarchyNo(customViewDetails.get(NumericConstants.TWO));
                    } else {
                        discountDto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));

                    }

                    String level = String.valueOf(obj[NumericConstants.FOUR]);
                    discountDto.setLevel(level);
                    discountDto.setLevelNo(levelNo);
                    boolean checkValue = isParentChecked;
                    if (!isParentChecked) {
                        checkValue = String.valueOf(obj[0]).equals(Constant.STRING_ONE);
                    }
                    discountDto.addBooleanProperties(Constant.CHECKRECORD, checkValue);
                    discountDto.setHierarchyIndicator(hierarchyIndicator);
                    if (Constant.TRADINGPARTNER.equals(level) || Constant.TRADING_PARTNER.equals(level)) {
                        String group = Constant.NULL.equals(String.valueOf(obj[NumericConstants.ELEVEN])) ? StringUtils.EMPTY : String.valueOf(obj[NumericConstants.ELEVEN]);
                        discountDto.addStringProperties(Constant.GROUP, group);
                    }
                }
                // To handle count for various discounts
                if (!discountName.equals(String.valueOf(obj[NumericConstants.SEVEN]))) {
                    discountName = String.valueOf(obj[NumericConstants.SEVEN]);
                    ccpCountForDiscount.put(discountName, Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                    discountDto.setCcpCount(discountDto.getCcpCount() + Integer.valueOf(String.valueOf(obj[NumericConstants.TWELVE])));
                    discountDto.setCcpCountForDiscount(ccpCountForDiscount);
                    discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.valueOf(String.valueOf(obj[NumericConstants.THIRTEEN])));
                }

                if (i == discountProjectionList.size() - 1) {
                    discountProjList.add(discountDto);
                }
            }
        }
        int mayBeAddedRecord = started - mayBeAdded;
        List<DiscountProjectionDTO> discountProj = new ArrayList<>();
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
       LOGGER.error(e);
       return Collections.EMPTY_LIST;
       }
    }

    public void getParentGroupValue(String frequency, List discountProjectionList, DiscountProjectionDTO discountDto,
           List<DiscountProjectionDTO> discountProjList,List<String> ahPeriodList, Map<String, String> hashMapValues) {
        try {
            Map<String, Object> mapValue = new HashMap<>();
            List<String> periodList = new ArrayList<>();
            if (discountProjectionList != null && discountProjectionList.size() != 0) {
                for (int i = 0; i < discountProjectionList.size(); i++) {
                    final Object[] obj = (Object[]) discountProjectionList.get(i);
                    String headerColumn = StringUtils.EMPTY;
                    if (frequency.equals(QUARTERLY.getConstant())) {
                        headerColumn = Constant.Q_SMALL + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                        headerColumn = Constant.S_SMALL + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequency.equals(MONTHLY.getConstant())) {
                        String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[NumericConstants.SIX]) - 1);
                        headerColumn = monthName + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequency.equals(ANNUALLY.getConstant())) {
                        headerColumn = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    }
                    if (!periodList.contains(headerColumn)) {
                        periodList.add(headerColumn);
                    }

                    String ActualAmtObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]);
                    String ProjectedAmtObject = Constant.NULL.equals(String.valueOf(obj[NumericConstants.SIXTEEN])) ? DASH : String.valueOf(obj[NumericConstants.SIXTEEN]);
                    if (mapValue.containsKey(headerColumn)) {
                        discountDto = (DiscountProjectionDTO) mapValue.get(headerColumn);
                        int APIndicator = Integer.valueOf(String.valueOf(obj[NumericConstants.TEN]));
                        if (APIndicator == 0) {
                            discountDto.addStringProperties(PAYMENT1 + Constant.ACTUALS, getFormattedValue(AMOUNT, ActualAmtObject));
                        }
                        discountDto.addStringProperties(PAYMENT1 + Constant.PROJECTIONS, getFormattedValue(AMOUNT, ProjectedAmtObject));
                    } else {
                        discountDto = new DiscountProjectionDTO();
                        int APIndicator = Integer.valueOf(String.valueOf(obj[NumericConstants.TEN]));
                        if (APIndicator == 0) {
                            discountDto.addStringProperties(PAYMENT1 + Constant.ACTUALS, getFormattedValue(AMOUNT, ActualAmtObject));
                        }
                        discountDto.addStringProperties(PAYMENT1 + Constant.PROJECTIONS, getFormattedValue(AMOUNT, ProjectedAmtObject));
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
                discountDto.setParentAlternatePivot(PIVOT_LABEL);
                discountProjList.add(discountDto);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    public String getDetailsId(SessionDTO session) {
        String detailsSid = StringUtils.EMPTY;
        try {
            String query = "Select Distinct PROJECTION_DETAILS_SID from ST_DISC_ALTERNATE_HIST_ALLOCATION";
            List list;
            list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            for (int i = 0; i < list.size(); i++) {
                Object obj = (Object) list.get(i);
                if (!StringUtils.EMPTY.equalsIgnoreCase(String.valueOf(obj)) && !"null".equalsIgnoreCase(String.valueOf(obj))) {
                    detailsSid += StringUtils.EMPTY + Integer.valueOf(String.valueOf(obj)) + ",";
                }
            }
            detailsSid.substring(0, detailsSid.length() - 1);
            return detailsSid;
        } catch (Exception e) {
            LOGGER.error(e);
            return detailsSid;
        }
    }

    
    /**
     * Checks for the available records in the actual (Government Discount)
     * tables.
     *
     * @param projectionId
     * @param userId
     * @param sessionId
     * @return true - if records available in table. false - if records not
     * available in table.
     */
    public boolean checkForActualGovtDiscount(SessionDTO session) {

        String query = SQlUtil.getQuery("check-mandated-actual-discount");
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        
        return (Integer) list.get(0) == 1;

    }

    /**
     * Checks for the available records in the projection (Government Discount)
     * tables.
     *
     * @param projectionId
     * @param userId
     * @param sessionId
     * @return true - if records available in table. false - if records not
     * available in table.
     */
    public boolean checkForGovtDiscountProjection(SessionDTO session) {
        String query = SQlUtil.getQuery("check-mandated-discount-projection");
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        
        return (Integer) list.get(0) == 1;
    }

    
    public int getDiscountCount(final SessionDTO sessionDTO, final String hierarchyNo, final int levelNo, final String hierarchyIndicator, 
                                        boolean isProgram,final List<String> discountList,final String userGroup) {        
        String query = queryBuilderAndExecutor.getDiscountCountQuery(sessionDTO,hierarchyNo,levelNo,hierarchyIndicator,isProgram,discountList,userGroup);
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        if(list!=null){
            return Integer.valueOf(list.get(0).toString());
}
        
        return 0;
    }
    
    public int getDiscountCustomCount(final SessionDTO sessionDTO, final String hierarchyIndicator,final int levelNo,
            final String customerHierarchyNo,final String productHierarchyNo, final List<String> discountList,boolean isProgram,final String userGroup) {
        String parentHierarchyIndicator = StringUtils.EMPTY;
        String parentHierarchyNo = StringUtils.EMPTY;
        
        if("C".equalsIgnoreCase(hierarchyIndicator) && StringUtils.isNotBlank(productHierarchyNo)){
            parentHierarchyIndicator = "P";
            parentHierarchyNo=productHierarchyNo;
        } else if("P".equalsIgnoreCase(hierarchyIndicator) && StringUtils.isNotBlank(customerHierarchyNo)){
            parentHierarchyIndicator = "C";
            parentHierarchyNo=customerHierarchyNo;
        }
        
        String query = queryBuilderAndExecutor.getDiscountCustomCountQuery(sessionDTO, hierarchyIndicator, levelNo, "C".equalsIgnoreCase(hierarchyIndicator)?customerHierarchyNo:productHierarchyNo
                , parentHierarchyIndicator,parentHierarchyNo,discountList,isProgram,userGroup);

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        if (list != null) {
            return Integer.valueOf(list.get(0).toString());
        }

        return 0;
    }
    
    public Map<Integer, List> configureVisibleColumnMapsForExcel(Map<Integer, List> sourceHeaderMap) {
    
        final Map<Integer, List> finalMap = new HashMap<>();

        for (int key : sourceHeaderMap.keySet()) {
    
            List list = sourceHeaderMap.get(key);
            if (finalMap.containsKey((Integer) list.get(NumericConstants.TWO))) {

                List tempList = finalMap.get((Integer) list.get(NumericConstants.TWO));
                
                List singleVisibleColumnList = (List) tempList.get(0);
                singleVisibleColumnList.addAll((Collection) list.get(0));

                List singleHeaderList = (List) tempList.get(1);
                singleHeaderList.addAll((Collection) list.get(1));

                List doubleVisibleColumnsList = (List) tempList.get(NumericConstants.THREE);
                doubleVisibleColumnsList.addAll((Collection) list.get(NumericConstants.THREE));

                List doubleHeaderList = (List) tempList.get(NumericConstants.FOUR);
                doubleHeaderList.addAll((Collection) list.get(NumericConstants.FOUR));

                Map<Object, Object[]> map = (Map<Object, Object[]>) tempList.get(NumericConstants.FIVE);
                map.putAll((Map<Object, Object[]>) list.get(NumericConstants.FIVE));

            } else {
                List tempList = new ArrayList();
                tempList.add(list.get(0));
                tempList.add(list.get(1));
                tempList.add(list.get(NumericConstants.TWO));
                tempList.add(list.get(NumericConstants.THREE));
                tempList.add(list.get(NumericConstants.FOUR));
                tempList.add(list.get(NumericConstants.FIVE));
                finalMap.put(Integer.valueOf(list.get(NumericConstants.TWO).toString()), tempList);

}

        }

        return finalMap;
    }
    

}