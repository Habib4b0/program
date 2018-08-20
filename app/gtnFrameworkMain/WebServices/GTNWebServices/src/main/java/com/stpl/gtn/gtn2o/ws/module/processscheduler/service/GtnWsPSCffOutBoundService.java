package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.constant.ProcessSchedulerConstant;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.SchedulerSynchronizer;
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
		/**
		 * empty constructor
		 */
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
		GtnWsGeneralRequest gtnWsGeneralRequest = processSchedulerCffRequest.getGtnWsGeneralRequest();
		GtnWsProcessSchedulerBean gtnProcessSchedulerBean = processSchedulerCffRequest.getProcessSchedulerRequest().getProcessSchedulerBean();
		String scriptName = gtnProcessSchedulerBean.getPsSchemaName();
		int processSid = (Integer)gtnProcessSchedulerBean.getProcessSchedulerSid();
		
		SchedulerSynchronizer process = SchedulerSynchronizer.getInstance();
        try {
            process.lock();
            int i = 0;
            if(checkETLRecords(gtnWsGeneralRequest)) {
    			logger.info("updated ETL_CHECK_RECORD, now running the job");
    			gtnWsProcessSchedularServiceUtil.runJob(GtnWsProcessSchedularServiceUtil.getFtpBundleValue(), scriptName);
    			logger.info("Started executing updatelastRun method");
    			processSchedulerUpdateService.updateLastRun(processSid, false);
    			
    			while (gtnWsProcessSchedularServiceUtil.existsQuery(gtnWsGeneralRequest.getUserId(), gtnWsGeneralRequest.getSessionId())) {
                    // Waiting block for ETL to end
                    Thread.sleep(ProcessSchedulerConstant.THREE_THOUSAND);
                    i++;
                    if (i == ProcessSchedulerConstant.HUNDRED) {
                        deleteOnClose(gtnWsGeneralRequest);
                        break;
                    }
                }
    		}
        } catch (Exception ex) {
            logger.error(ex.getMessage());
           return false;
         } finally {
             process.unlock();
         }
		return true;
		
	}
	
	public void deleteOnClose(GtnWsGeneralRequest gtnWsGeneralRequest) {
        logger.debug("Inside Delete On Close");
        gtnWsProcessSchedularServiceUtil.deleteTempCffOutbound(gtnWsGeneralRequest, Boolean.FALSE);
        logger.debug("Ending Delete On Close");
    }

	private boolean checkETLRecords(GtnWsGeneralRequest gtnWsGeneralRequest) {
		String query = "UPDATE ST_CFF_OUTBOUND_MASTER SET ETL_CHECK_RECORD = 1 WHERE USER_ID = "
				+gtnWsGeneralRequest.getUserId() + " AND SESSION_ID = " + gtnWsGeneralRequest.getSessionId() + " AND CHECK_RECORD = 1";
		try{
			int result = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
			return result > 0;
		}
		catch(GtnFrameworkGeneralException gtnGeneralxception) {
			logger.error("exception during Update query for setting ETL_CHECK_RECORD Failed", gtnGeneralxception);
			return false;
		}		
	}
}
