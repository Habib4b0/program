/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.layout;

import com.stpl.app.accountclose.gtnbalancereport.ui.view.GtnBalanceEditView;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.vaadin.server.Sizeable;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author santanukumar
 */
public class GtnBalanceEditWindow extends CustomWindow {

    ExtFilterTable resultTable;
      SessionDTO session;
    /**
     * The Constant LOGGER.
     */

    public GtnBalanceEditWindow(final SessionDTO session) throws Exception {
        super("GTN Balance Report");
          this.session = session;
        init();
       
        setMinimizeToTray();
    }

    private void init() throws Exception {
        
        center();
        setWidth(80, Unit.PERCENTAGE);
       
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        setClosable(true);
        setModal(true);
        loadSessionDTO();
        setContent(new GtnBalanceEditView(this,session));
    }

    private void loadSessionDTO() {
       
    }
}
