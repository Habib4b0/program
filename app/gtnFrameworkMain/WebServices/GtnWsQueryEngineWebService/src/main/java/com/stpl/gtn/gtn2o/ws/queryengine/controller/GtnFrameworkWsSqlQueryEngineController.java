package com.stpl.gtn.gtn2o.ws.queryengine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.GtnFrameworkQueryExecutorBean;
import com.stpl.gtn.gtn2o.queryengine.GtnWsQueryType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.service.GtnFrameworkWsSqlQueryEngineService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class GtnFrameworkWsSqlQueryEngineController {

	@Autowired
	private GtnFrameworkWsSqlQueryEngineService gtnFrameworkWsSqlQueryEngineService;

	public GtnFrameworkWsSqlQueryEngineController() {
		super();
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean test() {
		return true;
	}

	@RequestMapping(value = "/executeSelectQuery", method = RequestMethod.GET)
	public void executeSelectQuery()
			throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryType.COUNT);
		int count = gtnFrameworkWsSqlQueryEngineService.executeCountQuery(queryExecutorBean);
		System.out.println("Count is--->" + count);

	}

}
