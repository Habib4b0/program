/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author alok.v
 */
public class PromoteTpToChWindow extends CustomWindow {

    SessionDTO session;
    private final PromoteTpToChDto promoteTpToChDto = new PromoteTpToChDto();
    ExtFilterTable resultTable;
    /**
     * The Constant LOGGER.
     */

    public PromoteTpToChWindow(SessionDTO session, final ExtFilterTable resultTable) {
        super("Promote TP to Contract Holder");
        this.session = session;
        this.resultTable = resultTable;
        init();
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() {
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(false);
        setContent(new PromoteTpToChEdit(session, promoteTpToChDto, this, resultTable));
    }
}
