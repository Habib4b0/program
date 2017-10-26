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
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.utils.ARMUtils;
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
    private static final DecimalFormat PER_THREE = new DecimalFormat("#,##0.000%");
    
    @Override
    public int getCount(Criteria criteria) {
        int count = (int) getRateQuery(criteria, true,
                0, 0);
        if ((count > 0 && (criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO)))) && (criteria.getSelectionDto().getRates_levelFilterNo() == 0)) {
                count = count + 1;
        }
        return count;
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {

        List result = (List) getRateQuery(criteria, false,
                criteria.getStart(), criteria.getOffset());
        OriginalDataResult<T> dataResult = new OriginalDataResult<T>();
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
    protected List<AdjustmentDTO> customizeResultSet(List result, AbstractSelectionDTO selection, AdjustmentDTO lastParent) {

        List<AdjustmentDTO> resultList = new ArrayList<AdjustmentDTO>();
        List columnList = ARMConstants.getDeduction().equalsIgnoreCase(selection.getRate_DeductionLevelName())
                ? selection.getRate_ColumnList().get(NumericConstants.THREE) : selection.getRate_ColumnList().get(1);
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
                        dto.setDeductionSID(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                        break;

                    case VariableConstants.CUSTOMER_UPPERCASE:
                        dto.setCustomerSID(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;

                    case VariableConstants.CONTRACT_UPPERCASE:
                        dto.setContractSID(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;

                    case VariableConstants.BRAND_UPPERCASE:
                        dto.setBrandSID(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                        dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;
                }
                LOGGER.debug("----dto.getLevelName()----" + dto.getLevelName());
                    dto.setChildrenAllowed(!"PRODUCT".equalsIgnoreCase(dto.getLevelName()) && !"TOTAL".equalsIgnoreCase(dto.getGroup()) && (selection.getRates_levelFilterNo() == 0));
                dto.setLevelNo(selection.getLevelNo());
            }
            totalvalue += Double.valueOf(String.valueOf(obj[NumericConstants.TWO]));
            String totVal = getFormattedValue(PER_THREE, String.valueOf(totalvalue));
            dto.setTotalColumn(totVal);
            String value = getFormattedValue(PER_THREE, String.valueOf(obj[NumericConstants.TWO]));
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
    protected Object getRateQuery(Criteria criteria, boolean isCount, int start, int offset){
            AbstractSelectionDTO selection=(AbstractSelectionDTO)criteria.getSelectionDto();
            Object lastParent=criteria.getParent();
        LOGGER.debug("------start----" + start+"------offset----" + offset);

        List input = new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId()));
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (!isView) {
            input.add(selection.getSessionDTO().getUserId());
            input.add(selection.getSessionDTO().getSessionId());
        }
        String queryName = ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView()) ? isView ? "customercontractview" : "customercontractedit"
                : isView ? "customerproductview" : "customerproductedit";
        LOGGER.debug("queryName => " + queryName);
        if (isCount) {
            List count = null;
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                count = QueryUtils.getItemData(getQueryInput(parentDTO, selection, input, queryName), queryName, "customerproductcount");

            } else {
                if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView())) {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), selection.getRate_LevelName(), selection.getRate_BasisName() , selection.getTableName(), "%", "%", selection.getRate_DeductionValue())));
                    count = QueryUtils.getItemData(input, queryName, "customerproductcount");
                } else {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), selection.getRate_LevelName(), selection.getRate_BasisName() ,selection.getTableName(), "%", "%", "%", "%", selection.getRate_DeductionValue())));
                    count = QueryUtils.getItemData(input, queryName, "customerproductcount");
                }

            }

            return count != null && count.get(0) != null ? count.get(0) : 0;
        } else {
            boolean totalFlag = false;
            if (((criteria.getParent() == null || (!(criteria.getParent() instanceof AdjustmentDTO))) && (criteria.getCurrentPage() == criteria.getLastPage()) && criteria.getSelectionDto().getSummary_levelFilterNo() == 0&& (criteria.getSiblingCount() == (criteria.getStart() + criteria.getOffset()))) && selection.getRates_levelFilterNo() == 0) {
                    totalFlag = true;
                    offset = offset - 1;
            }

            int startindex = start + 1;
            int endindex = start + offset;
            List result = new ArrayList();
            List<AdjustmentDTO> resultDTO = new ArrayList();
            if (offset != 0) {
                if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                    AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                    getQueryInput(parentDTO, selection, input, queryName);
                    input.addAll(new ArrayList<>(Arrays.asList(startindex, endindex)));
                    result = QueryUtils.getItemData(input, queryName, "customerproductdata");
                    resultDTO = customizeResultSet(result, selection, parentDTO);
                } else {
                    if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView())) {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), selection.getRate_LevelName(),selection.getRate_BasisName() ,
                                selection.getTableName(), "%", "%", selection.getRate_DeductionValue(), startindex, endindex)));
                        result = QueryUtils.getItemData(input, queryName, "customerproductdata");
                    } else {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), selection.getRate_LevelName(),selection.getRate_BasisName() ,
                                selection.getTableName(), "%", "%", "%", "%", selection.getRate_DeductionValue(), startindex, endindex)));
                        result = QueryUtils.getItemData(input, queryName, "customerproductdata");
                    }

                    resultDTO = customizeResultSet(result, selection, null);
                }
            }

            if (totalFlag) {
                input.clear();
                if (!isView) {
                    if (ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView())) {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(),
                                selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),
                                selection.getRate_DeductionLevelName(), selection.getRate_LevelName(),selection.getRate_BasisName() ,
                                selection.getTableName(), "%", "%", "%", "%", selection.getRate_DeductionValue())));
                    } else {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(),
                                selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),
                                selection.getRate_DeductionLevelName(), selection.getRate_LevelName(),selection.getRate_BasisName() ,
                                selection.getTableName(), "%", "%", selection.getRate_DeductionValue())));
                    }
                } else {
                    if (ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView())) {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(), selection.getRate_DeductionLevelName(), selection.getRate_LevelName(),selection.getRate_BasisName() ,
                                selection.getTableName(), "%", "%", "%", "%", selection.getRate_DeductionValue())));
                    } else {
                        input.addAll(new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(), selection.getRate_DeductionLevelName(), selection.getRate_LevelName(),selection.getRate_BasisName() ,
                                selection.getTableName(), "%", "%", selection.getRate_DeductionValue())));
                    }
                }
                result = QueryUtils.getItemData(input, ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView()) ? isView ? "customercontractview" : "customercontractedit"
                        : isView ? "customerproductview" : "customerproductedit", "totalrate");
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
    protected List getQueryInput(AdjustmentDTO parentDTO, AbstractSelectionDTO selection, List input, String queryName) {
        switch (parentDTO.getLevelName()) {
            case VariableConstants.DEDUCTION_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), VariableConstants.CUSTOMER_UPPERCASE, selection.getRate_BasisName() , selection.getTableName())));
                input.add("%");
                input.add(parentDTO.getDeductionSID());
                input.addAll(new ArrayList<>(Arrays.asList("%", "%", selection.getRate_DeductionValue())));
                break;

            case VariableConstants.CUSTOMER_UPPERCASE:

                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(),
                        ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView()) ? VariableConstants.CONTRACT_UPPERCASE
                        : VariableConstants.BRAND_UPPERCASE, selection.getRate_BasisName() , selection.getTableName())));
                input.add("%");
                  if ("customercontractview".equals(queryName) || "customercontractedit".equals(queryName)) {
                    input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                }
                input.add(parentDTO.getCustomerSID());
                 if ("customercontractview".equals(queryName) || "customercontractedit".equals(queryName)) {
                    input.add("%");
                }
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionValue())));
                break;

            case VariableConstants.CONTRACT_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), VariableConstants.BRAND_UPPERCASE, selection.getRate_BasisName() , selection.getTableName())));
                input.add("%");
                input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                input.add(parentDTO.getCustomerSID());
                input.add(parentDTO.getContractSID());
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionValue())));
                break;

            case VariableConstants.BRAND_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), "PRODUCT", selection.getRate_BasisName() , selection.getTableName())));
                input.add(parentDTO.getBrandSID());
                  if ("customercontractview".equals(queryName) || "customercontractedit".equals(queryName)) {
                    input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                }
                input.add(ARMConstants.getDeductionProduct().equals(selection.getRate_DeductionView())
                        ? "%" : parentDTO.getCustomerSID());
                  if ("customercontractview".equals(queryName) || "customercontractedit".equals(queryName)) {
                    input.add(parentDTO.getContractSID());
                }
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionValue())));
                break;

        }
        return input;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {

        LOGGER.debug("selection.getRate_DeductionValue()========" + selection.getRate_DeductionValue());
        String query = StringUtils.EMPTY;
        String deductionValue = selection.getRate_DeductionValue().startsWith("'") ? selection.getRate_DeductionValue() : "'" + selection.getRate_DeductionValue() + "'";
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getExcelRatePipelineInventoryView");
        } else {
            query = SQlUtil.getQuery("getExcelRatePipelineInventory");
        }
        Object[] value = null;
        if (selection.getRate_DeductionView().equals(ARMConstants.getDeductionCustomerContract()) && selection.getRate_DeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else if (selection.getRate_DeductionView().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (selection.getRate_DeductionView().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "B", "I"};
        } else if (selection.getRate_DeductionView().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        }
        query = query.replace("@LEVEL_VAL", "'" + StringUtils.join(value, ",") + "'");
        query = query.replace("@DEDCONDITION", selection.getRate_DeductionLevelName());
        query = query.replace("@CONDITIONVALUE", deductionValue.replace("'", "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        query = query.replace("@RATEBASIS",  selection.getRate_BasisName());
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }
}
