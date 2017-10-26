package com.stpl.app.arm.balancesummaryreport.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import static com.stpl.app.utils.ExcelUtils.getKey;
import com.stpl.app.utils.VariableConstants;
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
 * @param <T>
 */
public class BSummaryPipelineLogic<T extends AdjustmentDTO> extends AbstractBSummaryLogic {

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        List<Object[]> listObj=list;
        SummarySelection selection = (SummarySelection) data;
        String lastMasterSid = StringUtils.EMPTY;
        String mastersId;
        List finalList = new ArrayList();
        Map<Object, String> headerValueMap = selection.getHeaderVisibleColumnMap();
        AdjustmentDTO dto = null;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0");
        DecimalFormat percentformat = new DecimalFormat("#,##0");
        boolean isChild = !Collections.max(selection.getSummaryLevel().keySet()).equals(selection.getLevelNo());
        for (Object[] list1 : listObj) {
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

            if (list1[10] != null) {
                String headerKey = StringUtils.EMPTY;
               
                switch (list1[10].toString()) {
                    case "0":
                        headerKey = StringUtils.EMPTY + period + year;
                        break;
                    case "1":                       
                        headerKey = "Cumulative Balance";
                        break;
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Actual Payments")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Actual Payments", list1[9], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Ending Balance")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Ending Balance", list1[8], percentformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Total Period Adjustment")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Total Period Adjustment", list1[7], decimalformat);
                }
                if (selection.getSelectedAdjustmentTypeValues().contains("Starting Balance")) {
                    dto.setDTOValues(headerValueMap, headerKey + "Starting Balance", list1[6], decimalformat);
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
    public List bsExcelCustomizer(List resultList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException {
        List<String> visibleColumnsList = visibleColumns;

        int j = 1;
        int keyParam = j;
        String oldC = StringUtils.EMPTY;
        String newC = StringUtils.EMPTY;
        String column = StringUtils.EMPTY;
        AdjustmentDTO dto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<Map<String, AdjustmentDTO>>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size = mapList.size();
        String key = "0.";
        Object[] excelHierarchy = selection.getExcelHierarchy();
        String view = StringUtils.join(excelHierarchy, ",");
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] resultSet = (Object[]) resultList.get(i);
                int length = resultSet.length - 1;
                newC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                if (!"0".equals(newC)) {
                    if (!newC.equals("null")) {
                        if (!oldC.equals(newC)) {
                            oldC = "";
                            if (object.length > keyParam) {
                                keyParam++;
                                if (String.valueOf(resultSet[(j + 1) * NumericConstants.TWO]).equalsIgnoreCase("null")) {
                                    j++;
                                }
                            }
                        }
                    } else if (!oldC.equals("null")) {
                        oldC = "";
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
                        map = new HashMap<String, AdjustmentDTO>();
                        mapList.add(map);
                    }
                    map.put(key, dto);
                }

                for (int k = 0; k < visibleColumns.size(); k++) {

                    String headerKey = StringUtils.EMPTY;
                    Object result = null;
                    Object value = null;
                    Object adjName = null;
                    if (VariableConstants.DEDUCTION_PRODUCT_DB.equals(view)) {
                        result = resultSet[6];
                        value = resultSet[9];
                        adjName = resultSet[8];
                    } else if (VariableConstants.DEDUCTION_CUSTOMER_DB.equals(view) || VariableConstants.CUSTOMER_DEDUC_DB.equals(view)) {
                        result = resultSet[8];
                        value = resultSet[11];
                        adjName = resultSet[10];
                    } else if (VariableConstants.DEDUCTION_CUS_CON_DB.equals(view)) {
                        result = resultSet[10];
                        value = resultSet[13];
                        adjName = resultSet[12];
                    }
                    switch (resultSet[length].toString()) {
                        case "0":
                            headerKey = String.valueOf(result).replace(" ", "").replace("-", StringUtils.EMPTY);
                            break;
                        case "1":
                            headerKey = "CumulativeBalance";
                            break;
                    }

                    column = visibleColumnsList.get(k).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    String gatheredColumn = headerKey + String.valueOf(adjName).replace(" ", "").replace("-", StringUtils.EMPTY);

                    if (column.contains("StartingBalance")) {
                        gatheredColumn = headerKey + "StartingBalance";
                        if (VariableConstants.DEDUCTION_PRODUCT_DB.equals(view)) {
                            value = resultSet[10];
                        } else if (VariableConstants.DEDUCTION_CUSTOMER_DB.equals(view) || VariableConstants.CUSTOMER_DEDUC_DB.equals(view)) {
                            value = resultSet[12];
                        } else if (VariableConstants.DEDUCTION_CUS_CON_DB.equals(view)) {
                            value = resultSet[14];
                        }
                    }
                    if (column.contains("TotalPeriodAdjustment")) {
                        gatheredColumn = headerKey + "TotalPeriodAdjustment";
                        if (VariableConstants.DEDUCTION_PRODUCT_DB.equals(view)) {
                            value = resultSet[11];
                        } else if (VariableConstants.DEDUCTION_CUSTOMER_DB.equals(view) || VariableConstants.CUSTOMER_DEDUC_DB.equals(view)) {
                            value = resultSet[13];
                        } else if (VariableConstants.DEDUCTION_CUS_CON_DB.equals(view)) {
                            value = resultSet[15];
                        }
                    }

                    if (column.contains("ActualPayments")) {
                        gatheredColumn = headerKey + "ActualPayments";
                        if (VariableConstants.DEDUCTION_PRODUCT_DB.equals(view)) {
                            value = resultSet[12];
                        } else if (VariableConstants.DEDUCTION_CUSTOMER_DB.equals(view) || VariableConstants.CUSTOMER_DEDUC_DB.equals(view)) {
                            value = resultSet[14];
                        } else if (VariableConstants.DEDUCTION_CUS_CON_DB.equals(view)) {
                            value = resultSet[16];
                        }

                    }

                    if (column.contains("EndingBalance")) {
                        gatheredColumn = headerKey + "EndingBalance";
                        if (VariableConstants.DEDUCTION_PRODUCT_DB.equals(view)) {
                            value = resultSet[13];
                        } else if (VariableConstants.DEDUCTION_CUSTOMER_DB.equals(view) || VariableConstants.CUSTOMER_DEDUC_DB.equals(view)) {
                            value = resultSet[15];
                        } else if (VariableConstants.DEDUCTION_CUS_CON_DB.equals(view)) {
                            value = resultSet[17];
                        }
                    }

                    if (column.startsWith(gatheredColumn)) {
                        if (column.matches("[a-zA-Z0-9-~\\s]+\\.\\d+$")) {
                            dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
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
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

}
