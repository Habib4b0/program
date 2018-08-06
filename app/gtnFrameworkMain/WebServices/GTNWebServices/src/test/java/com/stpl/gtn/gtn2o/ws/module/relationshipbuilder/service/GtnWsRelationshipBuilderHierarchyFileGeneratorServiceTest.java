/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Karthik.Raja
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsRelationshipBuilderHierarchyFileGeneratorServiceTest {

    @Autowired
    GtnWsRelationshipBuilderHierarchyFileGeneratorService instance;

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
        String result = instance.getQuery(sqlId);
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
        String result = instance.getQueryReplaced(input, queryName);
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
        String result = instance.getFinalQueryReplaced(input, queryName);
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
        if (result != null && !result.isEmpty()) {
            int hierarchyDefSId = (int) result.get(0)[0];
            int versionNo = (int) result.get(0)[1];
            List<Object[]> result1 = getHierarachylevelList(hierarchyDefSId, versionNo);

            List<HierarchyLevelDefinitionBean> expResult = instance.gettHierarchyLevelDefinitionListMain(result1);
            List<HierarchyLevelDefinitionBean> results = instance.getRBHierarchyLevelDefinitionBySid(hierarchyDefSId, versionNo);
            assertEquals(expResult.size(), results.size());
        }

    }

    private List<Object[]> getSampleHierarchy() throws GtnFrameworkGeneralException {
        String query = instance.getQuery("JUNIT_RB_SAMPLE_1");
        @SuppressWarnings("unchecked")
        List<Object[]> result = instance.executeQuery(query);
        return result;
    }

    private List<Object[]> getHierarachylevelList(int hierarchyDefSId, int versionNo) throws GtnFrameworkGeneralException {
        List<String> inputlist = new ArrayList<>(2);
        inputlist.add(String.valueOf(hierarchyDefSId));
        inputlist.add(String.valueOf(versionNo));
        @SuppressWarnings("unchecked")
        List<Object[]> result1 = instance.executeQuery(instance.getQueryReplaced(inputlist, "getRBHierarchyLevelDefinitionBySid"));
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
            List<HierarchyLevelDefinitionBean> results = instance.gettHierarchyLevelDefinitionListMain(result1);
            assertEquals(expResult, results.size());

        }
    }

    @Test
    public void testGetHierarchyBeanByLevelNo() throws GtnFrameworkGeneralException {
        System.out.println("getHierarchyBeanByLevelNo");
        int levelNo =1;
        List<Object[]> result = getSampleHierarchy();
        if (result != null && !result.isEmpty()) {
            int hierarchyDefSId = (int) result.get(0)[0];
            int versionNo = (int) result.get(0)[1];
            List<Object[]> result1 = getHierarachylevelList(hierarchyDefSId, versionNo);
            
            @SuppressWarnings("unchecked")
            List<HierarchyLevelDefinitionBean> hierarchyList = instance.gettHierarchyLevelDefinitionListMain(result1);
            
            HierarchyLevelDefinitionBean expResult=null;
            if (levelNo < hierarchyList.size() && hierarchyList.get(levelNo).getLevelNo() == levelNo) {
                expResult=hierarchyList.get(levelNo);
            }else{
              for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyList) {
			if (hierarchyLevelDefinitionBean.getLevelNo() == levelNo)
				expResult= hierarchyLevelDefinitionBean;
		}  
            }
            HierarchyLevelDefinitionBean res = instance.getHierarchyBeanByLevelNo(hierarchyList, levelNo);
            assertEquals(expResult, res);

        }
    }
}
