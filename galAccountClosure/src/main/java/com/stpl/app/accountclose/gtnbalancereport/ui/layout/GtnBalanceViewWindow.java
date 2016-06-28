/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.layout;

import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.ui.view.GTNBalanceView;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Sizeable;
import org.asi.ui.customwindow.CustomWindow;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class GtnBalanceViewWindow extends CustomWindow {

    private static final Logger LOGGER = Logger.getLogger(GtnBalanceViewWindow.class);
    SessionDTO session;
    DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

    public GtnBalanceViewWindow(String projectionName, SessionDTO session) {
        super(projectionName);
        this.session = session;
        init();
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() {
        try {
            center();
            setWidth(80, Sizeable.Unit.PERCENTAGE);
            addStyleName("bootstrap-ui");
            addStyleName("bootstrap");
            addStyleName("bootstrap-forecast bootstrap-nm");
            setClosable(false);
            setContent(new GTNBalanceView(session, dataSelectionDTO, this));
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    
}
