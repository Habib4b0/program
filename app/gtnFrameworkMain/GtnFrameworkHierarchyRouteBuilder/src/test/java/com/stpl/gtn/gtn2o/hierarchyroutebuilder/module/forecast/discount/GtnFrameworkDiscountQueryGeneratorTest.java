package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecast.discount;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.GtnFrameworkHierarchyQueryGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkHierarchyRouteBuilder-test.xml" })
@Ignore
public class GtnFrameworkDiscountQueryGeneratorTest {
	@Autowired
	GtnFrameworkHierarchyQueryGenerator queryGenerator;

	@Test
	public void getQueryForLoadingDiscount() {
		GtnFrameworkQueryGeneratorBean query = new GtnFrameworkQueryGeneratorBean();
		queryGenerator.generateQuery("AUTO_UPDATE_DEDUCTION", query);
		System.out.println(query.generateQuery());
	}
}
