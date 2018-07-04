/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.logic;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @param <T>
 */
public class RRSummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected String[] getTotalColumn() {
        return (new String[]{null});
    }

    @Override
    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        List<Object> finalList = new ArrayList<>();
        List<String> doubleSingleColumn;
        List<String> excelDoubleSingleColumn;
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> excelVisibleColumn = new ArrayList<>();
        List<String> excelVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleColumn = new ArrayList<>();
        List<String> excelSingleColumn = new ArrayList<>();
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
    public Boolean generateButtonCheck(SelectionDTO selection) {
        boolean retval = true;
        if (selection.getSummarydeductionLevel() == 0 || selection.getSummaryvariables().isEmpty() || (selection.getSummarydeductionVariables() != null && selection.getSummarydeductionVariables().isEmpty())) {
            retval = false;
        }
        return retval;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage())) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString());
            inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> data;
        String tableName = isView ? CommonConstant.ARM_RETURN_RESERVE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_RETURN_RESERVE);
        data = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_RR_GENERATE_EDIT, CommonConstant.SUMMARY_RR_GENERATE_DATA, null, "Summary_RR_generate_data_orderby", tableName), null, "WHERE RANK_VIEW BETWEEN ? AND ?", inputs, false);
        return cutomize(data, criteria.getSelectionDto(), masterSids);
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        List resultList;
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        String tableName = isView ? CommonConstant.ARM_RETURN_RESERVE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_RETURN_RESERVE);
        resultList = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_RR_GENERATE_EDIT, "Summary_RR_generate_count", null, null, tableName), null, null, inputs, false);
        return (resultList == null || resultList.isEmpty()) ? 0 : (int) resultList.get(0);
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
        boolean isTotal = false;
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            levelName = String.valueOf(get[NumericConstants.ELEVEN]);
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                if (!levelName.equals("null")) {
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
                dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))) + DataFormatConverter.INDICATOR_PERCENT);
                dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null || dto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))));
                dto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]))));
            }
            logger.debug("index-------" + index);
            lastBrand = brand;
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "rrSummaryUpdateOverride";
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_RETURN_RESERVE));
        return list;
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_RETURN_RESERVE));
        return input;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_RETURN_RESERVE));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_ADJUSTMENTS"));
        return input;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        try {
            String query;
            boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            query = SQlUtil.getQuery("getRRAdjustmentSummaryExcelQuery");
            Object[] value = null;
            if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
                value = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? new Object[]{"D", "T", "C", "B", "I"} : new Object[]{"T", "C", "B", "I"};
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionContract())) {
                value = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? new Object[]{"D", "C", "T", "B", "I"} : new Object[]{"C", "T", "B", "I"};
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionProduct())) {
                value = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? new Object[]{"D", "B", "I"} : new Object[]{"B", "I"};
            }
            query = query.replace("@LEVEL_VAL", "'" + StringUtils.join(value, ",") + "'");
            query = query.replace("@DEDCONDITION", selection.getSummarydeductionLevelDes());
            query = query.replace("@CONDITIONVALUE", selection.getSummarydeductionValues().replace("'", "''"));
            query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
            query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
            query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
            query = query.replace("@TABLE_NAME", isView ? CommonConstant.ARM_RETURN_RESERVE : CommonConstant.ST_ARM_RETURN_RESERVE);
            return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        } catch (Exception ex) {
            LOGGER.error("Error in getExcelResultList :" + ex);
            return Collections.emptyList();
        }
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
            if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
                Map<Integer, String> map = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? ARMUtils.getDemandSummaryLevelsinglePeriod() : ARMUtils.getPipelineSummaryLevelSinglePeriod();
                currentViewType = map.get(levelNo);
                viewType = map.get(++levelNo);
            } else if (selection.getSummaryviewType().equals(CommonConstant.DEDUCTION_CONTRACT)) {
                currentViewType = ARMUtils.getSummaryLevel().get(levelNo);
                viewType = ARMUtils.getSummaryLevel().get(++levelNo);
            } else if (levelNo == NumericConstants.ONE && selection.getSummaryviewType().equals(ARMConstants.getDeductionProduct())) {
                currentViewType = ARMUtils.getTYrx8LevelAndLevelFilter(ARMConstants.getDeductionProduct()).get(levelNo);
                viewType = ARMUtils.getTYrx8LevelAndLevelFilter(ARMConstants.getDeductionProduct()).get(++levelNo);
            } else {
                currentViewType = ARMUtils.getTYrx8LevelAndLevelFilter(ARMConstants.getDeductionCustomer()).get(levelNo);
                viewType = ARMUtils.getTYrx8LevelAndLevelFilter(ARMConstants.getDeductionCustomer()).get(++levelNo);
            }
            if (masterSids == null) {
                masterSids = new TreeMap<>();
            }
            masterSids.put(currentViewType, Integer.valueOf(val.getBranditemmasterSid()));
        } else {
            masterSids = new TreeMap<>();
            if (selection.getSummarylevelFilterNo() == 0) {
                viewType = getView(selection.getSummarydeductionLevelDes(), selection.getSummaryviewType());
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {

                viewType = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? ARMUtils.getReturnsDemandSummaryLevelsinglePeriod().get(selection.getSummarylevelFilterNo()) : ARMUtils.getPipelineSummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());

            } else if (selection.getSummaryviewType().equals(CommonConstant.DEDUCTION_CONTRACT)) {

                viewType = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? ARMUtils.getSummaryLevel().get(selection.getSummarylevelFilterNo()) : ARMUtils.getTrx7SummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());

            } else {
                String view = selection.getSummaryviewType();

                viewType = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? ARMUtils.getTYrx8LevelAndLevelFilter(view).get(selection.getSummarylevelFilterNo()) : ARMUtils.getReturnsLevelAndLevelFilter(view).get(selection.getSummarylevelFilterNo());
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
    public String getView(String deduction, String viewType) {
        return deduction.equals(ARMConstants.getDeduction()) ? ARMConstants.getDeduction() : ARMUtils.getViewName().get(viewType);
    }
}
