package com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller;

import static org.junit.Assert.assertFalse;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.forecastconfiguration.ForecastConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsForecastConfigurationControllerTest {
    @Mock
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@InjectMocks	
	@Autowired
	GtnWsForecastConfigurationController ins;
	
     @Before
      public void setup() {
    	 MockitoAnnotations.initMocks(this);
     }

	@Test
	public void testGtnWsForecastConfigurationController() {
		GtnWsForecastConfigurationController ins = new GtnWsForecastConfigurationController();
	}

	@Test
	public void testLoadForecastPeriod() {
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse result= ins.loadForecastPeriod(request);
		assertFalse(result==null);
	}
	
	

	@Test
	public void testPeriodModeValue() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse result=ins.periodModeValue(gtnWsRequest);
		assertFalse(result==null);
	}
	

	@Test
	public void testCheckSaveForecastConfiguration() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig=new  GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		GtnUIFrameworkWebserviceResponse result=ins.checkSaveForecastConfiguration(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testCheckSaveForecastConfiguration_Fail(){
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse result=ins.checkSaveForecastConfiguration(request);
		assertFalse(result==null);
	}
	
	@Test 
	public void testSaveForecastConfiguration() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig=new  GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Criteria cr=Mockito.mock(Criteria.class);
		reqConfig.setBusinessProcess(1);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction();
		doReturn(cr).when(session).createCriteria(ForecastConfig.class);
		
		reqConfig.setProcessType("Defined");
		reqConfig.setMode("Interval");
		
		HelperTable table = new HelperTable();
		table.setDescription("Month");
		doReturn(table).when(session).load(HelperTable.class, 1);
		reqConfig.setToDate(new Date());
		
		GtnUIFrameworkWebserviceResponse result=ins.saveForecastConfiguration(request);
	
				
		// case 1 private getModifiedProcessModeValue
		reqConfig.setMode("Interval");
		reqConfig.setFutureInterval("value");
		ins.saveForecastConfiguration(request);
				
		// case 2 private getModifiedProcessModeValue if block 773
		reqConfig.setMode("Intervl");
		ins.saveForecastConfiguration(request);
		
		//case 3 private getModifiedProcessModeValue 
		reqConfig.setMode("Interval");
		reqConfig.setHistoryInterval("val");
		ins.saveForecastConfiguration(request);
		
		//case 4
		reqConfig.setToDate(null);
		ins.saveForecastConfiguration(request);
		

		//case 5
		ForecastConfig fconig=new ForecastConfig();
		reqConfig.setMode("Interval");
		reqConfig.setToDate(new Date());
		
		reqConfig.setProcessType("Defined---");
		ins.saveForecastConfiguration(request);
		
		//case 6
		reqConfig.setMode("Interal");
		reqConfig.setToDate(new Date());
		
		fconig.setProcessType(false);
		ins.saveForecastConfiguration(request);
		
		assertFalse(result==null);
		
	}
	
	
	@Test 
	public void testSaveForecastConfiguration_Fail() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse result=ins.saveForecastConfiguration(request);
		assertFalse(result==null);
	}

	@Test
	public void testFromPeriodValueChange_1() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig = new GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		
		LocalDate localDate = LocalDate.now();
		localDate = localDate.minusYears(4);

		reqConfig.setFromDate(new Date(localDate.toEpochDay() * DateUtils.MILLIS_PER_DAY));

		GtnUIFrameworkWebserviceResponse result = ins.fromPeriodValueChange(request);
		assertFalse(result == null);
	}
	
	@Test
	public void testFromPeriodValueChange_2() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig = new GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		GtnUIFrameworkWebserviceResponse result = ins.fromPeriodValueChange(request);
		assertFalse(result == null);
		
	}
	
	@Test
	public void testFromPeriodValueChange_3() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig = new GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		reqConfig.setFromDate(new Date());
		GtnUIFrameworkWebserviceResponse result = ins.fromPeriodValueChange(request);
		assertFalse(result == null);
	}
	
	@Test
	public void testFromPeriodValueChange_4() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig = new GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		
		LocalDate localDate = LocalDate.now();
		localDate = localDate.minusYears(2);

		reqConfig.setFromDate(new Date(localDate.toEpochDay() * DateUtils.MILLIS_PER_DAY));
		GtnUIFrameworkWebserviceResponse result = ins.fromPeriodValueChange(request);
		assertFalse(result == null);
	}
	
	@Test
	public void testToPeriodValueChange_1() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig=new  GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 1, 1);
		reqConfig.setToDate(cal.getTime());
		GtnUIFrameworkWebserviceResponse result=ins.toPeriodValueChange(request);	
		assertFalse(result == null);
	
	}
	
	@Test
	public void testToPeriodValueChange_2() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig=new  GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 1, 1);
		reqConfig.setToDate(cal.getTime());
		GtnUIFrameworkWebserviceResponse result=ins.toPeriodValueChange(request);
		assertFalse(result == null);
	}
	
	@Test
	public void testToPeriodValueChange_Fail() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig=new  GtnWsForecastConfigurationRequest();
		GtnUIFrameworkWebserviceResponse result=ins.toPeriodValueChange(request);
		assertFalse(result == null);
	}
	
	
	@Test
	public void testGetForecastConfigurationTableData() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWsSearchRequest.setCount(true);
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = null;
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		ins.getForecastConfigurationTableData(gtnWsRequest);
	}
	
	@Test
	public void validateSaveForecastConfiguration_1() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		
		//c1
		fcRequest.setBusinessProcess(0);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c2
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c3
		fcRequest.setMode("Perio");
		fcRequest.setBusinessProcess(1);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c4 for private void validateInterval
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c5 for private void validateInterval
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c6 for private void validateInterval
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("0");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c7 for private void validateInterval
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureInterval("4");
		fcRequest.setFutureFrequency(4554);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c8 for validateIntervalDefined
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureInterval("4");
		fcRequest.setFutureFrequency(0);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c9 for validateIntervalDefined
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureFrequency(1);
		fcRequest.setFutureInterval("0");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c10 for validateIntervalDefined
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureFrequency(1);
		fcRequest.setFutureInterval(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c11 for validateIntervalDefined // if block line 612
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType("value");
		fcRequest.setFutureInterval("4");
		fcRequest.setFutureFrequency(4554);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c12  for validatePeriodDefined
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setFromDate(new Date());
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c13 for validatePeriodDefined
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setFromDate(null);
		fcRequest.setProcessType("Defined");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c14 for validatePeriod
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusYears(4);

		fcRequest.setFromDate(new Date(localDate.toEpochDay() * DateUtils.MILLIS_PER_DAY));
		fcRequest.setProcessType("Auto Update");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c15 for validatePeriod 
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setProcessType("Defined");
		fcRequest.setToDate(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
		//c16 for validatePeriod 
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setProcessType("Define");
		fcRequest.setToDate(new Date());
		fcRequest.setFromDate(new Date());
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		

	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void validateSaveForecastConfiguration_1False() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		
		//c2
		fcRequest.setBusinessProcess(4254);
		fcRequest.setMode(null);
		//fcRequest.setProcessType("val");
		fcRequest.setMode(null);
		fcRequest.setBusinessProcess(44444);
		try {
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		Assert.fail();
		}catch(GtnFrameworkGeneralException ex) {
			
		}
	}
	
	
	@Test
	public void testHistoryIntervalValueChange() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest reqConfig=new  GtnWsForecastConfigurationRequest();
		request.setForecastConfigurationRequest(reqConfig);
		
		 reqConfig.setHistoryInterval("3");
		reqConfig.setHistoryFrequency(1);
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		
		
		HelperTable table = new HelperTable();
		table.setDescription("Month");
		doReturn(table).when(session).load(HelperTable.class, 1);
		
		GtnUIFrameworkWebserviceResponse result =ins.historyIntervalValueChange(request);
		
		//case 5 if block 237
		table.setDescription("Quarter");
		ins.historyIntervalValueChange(request);
		
		// case 2 if block 236
		reqConfig.setHistoryInterval("55");
		table.setDescription("Month");
		ins.historyIntervalValueChange(request);
		
		//case 3 if block 233
		reqConfig.setHistoryFrequency(0);
		ins.historyIntervalValueChange(request);
		
		//case 4 if block 225
		reqConfig.setHistoryInterval("");
		ins.historyIntervalValueChange(request);
	
		assertFalse(result==null);
	}
	
	@Test
	public void testFutureFrequencyValueChange() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsForecastConfigurationResponse forecastResponse=new GtnWsForecastConfigurationResponse();
		 
		GtnWsForecastConfigurationRequest reqConfig=new  GtnWsForecastConfigurationRequest();
		reqConfig.setFutureInterval("1");
		reqConfig.setFutureFrequency(1);
		request.setForecastConfigurationRequest(reqConfig);
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		
		HelperTable table = new HelperTable();
		table.setDescription("Month");
		
		doReturn(table).when(session).load(HelperTable.class, 1);
		
		GtnUIFrameworkWebserviceResponse result =ins.futureFrequencyValueChange(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testGetCurrentGTSToCalendar() throws Exception {
		String fileType="value";
		Calendar result=ins.getCurrentGTSToCalendar(fileType);
		assertFalse(result==null);
	}
	
	
	@Test 
	public void testfutureIntervalDynamicValueChangeLogic_1() {
		GtnWsForecastConfigurationRequest request=new GtnWsForecastConfigurationRequest();
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		
		request.setFutureInterval("");
		
		GtnWsForecastConfigurationResponse result=ins.futureIntervalDynamicValueChangeLogic(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testfutureIntervalDynamicValueChangeLogic_2() {
		GtnWsForecastConfigurationRequest request=new GtnWsForecastConfigurationRequest();
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		
		request.setFutureInterval("1");
		request.setFutureFrequency(0);
		GtnWsForecastConfigurationResponse result=ins.futureIntervalDynamicValueChangeLogic(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testfutureIntervalDynamicValueChangeLogic_3() {
		GtnWsForecastConfigurationRequest request=new GtnWsForecastConfigurationRequest();
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		
		HelperTable table = new HelperTable();
		table.setDescription("Month");
		
		doReturn(table).when(session).load(HelperTable.class, 1);
		
		request.setFutureInterval("1");
		request.setFutureFrequency(1);
		table.setDescription("val");
		
		GtnWsForecastConfigurationResponse result=ins.futureIntervalDynamicValueChangeLogic(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testfutureIntervalDynamicValueChangeLogic_4_Fail() {
		GtnWsForecastConfigurationRequest request=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse result=ins.futureIntervalDynamicValueChangeLogic(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testFutureIntervalValueChangeLogic_1() {
		GtnWsForecastConfigurationRequest request = new GtnWsForecastConfigurationRequest();
		String foreCastPeriod="";
		GtnWsForecastConfigurationResponse response = new GtnWsForecastConfigurationResponse();
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		
		request.setFutureInterval("1");
		request.setFutureFrequency(0);
		ins.futureIntervalValueChangeLogic(request,foreCastPeriod,response);
	}
	
	@Test
	public void testFutureIntervalValueChangeLogic_2() {
		GtnWsForecastConfigurationRequest request = new GtnWsForecastConfigurationRequest();
		String foreCastPeriod="";
		GtnWsForecastConfigurationResponse response = new GtnWsForecastConfigurationResponse();
		
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		
		doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
		
		request.setFutureInterval("1");
		request.setFutureFrequency(0);
		ins.futureIntervalValueChangeLogic(request,foreCastPeriod,response);
	}
	
	
	

	
}
