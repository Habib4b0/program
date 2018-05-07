package com.stpl.gtn.gtn2o.ws.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCommonCalculationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnReport-DITest.xml" })
public class SpringInjectionTest {

	@Autowired
	GtnWsCommonCalculationService calcService;

	@Test
	public void testData() {
		System.out.println("Test");
		calcService.disp();
	}
}
