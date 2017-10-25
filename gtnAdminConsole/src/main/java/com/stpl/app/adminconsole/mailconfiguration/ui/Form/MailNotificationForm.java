/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.mailconfiguration.ui.Form;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.mailconfiguration.logic.MailConfigurationLogic;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.quartz.QuartzListener;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.model.WorkflowProfile;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 *
 * @author ahalya
 */
public class MailNotificationForm extends CustomComponent {

    @UiField("savebtn")
    private Button saveBtn;

    @UiField("successEmailTo")
    private TextField successEmailTo;
    @UiField("failureEmailTo")
    private TextField failureEmailTo;

    @UiField("successCc")
    private TextField successCc;
    @UiField("failureCc")
    private TextField failureCc;

    @UiField("successToLb")
    private Label successToLb;
    @UiField("failureToLb")
    private Label failureToLb;

    @UiField("successCcLb")
    private Label successCcLb;
    @UiField("failureCcLb")
    private Label failureCcLb;

    @UiField("successSubject")
    private TextArea successSubject;
    @UiField("failSubject")
    private TextArea failSubject;

    @UiField("successText")
    private TextArea successText;
    @UiField("failText")
    private TextArea failText;

    @UiField("processName")
    private ComboBox processName;

    HashMap<String, ProcessSchedulerDTO> hs = new HashMap<String, ProcessSchedulerDTO>();
    MailConfigurationLogic maillogic = new MailConfigurationLogic();
    String zero = "0";
    SessionDTO sessionDTO;
    private final static org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(MailNotificationForm.class);

    public MailNotificationForm(final SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        try {
            init();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void init()  {
        LOGGER.debug("Inside Init");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        addStyleName("bootstrap-admin");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/MailNotification.xml"), this));
        configureFields();
        LOGGER.debug("Ends Init");

    }

    private void configureFields() {
        LOGGER.debug("Inside configureFields");
        configureProcessName();
        successEmailTo.setInputPrompt(ConstantsUtils.MAIL_CONFIG_INPUT_PROMPT);
        failureEmailTo.setInputPrompt(ConstantsUtils.MAIL_CONFIG_INPUT_PROMPT);
        successCc.setInputPrompt(ConstantsUtils.MAIL_CONFIG_INPUT_PROMPT);
        failureCc.setInputPrompt(ConstantsUtils.MAIL_CONFIG_INPUT_PROMPT);
        processName.setImmediate(true);
        processName.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                LOGGER.debug("Inside name ");
                if (event != null && processName != null && processName.getValue() != null) {
                    if (!zero.equals(processName.getValue())) {
                        String sTo = hs.get(String.valueOf(processName.getValue())).getSuccessTo().replaceAll(ConstantsUtils.SPACE, ConstantsUtils.COMMA_SPACE);
                        String sCc = hs.get(String.valueOf(processName.getValue())).getSuccessCC().replaceAll(ConstantsUtils.SPACE, ConstantsUtils.COMMA_SPACE);
                        String sSubject = hs.get(String.valueOf(processName.getValue())).getSuccessSubject();
                        String sText = hs.get(String.valueOf(processName.getValue())).getSuccessText();
                        String fTo = hs.get(String.valueOf(processName.getValue())).getFailTo().replaceAll(ConstantsUtils.SPACE, ConstantsUtils.COMMA_SPACE);
                        String fCc = hs.get(String.valueOf(processName.getValue())).getFailCC().replaceAll(ConstantsUtils.SPACE, ConstantsUtils.COMMA_SPACE);
                        String fSubject = hs.get(String.valueOf(processName.getValue())).getFailSubject();
                        String fText = hs.get(String.valueOf(processName.getValue())).getFailText();
                        successEmailTo.setValue("null".equals(sTo) ? ConstantsUtils.EMPTY : sTo);
                        successCc.setValue("null".equals(sCc) ? ConstantsUtils.EMPTY : sCc);
                        successSubject.setValue("null".equals(sSubject) ? ConstantsUtils.EMPTY : sSubject);
                        successText.setValue("null".equals(sText) ? ConstantsUtils.EMPTY : sText);
                        failureEmailTo.setValue("null".equals(fTo) ? ConstantsUtils.EMPTY : fTo);
                        failureCc.setValue("null".equals(fCc) ? ConstantsUtils.EMPTY : fCc);
                        failSubject.setValue("null".equals(fSubject) ? ConstantsUtils.EMPTY : fSubject);
                        failText.setValue("null".equals(fText) ? ConstantsUtils.EMPTY : fText);
                    } else if (zero.equals(processName.getValue())) {
                        successEmailTo.setValue(ConstantsUtils.EMPTY);
                        successCc.setValue(ConstantsUtils.EMPTY);
                        successSubject.setValue(ConstantsUtils.EMPTY);
                        successText.setValue(ConstantsUtils.EMPTY);
                        failureEmailTo.setValue(ConstantsUtils.EMPTY);
                        failureCc.setValue(ConstantsUtils.EMPTY);
                        failSubject.setValue(ConstantsUtils.EMPTY);
                        failText.setValue(ConstantsUtils.EMPTY);

                    }

                }

            }
        });
        saveBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (event != null) {
                        if (processName != null && processName.getValue() != null && !zero.equals(processName.getValue())) {
                             if (validateEmailAddresses()) {
                                final String userId = String.valueOf(sessionDTO.getUserId());
                                
                                ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
                                dto.setProcessSid(Integer.valueOf(String.valueOf(processName.getValue())));
                                dto.setSuccessTo(emailSeperator(successEmailTo.getValue()));
                                dto.setSuccessCC(emailSeperator(successCc.getValue()));
                                dto.setSuccessSubject(successSubject.getValue());
                                dto.setSuccessText(successText.getValue());
                               
                                dto.setFailTo(emailSeperator(failureEmailTo.getValue()));
                                dto.setFailCC(emailSeperator(failureCc.getValue()));
                                dto.setFailSubject(failSubject.getValue());
                                dto.setFailText(failText.getValue());
                                WorkflowProfile profile = maillogic.updateEmailDetailsToprocess(userId, dto);

//                                QuartzListener.reScheduleJobs(maillogic.updateEmailDetailsToprocess(userId, dto));
//                                QuartzListener.updateJob(profile);
                                successEmailTo.setValue("");
                                successCc.setValue("");
                                successSubject.setValue("");
                                successText.setValue("");

                                failureEmailTo.setValue("");
                                failureCc.setValue("");
                                failSubject.setValue("");
                                failText.setValue("");

                                configureProcessName();

                            }

                        } else {

                            com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please select Process Name ");

                        }

                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
        LOGGER.debug("Out configureFields");
    }

