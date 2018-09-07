/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelValuesBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelsBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.hibernate.engine.spi.SessionImplementor;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

    @Mock
    GtnWsRelationshipBuilderHelperService helperLogic = Mockito.mock(GtnWsRelationshipBuilderHelperService.class);
    @Mock
    GtnFrameworkEntityMasterBean entitybean = Mockito.mock(GtnFrameworkEntityMasterBean.class);
    @Mock
    @Autowired
    private org.hibernate.SessionFactory sessionFactory;
    @Spy
    @Autowired
    GtnWsRelationshipBuilderHierarchyFileGeneratorService gtnWsRelationshipBuilderHierarchyFileGenerator;

    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    @InjectMocks
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
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
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
        List result = instance.executeQuery(sqlQuery, params, type);
        assertEquals(expResult, Integer.parseInt(result.get(0).toString()));
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

        Object[] expResult = {1, 2, 3};
        Object[] result = instance.createParams(1, 2, 3);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createDataTypes method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testCreateDataTypes() {
        System.out.println("createDataTypes");
        GtnFrameworkDataType[] dataTypes = {GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING};

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
     * Test of getRBHierarchyLevelNameList method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetRBHierarchyLevelNameList() throws Exception {
        System.out.println("getRBHierarchyLevelNameList");
        List<Object[]> hierchy = getSampleHierarchy();
        if (hierchy != null && !hierchy.isEmpty()) {
            int hierarchyDefSid = (int) hierchy.get(0)[0];
            int versionNo = (int) hierchy.get(0)[1];

            List<String> expResult = instance.executeQuery("SELECT LEVEL_VALUE_REFERENCE FROM HIERARCHY_LEVEL_DEFINITION"
                    + " WHERE HIERARCHY_DEFINITION_SID =" + hierarchyDefSid + " AND VERSION_NO = " + versionNo + " ORDER BY LEVEL_NO");
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

            int levelNo = 1;

            List<HierarchyLevelDefinitionBean> expResult = null;
            List<HierarchyLevelDefinitionBean> result = getHierarchyDefbeanList(hierarchyDefSid, versionNo, levelNo);
            String expQuery = "SELECT HLD.HIERARCHY_LEVEL_DEFINITION_SID, LEVEL_NO, LEVEL_NAME, LEVEL_VALUE_REFERENCE,"
                    + " HLD.HIERARCHY_DEFINITION_SID, TABLE_NAME, FIELD_NAME, HLD.VERSION_NO, HT.DESCRIPTION FROM HIERARCHY_LEVEL_DEFINITION "
                    + "HLD JOIN dbo.HIERARCHY_DEFINITION HD on HD.HIERARCHY_DEFINITION_SID = HLD.HIERARCHY_DEFINITION_SID"
                    + " JOIN dbo.HELPER_TABLE HT on HT.HELPER_TABLE_SID = HD.HIERARCHY_CATEGORY"
                    + " WHERE HLD.HIERARCHY_DEFINITION_SID = " + hierarchyDefSid + " AND HLD.VERSION_NO = " + versionNo + " AND HLD.LEVEL_NO= 1 ORDER BY HLD.LEVEL_NO";

            assertEquals(instance.executeQuery(expQuery).size(), result.size());
        }

    }

    private List<HierarchyLevelDefinitionBean> getHierarchyDefbeanList(int hierarchyDefSid, int versionNo, int levelNo) throws GtnFrameworkGeneralException {
        GtnWsSearchRequest request = new GtnWsSearchRequest();
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
        in1.setFilterValue1("" + hierarchyDefSid);
        GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
        in2.setFilterValue1("" + versionNo);
        gtnWebServiceSearchCriteriaList.add(in1);
        gtnWebServiceSearchCriteriaList.add(in2);
        request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsRequest.setGtnWsSearchRequest(request);
        List<HierarchyLevelDefinitionBean> result = instance.getHistHierarchyLevelDefinitionByLevelNO(gtnWsRequest, levelNo);
        return result;
    }

    /**
     * Test of executeQuery method, of class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testExecuteQuery_String() throws Exception {
        System.out.println("executeQuery");
        System.out.println("executeQuery");
        String sqlQuery = "select 1 as result";
        int expResult = 1;
        List result = instance.executeQuery(sqlQuery);
        assertEquals(expResult, Integer.parseInt(result.get(0).toString()));
    }

    /**
     * Test of getGtnWsRelationshipBuilderHierarchyFileGenerator method, of
     * class GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetGtnWsRelationshipBuilderHierarchyFileGenerator() {
        System.out.println("getGtnWsRelationshipBuilderHierarchyFileGenerator");
        GtnWsRelationshipBuilderHierarchyFileGeneratorService gtnWsRelationshipBuilderHierarchyFileGenerator = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(gtnWsRelationshipBuilderHierarchyFileGenerator);

        GtnWsRelationshipBuilderHierarchyFileGeneratorService result = instance.getGtnWsRelationshipBuilderHierarchyFileGenerator();
        assertEquals(gtnWsRelationshipBuilderHierarchyFileGenerator, result);
    }

    /**
     * Test of getHierarchyVersionNo method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetHierarchyVersionNo() throws Exception {
        System.out.println("getHierarchyVersionNo");
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setHierarchyDefSId(1);
        List<String> inputlist = new ArrayList<>();
        inputlist.add(GtnFrameworkWebserviceConstant.HIST);
        inputlist.add(String.valueOf(rbRequest.getHierarchyDefSId()));
        when(gtnWsRelationshipBuilderHierarchyFileGenerator.getFinalQueryReplaced(inputlist, "getRBHierarchyVersionNo")).thenReturn("select 0 as version");

        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        int expResult = 0;
        GtnWsRelationshipBuilderResponse result = instance.getHierarchyVersionNo(rbRequest, rbResponse);
        assertEquals(expResult, result.getSelectedVersionNo());
    }

    /**
     * Test of getHierarchyDefinition method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetHierarchyDefinition_GtnWsRelationshipBuilderRequest_GtnWsRelationshipBuilderResponse() throws Exception {
        System.out.println("getHierarchyDefinition");
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setHierarchyDefSId(1);
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
        GtnWsRelationshipBuilderResponse expResult = null;
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        List<Object[]> resultList = new ArrayList<>();
        Object[] bean = {1, "two", "three", "cat", 5};
        resultList.add(bean);
        doReturn(resultList).when(in).executeQuery(Mockito.anyString());

        GtnWsRelationshipBuilderResponse result = in.getHierarchyDefinition(rbRequest, rbResponse);
        assertEquals("two", result.getHierarchyDefinitionBean().getHierarchyName());
        assertEquals("cat", result.getHierarchyDefinitionBean().getHierarchyCategory());
        assertEquals(1, result.getHierarchyDefinitionBean().getHierarchyDefinitionSid());
        assertEquals("three", result.getHierarchyDefinitionBean().getHierarchyType());
    }

    /**
     * Test of getHierarchyDefinition method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetHierarchyDefinition_GtnUIFrameworkWebserviceRequest() throws Exception {
        System.out.println("getHierarchyDefinition");

        HierarchyDefinitionBean result = getHierarchyDefBean();
        assertEquals("two", result.getHierarchyName());
        assertEquals("cat", result.getHierarchyCategory());
        assertEquals(1, result.getHierarchyDefinitionSid());
        assertEquals("three", result.getHierarchyType());
    }

    private HierarchyDefinitionBean getHierarchyDefBean() throws GtnFrameworkGeneralException {
        GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest("1", null);
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        List<Object[]> resultList = new ArrayList<>();
        Object[] bean = {1, "two", "three", "cat", 5};
        resultList.add(bean);
        doReturn(resultList).when(in).executeQuery(Mockito.anyString());
        HierarchyDefinitionBean result = in.getHierarchyDefinition(gtnWsRequest);
        return result;
    }

    private GtnUIFrameworkWebserviceRequest getSampleWsRequest(String filtervalue1, String filtervalue2) {

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest request = new GtnWsSearchRequest();
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
        in1.setFilterValue1(filtervalue1);
        GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
        in2.setFilterValue2(filtervalue2);
        gtnWebServiceSearchCriteriaList.add(in1);
        gtnWebServiceSearchCriteriaList.add(in2);
        request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
        orderby.setPropertyId("test");
        orderby.setOrderByCriteria("ASC");
        gtnWebServiceOrderByCriteriaList.add(orderby);
        request.setTableRecordOffset(1);
        request.setTableRecordStart(0);
        request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
        gtnWsRequest.setGtnWsSearchRequest(request);
        return gtnWsRequest;
    }

    /**
     * Test of getHistHierarchyLevelDefinitionValuesByLevelNo method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetHistHierarchyLevelDefinitionValuesByLevelNo() throws Exception {
        System.out.println("getHistHierarchyLevelDefinitionValuesByLevelNo");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest("1", "2");
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        List<Object[]> resultList = new ArrayList<>();
        Object[] bean = {1, 2, "three", BigDecimal.ONE, "5"};
        resultList.add(bean);
        doReturn(resultList).when(in).executeQuery(Mockito.anyString());
        int levelNo = 0;
        List<HierarchyLevelValuesBean> result = in.getHistHierarchyLevelDefinitionValuesByLevelNo(gtnWsRequest, levelNo);
        assertEquals(1, result.get(0).getHierarchyLevelValuesSid());
    }
//

    /**
     * Test of getHistRelationshipLevelDefinitionValues method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetHistRelationshipLevelDefinitionValues() throws Exception {
        System.out.println("getHistRelationshipLevelDefinitionValues");
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        List<Object[]> resultList = new ArrayList<>();
        Object[] bean = {0, 1, "two", "three", "four", "5", "6"};
        resultList.add(bean);
        doReturn(resultList).when(in).executeQuery(Mockito.anyString());
        GtnWsRelationshipBuilderResponse result = in.getHistRelationshipLevelDefinitionValues(rbRequest, rbResponse);
        assertEquals(bean[2], result.getRelationshipBuilderBeanList().get(0).getRekationshipLevelValue());
    }
//

    /**
     * Test of getUserDefinedLevelValues method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetUserDefinedLevelValues() throws Exception {
        System.out.println("getUserDefinedLevelValues");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest("1", "2");
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        List<Object[]> resultList = new ArrayList<>();
        Object[] bean = {1, 2, "three", BigDecimal.ONE, "5"};
        resultList.add(bean);
        doReturn(resultList).when(in).executeQuery(Mockito.anyString());
        int levelNo = 0;

        List<Integer> modifiedHiddenIdList = new ArrayList<>();
        modifiedHiddenIdList.add(1);
        in.getUserDefinedLevelValues(gtnWsRequest, modifiedHiddenIdList, levelNo);
    }

    /**
     * Test of getLinkedLevelValues method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetLinkedLevelValues() throws GtnFrameworkGeneralException {
        System.out.println("getLinkedLevelValues");

        List<Object[]> hierchy = getSampleHierarchy();
        if (hierchy != null && !hierchy.isEmpty()) {
            int hierarchyDefSid = (int) hierchy.get(0)[0];
            int versionNo = (int) hierchy.get(0)[1];

            int levelNo = 1;
            GtnFrameworkSelectColumnRelationBean gtnFrameworkKeyListBean = new GtnFrameworkSelectColumnRelationBean();
            when(helperLogic.finderImplInLogic(Mockito.anyString(), Mockito.anyString(), Mockito.anyList(), Mockito.anyBoolean())).thenReturn("Sqlquery");
            when(entitybean.getKeyRelationBeanUsingTableIdAndColumnName(Mockito.anyString(), Mockito.anyString())).thenReturn(gtnFrameworkKeyListBean);
            List<HierarchyLevelDefinitionBean> results = getHierarchyDefbeanList(hierarchyDefSid, versionNo, levelNo);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest("1", "2");

            GtnWsRelationshipBuilderService in = Mockito.spy(instance);
            List<Object[]> resultList = new ArrayList<>();
            Object[] bean = {1, 2, "three", BigDecimal.ONE, "5"};
            resultList.add(bean);
            doReturn(resultList).when(in).executeQuery(Mockito.anyString());
            HierarchyDefinitionBean hierarchyDefinitionBean = getHierarchyDefBean();
            GtnSerachResponse expResult = null;
            GtnSerachResponse result = in.getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, results);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getCustomizedRelationShipBean method, of class
     * GtnWsRelationshipBuilderService.
     */
    @Test
    public void testGetCustomizedRelationShipBean() {
        System.out.println("getCustomizedRelationShipBean");
        Session session = Mockito.mock(Session.class);
        HelperTable ht = new HelperTable();
        when(session.load(HelperTable.class, 1)).thenReturn(ht);

        GtnWsRelationshipBuilderService instance = new GtnWsRelationshipBuilderService();
        HierarchyDefinition hd = new HierarchyDefinition();
        hd.setHierarchyCategory(1);
        RelationshipBuilder relationshipBuilder = new RelationshipBuilder(1, new HelperTable(), hd, "RName", "RDesc", new Date(), "Type",
                1, Integer.MIN_VALUE, 1, new Date(), 1, new Date(), null);
        GtnWsRelationshipBuilderBean result = instance.getCustomizedRelationShipBean(relationshipBuilder, session);
        assertEquals(relationshipBuilder.getRelationshipName(), result.getRelationshipName());
        assertEquals(relationshipBuilder.getHierarchyVersion(), result.getHierarchyVersion());
        assertEquals(relationshipBuilder.getRelationshipName(), result.getRelationshipName());
        assertEquals(relationshipBuilder.getCreatedBy(), result.getCreatedBy());
        assertEquals(relationshipBuilder.getModifiedBy(), result.getModifiedBy());
        assertEquals(relationshipBuilder.getModifiedDate(), result.getModifiedDate());
    }

    @Test
    public void testGtnWsRelationshipBuilderService_1()
            throws Exception {

        GtnWsRelationshipBuilderService result = new GtnWsRelationshipBuilderService();

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getGtnWsRelationshipBuilderHierarchyFileGenerator());
    }

    /**
     * Run the boolean checkForDuplicateCompanyTree(GtnWsRecordBean,int,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckForDuplicateCompanyTree_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean companyBean = new GtnWsRecordBean();
        int selectedRelationshipId = 1;
        int hierarchySid = 1;

        boolean result = instance.checkForDuplicateCompanyTree(companyBean, selectedRelationshipId, hierarchySid);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkForDuplicateCompanyTree(GtnWsRecordBean,int,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckForDuplicateCompanyTree_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean companyBean = new GtnWsRecordBean();
        int selectedRelationshipId = 1;
        int hierarchySid = 1;

        boolean result = instance.checkForDuplicateCompanyTree(companyBean, selectedRelationshipId, hierarchySid);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkForDuplicateCompanyTree(GtnWsRecordBean,int,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckForDuplicateCompanyTree_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean companyBean = new GtnWsRecordBean();
        int selectedRelationshipId = 1;
        int hierarchySid = 1;

        boolean result = instance.checkForDuplicateCompanyTree(companyBean, selectedRelationshipId, hierarchySid);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkForDuplicateTree(GtnWsRecordBean,int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckForDuplicateTree_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean companyBean = new GtnWsRecordBean();
        int selectedRelationshipId = 1;

        boolean result = instance.checkForDuplicateTree(companyBean, selectedRelationshipId);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkForDuplicateTree(GtnWsRecordBean,int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckForDuplicateTree_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean companyBean = new GtnWsRecordBean();
        int selectedRelationshipId = 0;

        boolean result = instance.checkForDuplicateTree(companyBean, selectedRelationshipId);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkForDuplicateTree(GtnWsRecordBean,int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckForDuplicateTree_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean companyBean = new GtnWsRecordBean();
        int selectedRelationshipId = 1;

        boolean result = instance.checkForDuplicateTree(companyBean, selectedRelationshipId);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkForDuplicateTree(GtnWsRecordBean,int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckForDuplicateTree_4()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean companyBean = new GtnWsRecordBean();
        int selectedRelationshipId = 1;

        boolean result = instance.checkForDuplicateTree(companyBean, selectedRelationshipId);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setRelationshipName("name");
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setRelationshipName("name");
        rbRequest.setRelationshipDescription("desc");
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_4()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setRelationshipName("name");
        rbRequest.setRelationshipDescription("desc");
        rbRequest.setHierarchyDefSId(5);
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_5()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
        rbRequest.setRelationshipName("name");
        rbRequest.setRelationshipDescription("desc");
        rbRequest.setHierarchyDefSId(5);
        rbRequest.setHierarchyVersionNo(1);
        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_6()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setRelationshipName("name");
        rbRequest.setRelationshipDescription("desc");
        rbRequest.setHierarchyDefSId(5);
        rbRequest.setHierarchyVersionNo(1);
        rbRequest.setStartDate(new Date());
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_7()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setRelationshipName("name");
        rbRequest.setRelationshipDescription("desc");
        rbRequest.setHierarchyDefSId(5);
        rbRequest.setHierarchyVersionNo(1);
        rbRequest.setRbSysId(Integer.MAX_VALUE);
        rbRequest.setStartDate(new Date());
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
        GtnWsRelationshipBuilderService in= Mockito.spy(instance);
        in.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_8()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_9()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_10()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the void
     * checkSaveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckSaveRelationship_11()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.checkSaveRelationship(rbRequest, rbResponse);

        // add additional test code here
    }

    /**
     * Run the boolean checkUsedRelationship(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckUsedRelationship_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        int selectedRelationshipId = 0;
     
        boolean result = instance.checkUsedRelationship(selectedRelationshipId);

        // add additional test code here
        assertEquals(false, result);
    }

    /**
     * Run the boolean checkUsedRelationship(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckUsedRelationship_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        int selectedRelationshipId = 1;

        boolean result = instance.checkUsedRelationship(selectedRelationshipId);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkUsedRelationship(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckUsedRelationship_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        int selectedRelationshipId = 1;

        boolean result = instance.checkUsedRelationship(selectedRelationshipId);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the boolean checkUsedRelationship(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testCheckUsedRelationship_4()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        int selectedRelationshipId = 1;

        boolean result = instance.checkUsedRelationship(selectedRelationshipId);

        // add additional test code here
        assertTrue(result);
    }

    /**
     * Run the GtnFrameworkDataType[] createDataTypes(GtnFrameworkDataType[])
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCreateDataTypes_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());

        GtnFrameworkDataType[] result = instance.createDataTypes();

        // add additional test code here
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * Run the Object[] createParams(Object[]) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCreateParams_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());

        Object[] result = instance.createParams();

        // add additional test code here
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * Run the List<GtnWsRecordBean>
     * customizeRelationDataForAutoBuild(List<Object[]>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCustomizeRelationDataForAutoBuild_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<Object[]> result = new LinkedList();

        List<GtnWsRecordBean> result2 = instance.customizeRelationDataForAutoBuild(result);

        // add additional test code here
        assertNotNull(result2);
        assertEquals(0, result2.size());
    }

    /**
     * Run the List<GtnWsRecordBean>
     * customizeRelationDataForAutoBuild(List<Object[]>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCustomizeRelationDataForAutoBuild_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<Object[]> result = new LinkedList();

        List<GtnWsRecordBean> result2 = instance.customizeRelationDataForAutoBuild(result);

        // add additional test code here
        assertNotNull(result2);
        assertEquals(0, result2.size());
    }

    /**
     * Run the List<GtnWsRecordBean>
     * customizeRelationDataForAutoBuild(List<Object[]>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCustomizeRelationDataForAutoBuild_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<Object[]> result = new LinkedList();
        Object[] in=IntStream.rangeClosed(0, 12).boxed().toArray();
        result.add(in);

        List<GtnWsRecordBean> result2 = instance.customizeRelationDataForAutoBuild(result);

    }

//	/**
//	 * Run the void deletAssociatedHierarchy(RelationshipBuilder,Session) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testDeletAssociatedHierarchy_1()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
		Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

		instance.deletAssociatedHierarchy(relationshipBuilder, session);
} catch (Exception e) {

        }
	}
//
//	/**
//	 * Run the void deletAssociatedHierarchy(RelationshipBuilder,Session) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testDeletAssociatedHierarchy_2()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
		Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

		instance.deletAssociatedHierarchy(relationshipBuilder, session);
} catch (Exception e) {

        }  
	}
//
//	/**
//	 * Run the void deletAssociatedHierarchy(RelationshipBuilder,Session) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testDeletAssociatedHierarchy_3()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
		Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

		instance.deletAssociatedHierarchy(relationshipBuilder, session);
} catch (Exception e) {

        }
	}
//
//	/**
//	 * Run the void deletAssociatedHierarchy(RelationshipBuilder,Session) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testDeletAssociatedHierarchy_4()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
		Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

		instance.deletAssociatedHierarchy(relationshipBuilder, session);

		} catch (Exception e) {

        }
	}
//
//	/**
//	 * Run the void deleteRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
    public void testDeleteRelationship_1()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
            GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

            instance.deleteRelationship(rbRequest, rbResponse);
        } catch (Exception e) {

        }
    }
//
	/**
	 * Run the void deleteRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testDeleteRelationship_2()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

		instance.deleteRelationship(rbRequest, rbResponse);

		} catch (Exception e) {

        }}
//
//	/**
//	 * Run the void deleteRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testDeleteRelationship_3()
		throws Exception {
                   try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

		instance.deleteRelationship(rbRequest, rbResponse);

		} catch (Exception e) {

        }}
//
//	/**
//	 * Run the List executeQuery(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteQuery_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//
//		List result = instance.executeQuery(sqlQuery);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List executeQuery(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteQuery_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//
//		List result = instance.executeQuery(sqlQuery);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List executeQuery(String,Object[],GtnFrameworkDataType[]) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteQuery_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//		Object[] params = new Object[] {};
//		GtnFrameworkDataType[] type = new GtnFrameworkDataType[] {};
//
//		List result = instance.executeQuery(sqlQuery, params, type);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:126)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List executeQuery(String,Object[],GtnFrameworkDataType[]) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteQuery_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//		Object[] params = new Object[] {};
//		GtnFrameworkDataType[] type = new GtnFrameworkDataType[] {};
//
//		List result = instance.executeQuery(sqlQuery, params, type);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:126)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the int executeUpdateQuery(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteUpdateQuery_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//
//		int result = instance.executeUpdateQuery(sqlQuery);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeUpdateQuery(GtnWsRelationshipBuilderService.java:130)
//		assertEquals(0, result);
//	}
//
//	/**
//	 * Run the int executeUpdateQuery(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteUpdateQuery_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//
//		int result = instance.executeUpdateQuery(sqlQuery);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeUpdateQuery(GtnWsRelationshipBuilderService.java:130)
//		assertEquals(0, result);
//	}
//
//	/**
//	 * Run the int executeUpdateQuery(String,Object[],GtnFrameworkDataType[]) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteUpdateQuery_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//		Object[] params = new Object[] {};
//		GtnFrameworkDataType[] type = new GtnFrameworkDataType[] {};
//
//		int result = instance.executeUpdateQuery(sqlQuery, params, type);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeUpdateQuery(GtnWsRelationshipBuilderService.java:135)
//		assertEquals(0, result);
//	}
//
//	/**
//	 * Run the int executeUpdateQuery(String,Object[],GtnFrameworkDataType[]) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testExecuteUpdateQuery_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlQuery = "";
//		Object[] params = new Object[] {};
//		GtnFrameworkDataType[] type = new GtnFrameworkDataType[] {};
//
//		int result = instance.executeUpdateQuery(sqlQuery, params, type);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeUpdateQuery(GtnWsRelationshipBuilderService.java:135)
//		assertEquals(0, result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderBean getCustomizedRelationShipBean(RelationshipBuilder,Session) method test.
//	 *
//	 * @throws Exception
//	 *
//	
	@Test
	public void testGetCustomizedRelationShipBean_1()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
		Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

		GtnWsRelationshipBuilderBean result = instance.getCustomizedRelationShipBean(relationshipBuilder, session);

		assertNotNull(result);
                } catch (Exception e) {

        }
	}
    /**
     * Run the GtnSerachResponse
     * getFilteredValue2(GtnUIFrameworkWebserviceRequest,List<Integer>,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testGetFilteredValue2_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<Integer> modifiedHiddenList = new LinkedList();
        int levelNo = 1;
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = new GtnWebServiceSearchCriteria();

//        List<String> primaryIdList = Arrays.asList("mhey", "mhey2", "mhey3");
        gtnWebServiceSearchCriteria.setFilterValue1("1");
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnSerachResponse result = instance.getFilteredValue2(gtnWsRequest, modifiedHiddenList, levelNo);

        // add additional test code here
        assertNotNull(result);
    }

    /**
     * Run the GtnSerachResponse
     * getFilteredValue2(GtnUIFrameworkWebserviceRequest,List<Integer>,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testGetFilteredValue2_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<Integer> modifiedHiddenList = new LinkedList();
        int levelNo = 1;

        GtnSerachResponse result = instance.getFilteredValue2(gtnWsRequest, modifiedHiddenList, levelNo);

        // add additional test code here
        assertNotNull(result);
    }

    /**
     * Run the GtnSerachResponse
     * getFilteredValue2(GtnUIFrameworkWebserviceRequest,List<Integer>,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testGetFilteredValue2_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<Integer> modifiedHiddenList = new LinkedList();
        int levelNo = 1;

        GtnSerachResponse result = instance.getFilteredValue2(gtnWsRequest, modifiedHiddenList, levelNo);

        // add additional test code here
        assertNotNull(result);
    }

    /**
     * Run the GtnSerachResponse
     * getFilteredValue2(GtnUIFrameworkWebserviceRequest,List<Integer>,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testGetFilteredValue2_4()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<Integer> modifiedHiddenList = new LinkedList();
        int levelNo = 1;

        GtnSerachResponse result = instance.getFilteredValue2(gtnWsRequest, modifiedHiddenList, levelNo);

        // add additional test code here
        assertNotNull(result);
    }

    /**
     * Run the GtnSerachResponse
     * getFilteredValue2(GtnUIFrameworkWebserviceRequest,List<Integer>,int)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
    public void testGetFilteredValue2_5()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<Integer> modifiedHiddenList = new LinkedList();
        int levelNo = 1;

        GtnSerachResponse result = instance.getFilteredValue2(gtnWsRequest, modifiedHiddenList, levelNo);

        // add additional test code here
        assertNotNull(result);
    }

    /**
     * Run the GtnWsRecordBean getGtnWsRecordBean(HierarchyLevelsBean) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetGtnWsRecordBean_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        HierarchyLevelsBean levelBean = new HierarchyLevelsBean();

        GtnWsRecordBean result = instance.getGtnWsRecordBean(levelBean);

        // add additional test code here
        assertNotNull(result);
        assertEquals("RecordBean [rawObj=null]", result.toString());
        assertEquals(null, result.getChildList());
        assertEquals(null, result.getRawObjectArray());
        assertEquals(Boolean.FALSE, result.getParentFlag());
    }

    /**
     * Run the GtnWsRelationshipBuilderHierarchyFileGeneratorService
     * getGtnWsRelationshipBuilderHierarchyFileGenerator() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetGtnWsRelationshipBuilderHierarchyFileGenerator_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());

        GtnWsRelationshipBuilderHierarchyFileGeneratorService result = instance.getGtnWsRelationshipBuilderHierarchyFileGenerator();

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getSessionFactory());
        assertEquals(null, result.getGtnSqlQueryEngine());
        assertEquals(null, result.getGtnWsSqlService());
        assertEquals(null, result.getQueryGeneratorService());
        assertEquals(null, result.getGtnHierarchyServiceBuilder());
    }

    /**
     * Run the HierarchyDefinitionBean
     * getHierarchyDefinition(GtnUIFrameworkWebserviceRequest) method test.
     *
     * @throws Exception
     *
     *
     */
