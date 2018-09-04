package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFramworkDeductionCheckForAutoUpdateRunnableTest</code>
 * contains tests for the class
 * <code>{@link GtnFramworkDeductionCheckForAutoUpdateRunnable}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFramworkDeductionCheckForAutoUpdateRunnableTest {

    @Autowired
    GtnFramworkDeductionCheckForAutoUpdateRunnable instance;

    /**
     * Run the GtnFramworkDeductionCheckForAutoUpdateRunnable() constructor
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnFramworkDeductionCheckForAutoUpdateRunnable_1()
            throws Exception {

        GtnFramworkDeductionCheckForAutoUpdateRunnable result = new GtnFramworkDeductionCheckForAutoUpdateRunnable();

        assertNotNull(result);
        assertEquals(0, result.getIndex());
        assertEquals("", result.call());
        assertEquals(null, result.getGtnWsSqlService());
        assertEquals(null, result.getRelationBean());
        assertEquals(null, result.getGtnHierarchyServiceBuilder());
    }

    /**
     * Run the String call() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCall_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        String result = fixture.call();

        assertEquals("", result);
    }

    /**
     * Run the String call() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCall_2()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        String result = fixture.call();

        assertEquals("", result);
    }

    /**
     * Run the GtnFrameworkHierarchyService getGtnHierarchyServiceBuilder()
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetGtnHierarchyServiceBuilder_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        GtnFrameworkHierarchyService result = fixture.getGtnHierarchyServiceBuilder();

        assertEquals(null, result);
    }

    /**
     * Run the GtnWsSqlService getGtnWsSqlService() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetGtnWsSqlService_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        GtnWsSqlService result = fixture.getGtnWsSqlService();

        assertEquals(null, result);
    }

    /**
     * Run the List<HierarchyLevelDefinitionBean>
     * getHierarchyLevelDefinitionList() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHierarchyLevelDefinitionList_1()
            throws Exception {
        instance.setRelationBean(new GtnWsRelationshipBuilderBean());
        instance.setIndex(0);
        instance.setItemMastersidList(Arrays.asList(1,2,3));
        instance.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        hierarchyLevelDefinitionList.add(new HierarchyLevelDefinitionBean());

        instance.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
        instance.getRelationBean().setRelationshipBuilderSid(-1);
        ExecutorService service = Executors.newFixedThreadPool(1);
        System.out.println("" + service.submit(instance));

    }

    /**
     * Run the int getIndex() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetIndex_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        int result = fixture.getIndex();

        assertEquals(1, result);
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
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

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
    }

    /**
     * Run the void setGtnHierarchyServiceBuilder(GtnFrameworkHierarchyService)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetGtnHierarchyServiceBuilder_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        GtnFrameworkHierarchyService gtnHierarchyServiceBuilder = null;

        fixture.setGtnHierarchyServiceBuilder(gtnHierarchyServiceBuilder);

    }

    /**
     * Run the void setGtnWsSqlService(GtnWsSqlService) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetGtnWsSqlService_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        GtnWsSqlService gtnWsSqlService = null;

        fixture.setGtnWsSqlService(gtnWsSqlService);

    }

    /**
     * Run the void
     * setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean>)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetHierarchyLevelDefinitionList_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

        fixture.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);

    }

    /**
     * Run the void setIndex(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetIndex_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        int index = 1;

        fixture.setIndex(index);

    }

    /**
     * Run the void setItemMastersidList(List<Integer>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetItemMastersidList_1()
            throws Exception {
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        List<Integer> itemMastersidList = new LinkedList();

        fixture.setItemMastersidList(itemMastersidList);

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
        GtnFramworkDeductionCheckForAutoUpdateRunnable fixture = new GtnFramworkDeductionCheckForAutoUpdateRunnable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setIndex(1);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

        fixture.setRelationBean(relationBean);

    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
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
     * @throws Exception if the clean-up fails for some reason
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
        new org.junit.runner.JUnitCore().run(GtnFramworkDeductionCheckForAutoUpdateRunnableTest.class);
    }
}
