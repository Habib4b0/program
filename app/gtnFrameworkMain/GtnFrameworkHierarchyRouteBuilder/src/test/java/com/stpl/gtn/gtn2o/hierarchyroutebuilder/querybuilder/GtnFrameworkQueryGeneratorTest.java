package com.stpl.gtn.gtn2o.hierarchyroutebuilder.querybuilder;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.GtnFrameworkHierarchyQueryGenerator;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkHierarchyRouteBuilder-test.xml" })
@Ignore
public class GtnFrameworkQueryGeneratorTest {
	@Autowired
	GtnFrameworkHierarchyQueryGenerator queryGenerator;
	Map<String, String> queryMap = new HashMap<>();

	@Before
	public void inintMap() {
		queryMap.put("generateQuery",
				"SELECT  DISTINCT $SelectMasterSid as MasterSid,? as RELATIONSHIP_BUILDER_SID,? as HIERARCHY_LEVEL_DEFINITION_SID,? as LEVEL_NO,? as LEVEL_NAME,$ParentNode as PARENT_NODE,$SelectHierarchyNo as HIERARCHY_NO,'F' as FLAG,? as CREATED_BY,getdate() as CREATED_DATE,? as MODIFIED_BY,getdate() as MODIFIED_DATE,? as VERSION_NO,$SelectHierarchyNo as PARENT_HIERARCHY_NO,1 as NEETTOINSERT FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN #SELECTED_RElATION_SHIP RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnPreviousLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationPreviousLevel JOIN #SELECTED_RElATION_SHIP RELATION_DATE_FILTER ON RELATION_DATE_FILTER.RELATIONSHIP_BUILDER_SID = RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID AND RELATION_DATE_FILTER.LEVEL_NO = ? AND RELATION_DATE_FILTER.VERSION_NO = ? AND (CONTRACT_MASTER.MODIFIED_DATE >= RELATION_DATE_FILTER.MODIFIED_DATE OR RELATION_DATE_FILTER.HIERARCHY_NO LIKE ?) LEFT JOIN #SELECTED_RElATION_SHIP USERDEFINED_RELATION_JOIN ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = USERDEFINED_RELATION_JOIN.RELATIONSHIP_BUILDER_SID AND USERDEFINED_RELATION_JOIN.LEVEL_NO = ? AND USERDEFINED_RELATION_JOIN.VERSION_NO = ? AND USERDEFINED_RELATION_JOIN.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel");
		queryMap.put("generateQuery1",
				"SELECT  DISTINCT PARENT_RELATION.RELATIONSHIP_LEVEL_VALUES as parent_relationrelationship_level_values,PARENT_RELATION.LEVEL_NO as parent_relationlevel_no,PARENT_RELATION.PARENT_NODE as parent_relationparent_node,PARENT_RELATION.RELATIONSHIP_LEVEL_SID as parent_relationrelationship_level_sid,PARENT_RELATION.HIERARCHY_NO as parent_relationhierarchy_no,PARENT_RELATION.RELATIONSHIP_BUILDER_SID as parent_relationrelationship_builder_sid FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel JOIN RELATIONSHIP_LEVEL_DEFINITION PARENT_RELATION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = PARENT_RELATION.RELATIONSHIP_BUILDER_SID AND PARENT_RELATION.VERSION_NO = ? AND PARENT_RELATION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE PARENT_RELATION.HIERARCHY_NO + '%'");
		queryMap.put("generateQuery2",
				"SELECT  DISTINCT $SelectColumn as selectColumn,$SelectMasterSid as selectMasterSid,$SelectCheckHierarchyNo as SelectCheckHierarchyNo FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN #SELECTED_RElATION_SHIP RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnPreviousLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationPreviousLevel JOIN #SELECTED_RElATION_SHIP RELATION_DATE_FILTER ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = RELATION_DATE_FILTER.RELATIONSHIP_BUILDER_SID AND RELATION_DATE_FILTER.LEVEL_NO = ? AND RELATION_DATE_FILTER.VERSION_NO = ? AND (CONTRACT_MASTER.MODIFIED_DATE >= RELATION_DATE_FILTER.MODIFIED_DATE OR RELATION_DATE_FILTER.HIERARCHY_NO LIKE ?)");
		queryMap.put("generateQuery3",
				"SELECT  DISTINCT $SelectColumn as selectColumn,$SelectMasterSid as selectMasterSid FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN CCP_DETAILS CCP_DETAILS ON CCP_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID AND CCP_DETAILS.CONTRACT_MASTER_SID = CONTRACT_MASTER.CONTRACT_MASTER_SID JOIN ST_CCP_HIERARCHY ST_CCP_HIERARCHY ON ST_CCP_HIERARCHY.CCP_DETAILS_SID = CCP_DETAILS.CCP_DETAILS_SID");
		queryMap.put("generateQuery4",
				"SELECT  DISTINCT $SelectColumn as selectColumn,$SelectMasterSid as selectMasterSid FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN CCP_DETAILS CCP_DETAILS ON CCP_DETAILS.ITEM_MASTER_SID = ITEM_MASTER.ITEM_MASTER_SID JOIN ST_CCP_HIERARCHY ST_CCP_HIERARCHY ON ST_CCP_HIERARCHY.CCP_DETAILS_SID = CCP_DETAILS.CCP_DETAILS_SID");
		queryMap.put("generateQuery5",
				"SELECT  DISTINCT $SelectMasterSid as MasterSid,? as RELATIONSHIP_BUILDER_SID,? as HIERARCHY_LEVEL_DEFINITION_SID,? as LEVEL_NO,? as LEVEL_NAME,$ParentNode as PARENT_NODE,$HierarchyNoFormationForDeduction as HIERARCHY_NO,'F' as FLAG,? as CREATED_BY,getdate() as CREATED_DATE,? as MODIFIED_BY,getdate() as MODIFIED_DATE,? as VERSION_NO,$HierarchyNoFormationForDeduction as PARENT_HIERARCHY_NO,1 as NEETTOINSERT FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RS_CONTRACT_DETAILS RS_CONTRACT_DETAILS ON RS_CONTRACT.RS_CONTRACT_SID = RS_CONTRACT_DETAILS.RS_CONTRACT_SID WHERE RS_CONTRACT_DETAILS.ITEM_MASTER_SID in (?)");
		queryMap.put("generateQuery6",
				"SELECT  DISTINCT  FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON ");
		queryMap.put("generateQuery7",
				"SELECT  DISTINCT PARENT_RELATION.RELATIONSHIP_LEVEL_VALUES as parent_relationrelationship_level_values,PARENT_RELATION.LEVEL_NO as parent_relationlevel_no,PARENT_RELATION.PARENT_NODE as parent_relationparent_node,PARENT_RELATION.RELATIONSHIP_LEVEL_SID as parent_relationrelationship_level_sid,PARENT_RELATION.HIERARCHY_NO as parent_relationhierarchy_no,PARENT_RELATION.RELATIONSHIP_BUILDER_SID as parent_relationrelationship_builder_sid,STr.DESCRIPTION as strdescription,FORm.DESCRIPTION as formdescription FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel JOIN RELATIONSHIP_LEVEL_DEFINITION PARENT_RELATION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = PARENT_RELATION.RELATIONSHIP_BUILDER_SID AND PARENT_RELATION.VERSION_NO = ? AND PARENT_RELATION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE PARENT_RELATION.HIERARCHY_NO + '%' JOIN CCP_DETAILS CCP_DETAILS ON ITEM_MASTER.ITEM_MASTER_SID = CCP_DETAILS.ITEM_MASTER_SID JOIN HELPER_TABLE STR ON STr.HELPER_TABLE_SID = ITEM_MASTER.STRENGTH JOIN HELPER_TABLE FORM ON FORm.HELPER_TABLE_SID = ITEM_MASTER.FORM");
		queryMap.put("generateQuery8",
				"SELECT  DISTINCT PARENT_RELATION.RELATIONSHIP_LEVEL_VALUES as parent_relationrelationship_level_values,PARENT_RELATION.LEVEL_NO as parent_relationlevel_no,PARENT_RELATION.PARENT_NODE as parent_relationparent_node,PARENT_RELATION.RELATIONSHIP_LEVEL_SID as parent_relationrelationship_level_sid,PARENT_RELATION.HIERARCHY_NO as parent_relationhierarchy_no,PARENT_RELATION.RELATIONSHIP_BUILDER_SID as parent_relationrelationship_builder_sid FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel JOIN RELATIONSHIP_LEVEL_DEFINITION PARENT_RELATION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = PARENT_RELATION.RELATIONSHIP_BUILDER_SID AND PARENT_RELATION.VERSION_NO = ? AND PARENT_RELATION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE PARENT_RELATION.HIERARCHY_NO + '%' JOIN CCP_DETAILS CCP_DETAILS ON ITEM_MASTER.ITEM_MASTER_SID = CCP_DETAILS.ITEM_MASTER_SID");
		queryMap.put("generateQuery9",
				"SELECT  DISTINCT $SelectColumn as selectColumn,$SelectMasterSid as selectMasterSid,$HierarchyNoFormationForDeduction as SelectCheckHierarchyNo FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RS_CONTRACT_DETAILS RS_CONTRACT_DETAILS ON RS_CONTRACT.RS_CONTRACT_SID = RS_CONTRACT_DETAILS.RS_CONTRACT_SID");
		queryMap.put("generateQuery10",
				"SELECT  DISTINCT COMPANY_MASTER.COMPANY_MASTER_SID as COMPANY_MASTER_SID,CONTRACT_MASTER.CONTRACT_MASTER_SID as CONTRACT_MASTER_SID,RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO as HIERARCHY_NO FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ?");
		queryMap.put("generateQuery11",
				"SELECT  DISTINCT COMPANY_MASTER.COMPANY_MASTER_SID as COMPANY_MASTER_SID,CONTRACT_MASTER.CONTRACT_MASTER_SID as CONTRACT_MASTER_SID,RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO as HIERARCHY_NO FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ?");
		queryMap.put("generateQuery12",
				"SELECT  DISTINCT COMPANY_MASTER.COMPANY_MASTER_SID as COMPANY_MASTER_SID,CONTRACT_MASTER.CONTRACT_MASTER_SID as CONTRACT_MASTER_SID FROM CONTRACT_MASTER AS CONTRACT_MASTER");
		queryMap.put("generateQuery13",
				"SELECT  DISTINCT HIERARCHY_NO_JOIN.LEVEL_NO as hierarchy_no_joinlevel_no,HIERARCHY_NO_JOIN.RELATIONSHIP_LEVEL_VALUES as hierarchy_no_joinrelationship_level_values,HIERARCHY_NO_JOIN.PARENT_NODE as hierarchy_no_joinparent_node,HIERARCHY_NO_JOIN.LEVEL_NAME as hierarchy_no_joinlevel_name,HIERARCHY_LEVEL_DEFINITION.LEVEL_VALUE_REFERENCE as hierarchy_level_definitionlevel_value_reference,HIERARCHY_LEVEL_DEFINITION.TABLE_NAME as hierarchy_level_definitiontable_name,HIERARCHY_LEVEL_DEFINITION.FIELD_NAME as hierarchy_level_definitionfield_name,HIERARCHY_NO_JOIN.RELATIONSHIP_LEVEL_SID as hierarchy_no_joinrelationship_level_sid,HIERARCHY_NO_JOIN.HIERARCHY_NO as hierarchy_no_joinhierarchy_no,HIERARCHY_NO_JOIN.RELATIONSHIP_BUILDER_SID as hierarchy_no_joinrelationship_builder_sid,HIERARCHY_LEVEL_DEFINITION.HIERARCHY_LEVEL_DEFINITION_SID as hierarchy_level_sid,HIERARCHY_LEVEL_DEFINITION.HIERARCHY_DEFINITION_SID as HIERARCHY_DEFINITION_SID,HIERARCHY_LEVEL_DEFINITION.VERSION_NO as Hierarchy_version_no FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE ? AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ? JOIN RELATIONSHIP_LEVEL_DEFINITION HIERARCHY_NO_JOIN ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = HIERARCHY_NO_JOIN.RELATIONSHIP_BUILDER_SID AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE HIERARCHY_NO_JOIN.HIERARCHY_NO + '%' AND HIERARCHY_NO_JOIN.VERSION_NO = ? AND HIERARCHY_NO_JOIN.LEVEL_NO < ? AND HIERARCHY_NO_JOIN.HIERARCHY_NO not in (?) JOIN HIERARCHY_LEVEL_DEFINITION HIERARCHY_LEVEL_DEFINITION ON HIERARCHY_LEVEL_DEFINITION.HIERARCHY_LEVEL_DEFINITION_SID = HIERARCHY_NO_JOIN.HIERARCHY_LEVEL_DEFINITION_SID");
		queryMap.put("generateQuery14",
				"SELECT  DISTINCT HIERARCHY_NO_JOIN.HIERARCHY_NO as hierarchy_no_joinhierarchy_no FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values = $WhereClauseColumnCurrentLevel AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = ? AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE ? AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE $HierachyNoFormationCurrentLevel JOIN RELATIONSHIP_LEVEL_DEFINITION HIERARCHY_NO_JOIN ON RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = HIERARCHY_NO_JOIN.RELATIONSHIP_BUILDER_SID AND RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE HIERARCHY_NO_JOIN.HIERARCHY_NO + '%' AND HIERARCHY_NO_JOIN.VERSION_NO = ? AND HIERARCHY_NO_JOIN.LEVEL_NO < ? AND HIERARCHY_NO_JOIN.HIERARCHY_NO not in (?)");
	}

