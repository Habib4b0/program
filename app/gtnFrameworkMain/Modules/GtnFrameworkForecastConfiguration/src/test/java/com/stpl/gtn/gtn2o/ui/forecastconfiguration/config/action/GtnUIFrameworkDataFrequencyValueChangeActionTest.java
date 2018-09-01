package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.vaadin.v7.ui.ComboBox;

/**
 * @author praveen.kumar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkDataFrequencyValueChangeActionTest {

    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkDataFrequencyValueChangeAction instance = new GtnUIFrameworkDataFrequencyValueChangeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkDataFrequencyValueChangeAction instance = new GtnUIFrameworkDataFrequencyValueChangeAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
	@Test
	public void testDoAction()
		throws Exception {
		GtnUIFrameworkDataFrequencyValueChangeAction instance= new GtnUIFrameworkDataFrequencyValueChangeAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		String componentId = "";
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		
		Constructor cons= (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
        cons.setAccessible(true);
        GtnUIFrameworkBaseComponent object= (GtnUIFrameworkBaseComponent) cons.newInstance(new ComboBox(),null);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
		
		List<Object> parameters = Arrays.asList(1, 2, 3);
		gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);

	}


}