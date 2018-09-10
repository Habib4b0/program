/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandaccrual.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractDemandSummaryLogic;
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
public class DASummaryLogic<T extends AdjustmentDTO> extends AbstractDemandSummaryLogic<T> {

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterIds) {
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
            totalFlag = true;
            int offset = Integer.parseInt(inputs.get(inputs.size() - 1).toString());
            offset = offset - 1;
            inputs.set(inputs.size() - 1, offset);
        }
        List<Object[]> data;
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        String tableName;
        if (!isView) {
            tableName = criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_ADJ_SUMMARY);
        } else {
            tableName = ARMUtils.DEMAND_ACCRUAL_VIEW_TABLE;
        }
        data = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_DA_GENERATE_EDIT, "Summary_DA_generate_data", null, "Summary_DA_generate_data_orderby",
                tableName), StringUtils.EMPTY, "WHERE RANK_VIEW BETWEEN ? AND ?", inputs, false);

        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterIds);
        if (totalFlag) {
            int listSize = inputs.size() - NumericConstants.TWO;
            List<Object[]> totaldata;
            String viewData = "= CASE WHEN @MULTIPLE_PERIOD = 1 THEN '' ELSE  DEDUCTION_SET_1 END";
            totaldata = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_DA_GENERATE_EDIT, "Total_Level_Summary_Demand_Accrual", "Summary_DA_generate_data", null, tableName), viewData, "", inputs.subList(0, listSize), true);
            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterIds)).getDataResults());
            result.setDataResults(l);
        }
        return result;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        int count = 0;
        try {
            List resultList;
            boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            String tableName;
            if (!isView) {
                tableName = criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_ADJ_SUMMARY);
            } else {
                tableName = ARMUtils.DEMAND_ACCRUAL_VIEW_TABLE;
            }
            resultList = QueryUtils.getItemData(QueryUtils.getItemQuery(CommonConstant.SUMMARY_DA_GENERATE_EDIT, "Summary_DA_generate_count", null, null, tableName), StringUtils.EMPTY, null, inputs, false);
            if (resultList != null && !resultList.isEmpty()) {
                count = (int) resultList.get(0);
            }
            if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getSummarylevelFilterNo() == 0)) {
                count = count + 1;
            }
        } catch (Exception e) {
            LOGGER.error("Error in getSummaryCount :", e);
        }
        return count;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "Update_Summary_Demand_Override";
    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        try {
            if (selection.getSummarydeductionLevel() == 0 || selection.getSummaryvariables().isEmpty()
                    || (selection.getSummarydeductionVariables() != null && selection.getSummarydeductionVariables().isEmpty())
                    || GlobalConstants.getSelectOne().equals(selection.getSummarydemandfromDate()) || ((!ARMConstants.getSinglePeriod().equalsIgnoreCase(selection.getSummarydemandview())) && GlobalConstants.getSelectOne().equals(selection.getSummarydemandtoDate()))) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("Error in generateButtonCheck :", e);
        }
        return Boolean.FALSE;
    }

    private DataResult<T> cutomize(List<Object[]> accrualData, SelectionDTO selection, TreeMap<String, Integer> masterSids) {
        List resultList = new ArrayList<>();
        List<String> variables = selection.getSummarycolumnList();
        Map<String, Integer> indexMap = new HashMap();
        int indexAdd = 0;
        int startIndex = 0;
        int nextStartIndex = 0;
        int totalColumnIndex = 0;
        if (ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
            totalColumnIndex = selection.getSummaryfrequencyList().size() * NumericConstants.EIGHT;
            for (String[] summary_deductionVariable : selection.getSummaryfrequencyList()) {
                indexMap.put(summary_deductionVariable[0], indexAdd);
                indexAdd++;
            }
        } else {
            totalColumnIndex = selection.getSummarydeductionVariables().size() * NumericConstants.EIGHT;
            for (String[] summary_deductionVariable : selection.getSummarydeductionVariables()) {
                indexMap.put(summary_deductionVariable[0], indexAdd);
                indexAdd++;
            }
        }
        double[] totalColumnValue = null;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.##");
        DecimalFormat percentageformat = new DecimalFormat("#,##0.##");
        int index = 0;
        String lastBrand = StringUtils.EMPTY;
        String nextBrand;
        AdjustmentDTO dto = null;
        boolean ovverrideFlag = false;
        boolean isTotal = false;
        for (int j = 0; j < accrualData.size(); j++) {
            Object[] get = accrualData.get(j);
            Object[] next = j != accrualData.size() - 1 ? accrualData.get(j + 1) : null;
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            nextBrand = next != null ? next[1].toString() : "aa";
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                ovverrideFlag = false;
                totalColumnValue = new double[NumericConstants.NINE];
                dto.setMonth(brand);
                dto.setLevelNo((int) get[NumericConstants.TEN]);
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                dto.setChildrenAllowed((selection.getSummarylevelFilterNo() != 0 || isTotal) ? false : (boolean) get[NumericConstants.ELEVEN]);
                dto.setBranditemmasterSid(String.valueOf(get[NumericConstants.TWELVE]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                dto.setPeriod(get[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(get[NumericConstants.THIRTEEN]));
                resultList.add(dto);
                if (ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
                    int sIndex = ARMConstants.getMonthly().equals(selection.getSummarydemandfrequency()) ? 0 : indexMap.get(get[get.length - 1].toString().replace(" ", StringUtils.EMPTY)) * NumericConstants.EIGHT;
                    startIndex = j == 0 ? sIndex : startIndex;
                    index = 0;
                } else {
                    dto = clearVariables(variables, dto);
                }
            }
            if (ARMConstants.getSinglePeriod().equals(selection.getSummarydemandview())) {
                index = indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) * NumericConstants.EIGHT;
            } else if (!ARMConstants.getMonthly().equals(selection.getSummarydemandfrequency())) {
                index = indexMap.get(get[get.length - 1].toString().replace(" ", StringUtils.EMPTY)) * NumericConstants.EIGHT;
                nextStartIndex = next != null ? indexMap.get(next[next.length - 1].toString().replace(" ", StringUtils.EMPTY)) * NumericConstants.EIGHT : startIndex;
            }
            if (dto != null) {
                if (index < totalColumnIndex) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null ? StringUtils.EMPTY : (percentageformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX])))) + "%");
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SEVEN]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.EIGHT] == null || dto.getChildrenAllowed() || isTotal ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.EIGHT]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.NINE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.NINE]))));

                    totalColumnValue[0] += Double.valueOf(String.valueOf(get[NumericConstants.TWO]));
                    totalColumnValue[1] += Double.valueOf(String.valueOf(get[NumericConstants.THREE]));
                    totalColumnValue[NumericConstants.TWO] += Double.valueOf(get[NumericConstants.FOUR].toString());
                    totalColumnValue[NumericConstants.THREE] += Double.valueOf(get[NumericConstants.FIVE].toString());
                    totalColumnValue[NumericConstants.FIVE] += Double.valueOf(get[NumericConstants.SEVEN].toString());
                    if (get[NumericConstants.EIGHT] != null && !dto.getChildrenAllowed() && !isTotal) {
                        totalColumnValue[NumericConstants.SIX] += Double.valueOf(get[NumericConstants.EIGHT].toString());
                        ovverrideFlag = true;
                    }
                    totalColumnValue[NumericConstants.SEVEN] += Double.valueOf(get[NumericConstants.NINE].toString());
                }
                if (getaccrualCondition(selection, index, totalColumnIndex, nextBrand, brand)
                        || ((!ARMConstants.getMonthly().equals(selection.getSummarydemandfrequency()) && getAccrualConditionMultiplePeriod(selection, startIndex, nextStartIndex))
                        || (ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview()) && ARMConstants.getMonthly().equals(selection.getSummarydemandfrequency()) && index >= totalColumnIndex))) {
                    dto.addStringProperties(variables.get(totalColumnIndex), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[0]))));
                    dto.addStringProperties(variables.get(totalColumnIndex + 1), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[1]))));
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.TWO), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.THREE), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.THREE]))));
                    if (Double.compare(totalColumnValue[NumericConstants.TWO], 0.0) != 0 && Double.compare(totalColumnValue[NumericConstants.THREE], 0.0) != 0) {
                        totalColumnValue[NumericConstants.FOUR] = (totalColumnValue[NumericConstants.TWO] / totalColumnValue[NumericConstants.THREE]) * NumericConstants.HUNDRED;
                    }
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FOUR), percentageformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.FOUR]))) + "%");
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.FIVE), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.FIVE]))));
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.SIX), ovverrideFlag ? decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.SIX]))) : StringUtils.EMPTY);
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.SEVEN), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.SEVEN]))));
                }
            }
            lastBrand = brand;
            dto.setUserId(selection.getSessionDTO().getUserId());
            dto.setSessionId(selection.getSessionDTO().getSessionId());
        }
        LOGGER.debug("index-------{}", index);
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    private boolean getaccrualCondition(SelectionDTO accrualSelection, int index, int totalColumnIndex, String nextBrand, String brand) {
        if (ARMConstants.getSinglePeriod().equals(accrualSelection.getSummarydemandview())) {
            return index >= totalColumnIndex || !nextBrand.equals(brand);
        }
        return false;
    }

    private boolean getAccrualConditionMultiplePeriod(SelectionDTO accrualSelection, int startIndex, int nextIndex) {
        if (ARMConstants.getMultiplePeriod().equals(accrualSelection.getSummarydemandview())) {
            return startIndex == nextIndex;
        }
        return false;
    }

    @Override
    protected Object[] generateInputs(Object accrualDto, SelectionDTO selection) {

        List<String[]> frequency = selection.getSummaryfrequencyList();
        Object[] returnObj = new Object[NumericConstants.TWO];
        try {
            List<Object> accrualInputs = new ArrayList<>();
            accrualInputs.add(selection.getProjectionMasterSid());
            accrualInputs.add(selection.getSummarydeductionLevelDes());

            String accrualViewType = "";
            String currentViewType = "";
            TreeMap<String, Integer> masterSids = null;
            if (accrualDto instanceof AdjustmentDTO) {
                AdjustmentDTO accrualVal = (AdjustmentDTO) accrualDto;
                int levelNo = accrualVal.getLevelNo();
                LOGGER.debug("levelNo----{}", levelNo);
                masterSids = accrualVal.getMasterIds();

                if (ARMConstants.getSinglePeriod().equals(selection.getSummarydemandview())) {
                    if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                        currentViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(levelNo);
                        accrualViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(++levelNo);
                    } else {
                        currentViewType = ARMUtils.getSummaryLevel().get(levelNo);
                        accrualViewType = ARMUtils.getSummaryLevel().get(++levelNo);
                    }
                    accrualInputs.add(0);
                } else {
                    currentViewType = selection.getSummarydemandLevel().get(levelNo);
                    accrualViewType = selection.getSummarydemandLevel().get(++levelNo);
                    accrualInputs.add(1);
                }
                masterSids.put(currentViewType, Integer.valueOf(accrualVal.getBranditemmasterSid()));
            } else {
                masterSids = new TreeMap<>();
                if (ARMConstants.getSinglePeriod().equals(selection.getSummarydemandview())) {
                    accrualViewType = getView(selection.getSummarydeductionLevelDes(), selection.getSummaryviewType());
                    accrualInputs.add(0);
                } else {
                    accrualViewType = selection.getSummarydemandLevel().get(1);
                    accrualInputs.add(1);
                }
                if (selection.getSummaryvalueSid() != 0) {
                    masterSids.put(selection.getSummarylevelFilterValue(), selection.getSummaryvalueSid());
                } else if (selection.getSummarylevelFilterNo() != 0) {
                    masterSids.remove(selection.getSummarylevelFilterValue());
                }

                if (selection.getSummarylevelFilterNo() != 0) {
                    accrualViewType = selection.getSummarylevelFilterValue();
                }
            }

            if (ARMConstants.getDeduction().equals(accrualViewType)) {
                accrualViewType = ARMUtils.getDeductionLevelQueryName(selection.getSummarydeductionLevelDes());
            }
            accrualInputs.add(accrualViewType);
            accrualInputs.add(ARMUtils.getSummaryViewType(selection.getSummaryviewType()));
            accrualInputs.add(frequency.get(0)[1]);
            accrualInputs.add(frequency.get(frequency.size() - 1)[1]);
            accrualInputs.add(selection.getSummarydemandfrequency());
            accrualInputs = getFilterInputs(accrualInputs, selection, masterSids);
            returnObj[0] = accrualInputs;
            returnObj[1] = masterSids;
        } catch (Exception e) {
            LOGGER.error("Error in generateInputs :", e);
        }
        return returnObj;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO accrualSelection) {
        String query;
        boolean isView = accrualSelection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (accrualSelection.isIsMultiple()) {
            query = SQlUtil.getQuery(isView ? "Excel_Muliple_Period_Demand_Accrual_QueryView" : "Excel_Muliple_Period_Demand_Accrual_Query");
            if (accrualSelection.getSummarydemandview().equals(ARMConstants.getSinglePeriod()) && accrualSelection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                query = query.replace("@SHIFT_VALUE", String.valueOf("S"));
            } else {
                query = query.replace("@SHIFT_VALUE", String.valueOf("M"));
            }
        } else {
            query = SQlUtil.getQuery(isView ? "Excel_Demand_Accrual_QueryView" : "Excel_Demand_Accrual_Query");
        }
        List<String[]> frequency = accrualSelection.getSummaryfrequencyList();
        Object[] value = accrualSelection.getExcelHierarchy();
        query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + StringUtils.join(value, ",") + ARMUtils.SINGLE_QUOTES);
        String val = accrualSelection.getSummarydeductionLevelDes();
        if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION_PROGRAM.toString())) {
            val += " TYPE";
        }
        query = query.replace("@DEDUCTIONLEVEL", val);
        query = query.replace("@DEDUCTIONVALUE", accrualSelection.getSummarydeductionValues().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@FREQUENCYSELECTED", accrualSelection.getSummarydemandfrequency());
        query = query.replace("@STARTPERIOD", frequency.get(0)[1]);
        query = query.replace("@ENDPERIOD", frequency.get(frequency.size() - 1)[1]);
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(accrualSelection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(accrualSelection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(accrualSelection.getSessionDTO().getSessionId()));
        query = query.replace("@ARM_DEMAND_ADJ_SUM_TABLE", isView ? "ARM_DEMAND_ADJ_SUMMARY" : "CONCAT( 'ST_ARM_DEMAND_ADJ_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE( CONVERT( VARCHAR( 50 ), GETDATE(), 2 ), '.', '' ))");
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, accrualSelection.getSessionDTO().getCurrentTableNames()));
    }

    @Override
    public String getView(String deduction, String viewType) {
        Map<String, String> viewMap = ARMUtils.getViewName();
        String selectedView = viewMap.get(viewType);
        if (viewType.equals(ARMConstants.getDeductionCustomerContract())) {
            selectedView = "Deduction";
        }
        return selectedView;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_ADJ_SUMMARY));
        return input;
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_ADJ_SUMMARY));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_ADJUSTMENTS"));
        input.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DEMAND_ADJ_SUMMARY));
        return input;
    }
}