	@Test
	public void generateQuery() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("AUTOMATIC_INSERT", queryBean);
			String generatrQuery = queryBean.generateQuery();
			Assert.assertEquals(queryMap.get("generateQuery"), generatrQuery);
			System.out.println(queryBean.generateQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery1() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("LOAD_AVAILABLE_TABLE", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery2"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery2() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("CHECK_AUTO_UPDATE", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery2"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery3() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("LOAD_DISCOUNT", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery3"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery4() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("LOAD_DISCOUNT_PRODUCT", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery4"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery5() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("AUTO_UPDATE_DEDUCTION", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery5"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery6() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("AUTO_UPDATE_DEDUCTION_PRODUCT", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery6"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery7() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("LOAD_AVAILABLE_TABLE_FOR_NDC", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery7"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery8() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("LOAD_AVAILABLE_TABLE_PRODUCT", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery8"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery9() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("CHECK_AUTO_UPDATE_DEDUCTION", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery9"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery10() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("CCP_INSERT_CUSTOMER", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery10"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery11() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("CCP_INSERT_PRODUCT", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery11"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery12() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("CCP_PRODUCT_CUSTOMER", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery12"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery13() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("LOAD_SELECTED_PRODUCT", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery13"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void generateQuery14() {
		try {
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			queryBean.setFromTableName("CONTRACT_MASTER");
			queryBean.setFromTableAlies("CONTRACT_MASTER");
			queryGenerator.generateQuery("LOAD_SELECTED_CUSTOMER", queryBean);
			String generatrQuery = queryBean.generateQuery();
			System.out.println(queryBean.generateQuery());
			Assert.assertEquals(queryMap.get("generateQuery14"), generatrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
