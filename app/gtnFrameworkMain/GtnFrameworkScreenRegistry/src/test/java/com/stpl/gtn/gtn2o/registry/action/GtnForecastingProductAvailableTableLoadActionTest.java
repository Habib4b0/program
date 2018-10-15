package com.stpl.gtn.gtn2o.registry.action;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.vaadin.ui.AbstractComponent;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnForecastingProductAvailableTableLoadActionTest {

	
	GtnForecastingProductAvailableTableLoadAction instance = new GtnForecastingProductAvailableTableLoadAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test","test"
				
				));

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);

		doReturn(compData).when(base).getComponentData();
		
		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		
		doReturn(recordBean).when(compData).getCustomData();
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		recordBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7));
		recordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6,7));
		
		GtnUIFrameworkWebserviceResponse relationResponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnForecastingProductAvailableTableLoadAction spy = Mockito.spy(instance);
		doReturn(relationResponse).when(spy).callWebService(Mockito.any(),Mockito.any());
		doReturn(relationResponse).when(spy).callGtnWebService(Mockito.any());
		doReturn(relationResponse).when(spy).callHierarchyWebService(Mockito.any());
		doReturn(relationResponse).when(spy).callProductHierarchyWebService(Mockito.any(), Mockito.any());
		
		Map<String, String> tempTableMap = new HashMap<>();
		tempTableMap.put("test", "test");
		GtnForecastHierarchyInputBean outputBean = Mockito.mock(GtnForecastHierarchyInputBean.class);
		outputBean.setTempTableMap(tempTableMap);
		List<GtnReportHierarchyLevelBean> levelList = new ArrayList<>();
		GtnReportHierarchyLevelBean bean = new GtnReportHierarchyLevelBean();
		bean.setLevel("test");
		bean.setHierarchyType("test");
		bean.setHierarchyDefSid(1);
		bean.setHierarchyLevelDefSid("1");
		bean.setHierarchyVersionNo(1);
		bean.setLevelNo(1);
		levelList.add(bean );
		outputBean.setLevelList(levelList );
		GtnWsForecastResponse foreCastResponse = Mockito.mock(GtnWsForecastResponse.class);
		doReturn(foreCastResponse).when(relationResponse).getGtnWsForecastResponse();
		doReturn(outputBean).when(foreCastResponse).getInputBean();
		doReturn(tempTableMap).when(outputBean).getTempTableMap();
		doReturn(levelList).when(outputBean).getLevelList();
		
		AbstractComponent abstractComponenet = Mockito.mock(AbstractComponent.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.any(), Mockito.any())).thenReturn(abstractComponenet);
		doReturn(compData1).when(abstractComponenet).getData();
		GtnFrameworkV8DualListBoxBean dualListBoxBean =Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		doReturn(dualListBoxBean).when(compData1).getCustomData();
		
		
		String componentId = "";
		spy.doAction(componentId, gtnUIFrameWorkActionConfig);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		
		
	}
	@Ignore
	@Test
	public void webserviceTest()
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class); 
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		instance.callWebService(client, request);
	} 
}
