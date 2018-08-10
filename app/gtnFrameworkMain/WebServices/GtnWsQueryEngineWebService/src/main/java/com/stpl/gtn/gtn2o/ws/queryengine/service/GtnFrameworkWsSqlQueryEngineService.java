package com.stpl.gtn.gtn2o.ws.queryengine.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryType;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryTypeInterface;

@Service
public class GtnFrameworkWsSqlQueryEngineService {

	@Autowired
	private GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine;

	public GtnFrameworkWsSqlQueryEngineService() {
		super();
	}

	public GtnFrameworkWsSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public Object executeQuery(GtnFrameworkQueryExecutorBean queryExecutorBean) throws GtnFrameworkGeneralException {
		GtnWsQueryType queryType = Enum.valueOf(GtnWsQueryType.class,
				queryExecutorBean.getQueryType().toUpperCase(Locale.ENGLISH));
		GtnWsQueryTypeInterface execute = queryType.getGtnWsQueryTypeInterface();
		Object result = execute.executeQuery(queryExecutorBean, gtnSqlQueryEngine);
		return result;
	}

}
