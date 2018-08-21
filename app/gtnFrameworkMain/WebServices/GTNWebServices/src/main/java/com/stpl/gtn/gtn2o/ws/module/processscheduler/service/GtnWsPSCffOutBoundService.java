package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.constant.GtnWsProcessSchedulerConstant;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsSchedulerSynchronizer;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

/**
 *
 * @author Deepak.kumar
 */
@Service()
@Scope(value = "singleton")
public class GtnWsPSCffOutBoundService {

	public GtnWsPSCffOutBoundService() {
		super();
	}

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsPSCffOutBoundService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsProcessSchedulerUpdateService processSchedulerUpdateService;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public boolean cffOutBoundService(GtnUIFrameworkWebserviceRequest processSchedulerCffRequest) {
		logger.info("inside cffOutBoundService to  update checkETLRecords and perform action to run job");
		GtnWsGeneralRequest gtnWsGeneralRequest = processSchedulerCffRequest.getGtnWsGeneralRequest();
		GtnWsProcessSchedulerBean gtnProcessSchedulerBean = processSchedulerCffRequest.getProcessSchedulerRequest()
				.getProcessSchedulerBean();
		String scriptName = gtnProcessSchedulerBean.getPsSchemaName();
		int processSid = gtnProcessSchedulerBean.getProcessSchedulerSid();

		GtnWsSchedulerSynchronizer process = GtnWsSchedulerSynchronizer.getInstance();
		try {
			process.lock();
			int i = 0;
			if (checkETLRecords(gtnWsGeneralRequest)) {
				logger.info("updated ETL_CHECK_RECORD, now running the job");
				gtnWsProcessSchedularServiceUtil.runJob(GtnWsProcessSchedularServiceUtil.getFtpBundleValue(),
						scriptName);
				logger.info("Started executing updatelastRun method");
				processSchedulerUpdateService.updateLastRun(processSid, false);

				while (gtnWsProcessSchedularServiceUtil.existsQuery(gtnWsGeneralRequest.getUserId(),
						gtnWsGeneralRequest.getSessionId())) {
					// Waiting block for ETL to end
					Thread.sleep(GtnWsProcessSchedulerConstant.THREE_THOUSAND);
					i++;
					if (i == GtnWsProcessSchedulerConstant.HUNDRED) {
						deleteOnClose(gtnWsGeneralRequest);
						break;
					}
				}
				return true;
			}
			logger.info("didn,t find any CHECK_ETL_RECORDS to update");
			return false;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return false;
		} finally {
			process.unlock();
		}
	}

	public void deleteOnClose(GtnWsGeneralRequest gtnWsGeneralRequest) {
		logger.debug("Inside Delete On Close");
		gtnWsProcessSchedularServiceUtil.deleteTempCffOutbound(gtnWsGeneralRequest, Boolean.FALSE);
		logger.debug("Ending Delete On Close");
	}

	private boolean checkETLRecords(GtnWsGeneralRequest gtnWsGeneralRequest) {
		String query = "UPDATE ST_CFF_OUTBOUND_MASTER SET ETL_CHECK_RECORD = 1 WHERE USER_ID = "
				+ gtnWsGeneralRequest.getUserId() + GtnWsProcessSchedulerConstant.AND_SESSION_ID + gtnWsGeneralRequest.getSessionId()
				+ " AND CHECK_RECORD = 1";
		try {
			int result = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
			return result > 0;
		} catch (GtnFrameworkGeneralException gtnGeneralxception) {
			logger.error("exception during Update query for setting ETL_CHECK_RECORD Failed", gtnGeneralxception);
			return false;
		}
	}

	public boolean checkAllItems(GtnUIFrameworkWebserviceRequest processSchedulerCffRequest) {
		boolean isCheckAll = (boolean) processSchedulerCffRequest.getProcessSchedulerRequest().getCffOutBoundBean()
				.getValue();
		int isChecked = isCheckAll ? 1 : 0;
		String query = "UPDATE ST_CFF_OUTBOUND_MASTER SET CHECK_RECORD = " + isChecked + " WHERE USER_ID = "
				+ processSchedulerCffRequest.getGtnWsGeneralRequest().getUserId() + GtnWsProcessSchedulerConstant.AND_SESSION_ID
				+ processSchedulerCffRequest.getGtnWsGeneralRequest().getSessionId() + " ;";
		try {
			int result = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
			return result > 0;
		} catch (GtnFrameworkGeneralException gtnGeneralxception) {
			logger.error("exception during Update query for setting ETL_CHECK_RECORD Failed", gtnGeneralxception);
			return false;
		}
	}
	
	public void deleteTempCffOutbound(Boolean isScheduler) {
		logger.debug("Enters deleteTempCffOutbound method");
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		try {
			String query = "";
			if (!isScheduler) {
				query = "DELETE FROM ST_CFF_OUTBOUND_MASTER WHERE USER_ID = " + generalRequest.getUserId() + GtnWsProcessSchedulerConstant.AND_SESSION_ID
						+ generalRequest.getSessionId() + ";";
			} else {
				query = "DELETE FROM ST_CFF_OUTBOUND_MASTER WHERE USER_ID = 1 AND SESSION_ID = 1;";
			}
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.debug("End of deleteTempCffOutbound method");
	}
}
