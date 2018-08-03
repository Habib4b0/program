/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsRelationshipBuilderHelperServiceTest {
    
    @Autowired
     GtnWsRelationshipBuilderHelperService instance ;
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTableNames method, of class GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testGetTableNames() {
        System.out.println("getTableNames");
        
        
        String expResult = "Table1";
        instance.addTableName(expResult);
        Set<String> result = instance.getTableNames();
        assertEquals(expResult, result.iterator().next());
    }

    /**
     * Test of getColumnNames method, of class GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testGetColumnNames() {
        System.out.println("getColumnNames");
        
        String expResult = "Column1";
        instance.addColumnName(expResult);
        Set<String> result = instance.getColumnNames();
        assertEquals(expResult, result.iterator().next());
    }


    /**
     * Test of addTableName method, of class GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAddTableName() {
        System.out.println("addTableName");
        String tableName = "Table1";
        
        instance.addTableName(tableName);
        Set<String> result = instance.getTableNames();
        assertEquals(tableName, result.iterator().next());
    }

    /**
     * Test of addColumnName method, of class GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testAddColumnName() {
        System.out.println("addColumnName");
        String columnName = "Column";
        
        instance.addColumnName(columnName);
        Set<String> result = instance.getColumnNames();
        assertEquals(columnName, result.iterator().next());
    }


    /**
     * Test of addTableJoin method, of class GtnWsRelationshipBuilderHelperService.
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
     * Test of getInclusionExclusionRulesWithoutBPM method, of class GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testGetInclusionExclusionRulesWithoutBPM() {
        try {
            System.out.println("getInclusionExclusionRulesWithoutBPM");
           
            String hierarchyDefName =instance. executeQuery("select HIERARCHY_NAME from dbo.HIERARCHY_DEFINITION"
                    + " Group by HIERARCHY_NAME ORDER BY HIERARCHY_NAME OFFSET 0 ROWS  FETCH NEXT 1 ROWS ONLY").get(0).toString();
            int expResult =instance. executeQuery( "select * from dbo.HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID \n" +
            "in (select HIERARCHY_DEFINITION_SID from dbo.HIERARCHY_DEFINITION where HIERARCHY_NAME='"+hierarchyDefName+"')").size();
            Map<Integer, String> result = instance.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);
            assertEquals(expResult, result.size());
        } catch (GtnFrameworkGeneralException ex) {
            Logger.getLogger(GtnWsRelationshipBuilderHelperServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRuleName method, of class GtnWsRelationshipBuilderHelperService.
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
     * Test of putRuleValuesInmap method, of class GtnWsRelationshipBuilderHelperService.
     */
    @Test
    public void testPutRuleValuesInmap() {
        System.out.println("putRuleValuesInmap");
        List<Object[]> ruleValueList =new ArrayList<>();
        ruleValueList.add(new Object[]{"key1"});
        Map<String, Object> ruleMap = new HashMap<>();
        
        instance.putRuleValuesInmap(ruleValueList, ruleMap);
        assertEquals(ruleValueList.get(0), ruleMap.get("key1"));
       
    }


    /**
     * Test of appendAndCondition method, of class GtnWsRelationshipBuilderHelperService.
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


    
}
