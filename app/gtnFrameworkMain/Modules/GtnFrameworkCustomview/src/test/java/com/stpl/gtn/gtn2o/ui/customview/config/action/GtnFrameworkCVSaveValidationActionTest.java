package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.powermock.api.mockito.PowerMockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.when;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;

import de.steinwedel.messagebox.MessageBox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkCVSaveValidationActionTest {

	GtnFrameworkCVSaveValidationAction ins = new GtnFrameworkCVSaveValidationAction();
	
	@Test
	public void testConfigureParams() {
	}


	@Test
	public void testDoAction_1() throws Exception {
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		String[] fields = null;    
		resetActionParams.set(1, fields);
		resetActionParams.set(3, "");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		gtnWsRecordBean.addAdditionalProperty(new Object());
		List<GtnWsRecordBean> list = new ArrayList<GtnWsRecordBean>();
		//list.add(gtnWsRecordBean);
		 
		doReturn(list).when(base).getItemsFromDataTable();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws Exception {
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		String[] fields = {"1","2","3","4"};    
		resetActionParams.set(1, fields);
		resetActionParams.set(3, "");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		
		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		gtnWsRecordBean.addAdditionalProperty(new Object());
		List<GtnWsRecordBean> list = new ArrayList<GtnWsRecordBean>();
		list.add(gtnWsRecordBean);
		 
		doReturn(list).when(base).getItemsFromDataTable();
		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("1");
		
		doReturn("Sales").when(base).getStringFromField();
		doReturn(1).when(base).getIntegerFromField();
		
		GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();
		cvRequest.setCustomViewType("Sales");
		List<GtnWsRecordBean> ls = new ArrayList<>();
		ls.add(new GtnWsRecordBean());
		doReturn(ls).when(base).getItemsFromDataTable();
		
		GtnWsRecordBean bean=new GtnWsRecordBean();

		bean.setRecordHeader(Arrays.asList(1,2));
		bean.setProperties(Arrays.asList(1,2));
		bean.addProperties("val");
		bean.addProperties("val2");
	
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	
	}
}
