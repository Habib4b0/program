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
        List replaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        String value = StringUtils.EMPTY;
        String property = StringUtils.EMPTY;
        String isReserveValue = isReserve ? "0" : "1";
        replaceList.add(isReserveValue);
        replaceList.add(selection.getDataSelectionDTO().getAdjustmentId());
        replaceList.add(selection.getDataSelectionDTO().getProjectionId());
        replaceList.add(selection.getDataSelectionDTO().getProjectionId());
        replaceList.add(selection.getDataSelectionDTO().getCompanyMasterSid());
        replaceList.add(selection.getDataSelectionDTO().getBu_companyMasterSid());
        StringBuilder query;
        if (selection.getSessionDTO().isWorkFlow()) {
            query = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, isReserve ? "0" : "1");
        } else {
            query = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
            for (Object temp : replaceList) {
                query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(temp));
            }
        }
        List list = QueryUtils.executeSelect(query.toString());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                value = StringUtils.EMPTY;
                property = StringUtils.EMPTY;
                if (isValid(obj[0])) {
                    value = helperId.getDescriptionByID(Integer.valueOf(String.valueOf(obj[0])));
                    property = String.valueOf(obj[0]);
                }
                if (isValid(obj[1])) {
                    value += DASH + helperId.getDescriptionByID(Integer.valueOf(String.valueOf(obj[1])));
                    property += DASH + String.valueOf(obj[1]);
                }
                if (isValid(obj[NumericConstants.TWO])) {
                    value += DASH + String.valueOf(obj[NumericConstants.TWO]);
                    property += DASH + String.valueOf(obj[NumericConstants.TWO]);
                }
                reserveHeader.add(value);
                reserveProperty.add(property);
            }
            finalList.add(reserveHeader);
            finalList.add(reserveProperty);
        }
        return finalList;
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
            String grlStr = StringUtils.EMPTY;
            for (int i = 0; i < condition.size(); i++) {
                String str = condition.get(i);
                grlStr += tableAliasName + "ACCRUAL_AMOUNT " + String.valueOf(str.charAt(0)) + " 0.00";
                if (condition.size() > 1 && i != 1) {
                    grlStr += " OR ";
                }
            }
            conditionStr = "(" + grlStr + " ) AND ";
        }
        return conditionStr;
    }
}
