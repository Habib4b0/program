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
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && (criteria.getSelectionDto().getSummary_levelFilterNo() == 0))  {
                totalFlag = true;
                int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
                inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> data;
        if (isView) {
            data = QueryUtils.getItemData(inputs, "Summary_PA_generateEdit", "Summary_PA_generate_data", null, "Summary_PA_generate_data_orderby", "ARM_PIPELINE_RATE", null, "WHERE RANK_VIEW BETWEEN ? AND ?" );
        } else {
            data = QueryUtils.getItemData(inputs, "Summary_PA_generateEdit", "Summary_PA_generate_data", null, "Summary_PA_generate_data_orderby", criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_PIPELINE_RATE"), null, "WHERE RANK_VIEW BETWEEN ? AND ?");
        }
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterSids, criteria);

        if (totalFlag) {
            List<Object[]> totaldata;
            List<Object> totalInputs = inputs;
            totalInputs.remove(totalInputs.size() - 1);
            totalInputs.remove(totalInputs.size() - 1);
            if (isView) {
                totaldata = QueryUtils.getItemData(totalInputs, "Summary_PA_generateEdit", "Summary_PA_total_data", "Summary_PA_generate_data", null, "ARM_PIPELINE_RATE", null, "" );
            } else {
                totaldata = QueryUtils.getItemData(totalInputs, "Summary_PA_generateEdit", "Summary_PA_total_data", "Summary_PA_generate_data", null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_PIPELINE_RATE"), null, "");
            }
            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterSids, criteria)).getDataResults());
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
            resultList = QueryUtils.getItemData(inputs, "Summary_PA_generateEdit", "Summary_PA_generate_count", null, null, "ARM_PIPELINE_RATE",null, null);
        } else {
            resultList = QueryUtils.getItemData(inputs, "Summary_PA_generateEdit", "Summary_PA_generate_count", null, null, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_PIPELINE_RATE"),null, null);
        }
        count = resultList == null || resultList.isEmpty() ? 0 : (int) resultList.get(0);
        if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getRates_levelFilterNo() == 0)) {
                count = count + 1;
        }
        return count;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO selection, TreeMap<String, Integer> masterSids, Criteria criteria) {
        List resultList = new ArrayList();
        List<String> variables = selection.getSummary_columnList();
        AdjustmentDTO parent = null;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.00");
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
        double[] totalColumnValue = null;
        boolean flag[] = null;
        boolean column = false;
        boolean isTotal = false;
        int totalColumnIndex = selection.getSummary_deductionVariables().size() * NumericConstants.FIVE;
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            levelName = String.valueOf(get[NumericConstants.TEN]);
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                totalColumnValue = new double[NumericConstants.FIVE];
                flag = new boolean[NumericConstants.FIVE];
                if (!levelName.equals("null")) {
                    dto.setMonth(brand + "-" + String.valueOf(get[NumericConstants.TEN]));
                } else {
                    dto.setMonth(brand);
                }
                dto.setLevelNo((int) get[NumericConstants.SEVEN]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                dto.setChildrenAllowed((selection.getSummary_levelFilterNo() != 0 || isTotal) ? false : (boolean) get[NumericConstants.EIGHT]);
                dto.setBrand_item_masterSid(String.valueOf(get[NumericConstants.NINE]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                resultList.add(dto);
                if (!isTotal) {
                    if (dto.getLevelNo() >= 1 && (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract()) || selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomer()))) {
                        index = 0 + ((parent == null ? indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd()) * NumericConstants.FIVE);
                        dto.setDemand_summary_indexAdd(parent == null ? indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd());
                    } else {
                        index = 0;
                    }
                }
                column = index == 0;
            }
            if (dto != null) {
                if (index < totalColumnIndex) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null || dto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY :  decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
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
                    if (get[NumericConstants.FIVE] != null && !dto.getChildrenAllowed() && !isTotal) {
                        flag[NumericConstants.THREE] = true;
                        totalColumnValue[NumericConstants.THREE] += Double.valueOf(String.valueOf(get[NumericConstants.FIVE]));
                    }
                    if (get[NumericConstants.SIX] != null) {
                        flag[NumericConstants.FOUR] = true;
                        totalColumnValue[NumericConstants.FOUR] += Double.valueOf(String.valueOf(get[NumericConstants.SIX]));
                    }
                    }
                 if (index >= totalColumnIndex || column) {
                    dto.addStringProperties(variables.get(totalColumnIndex), flag[0] ? decimalformat.format(totalColumnValue[0]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + 1), flag[1] ? decimalformat.format(totalColumnValue[1]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.TWO), flag[NumericConstants.TWO] ? decimalformat.format(totalColumnValue[NumericConstants.TWO]) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.THREE), flag[NumericConstants.THREE] ? decimalformat.format(totalColumnValue[NumericConstants.THREE]) : StringUtils.EMPTY);
                   dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FOUR), flag[NumericConstants.FOUR] ? decimalformat.format(totalColumnValue[NumericConstants.FOUR]) : StringUtils.EMPTY);
                }
                lastBrand = brand;
            }
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<T>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    protected String getUpdateOverrideQueryName() {
        return "paSummaryUpdateOverride";

    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        LOGGER.debug("Inside generate ButtonClick Btn");
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
            query = SQlUtil.getQuery("getPAAdjustmentSummaryExcelQueryView");
        } else {
            query = SQlUtil.getQuery("getPAAdjustmentSummaryExcelQuery");
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
        query = query.replace("@LEVEL_VAL", "'" + StringUtils.join(value, ",") + "'");
        query = query.replace("@DEDCONDITION", selection.getSummary_deductionLevelDes());
        query = query.replace("@CONDITIONVALUE", selection.getSummary_deductionValues().replace("'", "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionId()));
        query = query.replace("@SELECTED_COLUMNS", getSelectVariable(selection.getSummary_variables(), Boolean.FALSE));
        query = query.replace("@TOTAL_COLUMN", getSelectVariable(selection.getSummary_variables(), Boolean.TRUE));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }

    private String getSelectVariable(List<String[]> variables, boolean isTotal) {
        String selectQuery = StringUtils.EMPTY;
        String[] object = null;
        if (variables != null && !variables.isEmpty()) {
            for (int i = 0; i < variables.size(); i++) {
                object = variables.get(i);
                if (i != 0 && !object[0].contains("adjustment")) {
                    selectQuery += ",'+";
                }
                if (object[0].contains("cPipelineAccrual")) {
                    selectQuery += " 'ISNULL(SUM(CURRENT_PIPELINE_ACCRUAL),0)  AS CURRENT_PIPELINE_ACCRUAL ";
                } else if (object[0].contains("pPipelineAccrual")) {
                    selectQuery += " 'ISNULL(SUM(PROJECTED_PIPELINE_ACCRUAL),0) AS PROJECTED_PIPELINE_ACCRUAL ";
                } else if (object[0].contains("variance")) {
                    selectQuery += " 'ISNULL(SUM(VARIANCE),0) AS VARIANCE ";
                } else if (object[0].contains("override")) {
                    if (isTotal) {
                        selectQuery += " 'NULL AS OVERRIDE,'\n"
                                + " +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ";
                    } else {
                        selectQuery += "CASE WHEN @LEV='I' THEN 'SUM(OVERRIDE) AS OVERRIDE,' ELSE 'NULL AS OVERRIDE,' END\n"
                                + " +'COALESCE(SUM(ISNULL(OVERRIDE,VARIANCE)),0) AS ADJUSTMENT ";
                    }
                }
            }
        }
        return selectQuery;
    }

    /**
     * The method is for generating table headers for pipeline accrual only.
     * @param selection
     * @param columns
     * @return
     */
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
        String[] doublecolumn = getTotalColumn();
        if (doublecolumn != null) {
            doublecolumnList.add(doublecolumn);
        }
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
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add(sessionDTO.getCurrentTableNames().get(ST_ARM_PIPELINE_RATE.toString()));
        list.add(sessionDTO.getCurrentTableNames().get(ST_ARM_PIPELINE_RATE.toString()));
        return list;
    }
    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_PIPELINE_RATE"));
        return input;
    }
    
    @Override
      public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_PIPELINE_RATE"));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_PIPELINE_RATE"));
        return input;
    }
}
