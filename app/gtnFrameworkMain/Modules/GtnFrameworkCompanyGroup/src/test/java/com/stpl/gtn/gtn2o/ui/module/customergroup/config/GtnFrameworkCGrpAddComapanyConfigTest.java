package com.stpl.gtn.gtn2o.ui.module.customergroup.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkCGrpAddComapanyConfigTest {
	
	@Test
	public void testGetCGrpAddView(){
		GtnFrameworkCGrpAddComapanyConfig fixture = new GtnFrameworkCGrpAddComapanyConfig();

		GtnUIFrameworkViewConfig result = fixture.getCGrpAddView();

		
		assertNotNull(result);
	}

}