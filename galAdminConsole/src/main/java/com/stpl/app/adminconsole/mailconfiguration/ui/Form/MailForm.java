/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.mailconfiguration.ui.Form;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.jboss.logging.Logger;
/**
 *
 * @author ahalya
 */
public class MailForm extends VerticalLayout {

    MailConfigurationForm mailconfiguration = null;
    MailNotificationForm mailNotificationForm = null;
    TabSheet tabSheet = new TabSheet();
    SessionDTO sessionDTO;
    private final static Logger LOGGER = Logger.getLogger(MailForm.class);

    public MailForm(final SessionDTO sessionDTO) {
        try {
            this.sessionDTO = sessionDTO;
            mailconfiguration = new MailConfigurationForm(this.sessionDTO);
            mailNotificationForm = new MailNotificationForm(this.sessionDTO);
            init();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void init() {
        tabSheet.addTab(mailconfiguration, "Email Configuration", null, 0);
        tabSheet.addTab(mailNotificationForm, "Email Notification", null, 1);
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setSizeFull();
        addComponent(tabSheet);
    }

}
