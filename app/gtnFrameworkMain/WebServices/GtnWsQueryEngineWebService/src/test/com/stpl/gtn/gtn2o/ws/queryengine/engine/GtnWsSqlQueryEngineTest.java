package com.stpl.gtn.gtn2o.ws.queryengine.engine;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.GtnWsQueryType;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.controller.GtnFrameworkWsSqlQueryEngineController;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src\main\resources\GtnWsSqlQueryEngine-test.xml"})
public class GtnWsSqlQueryEngineTest {

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger.getGTNLogger(GtnWsSqlQueryEngineTest.class);
	
	@Autowired
	private GtnFrameworkWsSqlQueryEngineService gtnSqlQueryEngineService;
	
	public GtnWsSqlQueryEngineTest(){
		super();
	}
	
	@Test
	public void executeQuery(){
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryType.SELECT);
		String sqlQuery = "select count(*) from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
	}
}
