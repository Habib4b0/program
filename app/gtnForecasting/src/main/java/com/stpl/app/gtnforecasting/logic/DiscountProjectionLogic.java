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
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author sriram
 */
public class DiscountProjectionLogic {

    private final DiscountQueryBuilder queryBuilderAndExecutor = new DiscountQueryBuilder();
    private final CommonDAO dao = new CommonDAOImpl();
    public static final String PAYMENT1 = "payment";
    public static final String PIVOT_LABEL = "Pivot";
    public static final String ALL = "ALL";
    public static final String ACTUAL_RATE = "ActualRate";
    public static final String PROJECTED_RATE = "ProjectedRate";
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(DiscountProjectionLogic.class);
    public static final String JBOSS_DATA_POOL = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final String DF_LEVEL_NAME  = "dfLevelName";
    private static final String DF_LEVEL_NUMBER = "dfLevelNumber";
    private final QueryUtils utils = new QueryUtils();
    
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
        if (discountList != null && !discountList.isEmpty()) {
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
                    discountSelectionDto.setCheckRecord(discountNameList.toString().contains("~" + discountSelectionDto.getRsId()));
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
     * @param forecastConfigList
     * @return
     */
    public List getDiscountProjection(SessionDTO session, String frequency, List<Integer> startAndEndPeriods,
            String history, String hierarchyIndicator, String projectionPeriodorder, String userGroup,
            boolean isProgram, List<String> discountList, String year, List detailsList, boolean isParent, boolean isCustom,
            CustomTableHeaderDTO rightDto, int start, int offset, boolean isCount, Boolean isParentChecked, List<String> customViewDetails,
            boolean isExcelExport, boolean isRefresh, String refreshHierarchyNumbers, String relationshipBuilderSid, boolean isAltHistory, List<DiscountProjectionDTO> pivotList,
            boolean isTotal, String isAltView, String totalDetailList, List<String> ahPeriodList, Map<String, String> hashMapValues, List<String> forecastConfigList, ProjectionSelectionDTO projectionSelection) {

        DiscountProjectionDTO discountDto = new DiscountProjectionDTO();
        int levelNo = 0;
        int treeLevelNo = 0;
        String hierarchyNo;

        List doubleProjectedAndHistoryCombinedUniqueList = getDoubleProjectedAndHistoryCombinedUniqueList(rightDto);

        levelNo = Integer.parseInt(String.valueOf(detailsList.get(0)));
        hierarchyNo = String.valueOf(detailsList.get(1));
        treeLevelNo = Integer.parseInt(String.valueOf(detailsList.get(NumericConstants.TWO)));
        LOGGER.debug(" isCount= {} " , isCount);
        LOGGER.debug(" level no= {} " , levelNo);
        LOGGER.debug(" customTreeLevelNo= {} " , treeLevelNo);
        LOGGER.info(" Hierarchy No= {} " , hierarchyNo);
        LOGGER.debug(" Hierarchy Indicator= {} " , hierarchyIndicator);
        LOGGER.debug("Start= {}" , start);
        LOGGER.debug(" history= {} " , history);
        LOGGER.debug(" rightDto= {} " , rightDto);
        LOGGER.debug(" relationshipBuilderSid= {} " , relationshipBuilderSid);
        LOGGER.debug(" isRefresh= {} " , isRefresh);
        LOGGER.debug("Offset= {}" , offset);
        List discountProjectionList = Collections.emptyList();
        if (levelNo != 0) {
            if (CommonUtil.isValueEligibleForLoading() && !isCustom) {
                discountProjectionList = queryBuilderAndExecutor.getDiscountProjectionLastLevel(frequency, discountList, session, hierarchyNo,
                        hierarchyIndicator, levelNo,  customViewDetails, treeLevelNo, start, offset, userGroup, projectionSelection);
            } else {
                discountProjectionList = queryBuilderAndExecutor.getDiscountProjectionCustom(isProgram, frequency, discountList, session, hierarchyNo,
                        hierarchyIndicator, levelNo, isCustom, customViewDetails, treeLevelNo, start, offset, userGroup);
            }
        }
        if (isCount) {
            return discountProjectionList;
        }

        String levelId = StringUtils.EMPTY;
        String discountName = StringUtils.EMPTY;
        GtnSmallHashMap ccpCountForDiscount = new GtnSmallHashMap();
        List<DiscountProjectionDTO> discountProjList = new ArrayList<>();
        try {

            if (isAltHistory && isTotal) {
                discountProjList = getProjectionTotal(session.getUserId(), session.getSessionId(), "Variable".equalsIgnoreCase(isAltView) ? "pivot" : isAltView, frequency, discountDto, startAndEndPeriods, totalDetailList);
            }
            if (isAltHistory && "Variable".equalsIgnoreCase(isAltView)) {
                discountProjList = getPivotView(start, offset, pivotList, discountProjList, treeLevelNo, levelNo, customViewDetails, session, levelId, discountProjectionList, discountDto, discountName, isParentChecked, hierarchyIndicator, frequency, isCustom, ahPeriodList, hashMapValues, projectionSelection);
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
                            String relValue;
                            if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)) {
                                relValue = discountDto.getHierarchyNo().contains("~") ? discountDto.getHierarchyNo().substring(discountDto.getHierarchyNo().lastIndexOf('~') + 1) : discountDto.getHierarchyNo();
                            } else {
                                String hierarchy = discountDto.getHierarchyNo().contains(",") ? discountDto.getHierarchyNo().split(",")[0] : discountDto.getHierarchyNo();
                                relValue = hierarchy.trim();
                            }
                            String levelName;
                            if(isCustom){
                            levelName = CommonUtil.getDisplayFormattedName(relValue,  session.getDiscountHierarchyLevelDetails(), projectionSelection.getDisplayFormat());
                            }else{
                            levelName = CommonUtil.getDisplayFormattedName(relValue,  session.getHierarchyLevelDetails(), projectionSelection.getDisplayFormat());
                                                        }
                            discountDto.setLevelName(levelName);
                            if (levelName.contains("-")) {
                                String[] tempArr = levelName.split("-");
                                discountDto.addStringProperties(DF_LEVEL_NUMBER, tempArr[0]);
                                discountDto.addStringProperties(DF_LEVEL_NAME, tempArr[1]);
                            } else if (projectionSelection.getDisplayFormat().length > 0) {
                                int index = (int) projectionSelection.getDisplayFormat()[0];
                                if (index == 0) {
                                    discountDto.addStringProperties(DF_LEVEL_NUMBER, levelName);
                                    discountDto.addStringProperties(DF_LEVEL_NAME, StringUtils.EMPTY);
                                } else {
                                    discountDto.addStringProperties(DF_LEVEL_NAME, levelName);
                                    discountDto.addStringProperties(DF_LEVEL_NUMBER, StringUtils.EMPTY);
                                }
                            } else {
                                discountDto.addStringProperties(DF_LEVEL_NUMBER, levelName);
                            }
                            discountDto.setHierarchyIndicator(hierarchyIndicator);
                            if (isCustom) {
                                discountDto.setTreeLevelNo(treeLevelNo);
                                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(discountDto.getHierarchyIndicator())) {
                                    discountDto.setCustomerHierarchyNo(customViewDetails.get(NumericConstants.TWO));
                                    discountDto.setProductHierarchyNo(customViewDetails.get(NumericConstants.FOUR));
                                    discountDto.setDeductionHierarchyNo(discountDto.getHierarchyNo());
                                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(discountDto.getHierarchyIndicator())) {
                                    discountDto.setCustomerHierarchyNo(discountDto.getHierarchyNo());
                                    discountDto.setProductHierarchyNo(customViewDetails.get(NumericConstants.FOUR));
                                    discountDto.setDeductionHierarchyNo(customViewDetails.get(NumericConstants.NINE));
                                } else {
                                    discountDto.setCustomerHierarchyNo(customViewDetails.get(NumericConstants.TWO));
                                    discountDto.setProductHierarchyNo(discountDto.getHierarchyNo());
                                    discountDto.setDeductionHierarchyNo(customViewDetails.get(NumericConstants.NINE));
                                }
                            } else {
                                String hierarchy = discountDto.getHierarchyNo().contains(",") ? discountDto.getHierarchyNo().split(",")[0] : discountDto.getHierarchyNo();
                                discountDto.setTreeLevelNo(Integer.valueOf(session.getHierarchyLevelDetails().get(hierarchy.trim()).get(NumericConstants.TWO).toString()));
                            }
                            String level = "";
                            if (isCustom) {
                            if (!Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)) {
                                String hierarchy = discountDto.getHierarchyNo().contains(",") ? discountDto.getHierarchyNo().split(",")[0] : discountDto.getHierarchyNo();
                                level = String.valueOf(session.getDiscountHierarchyLevelDetails().get(hierarchy).get(1));
                            } else {
                                level = "";
                            }
                            }else{
                            if (!Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)) {
                                String hierarchy = discountDto.getHierarchyNo().contains(",") ? discountDto.getHierarchyNo().split(",")[0] : discountDto.getHierarchyNo();
                                level = String.valueOf(session.getHierarchyLevelDetails().get(hierarchy).get(1));
                            } else {
                                level = "";
                            }
                            }
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

                        if (!isCustom) {
                            if (!discountName.equals(String.valueOf(obj[NumericConstants.FOUR]))) {
                                discountName = String.valueOf(obj[NumericConstants.FOUR]);

                                if (obj[NumericConstants.NINE] != null) {
                                    ccpCountForDiscount.put(discountName, Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                                    discountDto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                                    discountDto.setCcpCountForDiscount(ccpCountForDiscount);
                                    discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.parseInt(String.valueOf(obj[NumericConstants.TEN])));
                                }
                            }
                        }else{
                             if (obj[NumericConstants.NINE] != null) {
                                 discountDto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                                 discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.parseInt(String.valueOf(obj[NumericConstants.TEN])));
                             }
                         }
                        String column = StringUtils.EMPTY;
                        if (frequency.equals(QUARTERLY.getConstant())) {
                            column = Constant.Q_SMALL + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
                        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
                            column = Constant.S_SMALL + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
                        } else if (frequency.equals(MONTHLY.getConstant())) {
                            String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[NumericConstants.THREE]) - 1);
                            column = monthName.toLowerCase(Locale.ENGLISH) + obj[NumericConstants.TWO];
                        } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
                            column = StringUtils.EMPTY + obj[NumericConstants.TWO];
                        }
                        String commonColumn;
                        if (!isCustom) {
                            commonColumn = discountName.replaceAll(" ", StringUtils.EMPTY) + column;
                        } else {
                            commonColumn = column;
                        }

                        String actualObj = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FIVE])) ? DASH : String.valueOf(obj[NumericConstants.FIVE]);
                        String projObj = Constant.NULL.equals(String.valueOf(obj[NumericConstants.SIX])) ? DASH : String.valueOf(obj[NumericConstants.SIX]);
                        String actualConvertedObj = CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.ELEVEN], false);
                        String actualAmountObj = Constant.NULL.equals(actualConvertedObj) ? DASH : actualConvertedObj;
                        actualConvertedObj = CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.THIRTEEN], false);
                        String projectedAmoutObj = Constant.NULL.equals(actualConvertedObj) ? DASH : actualConvertedObj;
                        String actualRpObj = Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWELVE])) ? DASH : String.valueOf(obj[NumericConstants.TWELVE]);
                        String projectedRpObj = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]);
                        String growthObj = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FIFTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FIFTEEN]);
                        discountDto.setDeductionInclusion(String.valueOf(obj[obj.length - 2]));
                        if (isExcelExport) {
                            actualObj = getFormattedValue(PERCENTAGE_FORMAT, actualObj);
                            projObj = getFormattedValue(PERCENTAGE_FORMAT, projObj);
                        }
                        
                          Integer apIndicator = obj[NumericConstants.SEVEN] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.SEVEN])) : null;
                        if (apIndicator == null) {
                            customizeDiscountForDeductionInclusion(doubleProjectedAndHistoryCombinedUniqueList, discountDto);
                        } else {
                            discountProjectionSetTableValues(frequency, forecastConfigList, discountDto,
                                    doubleProjectedAndHistoryCombinedUniqueList, column, commonColumn, actualObj,
                                    projObj, actualAmountObj, projectedAmoutObj, actualRpObj, projectedRpObj,
                                    growthObj, apIndicator);
                        }

                        if (i == discountProjectionList.size() - 1) {
                            discountProjList.add(discountDto);
                        }
                    }
                }
            }
            LOGGER.debug(" projectionPeriodorder= {} " , projectionPeriodorder);
            LOGGER.debug(" year= {} " , year);
            LOGGER.debug(" refreshHierarchyNumbers= {} " , refreshHierarchyNumbers);
            LOGGER.debug(" isParent= {} " , isParent);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("Exit getDiscountProjection");
        return discountProjList;
    }
    
    public void customizeDiscountForDeductionInclusion(List doubleProjectedAndHistoryCombinedUniqueList, DiscountProjectionDTO discountDto) {
        for (Object commonColumn : doubleProjectedAndHistoryCombinedUniqueList) {
            discountDto.addStringProperties(commonColumn + ACTUAL_RATE, StringUtils.EMPTY);
            discountDto.addStringProperties(commonColumn + ACTUAL_AMOUNT, StringUtils.EMPTY);
            discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU,
                    StringUtils.EMPTY);
            discountDto.addStringProperties(commonColumn + PROJECTED_RATE, StringUtils.EMPTY);
            discountDto.addStringProperties(commonColumn + PROJECTED_AMOUNT, StringUtils.EMPTY);
            discountDto.addStringProperties(commonColumn + Constant.PROJECTEDRPU,
                    StringUtils.EMPTY);
            discountDto.addStringProperties(commonColumn + Constant.GROWTH, StringUtils.EMPTY);
        }

    }


    private void discountProjectionSetTableValues(String frequency, List<String> forecastConfigList,
            DiscountProjectionDTO discountDto, List doubleProjectedAndHistoryCombinedUniqueList, String column,
            String commonColumn, String actualObject, String projectedObject, String actualAmountObject,
            String projAmountObj, String actualRpObject, String projRpObject, String growthObject,
            Integer apIndicatorObject) {
        String projectedObjectNew = projectedObject;
        String projAmountObjNew = projAmountObj;
        String projRpObjectNew  = projRpObject;   
                
        if (doubleProjectedAndHistoryCombinedUniqueList.contains(commonColumn)) {
            if (apIndicatorObject == 0) {
                if (!Constant.NULL.equals(discountDto.getDeductionInclusion())) {
                    discountDto.addStringProperties(commonColumn + ACTUAL_RATE, actualObject);
                    discountDto.addStringProperties(commonColumn + ACTUAL_AMOUNT, actualAmountObject);
                    discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU, actualRpObject);
                } else {
                    discountDto.addStringProperties(commonColumn + ACTUAL_RATE, StringUtils.EMPTY);
                    discountDto.addStringProperties(commonColumn + ACTUAL_AMOUNT, StringUtils.EMPTY);
                    discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU,
                            StringUtils.EMPTY);
                }
            }

            if (!Constant.NULL.equals(discountDto.getDeductionInclusion())) {
                projectedObjectNew = CommonUtils.forecastConfigDataHide(frequency, forecastConfigList,
                        column, projectedObjectNew);
                projAmountObjNew = CommonUtils.forecastConfigDataHide(frequency, forecastConfigList,
                        column, projAmountObjNew);
                projRpObjectNew = CommonUtils.forecastConfigDataHide(frequency, forecastConfigList,
                        column, projRpObjectNew);
                discountDto.addStringProperties(commonColumn + PROJECTED_RATE, projectedObjectNew);
                discountDto.addStringProperties(commonColumn + PROJECTED_AMOUNT, projAmountObjNew);
                discountDto.addStringProperties(commonColumn + Constant.PROJECTEDRPU,
                        projRpObjectNew);
                discountDto.addStringProperties(commonColumn + Constant.GROWTH, growthObject);
            } else {
                discountDto.addStringProperties(commonColumn + PROJECTED_RATE, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + PROJECTED_AMOUNT, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.PROJECTEDRPU,
                        StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.GROWTH, StringUtils.EMPTY);

            }
        } else {
            if (apIndicatorObject == 0) {
                discountDto.addStringProperties(commonColumn + ACTUAL_RATE, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + ACTUAL_AMOUNT, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU, StringUtils.EMPTY);
            } else {
                discountDto.addStringProperties(commonColumn + PROJECTED_RATE, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + PROJECTED_AMOUNT, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.PROJECTEDRPU,
                        StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.GROWTH, StringUtils.EMPTY);
            }
        }
    }

    public static final String PROJECTED_AMOUNT = "ProjectedAmount";
    public static final String ACTUAL_AMOUNT = "ActualAmount";

    /**
     * To adjust the discount projection
     *
     * @param session
     * @return
     */
    public boolean adjustDiscountProjection(SessionDTO session, final String adjustmentType,
            final String adjustmentBasis, final String adjustmentValue, final String actualOrSalesUnits, List<String> historyPeriods) {
        List<String> inputList = new ArrayList<>();
        inputList.add(session.getFrequency());
        inputList.add(Constant.STRING_ONE.equals(actualOrSalesUnits) ? StringUtils.join(historyPeriods.iterator(), ",") : session.getActualAdjustmentPeriods());
        inputList.add(adjustmentBasis);
        inputList.add(adjustmentValue);
        inputList.add(adjustmentType);
        inputList.add(actualOrSalesUnits);
        inputList.add(ALL.equals(session.getDeductionInclusion()) ? null : session.getDeductionInclusion());
        inputList.add(Constant.STRING_ONE.equals(actualOrSalesUnits) ? StringUtils.join(historyPeriods.iterator(), ",") : session.getActualAdjustmentPeriods());
        com.stpl.app.utils.QueryUtils.updateAppDataUsingSessionTables(inputList, "discount-adjustment-query", session);
        new DataSelectionLogic().callViewInsertProceduresThread(session, Constant.DISCOUNT3,"","","");
        return true;
    }
 public boolean adjustDiscountProjectionValidation(ProjectionSelectionDTO projectionSelectionDTO) {
        try {
            String query = SQlUtil.getQuery("discount-adjustment-query-Validation");
            List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
            return list.get(0) != null ? (Integer.parseInt(String.valueOf(list.get(0))) > 1) : false;
        } catch (NumberFormatException e) {
             LOGGER.error(e.getMessage());
        }
        return false;
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
        String baselineIndicator;
        String selectedPeriods;
        String baselinePeriods = "";

        if ("Historical % of Business".equals(allocationMethodology)) {
            baselineIndicator = "H";
        } else {
            baselineIndicator = "P";
        }

         for (Map.Entry<String, Map<String, List<String>>> discountName : periodsMap.entrySet()) {
            baselinePeriodsList = discountName.getValue().get(baselineIndicator);
            selectedPeriodsList = discountName.getValue().get("P");

            baselinePeriods = CommonUtils.collectionToStringMethod(baselinePeriodsList, false, true);
            selectedPeriods = CommonUtils.collectionToStringMethod(selectedPeriodsList, false, true);

            baselinePeriods = baselinePeriods.replace(", ", ",");
            selectedPeriods = selectedPeriods.replace(", ", ",");

            if (frequency.equals(MONTHLY.getConstant())) {
                baselinePeriods = CommonUtils.replaceShortMonthForMonth(baselinePeriods);
                selectedPeriods = CommonUtils.replaceShortMonthForMonth(selectedPeriods);
                LOGGER.debug(" baselinePeriods {} , baselinePeriods{} " , baselinePeriods, selectedPeriods);
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
    public int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo, String hierarchyIndicator,
            boolean isCustomView, List<String> customViewDetails, boolean isProgram, List<String> discountNamesList, String discountHierarchy) {
        session.setSelectedRsForCustom(queryBuilderAndExecutor.getRsContractSid(session, hierarchyNo, hierarchyIndicator,
                isCustomView, customViewDetails, discountNamesList));
        return queryBuilderAndExecutor.updateCheckRecord(session, checkValue, hierarchyNo, hierarchyIndicator,
                isCustomView, customViewDetails, isProgram, discountNamesList, discountHierarchy);
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
    public void massUpdate(SessionDTO session, ProjectionSelectionDTO projectionSelection, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
            List<String> selectedDiscounts, boolean isProgram, List<String[]> massUpdateData, boolean isCustomHierarchy) {
        queryBuilderAndExecutor.massUpdate(session, projectionSelection, startAndEndPeriods, selectedField, fieldValue, selectedDiscounts, isProgram, massUpdateData, isCustomHierarchy);
    }

    /**
     *
     * @param session
     * @param hierarchyNo
     * @param fieldValue
     * @return
     */
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String fieldValue, boolean isProgram, List<String> discountList, String deductionHierarchy) {

        return queryBuilderAndExecutor.saveGroupValues(session, hierarchyNo, fieldValue, isProgram, discountList, deductionHierarchy);
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
                String deductionLevelNo;
                String customerHierarchyNo;
                String productHierachyNo;
                String deductionHierachyNo;
                String discountName;
                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = StringUtils.EMPTY + dto.getTreeLevelNo();
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerLevelNo = StringUtils.EMPTY + dto.getTreeLevelNo();
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = StringUtils.EMPTY + dto.getTreeLevelNo();
                    deductionLevelNo = Constant.PERCENT;
                }
                customerHierarchyNo = dto.getCustomerHierarchyNo();
                productHierachyNo = dto.getProductHierarchyNo();
                deductionHierachyNo = dto.getDeductionHierarchyNo();

                LOGGER.debug(" SaveDTO - Custom hierarchy --- \n customId= {} " , customId);
                LOGGER.debug(" SaveDTO - Hierarchy indicator= {} " , dto.getHierarchyIndicator());
                LOGGER.debug(" SaveDTO - customerLevelNo= {} " , customerLevelNo);
                LOGGER.debug(" SaveDTO - customerHierarchyNo= {} " , customerHierarchyNo);
                LOGGER.debug(" SaveDTO - productLevelNo= {} " , productLevelNo);
                LOGGER.debug(" SaveDTO - productHierarchyNo= {} " , productHierachyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierachyNo);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
                customViewDetails.add(dto.getRefreshName());
                customViewDetails.add(session.getDedRelationshipBuilderSid());
                customViewDetails.add(deductionHierachyNo);
                customViewDetails.add(deductionLevelNo);
                try {
                    if (isCustomHierarchy && CommonUtil.isValueEligibleForLoading()) {
                        discountName = CommonUtils.collectionToStringMethod(session.getSelectedRsForCustom(), false);
                    } else {
                        discountName = isProgram ? dto.getDiscountName().contains("~") ? dto.getDiscountName().split("~")[1] : dto.getDiscountName() : dto.getDiscountName().contains("~") ? dto.getDiscountName().split("~")[0] : dto.getDiscountName();
                    }

                    boolean saveSuccess = queryBuilderAndExecutor.saveDiscountProjectionListView(session, frequency, dto.getPeriodNo(), dto.getYear(), dto.getHierarchyIndicator(),
                            dto.getHirarechyNo(), discountName, String.valueOf(dto.getValue()), isProgram, isCustomHierarchy, customViewDetails);

                    if (!saveSuccess) {
                        break;
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
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
        DataSource datasource = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
        } catch (NamingException namingEx) {
            LOGGER.error(namingEx.getMessage());
        }
        if (datasource != null) {
            try (Connection connection = datasource.getConnection();
                    CallableStatement statement = connection.prepareCall("{call PRC_NM_DISCOUNT_INSERT (?,?,?)}")) {
                statement.setInt(1, session.getProjectionId());
                statement.setInt(NumericConstants.TWO, Integer.parseInt(session.getUserId()));
                statement.setString(NumericConstants.THREE, session.getSessionId());
                statement.execute();

            } catch (NumberFormatException | SQLException ex) {
                LOGGER.error(ex.getMessage());
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
     * @param format
     * @param value
     * @return
     */
    public String getFormattedValue(DecimalFormat format, String value) {
        String valueDpLogic = value;
        if (valueDpLogic.contains(Constant.NULL) || valueDpLogic.equals("-")) {
            valueDpLogic = DASH;
        } else {
            Double newValue = Double.valueOf(valueDpLogic);
            if (format.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            valueDpLogic = format.format(newValue);
        }
        return valueDpLogic;
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
        DataSource datasource = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
        } catch (NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
        if (datasource != null) {
            try (Connection connection = datasource.getConnection();
                    CallableStatement statement = connection.prepareCall("{call PRC_NM_DISCOUNT_INSERT(?,?,?)}")) {
                LOGGER.debug(" Executing Discount Insert procedure ");
                statement.setInt(1, projectionId);
                statement.setInt(NumericConstants.TWO, Integer.parseInt(userId));
                statement.setInt(NumericConstants.THREE, Integer.parseInt(sessionId));
                statement.execute();
            } catch (NumberFormatException | SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return true;
    }

    public void checkClearAll(SessionDTO session, String userGroup, boolean checkClear) {
        queryBuilderAndExecutor.checkClearAll(session, userGroup, checkClear);
    }

    public boolean isAnyRecordChecked(SessionDTO session, boolean isCustomHierarchy) {
        int count = queryBuilderAndExecutor.getCheckedRecordCount(session);
        if (count != 0) {
            if (count == -1) {
                LOGGER.error("Check Count is not retrieved properly{}", isCustomHierarchy);
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
            String discountIds;
            StringBuilder discountIdsBuilder = new StringBuilder();
            String query;
            for (String discountName : selectedDiscount) {
                discountIdsBuilder.append('\'').append(discountName ).append( "',");
            }
            discountIds = discountIdsBuilder.toString();
            discountIds = discountIds.substring(0, discountIds.length() - 1);
            query = "UPDATE ST_NM_DISCOUNT_PROJ_MASTER \n"
                    + "SET CHECK_RECORD = " + (isCheck ? Constant.STRING_ONE : DASH) + '\n';
            if (session.getCustomId() != 0) {
                String discountId = CommonUtils.collectionToStringMethod(session.getSelectedRsForCustom(), false);
                if (isCheckList) {
                    query += " WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT WHERE RS_CONTRACT_SID NOT IN (" + discountId + "))";
                } else {
                    query += " WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT WHERE RS_CONTRACT_SID IN (" + discountId + "))";
                }
            } else if (isCheckList) {
                query += isProgram ? " WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT WHERE RS_NAME NOT IN (" + discountIds + "))"
                        : " WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT RC JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE WHERE HT.DESCRIPTION NOT IN (" + discountIds + "))";
            } else {
                query += isProgram ? "WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT WHERE RS_NAME IN (" + discountIds + "))"
                        : " WHERE RS_CONTRACT_SID IN (SELECT RS_CONTRACT_SID FROM RS_CONTRACT RC JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE WHERE HT.DESCRIPTION NOT IN (" + discountIds + ")) ";
            }

            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
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
            List<Object[]> rsContList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(rsContractQuery);
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
        } catch (NumberFormatException ex) {

            LOGGER.error(ex.getMessage());
            return Collections.emptyList();

        }
    }

    /**
     * /**
     * The Discount Projection procedure for calculation and adjustment
     *
     * @param session
     * @return
     */
    public boolean callDPProcedure(SessionDTO session, ProjectionSelectionDTO projectionSelection) {
        LOGGER.debug("Entering callDPProcedure  : PRC_NM_DISCOUNT_PROJECTION");
        LOGGER.debug("Projection ID= {}            " , session.getProjectionId());
        LOGGER.debug("Frequency= {}                " , String.valueOf(session.getFrequency()));
        LOGGER.debug("UserId= {}                   " , session.getUserId());
        LOGGER.debug("Session Id= {}               " , session.getSessionId());
        LOGGER.debug("Forecast Start= {}           " , projectionSelection.getFromDateDdlb());
        LOGGER.debug("Forecast End= {}             " , projectionSelection.getToDateDdlb());
        LOGGER.debug("Calc Based= {}               " , projectionSelection.getCalcBased());
        LOGGER.debug("DEDUCTION= {}                " , ((projectionSelection.getSessionDTO().getDeductionInclusion() == null || ALL.equals(projectionSelection.getSessionDTO().getDeductionInclusion())) ? null : projectionSelection.getSessionDTO().getDeductionInclusion()));

        DataSource datasource = null;

        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
        } catch (NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
        if (datasource != null) {
            try (Connection connection = datasource.getConnection();
                    CallableStatement statement = connection.prepareCall("{call PRC_NM_DISCOUNT_PROJECTION (?,?,?,?,?,?,?,?)}")) {
                statement.setInt(1, session.getProjectionId());
                statement.setString(NumericConstants.TWO, String.valueOf(session.getFrequency()));
                statement.setInt(NumericConstants.THREE, Integer.parseInt(session.getUserId()));
                statement.setString(NumericConstants.FOUR, session.getSessionId());
                statement.setString(NumericConstants.FIVE, projectionSelection.getFromDateDdlb());
                statement.setString(NumericConstants.SIX, projectionSelection.getToDateDdlb());
                statement.setString(NumericConstants.SEVEN, projectionSelection.getCalcBased());
                statement.setString(NumericConstants.EIGHT, (projectionSelection.getSessionDTO().getDeductionInclusion() == null || projectionSelection.getSessionDTO().getDeductionInclusion().equals(ALL)) ? null : projectionSelection.getSessionDTO().getDeductionInclusion());
                statement.execute();
            } catch (NumberFormatException | SQLException ex) {
                LOGGER.debug(ex.getMessage());
            }
        }
        return false;
    }

    public String getPeriodSid(String period, String fre, String order) {
        List periodSid = (List) dao.executeSelectQuery(utils.periodQuery(period, fre, order));
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
    public void calcDataUpdate(SessionDTO session, ProjectionSelectionDTO projectionSelection, Map<String, Map<String, List<String>>> selectedPeriods, List selectedDiscount, Boolean isProgram, Boolean isCustom) {
        projectionSelection.setSessionDTO(session);
        utils.updateDiscProjMasterCalc(projectionSelection, selectedPeriods, selectedDiscount, isProgram, isCustom);

    }

    public List<DiscountProjectionDTO> getProjectionTotal(String userId, String sesionId, String view, String frequency, DiscountProjectionDTO totalDTO, List<Integer> startAndEndPeriods, String detailsValue) {
        List<DiscountProjectionDTO> discountProjList = new ArrayList<>();
        totalDTO = new DiscountProjectionDTO();
        discountProjList.add(getTotalProjectionList(userId, sesionId, view, frequency, totalDTO, startAndEndPeriods, detailsValue));
        return discountProjList;
    }

    public DiscountProjectionDTO getTotalProjectionList(String userId, String sesionId, String view, String frequency, DiscountProjectionDTO totalDTO, List<Integer> startAndEndPeriods, String detailsValue) {
        LOGGER.debug("Details Id:==========>= {}" , detailsValue);
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

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);

                if (PIVOT_LABEL.equalsIgnoreCase(view)) {
                    totalDTO.addStringProperties(PAYMENT1 + Constant.ACTUALS, getFormattedValue(AMOUNT, String.valueOf(obj[0])));
                    totalDTO.addStringProperties(PAYMENT1 + Constant.PROJECTIONS, getFormattedValue(AMOUNT, String.valueOf(obj[1])));
                } else {
                    String discountName = Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWO])) ? "" : String.valueOf(obj[NumericConstants.TWO]);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequency.equals(QUARTERLY.getConstant())) {
                        commonColumn = Constant.Q_SMALL + obj[1] + obj[0];
                    } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
                        commonColumn = Constant.S_SMALL + obj[1] + obj[0];
                    } else if (frequency.equals(MONTHLY.getConstant())) {
                        String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[1]) - 1);
                        commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[0];
                    } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    }
                    commonColumn = discountName.replaceAll(" ", StringUtils.EMPTY) + commonColumn;
                    String actualAmtObj = Constant.NULL.equals(String.valueOf(obj[NumericConstants.THREE])) ? DASH : String.valueOf(obj[NumericConstants.THREE]);
                    String projeAmoutObj = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOUR])) ? DASH : String.valueOf(obj[NumericConstants.FOUR]);
                    totalDTO.addStringProperties(commonColumn + ACTUAL_AMOUNT, getFormattedValue(AMOUNT, actualAmtObj));
                    totalDTO.addStringProperties(commonColumn + PROJECTED_AMOUNT, getFormattedValue(AMOUNT, projeAmoutObj));
                }
            }
        }

        return totalDTO;
    }

    public List<DiscountProjectionDTO> getPivotView(int start, int offset, List<DiscountProjectionDTO> pivotList, List<DiscountProjectionDTO> discountProjList, int treeLevelNo, int levelNo, List<String> customViewDetails, SessionDTO session, String levelId, List discountProjectionList, DiscountProjectionDTO discountDto, String discountName, boolean isParentChecked, String hierarchyIndicator, String frequency, boolean isCustom, List<String> ahPeriodList, Map<String, String> hashMapValues, ProjectionSelectionDTO projectionSelection) {
        try {
            int neededRecord = offset;
            int started = start;
            int mayBeAdded = 0;
            String discountNamePivot = discountName;
            String levelIdPivot = levelId;
            GtnSmallHashMap ccpCountForDiscount = new GtnSmallHashMap();
            if (!pivotList.isEmpty()) {
                getParentGroupValue(frequency, pivotList, discountDto, discountProjList, ahPeriodList, hashMapValues);
            }
            if (discountProjectionList != null && !discountProjectionList.isEmpty()) {
                for (int i = 0; i < discountProjectionList.size(); i++) {
                    final Object[] obj = (Object[]) discountProjectionList.get(i);

                    if (!levelIdPivot.equals(String.valueOf(obj[NumericConstants.THREE]))) {
                        if (i != 0) {
                            discountProjList.add(discountDto);
                        }
                        discountDto = new DiscountProjectionDTO();
                        discountNamePivot = StringUtils.EMPTY;
                        levelIdPivot = String.valueOf(obj[NumericConstants.THREE]);
                        discountDto.setHierarchyNo(String.valueOf(obj[NumericConstants.TWO]));
                        discountDto.setLevelName(CommonUtil.getDisplayFormattedName(discountDto.getHierarchyNo(), session.getHierarchyLevelDetails(), projectionSelection.getDisplayFormat()));
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
                    if (!discountNamePivot.equals(String.valueOf(obj[NumericConstants.SEVEN]))) {
                        discountNamePivot = String.valueOf(obj[NumericConstants.SEVEN]);
                        ccpCountForDiscount.put(discountNamePivot, Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                        discountDto.setCcpCount(discountDto.getCcpCount() + Integer.parseInt(String.valueOf(obj[NumericConstants.TWELVE])));
                        discountDto.setCcpCountForDiscount(ccpCountForDiscount);
                        discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.parseInt(String.valueOf(obj[NumericConstants.THIRTEEN])));
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
            for (int k = mayBeAddedRecord; k < discountProjList.size() && neededRecord > 0; neededRecord--, k++) {
                discountProj.add(discountProjList.get(k));
                started++;
            }
            return discountProj;
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void getParentGroupValue(String frequency, List discountProjectionList, DiscountProjectionDTO discountDto,
            List<DiscountProjectionDTO> discountProjList, List<String> ahPeriodList, Map<String, String> hashMapValues) {
        try {
            Map<String, Object> mapValue = new HashMap<>();
            List<String> periodList = new ArrayList<>();
            if (discountProjectionList != null && !discountProjectionList.isEmpty()) {
                for (int i = 0; i < discountProjectionList.size(); i++) {
                    final Object[] obj = (Object[]) discountProjectionList.get(i);
                    String headerColumn = StringUtils.EMPTY;
                    if (frequency.equals(QUARTERLY.getConstant())) {
                        headerColumn = Constant.Q_SMALL + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
                        headerColumn = Constant.S_SMALL + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequency.equals(MONTHLY.getConstant())) {
                        String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[NumericConstants.SIX]) - 1);
                        headerColumn = monthName + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
                        headerColumn = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    }
                    if (!periodList.contains(headerColumn)) {
                        periodList.add(headerColumn);
                    }

                    String actualAmtParentGroupValue = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]);
                    String projAmtParentGroupValue = Constant.NULL.equals(String.valueOf(obj[NumericConstants.SIXTEEN])) ? DASH : String.valueOf(obj[NumericConstants.SIXTEEN]);
                    if (mapValue.containsKey(headerColumn)) {
                        discountDto = (DiscountProjectionDTO) mapValue.get(headerColumn);
                        int apIndicatorParentGroupValue = Integer.parseInt(String.valueOf(obj[NumericConstants.TEN]));
                        if (apIndicatorParentGroupValue == 0) {
                            discountDto.addStringProperties(PAYMENT1 + Constant.ACTUALS, getFormattedValue(AMOUNT, actualAmtParentGroupValue));
                        }
                        discountDto.addStringProperties(PAYMENT1 + Constant.PROJECTIONS, getFormattedValue(AMOUNT, projAmtParentGroupValue));
                    } else {
                        discountDto = new DiscountProjectionDTO();
                        int apIndicParentGroupValue = Integer.parseInt(String.valueOf(obj[NumericConstants.TEN]));
                        if (apIndicParentGroupValue == 0) {
                            discountDto.addStringProperties(PAYMENT1 + Constant.ACTUALS, getFormattedValue(AMOUNT, actualAmtParentGroupValue));
                        }
                        discountDto.addStringProperties(PAYMENT1 + Constant.PROJECTIONS, getFormattedValue(AMOUNT, projAmtParentGroupValue));
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

        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getDetailsId(SessionDTO session) {
        String detailsSid = StringUtils.EMPTY;
        StringBuilder idStringBuilder = new StringBuilder();
        try {
            String query = "Select Distinct PROJECTION_DETAILS_SID from ST_DISC_ALTERNATE_HIST_ALLOCATION";
            List list;
            list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            for (int i = 0; i < list.size(); i++) {
                Object obj = (Object) list.get(i);
                if (!StringUtils.EMPTY.equalsIgnoreCase(String.valueOf(obj)) && !"null".equalsIgnoreCase(String.valueOf(obj))) {
                    idStringBuilder.append(StringUtils.EMPTY ).append( Integer.valueOf(String.valueOf(obj)) ).append( ',');
                }
            }
            detailsSid = idStringBuilder.toString();
            detailsSid = detailsSid.substring(0, detailsSid.length() - 1);
            return detailsSid;
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
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
            boolean isProgram, final List<String> discountList, final String userGroup, final ProjectionSelectionDTO projselection) {
        String query;
        if (CommonUtil.isValueEligibleForLoading()) {
            query = queryBuilderAndExecutor.getDiscountCountQueryForAllLevel(sessionDTO, hierarchyNo, levelNo, hierarchyIndicator, discountList, userGroup);
        } else {
            query = queryBuilderAndExecutor.getDiscountCountQuery(sessionDTO, hierarchyNo, levelNo, hierarchyIndicator, isProgram, discountList, userGroup);
        }
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return Integer.parseInt(list.get(0).toString());
        }

        return 0;
    }

    public int getDiscountCustomCount(final SessionDTO sessionDTO, final String hierarchyIndicator, final int levelNo,final String userGroup,List<String> customViewDetails,boolean isCustom,List<String> customDetailsList) {
        String query = queryBuilderAndExecutor.getDiscountCustomQueryCount(sessionDTO, hierarchyIndicator, levelNo,userGroup,customViewDetails,isCustom,customDetailsList);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        if (list != null) {
            return Integer.parseInt(list.get(0).toString());
        }
        return 0;
    }
    
     public Map<Integer, List> configureVisibleColumnMapsForExcel(Map<Integer, List> sourceHeaderMap) {

        final Map<Integer, List> finalMap = new HashMap<>();

        for (Map.Entry<Integer, List> key : sourceHeaderMap.entrySet()) {

            List list = key.getValue();
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

    public List<String> getRsAllList(ProjectionSelectionDTO projdto) {
        List<String> rebateList = new ArrayList<>();
        try {
            String queryAllRebate;

            if (Constant.PROGRAM_CATEGORY_LABEL.equals(projdto.getDiscountLevel())) {
                queryAllRebate = "SELECT DISTINCT DPM.PRICE_GROUP_TYPE FROM  ST_NM_DISCOUNT_PROJ_MASTER DPM JOIN RS_CONTRACT RS ON DPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID"
                        + " JOIN ST_CCP_HIERARCHY CCP ON CCP.CCP_DETAILS_SID=DPM.CCP_DETAILS_SID ";
            } else {
                queryAllRebate = "SELECT DISTINCT RS.RS_NAME,RS.RS_CONTRACT_SID FROM  ST_NM_DISCOUNT_PROJ_MASTER DPM JOIN RS_CONTRACT RS ON DPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID"
                        + " JOIN ST_CCP_HIERARCHY CCP ON CCP.CCP_DETAILS_SID=DPM.CCP_DETAILS_SID ";
            }
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(queryAllRebate, projdto.getSessionDTO().getCurrentTableNames()));
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    String val;
                    if (list.get(i) instanceof String) {
                        val = list.get(i).toString();
                    } else {
                        val = (((Object[]) list.get(i))[0]).toString();
                    }
                    rebateList.add(val);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rebateList;
    }

    public String getLevelName(SessionDTO session, String relLevelValue) {
        String queryAllRebate;
        List inputList = new ArrayList<>();
        inputList.add(session.getDedRelationshipBuilderSid());
        inputList.add(relLevelValue);
        queryAllRebate = QueryUtils.getQuery(inputList, "GET_LEVEL_NAME");
        List<String> list = HelperTableLocalServiceUtil.executeSelectQuery(queryAllRebate);
        return list.get(0);
    }
    

    public String getFormatedHierarchyNo(Set<String> list) {
        StringBuilder hierarchyNo=new StringBuilder();
        for (String string : list) {
            hierarchyNo.append("('").append(string).append("'),");
        }
        hierarchyNo=hierarchyNo.replace(hierarchyNo.lastIndexOf(","), hierarchyNo.length(),"");
        return hierarchyNo.toString();
    }
    
    public void updateCheckRecordForAdjust(List discountList,Set<String> hierarchyList,SessionDTO sessionDto,String hierarchyIndicator){
    String updateQuery=SQlUtil.getQuery("UPDATE_ALL_LEVEL_CHECKRECORD_ADJUST");
    updateQuery= updateQuery.replace("@BUILDERSID", sessionDto.getDedRelationshipBuilderSid()).replace("@LEVNO", String.valueOf(sessionDto.getSelectedDeductionLevelNo()));
    updateQuery= updateQuery.replace("@RELATIONSIDS", StringUtils.join(discountList,",")).replace("@HIERARCHY_NO", getFormatedHierarchyNo(hierarchyList));
    updateQuery= updateQuery.replace("@HIERARCHY_COLUMN", hierarchyIndicator.equals("C")?"CCPH.CUST_HIERARCHY_NO":"CCPH.PROD_HIERARCHY_NO");
    HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery,sessionDto.getCurrentTableNames()));
    }
    
    public void updateAllToZero(SessionDTO sessionDto){
    String updateZeroQuery="UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET CHECK_RECORD=0";
    HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateZeroQuery,sessionDto.getCurrentTableNames()));
    }
    
    public List getDoubleProjectedAndHistoryCombinedUniqueList(CustomTableHeaderDTO rightDto) {
        List doubleProjectedColumnList = rightDto.getDoubleProjectedColumns();
         List doubleHistoryColumnList = rightDto.getDoubleHistoryColumns();
         List doubleProjectedAndHistoryCombinedList = ListUtils.union(doubleProjectedColumnList, doubleHistoryColumnList);
         Set doubleProjectedAndHistoryCombinedSet = new LinkedHashSet(doubleProjectedAndHistoryCombinedList);
         return new ArrayList(doubleProjectedAndHistoryCombinedSet);
     }
}