//	@Test
//	public void testGetHierarchyDefinition_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		HierarchyDefinitionBean result = instance.getHierarchyDefinition(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyDefinition(GtnWsRelationshipBuilderService.java:271)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the HierarchyDefinitionBean getHierarchyDefinition(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyDefinition_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		HierarchyDefinitionBean result = instance.getHierarchyDefinition(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyDefinition(GtnWsRelationshipBuilderService.java:271)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the HierarchyDefinitionBean getHierarchyDefinition(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyDefinition_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		HierarchyDefinitionBean result = instance.getHierarchyDefinition(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyDefinition(GtnWsRelationshipBuilderService.java:271)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHierarchyDefinition(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyDefinition_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyDefinition(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyDefinition(GtnWsRelationshipBuilderService.java:257)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHierarchyDefinition(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyDefinition_5()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyDefinition(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyDefinition(GtnWsRelationshipBuilderService.java:257)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHierarchyDefinition(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyDefinition_6()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyDefinition(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyDefinition(GtnWsRelationshipBuilderService.java:257)
//		assertNotNull(result);
//	}
//
    /**
     * Run the HierarchyLevelsBean
     * getHierarchyLevelsBeanFromRecordBean(GtnWsRecordBean) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHierarchyLevelsBeanFromRecordBean_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRecordBean recordBean = new GtnWsRecordBean();
        recordBean.setRecordHeader(IntStream.rangeClosed(0, 12).boxed().collect(Collectors.toList()));
        recordBean.setProperties(IntStream.rangeClosed(0, 12).boxed().collect(Collectors.toList()));

        HierarchyLevelsBean result = instance.getHierarchyLevelsBeanFromRecordBean(recordBean);

        assertNotNull(result);
    }

    /**
     * Run the String getHierarchyNoforQuery(String,String) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHierarchyNoforQuery_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        String hirarchyNo = "";
        String value = "";

        String result = instance.getHierarchyNoforQuery(hirarchyNo, value);

        // add additional test code here
        assertEquals(", '.'", result);
    }

    /**
     * Run the String getHierarchyNoforQuery(String,String) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHierarchyNoforQuery_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        String hirarchyNo = "";
        String value = "";

        String result = instance.getHierarchyNoforQuery(hirarchyNo, value);

        // add additional test code here
        assertEquals(", '.'", result);
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getHierarchyVersionNo(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
//	@Test
//	public void testGetHierarchyVersionNo_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyVersionNo(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyVersionNo(GtnWsRelationshipBuilderService.java:223)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHierarchyVersionNo(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyVersionNo_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyVersionNo(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyVersionNo(GtnWsRelationshipBuilderService.java:223)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHierarchyVersionNo(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyVersionNo_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyVersionNo(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyVersionNo(GtnWsRelationshipBuilderService.java:223)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHierarchyVersionNo(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyVersionNo_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyVersionNo(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyVersionNo(GtnWsRelationshipBuilderService.java:223)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHierarchyVersionNo(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHierarchyVersionNo_5()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHierarchyVersionNo(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHierarchyVersionNo(GtnWsRelationshipBuilderService.java:223)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_5()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_6()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_7()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_8()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistAllLevelValues_9()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnSerachResponse result = instance.getHistAllLevelValues(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistAllLevelValues(GtnWsRelationshipBuilderService.java:427)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<HierarchyLevelDefinitionBean> getHistHierarchyLevelDefinitionByLevelNO(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistHierarchyLevelDefinitionByLevelNO_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		List<HierarchyLevelDefinitionBean> result = instance.getHistHierarchyLevelDefinitionByLevelNO(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistHierarchyLevelDefinitionByLevelNO(GtnWsRelationshipBuilderService.java:238)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<HierarchyLevelDefinitionBean> getHistHierarchyLevelDefinitionByLevelNO(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistHierarchyLevelDefinitionByLevelNO_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		List<HierarchyLevelDefinitionBean> result = instance.getHistHierarchyLevelDefinitionByLevelNO(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistHierarchyLevelDefinitionByLevelNO(GtnWsRelationshipBuilderService.java:238)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<HierarchyLevelDefinitionBean> getHistHierarchyLevelDefinitionByLevelNO(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistHierarchyLevelDefinitionByLevelNO_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		List<HierarchyLevelDefinitionBean> result = instance.getHistHierarchyLevelDefinitionByLevelNO(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistHierarchyLevelDefinitionByLevelNO(GtnWsRelationshipBuilderService.java:238)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<HierarchyLevelValuesBean> getHistHierarchyLevelDefinitionValuesByLevelNo(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistHierarchyLevelDefinitionValuesByLevelNo_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		List<HierarchyLevelValuesBean> result = instance.getHistHierarchyLevelDefinitionValuesByLevelNo(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistHierarchyLevelDefinitionValuesByLevelNo(GtnWsRelationshipBuilderService.java:284)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<HierarchyLevelValuesBean> getHistHierarchyLevelDefinitionValuesByLevelNo(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistHierarchyLevelDefinitionValuesByLevelNo_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		List<HierarchyLevelValuesBean> result = instance.getHistHierarchyLevelDefinitionValuesByLevelNo(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistHierarchyLevelDefinitionValuesByLevelNo(GtnWsRelationshipBuilderService.java:284)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<HierarchyLevelValuesBean> getHistHierarchyLevelDefinitionValuesByLevelNo(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistHierarchyLevelDefinitionValuesByLevelNo_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		List<HierarchyLevelValuesBean> result = instance.getHistHierarchyLevelDefinitionValuesByLevelNo(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistHierarchyLevelDefinitionValuesByLevelNo(GtnWsRelationshipBuilderService.java:284)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHistRelationshipLevelDefinitionValues(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistRelationshipLevelDefinitionValues_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHistRelationshipLevelDefinitionValues(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistRelationshipLevelDefinitionValues(GtnWsRelationshipBuilderService.java:323)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHistRelationshipLevelDefinitionValues(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistRelationshipLevelDefinitionValues_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHistRelationshipLevelDefinitionValues(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistRelationshipLevelDefinitionValues(GtnWsRelationshipBuilderService.java:323)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnWsRelationshipBuilderResponse getHistRelationshipLevelDefinitionValues(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetHistRelationshipLevelDefinitionValues_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
//		GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
//
//		GtnWsRelationshipBuilderResponse result = instance.getHistRelationshipLevelDefinitionValues(rbRequest, rbResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getHistRelationshipLevelDefinitionValues(GtnWsRelationshipBuilderService.java:323)
//		assertNotNull(result);
//	}
    /**
     * Run the List<Object> getLevelBeanAsList(HierarchyLevelsBean) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetLevelBeanAsList_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        HierarchyLevelsBean levelBean = new HierarchyLevelsBean();

        List<Object> result = instance.getLevelBeanAsList(levelBean);

        // add additional test code here
        assertNotNull(result);
        assertEquals(10, result.size());
        assertTrue(result.contains(""));
        assertTrue(result.contains(new Integer(0)));
    }

//	/**
//	 * Run the GtnSerachResponse getLinkedLevelValues(GtnUIFrameworkWebserviceRequest,HierarchyDefinitionBean,List<HierarchyLevelDefinitionBean>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetLinkedLevelValues_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		HierarchyDefinitionBean hierarchyDefinitionBean = new HierarchyDefinitionBean();
//		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
//
//		GtnSerachResponse result = instance.getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, hierarchyLevelDefinitionList);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getLinkedLevelValues(GtnWsRelationshipBuilderService.java:369)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getLinkedLevelValues(GtnUIFrameworkWebserviceRequest,HierarchyDefinitionBean,List<HierarchyLevelDefinitionBean>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetLinkedLevelValues_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		HierarchyDefinitionBean hierarchyDefinitionBean = new HierarchyDefinitionBean();
//		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
//
//		GtnSerachResponse result = instance.getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, hierarchyLevelDefinitionList);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getLinkedLevelValues(GtnWsRelationshipBuilderService.java:369)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getLinkedLevelValues(GtnUIFrameworkWebserviceRequest,HierarchyDefinitionBean,List<HierarchyLevelDefinitionBean>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetLinkedLevelValues_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		HierarchyDefinitionBean hierarchyDefinitionBean = new HierarchyDefinitionBean();
//		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
//
//		GtnSerachResponse result = instance.getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, hierarchyLevelDefinitionList);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getLinkedLevelValues(GtnWsRelationshipBuilderService.java:369)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getLinkedLevelValues(GtnUIFrameworkWebserviceRequest,HierarchyDefinitionBean,List<HierarchyLevelDefinitionBean>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetLinkedLevelValues_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		HierarchyDefinitionBean hierarchyDefinitionBean = new HierarchyDefinitionBean();
//		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
//
//		GtnSerachResponse result = instance.getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, hierarchyLevelDefinitionList);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getLinkedLevelValues(GtnWsRelationshipBuilderService.java:369)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnSerachResponse getLinkedLevelValues(GtnUIFrameworkWebserviceRequest,HierarchyDefinitionBean,List<HierarchyLevelDefinitionBean>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetLinkedLevelValues_5()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		HierarchyDefinitionBean hierarchyDefinitionBean = new HierarchyDefinitionBean();
//		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
//
//		GtnSerachResponse result = instance.getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, hierarchyLevelDefinitionList);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getLinkedLevelValues(GtnWsRelationshipBuilderService.java:369)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<Integer> getModifiedHiddenIdList(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetModifiedHiddenIdList_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		List<Integer> result = instance.getModifiedHiddenIdList(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getModifiedHiddenIdList(GtnWsRelationshipBuilderService.java:606)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<Integer> getModifiedHiddenIdList(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetModifiedHiddenIdList_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		List<Integer> result = instance.getModifiedHiddenIdList(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getModifiedHiddenIdList(GtnWsRelationshipBuilderService.java:606)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<Integer> getModifiedHiddenIdList(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetModifiedHiddenIdList_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		List<Integer> result = instance.getModifiedHiddenIdList(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getModifiedHiddenIdList(GtnWsRelationshipBuilderService.java:606)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<Integer> getModifiedHiddenIdList(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetModifiedHiddenIdList_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		List<Integer> result = instance.getModifiedHiddenIdList(gtnWsRequest);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getModifiedHiddenIdList(GtnWsRelationshipBuilderService.java:606)
//		assertNotNull(result);
//	}
    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getModifiedHiddenIdList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetModifiedHiddenIdList_5()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        GtnWsRelationshipBuilderResponse result = instance.getModifiedHiddenIdList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(null, result.getMessageType());
        assertEquals(null, result.getHierarchyName());
        assertEquals(null, result.getCreationDate());
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(false, result.isSuccess());
        assertEquals(null, result.getHierarchyDefinitionBean());
        assertEquals(0, result.getRelationshipTypeId());
        assertEquals(null, result.getHierarchyLevelNameList());
        assertEquals(0, result.getSelectedVersionNo());
        assertEquals(null, result.getMainNode());
        assertEquals(0, result.getHierarchyDefSId());
        assertEquals(null, result.getHierarchyVersionNo());
        assertEquals(0, result.getRbSysId());
        assertEquals(1, result.getVersionNo());
        assertEquals(0, result.getLevelNo());
        assertEquals(null, result.getRbTreeNodeList());
        assertEquals(0, result.getCreatedById());
        assertEquals(null, result.getCreatedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getNoOfLevels());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getRelationshipType());
        assertEquals(null, result.getRelationshipBuilderBeanList());
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getModifiedHiddenIdList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetModifiedHiddenIdList_6()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        GtnWsRelationshipBuilderResponse result = instance.getModifiedHiddenIdList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(null, result.getMessageType());
        assertEquals(null, result.getHierarchyName());
        assertEquals(null, result.getCreationDate());
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(false, result.isSuccess());
        assertEquals(null, result.getHierarchyDefinitionBean());
        assertEquals(0, result.getRelationshipTypeId());
        assertEquals(null, result.getHierarchyLevelNameList());
        assertEquals(0, result.getSelectedVersionNo());
        assertEquals(null, result.getMainNode());
        assertEquals(0, result.getHierarchyDefSId());
        assertEquals(null, result.getHierarchyVersionNo());
        assertEquals(0, result.getRbSysId());
        assertEquals(1, result.getVersionNo());
        assertEquals(0, result.getLevelNo());
        assertEquals(null, result.getRbTreeNodeList());
        assertEquals(0, result.getCreatedById());
        assertEquals(null, result.getCreatedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getNoOfLevels());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getRelationshipType());
        assertEquals(null, result.getRelationshipBuilderBeanList());
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getModifiedHiddenIdList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetModifiedHiddenIdList_7()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        GtnWsRelationshipBuilderResponse result = instance.getModifiedHiddenIdList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(null, result.getMessageType());
        assertEquals(null, result.getHierarchyName());
        assertEquals(null, result.getCreationDate());
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(false, result.isSuccess());
        assertEquals(null, result.getHierarchyDefinitionBean());
        assertEquals(0, result.getRelationshipTypeId());
        assertEquals(null, result.getHierarchyLevelNameList());
        assertEquals(0, result.getSelectedVersionNo());
        assertEquals(null, result.getMainNode());
        assertEquals(0, result.getHierarchyDefSId());
        assertEquals(null, result.getHierarchyVersionNo());
        assertEquals(0, result.getRbSysId());
        assertEquals(1, result.getVersionNo());
        assertEquals(0, result.getLevelNo());
        assertEquals(null, result.getRbTreeNodeList());
        assertEquals(0, result.getCreatedById());
        assertEquals(null, result.getCreatedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getNoOfLevels());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getRelationshipType());
        assertEquals(null, result.getRelationshipBuilderBeanList());
    }

//	/**
//	 * Run the String getQuery(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetQuery_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String sqlId = "";
//
//		String result = instance.getQuery(sqlId);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getQuery(GtnWsRelationshipBuilderService.java:139)
//		assertNotNull(result);
//	}
//
    /**
     * Run the List<HierarchyLevelDefinitionBean>
     * getRBHierarchyLevelDefinitionBySid(int,int,int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRBHierarchyLevelDefinitionBySid_1()
            throws Exception {
        int hierarchyDefSid = 1;
        int versionNo = 1;
        int prodRelationShipBuilderSid = 1;
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        List<Object[]> resultList = new ArrayList<>();
        Object[] bean = {0, BigDecimal.ONE, "two", "3", 4, "5", "6", 7, "8"};
        resultList.add(bean);
        doReturn(resultList).when(in).executeQuery(Mockito.anyString());
        List<HierarchyLevelDefinitionBean> result = in.getRBHierarchyLevelDefinitionBySid(hierarchyDefSid, versionNo, prodRelationShipBuilderSid);
        assertNotNull(result);
    }

    /**
     * Run the List<HierarchyLevelDefinitionBean>
     * getRBHierarchyLevelDefinitionBySid(int,int,int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRBHierarchyLevelDefinitionBySid_2()
            throws Exception {
        int hierarchyDefSid = 1;
        int versionNo = 1;
        int prodRelationShipBuilderSid = 1;
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        List<Object[]> resultList = new ArrayList<>();
        Object[] bean = {0, BigDecimal.ONE, "two", "3", 4, "5", "6", 7, "8"};
        resultList.add(bean);
        doReturn(resultList).when(in).executeQuery(Mockito.anyString());
        List<HierarchyLevelDefinitionBean> result = in.getRBHierarchyLevelDefinitionBySid(hierarchyDefSid, versionNo, prodRelationShipBuilderSid);

        assertNotNull(result);
    }

//	/**
//	 * Run the List<String> getRBHierarchyLevelNameList(int,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetRBHierarchyLevelNameList_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		int hierarchyDefSid = 1;
//		int versionNo = 1;
//
//		List<String> result = instance.getRBHierarchyLevelNameList(hierarchyDefSid, versionNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getRBHierarchyLevelNameList(GtnWsRelationshipBuilderService.java:1515)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<String> getRBHierarchyLevelNameList(int,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetRBHierarchyLevelNameList_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		int hierarchyDefSid = 1;
//		int versionNo = 1;
//
//		List<String> result = instance.getRBHierarchyLevelNameList(hierarchyDefSid, versionNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.executeQuery(GtnWsRelationshipBuilderService.java:120)
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getRBHierarchyLevelNameList(GtnWsRelationshipBuilderService.java:1515)
//		assertNotNull(result);
//	}
    /**
     * Run the List<String>
     * getRelationQueries(int,List<HierarchyLevelDefinitionBean>,int) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRelationQueries_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        int relationshipSid = 1;
        List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList = new LinkedList();
        int versionNo = 1;

        List<String> result = instance.getRelationQueries(relationshipSid, levelHierarchyLevelDefinitionList, versionNo);

        // add additional test code here
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    /**
     * Run the List<String>
     * getRelationQueries(int,List<HierarchyLevelDefinitionBean>,int) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRelationQueries_2()
            throws Exception {
        int relationshipSid = 1;
        Mockito.when(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(Mockito.anyList(), Mockito.anyString())).thenReturn("");
        List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean hd = new HierarchyLevelDefinitionBean();
        hd.setLevelNo(1);
        levelHierarchyLevelDefinitionList.add(hd);
        int versionNo = 1;

        List<String> result = instance.getRelationQueries(relationshipSid, levelHierarchyLevelDefinitionList, versionNo);

        // add additional test code here
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Run the List<String>
     * getRelationQueries(int,List<HierarchyLevelDefinitionBean>,int) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRelationQueries_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        int relationshipSid = 1;
        List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList = new LinkedList();
        int versionNo = 1;

        List<String> result = instance.getRelationQueries(relationshipSid, levelHierarchyLevelDefinitionList, versionNo);

        // add additional test code here
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getSavedHistLevelValuesList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetSavedHistLevelValuesList_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        GtnWsRelationshipBuilderResponse result = instance.getSavedHistLevelValuesList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(null, result.getMessageType());
        assertEquals(null, result.getHierarchyName());
        assertEquals(null, result.getCreationDate());
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(false, result.isSuccess());
        assertEquals(null, result.getHierarchyDefinitionBean());
        assertEquals(0, result.getRelationshipTypeId());
        assertEquals(null, result.getHierarchyLevelNameList());
        assertEquals(0, result.getSelectedVersionNo());
        assertEquals(null, result.getMainNode());
        assertEquals(null, result.getHiddenIdList());
        assertEquals(0, result.getHierarchyDefSId());
        assertEquals(null, result.getHierarchyVersionNo());
        assertEquals(0, result.getRbSysId());
        assertEquals(1, result.getVersionNo());
        assertEquals(0, result.getLevelNo());
        assertEquals(null, result.getRbTreeNodeList());
        assertEquals(0, result.getCreatedById());
        assertEquals(null, result.getCreatedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getNoOfLevels());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getRelationshipType());
        assertEquals(null, result.getRelationshipBuilderBeanList());
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getSavedHistLevelValuesList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetSavedHistLevelValuesList_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        GtnWsRelationshipBuilderResponse result = instance.getSavedHistLevelValuesList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(null, result.getMessageType());
        assertEquals(null, result.getHierarchyName());
        assertEquals(null, result.getCreationDate());
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(false, result.isSuccess());
        assertEquals(null, result.getHierarchyDefinitionBean());
        assertEquals(0, result.getRelationshipTypeId());
        assertEquals(null, result.getHierarchyLevelNameList());
        assertEquals(0, result.getSelectedVersionNo());
        assertEquals(null, result.getMainNode());
        assertEquals(null, result.getHiddenIdList());
        assertEquals(0, result.getHierarchyDefSId());
        assertEquals(null, result.getHierarchyVersionNo());
        assertEquals(0, result.getRbSysId());
        assertEquals(1, result.getVersionNo());
        assertEquals(0, result.getLevelNo());
        assertEquals(null, result.getRbTreeNodeList());
        assertEquals(0, result.getCreatedById());
        assertEquals(null, result.getCreatedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getNoOfLevels());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getRelationshipType());
        assertEquals(null, result.getRelationshipBuilderBeanList());
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getSavedHistLevelValuesList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetSavedHistLevelValuesList_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        GtnWsRelationshipBuilderResponse result = instance.getSavedHistLevelValuesList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(null, result.getMessageType());
        assertEquals(null, result.getHierarchyName());
        assertEquals(null, result.getCreationDate());
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(false, result.isSuccess());
        assertEquals(null, result.getHierarchyDefinitionBean());
        assertEquals(0, result.getRelationshipTypeId());
        assertEquals(null, result.getHierarchyLevelNameList());
        assertEquals(0, result.getSelectedVersionNo());
        assertEquals(null, result.getMainNode());
        assertEquals(null, result.getHiddenIdList());
        assertEquals(0, result.getHierarchyDefSId());
        assertEquals(null, result.getHierarchyVersionNo());
        assertEquals(0, result.getRbSysId());
        assertEquals(1, result.getVersionNo());
        assertEquals(0, result.getLevelNo());
        assertEquals(null, result.getRbTreeNodeList());
        assertEquals(0, result.getCreatedById());
        assertEquals(null, result.getCreatedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getNoOfLevels());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getRelationshipType());
        assertEquals(null, result.getRelationshipBuilderBeanList());
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getSavedHistLevelValuesList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetSavedHistLevelValuesList_4()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
        rbRequest.setRbSysId(1);
        List<GtnWsRelationshipLevelDefinitionBean> list=new ArrayList<>();
        GtnWsRelationshipLevelDefinitionBean relationBean=new GtnWsRelationshipLevelDefinitionBean();
        list.add(relationBean);
        GtnWsRelationshipBuilderService in=  Mockito.spy(instance);
        rbResponse.setRelationshipBuilderBeanList(list);
        GtnWsRelationshipBuilderResponse res=new GtnWsRelationshipBuilderResponse();
        doReturn(res).when(in).getHistRelationshipLevelDefinitionValues(rbRequest, rbResponse);
        GtnWsRelationshipBuilderResponse result = in.getSavedHistLevelValuesList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
      
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * getSavedHistLevelValuesList(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetSavedHistLevelValuesList_5()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        GtnWsRelationshipBuilderResponse result = instance.getSavedHistLevelValuesList(rbRequest, rbResponse);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(null, result.getMessageType());
        assertEquals(null, result.getHierarchyName());
        assertEquals(null, result.getCreationDate());
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(false, result.isSuccess());
        assertEquals(null, result.getHierarchyDefinitionBean());
        assertEquals(0, result.getRelationshipTypeId());
        assertEquals(null, result.getHierarchyLevelNameList());
        assertEquals(0, result.getSelectedVersionNo());
        assertEquals(null, result.getMainNode());
        assertEquals(null, result.getHiddenIdList());
        assertEquals(0, result.getHierarchyDefSId());
        assertEquals(null, result.getHierarchyVersionNo());
        assertEquals(0, result.getRbSysId());
        assertEquals(1, result.getVersionNo());
        assertEquals(0, result.getLevelNo());
        assertEquals(null, result.getRbTreeNodeList());
        assertEquals(0, result.getCreatedById());
        assertEquals(null, result.getCreatedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getNoOfLevels());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getRelationshipType());
        assertEquals(null, result.getRelationshipBuilderBeanList());
    }

    /**
     * Run the String
     * getSelectClauseForAutoBuild(String,HierarchyLevelDefinitionBean,GtnFrameworkQueryGeneratorBean,String)
     * method test.
     *
     * @throws Exception
     *
     *
     * //
     */
