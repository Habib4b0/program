/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import static com.stpl.app.utils.VariableConstants.DASH;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author @param <T>
 */
public class RRDetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO returnReserveSelection, Boolean isReserve) {
        LOGGER.debug("--Inside getReserveAccountDetails --{}", isReserve);
        List replaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        StringBuilder retResvalue;
        StringBuilder property;
        String isReserveValue = isReserve ? "0" : "1";
        try {
            if (returnReserveSelection.getSessionDTO().isWorkFlow()) {
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getProjectionId());
                replaceList.add(isReserveValue);
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getProjectionId());
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getProjectionId());
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getCompanyMasterSid());
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getBucompanyMasterSid());
            } else {
                replaceList.add(isReserveValue);
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getAdjustmentId());
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getProjectionId());
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getProjectionId());
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getCompanyMasterSid());
                replaceList.add(returnReserveSelection.getDataSelectionDTO().getBucompanyMasterSid());
            }
            StringBuilder query;
            if (returnReserveSelection.getSessionDTO().isWorkFlow()) {
                query = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
                query.replace(query.indexOf(ARMUtils.CHAR_QUS), query.indexOf(ARMUtils.CHAR_QUS) + 1, String.valueOf(returnReserveSelection.getDataSelectionDTO().getProjectionId()));
                query.replace(query.indexOf(ARMUtils.CHAR_QUS), query.indexOf(ARMUtils.CHAR_QUS) + 1, isReserve ? "0" : "1");
            } else {
                query = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
                for (Object temp : replaceList) {
                    query.replace(query.indexOf(ARMUtils.CHAR_QUS), query.indexOf(ARMUtils.CHAR_QUS) + 1, String.valueOf(temp));
                }
            }
            LOGGER.debug("--query --{}", query);
            List list = QueryUtils.executeSelect(query.toString());
            if (list != null) {

                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    retResvalue = new StringBuilder();
                    property = new StringBuilder();
                    if (isValid(obj[0])) {
                        retResvalue = new StringBuilder(helperId.getDescriptionByID((Integer) (obj[0])));

                        property.append(String.valueOf(obj[0]));
                    }
                    if (isValid(obj[1])) {
                        retResvalue.append(DASH).append(helperId.getDescriptionByID((Integer) (obj[1])));

                        property.append(DASH).append(String.valueOf(obj[1]));
                    }
                    if (isValid(obj[NumericConstants.TWO])) {
                        retResvalue.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                        property.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                    }
                    reserveHeader.add(retResvalue.toString());
                    reserveProperty.add(property.toString());
                }
                finalList.add(reserveHeader);
                finalList.add(reserveProperty);
                LOGGER.debug("--Exit getReserveAccountDetails --{}", finalList.size());
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getReserveAccountDetails :", ex);
        }
        return finalList;
    }

    @Override
    protected String getTableNameForView() {
        return CommonConstant.ARM_RETURN_RATE;
    }

    @Override
    protected String getTableNameForEdit() {
        return CommonConstant.ST_ARM_RETURN_RATE;
    }

    @Override
    protected CharSequence getRateColumn() {
        return "B.RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> retResCondition, String tableAliasName) {
        String conditionStr = StringUtils.EMPTY;
        if (retResCondition != null && !retResCondition.isEmpty() && retResCondition.size() < NumericConstants.THREE) {
            StringBuilder grlStr = new StringBuilder();
            for (int i = 0; i < retResCondition.size(); i++) {
                String str = retResCondition.get(i);
                grlStr.append(tableAliasName).append("ACCRUAL_AMOUNT ").append(str.charAt(0)).append(" 0.00");
                if (retResCondition.size() > 1 && i != 1) {
                    grlStr.append(" OR ");
                }
            }
            conditionStr = ARMUtils.OPEN_PARANTHESIS + grlStr.toString() + " ) AND ";
        }
        return conditionStr;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        return Collections.emptyList();
    }

}
