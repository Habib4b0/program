package com.stpl.gtn.gtn2o.registry.action;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
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
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnCustomerSelectionRelationshipLoadActionTest {

	GtnCustomerSelectionRelationshipLoadAction instance = new GtnCustomerSelectionRelationshipLoadAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);

		
		String s1 = "1";
		String s2 = "1";
		String s3 = "1";
		
		GtnWsRelationshipBuilderBean gtnRelationshipBean = new GtnWsRelationshipBuilderBean();
		gtnRelationshipBean.setRelationshipBuilderSid(1);
		gtnRelationshipBean.setRelationshipName("test");
		gtnRelationshipBean.setVersionNo(1);
		
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		gtnUIFrameWorkActionConfig.addActionParameter(s1);
		gtnUIFrameWorkActionConfig.addActionParameter(s2);
		
		GtnWsRecordBean bean = new GtnWsRecordBean();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		
		doReturn(compData).when(base).getComponentData();
		
		doReturn(bean).when(compData).getCustomData();
				
		doReturn(compData).when(base).getComponentData();
		
		doReturn(bean).when(compData).getCustomData();

		doReturn("1").when(base).getCaptionFromV8ComboBox();
		
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		List<GtnWsRelationshipBuilderBean> value= new ArrayList<>();
		value.add(gtnRelationshipBean);
		Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap = new HashMap<>();
		relationshipMap.put(7, value );
		
		Map<Integer, String> hierarchyMap = new HashMap<>();
		hierarchyMap.put(12, "value");
		bean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8));
		bean.setProperties(Arrays.asList(0,1,2,3,4,5,6,hierarchyMap,relationshipMap));
//		GtnCustomerSelectionRelationshipLoadAction spy = Mockito.spy(instance);
		
		String componentId="";
		GtnCustomerSelectionRelationshipLoadAction spy = Mockito.spy(instance);
		doReturn(value).when(spy).getList(Mockito.any(),Mockito.any());
		
		spy.doAction(componentId, gtnUIFrameWorkActionConfig);
		spy.createInstance();
		
		
	}
	
	@Test
	public void listTest()
	{
		List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanListMapper = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		
		instance.getList(relationshipBuilderBeanListMapper, mapper);
		
		
		
	}
	
}
