/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.logic;

import com.stpl.app.arm.balancesummaryreport.BalanceSummaryReportUI;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryDemandLogic extends AbstractBSummaryLogic {

    private DecimalFormat percentformat = new DecimalFormat("#,##0.00'%'");
    public static final Logger DEMAND_LOGGER = LoggerFactory.getLogger(BSummaryDemandLogic.class);

    private String[] columns = {"Demand Accrual", "Demand Reforecast", "Payment True-up", "Actual Payments", "Period Balance", ARMUtils.PAYMENT_RATIO};

    public BSummaryDemandLogic() {
        super();
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        DEMAND_LOGGER.debug("Inside Demand getQueryTableinput");
        return Collections.emptyList();
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        DEMAND_LOGGER.debug("Inside Demand getTableInput");
        return Collections.emptyList();
    }

    @Override
    protected String getCommonQueryName() {
        DEMAND_LOGGER.debug("Inside Demand getCommonQueryName");
        return "BSummaryCommonQuery-Demand";
    }

    @Override
    protected String getLoadDataQueryName() {
        DEMAND_LOGGER.debug("Inside Demand getLoadDataQueryName");
        return "BSummaryLoadData-Demand";
    }

    @Override
    protected String getCountQueryName() {
        DEMAND_LOGGER.debug("Inside Demand getCountQueryName");
        return "BSummaryCount-Demand";
    }

    @Override
    protected String getTotalQueryName() {
        DEMAND_LOGGER.debug("Inside Demand getTotalQueryName");
        return "BSummaryLoadTotalData-Demand";
    }

    @Override
    protected String getExcelQueryName() {
        DEMAND_LOGGER.debug("Inside Demand getExcelQueryName");
        return "getBSummaryExcelQuery-Demand";
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        DEMAND_LOGGER.debug("Inside Demand getQueryTableinputparameter");
        return Collections.emptyList();
    }

    @Override
    protected String getExcelTotalQueryName() {
        DEMAND_LOGGER.debug("Inside Demand getExcelTotalQueryName");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO data) {
        Object[] returnObj = new Object[2];
        try {
            SummarySelection demandSelection = (SummarySelection) data;
            String rebateRecord;
            List<Object> inputs = new ArrayList<>();
            inputs.add(demandSelection.getDataSelectionDTO().getProjectionId());
            inputs.add(demandSelection.getFrequency());
            demandSelection.setMasterSids((TreeMap<String, Integer>) ARMUtils.getMasterIdsMap());
            inputs = BSummaryDemandLogic.this.getNextLevelDemand(inputs, dto, demandSelection);
            inputs.add(demandSelection.getFromDate());
            inputs.add(demandSelection.getToDate());
            inputs.add(demandSelection.getSessionDTO().getUserId());
            inputs.add(demandSelection.getSessionDTO().getSessionId());
            rebateRecord = "WHERE" + ARMUtils.getDeductionValuesMap().get(demandSelection.getSummarydeductionLevelDes()) + " IN ('" + StringUtils.join(demandSelection.getDeductionVariableIds(), "','") + "' )";
            inputs.add(demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            inputs.add(demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            inputs.add(demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            inputs.add(ARMUtils.getDeductionValuesMapForLevel().get(demandSelection.getSummarydeductionLevelDes()));
            inputs.add(demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            inputs.add(demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : demandSelection.getMasterSids().get(ARMUtils.levelVariablesVarables.ITEM.toString()));
            inputs.add(rebateRecord);
            returnObj[0] = inputs;
            returnObj[1] = new TreeMap();
        } catch (Exception ex) {
            DEMAND_LOGGER.error("Error in generateInputs:", ex);
        }
        return returnObj;
    }

    public List getNextLevelDemand(List inputs, Object dto, SummarySelection selection) {
        if (dto instanceof AdjustmentDTO) {
            inputs.add(getNextLevelDemand(dto, selection));
        } else {
            inputs.add(getNextLevelGen(selection));
        }
        return inputs;
    }

    private String getNextLevelGen(SummarySelection demandSelection) {

        String nextLevel;
        demandSelection.setLevelNo(1);
        if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(demandSelection.getSummaryLevel().get(1))) {
            nextLevel = demandSelection.getSummarydeductionLevelDes();
        } else {
            nextLevel = demandSelection.getSummaryLevel().get(1);
        }
        if (demandSelection.getSummaryvalueSid() != 0) {
            demandSelection.getMasterSids().put(demandSelection.getSummarylevelFilterValue(), demandSelection.getSummaryvalueSid());
        } else if (demandSelection.getSummarylevelFilterNo() != 0) {
            demandSelection.getMasterSids().put(demandSelection.getSummarylevelFilterValue(), null);
        }
        if (demandSelection.getSummarylevelFilterNo() != 0) {
            if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(demandSelection.getSummarylevelFilterValue())) {
                nextLevel = demandSelection.getSummarydeductionLevelDes();
            } else {
                nextLevel = demandSelection.getSummarylevelFilterValue();
            }
        }
        return nextLevel;
    }

    public String getNextLevelDemand(Object dto, SummarySelection selection) {
        String nextLevel;
        TreeMap<String, Integer> masterSids;
        AdjustmentDTO val = (AdjustmentDTO) dto;
        int levelNo = val.getLevelNo();
        masterSids = (TreeMap<String, Integer>) val.getMasterIds().clone();
        masterSids.put(selection.getSummaryLevel().get(levelNo), Integer.valueOf(val.getBranditemmasterSid()));
        selection.setMasterSids(masterSids);
        if (ARMUtils.levelVariablesVarables.DEDUCTION.toString().equals(selection.getSummaryLevel().get(++levelNo))) {
            nextLevel = selection.getSummarydeductionLevelDes();
        } else {
            nextLevel = selection.getSummaryLevel().get(levelNo);
        }
        selection.setSummaryviewType(selection.getSummaryLevel().get(levelNo));
        selection.setLevelNo(levelNo);
        return nextLevel;
    }

    @Override
    protected DataResult getCustomizedData(SelectionDTO data, List list) {
        List<Object[]> listObj = new ArrayList<>(list);
        SummarySelection selection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        List finalList = new ArrayList();
        AdjustmentDTO dto = null;
        boolean isChild = !Collections.max(selection.getSummaryLevel().keySet()).equals(selection.getLevelNo());
        int size = listObj.size();
        Double[] total = new Double[6];
        int index = 0;
        for (int i = 0; i < size; i++) {
            Object[] list1 = listObj.get(i);
            mastersId = String.valueOf(list1[1]);
            index = (i + 1) % (selection.getSummaryPeriods() + 1);
            if (!lastMasterSid.equals(mastersId) || dto == null) {
                dto = new AdjustmentDTO();
                finalList.add(dto);
                dto.setBranditemmasterSid(mastersId);
                dto.setMasterIds((TreeMap<String, Integer>) selection.getMasterSids());
                dto.setLevelNo(selection.getLevelNo());
                dto.setGroup(String.valueOf(list1[0]));
                dto.setChildrenAllowed((!CommonConstant.TOTAL.equalsIgnoreCase(dto.getGroup()) && selection.getSummarylevelFilterNo() == 0) ? isChild : false);
            }
            Object[] object = customizeListView(dto, list1, selection, total);
            dto = (AdjustmentDTO) object[0];
            total = (Double[]) object[1];
            if (index == 0) {
                dto = customizeTotal(dto, total, selection);
                total = new Double[6];
            }
            lastMasterSid = mastersId;
        }
        OriginalDataResult dataResult = new OriginalDataResult();
        dataResult.setDataResults(finalList);
        return dataResult;
    }

    public Object[] customizeListView(AdjustmentDTO dto, Object[] list1, SummarySelection selection, Double[] total) {
        String headerKey = getHeaderKey(list1);
        Double[] object = total.clone();
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        for (int i = 0; i < columns.length; i++) {
            if (selection.getSelectedAdjustmentTypeValues().contains(columns[i])) {
                dto.setDTOValues(headerValueMap, headerKey + columns[i], list1[i + 4], columns[i].contains(ARMUtils.PAYMENT_RATIO) ? percentformat : decimalformat);
                object = getTotalValue(object, i, list1);
            }
        }
        return new Object[]{dto, object};
    }

    private String getHeaderKey(Object[] listl) {
        Object[] list = listl.clone();
        int period = Integer.parseInt(list[NumericConstants.TWO].toString());
        int year = Integer.parseInt(list[NumericConstants.THREE].toString());
        return StringUtils.EMPTY + (period == 0 ? CommonConstant.BEGINNING_BALANCE : period) + (year == 0 ? StringUtils.EMPTY : year);
    }

    public AdjustmentDTO customizeTotal(AdjustmentDTO dto, Double[] total, SummarySelection selection) {
        String headerKey = ARMUtils.TOTAL;
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        for (int i = 0; i < columns.length; i++) {
            if (selection.getSelectedAdjustmentTypeValues().contains(columns[i])) {
                dto.setDTOValues(headerValueMap, headerKey + columns[i], total[i], columns[i].contains(ARMUtils.PAYMENT_RATIO) ? percentformat : decimalformat);
            }
        }
        return dto;
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
        query = query.replace("@STARTPERIOD", selection.getFromDate());
        query = query.replace("@ENDPERIOD", selection.getToDate());
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    private Double[] getTotal(Double[] total, String column, Object value) {
        int size = CommonLogic.getInstance().getDemandColumns().size();
        List<String> list = CommonLogic.getInstance().getDemandColumns();
        for (int i = 0; i < size; i++) {
            if (column.contains(VariableConstants.PAYMENT_RATIO) && column.contains(list.get(i))) {
                total[i] = total[NumericConstants.THREE] != null && total[NumericConstants.FOUR] != null && total[NumericConstants.THREE].compareTo(0.0) != 0
                        ? (total[NumericConstants.FOUR] / total[NumericConstants.THREE]) * NumericConstants.HUNDRED : NumericConstants.ZERO;
            } else if (column.contains(list.get(i))) {
                total[i] = getTotalForExcel(total[i], value);
            }
        }
        return total;
    }

    private String getGatheredColumn(int indicator, String headerKey, String column, int totListModIndex, String colValue) {
        boolean begBalFlag = indicator == 2;
        boolean totalFlag = totListModIndex == 0;
        String value;
        if (begBalFlag && totalFlag) {
            value = column.contains(ARMUtils.TOTAL) ? column : VariableConstants.BEGINNING_BALANCE + colValue;
        } else {
            String columnValue = totalFlag && column.contains(ARMUtils.TOTAL) ? column : headerKey + colValue;
            value = begBalFlag ? VariableConstants.BEGINNING_BALANCE + colValue
                    : columnValue;
        }
        return value;
    }

    @Override
    public List bsExcelCustomizer(List deductionList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException {
        List<String> visibleColumnsList = new ArrayList<>(visibleColumns);
        int j = 1;
        int keyParam = j;
        String demandOldC = StringUtils.EMPTY;
        String demandNewC;
        String demandColumn;
        AdjustmentDTO dto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        String demandKey = "0.";
        Double[] total = new Double[6];
        int totalListModIndex = 1;
        if (deductionList != null && !deductionList.isEmpty()) {
            for (int i = 0; i < deductionList.size(); i++) {
                Object[] resultSet = (Object[]) deductionList.get(i);
                totalListModIndex = (i + 1) % (selection.getSummaryPeriods() + 1);
                demandNewC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                if (!"0".equals(demandNewC)) {
                    if (!"null".equals(demandNewC)) {
                        if (object.length > keyParam && !demandOldC.equals(demandNewC)) {
                            keyParam++;
                            if ("null".equalsIgnoreCase(String.valueOf(resultSet[(j + 1) * NumericConstants.TWO]))) {
                                j++;
                            }
                        }
                    } else if (!"null".equals(demandOldC)) {
                        j = 1;
                        keyParam = 1;
                    }
                    demandKey = ExcelUtils.getKey(resultSet, keyParam).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY);
                    demandNewC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                    demandOldC = demandNewC;
                }
                size = mapList.size();
                for (int l = 0; l < size; l++) {
                    dto = mapList.get(l).get(demandKey);
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
                    map.put(demandKey, dto);
                }
                int indicator = 0;
                Object value = null;
                Object result = null;
                if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                    result = resultSet[6];
                    indicator = (Integer) resultSet[resultSet.length - 1];
                } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                    result = resultSet[8];
                    indicator = (Integer) resultSet[resultSet.length - 1];
                } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                    result = resultSet[10];
                    indicator = (Integer) resultSet[resultSet.length - 1];
                }
                String headerKey = String.valueOf(result).replace(ARMUtils.SPACE.toString(), "").replace("-", StringUtils.EMPTY);
                for (int k = 0; k < visibleColumns.size(); k++) {
                    demandColumn = visibleColumnsList.get(k).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    headerKey = (indicator == 2 && demandColumn.contains(VariableConstants.BEGINNING_BALANCE)) ? VariableConstants.BEGINNING_BALANCE : headerKey;
                    String gatheredColumn = StringUtils.EMPTY;
                    List<String> columnList = CommonLogic.getInstance().getDemandColumns();
                    List<String> totalColumnList = CommonLogic.getInstance().getTotalDemandColumns();
                    for (int l = 0; l < columnList.size(); l++) {
                        int index = l % 6;
                        if (demandColumn.contains(columnList.get(l))) {
                            gatheredColumn = getGatheredColumn(indicator, headerKey, demandColumn, totalListModIndex, columnList.get(l));
                            if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                                value = demandColumn.contains(totalColumnList.get(l)) ? getNullTotalExcel(total[index]) : resultSet[index + 7];
                            } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                                value = demandColumn.contains(totalColumnList.get(l)) ? getNullTotalExcel(total[index]) : resultSet[index + 9];
                            } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                                value = demandColumn.contains(totalColumnList.get(l)) ? getNullTotalExcel(total[index]) : resultSet[index + 11];
                            }
                        }
                    }
                    if (demandColumn.startsWith(gatheredColumn) && demandColumn.matches("[a-zA-Z0-9-~\\s]+\\.\\d+$")) {
                        total = !demandColumn.contains(ARMUtils.TOTAL) ? getTotal(total, demandColumn, value) : total;
                        dto.addStringProperties(demandColumn, value == null ? StringUtils.EMPTY : String.valueOf(value));
                    }
                }
                total = totalListModIndex == 0 ? new Double[6] : total;
                dto.setGroup(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
                dto.setMonth(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
            }
        }
        return mapList;
    }

    private Double[] getTotalValue(Double[] obj, int i, Object[] list1) {
        Double[] total = obj.clone();
        if (columns[i].contains(ARMUtils.PAYMENT_RATIO)) {
            total[i] = total[i - NumericConstants.TWO] != null && total[i - NumericConstants.ONE] != null && total[i - NumericConstants.TWO].compareTo(0.0) != 0 ? (total[i - NumericConstants.ONE] / total[i - NumericConstants.TWO]) * NumericConstants.HUNDRED : NumericConstants.ZERO;
        } else {
            total[i] = getNullTotal(total[i]) + Double.valueOf(ARMUtils.NULL.equalsIgnoreCase(String.valueOf(list1[i + 4])) ? ARMUtils.ZERO_STRING : String.valueOf(list1[i + 4]));
        }
        return total;
    }
}
