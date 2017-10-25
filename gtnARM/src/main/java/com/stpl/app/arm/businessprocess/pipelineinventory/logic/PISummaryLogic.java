/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.logic;

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
import com.stpl.ifs.util.constants.ARMConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.drools.core.util.StringUtils;

/**
 *
 * @author srithar
 */
public class PISummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {


    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {

        List resultList;
        int count = 0;
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            resultList = QueryUtils.getItemData(inputs, "Summary_generate_InventoryEdit", "Summary_generate_Inventory_count", null, null, "ARM_INVENTORY_RATE", null, null);
        } else {
            resultList = QueryUtils.getItemData(inputs, "Summary_generate_InventoryEdit", "Summary_generate_Inventory_count", null, null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"), null, null);
        }
        count = resultList == null || resultList.isEmpty() ? 0 : (int) resultList.get(0);
        if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getRates_levelFilterNo() == 0)) {
                count = count + 1;
        }
        return count;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && (criteria.getSelectionDto().getSummary_levelFilterNo() == 0)) {
                totalFlag = true;
                int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
                inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> data;
        if (isView) {
            data = QueryUtils.getItemData(inputs, "Summary_generate_InventoryEdit", "Summary_generate_Inventory_select", null, "Summary_generate_Inventory_select_orderby", "ARM_INVENTORY_RATE", null, "WHERE RANK_VIEW BETWEEN ? AND ?");
        } else {
            data = QueryUtils.getItemData(inputs, "Summary_generate_InventoryEdit", "Summary_generate_Inventory_select", null, "Summary_generate_Inventory_select_orderby", criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"), null, "WHERE RANK_VIEW BETWEEN ? AND ?");
        }
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterSids, criteria);
        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = inputs;
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            if (isView) {
                totaldata = QueryUtils.getItemData(totalInputs, "Summary_generate_InventoryEdit", "Summary_PI_total_data", "Summary_generate_Inventory_select", null, "ARM_INVENTORY_RATE", null, "");
            } else {
                totaldata = QueryUtils.getItemData(totalInputs, "Summary_generate_InventoryEdit", "Summary_PI_total_data", "Summary_generate_Inventory_select", null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"), null, "");
            }
            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterSids, criteria)).getDataResults());
            result.setDataResults(l);
        }

        return result;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO selection, TreeMap<String, Integer> masterSids, Criteria criteria) {
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
        int totalColumnIndex = selection.getSummary_deductionVariables().size() * NumericConstants.SIX;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.00");
        DecimalFormat percentageformat = new DecimalFormat("#,##0.00");
        double[] totalColumnValue = null;
        boolean flag[] = null;
        boolean column = false;
        boolean isTotal = false;
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            levelName = String.valueOf(get[NumericConstants.ELEVEN]);
            String brand = get[1] == null ? org.apache.commons.lang.StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                totalColumnValue = new double[NumericConstants.SIX];
                flag = new boolean[NumericConstants.SIX];
                if (!levelName.equals("null")) {
                    dto.setMonth(brand + "-" + String.valueOf(get[NumericConstants.ELEVEN]));
                } else {
                    dto.setMonth(brand);
                }
                dto.setLevelNo((int) get[NumericConstants.EIGHT]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                dto.setChildrenAllowed((selection.getSummary_levelFilterNo() == 0) ? (isTotal ? false : (boolean) get[NumericConstants.NINE]) : false);
                dto.setBrand_item_masterSid(String.valueOf(get[NumericConstants.TEN]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                resultList.add(dto);
                if (!isTotal) {
                    if (dto.getLevelNo() >= 1 && (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract()) || selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomer()))) {
                        index = 0 + ((parent == null ? indexMap.get(get[0].toString().replace(" ", org.apache.commons.lang.StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd()) * NumericConstants.SIX);
                        dto.setDemand_summary_indexAdd(parent == null ? indexMap.get(get[0].toString().replace(" ", org.apache.commons.lang.StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd());
                    } else {
                        index = 0;
                    }
                }
                column = index == 0;
            }
            if (dto != null) {
                if (index < totalColumnIndex) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? org.apache.commons.lang.StringUtils.EMPTY : (percentageformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR])))) + "%");
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null || dto.getChildrenAllowed() || isTotal ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] != null ? decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]))) : org.apache.commons.lang.StringUtils.EMPTY);
                    if (get[NumericConstants.TWO] != null) {
                        totalColumnValue[0] += Double.valueOf(String.valueOf(get[NumericConstants.TWO]));
                        flag[0] = true;
                    }
                    if (get[NumericConstants.THREE] != null) {
                        totalColumnValue[1] += Double.valueOf(String.valueOf(get[NumericConstants.THREE]));
                        flag[1] = true;
                    }
                    if (get[NumericConstants.FOUR] != null) {
                        totalColumnValue[NumericConstants.TWO] += Double.valueOf(String.valueOf(get[NumericConstants.FOUR]));
                        flag[NumericConstants.TWO] = true;
                    }
                    if (get[NumericConstants.FIVE] != null) {
                        totalColumnValue[NumericConstants.THREE] += Double.valueOf(String.valueOf(get[NumericConstants.FIVE]));
                        flag[NumericConstants.THREE] = true;
                    }
                    if (get[NumericConstants.SIX] != null && !dto.getChildrenAllowed() && !isTotal) {
                        totalColumnValue[NumericConstants.FOUR] += Double.valueOf(String.valueOf(get[NumericConstants.SIX]));// For Override in Total column
                        flag[NumericConstants.FOUR] = true;
                    }
                    if (get[NumericConstants.SEVEN] != null) {
                        totalColumnValue[NumericConstants.FIVE] += Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]));// For Adjustment in Total column
                        flag[NumericConstants.FIVE] = true;
                    }
                }
                if (index >= totalColumnIndex || column) {
                    dto.addStringProperties(variables.get(totalColumnIndex), flag[0] ? decimalformat.format(totalColumnValue[0]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + 1), flag[1] ? decimalformat.format(totalColumnValue[1]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.TWO), flag[NumericConstants.TWO] ? (percentageformat.format(totalColumnValue[NumericConstants.TWO]))+ "%" : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.THREE), flag[NumericConstants.THREE] ? decimalformat.format(totalColumnValue[NumericConstants.THREE]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FOUR), flag[NumericConstants.FOUR] ? decimalformat.format(totalColumnValue[NumericConstants.FOUR]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FIVE), flag[NumericConstants.FIVE] ? decimalformat.format(totalColumnValue[NumericConstants.FIVE]) : StringUtils.EMPTY);
                }
            }
            lastBrand = brand;
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<T>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "Update_Summary_Inventory_Override";

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
            query = SQlUtil.getQuery("getPIAdjustmentSummaryExcelQueryView");
        } else {
            query = SQlUtil.getQuery("getPIAdjustmentSummaryExcelQuery");
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
        query = query.replace("@DEDCONDITION", selection.getSummary_deductionLevelDes());
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
        list.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"));
        list.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"));
        return list;
    }
    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"));
        return input;
    }
    
    @Override
       public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"));
        return input;
    }
    
}
