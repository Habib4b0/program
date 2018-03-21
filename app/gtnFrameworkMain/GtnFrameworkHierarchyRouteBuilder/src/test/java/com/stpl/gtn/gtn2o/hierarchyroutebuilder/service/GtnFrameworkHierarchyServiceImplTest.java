package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;


/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkHierarchyRouteBuilder-test.xml" })
@Ignore
public class GtnFrameworkHierarchyServiceImplTest {

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;


	@Test
	public void creatQueryForMultiLevelHierarchy() {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("RS_CONTRACT");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(queryBean);
		String finalQuery = queryBean.generateQuery();
		Assert.assertEquals(true, !finalQuery.isEmpty());
	}

	@Test
	public void getRoutePath() {
		GtnFrameworkRouteBean routePath = hierarchyService.getRoutePath(5, 1);
		Object[] path = { 7, 4 };
		Assert.assertArrayEquals(routePath.getPathList().toArray(), path);

		GtnFrameworkRouteBean customerContractRoutePath = hierarchyService.getRoutePath(1, 2);
		Object[] customerContractPath = { 4, 3, 2, 1 };
		Assert.assertArrayEquals(customerContractRoutePath.getPathList().toArray(), customerContractPath);

		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(6, 7);
		Object[] companyItemPath = { 12, 9, 8 };
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);

		GtnFrameworkRouteBean invalidRoutePath = hierarchyService.getRoutePath(1, 10);
		Object[] invalidPath = {};
		Assert.assertArrayEquals(invalidRoutePath.getPathList().toArray(), invalidPath);
	}


	@Test
	public void getPathByTableNameAndHierarchyType() {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		GtnFrameworkRouteBean routePath = hierarchyService.getPathByTableNameAndHierarchyType("RS_CONTRACT", "UDCS",
				"Deduction Hierarchy");
		hierarchyService.createQuery(routePath, queryBean);
		String finalQuery = queryBean.generateQuery();
		

		Assert.assertEquals(true, !finalQuery.isEmpty());
		Assert.assertEquals(true, routePath.getPathList().isEmpty());
	}

	@Test
	public void getInboundRestrictionQueryForAutoUpdate() {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		GtnFrameworkRouteBean routePath = hierarchyService.getPathByTableNameAndHierarchyType("CONTRACT_MASTER",
				"COMPANY_MASTER", "CUSTOMER HIERARCHY");
		hierarchyService.createQuery(routePath, queryBean);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(queryBean);
		String finalQuery = queryBean.generateQuery();
		Assert.assertEquals(true, !finalQuery.isEmpty());
		Assert.assertEquals(true, routePath.getPathList().isEmpty());
	}

}