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
import com.stpl.app.arm.utils.CommonConstant;
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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class PISummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {

    private static final Logger PI_LOGGER = LoggerFactory.getLogger(PISummaryLogic.class);

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {

        List resultList;
        int count = 0;
        try {
            boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            String tableName = isView ? CommonConstant.ARM_INVENTORY_RATE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE);
            resultList = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_GENERATE_INVENTORY_EDIT, "Summary_generate_Inventory_count", null, null, tableName), null, null, inputs, false);
            count = resultList == null || resultList.isEmpty() ? 0 : (int) resultList.get(0);
            if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getRateslevelFilterNo() == 0)) {
                count = count + 1;
            }
        } catch (Exception e) {
            LOGGER.error("Error in getSummaryCount :", e);
        }
        return count;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            totalFlag = true;
            int offset = ARMUtils.getIntegerValue(inputs.get(inputs.size() - 1).toString()) - 1;
            inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> data;
        String tableName = isView ? CommonConstant.ARM_INVENTORY_RATE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE);
        data = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_GENERATE_INVENTORY_EDIT, CommonConstant.SUMMARY_GENERATE_INVENTORY_SELECT, null, "Summary_generate_Inventory_select_orderby", tableName), null, "WHERE RANK_VIEW BETWEEN ? AND ?", inputs, false);
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterSids);
        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = new ArrayList<>(inputs);
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            totaldata = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_GENERATE_INVENTORY_EDIT, "Summary_PI_total_data", CommonConstant.SUMMARY_GENERATE_INVENTORY_SELECT, null, tableName), null, "", totalInputs, true);
            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterSids)).getDataResults());
            result.setDataResults(l);
        }

        return result;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO selection, TreeMap<String, Integer> masterSids) {
        List resultList = new ArrayList();
        List<String> variables = selection.getSummarycolumnList();
        Map<String, Integer> indexMap = new HashMap();
        int indexAdd = 0;
        for (String[] summary_deductionVariable : selection.getSummarydeductionVariables()) {
            indexMap.put(summary_deductionVariable[0], indexAdd);
            indexAdd++;
        }
        int index = 0;
        String lastBrand = "";
        String levelName = "";
        AdjustmentDTO dto = null;
        int totalColumnIndex = selection.getSummarydeductionVariables().size() * NumericConstants.SIX;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.00");
        DecimalFormat percentageformat = new DecimalFormat("#,##0.00");
        DecimalFormat zeroDecimals = new DecimalFormat("#,##0");
        double[] totalColumnValue = null;
        boolean[] flag = null;
        boolean isTotal = false;
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            levelName = String.valueOf(get[NumericConstants.ELEVEN]);
            String brand = get[1] == null ? org.apache.commons.lang.StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                totalColumnValue = new double[NumericConstants.SIX];
                flag = new boolean[NumericConstants.SIX];
                if (!"null".equals(levelName)) {
                    dto.setMonth(brand + " - " + get[NumericConstants.ELEVEN]);
                } else {
                    dto.setMonth(brand);
                }
                dto.setLevelNo((Integer) get[NumericConstants.EIGHT]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                Boolean total = isTotal ? Boolean.FALSE : (Boolean) get[NumericConstants.NINE];
                dto.setChildrenAllowed((selection.getSummarylevelFilterNo() == 0) ? total : Boolean.FALSE);
                dto.setBranditemmasterSid(String.valueOf(get[NumericConstants.TEN]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                resultList.add(dto);
                dto = clearVariables(variables, dto);
            }
            index = indexMap.get(get[0].toString().replace(ARMUtils.SPACE.toString(), org.apache.commons.lang.StringUtils.EMPTY)) * NumericConstants.SIX;
            if (dto != null) {
                if (index < totalColumnIndex) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? (percentageformat.format(NumericConstants.ZERO) + ARMUtils.CHAR_PERCENT) : (percentageformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR])))) + ARMUtils.CHAR_PERCENT);
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null || dto.getChildrenAllowed() || isTotal ? org.apache.commons.lang.StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] != null ? decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]))) : org.apache.commons.lang.StringUtils.EMPTY);
                    if (get[NumericConstants.TWO] != null) {
                        totalColumnValue[0] += Double.parseDouble(String.valueOf(get[NumericConstants.TWO]));
                        flag[0] = true;
                    }
                    if (get[NumericConstants.THREE] != null) {
                        totalColumnValue[1] += Double.parseDouble(String.valueOf(get[NumericConstants.THREE]));
                        flag[1] = true;
                    }
                    if (get[NumericConstants.FOUR] != null) {
                        totalColumnValue[NumericConstants.TWO] += Double.parseDouble(String.valueOf(get[NumericConstants.FOUR]));
                        flag[NumericConstants.TWO] = true;
                    }
                    if (get[NumericConstants.FIVE] != null) {
                        totalColumnValue[NumericConstants.THREE] += Double.parseDouble(String.valueOf(get[NumericConstants.FIVE]));
                        flag[NumericConstants.THREE] = true;
                    }
                    if (get[NumericConstants.SIX] != null && !dto.getChildrenAllowed() && !isTotal && !selection.isCancelOverride()) {
                        totalColumnValue[NumericConstants.FOUR] += Double.parseDouble(String.valueOf(get[NumericConstants.SIX]));// For Override in Total column
                        flag[NumericConstants.FOUR] = true;
                    }
                    if (get[NumericConstants.SEVEN] != null) {
                        totalColumnValue[NumericConstants.FIVE] += Double.parseDouble(String.valueOf(get[NumericConstants.SEVEN]));// For Adjustment in Total column
                        flag[NumericConstants.FIVE] = true;
                    }
                }
                dto.addStringProperties(variables.get(totalColumnIndex), flag[0] ? zeroDecimals.format(totalColumnValue[0]) : StringUtils.EMPTY);
                dto.addStringProperties(variables.get(totalColumnIndex + 1), flag[1] ? zeroDecimals.format(totalColumnValue[1]) : StringUtils.EMPTY);
                dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.TWO), flag[NumericConstants.TWO] ? (percentageformat.format(totalColumnValue[NumericConstants.TWO])) + ARMUtils.CHAR_PERCENT : (percentageformat.format(NumericConstants.ZERO) + ARMUtils.CHAR_PERCENT));
                dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.THREE), flag[NumericConstants.THREE] ? zeroDecimals.format(totalColumnValue[NumericConstants.THREE]) : StringUtils.EMPTY);
                dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FOUR), flag[NumericConstants.FOUR] ? decimalformat.format(totalColumnValue[NumericConstants.FOUR]) : StringUtils.EMPTY);
                dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FIVE), flag[NumericConstants.FIVE] ? decimalformat.format(totalColumnValue[NumericConstants.FIVE]) : StringUtils.EMPTY);
            }
            lastBrand = brand;
            PI_LOGGER.debug("index-------{}", index);
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
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
            if (selection.getSummarydeductionLevel() == 0 || selection.getSummaryvariables().isEmpty() || (selection.getSummarydeductionVariables() != null && selection.getSummarydeductionVariables().isEmpty())) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("Error in generateButtonCheck :", e);
        }
        return Boolean.FALSE;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        LOGGER.debug("Inside getExcelResultList Method");
        String query;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getPIAdjustmentSummaryExcelQueryView");
        } else {
            query = SQlUtil.getQuery("getPIAdjustmentSummaryExcelQuery");
        }
        Object[] value = null;
        if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            value = ARMUtils.getDTCBI();
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
            value = ARMUtils.getTCBI();
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
            value = ARMUtils.getTBI();
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionProduct())) {
            value = ARMUtils.getBI();
        }
        query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + org.apache.commons.lang.StringUtils.join(value, ",") + ARMUtils.SINGLE_QUOTES);
        query = query.replace("@DEDCONDITION", selection.getSummarydeductionLevelDes());
        query = query.replace("@CONDITIONVALUE", selection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        LOGGER.debug("query--{}", query);
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
        return list;
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
        return input;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_ADJUSTMENTS"));
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
        return input;
    }

}
