/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import static com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic.LOGGER;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Srithar.Raju
 * @param <T>
 */
public abstract class AbstractBSummaryLogic<T extends AdjustmentDTO> extends AbstractSummaryLogic<T> {

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

    public List getRightTableHeaders(SummarySelection selection) throws ParseException {
        
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
            starttYear = Integer.valueOf(selection.getFromDate().substring(3, 7));
            endtYear = Integer.valueOf(selection.getToDate().substring(3, 7));
        } else if (ARMUtils.frequencyVarables.SEMI_ANNUALLY.toString().equals(selection.getFrequency())) {
            startMonth = selection.getFromDate().charAt(1) - 48;
            startMonth = 1 + (6 * (startMonth - 1));
            endMonth = selection.getToDate().charAt(1) - 48;
            endMonth = 1 + (6 * (endMonth - 1));
            starttYear = Integer.valueOf(selection.getFromDate().substring(3, 7));
            endtYear = Integer.valueOf(selection.getToDate().substring(3, 7));
        } else if (ARMUtils.frequencyVarables.MONTHLY.toString().equals(selection.getFrequency())) {
            startMonth = ArrayUtils.indexOf(months, selection.getFromDate().substring(0, 3)) + 1;
            endMonth = ArrayUtils.indexOf(months, selection.getToDate().substring(0, 3)) + 1;
            starttYear = Integer.valueOf(selection.getFromDate().substring(4, 8));
            endtYear = Integer.valueOf(selection.getToDate().substring(4, 8));
        } else if (ARMUtils.frequencyVarables.ANNUALLY.toString().equals(selection.getFrequency())) {
            startMonth = 1;
            endMonth = 12;
            starttYear = Integer.valueOf(selection.getFromDate());
            endtYear = Integer.valueOf(selection.getToDate());
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
                        doubleColumn = "" + year;
                        doubleHeaders.add("" + year);
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
                            adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = ARMUtils.Q + tempPeriod + year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                            visibleHeaders.add(adjType);
                            tempList.add(column);
                            visibleColumn.add(column);
                            break;

                        case NumericConstants.SIX:
                            adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = ARMUtils.S + tempPeriod + year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                            visibleHeaders.add(adjType);
                            tempList.add(column);
                            visibleColumn.add(column);
                            break;

                        case NumericConstants.ONE:
                            adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = months[tempPeriod - 1] + year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                            visibleHeaders.add(adjType);
                            tempList.add(column);
                            visibleColumn.add(column);
                            break;

                        case NumericConstants.TWELVE:
                            adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
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
                doubleColumn = selectedTotal.replace(" ", StringUtils.EMPTY);
                doubleHeaders.add(selectedTotal);
                doubleColumns.add(doubleColumn);
                for (int j = 0; j < selectedVariables.size(); j++) {
                    String adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                    column = doubleColumn + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                    visibleHeaders.add(adjType);
                    tempList.add(column);
                    visibleColumn.add(column);
                    headerVlaueMap.put(StringUtils.EMPTY + selectedTotal + selectedVariables.get(j), column);
                    index++;
                }
                headerMap.put(doubleColumn, tempList.toArray());
                excelHeaderMap.put(doubleColumn, tempList.toArray());
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
        return Collections.EMPTY_LIST;
    }

