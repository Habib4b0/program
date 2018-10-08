package com.stpl.gtn.gtn2o.registry.action;

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
import java.util.List;

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
import com.vaadin.ui.TreeGrid;


@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkSaveViewActionTest {

	
	GtnFrameworkSaveViewAction instance = new GtnFrameworkSaveViewAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test",
				"test","test","test","test","test","test","test","test","test","test"
				));
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.any(), Mockito.any())).thenReturn(compData1);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.any(), Mockito.any())).thenReturn(compData);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		TreeGrid<GtnWsRecordBean> rightTable = Mockito.mock(TreeGrid.class);
		TreeData<GtnWsRecordBean> treeData = Mockito.mock(TreeData.class);
		List<GtnWsRecordBean> list1 = new ArrayList<>();
		
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		doReturn("1").when(base).getV8PopupFieldValue();
		doReturn("1").when(base).getV8StringFromField();
		doReturn(compData1).when(base).getComponentData();
		doReturn(dualListBoxBean).when(compData).getCustomData();
		doReturn(rightTable).when(dualListBoxBean).getRightTable();
		GtnWsRecordBean recordBean1 = new GtnWsRecordBean();
		recordBean1.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		recordBean1.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		list1.add(recordBean1);

		doReturn(recordBean1).when(compData1).getCustomData();
		doReturn(treeData).when(rightTable).getTreeData();
		doReturn(list1).when(treeData).getRootItems();
		doNothing().when(rightTable).expand(Mockito.anyCollection());
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
	}
}
