/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class Trx7PipelineAccrualRateLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends Trx7RateLogic<T, E> {

    public static final Logger LOGGERFORRATESTAB = LoggerFactory.getLogger(Trx7PipelineAccrualRateLogic.class);

    @Override
    public int getCount(Criteria criteria) {
        return (int) getRateQuery((AbstractSelectionDTO) criteria.getSelectionDto(), criteria.getParent(), true, 0, 0, 0, 0);
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        List result = (List) getRateQuery((AbstractSelectionDTO) criteria.getSelectionDto(), criteria.getParent(), false, criteria.getStart(), criteria.getOffset(), 0, 0);
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(result);
        return dataResult;
    }

    /**
     *
     * @param result
     * @param selection
     * @param lastParent
     * @return
     */
    @Override
    protected List<AdjustmentDTO> customizeResultSet(List result, AbstractSelectionDTO selection, AdjustmentDTO lastParent) {

        List<AdjustmentDTO> resultList = new ArrayList<>();
        List columnList = ARMConstants.getDeduction().equalsIgnoreCase(selection.getRateDeductionLevelName())
                ? selection.getRateColumnList().get(NumericConstants.THREE) : selection.getRateColumnList().get(1);
        String lastValue = null;
        AdjustmentDTO dto = new AdjustmentDTO();
        DecimalFormat decimalformat = new DecimalFormat("#,##0.000");
        for (int i = 0; i < result.size(); i++) {
            Object[] obj = (Object[]) result.get(i);
            if (lastValue == null || !lastValue.equalsIgnoreCase(String.valueOf(obj[1]))) {
                dto = new AdjustmentDTO();
                resultList.add(dto);
                dto.setGroup(String.valueOf(obj[1]));
                dto.setLevelName(String.valueOf(obj[NumericConstants.FOUR]));
                switch (dto.getLevelName()) {
                    case VariableConstants.DEDUCTION_UPPERCASE:
                        dto.setDeductionSID((Integer) (obj[NumericConstants.THREE]));
                        break;

                    case VariableConstants.CUSTOMER_UPPERCASE:

                        dto.setCustomerSID((Integer) (obj[NumericConstants.THREE]));
                        dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;

                    case VariableConstants.CONTRACT_UPPERCASE:
                        dto.setContractSID((Integer) (obj[NumericConstants.THREE]));
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;

                    case VariableConstants.BRAND_UPPERCASE:
                        dto.setBrandSID((Integer) (obj[NumericConstants.THREE]));
                        dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;
                    case VariableConstants.PRODUCT_UPPER:
                        dto.setBranditemmasterSid(String.valueOf(obj[NumericConstants.THREE]));
                        dto.setBrandSID(lastParent != null && lastParent.getBrandSID() != null ? lastParent.getBrandSID() : 0);
                        dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;
                    default:
                }
                dto.setChildrenAllowed((!VariableConstants.PRODUCT_UPPER.equalsIgnoreCase(dto.getLevelName())) && (selection.getRateslevelFilterNo() == 0));
                dto.setLevelNo(selection.getLevelNo());
            }
            if (!ARMUtils.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
                dto.addStringProperties(String.valueOf(obj[0]) + "." + columnList.indexOf(String.valueOf(obj[0])), decimalformat.format(Double.valueOf(obj[NumericConstants.TWO] == null ? "0" : String.valueOf(obj[NumericConstants.TWO]))) + "%");
            }
            lastValue = String.valueOf(obj[1]);
        }

        return resultList;
    }

    /**
     *
     * @param selection
     * @param isCount
     * @return
     */
    @Override
    protected Object getRateQuery(AbstractSelectionDTO selection, Object lastParent, boolean isCount, int start, int offset, int currentPage, int lastPage) {
        LOGGERFORRATESTAB.debug("--Inside getRateQuery--");
        int startindex = start + 1;
        int endindex = start + offset;
        List input = new ArrayList<>();
        List<AdjustmentDTO> resultDTO = new ArrayList();
        try {
            input.add(selection.getDataSelectionDTO().getProjectionId());
            boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            if (!isView) {
                input.add(selection.getSessionDTO().getUserId());
                input.add(selection.getSessionDTO().getSessionId());
            }
            String queryNameCustomer = isView ? CommonConstant.TRX7_CUSTOMERCONTRACTVIEW : CommonConstant.TRX7_CUSTOMERCONTRACTEDIT;
            String queryNameProduct = isView ? "trx7_customerproductview" : "trx7_customerproductedit";
            String queryName = ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) || CommonConstant.DEDUCTION_CONTRACT.equals(selection.getRateDeductionView()) || ARMConstants.getDeductionCustomer().equals(selection.getRateDeductionView()) || ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView()) ? queryNameCustomer
                    : queryNameProduct;

            if (isCount) {
                List count = null;
                if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                    AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                    count = QueryUtils.getItemData(getQueryInput(parentDTO, selection, input, queryName), queryName, CommonConstant.TRX7_CUSTOMERPRODUCTCOUNT);

                } else if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView())
                        && !CommonConstant.DEDUCTION_CONTRACT.equals(selection.getRateDeductionView())
                        && !"Deduction Customer".equals(selection.getRateDeductionView()) && !ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), NumericConstants.ONE, selection.getTableName(), "%", "%", selection.getRateDeductionValue())));
                    count = QueryUtils.getItemData(input, queryName, CommonConstant.TRX7_CUSTOMERPRODUCTCOUNT);
                } else {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), NumericConstants.ONE, selection.getTableName(), "%", "%", "%", "%", selection.getRateDeductionValue())));
                    count = QueryUtils.getItemData(input, queryName, CommonConstant.TRX7_CUSTOMERPRODUCTCOUNT);
                }
                LOGGERFORRATESTAB.debug("-----------COUNT-----------{}", count);
                return count != null && !count.isEmpty() && count.get(0) != null ? count.get(0) : 0;
            } else {
                List result;
                if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                    AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                    getQueryInput(parentDTO, selection, input, queryName);
                    input.addAll(new ArrayList<>(Arrays.asList(isView ? CommonConstant.ARM_DISTRIBUTION_FEES_SALES : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_SALES), startindex, endindex)));
                    result = QueryUtils.getItemData(input, queryName, CommonConstant.TRX7_CUSTOMERPRODUCTDATA);
                    resultDTO = customizeResultSet(result, selection, parentDTO);
                } else {
                    String deductionValue = selection.getRateDeductionValue().startsWith(String.valueOf(ARMUtils.SINGLE_QUOTES)) ? selection.getRateDeductionValue() : ARMUtils.SINGLE_QUOTES + selection.getRateDeductionValue().replace(",", "','") + ARMUtils.SINGLE_QUOTES;
                    if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) && !CommonConstant.DEDUCTION_CONTRACT.equals(selection.getRateDeductionView())
                            && !"Deduction Customer".equals(selection.getRateDeductionView()) && !ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), NumericConstants.ONE,
                                selection.getTableName(), "%", "%", deductionValue, isView ? CommonConstant.ARM_DISTRIBUTION_FEES_SALES : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_SALES),
                                startindex, endindex)));
                        result = QueryUtils.getItemData(input, queryName, CommonConstant.TRX7_CUSTOMERPRODUCTDATA);
                    } else {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), NumericConstants.ONE,
                                selection.getTableName(), "%", "%", "%", "%", deductionValue, isView ? CommonConstant.ARM_DISTRIBUTION_FEES_SALES : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_SALES),
                                startindex, endindex)));
                        result = QueryUtils.getItemData(input, queryName, CommonConstant.TRX7_CUSTOMERPRODUCTDATA);
                    }
                    LOGGERFORRATESTAB.debug(queryName);
                    resultDTO = customizeResultSet(result, selection, null);
                }
                LOGGERFORRATESTAB.debug("--Exit getRateQuery--{}", resultDTO.size());
            }
        } catch (Exception e) {
            LOGGERFORRATESTAB.error("Error in getRateQuery :", e);
        }
        return resultDTO;
    }

    /**
     * returns input for rate query w.r.t. level
     *
     * @param parentDTO
     * @param selection
     * @param input
     * @param queryName
     * @return
     */
    @Override
    protected List getQueryInput(AdjustmentDTO parentDTO, AbstractSelectionDTO selection, List input, String queryName) {
        LOGGERFORRATESTAB.debug("--Inside getQueryInput --{}", queryName);
        selection.setRateDeductionValue(selection.getRateDeductionValue().startsWith(String.valueOf(ARMUtils.SINGLE_QUOTES)) ? selection.getRateDeductionValue() : ARMUtils.SINGLE_QUOTES + selection.getRateDeductionValue().replace(",", "','") + ARMUtils.SINGLE_QUOTES);
        switch (parentDTO.getLevelName()) {
            case VariableConstants.DEDUCTION_UPPERCASE:
                if (!CommonConstant.DEDUCTION_CONTRACT.equals(selection.getRateDeductionView())) {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE : VariableConstants.CUSTOMER_UPPERCASE, NumericConstants.ONE, selection.getTableName())));
                } else {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), VariableConstants.CONTRACT_UPPERCASE, NumericConstants.ONE, selection.getTableName())));
                }

                input.add("%");
                input.add(parentDTO.getDeductionSID());
                input.addAll(new ArrayList<>(Arrays.asList("%", "%", selection.getRateDeductionValue())));
                break;

            case VariableConstants.CUSTOMER_UPPERCASE:
                if (!CommonConstant.DEDUCTION_CONTRACT.equals(selection.getRateDeductionView())) {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(),
                            ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView())
                            || ARMConstants.getDeductionCustomer().equals(selection.getRateDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE
                            : VariableConstants.BRAND_UPPERCASE, NumericConstants.ONE, selection.getTableName())));
                } else {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), VariableConstants.BRAND_UPPERCASE, NumericConstants.ONE, selection.getTableName())));
                }
                input.add("%");
                if (CommonConstant.TRX7_CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.TRX7_CUSTOMERCONTRACTEDIT.equals(queryName)) {
                    input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                }
                input.add(parentDTO.getCustomerSID());
                if (CommonConstant.TRX7_CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.TRX7_CUSTOMERCONTRACTEDIT.equals(queryName)) {
                    input.add("%");
                }
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionValue())));
                break;

            case VariableConstants.CONTRACT_UPPERCASE:
                if (!CommonConstant.DEDUCTION_CONTRACT.equals(selection.getRateDeductionView())) {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView()) ? VariableConstants.CUSTOMER_UPPERCASE : VariableConstants.BRAND_UPPERCASE, NumericConstants.ONE, selection.getTableName())));
                } else {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), VariableConstants.CUSTOMER_UPPERCASE, NumericConstants.ONE, selection.getTableName())));
                }
                input.add("%");
                input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                input.add(parentDTO.getCustomerSID() == 0 ? "%" : parentDTO.getCustomerSID());
                input.add(parentDTO.getContractSID());
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionValue())));
                break;

            case VariableConstants.BRAND_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), VariableConstants.PRODUCT_UPPER, NumericConstants.ONE, selection.getTableName())));
                input.add(parentDTO.getBrandSID());
                if (CommonConstant.TRX7_CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.TRX7_CUSTOMERCONTRACTEDIT.equals(queryName)) {
                    input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                }
                input.add(ARMConstants.getDeductionProduct().equals(selection.getRateDeductionView())
                        ? "%" : parentDTO.getCustomerSID());
                if (CommonConstant.TRX7_CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.TRX7_CUSTOMERCONTRACTEDIT.equals(queryName)) {
                    input.add(parentDTO.getContractSID());
                }
                input.addAll(new ArrayList<>(Arrays.asList(getDeductionList(selection.getRateDeductionValue()))));
                break;
            default:

        }
        LOGGERFORRATESTAB.debug("--Exit getQueryInput --{}", input.size());
        return input;
    }

    /**
     * Method to return string with '' if its not present
     *
     * @param deductionValue
     * @return String
     */
    private String getDeductionList(String deductionValue) {
        List<String> listSize = new ArrayList<>(Arrays.asList(deductionValue));
        String value = listSize.get(0);
        if (!listSize.isEmpty() && !listSize.get(0).contains(String.valueOf(ARMUtils.SINGLE_QUOTES))) {
            value = ARMUtils.SINGLE_QUOTES + value.replace(",", "','") + ARMUtils.SINGLE_QUOTES;
        }
        return value;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        LOGGERFORRATESTAB.debug("Inside getExcelResultList");
        String query = StringUtils.EMPTY;
        List list = new ArrayList();
        try {
            boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
            if (isView) {
                query = SQlUtil.getQuery("trx7_getExcelRatePipelineAccrualView");
            } else {
                query = SQlUtil.getQuery("trx7_getExcelRatePipelineAccrual");
            }
            Object[] value = null;
            if ("Deduction".equals(selection.getRateDeductionView()) && selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                value = new Object[]{"D", "T", "C", "B", "I"};
            } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionContractCustomer())) {
                value = new Object[]{"D", "C", "T", "B", "I"};
            } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionCustomerContract())) {
                value = new Object[]{"D", "T", "C", "B", "I"};
            } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionCustomer())) {
                value = new Object[]{"T", "C", "B", "I"};
            } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionProduct())) {
                value = new Object[]{"B", "I"};
            } else if (selection.getRateDeductionView().equals(CommonConstant.DEDUCTION_CONTRACT)) {
                value = new Object[]{"C", "T", "B", "I"};
            }
            query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + StringUtils.join(value, ",") + ARMUtils.SINGLE_QUOTES);
            query = query.replace("@DEDCONDITION", selection.getRateDeductionLevelName());
            query = query.replace("@CONDITIONVALUE", selection.getRateDeductionValue().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
            query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
            query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
            query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
            query = query.replace("@FLAG_BIT", String.valueOf(NumericConstants.ONE));
            LOGGERFORRATESTAB.debug("query-->{}", query);
            list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        } catch (Exception e) {
            LOGGERFORRATESTAB.error("Error in getExcelResultList :", e);
        }
        return list;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add("RATE");
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_SALES));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_DISTRIBUTION_FEES_RATE));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_ADJUSTMENTS));
        return list;
    }

    @Override
    public boolean getCondition(AdjustmentDTO dto, Object propertyId, AbstractSelectionDTO selection) {
        return VariableConstants.PRODUCT_UPPER.equalsIgnoreCase(dto.getLevelName());
    }

    @Override
    public boolean updateOverride(List input) {
        try {
            QueryUtils.itemUpdate(input, "pipeline_common_query", "Txn7_rates_override_query");
        } catch (Exception e) {
            LOGGER.error("Error in updateOverride :", e);
            return false;
        }
        return true;
    }
       @Override
    public boolean updateOverrideLevelFilter(List input) {
        try {
            QueryUtils.itemUpdate(input, "pipeline_common_query_Level_Filter", "Txn7_rates_override_query_Level_Filter");
        } catch (Exception e) {
            LOGGER.error("Error in updateOverride :",e);
            return false;
        }
        return true;
    }
}
