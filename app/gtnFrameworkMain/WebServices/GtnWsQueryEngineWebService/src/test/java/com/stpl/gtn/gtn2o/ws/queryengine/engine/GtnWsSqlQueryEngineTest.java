package com.stpl.gtn.gtn2o.ws.queryengine.engine;

import static org.junit.Assert.assertFalse;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.service.GtnFrameworkWsSqlQueryEngineService;

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

	@Test
	public void executeSelectQuery() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("Select");
		String sqlQuery = "select * from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		assertFalse(response.toString().isEmpty());
	}

	@Test
	public void executeSelectParamsQuery() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		String sqlQuery = "select * from company_master where company_master_sid = ?";
		Object[] params = { 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info("Result is----->" + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());
	}

	@Ignore
	@Test
	public void executeControllerTest() throws GtnFrameworkGeneralException {

		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		String sqlQuery = "select * from company_master where company_master_sid = ?";
		Object[] params = { 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		RestTemplate restTemplate = new RestTemplate();
		GtnQueryEngineWebServiceResponse webServiceResponse = restTemplate.postForObject(
				"http://localhost:8090/GtnWsQueryEngineWebService/executeQuery", gtnQueryEngineWebServiceRequest,
				GtnQueryEngineWebServiceResponse.class);

		gtnLogger.info("Result is----->" + webServiceResponse.getQueryResponseBean().getResultString());

	}

	@Test
	public void updateTestWithParams() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");
		String sqlQuery = "update company_master" + " set CITY = ? " + "where company_master_sid = ? ";
		Object[] params = { "MADISON", 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info("Result is----->" + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}

	@Test
	public void updateTestWithoutParams() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("INSERTORUPDATE");
		String sqlQuery = "update company_master set ZIP_CODE =12345   where company_master_sid = 74775";
		gtnLogger.info(sqlQuery);
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		gtnLogger.info("Result is----->" + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}
	
	@Ignore
	@Test
	public void executeProcedure() throws GtnFrameworkGeneralException{
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("PROCEDURE");

		
	}
	
	@Ignore
	@Test
	public void insertOrUpdateUsingHqlTest() throws GtnFrameworkGeneralException{
		
		
		GtnFrameworkWsSqlQueryEngine gtnFrameworkWsSqlQueryEngine = new GtnFrameworkWsSqlQueryEngine();
		
		String hqlQuery="update company_master set ZIP_CODE = ?  where company_master_sid = ?";
		
		int count =gtnFrameworkWsSqlQueryEngine.executeInsertAndUpdateHqlQuery(hqlQuery);
		//assertFalse();
		 
	}

	
	
	
	
}
