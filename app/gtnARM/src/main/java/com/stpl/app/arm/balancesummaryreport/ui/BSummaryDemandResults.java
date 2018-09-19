/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.balancesummaryreport.logic.BSummaryDemandLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryDemandResults extends AbstractBalanceSummaryResutls {

    public static final Logger DEMAND_RESULTS_LOGGER = LoggerFactory.getLogger(BSummaryDemandResults.class);

    public BSummaryDemandResults(BSummaryDemandLogic logic, SummarySelection selection) {
        super(logic, selection);
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            List<Object> header = getSummaryLogic().getRightTableHeaders(getSummarySelection());
            List rightSingleVisibleColumn = (List) header.get(NumericConstants.ZERO);
            List rightSingleVisibleHeader = (List) header.get(NumericConstants.ONE);
            List<String> rightDoubleVisibleColumn = (List) header.get(NumericConstants.TWO);
            List<String> rightDoubleVisibleHeader = (List) header.get(NumericConstants.THREE);
            for (Object variableColumn : rightSingleVisibleColumn) {
                properties.put(variableColumn, String.class);
            }
            getExcelContainer().setColumnProperties(properties);
            getExcelContainer().setRecordHeader(rightSingleVisibleColumn);
            List rightSingleVisibleColumn1 = new ArrayList(rightSingleVisibleColumn);
            rightSingleVisibleColumn1.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rightSingleVisibleHeader.add(0, ARMUtils.CUSTOMERORPRODUCT);
            rightDoubleVisibleColumn.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rightDoubleVisibleHeader.add(0, "");
            getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
            getExcelTable().setColumnHeaders(Arrays.copyOf((rightSingleVisibleHeader).toArray(), (rightSingleVisibleHeader).size(), String[].class));
            getExcelTable().setDoubleHeaderVisible(true);
            getExcelTable().setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
            getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(rightDoubleVisibleHeader.toArray(), rightDoubleVisibleHeader.size(), String[].class));
            getExcelTable().setDoubleHeaderMap((Map) header.get(NumericConstants.FIVE));
            setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
        } catch (Exception ex) {
            DEMAND_RESULTS_LOGGER.error("Error in setExcelVisibleColumn :", ex);
        }
    }

    @Override
    public ExcelInterface getExcelLogic() {
        DEMAND_RESULTS_LOGGER.debug("inside getExcelLogic");
        return getSummaryLogic();
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        DEMAND_RESULTS_LOGGER.debug("inside getHierarchy");
        return getSelection().getSummeryhierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        DEMAND_RESULTS_LOGGER.debug("inside viewType");
        getSummarySelection().setSummeryhierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    public String getExcelFileName() {
        DEMAND_RESULTS_LOGGER.debug("inside getExcelFileName");
        return "Balance Summary Report - Demand";
    }

    @Override
    protected boolean isPercentageColumn2Decimal(String column) {
        DEMAND_RESULTS_LOGGER.debug("inside isPercentageColumn2Decimal");
        return column.contains("PaymentRatio");
    }
}
