/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.logic;

import com.stpl.app.arm.balancesummaryreport.BalanceSummaryReportUI;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.text.DecimalFormat;

/**
 *
 * @author Srithar.R
 * @param <T>
 */
public abstract class AbstractBSummaryLogic<T extends AdjustmentDTO> extends AbstractSummaryLogic<T> {

    protected DecimalFormat decimalformat = new DecimalFormat("$#,##0.00");

    public Object[] getLeftTableHeaders() {
        Object[] finalList = new Object[NumericConstants.FIVE];
        Object[] doubleColumns = {ARMUtils.CUSTOMERORPRODUCT_COLUMN};
        String[] doubleHeaders = {StringUtils.EMPTY};
        Object[] columns = {ARMUtils.CUSTOMERORPRODUCT_COLUMN};
        String[] headers = {ARMUtils.CUSTOMER_SMALL};
        Map<String, Object[]> headerMap = new HashMap<>();
        Object[] visibleList = new Object[NumericConstants.ONE];
        visibleList[NumericConstants.ZERO] = ARMUtils.CUSTOMERORPRODUCT_COLUMN;
        headerMap.put(ARMUtils.CUSTOMERORPRODUCT_COLUMN, visibleList);
        finalList[NumericConstants.ZERO] = columns;
        finalList[NumericConstants.ONE] = headers;
        finalList[NumericConstants.TWO] = doubleColumns;
        finalList[NumericConstants.THREE] = doubleHeaders;
        finalList[NumericConstants.FOUR] = headerMap;
        return finalList;
    }

