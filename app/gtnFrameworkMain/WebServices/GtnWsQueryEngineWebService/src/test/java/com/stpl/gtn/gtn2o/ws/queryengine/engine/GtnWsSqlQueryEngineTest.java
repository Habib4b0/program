package com.stpl.gtn.gtn2o.ws.queryengine.engine;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.GtnWsQueryType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.service.GtnFrameworkWsSqlQueryEngineService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/GtnWsSqlQueryEngine-test.xml" })
public class GtnWsSqlQueryEngineTest {

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnWsSqlQueryEngineTest.class);

	@Autowired
	private GtnFrameworkWsSqlQueryEngineService gtnSqlQueryEngineService;

	@Test
	public void executeQuery() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryType.COUNT);
		String sqlQuery = "select count(*) from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		int count = (int) gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		System.out.println("Result is----->" + count);
		gtnLogger.info("Result is----->" + count);
	}

}
