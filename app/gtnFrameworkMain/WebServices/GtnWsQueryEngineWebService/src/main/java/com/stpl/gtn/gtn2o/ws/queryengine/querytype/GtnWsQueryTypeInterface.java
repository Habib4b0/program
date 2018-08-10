package com.stpl.gtn.gtn2o.ws.queryengine.querytype;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;

public interface GtnWsQueryTypeInterface {

	public GtnFrameworkQueryResponseBean executeQuery(GtnFrameworkQueryExecutorBean gtnFrameworkQueryExecutorBean,
			GtnFrameworkWsSqlQueryEngine gtnFrameworkWsSqlQueryEngine) throws GtnFrameworkGeneralException;

}
