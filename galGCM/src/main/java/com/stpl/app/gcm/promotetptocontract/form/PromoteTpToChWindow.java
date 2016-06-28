/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author alok.v
 */
public class PromoteTpToChWindow extends CustomWindow {

    SessionDTO session;
    private PromoteTpToChDto promoteTpToChDto = new PromoteTpToChDto();
    ExtFilterTable resultTable;
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(PromoteTpToChWindow.class);

    public PromoteTpToChWindow(SessionDTO session, final ExtFilterTable resultTable) throws Exception {
        super("Promote TP to Contract Holder");
        this.session = session;
        this.resultTable = resultTable;
        init();
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() throws Exception {
        center();
        setWidth(100, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        setClosable(false);
        setContent(new PromoteTpToChEdit(session, promoteTpToChDto, this, resultTable));
    }
}
