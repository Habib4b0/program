/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRelationshipBuilderConstants {
	public static final String RELATIONSHIP_BUILDER_SCREEN_SEARCH = "RBSearch";
	public static final String RELATIONSHIP_BUILDER_SCREEN_CRUD = "RB001";
	private static final List<String> RELATIONSHIP_BUILDER_HEADER = Collections
			.unmodifiableList(Arrays.asList("Relationship Name", "Relationship Description", "Relationship Type",
					"Hierarchy Name", "Version No", "Start Date", "Creation Date", "Modified Date", "Created By"));
	private static final List<String> RELATIONSHIP_BUILDER_COLUMN = Collections
			.unmodifiableList(Arrays.asList("relationshipName", "relationshipDescription", "relationshipType",
					"hierarchyName", "versionNo", "startDate", "creationDate", "modifiedDate", "createdBy"));
	public static final String AVAILABLE_LIST_COLUMNID = "value";
	public static final String LEVEL_PANEL = "levelPanel";
	public static final String CREATED_BY = "createdBy";
	public static final String USERS = "USERS";
	public static final String CUSTOMFILTERCOMBOBOX = "customFilterComboBox";

	private GtnFrameworkRelationshipBuilderConstants() {

	}

	public static String[] getRelationshipBuilderHeader() {
		return RELATIONSHIP_BUILDER_HEADER.toArray(new String[RELATIONSHIP_BUILDER_HEADER.size()]);
	}

	public static String[] getRelationshipBuilderColumn() {
		return RELATIONSHIP_BUILDER_COLUMN.toArray(new String[RELATIONSHIP_BUILDER_COLUMN.size()]);
	}

	private static final String[] CUSTOM_PROPERTY_IDS = { CREATED_BY };
	private static final String[] CUSTOM_LIST_NAME_ARRAY = { USERS };

	public static String[] getCustomListNameArray() {
		return CUSTOM_LIST_NAME_ARRAY.clone();
	}

	public static String[] getCustomPropertyIds() {
		return CUSTOM_PROPERTY_IDS.clone();
	}
        
}