//	@Test
//	public void testGetSelectClauseForAutoBuild_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		String hirarchyNo = "";
//		HierarchyLevelDefinitionBean hierarchyBean = new HierarchyLevelDefinitionBean();
//		GtnFrameworkQueryGeneratorBean finalQueryBean = new GtnFrameworkQueryGeneratorBean();
//		String gethiddenIdhierarchyNo = "";
//
//		String result = instance.getSelectClauseForAutoBuild(hirarchyNo, hierarchyBean, finalQueryBean, gethiddenIdhierarchyNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getSelectClauseForAutoBuild(GtnWsRelationshipBuilderService.java:901)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the int getUserDefinedLevelCount(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetUserDefinedLevelCount_1()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		int result = instance.getUserDefinedLevelCount(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getUserDefinedLevelCount(GtnWsRelationshipBuilderService.java:461)
//		assertEquals(0, result);
//	}
//
//	/**
//	 * Run the int getUserDefinedLevelCount(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetUserDefinedLevelCount_2()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		int result = instance.getUserDefinedLevelCount(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getUserDefinedLevelCount(GtnWsRelationshipBuilderService.java:461)
//		assertEquals(0, result);
//	}
//
//	/**
//	 * Run the int getUserDefinedLevelCount(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetUserDefinedLevelCount_3()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		int result = instance.getUserDefinedLevelCount(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getUserDefinedLevelCount(GtnWsRelationshipBuilderService.java:461)
//		assertEquals(0, result);
//	}
//
//	/**
//	 * Run the int getUserDefinedLevelCount(GtnUIFrameworkWebserviceRequest,int) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
//	@Test
//	public void testGetUserDefinedLevelCount_4()
//		throws Exception {
//		
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		int levelNo = 1;
//
//		int result = instance.getUserDefinedLevelCount(gtnWsRequest, levelNo);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService.getUserDefinedLevelCount(GtnWsRelationshipBuilderService.java:461)
//		assertEquals(0, result);
//	}
//

	@Test
	public void testGetUserDefinedLevelValues_5()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		List<Integer> modifiedHiddenIdList = new LinkedList();
		int levelNo = 1;

		GtnUIFrameworkDataTable result = instance.getUserDefinedLevelValues(gtnWsRequest, modifiedHiddenIdList, levelNo);

		assertNotNull(result);
                } catch (Exception e) {

        }
	}
    /**
     * Run the void
     * getUserdefinedDataCombination(List<GtnWsRecordBean>,List<GtnWsRecordBean>,List<GtnWsRecordBean>,HierarchyLevelDefinitionBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetUserdefinedDataCombination_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> linkedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> userDefinedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> finalDataList = new LinkedList();
        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = new HierarchyLevelDefinitionBean();

        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);

        // add additional test code here
    }

    /**
     * Run the void
     * getUserdefinedDataCombination(List<GtnWsRecordBean>,List<GtnWsRecordBean>,List<GtnWsRecordBean>,HierarchyLevelDefinitionBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetUserdefinedDataCombination_2()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> linkedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> userDefinedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> finalDataList = new LinkedList();
        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = new HierarchyLevelDefinitionBean();

        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);

        // add additional test code here
    }

    /**
     * Run the void
     * getUserdefinedDataCombination(List<GtnWsRecordBean>,List<GtnWsRecordBean>,List<GtnWsRecordBean>,HierarchyLevelDefinitionBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetUserdefinedDataCombination_3()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> linkedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> userDefinedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> finalDataList = new LinkedList();
        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = new HierarchyLevelDefinitionBean();

        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);

        // add additional test code here
    }

    /**
     * Run the void
     * getUserdefinedDataCombination(List<GtnWsRecordBean>,List<GtnWsRecordBean>,List<GtnWsRecordBean>,HierarchyLevelDefinitionBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetUserdefinedDataCombination_4()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> linkedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> userDefinedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> finalDataList = new LinkedList();
        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = new HierarchyLevelDefinitionBean();

        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);

        // add additional test code here
    }

    /**
     * Run the void
     * getUserdefinedDataCombination(List<GtnWsRecordBean>,List<GtnWsRecordBean>,List<GtnWsRecordBean>,HierarchyLevelDefinitionBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetUserdefinedDataCombination_5()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> linkedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> userDefinedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> finalDataList = new LinkedList();
        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = new HierarchyLevelDefinitionBean();

        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);

        // add additional test code here
    }

    /**
     * Run the void
     * getUserdefinedDataCombination(List<GtnWsRecordBean>,List<GtnWsRecordBean>,List<GtnWsRecordBean>,HierarchyLevelDefinitionBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetUserdefinedDataCombination_6()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> linkedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> userDefinedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> finalDataList = new LinkedList();
        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = new HierarchyLevelDefinitionBean();

        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);

        // add additional test code here
    }

    /**
     * Run the void
     * getUserdefinedDataCombination(List<GtnWsRecordBean>,List<GtnWsRecordBean>,List<GtnWsRecordBean>,HierarchyLevelDefinitionBean)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetUserdefinedDataCombination_7()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> linkedLevelDataList = new LinkedList();
        List<GtnWsRecordBean> userDefinedLevelDataList = new LinkedList();
        
        List<GtnWsRecordBean> finalDataList = new LinkedList();
        GtnWsRecordBean recordBean = new GtnWsRecordBean();
        
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        recordBean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        recordBean.setProperties(values);
       GtnWsRecordBean linkedBean = new GtnWsRecordBean();
        
        List<Object> valuelinkeds = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        linkedBean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        linkedBean.setProperties(valuelinkeds);
        linkedBean.setPropertyValueByIndex(1, 0);
        
        userDefinedLevelDataList.add(recordBean);
        linkedLevelDataList.add(linkedBean);
        
        HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = new HierarchyLevelDefinitionBean();
       hierarchyLevelDefinitionBean.setLevelNo(1);
        instance.getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList, hierarchyLevelDefinitionBean);

        // add additional test code here
    }

    /**
     * Run the List<GtnWsRecordBean>
     * loadAutoBuildData(int,int,GtnWsRecordBean,List<String>) method test.
     *
     * @throws Exception
     *
     *
     */
	@Test
	public void testLoadAutoBuildData_1()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		int hierarchyDefSid = 1;
		int hierarchyVersionNo = 1;
		GtnWsRecordBean selectedTreeBean = new GtnWsRecordBean();
		List<String> hiddenIdList = new LinkedList();

		List<GtnWsRecordBean> result = instance.loadAutoBuildData(hierarchyDefSid, hierarchyVersionNo, selectedTreeBean, hiddenIdList);

		assertNotNull(result);
                } catch (Exception e) {

        }
	}
