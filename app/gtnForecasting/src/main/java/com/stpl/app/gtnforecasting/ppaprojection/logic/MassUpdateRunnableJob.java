/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ppaprojection.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.utils.Constant;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sooriya.lakshmanan
 */
public class MassUpdateRunnableJob implements Runnable {

    private final Object value;
    private final String fieldValue;
    private final String columnName;
    private final String group;
    private final int startQuater;
    private final int endQuater;
    private final int startYear;
    private final int endYear;
    private final ProjectionSelectionDTO selection;
    private final PPAProjectionLogic logic = new PPAProjectionLogic();
    private final static Map<String, List<String>> populateIdentifier = Constant.getPopulateIdentifier();

    public MassUpdateRunnableJob(Object value, String fieldValue, String columnName, int startQuater, int endQuater,
            int startYear, int endYear, String group, ProjectionSelectionDTO selection) {
        this.value = value;
        this.fieldValue = fieldValue;
        this.columnName = columnName;
        this.group = group;
        this.startQuater = startQuater;
        this.endQuater = endQuater;
        this.startYear = startYear;
        this.endYear = endYear;
        this.selection = selection;
    }

    @Override
    public void run() {
        List input = null;
        if (populateIdentifier.get(Constant.FROZEN_FIELDS).contains(fieldValue)) {
            input = logic.getInputForMassUpdateGroup(value, columnName, selection);
            PPAQuerys.ppaUpdate(input, "PPA.MAssUpdate-Group");
        } else {
            input = logic.getInputForMassUpdate(startQuater, endQuater, startYear, endYear, value, columnName, selection);
            PPAQuerys.ppaUpdate(input, "PPA.MAssUpdate-PriceCap");

        }
    }
}
