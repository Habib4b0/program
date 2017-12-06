package GtnFrameworkHierarchyRouteBuilder.GtnFrameworkHierarchyRouteBuilder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkHierarchyRouteBuilder-test.xml" })
// @Ignore
public class AppTest {

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

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

	// @Test
	public void creatQueryForMultiLevelHierarchy() {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("RS_CONTRACT");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(queryBean);
		String finalQuery = queryBean.generateQuery();

		Assert.assertEquals(true, !finalQuery.isEmpty());
	}

	// @Test
	public void getRoutePath() {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		GtnFrameworkRouteBean routePath = hierarchyService.getRoutePath(6, 7);

		String finalQuery = queryBean.generateQuery();

		Assert.assertEquals(true, !finalQuery.isEmpty());
		Assert.assertEquals(true, routePath.getPathList().isEmpty());
	}

	@Test
	public void getPathByTableNameAndHierarchyType() {
		try {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			GtnFrameworkRouteBean routePath = hierarchyService.getPathByTableNameAndHierarchyType("COMPANY_MASTER",
					"ITEM_MASTER", "PRODUCT HIERARCHY");
		System.out.println(routePath);
		hierarchyService.createQuery(routePath, queryBean);
		System.out.println(routePath.getPathList());
		// final GtnFrameworkSingleColumnRelationBean destinationkeyBean =
		// gtnFrameworkEntityMasterBean
		// .getKeyRelationBeanUsingTableIdAndColumnName("ITEM_MASTER",
		// "ITEM_CATEGORY");
		// hierarchyService.getSelectColumnsForRelationShipBuilder(destinationkeyBean,
		// queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		// Assert.assertEquals(true, !finalQuery.isEmpty());
		// Assert.assertEquals(true, routePath.getPathList().isEmpty());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
