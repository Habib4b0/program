/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.VerticalLayout;



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


    /**
     * Default constructor.
     */
    public NonMandatedView(SessionDTO session, DataSelectionDTO dataSelectionDTO)  {
        this.session = session;
        this.dataSelectionDTO = dataSelectionDTO;
        enter();
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public final void enter() {
        dataSelectionBinder.setItemDataSource(new BeanItem<>(dataSelectionDTO));
    }

}
