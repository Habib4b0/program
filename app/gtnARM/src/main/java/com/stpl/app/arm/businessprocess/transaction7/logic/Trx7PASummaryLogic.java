/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.logic;

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
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
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
public class Trx7PASummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {

    public static final Logger LOGGERFORSUMMAEYTAB = LoggerFactory.getLogger(Trx7PADetailsLogic.class);

    @Override
    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        List<Object> finalList = new ArrayList<>();
        List<String> doubleSingleColumn;
        List<String> excelDoubleSingleColumn;
        List<String> excelSingleColumn = new ArrayList<>();
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> excelVisibleColumn = new ArrayList<>();
        List<String> excelVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleColumn = new ArrayList<>();
        Map<Object, Object[]> doubleSingleVisibleColumn = new HashMap<>();
        Map<Object, Object[]> excelDoubleSingleVisibleColumn = new HashMap<>();
        excelDoubleSingleVisibleColumn.put("month", new Object[]{"month"});
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleHeader = new ArrayList<>();

        List<String> singleColumn = new ArrayList<>();
        int index = 0;
        List<String> columnList = getColumns(selection.getSummaryvariables());

        List<String[]> doubleHeaderVariables = selection.getSummarydeductionVariables();
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        String[] doublecolumn = getTotalColumn();
        if (doublecolumn.length > 1) {
            doublecolumnList.add(doublecolumn);
        }
        doubleHeaderVariables = doublecolumnList;
        for (String[] detection : doubleHeaderVariables) {
            doubleSingleColumn = new ArrayList<>();
            excelDoubleSingleColumn = new ArrayList<>();
            for (int i = 0; i < columns.length; i++, index++) {
                String column = columns[i];
                singleColumn.add(column + "." + index);
                excelSingleColumn.add((detection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : detection[0].replace(" ", StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + column + "." + (index));
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummaryvariables().get(listIndex)[0] + "." + index;
                    String header = selection.getSummaryvariables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    doubleSingleColumn.add(visibleColumn);
                    excelVisibleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                    excelVisibleHeader.add(header);
                    excelDoubleSingleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                }
            }
            doubleVisibleColumn.add(detection[2] + "~" + detection[1].replace(" ", StringUtils.EMPTY));
            doubleVisibleHeader.add(detection[1]);
            doubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(" ", StringUtils.EMPTY), doubleSingleColumn.toArray());
            excelDoubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(" ", StringUtils.EMPTY), excelDoubleSingleColumn.toArray());
        }
        selection.setSummarycolumnList(singleColumn);
        finalList.add(singleVisibleColumn);
        finalList.add(doubleVisibleColumn);
        finalList.add(singleVisibleHeader);
        finalList.add(doubleVisibleHeader);
        finalList.add(doubleSingleVisibleColumn);
        finalList.add(excelVisibleColumn);
        finalList.add(excelVisibleHeader);
        finalList.add(excelDoubleSingleVisibleColumn);
        finalList.add(excelSingleColumn);
        return finalList;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage())) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            totalFlag = true;
            int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
            inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> data;
        String tableName = isView ? CommonConstant.ARM_DISTRIBUTION_FEES_RATE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE);
        data = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.TRX7_SUMMARY_PA_GENERATE_EDIT, CommonConstant.TRX7_SUMMARY_PA_GENERATE_DATA, null, "trx7_Summary_PA_generate_data_orderby", tableName), null, "WHERE RANK_VIEW BETWEEN ? AND ?", inputs, false);
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterSids);

        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = new ArrayList<>(inputs);
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            totaldata = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.TRX7_SUMMARY_PA_GENERATE_EDIT, "trx7_Summary_PA_total_data", CommonConstant.TRX7_SUMMARY_PA_GENERATE_DATA, null, tableName), null, "", totalInputs, true);
            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterSids)).getDataResults());
            result.setDataResults(l);
        }
        return result;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        List resultList;
        int count = 0;
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        String tableName = isView ? CommonConstant.ARM_DISTRIBUTION_FEES_RATE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE);
        resultList = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.TRX7_SUMMARY_PA_GENERATE_EDIT, "Summary_PA_generate_count", null, null, tableName), null, null, inputs, false);
        count = resultList == null || resultList.isEmpty() ? 0 : (int) resultList.get(0);
        if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getRateslevelFilterNo() == 0)) {
            count = count + 1;
        }
        return count;
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

        DecimalFormat decimalformat = new DecimalFormat("#,##0.00");
        DecimalFormat percentformat = new DecimalFormat("#,##0%");
        boolean isTotal = false;
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            levelName = String.valueOf(get[NumericConstants.ELEVEN]);
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                if (!"null".equals(levelName)) {
                    dto.setMonth(brand + " - " + get[NumericConstants.ELEVEN]);
                } else {
                    dto.setMonth(brand);
                }
                dto.setLevelNo((int) get[NumericConstants.EIGHT]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                dto.setChildrenAllowed((selection.getSummarylevelFilterNo() != 0 || isTotal) ? false : (boolean) get[NumericConstants.NINE]);
                dto.setBranditemmasterSid(String.valueOf(get[NumericConstants.TEN]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                dto = clearVariables(variables, dto);
                resultList.add(dto);
            }
            index = indexMap.get(get[0].toString().replace(" ", org.apache.commons.lang.StringUtils.EMPTY)) * NumericConstants.SIX;
            if (dto != null) {
                dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + percentformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null || dto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]))));
            }
            LOGGERFORSUMMAEYTAB.debug("index-------" + index);
            lastBrand = brand;
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "trx7_paSummaryUpdateOverride";

    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        LOGGERFORSUMMAEYTAB.debug("Inside generate ButtonClick Btn");
        try {
            if (selection.getSummarydeductionLevel() == 0 || selection.getSummaryvariables().isEmpty() || (selection.getSummarydeductionVariables() != null && selection.getSummarydeductionVariables().isEmpty())) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGERFORSUMMAEYTAB.error("Error in generateButtonCheck :" + e);
        }
        return Boolean.FALSE;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        LOGGERFORSUMMAEYTAB.debug("Inside getExcelResultList");
        String query;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("trx7_getPAAdjustmentSummaryExcelQueryView");
        } else {
            query = SQlUtil.getQuery("trx7_getPAAdjustmentSummaryExcelQuery");
        }
        Object[] value = null;
        if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "B", "I"};
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        } else if (selection.getRateDeductionView().equals(CommonConstant.DEDUCTION_CONTRACT)) {
            value = new Object[]{"C", "T", "B", "I"};

        }
        query = query.replace("@LEVEL_VAL", "'" + StringUtils.join(value, ",") + "'");
        query = query.replace("@DEDCONDITION", selection.getSummarydeductionLevelDes());
        query = query.replace("@CONDITIONVALUE", selection.getSummarydeductionValues().replace("'", "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionId()));
        query = query.replace("@SELECTED_COLUMNS", getSelectVariable(selection.getSummaryvariables(), false));
        query = query.replace("@TOTAL_SELECTED_COLUMNS", getSelectVariable(selection.getSummaryvariables(), true));
        LOGGERFORSUMMAEYTAB.debug("--Exit getExcelResultList --" + query);
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }

    private String getSelectVariable(List<String[]> variables, boolean isTotal) {
        StringBuilder selectQuery = new StringBuilder(StringUtils.EMPTY);
        String[] object = null;
        if (variables != null && !variables.isEmpty()) {
            for (int i = 0; i < variables.size(); i++) {
                object = variables.get(i);
                if (i != 0 && !"adjustment".equals(object[0])) {
                    selectQuery.append(",'+");
                }
                if (object[0].contains("currentBalance")) {
                    selectQuery.append(" 'ISNULL(SUM(CURRENT_BALANCE),0) AS CURRENT_BALANCE ");
                } else if (object[0].contains("calculatedAdjustment")) {
                    selectQuery.append(" 'ISNULL(SUM(CALCULATED_ADJUSTMENT),0) AS CALCULATED_ADJUSTMENT ");
                } else if (object[0].contains("adjustmentRatio")) {
                    selectQuery.append(" 'ISNULL(SUM(ADJUSTMENT_RATIO),0) AS ADJUSTMENT_RATIO ");
                } else if (object[0].contains("variance")) {
                    selectQuery.append(" 'ISNULL(SUM(VARIANCE),0) AS VARIANCE ");
                } else if (object[0].contains("override")) {
                    if (isTotal) {
                        selectQuery.append(" 'NULL AS OVERRIDE,'\n").append(" +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ");

                    } else {
                        selectQuery.append(" CASE WHEN @LEV='I' THEN 'SUM(OVERRIDE) AS OVERRIDE,' ELSE 'NULL AS OVERRIDE,' END\n").append(" +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ");

                    }
                }
            }
        }
        LOGGERFORSUMMAEYTAB.debug("--Exit selectQuery --" + selectQuery);
        return selectQuery.toString();
    }

    @Override
    protected String[] getTotalColumn() {
        return (new String[]{null});
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO selection) {
        Object[] returnObj = new Object[NumericConstants.TWO];
        List<Object> inputs = new ArrayList<>();
        inputs.add(selection.getProjectionMasterSid());
        inputs.add(selection.getSummarydeductionLevelDes());
        String viewType = "";
        String currentViewType = "";
        TreeMap<String, Integer> masterSids = null;
        if (dto instanceof AdjustmentDTO) {
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            masterSids = val.getMasterIds();
            if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    currentViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(levelNo);
                    viewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(++levelNo);
                } else {
                    currentViewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(levelNo);
                    viewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(++levelNo);
                }
            } else if (selection.getSummaryviewType().equals(CommonConstant.DEDUCTION_CONTRACT)) {
                currentViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(levelNo);
                viewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(++levelNo);
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
                levelNo = levelNo == NumericConstants.FOUR ? levelNo - 1 : levelNo;
                currentViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(levelNo);
                viewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(++levelNo);
            } else {
                currentViewType = ARMUtils.getTYrx7LevelAndLevelFilter(ARMConstants.getDeductionCustomerContract()).get(levelNo);
                viewType = ARMUtils.getTYrx7LevelAndLevelFilter(ARMConstants.getDeductionCustomerContract()).get(++levelNo);
            }
            if (masterSids == null) {
                masterSids = new TreeMap<>();
            }
            masterSids.put(currentViewType, Integer.valueOf(val.getBranditemmasterSid()));
        } else {
            masterSids = new TreeMap<>();
            if (selection.getSummarylevelFilterNo() == 0) {
                viewType = getView(selection.getSummarydeductionLevelDes(), selection.getSummaryviewType());
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    viewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(selection.getSummarylevelFilterNo());
                } else {
                    viewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());
                }
            } else if (selection.getSummaryviewType().equals(CommonConstant.DEDUCTION_CONTRACT)) {

                viewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());

            } else {
                String view;
                if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && !selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    view = "non" + ARMConstants.getDeductionCustomerContract();
                } else {
                    view = selection.getSummaryviewType();
                }
                viewType = ARMUtils.getTYrx7LevelAndLevelFilter(view).get(selection.getSummarylevelFilterNo());
            }
        }
        if (viewType.equals(ARMConstants.getDeduction())) {
            viewType = ARMUtils.getDeductionLevelQueryName(selection.getSummarydeductionLevelDes());
        }
        inputs.add(viewType);
        inputs.add(ARMUtils.getSummaryViewType(selection.getSummaryviewType()));
        inputs.add(StringUtils.EMPTY);
        inputs.add(StringUtils.EMPTY);
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()));

        inputs.add(selection.getSummarydeductionValues());
        returnObj[0] = inputs;
        returnObj[1] = masterSids;

        return returnObj;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        return list;
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        return input;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_ADJUSTMENTS"));
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        return input;
    }

}