    public static final int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * NumericConstants.TWELVE + date1.getMonth();
        int m2 = date2.getYear() * NumericConstants.TWELVE + date2.getMonth();
        return m2 - m1;
    }

    private boolean isHeaderIsAvail(Date frmDate, Date toDate, List<String> selectedAdjustmentType) {
        if (!selectedAdjustmentType.isEmpty() && (toDate.equals(frmDate) || toDate.after(frmDate))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private List<String> removeTotalAndCumulative(List<String> selectedVariables) {
        List<String> selectedTotal = new ArrayList();
        if (selectedVariables.contains("Total")) {
            selectedTotal.add("Total");
            selectedVariables.remove("Total");
        }
        if (selectedVariables.contains("Cumulative Balance")) {
            selectedTotal.add("Cumulative Balance");
            selectedVariables.remove("Cumulative Balance");
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
            inputs.add(CommonLogic.listToString(selection.getSelectedAdjustmentType()));
            selection.setMasterSids(ARMUtils.getMasterIdsMap());
            String nextLevel;
            if (dto instanceof AdjustmentDTO) {
                TreeMap<String, Integer> masterSids;
                AdjustmentDTO val = (AdjustmentDTO) dto;
                int levelNo = val.getLevelNo();
                masterSids = (TreeMap<String, Integer>) val.getMasterIds().clone();
                masterSids.put(selection.getSummaryLevel().get(levelNo), Integer.valueOf(val.getBrand_item_masterSid()));
                selection.setMasterSids(masterSids);
                if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummaryLevel().get(++levelNo))) {
                    nextLevel = selection.getSummary_deductionLevelDes();
                } else {
                    nextLevel = selection.getSummaryLevel().get(levelNo);
                }
                selection.setSummary_viewType(selection.getSummaryLevel().get(levelNo));
                selection.setLevelNo(levelNo);
            } else {
                selection.setLevelNo(1);
                // Do not change the order of the below code
                // This condition will work when Level filter is not used
                if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummaryLevel().get(1))) {
                    nextLevel = selection.getSummary_deductionLevelDes();
                } else {
                    nextLevel = selection.getSummaryLevel().get(1);
                }
                // This condition will work for Value Ddlb
                if (selection.getSummary_valueSid() != 0) {
                    selection.getMasterSids().put(selection.getSummary_levelFilterValue(), selection.getSummary_valueSid());
                } else if (selection.getSummary_levelFilterNo() != 0) {
                    selection.getMasterSids().put(selection.getSummary_levelFilterValue(), null);
                }
                //This will ovverride the default first Level (For Level Filter)
                if (selection.getSummary_levelFilterNo() != 0) {
                    if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummary_levelFilterValue())) {
                        nextLevel = selection.getSummary_deductionLevelDes();
                    } else {
                        nextLevel = selection.getSummary_levelFilterValue();
                    }
                }

            }
            inputs.add(nextLevel);
            inputs.add(selection.getFromDate());
            inputs.add(selection.getToDate());
            inputs.add(selection.getSessionDTO().getUserId());
            inputs.add(selection.getSessionDTO().getSessionId());
            rebateRecord = "WHERE" + ARMUtils.getDeductionValuesMap().get(selection.getSummary_deductionLevelDes()) + " IN ('" + StringUtils.join(selection.getDeductionVariableIds(), "','") + "' )";
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            inputs.add(ARMUtils.getDeductionValuesMapForLevel().get(selection.getSummary_deductionLevelDes()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()));
            inputs.add(rebateRecord);
            returnObj[0] = inputs;
            returnObj[1] = new TreeMap();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return returnObj;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean totalFlag = false;
        boolean dataFlag = true;
        if ((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))
                && (criteria.getCurrentPage() == criteria.getLastPage()) && criteria.getSelectionDto().getSummary_levelFilterNo() == 0 && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) {
            totalFlag = true;
            int start = Integer.valueOf(inputs.get(inputs.size() - 2).toString());
            int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
            inputs.set(inputs.size() - 1, offset);
            dataFlag = offset >= start;
        }
        List<Object[]> list = new ArrayList<>();

        if (dataFlag) {
            list = QueryUtils.getItemData(inputs, getCommonQueryName(), getLoadDataQueryName());
        }
        DataResult<T> result = getCustomizedData(criteria.getSelectionDto(), list);
        if (totalFlag) {
            List<Object[]> totaldata;
            int inputSize = inputs.size();
            inputs.remove(inputSize - 1);
            inputs.remove(inputSize - 2);
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
        if (count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getSelectionDto().getSummary_levelFilterNo() == 0)) {
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
        return true;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query = SQlUtil.getQuery(getExcelQueryName());
        Object[] value = selection.getExcelHierarchy(); 
        query = query.replace("@LEVEL_VAL", StringUtils.join(value, ","));
        query = query.replace("@DEDUCTIONLEVEL", selection.getSummary_deductionLevelDes());
        query = query.replace("@DEDUCTIONVALUE", selection.getSummary_deductionValues().replace("'", "''"));
        query = query.replace("@FREQUENCYSELECTED", selection.getSummary_FrequencyName());
        query = query.replace("@STARTPERIOD", selection.getFromDate());
        query = query.replace("@ENDPERIOD", selection.getToDate());
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));

        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;
    }

    private List<String> addTotalAndCumulative(List<String> selectedVariables, List<String> selectedTotal) {
        if (selectedTotal.contains("Total")) {
            selectedVariables.add("Total");
        }
        if (selectedTotal.contains("Cumulative Balance")) {
            selectedVariables.add("Cumulative Balance");
        }
        return selectedTotal;
    }
    
    protected abstract String getCommonQueryName();

    protected abstract String getLoadDataQueryName();

    protected abstract String getCountQueryName();

    protected abstract String getTotalQueryName();
    
    protected abstract String getExcelQueryName();
    
    protected abstract DataResult getCustomizedData(SelectionDTO data, List list);
    
    public abstract List<Map<String, AdjustmentDTO>> bsExcelCustomizer(final List resultList, final Object[] object, final List<String> visibleColumns, final boolean isFixedColumns, final int interval, final int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException ;
    
}