/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsRelationshipBuilderConstants {
	public static final String VALUE_PROPERTY_ID = "value";
	public static final String GTN_RELATIONSHIP_BUILDER_SERVICE = "/GtnRelationshipBuilderService";
	public static final String GET_RELATIONSHIP_BUILDER_TABLE_DATA = "/getRelationshipBuilderTableData";
	public static final String GET_RELATIONSHIP_BUILDER_AVAILABLE_DATA = "/getRelationshipBuilderAvailableTableData";
	public static final String GET_VERSION_NO = "/getVersionNo";
	public static final String CHECK_SAVE_RELATIONSHIP = "/checkSaveRelationship";
	public static final String SAVE_RELATIONSHIP = "/saveRelationship";
	public static final String LOAD_RELATIONSHIP = "/loadRelationship";
	public static final String DELETE_RELATIONSHIP = "/deleteRelationship";
	public static final String ANNUAL = "Annual";
	public static final String SEMI_ANNUAL = "Semi-Annual";
	public static final String QUARTER = "Quarter";
	public static final String MONTHLY = "Month";
	public static final String REPLACE_STRING = "HDStPl123rUlE";
	public static final String COMPANY_MASTER = "COMPANY_MASTER";
	public static final String ITEM_MASTER = "ITEM_MASTER";
	public static final String CONTRACT_MASTER = "CONTRACT_MASTER";
	public static final String LEVEL_NO = "levelNo";
	public static final String BRAND_MASTER = "BRAND_MASTER";
	public static final String CONTRACT = "Contract";
	public static final String PRODUCT = "Product";
	public static final String CUSTOMER = "Customer";
	public static final String GL_COMPANY = "GL Company";
	public static final String TRADING_PARTNER = "Trading Partner";
	public static final String CONTAINS = "contains";
	public static final String CONTRACT_HOLDER = "Contract Holder";
	public static final String GROUP = "Group";
	public static final String EXCLUSION = "Exclusion";
	private static final Map<String, String> CONDITIONS = new HashMap<>();
	private static final Map<String, String> TABLE_PRIMARY_COLUMN = new HashMap<>();
	public static final String TREE_CSS_LAYOUT = "treeCssLayout";
	public static final String ITEM_MASTER_TABLE_COLUMN_NAME = "ITEM_MASTER.ITEM_MASTER_SID";
	public static final String HIERARCHY_FILE_CREATION = "/hierarchyFileCreation";
	public static final String NUMERIC_CONSTANT_ONE = "1";
	public static final String HIERARCHY_LEVELNAME_LIST = "/hierarchyLevelNameList";
	public static final String AUTOBUILDERELATIONSHIP = "/autoBuildRelationShip";
	public static final String HIERARCHY_NAME_LEVEL_VALUE = "/loadHierarchyLevelNameAndValue";

	private GtnWsRelationshipBuilderConstants() {
		/**
		 * empty constructor
		 */
	}

       static {
        CONDITIONS.put("equal to", "=");
        CONDITIONS.put("starts with", "like");
        CONDITIONS.put("ends with", "like");
        CONDITIONS.put("greater than", ">");
        CONDITIONS.put("greater than or equal to", ">=");
        CONDITIONS.put("lesser than", "<");
        CONDITIONS.put("lesser than or equal to", "<=");
        CONDITIONS.put("in", "in");
        CONDITIONS.put("not in", "not in");
        CONDITIONS.put(CONTAINS, "like");
    }
	public static String getConditions(String key) {
		
		return CONDITIONS.get(key);
	}

	public static Map<String, String> getTablePrimaryColumn() {
		return TABLE_PRIMARY_COLUMN;
	}

}
