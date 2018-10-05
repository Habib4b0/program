package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.vaadin.ui.AbstractComponent;



@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCfpAddActionTest {

	GtnFrameworkCfpAddAction obj=new GtnFrameworkCfpAddAction();
	@Test
	public void testDoAction()  throws GtnFrameworkGeneralException {
		String componentId ="";
		 
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig =new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		AbstractComponent abstractComponent = Mockito.mock(AbstractComponent.class);
		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("5456456");
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.any())).thenReturn(abstractComponent);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		 
		doNothing().when(base).loadFieldValue("test");
		

		
		GtnUIFrameworkNotesTab notesTab = Mockito.mock(GtnUIFrameworkNotesTab.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.anyString())).thenReturn(notesTab);
		
		ExtPagedTable<ExtContainer<GtnWsRecordBean>> varExtPagedTable = Mockito.mock(ExtPagedTable.class);
		
		doReturn(varExtPagedTable).when(base).getExtPagedTable();
		
		GtnFrameworkCfpAddAction spy = Mockito.spy(obj);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnWsGeneralResponse.setUserName("test");
		response.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		doReturn(response).when(spy).callWebService(Mockito.any());
		 

		spy.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void webServiceTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		obj.callWebService(gtnRequest );
		obj.createInstance();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		obj.configureParams(gtnUIFrameWorkActionConfig );
	}

}
