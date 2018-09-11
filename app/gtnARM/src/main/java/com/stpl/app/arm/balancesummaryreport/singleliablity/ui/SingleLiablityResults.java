/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.singleliablity.ui;

import com.stpl.app.arm.balancesummaryreport.logic.AbstractBSummaryLogic;
import com.stpl.app.arm.balancesummaryreport.ui.AbstractBalanceSummaryResutls;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sathya.Seelan
 */
public class SingleLiablityResults extends AbstractBalanceSummaryResutls {

    public final Logger singleLiablityLogger = LoggerFactory.getLogger(SingleLiablityResults.class);

    public SingleLiablityResults(AbstractBSummaryLogic logic, SummarySelection selection) {
        super(logic, selection);
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            List<Object> header = getSummaryLogic().getRightTableHeaders(getSummarySelection());
            List slRightSingleVisibleColumn = (ArrayList) header.get(0);
            List slRightSingleVisibleHeader = (ArrayList) header.get(1);
            List<String> slRightDoubleVisibleColumn = (ArrayList) header.get(2);
            List<String> slRightDoubleVisibleHeader = (ArrayList) header.get(3);
            for (Object variableColumn : slRightSingleVisibleColumn) {
                properties.put(variableColumn, String.class);
            }
            getExcelContainer().setColumnProperties(properties);
            getExcelContainer().setRecordHeader(slRightSingleVisibleColumn);
            List rightsingleVisibleColumn1 = new ArrayList(slRightSingleVisibleColumn);
            rightsingleVisibleColumn1.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            slRightSingleVisibleHeader.add(0, ARMUtils.CUSTOMERORPRODUCT);
            slRightDoubleVisibleColumn.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            slRightDoubleVisibleHeader.add(0, "");
            getExcelTable().setVisibleColumns(rightsingleVisibleColumn1.toArray());
            getExcelTable().setColumnHeaders(Arrays.copyOf((slRightSingleVisibleHeader).toArray(), (slRightSingleVisibleHeader).size(), String[].class));
            getExcelTable().setDoubleHeaderVisible(Boolean.TRUE);
            getExcelTable().setDoubleHeaderVisibleColumns(slRightDoubleVisibleColumn.toArray());
            getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(slRightDoubleVisibleHeader.toArray(), slRightDoubleVisibleHeader.size(), String[].class));
            getExcelTable().setDoubleHeaderMap((Map) header.get(5));
            setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
        } catch (Exception ex) {
            singleLiablityLogger.error("Error in setExcelVisibleColumn :", ex);
        }
    }

    @Override
    public ExcelInterface getExcelLogic() {
        singleLiablityLogger.debug("inside getExcelLogic");
        return getSummaryLogic();
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        singleLiablityLogger.debug("inside getHierarchy ");
        return getSelection().getSummeryhierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        singleLiablityLogger.debug("inside setRespectiveHierarchy");
        getSummarySelection().setSummeryhierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    public String getExcelFileName() {
        singleLiablityLogger.debug("inside getExcelFileName");
        return "Balance Summary Report - Single Liablity";
    }
}