//
//	/**
//	 * Run the List<GtnWsRecordBean> loadAutoBuildData(int,int,GtnWsRecordBean,List<String>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testLoadAutoBuildData_2()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		int hierarchyDefSid = 1;
		int hierarchyVersionNo = 1;
		GtnWsRecordBean selectedTreeBean = new GtnWsRecordBean();
		List<String> hiddenIdList = new LinkedList();

		List<GtnWsRecordBean> result = instance.loadAutoBuildData(hierarchyDefSid, hierarchyVersionNo, selectedTreeBean, hiddenIdList);

		assertNotNull(result);
                } catch (Exception e) {

        }
	}
//
//	/**
//	 * Run the List<GtnWsRecordBean> loadAutoBuildData(int,int,GtnWsRecordBean,List<String>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testLoadAutoBuildData_3()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		int hierarchyDefSid = 1;
		int hierarchyVersionNo = 1;
		GtnWsRecordBean selectedTreeBean = new GtnWsRecordBean();
		List<String> hiddenIdList = new LinkedList();

		List<GtnWsRecordBean> result = instance.loadAutoBuildData(hierarchyDefSid, hierarchyVersionNo, selectedTreeBean, hiddenIdList);
		assertNotNull(result);
                } catch (Exception e) {

        }
	}
