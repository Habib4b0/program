package com.stpl.gtn.gtn2o.registry.action;

import org.apache.poi.ss.formula.functions.T;
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
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.vaadin.data.provider.HierarchicalDataProvider;
import com.vaadin.ui.TreeGrid;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkForecastingDStabLoadActionTest {

	
	GtnFrameworkForecastingDStabLoadAction instance = new GtnFrameworkForecastingDStabLoadAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		GtnFrameworkForecastDataSelectionBean forecastDsBean = new GtnFrameworkForecastDataSelectionBean();
		GtnWsRecordBean customerHierarchyRecordBean = new GtnWsRecordBean();
		customerHierarchyRecordBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		customerHierarchyRecordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		forecastDsBean.setCustomerHierarchyRecordBean(customerHierarchyRecordBean );
		forecastDsBean.setCustomerHierarchyLevel(1);
		forecastDsBean.setCustomerHierarchyInnerLevel(1);
		forecastDsBean.setProductHierarchyLevel(1);
		forecastDsBean.setProductHierarchyInnerLevel(1);
		GtnWsRecordBean productHierarchyRecordBean =  new GtnWsRecordBean();
		productHierarchyRecordBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		productHierarchyRecordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		forecastDsBean.setProductHierarchyRecordBean(productHierarchyRecordBean);
		forecastDsBean.setCustomerGroup(customerHierarchyRecordBean);
		forecastDsBean.setProductGroup(productHierarchyRecordBean);
		TreeGrid<GtnWsRecordBean> rightTable = Mockito.mock(TreeGrid.class);
		GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = Mockito.mock(GtnUIFrameworkHierarchyTreeBuilder.class);
		HierarchicalDataProvider<T, ?> dataProvider = Mockito.mock(HierarchicalDataProvider.class);
		
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		doReturn(compData).when(base).getComponentData();
		doReturn(forecastDsBean).when(compData).getSharedPopupData();
		doReturn(compData1).when(base).getData();
		doReturn(dualListBoxBean).when(compData1).getCustomData();
		doReturn(rightTable).when(dualListBoxBean).getRightTable();
		doReturn(gtnUIFrameworkHierarchyTreeBuilder).when(dualListBoxBean).getTreeBuilder();
		doReturn(dataProvider).when(rightTable).getDataProvider();
		
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
	}
}
