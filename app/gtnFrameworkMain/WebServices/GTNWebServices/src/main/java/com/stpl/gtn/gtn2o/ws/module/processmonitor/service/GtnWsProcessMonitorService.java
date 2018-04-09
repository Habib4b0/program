package com.stpl.gtn.gtn2o.ws.module.processmonitor.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processmonitor.bean.GtnWsProcessMonitorBean;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

@Service()
@Scope(value = "singleton")
public class GtnWsProcessMonitorService {
	public GtnWsProcessMonitorService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessMonitorService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void deleteProcessMonitor(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		logger.debug("Enter Save/update Process Monitor ");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {

			GtnWsProcessMonitorBean monitorBean = gtnWsRequest.getProcessMonitorRequest().getProcessMonitorBean();
			WorkflowProfile p = session.load(WorkflowProfile.class, monitorBean.getProcessMonitorSid());
			session.delete(p);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in save/Update Process Monitor ", e);
		} finally {
			session.close();
		}
	}

	public Integer saveUpdateProcessMonitor(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		logger.debug("Enter Save/update Process Monitor ");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsProcessMonitorBean monitorBean = gtnWsRequest.getProcessMonitorRequest().getProcessMonitorBean();
			GtnWsGeneralRequest generalRequest = gtnWsRequest.getGtnWsGeneralRequest();
			WorkflowProfile workflowprofile = new WorkflowProfile();
			workflowprofile.setProcessSid(monitorBean.getProcessMonitorSid());
			workflowprofile.setProcessName(monitorBean.getProcessName());
			workflowprofile.setSlaCalendarMasterSid(monitorBean.getSlaCalendarMasterSid());
			workflowprofile.setStartDate(monitorBean.getStartDate());
			workflowprofile.setEndDate(monitorBean.getEndDate());
			if (isNotBlank(monitorBean.getHours1())) {
				workflowprofile.setStartHour1(Byte.valueOf(monitorBean.getHours1()));
			}
			if (isNotBlank(monitorBean.getHours2())) {
				workflowprofile.setStartHour2(Byte.valueOf(monitorBean.getHours2()));
			}
			if (isNotBlank(monitorBean.getHours3())) {
				workflowprofile.setStartHour3(Byte.valueOf(monitorBean.getHours3()));
			}
			if (isNotBlank(monitorBean.getMinutes1())) {
				workflowprofile.setStartMinutes1(Byte.valueOf(monitorBean.getMinutes1()));
			}
			if (isNotBlank(monitorBean.getMinutes2())) {
				workflowprofile.setStartMinutes2(Byte.valueOf(monitorBean.getMinutes2()));
			}
			if (isNotBlank(monitorBean.getMinutes3())) {
				workflowprofile.setStartMinutes3(Byte.valueOf(monitorBean.getMinutes3()));
			}
			workflowprofile.setInboundStatus(monitorBean.getInboundStatus().charAt(0));
			workflowprofile.setProcessDisplayName(monitorBean.getProcessDisplayName());
			workflowprofile.setFrequency(monitorBean.getFrequency());
			workflowprofile.setSchemaName(monitorBean.getSchemaName());
			workflowprofile.setUserSid(Integer.parseInt(generalRequest.getUserId()));
			workflowprofile.setCreatedBy(Integer.parseInt(generalRequest.getUserId()));
			workflowprofile.setCreatedDate(monitorBean.getCreatedDate());
			workflowprofile.setModifiedBy(Integer.parseInt(generalRequest.getUserId()));
			workflowprofile.setModifiedDate(monitorBean.getModifiedDate());
			workflowprofile
					.setHelperTable(session.load(HelperTable.class, Integer.valueOf(monitorBean.getProcessType())));

			session.saveOrUpdate(workflowprofile);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in save/Update Process Monitor ", e);
		} finally {
			session.close();
		}
		return null;
	}

	public Integer duplicateProcessName(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		logger.debug("Enter duplicate Process Name  ");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cr;
			GtnWsProcessMonitorBean monitorBean = gtnWsRequest.getProcessMonitorRequest().getProcessMonitorBean();
			if (!GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_ADD.equals(monitorBean.getComponent())) {
				cr = session.createCriteria(WorkflowProfile.class)
						.add(Restrictions.and(Restrictions.ne("processSid", monitorBean.getProcessMonitorSid()),
								(Restrictions.eq("processName", monitorBean.getProcessName()))));

			} else {
				cr = session.createCriteria(WorkflowProfile.class)
						.add(Restrictions.eq("processName", monitorBean.getProcessName()));
			}
			@SuppressWarnings("unchecked")
			List<WorkflowProfile> results = cr.list();
			int result = results.size();
			return !results.isEmpty() ? result : 0;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in duplicate Process Name ", e);
		}
	}

	public boolean isNotBlank(String str) {
		return !(str == null || (str.trim().length()) == 0);
	}

}
