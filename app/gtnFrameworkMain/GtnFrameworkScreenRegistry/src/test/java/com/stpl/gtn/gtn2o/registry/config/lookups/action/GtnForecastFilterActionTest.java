package com.stpl.gtn.gtn2o.registry.config.lookups.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnForecastFilterActionTest {

	GtnForecastFilterAction instance = new GtnForecastFilterAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test"
				));
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.any(), Mockito.any())).thenReturn(compData);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		  PagedGrid pagedGrid = Mockito.mock(PagedGrid.class);
		doReturn(compData).when(base).getComponentData();
		doReturn(pagedGrid).when(compData).getCustomData();
		Grid<GtnWsRecordBean> grid = Mockito.mock(Grid.class);
		doReturn(grid).when(pagedGrid).getGrid();
		ListDataProvider<GtnWsRecordBean> dataprovider = Mockito.mock(ListDataProvider.class);
		doNothing().when(dataprovider).setFilter(Mockito.any(), Mockito.any());
		doReturn(dataprovider).when(grid).getDataProvider();
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
	}
}