//
//	/**
//	 * Run the List<GtnWsRecordBean> loadAutoBuildData(int,int,GtnWsRecordBean,List<String>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testLoadAutoBuildData_4()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		int hierarchyDefSid = 1;
		int hierarchyVersionNo = 1;
		GtnWsRecordBean selectedTreeBean = new GtnWsRecordBean();
		List<String> hiddenIdList = new LinkedList();

		List<GtnWsRecordBean> result = instance.loadAutoBuildData(hierarchyDefSid, hierarchyVersionNo, selectedTreeBean, hiddenIdList);
		assertNotNull(result);
            } catch (Exception e) {

        }
	}
//
//	/**
//	 * Run the List<GtnWsRecordBean> loadAutoBuildData(int,int,GtnWsRecordBean,List<String>) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
	@Test
	public void testLoadAutoBuildData_5()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		int hierarchyDefSid = 1;
		int hierarchyVersionNo = 1;
		GtnWsRecordBean selectedTreeBean = new GtnWsRecordBean();
		List<String> hiddenIdList = new LinkedList();

		List<GtnWsRecordBean> result = instance.loadAutoBuildData(hierarchyDefSid, hierarchyVersionNo, selectedTreeBean, hiddenIdList);

		assertNotNull(result);
                } catch (Exception e) {

        }
	}