    public List getRightTableHeaders(SummarySelection selection) {

        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        Date frmDate = new Date();
        Date toDate = new Date();

        int startMonth = 0;
        int endMonth = 0;
        int endtYear = 0;
        int starttYear = 0;
        if (ARMUtils.frequencyVarables.QUARTERLY.toString().equals(selection.getFrequency())) {
            startMonth = selection.getFromDate().charAt(1) - 48;
            startMonth = 1 + (3 * (startMonth - 1));
            endMonth = selection.getToDate().charAt(1) - 48;
            endMonth = 1 + (3 * (endMonth - 1));
            starttYear = ARMUtils.getIntegerValue(selection.getFromDate().substring(3, 7));
            endtYear = ARMUtils.getIntegerValue(selection.getToDate().substring(3, 7));
        } else if (ARMUtils.frequencyVarables.SEMI_ANNUALLY.toString().equals(selection.getFrequency())) {
            startMonth = selection.getFromDate().charAt(1) - 48;
            startMonth = 1 + (6 * (startMonth - 1));
            endMonth = selection.getToDate().charAt(1) - 48;
            endMonth = 1 + (6 * (endMonth - 1));
            starttYear = ARMUtils.getIntegerValue(selection.getFromDate().substring(3, 7));
            endtYear = ARMUtils.getIntegerValue(selection.getToDate().substring(3, 7));
        } else if (ARMUtils.frequencyVarables.MONTHLY.toString().equals(selection.getFrequency())) {
            startMonth = ArrayUtils.indexOf(months, selection.getFromDate().substring(0, 3)) + 1;
            endMonth = ArrayUtils.indexOf(months, selection.getToDate().substring(0, 3)) + 1;
            starttYear = ARMUtils.getIntegerValue(selection.getFromDate().substring(4, 8));
            endtYear = ARMUtils.getIntegerValue(selection.getToDate().substring(4, 8));
        } else if (ARMUtils.frequencyVarables.ANNUALLY.toString().equals(selection.getFrequency())) {
            startMonth = 1;
            endMonth = 12;
            starttYear = ARMUtils.getIntegerValue(selection.getFromDate());
            endtYear = ARMUtils.getIntegerValue(selection.getToDate());
        }
        frmDate.setDate(1);
        toDate.setDate(28);
        frmDate.setMonth(startMonth - NumericConstants.ONE);
        toDate.setMonth(endMonth - NumericConstants.ONE);
        frmDate.setYear(starttYear - NumericConstants.ONE_NINE_ZERO_ZERO);
        toDate.setYear(endtYear - NumericConstants.ONE_NINE_ZERO_ZERO);
        int totalMonth = getMonthsDifference(frmDate, toDate);
        if (isHeaderIsAvail(frmDate, toDate, selection.getSelectedAdjustmentTypeValues())) {
            int frequencyDivision = 1;
            List finalList = new ArrayList();
            List doubleColumns = new ArrayList();
            List doubleHeaders = new ArrayList();
            List visibleHeaders = new ArrayList();
            Map<String, Object[]> headerMap = new HashMap<>();
            Map<String, Object[]> excelHeaderMap = new HashMap<>();
            excelHeaderMap.put("group", new Object[]{"group"});
            List visibleColumn = new ArrayList<>();
            if (ARMUtils.frequencyVarables.QUARTERLY.toString().equals(selection.getFrequency())) {
                frequencyDivision = NumericConstants.THREE;
            } else if (ARMUtils.frequencyVarables.SEMI_ANNUALLY.toString().equals(selection.getFrequency())) {
                frequencyDivision = NumericConstants.SIX;
            } else if (ARMUtils.frequencyVarables.MONTHLY.toString().equals(selection.getFrequency())) {
                frequencyDivision = NumericConstants.ONE;
            } else if (ARMUtils.frequencyVarables.ANNUALLY.toString().equals(selection.getFrequency())) {
                frequencyDivision = NumericConstants.TWELVE;
            }
            int index = 0;
            List tempList = new ArrayList<>();
            String column = null;
            String doubleColumn = null;
            int year = frmDate.getYear() + 1900;
            int tempPeriod = 0;
            if (ARMUtils.frequencyVarables.MONTHLY.toString().equals(selection.getFrequency())) {
                tempPeriod = startMonth / frequencyDivision;
            } else {
                tempPeriod = (startMonth / frequencyDivision) + 1;
            }
            HashMap<Object, String> headerVlaueMap = new HashMap<>();
            List<String> selectedVariables = selection.getSelectedAdjustmentTypeValues();
            List<String> selectedToatalAndCumulative = removeTotalAndCumulative(selectedVariables);

        for (int i = 0; i < ((totalMonth / frequencyDivision) + NumericConstants.ONE); i++) {
            if (tempPeriod > (NumericConstants.TWELVE / frequencyDivision)) {
                tempPeriod = NumericConstants.ONE;
                year++;
            }
            switch (frequencyDivision) {
                case NumericConstants.THREE:
                    doubleColumn = ARMUtils.Q + tempPeriod + year;
                    doubleHeaders.add(ARMUtils.Q + tempPeriod + " " + year);
                    break;
                case NumericConstants.SIX:
                    doubleColumn = ARMUtils.S + tempPeriod + year;
                    doubleHeaders.add(ARMUtils.S + tempPeriod + " " + year);
                    break;
                case NumericConstants.ONE:
                    doubleColumn = months[tempPeriod - NumericConstants.ONE] + year;
                    doubleHeaders.add(months[tempPeriod - NumericConstants.ONE] + " " + year);
                    break;
                case NumericConstants.TWELVE:
                    doubleColumn = String.valueOf(year);
                    doubleHeaders.add(String.valueOf(year));
                    break;
                default:
                    break;
            }
            doubleColumns.add(doubleColumn);
            tempList.clear();
            for (int j = 0; j < selectedVariables.size(); j++) {
                String adjType;
                switch (frequencyDivision) {
                    case NumericConstants.THREE:
                        adjType = selectedVariables.get(j);
                        column = ARMUtils.Q + tempPeriod + year + selectedVariables.get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                        visibleHeaders.add(adjType);
                        tempList.add(column);
                        visibleColumn.add(column);
                        break;

                    case NumericConstants.SIX:
                        adjType = selectedVariables.get(j);
                        column = ARMUtils.S + tempPeriod + year + selectedVariables.get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                        visibleHeaders.add(adjType);
                        tempList.add(column);
                        visibleColumn.add(column);
                        break;

                    case NumericConstants.ONE:
                        adjType = selectedVariables.get(j);
                        column = months[tempPeriod - 1] + year + selectedVariables.get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                        visibleHeaders.add(adjType);
                        tempList.add(column);
                        visibleColumn.add(column);
                        break;

                    case NumericConstants.TWELVE:
                        adjType = selectedVariables.get(j);
                        column = year + selectedVariables.get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                        visibleHeaders.add(adjType);
                        tempPeriod = year;
                        tempList.add(column);
                        visibleColumn.add(column);
                        break;

                    default:
                        break;
                }
                headerVlaueMap.put(StringUtils.EMPTY + tempPeriod + year + selectedVariables.get(j), column);
                index++;
            }
            headerMap.put(doubleColumn, tempList.toArray());
            excelHeaderMap.put(doubleColumn, tempList.toArray());
            tempPeriod++;
        }
        for (String selectedTotal : selectedToatalAndCumulative) {
            tempList.clear();
            if (CommonConstant.BEGINNING_BALANCE.equals(selectedTotal)) {
                doubleColumn = selectedTotal.replace(" ", StringUtils.EMPTY);
                doubleHeaders.add(0, selectedTotal);
                doubleColumns.add(0, doubleColumn);
                for (int j = 0; j < selectedVariables.size(); j++) {
                    String adjType = selectedVariables.get(j);
                    column = doubleColumn + selectedVariables.get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                    visibleHeaders.add(j, adjType);
                    tempList.add(j, column);
                    visibleColumn.add(j, column);
                    headerVlaueMap.put(StringUtils.EMPTY + selectedTotal + selectedVariables.get(j), column);
                    index++;
                }
                headerMap.put(doubleColumn, tempList.toArray());
                excelHeaderMap.put(doubleColumn, tempList.toArray());
            } else {
                doubleColumn = selectedTotal.replace(" ", StringUtils.EMPTY);
                doubleHeaders.add(selectedTotal);
                doubleColumns.add(doubleColumn);
                for (int j = 0; j < selectedVariables.size(); j++) {
                    String adjType = selectedVariables.get(j);
                    column = doubleColumn + selectedVariables.get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                    visibleHeaders.add(totalSectionHeader(selectedTotal, adjType));
                    tempList.add(column);
                    visibleColumn.add(column);
                    headerVlaueMap.put(StringUtils.EMPTY + selectedTotal + (ConstantsUtils.ENDING_BALANCE.equals(selectedVariables.get(j)) ? ConstantsUtils.BALANCE : selectedVariables.get(j)), column);
                    index++;
                }
                headerMap.put(doubleColumn, tempList.toArray());
                excelHeaderMap.put(doubleColumn, tempList.toArray());
            }
        }

        selection.setHeaderVisibleColumnMap(headerVlaueMap);
        selection.setExcelVisibleColumn(visibleColumn);

        finalList.add(visibleColumn);
        finalList.add(visibleHeaders);
        finalList.add(doubleColumns);
        finalList.add(doubleHeaders);
        finalList.add(headerMap);
        finalList.add(excelHeaderMap);

        addTotalAndCumulative(selectedVariables, selectedToatalAndCumulative);

            return finalList;
    }
        return Collections.emptyList();
    }

