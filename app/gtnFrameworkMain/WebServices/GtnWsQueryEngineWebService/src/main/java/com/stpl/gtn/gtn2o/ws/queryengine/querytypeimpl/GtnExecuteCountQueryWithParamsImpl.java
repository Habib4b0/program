/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.engine.GtnFrameworkWsSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.queryengine.querytype.GtnWsQueryTypeInterface;

/**
 *
 * @author anandh.karuppusamy
 */
public class GtnExecuteCountQueryWithParamsImpl implements GtnWsQueryTypeInterface{
    public GtnExecuteCountQueryWithParamsImpl()
    {
        super();
    }

    @Override
    public GtnFrameworkQueryResponseBean executeQuery(GtnFrameworkQueryExecutorBean 
                        gtnFrameworkQueryExecutorBean,
            GtnFrameworkWsSqlQueryEngine gtnFrameworkWsSqlQueryEngine) throws GtnFrameworkGeneralException {
        		GtnFrameworkQueryResponseBean responseBean = new GtnFrameworkQueryResponseBean();
		responseBean.setResultInteger(gtnFrameworkWsSqlQueryEngine
                        .executeCountQueryWithParams(gtnFrameworkQueryExecutorBean.getSqlQuery(),
                                gtnFrameworkQueryExecutorBean.getParams(),
                                gtnFrameworkQueryExecutorBean.getDataType()));
		return responseBean;
        
    }
    
}
