package com.stpl.gtn.gtn2o.hierarchyroutebuilder.querybuilder;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.GtnFrameworkHierarchyQueryGenerator;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkHierarchyRouteBuilder-test.xml" })
@Ignore
public class GtnFrameworkQueryGeneratorTest {
	@Autowired
	GtnFrameworkHierarchyQueryGenerator queryGenerator;

	@Test
	public void generateQuery() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("AUTOMATIC_INSERT", queryBean);
			System.out.println(queryBean.generateQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
