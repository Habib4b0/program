package com.stpl.gtn.gtn2o.registry.action;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;


import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class})
public class GtnCustomerSelectionForecastLevelLoadActionTest {

	GtnCustomerSelectionForecastLevelLoadAction instance = new GtnCustomerSelectionForecastLevelLoadAction();
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		
		GtnWsRelationshipBuilderBean gtnRelationshipBean = new GtnWsRelationshipBuilderBean();
		gtnRelationshipBean.setRelationshipBuilderSid(1);
		gtnRelationshipBean.setRelationshipName("test");
		gtnRelationshipBean.setVersionNo(1);
		
		
		
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		String s1 = "1";
		String s2="1";
		String s3 = "1";
		String s4 ="1";
		
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		gtnUIFrameWorkActionConfig.addActionParameter(s1);
		gtnUIFrameWorkActionConfig.addActionParameter(s2);
		gtnUIFrameWorkActionConfig.addActionParameter(s3);
		gtnUIFrameWorkActionConfig.addActionParameter(s4);
		
		
		
		
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		GtnWsRecordBean bean = new GtnWsRecordBean();
		
		doReturn(compData).when(base).getComponentData();
		
		doReturn(bean).when(compData).getCustomData();
		
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		
		List<GtnWsRelationshipBuilderBean> value= new ArrayList<>();
		value.add(gtnRelationshipBean);
		Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap = new HashMap<>();
		relationshipMap.put(7, value );
		
		Map<Integer, String> hierarchyMap = new HashMap<>();
		hierarchyMap.put(12, "value");
		bean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8));
		bean.setProperties(Arrays.asList(0,1,2,3,4,5,6,hierarchyMap,relationshipMap));
		GtnCustomerSelectionForecastLevelLoadAction spy = Mockito.spy(instance);
		doReturn(value).when(spy).getList(Mockito.any(),Mockito.any());
		
		spy.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}
	
	
}
