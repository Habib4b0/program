
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class Trx7PADetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    public static final Logger LOGGERFORDETAILLOGIC = LoggerFactory.getLogger(Trx7PADetailsLogic.class);

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO distributionSelection, Boolean isReserve) {
        LOGGERFORDETAILLOGIC.debug("--Inside getReserveAccountDetails --{}", distributionSelection.getDataSelectionDTO().getProjectionId());
        List replaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        StringBuilder distributionValue;
        StringBuilder property;
        String isReserveValue = isReserve ? "0" : "1";
        replaceList.add(isReserveValue);
        if (distributionSelection.getDataSelectionDTO().getAdjustmentType() != null) {
            replaceList.add(distributionSelection.getDataSelectionDTO().getAdjustmentId());
        }
        replaceList.add(distributionSelection.getDataSelectionDTO().getProjectionId());
        replaceList.add(distributionSelection.getDataSelectionDTO().getProjectionId());
        replaceList.add(distributionSelection.getDataSelectionDTO().getCompanyMasterSid());
        replaceList.add(distributionSelection.getDataSelectionDTO().getBucompanyMasterSid());
        StringBuilder query = getQueryForReserve(distributionSelection, isReserve, replaceList);
        LOGGERFORDETAILLOGIC.debug("--query --{}", query);
        List list = QueryUtils.executeSelect(query.toString());
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                distributionValue = new StringBuilder(StringUtils.EMPTY);
                property = new StringBuilder(StringUtils.EMPTY);
                if (isValid(obj[0])) {
                    distributionValue = new StringBuilder(helperId.getDescriptionByID((Integer) (obj[0])));
                    property = new StringBuilder(String.valueOf(obj[0]));
                }
                if (isValid(obj[1])) {
                    distributionValue.append(DASH).append(helperId.getDescriptionByID((Integer) (obj[1])));
                    property.append(DASH).append(String.valueOf(obj[1]));
                }
                if (isValid(obj[NumericConstants.TWO])) {
                    distributionValue.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                    property.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                }
                reserveHeader.add(distributionValue.toString());
                reserveProperty.add(property.toString());
            }
            finalList.add(reserveHeader);
            finalList.add(reserveProperty);

        }
        LOGGERFORDETAILLOGIC.debug("--Exit getReserveAccountDetails --{}", finalList.size());
        return finalList;
    }

    private StringBuilder getQueryForReserve(AbstractSelectionDTO distributionSelection, Boolean isReserve, List replaceList) {
        StringBuilder query;
        if (distributionSelection.getSessionDTO().isWorkFlow()) {
            query = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(distributionSelection.getDataSelectionDTO().getProjectionId()));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, isReserve ? "0" : "1");
        } else {
            query = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
            for (Object temp : replaceList) {
                query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(temp));
            }
        }
        return query;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        LOGGERFORDETAILLOGIC.debug("Inside getExcelResultList");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        LOGGERFORDETAILLOGIC.debug("Inside getTableNameForView");
        return "ARM_DISTRIBUTION_FEES_RATE";
    }

    @Override
    protected String getTableNameForEdit() {
        LOGGERFORDETAILLOGIC.debug("Inside getTableNameForEdit");
        return "ST_ARM_DISTRIBUTION_FEES_RATE";
    }

    @Override
    protected CharSequence getRateColumn() {
        LOGGERFORDETAILLOGIC.debug("Inside getRateColumn");
        return "B.RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> distributionCondition, String tableAliasName) {
        LOGGERFORDETAILLOGIC.debug("--Inside getAmountFilterCondition --");
        String conditionStr = StringUtils.EMPTY;
        if (distributionCondition != null && !distributionCondition.isEmpty() && distributionCondition.size() < NumericConstants.THREE) {
            StringBuilder grlStr = new StringBuilder(StringUtils.EMPTY);
            for (int i = 0; i < distributionCondition.size(); i++) {
                String str = distributionCondition.get(i);
                grlStr.append(tableAliasName).append("ACCRUAL_AMOUNT ").append(str.charAt(0)).append(" 0.00");
                if (distributionCondition.size() > 1 && i != 1) {
                    grlStr.append(" OR ");
                }
            }
            conditionStr = "(" + grlStr.toString() + " ) AND ";
        }
        LOGGERFORDETAILLOGIC.debug("--Exit getAmountFilterCondition --{}", conditionStr);
        return conditionStr;
    }

}
