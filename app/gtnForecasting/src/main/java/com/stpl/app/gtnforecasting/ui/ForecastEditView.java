/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.ui.form.ForecastForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author soundarrajan
 */
public class ForecastEditView extends VerticalLayout {

    protected SessionDTO session;
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastEditView.class);

    /**
     * View name for navigation.
     */
    public static final String NAME = "NMPROJECTION";
    protected ForecastForm forecastName;
    protected ForecastEditWindow editWindow;
    protected ExtFilterTable resultTable;
    protected final String screenName;
    protected final DataSelectionForm dataSelectionForm;

    /**
     * Default constructor.
     *
     * @param session
     * @param dataSelectionDTO
     * @param editWindow
     * @param resultTable
     * @param screenName
     * @param dataSelectionForm
     * @throws Exception
     */
    public ForecastEditView(final SessionDTO session, final DataSelectionDTO dataSelectionDTO, final ForecastEditWindow editWindow,
            final ExtFilterTable resultTable, final String screenName, final DataSelectionForm dataSelectionForm) {
        this.session = session;
       
        this.editWindow = editWindow;
        this.resultTable = resultTable;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;

        try {
            forecastName = new ForecastForm(new CustomFieldGroup(new BeanItem<>(dataSelectionDTO)), dataSelectionDTO, this.session, this.editWindow, this.resultTable, screenName, dataSelectionForm, null);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        addComponent(forecastName);
    }
    
}
