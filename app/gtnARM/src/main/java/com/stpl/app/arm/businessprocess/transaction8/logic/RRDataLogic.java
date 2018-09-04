/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
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
import com.stpl.ifs.util.QueryUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author @param <T>
 * @param <E>
 */
public class RRDataLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T, E> {

    @Override
    public int getCount(Criteria criteria) {
        return (int) getReturnReserveDataQuery((AbstractSelectionDTO) criteria.getSelectionDto(), criteria.getParent(), true, 0, 0, 0, 0);
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        List result = (List) getReturnReserveDataQuery((AbstractSelectionDTO) criteria.getSelectionDto(), criteria.getParent(), false, criteria.getStart(), criteria.getOffset(), 0, 0);
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(result);
        return dataResult;
    }

    protected Object getReturnReserveDataQuery(AbstractSelectionDTO selection, Object lastParent, boolean isCount, int start, int offset, int currentPage, int lastPage) {
        LOGGER.debug("currentPage {}", (currentPage + " " + lastPage));
        List<AdjustmentDTO> resultDTO;
        int startindex = start + 1;
        int endindex = start + offset;
        List input = new ArrayList<>();
        input.add(selection.getDataSelectionDTO().getProjectionId());
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        String tableName = isView ? "ARM_RETURN_RESERVE" : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_RETURN_RESERVE);
        String queryName = "tx8ReturnReserveDataMain";
        if (isCount) {
            List count;
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                count = QueryUtils.getItemData(getQueryInput(parentDTO, selection, input, tableName), queryName, "tx8ReturnReserveDataCount");

            } else {
                input.addAll(new ArrayList<>(new ArrayList<>(Arrays.asList(selection.getReturnReserveDataLevelName(),
                        tableName, "%", "%", "%", "%", selection.getReturnReserveDeductionValue()))));
                count = QueryUtils.getItemData(input, queryName, "tx8ReturnReserveDataCount");
            }
            return count != null && !count.isEmpty() && count.get(0) != null ? count.get(0) : 0;
        } else {
            List result;
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                getQueryInput(parentDTO, selection, input, tableName);
                input.addAll(new ArrayList<>(Arrays.asList(startindex, endindex)));
                result = QueryUtils.getItemData(input, queryName, "tx8ReturnReserveDataDataQuery");
                resultDTO = customizeResultSet(result, selection, parentDTO);
            } else {
                input.addAll(new ArrayList<>(Arrays.asList(selection.getReturnReserveDataLevelName(), tableName, "%", "%", "%", "%", selection.getReturnReserveDeductionValue(), startindex, endindex)));
                result = QueryUtils.getItemData(input, queryName, "tx8ReturnReserveDataDataQuery");
                resultDTO = customizeResultSet(result, selection, null);

            }
        }
        return resultDTO;
    }

    protected List<AdjustmentDTO> customizeResultSet(List result, AbstractSelectionDTO selection, AdjustmentDTO lastParent) {
        List<AdjustmentDTO> resultList = new ArrayList<>();
        String[] variables = ARMUtils.getReturnReserveDataColumns();
        String lastValue = null;
        AdjustmentDTO dto = new AdjustmentDTO();
        for (int i = 0; i < result.size(); i++) {
            Object[] obj = (Object[]) result.get(i);
            if (lastValue == null || !lastValue.equalsIgnoreCase(String.valueOf(obj[1]))) {
                dto = new AdjustmentDTO();
                resultList.add(dto);
                dto.setGroup(String.valueOf(obj[1]));
                dto.setLevelName(String.valueOf(obj[NumericConstants.NINE]));
                switch (dto.getLevelName()) {
                    case VariableConstants.DEDUCTION_UPPERCASE:
                        dto.setDeductionSID((Integer)(obj[NumericConstants.EIGHT]));
                        dto.setDeductionName(String.valueOf(obj[NumericConstants.ZERO]));
                        break;

                    case VariableConstants.CUSTOMER_UPPERCASE:
                        if (ARMUtils.CUSTOMER_CONTRACT.equals(selection.getReturnReserveDataDeductionView())) {
                            dto.setCustomerSID((Integer)(obj[NumericConstants.EIGHT]));
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        } else {
                            dto.setCustomerSID((Integer)(obj[NumericConstants.EIGHT]));
                            dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        }
                        break;

                    case VariableConstants.CONTRACT_UPPERCASE:
                        if (ARMUtils.CUSTOMER_CONTRACT.equals(selection.getReturnReserveDataDeductionView())) {
                            dto.setContractSID((Integer)(obj[NumericConstants.EIGHT]));
                            dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        } else {
                            dto.setContractSID((Integer)(obj[NumericConstants.EIGHT]));
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        }
                        break;

                    case VariableConstants.BRAND_UPPERCASE:
                        dto.setBrandSID((Integer)(obj[NumericConstants.EIGHT]));
                        dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        break;
                    default:
                }
                LOGGER.debug("----dto.getLevelName()----{}", dto.getLevelName());
                dto.setChildrenAllowed(!VariableConstants.PRODUCT_RETURNS.equalsIgnoreCase(dto.getLevelName()) && !"TOTAL".equalsIgnoreCase(dto.getGroup()) && (selection.getReturnReserveDatalevelFilterNo() == 0));
                dto.setLevelNo(selection.getLevelNo());
            }
            if (VariableConstants.PRODUCT_RETURNS.equalsIgnoreCase(dto.getLevelName())) {
                dto.addStringProperties(variables[NumericConstants.ZERO], String.valueOf(obj[NumericConstants.TWO]));
                dto.addStringProperties(variables[NumericConstants.ONE], String.valueOf(getFormattedValue(NUM_ZERO, String.valueOf(obj[NumericConstants.THREE]))));
                dto.addStringProperties(variables[NumericConstants.TWO], String.valueOf(getFormattedValue(NUM_ZERO, String.valueOf(obj[NumericConstants.FOUR]))));
                dto.addStringProperties(variables[NumericConstants.THREE], String.valueOf(getFormattedValue(NUM_ZERO, String.valueOf(obj[NumericConstants.FIVE]))));
                dto.addStringProperties(variables[NumericConstants.FOUR], String.valueOf(getFormattedValue(CUR_TWO, String.valueOf(obj[NumericConstants.SIX]))));
                dto.addStringProperties(variables[NumericConstants.FIVE], String.valueOf(getFormattedValue(CUR_TWO, String.valueOf(obj[NumericConstants.SEVEN]))));
                lastValue = String.valueOf(obj[1]);
            }
        }
        return resultList;
    }

    protected List getQueryInput(AdjustmentDTO parentDTO, AbstractSelectionDTO selection, List input, String tableName) {
        switch (parentDTO.getLevelName()) {
            case VariableConstants.DEDUCTION_UPPERCASE:
                String value = ARMUtils.CUSTOMER_CONTRACT.equals(selection.getReturnReserveDataDeductionView())
                        ? VariableConstants.CUSTOMER_UPPERCASE : VariableConstants.CONTRACT_UPPERCASE;
                input.addAll(new ArrayList<>(Arrays.asList(value, tableName)));
                input.addAll(new ArrayList<>(Arrays.asList("%", "%", "%", "%", "'" + parentDTO.getDeductionName() + "'")));
                break;

            case VariableConstants.CUSTOMER_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(ARMUtils.CUSTOMER_CONTRACT.equals(selection.getReturnReserveDataDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE
                        : VariableConstants.BRAND_UPPERCASE, tableName)));
                input.add("%");
                input.add(parentDTO.getDeductionSID());
                input.add(parentDTO.getCustomerSID());
                input.add(ARMUtils.CUSTOMER_CONTRACT.equals(selection.getReturnReserveDataDeductionView()) ? "%" : parentDTO.getContractSID());
                input.add("'" + parentDTO.getDeductionName() + "'");
                break;

            case VariableConstants.CONTRACT_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(ARMUtils.CONTRACT_CUSTOMER.equals(selection.getReturnReserveDataDeductionView())
                        ? VariableConstants.CUSTOMER_UPPERCASE : VariableConstants.BRAND_UPPERCASE, tableName)));
                input.add("%");
                input.add(parentDTO.getDeductionSID());
                input.add(ARMUtils.CONTRACT_CUSTOMER.equals(selection.getReturnReserveDataDeductionView()) ? "%" : parentDTO.getCustomerSID());
                input.add(parentDTO.getContractSID());
                input.add("'" + parentDTO.getDeductionName() + "'");
                break;
            case VariableConstants.BRAND_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(VariableConstants.PRODUCT_RETURNS, tableName)));
                input.add(parentDTO.getBrandSID());
                input.add(parentDTO.getDeductionSID());
                input.add(parentDTO.getCustomerSID());
                input.add(parentDTO.getContractSID());
                input.add("'" + parentDTO.getDeductionName() + "'");
                break;
            default:
        }
        return input;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        String query = SQlUtil.getQuery("getExcelReturnReserveDataTx8");
        Object[] value = null;
        if (selection.getRateDeductionView().equals(ARMUtils.CUSTOMER_CONTRACT)) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else {
            value = new Object[]{"D", "C", "T", "B", "I"};
        }
        query = query.replace("@LEVEL_VAL", "'" + StringUtils.join(value, ",") + "'");
        query = query.replace("@DEDCONDITION", selection.getRateDeductionLevelName());
        query = query.replace("@CONDITIONVALUE", selection.getRateDeductionValue().replace("'", "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        query = query.replace("@RATEBASIC", String.valueOf(selection.getRateBasisName()));
        query = query.replace("@TABLE_NAME", isView ? CommonConstant.ARM_RETURN_RESERVE : CommonConstant.ST_ARM_RETURN_RESERVE);
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }

}
