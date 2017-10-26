/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.dto.MailServerConfigDTO;
import com.stpl.app.accountclose.email.config.Emailer;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.YES;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.NO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class MailServerConfigIndex extends CustomComponent implements View {

    SessionDTO session;
    private MailServerConfigDTO mailServerConfigDTO;
    public CustomFieldGroup mailServerConfigBinder;
    @UiField("smtp")
    public ComboBox useSmtp;
    @UiField("tesMail")
    private Button tesMail;
    @UiField("saveBtn")
    private Button saveBtn;
    @UiField("editBtn")
    private Button editBtn;
    @UiField("viewBtn")
    private Button viewBtn;
    final Emailer mail = new Emailer();
    private static final Logger LOGGER = Logger.getLogger(MailServerConfigIndex.class);

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     *
     * @param screenIndicator
     * @param mailServerConfigDTO
     * @param custom
     * @throws Exception
     */
    public MailServerConfigIndex(String screenIndicator, MailServerConfigDTO mailServerConfigDTO, CustomFieldGroup custom) throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/mailServerConfiguration.xml"), this));
        this.mailServerConfigDTO = mailServerConfigDTO;
        this.mailServerConfigBinder = custom;
        configureFields();
    }

    /**
     * Configure Email Configuration Fields
     *
     */
    protected void configureFields() {
        try {
            useSmtp.setImmediate(true);
            useSmtp.addItem(YES.getConstant());
            useSmtp.addItem(NO.getConstant());
            useSmtp.select(YES.getConstant());
            useSmtp.setEnabled(true);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Test mail Logic
     *
     * @param event
     */
    @UiHandler("tesMail")
    public void testMailConfig(Button.ClickEvent event) {
        String smtpType = String.valueOf(useSmtp.getValue());
        String status = mail.testMail("alokkumar.vishwakarma@sysbiz.com", "Test Mail", "Test Mail", smtpType);
    }

    /**
     *
     * @param event
     */
    @UiHandler("saveBtn")
    public void saveMailConfig(Button.ClickEvent event) {
    }

    /**
     *
     * @param event
     */
    @UiHandler("editBtn")
    public void editMailConfig(Button.ClickEvent event) {
    }

    /**
     *
     * @param event
     */
    @UiHandler("viewBtn")
    public void viewMailConfig(Button.ClickEvent event) {
    }
}
