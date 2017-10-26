/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
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
 * @author srithar
 */
public class SummaryLogic<T extends AdjustmentDTO> extends AbstractSummaryLogic<T> {

    public Object[] getLeftTableHeaders() {
        Object[] finalList = new Object[NumericConstants.FIVE];
        Object[] doubleColumns = {ARMUtils.CUSTOMERORPRODUCT_COLUMN};
        String[] doubleHeaders = {StringUtils.EMPTY};
        Object[] columns = {ARMUtils.CUSTOMERORPRODUCT_COLUMN};
        String[] headers = {ARMUtils.CUSTOMER_SMALL};
        Map<String, Object[]> headerMap = new HashMap<>();
        Object[] visibleList = new Object[1];
        visibleList[0] = ARMUtils.CUSTOMERORPRODUCT_COLUMN;
        headerMap.put(ARMUtils.CUSTOMERORPRODUCT_COLUMN, visibleList);
        finalList[0] = columns;
        finalList[1] = headers;
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
            startMonth = ArrayUtils.indexOf(months, selection.getFromDate().substring(0, NumericConstants.THREE));
            endMonth = ArrayUtils.indexOf(months, selection.getToDate().substring(0, NumericConstants.THREE));
            starttYear = Integer.valueOf(selection.getFromDate().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
            endtYear = Integer.valueOf(selection.getToDate().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
        } else if (ARMUtils.frequencyVarables.ANNUALLY.toString().equals(selection.getFrequency())) {
            startMonth = 1;
            endMonth = NumericConstants.TWELVE;
            starttYear = Integer.valueOf(selection.getFromDate());
            endtYear = Integer.valueOf(selection.getToDate());
        }
        frmDate.setDate(1);
        toDate.setDate(NumericConstants.TWENTY_EIGHT);
        frmDate.setMonth(startMonth - 1);
        toDate.setMonth(endMonth - 1);
        frmDate.setYear(starttYear - NumericConstants.ONE_NINE_ZERO_ZERO);
        toDate.setYear(endtYear - NumericConstants.ONE_NINE_ZERO_ZERO);
        int totalMonth = getMonthsDifference(frmDate, toDate);
        if (isHeaderIsAvail(frmDate, toDate, totalMonth, selection.getSelectedAdjustmentTypeValues())) {
            int frequencyDivision = 1;
            List finalList = new ArrayList();
            finalList = new ArrayList();
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
                frequencyDivision = 1;
            } else if (ARMUtils.frequencyVarables.ANNUALLY.toString().equals(selection.getFrequency())) {
                frequencyDivision = NumericConstants.TWELVE;
            }
            int index = 0;
            List tempList = new ArrayList<>();
            String column = null;
            String doubleColumn = null;
            int year = frmDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
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
                        doubleColumn = months[tempPeriod - 1] + year;
                        doubleHeaders.add(months[tempPeriod - 1] + " " + year);
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
                for (int j = 0; j < adjustMentIds.size(); j++) {
                    switch (frequencyDivision) {
                        case NumericConstants.THREE: {
                            String adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = ARMUtils.Q + tempPeriod + year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                            visibleHeaders.add(adjType);
                            tempList.add(column);
                            visibleColumn.add(column);
                            break;
                        }
                        case NumericConstants.SIX: {
                            String adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = ARMUtils.S + tempPeriod + year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                            visibleHeaders.add(adjType);
                            tempList.add(column);
                            visibleColumn.add(column);
                            break;
                        }
                        case 1: {
                            String adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = months[tempPeriod - 1] + year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                            visibleHeaders.add(adjType);
                            tempList.add(column);
                            visibleColumn.add(column);
                            break;
                        }
                        case NumericConstants.TWELVE: {
                            String adjType = selection.getSelectedAdjustmentTypeValues().get(j);
                            column = year + selection.getSelectedAdjustmentTypeValues().get(j).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY) + ARMUtils.DOT + index;
                            visibleHeaders.add(adjType);
                            tempPeriod = year;
                            tempList.add(column);
                            visibleColumn.add(column);
                            break;
                        }
                        default:
                            break;
                    }
                    headerVlaueMap.put(StringUtils.EMPTY + tempPeriod + year + adjustMentIds.get(j).toString(), column);
                    index++;
                }
                headerMap.put(doubleColumn, tempList.toArray());
                excelHeaderMap.put(doubleColumn, tempList.toArray());
                tempPeriod++;
            }
            selection.setHeaderVisibleColumnMap(headerVlaueMap);
            selection.setExcelVisibleColumn(visibleColumn);
            finalList.add(visibleColumn);
            finalList.add(visibleHeaders);
            finalList.add(doubleColumns);
            finalList.add(doubleHeaders);
            finalList.add(headerMap);
            finalList.add(excelHeaderMap);
            return finalList;
        }
        return null;
    }

    public static final int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * NumericConstants.TWELVE + date1.getMonth();
        int m2 = date2.getYear() * NumericConstants.TWELVE + date2.getMonth();
        return m2 - m1;
    }

    private boolean isHeaderIsAvail(Date frmDate, Date toDate, int totalMonth, List<String> selectedAdjustmentType) {
        LOGGER.debug("selectedAdjustmentType.size()-->>" + selectedAdjustmentType.size()+"frmDate.after(toDate)-->>" + frmDate.after(toDate)+"totalMonth-->>" + totalMonth);
        if (selectedAdjustmentType.size() > 0 && (toDate.equals(frmDate) || toDate.after(frmDate))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO data) {
        try {
            SummarySelection selection = (SummarySelection) data;
            Object[] returnObj = new Object[NumericConstants.TWO];
            String rebateRecord = StringUtils.EMPTY;
            List<Object> inputs = new ArrayList<>();
            inputs.add(selection.getDataSelectionDTO().getProjectionId());
            inputs.add(ConstantsUtils.SELECT_ONE.equalsIgnoreCase(selection.getStatus()) ? StringUtils.EMPTY : selection.getStatus().replace("[", "").replace("]", ""));
            inputs.add(selection.getFrequency());
            inputs.add(CommonLogic.listToString(selection.getSelectedAdjustmentType()));
            selection.setMasterSids(ARMUtils.getMasterIdsMap());
            String nextLevel = StringUtils.EMPTY;
            if (dto instanceof AdjustmentDTO) {
                TreeMap<String, Integer> masterSids;
                AdjustmentDTO val = (AdjustmentDTO) dto;
                int levelNo = val.getLevelNo();
                LOGGER.debug("levelNo-->>" + levelNo + "selection.getSummaryLevel()-->>" + selection.getSummaryLevel()+"selection.getSummaryLevel().get(levelNo)-->>" + selection.getSummaryLevel().get(levelNo));
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
            rebateRecord = "WHERE" + ARMUtils.getDeductionValuesMap().get(selection.getSummary_deductionLevelDes()) + " IN ('" + StringUtils.join(selection.getDeductionVariableIds(), "','") + "' )";
            inputs.add(selection.getFromDate());
            inputs.add(selection.getToDate());
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            inputs.add(ARMUtils.getDeductionValuesMapForLevel().get(selection.getSummary_deductionLevelDes()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            inputs.add(selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : selection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()));
            inputs.add(ConstantsUtils.SELECT_ONE.equalsIgnoreCase(selection.getStatus()) ? " LEFT JOIN " : " JOIN ");
            inputs.add(rebateRecord);
            returnObj[0] = inputs;
            returnObj[1] = new TreeMap();

            return returnObj;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean totalFlag = false;
        if ((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))
                && (criteria.getCurrentPage() == criteria.getLastPage()) && criteria.getSelectionDto().getSummary_levelFilterNo() == 0&& (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) {
            totalFlag = true;
            int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
            inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> list = QueryUtils.getItemData(inputs, VariableConstants.ADJUSTMENT_SUMMARY, "AdjustmnetSummaryLoadData");
        DataResult<T> result = getCustomizedData(criteria.getSelectionDto(), list);
        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = inputs;
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
        if (count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getSelectionDto().getSummary_levelFilterNo() == 0)) {
            count = count + 1;
        }
        LOGGER.debug("count-->>" + count);
        return count;
    }

    private DataResult<T> getCustomizedData(SelectionDTO data, List<Object[]> list) {
        SummarySelection selection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId = StringUtils.EMPTY;
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
                dto.setBrand_item_masterSid(mastersId);
                dto.setMasterIds(selection.getMasterSids());
                dto.setLevelNo(selection.getLevelNo());
                dto.setGroup(String.valueOf(list1[0]));
                dto.setChildrenAllowed((!"Total".equalsIgnoreCase(dto.getGroup()) && selection.getSummary_levelFilterNo() == 0) ? isChild : false);
            }

            int period = Integer.valueOf(list1[NumericConstants.TWO].toString());
            int year = Integer.valueOf(list1[NumericConstants.THREE].toString());
            String temp = StringUtils.EMPTY + period + year + list1[NumericConstants.FOUR];
            if (headerValueMap.get(temp) != null) {
                dto.addStringProperties(headerValueMap.get(temp), list1[NumericConstants.FIVE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(list1[NumericConstants.FIVE]))));
            }
            lastMasterSid = mastersId;
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<T>();
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
        query = query.replace("@DEDUCTIONLEVEL", selection.getSummary_deductionLevelDes());
        query = query.replace("@DEDUCTIONVALUE", selection.getSummary_deductionValues().replace("'", "''"));
        query = query.replace("@FREQUENCYSELECTED", selection.getSummary_FrequencyName());
        query = query.replace("@STARTPERIOD", selection.getFromDate());
        query = query.replace("@ENDPERIOD", selection.getToDate());
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        query = query.replace("@ADJUSTMENTTYPE", StringUtils.join(selection.getSelectedAdjustmentTypeValues().toArray(), ",").toUpperCase());
        String status="0".equals(selection.getSummary_StatusID()) ? StringUtils.EMPTY : selection.getSummary_StatusID();
            status=status.replace("[", "").replace("]", "");
            query = query.replace("@WORKFLOWSTATUS", status);
        /**
         * Currently we can select only single work flow status so we should not
         * do left join for work flow status If they change the work flow status
         * column to multi select the we should do left join. The below query
         * replace is for future use.
         */
        query = query.replace("@LEFT_JOIN", "0".equals(selection.getSummary_StatusID()) ? " LEFT " : StringUtils.EMPTY);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    }
