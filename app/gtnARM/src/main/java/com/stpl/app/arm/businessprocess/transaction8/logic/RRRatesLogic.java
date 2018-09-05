/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
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
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author @param <T>
 * @param <E>
 */
public class RRRatesLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T, E> {

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

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        String query = SQlUtil.getQuery("getExcelRateTx8");
        Object[] value = selection.getExcelHierarchy();
        query = query.replace("@LEVEL_VAL", ARMUtils.SINGLE_QUOTES + StringUtils.join(value, ",") + ARMUtils.SINGLE_QUOTES);
        query = query.replace("@DEDCONDITION", selection.getRateDeductionLevelName());
        query = query.replace("@CONDITIONVALUE", selection.getRateDeductionValue().replace(String.valueOf(ARMUtils.SINGLE_QUOTES), "''"));
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        query = query.replace("@RATEBASIC", String.valueOf(selection.getRateBasisName()));
        query = query.replace("@TABLE_NAME", isView ? CommonConstant.ARM_RETURN_RATE : CommonConstant.ST_ARM_RETURN_RATE);
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }

    protected Object getRateQuery(AbstractSelectionDTO selection, Object lastParent, boolean isCount, int start, int offset, int currentPage, int lastPage) {
        LOGGER.debug("currentPage {} ", currentPage + " " + lastPage);
        List<AdjustmentDTO> resultDTO;
        int startindex = start + 1;
        int endindex = start + offset;
        List input = new ArrayList<>();
        input.add(selection.getDataSelectionDTO().getProjectionId());
        String queryName = "tx8dedProdMain";
        if (isCount) {
            List count;
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                count = QueryUtils.getItemData(getQueryInput(parentDTO, selection, input), queryName, "tx8dedProdCount");

            } else {
                input.addAll(new ArrayList<>(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(),
                        selection.getRateLevelName(),
                        selection.getTableName(), "%", "%", "%", ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()), "%", selection.getRateDeductionValue()))));
                count = QueryUtils.getItemData(input, queryName, "tx8dedProdCount");
            }
            return count != null && !count.isEmpty() && count.get(0) != null ? count.get(0) : 0;
        } else {
            List result;
            if (lastParent != null && (lastParent instanceof AdjustmentDTO)) {
                AdjustmentDTO parentDTO = (AdjustmentDTO) lastParent;
                getQueryInput(parentDTO, selection, input);
                input.addAll(new ArrayList<>(Arrays.asList(startindex, endindex)));
                result = QueryUtils.getItemData(input, queryName, "tx8dedProdData");
                resultDTO = customizeResultSet(result, selection, parentDTO);
            } else {
                input.addAll(new ArrayList<>(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(),
                        selection.getRateLevelName(),
                        selection.getTableName(), "%", "%", "%", ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()), "%", selection.getRateDeductionValue(), startindex, endindex))));
                result = QueryUtils.getItemData(input, queryName, "tx8dedProdData");
                resultDTO = customizeResultSet(result, selection, null);

            }
        }
        return resultDTO;
    }

    protected List<AdjustmentDTO> customizeResultSet(List result, AbstractSelectionDTO selection, AdjustmentDTO lastParent) {
        List<AdjustmentDTO> resultList = new ArrayList<>();
        String lastValue = null;
        AdjustmentDTO dto = new AdjustmentDTO();
        for (int i = 0; i < result.size(); i++) {
            Object[] obj = (Object[]) result.get(i);
            if (lastValue == null || !lastValue.equalsIgnoreCase(String.valueOf(obj[1]))) {
                dto = new AdjustmentDTO();
                dto.setGroup(String.valueOf(obj[1]));
                dto.setLevelName(String.valueOf(obj[NumericConstants.SIX]));
                switch (dto.getLevelName()) {
                    case VariableConstants.DEDUCTION_UPPERCASE:
                        dto.setDeductionName(String.valueOf(obj[0]));
                        dto.setDeductionSID((Integer)(obj[NumericConstants.FIVE]));
                        break;

                    case VariableConstants.CUSTOMER_UPPERCASE:
                        if (ARMConstants.getDeductionCustomer().equals(selection.getRateDeductionView())) {
                            dto.setCustomerSID((Integer)(obj[NumericConstants.FIVE]));
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        } else {
                            dto.setCustomerSID((Integer)(obj[NumericConstants.FIVE]));
                            dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        }
                        break;

                    case VariableConstants.CONTRACT_UPPERCASE:
                        if (ARMConstants.getDeductionCustomer().equals(selection.getRateDeductionView())) {
                            dto.setContractSID((Integer)(obj[NumericConstants.FIVE]));
                            dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        } else {
                            dto.setContractSID((Integer)(obj[NumericConstants.FIVE]));
                            dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                            dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        }
                        break;

                    case VariableConstants.BRAND_UPPERCASE:
                        dto.setBrandSID((Integer)(obj[NumericConstants.FIVE]));
                        dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;
                    case VariableConstants.PRODUCT_UPPER:
                        dto.setBranditemmasterSid(String.valueOf(obj[NumericConstants.FIVE]));
                        dto.setBrandSID(lastParent != null && lastParent.getBrandSID() != 0 ? lastParent.getBrandSID() : 0);
                        dto.setContractSID(lastParent != null && lastParent.getContractSID() != null ? lastParent.getContractSID() : 0);
                        dto.setCustomerSID(lastParent != null && lastParent.getCustomerSID() != null ? lastParent.getCustomerSID() : 0);
                        dto.setDeductionName(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionName() : StringUtils.EMPTY);
                        dto.setDeductionSID(lastParent != null && lastParent.getDeductionSID() != null ? lastParent.getDeductionSID() : 0);
                        break;
                    default:
                }

                LOGGER.debug("----dto.getLevelName()----{}", dto.getLevelName());
                dto.setChildrenAllowed(!VariableConstants.PRODUCT_RETURNS.equalsIgnoreCase(dto.getLevelName()) && !"TOTAL".equalsIgnoreCase(dto.getGroup()) && (selection.getRateslevelFilterNo() == 0));
                dto.setLevelNo(selection.getLevelNo());
            }
            if (VariableConstants.PRODUCT_RETURNS.equalsIgnoreCase(dto.getLevelName())) {
                String value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.TWO]));
                dto.addStringProperties("rate.1", value);
                String value1 = obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : StringUtils.EMPTY;
                dto.addStringProperties("methodology.0", value1);
                String value2 = obj[NumericConstants.FOUR] != null ? getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.FOUR])) : StringUtils.EMPTY;
                dto.addStringProperties("override.2", value2);
                lastValue = String.valueOf(obj[1]);
            }
            resultList.add(dto);
        }
        return resultList;
    }

    protected List getQueryInput(AdjustmentDTO parentDTO, AbstractSelectionDTO selection, List input) {
        switch (parentDTO.getLevelName()) {
            case VariableConstants.DEDUCTION_UPPERCASE:
                String customerValue = ARMConstants.getDeductionCustomer().equals(selection.getRateDeductionView())
                        ? VariableConstants.CUSTOMER_UPPERCASE : VariableConstants.CONTRACT_UPPERCASE;
                String value = ARMConstants.getDeductionProduct().equals(selection.getRateDeductionView())
                        ? VariableConstants.BRAND_UPPERCASE : customerValue;
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), value, selection.getTableName())));
                input.addAll(new ArrayList<>(Arrays.asList("%", "%", "%", ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()), parentDTO.getDeductionSID(), ARMUtils.SINGLE_QUOTES + parentDTO.getDeductionName() + ARMUtils.SINGLE_QUOTES)));
                break;

            case VariableConstants.CUSTOMER_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(),
                        ARMConstants.getDeductionCustomer().equals(selection.getRateDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE
                        : VariableConstants.BRAND_UPPERCASE, selection.getTableName())));
                input.add("%");
                input.add(parentDTO.getCustomerSID());
                input.add(ARMConstants.getDeductionCustomer().equals(selection.getRateDeductionView()) ? "%" : parentDTO.getContractSID());
                input.add(ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()));
                input.add(parentDTO.getDeductionSID());
                input.addAll(new ArrayList<>(Arrays.asList(ARMUtils.SINGLE_QUOTES + parentDTO.getDeductionName() + ARMUtils.SINGLE_QUOTES)));
                break;

            case VariableConstants.CONTRACT_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), ARMConstants.getDeductionContract().equals(selection.getRateDeductionView())
                        ? VariableConstants.CUSTOMER_UPPERCASE : VariableConstants.BRAND_UPPERCASE, selection.getTableName())));
                input.add("%");
                input.add(ARMConstants.getDeductionContract().equals(selection.getRateDeductionView()) ? "%" : parentDTO.getCustomerSID());
                input.add(parentDTO.getContractSID());
                input.add(ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()));
                input.add(parentDTO.getDeductionSID());
                input.addAll(new ArrayList<>(Arrays.asList(ARMUtils.SINGLE_QUOTES + parentDTO.getDeductionName() + ARMUtils.SINGLE_QUOTES)));
                break;
            case VariableConstants.BRAND_UPPERCASE:
                input.addAll(new ArrayList<>(Arrays.asList(selection.getRateDeductionLevelName(), VariableConstants.PRODUCT_RETURNS, selection.getTableName())));
                input.add(parentDTO.getBrandSID());
                input.add(ARMConstants.getDeductionProduct().equals(selection.getRateDeductionView())
                        ? "%" : parentDTO.getCustomerSID());
                input.add(ARMConstants.getDeductionProduct().equals(selection.getRateDeductionView())
                        ? "%" : parentDTO.getContractSID());
                input.add(ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()));
                input.add(parentDTO.getDeductionSID());

                input.addAll(new ArrayList<>(Arrays.asList(ARMUtils.SINGLE_QUOTES + parentDTO.getDeductionName() + ARMUtils.SINGLE_QUOTES)));
                break;
            default:
        }
        return input;
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        List list = new ArrayList<>();
        list.add("OVERRIDE_RATE");
        list.add(sessionDTO.getCurrentTableNames().get("ST_ARM_RETURN_RATE"));
        return list;
    }

    @Override
    public boolean updateOverride(List input) {
        try {
            int inputsize = input.size();
            input.add(inputsize - 1, input.get(inputsize - 1).toString().contains("UDC") ? "JOIN UDCS U ON U.MASTER_SID = "
                    + "R.RS_CONTRACT_SID AND U.MASTER_TYPE = 'RS_CONTRACT'" : StringUtils.EMPTY);
            QueryUtils.itemUpdate(input, "OVERRIDE_QUERY");
        } catch (Exception e) {
            LOGGER.error("Error in updateOverride :", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean getCondition(AdjustmentDTO dto, Object propertyId, AbstractSelectionDTO selection) {
        return propertyId.toString().contains("override") && "PRODUCT".equalsIgnoreCase(dto.getLevelName());
    }
}
