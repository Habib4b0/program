/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.logic;

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
import static com.stpl.app.arm.utils.ARMUtils.SalesVariables.ST_ARM_PIPELINE_RATE;
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

/**
 *
 * @author srithar
 */
public class PASummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            totalFlag = true;
            int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
            inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> data;
        if (isView) {
            data = QueryUtils.getItemData(inputs, CommonConstant.SUMMARY_PA_GENERATE_EDIT, CommonConstant.SUMMARY_PA_GENERATE_DATA, null, "Summary_PA_generate_data_orderby", CommonConstant.ARM_PIPELINE_RATE, null, "WHERE RANK_VIEW BETWEEN ? AND ?");
        } else {
            data = QueryUtils.getItemData(inputs, CommonConstant.SUMMARY_PA_GENERATE_EDIT, CommonConstant.SUMMARY_PA_GENERATE_DATA, null, "Summary_PA_generate_data_orderby", criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE), null, "WHERE RANK_VIEW BETWEEN ? AND ?");
        }
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterSids);

        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = inputs;
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            if (isView) {
                totaldata = QueryUtils.getItemData(totalInputs, CommonConstant.SUMMARY_PA_GENERATE_EDIT, "Summary_PA_total_data", CommonConstant.SUMMARY_PA_GENERATE_DATA, null, CommonConstant.ARM_PIPELINE_RATE, null, "");
            } else {
                totaldata = QueryUtils.getItemData(totalInputs, CommonConstant.SUMMARY_PA_GENERATE_EDIT, "Summary_PA_total_data", CommonConstant.SUMMARY_PA_GENERATE_DATA, null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE), null, "");
            }
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
        if (isView) {
            resultList = QueryUtils.getItemData(inputs, CommonConstant.SUMMARY_PA_GENERATE_EDIT, "Summary_PA_generate_count", null, null, CommonConstant.ARM_PIPELINE_RATE, null, null);
        } else {
            resultList = QueryUtils.getItemData(inputs, CommonConstant.SUMMARY_PA_GENERATE_EDIT, "Summary_PA_generate_count", null, null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE), null, null);
        }
        count = resultList == null || resultList.isEmpty() ? 0 : (int) resultList.get(0);
        if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getRateslevelFilterNo() == 0)) {
            count = count + 1;
        }
        return count;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO selection, TreeMap<String, Integer> masterSids) {
        List resultList = new ArrayList();
        List<String> variables = selection.getSummarycolumnList();
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.00");
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
        double[] totalColumnValue = null;
        boolean[] flag = null;
        boolean isTotal = false;
        int totalColumnIndex = selection.getSummarydeductionVariables().size() * NumericConstants.FIVE;
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            levelName = String.valueOf(get[NumericConstants.TEN]);
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                totalColumnValue = new double[NumericConstants.FIVE];
                flag = new boolean[NumericConstants.FIVE];
                if (!"null".equals(levelName)) {
                    dto.setMonth(brand + "-" + String.valueOf(get[NumericConstants.TEN]));
                } else {
                    dto.setMonth(brand);
                }
                dto.setLevelNo((int) get[NumericConstants.SEVEN]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                dto.setChildrenAllowed((selection.getSummarylevelFilterNo() != 0 || isTotal) ? false : (boolean) get[NumericConstants.EIGHT]);
                dto.setBranditemmasterSid(String.valueOf(get[NumericConstants.NINE]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                resultList.add(dto);
                if (!isTotal) {
                    index = indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) * NumericConstants.FIVE;
                }
            }
            if (dto != null) {
                if (index < totalColumnIndex) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null || dto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] != null ? decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))) : StringUtils.EMPTY);
                    if (get[NumericConstants.TWO] != null) {
                        flag[0] = true;
                        totalColumnValue[0] += Double.valueOf(String.valueOf(get[NumericConstants.TWO]));
                    }
                    if (get[NumericConstants.THREE] != null) {
                        flag[1] = true;
                        totalColumnValue[1] += Double.valueOf(String.valueOf(get[NumericConstants.THREE]));
                    }
                    if (get[NumericConstants.FOUR] != null) {
                        flag[NumericConstants.TWO] = true;
                        totalColumnValue[NumericConstants.TWO] += Double.valueOf(String.valueOf(get[NumericConstants.FOUR]));
                    }
                    if (get[NumericConstants.FIVE] != null && !dto.getChildrenAllowed() && !isTotal && !selection.isCancelOverride()) {
                        flag[NumericConstants.THREE] = true;
                        totalColumnValue[NumericConstants.THREE] += Double.valueOf(String.valueOf(get[NumericConstants.FIVE]));
                    }
                    if (get[NumericConstants.SIX] != null) {
                        flag[NumericConstants.FOUR] = true;
                        totalColumnValue[NumericConstants.FOUR] += Double.valueOf(String.valueOf(get[NumericConstants.SIX]));
                    }
                }
                    dto.addStringProperties(variables.get(totalColumnIndex), flag[0] ? decimalformat.format(totalColumnValue[0]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + 1), flag[1] ? decimalformat.format(totalColumnValue[1]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.TWO), flag[NumericConstants.TWO] ? decimalformat.format(totalColumnValue[NumericConstants.TWO]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.THREE), flag[NumericConstants.THREE] ? decimalformat.format(totalColumnValue[NumericConstants.THREE]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FOUR), flag[NumericConstants.FOUR] ? decimalformat.format(totalColumnValue[NumericConstants.FOUR]) : StringUtils.EMPTY);
                lastBrand = brand;
            }
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "paSummaryUpdateOverride";

    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        LOGGER.debug("Inside generate ButtonClick Btn");
        try {
            if (selection.getSummarydeductionLevel() == 0 || selection.getSummaryvariables().isEmpty() || (selection.getSummarydeductionVariables() != null && selection.getSummarydeductionVariables().isEmpty())) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("Error in generateButtonCheck :"+e);
        }
        return Boolean.FALSE;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getPAAdjustmentSummaryExcelQueryView");
        } else {
            query = SQlUtil.getQuery("getPAAdjustmentSummaryExcelQuery");
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
        }
        query = query.replace("@LEVEL_VAL", "'" + StringUtils.join(value, ",") + "'");
        query = query.replace("@DEDCONDITION", selection.getSummarydeductionLevelDes());
        query = query.replace("@CONDITIONVALUE", selection.getSummarydeductionValues().replace("'", "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionId()));
        query = query.replace("@SELECTED_COLUMNS", getSelectVariable(selection.getSummaryvariables(), Boolean.FALSE));
        query = query.replace("@TOTAL_COLUMN", getSelectVariable(selection.getSummaryvariables(), Boolean.TRUE));
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }

    private String getSelectVariable(List<String[]> variables, boolean isTotal) {
        StringBuilder selectQuery = new StringBuilder(StringUtils.EMPTY);
        String[] object = null;
        if (variables != null && !variables.isEmpty()) {
            for (int i = 0; i < variables.size(); i++) {
                object = variables.get(i);
                if (i != 0 && !object[0].contains("adjustment")) {
                    selectQuery.append(",'+");
                }
                if (object[0].contains("cPipelineAccrual")) {
                    selectQuery.append(" 'ISNULL(SUM(CURRENT_PIPELINE_ACCRUAL),0)  AS CURRENT_PIPELINE_ACCRUAL ");
                } else if (object[0].contains("pPipelineAccrual")) {
                    selectQuery.append(" 'ISNULL(SUM(PROJECTED_PIPELINE_ACCRUAL),0) AS PROJECTED_PIPELINE_ACCRUAL ");
                } else if (object[0].contains("variance")) {
                    selectQuery.append(" 'ISNULL(SUM(VARIANCE),0) AS VARIANCE ");
                } else if (object[0].contains("override")) {
                    if (isTotal) {
                        selectQuery.append(" 'NULL AS OVERRIDE,'\n").append(" +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ");

                    } else {
                        selectQuery.append("CASE WHEN @LEV='I' THEN 'SUM(OVERRIDE) AS OVERRIDE,' ELSE 'NULL AS OVERRIDE,' END\n").append(" +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ");

                    }
                }
            }
        }
        return selectQuery.toString();
    }

    /**
     * The method is for generating table headers for pipeline accrual only.
     *
     * @param selection
     * @param columns
     * @return
     */
    @Override
    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        List<Object> finalList = new ArrayList<>();
        List<String> doubleSingleColumn;
        List<String> excelDoubleSingleColumn;
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
        selection.setSummarycolumnList(singleColumn);
        int index = 0;
        List<String> columnList = getColumns(selection.getSummaryvariables());

        List<String[]> doubleHeaderVariables = selection.getSummarydeductionVariables();
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        String[] doublecolumn = getTotalColumn();
        if (doublecolumn != null) {
            doublecolumnList.add(doublecolumn);
        }
        doubleHeaderVariables = doublecolumnList;
        for (String[] detection : doubleHeaderVariables) {
            doubleSingleColumn = new ArrayList<>();
            excelDoubleSingleColumn = new ArrayList<>();
            for (int i = 0; i < columns.length; i++, index++) {
                String column = columns[i];
                singleColumn.add(column + "." + index);
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummaryvariables().get(listIndex)[0] + "." + index;
                    String header = selection.getSummaryvariables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    doubleSingleColumn.add(visibleColumn);
                    excelVisibleColumn.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                    excelVisibleHeader.add(header);
                    excelDoubleSingleColumn.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                }
            }

            doubleVisibleColumn.add(detection[2] + "~" + detection[1].replace(" ", StringUtils.EMPTY));
            doubleVisibleHeader.add(detection[1]);
            doubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(" ", StringUtils.EMPTY), doubleSingleColumn.toArray());
            excelDoubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(" ", StringUtils.EMPTY), excelDoubleSingleColumn.toArray());
        }
        finalList.add(singleVisibleColumn);
        finalList.add(doubleVisibleColumn);
        finalList.add(singleVisibleHeader);
        finalList.add(doubleVisibleHeader);
        finalList.add(doubleSingleVisibleColumn);
        finalList.add(excelVisibleColumn);
        finalList.add(excelVisibleHeader);
        finalList.add(excelDoubleSingleVisibleColumn);
        return finalList;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add(sessionDTO.getCurrentTableNames().get(ST_ARM_PIPELINE_RATE.toString()));
        list.add(sessionDTO.getCurrentTableNames().get(ST_ARM_PIPELINE_RATE.toString()));
        return list;
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE));
        return input;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_ADJUSTMENTS"));
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE));
        return input;
    }
}
