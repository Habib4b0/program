/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.mailconfiguration.logic;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import static com.stpl.app.adminconsole.filemanagement.ui.view.FileManagementIndexView.LOGGER;
import com.stpl.app.adminconsole.mailconfiguration.dto.MailConfigurationDTO;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.model.WfMailConfig;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.WfMailConfigLocalServiceUtil;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author manikandaprabu.g
 */
public class MailConfigurationLogic {
    
    Properties mailServerProperties;
    Session mailSession;
    MimeMessage mailMessage;
    
    public static void save(MailConfigurationDTO maildto,final SessionDTO sessionDTO) throws SystemException {
        WfMailConfig wfMailConfig;
       
        if (WfMailConfigLocalServiceUtil.getWfMailConfigsCount() == 0) {
            
            wfMailConfig = WfMailConfigLocalServiceUtil.createWfMailConfig(0);
            wfMailConfig.setPassword(maildto.getPassword());
            wfMailConfig.setEmailAddress(maildto.getEmail());
            wfMailConfig.setHostName(maildto.getHostname());
            wfMailConfig.setPortNumber(maildto.getPortno());
            wfMailConfig.setSmtpFlag(maildto.getUsesmtp());
            wfMailConfig.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
            wfMailConfig.setCreatedDate(new Date());
            wfMailConfig.setCreatedBy(Integer.parseInt(sessionDTO.getUserId().toString()));
            wfMailConfig.setModifiedDate(new Date());
           
            WfMailConfigLocalServiceUtil.addWfMailConfig(wfMailConfig);
        } else {
            final DynamicQuery wfMailquery = DynamicQueryFactoryUtil.forClass(WfMailConfig.class);
            final List<WfMailConfig> wfmaillist = WfMailConfigLocalServiceUtil.dynamicQuery(wfMailquery, 0, 1);
            wfMailConfig = wfmaillist.get(0);

            if (maildto.getPassword()==null || maildto.getPassword().isEmpty() || maildto.getPassword().equals("null")) {
                wfMailConfig.setPassword(wfMailConfig.getPassword());
            } else {
                wfMailConfig.setPassword(maildto.getPassword());
            }
            
            if (maildto.getEmail() == null || maildto.getEmail().isEmpty() || maildto.getEmail().equals("null")) {
                wfMailConfig.setEmailAddress(wfmaillist.get(0).getEmailAddress());
            } else {
                wfMailConfig.setEmailAddress(maildto.getEmail());
            }

            if (maildto.getHostname() == null || maildto.getHostname().isEmpty() || maildto.getHostname().equals("null")) {
                wfMailConfig.setHostName(wfmaillist.get(0).getHostName());
            } else {
                wfMailConfig.setHostName(maildto.getHostname());
            }

            if (maildto.getPortno() == null || maildto.getPortno().isEmpty() || maildto.getPortno().equals("null")) {
                wfMailConfig.setPortNumber(wfmaillist.get(0).getPortNumber());
            } else {
                wfMailConfig.setPortNumber(maildto.getPortno());
            }

            if (maildto.getUsesmtp() == null || maildto.getUsesmtp().isEmpty() || maildto.getUsesmtp().equals("null")) {
                wfMailConfig.setSmtpFlag(wfmaillist.get(0).getSmtpFlag());
            } else {
                wfMailConfig.setSmtpFlag(maildto.getUsesmtp());
            }
            wfMailConfig.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
            wfMailConfig.setCreatedDate(wfMailConfig.getCreatedDate());
            wfMailConfig.setCreatedBy(wfMailConfig.getCreatedBy());
            wfMailConfig.setModifiedDate(new Date());
            wfMailConfig.setModifiedBy(Integer.parseInt(sessionDTO.getUserId().toString()));
            WfMailConfigLocalServiceUtil.updateWfMailConfig(wfMailConfig);
        }

    }
    
