/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.view;

import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.ui.form.GtnBalanceForm;
import com.stpl.app.accountclose.gtnbalancereport.ui.layout.GtnBalanceViewWindow;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class GTNBalanceView extends VerticalLayout {

    SessionDTO session;

    public static final String NAME = "GTNREPORTVIEW";

    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<DataSelectionDTO>(dataSelectionDTO));
    GtnBalanceForm gtnForm;

    private static final Logger LOGGER = Logger.getLogger(GTNBalanceView.class);

    public GTNBalanceView(SessionDTO session, DataSelectionDTO dataSelectionDTO, GtnBalanceViewWindow viewWindow) throws SystemException, Exception {
        this.session = session;
        this.dataSelectionDTO = dataSelectionDTO;
        try {

            gtnForm = new GtnBalanceForm(session, dataSelectionDTO, viewWindow);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        addComponent(gtnForm);
        enter();
    }

    public void enter() {
        dataSelectionBinder.setItemDataSource(new BeanItem<DataSelectionDTO>(dataSelectionDTO));
        gtnForm.configureOnEnter(session.getProjectionId(), dataSelectionDTO);
    }
}
