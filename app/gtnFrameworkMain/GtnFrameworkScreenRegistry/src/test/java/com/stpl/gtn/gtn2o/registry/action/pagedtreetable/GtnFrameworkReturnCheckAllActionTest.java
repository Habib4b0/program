package com.stpl.gtn.gtn2o.registry.action.pagedtreetable;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
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
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkReturnCheckAllActionTest {

	
	GtnFrameworkReturnCheckAllAction instance = new GtnFrameworkReturnCheckAllAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test","test"
				
				));
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString(),Mockito.anyString())).thenReturn(compData);
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		doReturn(gtnForecastBean).when(compData).getCustomData();
		FreezePagedTreeTable resultsTable = Mockito.mock(FreezePagedTreeTable.class);
		List<Object> customDataList = Mockito.mock(ArrayList.class);
		ExtPagedTreeTable ext = Mockito.mock(ExtPagedTreeTable.class);
		GtnUIFrameworkPagedTreeTableLogic tableLogic = Mockito.mock(GtnUIFrameworkPagedTreeTableLogic.class);
		GtnUIFrameworkActionParameter actionParameter = new GtnUIFrameworkActionParameter();
		actionParameter.setCurrentValue(true);
		gtnUIFrameWorkActionConfig.setActionParameter(actionParameter ); 
		String componentId = "";
		GtnFrameworkReturnCheckAllAction spy = Mockito.spy(instance);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		doReturn(response).when(spy).callWebservice(Mockito.any(), Mockito.any());
		doReturn(customDataList).when(compData).getCustomDataList();
		doReturn(resultsTable).when(customDataList).get(Mockito.eq(0));
		doReturn(ext).when(resultsTable).getLeftFreezeAsTable(); 
		doReturn(tableLogic).when(ext).getContainerLogic();
		GtnWsRecordBean dto = new GtnWsRecordBean();
		spy.doAction(componentId , gtnUIFrameWorkActionConfig);
		instance.createInstance(); 
		instance.configureParams(gtnUIFrameWorkActionConfig);
	}
}
