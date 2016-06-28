/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 *
 * @author SYSBIZ\abishekram.r
 */
public class CalendarMainView extends VerticalLayout implements View {

    static final String NAME = "";
    SessionDTO sessionDTO;

    private static final Logger LOGGER = Logger.getLogger(CalendarMainView.class);

    public CalendarMainView(final SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        setSpacing(true);
        setStyleName("bootstrap");
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.gc();
        try {
            addComponent(new CalendarIndex(sessionDTO, false));
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

}
