package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import com.google.gwt.user.client.rpc.core.java.util.Arrays;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

import de.steinwedel.messagebox.MessageBox;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageBox.class,GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class})
public class GtnFrameworkDeleteRSActionTest {

	private transient GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDeleteRSActionTest.class); 
	
	GtnFrameworkDeleteRSAction ins = new GtnFrameworkDeleteRSAction();

	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class);
		
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter("1");

		when(GtnUIFrameworkGlobalUI.getSessionProperty("systemId")).thenReturn(1);
		
		GtnFrameworkDeleteRSAction in=Mockito.spy(ins);
		Object[] outBountData = {"Fail"};
		response.setOutBountData(outBountData );
		doReturn(response).when(in).getResponse(Mockito.any(),Mockito.any(), Mockito.any());

		try {
			in.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			gtnLogger.info("Error" +e);
		}
	}
	
	@Test
	public void testGetResponse() {
		String uri="";
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		ins.getResponse( uri, wsclient, request);
		
		
	}
	
	@Test
	public void testCreateInstance(){
		
		ins.createInstance();
	}
	
	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException{
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	

}
