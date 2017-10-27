/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.returnreserve.logic;

import com.stpl.app.arm.balancesummaryreport.logic.AbstractBSummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.utils.ExcelUtils;
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
 * @author Sathya.Seelan
 */
public class ReturnReserveLogic extends AbstractBSummaryLogic {

    public ReturnReserveLogic() {
        super();
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        return Collections.emptyList();
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        return Collections.emptyList();
    }

    @Override
    protected String getCommonQueryName() {
        return "BSummaryCommonQuery-ReturnReserve";
    }

    @Override
    protected String getLoadDataQueryName() {
        return "BSummaryLoadData-ReturnReserve";
    }

    @Override
    protected String getCountQueryName() {
        return "BSummaryCount-ReturnReserve";
    }

    @Override
    protected String getTotalQueryName() {
        return "BSummaryLoadTotalData-ReturnReserve";
    }

    @Override
    protected String getExcelQueryName() {
        return "getBSummaryExcelQuery-ReturnReserve";
    }

    @Override
    protected DataResult getCustomizedData(SelectionDTO data, List list) {
        SummarySelection selection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        List finalList = new ArrayList();
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        AdjustmentDTO dto = null;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0");
        DecimalFormat percentformat = new DecimalFormat("#,##0");
        boolean isChild = !Collections.max(selection.getSummaryLevel().keySet()).equals(selection.getLevelNo());
        for (Object[] list1 : (List<Object[]>) list) {
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
            if (list1[8] != null) {
                String headerKey = StringUtils.EMPTY + (period == 99999 ? "Total" : period) + (year == 99999 ? StringUtils.EMPTY : year);
                if (selection.getSelectedAdjustmentTypeValues().contains("Starting Balance")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Starting Balance", list1[5], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Return Reserve")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Return Reserve", list1[6], percentformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Ending Balance")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Ending Balance", list1[7], decimalformat);
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
                    key = ExcelUtils.getKey(resultSet, keyParam).replace(" ", StringUtils.EMPTY);
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
                    Object[] totalSet = (Object[]) totalList.get("0.".equals(key) ? 0 : totalListIndex);
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
                        flag = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[7] : resultSet[7];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[9] : resultSet[9];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_STARTING_BALANCE) ? totalSet[11] : resultSet[11];
                        }
                    }
                    if (column.contains("ReturnReserve")) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_RETURN_RESERVE) ? headerKey + "ReturnReserve" : column;
                        flag = column.contains(VariableConstants.TOTAL_RETURN_RESERVE) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_RETURN_RESERVE) ? totalSet[8] : resultSet[8];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_RETURN_RESERVE) ? totalSet[10] : resultSet[10];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_RETURN_RESERVE) ? totalSet[12] : resultSet[12];
                        }
                    }

                    if (column.contains("EndingBalance")) {
                        gatheredColumn = !column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? headerKey + "EndingBalance" : column;
                        flag = (column.contains(VariableConstants.TOTAL_ENDING_BALANCE)) ? totalListModIndex == 0 : true;
                        if (ARMConstants.getDeductionProduct().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[9] : resultSet[9];
                        } else if (ARMConstants.getDeductionCustomer().equals(selection.getSummaryDeductionView()) || ARMConstants.getCustomerDedection().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[10] : resultSet[11];
                        } else if (ARMConstants.getDeductionCustomerContract().equals(selection.getSummaryDeductionView())) {
                            value = column.contains(VariableConstants.TOTAL_ENDING_BALANCE) ? totalSet[12] : resultSet[13];
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
        return "getBSummaryExcelTotalQuery-ReturnReserve";
    }
}
