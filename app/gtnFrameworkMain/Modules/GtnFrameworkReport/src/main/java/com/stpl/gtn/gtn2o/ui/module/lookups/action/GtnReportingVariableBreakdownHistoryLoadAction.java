/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownHistoryLoadAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {

        List<Object> actionParams = gtnUIFrameWorkActionConfig.getActionParameterList();
        String fromPeriod = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParams.get(1).toString(), componentId)
                .getStringCaptionFromV8ComboBox();
        fromPeriod="2016-01-01";
        String frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParams.get(2).toString(),componentId)
                .getStringCaptionFromV8ComboBox();

        String toPeriod = String.valueOf(LocalDate.now());

        long diffInMonths = ChronoUnit.MONTHS.between(LocalDate.parse(fromPeriod), LocalDate.parse(toPeriod));

        int quotient = 0;
        int historyPeriod = 0;
        List<String> historyPeriodCaptionList = new ArrayList<>();

        switch (frequency) {
            case "Quarter":
                quotient = (int) (diffInMonths / 3);
                historyPeriod = getRoundOffDates((int) diffInMonths, quotient, 3);
                historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList, "Quarters");
                break;
            case "Semi-Annual":
                quotient = (int) (diffInMonths / 6);
                historyPeriod = getRoundOffDates((int) diffInMonths, quotient, 6);
                historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList, "Semi-Annual Periods");

                break;
            case "Month":
                historyPeriod = (int) diffInMonths;
                historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList, "Months");

                break;
            case "Annual":
                historyPeriod = (int) ChronoUnit.YEARS.between(LocalDate.parse(fromPeriod), LocalDate.parse(toPeriod));
                historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList, "Year");

                break;
        }

        GtnUIFrameworkComboBoxConfig historyReloadComboboxConfig = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(actionParams.get(3).toString(), componentId).getComponentConfig()
                    .getGtnComboboxConfig();
            historyReloadComboboxConfig.setItemValues(historyPeriodCaptionList);
            historyReloadComboboxConfig.setItemCaptionValues(historyPeriodCaptionList);

            GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
            combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
                    actionParams.get(3).toString(), componentId,
                    Arrays.asList(""));
    }

    private List<String> getHistoryPeriodCaptionList(int historyPeriod, List<String> historyPeriodCaptionList,String frequencyFormat) {
        for(int i=1;i<=historyPeriod;i++){
            historyPeriodCaptionList.add(i+" "+frequencyFormat);
        }
        return historyPeriodCaptionList;
    }

    private int getRoundOffDates(int diffInMonths,int quotient,int num) {
       return diffInMonths%num >=1 ? quotient+1 : quotient;
      
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
