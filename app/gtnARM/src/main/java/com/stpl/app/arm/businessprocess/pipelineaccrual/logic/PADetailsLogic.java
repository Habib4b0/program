/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import com.stpl.app.arm.utils.QueryUtils;
import static com.stpl.app.utils.VariableConstants.DASH;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class PADetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    private final Logger paDetailsLogger = LoggerFactory.getLogger(PADetailsLogic.class);

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO accrualSelection, Boolean isReserve) {
        List pipelineReplaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        StringBuilder accrualValue;
        StringBuilder property;
        String isReserveValue = isReserve ? "0" : "1";
        pipelineReplaceList.add(isReserveValue);
        if (accrualSelection.getDataSelectionDTO().getAdjustmentType() != null) {
            pipelineReplaceList.add(accrualSelection.getDataSelectionDTO().getAdjustmentId());
        }
        pipelineReplaceList.add(accrualSelection.getDataSelectionDTO().getProjectionId());
        pipelineReplaceList.add(accrualSelection.getDataSelectionDTO().getProjectionId());
        pipelineReplaceList.add(accrualSelection.getDataSelectionDTO().getCompanyMasterSid());
        pipelineReplaceList.add(accrualSelection.getDataSelectionDTO().getBucompanyMasterSid());
        StringBuilder query;
        if (accrualSelection.getSessionDTO().isWorkFlow()) {
            query = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(accrualSelection.getDataSelectionDTO().getProjectionId()));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, isReserve ? "0" : "1");
        } else {
            query = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
            for (Object temp : pipelineReplaceList) {
                query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(temp));
            }
        }
        List list = QueryUtils.executeSelect(query.toString());
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                Object[] pipelineObj = (Object[]) list.get(i);
                accrualValue = new StringBuilder(StringUtils.EMPTY);
                property = new StringBuilder(StringUtils.EMPTY);
                if (isValid(pipelineObj[0])) {
                    accrualValue = new StringBuilder(helperId.getDescriptionByID((Integer) (pipelineObj[0])));
                    property = new StringBuilder(String.valueOf(pipelineObj[0]));
                }
                if (isValid(pipelineObj[1])) {
                    accrualValue.append(DASH).append(helperId.getDescriptionByID((Integer) (pipelineObj[1])));
                    property.append(DASH).append(String.valueOf(pipelineObj[1]));
                }
                if (isValid(pipelineObj[NumericConstants.TWO])) {
                    accrualValue.append(DASH).append(String.valueOf(pipelineObj[NumericConstants.TWO]));
                    property.append(DASH).append(String.valueOf(pipelineObj[NumericConstants.TWO]));
                }
                reserveHeader.add(accrualValue.toString());
                reserveProperty.add(property.toString());
            }
            finalList.add(reserveHeader);
            finalList.add(reserveProperty);

        }
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        paDetailsLogger.debug("Inside getExcelResultList");
        return Collections.emptyList();
    }

    @Override
    protected String getTableNameForView() {
        paDetailsLogger.debug("Inside getTableNameForView");
        return "ARM_PIPELINE_RATE";
    }

    @Override
    protected String getTableNameForEdit() {
        paDetailsLogger.debug("Inside getTableNameForEdit");
        return "ST_ARM_PIPELINE_RATE";
    }

    @Override
    protected CharSequence getRateColumn() {
        paDetailsLogger.debug("Inside getRateColumn");
        return "B.RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> pipelineCondition, String tableAliasName) {
        String conditionStr = StringUtils.EMPTY;
        if (pipelineCondition != null && !pipelineCondition.isEmpty() && pipelineCondition.size() < NumericConstants.THREE) {
            StringBuilder grlStr = new StringBuilder(StringUtils.EMPTY);
            for (int i = 0; i < pipelineCondition.size(); i++) {
                String str = pipelineCondition.get(i);
                grlStr.append(tableAliasName).append("ACCRUAL_AMOUNT ").append(str.charAt(0)).append(" 0.00");
                if (pipelineCondition.size() > 1 && i != 1) {
                    grlStr.append(" OR ");
                }
            }
            conditionStr = "(" + grlStr.toString() + " ) AND ";
        }
        return conditionStr;
    }
}
