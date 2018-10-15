package com.stpl.gtn.gtn2o.ui;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.dynamicclasses.GtnUIFrameworkItemGroupDynamicClassFiller;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { UI.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIGrpTest {

	@InjectMocks
	GtnFrameworkIGrp gtnFrameworkIGrp;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Run the void init(VaadinRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testInit_1() throws Exception {

		try {
			VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);
			UI ui = new UI() {

				@Override
				protected void init(VaadinRequest request) {
					// Init method
				}
			};
			PowerMockito.mockStatic(UI.class, GtnUIFrameworkGlobalUI.class);
			when(UI.getCurrent()).thenReturn(ui);

			GtnUIFrameworkEngine gtnUIFrameworkEngine = Mockito.mock(GtnUIFrameworkEngine.class);
			GtnUIFrameworkRootConfig gtnUIFrameworkRootConfig = Mockito.mock(GtnUIFrameworkRootConfig.class);
			Navigator navigator = Mockito.mock(Navigator.class);
			GtnUIFrameworkItemGroupDynamicClassFiller gtnUIFrameworkIfpDynamicClassFiller = Mockito
					.mock(GtnUIFrameworkItemGroupDynamicClassFiller.class);

			PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(gtnUIFrameworkEngine);
			PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments()
					.thenReturn(gtnUIFrameworkRootConfig);
			PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(navigator);
			PowerMockito.whenNew(GtnUIFrameworkItemGroupDynamicClassFiller.class).withNoArguments()
					.thenReturn(gtnUIFrameworkIfpDynamicClassFiller);

			doNothing().when(gtnUIFrameworkEngine).buildVaadinScreen(gtnUIFrameworkRootConfig, navigator, vaadinRequest,
					ui, "Item Family Plan", gtnUIFrameworkIfpDynamicClassFiller);

			gtnFrameworkIGrp.init(vaadinRequest);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}