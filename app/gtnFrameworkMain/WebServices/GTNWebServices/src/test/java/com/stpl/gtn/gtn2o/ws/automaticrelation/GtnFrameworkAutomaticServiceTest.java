package com.stpl.gtn.gtn2o.ws.automaticrelation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkAutomaticServiceTest {

	@Autowired
	GtnFrameworkAutomaticService service;
	@Test
	public void checkAndUpdateAllRelationShip() throws InterruptedException {
		try {
			System.setProperty("com.stpl.gtnframework.base.path", "D:/SriThAr/Allergan/Server");
			long startTime = System.currentTimeMillis();
			service.checkAndUpdateAllRelationShip("");
			service.waitForRelaitonUpdatetoFinish();
			service.waitForRelaitonUpdatetoFinish();
			System.out.println(System.currentTimeMillis() - startTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