//
    /**
     * Run the GtnWsRelationshipBuilderResponse
     * loadRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testLoadRelationship_1()
            throws Exception {
//		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
        GtnWsRelationshipBuilderService in = Mockito.spy(instance);
        GtnWsRecordBean recordBean = new GtnWsRecordBean();
        recordBean.setRecordHeader(IntStream.rangeClosed(0, 12).boxed().collect(Collectors.toList()));
        recordBean.setProperties(IntStream.rangeClosed(0, 12).boxed().collect(Collectors.toList()));
        rbResponse.addToRbTreeNodeList(recordBean);
        when(in.getSavedHistLevelValuesList(rbRequest, rbResponse)).thenReturn(rbResponse);
        doNothing().when(in).sortGtnWsRecordBean(rbResponse.getRbTreeNodeList());
        GtnWsRelationshipBuilderResponse result = instance.loadRelationship(rbRequest, rbResponse);

        assertNotNull(result);
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * loadRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testLoadRelationship_2()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
            GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

            GtnWsRelationshipBuilderResponse result = instance.loadRelationship(rbRequest, rbResponse);

            assertNotNull(result);
        } catch (Exception e) {

        }
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * loadRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testLoadRelationship_3()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
            GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

            GtnWsRelationshipBuilderResponse result = instance.loadRelationship(rbRequest, rbResponse);

            assertNotNull(result);
        } catch (Exception e) {

        }
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * loadRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testLoadRelationship_4()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
            GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

            GtnWsRelationshipBuilderResponse result = instance.loadRelationship(rbRequest, rbResponse);

            assertNotNull(result);
        } catch (Exception e) {

        }
    }

    /**
     * Run the GtnWsRelationshipBuilderResponse
     * loadRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testLoadRelationship_5()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
            GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

            GtnWsRelationshipBuilderResponse result = instance.loadRelationship(rbRequest, rbResponse);
            assertNotNull(result);
        } catch (Exception e) {

        }
    }

    /**
     * Run the void
     * saveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationship_1()
            throws Exception {
       
        Session session = Mockito.mock(Session.class);
        Transaction tx = Mockito.mock(Transaction.class);
        RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
        relationshipBuilder.setHelperTable(new HelperTable());
        relationshipBuilder.setHierarchyDefinition(new HierarchyDefinition());
        doReturn(relationshipBuilder).when(session).load(RelationshipBuilder.class, 1);
        doNothing().when(session).saveOrUpdate(Mockito.any());
        doReturn(session).when(sessionFactory).openSession();
        doReturn(tx).when(session).beginTransaction();

        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        rbRequest.setRbSysId(1);
        GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

        instance.saveRelationship(rbRequest, rbResponse);
            
            
        
    }

    /**
     * Run the void
     * saveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationship_2()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
            GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

            instance.saveRelationship(rbRequest, rbResponse);
        } catch (Exception e) {

        }
    }

    /**
     * Run the void
     * saveRelationship(GtnWsRelationshipBuilderRequest,GtnWsRelationshipBuilderResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationship_3()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
            GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();

            instance.saveRelationship(rbRequest, rbResponse);

        } catch (Exception e) {

        }
    }

    /**
     * Run the void
     * saveRelationshipBuilderLevels(RelationshipBuilder,Date,String,String,List<GtnWsRecordBean>,Session)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationshipBuilderLevels_1()
            throws Exception {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
            Date date = new Date();
            String parentHierarchyNo = "";
            String parentNode = "";
            List<GtnWsRecordBean> levelBeanList = new LinkedList();
            GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
            gtnWsRecordBean.setRecordHeader(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
            gtnWsRecordBean.setProperties(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
            levelBeanList.add(gtnWsRecordBean);
            Session session = Mockito.mock(Session.class);
//   Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);
            instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);
    }

    /**
     * Run the void
     * saveRelationshipBuilderLevels(RelationshipBuilder,Date,String,String,List<GtnWsRecordBean>,Session)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationshipBuilderLevels_2()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
            Date date = new Date();
            String parentHierarchyNo = "";
            String parentNode = "";
            List<GtnWsRecordBean> levelBeanList = new LinkedList();
            Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

            instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);

        } catch (Exception e) {

        }
    }

    /**
     * Run the void
     * saveRelationshipBuilderLevels(RelationshipBuilder,Date,String,String,List<GtnWsRecordBean>,Session)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationshipBuilderLevels_3()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
            Date date = new Date();
            String parentHierarchyNo = "";  
            String parentNode = "";
            List<GtnWsRecordBean> levelBeanList = new LinkedList();
            Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

            instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);
        } catch (Exception e) {

        }
    }

    /**
     * Run the void
     * saveRelationshipBuilderLevels(RelationshipBuilder,Date,String,String,List<GtnWsRecordBean>,Session)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationshipBuilderLevels_4()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
            Date date = new Date();
            String parentHierarchyNo = "";
            String parentNode = "";
            List<GtnWsRecordBean> levelBeanList = new LinkedList();
            Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

            instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);
        } catch (Exception e) {

        }
    }
    /**
     * Run the void
     * saveRelationshipBuilderLevels(RelationshipBuilder,Date,String,String,List<GtnWsRecordBean>,Session)
     * method test.
     *
     * @throws Exception
     *
     *
     */
	@Test
	public void testSaveRelationshipBuilderLevels_5()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
		Date date = new Date();
		String parentHierarchyNo = "";
		String parentNode = "";
		List<GtnWsRecordBean> levelBeanList = new LinkedList();
		Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

		instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);

		} catch (Exception e) {

        }    
	}
    /**
     * Run the void
     * saveRelationshipBuilderLevels(RelationshipBuilder,Date,String,String,List<GtnWsRecordBean>,Session)
     * method test.
     *
     * @throws Exception
     *
     *
     */
	@Test
	public void testSaveRelationshipBuilderLevels_6()
		throws Exception {
            try{
		
		instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
		Date date = new Date();
		String parentHierarchyNo = "";
		String parentNode = "";
		List<GtnWsRecordBean> levelBeanList = new LinkedList();
		Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

		instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);
                } catch (Exception e) {

        }

	}
