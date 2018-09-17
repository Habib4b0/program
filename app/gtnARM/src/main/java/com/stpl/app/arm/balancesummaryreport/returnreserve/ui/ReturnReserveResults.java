/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.returnreserve.ui;

import com.stpl.app.arm.balancesummaryreport.returnreserve.logic.ReturnReserveLogic;
import com.stpl.app.arm.balancesummaryreport.ui.AbstractBalanceSummaryResutls;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sathya.Seelan
 */
public class ReturnReserveResults extends AbstractBalanceSummaryResutls {

    public ReturnReserveResults(ReturnReserveLogic logic, SummarySelection selection) {
        super(logic, selection);
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            List<Object> header = getSummaryLogic().getRightTableHeaders(getSummarySelection());
            List rrRightSingleVisibleColumn = (ArrayList) header.get(NumericConstants.ZERO);
            List rrRightSingleVisibleHeader = (ArrayList) header.get(NumericConstants.ONE);
            List<String> rrRightDoubleVisibleColumn = (ArrayList) header.get(NumericConstants.TWO);
            List<String> rrRightDoubleVisibleHeader = (ArrayList) header.get(NumericConstants.THREE);
            for (Object variableColumn : rrRightSingleVisibleColumn) {
                properties.put(variableColumn, String.class);
            }
            getExcelContainer().setColumnProperties(properties);
            getExcelContainer().setRecordHeader(rrRightSingleVisibleColumn);
            List rrRightSingleVisibleColumn1 = new ArrayList(rrRightSingleVisibleColumn);
            rrRightSingleVisibleColumn1.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rrRightSingleVisibleHeader.add(0, ARMUtils.CUSTOMERORPRODUCT);
            rrRightDoubleVisibleColumn.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rrRightDoubleVisibleHeader.add(0, "");
            getExcelTable().setVisibleColumns(rrRightSingleVisibleColumn1.toArray());
            getExcelTable().setColumnHeaders(Arrays.copyOf((rrRightSingleVisibleHeader).toArray(), (rrRightSingleVisibleHeader).size(), String[].class));
            getExcelTable().setDoubleHeaderVisible(true);
            getExcelTable().setDoubleHeaderVisibleColumns(rrRightDoubleVisibleColumn.toArray());
            getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(rrRightDoubleVisibleHeader.toArray(), rrRightDoubleVisibleHeader.size(), String[].class));
            getExcelTable().setDoubleHeaderMap((Map) header.get(NumericConstants.FIVE));
            setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
        } catch (Exception ex) {
            LOGGER.error("Error in setExcelVisibleColumn :", ex);
        }
    }

    @Override
    public String getExcelFileName() {
        return "Balance Summary Report - Return Reserve";
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSummarySelection().setSummeryhierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummeryhierarchy();
    }
}
