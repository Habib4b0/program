/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.singleliablity.logic;

import com.stpl.app.arm.balancesummaryreport.BalanceSummaryReportUI;
import com.stpl.app.arm.balancesummaryreport.logic.AbstractBSummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Sathya.Seelan
 */
public class SingleLiablityLogic extends AbstractBSummaryLogic {

    private String[] columns = {"Fees Accrual", "Inflation Adjustment", "Credit Card Fees", "Other Fixed Dollar Fees",
        "Inventory Valuation", "Payment True-up", VariableConstants.PAYMENTS, "Period Balance"};

    public SingleLiablityLogic() {
        super();
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getCommonQueryName() {
        return "BSummaryCommonQuery-SingleLiablity";

    }

    @Override
    protected String getLoadDataQueryName() {
        return "BSummaryLoadData-SingleLiablity";
    }

    @Override
    protected String getCountQueryName() {
        return "BSummaryCount-SingleLiablity";
    }

    @Override
    protected String getTotalQueryName() {
        return "BSummaryLoadTotalData-SingleLiablity";
    }

    @Override
    protected String getExcelQueryName() {
        return "getBSummaryExcelQuery-SingleLiablity";
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO singleLiablitySelection) {
        BalanceSummaryReportUI.setExcelClose(true);
        String query = SQlUtil.getQuery(getExcelQueryName());
        Object[] value = singleLiablitySelection.getExcelHierarchy();
        query = query.replace("@LEVEL_VAL", StringUtils.join(value, ","));
        query = query.replace("@DEDUCTIONLEVEL", singleLiablitySelection.getSummarydeductionLevelDes());
        query = query.replace("@DEDUCTIONVALUE", singleLiablitySelection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@FREQUENCYSELECTED", singleLiablitySelection.getSummaryFrequencyName());
        query = query.replace("@STARTPERIOD", singleLiablitySelection.getFromDate());
        query = query.replace("@ENDPERIOD", singleLiablitySelection.getToDate());
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(singleLiablitySelection.getDataSelectionDTO().getProjectionId()));
        query = query.replace("@USERID", String.valueOf(singleLiablitySelection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(singleLiablitySelection.getSessionDTO().getSessionId()));
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    protected DataResult getCustomizedData(SelectionDTO data, List list) {
        List<Object[]> listObj = new ArrayList<>(list);
        SummarySelection selection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        List finalList = new ArrayList();
        AdjustmentDTO singleLiablityAdjustmentdto = null;
        boolean isChild = !Collections.max(selection.getSummaryLevel().keySet()).equals(selection.getLevelNo());
        int size = listObj.size();
        Double[] total = new Double[8];
        int index = 0;
        for (int i = 0; i < size; i++) {
            Object[] list1 = listObj.get(i);
            mastersId = String.valueOf(list1[1]);
            index = (i + 1) % (selection.getSummaryPeriods() + 1);
            if (!lastMasterSid.equals(mastersId) || singleLiablityAdjustmentdto == null) {
                singleLiablityAdjustmentdto = new AdjustmentDTO();
                finalList.add(singleLiablityAdjustmentdto);
                singleLiablityAdjustmentdto.setBranditemmasterSid(mastersId);
                singleLiablityAdjustmentdto.setMasterIds(selection.getMasterSids());
                singleLiablityAdjustmentdto.setLevelNo(selection.getLevelNo());
                singleLiablityAdjustmentdto.setGroup(String.valueOf(list1[0]));
                singleLiablityAdjustmentdto.setChildrenAllowed((!CommonConstant.TOTAL.equalsIgnoreCase(singleLiablityAdjustmentdto.getGroup()) && selection.getSummarylevelFilterNo() == 0) ? isChild : false);
            }
            singleLiablityAdjustmentdto = customizeListView(singleLiablityAdjustmentdto, list1, selection, total);
            if (index == 0) {
                singleLiablityAdjustmentdto = customizeTotal(singleLiablityAdjustmentdto, total, selection);
                total = new Double[8];
            }
            lastMasterSid = mastersId;
        }
        OriginalDataResult dataResult = new OriginalDataResult();
        dataResult.setDataResults(finalList);
        return dataResult;
    }

    @Override
    public List bsExcelCustomizer(List deductionList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO singleLiablitySelection) throws IllegalAccessException, InvocationTargetException {
        List<String> visibleColumnsList = new ArrayList<>(visibleColumns);
        int j = 1;
        int keyParam = j;
        String oldC = StringUtils.EMPTY;
        String newC;
        String column;
        AdjustmentDTO dto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        String key = "0.";
        Double[] total = new Double[8];
        int totalListModIndex = 1;
        if (deductionList != null && !deductionList.isEmpty()) {
            for (int i = 0; i < deductionList.size(); i++) {
                Object[] resultSet = (Object[]) deductionList.get(i);
                totalListModIndex = (i + 1) % (singleLiablitySelection.getSummaryPeriods() + 1);
                newC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                if (!"0".equals(newC)) {
                    if (!"null".equals(newC)) {
                        if (!oldC.equals(newC) && object.length > keyParam) {
                            keyParam++;
                            if ("null".equalsIgnoreCase(String.valueOf(resultSet[(j + 1) * NumericConstants.TWO]))) {
                                j++;
                            }
                        }
                    } else if (!"null".equals(oldC)) {
                        j = 1;
                        keyParam = 1;
                    }
                    key = ExcelUtils.getKey(resultSet, keyParam).replace(" ", StringUtils.EMPTY).replace(" ", StringUtils.EMPTY);
                    newC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                    oldC = newC;
                }
                size = mapList.size();
                for (int l = 0; l < size; l++) {
                    dto = mapList.get(l).get(key);
                    if (dto != null) {
                        break;
                    }
                }
                if (dto == null) {
                    dto = new AdjustmentDTO();
                    Map<String, AdjustmentDTO> map = mapList.get(size - 1);
                    if (map.size() >= NumericConstants.THOUSAND) {
                        map = new HashMap<>();
                        mapList.add(map);
                    }
                    map.put(key, dto);
                }
                int indicator = 0;
                Object value = null;
                Object result = null;
                if (ARMConstants.getDeductionProduct().equals(singleLiablitySelection.getSummaryDeductionView())) {
                    result = resultSet[6];
                    indicator = (Integer) resultSet[resultSet.length - 1];
                } else if (ARMConstants.getDeductionCustomer().equals(singleLiablitySelection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(singleLiablitySelection.getSummaryDeductionView())) {
                    result = resultSet[8];
                    indicator = (Integer) resultSet[resultSet.length - 1];
                } else if (ARMConstants.getDeductionCustomerContract().equals(singleLiablitySelection.getSummaryDeductionView())) {
                    result = resultSet[10];
                    indicator = (Integer) resultSet[resultSet.length - 1];
                }
                total = indicator == 1 ? new Double[8] : total;
                String headerKey = String.valueOf(result).replace(" ", "").replace("-", StringUtils.EMPTY);
                for (int k = 0; k < visibleColumns.size(); k++) {
                    column = visibleColumnsList.get(k).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    headerKey = (column.contains(VariableConstants.BEGINNING_BALANCE) && indicator == 2) ? VariableConstants.BEGINNING_BALANCE : headerKey;
                    String gatheredColumn = StringUtils.EMPTY;
                    List<String> columnList = CommonLogic.getInstance().getSingleLiablityColumns();
                    List<String> totalColumnList = CommonLogic.getInstance().getTotalSingleLiablityColumns();
                    for (int l = 0; l < columnList.size(); l++) {
                        int index = l % 8;
                        if (column.contains(columnList.get(l))) {
                            gatheredColumn = getGatheredColumn(indicator, headerKey, column, totalListModIndex, columnList.get(l));
                            if (ARMConstants.getDeductionProduct().equals(singleLiablitySelection.getSummaryDeductionView())) {
                                value = column.contains(totalColumnList.get(l)) ? getNullTotalExcel(total[index]) : resultSet[index + 7];
                            } else if (ARMConstants.getDeductionCustomer().equals(singleLiablitySelection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(singleLiablitySelection.getSummaryDeductionView())) {
                                value = column.contains(totalColumnList.get(l)) ? getNullTotalExcel(total[index]) : resultSet[index + 9];
                            } else if (ARMConstants.getDeductionCustomerContract().equals(singleLiablitySelection.getSummaryDeductionView())) {
                                value = column.contains(totalColumnList.get(l)) ? getNullTotalExcel(total[index]) : resultSet[index + 11];
                            }
                        }
                    }
                    if (column.startsWith(gatheredColumn) && column.matches("[a-zA-Z0-9-~\\s]+\\.\\d+$")) {
                        total = !column.contains(ARMUtils.TOTAL) ? getTotal(total, column, value) : total;
                        dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                    }
                }
                total = totalListModIndex == 0 ? new Double[8] : total;
                dto.setGroup(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
                dto.setMonth(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
            }
        }
        return mapList;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        return Collections.emptyList();
    }

    @Override
    protected String getExcelTotalQueryName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO data) {
        Object[] returnObj = new Object[2];
        try {
            SummarySelection singleLiablitySelection = (SummarySelection) data;
            String rebateRecord;

            List<Object> inputs = new ArrayList<>();
            inputs.add(singleLiablitySelection.getDataSelectionDTO().getProjectionId());
            inputs.add(singleLiablitySelection.getFrequency());
            singleLiablitySelection.setMasterSids(ARMUtils.getMasterIdsMap());
            String nextLevel = getNextLevelSingleLiablity(dto, singleLiablitySelection);
            inputs.add(nextLevel);
            inputs.add(singleLiablitySelection.getFromDate());
            inputs.add(singleLiablitySelection.getToDate());
            inputs.add(singleLiablitySelection.getSessionDTO().getUserId());
            inputs.add(singleLiablitySelection.getSessionDTO().getSessionId());
            rebateRecord = "WHERE" + ARMUtils.getDeductionValuesMap().get(singleLiablitySelection.getSummarydeductionLevelDes()) + " IN ('" + StringUtils.join(singleLiablitySelection.getDeductionVariableIds(), "','") + "' )";
            inputs.add(singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            inputs.add(singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            inputs.add(singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            inputs.add(ARMUtils.getDeductionValuesMapForLevel().get(singleLiablitySelection.getSummarydeductionLevelDes()));
            inputs.add(singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            inputs.add(singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : singleLiablitySelection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()));
            inputs.add(rebateRecord);
            returnObj[0] = inputs;
            returnObj[1] = new TreeMap();
        } catch (NumberFormatException ex) {
            LOGGER.error("Error in generateInputs:", ex);
        }
        return returnObj;
    }

    private String getNextLevelSingleLiablity(Object dto, SummarySelection singleLiablitySelection) {
        String nextLevel;
        if (dto instanceof AdjustmentDTO) {
            TreeMap<String, Integer> masterSids;
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            masterSids = (TreeMap<String, Integer>) val.getMasterIds().clone();
            masterSids.put(singleLiablitySelection.getSummaryLevel().get(levelNo), Integer.valueOf(val.getBranditemmasterSid()));
            singleLiablitySelection.setMasterSids(masterSids);
            if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(singleLiablitySelection.getSummaryLevel().get(++levelNo))) {
                nextLevel = singleLiablitySelection.getSummarydeductionLevelDes();
            } else {
                nextLevel = singleLiablitySelection.getSummaryLevel().get(levelNo);
            }
            singleLiablitySelection.setSummaryviewType(singleLiablitySelection.getSummaryLevel().get(levelNo));
            singleLiablitySelection.setLevelNo(levelNo);
        } else {
            singleLiablitySelection.setLevelNo(1);
            nextLevel = getNextLevelDeduction(singleLiablitySelection);
            getNextLevelMasterSid(singleLiablitySelection);
            //This will ovverride the default first Level (For Level Filter)
            if (singleLiablitySelection.getSummarylevelFilterNo() != 0) {
                if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(singleLiablitySelection.getSummarylevelFilterValue())) {
                    nextLevel = singleLiablitySelection.getSummarydeductionLevelDes();
                } else {
                    nextLevel = singleLiablitySelection.getSummarylevelFilterValue();
                }
            }

        }
        return nextLevel;
    }

    private void getNextLevelMasterSid(SummarySelection selection) {
        if (selection.getSummaryvalueSid() != 0) {
            selection.getMasterSids().put(selection.getSummarylevelFilterValue(), selection.getSummaryvalueSid());
        } else if (selection.getSummarylevelFilterNo() != 0) {
            selection.getMasterSids().put(selection.getSummarylevelFilterValue(), null);
        }
    }

    private String getNextLevelDeduction(SummarySelection selection) {
        String nextLevel;
        if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummaryLevel().get(1))) {
            nextLevel = selection.getSummarydeductionLevelDes();
        } else {
            nextLevel = selection.getSummaryLevel().get(1);
        }
        return nextLevel;
    }

    public AdjustmentDTO customizeListView(AdjustmentDTO dto, Object[] list1, SummarySelection selection, Double[] total) {
        int period = Integer.parseInt(list1[NumericConstants.TWO].toString());
        int year = Integer.parseInt(list1[NumericConstants.THREE].toString());
        String headerKey = StringUtils.EMPTY + (period == 0 ? CommonConstant.BEGINNING_BALANCE : period) + (year == 0 ? StringUtils.EMPTY : year);
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        for (int i = 0; i < columns.length; i++) {
            if (selection.getSelectedAdjustmentTypeValues().contains(columns[i])) {
                dto.setDTOValues(headerValueMap, headerKey + columns[i], list1[i + 4], decimalformat);
                total[i] = getNullTotal(total[i]) + Double.valueOf(ARMUtils.NULL.equalsIgnoreCase(String.valueOf(list1[i + 4])) ? ARMUtils.ZERO_STRING : String.valueOf(list1[i + 4]));
            }
        }
        return dto;
    }

    public AdjustmentDTO customizeTotal(AdjustmentDTO dto, Double[] total, SummarySelection selection) {
        String headerKey = ARMUtils.TOTAL;
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        for (int i = 0; i < columns.length; i++) {
            if (selection.getSelectedAdjustmentTypeValues().contains(columns[i])) {
                dto.setDTOValues(headerValueMap, headerKey + columns[i], total[i], decimalformat);
            }
        }
        return dto;
    }

    private Double[] getTotal(Double[] total, String column, Object value) {
        int size = CommonLogic.getInstance().getSingleLiablityColumns().size();
        List<String> list = CommonLogic.getInstance().getSingleLiablityColumns();
        for (int i = 0; i < size; i++) {
            if (column.contains(list.get(i))) {
                total[i] = getTotalForExcel(total[i], value);
            }
        }
        return total;
    }

    private String getGatheredColumn(int indicator, String headerKey, String column, int totListModIndex, String colValue) {
        boolean singleLiablityBegBalFlag = indicator == 2;
        boolean singleLiablityTotalFlag = totListModIndex == 0;
        String value;
        if (singleLiablityBegBalFlag && singleLiablityTotalFlag) {
            value = column.contains(ARMUtils.TOTAL) ? column : VariableConstants.BEGINNING_BALANCE + colValue;
        } else {
            String columnValue = singleLiablityTotalFlag && column.contains(ARMUtils.TOTAL) ? column : headerKey + colValue;
            value = singleLiablityBegBalFlag ? VariableConstants.BEGINNING_BALANCE + colValue
                    : columnValue;
        }
        return value;
    }
}
