package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	GtnFrameworkIfpItemsTabConfigTest.class,
	GtnFrameworkIfpConfigTest.class,
	GtnFrameworkIfpItemAdditionTabConfigTest.class,
	GtnFrameworkIfpInformationTabConfigTest.class,
	GtnFrameworkIfpAddConfigTest.class,
	GtnFrameworkIfpLandingScreenConfigTest.class,
	GtnFrameworkParentIfpPopupConfigTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