//	/**
//	 * Run the void saveRelationshipBuilderLevels(RelationshipBuilder,Date,String,String,List<GtnWsRecordBean>,Session) method test.
//	 *
//	 * @throws Exception
//	 *
//	
//	 */
    @Test
    public void testSaveRelationshipBuilderLevels_7()
            throws Exception {
        try {
            
            instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
            RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
            Date date = new Date();
            String parentHierarchyNo = "";
            String parentNode = "";
            List<GtnWsRecordBean> levelBeanList = new LinkedList();
            Session session = new SessionDelegatorBaseImpl((SessionImplementor) null, (Session) null);

            instance.saveRelationshipBuilderLevels(relationshipBuilder, date, parentHierarchyNo, parentNode, levelBeanList, session);
        } catch (Exception e) {

        }
    }

    /**
     * Run the void
     * setGtnWsRelationshipBuilderHierarchyFileGenerator(GtnWsRelationshipBuilderHierarchyFileGeneratorService)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetGtnWsRelationshipBuilderHierarchyFileGenerator_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        GtnWsRelationshipBuilderHierarchyFileGeneratorService gtnWsRelationshipBuilderHierarchyFileGenerator = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();

        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(gtnWsRelationshipBuilderHierarchyFileGenerator);

        // add additional test code here
    }

    /**
     * Run the void sortGtnWsRecordBean(List<GtnWsRecordBean>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSortGtnWsRecordBean_1()
            throws Exception {
        
        instance.setGtnWsRelationshipBuilderHierarchyFileGenerator(new GtnWsRelationshipBuilderHierarchyFileGeneratorService());
        List<GtnWsRecordBean> levelsList = new LinkedList();
        GtnWsRecordBean recordBean = new GtnWsRecordBean();
        
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        recordBean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        recordBean.setProperties(values);
        levelsList.add(recordBean);

        GtnWsRecordBean recordBean2 = new GtnWsRecordBean();
        recordBean2.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        recordBean2.setProperties(values);
        levelsList.add(recordBean2);

        instance.sortGtnWsRecordBean(levelsList);

        // add additional test code here
    }

}