    public static final int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * NumericConstants.TWELVE + date1.getMonth();
        int m2 = date2.getYear() * NumericConstants.TWELVE + date2.getMonth();
        return m2 - m1;
    }

    private boolean isHeaderIsAvail(Date frmDate, Date toDate, List<String> selectedAdjustmentType) {
        if (!selectedAdjustmentType.isEmpty() && (toDate.equals(frmDate) || toDate.after(frmDate))) {
            return true;
        }
        return false;
    }

    private List<String> removeTotalAndCumulative(List<String> selectedVariables) {
        List<String> selectedTotal = new ArrayList();
        if (selectedVariables.contains(CommonConstant.TOTAL)) {
            selectedTotal.add(CommonConstant.TOTAL);
            selectedVariables.remove(CommonConstant.TOTAL);
        }
        if (selectedVariables.contains(CommonConstant.CUMULATIVE_BALANCE)) {
            selectedTotal.add(CommonConstant.CUMULATIVE_BALANCE);
            selectedVariables.remove(CommonConstant.CUMULATIVE_BALANCE);
        }
        if (selectedVariables.contains(CommonConstant.BEGINNING_BALANCE)) {
            selectedTotal.add(CommonConstant.BEGINNING_BALANCE);
            selectedVariables.remove(CommonConstant.BEGINNING_BALANCE);
        }
        return selectedTotal;
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO data) {
        Object[] returnObj = new Object[2];
        try {
            SummarySelection selection = (SummarySelection) data;
            String rebateRecord;

            List<Object> inputs = new ArrayList<>();
            inputs.add(selection.getDataSelectionDTO().getProjectionId());
            inputs.add(selection.getFrequency());
            selection.setMasterSids(ARMUtils.getMasterIdsMap());
            String nextLevel = getNextLevel(dto, selection);
            inputs.add(nextLevel);
            inputs.add(selection.getDataSelectionFromDate());
            inputs.add(selection.getDataSelectionToDate());
            inputs.add(selection.getSessionDTO().getUserId());
            inputs.add(selection.getSessionDTO().getSessionId());
            rebateRecord = "WHERE" + ARMUtils.getDeductionValuesMap().get(selection.getSummarydeductionLevelDes()) + " IN ('" + StringUtils.join(selection.getDeductionVariableIds(), "','") + "' )";
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            inputs.add(ARMUtils.getDeductionValuesMapForLevel().get(selection.getSummarydeductionLevelDes()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()));
            inputs.add(rebateRecord);
            inputs.add(selection.getFromDateFilter());
            inputs.add(selection.getToDateFilter());
            returnObj[0] = inputs;
            returnObj[1] = new TreeMap();
        } catch (Exception ex) {
            LOGGER.error("Error in generateInputs:", ex);
        }
        return returnObj;
    }

    private String getNextLevel(Object bsrDto, SummarySelection bsrSelection) {
        String nextLevel;
        if (bsrDto instanceof AdjustmentDTO) {
            TreeMap<String, Integer> masterSids;
            AdjustmentDTO val = (AdjustmentDTO) bsrDto;
            int levelNo = val.getLevelNo();
            masterSids = (TreeMap<String, Integer>) val.getMasterIds().clone();
            masterSids.put(bsrSelection.getSummaryLevel().get(levelNo), ARMUtils.getIntegerValue(val.getBranditemmasterSid()));
            bsrSelection.setMasterSids(masterSids);
            if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(bsrSelection.getSummaryLevel().get(++levelNo))) {
                nextLevel = bsrSelection.getSummarydeductionLevelDes();
            } else {
                nextLevel = bsrSelection.getSummaryLevel().get(levelNo);
            }
            bsrSelection.setSummaryviewType(bsrSelection.getSummaryLevel().get(levelNo));
            bsrSelection.setLevelNo(levelNo);
        } else {
            bsrSelection.setLevelNo(1);
            nextLevel = getnextLevelLevelFilter(bsrSelection);
            getNextLevelValueMethod(bsrSelection);
            nextLevel = getNextLevelFirstLevel(bsrSelection, nextLevel);
        }
        return nextLevel;
    }

    private String getNextLevelFirstLevel(SummarySelection selection, String newLevel) {
        //This will ovverride the default first Level (For Level Filter)
        String nextLevel = newLevel;
        if (selection.getSummarylevelFilterNo() != 0) {
            if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummarylevelFilterValue())) {
                nextLevel = selection.getSummarydeductionLevelDes();
            } else {
                nextLevel = selection.getSummarylevelFilterValue();
            }
        }
        return nextLevel;
    }

    private void getNextLevelValueMethod(SummarySelection selection) {
        // This condition will work for Value Ddlb
        if (selection.getSummaryvalueSid() != 0) {
            selection.getMasterSids().put(selection.getSummarylevelFilterValue(), selection.getSummaryvalueSid());
        } else if (selection.getSummarylevelFilterNo() != 0) {
            selection.getMasterSids().put(selection.getSummarylevelFilterValue(), null);
        }
    }

    private String getnextLevelLevelFilter(SummarySelection selection) {
        String nextLevel;
        // Do not change the order of the below code
        // This condition will work when Level filter is not used
        if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummaryLevel().get(1))) {
            nextLevel = selection.getSummarydeductionLevelDes();
        } else {
            nextLevel = selection.getSummaryLevel().get(1);
        }
        return nextLevel;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean totalFlag = false;
        boolean dataFlag = true;
        if ((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))
                && (criteria.getCurrentPage() == criteria.getLastPage()) && criteria.getSelectionDto().getSummarylevelFilterNo() == 0 && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) {
            totalFlag = true;
            int start = Integer.parseInt(inputs.get(inputs.size() - 2).toString());
            int offset = ARMUtils.getIntegerValue(inputs.get(inputs.size() - 1).toString()) - 1;
            inputs.set(inputs.size() - 1, offset);
            dataFlag = offset >= start;
        }
        List<Object[]> list = new ArrayList<>();
        inputs.add(inputs.get(inputs.size() - 2));
        inputs.add(inputs.get(inputs.size() - 2));
        inputs.add(!ARMConstants.getDeduction().equals(inputs.get(2)) && String.valueOf(inputs.get(2)).contains(ARMConstants.getDeduction()) ? "DEDUCTION_SET_1" : "DEDUCTION_SET_2");
        if (dataFlag) {
            list = QueryUtils.getItemData(inputs, getCommonQueryName(), getLoadDataQueryName());
        }
        DataResult<T> result = getCustomizedData(criteria.getSelectionDto(), list);
        if (totalFlag) {
            List<Object[]> totaldata;
            int inputSize = inputs.size();
            inputs.remove(inputSize - 1);
            inputs.remove(inputSize - 2);
            inputs.remove(inputSize - 3);
            inputs.remove(inputSize - 4);
            inputs.remove(inputSize - 5);
            totaldata = QueryUtils.getItemData(inputs, getCommonQueryName(), getTotalQueryName());
            List l = result.getDataResults();
            l.addAll((getCustomizedData(criteria.getSelectionDto(), totaldata)).getDataResults());
            result.setDataResults(l);
        }

        return result;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        int count;
        count = CommonLogic.getCount(QueryUtils.getItemData(inputs, getCommonQueryName(), getCountQueryName()));
        if (count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            count = count + 1;
        }
        return count;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return StringUtils.EMPTY;
    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        return Boolean.TRUE;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        BalanceSummaryReportUI.setExcelClose(true);
        String query = SQlUtil.getQuery(getExcelQueryName());
        Object[] value = selection.getExcelHierarchy();
        query = query.replace("@LEVEL_VAL", StringUtils.join(value, ","));
        query = query.replace("@DEDUCTIONLEVEL", selection.getSummarydeductionLevelDes());
        query = query.replace("@DEDUCTIONVALUE", selection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@FREQUENCYSELECTED", selection.getSummaryFrequencyName());
        query = query.replace("@STARTPERIOD", selection.getDataSelectionFromDate());
        query = query.replace("@ENDPERIOD", selection.getDataSelectionToDate());
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        query = query.replace("@STARTFILTER", String.valueOf(selection.getPeriodSidList().get(0)));
        query = query.replace("@ENDFILTER", selection.getPeriodSidList().size() > 1 ? String.valueOf(selection.getPeriodSidList().get(1)) : String.valueOf(selection.getPeriodSidList().get(0)));
        query = query.replace("@ORDER_COLUMNS", getOrderBasedOnView(selection));
        List deductionList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        query = SQlUtil.getQuery(getExcelTotalQueryName());
        query = query.replace("@LEVEL_VAL", StringUtils.join(value, ","));
        query = query.replace("@DEDUCTIONLEVEL", selection.getSummarydeductionLevelDes());
        query = query.replace("@DEDUCTIONVALUE", selection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@FREQUENCYSELECTED", selection.getSummaryFrequencyName());
        query = query.replace("@STARTPERIOD", selection.getFromDate());
        query = query.replace("@ENDPERIOD", selection.getToDate());
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        query = query.replace("@ORDER_COLUMNS", getOrderBasedOnView(selection));
        List totalList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        List returnList = new ArrayList();
        returnList.add(deductionList);
        returnList.add(totalList);
        return returnList;
    }

    private List<String> addTotalAndCumulative(List<String> selectedVariables, List<String> selectedTotal) {
        if (selectedTotal.contains(CommonConstant.TOTAL)) {
            selectedVariables.add(CommonConstant.TOTAL);
        }
        if (selectedTotal.contains(CommonConstant.CUMULATIVE_BALANCE)) {
            selectedVariables.add(CommonConstant.CUMULATIVE_BALANCE);
        }
        if (selectedTotal.contains(CommonConstant.BEGINNING_BALANCE)) {
            selectedVariables.add(NumericConstants.ZERO, CommonConstant.BEGINNING_BALANCE);
        }
        return selectedTotal;
    }

    private String totalSectionHeader(String selectedTotal, String adjType) {
        if (selectedTotal.equals("Total")) {
            switch (adjType) {
                case "Actual Payments":
                    return "Payments";
                case ConstantsUtils.ENDING_BALANCE:
                    return ConstantsUtils.BALANCE;
                case "Period Balance":
                    return ConstantsUtils.ENDING_BALANCE;
                default:
                    return adjType;
            }
        }
        return adjType;
    }

    protected abstract String getCommonQueryName();

    protected abstract String getLoadDataQueryName();

    protected abstract String getCountQueryName();

    protected abstract String getTotalQueryName();

    protected abstract String getExcelQueryName();

    protected abstract String getExcelTotalQueryName();

    protected abstract DataResult getCustomizedData(SelectionDTO data, List list);

    public abstract List<Map<String, AdjustmentDTO>> bsExcelCustomizer(final List resultList, final Object[] object, final List<String> visibleColumns, final boolean isFixedColumns, final int interval, final int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException;

    private String getOrderBasedOnView(AbstractSelectionDTO selection) {
        String val = "1,2,3,4,5,6,7";
        if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
            val = val + ",8,9";
        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
            val = val + ",8,9,10,11";
        }
        return val;
    }

    protected int getKeyParam(AbstractSelectionDTO selection) {
        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
            return 6;
        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
            return 8;
        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
            return 10;
        }
        return 0;
    }

    protected String[] getKey(Object[] resultSet, int keyParam) {
        StringBuilder keys = new StringBuilder();
        keys.append(resultSet[0].toString()).append(".");
        String group = resultSet[1].toString();
        for (int i = 2; i < keyParam; i += 2) {
            if (resultSet[i] != null) {
                keys.append(resultSet[i]).append(".");
                group = resultSet[i + 1].toString();
            }
        }
        return new String[]{keys.toString(), group};
    }

    public Double getNullTotal(Double total) {
        return total == null ? 0.00 : total;
    }

    public String getNullTotalExcel(Double total) {
        return total == null ? "0.00" : decimalformat.format(total);
    }

    public Double getTotalForExcel(Double val, Object obj) {
        return getNullTotal(val) + Double.valueOf(ARMUtils.NULL.equalsIgnoreCase(String.valueOf(obj)) ? ARMUtils.ZERO_STRING : String.valueOf(obj));
    }
}
