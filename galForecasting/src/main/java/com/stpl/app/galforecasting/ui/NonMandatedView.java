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
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soundarrajan
 */
public class NonMandatedView extends VerticalLayout {

    SessionDTO session;
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
    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<DataSelectionDTO>(dataSelectionDTO));

    ForecastForm nonMandatedForm;

    /**
     * Default constructor.
     */
    public NonMandatedView(SessionDTO session, DataSelectionDTO dataSelectionDTO, NonMandatedViewWindow viewWindow, DataSelectionForm dataSelectionForm) throws SystemException, Exception {
        this.session = session;
        this.dataSelectionDTO = dataSelectionDTO;
        try {

        } catch (Exception ex) {
            Logger.getLogger(NonMandatedView.class.getName()).log(Level.SEVERE, null, ex);
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
        dataSelectionBinder.setItemDataSource(new BeanItem<DataSelectionDTO>(dataSelectionDTO));
        nonMandatedForm.configureOnEnter(session.getProjectionId(), dataSelectionDTO);
    }

}
