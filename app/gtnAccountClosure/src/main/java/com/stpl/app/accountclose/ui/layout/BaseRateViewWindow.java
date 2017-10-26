/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.layout;

import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.home.ui.BaseRateView;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Sizeable;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author alok.v
 */
public class BaseRateViewWindow extends CustomWindow{
       /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(BaseRateViewWindow.class);

    SessionDTO session;
    BaseRateDTO baseRateDTO = new BaseRateDTO();
    
    public BaseRateViewWindow(String projectionName, SessionDTO session) {
        super(projectionName);
        this.session = session;
        init();
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() {
        try {
            center();
            setWidth(100, Sizeable.Unit.PERCENTAGE);
            setPositionX(Constants.ZERO);
            setPositionY(Constants.ZERO);
            addStyleName("bootstrap-ui");
            addStyleName("bootstrap");
            addStyleName("bootstrap-forecast bootstrap-nm");
            setClosable(false);
            setContent(new BaseRateView(session, baseRateDTO, this));
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

}
