package com.stpl.gtn.gtn2o.ui.module.customergroup.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

public class GtnFrameworkCGrpConfigTest {

	@Test
	public void testGetCGrpRootConfig(){
		GtnFrameworkCGrpConfig fixture = new GtnFrameworkCGrpConfig();

		GtnUIFrameworkRootConfig result = fixture.getCGrpRootConfig();

		
		assertNotNull(result);
	}

}