
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
import java.util.Iterator;
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
public class Trx6SummaryLogic<T extends AdjustmentDTO> extends AbstractPipelineSummaryLogic<T> {

    public static final Logger LOGGERFORSUMMARYLOGIC = LoggerFactory.getLogger(Trx6SummaryLogic.class);

    @Override
    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        LOGGERFORSUMMARYLOGIC.debug("inside generateHeader");
        List<Object> finalList = new ArrayList<>();
        try {
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
            List<String> excelSingleColumn = new ArrayList<>();

            List<String> singleColumn = new ArrayList<>();
            int index = 0;
            List<String> columnList = getColumns(selection.getSummaryvariables());
            List<String[]> doubleHeaderVariables = selection.getSummarydeductionVariables();
            List<String[]> doublecolumnList = new ArrayList<>();
            doublecolumnList.addAll(doubleHeaderVariables);
            doubleHeaderVariables = doublecolumnList;

            for (String[] detection : doubleHeaderVariables) {
                doubleSingleColumn = new ArrayList<>();
                excelDoubleSingleColumn = new ArrayList<>();
                for (int i = 0; i < columns.length; i++, index++) {
                    String column = columns[i];
                    singleColumn.add(column + "." + index);
                    excelSingleColumn.add((detection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : detection[0].replace(" ", org.apache.commons.lang.StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + column + "." + (index));
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
        } catch (Exception e) {
            LOGGERFORSUMMARYLOGIC.error("Error in generateHeader :" + e);
        }
        return finalList;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        LOGGERFORSUMMARYLOGIC.debug("inside getSummaryCount");
        List resultList;
        int count = 0;
        try {
            boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            String tableName = isView ? CommonConstant.ARM_INFLATION_INVENTORY_ADJ : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_INFLATION_INVENTORY_ADJ);
            resultList = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.TRX6_SUMMARY_GENERATE_INVENTORY_EDIT, "getTrx6_Summary_generate_Inventory_Count", null, null, tableName), null, null, inputs, false);
            count = (int) resultList.get(0);
            if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
                count = count + 1;
            }
        } catch (Exception e) {
            LOGGERFORSUMMARYLOGIC.error("Error in getSummaryCount :" + e);
        }
        return count;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        LOGGERFORSUMMARYLOGIC.debug("inside getSummaryData");
        DataResult<T> result = null;
        try {
            boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            boolean totalFlag = false;
            if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage())) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
                totalFlag = true;
                int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString()) - 1;
                inputs.set(inputs.size() - 1, offset);

            }
            List<Object[]> data;
            String tableName = isView ? CommonConstant.ARM_INFLATION_INVENTORY_ADJ : criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_INFLATION_INVENTORY_ADJ);
            data = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.TRX6_SUMMARY_GENERATE_INVENTORY_EDIT, "getTrx6_Summary_generate_Inventory_select", null, "getTrx6_Summary_generate_Inventory_select_orderBy", tableName), null, "WHERE RANK_VIEW BETWEEN ? AND ?", inputs, false);
            result = cutomize(data, criteria.getSelectionDto(), masterSids);
            if (totalFlag) {
                List<Object[]> totaldata;
                for (Iterator<Object> it = inputs.iterator(); it.hasNext();) {
                    Object object = it.next();
                    if (StringUtils.EMPTY.equals(String.valueOf(object))) {
                        it.remove();
                    }
                }
                List<Object> totalInputs = new ArrayList<>(inputs);
                totalInputs.remove(totalInputs.size() - 1);
                totalInputs.remove(totalInputs.size() - 1);
                totaldata = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.TRX6_SUMMARY_GENERATE_INVENTORY_EDIT, "Summary_TRX6Inventory_total_data", null, null, tableName), null, null, totalInputs, true);
                List<T> l = result.getDataResults();
                l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterSids)).getDataResults());
                result.setDataResults(l);
            }
            LOGGERFORSUMMARYLOGIC.debug("Exit getSummaryData");

        } catch (Exception e) {
            LOGGERFORSUMMARYLOGIC.error("Error in getSummaryData :" + e);
        }
        return result;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO selection, TreeMap<String, Integer> masterSids) {
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        try {
            List<String> columnList = getColumns(selection.getSummaryvariables());
            boolean hasOverride = columnList.contains("override");
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
            for (int j = 0; j < data.size(); j++) {
                Object[] get = data.get(j);
                levelName = String.valueOf(get[NumericConstants.ELEVEN]);
                String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
                boolean isTotal = false;
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
                    resultList.add(dto);
                    dto = clearVariables(variables, dto);
                }
                index = indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) * NumericConstants.SIX;
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
                LOGGERFORSUMMARYLOGIC.debug("index-------" + index);
            }

            dataResult.setDataResults(resultList);
        } catch (Exception e) {
            LOGGERFORSUMMARYLOGIC.error("Error in cutomize :" + e);
        }
        return dataResult;

    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "Update_Summary_Inventory_OverrideFor_Trx6";

    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        try {
            if (selection.getSummarydeductionLevel() == 0 || selection.getSummaryvariables().isEmpty() || (selection.getSummarydeductionVariables() != null && selection.getSummarydeductionVariables().isEmpty())) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGERFORSUMMARYLOGIC.error("Error in generateButtonCheck :" + e);
        }
        return Boolean.FALSE;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        List list = null;

        try {
            String query;
            boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            if (isView) {
                query = SQlUtil.getQuery("getAdjustmentSummaryExcelQueryView_For_Tx6");
            } else {
                query = SQlUtil.getQuery("getAdjustmentSummaryExcelQuery_For_Trx6");
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
            query = query.replace("@LEVEL_VAL", "'" + org.apache.commons.lang.StringUtils.join(value, ",") + "'");

            query = query.replace("@DEDCONDITION", "'" + selection.getSummarydeductionLevelDes() + "'");
            query = query.replace("@CONDITIONVALUE", selection.getSummarydeductionValues().replace("'", "''"));
            query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
            query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
            query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
            LOGGERFORSUMMARYLOGIC.debug(query);
            list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        } catch (Exception e) {
            LOGGERFORSUMMARYLOGIC.error("Error in getExcelResultList :" + e);
        }
        return list;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INFLATION_INVENTORY_ADJ));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INFLATION_INVENTORY_ADJ));
        return list;
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INFLATION_INVENTORY_ADJ));
        return input;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INFLATION_INVENTORY_ADJ));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_ADJUSTMENTS"));
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INFLATION_INVENTORY_ADJ));
        return input;
    }

}
