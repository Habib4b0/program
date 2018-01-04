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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

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
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(DiscountProjectionLogic.class);
    public static final String JBOSS_DATA_POOL = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private final QueryUtils utils = new QueryUtils();
    private String baselinePeriods = "";
    private String selectedPeriods = "";

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

        List doubleProjectedColumnList = rightDto.getDoubleProjectedColumns();
        List doubleHistoryColumnList = rightDto.getDoubleHistoryColumns();
        List doubleProjectedAndHistoryCombinedList = ListUtils.union(doubleProjectedColumnList, doubleHistoryColumnList);
        Set doubleProjectedAndHistoryCombinedSet = new LinkedHashSet(doubleProjectedAndHistoryCombinedList);
        List doubleProjectedAndHistoryCombinedUniqueList = new ArrayList(doubleProjectedAndHistoryCombinedSet);

        levelNo = Integer.valueOf(String.valueOf(detailsList.get(0)));
        hierarchyNo = String.valueOf(detailsList.get(1));
        treeLevelNo = Integer.valueOf(String.valueOf(detailsList.get(NumericConstants.TWO)));
        LOGGER.debug(" isCount " + isCount);
        LOGGER.debug(" level no " + levelNo);
        LOGGER.debug(" customTreeLevelNo " + treeLevelNo);
        LOGGER.info(" Hierarchy No " + hierarchyNo);
        LOGGER.debug(" Hierarchy Indicator " + hierarchyIndicator);
        LOGGER.debug("Start:" + start);
        LOGGER.debug(" history " + history);
        LOGGER.debug(" rightDto " + rightDto);
        LOGGER.debug(" relationshipBuilderSid " + relationshipBuilderSid);
        LOGGER.debug(" isRefresh " + isRefresh);
        LOGGER.debug("Offset:" + offset);
        List discountProjectionList = Collections.EMPTY_LIST;
        if (levelNo != 0) {
            if (CommonUtil.isValueEligibleForLoading() && !isCustom) {
                discountProjectionList = queryBuilderAndExecutor.getDiscountProjectionLastLevel(frequency, discountList, session, hierarchyNo,
                        hierarchyIndicator, levelNo, isCustom, customViewDetails, treeLevelNo, start, offset, userGroup, projectionSelection);
            } else {
                discountProjectionList = queryBuilderAndExecutor.getDiscountProjection(isProgram, frequency, discountList, session, hierarchyNo,
                        hierarchyIndicator, levelNo, isCustom, customViewDetails, treeLevelNo, start, offset, userGroup);
            }
        }
        //To Return the list as it is for the getCount method of Pagination table
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
                                relValue = discountDto.getHierarchyNo().contains("~") ? discountDto.getHierarchyNo().substring(discountDto.getHierarchyNo().lastIndexOf("~") + 1) : discountDto.getHierarchyNo();
                            } else {
                                relValue = discountDto.getHierarchyNo();
                            }
                            discountDto.setLevelName(CommonUtil.getDisplayFormattedName(relValue, hierarchyIndicator, session.getHierarchyLevelDetails(), session, projectionSelection.getDisplayFormat()));
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
                                discountDto.setTreeLevelNo(Integer.valueOf(session.getHierarchyLevelDetails().get(discountDto.getHierarchyNo()).get(NumericConstants.TWO).toString()));
                            }
                            String level = "";
                            if (!Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)) {
                                level = String.valueOf(session.getHierarchyLevelDetails().get(discountDto.getHierarchyNo()).get(1));
                            } else {
                                level = "";
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

                        // To handle count for various discounts
                        if (!isCustom) {
                            if (!discountName.equals(String.valueOf(obj[NumericConstants.FOUR]))) {
                                discountName = String.valueOf(obj[NumericConstants.FOUR]);

                                if (obj[NumericConstants.NINE] != null) {
                                    ccpCountForDiscount.put(discountName, Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                                    discountDto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                                    discountDto.setCcpCountForDiscount(ccpCountForDiscount);
                                    discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.valueOf(String.valueOf(obj[NumericConstants.TEN])));
                                }
                            }
                        }else{
                             if (obj[NumericConstants.NINE] != null) {
                                 discountDto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                                 discountDto.setUncheckCount(discountDto.getUncheckCount() + Integer.valueOf(String.valueOf(obj[NumericConstants.TEN])));
                             }
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
                        String commonColumn = StringUtils.EMPTY;
                        if (!isCustom) {
                            commonColumn = discountName.replaceAll(" ", StringUtils.EMPTY) + column;
                        } else {
                            commonColumn = column;
                        }

                        String ACTUAL_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FIVE])) ? DASH : String.valueOf(obj[NumericConstants.FIVE]);
                        String PROJECTED_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.SIX])) ? DASH : String.valueOf(obj[NumericConstants.SIX]);
                        String ACTUAL_CONVERTED_AMT = CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.ELEVEN], false);
                        String ACTUAL_AMT_OBJ = Constant.NULL.equals(ACTUAL_CONVERTED_AMT) ? DASH : ACTUAL_CONVERTED_AMT;
                        ACTUAL_CONVERTED_AMT = CommonUtil.getConversionFormattedValue(projectionSelection, obj[NumericConstants.THIRTEEN], false);
                        String PROJECTED_AMT_OBJ = Constant.NULL.equals(ACTUAL_CONVERTED_AMT) ? DASH : ACTUAL_CONVERTED_AMT;
                        String ACTUAL_RP_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWELVE])) ? DASH : String.valueOf(obj[NumericConstants.TWELVE]);
                        String PROJECTED_RP_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]);
                        String GROWTH_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FIFTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FIFTEEN]);
                        discountDto.setDeductionInclusion(String.valueOf(obj[obj.length - 1]));
                        if (isExcelExport) {
                            ACTUAL_OBJ = getFormattedValue(PERCENTAGE_FORMAT, ACTUAL_OBJ);
                            PROJECTED_OBJ = getFormattedValue(PERCENTAGE_FORMAT, PROJECTED_OBJ);
                        }
                        int AP_INDICATOR = Integer.valueOf(String.valueOf(obj[NumericConstants.SEVEN]));

                        discountProjectionSetTableValues(frequency, forecastConfigList, discountDto,
                                doubleProjectedAndHistoryCombinedUniqueList, column, commonColumn, ACTUAL_OBJ,
                                PROJECTED_OBJ, ACTUAL_AMT_OBJ, PROJECTED_AMT_OBJ, ACTUAL_RP_OBJ, PROJECTED_RP_OBJ,
                                GROWTH_OBJ, AP_INDICATOR);

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
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Exit getDiscountProjection");
        return discountProjList;
    }

    private void discountProjectionSetTableValues(String frequency, List<String> forecastConfigList,
            DiscountProjectionDTO discountDto, List doubleProjectedAndHistoryCombinedUniqueList, String column,
            String commonColumn, String ACTUAL_OBJ, String PROJECTED_OBJ, String ACTUAL_AMT_OBJ,
            String PROJ_AMT_OBJ, String ACTUAL_RP_OBJ, String PROJ_RP_OBJ, String GROWTH_OBJ,
            int AP_INDICATOR) {
        if (doubleProjectedAndHistoryCombinedUniqueList.contains(commonColumn)) {
            if (AP_INDICATOR == 0) {
                if (!Constant.NULL.equals(discountDto.getDeductionInclusion())) {
                    discountDto.addStringProperties(commonColumn + "ActualRate", ACTUAL_OBJ);
                    discountDto.addStringProperties(commonColumn + ACTUAL_AMOUNT, ACTUAL_AMT_OBJ);
                    discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU, ACTUAL_RP_OBJ);
                } else {
                    discountDto.addStringProperties(commonColumn + "ActualRate", StringUtils.EMPTY);
                    discountDto.addStringProperties(commonColumn + ACTUAL_AMOUNT, StringUtils.EMPTY);
                    discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU,
                            StringUtils.EMPTY);
                }
            }

            if (!Constant.NULL.equals(discountDto.getDeductionInclusion())) {
                PROJECTED_OBJ = CommonUtils.forecastConfigDataHide(frequency, forecastConfigList,
                        column, PROJECTED_OBJ);
                PROJ_AMT_OBJ = CommonUtils.forecastConfigDataHide(frequency, forecastConfigList,
                        column, PROJ_AMT_OBJ);
                PROJ_RP_OBJ = CommonUtils.forecastConfigDataHide(frequency, forecastConfigList,
                        column, PROJ_RP_OBJ);
                discountDto.addStringProperties(commonColumn + "ProjectedRate", PROJECTED_OBJ);
                discountDto.addStringProperties(commonColumn + PROJECTED_AMOUNT, PROJ_AMT_OBJ);
                discountDto.addStringProperties(commonColumn + Constant.PROJECTEDRPU,
                        PROJ_RP_OBJ);
                discountDto.addStringProperties(commonColumn + Constant.GROWTH, GROWTH_OBJ);
            } else {
                discountDto.addStringProperties(commonColumn + "ProjectedRate", StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + PROJECTED_AMOUNT, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.PROJECTEDRPU,
                        StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.GROWTH, StringUtils.EMPTY);

            }
        } else {
            if (AP_INDICATOR == 0) {
                discountDto.addStringProperties(commonColumn + "ActualRate", StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + ACTUAL_AMOUNT, StringUtils.EMPTY);
                discountDto.addStringProperties(commonColumn + Constant.ACTUALRPU, StringUtils.EMPTY);
            } else {
                discountDto.addStringProperties(commonColumn + "ProjectedRate", StringUtils.EMPTY);
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
        inputList.add(selectedPeriods);
        inputList.add(adjustmentBasis);
        inputList.add(adjustmentValue);
        inputList.add(adjustmentType);
        inputList.add(actualOrSalesUnits);
        inputList.add(ALL.equals(session.getDeductionInclusion()) ? null : session.getDeductionInclusion());
        inputList.add(StringUtils.join(historyPeriods.iterator(), ","));
        com.stpl.app.utils.QueryUtils.updateAppDataUsingSessionTables(inputList, "discount-adjustment-query", session);
        return true;
    }
 public boolean adjustDiscountProjectionValidation(ProjectionSelectionDTO projectionSelectionDTO) {
        try {
            String query = SQlUtil.getQuery("discount-adjustment-query-Validation");
            List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
            return list.get(0) != null ? (Integer.parseInt(String.valueOf(list.get(0))) > 1) : false;
        } catch (Exception e) {
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
    public int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo, String hierarchyIndicator,
            boolean isCustomView, List<String> customViewDetails, boolean isProgram, List<String> discountNamesList, String discountHierarchy) {
        session.setSelectedRsForCustom(queryBuilderAndExecutor.getRsContractSid(session, checkValue, hierarchyNo, hierarchyIndicator,
                isCustomView, customViewDetails, isProgram, discountNamesList));
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
    public void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
            List<String> selectedDiscounts, boolean isProgram, List<String[]> massUpdateData, List<String> selectedPeriods, boolean isCustomHierarchy) {
        queryBuilderAndExecutor.massUpdate(session, frequency, startAndEndPeriods, selectedField, fieldValue, selectedDiscounts, isProgram, massUpdateData, selectedPeriods, isCustomHierarchy);
    }

    /**
     *
     * @param session
     * @param hierarchyNo
     * @param fieldValue
     * @return
     */
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String fieldValue, boolean isProgram, List<String> discountList, String deductionHierarchy, String hierindicator) {

        return queryBuilderAndExecutor.saveGroupValues(session, hierarchyNo, fieldValue, isProgram, discountList, deductionHierarchy, hierindicator);
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
                customViewDetails.add(session.getDedRelationshipBuilderSid());
                customViewDetails.add(deductionHierachyNo);
                customViewDetails.add(deductionLevelNo);
                try {
                    if (isCustomHierarchy && CommonUtil.isValueEligibleForLoading()) {
                        discountName = CommonUtils.CollectionToString(session.getSelectedRsForCustom(), false);
                    } else {
                        discountName = isProgram ? dto.getDiscountName().contains("~") ? dto.getDiscountName().split("~")[1] : dto.getDiscountName() : dto.getDiscountName().contains("~") ? dto.getDiscountName().split("~")[0] : dto.getDiscountName();
                    }

                    boolean saveSuccess = queryBuilderAndExecutor.saveDiscountProjectionListView(session, frequency, dto.getPeriodNo(), dto.getYear(), dto.getHierarchyIndicator(),
                            dto.getHirarechyNo(), discountName, String.valueOf(dto.getValue()), isProgram, isCustomHierarchy, customViewDetails);

                    if (!saveSuccess) {
                        break;
                    }
                } catch (Exception e) {
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
            LOGGER.error(namingEx);
        }
        if (datasource != null) {
            try (Connection connection = datasource.getConnection();
                    CallableStatement statement = connection.prepareCall("{call PRC_NM_DISCOUNT_INSERT (?,?,?)}")) {
                statement.setInt(1, session.getProjectionId());
                statement.setInt(NumericConstants.TWO, Integer.parseInt(session.getUserId()));
                statement.setString(NumericConstants.THREE, session.getSessionId());
                statement.execute();

            } catch (NumberFormatException | SQLException ex) {
                LOGGER.error(ex);
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
        if (value.contains(Constant.NULL) || value.equals("-")) {
            value = DASH;
        } else {
            Double newValue = Double.valueOf(value);
            if (format.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = format.format(newValue);
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
        DataSource datasource = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
        } catch (NamingException ex) {
            LOGGER.error(ex);
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
                LOGGER.error(ex);
            }
        }
        return true;
    }

    public void checkClearAll(SessionDTO session, String userGroup, boolean checkClear) {
        queryBuilderAndExecutor.checkClearAll(session, userGroup, checkClear);
    }

    public boolean isAnyRecordChecked(SessionDTO session, boolean isProgram, List<String> discountProgramsList, boolean isCustomHierarchy) {
        int count = queryBuilderAndExecutor.getCheckedRecordCount(session, isProgram, discountProgramsList, isCustomHierarchy);
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
            if (session.getCustomId() != 0) {
                String discountId = CommonUtils.CollectionToString(session.getSelectedRsForCustom(), false);
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

            LOGGER.error(ex);
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
        LOGGER.debug("Projection ID " + session.getProjectionId());
        LOGGER.debug("Frequency                " + String.valueOf(session.getFrequency()));
        LOGGER.debug("UserId                   " + session.getUserId());
        LOGGER.debug("Session Id               " + session.getSessionId());
        LOGGER.debug("Forecast Start               " + projectionSelection.getFromDateDdlb());
        LOGGER.debug("Forecast End              " + projectionSelection.getToDateDdlb());
        LOGGER.debug("Calc Based              " + projectionSelection.getCalcBased());
        LOGGER.debug("DEDUCTION  " + ((projectionSelection.getSessionDTO().getDeductionInclusion() == null || ALL.equals(projectionSelection.getSessionDTO().getDeductionInclusion())) ? null : projectionSelection.getSessionDTO().getDeductionInclusion()));

        DataSource datasource = null;

        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(JBOSS_DATA_POOL);
        } catch (NamingException ex) {
            LOGGER.error(ex);
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
                LOGGER.debug(ex);
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
            Map<String, Map<String, List<String>>> selectedPeriods, List selectedDiscount, Boolean isProgram, Boolean isCustom) {
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
        LOGGER.debug("Details Id:==========>" + detailsValue);
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
                    String ACTUAL_AMT_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.THREE])) ? DASH : String.valueOf(obj[NumericConstants.THREE]);
                    String PROJ_AMT_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOUR])) ? DASH : String.valueOf(obj[NumericConstants.FOUR]);
                    totalDTO.addStringProperties(commonColumn + ACTUAL_AMOUNT, getFormattedValue(AMOUNT, ACTUAL_AMT_OBJ));
                    totalDTO.addStringProperties(commonColumn + PROJECTED_AMOUNT, getFormattedValue(AMOUNT, PROJ_AMT_OBJ));
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
            GtnSmallHashMap ccpCountForDiscount = new GtnSmallHashMap();
            if (!pivotList.isEmpty()) {
                getParentGroupValue(frequency, pivotList, discountDto, discountProjList, ahPeriodList, hashMapValues);
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
//                        discountDto.setLevelName(session.getLevelValueDiscription(discountDto.getHierarchyNo(), hierarchyIndicator));
                        discountDto.setLevelName(CommonUtil.getDisplayFormattedName(discountDto.getHierarchyNo(), hierarchyIndicator, session.getHierarchyLevelDetails(), session, projectionSelection.getDisplayFormat()));
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
            for (int k = mayBeAddedRecord; k < discountProjList.size() && neededRecord > 0; neededRecord--, k++) {
                discountProj.add(discountProjList.get(k));
                started++;
            }
            return discountProj;
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            return Collections.EMPTY_LIST;
        }
    }

    public void getParentGroupValue(String frequency, List discountProjectionList, DiscountProjectionDTO discountDto,
            List<DiscountProjectionDTO> discountProjList, List<String> ahPeriodList, Map<String, String> hashMapValues) {
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

                    String ACTUAL_AMT_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? DASH : String.valueOf(obj[NumericConstants.FOURTEEN]);
                    String PROJ_AMT_OBJ = Constant.NULL.equals(String.valueOf(obj[NumericConstants.SIXTEEN])) ? DASH : String.valueOf(obj[NumericConstants.SIXTEEN]);
                    if (mapValue.containsKey(headerColumn)) {
                        discountDto = (DiscountProjectionDTO) mapValue.get(headerColumn);
                        int AP_INDICATOR = Integer.valueOf(String.valueOf(obj[NumericConstants.TEN]));
                        if (AP_INDICATOR == 0) {
                            discountDto.addStringProperties(PAYMENT1 + Constant.ACTUALS, getFormattedValue(AMOUNT, ACTUAL_AMT_OBJ));
                        }
                        discountDto.addStringProperties(PAYMENT1 + Constant.PROJECTIONS, getFormattedValue(AMOUNT, PROJ_AMT_OBJ));
                    } else {
                        discountDto = new DiscountProjectionDTO();
                        int AP_INDICATOR = Integer.valueOf(String.valueOf(obj[NumericConstants.TEN]));
                        if (AP_INDICATOR == 0) {
                            discountDto.addStringProperties(PAYMENT1 + Constant.ACTUALS, getFormattedValue(AMOUNT, ACTUAL_AMT_OBJ));
                        }
                        discountDto.addStringProperties(PAYMENT1 + Constant.PROJECTIONS, getFormattedValue(AMOUNT, PROJ_AMT_OBJ));
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
        } catch (NumberFormatException e) {
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
            boolean isProgram, final List<String> discountList, final String userGroup, final ProjectionSelectionDTO projselection) {
        String query = StringUtils.EMPTY;
        if (CommonUtil.isValueEligibleForLoading()) {
            query = queryBuilderAndExecutor.getDiscountCountQueryForAllLevel(sessionDTO, hierarchyNo, levelNo, hierarchyIndicator, isProgram, discountList, userGroup, projselection);
        } else {
            query = queryBuilderAndExecutor.getDiscountCountQuery(sessionDTO, hierarchyNo, levelNo, hierarchyIndicator, isProgram, discountList, userGroup);
        }

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return Integer.valueOf(list.get(0).toString());
        }

        return 0;
    }

    public int getDiscountCustomCount(final SessionDTO sessionDTO, final String hierarchyIndicator, final int levelNo, final String customerHierarchyNo, final String productHierarchyNo, final String deductionHierarchyNo, final List<String> discountList, boolean isProgram, final String userGroup) {
        String parentHierarchyIndicator = StringUtils.EMPTY;
        String parentHierarchyNo = StringUtils.EMPTY;
        String parentHierarchyIndicatorDeduction = StringUtils.EMPTY;
        String parentHierarchyNoDeduction = StringUtils.EMPTY;

        if ("C".equalsIgnoreCase(hierarchyIndicator) && (StringUtils.isNotBlank(productHierarchyNo) || StringUtils.isNotBlank(deductionHierarchyNo))) {
            parentHierarchyIndicator = "P";
            parentHierarchyNo = productHierarchyNo;
            parentHierarchyIndicatorDeduction = "D";
            parentHierarchyNoDeduction = deductionHierarchyNo;
        } else if ("P".equalsIgnoreCase(hierarchyIndicator) && (StringUtils.isNotBlank(customerHierarchyNo) || StringUtils.isNotBlank(deductionHierarchyNo))) {
            parentHierarchyIndicator = "C";
            parentHierarchyNo = customerHierarchyNo;
            parentHierarchyIndicatorDeduction = "D";
            parentHierarchyNoDeduction = deductionHierarchyNo;
        } else if ("D".equalsIgnoreCase(hierarchyIndicator) && (StringUtils.isNotBlank(productHierarchyNo) || StringUtils.isNotBlank(customerHierarchyNo))) {
            parentHierarchyIndicator = "C";
            parentHierarchyNo = customerHierarchyNo;
            parentHierarchyIndicatorDeduction = "P";
            parentHierarchyNoDeduction = productHierarchyNo;
        }

        String query = queryBuilderAndExecutor.getDiscountCustomCountQuery(sessionDTO, hierarchyIndicator, levelNo, "C".equalsIgnoreCase(hierarchyIndicator) ? customerHierarchyNo : "P".equalsIgnoreCase(hierarchyIndicator) ? productHierarchyNo : deductionHierarchyNo, parentHierarchyIndicator, parentHierarchyNo, parentHierarchyIndicatorDeduction, parentHierarchyNoDeduction, discountList, isProgram, userGroup);

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

    public List<String> getRsAllList(ProjectionSelectionDTO projdto) {
        List<String> rebateList = new ArrayList<>();
        try {
            String queryAllRebate = StringUtils.EMPTY;

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
                    rebateList.add(val.toString());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
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
}
