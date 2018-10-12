package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;


import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.TreeData;
import com.vaadin.sass.internal.tree.controldirective.WhileNode;
import com.vaadin.ui.TreeGrid;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkForecastingCCPTableLoadActionTest {

	
	GtnFrameworkForecastingCCPTableLoadAction instance = new GtnFrameworkForecastingCCPTableLoadAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		TreeData<GtnWsRecordBean> treeData = Mockito.mock(TreeData.class);
		List<GtnWsRecordBean>  list = new ArrayList<GtnWsRecordBean>();
		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		recordBean.addProperties("test");
		list.add(recordBean);
		TreeGrid<GtnWsRecordBean> rightTable = Mockito.mock(TreeGrid.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test"
				));
		
		List<GtnWsRecordBean> list1 = new ArrayList<>();
		
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.any(), Mockito.any())).thenReturn(compData);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		GtnWsRecordBean recordBean1 = new GtnWsRecordBean();
		recordBean1.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		recordBean1.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		list1.add(recordBean1);
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		doReturn("1").when(base).getV8PopupFieldValue();
		doReturn(1).when(base).getIntegerFromV8ComboBox();
		doReturn("1").when(base).getV8StringFromField();
		doReturn(compData1).when(base).getComponentData();
		doReturn(recordBean1).when(compData1).getCustomData();
		doReturn(dualListBoxBean).when(compData).getCustomData();
		doReturn(rightTable).when(dualListBoxBean).getRightTable(); 
		doReturn(treeData).when(rightTable).getTreeData();
		doReturn(list1).when(treeData).getRootItems();
		doNothing().when(rightTable).expand(Mockito.anyCollection());
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
	}
	@Test
	public void doActionTestForException() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		TreeData<GtnWsRecordBean> treeData = Mockito.mock(TreeData.class);
		List<GtnWsRecordBean>  list = new ArrayList<GtnWsRecordBean>();
		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		recordBean.addProperties("test");
		list.add(recordBean);
		TreeGrid<GtnWsRecordBean> rightTable = Mockito.mock(TreeGrid.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test"
				));
		
		List<GtnWsRecordBean> list1 = new ArrayList<>();
		
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.any(), Mockito.any())).thenReturn(compData);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		GtnWsRecordBean recordBean1 = new GtnWsRecordBean();
		recordBean1.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		recordBean1.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		list1.add(recordBean1);
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		doReturn("1").when(base).getV8PopupFieldValue();
		doReturn("1").when(base).getV8StringFromField();
		doReturn(compData1).when(base).getComponentData();
		doReturn(recordBean1).when(compData1).getCustomData();
		doReturn(dualListBoxBean).when(compData).getCustomData();
		doReturn(rightTable).when(dualListBoxBean).getRightTable(); 
		doReturn(treeData).when(rightTable).getTreeData();
		doReturn(list1).when(treeData).getRootItems();
		doNothing().when(rightTable).expand(Mockito.anyCollection());
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
	}
}
