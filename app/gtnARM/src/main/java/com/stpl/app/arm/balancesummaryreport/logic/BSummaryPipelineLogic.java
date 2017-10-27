package com.stpl.app.arm.balancesummaryreport.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.CommonConstant;
import static com.stpl.app.utils.ExcelUtils.getKey;
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
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Srithar.Raju
 * @param <T>
 */
public class BSummaryPipelineLogic<T extends AdjustmentDTO> extends AbstractBSummaryLogic {

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
        List<Object[]> listObj = list;
        SummarySelection selection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        List finalList = new ArrayList();
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        AdjustmentDTO dto = null;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0");
        boolean isChild = !Collections.max(selection.getSummaryLevel().keySet()).equals(selection.getLevelNo());
        for (Object[] list1 : listObj) {
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
            if (list1[10] != null) {
                String headerKey = StringUtils.EMPTY + (period == 99999 ? "Total" : period) + (year == 99999 ? StringUtils.EMPTY : year);
                if (selection.getSelectedAdjustmentTypeValues().contains("Starting Balance")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Starting Balance", list1[5], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Pipeline Accrual")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Pipeline Accrual", list1[6], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Demand Accrual")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Demand Accrual", list1[7], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Pipeline Inventory True-Up")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Pipeline Inventory True-Up", list1[8], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Demand Reforecast")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Demand Reforecast", list1[9], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Total Period Adjustment")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Total Period Adjustment", list1[10], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Ending Balance")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Ending Balance", list1[11], decimalformat);
                }
            }
            lastMasterSid = mastersId;
        }
        OriginalDataResult dataResult = new OriginalDataResult();
        dataResult.setDataResults(finalList);
        return dataResult;
    }

    @Override
    public List bsExcelCustomizer(List resultList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException {
        List<String> visibleColumnsList = visibleColumns;
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
        List deductionList = (List<Object[]>) resultList.get(0);
        List totalList = (List<Object[]>) resultList.get(1);
        int totalListIndex = 0;
        int totalListModIndex = 1;
        boolean flag = true;
        if (deductionList != null && !deductionList.isEmpty()) {
            for (int i = 0; i < deductionList.size(); i++) {
                totalListIndex = i / selection.getSummaryPeriods();
                totalListModIndex = (i + 1) % selection.getSummaryPeriods();
                Object[] resultSet = (Object[]) deductionList.get(i);
                Object[] totalSet = (Object[]) totalList.get(totalListIndex);
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
                    key = getKey(resultSet, keyParam).replace(" ", StringUtils.EMPTY).replace(" ", StringUtils.EMPTY);
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
                for (int k = 0; k < visibleColumns.size(); k++) {

                    String headerKey;
                    Object result = null;
                    Object value = null;
                    if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                        result = resultSet[6];
                    } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                        result = resultSet[8];
                    } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                        result = resultSet[10];
                    }
                    headerKey = String.valueOf(result).replace(" ", "").replace("-", StringUtils.EMPTY);
                    column = visibleColumnsList.get(k).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    String gatheredColumn = StringUtils.EMPTY;
                    if (column.contains("StartingBalance")) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? headerKey + "StartingBalance" : column;
                        flag = (column.contains(VariableConstants.TOTAL_STARTING_BALANCE)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[7] : resultSet[7];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[9] : resultSet[9];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[11] : resultSet[11];
                        }
                    }
                    if (column.contains("PipelineAccrual")) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? headerKey + "PipelineAccrual" : column;
                        flag = (column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? totalSet[8] : resultSet[8];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? totalSet[10] : resultSet[10];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_PIPELINE_ACCRUAL) ? totalSet[12] : resultSet[12];
                        }
                    }
                    if (column.contains("DemandAccrual")) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? headerKey + "DemandAccrual" : column;
                        flag = (column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? totalSet[9] : resultSet[9];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? totalSet[11] : resultSet[11];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_DEMAND_ACCRUAL) ? totalSet[13] : resultSet[13];
                        }
                    }
                    if (column.contains(VariableConstants.PIPELINE_INVENTORY_TRUE_UP)) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? headerKey + VariableConstants.PIPELINE_INVENTORY_TRUE_UP : column;
                        flag = (column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? totalSet[10] : resultSet[10];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? totalSet[12] : resultSet[12];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_PIPELINE_INVENTORY_TRUE_UP) ? totalSet[14] : resultSet[14];
                        }
                    }
                    if (column.contains(VariableConstants.DEMAND_REFORECAST)) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? headerKey + VariableConstants.DEMAND_REFORECAST : column;
                        flag = (column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? totalSet[11] : resultSet[11];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? totalSet[13] : resultSet[13];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_DEMAND_REFORECAST) ? totalSet[15] : resultSet[15];
                        }
                    }
                    if (column.contains("TotalPeriodAdjustment")) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? headerKey + "TotalPeriodAdjustment" : column;
                        flag = (column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? totalSet[12] : resultSet[12];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? totalSet[14] : resultSet[14];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_TOTAL_PERIOD_ADJUSTMENT) ? totalSet[16] : resultSet[16];
                        }
                    }

                    if (column.contains("EndingBalance")) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? headerKey + "EndingBalance" : column;
                        flag = (column.contains(VariableConstants.TOTAL_ENDING_BALANCE)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[13] : resultSet[13];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[15] : resultSet[15];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[17] : resultSet[17];
                        }
                    }                    
                    if (column.startsWith(gatheredColumn) && column.matches("[a-zA-Z0-9-~\\s]+\\.\\d+$") && flag) {
                        dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        
                    }
                }
                dto.setGroup(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
                dto.setMonth(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
            }
        }
        return mapList;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    protected String getExcelTotalQueryName() {
        return "getBSummaryExcelTotal-Pipeline";
    }
}
