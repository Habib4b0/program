/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.mailconfiguration.ui.Form;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.mailconfiguration.dto.MailConfigurationDTO;
import com.stpl.app.adminconsole.mailconfiguration.logic.MailConfigurationLogic;
import com.stpl.app.adminconsole.mailconfiguration.ui.View.MailConfigurationView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author manikandaprabu.g
 */
public class MailConfigurationForm extends CustomComponent {

    @UiField("savebtn")
    private Button saveBtn;
    @UiField("viewbtn")
    private Button viewBtn;

    @UiField("usesmtp")
    private ComboBox usesmtp;
    @UiField("hostname")
    private TextField hostname;
    @UiField("email")
    private TextField email;
    @UiField("password")
    private PasswordField password;
    @UiField("portno")
    private TextField portno;
    @UiField("testmailadd")
    private TextField testmailadd;

    @UiField("backbtn")
    private Button backBtn;
    @UiField("horizonallayout")
    private HorizontalLayout horizonallayout;
    @UiField("hlayout")
    private HorizontalLayout hlayout;

    MailConfigurationLogic maillogic = new MailConfigurationLogic();
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @UiField("errorMsg")
    public ErrorLabel errorMsg = new ErrorLabel();

    private static MailConfigurationDTO mailconfigurationdto = new MailConfigurationDTO();

    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<MailConfigurationDTO>(mailconfigurationdto));
    CommonUtil commonUtil = new CommonUtil();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    private BeanItemContainer<MailConfigurationDTO> tradeClassResultsBean = new BeanItemContainer<MailConfigurationDTO>(MailConfigurationDTO.class);
    private final static Logger LOGGER = Logger.getLogger(MailConfigurationForm.class);
    SessionDTO sessionDTO;

    public MailConfigurationForm(final SessionDTO sessionDTO) throws Exception {
        super();
        this.sessionDTO = sessionDTO;
        init();
    }

    private void init() throws Exception {
        LOGGER.info("Inside Init");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        addStyleName("bootstrap-admin");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/MailConfiguration.xml"), this));
        maillogic.LoadOnClick(mailconfigurationdto);
        LOGGER.info("Ending Init");
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        final Map<String, AppPermission> fieldItemHM = stplSecurity
                .getFieldOrColumnPermission(userId, "Mail Configuration", false);
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Mail Configuration" + "," + "Functional Screen");
        configureFields();
        if (functionHM.get("savebtn") != null && !((AppPermission) functionHM.get("savebtn")).isFunctionFlag()) {
            saveBtn.setVisible(false);
        } else {
            saveButton();
        }
        getBinder();
        if (functionHM.get("viewbtn") != null && !((AppPermission) functionHM.get("viewbtn")).isFunctionFlag()) {
            viewBtn.setVisible(false);
        } else {
            ViewButton();
        }

        backButton();

        horizonallayout.setVisible(false);

    }

    private void configureFields() throws Exception {
        LOGGER.info("Entering configureFields");
        usesmtp.addItem("-Select One-");
        usesmtp.addItem("Yes");
        usesmtp.addItem("No");
        usesmtp.setImmediate(true);
        usesmtp.setNullSelectionAllowed(true);
        usesmtp.setNullSelectionItemId("-Select One-");
        LOGGER.info("Ending configureFields");
    }

    private void saveButton() {
        LOGGER.info("Entering Save Button");
        saveBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        saveBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getErrorDisplay().clearError();
                    binder.commit();
                    LOGGER.info("Entering inside  SAVE  method from ADD ");
                    final MailConfigurationDTO maildto = new MailConfigurationDTO();
                    if (isBinderNull()) {
                        if (email.getValue().matches(EMAIL_PATTERN)) {
                            new AbstractNotificationUtils() {
                                @Override
                                public void yesMethod() {
                                    try {
                                        maildto.setEmail(email.getValue());
                                        maildto.setUsesmtp(usesmtp.getValue().toString());
                                        maildto.setHostname(hostname.getValue());
                                        maildto.setPortno(portno.getValue());
                                        maildto.setTestmailadd(testmailadd.getValue());
                                        maildto.setPassword(password.getValue());
                                        maillogic.save(maildto, sessionDTO);
                                        AbstractNotificationUtils.getAlertNotification("Notification", "Sucessfully saved");
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }

                                @Override
                                public void noMethod() {
                                }
                            }.getConfirmationMessage("Confirmation", "Are you sure you want to Save the Configuration?");
                        } else {
                            binder.getErrorDisplay().setError("Please Enter a Valid Email Address");
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                LOGGER.info("Ending  SAVE  method");
            }
        });
        LOGGER.info("Ending Save Button");
    }

    private void ViewButton() {
        LOGGER.info("Entering View Button");
        viewBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        viewBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                binder.setReadOnly(true);
                horizonallayout.setVisible(true);
                hlayout.setVisible(false);
            }
        });

        LOGGER.info("Endind View Button");
    }

    private ErrorfulFieldGroup getBinder() throws Exception {
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<MailConfigurationDTO>(mailconfigurationdto));
        binder.setBuffered(true);

        return binder;
    }

    public void backButton() {
        backBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        backBtn.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                binder.setReadOnly(false);
                hlayout.setVisible(true);
                horizonallayout.setVisible(false);
            }
        });
    }

    private boolean isBinderNull() {
        boolean flag = false;
        if (mailconfigurationdto.getUsesmtp() != null) {
            if (!mailconfigurationdto.getHostname().trim().isEmpty()) {
                if (!mailconfigurationdto.getEmail().trim().isEmpty()) {
                    if (!mailconfigurationdto.getPassword().trim().isEmpty()) {
                        if (!mailconfigurationdto.getPortno().trim().isEmpty()) {
                            if (!mailconfigurationdto.getTestmailadd().trim().isEmpty()) {
                                flag = true;
                            } else {
                                binder.getErrorDisplay().setError("Please Enter Test Email Address");
                            }
                        } else {
                            binder.getErrorDisplay().setError("Please Enter Port No");
                        }
                    } else {
                        binder.getErrorDisplay().setError("Please Enter Password");
                    }
                } else {
                    binder.getErrorDisplay().setError("Please Enter Email Address");
                }
            } else {
                binder.getErrorDisplay().setError("Please Enter HostName");
            }
        } else {
            binder.getErrorDisplay().setError("Please select SMTP");
        }
        return flag;
    }

}
