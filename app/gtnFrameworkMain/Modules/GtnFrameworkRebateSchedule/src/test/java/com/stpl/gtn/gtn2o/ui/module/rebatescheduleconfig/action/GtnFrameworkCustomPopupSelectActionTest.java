package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import static org.powermock.api.mockito.PowerMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;



@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class})
public class GtnFrameworkCustomPopupSelectActionTest {
	
	GtnFrameworkCustomPopupSelectAction ins = new GtnFrameworkCustomPopupSelectAction();
	private transient GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCustomPopupSelectActionTest.class); 
	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	
	}

	@Test
	public void testDoAction() throws GtnFrameworkValidationFailedException {
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter("1");
		gtnUIFrameWorkActionConfig.addActionParameter("1");
		gtnUIFrameWorkActionConfig.addActionParameter("1");
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		GtnWsRecordBean recordDto = new GtnWsRecordBean();
		
		recordDto.setRecordHeader(java.util.Arrays.asList(0,1));
		recordDto.setProperties(java.util.Arrays.asList(0,"da"));
	
		doReturn(recordDto).when(base).getValueFromComponent();
		
	
		GtnUIFrameworkComponentData componentViewData=Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentViewData);
		
		List<Object> list=new ArrayList<>(); 
		List<String> list1 = new ArrayList<>();
		list1.add("0");
		list.add(0, " ");
		list.add(1,list1);
		list.add(2,list1);
		doReturn(list).when(componentViewData).getSharedPopupData();
		
		
		try {
			ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			gtnLogger.info("Error" +e);
		}
	}

	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

}
