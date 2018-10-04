package com.stpl.gtn.gtn2o.registry.action;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.mockito.Mockito.*;

import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.vaadin.ui.AbstractComponent;


@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnCustomerAvailableTableLoadActionTest {

	GtnCustomerAvailableTableLoadAction instance = new GtnCustomerAvailableTableLoadAction();
	@Test
	public void testConfigpParams()
	{
		GtnForecastingProductAvailableTableLoadAction instance = new GtnForecastingProductAvailableTableLoadAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
		try {
			instance.configureParams(gtnUIFrameWorkActionConfig );
		} catch (GtnFrameworkGeneralException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testCreateInstance() {
		
		
		GtnForecastingProductAvailableTableLoadAction instance = new GtnForecastingProductAvailableTableLoadAction();
		GtnUIFrameWorkAction result  = instance.createInstance(); 
		
	}
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException {

		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		String s1 = "1";
		String s2 = "1";
		String s3 = "1";
		String s4 = "11";
		String s5="2014-05-05";
		String s6="1";
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		gtnUIFrameWorkActionConfig.addActionParameter(s1);
		gtnUIFrameWorkActionConfig.addActionParameter(s2);
		gtnUIFrameWorkActionConfig.addActionParameter(s3);
		gtnUIFrameWorkActionConfig.addActionParameter(s4);
		gtnUIFrameWorkActionConfig.addActionParameter(s5);
		gtnUIFrameWorkActionConfig.addActionParameter(s6);
		GtnWsRecordBean bean = new GtnWsRecordBean();
		bean.addProperties("1");
		bean.addProperties("1");
		bean.addProperties("1");
		bean.addProperties("1");
		bean.addProperties("1");
		bean.addProperties("1");
		bean.addProperties("1");
		bean.addProperties(11);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		 
		doReturn(compData).when(base).getComponentData();
		LocalDate date = LocalDate.now() ;
		doReturn(date).when(base).getFieldValue();
		
		doReturn(bean).when(compData).getCustomData();
		 
		
		AbstractComponent abstractComponent = Mockito.mock(AbstractComponent.class); 
		
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(abstractComponent);
		doReturn(compData1).when(abstractComponent).getData();
		  
		GtnFrameworkV8DualListBoxBean dualListBoxBean =  Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		doReturn(dualListBoxBean).when(compData1).getCustomData();
		  
		GtnCustomerAvailableTableLoadAction spy = Mockito.spy(instance);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		doReturn(response).when(spy).callGtnService(Mockito.any());
		GtnWsForecastResponse gtnWsForecastResponse=new GtnWsForecastResponse();
		GtnForecastHierarchyInputBean inputBean=new GtnForecastHierarchyInputBean();
		inputBean.setHieraryQuery("val");
		List<GtnReportHierarchyLevelBean> levelList=new ArrayList<>();
		GtnReportHierarchyLevelBean e = new GtnReportHierarchyLevelBean();
		e.setHierarchyDefSid(11);
		e.setHierarchyLevelDefSid("1");
		e.setHierarchyNo("1");  
		e.setHierarchyType("type");
		e.setHierarchyVersionNo(1);
		e.setRelationShipBuilderId("1");
		e.setRelationShipVersionNo(1);
		e.setRelationShipSid("1");
		e.setLevelNo(1);
		levelList.add(e );
		inputBean.setLevelList(levelList);
		gtnWsForecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(gtnWsForecastResponse);
		
		
		
		spy.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		
	}
	
	
}
