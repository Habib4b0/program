package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz.QuartzListener;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;

@Service()
@Scope(value = "singleton")
public class GtnWsUpdateWorkFlowProfileService {
	
	public GtnWsUpdateWorkFlowProfileService() {
		/*
		 * no need to implement
		 */
	}

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsUpdateWorkFlowProfileService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private QuartzListener quartzListener;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void updateWorkFlowProfile(GtnWsProcessSchedulerBean gtnWsProcessSchedulerBean) {

		try (Session updateSession = getSessionFactory().openSession()) {
			Transaction updateTransaction = updateSession.beginTransaction();
			updateTransaction.begin();
			WorkflowProfile workflowProfile = updateSession.load(WorkflowProfile.class,
					gtnWsProcessSchedulerBean.getProcessSchedulerSid());
			workflowProfile.setFrequency(gtnWsProcessSchedulerBean.getPsProcessFrequency());
			if ("Active".equals(gtnWsProcessSchedulerBean.getPsStatus())) {
				workflowProfile.setActiveFlag('Y');
			} else {
				workflowProfile.setActiveFlag('N');
			}

			workflowProfile.setStartHour(Byte.valueOf(gtnWsProcessSchedulerBean.getStartHour()));
			workflowProfile.setStartMinutes(Byte.valueOf(gtnWsProcessSchedulerBean.getStartMinute()));
			workflowProfile.setStartHour1(Byte.valueOf(gtnWsProcessSchedulerBean.getPsHours1()));
			workflowProfile.setStartHour2(Byte.valueOf(gtnWsProcessSchedulerBean.getPsHours2()));
			workflowProfile.setStartHour3(Byte.valueOf(gtnWsProcessSchedulerBean.getPsHours3()));
			workflowProfile.setStartMinutes1(Byte.valueOf(gtnWsProcessSchedulerBean.getPsMinutes1()));
			workflowProfile.setStartMinutes2(Byte.valueOf(gtnWsProcessSchedulerBean.getPsMinutes2()));
			workflowProfile.setStartMinutes3(Byte.valueOf(gtnWsProcessSchedulerBean.getPsMinutes3()));
			logger.info(""+gtnWsProcessSchedulerBean.getPsStartDate());
			workflowProfile.setStartDate(gtnWsProcessSchedulerBean.getPsStartDate());
			logger.info("   " + workflowProfile.getStartDate());
			workflowProfile.setEndDate(gtnWsProcessSchedulerBean.getPsEndDate());
			logger.info("updated end date "+ workflowProfile.getEndDate());
			workflowProfile.setModifiedDate(new Date());
			updateSession.update(workflowProfile);
			updateTransaction.commit();
			logger.info("********** Calling Quartz Listener clas method **********");
			quartzListener.createQuartzScheduler();
		} catch (Exception exp) {
			logger.info("exception occured while reterving bean record " + exp);
		}
	}

}
