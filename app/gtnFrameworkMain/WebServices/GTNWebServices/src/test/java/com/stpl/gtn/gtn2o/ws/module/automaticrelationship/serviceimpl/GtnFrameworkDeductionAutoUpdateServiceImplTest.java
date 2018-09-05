package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFrameworkDeductionAutoUpdateServiceImplTest</code> contains tests for the class <code>{@link GtnFrameworkDeductionAutoUpdateServiceImpl}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkDeductionAutoUpdateServiceImplTest {
	/**
	 * Run the GtnFrameworkDeductionAutoUpdateServiceImpl() constructor test.
	 *
	 * @throws Exception
	 *
	 *
	 */
      @Autowired
      GtnFrameworkDeductionAutoUpdateServiceImpl fixture ;
	@Test
	public void testGtnFrameworkDeductionAutoUpdateServiceImpl_1()
		throws Exception {

		GtnFrameworkDeductionAutoUpdateServiceImpl result = new GtnFrameworkDeductionAutoUpdateServiceImpl();

		
		assertNotNull(result);
		assertEquals(null, result.getService());
	}

	/**
	 * Run the boolean checkAutomaticRelation(int) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckAutomaticRelation_1()
		throws Exception {
		
		int relationshipBuilderSid = 1;

		boolean result = fixture.checkAutomaticRelation(relationshipBuilderSid);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_1()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_2()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_3()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_4()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_5()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_6()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_7()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_8()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testCheckForAutoUpdate_9()
		throws Exception {
		
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

		boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

		
		assertEquals(true, result);
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_1()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_2()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_3()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_4()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_5()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_6()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_7()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_8()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_9()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_10()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_11()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_12()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_13()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testDoAutomaticUpdate_14()
		throws Exception {
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

		
	}

	/**
	 * Run the List<Integer> getItemMasterSidForProductRelation(RelationshipBuilder) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetItemMasterSidForProductRelation_1()
		throws Exception {
		
	    RelationshipBuilder productrelationshipBuilder = new RelationshipBuilder();
            
            List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
            HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
            bean.setLevelValueReference(GtnFrameworkWebserviceConstant.USER_DEFINED);
            bean.setLevelNo(0);
            hierarchyLevelDefinitionList.add(bean);
            HierarchyDefinition hd=new HierarchyDefinition();
            productrelationshipBuilder.setHierarchyDefinition(hd);
            GtnFrameworkDeductionAutoUpdateServiceImpl fix = Mockito.spy(fixture);
            Mockito.doReturn(hierarchyLevelDefinitionList).when(fix).getRBHierarchyLevelDefinitionBySid(hd.getHierarchyDefinitionSid(), hd.getVersionNo(),
                    productrelationshipBuilder.getRelationshipBuilderSid());
            List<Integer> result = fix.getItemMasterSidForProductRelation(productrelationshipBuilder);

            assertNotNull(result);
            assertEquals(0, result.size());
	}

	/**
	 * Run the List<Integer> getItemMasterSidForProductRelation(RelationshipBuilder) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetItemMasterSidForProductRelation_2()
		throws Exception {
		
		RelationshipBuilder productrelationshipBuilder = new RelationshipBuilder();

		List<Integer> result = fixture.getItemMasterSidForProductRelation(productrelationshipBuilder);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Integer> getItemMasterSidForProductRelation(RelationshipBuilder) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetItemMasterSidForProductRelation_3()
		throws Exception {
		
		RelationshipBuilder productrelationshipBuilder = new RelationshipBuilder();

		List<Integer> result = fixture.getItemMasterSidForProductRelation(productrelationshipBuilder);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

//	/**
//	 * Run the List<HierarchyLevelDefinitionBean> getRBHierarchyLevelDefinitionBySid(int,int,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	 *
//	 */
	@Test
	public void testGetRBHierarchyLevelDefinitionBySid_2()
		throws Exception {
		
		int hierarchyDefSid = 1;
		int versionNo = 1;
		int prodRelationShipBuilderSid = 1;

		List<HierarchyLevelDefinitionBean> result = fixture.getRBHierarchyLevelDefinitionBySid(hierarchyDefSid, versionNo, prodRelationShipBuilderSid);

		
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl.GtnFrameworkDeductionAutoUpdateServiceImpl.getRBHierarchyLevelDefinitionBySid(GtnFrameworkDeductionAutoUpdateServiceImpl.java:191)
		assertNotNull(result);
	}

	/**
	 * Run the GtnFrameworkAutomaticRelationUpdateService getService() method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetService_1()
		throws Exception {
		

		GtnFrameworkAutomaticRelationUpdateService result = fixture.getService();

		
		assertNotNull( result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 *
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 *
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 *
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkDeductionAutoUpdateServiceImplTest.class);
	}
}