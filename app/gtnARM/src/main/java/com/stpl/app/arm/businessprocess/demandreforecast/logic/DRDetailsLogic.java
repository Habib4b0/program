/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandreforecast.logic;

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
public class DRDetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    private static final Logger DR_DETAILS_LOGGER = LoggerFactory.getLogger(DRDetailsLogic.class);

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO reforecastSelection, Boolean isReserve) {
        List reforecastReplaceList = new ArrayList();
        List<String> reforecastReserveHeader = new ArrayList<>();
        List<String> reforecastReserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        String isReserveValue = isReserve ? "0" : "1";
        reforecastReplaceList.add(isReserveValue);
        reforecastReplaceList.add(reforecastSelection.getDataSelectionDTO().getAdjustmentId());
        reforecastReplaceList.add(reforecastSelection.getDataSelectionDTO().getProjectionId());
        reforecastReplaceList.add(reforecastSelection.getDataSelectionDTO().getProjectionId());
        reforecastReplaceList.add(reforecastSelection.getDataSelectionDTO().getCompanyMasterSid());
        reforecastReplaceList.add(reforecastSelection.getDataSelectionDTO().getBucompanyMasterSid());
        StringBuilder reforecastQuery;
        if (reforecastSelection.getSessionDTO().isWorkFlow()) {
            reforecastQuery = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
            reforecastQuery.replace(reforecastQuery.indexOf(ARMUtils.CHAR_QUS), reforecastQuery.indexOf(ARMUtils.CHAR_QUS) + 1, String.valueOf(reforecastSelection.getDataSelectionDTO().getProjectionId()));
            reforecastQuery.replace(reforecastQuery.indexOf(ARMUtils.CHAR_QUS), reforecastQuery.indexOf(ARMUtils.CHAR_QUS) + 1, isReserve ? "0" : "1");
        } else {
            reforecastQuery = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
            for (Object temp : reforecastReplaceList) {
                reforecastQuery.replace(reforecastQuery.indexOf(ARMUtils.CHAR_QUS), reforecastQuery.indexOf(ARMUtils.CHAR_QUS) + 1, String.valueOf(temp));
            }
        }
        List list = QueryUtils.executeSelect(reforecastQuery.toString());
        return reforecastDetailsList(list, reforecastReserveHeader, reforecastReserveProperty, finalList);
    }

    private List<List> reforecastDetailsList(List list, List<String> reforecastReserveHeader, List<String> reforecastReserveProperty, List<List> finalList) {
        StringBuilder value;
        StringBuilder property;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] reforecastObj = (Object[]) list.get(i);
                value = new StringBuilder(StringUtils.EMPTY);
                property = new StringBuilder(StringUtils.EMPTY);
                if (isValid(reforecastObj[0])) {
                    value = new StringBuilder(helperId.getDescriptionByID((Integer) (reforecastObj[0])));
                    property = new StringBuilder(String.valueOf(reforecastObj[0]));
                }
                if (isValid(reforecastObj[1])) {
                    value.append(DASH).append(helperId.getDescriptionByID((Integer) (reforecastObj[1])));
                    property.append(DASH).append(String.valueOf(reforecastObj[1]));
                }
                if (isValid(reforecastObj[NumericConstants.TWO])) {
                    value.append(DASH).append(String.valueOf(reforecastObj[NumericConstants.TWO]));
                    property.append(DASH).append(String.valueOf(reforecastObj[NumericConstants.TWO]));
                }
                reforecastReserveHeader.add(value.toString());
                reforecastReserveProperty.add(property.toString());
            }
            finalList.add(reforecastReserveHeader);
            finalList.add(reforecastReserveProperty);
        }
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        DR_DETAILS_LOGGER.debug("inside getExcelResultList");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        DR_DETAILS_LOGGER.debug("inside getTableNameForView");
        return "ARM_DEMAND_RF_TRUE_UP_SUMMARY";
    }

    @Override
    protected String getTableNameForEdit() {
        DR_DETAILS_LOGGER.debug("inside getTableNameForEdit");
        return "ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY";
    }

    @Override
    protected CharSequence getRateColumn() {
        DR_DETAILS_LOGGER.debug("inside getRateColumn");
        return "B.PROJECTED_RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> reforecastCondition, String tableAliasName) {
        String conditionStr = StringUtils.EMPTY;
        if (reforecastCondition != null && !reforecastCondition.isEmpty() && reforecastCondition.size() < NumericConstants.THREE) {
            StringBuilder grlStr = new StringBuilder(StringUtils.EMPTY);
            for (int i = 0; i < reforecastCondition.size(); i++) {
                String str = reforecastCondition.get(i);
                grlStr.append(tableAliasName).append("ACCRUAL_AMOUNT ").append(str.charAt(0)).append(" 0.00");
                if (reforecastCondition.size() > 1 && i != 1) {
                    grlStr.append(" OR ");
                }
            }
            conditionStr = ARMUtils.OPEN_PARANTHESIS + grlStr.toString() + " ) AND ";
        }
        return conditionStr;
    }
}
