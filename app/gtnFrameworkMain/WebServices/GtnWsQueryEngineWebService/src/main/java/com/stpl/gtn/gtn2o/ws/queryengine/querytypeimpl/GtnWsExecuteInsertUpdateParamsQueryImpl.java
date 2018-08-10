package com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryTypeInterface;

public class GtnWsExecuteInsertUpdateParamsQueryImpl implements GtnWsQueryTypeInterface {
	@Override
	public GtnFrameworkQueryResponseBean executeQuery(GtnFrameworkQueryExecutorBean gtnFrameworkQueryExecutorBean,
			GtnFrameworkWsSqlQueryEngine gtnFrameworkWsSqlQueryEngine) throws GtnFrameworkGeneralException {
		GtnFrameworkQueryResponseBean responseBean = new GtnFrameworkQueryResponseBean();
		responseBean.setResultInteger(
				gtnFrameworkWsSqlQueryEngine.executeInsertOrUpdateQuery(gtnFrameworkQueryExecutorBean.getSqlQuery(),
						gtnFrameworkQueryExecutorBean.getParams(), gtnFrameworkQueryExecutorBean.getDataType()));
		return responseBean;
	}

}
