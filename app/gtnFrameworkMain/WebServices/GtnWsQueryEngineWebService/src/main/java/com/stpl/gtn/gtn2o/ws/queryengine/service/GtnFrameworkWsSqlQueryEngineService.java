package com.stpl.gtn.gtn2o.ws.queryengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.GtnFrameworkQueryExecutorBean;
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

	public Object executeQuery(GtnFrameworkQueryExecutorBean queryExecutorBean) throws GtnFrameworkGeneralException {
		Object result = null;
		switch (queryExecutorBean.getQueryType()) {
		case COUNT:
			result = gtnSqlQueryEngine.executeCountQuery(queryExecutorBean.getSqlQuery());
			break;
		case SELECT:
			result = gtnSqlQueryEngine.executeSelectQuery(queryExecutorBean.getSqlQuery());
			break;
		case SELECTWITHPARAMS:
			result = gtnSqlQueryEngine.executeSelectQuery(queryExecutorBean.getSqlQuery(),
					queryExecutorBean.getParams(), queryExecutorBean.getDataType());
			break;
		case INSERTORUPDATE:
			result = gtnSqlQueryEngine.executeInsertOrUpdateQuery(queryExecutorBean.getSqlQuery());
			break;
		case INSERTORUPDATEWITHPARAMS:
			result = gtnSqlQueryEngine.executeInsertOrUpdateQuery(queryExecutorBean.getSqlQuery(),
					queryExecutorBean.getParams(), queryExecutorBean.getDataType());
			break;
		case PROCEDURE:
			gtnSqlQueryEngine.executeProcedure(queryExecutorBean.getProcedureName(), queryExecutorBean.getParams(),
					queryExecutorBean.getDataType());
			break;
		default:
			break;
		}
		return result;
	}

}
