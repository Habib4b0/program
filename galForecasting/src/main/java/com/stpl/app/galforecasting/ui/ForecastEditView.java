/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui;

import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.ui.form.ForecastForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author soundarrajan
 */
public class ForecastEditView extends VerticalLayout {

    SessionDTO session;

    /**
     * View name for navigation.
     */
    public static final String NAME = "NMPROJECTION";

    /**
     * DTO object for DataSelection.
     */
    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

    /**
     * Binder for DataSelection.
     */
    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<DataSelectionDTO>(dataSelectionDTO));

    ForecastForm forecastName;
    ForecastEditWindow editWindow;
    ExtFilterTable resultTable;
    final String screenName;
    final DataSelectionForm dataSelectionForm;
    /**
     * Default constructor.
     * 
     * @param session
     * @param dataSelectionDTO
     * @param editWindow
     * @param resultTable
     * @param screenName
     * @throws Exception 
     */
    public ForecastEditView(final SessionDTO session, final DataSelectionDTO dataSelectionDTO, final ForecastEditWindow editWindow, 
            final ExtFilterTable resultTable,final String screenName,final DataSelectionForm dataSelectionForm) throws Exception {
        this.session = session;
        this.dataSelectionDTO = dataSelectionDTO;
        this.editWindow = editWindow;
        this.resultTable = resultTable;
        this.screenName=screenName;
        this.dataSelectionForm = dataSelectionForm;
        
        forecastName = new ForecastForm(dataSelectionBinder, this.dataSelectionDTO, this.session, this.editWindow, this.resultTable,screenName,dataSelectionForm);
        addComponent(forecastName);        
    }
    
}
