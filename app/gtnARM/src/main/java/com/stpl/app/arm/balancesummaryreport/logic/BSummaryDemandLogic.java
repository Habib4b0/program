/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.stpl.ifs.ui.util.NumericConstants;
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
 */
public class BSummaryDemandLogic extends AbstractBSummaryLogic {

    public BSummaryDemandLogic() {
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
        return "BSummaryCommonQuery-Demand";
    }

    @Override
    protected String getLoadDataQueryName() {
        return "BSummaryLoadData-Demand";
    }

    @Override
    protected String getCountQueryName() {
        return "BSummaryCount-Demand";
    }

    @Override
    protected String getTotalQueryName() {
        return "BSummaryLoadTotalData-Demand";
    }

    @Override
    protected String getExcelQueryName() {
        return "getBSummaryExcelQuery-Demand";
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
        DecimalFormat decimalformatto = new DecimalFormat("#,#0%");
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
                dto.setChildrenAllowed((!CommonConstant.TOTAL.equalsIgnoreCase(dto.getGroup()) && selection.getSummarylevelFilterNo() == 0) ? isChild : false);
            }

            int period = Integer.parseInt(list1[NumericConstants.TWO].toString());
            int year = Integer.parseInt(list1[NumericConstants.THREE].toString());

            if (list1[9] != null) {
                String headerKey = StringUtils.EMPTY;
                switch (list1[9].toString()) {
                    case "0":
                        headerKey = StringUtils.EMPTY + period + year;
                        break;
                    case "1":
                        headerKey = CommonConstant.TOTAL;
                        break;
                    case "2":
                        headerKey = "Cumulative Balance";
                        break;
                    default:
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Actual Payments")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Actual Payments", list1[6], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Payment Ratio")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Payment Ratio", list1[7], decimalformatto);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Period Balance")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Period Balance", list1[8], decimalformat);
                }
                dto.setDTOValues(headerValueMap, headerKey + list1[4], list1[5], decimalformat);
            }
            lastMasterSid = mastersId;
        }
        OriginalDataResult dataResult = new OriginalDataResult();
        dataResult.setDataResults(finalList);
        return dataResult;
    }

    @Override
    public List bsExcelCustomizer(List resultList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO abstractDto) throws IllegalAccessException, InvocationTargetException {
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
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] resultSet = (Object[]) resultList.get(i);
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
                    key = getKey(resultSet, keyParam);
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

                    String headerKey = StringUtils.EMPTY;
                    switch (resultSet[13].toString()) {
                        case "0":
                            headerKey = String.valueOf(resultSet[6]).replace(" ", "").replace("-", StringUtils.EMPTY);
                            break;
                        case "1":
                            headerKey = CommonConstant.TOTAL;
                            break;
                        case "2":
                            headerKey = "CumulativeBalance";
                            break;
                        default:
                    }
                    column = visibleColumnsList.get(k).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    Object value = resultSet[9];
                    String gatheredColumn = headerKey + String.valueOf(resultSet[8]).replace(" ", "").replace("-", StringUtils.EMPTY);

                    if (column.contains("PeriodBalance")) {
                        gatheredColumn = headerKey + "PeriodBalance";
                        value = resultSet[10];
                    }
                    if (column.contains("ActualPayments")) {
                        gatheredColumn = headerKey + "ActualPayments";
                        value = resultSet[11];
                    }
                    if (column.contains("PaymentRatio")) {
                        gatheredColumn = headerKey + "PaymentRatio";
                        value = resultSet[12];
                    }

                    if (column.startsWith(gatheredColumn) && column.matches("[a-zA-Z0-9-~\\s]+\\.\\d+$")) {
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
        return Collections.emptyList();
    }
    
    @Override
    protected String getExcelTotalQueryName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
