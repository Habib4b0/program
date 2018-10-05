package com.stpl.gtn.gtn2o.ws.module.periodconfiguration.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustViewDetails;
import com.stpl.gtn.gtn2o.ws.entity.periodconfiguration.PeriodConfigMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;


/**
*
* @author Praveen.Kumar
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsPeriodConfigurationControllerTest {

	@Spy
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	
	@InjectMocks
	@Autowired
	GtnWsPeriodConfigurationController ins;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPeriodConfigLoad_1() {
		GtnUIFrameworkWebserviceRequest periodConfigRequest= new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
		gtnWsSearchRequest.setCount(true);
		GtnWebServiceSearchCriteria criteria=new GtnWebServiceSearchCriteria();
		criteria.setFilter(true);
		criteria.setFilterValue1("val");
		criteria.setExpression("LIKE");
		criteria.setFieldId("1");
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList=Arrays.asList(criteria);
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		periodConfigRequest.setGtnWsSearchRequest(gtnWsSearchRequest); 
		
		ins.periodConfigLoad(periodConfigRequest);
	}


	@Test
	public void testPeriodConfigLoad_2() throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest periodConfigRequest= new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
		gtnWsSearchRequest.setCount(false);
		GtnWebServiceSearchCriteria criteria=new GtnWebServiceSearchCriteria();
		criteria.setFilter(true);
		criteria.setFilterValue1("val");
		criteria.setExpression("LIK");
		criteria.setFieldId("1");
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList=Arrays.asList(criteria);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList=Arrays.asList(new GtnWebServiceOrderByCriteria());
		gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		periodConfigRequest.setGtnWsSearchRequest(gtnWsSearchRequest); 
		List<Object[]> queryInputList1 = new ArrayList<>();
		doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());
		
		ins.periodConfigLoad(periodConfigRequest);
	}

	
	@Test
	public void testSavePeriodConfig() throws GtnFrameworkGeneralException, ParseException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsPeriodConfigurationRequest configurationRequest = new GtnWsPeriodConfigurationRequest();

		configurationRequest.setCompany(1);
		configurationRequest.setBusinessUnit(1);
		configurationRequest.setBusinessProcess(1);
		configurationRequest.setModule(1);
		configurationRequest.setPeriodFromTextBox("val");
		configurationRequest.setPeriodFrom(new Date());
		configurationRequest.setDefaultModeFrom(1);
		configurationRequest.setDefaultFrequencyFrom(1);
		configurationRequest.setDefaultPeriodFromTextBox("val");
		configurationRequest.setDefaultDateFrom("12/12/2017");
		configurationRequest.setDateFrom("12/12/2017");
		configurationRequest.setPeriodView("Singl");
		configurationRequest.setModeTo(1);
		configurationRequest.setFrequencyTo(1);
		configurationRequest.setDefaultModeTo(1);
		configurationRequest.setDefaultFrequencyTo(1);
		configurationRequest.setDefaultPeriodToTextBox("val");
		configurationRequest.setDefaultDateTo("12/12/2017");
		configurationRequest.setPeriodToTextBox("val");
		configurationRequest.setDateTo("12/12/2018");
		configurationRequest.setUserId(1);
		
		gtnWsRequest.setGtnWsPeriodConfigurationRequest(configurationRequest);
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction transaction = Mockito.mock(Transaction.class);
		
		GtnWsPeriodConfigurationController spy=Mockito.spy(ins);
		doReturn(factory).when(spy).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(transaction).when(session).beginTransaction();
		doNothing().when(transaction).commit();
		
		List<Object[]> queryInputList1 = new ArrayList<>();
		doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.any(), Mockito.anyList(), Mockito.any(), Mockito.any());
		
		CompanyMaster company = new CompanyMaster();
		HelperTable moduleType = new HelperTable();
		
		doReturn(company).when(session).load(CompanyMaster.class, 0);
		doReturn(moduleType).when(session).load(HelperTable.class, 0);
		
		Criteria cr = Mockito.mock(Criteria.class);
		doReturn(cr).when(session).createCriteria(HelperTable.class);
		doReturn(cr).when(cr).add( Mockito.any(Criterion.class));
		
		List<Object[]> resultList=new  ArrayList<>();
		Object[] ob = {"Singl",2};
		resultList.add(ob);
		doReturn(resultList).when(cr).list();
		
		spy.savePeriodConfig(gtnWsRequest);
	}
	
	
	@Test
	public void testGetDate() throws ParseException {
		String input = "Wed Oct 16 00:00:00 CEST 2013";
		ins.getDate(input);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_1() throws GtnFrameworkGeneralException {
		String expersion="BETWEEN";
		String field="val";
		String value1="1988-07-29 12:12:12.123";
		String value2="1988-07-29 12:12:12.123";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_2() throws GtnFrameworkGeneralException {
		String expersion="AND";
		String field="val";
		String value1="1988-07-29 12:12:12.123";
		String value2="1988-07-29 12:12:12.123";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_3() throws GtnFrameworkGeneralException {
		String expersion="GREATER_OR_EQUAL";
		String field="val";
		String value1="1988-07-29 12:12:12.123";
		String value2="1988-07-29 12:12:12.123";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_4() throws GtnFrameworkGeneralException {
		String expersion="LESS_OR_EQUAL";
		String field="val";
		String value1="1988-07-29 12:12:12.123";
		String value2="1988-07-29 12:12:12.123";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_5() throws GtnFrameworkGeneralException {
		String expersion="EQUALS";
		String field="val";
		String value1="1988-07-29 12:12:12.123";
		String value2="1988-07-29 12:12:12.123";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_6() throws GtnFrameworkGeneralException {
		String expersion="GREATER";
		String field="val";
		String value1="1988-07-29 12:12:12.123";
		String value2="1988-07-29 12:12:12.123";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_7() throws GtnFrameworkGeneralException {
		String expersion="LESS";
		String field="val";
		String value1="1988-07-29 12:12:12.123";
		String value2="1988-07-29 12:12:12.123";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
	
	@Test
	public void testGetWhereClauseForAColumn_Fail() throws GtnFrameworkGeneralException {
		String expersion="LESS";
		String field="val";
		String value1="";
		String value2="";
		
		ins.getWhereClauseForAColumn(expersion, field, value1, value2);
	}
}
