package com.stpl.gtn.gtn2o.registry.action;

import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkFromAndToLoadBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnModeOptionValueChangeActionTest {

	GtnModeOptionValueChangeAction instance = new GtnModeOptionValueChangeAction();
	
	@Test
	public void doActionTest1() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test"
			
				));
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		GtnFrameworkFromAndToLoadBean bean = GtnFrameworkFromAndToLoadBean.getInstance();
		List<Integer> fromPeriodItemCodeList = new ArrayList<>();
		fromPeriodItemCodeList.add(1);
		bean.setFromPeriodItemCodeList(fromPeriodItemCodeList );
		List<String> fromPeriodItemValueList = new ArrayList<>();
		fromPeriodItemValueList.add("test");
		bean.setFromPeriodItemValueList(fromPeriodItemValueList );
		List<Integer> toPeriodItemCodeList = new ArrayList<>();
		toPeriodItemCodeList.add(1);
		List<String> toPeriodItemValueList = new ArrayList<>();
		toPeriodItemValueList.add("test");
		bean.setToPeriodItemValueList(toPeriodItemValueList );
		bean.setToPeriodItemCodeList(toPeriodItemCodeList);
		doReturn("Add").when(base).getV8StringFromField();
		
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
		
	}
	@Test
	public void doActionTest2() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkComponentData compData1 = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = Mockito.mock(GtnFrameworkV8DualListBoxBean.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test"
			
				));
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		GtnFrameworkFromAndToLoadBean bean = GtnFrameworkFromAndToLoadBean.getInstance();
		List<Integer> fromPeriodItemCodeList = new ArrayList<>();
		fromPeriodItemCodeList.add(1);
		bean.setFromPeriodItemCodeList(fromPeriodItemCodeList );
		List<String> fromPeriodItemValueList = new ArrayList<>();
		fromPeriodItemValueList.add("test");
		bean.setFromPeriodItemValueList(fromPeriodItemValueList );
		List<Integer> toPeriodItemCodeList = new ArrayList<>();
		toPeriodItemCodeList.add(1);
		List<String> toPeriodItemValueList = new ArrayList<>();
		toPeriodItemValueList.add("test");
		bean.setToPeriodItemValueList(toPeriodItemValueList );
		bean.setToPeriodItemCodeList(toPeriodItemCodeList);
		doReturn("Search").when(base).getV8StringFromField();
		
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
		
		
	}
}
