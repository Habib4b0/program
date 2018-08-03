/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.ConfigSuite.class, com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.GtnFrameworkRelationshipBuilderTest.class, com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants.ConstantsSuite.class, com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.dynamicclasses.DynamicclassesSuite.class})
public class RelationshipbuilderSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
