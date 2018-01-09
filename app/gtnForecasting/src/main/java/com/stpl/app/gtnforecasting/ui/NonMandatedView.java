/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.ForecastForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;



/**
 *
 * @author soundarrajan
 */
public class NonMandatedView extends VerticalLayout {

    protected SessionDTO session;
    /**
     * View name for navigation.
     */
    public static final String NAME = "NMPROJECTIONVIEW";

    /**
     * DTO object for DataSelection.
     */
    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

    /**
     * Binder for DataSelection.
     */
    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(dataSelectionDTO));

    protected ForecastForm nonMandatedForm;

    /**
     * Default constructor.
     */
    public NonMandatedView(SessionDTO session, DataSelectionDTO dataSelectionDTO)  {
        this.session = session;
        this.dataSelectionDTO = dataSelectionDTO;
        try {

        } catch (Exception ex) {
            LoggerFactory.getLogger(NonMandatedView.class.getName()).error( StringUtils.EMPTY, ex);
        }
        addComponent(nonMandatedForm);
        enter();
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter() {
        dataSelectionBinder.setItemDataSource(new BeanItem<>(dataSelectionDTO));
        nonMandatedForm.configureOnEnter();
    }

}
