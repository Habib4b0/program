/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.ui.form.ForecastForm;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItem;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author Sooriya.Lakshmanan
 */
public class ForecastWindow extends CustomWindow {
    protected ForecastForm forecastName;
    
     public ForecastWindow(String projectionName, SessionDTO session, final ExtFilterTable resultTable, final String screenName, final DataSelectionForm dataSelectionForm,final DataSelectionDTO dataSelectionDTO) throws  Exception {
        super(projectionName);
        init();
        CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(new DataSelectionDTO()));
        setContent(new ForecastForm(dataSelectionBinder, dataSelectionDTO, session, null, resultTable,screenName,dataSelectionForm,this));
    }

    private void init()  {
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constant.ZERO);
        setPositionY(Constant.ZERO);
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        addStyleName("body-fixed");
        setSizeFull();
        setClosable(false);
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }
}
