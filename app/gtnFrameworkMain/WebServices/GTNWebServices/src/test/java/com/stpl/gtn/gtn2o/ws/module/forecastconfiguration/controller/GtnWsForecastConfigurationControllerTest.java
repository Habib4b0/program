package com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller;

import static org.junit.Assert.assertFalse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.doReturn;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
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
		
		//Session session = gtnSqlQueryEngine.getSessionFactory().openSession();
		HelperTable table = new HelperTable();
		table.setDescription("Month");
		
		doReturn(table).when(session).load(HelperTable.class, 1);
		
		GtnUIFrameworkWebserviceResponse result =ins.futureFrequencyValueChange(request);
		assertFalse(result==null);
	}
	
	

	@Test
	public void testPeriodModeValue() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse result=ins.periodModeValue(gtnWsRequest);
		assertFalse(result==null);
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
	
}
