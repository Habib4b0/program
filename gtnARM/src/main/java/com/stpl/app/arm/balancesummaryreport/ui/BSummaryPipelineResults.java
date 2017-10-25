/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.balancesummaryreport.logic.AbstractBSummaryLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryPipelineResults extends AbstractBalanceSummaryResutls {

    public BSummaryPipelineResults(AbstractBSummaryLogic logic, SummarySelection selection) {
        super(logic, selection);
        this.selection = selection;
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            List<Object> header = getLogic().getRightTableHeaders(selection);
            List rightSingleVisibleColumn = (ArrayList) header.get(0);
            List rightSingleVisibleHeader = (ArrayList) header.get(1);
            List<String> rightDoubleVisibleColumn = (ArrayList) header.get(2);
            List<String> rightDoubleVisibleHeader = (ArrayList) header.get(3);
            for (Object variableColumn : rightSingleVisibleColumn) {
                properties.put(variableColumn, String.class);
            }
            getExcelContainer().setColumnProperties(properties);
            getExcelContainer().setRecordHeader(rightSingleVisibleColumn);
            List right_singleVisibleColumn1 = new ArrayList(rightSingleVisibleColumn);
            right_singleVisibleColumn1.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rightSingleVisibleHeader.add(0, ARMUtils.CUSTOMERORPRODUCT);
            rightDoubleVisibleColumn.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rightDoubleVisibleHeader.add(0, "");
            getExcelTable().setVisibleColumns(right_singleVisibleColumn1.toArray());
            getExcelTable().setColumnHeaders(Arrays.copyOf((rightSingleVisibleHeader).toArray(), (rightSingleVisibleHeader).size(), String[].class));
            getExcelTable().setDoubleHeaderVisible(Boolean.TRUE);
            getExcelTable().setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
            getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(rightDoubleVisibleHeader.toArray(), rightDoubleVisibleHeader.size(), String[].class));
            getExcelTable().setDoubleHeaderMap((Map) header.get(5));
            setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public AbstractBSummaryLogic getLogic() {
        return (AbstractBSummaryLogic) super.getLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getLogic();
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummery_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        selection.setSummery_hierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }
    
    @Override
    public String getExcelFileName() {
        return "Balance Summary Report-Pipeline";
    }
}
