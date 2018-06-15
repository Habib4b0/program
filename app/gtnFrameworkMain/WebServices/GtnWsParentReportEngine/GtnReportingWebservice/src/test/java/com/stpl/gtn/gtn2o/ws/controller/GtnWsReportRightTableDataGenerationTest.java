package com.stpl.gtn.gtn2o.ws.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportRightTableLoadDataService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnReport-SpringContext-Test.xml" })
public class GtnWsReportRightTableDataGenerationTest {
	@Autowired
	GtnWsReportRightTableLoadDataService service;

	@Test
	public void testPRocedureOutput() {
		System.out.println(service.getDataFromBackend(null));
	}
}
