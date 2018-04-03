package com.stpl.gtn.gtn2o.ws.module.forecastdataselection.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkLoadDiscountService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkLoadDiscountServiceTest {
	@Autowired
	private GtnFrameworkLoadDiscountService service;

	@Test
	public void queryFormationForLoadingDdlb() {
		try {
			System.setProperty("gtn.app.data.path", "D:/SERVERS/BASEPATH");
			GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
			inputBean.setHierarchyIndicator("C");
			inputBean.setProjectionId(2142);
			inputBean.setLevelNo(3);
			System.out.println(service.queryFormationForLoadingDdlb(inputBean));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
