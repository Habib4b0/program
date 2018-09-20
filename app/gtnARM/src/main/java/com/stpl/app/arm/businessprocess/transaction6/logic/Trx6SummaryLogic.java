
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

    public static final Logger LOGGERFORTX6SUMMARYLOGIC = LoggerFactory.getLogger(Trx6SummaryLogic.class);

    @Override
    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        LOGGERFORTX6SUMMARYLOGIC.debug("inside generateHeader");
        List<Object> finalList = new ArrayList<>();
        try {
            List<String> tx6DoubleSingleColumn;
            List<String> tx6ExcelDoubleSingleColumn;
            List<String> tx6SingleVisibleColumn = new ArrayList<>();
            List<String> tx6ExcelVisibleColumn = new ArrayList<>();
            List<String> tx6ExcelVisibleHeader = new ArrayList<>();
            List<String> tx6DoubleVisibleColumn = new ArrayList<>();
            Map<Object, Object[]> tx6DoubleSingleVisibleColumn = new HashMap<>();
            Map<Object, Object[]> tx6ExcelDoubleSingleVisibleColumn = new HashMap<>();
            tx6ExcelDoubleSingleVisibleColumn.put("month", new Object[]{"month"});
            List<String> tx6SingleVisibleHeader = new ArrayList<>();
            List<String> tx6DoubleVisibleHeader = new ArrayList<>();
            List<String> tx6ExcelSingleColumn = new ArrayList<>();

            List<String> singleColumn = new ArrayList<>();
            int index = 0;
            List<String> columnList = getColumns(selection.getSummaryvariables());
            List<String[]> doubleHeaderVariables = selection.getSummarydeductionVariables();
            List<String[]> doublecolumnList = new ArrayList<>();
            doublecolumnList.addAll(doubleHeaderVariables);
            doubleHeaderVariables = doublecolumnList;

            for (String[] detection : doubleHeaderVariables) {
                tx6DoubleSingleColumn = new ArrayList<>();
                tx6ExcelDoubleSingleColumn = new ArrayList<>();
                for (int i = 0; i < columns.length; i++, index++) {
                    String column = columns[i];
                    singleColumn.add(column + ARMUtils.DOT + index);
                    tx6ExcelSingleColumn.add((detection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : detection[0].replace(ARMUtils.SPACE.toString(), org.apache.commons.lang.StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + column + ARMUtils.DOT + (index));
                    if (columnList.contains(column)) {
                        int listIndex = columnList.indexOf(column);
                        String visibleColumn = selection.getSummaryvariables().get(listIndex)[0] + ARMUtils.DOT + index;
                        String header = selection.getSummaryvariables().get(listIndex)[1];
                        tx6SingleVisibleColumn.add(visibleColumn);
                        tx6SingleVisibleHeader.add(header);
                        tx6DoubleSingleColumn.add(visibleColumn);
                        tx6ExcelVisibleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                        tx6ExcelVisibleHeader.add(header);
                        tx6ExcelDoubleSingleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                    }
                }

                tx6DoubleVisibleColumn.add(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY));
                tx6DoubleVisibleHeader.add(detection[1]);
                tx6DoubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), tx6DoubleSingleColumn.toArray());
                tx6ExcelDoubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), tx6ExcelDoubleSingleColumn.toArray());
            }
            selection.setSummarycolumnList(singleColumn);
            finalList.add(tx6SingleVisibleColumn);
            finalList.add(tx6DoubleVisibleColumn);
            finalList.add(tx6SingleVisibleHeader);
            finalList.add(tx6DoubleVisibleHeader);
            finalList.add(tx6DoubleSingleVisibleColumn);
            finalList.add(tx6ExcelVisibleColumn);
            finalList.add(tx6ExcelVisibleHeader);
            finalList.add(tx6ExcelDoubleSingleVisibleColumn);
            finalList.add(tx6ExcelSingleColumn);
        } catch (Exception e) {
            LOGGERFORTX6SUMMARYLOGIC.error("Error in generateHeader :", e);
        }
        return finalList;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        LOGGERFORTX6SUMMARYLOGIC.debug("inside getSummaryCount");
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
            LOGGERFORTX6SUMMARYLOGIC.error("Error in getSummaryCount :", e);
        }
        return count;
    }

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterSids) {
        LOGGERFORTX6SUMMARYLOGIC.debug("inside getSummaryData");
        DataResult<T> result = null;
        try {
            boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            boolean totalFlag = false;
            if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage())) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
                totalFlag = true;
                int offset = ARMUtils.getIntegerValue(inputs.get(inputs.size() - 1).toString()) - 1;
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
            LOGGERFORTX6SUMMARYLOGIC.debug("Exit getSummaryData");

        } catch (Exception e) {
            LOGGERFORTX6SUMMARYLOGIC.error("Error in getSummaryData :", e);
        }
        return result;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO tx6Selection, TreeMap<String, Integer> masterSids) {
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        try {
            List<String> columnList = getColumns(tx6Selection.getSummaryvariables());
            boolean hasOverride = columnList.contains("override");
            List resultList = new ArrayList();
            List<String> variables = tx6Selection.getSummarycolumnList();
            Map<String, Integer> indexMap = new HashMap();
            int indexAdd = 0;
            for (String[] summary_deductionVariable : tx6Selection.getSummarydeductionVariables()) {
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
                    dto.setLevelNo((Integer) get[NumericConstants.EIGHT]);
                    isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                    dto.setChildrenAllowed((tx6Selection.getSummarylevelFilterNo() != 0 || isTotal) ? Boolean.FALSE : (Boolean) get[NumericConstants.NINE]);
                    dto.setBranditemmasterSid(String.valueOf(get[NumericConstants.TEN]));
                    if (masterSids != null) {
                        dto.setMasterIds(masterSids);
                    }
                    resultList.add(dto);
                    dto = clearVariables(variables, dto);
                }
                index = indexMap.get(get[0].toString().replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) * NumericConstants.SIX;
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
                LOGGERFORTX6SUMMARYLOGIC.debug("index-------{}", index);
            }

            dataResult.setDataResults(resultList);
        } catch (Exception e) {
            LOGGERFORTX6SUMMARYLOGIC.error("Error in cutomize :", e);
        }
        return dataResult;

    }

    @Override
    protected String getUpdateOverrideQueryName() {
        LOGGERFORTX6SUMMARYLOGIC.debug("inside getUpdateOverrideQueryName");
        return "Update_Summary_Inventory_OverrideFor_Trx6";

    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        try {
            LOGGERFORTX6SUMMARYLOGIC.debug("inside generateButtonCheck");
            if (selection.getSummarydeductionLevel() == 0 || selection.getSummaryvariables().isEmpty() || (selection.getSummarydeductionVariables() != null && selection.getSummarydeductionVariables().isEmpty())) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGERFORTX6SUMMARYLOGIC.error("Error in generateButtonCheck :", e);
        }
        return Boolean.FALSE;

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO tx6Selection) {
        List list = null;

        try {
            String query;
            boolean isView = tx6Selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            if (isView) {
                query = SQlUtil.getQuery("getAdjustmentSummaryExcelQueryView_For_Tx6");
            } else {
                query = SQlUtil.getQuery("getAdjustmentSummaryExcelQuery_For_Trx6");
            }
            Object[] value = null;
            if (tx6Selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && tx6Selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                value = ARMUtils.getDTCBI();
            } else if (tx6Selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                value = ARMUtils.getTCBI();
            } else if (tx6Selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomer())) {
                value = ARMUtils.getTBI();
            } else if (tx6Selection.getSummaryviewType().equals(ARMConstants.getDeductionProduct())) {
                value = ARMUtils.getBI();
            }
            query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + org.apache.commons.lang.StringUtils.join(value, ",") + ARMUtils.SINGLE_QUOTES);

            query = query.replace("@DEDCONDITION", ARMUtils.SINGLE_QUOTES + tx6Selection.getSummarydeductionLevelDes() + ARMUtils.SINGLE_QUOTES);
            query = query.replace("@CONDITIONVALUE", tx6Selection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
            query = query.replace("@PROJECTIONMASTERSID", String.valueOf(tx6Selection.getProjectionMasterSid()));
            query = query.replace("@USERID", String.valueOf(tx6Selection.getSessionDTO().getUserId()));
            query = query.replace("@SESSIONID", String.valueOf(tx6Selection.getSessionDTO().getSessionId()));
            LOGGERFORTX6SUMMARYLOGIC.debug(query);
            list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, tx6Selection.getSessionDTO().getCurrentTableNames()));
        } catch (Exception e) {
            LOGGERFORTX6SUMMARYLOGIC.error("Error in getExcelResultList :", e);
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
