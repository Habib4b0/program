/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.utils.dto.ExcelSorterDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public static void setExcelData(final List resultList, final Object[] object, final List<String> visibleColumns, ExtTreeContainer<AdjustmentDTO> excelBeanContainer, final int discountColumnNeeded, final String module, List<Object> listData) throws IllegalAccessException, InvocationTargetException {
        int interval = (int) listData.get(3);
        if (module.equals(ARMConstants.getPipelineInventoryTrueUp())) {
            setExcelHierarchyData(inventoryCustomizer(resultList, object, visibleColumns, true, interval, discountColumnNeeded), excelBeanContainer);
        } else if (module.equals(ARMConstants.getTransaction8())) {
            setExcelHierarchyData(returnReserveCustomizer(resultList, object, visibleColumns, interval, discountColumnNeeded, listData), excelBeanContainer);
        } else if (module.equals("Rates")) {
            setExcelHierarchyData(customizer(resultList, object, visibleColumns, interval, discountColumnNeeded, listData, true), excelBeanContainer);
        } else {
            setExcelHierarchyData(customizer(resultList, object, visibleColumns, interval, discountColumnNeeded, listData, false), excelBeanContainer);
        }
    }

    public static List<Map<String, AdjustmentDTO>> customizer(final List resultList, final Object[] object, final List<String> visibleColumns, final int interval, final int discountColumnNeeded, List<Object> list, boolean isTrx3) throws IllegalAccessException, InvocationTargetException {

        int j = 1;
        int doubleHeaderIndex = 0;
        int keyParam = j;
        String oldC = StringUtils.EMPTY;
        String newC;
        AdjustmentDTO excelAdjustmentDto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        if ((Boolean) list.get(2) && (Boolean) list.get(1)) {
            doubleHeaderIndex = 1;
        } else {
            doubleHeaderIndex = object.length * NumericConstants.TWO;
        }
        String key = "0.";
        for (int i = 0; i < resultList.size(); i++) {
            Object[] obj = (Object[]) resultList.get(i);
            newC = String.valueOf(obj[j * NumericConstants.TWO]);
            if (!"0".equals(newC)) {
                if (!newC.equals("null")) {
                    if (!oldC.equals(newC) && object.length > keyParam) {

                        keyParam++;
                        if (String.valueOf(obj[(j + 1) * NumericConstants.TWO]).equalsIgnoreCase("null")) {
                            j++;
                        }
                    }

                } else if (!oldC.equals("null")) {

                    j = 1;
                    keyParam = 1;
                }
                key = getKey(obj, keyParam);
                newC = String.valueOf(obj[j * NumericConstants.TWO]);
                oldC = newC;
            }
            size = mapList.size();
            for (int l = 0; l < size; l++) {
                excelAdjustmentDto = mapList.get(l).get(key);
                if (excelAdjustmentDto != null) {
                    break;
                }
            }

            if (excelAdjustmentDto == null) {
                excelAdjustmentDto = new AdjustmentDTO();
                Map<String, AdjustmentDTO> map = mapList.get(size - 1);
                if (map.size() >= NumericConstants.THOUSAND) {
                    map = new HashMap<>();
                    mapList.add(map);
                }
                map.put(key, excelAdjustmentDto);
                for (int k = excelAdjustmentDto.getVisibleColumnIndex(); k < visibleColumns.size(); k++) {
                    String column = visibleColumns.get(k).replace(ARMUtils.SPACE.toString(), "");
                    excelAdjustmentDto.addStringProperties(column, StringUtils.EMPTY);
                }
            }
            int index = 0;
            int dedIndex = (object.length * NumericConstants.TWO) + discountColumnNeeded;
            for (int k = excelAdjustmentDto.getVisibleColumnIndex(); k < visibleColumns.size(); k++) {
                String column = visibleColumns.get(k).replace(ARMUtils.SPACE.toString(), "");
                boolean columnCheck = true;
                if (column.contains(ARMUtils.DOUBLE_HIPHEN)) {
                    String[] newColumn = column.split("\\--");
                    columnCheck = newColumn[0].equals(obj[dedIndex - 1].toString().replace(ARMUtils.SPACE.toString(), "")) || ARMUtils.TOTAL.equalsIgnoreCase(newColumn[0]);
                }
                int dataIndex = dedIndex + index;
                Object value = obj[dataIndex];
                if (columnCheck) {
                    if ("0.".equals(key)) {
                        if (column.startsWith(obj[doubleHeaderIndex].toString().replace(ARMUtils.SPACE.toString(), ""))) {
                            index = setExcelAndgetIndex(column, value, excelAdjustmentDto, index);
                            setTotalDTOValue(excelAdjustmentDto, value, column);
                            setTotalValue(newC, value, excelAdjustmentDto, isTrx3);
                            excelAdjustmentDto.setVisibleColumnIndex(0);
                        }
                    } else if ((Boolean) list.get(0) && column.startsWith(obj[doubleHeaderIndex].toString().replace(ARMUtils.SPACE.toString(), ""))) {
                        index = setExcelAndgetIndex(column, value, excelAdjustmentDto, index);
                        setTotalDTOValue(excelAdjustmentDto, value, column);
                        if (!"0".equals(newC) && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                            excelAdjustmentDto.setTotalColumnValue(excelAdjustmentDto.getTotalColumnValue() + Double.valueOf(String.valueOf(value)));
                        }
                        excelAdjustmentDto.setVisibleColumnIndex(0);
                    } else if (!(Boolean) list.get(0)) {
                        if (column.matches("[a-zA-Z0-9-\\s]+\\.\\d+$")) {
                            if (value instanceof BigInteger) {
                                excelAdjustmentDto.addProperties(column, value);
                            } else {
                                excelAdjustmentDto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                            }
                            index++;
                        } else {
                            BeanUtils.setProperty(excelAdjustmentDto, column, value);
                        }
                        excelAdjustmentDto.setVisibleColumnIndex(0);
                    } else if ((Boolean) list.get(1)) {
                        excelAdjustmentDto.addStringProperties(column, StringUtils.EMPTY);
                    }
                    if ((Boolean) list.get(0) && interval == index) {
                        index = 0;
                    }
                    setTotalProperity(excelAdjustmentDto, column);
                    excelAdjustmentDto.setTotalColumn(String.valueOf(excelAdjustmentDto.getTotalColumnValue()));
                }
            }
            excelAdjustmentDto.setGroup(obj[(keyParam * NumericConstants.TWO) - 1].toString());
            excelAdjustmentDto.setMonth(obj[(keyParam * NumericConstants.TWO) - 1].toString());
        }
        return mapList;
    }

    private static int setExcelAndgetIndex(String column, Object value, AdjustmentDTO excelAdjustmentDto, int newIndex) throws IllegalAccessException, InvocationTargetException {
        int index = newIndex;
        if (column.matches(CommonConstant.ALPHA)) {
            if (value instanceof BigInteger) {
                excelAdjustmentDto.addProperties(column, value);
            } else {
                excelAdjustmentDto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
            }
            index++;
        } else {
            BeanUtils.setProperty(excelAdjustmentDto, column, value);
            index++;
        }
        return index;
    }

    public static List<Map<String, AdjustmentDTO>> returnReserveCustomizer(final List resultList, final Object[] object, final List<String> visibleColumns, final int interval, final int discountColumnNeeded, List<Object> list) throws IllegalAccessException, InvocationTargetException {
        int j = 1;
        int excelDoubleHeaderIndex = 0;
        int keyParam = j;
        String oldC = StringUtils.EMPTY;
        String newC;
        AdjustmentDTO retResExcelAdjustmentDto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        if ((Boolean) list.get(2) && (Boolean) list.get(1)) {
            excelDoubleHeaderIndex = 1;
        } else {
            excelDoubleHeaderIndex = object.length * NumericConstants.TWO;
        }
        String key = "0.";
        for (int i = 0; i < resultList.size(); i++) {
            Object[] excelobj = (Object[]) resultList.get(i);
            newC = String.valueOf(excelobj[j * NumericConstants.TWO]);
            if (!"0".equals(newC)) {
                if (!newC.equals("null")) {
                    if (!oldC.equals(newC) && object.length > keyParam) {

                        keyParam++;
                        if (String.valueOf(excelobj[(j + 1) * NumericConstants.TWO]).equalsIgnoreCase("null")) {
                            j++;
                        }
                    }

                } else if (!oldC.equals("null")) {

                    j = 1;
                    keyParam = 1;
                }
                key = getKey(excelobj, keyParam);
                newC = String.valueOf(excelobj[j * NumericConstants.TWO]);
                oldC = newC;
            }
            size = mapList.size();

            for (int l = 0; l < size; l++) {
                retResExcelAdjustmentDto = mapList.get(l).get(key);
                if (retResExcelAdjustmentDto != null) {
                    break;
                }
            }

            if (retResExcelAdjustmentDto == null) {
                retResExcelAdjustmentDto = new AdjustmentDTO();
                Map<String, AdjustmentDTO> map = mapList.get(size - 1);
                if (map.size() >= NumericConstants.THOUSAND) {
                    map = new HashMap<>();
                    mapList.add(map);
                }
                map.put(key, retResExcelAdjustmentDto);
            }
            int index = 0;
            for (int k = retResExcelAdjustmentDto.getVisibleColumnIndex(); k < visibleColumns.size(); k++) {
                String column = visibleColumns.get(k).replace(ARMUtils.SPACE.toString(), "");
                int dataIndex = (object.length * NumericConstants.TWO) + discountColumnNeeded + index;
                Object value = excelobj[dataIndex];
                if ("null".equals(String.valueOf(excelobj[object.length * NumericConstants.TWO - 1]))) {
                    retResExcelAdjustmentDto.addStringProperties(column, StringUtils.EMPTY);
                    index++;
                } else if ("0.".equals(key)) {
                    if (column.startsWith(excelobj[excelDoubleHeaderIndex].toString().replace(ARMUtils.SPACE.toString(), ""))) {
                        index = setExcelAndgetIndex(column, value, retResExcelAdjustmentDto, index);
                        setTotalDTOValue(retResExcelAdjustmentDto, value, column);
                        if ("0".equals(newC) && value != null && !Double.valueOf(String.valueOf(value)).isNaN() && String.valueOf(value).matches("^[\\d.]+$")) {
                            retResExcelAdjustmentDto.setTotalColumnValue(retResExcelAdjustmentDto.getTotalColumnValue() + Double.valueOf(String.valueOf(value)));
                        }
                        retResExcelAdjustmentDto.setVisibleColumnIndex(0);
                    }
                } else if ((Boolean) list.get(0) && column.startsWith(excelobj[excelDoubleHeaderIndex].toString().replace(ARMUtils.SPACE.toString(), ""))) {

                    index = setExcelAndgetIndex(column, value, retResExcelAdjustmentDto, index);
                    setTotalDTOValue(retResExcelAdjustmentDto, value, column);
                    if (!"0".equals(newC) && value != null && !Double.valueOf(String.valueOf(value)).isNaN()) {
                        retResExcelAdjustmentDto.setTotalColumnValue(retResExcelAdjustmentDto.getTotalColumnValue() + Double.valueOf(String.valueOf(value)));
                    }
                    retResExcelAdjustmentDto.setVisibleColumnIndex(0);
                } else if (!(Boolean) list.get(0)) {
                    if (column.matches("[a-zA-Z0-9-\\s]+\\.\\d+$")) {
                        if (value instanceof BigInteger) {
                            retResExcelAdjustmentDto.addProperties(column, value);
                        } else {
                            retResExcelAdjustmentDto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
                        index++;
                    } else {
                        BeanUtils.setProperty(retResExcelAdjustmentDto, column, value);
                    }
                    retResExcelAdjustmentDto.setVisibleColumnIndex(0);
                } else if ((Boolean) list.get(1)) {
                    retResExcelAdjustmentDto.addStringProperties(column, StringUtils.EMPTY);
                }

                if ((Boolean) list.get(0) && interval == index) {
                    index = 0;
                }
                setTotalProperity(retResExcelAdjustmentDto, column);
                retResExcelAdjustmentDto.setTotalColumn(String.valueOf(retResExcelAdjustmentDto.getTotalColumnValue()));
            }
            retResExcelAdjustmentDto.setGroup(excelobj[(keyParam * NumericConstants.TWO) - 1].toString());
            retResExcelAdjustmentDto.setMonth(excelobj[(keyParam * NumericConstants.TWO) - 1].toString());
        }
        return mapList;
    }

    /**
     * Method used for returning key from the Object array
     *
     * @param obj
     * @param j
     * @return
     */
    public static String getKey(Object[] obj, int j) {
        StringBuilder returnValue = new StringBuilder("");
        for (int i = 0; i < j; i++) {
            if (!"null".equals(String.valueOf(obj[i * NumericConstants.TWO]))) {
                returnValue.append(String.valueOf(obj[i * NumericConstants.TWO])).append(ARMUtils.DOT);
            }
        }
        return returnValue.toString();
    }

    public static void setExcelHierarchyData(List<Map<String, AdjustmentDTO>> mapList, ExtTreeContainer<AdjustmentDTO> excelBeanContainer) {
        List<String> keyList = new ArrayList<>();
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
            if (dto != null) {
                excelBeanContainer.addItem(dto);
            }
            if (parent != null) {
                excelBeanContainer.setParent(dto, parent);
            }
            List<String> listKey1 = searchChild(keyValue + "*.", hierKeySet);
            setContainerValue(dto, excelBeanContainer, mapList, hierKeySet, listKey1);
        }
    }

    public static AdjustmentDTO getMapListValue(List<Map<String, AdjustmentDTO>> mapList, String keyValue) {
        AdjustmentDTO dto = null;
        for (int i = 0; i < mapList.size(); i++) {
            dto = mapList.get(i).remove(keyValue);
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
        String queryStrs = queryStr.replaceAll("\\*", "\\\\w*");
        List< String> list = new ArrayList<>();
        for (HierarchyString str : valuesList) {
            if (str.getString().matches(queryStrs)) {
                list.add(str.getString());
            }
        }
        list = getSortedList(list);
        return list;
    }

    public static void setTotalDTOValue(AdjustmentDTO dto, Object value, String column) {
        getDemandColumns(column, value, dto);
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

    private static void getDemandColumns(String column, Object value, AdjustmentDTO dto) {
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
    }

    public static void setTotalProperity(AdjustmentDTO dto, String column) {
        getStringProperties(column, dto, "Total--demandAccrual.", dto.getExcelTotalDemandAccrual());
        getStringProperties(column, dto, "Total--demandAccrualReforecast..", dto.getExcelTotalDemandAccrualReforecast());
        getStringProperties(column, dto, "Total--totalDemandAccrual.", dto.getExcelTotalTotalDemandAccrual());
        getStringProperties(column, dto, "Total--projectedTotalDemandAccrual.", dto.getExcelTotalProjectedTotalDemandAccrual());
        getStringProperties(column, dto, "Total--variance.", dto.getExcelTotalVariance());
        getStringProperties(column, dto, "Total--adjustment.", dto.getExcelTotalAdjustment());
        getStringProperties(column, dto, "Total--demandPaymentRecon.", dto.getExcelTotalDemandPaymentRecon());
        getStringProperties(column, dto, "Total--actualPayments.", dto.getExcelTotalActualPayments());
        getStringProperties(column, dto, "Total--cPipelineAccrual.", dto.getExcelTotalcPipelineAccrual());
        getStringProperties(column, dto, "Total--pPipelineAccrual.", dto.getExcelTotalpPipelineAccrual());
        if (column.contains("Total--demandAccrualRatio.")) {
            Double demandAccrualRatio = 0.0;
            if (Double.compare(dto.getExcelTotalProjectedTotalDemandAccrual(), 0.0) != 0) {
                demandAccrualRatio = dto.getExcelTotalTotalDemandAccrual() / dto.getExcelTotalProjectedTotalDemandAccrual() * NumericConstants.HUNDRED;
            }
            dto.addStringProperties(column, "" + new BigDecimal(demandAccrualRatio));
        }
        if (column.contains("Total--override.")) {
            if (dto.getExcelTotalOverride() != null) {
                dto.addStringProperties(column, "" + new BigDecimal(dto.getExcelTotalOverride()));
            } else {
                dto.addStringProperties(column, "");
            }
        }
        if (column.contains("Total--pipelineRatio.")) {
            Double totalpipelineRatio = 0.0;
            if (Double.compare(dto.getExcelTotalpPipelineAccrual(), 0.0) != 0) {
                totalpipelineRatio = dto.getExcelTotalcPipelineAccrual() / dto.getExcelTotalpPipelineAccrual();
            }
            dto.addStringProperties(column, "" + new BigDecimal(totalpipelineRatio));
        }

        if (column.contains("Total--paymentRatio.")) {
            Double totalpaymentRatio = 0.0;
            if ((Double.compare(dto.getExcelTotalActualPayments(), 0.0) != 0)) {
                totalpaymentRatio = (dto.getExcelTotalTotalDemandAccrual() / dto.getExcelTotalActualPayments()) * NumericConstants.HUNDRED;
            }
            dto.addStringProperties(column, "" + new BigDecimal(totalpaymentRatio));
        }
    }

    private static void getStringProperties(String column, AdjustmentDTO dto, String checkColumn, Double value) {
        // Case Sensitive. Do not change the case.
        if (column.contains(checkColumn)) {
            dto.addStringProperties(column, "" + new BigDecimal(value));
        }
    }

    public static List<Map<String, AdjustmentDTO>> inventoryCustomizer(final List resultList, final Object[] object, final List<String> visibleColumns, final boolean isFixedColumns, final int interval, final int discountColumnNeeded) {
        int j = 1;
        int keyParam = j;
        String oldC = StringUtils.EMPTY;
        String newC;
        String column;
        AdjustmentDTO inventoryAdjustmentDto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] resultSet = (Object[]) resultList.get(i);
                newC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                if (!newC.equals("null")) {
                    if (!oldC.equals(newC) && object.length > keyParam) {

                        keyParam++;
                        if (String.valueOf(resultSet[(j + 1) * NumericConstants.TWO]).equalsIgnoreCase("null")) {
                            j++;
                        }
                    }
                } else if (!oldC.equals("null")) {

                    j = 1;
                    keyParam = 1;
                }

                newC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                oldC = newC;
                String key = getKey(resultSet, keyParam);
                size = mapList.size();
                for (int l = 0; l < size; l++) {
                    inventoryAdjustmentDto = mapList.get(l).get(key);
                    if (inventoryAdjustmentDto != null) {
                        break;
                    }
                }
                if (inventoryAdjustmentDto == null) {
                    inventoryAdjustmentDto = new AdjustmentDTO();
                    Map<String, AdjustmentDTO> inventoryMap = mapList.get(size - 1);
                    if (inventoryMap.size() >= NumericConstants.THOUSAND) {
                        inventoryMap = new HashMap<>();
                        mapList.add(inventoryMap);
                    }
                    inventoryMap.put(key, inventoryAdjustmentDto);
                }
                int index = 0;
                for (int k = 0; k < visibleColumns.size(); k++) {
                    column = visibleColumns.get(k).replace(ARMUtils.SPACE.toString(), "");
                    int dataIndex = (object.length * NumericConstants.TWO) + discountColumnNeeded + index;
                    Object value = resultSet[dataIndex];
                    String company = String.valueOf(resultSet[object.length * NumericConstants.TWO]);
                    if (column.contains("~" + company)) {
                        inventoryAdjustmentDto.addStringProperties(column, String.valueOf(resultSet[(object.length * NumericConstants.TWO) + 1]));
                    } else if (!column.contains("~")) {
                        if (column.matches(CommonConstant.ALPHA)) {
                            inventoryAdjustmentDto.addStringProperties(column, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
                        index++;
                        if (isFixedColumns && interval == index) {
                            index = 0;
                        }
                    }
                }
                inventoryAdjustmentDto.setGroup(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
                inventoryAdjustmentDto.setMonth(resultSet[(keyParam * NumericConstants.TWO) - 1].toString());
            }
        }
        return mapList;
    }

    public static List<Map<String, AdjustmentDTO>> adjustmentSummaryModuleCustomizer(final List resultList, final Object[] object, final List<String> visibleColumns, final boolean isFixedColumns, final int interval, final int discountColumnNeeded) {
        int j = 1;
        int summaryKeyParam = j;
        String summaryOldC = StringUtils.EMPTY;
        String newC;
        String summaryColumn;
        AdjustmentDTO summaryAdjustmentDto = null;
        List<Map<String, AdjustmentDTO>> mapList = new ArrayList<>();
        mapList.add(new HashMap<String, AdjustmentDTO>());
        int size;
        String key = "0.";
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] resultSet = (Object[]) resultList.get(i);
                newC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                if (!"0".equals(newC)) {
                    if (!newC.equals("null")) {
                        if (!summaryOldC.equals(newC) && object.length > summaryKeyParam) {

                            summaryKeyParam++;
                            if (String.valueOf(resultSet[(j + 1) * NumericConstants.TWO]).equalsIgnoreCase("null")) {
                                j++;
                            }
                        }
                    } else if (!summaryOldC.equals("null")) {

                        j = 1;
                        summaryKeyParam = 1;
                    }
                    key = getKey(resultSet, summaryKeyParam);
                    newC = String.valueOf(resultSet[j * NumericConstants.TWO]);
                    summaryOldC = newC;
                }
                size = mapList.size();
                for (int l = 0; l < size; l++) {
                    summaryAdjustmentDto = mapList.get(l).get(key);
                    if (summaryAdjustmentDto != null) {
                        break;
                    }
                }
                if (summaryAdjustmentDto == null) {
                    summaryAdjustmentDto = new AdjustmentDTO();
                    Map<String, AdjustmentDTO> map = mapList.get(size - 1);
                    if (map.size() >= NumericConstants.THOUSAND) {
                        map = new HashMap<>();
                        mapList.add(map);
                    }
                    map.put(key, summaryAdjustmentDto);
                }
                int index = 0;
                for (int k = 0; k < visibleColumns.size(); k++) {

                    summaryColumn = visibleColumns.get(k).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY).replace("-", StringUtils.EMPTY);
                    int dataIndex = (object.length * NumericConstants.TWO) + discountColumnNeeded + index;
                    Object value = resultSet[dataIndex];
                    String company = String.valueOf(resultSet[object.length * NumericConstants.TWO]).replace(ARMUtils.SPACE.toString(), "").replace("-", StringUtils.EMPTY) + String.valueOf(resultSet[(object.length * NumericConstants.TWO) + 1]).replace(ARMUtils.SPACE.toString(), "").replace("-", StringUtils.EMPTY);
                    if (summaryColumn.startsWith(company)) {
                        if (summaryColumn.matches(CommonConstant.ALPHA)) {
                            summaryAdjustmentDto.addStringProperties(summaryColumn, value == null ? StringUtils.EMPTY : String.valueOf(value));
                        }
                        index++;
                        if (isFixedColumns && interval == index) {
                            index = 0;
                        }
                    }
                }
                summaryAdjustmentDto.setGroup(resultSet[(summaryKeyParam * NumericConstants.TWO) - 1].toString());
                summaryAdjustmentDto.setMonth(resultSet[(summaryKeyParam * NumericConstants.TWO) - 1].toString());
            }
        }
        return mapList;
    }

    private static List<String> getSortedList(List<String> list) {
        boolean flag = false;
        List<ExcelSorterDTO> dtoList = new ArrayList<>();
        for (String string : list) {
            String[] str = string.split("\\.");
            if (!str[str.length - 1].matches("^(-?[1-9]+\\d*)$|^0$")) {
                flag = true;
                Collections.sort(list);
                break;
            }
            ExcelSorterDTO dto = new ExcelSorterDTO();
            dto.setKey(Integer.valueOf(str[str.length - 1]));
            dto.setValue(string);
            dtoList.add(dto);
        }
        if (!flag) {
            Collections.sort(dtoList, new CustomComparator());
            list.clear();
            for (ExcelSorterDTO excelSorterDTO : dtoList) {
                list.add(excelSorterDTO.getValue());
            }
        }
        return list;
    }

    private static void setTotalValue(String newC, Object value, AdjustmentDTO dto, boolean isTrx3) {
        if ("0".equals(newC) && value != null && !Double.valueOf(String.valueOf(value)).isNaN() && isTrx3 ? true : String.valueOf(value).matches("^[\\d.]+$")) {
            dto.setTotalColumnValue(dto.getTotalColumnValue() + Double.valueOf(String.valueOf(value)));
        }
    }

    private ExcelUtils() {
        /*
        Empty COnstructor
         */
    }

    public static class CustomComparator implements Comparator<ExcelSorterDTO> {

        @Override
        public int compare(ExcelSorterDTO o1, ExcelSorterDTO o2) {
            return o1.getKey() == o2.getKey() ? o1.getValue().compareTo(o2.getValue()) : ((Integer) o1.getKey()).compareTo((Integer) o2.getKey());
        }

    }

}
