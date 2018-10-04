package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnFrameworkRebatePlanCalculationAddButtonAction;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value= {GtnUIFrameWorkAction.class,GtnUIFrameworkGlobalUI.class,GtnUIFrameworkBaseComponent.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkRebatePlanCalculationAddButtonActionTest {

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkRebatePlanCalculationAddButtonAction instance = new GtnFrameworkRebatePlanCalculationAddButtonAction(); 
        instance.configureParams(gtnUIFrameWorkActionConfig);
	}
   @Ignore
   @Test
	public void testDoAction() throws GtnFrameworkValidationFailedException {
	   String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
        GtnFrameworkRebatePlanCalculationAddButtonAction addButtonAction = new GtnFrameworkRebatePlanCalculationAddButtonAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class,GtnUIFrameworkBaseComponent.class);
        GtnUIFrameworkBaseComponent obj=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn(" ").when(obj).getCaptionFromComboBox();

	}

	@Test
	public void testCreateInstance() {
	        GtnFrameworkRebatePlanCalculationAddButtonAction instance = new GtnFrameworkRebatePlanCalculationAddButtonAction(); 
	        GtnUIFrameWorkAction result = instance.createInstance();
	        assertEquals(instance, result);
	    }	

/*	@Test
	public void testSetRecordProperties() {
		fail("Not yet implemented");
	}

	@Test
	public void testToDouble() {
		fail("Not yet implemented");
	}
*/
}
