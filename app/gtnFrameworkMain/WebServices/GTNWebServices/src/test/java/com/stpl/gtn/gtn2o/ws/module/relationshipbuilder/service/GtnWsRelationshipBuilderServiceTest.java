/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelValuesBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelsBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsRelationshipBuilderServiceTest {
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    @Autowired
    private GtnWsRelationshipBuilderService instance;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    public GtnWsRelationshipBuilderServiceTest() {
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
     * Test of executeQuery method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testExecuteQuery_String() throws Exception {
        System.out.println("executeQuery");
        String sqlQuery = " SELECT COUNT(*) from COMPANY_MASTER";
        
        List expResult = gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
        List result = instance.executeQuery(sqlQuery);
        assertEquals(expResult.size(), result.size());
    }

    
   



    /**
     * Test of getQuery method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetQuery() {
        System.out.println("getQuery");
        String sqlId = "JUNIT_SAMPLE";
        
        String expResult = gtnWsSqlService.getQuery(sqlId);
        String result = instance.getQuery(sqlId);
        assertEquals(expResult, result);
    }

    /**
     * Test of createParams method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testCreateParams() {
        System.out.println("createParams");
        
        Object[] expResult = {1,2,3};
        Object[] result = instance.createParams(1,2,3);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createDataTypes method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testCreateDataTypes() {
        System.out.println("createDataTypes");
        GtnFrameworkDataType[] dataTypes = {GtnFrameworkDataType.INTEGER,GtnFrameworkDataType.INTEGER,GtnFrameworkDataType.STRING};
        
        GtnFrameworkDataType[] result = instance.createDataTypes(dataTypes);
        assertArrayEquals(dataTypes, result);
    }

    
        private List<Object[]> getSampleHierarchy() throws GtnFrameworkGeneralException {
        String query = instance.getQuery("JUNIT_RB_SAMPLE_1");
        @SuppressWarnings("unchecked")
        List<Object[]> result = instance.executeQuery(query);
        return result;
    }
    /**
     * Test of getRBHierarchyLevelNameList method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetRBHierarchyLevelNameList() throws Exception {
        System.out.println("getRBHierarchyLevelNameList");
        List<Object[]> hierchy = getSampleHierarchy();
        if (hierchy != null && !hierchy.isEmpty()) {
            int hierarchyDefSid = (int) hierchy.get(0)[0];
            int versionNo = (int) hierchy.get(0)[1];

            List<String> expResult = instance.executeQuery("SELECT LEVEL_VALUE_REFERENCE FROM HIERARCHY_LEVEL_DEFINITION"
                    + " WHERE HIERARCHY_DEFINITION_SID ="+hierarchyDefSid+" AND VERSION_NO = "+versionNo+" ORDER BY LEVEL_NO");
            List<String> result = instance.getRBHierarchyLevelNameList(hierarchyDefSid, versionNo);
            assertEquals(expResult.size(), result.size());
        }
    }
   

    /**
     * Test of getHistHierarchyLevelDefinitionByLevelNO method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetHistHierarchyLevelDefinitionByLevelNO() throws Exception {
        System.out.println("getHistHierarchyLevelDefinitionByLevelNO");

        List<Object[]> hierchy = getSampleHierarchy();
        if (hierchy != null && !hierchy.isEmpty()) {
            int hierarchyDefSid = (int) hierchy.get(0)[0];
            int versionNo = (int) hierchy.get(0)[1];
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            int levelNo = 1;

            List<HierarchyLevelDefinitionBean> expResult = null;
            GtnWsSearchRequest request = new GtnWsSearchRequest();
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
            in1.setFilterValue1("" + hierarchyDefSid);
            GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
            in2.setFilterValue1("" + versionNo);
            gtnWebServiceSearchCriteriaList.add(in1);
            gtnWebServiceSearchCriteriaList.add(in2);
            request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            gtnWsRequest.setGtnWsSearchRequest(request);
            
            String expQuery="SELECT HLD.HIERARCHY_LEVEL_DEFINITION_SID, LEVEL_NO, LEVEL_NAME, LEVEL_VALUE_REFERENCE,"
                    + " HLD.HIERARCHY_DEFINITION_SID, TABLE_NAME, FIELD_NAME, HLD.VERSION_NO, HT.DESCRIPTION FROM HIERARCHY_LEVEL_DEFINITION "
                    + "HLD JOIN dbo.HIERARCHY_DEFINITION HD on HD.HIERARCHY_DEFINITION_SID = HLD.HIERARCHY_DEFINITION_SID"
                    + " JOIN dbo.HELPER_TABLE HT on HT.HELPER_TABLE_SID = HD.HIERARCHY_CATEGORY"
                    + " WHERE HLD.HIERARCHY_DEFINITION_SID = "+hierarchyDefSid+" AND HLD.VERSION_NO = "+versionNo+" AND HLD.LEVEL_NO= 1 ORDER BY HLD.LEVEL_NO";

            List<HierarchyLevelDefinitionBean> result = instance.getHistHierarchyLevelDefinitionByLevelNO(gtnWsRequest, levelNo);
            assertEquals(instance.executeQuery(expQuery).size(), result.size());
        }

    }
//
    /**
     * Test of getHierarchyDefinition method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetHierarchyDefinition_GtnWsRelationshipBuilderRequest_GtnWsRelationshipBuilderResponse() throws Exception {
        System.out.println("getHierarchyDefinition");
        GtnWsRelationshipBuilderRequest rbRequest = null;
        GtnWsRelationshipBuilderResponse rbResponse = null;
        
        GtnWsRelationshipBuilderResponse expResult = null;
        GtnWsRelationshipBuilderResponse result = instance.getHierarchyDefinition(rbRequest, rbResponse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
//
//    /**
//     * Test of getHierarchyDefinition method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetHierarchyDefinition_GtnUIFrameworkWebserviceRequest() throws Exception {
//        System.out.println("getHierarchyDefinition");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        
//        HierarchyDefinitionBean expResult = null;
//        HierarchyDefinitionBean result = instance.getHierarchyDefinition(gtnWsRequest);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHistHierarchyLevelDefinitionValuesByLevelNo method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetHistHierarchyLevelDefinitionValuesByLevelNo() throws Exception {
//        System.out.println("getHistHierarchyLevelDefinitionValuesByLevelNo");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        int levelNo = 0;
//        
//        List<HierarchyLevelValuesBean> expResult = null;
//        List<HierarchyLevelValuesBean> result = instance.getHistHierarchyLevelDefinitionValuesByLevelNo(gtnWsRequest, levelNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHistRelationshipLevelDefinitionValues method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetHistRelationshipLevelDefinitionValues() throws Exception {
//        System.out.println("getHistRelationshipLevelDefinitionValues");
//        GtnWsRelationshipBuilderRequest rbRequest = null;
//        GtnWsRelationshipBuilderResponse rbResponse = null;
//        
//        GtnWsRelationshipBuilderResponse expResult = null;
//        GtnWsRelationshipBuilderResponse result = instance.getHistRelationshipLevelDefinitionValues(rbRequest, rbResponse);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserDefinedLevelValues method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetUserDefinedLevelValues() throws Exception {
//        System.out.println("getUserDefinedLevelValues");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        List<Integer> modifiedHiddenIdList = null;
//        int levelNo = 0;
//        
//        GtnUIFrameworkDataTable expResult = null;
//        GtnUIFrameworkDataTable result = instance.getUserDefinedLevelValues(gtnWsRequest, modifiedHiddenIdList, levelNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLinkedLevelValues method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetLinkedLevelValues() {
//        System.out.println("getLinkedLevelValues");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        HierarchyDefinitionBean hierarchyDefinitionBean = null;
//        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = null;
//        
//        GtnSerachResponse expResult = null;
//        GtnSerachResponse result = instance.getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, hierarchyLevelDefinitionList);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHistAllLevelValues method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetHistAllLevelValues() throws Exception {
//        System.out.println("getHistAllLevelValues");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        
//        GtnSerachResponse expResult = null;
//        GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserDefinedLevelCount method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetUserDefinedLevelCount() throws Exception {
//        System.out.println("getUserDefinedLevelCount");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        int levelNo = 0;
//        
//        int expResult = 0;
//        int result = instance.getUserDefinedLevelCount(gtnWsRequest, levelNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFilteredValue2 method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetFilteredValue2() throws Exception {
//        System.out.println("getFilteredValue2");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        List<Integer> modifiedHiddenList = null;
//        int levelNo = 0;
//        
//        GtnSerachResponse expResult = null;
//        GtnSerachResponse result = instance.getFilteredValue2(gtnWsRequest, modifiedHiddenList, levelNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getModifiedHiddenIdList method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetModifiedHiddenIdList_GtnWsRelationshipBuilderRequest_GtnWsRelationshipBuilderResponse() {
//        System.out.println("getModifiedHiddenIdList");
//        GtnWsRelationshipBuilderRequest rbRequest = null;
//        GtnWsRelationshipBuilderResponse rbResponse = null;
//        
//        GtnWsRelationshipBuilderResponse expResult = null;
//        GtnWsRelationshipBuilderResponse result = instance.getModifiedHiddenIdList(rbRequest, rbResponse);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getModifiedHiddenIdList method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetModifiedHiddenIdList_GtnUIFrameworkWebserviceRequest() {
//        System.out.println("getModifiedHiddenIdList");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        
//        List<Integer> expResult = null;
//        List<Integer> result = instance.getModifiedHiddenIdList(gtnWsRequest);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of loadRelationship method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testLoadRelationship() throws Exception {
//        System.out.println("loadRelationship");
//        GtnWsRelationshipBuilderRequest rbRequest = null;
//        GtnWsRelationshipBuilderResponse rbResponse = null;
//        
//        GtnWsRelationshipBuilderResponse expResult = null;
//        GtnWsRelationshipBuilderResponse result = instance.loadRelationship(rbRequest, rbResponse);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of loadAutoBuildData method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testLoadAutoBuildData() throws Exception {
//        System.out.println("loadAutoBuildData");
//        int hierarchyDefSid = 0;
//        int hierarchyVersionNo = 0;
//        GtnWsRecordBean selectedTreeBean = null;
//        List<String> hiddenIdList = null;
//        
//        List<GtnWsRecordBean> expResult = null;
//        List<GtnWsRecordBean> result = instance.loadAutoBuildData(hierarchyDefSid, hierarchyVersionNo, selectedTreeBean, hiddenIdList);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserdefinedDataCombination method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetUserdefinedDataCombination() {
//        System.out.println("getUserdefinedDataCombination");
//        List<GtnWsRecordBean> linkedLevelDataList = null;
//        List<GtnWsRecordBean> userDefinedLevelDataList = null;
//        List<GtnWsRecordBean> finalDataList = null;
//        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = null;
//        
//        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of customizeRelationDataForAutoBuild method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testCustomizeRelationDataForAutoBuild() {
//        System.out.println("customizeRelationDataForAutoBuild");
//        List result_2 = null;
//        
//        List<GtnWsRecordBean> expResult = null;
//        List<GtnWsRecordBean> result = instance.customizeRelationDataForAutoBuild(result_2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSelectClauseForAutoBuild method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetSelectClauseForAutoBuild() {
//        System.out.println("getSelectClauseForAutoBuild");
//        String hirarchyNo = "";
//        HierarchyLevelDefinitionBean hierarchyBean = null;
//        GtnFrameworkQueryGeneratorBean finalQueryBean = null;
//        String gethiddenIdhierarchyNo = "";
//        
//        String expResult = "";
//        String result = instance.getSelectClauseForAutoBuild(hirarchyNo, hierarchyBean, finalQueryBean, gethiddenIdhierarchyNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHierarchyNoforQuery method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetHierarchyNoforQuery() {
//        System.out.println("getHierarchyNoforQuery");
//        String hirarchyNo = "";
//        String value = "";
//        
//        String expResult = "";
//        String result = instance.getHierarchyNoforQuery(hirarchyNo, value);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSavedHistLevelValuesList method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetSavedHistLevelValuesList() throws Exception {
//        System.out.println("getSavedHistLevelValuesList");
//        GtnWsRelationshipBuilderRequest rbRequest = null;
//        GtnWsRelationshipBuilderResponse rbResponse = null;
//        
//        GtnWsRelationshipBuilderResponse expResult = null;
//        GtnWsRelationshipBuilderResponse result = instance.getSavedHistLevelValuesList(rbRequest, rbResponse);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkSaveRelationship method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testCheckSaveRelationship() throws Exception {
//        System.out.println("checkSaveRelationship");
//        GtnWsRelationshipBuilderRequest rbRequest = null;
//        GtnWsRelationshipBuilderResponse rbResponse = null;
//        
//        instance.checkSaveRelationship(rbRequest, rbResponse);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkForDuplicateCompanyTree method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testCheckForDuplicateCompanyTree() throws Exception {
//        System.out.println("checkForDuplicateCompanyTree");
//        GtnWsRecordBean companyBean = null;
//        int selectedRelationshipId = 0;
//        int hierarchySid = 0;
//        
//        boolean expResult = false;
//        boolean result = instance.checkForDuplicateCompanyTree(companyBean, selectedRelationshipId, hierarchySid);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkForDuplicateTree method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testCheckForDuplicateTree() throws Exception {
//        System.out.println("checkForDuplicateTree");
//        GtnWsRecordBean companyBean = null;
//        int selectedRelationshipId = 0;
//        
//        boolean expResult = false;
//        boolean result = instance.checkForDuplicateTree(companyBean, selectedRelationshipId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkUsedRelationship method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testCheckUsedRelationship() throws Exception {
//        System.out.println("checkUsedRelationship");
//        int selectedRelationshipId = 0;
//        
//        boolean expResult = false;
//        boolean result = instance.checkUsedRelationship(selectedRelationshipId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveRelationship method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testSaveRelationship() throws Exception {
//        System.out.println("saveRelationship");
//        GtnWsRelationshipBuilderRequest rbRequest = null;
//        GtnWsRelationshipBuilderResponse rbResponse = null;
//        
//        instance.saveRelationship(rbRequest, rbResponse);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteRelationship method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testDeleteRelationship() throws Exception {
//        System.out.println("deleteRelationship");
//        GtnWsRelationshipBuilderRequest rbRequest = null;
//        GtnWsRelationshipBuilderResponse rbResponse = null;
//        
//        instance.deleteRelationship(rbRequest, rbResponse);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveRelationshipBuilderLevels method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testSaveRelationshipBuilderLevels() throws Exception {
//        System.out.println("saveRelationshipBuilderLevels");
//        RelationshipBuilder relationshipBuilder = null;
//        Date date = null;
//        String parentHierarchyNo = "";
//        String parentNode = "";
//        List<GtnWsRecordBean> levelBeanList = null;
//        Session session = null;
//        
//        instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deletAssociatedHierarchy method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testDeletAssociatedHierarchy() throws Exception {
//        System.out.println("deletAssociatedHierarchy");
//        RelationshipBuilder relationshipBuilder = null;
//        Session session = null;
//        
//        instance.deletAssociatedHierarchy(relationshipBuilder, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sortGtnWsRecordBean method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testSortGtnWsRecordBean() {
//        System.out.println("sortGtnWsRecordBean");
//        List<GtnWsRecordBean> levelsList = null;
//        
//        instance.sortGtnWsRecordBean(levelsList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getGtnWsRecordBean method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetGtnWsRecordBean() {
//        System.out.println("getGtnWsRecordBean");
//        HierarchyLevelsBean levelBean = null;
//        
//        GtnWsRecordBean expResult = null;
//        GtnWsRecordBean result = instance.getGtnWsRecordBean(levelBean);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLevelBeanAsList method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetLevelBeanAsList() {
//        System.out.println("getLevelBeanAsList");
//        HierarchyLevelsBean levelBean = null;
//        
//        List<Object> expResult = null;
//        List<Object> result = instance.getLevelBeanAsList(levelBean);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHierarchyLevelsBeanFromRecordBean method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetHierarchyLevelsBeanFromRecordBean() {
//        System.out.println("getHierarchyLevelsBeanFromRecordBean");
//        GtnWsRecordBean recordBean = null;
//        
//        HierarchyLevelsBean expResult = null;
//        HierarchyLevelsBean result = instance.getHierarchyLevelsBeanFromRecordBean(recordBean);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRelationQueries method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetRelationQueries() {
//        System.out.println("getRelationQueries");
//        int relationshipSid = 0;
//        List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList = null;
//        int versionNo = 0;
//        
//        List<String> expResult = null;
//        List<String> result = instance.getRelationQueries(relationshipSid, levelHierarchyLevelDefinitionList, versionNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRBHierarchyLevelDefinitionBySid method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetRBHierarchyLevelDefinitionBySid() throws Exception {
//        System.out.println("getRBHierarchyLevelDefinitionBySid");
//        int hierarchyDefSid = 0;
//        int versionNo = 0;
//        int prodRelationShipBuilderSid = 0;
//        
//        List<HierarchyLevelDefinitionBean> expResult = null;
//        List<HierarchyLevelDefinitionBean> result = instance.getRBHierarchyLevelDefinitionBySid(hierarchyDefSid, versionNo, prodRelationShipBuilderSid);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//
//    /**
//     * Test of getCustomizedRelationShipBean method, of class GtnWsRelationshipBuilderService.
//     */
//    @Test
//    public void testGetCustomizedRelationShipBean() {
//        System.out.println("getCustomizedRelationShipBean");
//        RelationshipBuilder relationshipBuilder = null;
//        Session session = null;
//        
//        GtnWsRelationshipBuilderBean expResult = null;
//        GtnWsRelationshipBuilderBean result = instance.getCustomizedRelationShipBean(relationshipBuilder, session);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
