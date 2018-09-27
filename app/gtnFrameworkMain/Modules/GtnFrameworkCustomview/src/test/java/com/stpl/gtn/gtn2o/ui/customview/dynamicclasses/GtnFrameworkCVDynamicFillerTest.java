package com.stpl.gtn.gtn2o.ui.customview.dynamicclasses;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atMost;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnFrameworkCVDynamicFillerTest {


	@Test
	public void testAddDynamicObject() {
		GtnFrameworkCVDynamicFiller ins=new GtnFrameworkCVDynamicFiller();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		ins.addDynamicObject();
	}

}
