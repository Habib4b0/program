/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.view;

import com.stpl.app.accountclose.gtnbalancereport.ui.form.GtnBalanceForm;
import com.stpl.app.accountclose.gtnbalancereport.ui.layout.GtnBalanceEditWindow;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author santanukumar
 */
public class GtnBalanceEditView extends VerticalLayout {

    /**
     * View name for navigation.
     */
    public static final String NAME = "GTNBALANCE";

    /**
     * Binder for DataSelection.
     */
    GtnBalanceEditWindow editWindow;

    GtnBalanceForm gtnBalanceForm;
    SessionDTO session;
    
    /**
     * Default constructor.
     *
     * @param session
     * @param resultTable
     * @param dataSelectionDTO
     * @param editWindow
     * @throws java.lang.Exception
     */
    public GtnBalanceEditView(final GtnBalanceEditWindow editWindow,final SessionDTO session) throws Exception {
        this.editWindow = editWindow;
          this.session = session;
        gtnBalanceForm = new GtnBalanceForm( this.editWindow,this.session);
        addComponent(gtnBalanceForm);
        enter();
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter() {
    }

}
