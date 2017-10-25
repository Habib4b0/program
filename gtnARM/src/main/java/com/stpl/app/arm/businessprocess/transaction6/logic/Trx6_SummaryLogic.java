
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.constants.ARMConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.drools.core.util.StringUtils;

/**
 *
 * @author srithar
 */
public class Trx6_SummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {

    @Override
    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        List<Object> finalList = new ArrayList<>();
        List<String> double_single_Column;
        List<String> excel_double_single_Column;
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> excelVisibleColumn = new ArrayList<>();
        List<String> excelVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleColumn = new ArrayList<>();
        Map<Object, Object[]> double_single_visibleColumn = new HashMap<>();
        Map<Object, Object[]> excel_double_single_visibleColumn = new HashMap<>();
        excel_double_single_visibleColumn.put("month", new Object[]{"month"});
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleHeader = new ArrayList<>();

        List<String> singleColumn = new ArrayList<>();
        selection.setSummary_columnList(singleColumn);
        int index = 0;
        List<String> columnList = getColumns(selection.getSummary_variables());
        List<String[]> doubleHeaderVariables = selection.getSummary_deductionVariables();
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        doubleHeaderVariables = doublecolumnList;      
        
        for (String[] detection : doubleHeaderVariables) {
            double_single_Column = new ArrayList<>();
            excel_double_single_Column = new ArrayList<>();
            for (int i = 0; i < columns.length; i++, index++) {
                String column = columns[i];
                singleColumn.add(column + "." + index);
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummary_variables().get(listIndex)[0] + "." + index;
                    String header = selection.getSummary_variables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    double_single_Column.add(visibleColumn);
                    excelVisibleColumn.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                    excelVisibleHeader.add(header);
                    excel_double_single_Column.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                }
            }

