/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary.logic;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
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
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;

import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.util.Locale;

/**
 *
 * @author srithar
 * @param <T>
 */
public class SummaryLogic<T extends AdjustmentDTO> extends AbstractSummaryLogic<T> {

    public Object[] getLeftTableHeaders() {
        Object[] summaryFinalList = new Object[NumericConstants.FIVE];
        Object[] summaryDoubleColumns = {ARMUtils.CUSTOMERORPRODUCT_COLUMN};
        String[] summaryDoubleHeaders = {StringUtils.EMPTY};
        Object[] summaryColumns = {ARMUtils.CUSTOMERORPRODUCT_COLUMN};
        String[] summaryHeaders = {ARMUtils.CUSTOMER_SMALL};
        Map<String, Object[]> headerMap = new HashMap<>();
        Object[] visibleList = new Object[1];
        visibleList[0] = ARMUtils.CUSTOMERORPRODUCT_COLUMN;
        headerMap.put(ARMUtils.CUSTOMERORPRODUCT_COLUMN, visibleList);
        summaryFinalList[0] = summaryColumns;
        summaryFinalList[1] = summaryHeaders;
        summaryFinalList[NumericConstants.TWO] = summaryDoubleColumns;
        summaryFinalList[NumericConstants.THREE] = summaryDoubleHeaders;
        summaryFinalList[NumericConstants.FOUR] = headerMap;
        return summaryFinalList;
    }

