package com.stpl.gtn.gtn2o.ws.module.calendarconfiguration.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.calendarconfig.CalendarConfigDetails;
import com.stpl.gtn.gtn2o.ws.entity.calendarconfig.CalendarConfigMaster;
import com.stpl.gtn.gtn2o.ws.module.calendarconfiguration.logic.GtnWsCalendarConfigurationLogic;
import com.stpl.gtn.gtn2o.ws.module.customview.controller.GtnWsCustomViewController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.calendarconfiguration.GtnWsCalendarConfigurationRequest;


/**
*
* @author Praveen.Kumar
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsCalendarConfigurationControllerTest {

	@InjectMocks
	@Autowired
	GtnWsCalendarConfigurationController ins;

	@Spy
	private GtnWsCalendarConfigurationLogic logic=new GtnWsCalendarConfigurationLogic(ins);
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	



	@Test
	public void testGetCalendarConfigurationTableData_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWsSearchRequest.setCount(false);

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId("val");
		searchCriteria.setExpression("LIKE");
		searchCriteria.setFilterValue1("val");
		searchCriteria.setFilterValue2("val");
		searchCriteria.setFilter(true);
		gtnWebServiceSearchCriteriaList.add(searchCriteria);
		
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList );
		ins.getCalendarConfigurationTableData(gtnWsRequest);
	}
	
	@Test
	public void testGetCalendarConfigurationTableData_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWsSearchRequest.setCount(true);

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId("val");
		searchCriteria.setExpression("LIKE");
		searchCriteria.setFilterValue1("val");
		searchCriteria.setFilterValue2("val");
		searchCriteria.setFilter(true);
		gtnWebServiceSearchCriteriaList.add(searchCriteria);
		
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList );
		ins.getCalendarConfigurationTableData(gtnWsRequest);
	}

	
	@Test
	public void testSaveCalendarConfiguration_1() { 
		GtnUIFrameworkWebserviceRequest gtnWsRequest= new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest calendarConfigurationRequest=new GtnWsCalendarConfigurationRequest();
		calendarConfigurationRequest.setCalendarId(1);
		gtnWsRequest.setCalendarConfigurationRequest(calendarConfigurationRequest);
		
		CalendarConfigMaster calendarConfigMaster = new CalendarConfigMaster();
		List<CalendarConfigDetails> results= new ArrayList<>();
		results.add(new CalendarConfigDetails());
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Criteria cr = Mockito.mock(Criteria.class);
		
		GtnWsCalendarConfigurationController instance=Mockito.spy(GtnWsCalendarConfigurationController.class);
		doReturn(instance).when(logic).getController();
		doReturn(factory).when(instance).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction(); 
		doReturn(calendarConfigMaster).when(session).load(CalendarConfigMaster.class, 1);
		doReturn(cr).when(session).createCriteria(CalendarConfigDetails.class);
		doReturn(cr).when(cr).add( Mockito.any(Criterion.class));
		doReturn(results).when(cr).list();
		
		instance.saveCalendarConfiguration(gtnWsRequest);
	}
	

	@Test
	public void testDeleteCalendarConfiguration_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest calendarConfigurationRequest=new GtnWsCalendarConfigurationRequest();
		gtnWsRequest.setCalendarConfigurationRequest(calendarConfigurationRequest);
		
		ins.deleteCalendarConfiguration(gtnWsRequest);
	}
	
	@Test
	public void testDeleteCalendarConfiguration_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest calendarConfigurationRequest=new GtnWsCalendarConfigurationRequest();
		gtnWsRequest.setCalendarConfigurationRequest(calendarConfigurationRequest);
		
		ins.deleteCalendarConfiguration(gtnWsRequest);
	}
	
	@Test
	public void testLoadCalendarConfiguration() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest calendarConfigurationRequest=new GtnWsCalendarConfigurationRequest();
		gtnWsRequest.setCalendarConfigurationRequest(calendarConfigurationRequest);
		
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Criteria cr = Mockito.mock(Criteria.class);
		
		CalendarConfigMaster calendarConfigMaster = new CalendarConfigMaster();
		List<CalendarConfigDetails> results= new ArrayList<>();
		results.add(new CalendarConfigDetails());
		
		GtnWsCalendarConfigurationController instance=Mockito.spy(GtnWsCalendarConfigurationController.class);
		doReturn(instance).when(logic).getController();
		doReturn(factory).when(instance).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction(); 
		doReturn(calendarConfigMaster).when(session).load(CalendarConfigMaster.class, 1);
		doReturn(cr).when(session).createCriteria(CalendarConfigDetails.class);
		doReturn(cr).when(cr).add( Mockito.any(Criterion.class));
		doReturn(results).when(cr).list();
		
		instance.loadCalendarConfiguration(gtnWsRequest);
	}
	
	
	@Test
	public void testLoadCalendarConfigurationCalendarName_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest calendarConfigurationRequest=new GtnWsCalendarConfigurationRequest();
		calendarConfigurationRequest.setCalendarName("val");
		calendarConfigurationRequest.setCalendarId(0);
		gtnWsRequest.setCalendarConfigurationRequest(calendarConfigurationRequest);
		List<CalendarConfigMaster> resultList= new ArrayList<>();
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Criteria cr = Mockito.mock(Criteria.class);
		
		GtnWsCalendarConfigurationController instance=Mockito.spy(GtnWsCalendarConfigurationController.class);
		doReturn(instance).when(logic).getController();
		doReturn(factory).when(instance).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(cr).when(session).createCriteria(CalendarConfigMaster.class);
		doReturn(resultList).when(cr).list();
		
		instance.loadCalendarConfigurationCalendarName(gtnWsRequest);
	}
	
	@Test
	public void testLoadCalendarConfigurationCalendarName_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest calendarConfigurationRequest=new GtnWsCalendarConfigurationRequest();
		calendarConfigurationRequest.setCalendarName("val");
		calendarConfigurationRequest.setCalendarId(1);
		gtnWsRequest.setCalendarConfigurationRequest(calendarConfigurationRequest);
		List<CalendarConfigMaster> resultList= new ArrayList<>();
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria cr = Mockito.mock(Criteria.class);
		
		GtnWsCalendarConfigurationController instance=Mockito.spy(GtnWsCalendarConfigurationController.class);
		doReturn(instance).when(logic).getController();
		doReturn(factory).when(instance).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(cr).when(session).createCriteria(CalendarConfigMaster.class);
		doReturn(resultList).when(cr).list();
		
		instance.loadCalendarConfigurationCalendarName(gtnWsRequest);
	}
	
	
	@Test
	public void testSaveCalendarConfiguration_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		ins.saveCalendarConfiguration(gtnWsRequest);
	}
	
	@Test
	public void testDeleteCalendarConfiguration_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		ins.deleteCalendarConfiguration(gtnWsRequest);
	}
	
	@Test
	public void testLoadCalendarConfiguration_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		ins.loadCalendarConfiguration(gtnWsRequest);
	}
	
	@Test
	public void testLoadCalendarConfigurationCalendarName() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest=new GtnUIFrameworkWebserviceRequest();
		ins.loadCalendarConfigurationCalendarName(gtnWsRequest);
	}
	
	
}
