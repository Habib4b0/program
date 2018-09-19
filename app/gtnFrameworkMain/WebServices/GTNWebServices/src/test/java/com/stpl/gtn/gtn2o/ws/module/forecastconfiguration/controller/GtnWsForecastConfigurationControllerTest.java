package com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
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
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceTextBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;

import junit.framework.Assert;

/**
*
* @author Praveen.Kumar
*/

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
	public void testLoadForecastPeriod_Fail() throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationController in=Mockito.spy(ins);
		doReturn(null).when(in).getCurrentGTSToCalendar(Mockito.anyString());
		GtnUIFrameworkWebserviceResponse result= in.loadForecastPeriod(request);
	}

	@Test
	public void testPeriodModeValue() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse result=ins.periodModeValue(gtnWsRequest);
		assertFalse(result==null);
	}
	
	@Test
	public void testPeriodModeValue_Fail() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationController in=Mockito.spy(ins);
		doReturn(null).when(in).getCurrentGTSToCalendar(Mockito.anyString());
		GtnUIFrameworkWebserviceResponse result=in.periodModeValue(gtnWsRequest);
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
		
		reqConfig.setProcessType("Defin");
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
	public void testGetForecastConfigurationTableData_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWsSearchRequest.setCount(true);
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = new GtnWebServiceSearchCriteria();

		List<String> primaryIdList = Arrays.asList("a", "ab", "abc");
		gtnWebServiceSearchCriteria.setFilterValue3(primaryIdList);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWsSearchRequest.setCount(false);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceSearchCriteriaList1=new ArrayList<>();
		gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceSearchCriteriaList1);
		
		GtnUIFrameworkWebserviceResponse res=ins.getForecastConfigurationTableData(gtnWsRequest);
		assertFalse(res == null);
	}
	
	//case 2 GetForecastConfigurationTableData if block 428
	@Test
	public void testGetForecastConfigurationTableData_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWsSearchRequest.setCount(true);
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = new GtnWebServiceSearchCriteria();

		List<String> primaryIdList = Arrays.asList("a", "ab", "abc");
		gtnWebServiceSearchCriteria.setFilterValue3(primaryIdList);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWebServiceSearchCriteria.setFilter(true);
		GtnUIFrameworkWebserviceResponse res=ins.getForecastConfigurationTableData(gtnWsRequest);
		assertFalse(res == null);
}
	
	
	@Test
	public void testValidateSaveForecastConfiguration_1() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(0);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	@Test
	public void testValidateSaveForecastConfiguration_2() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	
	@Test
	public void testValidateSaveForecastConfiguration_3() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setMode("Perio");
		fcRequest.setBusinessProcess(1);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	
	//c4 for private void validateInterval
	@Test
	public void testValidateSaveForecastConfiguration_4() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c5 for private void validateInterval
	@Test
	public void testValidateSaveForecastConfiguration_5() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c6 for private void validateInterval
	@Test
	public void testValidateSaveForecastConfiguration_6() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("0");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	

	//c7 for private void validateInterval
	@Test
	public void testValidateSaveForecastConfiguration_7() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureInterval("4");
		fcRequest.setFutureFrequency(4554);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);	
	}
	
	//c8 for validateIntervalDefined
	@Test
	public void testValidateSaveForecastConfiguration_8() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureInterval("4");
		fcRequest.setFutureFrequency(0);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c9 for validateIntervalDefined
	@Test
	public void testValidateSaveForecastConfiguration_9() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureFrequency(1);
		fcRequest.setFutureInterval("0");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c10 for validateIntervalDefined
	@Test
	public void testValidateSaveForecastConfiguration_10() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		fcRequest.setFutureFrequency(1);
		fcRequest.setFutureInterval(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c11 for validateIntervalDefined // if block line 612
	@Test
	public void testValidateSaveForecastConfiguration_11() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Perio");
		fcRequest.setHistoryFrequency(1);
		fcRequest.setHistoryInterval("1");
		fcRequest.setProcessType("value");
		fcRequest.setFutureInterval("4");
		fcRequest.setFutureFrequency(4554);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
		
//		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
//		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setFromDate(new Date());
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	
	//c13 for validatePeriodDefined
	@Test
	public void testValidateSaveForecastConfiguration_13() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setFromDate(null);
		fcRequest.setProcessType("Defined");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c14 for validatePeriod
	@Test
	public void testValidateSaveForecastConfiguration_14() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusYears(4);

		fcRequest.setFromDate(new Date(localDate.toEpochDay() * DateUtils.MILLIS_PER_DAY));
		fcRequest.setProcessType("Auto Update");
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c15 for validatePeriod 
	@Test
	public void testValidateSaveForecastConfiguration_15() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setProcessType("Defined");
		fcRequest.setToDate(null);
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c16 for validatePeriod 
	@Test
	public void testValidateSaveForecastConfiguration_16() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setProcessType("Define");
		fcRequest.setToDate(new Date());
		fcRequest.setFromDate(new Date());
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c17  for validatePeriodDefined
	@Test
	public void testValidateSaveForecastConfiguration_17() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setMode("Period");
		fcRequest.setToDate(new Date());
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	//c18 for validatePeriodDefined
	@Test
	public void testValidateSaveForecastConfiguration_18() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setFromDate(new Date());
		fcRequest.setProcessType("val");
		fcRequest.setProcessType("Defined");
		fcRequest.setMode("Period");
		Calendar cal = Calendar.getInstance();
		cal.set(2018,2, 31);
		fcRequest.setToDate(cal.getTime());
		ins.validateSaveForecastConfiguration(fcRequest, fcResponse);
	}
	
	// c19 for ValidatePeriodDefined
	@Test
	public void testValidateSaveForecastConfiguration_19() throws GtnFrameworkGeneralException {
		GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
		GtnWsForecastConfigurationResponse fcResponse=new GtnWsForecastConfigurationResponse();
		fcRequest.setBusinessProcess(1);
		fcRequest.setBusinessProcess(1);
		Calendar cal1=Calendar.getInstance();
		cal1.set(2017,10,1);
		fcRequest.setFromDate(cal1.getTime());
		fcRequest.setToDate(null);
		fcRequest.setProcessType("val");
		fcRequest.setProcessType("Defined");
		fcRequest.setMode("Period");
		Calendar cale = Calendar.getInstance();
		cale.set(2018, 2, 31);
		fcRequest.setToDate(cale.getTime());
		fcRequest.setToDate(null);
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
	public void testHistoryIntervalValueChangeFail() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationController in=Mockito.spy(ins);
		doReturn(null).when(in).historyIntervalValueChangeLogic(Mockito.any());
		in.historyIntervalValueChange(request);
	}
	
	@Test
	public void testHistoryIntervalValueChangeLogic_Fail() {
		GtnWsForecastConfigurationRequest request=new GtnWsForecastConfigurationRequest();
		SessionFactory factory=Mockito.mock(SessionFactory.class);
		doReturn(null).when(factory).openSession();
		ins.historyIntervalValueChangeLogic(request);
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
	public void testFutureFrequencyValueChange_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationController in=Mockito.spy(ins);
		doReturn(null).when(in).futureIntervalDynamicValueChangeLogic(Mockito.any());
		GtnUIFrameworkWebserviceResponse result= in.futureFrequencyValueChange(gtnWsRequest);
		
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
	
	@Test
	public void testFutureIntervalValueChangeLogic_3() {
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
		
		table.setDescription("Quarter");
		
		GtnWsForecastConfigurationResponse result=ins.futureIntervalDynamicValueChangeLogic(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testFutureIntervalValueChangeLogic_4() {
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
		
		table.setDescription("Semi-Annual");
		
		GtnWsForecastConfigurationResponse result=ins.futureIntervalDynamicValueChangeLogic(request);
		assertFalse(result==null);
	}
	
	@Test
	public void testFutureIntervalValueChangeLogic_5() {
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
		
		table.setDescription("Annual");
		
		GtnWsForecastConfigurationResponse result=ins.futureIntervalDynamicValueChangeLogic(request);
		assertFalse(result==null);
	}
	
	
	
	@Test
	public void testGetQuarterForMonth_1() {
		String actual=ins.getQuarterForMonth(1);
		String expected="1";
		assertEquals(expected, actual);
	}

	@Test
	public void testGetQuarterForMonth_2() {
		String actual=ins.getQuarterForMonth(4);
		String expected="2";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetQuarterForMonth_3() {
		String actual=ins.getQuarterForMonth(7);
		String expected="3";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetQuarterForMonth_4() {
		String actual=ins.getQuarterForMonth(10);
		String expected="4";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetQuarterForMonth_5() {
		String actual=ins.getQuarterForMonth(20);
		String expected="1";
		assertEquals(expected, actual);
	}
	
		
	@Test
	public void testGetSemmiAnnualForMonth_1() {
		String actual=ins.getSemmiAnnualForMonth(1);
		String expected="1";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSemmiAnnualForMonth_2() {
		String actual=ins.getSemmiAnnualForMonth(7);
		String expected="2";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSemmiAnnualForMonth_3() {
		String actual=ins.getSemmiAnnualForMonth(13);
		String expected="1";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdateActiveEndDate() throws GtnFrameworkGeneralException {
		Session session = null;
		int businessProcess=1;
		ins.updateActiveEndDate(session, businessProcess);
	}

	@Test
	public void testGetGtnWsSqlService() {
		ins.getGtnWsSqlService();
	}
	
	@Test
	public void testGetSysSchemaCatalog() throws GtnFrameworkGeneralException {
		ins.getSysSchemaCatalog();
	}
	
	@Test
	public void testGetSysSchemaCatalog_Fail() throws GtnFrameworkGeneralException {
		
		ins.getSysSchemaCatalog();
	}
	
	@Test
	public void testGetForecastYear() throws Exception {
		ins.getForecastYear("v", "val");
	}
}
