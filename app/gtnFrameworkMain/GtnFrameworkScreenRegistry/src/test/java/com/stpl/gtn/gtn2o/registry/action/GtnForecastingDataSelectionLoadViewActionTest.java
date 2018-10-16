package com.stpl.gtn.gtn2o.registry.action;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
public class GtnForecastingDataSelectionLoadViewActionTest {
	
	GtnForecastingDataSelectionLoadViewAction instance = new GtnForecastingDataSelectionLoadViewAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		gtnUIFrameWorkActionConfig.addActionParameter(0);
		gtnUIFrameWorkActionConfig.addActionParameter("test");
		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("");
		actionParametersList.add("");
		actionParametersList.add("test");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList );
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		GtnFrameworkV8DualListBoxBean dualListBoxBean =  Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		TreeGrid<GtnWsRecordBean> rightTable = new TreeGrid<>();
		dualListBoxBean.setRightTable(rightTable );
		doReturn(rightTable).when(dualListBoxBean).getRightTable();
		
		GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = Mockito.mock(GtnUIFrameworkHierarchyTreeBuilder.class);
		doReturn(gtnUIFrameworkHierarchyTreeBuilder).when(dualListBoxBean).getTreeBuilder();
		
						
		doReturn(compData).when(base).getComponentData();
		GtnUIFrameworkBaseComponent base1 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);

		doReturn(compData1).when(base).getData();
		doReturn(dualListBoxBean).when(compData1).getCustomData();
		
		GtnWsRecordBean recordBean =new GtnWsRecordBean();
		GtnFrameworkForecastDataSelectionBean dataSelectionBean = new GtnFrameworkForecastDataSelectionBean();
		GtnWsRecordBean productHierarchyRecordBean = new GtnWsRecordBean();
		productHierarchyRecordBean.addProperties("test");
		dataSelectionBean.setProductHierarchyRecordBean(productHierarchyRecordBean );
		GtnWsRecordBean productGroup = new GtnWsRecordBean();
		productGroup.addProperties("test");
		dataSelectionBean.setProductGroup(productGroup );
		dataSelectionBean.setCompany(100);
		dataSelectionBean.setBusinessUnit(100);
		dataSelectionBean.setProjectionName("test");
		dataSelectionBean.setProjectionDescription("test");
		dataSelectionBean.setCustomerHierarchyLevel(100);
		dataSelectionBean.setProductHierarchyLevel(100);
		GtnWsRecordBean customerHierarchyRecordBean = new GtnWsRecordBean();
		customerHierarchyRecordBean.addProperties("test");
		dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyRecordBean );
		dataSelectionBean.setCustomerGroup(customerHierarchyRecordBean);
		recordBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7));
		recordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6,dataSelectionBean));
		
		doReturn(recordBean).when(compData).getCustomData();
		
		Object value="test";
		doNothing().when(base).setV8PopupFieldValue(value);

		String componentId= "";
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
		instance.createInstance();
		instance.configureParams(gtnUIFrameWorkActionConfig);
		
	}

}