    public List getRightTableHeaders(SummarySelection selection) {

        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] summaryMonths = dateFormatSymbols.getShortMonths();
        Date summaryFrmDate = new Date();
        Date summaryToDate = new Date();
        int startMonth = 0;
        int endMonth = 0;
        int endtYear = 0;
        int starttYear = 0;
        if (ARMUtils.frequencyVarables.QUARTERLY.toString().equals(selection.getFrequency())) {
            startMonth = selection.getFromDate().charAt(1) - NumericConstants.FORTY_EIGHT;
            startMonth = 1 + (NumericConstants.THREE * (startMonth - 1));
            endMonth = selection.getToDate().charAt(1) - NumericConstants.FORTY_EIGHT;
            endMonth = 1 + (NumericConstants.THREE * (endMonth - 1));
            starttYear = Integer.valueOf(selection.getFromDate().substring(NumericConstants.THREE, NumericConstants.SEVEN));
            endtYear = Integer.valueOf(selection.getToDate().substring(NumericConstants.THREE, NumericConstants.SEVEN));
        } else if (ARMUtils.frequencyVarables.SEMI_ANNUALLY.toString().equals(selection.getFrequency())) {
            startMonth = selection.getFromDate().charAt(1) - NumericConstants.FORTY_EIGHT;
            startMonth = 1 + (NumericConstants.SIX * (startMonth - 1));
            endMonth = selection.getToDate().charAt(1) - NumericConstants.FORTY_EIGHT;
            endMonth = 1 + (NumericConstants.SIX * (endMonth - 1));
            starttYear = Integer.valueOf(selection.getFromDate().substring(NumericConstants.THREE, NumericConstants.SEVEN));
            endtYear = Integer.valueOf(selection.getToDate().substring(NumericConstants.THREE, NumericConstants.SEVEN));
        } else if (ARMUtils.frequencyVarables.MONTHLY.toString().equals(selection.getFrequency())) {
            startMonth = ArrayUtils.indexOf(summaryMonths, selection.getFromDate().substring(0, NumericConstants.THREE));
            endMonth = ArrayUtils.indexOf(summaryMonths, selection.getToDate().substring(0, NumericConstants.THREE));
            starttYear = Integer.valueOf(selection.getFromDate().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
            endtYear = Integer.valueOf(selection.getToDate().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
        } else if (ARMUtils.frequencyVarables.ANNUALLY.toString().equals(selection.getFrequency())) {
            startMonth = 1;
            endMonth = NumericConstants.TWELVE;
            starttYear = Integer.valueOf(selection.getFromDate());
            endtYear = Integer.valueOf(selection.getToDate());
        }
        summaryFrmDate.setDate(1);
        summaryToDate.setDate(NumericConstants.TWENTY_EIGHT);
        summaryFrmDate.setMonth(startMonth - 1);
        summaryToDate.setMonth(endMonth - 1);
        summaryFrmDate.setYear(starttYear - NumericConstants.ONE_NINE_ZERO_ZERO);
        summaryToDate.setYear(endtYear - NumericConstants.ONE_NINE_ZERO_ZERO);
        int totalMonth = getMonthsDifference(summaryFrmDate, summaryToDate);
        if (isHeaderIsAvail(summaryFrmDate, summaryToDate, totalMonth, selection.getSelectedAdjustmentTypeValues())) {
            List summaryFinalList;
            summaryFinalList = new ArrayList();
            List doubleColumns = new ArrayList();
            List doubleHeaders = new ArrayList();
            List visibleHeaders = new ArrayList();
            Map<String, Object[]> headerMap = new HashMap<>();
            Map<String, Object[]> excelHeaderMap = new HashMap<>();
            excelHeaderMap.put("group", new Object[]{"group"});
            List visibleColumn = new ArrayList<>();
            int frequencyDivision = getFreqDivision(selection);
            int index = 0;
            List tempList = new ArrayList<>();
            String column = null;
            String doubleColumn = null;
            int year = summaryFrmDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
            int tempPeriod = (startMonth / frequencyDivision) + 1;
            HashMap<Object, String> headerVlaueMap = new HashMap<>();
            List adjustMentIds = selection.getSelectedAdjustmentType();
            for (int i = 0; i < (totalMonth / frequencyDivision) + 1; i++) {
                if (tempPeriod > (NumericConstants.TWELVE / frequencyDivision)) {
                    tempPeriod = 1;
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
                    case 1:
                        doubleColumn = summaryMonths[tempPeriod - 1] + year;
                        doubleHeaders.add(summaryMonths[tempPeriod - 1] + " " + year);
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
                for (int j = 0; j < adjustMentIds.size(); j++) {
                    String adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                    switch (frequencyDivision) {
                        case NumericConstants.THREE:
                            column = getRightTableHeadersColumn(ARMUtils.Q, year, selection, index, tempPeriod, j);
                            break;

                        case NumericConstants.SIX:
                            getRightTableHeadersColumn(ARMUtils.S, year, selection, index, tempPeriod, j);
                            break;

                        case 1:
                            column = getRightTableHeadersColumn(summaryMonths[tempPeriod - 1], year, selection, index, 0, j);
                            break;

                        case NumericConstants.TWELVE:
                            column = getRightTableHeadersColumn(StringUtils.EMPTY, year, selection, index, tempPeriod, j);
                            break;

                        default:
                            break;
                    }
                    visibleHeaders.add(adjType);
                    tempList.add(column);
                    visibleColumn.add(column);

                    headerVlaueMap.put(StringUtils.EMPTY + tempPeriod + year + adjustMentIds.get(j).toString(), column);
                    index++;
                }
                headerMap.put(doubleColumn, tempList.toArray());
                excelHeaderMap.put(doubleColumn, tempList.toArray());
                tempPeriod++;
            }
            selection.setHeaderVisibleColumnMap(headerVlaueMap);
            selection.setExcelVisibleColumn(visibleColumn);
            summaryFinalList.add(visibleColumn);
            summaryFinalList.add(visibleHeaders);
            summaryFinalList.add(doubleColumns);
            summaryFinalList.add(doubleHeaders);
            summaryFinalList.add(headerMap);
            summaryFinalList.add(excelHeaderMap);
            return summaryFinalList;
        }
        return Collections.emptyList();
    }

    private int getFreqDivision(SummarySelection selection) {
        int frequencyDivision = NumericConstants.ONE;
        if (ARMUtils.frequencyVarables.QUARTERLY.toString().equals(selection.getFrequency())) {
            frequencyDivision = NumericConstants.THREE;
        } else if (ARMUtils.frequencyVarables.SEMI_ANNUALLY.toString().equals(selection.getFrequency())) {
            frequencyDivision = NumericConstants.SIX;
        } else if (ARMUtils.frequencyVarables.MONTHLY.toString().equals(selection.getFrequency())) {
            frequencyDivision = 1;
        } else if (ARMUtils.frequencyVarables.ANNUALLY.toString().equals(selection.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        }
        return frequencyDivision;
    }

    public static final int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * NumericConstants.TWELVE + date1.getMonth();
        int m2 = date2.getYear() * NumericConstants.TWELVE + date2.getMonth();
        return m2 - m1;
    }

    private boolean isHeaderIsAvail(Date frmDate, Date toDate, int totalMonth, List<String> selectedAdjustmentType) {
        LOGGER.debug("selectedAdjustmentType.size()-->>{}", selectedAdjustmentType.size());
        LOGGER.debug("totalMonth-->{}", totalMonth);
        if (!selectedAdjustmentType.isEmpty() && (toDate.equals(frmDate) || toDate.after(frmDate))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO data) {
        SummarySelection selection = (SummarySelection) data;
        Object[] returnObj = new Object[NumericConstants.TWO];
        String rebateRecord;
        List<Object> inputs = new ArrayList<>();
        inputs.add(selection.getDataSelectionDTO().getProjectionId());
        inputs.add(GlobalConstants.getSelectOne().equalsIgnoreCase(selection.getStatus()) ? StringUtils.EMPTY : selection.getStatus().replace("[", "").replace("]", ""));
        inputs.add(selection.getFrequency());
        inputs.add(CommonLogic.listToString(selection.getSelectedAdjustmentType()));
        selection.setMasterSids(ARMUtils.getMasterIdsMap());
        String nextLevel = getNextLevel(dto, selection);
        inputs.add(nextLevel);
        rebateRecord = "WHERE" + ARMUtils.getDeductionValuesMap().get(selection.getSummarydeductionLevelDes()) + " IN ('" + StringUtils.join(selection.getDeductionVariableIds(), "','") + "' )";
        inputs.add(selection.getFromDate());
        inputs.add(selection.getToDate());
        inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        inputs.add(ARMUtils.getDeductionValuesMapForLevel().get(selection.getSummarydeductionLevelDes()));
        inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()));
        inputs.add(GlobalConstants.getSelectOne().equalsIgnoreCase(selection.getStatus()) ? " LEFT JOIN " : " JOIN ");
        inputs.add(rebateRecord);
        returnObj[0] = inputs;
        returnObj[1] = new TreeMap();
        return returnObj;
    }

    private String getNextLevel(Object dto, SummarySelection selection) {
        String summaryNextLevel;
        if (dto instanceof AdjustmentDTO) {
            TreeMap<String, Integer> masterSids;
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            masterSids = (TreeMap<String, Integer>) val.getMasterIds().clone();
            masterSids.put(selection.getSummaryLevel().get(levelNo), Integer.valueOf(val.getBranditemmasterSid()));
            selection.setMasterSids(masterSids);
            if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummaryLevel().get(++levelNo))) {
                summaryNextLevel = selection.getSummarydeductionLevelDes();
            } else {
                summaryNextLevel = selection.getSummaryLevel().get(levelNo);
            }
            selection.setSummaryviewType(selection.getSummaryLevel().get(levelNo));
            selection.setLevelNo(levelNo);
        } else {
            selection.setLevelNo(1);

            summaryNextLevel = getNextLevelAdjustmentDto(selection);
            // This condition will work for Value Ddlb
            if (selection.getSummaryvalueSid() != 0) {
                selection.getMasterSids().put(selection.getSummarylevelFilterValue(), selection.getSummaryvalueSid());
            } else if (selection.getSummarylevelFilterNo() != 0) {
                selection.getMasterSids().put(selection.getSummarylevelFilterValue(), null);
            }
            summaryNextLevel = getNextLevelFilter(selection, summaryNextLevel);

        }
        return summaryNextLevel;
    }

    private String getNextLevelFilter(SummarySelection selection, String summarynewLevel) {
        String summaryNextLevel = summarynewLevel;
        //This will ovverride the default first Level (For Level Filter)
        if (selection.getSummarylevelFilterNo() != 0) {
            if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummarylevelFilterValue())) {
                summaryNextLevel = selection.getSummarydeductionLevelDes();
            } else {
                summaryNextLevel = selection.getSummarylevelFilterValue();
            }
        }
        return summaryNextLevel;
    }

    private String getNextLevelAdjustmentDto(SummarySelection selection) {
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
        if ((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))
                && (criteria.getCurrentPage() == criteria.getLastPage()) && criteria.getSelectionDto().getSummarylevelFilterNo() == 0 && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) {
            totalFlag = true;
            int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
            inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> list = QueryUtils.getItemData(inputs, VariableConstants.ADJUSTMENT_SUMMARY, "AdjustmnetSummaryLoadData");
        DataResult<T> result = getCustomizedData(criteria.getSelectionDto(), list);
        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = new ArrayList(inputs);
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            totaldata = QueryUtils.getItemData(totalInputs, VariableConstants.ADJUSTMENT_SUMMARY, "AdjustmnetSummary_LoadTotalData");
            List<T> l = result.getDataResults();
            l.addAll((getCustomizedData(criteria.getSelectionDto(), totaldata)).getDataResults());
            result.setDataResults(l);
        }
        return result;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        int count = 0;
        count = CommonLogic.getCount(QueryUtils.getItemData(inputs, VariableConstants.ADJUSTMENT_SUMMARY, "AdjustmnetSummaryLoadCount"));
        if (count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            count = count + 1;
        }
        LOGGER.debug("count-->>{}", count);
        return count;
    }

    private DataResult<T> getCustomizedData(SelectionDTO data, List<Object[]> list) {
        SummarySelection selection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        List finalList = new ArrayList();
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        AdjustmentDTO dto = null;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0");
        boolean isChild = !Collections.max(selection.getSummaryLevel().keySet()).equals(selection.getLevelNo());
        for (Object[] list1 : list) {
            mastersId = String.valueOf(list1[1]);
            if (!lastMasterSid.equals(mastersId) || dto == null) {
                dto = new AdjustmentDTO();
                finalList.add(dto);
                dto.setBranditemmasterSid(mastersId);
                dto.setMasterIds(selection.getMasterSids());
                dto.setLevelNo(selection.getLevelNo());
                dto.setGroup(String.valueOf(list1[0]));
                dto.setChildrenAllowed((!"Total".equalsIgnoreCase(dto.getGroup()) && selection.getSummarylevelFilterNo() == 0) ? isChild : false);
            }

            int period = Integer.parseInt(list1[NumericConstants.TWO].toString());
            int year = Integer.parseInt(list1[NumericConstants.THREE].toString());
            String temp = StringUtils.EMPTY + period + year + list1[NumericConstants.FOUR];
            if (headerValueMap.get(temp) != null) {
                dto.addStringProperties(headerValueMap.get(temp), list1[NumericConstants.FIVE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(list1[NumericConstants.FIVE]))));
            }
            lastMasterSid = mastersId;
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(finalList);
        return dataResult;
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
        String query = SQlUtil.getQuery("getASSummaryExcelQuery");
        Object[] value = selection.getExcelHierarchy();
        query = query.replace("@LEVEL_VAL", StringUtils.join(value, ","));
        query = query.replace("@DEDUCTIONLEVEL", selection.getSummarydeductionLevelDes());
        query = query.replace("@DEDUCTIONVALUE", selection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@FREQUENCYSELECTED", selection.getSummaryFrequencyName());
        query = query.replace("@STARTPERIOD", selection.getFromDate());
        query = query.replace("@ENDPERIOD", selection.getToDate());
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        query = query.replace("@ADJUSTMENTTYPE", StringUtils.join(selection.getSelectedAdjustmentTypeValues().toArray(), ",").toUpperCase(Locale.ENGLISH));
        String status = "0".equals(selection.getSummaryStatusID()) ? StringUtils.EMPTY : selection.getSummaryStatusID();
        status = status.replace("[", "").replace("]", "");
        query = query.replace("@WORKFLOWSTATUS", status);
        /**
         * Currently we can select only single work flow status so we should not
         * do left join for work flow status If they change the work flow status
         * column to multi select the we should do left join. The below query
         * replace is for future use.
         */
        query = query.replace("@LEFT_JOIN", "0".equals(selection.getSummaryStatusID()) ? " LEFT " : StringUtils.EMPTY);
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    private String getRightTableHeadersColumn(String q, int year, SummarySelection selection, int index, int tempPeriod, int j) {
        String frontVal;
        int tempPeriods;
        if (q.isEmpty()) {
            tempPeriods = year;
            LOGGER.debug(String.valueOf(tempPeriods));
            frontVal = StringUtils.EMPTY;
        } else {
            tempPeriods = tempPeriod;
            frontVal = q + (tempPeriods == 0 ? StringUtils.EMPTY : tempPeriods);
        }
        return frontVal + year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
    }

}
