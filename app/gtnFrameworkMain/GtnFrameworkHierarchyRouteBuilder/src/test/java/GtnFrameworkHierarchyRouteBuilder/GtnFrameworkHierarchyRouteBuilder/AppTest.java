package GtnFrameworkHierarchyRouteBuilder.GtnFrameworkHierarchyRouteBuilder;

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
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;


/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkHierarchyRouteBuilder-test.xml" })
@Ignore
public class AppTest {

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest() {
		/*
		* 
		*/
	}

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
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		GtnFrameworkRouteBean routePath = hierarchyService.getRoutePath(6, 7);

		String finalQuery = queryBean.generateQuery();

		Assert.assertEquals(true, !finalQuery.isEmpty());
		Assert.assertEquals(true, routePath.getPathList().isEmpty());
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

}