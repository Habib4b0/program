/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.logic;

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
 * @author Srithar
 * @param <T>
 */
public class Trx6DetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO selection, Boolean isReserve) {
        List replaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        StringBuilder value;
        StringBuilder property;
        String isReserveValue = isReserve ? "0" : "1";
        replaceList.add(isReserveValue);
        replaceList.add(selection.getDataSelectionDTO().getAdjustmentId());
        replaceList.add(selection.getDataSelectionDTO().getProjectionId());
        replaceList.add(selection.getDataSelectionDTO().getProjectionId());
        replaceList.add(selection.getDataSelectionDTO().getCompanyMasterSid());
        replaceList.add(selection.getDataSelectionDTO().getBucompanyMasterSid());
        StringBuilder query;
        if (selection.isIsWorkFlow()) {
            query = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, isReserve ? "0" : "1");
        } else {
            query = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
            for (Object temp : replaceList) {
                query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(temp));
            }
        }
        LOGGER.debug("Query --{}", query.toString());
        List list = QueryUtils.executeSelect(query.toString());
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                value = new StringBuilder(StringUtils.EMPTY);
                property = new StringBuilder(StringUtils.EMPTY);
                if (isValid(obj[0])) {
                    value = new StringBuilder(helperId.getDescriptionByID((Integer)(obj[0])));
                    property = new StringBuilder(String.valueOf(obj[0]));
                }
                if (isValid(obj[1])) {
                    value.append(DASH).append(helperId.getDescriptionByID((Integer)(obj[1])));
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
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        return "ARM_INFLATION_INVENTORY_ADJ";
    }

    @Override
    protected String getTableNameForEdit() {
        return "ST_ARM_INFLATION_INVENTORY_ADJ";
    }

    @Override
    protected CharSequence getRateColumn() {
        return "INFLATION_FACTOR";
    }

    @Override
    protected String getGTNQuery() {
        return "Adjustment GTN Details Trx6 query ";
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
            conditionStr = "(" + grlStr.toString() + " ) AND ";
        }
        return conditionStr;
    }

}
