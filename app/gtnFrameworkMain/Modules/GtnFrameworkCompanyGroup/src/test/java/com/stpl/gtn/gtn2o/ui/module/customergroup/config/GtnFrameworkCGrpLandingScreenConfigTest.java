package com.stpl.gtn.gtn2o.ui.module.customergroup.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkCGrpLandingScreenConfigTest {

	@Test
	public void testGetSearchView(){
		GtnFrameworkCGrpLandingScreenConfig fixture = new GtnFrameworkCGrpLandingScreenConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		
		assertNotNull(result);
	}

}