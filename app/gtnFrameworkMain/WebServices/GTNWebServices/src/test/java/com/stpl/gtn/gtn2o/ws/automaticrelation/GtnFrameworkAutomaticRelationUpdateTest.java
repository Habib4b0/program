package com.stpl.gtn.gtn2o.ws.automaticrelation;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkAutomaticRelationUpdateTest {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService service;

	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return service;
	}

	public void setService(GtnFrameworkAutomaticRelationUpdateService service) {
		this.service = service;
	}

	@Test
	public void checkAndUpdateAutomaticRelationship() throws GtnFrameworkGeneralException, InterruptedException {
		service.checkAndUpdateAutomaticRelationship(324);
		return;
	}
}
