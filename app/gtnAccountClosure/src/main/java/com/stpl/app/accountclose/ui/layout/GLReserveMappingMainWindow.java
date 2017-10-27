/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.layout;

import com.stpl.app.accountclose.glReserveMapping.ui.form.GLReserveMappingMain;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author kasiammal.m
 */
public class GLReserveMappingMainWindow
        extends CustomWindow {

    SessionDTO session;
    String Count = StringUtils.EMPTY;
    String mode = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */

    public GLReserveMappingMainWindow(final SessionDTO session, String mode) throws Exception {
        super("Reserve Configuration");
        this.session = session;
        this.mode = mode;
        init();
        setResizable(true);
    }

    private void init() throws Exception {

        center();
        setWidth(100, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        setClosable(true);
        setContent(new GLReserveMappingMain(session, this.mode));
    }
}
