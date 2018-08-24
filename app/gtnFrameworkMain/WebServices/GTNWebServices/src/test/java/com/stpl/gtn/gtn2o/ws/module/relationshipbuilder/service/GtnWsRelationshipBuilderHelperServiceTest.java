/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsRelationshipBuilderHelperServiceTest {
    
    @Mock
    GtnFrameworkEntityMasterBean entitybean = Mockito.mock(GtnFrameworkEntityMasterBean.class);

    @InjectMocks
    @Autowired
    GtnWsRelationshipBuilderHelperService instance;

    public GtnWsRelationshipBuilderHelperServiceTest() {
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
     * Test of getTableNames method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testGetTableNames() {
        System.out.println("getTableNames");

        String expResult = "Table1";
        instance.addTableName(expResult);
        Set<String> result = instance.getTableNames();
        assertFalse(!result.contains(expResult));
    }

    /**
     * Test of addTableName method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAddTableName() {
        System.out.println("addTableName");
        String tableName = "Table1";

        instance.addTableName(tableName);
        Set<String> result = instance.getTableNames();
        assertFalse(!result.contains(tableName));
    }

    /**
     * Test of addColumnName method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAddColumnName() {
        System.out.println("addColumnName");
        String columnName = "Column";

        instance.addColumnName(columnName);
        Set<String> result = instance.getColumnNames();
        assertFalse(!result.contains(columnName));
    }

    /**
     * Test of addTableJoin method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAddTableJoin() {
        System.out.println("addTableJoin");
        GtnFrameworkSelectColumnRelationBean keyBean = new GtnFrameworkSelectColumnRelationBean();
        keyBean.setMappingColumnName("COMPANY_MASTER");
        keyBean.setReferenceTableName("REF_MASTER");
        keyBean.setActualTtableName("ACTUAL_MASTER");
        keyBean.setActualColumnName("ACTUAL_MASTER_SID");

        String expResult = "JOIN REF_MASTER as HELPER_JOIN on HELPER_JOIN.COMPANY_MASTER = ACTUAL_MASTER.ACTUAL_MASTER_SID";
        String result = instance.addTableJoin(keyBean);
        assertEquals(expResult, result.trim());
    }

    /**
     * Test of getInclusionExclusionRulesWithoutBPM method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testGetInclusionExclusionRulesWithoutBPM() {
        try {
            System.out.println("getInclusionExclusionRulesWithoutBPM");

            String hierarchyDefName = instance.executeQuery("select HIERARCHY_NAME from dbo.HIERARCHY_DEFINITION"
                    + " Group by HIERARCHY_NAME ORDER BY HIERARCHY_NAME OFFSET 0 ROWS  FETCH NEXT 1 ROWS ONLY").get(0).toString();
            int expResult = instance.executeQuery("select * from dbo.HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID \n"
                    + "in (select HIERARCHY_DEFINITION_SID from dbo.HIERARCHY_DEFINITION where HIERARCHY_NAME='" + hierarchyDefName + "')").size();
            Map<Integer, String> result = instance.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);
            assertEquals(expResult, result.size());
        } catch (GtnFrameworkGeneralException ex) {
            Logger.getLogger(GtnWsRelationshipBuilderHelperServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRuleName method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAddRuleName() {
        System.out.println("addRuleName");
        String ruleType = "TYPE";
        List<String> rulesName = new ArrayList<>(1);
        String ruleName = "MyRule";

        instance.addRuleName(ruleType, rulesName, ruleName);
        assertEquals(ruleName, rulesName.get(0));
    }

    /**
     * Test of putRuleValuesInmap method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testPutRuleValuesInmap() {
        System.out.println("putRuleValuesInmap");
        List<Object[]> ruleValueList = new ArrayList<>();
        ruleValueList.add(new Object[]{"key1"});
        Map<String, Object> ruleMap = new HashMap<>();

        instance.putRuleValuesInmap(ruleValueList, ruleMap);
        assertEquals(ruleValueList.get(0), ruleMap.get("key1"));

    }

    /**
     * Test of appendAndCondition method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAppendAndCondition() {
        System.out.println("appendAndCondition");
        StringBuilder rules = new StringBuilder("rule1");
        String inclusionOrExclusion = GtnFrameworkWebserviceConstant.EXCLUSION;

        StringBuilder expResult = rules.append(GtnFrameworkWebserviceConstant.AND2);
        StringBuilder result = instance.appendAndCondition(rules, inclusionOrExclusion);
        assertEquals(expResult, result);
    }

    /**
     * Test of executeQuery method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testExecuteQuery() throws Exception {
        System.out.println("executeQuery");
        System.out.println("executeQuery");
        String sqlQuery = "select 1 as result";
        int expResult = 1;
        List result = instance.executeQuery(sqlQuery);
        assertEquals(expResult, Integer.parseInt(result.get(0).toString()));
    }

    /**
     * Test of finderImplInLogic method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testFinderImplInLogic() throws GtnFrameworkGeneralException {
        System.out.println("finderImplInLogic");
//        GtnFrameworkEntityMasterBean entitybean=Mockito.mock(GtnFrameworkEntityMasterBean.class);
        GtnFrameworkSelectColumnRelationBean gtnFrameworkKeyListBean = new GtnFrameworkSelectColumnRelationBean();

        GtnFramworkTableBean tableBean = new GtnFramworkTableBean();
        tableBean.setPrimaryKeyColumn("PrimaryKey");
        gtnFrameworkKeyListBean.setPrimaryKeyColumnName(tableBean.getPrimaryKeyColumn());
        when(entitybean.getKeyRelationBeanUsingTableIdAndColumnName(Mockito.anyString(), Mockito.anyString())).thenReturn(gtnFrameworkKeyListBean);

        String tableName = "Junit_table";
        String columnName = "Column_Junit";
        List hierListValues = Arrays.asList("type", "Category", 1, "( rule rule1 rule3 rule2 HDStPl123rUlE ) ");
        boolean isFirst = false;
        String expResult = "SELECT DISTINCT  TOP 150 null.null,null.PrimaryKey FROM Junit_table WHERE null.PrimaryKey IS NOT NULL  and  (nullrule IN (SELECT null FROM null WHERE PrimaryKey rule1 rule3  ))";
        String result = instance.finderImplInLogic(tableName, columnName, hierListValues, isFirst);
        System.out.println("result = " + result);
        assertEquals(expResult.trim(), result.trim());

        //case 3
        when(entitybean.getKeyRelationBeanUsingTableIdAndColumnName(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        expResult = "SELECT  DISTINCT  TOP 150  Column_Junit FROM Junit_table WHERE Column_Junit IS NOT NULL  and ( rule rule1 rule3 rule2  )";
         result = instance.finderImplInLogic(tableName, columnName, hierListValues, isFirst);
        System.out.println("result = " + result);
        assertEquals(expResult.trim(), result.trim());
        
        //Case 2
        hierListValues = Arrays.asList("type", "Category", 1, "null");
        expResult = " SELECT  DISTINCT  TOP 150  Column_Junit FROM Junit_table WHERE Column_Junit IS NOT NULL";
        result = instance.finderImplInLogic(tableName, columnName, hierListValues, isFirst);
        System.out.println("result 2= " + result);
        assertEquals(expResult.trim(), result.trim());
    }

    /**
     * Test of appendInclusionExclusionConditions method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAppendInclusionExclusionConditions() throws Exception {
        System.out.println("appendInclusionExclusionConditions");
        StringBuilder rules = new StringBuilder();
        Map<String, Object> ruleMap = new HashMap<>();
        ruleMap.put(GtnFrameworkWebserviceConstant.GROUP, new Object[]{1, 2, 3, 4, 5});
        Object[] obj = new Object[18];
        obj[14] = GtnFrameworkWebserviceConstant.GROUP;
        obj[15] = GtnFrameworkWebserviceConstant.GROUP;
        obj[17] = GtnFrameworkWebserviceConstant.GROUP;
        obj[3] = GtnFrameworkWebserviceConstant.GROUP;
        String inclusionOrExclusion = GtnFrameworkWebserviceConstant.EXCLUSION;
        GtnWsRelationshipBuilderHelperService service = Mockito.spy(instance);
        when(service.getGroupRuleList(Mockito.anyString())).thenReturn(Arrays.asList(GtnFrameworkWebserviceConstant.GROUP));
        service.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);
        obj[14] = GtnFrameworkWebserviceConstant.ACTIVE;
        service.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);
    }

    /**
     * Test of getMasterSidList method, of class
     * GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testGetMasterSidList_3args() {
        System.out.println("getMasterSidList");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = new GtnWebServiceSearchCriteria();

        List<String> primaryIdList = Arrays.asList("mhey", "mhey2", "mhey3");
        gtnWebServiceSearchCriteria.setFilterValue3(primaryIdList);
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        List<HierarchyLevelDefinitionBean> hierarchyList = new ArrayList<>();
        HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
        bean.setLevelValueReference("reference");
        hierarchyList.add(bean);
        int levelNo = 2;
        String expResult = "mhey3";
        List<Object> result = instance.getMasterSidList(gtnWsRequest, hierarchyList, levelNo);
        assertEquals(expResult, result.get(0));
    }
/**
	 * Run the GtnWsRelationshipBuilderHelperService() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnWsRelationshipBuilderHelperService_1()
		throws Exception {

		GtnWsRelationshipBuilderHelperService result = new GtnWsRelationshipBuilderHelperService();

		
		assertNotNull(result);
	}

	/**
	 * Run the void addColumnName(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddColumnName_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String columnName = "";

		fixture.addColumnName(columnName);

		
	}

	/**
	 * Run the void addRuleName(String,List<String>,String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddRuleName_4()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String ruleType = "";
		List<String> rulesName = new LinkedList();
		String ruleName = "";

		fixture.addRuleName(ruleType, rulesName, ruleName);


	}

	/**
	 * Run the void addRuleName(String,List<String>,String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddRuleName_5()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String ruleType = "null";
		List<String> rulesName = new LinkedList();
		String ruleName = "";

		fixture.addRuleName(ruleType, rulesName, ruleName);

		
	}

	/**
	 * Run the void addRuleName(String,List<String>,String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddRuleName_6()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String ruleType = "";
		List<String> rulesName = new LinkedList();
		String ruleName = "";

		fixture.addRuleName(ruleType, rulesName, ruleName);

		
	}

	/**
	 * Run the String addTableJoin(GtnFrameworkSelectColumnRelationBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddTableJoin_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		GtnFrameworkSelectColumnRelationBean keyBean = new GtnFrameworkSelectColumnRelationBean();

		String result = fixture.addTableJoin(keyBean);

		
		assertEquals("", result);
	}

	/**
	 * Run the String addTableJoin(GtnFrameworkSelectColumnRelationBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddTableJoin_2()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		GtnFrameworkSelectColumnRelationBean keyBean = new GtnFrameworkSelectColumnRelationBean();

		String result = fixture.addTableJoin(keyBean);

		
		assertEquals("", result);
	}

	/**
	 * Run the String addTableJoin(GtnFrameworkSelectColumnRelationBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddTableJoin_3()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		GtnFrameworkSelectColumnRelationBean keyBean = new GtnFrameworkSelectColumnRelationBean();

		String result = fixture.addTableJoin(keyBean);

		
		assertEquals("", result);
	}

	/**
	 * Run the void addTableName(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAddTableName_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String tableName = "";

		fixture.addTableName(tableName);

		
	}

	/**
	 * Run the StringBuilder appendAndCondition(StringBuilder,String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendAndCondition_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		String inclusionOrExclusion = "Exclusion";

		StringBuilder result = fixture.appendAndCondition(rules, inclusionOrExclusion);

		
		assertNotNull(result);
		assertEquals("", result.toString());
		assertEquals(0, result.length());
		assertEquals(16, result.capacity());
	}

	/**
	 * Run the StringBuilder appendAndCondition(StringBuilder,String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendAndCondition_2()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		String inclusionOrExclusion = "";

		StringBuilder result = fixture.appendAndCondition(rules, inclusionOrExclusion);

		
		assertNotNull(result);
		assertEquals("", result.toString());
		assertEquals(0, result.length());
		assertEquals(16, result.capacity());
	}

	/**
	 * Run the StringBuilder appendAndCondition(StringBuilder,String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendAndCondition_3()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		String inclusionOrExclusion = "";

		StringBuilder result = fixture.appendAndCondition(rules, inclusionOrExclusion);

		
		assertNotNull(result);
		assertEquals("", result.toString());
		assertEquals(0, result.length());
		assertEquals(16, result.capacity());
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_2()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_3()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_4()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_5()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_6()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_7()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_8()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_9()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_10()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_11()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_12()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_13()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_14()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_15()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, new Object()};
		String inclusionOrExclusion = "Exclusion";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}

	/**
	 * Run the void appendInclusionExclusionConditions(StringBuilder,Map<String,Object>,Object[],String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAppendInclusionExclusionConditions_16()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		StringBuilder rules = new StringBuilder();
		Map<String, Object> ruleMap = new HashMap();
		Object[] obj = new Object[] {null, null, null, null, null, null, null, null, null, null, null, null, null, new Object(), null, null, new Object()};
		String inclusionOrExclusion = "";

		fixture.appendInclusionExclusionConditions(rules, ruleMap, obj, inclusionOrExclusion);

		
	}


	/**
	 * Run the Set<String> getColumnNames() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetColumnNames_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");

		Set<String> result = fixture.getColumnNames();

		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the List<String> getGroupRuleList(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
	public void testGetGroupRuleList_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String ruleGroup = "";

		List<String> result = fixture.getGroupRuleList(ruleGroup);

		
		assertNotNull(result);
	}

	/**
	 * Run the List<String> getGroupRuleList(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
	public void testGetGroupRuleList_2()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String ruleGroup = "";

		List<String> result = fixture.getGroupRuleList(ruleGroup);

		
		assertNotNull(result);
	}

	/**
	 * Run the List<String> getGroupRuleList(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test(expected = com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException.class)
	public void testGetGroupRuleList_3()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String ruleGroup = "";

		List<String> result = fixture.getGroupRuleList(ruleGroup);

		
		assertNotNull(result);
	}

	/**
	 * Run the Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetInclusionExclusionRulesWithoutBPM_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String hierarchyDefName = "";

		Map<Integer, String> result = fixture.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetInclusionExclusionRulesWithoutBPM_2()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String hierarchyDefName = "";

		Map<Integer, String> result = fixture.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetInclusionExclusionRulesWithoutBPM_3()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String hierarchyDefName = "";

		Map<Integer, String> result = fixture.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetInclusionExclusionRulesWithoutBPM_4()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String hierarchyDefName = "";

		Map<Integer, String> result = fixture.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetInclusionExclusionRulesWithoutBPM_5()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		String hierarchyDefName = "";

		Map<Integer, String> result = fixture.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}


	/**
	 * Run the Set<String> getTableNames() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetTableNames_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");

		Set<String> result = fixture.getTableNames();

		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the void putRuleValuesInmap(List<?>,Map<String,Object>) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testPutRuleValuesInmap_1()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		List<Object> ruleValueList = new LinkedList();
		Map<String, Object> ruleMap = new HashMap();

		fixture.putRuleValuesInmap(ruleValueList, ruleMap);

		
	}

	/**
	 * Run the void putRuleValuesInmap(List<?>,Map<String,Object>) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testPutRuleValuesInmap_2()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		List<Object> ruleValueList = new LinkedList();
		Map<String, Object> ruleMap = new HashMap();

		fixture.putRuleValuesInmap(ruleValueList, ruleMap);

		
	}

	/**
	 * Run the void putRuleValuesInmap(List<?>,Map<String,Object>) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testPutRuleValuesInmap_3()
		throws Exception {
		GtnWsRelationshipBuilderHelperService fixture = new GtnWsRelationshipBuilderHelperService();
		fixture.addColumnName("");
		fixture.addTableName("");
		List<Object> ruleValueList = null;
		Map<String, Object> ruleMap = new HashMap();

		fixture.putRuleValuesInmap(ruleValueList, ruleMap);

		
	}

}