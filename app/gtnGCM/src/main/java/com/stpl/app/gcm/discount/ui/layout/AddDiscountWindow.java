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
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author santanukumar
 */
public class AddDiscountWindow extends CustomWindow {

    private final SessionDTO session;
    private final List<RemoveDiscountDto> removeList;
    /**
     * The Constant LOGGER.
     */

    public AddDiscountWindow(final SessionDTO session, List<RemoveDiscountDto> removeList) throws SystemException {
    
        this.session = session;
        this.removeList = removeList;
        init();
    }

    private void init() throws SystemException {
        setContent(new AddDiscountAddView(this, session, removeList));
        center();
        setCaption("Add Discount");
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
    }

}
