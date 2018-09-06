package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFrameworkAutomaticRunnableTest</code> contains tests for the class <code>{@link GtnFrameworkAutomaticRunnable}</code>.
 *
 * 
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkAutomaticRunnableTest {
    
     @Autowired
     GtnFrameworkAutomaticRunnable instance;
	/**
	 * Run the GtnFrameworkAutomaticRunnable() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnFrameworkAutomaticRunnable_1()
		throws Exception {

		GtnFrameworkAutomaticRunnable result = new GtnFrameworkAutomaticRunnable();

		
		assertNotNull(result);
		assertEquals(null, result.getUserId());
		assertEquals(null, result.getRelationBean());
	}
	/**
	 * Run the boolean checkAndUpdateAutomaticRelationship(GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testCheckAndUpdateAutomaticRelationship_6()
		throws Exception {
		instance.setUserId("");
		instance.setRelationBean(new GtnWsRelationshipBuilderBean());
		GtnWsRelationshipBuilderBean relationBean = null;

		boolean result = instance.checkAndUpdateAutomaticRelationship(relationBean);

		assertEquals(false, result);
	}

	/**
	 * Run the GtnWsRelationshipBuilderBean getRelationBean() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetRelationBean_1()
		throws Exception {
		GtnFrameworkAutomaticRunnable fixture = new GtnFrameworkAutomaticRunnable();
		fixture.setUserId("");
		fixture.setRelationBean(new GtnWsRelationshipBuilderBean());

		GtnWsRelationshipBuilderBean result = fixture.getRelationBean();

		
		assertNotNull(result);
		assertEquals(null, result.getStartDate());
		assertEquals(null, result.getModifiedDate());
		assertEquals(0, result.getRelationshipBuilderSid());
		assertEquals(null, result.getRelationshipDescription());
		assertEquals(null, result.getRelationshipName());
		assertEquals(null, result.getDeductionRelation());
		assertEquals(null, result.getHierarchycategory());
		assertEquals(0, result.getHierarchyDefinitionSid());
		assertEquals(null, result.getHierarchyVersion());
		assertEquals(null, result.getCreatedDate());
		assertEquals(0, result.getModifiedBy());
		assertEquals(null, result.getBuildType());
		assertEquals(0, result.getVersionNo());
		assertEquals(0, result.getCreatedBy());
                
                
                Thread t1=new Thread(instance);
                t1.start();
                t1.interrupt();
	}

	/**
	 * Run the String getUserId() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetUserId_1()
		throws Exception {
		GtnFrameworkAutomaticRunnable fixture = new GtnFrameworkAutomaticRunnable();
		fixture.setUserId("");
		fixture.setRelationBean(new GtnWsRelationshipBuilderBean());

		String result = fixture.getUserId();

		
		assertEquals("", result);
	}


	/**
	 * Run the void setRelationBean(GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testSetRelationBean_1()
		throws Exception {
		GtnFrameworkAutomaticRunnable fixture = new GtnFrameworkAutomaticRunnable();
		fixture.setUserId("");
		fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

		fixture.setRelationBean(relationBean);

		
	}

	/**
	 * Run the void setUserId(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testSetUserId_1()
		throws Exception {
		GtnFrameworkAutomaticRunnable fixture = new GtnFrameworkAutomaticRunnable();
		fixture.setUserId("");
		fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
		String userId = "";

		fixture.setUserId(userId);

		
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
		
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * 
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkAutomaticRunnableTest.class);
	}
}