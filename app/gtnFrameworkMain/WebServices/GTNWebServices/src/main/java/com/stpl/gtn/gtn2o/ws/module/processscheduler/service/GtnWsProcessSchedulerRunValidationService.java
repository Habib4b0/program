package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.GtnWsCallEtlService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;

/**
 *
 * @author Deepak.Kumar
 */
@Service()
@Scope(value = "singleton")
public class GtnWsProcessSchedulerRunValidationService {
	
	public GtnWsProcessSchedulerRunValidationService() {
			/**
			 * empty constructor
			 */
		}

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedulerRunValidationService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	
	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public boolean validateProcessRun(List<String> validationCriteria, List<Object> inputlist) throws GtnFrameworkGeneralException {
		boolean isValid=false;
		String query=gtnWsSqlService.getQuery(inputlist, "getRunningStatus");
		logger.info(":::::::::::: query  "+query);
		query=query.replace("@PROCESS_NAME", validationCriteria.get(1));
		logger.info(":----------- query  after replace "+query);
		@SuppressWarnings("unchecked")
		List<Object> result = gtnWsProcessSchedularServiceUtil.executeQuery(query);
		if(result==null || result.get(0).toString().equals("Y")) {
			isValid=false;
			logger.info(":::::::::::: isvalid"+ null);
		}
		if(result.get(0).toString().equals("N")) {
			isValid=true;
			logger.info(":::::::::::: isvalid"+isValid);
		}		
		return isValid;
	}
	
}
