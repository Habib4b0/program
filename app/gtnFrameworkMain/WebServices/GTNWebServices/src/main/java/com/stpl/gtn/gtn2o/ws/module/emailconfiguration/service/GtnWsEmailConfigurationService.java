/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.emailconfiguration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.entity.emailconfiguration.WfMailConfig;
import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service()
@Scope(value = "singleton")
public class GtnWsEmailConfigurationService {
	public GtnWsEmailConfigurationService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsEmailConfigurationService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> comboBoxOnChangeLogic(GtnWsMailConfigurationRequest gtnWsRequest) {

		Session session = getSessionFactory().openSession();
		GtnWsEMailConfigurationBean configurationBean = gtnWsRequest.getConfigurationBean();
		int processSid = configurationBean.getEmailNotificationTabProcessName().isEmpty() ? 0
				: Integer.parseInt(configurationBean.getEmailNotificationTabProcessName());

		Criteria selectQuery = session.createCriteria(WorkflowProfile.class);
		ProjectionList selectList = Projections.projectionList();

		selectList.add(Projections.property("processSid"));
		selectList.add(Projections.property("emailNotificationSuccessTo"));
		selectList.add(Projections.property("successMailSubject"));
		selectList.add(Projections.property("emailNotificationSuccessCc"));
		selectList.add(Projections.property("successMailBody"));
		selectList.add(Projections.property("emailNotificationFailureTo"));
		selectList.add(Projections.property("failureMailSubject"));
		selectList.add(Projections.property("emailNotificationFailureCc"));
		selectList.add(Projections.property("failureMailBody"));
		selectList.add(Projections.property("failureMailBody"));
		selectQuery.setProjection(selectList);
		selectQuery.add(Restrictions.eq("processSid", processSid));

		return selectQuery.list();
	}

	@SuppressWarnings("unchecked")
	public void saveMailConfigurationLogic(GtnWsMailConfigurationRequest mcRequest,
			GtnWsGeneralRequest gtnWsGeneralRequest) {
		Integer primaryId = 0;
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		WfMailConfig wfMailConfig = new WfMailConfig();
		GtnWsEMailConfigurationBean eMailConfigurationBean = mcRequest.getConfigurationBean();
		Criteria selectQuery = session.createCriteria(WfMailConfig.class);
		selectQuery.setProjection(Projections.property("wfMailConfigSid"));
		List<Object> defaultList = selectQuery.list();
		if (!defaultList.isEmpty()) {
			primaryId = (Integer) defaultList.get(0);
			wfMailConfig.setWfMailConfigSid(primaryId);
		}
		wfMailConfig.setSmtpFlag(eMailConfigurationBean.getEmailConfigTabSMTP());
		wfMailConfig.setHostName(eMailConfigurationBean.getEmailConfigTabHostName());
		wfMailConfig.setPortNumber(eMailConfigurationBean.getEmailConfigPortNumber());
		wfMailConfig.setInboundStatus(defaultList.isEmpty() ? 'A' : 'C');
		wfMailConfig.setTestMailAddress(eMailConfigurationBean.getEmailConfigTabTestMailAddress());
		wfMailConfig.setEmailAddress(eMailConfigurationBean.getEmailConfigTabemailAddress());
		wfMailConfig.setPassword(eMailConfigurationBean.getEmailConfigTabPassword());
		wfMailConfig.setCreatedDate(new Date());
		wfMailConfig.setModifiedDate(new Date());
		wfMailConfig.setCreatedBy(Integer.parseInt(gtnWsGeneralRequest.getUserId()));
		wfMailConfig.setModifiedBy(Integer.parseInt(gtnWsGeneralRequest.getUserId()));
		try {
			session.saveOrUpdate(wfMailConfig);
			tx.commit();
		} catch (Exception e) {
			logger.error("Sorry, The Update failed due to ", e);
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public void saveMailNotificationLogic(GtnWsMailConfigurationRequest mcRequest,
			GtnWsGeneralRequest gtnWsGeneralRequest) throws GtnFrameworkGeneralException {
		GtnWsEMailConfigurationBean gtnWsEMailConfigurationBean = mcRequest.getConfigurationBean();
		int processSid = Integer.parseInt(gtnWsEMailConfigurationBean.getEmailNotificationTabProcessName());
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		WorkflowProfile workflowProfile = session.load(WorkflowProfile.class, processSid);

		workflowProfile
				.setEmailNotificationFailureCc(gtnWsEMailConfigurationBean.getEmailNotificationTabFailureEmailCc());
		workflowProfile.setSuccessMailSubject(gtnWsEMailConfigurationBean.getEmailNotificationTabSubject());
		workflowProfile.setSuccessMailBody(gtnWsEMailConfigurationBean.getEmailNotificationTabEmailBody());
		workflowProfile.setFailureMailSubject(gtnWsEMailConfigurationBean.getEmailNotificationTabFailureSubject());
		workflowProfile.setFailureMailBody(gtnWsEMailConfigurationBean.getEmailNotificationTabFailureEmailBody());
		workflowProfile.setEmailNotificationSuccessCc(gtnWsEMailConfigurationBean.getEmailNotificationTabEmailCc());
		workflowProfile.setEmailNotificationSuccessTo(gtnWsEMailConfigurationBean.getEmailNotificationTabEmailTo());
		workflowProfile.setEmailNotificationFailureTo(gtnWsEMailConfigurationBean.getEmailNotificationTabFailureTo());
		workflowProfile.setCreatedDate(new Date());
		workflowProfile.setModifiedDate(new Date());
		workflowProfile.setCreatedBy(Integer.parseInt(gtnWsGeneralRequest.getUserId()));
		workflowProfile.setModifiedBy(Integer.parseInt(gtnWsGeneralRequest.getUserId()));

		try {
			session.saveOrUpdate(workflowProfile);
			tx.commit();
		} catch (Exception e) {
			logger.error("Sorry, The Update failed due to ", e);
			tx.rollback();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse setDefaultValueLogic() throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		Session session = getSessionFactory().openSession();
		List<Object[]> defaultValue = new ArrayList<>();
		try {
			Criteria selectQuery = session.createCriteria(WfMailConfig.class);

			ProjectionList selectList = Projections.projectionList();

			selectList.add(Projections.property("wfMailConfigSid"));
			selectList.add(Projections.property("hostName"));
			selectList.add(Projections.property("emailAddress"));
			selectList.add(Projections.property("password"));
			selectList.add(Projections.property("portNumber"));
			selectList.add(Projections.property("smtpFlag"));
			selectList.add(Projections.property("testMailAddress"));
			selectQuery.setProjection(selectList);

			defaultValue = selectQuery.list();

			GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();

			GtnWsEMailConfigurationBean mailConfigurationBean = new GtnWsEMailConfigurationBean();
			if (!defaultValue.isEmpty()) {
				mailConfigurationBean.setDefaultDataLoad(defaultValue);
				gtnWsMailConfigurationResponse.seteMailConfigurationBean(mailConfigurationBean);
				gtnResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
			} else {
				logger.debug("No data in table");
			}
		} catch (Exception ex) {

			logger.error("Error in " + GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE, ex);
		}

		return gtnResponse;
	}

}
