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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sathya.Seelan
 */
public class ReturnReserveLogic extends AbstractBSummaryLogic {

    public static final Logger RETURN_RESERVE_LOGGER = LoggerFactory.getLogger(ReturnReserveLogic.class);

    public ReturnReserveLogic() {
        super();
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        RETURN_RESERVE_LOGGER.debug("Inside ReturnReserve getQueryTableinput");
        return Collections.emptyList();
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        RETURN_RESERVE_LOGGER.debug("Inside ReturnReserve getTableInput");
        return Collections.emptyList();
    }

    @Override
    protected String getCommonQueryName() {
        RETURN_RESERVE_LOGGER.debug("Inside ReturnReserve getCommonQueryName");
        return "BSummaryCommonQuery-ReturnReserve";
    }

    @Override
    protected String getLoadDataQueryName() {
        RETURN_RESERVE_LOGGER.debug("Inside ReturnReserve getLoadDataQueryName");
        return "BSummaryLoadData-ReturnReserve";
    }

    @Override
    protected String getCountQueryName() {
        RETURN_RESERVE_LOGGER.debug("Inside ReturnReserve getCountQueryName");
        return "BSummaryCount-ReturnReserve";
    }

    @Override
    protected String getTotalQueryName() {
        RETURN_RESERVE_LOGGER.debug("Inside ReturnReserve getTotalQueryName");
        return "BSummaryLoadTotalData-ReturnReserve";
    }

    @Override
    protected String getExcelQueryName() {
        RETURN_RESERVE_LOGGER.debug("Inside ReturnReserve getExcelQueryName");
        return "getBSummaryExcelQuery-ReturnReserve";
    }

    @Override
    protected DataResult getCustomizedData(SelectionDTO data, List list) {
        SummarySelection selection = (SummarySelection) data;

        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        boolean isChild = !Collections.max(selection.getSummaryLevel().keySet()).equals(selection.getLevelNo());
        List finalList = getFinalList(list, selection, isChild, headerValueMap);
        OriginalDataResult dataResult = new OriginalDataResult();
        dataResult.setDataResults(finalList);
        return dataResult;
    }

    private List getFinalList(List list, SummarySelection selection, boolean isChild, Map<Object, String> headerValueMap) {
        AdjustmentDTO dto = null;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        DecimalFormat decimalformatfinalList = new DecimalFormat("$#,##0.00");
        List finalList = new ArrayList();
        for (Object[] list1 : (List<Object[]>) list) {
            mastersId = String.valueOf(list1[1]);
            if (!lastMasterSid.equals(mastersId) || dto == null) {
                dto = new AdjustmentDTO();
                finalList.add(dto);
                dto.setBranditemmasterSid(mastersId);
                dto.setMasterIds(selection.getMasterSids());
                dto.setLevelNo(selection.getLevelNo());
                dto.setGroup(String.valueOf(list1[0]));

                dto.setChildrenAllowed((!ARMUtils.TOTAL.equalsIgnoreCase(dto.getGroup()) && selection.getSummarylevelFilterNo() == 0) ? isChild : false);
            }
            int period = Integer.parseInt(list1[NumericConstants.TWO].toString());
            int year = Integer.parseInt(list1[NumericConstants.THREE].toString());
            setTableFields(list1, period, year, selection, dto, headerValueMap, decimalformatfinalList);
            lastMasterSid = mastersId;
        }
        return finalList;
    }

    private void setTableFields(Object[] list1, int period, int year, SummarySelection selection, AdjustmentDTO dto, Map<Object, String> headerValueMap, DecimalFormat decimalformatfinalList) {
        if (list1[8] != null) {
            String headerKey = StringUtils.EMPTY + (period == 99999 ? ARMUtils.TOTAL : period) + (year == 99999 ? StringUtils.EMPTY : year);
            if (selection.getSelectedAdjustmentTypeValues().contains("Starting Balance")) {
                dto.setDTOValues(headerValueMap, headerKey + "Starting Balance", list1[5], decimalformatfinalList);
            }
            if (selection.getSelectedAdjustmentTypeValues().contains("Return Reserve")) {
                dto.setDTOValues(headerValueMap, headerKey + "Return Reserve", list1[6], decimalformatfinalList);
            }
            if (selection.getSelectedAdjustmentTypeValues().contains("Ending Balance")) {
                dto.setDTOValues(headerValueMap, headerKey + (headerKey.equals(ARMUtils.TOTAL) ? "Balance" : "Ending Balance"), list1[7], decimalformatfinalList);
            }
        }
    }

    @Override
    public List bsExcelCustomizer(List resultList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException {
        List<String> visibleColumnsList = new ArrayList<>(visibleColumns);
        int keyParam;
        String column;
        AdjustmentDTO rrExcelDto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        String key = "0.";
        List deductionList = (List<Object[]>) resultList.get(0);
        List totalList = (List<Object[]>) resultList.get(1);
        boolean flag = true;
        String group;
        if (deductionList != null && !deductionList.isEmpty()) {
            for (int i = 0; i < deductionList.size(); i++) {
                int totalListIndex = i / selection.getSummaryPeriods();
                int totalListModIndex = (i + 1) % selection.getSummaryPeriods();
                Object[] resultSet = (Object[]) deductionList.get(i);
                group = String.valueOf(resultSet[NumericConstants.ONE]);
                keyParam = getKeyParam(selection);
                if (!"0".equals(String.valueOf(resultSet[NumericConstants.TWO]))) {
                    String[] arr = getKey(resultSet, keyParam);
                    key = arr[0].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY);
                    group = arr[1];
                }
                size = mapList.size();
                for (int l = 0; l < size; l++) {
                    rrExcelDto = mapList.get(l).get(key);
                    if (rrExcelDto != null) {
                        break;
                    }
                }
                if (rrExcelDto == null) {
                    rrExcelDto = new AdjustmentDTO();
                    Map<String, AdjustmentDTO> map = mapList.get(size - 1);
                    if (map.size() >= NumericConstants.THOUSAND) {
                        map = new HashMap<>();
                        mapList.add(map);
                    }
                    map.put(key, rrExcelDto);
                }
                for (int k = 0; k < visibleColumns.size(); k++) {
                    Object[] totalSet = (Object[]) totalList.get("0.".equals(key) ? 0 : totalListIndex);
                    String headerKey;
                    Object result = resultSet[keyParam];
                    Object value = null;
                    headerKey = String.valueOf(result).replace(ARMUtils.SPACE.toString(), "").replace("-", StringUtils.EMPTY);
                    column = visibleColumnsList.get(k).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
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
                        rrExcelDto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                    }
                }
                rrExcelDto.setGroup(group);
                rrExcelDto.setMonth(group);
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
