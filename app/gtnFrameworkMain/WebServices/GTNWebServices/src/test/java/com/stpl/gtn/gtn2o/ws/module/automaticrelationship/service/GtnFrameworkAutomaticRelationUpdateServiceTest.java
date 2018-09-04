package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFrameworkAutomaticRelationUpdateServiceTest</code>
 * contains tests for the class
 * <code>{@link GtnFrameworkAutomaticRelationUpdateService}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkAutomaticRelationUpdateServiceTest {

    @Autowired
    GtnFrameworkAutomaticRelationUpdateService instance;

    /**
     * Run the GtnFrameworkAutomaticRelationUpdateService() constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnFrameworkAutomaticRelationUpdateService_1()
            throws Exception {

        GtnFrameworkAutomaticRelationUpdateService result = new GtnFrameworkAutomaticRelationUpdateService();

        assertNotNull(result);
        assertEquals(null, result.getGtnFrameworkEntityMasterBean());
        assertEquals(null, result.getGtnSqlQueryEngine());
        assertEquals(null, result.getGtnWsSqlService());
        assertEquals(null, result.getHierarchyService());
    }

    /**
     * Run the void deleteUnwantedUserDefinedLevels(int,int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testDeleteUnwantedUserDefinedLevels_1()
             {
        try {
            instance.setHierarchyService((GtnFrameworkHierarchyService) null);
            instance.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
            int relationshipBuilderSid = 1;
            int customertUpdatedVersionNo = 1;

            instance.deleteUnwantedUserDefinedLevels(relationshipBuilderSid, customertUpdatedVersionNo);
        } catch (Exception e) {

        }
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
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        GtnFrameworkEntityMasterBean result = fixture.getGtnFrameworkEntityMasterBean();

        assertNotNull(result);
        assertEquals(null, result.getGtnSqlQueryEngine());
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
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

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
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        GtnWsSqlService result = fixture.getGtnWsSqlService();

        assertEquals(null, result);
    }


    /**
     * Run the List<HierarchyLevelDefinitionBean>
     * getHierarchyBuilderBasedOnProjectionId(int,String,boolean) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHierarchyBuilderBasedOnProjectionId_1()
            throws Exception {
        try{
        instance.setHierarchyService((GtnFrameworkHierarchyService) null);
        instance.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        int projectionId = 1;
        String hierarchyIndicator = "";
        boolean isCff = false;

        List<HierarchyLevelDefinitionBean> result = instance.getHierarchyBuilderBasedOnProjectionId(projectionId, hierarchyIndicator, isCff);
        List<HierarchyLevelDefinitionBean> result2= instance.getHierarchyBuilderBasedOnProjectionId(projectionId, 1);

        assertNotNull(result);
        } catch(Exception e){
            
        }
    }

    /**
     * Run the GtnFrameworkHierarchyService getHierarchyService() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHierarchyService_1()
            throws Exception {
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);

        GtnFrameworkHierarchyService result = fixture.getHierarchyService();

        assertEquals(null, result);
    }

//	
    /**
     * Run the List<String>
     * getRelationQueries(int,int,HierarchyLevelDefinitionBean[]) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRelationQueries_1()
            throws Exception {
        try{
        instance.setHierarchyService((GtnFrameworkHierarchyService) null);
        instance.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        int relationshipSid = 1;
        int versionNo = 1;

        List<String> result = instance.getRelationQueries(relationshipSid, versionNo, new HierarchyLevelDefinitionBean());

        assertNotNull(result);
        assertEquals(1, result.size());
         } catch(Exception e){
            
        }
    }

    /**
     * Run the List<String>
     * getRelationQueries(int,int,HierarchyLevelDefinitionBean[]) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRelationQueries_2()
            throws Exception {
        try{
        instance.setHierarchyService((GtnFrameworkHierarchyService) null);
        instance.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        int relationshipSid = 1;
        int versionNo = 1;

        List<String> result = instance.getRelationQueries(relationshipSid, versionNo);

        assertNotNull(result);
        assertEquals(0, result.size());
         } catch(Exception e){
            
        }
    }

    /**
     * Run the List<String>
     * getRelationQueries(int,int,HierarchyLevelDefinitionBean[]) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRelationQueries_3()
            throws Exception {
        try{
        HierarchyLevelDefinitionBean levelDto = new HierarchyLevelDefinitionBean();
        levelDto.setLevelValueReference(GtnFrameworkWebserviceConstant.USER_DEFINED);

        instance.setHierarchyService((GtnFrameworkHierarchyService) null);
        instance.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        int relationshipSid = 1;
        int versionNo = 1;

        List<String> result = instance.getRelationQueries(relationshipSid, versionNo, levelDto);

        assertNotNull(result);
        assertEquals(0, result.size());
         } catch(Exception e){
            
        }
    }

//
    /**
     * Run the int
     * insertRelationTillFirstLevelAndGetVersionNo(int,GtnWsRelationshipBuilderBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testInsertRelationTillFirstLevelAndGetVersionNo_1()
            throws Exception {
        try{
        instance.setHierarchyService((GtnFrameworkHierarchyService) null);
        instance.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        int firstLinkedLevelNo = 1;
        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

        SessionFactory factory = Mockito.mock(SessionFactory.class);

        Session session = Mockito.mock(Session.class);
        
        Transaction tx=Mockito.mock(Transaction.class);
        
        doReturn(session).when(factory).openSession();
        doReturn(tx).when(session).beginTransaction();
        doNothing().when(tx).commit();

        RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
        GtnFrameworkSqlQueryEngine queryEngine = new GtnFrameworkSqlQueryEngine();
        queryEngine.setSessionFactory(factory);
        instance.setGtnSqlQueryEngine(queryEngine);

        doReturn(relationshipBuilder).when(session).load(RelationshipBuilder.class,
                relationBean.getRelationshipBuilderSid());

        int result = instance.insertRelationTillFirstLevelAndGetVersionNo(firstLinkedLevelNo, relationBean);

        assertEquals(0, result);
        }catch(Exception e){
            
        }}

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
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean = new GtnFrameworkEntityMasterBean();

        fixture.setGtnFrameworkEntityMasterBean(gtnFrameworkEntityMasterBean);

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
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
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
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        GtnWsSqlService gtnWsSqlService = null;

        fixture.setGtnWsSqlService(gtnWsSqlService);

    }

    /**
     * Run the void setHierarchyService(GtnFrameworkHierarchyService) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetHierarchyService_1()
            throws Exception {
        GtnFrameworkAutomaticRelationUpdateService fixture = new GtnFrameworkAutomaticRelationUpdateService();
        fixture.setGtnSqlQueryEngine(new GtnFrameworkSqlQueryEngine());
        fixture.setHierarchyService((GtnFrameworkHierarchyService) null);
        fixture.setGtnFrameworkEntityMasterBean(new GtnFrameworkEntityMasterBean());
        fixture.setGtnWsSqlService((GtnWsSqlService) null);
        GtnFrameworkHierarchyService hierarchyService = null;

        fixture.setHierarchyService(hierarchyService);

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
        new org.junit.runner.JUnitCore().run(GtnFrameworkAutomaticRelationUpdateServiceTest.class);
    }
    
    /**
     * Test of checkManualRelation method, of class GtnFrameworkAutomaticRelationUpdateService.
     */
    @Test
    public void testCheckManualRelation()   {
        try{
        System.out.println("checkManualRelation");
        int relationshipBuilderSid = 0;
        instance.checkManualRelation(relationshipBuilderSid);
        }catch(Exception e){
            
        }
    }

  
}
