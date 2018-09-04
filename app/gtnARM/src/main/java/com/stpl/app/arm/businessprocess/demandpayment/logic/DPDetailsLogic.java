/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandpayment.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import com.stpl.app.arm.utils.QueryUtils;
import static com.stpl.app.utils.VariableConstants.DASH;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class DPDetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO selection, Boolean isReserve) {
        List paymentsReplaceList = new ArrayList();
        List<String> paymentsReserveHeader = new ArrayList<>();
        List<String> paymentsReserveProperty = new ArrayList<>();
        List<List> paymentsFinalList = new ArrayList<>();
        StringBuilder value;
        StringBuilder property;
        String isReserveValue = isReserve ? "0" : "1";
        paymentsReplaceList.add(isReserveValue);
        paymentsReplaceList.add(selection.getDataSelectionDTO().getAdjustmentId());
        paymentsReplaceList.add(selection.getDataSelectionDTO().getProjectionId());
        paymentsReplaceList.add(selection.getDataSelectionDTO().getProjectionId());
        paymentsReplaceList.add(selection.getDataSelectionDTO().getCompanyMasterSid());
        paymentsReplaceList.add(selection.getDataSelectionDTO().getBucompanyMasterSid());
        StringBuilder paymentsQuery;
        if (selection.getSessionDTO().isWorkFlow()) {
            paymentsQuery = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
            paymentsQuery.replace(paymentsQuery.indexOf("?"), paymentsQuery.indexOf("?") + 1, String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
            paymentsQuery.replace(paymentsQuery.indexOf("?"), paymentsQuery.indexOf("?") + 1, isReserve ? "0" : "1");
        } else {
            paymentsQuery = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
            for (Object temp : paymentsReplaceList) {
                paymentsQuery.replace(paymentsQuery.indexOf("?"), paymentsQuery.indexOf("?") + 1, String.valueOf(temp));
            }
        }
        List list = QueryUtils.executeSelect(paymentsQuery.toString());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] paymentObj = (Object[]) list.get(i);
                value = new StringBuilder(StringUtils.EMPTY);
                property = new StringBuilder(StringUtils.EMPTY);
                if (isValid(paymentObj[0])) {
                    value = new StringBuilder(helperId.getDescriptionByID((Integer)(paymentObj[0])));
                    property = new StringBuilder(String.valueOf(paymentObj[0]));
                }
                if (isValid(paymentObj[1])) {
                    value.append(DASH).append(helperId.getDescriptionByID((Integer)(paymentObj[1])));
                    property.append(DASH).append(String.valueOf(paymentObj[1]));
                }
                if (isValid(paymentObj[NumericConstants.TWO])) {
                    value.append(DASH).append(String.valueOf(paymentObj[NumericConstants.TWO]));
                    property.append(DASH).append(String.valueOf(paymentObj[NumericConstants.TWO]));
                }
                paymentsReserveHeader.add(value.toString());
                paymentsReserveProperty.add(property.toString());
            }
            paymentsFinalList.add(paymentsReserveHeader);
            paymentsFinalList.add(paymentsReserveProperty);
        }
        return paymentsFinalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        return "ARM_DEMAND_RECON_SUMMARY";
    }

    @Override
    protected String getTableNameForEdit() {
        return "ST_ARM_DEMAND_RECON_SUMMARY";
    }

    @Override
    protected CharSequence getRateColumn() {
        return "B.PROJECTED_RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> condition, String tableAliasName) {
        String conditionStr = StringUtils.EMPTY;
        if (condition != null && !condition.isEmpty() && condition.size() < NumericConstants.THREE) {
            StringBuilder grlStr = new StringBuilder(StringUtils.EMPTY);
            for (int i = 0; i < condition.size(); i++) {
                String str = condition.get(i);
                grlStr.append(tableAliasName).append("ACCRUAL_AMOUNT ").append(str.charAt(0)).append(" 0.00");
                if (condition.size() > 1 && i != 1) {
                    grlStr.append(" OR ");
                }
            }
            conditionStr = "(" + grlStr + " ) AND ";
        }
        return conditionStr;
    }
}
