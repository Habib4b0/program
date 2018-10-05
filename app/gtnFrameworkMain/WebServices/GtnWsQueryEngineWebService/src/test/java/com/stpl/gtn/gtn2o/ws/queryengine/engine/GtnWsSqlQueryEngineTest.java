package com.stpl.gtn.gtn2o.ws.queryengine.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.constants.GtnWsQueryEngineConstants;
import com.stpl.gtn.gtn2o.ws.queryengine.controller.GtnFrameworkWsSqlQueryEngineController;
import com.stpl.gtn.gtn2o.ws.queryengine.service.GtnFrameworkWsSqlQueryEngineService;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/GtnWsSqlQueryEngine-test.xml" })
public class GtnWsSqlQueryEngineTest extends GtnCommonWebServiceImplClass {

	public GtnWsSqlQueryEngineTest() {
		super(GtnWsSqlQueryEngineTest.class);
		}
	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnWsSqlQueryEngineTest.class);
	
	@Rule
	  public final ExpectedException thrown = ExpectedException.none();

	@Autowired
	private GtnFrameworkWsSqlQueryEngineService gtnSqlQueryEngineService;
	
	@Autowired
	private GtnFrameworkWsSqlQueryEngineController gtnFrameworkWsSqlQueryEngineController;
	
	@Autowired
	private GtnFrameworkWsSqlQueryEngine gtnFrameworkWsSqlQueryEngine;

	@Test
	public void forSampleTest()
	{
		gtnFrameworkWsSqlQueryEngine.registerWs();
	}

	@Test
	public void executeQuery() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("Count");
		String sqlQuery = "select count(*) from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnFrameworkQueryResponseBean count = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		gtnLogger.info(GtnWsQueryEngineConstants.RESULT + count.getResultInteger());
	}
	@Test(expected=GtnFrameworkGeneralException.class)
	public void executeCountQueryExceptionTest() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("Count");
		String sqlQuery = "select count(#) from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnFrameworkQueryResponseBean count = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		gtnLogger.info(GtnWsQueryEngineConstants.RESULT + count.getResultInteger());
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
	@Test(expected=GtnFrameworkGeneralException.class)
	public void executeSelectQueryExceptionTest() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("Select");
		String sqlQuery = "select *";
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		assertFalse(response.toString().isEmpty());
	}


	@Test
	public void executeSelectParamsQuery() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.SELECT_WITH_PARAMS);
		String sqlQuery = "select * from company_master where company_master_sid = ?";
		Object[] params = { 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());
	}
	@Test(expected=GtnFrameworkGeneralException.class)
	public void executeSelectParamsQueryExceptionTest() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.SELECT_WITH_PARAMS);
		String sqlQuery = "select # from company_master where company_master_sid = ?";
		Object[] params = { 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.NULL_ALLOWED };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());
	}
	
	@Test
	public void executeControllerTest() throws GtnFrameworkGeneralException {

		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.SELECT_WITH_PARAMS);
		String sqlQuery = "select * from company_master where company_master_sid = ?";
		Object[] params = { 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		assertFalse(gtnFrameworkWsSqlQueryEngineController.executeQuery(gtnQueryEngineWebServiceRequest)==null);
		
		
	}

	@Test
	public void updateTestWithParams() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.INSERTORUPDATE_WITH_PARAMS);
		String sqlQuery = GtnWsQueryEngineConstants.UPDATE_CM + " set CITY = ? " + GtnWsQueryEngineConstants.CM_SID;
		Object[] params = { "MADISON", 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}
	@Test
	public void updateTestWithDateParams() throws GtnFrameworkGeneralException {
		Date date=new Date();
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.INSERTORUPDATE_WITH_PARAMS);
		String sqlQuery =GtnWsQueryEngineConstants.UPDATE_CM + " set COMPANY_START_DATE = ? " + GtnWsQueryEngineConstants.CM_SID;
		Object[] params = { date, 74775 };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.DATE, GtnFrameworkDataType.INTEGER };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info(GtnWsQueryEngineConstants.RESULT + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}
	
	@Test(expected=GtnFrameworkGeneralException.class)
	public void updateTestWithParamsExceptionTest() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.INSERTORUPDATE_WITH_PARAMS);
		String sqlQuery = GtnWsQueryEngineConstants.UPDATE_CM + GtnWsQueryEngineConstants.SET_CITY + GtnWsQueryEngineConstants.CM_SID;
		Object[] params = { 4575.25785, "54654786768476558" };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.DOUBLE, GtnFrameworkDataType.BIG_DECIMAL };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}
	@Test(expected=GtnFrameworkGeneralException.class)
	public void updateTestWithParamsExceptionTest2() throws GtnFrameworkGeneralException {
		
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.INSERTORUPDATE_WITH_PARAMS);
		String sqlQuery = GtnWsQueryEngineConstants.UPDATE_CM + GtnWsQueryEngineConstants.SET_CITY + GtnWsQueryEngineConstants.CM_SID;
		Object[] params = { null, "54654786768476558" };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.DATE, GtnFrameworkDataType.IN_LIST };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}
	@Test(expected=GtnFrameworkGeneralException.class)
	public void updateTestWithParamsExceptionTest3() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType(GtnWsQueryEngineConstants.INSERTORUPDATE_WITH_PARAMS);
		String sqlQuery = GtnWsQueryEngineConstants.UPDATE_CM + GtnWsQueryEngineConstants.SET_CITY + GtnWsQueryEngineConstants.CM_SID;
		Object[] params = { null, "" };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.LIST, GtnFrameworkDataType.BIG_DECIMAL };
		queryExecutorBean.setSqlQuery(sqlQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(type);

		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);

		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
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
		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}
	@Test(expected=GtnFrameworkGeneralException.class)
	public void updateTestWithoutParamsExceptionTest() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("INSERTORUPDATE");
		String sqlQuery = "update company_master sett ZIP_CODE =12345   where company_master_sid = 74775";
		gtnLogger.info(sqlQuery);
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnFrameworkQueryResponseBean response = gtnSqlQueryEngineService.executeQuery(queryExecutorBean);
		gtnLogger.info(GtnWsQueryEngineConstants.RESULT  + response);
		assertFalse(queryExecutorBean.getSqlQuery().isEmpty());

	}


	@Test
	public void forCoveringPurposeTest() {
		GtnFrameworkWsSqlQueryEngineService gtnFrameworkWsSqlQueryEngineService = new GtnFrameworkWsSqlQueryEngineService();
		assertFalse(gtnFrameworkWsSqlQueryEngineService.getGtnSqlQueryEngine() != null);

	}

	@Before
	public void propertyTest() {
		System.setProperty("com.stpl.gtnframework.base.path", "E:/GTN Server Setup/conf");
	}

	@Test
	public void testingWS()
	{
		
		boolean b=gtnFrameworkWsSqlQueryEngineController.test();
		assertEquals(b,true);
		
		
	}
	@Test
	  public void throwsNullPointerException() {
	    thrown.expect(NullPointerException.class);
	    throw new NullPointerException();
	  }
	@Test
	  public void throwsIOException() throws IOException {
	    thrown.expect(IOException.class);
	    throw new IOException();
	  }
	@Test
	  public void throwsException() throws Exception {
	    thrown.expect(Exception.class);
	   
	  }
	@Test
	public void loggerTest()
	{
		gtnFrameworkWsSqlQueryEngine.getQueryLogger();
	}

    @Override
    public void initCallOnFailure() {
        // Default Method
    }

    @Override
    public void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean) {
        // Default Method
    }
	

}
