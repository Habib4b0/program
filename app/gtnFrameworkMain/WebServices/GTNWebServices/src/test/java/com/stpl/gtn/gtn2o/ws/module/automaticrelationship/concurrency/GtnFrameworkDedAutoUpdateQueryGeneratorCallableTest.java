package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
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
 * The class <code>GtnFrameworkDedAutoUpdateQueryGeneratorCallableTest</code>
 * contains tests for the class
 * <code>{@link GtnFrameworkDedAutoUpdateQueryGeneratorCallable}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkDedAutoUpdateQueryGeneratorCallableTest {

    @Autowired
    GtnFrameworkDedAutoUpdateQueryGeneratorCallable instance;

    /**
     * Run the GtnFrameworkDedAutoUpdateQueryGeneratorCallable() constructor
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnFrameworkDedAutoUpdateQueryGeneratorCallable_1()
            throws Exception {

        GtnFrameworkDedAutoUpdateQueryGeneratorCallable result = new GtnFrameworkDedAutoUpdateQueryGeneratorCallable();

        assertNotNull(result);
    }

    /**
     * Run the String call() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = java.lang.NullPointerException.class)
    public void testCall_1()
            throws Exception {
        GtnFrameworkDedAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkDedAutoUpdateQueryGeneratorCallable();
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());

        String result = fixture.call();

        assertNotNull(result);
    }

    /**
     * Run the void setCustomertUpdatedVersionNo(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetCustomertUpdatedVersionNo_1()
            throws Exception {
        GtnFrameworkDedAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkDedAutoUpdateQueryGeneratorCallable();
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        int customertUpdatedVersionNo = 1;

        fixture.setCustomertUpdatedVersionNo(customertUpdatedVersionNo);

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
        GtnFrameworkDedAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkDedAutoUpdateQueryGeneratorCallable();
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
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
        GtnFrameworkDedAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkDedAutoUpdateQueryGeneratorCallable();
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
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
        GtnFrameworkDedAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkDedAutoUpdateQueryGeneratorCallable();
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
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
        instance.setCustomertUpdatedVersionNo(1);
        instance.setIndex(0);
        instance.setRelationBean(new GtnWsRelationshipBuilderBean());
        instance.setItemMastersidList(Arrays.asList(1,2,3));
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        hierarchyLevelDefinitionList.add(new HierarchyLevelDefinitionBean());

        instance.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

        instance.setRelationBean(relationBean);

        ExecutorService service = Executors.newFixedThreadPool(1);
        System.out.println("" + service.submit(instance));

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
        new org.junit.runner.JUnitCore().run(GtnFrameworkDedAutoUpdateQueryGeneratorCallableTest.class);
    }
}
