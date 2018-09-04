package com.stpl.gtn.gtn2o.ws.queryengine.service;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryType;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryTypeInterface;

@Service
public class GtnFrameworkWsSqlQueryEngineService extends GtnServiceRegistryImplClass {

	@Autowired
	private volatile GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine;

	public GtnFrameworkWsSqlQueryEngineService() {
		super();
		initializeLogger();
	}

	@PostConstruct
	public final void initializeLogger() {
		super.logInformation(GtnFrameworkWsSqlQueryEngineService.class);
	}

	public GtnFrameworkWsSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnFrameworkQueryResponseBean executeQuery(GtnFrameworkQueryExecutorBean queryExecutorBean) throws GtnFrameworkGeneralException {
		GtnWsQueryType queryType = Enum.valueOf(GtnWsQueryType.class,
				queryExecutorBean.getQueryType().toUpperCase(Locale.ENGLISH));
		logger.info("Executing Query Type" + queryType);
		GtnWsQueryTypeInterface execute = queryType.getGtnWsQueryTypeInterface();
		return execute.executeQuery(queryExecutorBean, gtnSqlQueryEngine);
	}

}
