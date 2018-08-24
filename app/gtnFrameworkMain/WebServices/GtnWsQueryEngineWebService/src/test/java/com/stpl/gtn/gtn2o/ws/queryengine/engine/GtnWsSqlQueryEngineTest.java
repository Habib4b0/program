package com.stpl.gtn.gtn2o.ws.queryengine.engine;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
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
		queryExecutorBean.setQueryType("Count");
		String sqlQuery = "select count(*) from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnFrameworkQueryResponseBean count = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		gtnLogger.info("Result is----->" + count.getResultInteger());
	}
	
	@Ignore
	@Test
	public void executeSelectQuery() throws GtnFrameworkGeneralException{
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("Select");
		String sqlQuery = "select * from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
	}
	
	@Test
	public void executeSelectParamsQuery() throws GtnFrameworkGeneralException{
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		String sqlQuery = "select * from company_master where company_master_sid = ?";
		Object[] params = { 74775};
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER};
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
	}

}
