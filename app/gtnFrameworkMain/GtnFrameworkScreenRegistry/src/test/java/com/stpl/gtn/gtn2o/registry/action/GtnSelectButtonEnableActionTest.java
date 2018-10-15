package com.stpl.gtn.gtn2o.registry.action;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
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
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnSelectButtonEnableActionTest {

	GtnSelectButtonEnableAction instance = new GtnSelectButtonEnableAction();
	
	
	@Test
	public void doActionTest1() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				"test","test","test"
				));
		
		AbstractComponent abstractComponent = Mockito.mock(AbstractComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);

		doReturn(abstractComponent).when(base).getComponent();
		doReturn(compData).when(abstractComponent).getData();
		PagedGrid pagedGrid = Mockito.mock(PagedGrid.class);
		doReturn(pagedGrid).when(compData).getPagedGrid();
		
		Set<GtnWsRecordBean> selectedItem = new HashSet<>();
		GtnWsRecordBean e = new GtnWsRecordBean();
		e.addProperties("test");
		e.addProperties("test");
		selectedItem.add(e );
		
		Grid<GtnWsRecordBean> grid = new Grid<>();
		
		doReturn(grid).when(pagedGrid).getGrid();
		
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
		
	}
	@Test
	public void doActionTest2() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				"test","test","test"
				));
		
		AbstractComponent abstractComponent = Mockito.mock(AbstractComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);

		doReturn(abstractComponent).when(base).getComponent();
		doReturn(compData).when(abstractComponent).getData();
		PagedGrid pagedGrid = Mockito.mock(PagedGrid.class);
		doReturn(pagedGrid).when(compData).getPagedGrid();
		
		
		Grid<GtnWsRecordBean> grid = Mockito.mock(Grid.class);
		
		doReturn(grid).when(pagedGrid).getGrid(); 
		
		Set<GtnWsRecordBean> selectedItem = new HashSet<>();
		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		recordBean.addProperties("test");
		recordBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6));
		recordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6));
		selectedItem.add(recordBean );
		
		
		Set<GtnWsRecordBean>  spy = Mockito.spy(selectedItem);
		doReturn(false).when(spy).isEmpty();
		doReturn(selectedItem).when(grid).getSelectedItems();
		boolean isEmpty = selectedItem.isEmpty();
		System.out.println("" + isEmpty);
		
		
		
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		instance.createInstance();
		instance.configureParams(gtnUIFrameWorkActionConfig);
		
		
	}
	
}