    private void configureProcessName() {
        try {
            List<ProcessSchedulerDTO> list = maillogic.loadProcessNameDDlb();
            if (processName.size() > 0) {
                processName.removeAllItems();
            }
            processName.addItem(zero);
            processName.setItemCaption(zero, "-Select One-");
            processName.setNullSelectionAllowed(true);
            processName.setNullSelectionItemId(zero);
            for (int i = 0; i < list.size(); i++) {
                processName.addItem(String.valueOf(list.get(i).getProcessSid()));
                processName.setItemCaption(String.valueOf(list.get(i).getProcessSid()), list.get(i).getProcessName());
                hs.put(String.valueOf(list.get(i).getProcessSid()), list.get(i));
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private boolean validateEmailAddresses() {
        if (successEmailTo.getValue() != null && !successEmailTo.getValue().equals(StringUtils.EMPTY)) {
            
            if (!validateEmail(emailSeperator(successEmailTo.getValue()))) {

                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter valid Email Address for " + successToLb.getValue());
                return false;
            }
        }else{
           com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter  Email Address for " + successToLb.getValue());
                return false;
        }
        if (successCc.getValue() != null && !successCc.getValue().equals(StringUtils.EMPTY) && !validateEmail(emailSeperator(successCc.getValue()))) {
                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter valid Email Address for " + successCcLb.getValue());
                return false;
        }
        if (failureEmailTo.getValue() != null && !failureEmailTo.getValue().equals(StringUtils.EMPTY)) {
            if (!validateEmail(emailSeperator(failureEmailTo.getValue()))) {

                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter valid Email Address for " + failureToLb.getValue());
                return false;
            }
        }
        else{
           com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter  Email Address for " + failureToLb.getValue());
                return false;
        }
        if (failureCc.getValue() != null && !failureCc.getValue().equals(StringUtils.EMPTY) && !validateEmail(emailSeperator(failureCc.getValue()))) {
                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter valid Email Address for " + failureCcLb.getValue());
                return false;
        }

         if (successSubject.getValue().equals(StringUtils.EMPTY)) {
                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter  Subject in Success Mail Notification ");
                return false;
        }
         if ( failSubject.getValue().equals(StringUtils.EMPTY)) {
                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter Subject in Failure Mail Notification");
                return false;
        }
        
          if (successText.getValue().equals(StringUtils.EMPTY) ) {
                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter Email Body in Success Mail Notification ");
                return false;
        }
          if ( failText.getValue().equals(StringUtils.EMPTY)) {
                com.stpl.app.adminconsole.util.AbstractNotificationUtils.getErrorNotification("Error", "Please enter Email Body  in Failure Mail Notification");
                return false;
        }
        return true;

    }

    private boolean validateEmail(String emails) {

        StringTokenizer st = new StringTokenizer(emails, " ");
        while (st.hasMoreElements()) {
            String email = st.nextToken();
            if (!CommonUtils.isEmailValid(email)) {
                return false;
            }
        }
        return true;

    }
    public String emailSeperator(String email) {
        String finalSuccessEmail = "";
        String[] tempString = null;
        String tempmailid = email;
        if (tempmailid.contains(",")) {
            tempString = tempmailid.split(",");
        } else {
            finalSuccessEmail = tempmailid;
        }
        if (tempString != null && tempString.length != 0) {
            for (int i = 0; i < tempString.length; i++) {
                finalSuccessEmail = (i != 0) ? finalSuccessEmail + ConstantsUtils.SPACE + tempString[i].trim() : tempString[i].trim();
            }
        }
        return finalSuccessEmail;
    }
}
