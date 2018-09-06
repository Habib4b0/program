/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.RateLogic;
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

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class PipelineInventoryRatelogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends RateLogic<T, E> {

    /**
     * The Percent Three Decimal Places Format.
     */
    private static final DecimalFormat PER_THREEPIPELINE = new DecimalFormat("#,##0.000%");

    @Override
    public int getCount(Criteria criteria) {
        int count = (int) getRateQuery(criteria, true,
                0, 0);
        if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getRateslevelFilterNo() == 0)) {
            count = count + 1;
        }
        return count;
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {

        List result = (List) getRateQuery(criteria, false,
                criteria.getStart(), criteria.getOffset());
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
        double totalvalue = 0;
        for (int i = 0; i < result.size(); i++) {
            Object[] obj = (Object[]) result.get(i);
            if (lastValue == null || !lastValue.equalsIgnoreCase(String.valueOf(obj[1]))) {
                totalvalue = 0;
                dto = new AdjustmentDTO();
                resultList.add(dto);
                dto.setGroup(String.valueOf(obj[1]));
                dto.setLevelName(String.valueOf(obj[NumericConstants.FOUR]));
                switch (dto.getLevelName()) {
                    case VariableConstants.DEDUCTION_UPPERCASE:
                        dto.setDeductionSID((Integer) (obj[NumericConstants.THREE]));
                        break;

                    case VariableConstants.CUSTOMER_UPPERCASE:
                        if (ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                            dto.setCustomerSID((Integer) (obj[NumericConstants.THREE]));
                            dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        } else {
                            dto.setCustomerSID((Integer) (obj[NumericConstants.THREE]));
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        }

                        break;

                    case VariableConstants.CONTRACT_UPPERCASE:
                        if (ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                            dto.setContractSID((Integer) (obj[NumericConstants.THREE]));
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        } else {
                            dto.setContractSID((Integer) (obj[NumericConstants.THREE]));
                            dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        }
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
                LOGGER.debug("----dto.getLevelName()----{}", dto.getLevelName());
                dto.setChildrenAllowed(!VariableConstants.PRODUCT_UPPER.equalsIgnoreCase(dto.getLevelName()) && !"TOTAL".equalsIgnoreCase(dto.getGroup()) && (selection.getRateslevelFilterNo() == 0));
                dto.setLevelNo(selection.getLevelNo());
            }
            totalvalue += Double.valueOf(String.valueOf(obj[NumericConstants.TWO]));
            String totVal = getFormattedValue(PER_THREEPIPELINE, String.valueOf(totalvalue));
            dto.setTotalColumn(totVal);
            String value = getFormattedValue(PER_THREEPIPELINE, String.valueOf(obj[NumericConstants.TWO]));
            dto.addStringProperties(String.valueOf(obj[0]) + "." + columnList.indexOf(String.valueOf(obj[0])), value);
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
    protected Object getRateQuery(Criteria criteria, boolean isCount, int start, int offset) {
        AbstractSelectionDTO selection = (AbstractSelectionDTO) criteria.getSelectionDto();
        Object lastParent = criteria.getParent();
        List input = new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId()));
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (!isView) {
            input.add(selection.getSessionDTO().getUserId());
            input.add(selection.getSessionDTO().getSessionId());
        }
        String queryNameCustomer = isView ? CommonConstant.CUSTOMERCONTRACTVIEW : CommonConstant.CUSTOMERCONTRACTEDIT;
        String queryNameProduct = isView ? "customerproductview" : "customerproductedit";
        String queryName = ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) || ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView()) ? queryNameCustomer
                : queryNameProduct;
        LOGGER.debug("queryName => {}", queryName);
        if (isCount) {
            List count = null;
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                count = QueryUtils.getItemData(getQueryInput(parentDTO, selection, input, queryName), queryName, CommonConstant.CUSTOMERPRODUCTCOUNT);

            } else {
                if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) && !ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(), selection.getTableName(), "%", "%", selection.getRateDeductionValue())));
                    count = QueryUtils.getItemData(input, queryName, CommonConstant.CUSTOMERPRODUCTCOUNT);
                } else {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(), selection.getTableName(), "%", "%", "%", "%", selection.getRateDeductionValue())));
                    count = QueryUtils.getItemData(input, queryName, CommonConstant.CUSTOMERPRODUCTCOUNT);
                }

            }

            return count != null && count.get(0) != null ? count.get(0) : 0;
        } else {
            boolean totalFlag = false;
            if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && criteria.getSelectionDto().getSummarylevelFilterNo() == 0 && (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && selection.getRateslevelFilterNo() == 0) {
                totalFlag = true;
                offset = offset - 1;
            }

            int startindex = start + 1;
            int endindex = start + offset;
            List result;
            List<AdjustmentDTO> resultDTO = new ArrayList();
            if (offset != 0) {
                if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                    AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                    getQueryInput(parentDTO, selection, input, queryName);
                    input.addAll(new ArrayList<>(Arrays.asList(startindex, endindex)));
                    result = QueryUtils.getItemData(input, queryName, CommonConstant.CUSTOMERPRODUCTDATA);
                    resultDTO = customizeResultSet(result, selection, parentDTO);
                } else {
                    if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) && !ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(),
                                selection.getTableName(), "%", "%", selection.getRateDeductionValue(), startindex, endindex)));
                        result = QueryUtils.getItemData(input, queryName, CommonConstant.CUSTOMERPRODUCTDATA);
                    } else {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(),
                                selection.getTableName(), "%", "%", "%", "%", selection.getRateDeductionValue(), startindex, endindex)));
                        result = QueryUtils.getItemData(input, queryName, CommonConstant.CUSTOMERPRODUCTDATA);
                    }

                    resultDTO = customizeResultSet(result, selection, null);
                }
            }

            if (totalFlag) {
                input.clear();
                if (!isView) {
                    if (ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) || ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(),
                                selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),
                                selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(),
                                selection.getTableName(), "%", "%", "%", "%", selection.getRateDeductionValue())));
                    } else {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(),
                                selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),
                                selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(),
                                selection.getTableName(), "%", "%", selection.getRateDeductionValue())));
                    }
                } else {
                    if (ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) || ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(), selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(),
                                selection.getTableName(), "%", "%", "%", "%", selection.getRateDeductionValue())));
                    } else {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(), selection.getRateDeductionLevelName(), selection.getRateLevelName(), selection.getRateBasisName(), selection.getRatesOverrideFlag(),
                                selection.getTableName(), "%", "%", selection.getRateDeductionValue())));
                    }
                }
                String resultCustomer = isView ? CommonConstant.CUSTOMERCONTRACTVIEW : CommonConstant.CUSTOMERCONTRACTEDIT;
                String resultProduct = isView ? "customerproductview" : "customerproductedit";
                result = QueryUtils.getItemData(input, ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) || ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView()) ? resultCustomer
                        : resultProduct, "totalrate");
                resultDTO.addAll(customizeResultSet(result, selection, null));
            }
            return resultDTO;
        }

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
        switch (parentDTO.getLevelName()) {
            case VariableConstants.DEDUCTION_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE : VariableConstants.CUSTOMER_UPPERCASE, selection.getRateBasisName(), selection.getRatesOverrideFlag(), selection.getTableName())));
                input.add("%");
                input.add(parentDTO.getDeductionSID());
                input.addAll(new ArrayList<>(Arrays.asList("%", "%", selection.getRateDeductionValue())));
                break;

            case VariableConstants.CUSTOMER_UPPERCASE:

                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(),
                        ARMConstants.getDeductionCustomerContract().equals(selection.getRateDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE
                        : VariableConstants.BRAND_UPPERCASE, selection.getRateBasisName(), selection.getRatesOverrideFlag(), selection.getTableName())));
                input.add("%");
                if (CommonConstant.CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.CUSTOMERCONTRACTEDIT.equals(queryName)) {
                    input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                }
                input.add(parentDTO.getCustomerSID());
                if ((CommonConstant.CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.CUSTOMERCONTRACTEDIT.equals(queryName))) {
                    if (!ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                        input.add("%");
                    } else {
                        input.add(parentDTO.getContractSID());
                    }

                }

                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionValue())));
                break;

            case VariableConstants.CONTRACT_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView()) ? VariableConstants.CUSTOMER_UPPERCASE : VariableConstants.BRAND_UPPERCASE, selection.getRateBasisName(), selection.getRatesOverrideFlag(), selection.getTableName())));
                input.add("%");
                input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                if (ARMConstants.getDeductionContractCustomer().equals(selection.getRateDeductionView())) {
                    input.add("%");
                } else {
                    input.add(parentDTO.getCustomerSID());
                }

                input.add(parentDTO.getContractSID());
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionValue())));
                break;

            case VariableConstants.BRAND_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), VariableConstants.PRODUCT_UPPER, selection.getRateBasisName(), selection.getRatesOverrideFlag(), selection.getTableName())));
                input.add(parentDTO.getBrandSID());
                if (CommonConstant.CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.CUSTOMERCONTRACTEDIT.equals(queryName)) {
                    input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                }
                input.add(ARMConstants.getDeductionProduct().equals(selection.getRateDeductionView())
                        ? "%" : parentDTO.getCustomerSID());
                if (CommonConstant.CUSTOMERCONTRACTVIEW.equals(queryName) || CommonConstant.CUSTOMERCONTRACTEDIT.equals(queryName)) {
                    input.add(parentDTO.getContractSID());
                }
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionValue())));
                break;
            default:

        }
        return input;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {

        LOGGER.debug("selection.getRate_DeductionValue()========{}", selection.getRateDeductionValue());
        String query;
        String deductionValue = selection.getRateDeductionValue().startsWith(String.valueOf(ARMUtils.SINGLE_QUOTES)) ? selection.getRateDeductionValue() : ARMUtils.SINGLE_QUOTES + selection.getRateDeductionValue() + ARMUtils.SINGLE_QUOTES;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getExcelRatePipelineInventoryView");
        } else {
            query = SQlUtil.getQuery("getExcelRatePipelineInventory");
        }
        Object[] value = null;
        if (selection.getRateDeductionView().equals(ARMConstants.getDeductionCustomerContract()) && selection.getRateDeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionContractCustomer()) && selection.getRateDeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "C", "T", "B", "I"};
        } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionContractCustomer())) {
            value = new Object[]{"C", "T", "B", "I"};
        } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "B", "I"};
        } else if (selection.getRateDeductionView().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        }
        query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + StringUtils.join(value, ",") + ARMUtils.SINGLE_QUOTES);
        query = query.replace("@DEDCONDITION", selection.getRateDeductionLevelName());
        query = query.replace("@CONDITIONVALUE", deductionValue.replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        query = query.replace("@RATEBASIS", selection.getRateBasisName());
        query = query.replace("@FLAG_BIT", String.valueOf(selection.getRatesOverrideFlag()));
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add("RATE");
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY));
        list.add(sessionDTO.getCurrentTableNames().get(CommonConstant.ST_ARM_INVENTORY_RATE));
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
            QueryUtils.itemUpdate(input, "pipeline_common_query", "Txn3_rates_override_query");
        } catch (Exception e) {
            LOGGER.error("Error in updateOverride :", e);
            return false;
        }
        return true;
    }
}
