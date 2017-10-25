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
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
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
public class DRSummaryLogic<T extends AdjustmentDTO> extends AbstractDemandSummaryLogic<T> {

    @Override
    protected DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterIds) {
        boolean totalFlag = false;
        if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && (criteria.getSelectionDto().getSummary_levelFilterNo() == 0)) {
                totalFlag = true;
                int offset = Integer.valueOf(inputs.get(inputs.size() - 1).toString());
                offset = offset - 1;
                inputs.set(inputs.size() - 1, offset);
        }
        boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        List<Object[]> data;
        String tableName = criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY");
        if (isView) {
            tableName = "ARM_DEMAND_RF_TRUE_UP_SUMMARY";
        }
        data = QueryUtils.getItemData(inputs, "Summary_DReforecast_generate", "Summary_DReforecast_generate_data", null, "Summary_DReforecast_generate_data_orderby", tableName, "", "WHERE RANK_VIEW BETWEEN ? AND ?");
        DataResult<T> result = cutomize(data, criteria.getSelectionDto(), masterIds, criteria);
        if (totalFlag) {
            int listSize = inputs.size() - NumericConstants.TWO;
            List<Object[]> totaldata;
            String viewData = "= CASE WHEN @MULTIPLE_PERIOD = 1 THEN '' ELSE  DEDUCTION_SET_1 END";
            totaldata = QueryUtils.getItemData(inputs.subList(0, listSize), "Summary_DReforecast_generate", "Total_Level_Summary_Demand_Reforecast", "Summary_DReforecast_generate_data", null, tableName, viewData, "");

            List<T> l = result.getDataResults();
            l.addAll((cutomize(totaldata, criteria.getSelectionDto(), masterIds, criteria)).getDataResults());
            result.setDataResults(l);
        }
        return result;
    }

    @Override
    protected int getSummaryCount(List<Object> inputs, Criteria criteria) {
        int count = 0;
        try {
            boolean isView = criteria.getSelectionDto().getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            List resultList;
            String tableName = StringUtils.EMPTY;
            if (!isView) {
                tableName = criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get("ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY");
                resultList = QueryUtils.getItemData(inputs, "Summary_DReforecast_generate", "Summary_DReforecast_generate_count", null, null, tableName, "", null);
            } else {
                tableName = "ARM_DEMAND_RF_TRUE_UP_SUMMARY";
                resultList = QueryUtils.getItemData(inputs, "Summary_DReforecast_generate", "Summary_DReforecast_generate_count", null, null, tableName, "", null);
            }
            count = (int) resultList.get(0);
            if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getSummary_levelFilterNo() == 0)) {
                    count = count + 1;
            }
        } catch (Exception e) {
           LOGGER.error(e);
        }
        return count;
    }

    @Override
    protected String getUpdateOverrideQueryName() {
        return "Update_Summary_Demand_Reforecast_Override";
    }

    @Override
    public Boolean generateButtonCheck(SelectionDTO selection) {
        LOGGER.debug("Inside generate ButtonClick Btn");
        try {
            if (selection.getSummary_deductionLevel() == 0 || selection.getSummary_variables().size() == 0
                    || (selection.getSummary_deductionVariables() != null && selection.getSummary_deductionVariables().size() == 0)
                    || ConstantsUtils.SELECT_ONE.equals(selection.getSummary_demand_fromDate()) || ConstantsUtils.SELECT_ONE.equals(selection.getSummary_demand_toDate())) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (Exception e) {
           LOGGER.error(e);
        }
        return Boolean.FALSE;
    }

    private DataResult<T> cutomize(List<Object[]> data, SelectionDTO selection, TreeMap<String, Integer> masterSids, Criteria criteria) {
        List resultList = new ArrayList<>();
        Map<String, Integer> indexMap = new HashMap();
        List<String> variables = selection.getSummary_columnList();
        int totalColumnIndex = 0;
        if (ARMConstants.getMultiplePeriod().equals(selection.getSummary_demand_view())) {
            totalColumnIndex = selection.getSummary_frequencyList().size() * NumericConstants.EIGHT;
        } else {
            totalColumnIndex = selection.getSummary_deductionVariables().size() * NumericConstants.EIGHT;
        }
        AdjustmentDTO parent = null;
        if (criteria.getParent() != null && criteria.getParent() instanceof AdjustmentDTO) {
            parent = (AdjustmentDTO) criteria.getParent();
        }
        int indexAdd = 0;
        for (String[] summary_deductionVariable : selection.getSummary_deductionVariables()) {
            indexMap.put(summary_deductionVariable[0], indexAdd);
            indexAdd++;
        }
        double[] totalColumnValue = null;
        int index = 0;
        String lastBrand = "";
        AdjustmentDTO dto = null;
        boolean ovverrideFlag = false;
        boolean isTotal = false;
        DecimalFormat decimalformat = new DecimalFormat("$#,##0.##");
        DecimalFormat percentageformat = new DecimalFormat("#,##0.##");
        for (int j = 0; j < data.size(); j++) {
            Object[] get = data.get(j);
            String brand = get[1] == null ? StringUtils.EMPTY : get[1].toString();
            if (!brand.equals(lastBrand)) {
                dto = new AdjustmentDTO();
                ovverrideFlag = false;
                isTotal = ARMUtils.TOTAL.equalsIgnoreCase(brand);
                totalColumnValue = new double[NumericConstants.NINE];
                dto.setMonth(brand);
                dto.setLevelNo((int) get[NumericConstants.TEN]);
                dto.setChildrenAllowed((selection.getSummary_levelFilterNo() != 0 || isTotal) ? false : (boolean) get[NumericConstants.ELEVEN]);
                dto.setBrand_item_masterSid(String.valueOf(get[NumericConstants.TWELVE]));
                if (masterSids != null) {
                    dto.setMasterIds(masterSids);
                }
                resultList.add(dto);
                if (ARMConstants.getMultiplePeriod().equals(selection.getSummary_demand_view()) || (!selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract()) && !selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomer()))) {
                    index = 0;
                } else if (dto.getLevelNo() >= 1) {
                    index = 0 + ((parent == null ? indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd()) * NumericConstants.EIGHT);
                    dto.setDemand_summary_indexAdd(parent == null ? indexMap.get(get[0].toString().replace(" ", StringUtils.EMPTY)) : parent.getDemand_summary_indexAdd());
                }
            }
            if (dto != null) {
                if (index < totalColumnIndex) {
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.TWO] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.THREE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.THREE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FOUR] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FOUR]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.FIVE] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(get[NumericConstants.FIVE]))));
                    dto.addStringProperties(variables.get(index++), get[NumericConstants.SIX] == null ? StringUtils.EMPTY : percentageformat.format(Double.valueOf(String.valueOf(get[NumericConstants.SIX]))) + "%");
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
                if (index >= totalColumnIndex || !brand.equals(lastBrand)) {
                    dto.addStringProperties(variables.get(totalColumnIndex), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[0]))));
                    dto.addStringProperties(variables.get(totalColumnIndex + 1), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[1]))));
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.TWO), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.TWO]))));
                    dto.addStringProperties(variables.get(totalColumnIndex + NumericConstants.THREE), decimalformat.format(Double.valueOf(String.valueOf(totalColumnValue[NumericConstants.THREE]))));
                    if (totalColumnValue[NumericConstants.TWO] != 0.0 && totalColumnValue[NumericConstants.THREE] != 0.0) {
                        totalColumnValue[NumericConstants.FOUR] = totalColumnValue[NumericConstants.TWO] / totalColumnValue[NumericConstants.THREE] * NumericConstants.HUNDRED;
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
        OriginalDataResult<T> dataResult = new OriginalDataResult<T>();
        dataResult.setDataResults(resultList);
        return dataResult;
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO selection) {
        List<String[]> frequency = selection.getSummary_frequencyList();
        Object[] returnObj = new Object[NumericConstants.TWO];
        List<Object> inputs = new ArrayList<>();
        inputs.add(selection.getProjectionMasterSid());
        inputs.add(selection.getSummary_deductionLevelDes());

        String viewType = "";
        String currentViewType = "";
        TreeMap<String, Integer> masterSids = null;
        if (dto instanceof AdjustmentDTO) {
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            LOGGER.debug("levelNo----" + levelNo);
            masterSids = val.getMasterIds();
            if (ARMConstants.getSinglePeriod().equals(selection.getSummary_demand_view())) {
                if (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract())) {
                    currentViewType = ARMUtils.getDemandSummaryLevel_singlePeriod().get(levelNo);
                    viewType = ARMUtils.getDemandSummaryLevel_singlePeriod().get(++levelNo);
                } else {
                    currentViewType = ARMUtils.getSummaryLevel().get(levelNo);
                    viewType = ARMUtils.getSummaryLevel().get(++levelNo);
                }
                inputs.add(0);
            } else {
                currentViewType = selection.getSummary_demand_Level().get(levelNo);
                viewType = selection.getSummary_demand_Level().get(++levelNo);
                inputs.add(1);
            }
            masterSids.put(currentViewType, Integer.valueOf(val.getBrand_item_masterSid()));
        } else {
            masterSids = new TreeMap<>();
            if (ARMConstants.getSinglePeriod().equals(selection.getSummary_demand_view())) {
                viewType = getView(selection.getSummary_deductionLevelDes(), selection.getSummary_viewType());
                inputs.add(0);
            } else {
                viewType = selection.getSummary_demand_Level().get(1);
                inputs.add(1);
            }
            if (selection.getSummary_levelFilterNo() != 0) {
                viewType = selection.getSummary_levelFilterValue();
            }
            if (selection.getSummary_valueSid() != 0) {
                masterSids.put(selection.getSummary_levelFilterValue(), selection.getSummary_valueSid());
            } else if (selection.getSummary_levelFilterNo() != 0) {
                masterSids.remove(selection.getSummary_levelFilterValue());
            }
        }
        if (viewType.equals(ARMConstants.getDeduction())) {
            viewType = ARMUtils.getDeductionLevelQueryName(selection.getSummary_deductionLevelDes());
        }
        inputs.add(viewType);
        inputs.add(ARMUtils.getSummaryViewType(selection.getSummary_viewType()));
        inputs.add(frequency.get(0)[1]);
        inputs.add(frequency.get(frequency.size() - 1)[1]);
        
        if (selection.getSummary_demand_frequency().matches("^[-+]?\\d+(\\.\\d+)?$")) {
            inputs.add(HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(selection.getSummary_demand_frequency())));
        } else {
            inputs.add(selection.getSummary_demand_frequency());
        }
        String viewUserID = StringUtils.EMPTY;
        String viewSessionId = StringUtils.EMPTY;
        inputs.add(viewUserID);
        inputs.add(viewSessionId);
        inputs.add(ARMUtils.getDeductionValuesMapForMultiplePeriod().get(selection.getSummary_deductionLevelDes()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()));
        inputs.add(selection.getSummary_deductionValues());
        returnObj[0] = inputs;
        returnObj[1] = masterSids;

        return returnObj;
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
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query = StringUtils.EMPTY;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (selection.isIsMultiple()) {
            query = SQlUtil.getQuery(isView ? "getDRSummaryMultiPeriodExcelQueryView" : "getDRSummaryMultiPeriodExcelQuery");
            if (selection.getSummary_demand_view().equals(ARMConstants.getSinglePeriod()) && selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract())) {
                query = query.replace("@SHIFT_VALUE", String.valueOf("S"));
            } else {
                query = query.replace("@SHIFT_VALUE", String.valueOf("M"));
            }
        } else {
 
            query = SQlUtil.getQuery(isView ? "getDRSummarySinglePeriodExcelQueryView" : "getDRSummarySinglePeriodExcelQuery");
        }
        List<String[]> frequency = selection.getSummary_frequencyList(); 
        Object[] value = selection.getExcelHierarchy();
        query = query.replace("@LEVEL_VAL", "'" + StringUtils.join(value, ",") + "'");
        String val = selection.getSummary_deductionLevelDes();
        if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION_PROGRAM.toString())) {
            val += " TYPE";
        }
        if(StringUtils.isNumeric(selection.getSummary_demand_frequency())){ 
            selection.setSummary_demand_frequency(HelperListUtil.getInstance().getIdHelperDTOMap().get(Integer.valueOf(selection.getSummary_demand_frequency())).getDescription());
        }
        query = query.replace("@DEDUCTIONLEVEL", val);
        query = query.replace("@DEDUCTIONVALUE", selection.getSummary_deductionValues().replace("'", "''"));
        query = query.replace("@FREQUENCYSELECTED", selection.getSummary_demand_frequency());
        query = query.replace("@STARTPERIOD", frequency.get(0)[1]);
        query = query.replace("@ENDPERIOD", frequency.get(frequency.size() - 1)[1]);
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY"));
        return input;
    }
    
    @Override
       public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        List input = new ArrayList<>();
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY"));
        input.add(sessionDTO.getCurrentTableNames().get("ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY"));
        return input;
    }
    
}
