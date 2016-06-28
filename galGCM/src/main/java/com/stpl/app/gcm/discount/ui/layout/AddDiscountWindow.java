/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.layout;

import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.ui.view.AddDiscountAddView;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import java.util.List;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author santanukumar
 */
public class AddDiscountWindow extends CustomWindow {

    ExtFilterTable resultTable;
    SessionDTO session;
    List<RemoveDiscountDto> removeList;
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AddDiscountWindow.class);

    public AddDiscountWindow(final SessionDTO session, List<RemoveDiscountDto> removeList) throws Exception {
    
        this.session = session;
        this.removeList = removeList;
        init();
    }

    private void init() throws Exception {
        loadSessionDTO();
        setContent(new AddDiscountAddView(this, session, removeList));
        center();
        setCaption("Add Discount");
        setWidth(100, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
    }

    private void loadSessionDTO() {

    }
}
