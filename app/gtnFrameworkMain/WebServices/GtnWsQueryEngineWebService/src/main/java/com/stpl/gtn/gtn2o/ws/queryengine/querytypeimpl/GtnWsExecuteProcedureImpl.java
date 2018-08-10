package com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryTypeInterface;

public class GtnWsExecuteProcedureImpl implements GtnWsQueryTypeInterface {

	@Override
	public GtnFrameworkQueryResponseBean executeQuery(GtnFrameworkQueryExecutorBean gtnFrameworkQueryExecutorBean,
			GtnFrameworkWsSqlQueryEngine gtnSqlQueryEngine) throws GtnFrameworkGeneralException {
		GtnFrameworkQueryResponseBean responseBean = new GtnFrameworkQueryResponseBean();
		gtnSqlQueryEngine.executeProcedure(gtnFrameworkQueryExecutorBean.getProcedureName(),
				gtnFrameworkQueryExecutorBean.getParams(), gtnFrameworkQueryExecutorBean.getDataType());
		return responseBean;
	}

}
