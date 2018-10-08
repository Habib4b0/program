package com.stpl.gtn.gtn2o.registry.action;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.client.widgets.Grid.HeaderCell;
import com.vaadin.client.widgets.Grid.HeaderRow;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.HierarchicalDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkScreenRegistryResetActionTest {

	GtnFrameworkScreenRegistryResetAction instance = new GtnFrameworkScreenRegistryResetAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkComponentConfig compConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkV8DualListBoxConfig dualBoxConfig = Mockito.mock(GtnUIFrameworkV8DualListBoxConfig.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test","test","test"
				)
				);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		com.vaadin.ui.components.grid.HeaderRow headerRow = Mockito.mock(com.vaadin.ui.components.grid.HeaderRow.class);
		com.vaadin.ui.components.grid.HeaderCell cell = Mockito.mock(com.vaadin.ui.components.grid.HeaderCell.class);
		TreeGrid<GtnWsRecordBean> rightTable =  Mockito.mock(TreeGrid.class);
		HierarchicalDataProvider<T, ?> dataProvider = Mockito.mock(HierarchicalDataProvider.class);
		List<GtnWsRecordBean> list = new ArrayList<>();
		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		recordBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6));
		recordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6));
		list.add(recordBean);
		Iterator<GtnWsRecordBean> iterator = Mockito.mock(Iterator.class);
		TreeData<GtnWsRecordBean> treeData = Mockito.mock(TreeData.class);
		Component component = Mockito.mock(Component.class);
		HorizontalLayout horizontalLayout = Mockito.mock(HorizontalLayout.class);
		Grid<GtnWsRecordBean> leftTable = Mockito.mock(Grid.class);
		TextField textField =  Mockito.mock(TextField.class);
		doReturn(rightTable).when(dualListBoxBean).getRightTable();
		doReturn(treeData).when(rightTable).getTreeData();
		doReturn(textField).when(horizontalLayout).getComponent(0);
		doReturn(compData).when(base).getComponentData();
		doReturn(compConfig).when(base).getComponentConfig();
		doReturn(dualListBoxBean).when(compData).getCustomData();
		doReturn(headerRow).when(leftTable).getHeaderRow(Mockito.anyInt());
		doReturn(cell).when(headerRow).getCell(Mockito.anyString());
		doReturn(horizontalLayout).when(cell).getComponent();
		doReturn(leftTable).when(dualListBoxBean).getLeftTable();
		doReturn(list).when(treeData).getRootItems();
		doReturn(dataProvider).when(rightTable).getDataProvider();
		doReturn(dualBoxConfig).when(compConfig).getGtnUIFrameworkV8DualListBoxConfig();
		Object[] object = {"test"};
		doReturn(object ).when(dualBoxConfig).getLeftVisibleColumns();
		doNothing().when(base).loadV8FieldValue(Mockito.any());
		doNothing().when(base).setV8PopupFieldValue(Mockito.any());
		doNothing().when(base).loadV8ComboBoxComponentValue(Mockito.anyInt());
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
	}
}
