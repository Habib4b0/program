/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandaccrual.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import static com.stpl.app.utils.VariableConstants.DASH;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class DADetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    private static final Logger DETAILS_LOGGER = LoggerFactory.getLogger(DADetailsLogic.class);

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO demandSelection, Boolean isReserve) {
        List replaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        String isReserveValue = isReserve ? "0" : "1";

        if (demandSelection.getSessionDTO().isWorkFlow()) {
            replaceList.add(demandSelection.getDataSelectionDTO().getProjectionId());
            replaceList.add(isReserveValue);
            replaceList.add(demandSelection.getDataSelectionDTO().getProjectionId());
            replaceList.add(demandSelection.getDataSelectionDTO().getProjectionId());
            replaceList.add(demandSelection.getDataSelectionDTO().getCompanyMasterSid());
            replaceList.add(demandSelection.getDataSelectionDTO().getBucompanyMasterSid());
        } else {
            replaceList.add(isReserveValue);
            replaceList.add(demandSelection.getDataSelectionDTO().getAdjustmentId());
            replaceList.add(demandSelection.getDataSelectionDTO().getProjectionId());
            replaceList.add(demandSelection.getDataSelectionDTO().getProjectionId());
            replaceList.add(demandSelection.getDataSelectionDTO().getCompanyMasterSid());
            replaceList.add(demandSelection.getDataSelectionDTO().getBucompanyMasterSid());
        }
        StringBuilder query;
        if (demandSelection.getSessionDTO().isWorkFlow()) {
            query = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
            query.replace(query.indexOf(ARMUtils.CHAR_QUS), query.indexOf(ARMUtils.CHAR_QUS) + 1, String.valueOf(demandSelection.getDataSelectionDTO().getProjectionId()));
            query.replace(query.indexOf(ARMUtils.CHAR_QUS), query.indexOf(ARMUtils.CHAR_QUS) + 1, isReserve ? "0" : "1");
        } else {
            query = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
            for (Object temp : replaceList) {
                query.replace(query.indexOf(ARMUtils.CHAR_QUS), query.indexOf(ARMUtils.CHAR_QUS) + 1, String.valueOf(temp));
            }
        }
        List list = QueryUtils.executeSelect(query.toString());
        return getFinalList(list, reserveHeader, reserveProperty, finalList);
    }

    private List<List> getFinalList(List list, List<String> reserveHeader, List<String> reserveProperty, List<List> finalList) {
        StringBuilder demandValue;
        StringBuilder property;
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                demandValue = new StringBuilder();
                property = new StringBuilder();
                if (isValid(obj[0])) {
                    demandValue = new StringBuilder(helperId.getDescriptionByID((Integer) (obj[0])));
                    property = new StringBuilder(String.valueOf(obj[0]));
                }
                if (isValid(obj[1])) {
                    demandValue.append(DASH).append(helperId.getDescriptionByID((Integer) (obj[1])));
                    property.append(DASH).append(String.valueOf(obj[1]));
                }
                if (isValid(obj[NumericConstants.TWO])) {
                    demandValue.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                    property.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                }
                reserveHeader.add(demandValue.toString());
                reserveProperty.add(property.toString());
            }
            finalList.add(reserveHeader);
            finalList.add(reserveProperty);
        }
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        DETAILS_LOGGER.debug("Inside getExcelResultList");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        DETAILS_LOGGER.debug("Inside getTableNameForView");
        return "ARM_DEMAND_ADJ_SUMMARY";
    }

    @Override
    protected String getTableNameForEdit() {
        DETAILS_LOGGER.debug("Inside getTableNameForEdit");
        return "ST_ARM_DEMAND_ADJ_SUMMARY";
    }

    @Override
    protected CharSequence getRateColumn() {
        DETAILS_LOGGER.debug("Inside getRateColumn");
        return "B.PROJECTED_RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> demandCondition, String tableAliasName) {
        String conditionStr = StringUtils.EMPTY;
        if (demandCondition != null && !demandCondition.isEmpty() && demandCondition.size() < NumericConstants.THREE) {
            StringBuilder grlStr = new StringBuilder();
            for (int i = 0; i < demandCondition.size(); i++) {
                String str = demandCondition.get(i);
                grlStr.append(tableAliasName).append("ACCRUAL_AMOUNT ").append(String.valueOf(str.charAt(0))).append(" 0.00");
                if (demandCondition.size() > 1 && i != 1) {
                    grlStr.append(" OR ");
                }
            }
            conditionStr = ARMUtils.OPEN_PARANTHESIS + grlStr.toString() + " ) AND ";
        }
        return conditionStr;
    }
}
