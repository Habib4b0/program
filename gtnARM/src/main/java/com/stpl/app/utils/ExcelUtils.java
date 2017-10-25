/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.HierarchyString;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class ExcelUtils {

    protected static final Map<Integer, String> idDescMap = HelperListUtil.getInstance().getIdDescMap();

    public static void setExcelData(final List resultList, final Object[] object, final List<String> visibleColumns, ExtTreeContainer<AdjustmentDTO> excelBeanContainer, final boolean isFixedColumns, final int interval, final int discountColumnNeeded, final boolean isDeductionCustomer, final Boolean isInventory, final boolean isDemandScreen) throws IllegalAccessException, InvocationTargetException {

        if (isInventory) {
            setExcelHierarchyData(InventoryCustomizer(resultList, object, visibleColumns, true, interval, discountColumnNeeded), excelBeanContainer);
        } else {
            setExcelHierarchyData(Customizer(resultList, object, visibleColumns, isFixedColumns, interval, discountColumnNeeded, isDeductionCustomer, isDemandScreen), excelBeanContainer);
        }
    }

    public static List<Map<String, AdjustmentDTO>> Customizer(final List resultList, final Object[] object, final List<String> visibleColumns, final boolean isFixedColumns, final int interval, final int discountColumnNeeded, final boolean isDeductionCustomer, final boolean isDemandScreen) throws IllegalAccessException, InvocationTargetException {

        int j = 1;
        int doubleHeaderIndex = 0;
        int keyParam = j;
        String oldC = StringUtils.EMPTY;
        String newC = StringUtils.EMPTY;
        AdjustmentDTO dto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<Map<String, AdjustmentDTO>>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size = mapList.size();
        if (isDemandScreen && isDeductionCustomer) {
            doubleHeaderIndex = 1;
        } else {
            doubleHeaderIndex = object.length * NumericConstants.TWO;
        }
        String key = "0.";
        for (int i = 0; i < resultList.size(); i++) {
            Object[] obj = (Object[]) resultList.get(i);
            newC = String.valueOf(obj[j * NumericConstants.TWO] );
            if (!"0".equals(newC)) {
                if (!newC.equals("null")) {
                    if (!oldC.equals(newC)) {
                        oldC = "";
                        if (object.length > keyParam) {
                            keyParam++;
                            if (String.valueOf(obj[(j + 1) * NumericConstants.TWO] ).equalsIgnoreCase("null")) {
                                j++;
                            }
                        }
                    }

                } else if (!oldC.equals("null")) {
                    oldC = "";
                    j = 1;
                    keyParam = 1;
                }
                key = getKey(obj, keyParam);
                newC = String.valueOf(obj[j * NumericConstants.TWO] );
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
            int index = 0;
            for (int k = dto.getVisibleColumnIndex(); k < visibleColumns.size(); k++) {
                String column = visibleColumns.get(k).replace(" ", "");
                int dataIndex = (object.length * NumericConstants.TWO) + discountColumnNeeded + index;
                Object value = obj[dataIndex];
                if ("0.".equals(key)) {
                    if (column.startsWith(obj[doubleHeaderIndex].toString().replace(" ", ""))) {
                        if (column.matches("[a-zA-Z0-9-_~\\s]+\\.\\d+$")) {

                            if (value instanceof BigInteger) {
                                dto.addProperties(column, value);
                            } else {
                                dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                            }
                            index++;
                        } else {
                            BeanUtils.setProperty(dto, column, value);
                            index++;
                        }
                        setTotalDTOValue(dto, value, column);
                        if ("0".equals(newC) && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                            dto.setTotalColumnValue(dto.getTotalColumnValue() + Double.valueOf(String.valueOf(value)));
                        }
                        dto.setVisibleColumnIndex(0);
                    }
                } else if (isFixedColumns && column.startsWith(obj[doubleHeaderIndex].toString().replace(" ", ""))) {
                    if (column.matches("[a-zA-Z0-9-_~\\s]+\\.\\d+$")) {
                        if (value instanceof BigInteger) {
                            dto.addProperties(column, value);
                        } else {
                            dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
                        index++;
                    } else {
                        BeanUtils.setProperty(dto, column, value);
                        index++;
                    }
                    setTotalDTOValue(dto, value, column);
                    if (!"0".equals(newC) && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                        dto.setTotalColumnValue(dto.getTotalColumnValue() + Double.valueOf(String.valueOf(value)));
                    }
                    dto.setVisibleColumnIndex(0);
                } else if (!isFixedColumns) {
                    if (column.matches("[a-zA-Z0-9-\\s]+\\.\\d+$")) {
                        if (value instanceof BigInteger) {
                            dto.addProperties(column, value);
                        } else {
                            dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
                        index++;
                    } else {
                        BeanUtils.setProperty(dto, column, value);
                    }
                    dto.setVisibleColumnIndex(0);
                } else if (isDeductionCustomer) {
                    dto.addStringProperties(column, StringUtils.EMPTY);
                }

                if (isFixedColumns && interval == index) {
                    index = 0;
                }
                setTotalProperity(dto, column);
                dto.setTotalColumn(String.valueOf(dto.getTotalColumnValue()));
            }
            dto.setGroup(obj[(keyParam * NumericConstants.TWO) - 1].toString());
            dto.setMonth(obj[(keyParam * NumericConstants.TWO) - 1].toString());
        }
        return mapList;
    }

    /**
     * Method used for returning key from the Object array
     *
     * @param obj
     * @param inputArr
     * @param j
     * @return
     */
    public static String getKey(Object[] obj, int j) {
        String returnValue = "";
        for (int i = 0; i < j; i++) {
            returnValue += String.valueOf(obj[i * NumericConstants.TWO] ) + ".";
        }
        return returnValue;
    }

    public static void setExcelHierarchyData(List<Map<String, AdjustmentDTO>> mapList, ExtTreeContainer<AdjustmentDTO> excelBeanContainer) {
        List<String> keyList = new ArrayList<String>();
        for (int i = 0; i < mapList.size(); i++) {
            keyList.addAll(mapList.get(i).keySet());
        }
        boolean hasTotal = keyList.remove("0.");
        AdjustmentDTO totalDto = null;
        if (hasTotal) {
            totalDto = getMapListValue(mapList, "0.");
        }
        List<HierarchyString> strkeys = HierarchyString.getHierarchyStringList(keyList, true);
        List<String> listKey = searchChild("*.", strkeys);
        setContainerValue(null, excelBeanContainer, mapList, strkeys, listKey);
        if (totalDto != null) {
            totalDto.setGroup("Total");
            totalDto.setMonth("Total");
            excelBeanContainer.addItem(totalDto);
        }
    }

    public static void setContainerValue(AdjustmentDTO parent, ExtTreeContainer<AdjustmentDTO> excelBeanContainer, List<Map<String, AdjustmentDTO>> mapList, List<HierarchyString> hierKeySet, List<String> listKey) {
        for (String keyValue : listKey) {
            AdjustmentDTO dto = getMapListValue(mapList, keyValue);
            excelBeanContainer.addItem(dto);
            if (parent != null) {
                excelBeanContainer.setParent(dto, parent);
            }
            List<String> listKey1 = searchChild(keyValue + "*.", hierKeySet);
            setContainerValue( dto, excelBeanContainer, mapList, hierKeySet, listKey1);
        }
    }

    public static AdjustmentDTO getMapListValue(List<Map<String, AdjustmentDTO>> mapList, String KeyValue) {
        AdjustmentDTO dto = null;
        for (int i = 0; i < mapList.size(); i++) {
            dto = mapList.get(i).remove(KeyValue);
            if (dto != null) {
                break;
            }
        }
        return dto;
    }

    public static int getCount(List<String> keySet, String keyValue) {
        int index = 0;
        for (String keys : keySet) {
            if (keys.startsWith(keyValue)) {
                index += 1;
            }
        }
        return index;
    }

    //Remember
    //Hierarchy String Need To Change 
    public static List<String> searchChild(String queryStr, List<HierarchyString> valuesList) {
        queryStr = queryStr.replaceAll("\\*", "\\\\w*");
        List< String> list = new ArrayList< String>();
        for (HierarchyString str : valuesList) {
            if (str.getString().matches(queryStr)) {
                list.add(str.getString());
            }
        }
        return list;
    }

    public static void setTotalDTOValue(AdjustmentDTO dto, Object value, String column) {
            if (column.contains("demandAccrual.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalDemandAccrual(dto.getExcelTotalDemandAccrual() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("demandAccrualReforecast.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalDemandAccrualReforecast(dto.getExcelTotalDemandAccrualReforecast() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("totalDemandAccrual.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalTotalDemandAccrual(dto.getExcelTotalTotalDemandAccrual() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("projectedTotalDemandAccrual.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalProjectedTotalDemandAccrual(dto.getExcelTotalProjectedTotalDemandAccrual() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("variance.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalVariance(dto.getExcelTotalVariance() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("override.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                if (dto.getExcelTotalOverride() == null) {
                    dto.setExcelTotalOverride(Double.valueOf(String.valueOf(value)));
                } else {
                    dto.setExcelTotalOverride(dto.getExcelTotalOverride() + Double.valueOf(String.valueOf(value)));
                }
            }
            if (column.contains("adjustment.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalAdjustment(dto.getExcelTotalAdjustment() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("demandPaymentRecon.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalDemandPaymentRecon(dto.getExcelTotalDemandPaymentRecon() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("actualPayments.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalActualPayments(dto.getExcelTotalActualPayments() + Double.valueOf(String.valueOf(value)));
            }

            if (column.contains("cPipelineAccrual.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalcPipelineAccrual(dto.getExcelTotalcPipelineAccrual() + Double.valueOf(String.valueOf(value)));
            }
            if (column.contains("pPipelineAccrual.") && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                dto.setExcelTotalpPipelineAccrual(dto.getExcelTotalpPipelineAccrual() + Double.valueOf(String.valueOf(value)));
            }

    }

    public static void setTotalProperity(AdjustmentDTO dto, String column) {
        // Case Sensitive. Do not change the case.
        if (column.contains("TotaldemandAccrual.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalDemandAccrual()));
        }
        if (column.contains("TotaldemandAccrualReforecast.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalDemandAccrualReforecast()));
        }
        if (column.contains("TotaltotalDemandAccrual.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalTotalDemandAccrual()));
        }
        if (column.contains("TotalprojectedTotalDemandAccrual.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalProjectedTotalDemandAccrual()));
        }
        if (column.contains("TotaldemandAccrualRatio.")) {
            Double demandAccrualRatio = 0.0;
            if (dto.getExcelTotalProjectedTotalDemandAccrual() != 0.0) {
                demandAccrualRatio = dto.getExcelTotalTotalDemandAccrual() / dto.getExcelTotalProjectedTotalDemandAccrual() * NumericConstants.HUNDRED;
            }
            dto.addStringProperties(column, "" + new BigDecimal(demandAccrualRatio));
        }
        if (column.contains("Totalvariance.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalVariance()));
        }
        if (column.contains("Totaloverride.")) {
            if (dto.getExcelTotalOverride() != null) {
                dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalOverride()));
            } else {
                dto.addStringProperties(column, "");
            }
        }
        if (column.contains("Totaladjustment.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalAdjustment()));
        }
        if (column.contains("TotaldemandPaymentRecon.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalDemandPaymentRecon()));
        }
        if (column.contains("TotalactualPayments.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalActualPayments()));
        }
        if (column.contains("TotalcPipelineAccrual.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalcPipelineAccrual()));
        }
        if (column.contains("TotalpPipelineAccrual.")) {
            dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalpPipelineAccrual()));
        }
        if (column.contains("TotalpipelineRatio.")) {
            Double totalpipelineRatio = 0.0;
            if (dto.getExcelTotalpPipelineAccrual() != 0.0) {
                totalpipelineRatio = dto.getExcelTotalcPipelineAccrual() / dto.getExcelTotalpPipelineAccrual();
            }
            dto.addStringProperties(column, "" + new BigDecimal(totalpipelineRatio));
        }

        if (column.contains("TotalpaymentRatio.")) {
            Double totalpaymentRatio = 0.0;
            if (dto.getExcelTotalActualPayments() != 0.0) {
                totalpaymentRatio = (dto.getExcelTotalTotalDemandAccrual() / dto.getExcelTotalActualPayments()) * NumericConstants.HUNDRED;
            }
            dto.addStringProperties(column, "" + new BigDecimal(totalpaymentRatio));
        }
    }

    public static List<Map<String, AdjustmentDTO>> InventoryCustomizer(final List resultList, final Object[] object, final List<String> visibleColumns, final boolean isFixedColumns, final int interval, final int discountColumnNeeded) throws IllegalAccessException, InvocationTargetException {
        int j = 1;
        int keyParam = j;
        String oldC = StringUtils.EMPTY;
        String newC = StringUtils.EMPTY;
        String column = StringUtils.EMPTY;
        AdjustmentDTO dto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<Map<String, AdjustmentDTO>>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size = mapList.size();
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] resultSet = (Object[]) resultList.get(i);
                newC = String.valueOf(resultSet[j * NumericConstants.TWO] );
                if (!newC.equals("null")) {
                    if (!oldC.equals(newC)) {
                        oldC = "";
                        if (object.length > keyParam) {
                            keyParam++;
                            if (String.valueOf(resultSet[(j + 1) * NumericConstants.TWO] ).equalsIgnoreCase("null")) {
                                j++;
                            }
                        }
                    }
                } else if (!oldC.equals("null")) {
                    oldC = "";
                    j = 1;
                    keyParam = 1;
                }

                newC = String.valueOf(resultSet[j * NumericConstants.TWO] );
                oldC = newC;
                String key = getKey(resultSet, keyParam);
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
                int index = 0;
                for (int k = 0; k < visibleColumns.size(); k++) {
                    column = visibleColumns.get(k).replace(" ", "");
                    int dataIndex = (object.length * NumericConstants.TWO) + discountColumnNeeded + index;
                    Object value = resultSet[dataIndex];
                    String company = String.valueOf(resultSet[object.length * NumericConstants.TWO] );
                    if (column.contains("~" + company)) {
                        dto.addStringProperties(column, String.valueOf(resultSet[(object.length * NumericConstants.TWO) + 1]));
                    } else if (!column.contains("~")) {
                        if (column.matches("[a-zA-Z0-9-_~\\s]+\\.\\d+$")) {
                            dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
                        index++;
                        if (isFixedColumns && interval == index) {
                            index = 0;
                        }
                    }
                }
                dto.setGroup(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
                dto.setMonth(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
            }
        }
        return mapList;
    }

    public static List<Map<String, AdjustmentDTO>> AdjustmentSummaryModuleCustomizer(final List resultList, final Object[] object, final List<String> visibleColumns, final boolean isFixedColumns, final int interval, final int discountColumnNeeded) throws IllegalAccessException, InvocationTargetException {
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
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] resultSet = (Object[]) resultList.get(i);
                newC = String.valueOf(resultSet[j * NumericConstants.TWO] );
                if (!"0".equals(newC)) {
                    if (!newC.equals("null")) {
                        if (!oldC.equals(newC)) {
                            oldC = "";
                            if (object.length > keyParam) {
                                keyParam++;
                                if (String.valueOf(resultSet[(j + 1) * NumericConstants.TWO] ).equalsIgnoreCase("null")) {
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
                    newC = String.valueOf(resultSet[j * NumericConstants.TWO] );
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
                int index = 0;
                for (int k = 0; k < visibleColumns.size(); k++) {

                    column = visibleColumns.get(k).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    int dataIndex = (object.length * NumericConstants.TWO) + discountColumnNeeded + index;
                    Object value = resultSet[dataIndex];
                    String company = String.valueOf(resultSet[object.length * NumericConstants.TWO] ).replace(" ", "").replace("-", StringUtils.EMPTY) + String.valueOf(resultSet[(object.length * NumericConstants.TWO) + 1]).replace(" ", "").replace("-", StringUtils.EMPTY);
                    if (column.startsWith(company)) {
                        if (column.matches("[a-zA-Z0-9-_~\\s]+\\.\\d+$")) {
                            dto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
                        index++;
                        if (isFixedColumns && interval == index) {
                            index = 0;
                        }
                    }
                }
                dto.setGroup(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
                dto.setMonth(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
            }
        }
        return mapList;
    }

}
