package com.stpl.gtn.gtn2o.ws.automaticrelation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdate;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkAutomaticRelationUpdateTest {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdate service;

	public GtnFrameworkAutomaticRelationUpdate getService() {
		return service;
	}

	public void setService(GtnFrameworkAutomaticRelationUpdate service) {
		this.service = service;
	}

	@Test
	public void checkAndUpdateAutomaticRelationship() {
		try {
			service.checkAndUpdateAutomaticRelationship(324, "12");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return;
	}
}
