/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
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
public class PipelineAccrualRateLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends RateLogic<T,E> {

    @Override
    public int getCount(Criteria criteria) {
        int count = (int) getRateQuery(criteria, true, 0, 0);
        return count;
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        List result = (List) getRateQuery(criteria, false, criteria.getStart(), criteria.getOffset());
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
                dto.setChildrenAllowed((!"PRODUCT".equalsIgnoreCase(dto.getLevelName())) && (selection.getRates_levelFilterNo() == 0));
                dto.setLevelNo(selection.getLevelNo());
            }

            dto.addStringProperties(String.valueOf(obj[0]) + "." + columnList.indexOf(String.valueOf(obj[0])), decimalformat.format(Double.valueOf(String.valueOf(obj[NumericConstants.TWO]))) + "%");
            lastValue = String.valueOf(obj[1]);
        }

        return resultList;
    }

    /**
     *
     * @param isCount
     * @return
     */
    protected Object getRateQuery(Criteria criteria, boolean isCount, int start, int offset){
            AbstractSelectionDTO selection=(AbstractSelectionDTO)criteria.getSelectionDto();
            Object lastParent=criteria.getParent();
        int startindex = start + 1;
        int endindex = start + offset;
        List input = new ArrayList<>();
        input.add(selection.getDataSelectionDTO().getProjectionId());
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (!isView) {
            input.add(selection.getSessionDTO().getUserId());
            input.add(selection.getSessionDTO().getSessionId());
        }
        String queryName = ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView()) ? isView ? "customercontractview" : "customercontractedit"
                : isView ? "customerproductview" : "customerproductedit";

        if (isCount) {
            List count = null;
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                count = QueryUtils.getItemData(getQueryInput(parentDTO, selection, input, queryName), queryName, "customerproductcount");
            } else if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView())) {
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), selection.getRate_LevelName(), selection.getRate_BasisName() , selection.getTableName(), "%", "%", selection.getRate_DeductionValue())));
                count = QueryUtils.getItemData(input, queryName, "customerproductcount");
            } else {
                String level = "Deduction".equals(selection.getRate_DeductionLevelName()) ? VariableConstants.DEDUCTION_UPPERCASE : VariableConstants.CUSTOMER_UPPERCASE;  // Added for GAL-7944
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), level, selection.getRate_BasisName() , selection.getTableName() , "%", "%", "%", "%", selection.getRate_DeductionValue())));
                count = QueryUtils.getItemData(input, queryName, "customerproductcount");
            }
            return count != null && !count.isEmpty() && count.get(0) != null ? count.get(0) : 0;
        } else {
            List result = new ArrayList();
            List<AdjustmentDTO> resultDTO = new ArrayList();
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                getQueryInput(parentDTO, selection, input, queryName);
                input.addAll(new ArrayList<>(Arrays.asList(startindex, endindex)));
                result = QueryUtils.getItemData(input, queryName, "customerproductdata");
                resultDTO = customizeResultSet(result, selection, parentDTO);
            } else {
                if (!ARMConstants.getDeductionCustomerContract().equals(selection.getRate_DeductionView())) {
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), selection.getRate_LevelName(), selection.getRate_BasisName() ,
                            selection.getTableName(), "%", "%", selection.getRate_DeductionValue(), startindex, endindex)));
                    result = QueryUtils.getItemData(input, queryName, "customerproductdata");
                } else {
                    String level = "Deduction".equals(selection.getRate_DeductionLevelName()) ? VariableConstants.DEDUCTION_UPPERCASE : VariableConstants.CUSTOMER_UPPERCASE;  // 	Added for GAL-7944
                    input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), level, selection.getRate_BasisName() ,
                            selection.getTableName(), "%", "%", "%", "%", selection.getRate_DeductionValue(), startindex, endindex)));
                    result = QueryUtils.getItemData(input, queryName, "customerproductdata");
                }

                resultDTO = customizeResultSet(result, selection, null);
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
        LOGGER.debug("parentDTO.getLevelName()====="+parentDTO.getLevelName());
        LOGGER.debug("selection.getRate_Basis()"+selection.getRate_Basis());
        switch (parentDTO.getLevelName()) {
            case VariableConstants.DEDUCTION_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), VariableConstants.CUSTOMER_UPPERCASE, selection.getRate_BasisName() , selection.getTableName())));
                input.add("%");
                input.add(parentDTO.getDeductionSID()== 0 ? "%" : parentDTO.getDeductionSID());
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
                input.add(parentDTO.getCustomerSID() == 0 ? "%" : parentDTO.getCustomerSID());
                if ("customercontractview".equals(queryName) || "customercontractedit".equals(queryName)) {
                    input.add("%");
                }
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionValue())));
                break;

            case VariableConstants.CONTRACT_UPPERCASE:
                  input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), VariableConstants.BRAND_UPPERCASE, selection.getRate_BasisName() , selection.getTableName())));
                input.add("%");
                input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                input.add(parentDTO.getCustomerSID() == 0 ? "%" : parentDTO.getCustomerSID());
                input.add(parentDTO.getContractSID() == 0 ? "%" : parentDTO.getContractSID());
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionValue())));
                break;

            case VariableConstants.BRAND_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRate_DeductionLevelName(), "PRODUCT", selection.getRate_BasisName() , selection.getTableName())));
                input.add(parentDTO.getBrandSID() == 0 ? "%" : parentDTO.getBrandSID());
                if ("customercontractview".equals(queryName) || "customercontractedit".equals(queryName)) {
                    input.add(parentDTO.getDeductionSID() == 0 ? "%" : parentDTO.getDeductionSID());
                }
                input.add(ARMConstants.getDeductionProduct().equals(selection.getRate_DeductionView()) ? "%" : parentDTO.getCustomerSID()== 0 ? "%" : parentDTO.getCustomerSID());
                if ("customercontractview".equals(queryName) || "customercontractedit".equals(queryName)) {
                    input.add(parentDTO.getContractSID() == 0 ? "%" : parentDTO.getContractSID());
                }
                input.addAll(new ArrayList<>(Arrays.asList(getDeductionList(selection.getRate_DeductionValue()))));
                break;

        }
        return input;
    }
    /**
     *  Method to return string with '' if its not present
     * @param deductionValue
     * @return String
     */
    private String getDeductionList(String deductionValue){
        List<String> listSize = new ArrayList<>(Arrays.asList(deductionValue));
        String value = listSize.get(0);
        if (!listSize.isEmpty() && !listSize.get(0).contains("'")) {
            value = "'" + value.replace(",", "','") + "'";
        }
        return value;
    }
    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query = StringUtils.EMPTY;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getExcelRatePipelineAccrualView");
        } else {
            query = SQlUtil.getQuery("getExcelRatePipelineAccrual");
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
        query = query.replace("@CONDITIONVALUE", selection.getRate_DeductionValue().replace("'", "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        query = query.replace("@RATEBASIC", String.valueOf(selection.getRate_BasisName())); 
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }
}
