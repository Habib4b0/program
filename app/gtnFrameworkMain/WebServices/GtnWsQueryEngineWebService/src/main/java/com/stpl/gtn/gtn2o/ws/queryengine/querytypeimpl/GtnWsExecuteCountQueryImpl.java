package com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl;

import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryTypeInterface;

@Service
public class GtnWsExecuteCountQueryImpl implements GtnWsQueryTypeInterface {

	public GtnWsExecuteCountQueryImpl() {
		super();
	}

	@Override
	public GtnFrameworkQueryResponseBean executeQuery(GtnFrameworkQueryExecutorBean gtnFrameworkQueryExecutorBean,
			GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine) throws GtnFrameworkGeneralException {
		GtnFrameworkQueryResponseBean responseBean = new GtnFrameworkQueryResponseBean();
		responseBean.setResultInteger(gtnSqlQueryEngine.executeCountQuery(gtnFrameworkQueryExecutorBean.getSqlQuery()));
		return responseBean;
	}

}
