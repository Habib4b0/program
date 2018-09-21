/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandreforecast.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractDemandSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;

import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
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
public class DRSummaryLogic<T extends AdjustmentDTO> extends AbstractDemandSummaryLogic<T> {

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterIds) {
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            totalFlag = true;
            int offset = Integer.parseInt(inputs.get(inputs.size() - 1).toString());
            offset = offset - 1;
            inputs.set(inputs.size() - 1, offset);
        }
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        List<Object[]> data;
        String tableName = criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY);
        if (isView) {
            tableName = ARM_DEMAND_RF_TRUE_UP_SUMMARY;
        }
        data = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_D_REFORECAST_GENERATE, "Summary_DReforecast_generate_data", null, "Summary_DReforecast_generate_data_orderby", tableName), "", "WHERE RANK_VIEW BETWEEN ? AND ?", inputs, false);
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterIds);
        if (totalFlag) {
            int listSize = inputs.size() - NumericConstants.TWO;
            List<Object[]> totaldata;
            String viewData = "= CASE WHEN @MULTIPLE_PERIOD = 1 THEN '' ELSE  DEDUCTION_SET_1 END";
            totaldata = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_D_REFORECAST_GENERATE, "Total_Level_Summary_Demand_Reforecast", "Summary_DReforecast_generate_data", null, tableName), viewData, "", inputs.subList(0, listSize), true);

            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterIds)).getDataResults());
            result.setDataResults(l);
        }
        return result;
    }
    private static final String ARM_DEMAND_RF_TRUE_UP_SUMMARY = "ARM_DEMAND_RF_TRUE_UP_SUMMARY";

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria reforecastCriteria) {
        int count = 0;
        try {
            boolean isView = reforecastCriteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            String tableName = !isView ? reforecastCriteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY) : ARM_DEMAND_RF_TRUE_UP_SUMMARY;
            List resultList = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_D_REFORECAST_GENERATE, "Summary_DReforecast_generate_count", null, null, tableName), "", null, inputs, false);
            count = (int) resultList.get(0);
            if ((count > 0 && (reforecastCriteria.getParent() == null || (!(reforecastCriteria.getParent() instanceof AdjustmentDTO)))) && (reforecastCriteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
                count = count + 1;
            }
        } catch (Exception e) {
            LOGGER.error("Error in getSummaryCount :", e);
        }
        return count;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "Update_Summary_Demand_Reforecast_Override";
    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO reforecastSelection) {
        LOGGER.debug("Inside generate ButtonClick Btn");
        try {
            if (reforecastSelection.getSummarydeductionLevel() == 0 || reforecastSelection.getSummaryvariables().isEmpty()
                    || (reforecastSelection.getSummarydeductionVariables() != null && reforecastSelection.getSummarydeductionVariables().isEmpty())
                    || GlobalConstants.getSelectOne().equals(reforecastSelection.getSummarydemandfromDate()) || GlobalConstants.getSelectOne().equals(reforecastSelection.getSummarydemandtoDate())) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("Error in generateButtonCheck :", e);
        }
        return Boolean.FALSE;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO reforecastSelection, TreeMap<String, Integer> masterSids) {
        List resultList = new ArrayList<>();
        Map<String, Integer> indexMap = new HashMap();
        List<String> variables = reforecastSelection.getSummarycolumnList();
        int indexAdd = 0;
        int startIndex = 0;
        int nextStartIndex = 0;
        int totalColumnIndex = 0;
        if (ARMConstants.getMultiplePeriod().equals(reforecastSelection.getSummarydemandview())) {
            totalColumnIndex = reforecastSelection.getSummaryfrequencyList().size() * NumericConstants.EIGHT;
            for (String[] summary_deductionVariable : reforecastSelection.getSummaryfrequencyList()) {
                indexMap.put(summary_deductionVariable[0], indexAdd);
                indexAdd++;
            }
        } else {
            totalColumnIndex = reforecastSelection.getSummarydeductionVariables().size() * NumericConstants.EIGHT;
            for (String[] summary_deductionVariable : reforecastSelection.getSummarydeductionVariables()) {
                indexMap.put(summary_deductionVariable[0], indexAdd);
                indexAdd++;
            }
        }
        double[] totalColumnValue = null;
        int index = 0;
        String lastBrand = "";
        String nextBrand;
        AdjustmentDTO reforecastDto = null;
        boolean ovverrideFlag = false;
        boolean isTotal = false;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.##");
        DecimalFormat percentageformat = new DecimalFormat("#,##0.##");
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            Object[] next = j != data.size() - 1 ? data.get(j + 1) : null;
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            nextBrand = next != null ? next[1].toString() : "aa";
            if (!brand.equals(lastBrand)) {
                reforecastDto = new AdjustmentDTO();
                ovverrideFlag = false;
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                totalColumnValue = new double[NumericConstants.NINE];
                reforecastDto.setMonth(brand);
                reforecastDto.setLevelNo((Integer) get[NumericConstants.TEN]);
                reforecastDto.setChildrenAllowed((reforecastSelection.getSummarylevelFilterNo() != 0 || isTotal) ? Boolean.FALSE : (Boolean) get[NumericConstants.ELEVEN]);
                reforecastDto.setBranditemmasterSid(String.valueOf(get[NumericConstants.TWELVE]));
                if (masterSids != null) {
                    reforecastDto.setMasterIds(masterSids);
                }
                resultList.add(reforecastDto);
                if (ARMConstants.getMultiplePeriod().equals(reforecastSelection.getSummarydemandview())) {
                    int sIndex = ARMConstants.getMonthly().equals(reforecastSelection.getSummarydemandfrequency()) ? 0 : indexMap.get(get[get.length - 1].toString().replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) * NumericConstants.EIGHT;
                    startIndex = j == 0 ? sIndex : startIndex;
                    index = 0;
                } else {
                    reforecastDto = clearVariables(variables, reforecastDto);
                }
            }
            if (ARMConstants.getSinglePeriod().equals(reforecastSelection.getSummarydemandview())) {
                index = indexMap.get(get[0].toString().replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) * NumericConstants.EIGHT;
            } else if (!ARMConstants.getMonthly().equals(reforecastSelection.getSummarydemandfrequency())) {
                index = indexMap.get((get[get.length - 1].toString()).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) * NumericConstants.EIGHT;
                nextStartIndex = next != null ? indexMap.get(next[next.length - 1].toString().replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) * NumericConstants.EIGHT : startIndex;
            }
            if (reforecastDto != null) {
                if (index < totalColumnIndex) {
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null ? StringUtils.EMPTY : percentageformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))) + "%");
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]))));
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.EIGHT] == null || reforecastDto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.EIGHT]))));
                    reforecastDto.addStringProperties(variables.get(index++), get[NumericConstants.NINE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.NINE]))));
                    totalColumnValue[0] += Double.parseDouble(String.valueOf(get[NumericConstants.TWO]));
                    totalColumnValue[1] += Double.parseDouble(String.valueOf(get[NumericConstants.THREE]));
                    totalColumnValue[NumericConstants.TWO] += Double.parseDouble(get[NumericConstants.FOUR].toString());
                    totalColumnValue[NumericConstants.THREE] += Double.parseDouble(get[NumericConstants.FIVE].toString());
                    totalColumnValue[NumericConstants.FIVE] += Double.parseDouble(get[NumericConstants.SEVEN].toString());
                    if (get[NumericConstants.EIGHT] != null && !reforecastDto.getChildrenAllowed() && !isTotal) {
                        totalColumnValue[NumericConstants.SIX] += Double.parseDouble(get[NumericConstants.EIGHT].toString());
                        ovverrideFlag = true;
                    }
                    totalColumnValue[NumericConstants.SEVEN] += Double.parseDouble(get[NumericConstants.NINE].toString());
                }
                if (getCondition(reforecastSelection, index, totalColumnIndex, nextBrand, brand)
                        || ((!ARMConstants.getMonthly().equals(reforecastSelection.getSummarydemandfrequency()) && getConditionMultiplePeriod(reforecastSelection, startIndex, nextStartIndex))
                        || (index >= totalColumnIndex && ARMConstants.getMultiplePeriod().equals(reforecastSelection.getSummarydemandview()) && ARMConstants.getMonthly().equals(reforecastSelection.getSummarydemandfrequency())))) {
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[0]))));
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex + 1), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[1]))));
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.TWO), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.TWO]))));
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.THREE), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.THREE]))));
                    if (Double.compare(totalColumnValue[NumericConstants.TWO], 0.0) != 0 && Double.compare(totalColumnValue[NumericConstants.THREE], 0.0) != 0) {
                        totalColumnValue[NumericConstants.FOUR] = totalColumnValue[NumericConstants.TWO] / totalColumnValue[NumericConstants.THREE] * NumericConstants.HUNDRED;
                    }
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FOUR), percentageformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.FOUR]))) + "%");
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FIVE), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.FIVE]))));
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.SIX), ovverrideFlag ? decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.SIX]))) : StringUtils.EMPTY);
                    reforecastDto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.SEVEN), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.SEVEN]))));
                }
            }
            lastBrand = brand;
            reforecastDto.setUserId(reforecastSelection.getSessionDTO().getUserId());
            reforecastDto.setSessionId(reforecastSelection.getSessionDTO().getSessionId());
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    private boolean getCondition(SelectionDTO selection, int index, int totalColumnIndex, String nextBrand, String brand) {
        if (ARMConstants.getSinglePeriod().equals(selection.getSummarydemandview())) {
            return index >= totalColumnIndex || !nextBrand.equals(brand);
        }
        return false;
    }

    private boolean getConditionMultiplePeriod(SelectionDTO selection, int startIndex, int nextIndex) {
        if (ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
            return startIndex == nextIndex;
        }
        return false;
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO selection) {
        List<String[]> frequency = selection.getSummaryfrequencyList();
        Object[] returnObj = new Object[NumericConstants.TWO];
        List<Object> reforecastInputs = new ArrayList<>();
        reforecastInputs.add(selection.getProjectionMasterSid());
        reforecastInputs.add(selection.getSummarydeductionLevelDes());

        String viewType = "";
        String currentViewType = "";
        TreeMap<String, Integer> masterSids = null;
        if (dto instanceof AdjustmentDTO) {
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            LOGGER.debug("levelNo----{}", levelNo);
            masterSids = val.getMasterIds();
            if (ARMConstants.getSinglePeriod().equals(selection.getSummarydemandview())) {
                if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                    currentViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(levelNo);
                    viewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(++levelNo);
                } else {
                    currentViewType = ARMUtils.getSummaryLevel().get(levelNo);
                    viewType = ARMUtils.getSummaryLevel().get(++levelNo);
                }
                reforecastInputs.add(0);
            } else {
                currentViewType = selection.getSummarydemandLevel().get(levelNo);
                viewType = selection.getSummarydemandLevel().get(++levelNo);
                reforecastInputs.add(1);
            }
            masterSids.put(currentViewType, Integer.valueOf(val.getBranditemmasterSid()));
        } else {
            masterSids = new TreeMap<>();
            if (ARMConstants.getSinglePeriod().equals(selection.getSummarydemandview())) {
                viewType = getView(selection.getSummarydeductionLevelDes(), selection.getSummaryviewType());
                reforecastInputs.add(0);
            } else {
                viewType = selection.getSummarydemandLevel().get(1);
                reforecastInputs.add(1);
            }
            if (selection.getSummarylevelFilterNo() != 0) {
                viewType = selection.getSummarylevelFilterValue();
            }
            if (selection.getSummaryvalueSid() != 0) {
                masterSids.put(selection.getSummarylevelFilterValue(), selection.getSummaryvalueSid());
            } else if (selection.getSummarylevelFilterNo() != 0) {
                masterSids.remove(selection.getSummarylevelFilterValue());
            }
        }
        if (viewType.equals(ARMConstants.getDeduction())) {
            viewType = ARMUtils.getInstance().getDeductionLevelQueryName(selection.getSummarydeductionLevelDes());
        }
        reforecastInputs.add(viewType);
        reforecastInputs.add(ARMUtils.getInstance().getSummaryViewType(selection.getSummaryviewType()));
        reforecastInputs.add(frequency.get(0)[1]);
        reforecastInputs.add(frequency.get(frequency.size() - 1)[1]);

        if (selection.getSummarydemandfrequency().matches("^[-+]?\\d+(\\.\\d+)?$")) {
            reforecastInputs.add(HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(selection.getSummarydemandfrequency())));
        } else {
            reforecastInputs.add(selection.getSummarydemandfrequency());
        }
        reforecastInputs = getFilterInputs(reforecastInputs, selection, masterSids);
        returnObj[0] = reforecastInputs;
        returnObj[1] = masterSids;

        return returnObj;
    }

    @Override
    public String getView(String deduction, String viewType) {
        Map<String, String> reforecastViewMap = ARMUtils.getViewName();
        String selectedView = reforecastViewMap.get(viewType);
        if (viewType.equals(ARMConstants.getDeductionCustomerContract())) {
            selectedView = "Deduction";
        }
        return selectedView;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO reforecastSelection) {
        String query;
        boolean isView = reforecastSelection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (reforecastSelection.isIsMultiple()) {
            query = SQlUtil.getQuery(isView ? "getDRSummaryMultiPeriodExcelQueryView" : "getDRSummaryMultiPeriodExcelQuery");
            if (reforecastSelection.getSummarydemandview().equals(ARMConstants.getSinglePeriod()) && reforecastSelection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                query = query.replace("@SHIFT_VALUE", String.valueOf("S"));
            } else {
                query = query.replace("@SHIFT_VALUE", String.valueOf("M"));
            }
        } else {

            query = SQlUtil.getQuery(isView ? "getDRSummarySinglePeriodExcelQueryView" : "getDRSummarySinglePeriodExcelQuery");
        }
        List<String[]> frequency = reforecastSelection.getSummaryfrequencyList();
        Object[] value = reforecastSelection.getExcelHierarchy();
        query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + StringUtils.join(value, ",") + ARMUtils.SINGLE_QUOTES);
        String val = reforecastSelection.getSummarydeductionLevelDes();
        if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION_PROGRAM.toString())) {
            val += " TYPE";
        }
        if (StringUtils.isNumeric(reforecastSelection.getSummarydemandfrequency())) {
            reforecastSelection.setSummarydemandfrequency(HelperListUtil.getInstance().getIdHelperDTOMap().get(Integer.valueOf(reforecastSelection.getSummarydemandfrequency())).getDescription());
        }
        query = query.replace("@DEDUCTIONLEVEL", val);
        query = query.replace("@DEDUCTIONVALUE", reforecastSelection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@FREQUENCYSELECTED", reforecastSelection.getSummarydemandfrequency());
        query = query.replace("@STARTPERIOD", frequency.get(0)[1]);
        query = query.replace("@ENDPERIOD", frequency.get(frequency.size() - 1)[1]);
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(reforecastSelection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(reforecastSelection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(reforecastSelection.getSessionDTO().getSessionId()));
        query = query.replace("@ARM_DEM_RF_TRUE_UP_SUM_TABLE", isView ? ARM_DEMAND_RF_TRUE_UP_SUMMARY : "CONCAT('ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))");
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, reforecastSelection.getSessionDTO().getCurrentTableNames()));
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY));
        return input;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_ADJUSTMENTS"));
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY));
        return input;
    }

}
