package com.stpl.gtn.gtn2o.registry.action;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnCustomerAvailableTableLoadActionTest {

	
	@Test
	public void testConfigpParams()
	{
		GtnForecastingProductAvailableTableLoadAction instance = new GtnForecastingProductAvailableTableLoadAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
		try {
			instance.configureParams(gtnUIFrameWorkActionConfig );
		} catch (GtnFrameworkGeneralException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testCreateInstance() {
		
		
		GtnForecastingProductAvailableTableLoadAction instance = new GtnForecastingProductAvailableTableLoadAction();
		GtnUIFrameWorkAction result  = instance.createInstance(); 
		
	}
	
	
}