    public static WfMailConfig LoadOnClick(MailConfigurationDTO maildto) throws SystemException {
          LOGGER.debug("Inside Loading of Data");
             WfMailConfig wfMailConfig=null;
             final DynamicQuery wfMailquery = DynamicQueryFactoryUtil.forClass(WfMailConfig.class);
             List<WfMailConfig> wfMailConfigList= WfMailConfigLocalServiceUtil.dynamicQuery(wfMailquery);
             
             if(wfMailConfigList!=null && wfMailConfigList.size()!=0){
             wfMailConfig=wfMailConfigList.get(0);
            
             maildto.setUsesmtp(wfMailConfig.getSmtpFlag());
             maildto.setEmail(wfMailConfig.getEmailAddress());
             maildto.setHostname(wfMailConfig.getHostName());
             maildto.setPassword(wfMailConfig.getPassword());
             maildto.setPortno(wfMailConfig.getPortNumber());
             }else{
                 LOGGER.error("No mail Id's available in the DB");
             }
             
             return wfMailConfig;
    }
    
    public void sendingEmailLogic(MailConfigurationDTO mailConfigurationDTO,String toMail) throws MessagingException {
        LOGGER.debug("Setting Mail Server Properties..");
        
        final String cc = StringUtils.EMPTY;
        final String fromMail = mailConfigurationDTO.getEmail();
        final String host = mailConfigurationDTO.getHostname();
        final String password = mailConfigurationDTO.getPassword();
        final int PortNumber = Integer.valueOf(mailConfigurationDTO.getPortno());
        //Get the session object  
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.host", host);
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.port", PortNumber);

        mailSession = mailSession.getInstance(mailServerProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromMail, password);
                    }
                });
        try {
            mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(fromMail));
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            mailMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            mailMessage.setSubject("Galderma Test");
            mailMessage.setContent("This is Galderma test mail", "text/html");
            Transport.send(mailMessage);
            LOGGER.debug("Mail Sent Successfully");

        } catch (MessagingException mex) {
            LOGGER.error(mex);
        }
    }  

    public List<ProcessSchedulerDTO> loadProcessNameDDlb() {
        List<ProcessSchedulerDTO> processList = new ArrayList<ProcessSchedulerDTO>();
        List list = getSearchResult();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
                dto.setProcessSid(Integer.valueOf(String.valueOf(obj[0])));
                dto.setProcessName(String.valueOf(obj[1]));
                dto.setSuccessTo(String.valueOf(obj[NumericConstants.TWO]));
                dto.setSuccessCC(String.valueOf(obj[NumericConstants.THREE]));
                dto.setFailTo(String.valueOf(obj[NumericConstants.FOUR]));
                dto.setFailCC(String.valueOf(obj[NumericConstants.FIVE]));
                dto.setSuccessSubject(String.valueOf(obj[NumericConstants.SIX]));
                dto.setSuccessText(String.valueOf(obj[NumericConstants.SEVEN]));
                dto.setFailSubject(String.valueOf(obj[NumericConstants.EIGHT]));
                dto.setFailText(String.valueOf(obj[NumericConstants.NINE]));
                processList.add(dto);
            }

        }
        return processList;
    }

    public List getSearchResult() {
        LOGGER.debug("Entering getSearchResult");
        try {
            String query = CommonUtil.processNameDDlbQuery();
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

            return list;

        } catch (Exception ex) {
           
            LOGGER.error(ex);
            return null;
        }
    }

    public WorkflowProfile updateEmailDetailsToprocess(String userid, ProcessSchedulerDTO dto) {
        try {
            LOGGER.debug("Entering update");

            WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(dto.getProcessSid());
            profile.setEmailNotificationSuccessTo(dto.getSuccessTo());
            profile.setEmailNotificationSuccessCc(dto.getSuccessCC());
            profile.setSuccessMailSubject(dto.getSuccessSubject());
            profile.setSuccessMailBody(dto.getSuccessText());

            profile.setEmailNotificationFailureTo(dto.getFailTo());
            profile.setEmailNotificationFailureCc(dto.getFailCC());
            profile.setFailureMailSubject(dto.getFailSubject());
            profile.setFailureMailBody(dto.getFailText());

            profile.setModifiedDate(new Date());
            profile.setModifiedBy(Integer.valueOf(userid));
            WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);
          LOGGER.debug("Ending update");
            return profile;
            
        } catch (Exception ex) {
            
            LOGGER.error(ex);
        }
        return null;
    }
}