            doubleVisibleColumn.add(detection[0]);
            doubleVisibleHeader.add(detection[1]);
            double_single_visibleColumn.put(detection[0], double_single_Column.toArray());
            excel_double_single_visibleColumn.put(detection[0], excel_double_single_Column.toArray());
        }
        finalList.add(singleVisibleColumn);
        finalList.add(doubleVisibleColumn);
        finalList.add(singleVisibleHeader);
        finalList.add(doubleVisibleHeader);
        finalList.add(double_single_visibleColumn);
        finalList.add(excelVisibleColumn);
        finalList.add(excelVisibleHeader);
        finalList.add(excel_double_single_visibleColumn);
        return finalList;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {

        List resultList;
        int count = 0;
        try {
            boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            if (isView) {
                resultList = QueryUtils.getItemData(inputs, "getTrx6_Summary_generate_InventoryEdit", "getTrx6_Summary_generate_Inventory_Count", null, null, "ARM_INFLATION_INVENTORY_ADJ", null, null);
            } else {
                resultList = QueryUtils.getItemData(inputs, "getTrx6_Summary_generate_InventoryEdit", "getTrx6_Summary_generate_Inventory_Count", null, null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"), null, null);
            }
                count = (int) resultList.get(0);
            if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getSummary_levelFilterNo() == 0)) {
                    count = count + 1;
            }
        } catch (Exception e) {
           LOGGER.error(e);
        }
        return count;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {

        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage())) &&  (criteria.getSelectionDto().getSummary_levelFilterNo() == 0) ) {
                totalFlag = true;
                int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
                inputs.set(inputs.size() - 1, offset);
            
        }
        List<Object[]> data;
        if (isView) {
            data = QueryUtils.getItemData(inputs, "getTrx6_Summary_generate_InventoryEdit", "getTrx6_Summary_generate_Inventory_select", null, "getTrx6_Summary_generate_Inventory_select_orderBy", "ARM_INFLATION_INVENTORY_ADJ", null, "WHERE RANK_VIEW BETWEEN ? AND ?");
        } else {
            data = QueryUtils.getItemData(inputs, "getTrx6_Summary_generate_InventoryEdit", "getTrx6_Summary_generate_Inventory_select", null, "getTrx6_Summary_generate_Inventory_select_orderBy", criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"), null, "WHERE RANK_VIEW BETWEEN ? AND ?");
        }
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterSids, criteria);
        if (totalFlag) {
            List<Object[]> totaldata;
            for (Iterator<Object> it = inputs.iterator(); it.hasNext();) {
                Object object = it.next();
                if (StringUtils.EMPTY.equals(String.valueOf(object))) {
                    it.remove();
                }
            }
            List<Object> totalInputs = inputs;
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            if (isView) {
                totaldata = QueryUtils.getItemData(totalInputs, "getTrx6_Summary_generate_InventoryEdit", "Summary_TRX6Inventory_total_data", null, null, "ARM_INFLATION_INVENTORY_ADJ", null, null);
            } else {
                totaldata = QueryUtils.getItemData(totalInputs, "getTrx6_Summary_generate_InventoryEdit", "Summary_TRX6Inventory_total_data", null, null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"), null, null);
            }
            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterSids, criteria)).getDataResults());
            result.setDataResults(l);
        }

        return result;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO selection, TreeMap<String, Integer> masterSids, Criteria criteria) {
        List<String> columnList = getColumns(selection.getSummary_variables());
        boolean hasOverride = columnList.contains("override");
        List resultList = new ArrayList();
        List<String> variables = selection.getSummary_columnList();
        AdjustmentDTO parent = null;
        Map<String, Integer> indexMap = new HashMap();
        if (criteria.getParent() != null && criteria.getParent() instanceof AdjustmentDTO) {
            parent = (AdjustmentDTO) criteria.getParent();
        }
        int indexAdd = 0;
        for (String[] summary_deductionVariable : selection.getSummary_deductionVariables()) {
            indexMap.put(summary_deductionVariable[0], indexAdd);
            indexAdd++;
        }
        int index = 0;
        String lastBrand = "";
        String levelName = "";
        AdjustmentDTO dto = null;
        DecimalFormat decimalformat = new DecimalFormat("#,##0.00");
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            levelName = String.valueOf(get[NumericConstants.ELEVEN]);
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            boolean isTotal = false;
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                if (!levelName.equals("null")) {
                    dto.setMonth(brand + "-" + String.valueOf(get[NumericConstants.ELEVEN]));
                } else {
                    dto.setMonth(brand);
                }
                dto.setLevelNo((int) get[NumericConstants.EIGHT]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                dto.setChildrenAllowed((selection.getSummary_levelFilterNo() != 0 || isTotal) ? false : (boolean) get[NumericConstants.NINE]);
                dto.setBrand_item_masterSid(String.valueOf(get[NumericConstants.TEN]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                resultList.add(dto);
                if (!isTotal) {
                    if (dto.getLevelNo() >= 1 && (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract()) || selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomer()))) {
                        index = 0 + ((parent == null ? indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd()) * (hasOverride ? NumericConstants.SIX : NumericConstants.FIVE));
                        dto.setDemand_summary_indexAdd(parent == null ? indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd());
                    } else {
                        index = 0;
                    }
                }
            }
            if (dto != null) {
                dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(get[NumericConstants.TWO].toString().trim())));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(get[NumericConstants.THREE].toString().trim())));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(get[NumericConstants.FOUR].toString().trim(), ARMUtils.TWO_DECIMAL_PERCENT) + "%");
                dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(get[NumericConstants.FIVE].toString().trim())));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null || dto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(get[NumericConstants.SIX].toString().trim())));
                if (hasOverride) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR
                            + decimalformat.format(Double.valueOf(get[NumericConstants.SEVEN].toString().trim())));
                }
                dto.addStringProperties("total", !isTotal && get[NumericConstants.EIGHT] != null ? DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(get[NumericConstants.EIGHT].toString().trim())) : StringUtils.EMPTY);
            }
            lastBrand = brand;
        }

        OriginalDataResult<T> dataResult = new OriginalDataResult<T>();
        dataResult.setDataResults(resultList);
        return dataResult;

    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "Update_Summary_Inventory_OverrideFor_Trx6";

    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        try {
            if (selection.getSummary_deductionLevel() == 0 || selection.getSummary_variables().size() == 0 || (selection.getSummary_deductionVariables() != null && selection.getSummary_deductionVariables().size() == 0)) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return Boolean.FALSE;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {

        String query = StringUtils.EMPTY;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getAdjustmentSummaryExcelQueryView_For_Tx6");
        } else {
            query = SQlUtil.getQuery("getAdjustmentSummaryExcelQuery_For_Trx6");
        }
        Object[] value = null;
        if (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract()) && selection.getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else if (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "B", "I"};
        } else if (selection.getSummary_viewType().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        }
        query = query.replace("@LEVEL_VAL", "'" + org.apache.commons.lang.StringUtils.join(value, ",") + "'");

        query = query.replace("@DEDCONDITION", "'" + selection.getSummary_deductionLevelDes() + "'");
        query = query.replace("@CONDITIONVALUE", selection.getSummary_deductionValues().replace("'", "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }

   @Override
    public List getTableInput(SessionDTO sessionDTO) {
       List list = new ArrayList<>();
       list.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"));
       list.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"));
       return list;
    }
    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"));
        return input;
    }
    
    @Override
       public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"));
        return input;
    }
    
}
