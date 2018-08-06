package com.stpl.gtn.gtn2o.ws.queryengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.GtnFrameworkQueryExecutorBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;

@Service
public class GtnFrameworkWsSqlQueryEngineService {

	@Autowired
	private GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine;

	public GtnFrameworkWsSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnFrameworkWsSqlQueryEngineService() {
		super();
	}

	public int executeCountQuery(GtnFrameworkQueryExecutorBean queryExecutorBean) throws GtnFrameworkGeneralException {
		int count = 0;
		switch (queryExecutorBean.getQueryType()) {
		case COUNT:
			count = gtnSqlQueryEngine.executeCountQuery("select count(*) from Company_Master");
			break;
		case SELECT:
			break;
		}
		return count;
	}

}
