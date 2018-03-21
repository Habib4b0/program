package com.stpl.gtn.gtn2o.ws.module.forecastdataselection.service;

import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkCCPInsertService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkCustomerLevelLoadServiceTest {
	@Autowired
	private GtnFrameworkCCPInsertService service;

	@Test
	public void getCustomerLevelQuery() {
		try {
			System.setProperty("gtn.app.data.path", "D:/SERVERS/BASEPATH");
			GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
			inputBean.setRelationShipBuilderSid(731);
			inputBean.setGroupFilterCompenies(Collections.<String>emptyList());
			inputBean.setDeductionLevel("");
			inputBean.setDeductionValue("");
			inputBean.setRelationVersionNo(66);
			inputBean.setForecastEligibleDate(null);
			inputBean.setNdc(false);
			inputBean.setHierarchyDefinitionSid(3);
			inputBean.setHierarchyLevelDefinitionSid(18);
			inputBean.setHierarchyVersionNo(1);
			inputBean.setLevelNo(4);
			service.insertToCPPTable(inputBean);
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
