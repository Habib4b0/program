
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.logic;

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
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class Trx7PADetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    public static final Logger LOGGERFORDETAILLOGIC = Logger.getLogger(Trx7PADetailsLogic.class);

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO selection, Boolean isReserve) {
        LOGGERFORDETAILLOGIC.debug("--Inside getReserveAccountDetails --" + selection.getDataSelectionDTO().getProjectionId());
        List replaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        StringBuilder value;
        StringBuilder property;
        String isReserveValue = isReserve ? "0" : "1";
        replaceList.add(isReserveValue);
        if (selection.getDataSelectionDTO().getAdjustmentType() != null) {
            replaceList.add(selection.getDataSelectionDTO().getAdjustmentId());
        }
        replaceList.add(selection.getDataSelectionDTO().getProjectionId());
        replaceList.add(selection.getDataSelectionDTO().getProjectionId());
        replaceList.add(selection.getDataSelectionDTO().getCompanyMasterSid());
        replaceList.add(selection.getDataSelectionDTO().getBucompanyMasterSid());
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
        LOGGERFORDETAILLOGIC.debug("--query --" + query);
        List list = QueryUtils.executeSelect(query.toString());
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                value = new StringBuilder(StringUtils.EMPTY);
                property = new StringBuilder(StringUtils.EMPTY);
                if (isValid(obj[0])) {
                    value = new StringBuilder(helperId.getDescriptionByID(Integer.valueOf(String.valueOf(obj[0]))));
                    property = new StringBuilder(String.valueOf(obj[0]));
                }
                if (isValid(obj[1])) {
                    value.append(DASH).append(helperId.getDescriptionByID(Integer.valueOf(String.valueOf(obj[1]))));
                    property.append(DASH).append(String.valueOf(obj[1]));
                }
                if (isValid(obj[NumericConstants.TWO])) {
                    value.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                    property.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                }
                reserveHeader.add(value.toString());
                reserveProperty.add(property.toString());
            }
            finalList.add(reserveHeader);
            finalList.add(reserveProperty);

        }
        LOGGERFORDETAILLOGIC.debug("--Exit getReserveAccountDetails --" + finalList.size());
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        return "ARM_DISTRIBUTION_FEES_RATE";
    }

    @Override
    protected String getTableNameForEdit() {
        return "ST_ARM_DISTRIBUTION_FEES_RATE";
    }

    @Override
    protected CharSequence getRateColumn() {
        return "B.RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> condition, String tableAliasName) {
        LOGGERFORDETAILLOGIC.debug("--Inside getAmountFilterCondition --");
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
            conditionStr = "(" + grlStr.toString() + " ) AND ";
        }
        LOGGERFORDETAILLOGIC.debug("--Exit getAmountFilterCondition --" + conditionStr);
        return conditionStr;
    }

}
