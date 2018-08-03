/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants;

import java.util.Arrays;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkRelationshipBuilderConstantsTest {
    
    public GtnFrameworkRelationshipBuilderConstantsTest() {
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
     * Test of getRelationshipBuilderHeader method, of class GtnFrameworkRelationshipBuilderConstants.
     */
    @Test
    public void testGetRelationshipBuilderHeader() {
        System.out.println("getRelationshipBuilderHeader");
        String[] expResult =  Collections
			.unmodifiableList(Arrays.asList("Relationship Name", "Relationship Description", "Relationship Type",
					"Hierarchy Name", "Version No", "Start Date", "Creation Date", "Modified Date", "Created By")).toArray(new String[9]);
        String[] result = GtnFrameworkRelationshipBuilderConstants.getRelationshipBuilderHeader();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getRelationshipBuilderColumn method, of class GtnFrameworkRelationshipBuilderConstants.
     */
    @Test
    
    public void testGetRelationshipBuilderColumn() {
        System.out.println("getRelationshipBuilderColumn");
        String[] expResult = Collections
			.unmodifiableList(Arrays.asList("relationshipName", "relationshipDescription", "relationshipType",
					"hierarchyName", "versionNo", "startDate", "creationDate", "modifiedDate", "createdBy")).toArray(new String[9]);
        String[] result = GtnFrameworkRelationshipBuilderConstants.getRelationshipBuilderColumn();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getCustomListNameArray method, of class GtnFrameworkRelationshipBuilderConstants.
     */
    @Test
    public void testGetCustomListNameArray() {
        System.out.println("getCustomListNameArray");
        String[] expResult = {"USERS" };
        String[] result = GtnFrameworkRelationshipBuilderConstants.getCustomListNameArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getCustomPropertyIds method, of class GtnFrameworkRelationshipBuilderConstants.
     */
    @Test
    public void testGetCustomPropertyIds() {
        System.out.println("getCustomPropertyIds");
        String[] expResult = {"createdBy"};
        String[] result = GtnFrameworkRelationshipBuilderConstants.getCustomPropertyIds();
        assertArrayEquals(expResult, result);
    }
    
}
