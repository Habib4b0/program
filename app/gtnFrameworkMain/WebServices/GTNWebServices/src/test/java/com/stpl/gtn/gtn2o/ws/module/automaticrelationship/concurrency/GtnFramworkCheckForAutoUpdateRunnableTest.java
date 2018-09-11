package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFramworkCheckForAutoUpdateRunnableTest</code> contains
 * tests for the class
 * <code>{@link GtnFramworkCheckForAutoUpdateRunnable}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFramworkCheckForAutoUpdateRunnableTest {

    @InjectMocks
    @Autowired
    GtnFramworkCheckForAutoUpdateRunnable fixture;

    @Mock
    @Autowired
    private GtnFrameworkQueryGeneratorService queryGeneratorService;

    /**
     * Run the GtnFramworkCheckForAutoUpdateRunnable() constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnFramworkCheckForAutoUpdateRunnable_1()
            throws Exception {

        GtnFramworkCheckForAutoUpdateRunnable result = new GtnFramworkCheckForAutoUpdateRunnable();

        assertNotNull(result);
        assertEquals(0, result.getIndex());
        assertEquals("", result.call());
        assertEquals(null, result.getGtnFrameworkEntityMasterBean());
        assertEquals(null, result.getGtnSqlQueryEngine());
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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

        String result = fixture.call();

        assertEquals("", result);
    }

    /**
     * Run the GtnFrameworkEntityMasterBean getGtnFrameworkEntityMasterBean()
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetGtnFrameworkEntityMasterBean_1()
            throws Exception {

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

        GtnFrameworkEntityMasterBean result = fixture.getGtnFrameworkEntityMasterBean();

        assertNotNull(result);
        assertEquals(null, result.getGtnSqlQueryEngine());
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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

        GtnFrameworkHierarchyService result = fixture.getGtnHierarchyServiceBuilder();

        assertEquals(null, result);
    }

    /**
     * Run the GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetGtnSqlQueryEngine_1()
            throws Exception {

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

        GtnFrameworkSqlQueryEngine result = fixture.getGtnSqlQueryEngine();

        assertNotNull(result);
        assertEquals(null, result.getSessionFactory());
        assertEquals(null, result.getQueryLogger());
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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

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
        fixture.setIndex(0);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
        bean.setLevelNo(2);
        hierarchyLevelDefinitionList.add(bean);
        GtnFrameworkQueryGeneratorBean qu = new GtnFrameworkQueryGeneratorBean();
        doReturn(qu).when(queryGeneratorService).getQuerybySituationNameAndLevel(hierarchyLevelDefinitionList.get(0),  "CHECK_AUTO_UPDATE", hierarchyLevelDefinitionList);
        fixture.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);

        fixture.call();
//        ExecutorService service = Executors.newFixedThreadPool(1);
//        System.out.println("" + service.submit(fixture));
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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());

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
     * Run the void
     * setGtnFrameworkEntityMasterBean(GtnFrameworkEntityMasterBean) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetGtnFrameworkEntityMasterBean_1()
            throws Exception {

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean = new GtnFrameworkEntityMasterBean();

        fixture.setGtnFrameworkEntityMasterBean(gtnFrameworkEntityMasterBean);

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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        GtnFrameworkHierarchyService gtnHierarchyServiceBuilder = null;

        fixture.setGtnHierarchyServiceBuilder(gtnHierarchyServiceBuilder);

    }

    /**
     * Run the void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetGtnSqlQueryEngine_1()
            throws Exception {

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        GtnFrameworkSqlQueryEngine gtnSqlQueryEngine = new GtnFrameworkSqlQueryEngine();

        fixture.setGtnSqlQueryEngine(gtnSqlQueryEngine);

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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        int index = 1;

        fixture.setIndex(index);

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

        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setIndex(1);
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        fixture.setGtnHierarchyServiceBuilder((GtnFrameworkHierarchyService) null);
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
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
        MockitoAnnotations.initMocks(this);
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
        new org.junit.runner.JUnitCore().run(GtnFramworkCheckForAutoUpdateRunnableTest.class);
    }
}
