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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class PASummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(PASummaryLogic.class);

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
        String tableName = isView ? CommonConstant.ARM_PIPELINE_RATE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE);
        data = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_PA_GENERATE_EDIT, CommonConstant.SUMMARY_PA_GENERATE_DATA, null, "Summary_PA_generate_data_orderby", tableName), null, "WHERE RANK_VIEW BETWEEN ? AND ?", inputs, false);
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterSids);

        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = new ArrayList<>(inputs);
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            totaldata = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_PA_GENERATE_EDIT, "Summary_PA_total_data", CommonConstant.SUMMARY_PA_GENERATE_DATA, null, tableName), null, "", totalInputs, true);
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
        String tableName = isView ? CommonConstant.ARM_PIPELINE_RATE : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_PIPELINE_RATE);
        resultList = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_PA_GENERATE_EDIT, "Summary_PA_generate_count", null, null, tableName), null, null, inputs, false);
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
                    dto.setMonth(brand + " - " + get[NumericConstants.TEN]);
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
                dto = clearVariables(variables, dto);
                resultList.add(dto);
            }
            index = indexMap.get(get[0].toString().replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) * NumericConstants.FIVE;
            if (dto != null) {
                if (index < totalColumnIndex) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null || dto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] != null ? decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))) : StringUtils.EMPTY);
                    if (get[NumericConstants.TWO] != null) {
                        flag[0] = true;
                        totalColumnValue[0] += Double.parseDouble(String.valueOf(get[NumericConstants.TWO]));
                    }
                    if (get[NumericConstants.THREE] != null) {
                        flag[1] = true;
                        totalColumnValue[1] += Double.parseDouble(String.valueOf(get[NumericConstants.THREE]));
                    }
                    if (get[NumericConstants.FOUR] != null) {
                        flag[NumericConstants.TWO] = true;
                        totalColumnValue[NumericConstants.TWO] += Double.parseDouble(String.valueOf(get[NumericConstants.FOUR]));
                    }
                    if (get[NumericConstants.FIVE] != null && !dto.getChildrenAllowed() && !isTotal && !selection.isCancelOverride()) {
                        flag[NumericConstants.THREE] = true;
                        totalColumnValue[NumericConstants.THREE] += Double.parseDouble(String.valueOf(get[NumericConstants.FIVE]));
                    }
                    if (get[NumericConstants.SIX] != null) {
                        flag[NumericConstants.FOUR] = true;
                        totalColumnValue[NumericConstants.FOUR] += Double.parseDouble(String.valueOf(get[NumericConstants.SIX]));
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
        LOGGER.debug("index-------{}", index);
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
            LOGGER.error("Error in generateButtonCheck :", e);
        }
        return Boolean.FALSE;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String paExcelQuery;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            paExcelQuery = SQlUtil.getQuery("getPAAdjustmentSummaryExcelQueryView");
        } else {
            paExcelQuery = SQlUtil.getQuery("getPAAdjustmentSummaryExcelQuery");
        }
        Object[] paExcelValue = null;
        if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            paExcelValue = new Object[]{"D", "T", "C", "B", "I"};
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
            paExcelValue = new Object[]{"T", "C", "B", "I"};
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
            paExcelValue = new Object[]{"T", "B", "I"};
        } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionProduct())) {
            paExcelValue = new Object[]{"B", "I"};
        }
        paExcelQuery = paExcelQuery.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + StringUtils.join(paExcelValue, ",") + ARMUtils.SINGLE_QUOTES);
        paExcelQuery = paExcelQuery.replace("@DEDCONDITION", selection.getSummarydeductionLevelDes());
        paExcelQuery = paExcelQuery.replace("@CONDITIONVALUE", selection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        paExcelQuery = paExcelQuery.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        paExcelQuery = paExcelQuery.replace("@USERID", String.valueOf(selection.getUserId()));
        paExcelQuery = paExcelQuery.replace("@SESSIONID", String.valueOf(selection.getSessionId()));
        paExcelQuery = paExcelQuery.replace("@SELECTED_COLUMNS", getSelectVariable(selection.getSummaryvariables(), false));
        paExcelQuery = paExcelQuery.replace("@TOTAL_COLUMN", getSelectVariable(selection.getSummaryvariables(), true));
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(paExcelQuery, selection.getSessionDTO().getCurrentTableNames()));
    }

    private String getSelectVariable(List<String[]> variables, boolean isTotal) {
        StringBuilder selectQuery = new StringBuilder();
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
        List<String> paDoubleSingleColumn;
        List<String> paExcelDoubleSingleColumn;
        List<String> paSingleVisibleColumn = new ArrayList<>();
        List<String> paExcelSingleColumn = new ArrayList<>();
        List<String> paExcelVisibleColumn = new ArrayList<>();
        List<String> paExcelVisibleHeader = new ArrayList<>();
        List<String> paDoubleVisibleColumn = new ArrayList<>();
        Map<Object, Object[]> paDoubleSingleVisibleColumn = new HashMap<>();
        Map<Object, Object[]> paExcelDoubleSingleVisibleColumn = new HashMap<>();
        paExcelDoubleSingleVisibleColumn.put("month", new Object[]{"month"});
        List<String> paSingleVisibleHeader = new ArrayList<>();
        List<String> paDoubleVisibleHeader = new ArrayList<>();

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
            paDoubleSingleColumn = new ArrayList<>();
            paExcelDoubleSingleColumn = new ArrayList<>();
            for (int i = 0; i < columns.length; i++, index++) {
                String column = columns[i];
                singleColumn.add(column + ARMUtils.DOT + index);
                paExcelSingleColumn.add((detection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : detection[0].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + column + ARMUtils.DOT + (index));
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummaryvariables().get(listIndex)[0] + ARMUtils.DOT + index;
                    String header = selection.getSummaryvariables().get(listIndex)[1];
                    paSingleVisibleColumn.add(visibleColumn);
                    paSingleVisibleHeader.add(header);
                    paDoubleSingleColumn.add(visibleColumn);
                    paExcelVisibleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                    paExcelVisibleHeader.add(header);
                    paExcelDoubleSingleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                }
            }

            paDoubleVisibleColumn.add(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY));
            paDoubleVisibleHeader.add(detection[1]);
            paDoubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), paDoubleSingleColumn.toArray());
            paExcelDoubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), paExcelDoubleSingleColumn.toArray());
        }
        selection.setSummarycolumnList(singleColumn);
        finalList.add(paSingleVisibleColumn);
        finalList.add(paDoubleVisibleColumn);
        finalList.add(paSingleVisibleHeader);
        finalList.add(paDoubleVisibleHeader);
        finalList.add(paDoubleSingleVisibleColumn);
        finalList.add(paExcelVisibleColumn);
        finalList.add(paExcelVisibleHeader);
        finalList.add(paExcelDoubleSingleVisibleColumn);
        finalList.add(paExcelSingleColumn);
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
