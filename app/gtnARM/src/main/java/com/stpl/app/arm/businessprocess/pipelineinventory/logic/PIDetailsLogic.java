
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.logic;

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
public class PIDetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO selection, Boolean isReserve) {
        LOGGER.debug("--Inside getReserveAccountDetails--");
        List<List> finalList = new ArrayList<>();
        List inventoryReplaceList = new ArrayList();
        List<String> inventoryReserveHeader = new ArrayList<>();
        List<String> inventoryReserveProperty = new ArrayList<>();
        try {
            StringBuilder value;
            StringBuilder property;
            String isReserveValue = isReserve ? "0" : "1";
            inventoryReplaceList.add(isReserveValue);
            inventoryReplaceList.add(selection.getDataSelectionDTO().getAdjustmentId());
            inventoryReplaceList.add(selection.getDataSelectionDTO().getProjectionId());
            inventoryReplaceList.add(selection.getDataSelectionDTO().getProjectionId());
            inventoryReplaceList.add(selection.getDataSelectionDTO().getCompanyMasterSid());
            inventoryReplaceList.add(selection.getDataSelectionDTO().getBucompanyMasterSid());
            StringBuilder inventoryQuery;
            if (selection.getSessionDTO().isWorkFlow()) {
                inventoryQuery = new StringBuilder(SQlUtil.getQuery("getloadworflowViewData"));
                inventoryQuery.replace(inventoryQuery.indexOf("?"), inventoryQuery.indexOf("?") + 1, String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
                inventoryQuery.replace(inventoryQuery.indexOf("?"), inventoryQuery.indexOf("?") + 1, isReserve ? "0" : "1");
            } else {
                inventoryQuery = new StringBuilder(SQlUtil.getQuery("getReserveAccountPipeline"));
                for (Object temp : inventoryReplaceList) {
                    inventoryQuery.replace(inventoryQuery.indexOf("?"), inventoryQuery.indexOf("?") + 1, String.valueOf(temp));
                }
            }
            LOGGER.debug("query-- {}", inventoryQuery);
            List list = QueryUtils.executeSelect(inventoryQuery.toString());
            if (list != null) {

                for (int i = 0; i < list.size(); i++) {
                    Object[] inventoryObj = (Object[]) list.get(i);
                    value = new StringBuilder(StringUtils.EMPTY);
                    property = new StringBuilder(StringUtils.EMPTY);
                    if (isValid(inventoryObj[0])) {
                        value = new StringBuilder(helperId.getDescriptionByID(Integer.valueOf(String.valueOf(inventoryObj[0]))));
                        property = new StringBuilder(String.valueOf(inventoryObj[0]));
                    }
                    if (isValid(inventoryObj[1])) {
                        value.append(DASH).append(helperId.getDescriptionByID(Integer.valueOf(String.valueOf(inventoryObj[1]))));
                        property.append(DASH).append(String.valueOf(inventoryObj[1]));
                    }
                    if (isValid(inventoryObj[NumericConstants.TWO])) {
                        value.append(DASH).append(String.valueOf(inventoryObj[NumericConstants.TWO]));
                        property.append(DASH).append(String.valueOf(inventoryObj[NumericConstants.TWO]));
                    }
                    inventoryReserveHeader.add(value.toString());
                    inventoryReserveProperty.add(property.toString());
                }
                finalList.add(inventoryReserveHeader);
                finalList.add(inventoryReserveProperty);

            }
        } catch (Exception e) {
            LOGGER.error("Error in getReserveAccountDetails :", e);
        }
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        return "ARM_INVENTORY_RATE";
    }

    @Override
    protected String getTableNameForEdit() {
        return "ST_ARM_INVENTORY_RATE";
    }

    @Override
    protected CharSequence getRateColumn() {
        return "B.RATE";
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
        LOGGER.debug("conditionStr--{}", conditionStr);
        return conditionStr;
    }

}
