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

    public static final Logger LOGGERFORSUMMAEYTAB = LoggerFactory.getLogger(Trx7PASummaryLogic.class);

    @Override
    public List<Object> generateHeader(AbstractSelectionDTO distributionSelection, String[] columns) {
        List<Object> distributonFinalList = new ArrayList<>();
        List<String> distributionExcelSingleColumn = new ArrayList<>();
        List<String> distributionSingleVisibleColumn = new ArrayList<>();
        List<String> distributionExcelVisibleColumn = new ArrayList<>();
        List<String> distributionExcelVisibleHeader = new ArrayList<>();
        List<String> distributionDoubleVisibleColumn = new ArrayList<>();
        Map<Object, Object[]> distributionDoubleSingleVisibleColumn = new HashMap<>();
        Map<Object, Object[]> distributionExcelDoubleSingleVisibleColumn = new HashMap<>();
        distributionExcelDoubleSingleVisibleColumn.put("month", new Object[]{"month"});
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleHeader = new ArrayList<>();

        List<String> singleColumn = new ArrayList<>();
        int index = 0;
        List<String> columnList = getColumns(distributionSelection.getSummaryvariables());

        List<String[]> doubleHeaderVariables = distributionSelection.getSummarydeductionVariables();
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        String[] doublecolumn = getTotalColumn();
        if (doublecolumn.length > 1) {
            doublecolumnList.add(doublecolumn);
        }
        doubleHeaderVariables = doublecolumnList;
        List<String> distributionDoubleSingleColumn = new ArrayList<>();
        List<String> distributionExcelDoubleSingleColumn = new ArrayList<>();
        for (String[] distributionDetection : doubleHeaderVariables) {
            distributionDoubleSingleColumn.clear();
            distributionExcelDoubleSingleColumn.clear();
            for (int i = 0; i < columns.length; i++, index++) {
                String column = columns[i];
                singleColumn.add(column + ARMUtils.DOT + index);
                distributionExcelSingleColumn.add((distributionDetection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : distributionDetection[0].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + column + ARMUtils.DOT + (index));
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = distributionSelection.getSummaryvariables().get(listIndex)[0] + ARMUtils.DOT + index;
                    String header = distributionSelection.getSummaryvariables().get(listIndex)[1];
                    distributionSingleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    distributionDoubleSingleColumn.add(visibleColumn);
                    distributionExcelVisibleColumn.add(distributionDetection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                    distributionExcelVisibleHeader.add(header);
                    distributionExcelDoubleSingleColumn.add(distributionDetection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                }
            }
            distributionDoubleVisibleColumn.add(distributionDetection[2] + "~" + distributionDetection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY));
            doubleVisibleHeader.add(distributionDetection[1]);
            distributionDoubleSingleVisibleColumn.put(distributionDetection[2] + "~" + distributionDetection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), distributionDoubleSingleColumn.toArray());
            distributionExcelDoubleSingleVisibleColumn.put(distributionDetection[2] + "~" + distributionDetection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), distributionExcelDoubleSingleColumn.toArray());
        }
        distributionSelection.setSummarycolumnList(singleColumn);
        distributonFinalList.add(distributionSingleVisibleColumn);
        distributonFinalList.add(distributionDoubleVisibleColumn);
        distributonFinalList.add(singleVisibleHeader);
        distributonFinalList.add(doubleVisibleHeader);
        distributonFinalList.add(distributionDoubleSingleVisibleColumn);
        distributonFinalList.add(distributionExcelVisibleColumn);
        distributonFinalList.add(distributionExcelVisibleHeader);
        distributonFinalList.add(distributionExcelDoubleSingleVisibleColumn);
        distributonFinalList.add(distributionExcelSingleColumn);
        return distributonFinalList;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage())) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            totalFlag = true;
            int offset = ARMUtils.getIntegerValue(inputs.get(inputs.size() - 1).toString()) - 1;
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

    private DataResult<T> cutomize(List<Object[]> distributionData, SelectionDTO distributionSelection, TreeMap<String, Integer> masterSids) {
        List resultList = new ArrayList();
        List<String> variables = distributionSelection.getSummarycolumnList();
        Map<String, Integer> indexMap = new HashMap();
        int indexAdd = 0;
        for (String[] summary_deductionVariable : distributionSelection.getSummarydeductionVariables()) {
            indexMap.put(summary_deductionVariable[0], indexAdd);
            indexAdd++;
        }
        int index = 0;
        String lastBrand = "";
        String levelName = "";
        AdjustmentDTO istributionDto = null;

        DecimalFormat decimalformat = new DecimalFormat("#,##0.00");
        DecimalFormat percentformat = new DecimalFormat("#,##0%");
        boolean isTotal = false;
        for (int j = 0; j < distributionData.size(); j++) {
            Object[] get = distributionData.get(j);
            levelName = String.valueOf(get[NumericConstants.ELEVEN]);
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                istributionDto = new AdjustmentDTO();
                if (!"null".equals(levelName)) {
                    istributionDto.setMonth(brand + " - " + get[NumericConstants.ELEVEN]);
                } else {
                    istributionDto.setMonth(brand);
                }
                istributionDto.setLevelNo((Integer) get[NumericConstants.EIGHT]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                istributionDto.setChildrenAllowed((distributionSelection.getSummarylevelFilterNo() != 0 || isTotal) ? Boolean.FALSE : (Boolean) get[NumericConstants.NINE]);
                istributionDto.setBranditemmasterSid(String.valueOf(get[NumericConstants.TEN]));
                if (masterSids != null) {
                    istributionDto.setMasterIds(masterSids);
                }
                istributionDto = clearVariables(variables, istributionDto);
                resultList.add(istributionDto);
            }
            index = indexMap.get(get[0].toString().replace(ARMUtils.SPACE.toString(), org.apache.commons.lang.StringUtils.EMPTY)) * NumericConstants.SIX;
            if (istributionDto != null) {
                istributionDto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                istributionDto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                istributionDto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + percentformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                istributionDto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                istributionDto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null || istributionDto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))));
                istributionDto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : DataFormatConverter.INDICATOR_DOLLAR + decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]))));
            }
            LOGGERFORSUMMAEYTAB.debug("index-------{}", index);
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
            LOGGERFORSUMMAEYTAB.error("Error in generateButtonCheck :", e);
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
        Object[] distributionValue = null;
        if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            distributionValue = ARMUtils.getDTCBI();
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
            distributionValue = ARMUtils.getTCBI();
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
            distributionValue = ARMUtils.getTBI();
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionProduct())) {
            distributionValue = ARMUtils.getBI();
        } else if (selection.getRateDeductionView().equals(CommonConstant.DEDUCTION_CONTRACT)) {
            distributionValue = ARMUtils.getCTBI();

        }
        query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + StringUtils.join(distributionValue, ",") + ARMUtils.SINGLE_QUOTES);
        query = query.replace("@DEDCONDITION", selection.getSummarydeductionLevelDes());
        query = query.replace("@CONDITIONVALUE", selection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionId()));
        query = query.replace("@SELECTED_COLUMNS", getSelectVariable(selection.getSummaryvariables(), false));
        query = query.replace("@TOTAL_SELECTED_COLUMNS", getSelectVariable(selection.getSummaryvariables(), true));
        LOGGERFORSUMMAEYTAB.debug("--Exit getExcelResultList --{}", query);
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }

    private String getSelectVariable(List<String[]> variables, boolean isTotal) {
        StringBuilder distributionSelectQuery = new StringBuilder();
        String[] object = null;
        if (variables != null && !variables.isEmpty()) {
            for (int i = 0; i < variables.size(); i++) {
                object = variables.get(i);
                if (i != 0 && !"adjustment".equals(object[0])) {
                    distributionSelectQuery.append(",'+");
                }
                if (object[0].contains("currentBalance")) {
                    distributionSelectQuery.append(" 'ISNULL(SUM(CURRENT_BALANCE),0) AS CURRENT_BALANCE ");
                } else if (object[0].contains("calculatedAdjustment")) {
                    distributionSelectQuery.append(" 'ISNULL(SUM(CALCULATED_ADJUSTMENT),0) AS CALCULATED_ADJUSTMENT ");
                } else if (object[0].contains("adjustmentRatio")) {
                    distributionSelectQuery.append(" 'ISNULL(SUM(ADJUSTMENT_RATIO),0) AS ADJUSTMENT_RATIO ");
                } else if (object[0].contains("variance")) {
                    distributionSelectQuery.append(" 'ISNULL(SUM(VARIANCE),0) AS VARIANCE ");
                } else if (object[0].contains("override")) {
                    if (isTotal) {
                        distributionSelectQuery.append(" 'NULL AS OVERRIDE,'\n").append(" +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ");

                    } else {
                        distributionSelectQuery.append(" CASE WHEN @LEV='I' THEN 'SUM(OVERRIDE) AS OVERRIDE,' ELSE 'NULL AS OVERRIDE,' END\n").append(" +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ");

                    }
                }
            }
        }
        LOGGERFORSUMMAEYTAB.debug("--Exit selectQuery --{}", distributionSelectQuery);
        return distributionSelectQuery.toString();
    }

    @Override
    protected String[] getTotalColumn() {
        return (new String[]{null});
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO selection) {
        Object[] returnObj = new Object[NumericConstants.TWO];
        List<Object> distributionInputs = new ArrayList<>();
        distributionInputs.add(selection.getProjectionMasterSid());
        distributionInputs.add(selection.getSummarydeductionLevelDes());
        String distributionViewType = "";
        String currentViewType = "";
        TreeMap<String, Integer> distributionMasterSids = null;
        if (dto instanceof AdjustmentDTO) {
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            distributionMasterSids = val.getMasterIds();
            if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    currentViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(levelNo);
                    distributionViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(++levelNo);
                } else {
                    currentViewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(levelNo);
                    distributionViewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(++levelNo);
                }
            } else if (selection.getSummaryviewType().equals(CommonConstant.DEDUCTION_CONTRACT)) {
                currentViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(levelNo);
                distributionViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(++levelNo);
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
                levelNo = levelNo == NumericConstants.FOUR ? levelNo - 1 : levelNo;
                currentViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(levelNo);
                distributionViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(++levelNo);
            } else {
                currentViewType = ARMUtils.getInstance().getTYrx7LevelAndLevelFilter(ARMConstants.getDeductionCustomerContract()).get(levelNo);
                distributionViewType = ARMUtils.getInstance().getTYrx7LevelAndLevelFilter(ARMConstants.getDeductionCustomerContract()).get(++levelNo);
            }
            if (distributionMasterSids == null) {
                distributionMasterSids = new TreeMap<>();
            }
            distributionMasterSids.put(currentViewType, Integer.valueOf(val.getBranditemmasterSid()));
        } else {
            distributionMasterSids = new TreeMap<>();
            if (selection.getSummarylevelFilterNo() == 0) {
                distributionViewType = getView(selection.getSummarydeductionLevelDes(), selection.getSummaryviewType());
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    distributionViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(selection.getSummarylevelFilterNo());
                } else {
                    distributionViewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());
                }
            } else if (selection.getSummaryviewType().equals(CommonConstant.DEDUCTION_CONTRACT)) {

                distributionViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());

            } else {
                String view;
                if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && !selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    view = "non" + ARMConstants.getDeductionCustomerContract();
                } else {
                    view = selection.getSummaryviewType();
                }
                distributionViewType = ARMUtils.getInstance().getTYrx7LevelAndLevelFilter(view).get(selection.getSummarylevelFilterNo());
            }
        }
        if (distributionViewType.equals(ARMConstants.getDeduction())) {
            distributionViewType = ARMUtils.getInstance().getDeductionLevelQueryName(selection.getSummarydeductionLevelDes());
        }
        distributionInputs.add(distributionViewType);
        distributionInputs.add(ARMUtils.getInstance().getSummaryViewType(selection.getSummaryviewType()));
        distributionInputs.add(StringUtils.EMPTY);
        distributionInputs.add(StringUtils.EMPTY);
        distributionInputs.add(distributionMasterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : distributionMasterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        distributionInputs.add(distributionMasterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : distributionMasterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        distributionInputs.add(distributionMasterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : distributionMasterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        distributionInputs.add(distributionMasterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : distributionMasterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        distributionInputs.add(distributionMasterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : distributionMasterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()));

        distributionInputs.add(selection.getSummarydeductionValues());
        returnObj[0] = distributionInputs;
        returnObj[1] = distributionMasterSids;

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
