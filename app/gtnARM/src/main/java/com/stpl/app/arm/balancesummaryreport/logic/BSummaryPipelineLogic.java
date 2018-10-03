package com.stpl.app.arm.balancesummaryreport.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.utils.VariableConstants;
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

/**
 *
 * @author Srithar.Raju
 * @param <T>
 */
public class BSummaryPipelineLogic extends AbstractBSummaryLogic {

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
        return "BSummaryCommonQuery-Pipeline";

    }

    @Override
    protected String getLoadDataQueryName() {
        return "BSummaryLoadData-Pipeline";
    }

    @Override
    protected String getCountQueryName() {
        return "BSummaryCount-Pipeline";
    }

    @Override
    protected String getTotalQueryName() {
        return "BSummaryLoadTotalData-Pipeline";
    }

    @Override
    protected String getExcelQueryName() {
        return "getBSummaryExcelQuery-Pipeline";
    }

    @Override
    protected DataResult getCustomizedData(SelectionDTO data, List list) {
        List<Object[]> listObj = new ArrayList<>(list);
        SummarySelection bsrPipelineSelection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        List finalList = new ArrayList();
        Map<Object, String> headerValueMap = bsrPipelineSelection.getHeaderVisibleColumnMap();
        AdjustmentDTO pipelineDto = null;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.00");
        boolean isChild = !Collections.max(bsrPipelineSelection.getSummaryLevel().keySet()).equals(bsrPipelineSelection.getLevelNo());
        for (Object[] list1 : listObj) {
            mastersId = String.valueOf(list1[1]);
            if (!lastMasterSid.equals(mastersId) || pipelineDto == null) {
                pipelineDto = new AdjustmentDTO();
                finalList.add(pipelineDto);
                pipelineDto.setBranditemmasterSid(mastersId);
                pipelineDto.setMasterIds((TreeMap<String, Integer>) bsrPipelineSelection.getMasterSids());
                pipelineDto.setLevelNo(bsrPipelineSelection.getLevelNo());
                pipelineDto.setGroup(String.valueOf(list1[0]));
                pipelineDto.setChildrenAllowed((!ARMUtils.TOTAL.equalsIgnoreCase(pipelineDto.getGroup()) && bsrPipelineSelection.getSummarylevelFilterNo() == 0) ? isChild : false);
            }

            int period = Integer.parseInt(list1[NumericConstants.TWO].toString());
            int year = Integer.parseInt(list1[NumericConstants.THREE].toString());
            addPipelineDto(list1, period, year, bsrPipelineSelection, pipelineDto, headerValueMap, decimalformat);
            lastMasterSid = mastersId;
        }
        OriginalDataResult dataResult = new OriginalDataResult();
        dataResult.setDataResults(finalList);
        return dataResult;
    }

    private void addPipelineDto(Object[] list1, int period, int year, SummarySelection bsrPipelineSelection, AdjustmentDTO pipelineDto, Map<Object, String> pipelineHeaderValueMap, DecimalFormat decimalformat1) {
        if (list1[10] != null) {
            String headerKey = StringUtils.EMPTY + (period == 99999 ? ARMUtils.TOTAL : period) + (year == 99999 ? StringUtils.EMPTY : year);
            if (bsrPipelineSelection.getSelectedAdjustmentTypeValues().contains("Starting Balance")) {
                pipelineDto.setDTOValues(pipelineHeaderValueMap, headerKey + "Starting Balance", list1[5], decimalformat1);
            }
            if (bsrPipelineSelection.getSelectedAdjustmentTypeValues().contains("Pipeline Accrual")) {
                pipelineDto.setDTOValues(pipelineHeaderValueMap, headerKey + "Pipeline Accrual", list1[6], decimalformat1);
            }
            if (bsrPipelineSelection.getSelectedAdjustmentTypeValues().contains("Demand Accrual")) {
                pipelineDto.setDTOValues(pipelineHeaderValueMap, headerKey + "Demand Accrual", list1[7], decimalformat1);
            }
            if (bsrPipelineSelection.getSelectedAdjustmentTypeValues().contains("Pipeline Inventory True-Up")) {
                pipelineDto.setDTOValues(pipelineHeaderValueMap, headerKey + "Pipeline Inventory True-Up", list1[8], decimalformat1);
            }
            if (bsrPipelineSelection.getSelectedAdjustmentTypeValues().contains("Demand Reforecast")) {
                pipelineDto.setDTOValues(pipelineHeaderValueMap, headerKey + "Demand Reforecast", list1[9], decimalformat1);
            }
            if (bsrPipelineSelection.getSelectedAdjustmentTypeValues().contains("Total Period Adjustment")) {
                pipelineDto.setDTOValues(pipelineHeaderValueMap, headerKey + "Total Period Adjustment", list1[10], decimalformat1);
            }
            if (bsrPipelineSelection.getSelectedAdjustmentTypeValues().contains("Ending Balance")) {
                pipelineDto.setDTOValues(pipelineHeaderValueMap, headerKey + (headerKey.equals(ARMUtils.TOTAL) ? "Balance" : "Ending Balance"), list1[11], decimalformat1);
            }
        }
    }

    @Override
    public List bsExcelCustomizer(List resultList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException {
        List<String> visibleColumnsList = new ArrayList<>(visibleColumns);
        String column;
        AdjustmentDTO bsrPipelineAdjustmentDto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        String bsrPipelineKey = "0.";
        List bsrDeductionList = (List<Object[]>) resultList.get(0);
        List totalList = (List<Object[]>) resultList.get(1);
        int totalListIndex = 0;
        int totalListModIndex = 1;
        String group;
        int keyParam = 0;
        boolean pipelineFlag = true;
        if (bsrDeductionList != null && !bsrDeductionList.isEmpty()) {
            for (int i = 0; i < bsrDeductionList.size(); i++) {
                totalListIndex = i / selection.getSummaryPeriods();
                totalListModIndex = (i + 1) % selection.getSummaryPeriods();
                Object[] resultSet = (Object[]) bsrDeductionList.get(i);
                Object[] totalSet = (Object[]) totalList.get(totalListIndex);
                group = String.valueOf(resultSet[NumericConstants.ONE]);
                keyParam = getKeyParam(selection);
                if (!"0".equals(String.valueOf(resultSet[NumericConstants.TWO]))) {
                    String[] arr = getKey(resultSet, keyParam);
                    bsrPipelineKey = arr[0].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY);
                    group = arr[1];
                }
                size = mapList.size();
                for (int l = 0; l < size; l++) {
                    bsrPipelineAdjustmentDto = mapList.get(l).get(bsrPipelineKey);
                    if (bsrPipelineAdjustmentDto != null) {
                        break;
                    }
                }
                if (bsrPipelineAdjustmentDto == null) {
                    bsrPipelineAdjustmentDto = new AdjustmentDTO();
                    Map<String, AdjustmentDTO> map = mapList.get(size - 1);
                    if (map.size() >= NumericConstants.THOUSAND) {
                        map = new HashMap<>();
                        mapList.add(map);
                    }
                    map.put(bsrPipelineKey, bsrPipelineAdjustmentDto);
                }
                for (int k = 0; k < visibleColumns.size(); k++) {

                    String headerKey;
                    Object result;
                    Object pipelineValue = null;
                    result = resultSet[keyParam];
                    headerKey = String.valueOf(result).replace(ARMUtils.SPACE.toString(), "").replace("-", StringUtils.EMPTY);
                    column = visibleColumnsList.get(k).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    String pipelineGatheredColumn = StringUtils.EMPTY;
                    if (column.contains("StartingBalance")) {
                        pipelineGatheredColumn = !column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? headerKey + "StartingBalance" : column;
                        pipelineFlag = (column.contains(VariableConstants.TOTAL_STARTING_BALANCE)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[7] : resultSet[7];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[9] : resultSet[9];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[11] : resultSet[11];
                        }
                    }
                    if (column.contains("PipelineAccrual")) {
                        pipelineGatheredColumn = !column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? headerKey + "PipelineAccrual" : column;
                        pipelineFlag = (column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? totalSet[8] : resultSet[8];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? totalSet[10] : resultSet[10];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? totalSet[12] : resultSet[12];
                        }
                    }
                    if (column.contains("DemandAccrual")) {
                        pipelineGatheredColumn = !column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? headerKey + "DemandAccrual" : column;
                        pipelineFlag = (column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? totalSet[9] : resultSet[9];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? totalSet[11] : resultSet[11];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? totalSet[13] : resultSet[13];
                        }
                    }
                    if (column.contains(VariableConstants.PIPELINE_INVENTORY_TRUE_UP)) {
                        pipelineGatheredColumn = !column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? headerKey + VariableConstants.PIPELINE_INVENTORY_TRUE_UP : column;
                        pipelineFlag = (column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? totalSet[10] : resultSet[10];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? totalSet[12] : resultSet[12];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? totalSet[14] : resultSet[14];
                        }
                    }
                    if (column.contains(VariableConstants.DEMAND_REFORECAST)) {
                        pipelineGatheredColumn = !column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? headerKey + VariableConstants.DEMAND_REFORECAST : column;
                        pipelineFlag = (column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? totalSet[11] : resultSet[11];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? totalSet[13] : resultSet[13];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? totalSet[15] : resultSet[15];
                        }
                    }
                    if (column.contains("TotalPeriodAdjustment")) {
                        pipelineGatheredColumn = !column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? headerKey + "TotalPeriodAdjustment" : column;
                        pipelineFlag = (column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? totalSet[12] : resultSet[12];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? totalSet[14] : resultSet[14];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? totalSet[16] : resultSet[16];
                        }
                    }

                    if (column.contains("EndingBalance")) {
                        pipelineGatheredColumn = !column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? headerKey + "EndingBalance" : column;
                        pipelineFlag = (column.contains(VariableConstants.TOTAL_ENDING_BALANCE)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[13] : resultSet[13];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[15] : resultSet[15];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            pipelineValue = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[17] : resultSet[17];
                        }
                    }
                    if (column.startsWith(pipelineGatheredColumn) && column.matches("[a-zA-Z0-9-~\\s]+\\.\\d+$") && pipelineFlag) {
                        bsrPipelineAdjustmentDto.addStringProperties(column, pipelineValue == null ? StringUtils.EMPTY : String.valueOf(pipelineValue));

                    }
                }
                bsrPipelineAdjustmentDto.setGroup(group);
                bsrPipelineAdjustmentDto.setMonth(group);
            }
        }
        return mapList;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        LOGGER.debug("Inside getQueryTableinputparameter");
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getExcelTotalQueryName() {
        LOGGER.debug("Inside getExcelTotalQueryName");
        return "getBSummaryExcelTotal-Pipeline";
    }
}
