/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.GtnWsAllHierarchyQueryGenerator;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.boot.internal.InFlightMetadataCollectorImpl;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.hibernate.boot.internal.SessionFactoryOptionsImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.type.TypeResolver;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsRelationshipBuilderHierarchyFileGeneratorServiceTest {

    @Autowired
    GtnWsRelationshipBuilderHierarchyFileGeneratorService rbFileService;

    public GtnWsRelationshipBuilderHierarchyFileGeneratorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getQuery method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetQuery() {
        System.out.println("getQuery");
        String sqlId = "JUNIT_SAMPLE";

        String expResult = "UPDATE IMTD_DEDUCTION_DETAILS SET CHECK_RECORD = ? WHERE USERS_SID = ?  AND SESSION_ID = ?";
        String result = rbFileService.getQuery(sqlId);
        assertEquals(expResult, result.trim());
    }

    /**
     * Test of getQueryReplaced method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetQueryReplaced() {
        System.out.println("getQueryReplaced");
        List<String> input = Arrays.asList("1", "2", "3");
        String queryName = "JUNIT_SAMPLE";

        String expResult = "UPDATE IMTD_DEDUCTION_DETAILS SET CHECK_RECORD = 1 WHERE USERS_SID = 2  AND SESSION_ID = 3";
        String result = rbFileService.getQueryReplaced(input, queryName);
        assertEquals(expResult, result.trim());

    }

    /**
     * Test of getFinalQueryReplaced method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetFinalQueryReplaced() {
        System.out.println("getFinalQueryReplaced");
        List<String> input = Arrays.asList("1", "2", "3");
        String queryName = "UPDATE IMTD_DEDUCTION_DETAILS SET CHECK_RECORD = ? WHERE USERS_SID = ?  AND SESSION_ID = ?";

        String expResult = "UPDATE IMTD_DEDUCTION_DETAILS SET CHECK_RECORD = 1 WHERE USERS_SID = 2  AND SESSION_ID = 3";
        String result = rbFileService.getFinalQueryReplaced(input, queryName);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateQueryInHierarchy method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testUpdateQueryInHierarchy() throws GtnFrameworkGeneralException {
        System.out.println("updateQueryInHierarchy");
        List<Object[]> result = getSampleHierarchy();
        List<HierarchyLevelDefinitionBean> beans = new ArrayList<>();
        HierarchyLevelDefinitionBean hd = new HierarchyLevelDefinitionBean();
        beans.add(hd);
        if (result != null && !result.isEmpty()) {
            int hierarchyDefSId = (int) result.get(0)[0];
            int versionNo = (int) result.get(0)[1];
            GtnWsRelationshipBuilderHierarchyFileGeneratorService instance = Mockito.spy(rbFileService);
            when(instance.getRBHierarchyLevelDefinitionBySid(Mockito.anyInt(), Mockito.anyInt()))
                    .thenReturn(beans);
            rbFileService.updateQueryInHierarchy(hierarchyDefSId, versionNo);
        }

    }

    private List<Object[]> getSampleHierarchy() throws GtnFrameworkGeneralException {
        String query = rbFileService.getQuery("JUNIT_RB_SAMPLE_1");
        @SuppressWarnings("unchecked")
        List<Object[]> result = rbFileService.executeQuery(query);
        return result;
    }

    private List<Object[]> getHierarachylevelList(int hierarchyDefSId, int versionNo) throws GtnFrameworkGeneralException {
        List<String> inputlist = new ArrayList<>(2);
        inputlist.add(String.valueOf(hierarchyDefSId));
        inputlist.add(String.valueOf(versionNo));
        @SuppressWarnings("unchecked")
        List<Object[]> result1 = rbFileService.executeQuery(rbFileService.getQueryReplaced(inputlist, "getRBHierarchyLevelDefinitionBySid"));
        return result1;
    }

    /**
     * Test of gettHierarchyLevelDefinitionListMain method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGettHierarchyLevelDefinitionListMain() throws GtnFrameworkGeneralException {
        System.out.println("gettHierarchyLevelDefinitionListMain");
        List<Object[]> result = getSampleHierarchy();
        if (result != null && !result.isEmpty()) {
            int hierarchyDefSId = (int) result.get(0)[0];
            int versionNo = (int) result.get(0)[1];
            List<Object[]> result1 = getHierarachylevelList(hierarchyDefSId, versionNo);
            int expResult = result1.size();
            @SuppressWarnings("unchecked")
            List<HierarchyLevelDefinitionBean> results = rbFileService.gettHierarchyLevelDefinitionListMain(result1);
            assertEquals(expResult, results.size());

        }
    }

    @Test
    public void testGetHierarchyBeanByLevelNo() throws GtnFrameworkGeneralException {
        System.out.println("getHierarchyBeanByLevelNo");
        int levelNo = 1;
        List<Object[]> result = getSampleHierarchy();
        if (result != null && !result.isEmpty()) {
            int hierarchyDefSId = (int) result.get(0)[0];
            int versionNo = (int) result.get(0)[1];
            List<Object[]> result1 = getHierarachylevelList(hierarchyDefSId, versionNo);

            @SuppressWarnings("unchecked")
            List<HierarchyLevelDefinitionBean> hierarchyList = rbFileService.gettHierarchyLevelDefinitionListMain(result1);

            HierarchyLevelDefinitionBean expResult = null;
            if (levelNo < hierarchyList.size() && hierarchyList.get(levelNo).getLevelNo() == levelNo) {
                expResult = hierarchyList.get(levelNo);
            } else {
                for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyList) {
                    if (hierarchyLevelDefinitionBean.getLevelNo() == levelNo) {
                        expResult = hierarchyLevelDefinitionBean;
                    }
                }
            }
            HierarchyLevelDefinitionBean res = rbFileService.getHierarchyBeanByLevelNo(hierarchyList, levelNo);
            assertEquals(expResult, res);

        }
    }

    /**
     * Test of executeQuery method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testExecuteQuery_String() throws Exception {
        System.out.println("executeQuery");
        String sqlQuery = "select 1 as result";
        int expResult = 1;
        List result = rbFileService.executeQuery(sqlQuery);
        assertEquals(expResult, Integer.parseInt(result.get(0).toString()));
    }

    /**
     * Test of executeQuery method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testExecuteQuery_3args() throws Exception {
        System.out.println("executeQuery");
        String sqlQuery = "SELECT 1 FROM (VALUES ('Hello world')) t1 (col1) WHERE 1 = ?";
        Object[] params = {1};
        GtnFrameworkDataType[] type = {GtnFrameworkDataType.INTEGER};
        int expResult = 1;
        List result = rbFileService.executeQuery(sqlQuery, params, type);
        assertEquals(expResult, Integer.parseInt(result.get(0).toString()));
    }

    /**
     * Test of getSessionFactory method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        GtnWsRelationshipBuilderHierarchyFileGeneratorService instance = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();
        instance.setSessionFactory(sessionFactory);
        SessionFactory result = instance.getSessionFactory();
        assertEquals(sessionFactory, result);
    }

    /**
     * Test of setGtnHierarchyServiceBuilder method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testSetGtnHierarchyServiceBuilder() {
        System.out.println("setGtnHierarchyServiceBuilder");
        GtnFrameworkHierarchyService gtnHierarchyServiceBuilder = Mockito.mock(GtnFrameworkHierarchyService.class);
        GtnWsRelationshipBuilderHierarchyFileGeneratorService instance = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();
        instance.setGtnHierarchyServiceBuilder(gtnHierarchyServiceBuilder);
        GtnFrameworkHierarchyService expResult = gtnHierarchyServiceBuilder;
        GtnFrameworkHierarchyService result = instance.getGtnHierarchyServiceBuilder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGtnSqlQueryEngine method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetGtnSqlQueryEngine() {
        System.out.println("getGtnSqlQueryEngine");
        GtnFrameworkSqlQueryEngine gtnSqlQueryEngine = new GtnFrameworkSqlQueryEngine();
        GtnWsRelationshipBuilderHierarchyFileGeneratorService instance = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();
        instance.setGtnSqlQueryEngine(gtnSqlQueryEngine);
        GtnFrameworkSqlQueryEngine expResult = gtnSqlQueryEngine;
        GtnFrameworkSqlQueryEngine result = instance.getGtnSqlQueryEngine();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGtnWsSqlService method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetGtnWsSqlService() {
        System.out.println("getGtnWsSqlService");
        GtnWsSqlService gtnWsSqlService = Mockito.mock(GtnWsSqlService.class);
        GtnWsRelationshipBuilderHierarchyFileGeneratorService instance = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();
        instance.setGtnWsSqlService(gtnWsSqlService);
        GtnWsSqlService expResult = gtnWsSqlService;
        GtnWsSqlService result = instance.getGtnWsSqlService();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQueryGeneratorService method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetQueryGeneratorService() {
        System.out.println("getQueryGeneratorService");
        GtnWsAllHierarchyQueryGenerator queryGeneratorService = new GtnWsAllHierarchyQueryGenerator();
        GtnWsRelationshipBuilderHierarchyFileGeneratorService instance = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();
        instance.setQueryGeneratorService(queryGeneratorService);
        GtnWsAllHierarchyQueryGenerator expResult = queryGeneratorService;
        GtnWsAllHierarchyQueryGenerator result = instance.getQueryGeneratorService();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRBHierarchyLevelDefinitionBySid method, of class
     * GtnWsRelationshipBuilderHierarchyFileGeneratorService.
     */
    @Test
    public void testGetRBHierarchyLevelDefinitionBySid() throws Exception {
        System.out.println("getRBHierarchyLevelDefinitionBySid");
        int hierarchyDefSid = 0;
        int versionNo = 0;
        GtnFrameworkSqlQueryEngine gtnSqlQueryEngine = mock(GtnFrameworkSqlQueryEngine.class);
        when(gtnSqlQueryEngine.executeSelectQuery(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(GtnFrameworkDataType[].class)))
                .thenReturn(Collections.EMPTY_LIST);

        List<HierarchyLevelDefinitionBean> result = rbFileService.getRBHierarchyLevelDefinitionBySid(hierarchyDefSid, versionNo);
        assertEquals(0, result.size());
    }
           @Test
	public void testGtnWsRelationshipBuilderHierarchyFileGeneratorService_1()
		throws Exception {

		GtnWsRelationshipBuilderHierarchyFileGeneratorService result = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSessionFactory());
		assertEquals(null, result.getGtnHierarchyServiceBuilder());
		assertEquals(null, result.getQueryGeneratorService());
		assertEquals(null, result.getGtnSqlQueryEngine());
		assertEquals(null, result.getGtnWsSqlService());
	}

	
}
