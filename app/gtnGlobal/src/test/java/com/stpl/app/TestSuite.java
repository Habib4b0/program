/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app;


import com.stpl.app.global.abstractsearch.util.ValidationUtilTest;
import com.stpl.app.global.cfp.util.CommonUtilsTest;
import com.stpl.app.global.common.ui.lookup.FormulaLookupTest;
import com.stpl.app.global.common.util.CommonUtilTest;
import com.stpl.app.global.company.logic.CompanySearchLogicTest;
import com.stpl.app.global.company.ui.layout.ParentCompanyNoButtonLayoutTest;
import com.stpl.app.unittest.testcases.CompanyFilterGeneratorTest;
import com.stpl.app.util.ErrorCodeUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 *
 * @author manikandaprabu.g
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CompanyFilterGeneratorTest.class,CompanySearchLogicTest.class,ParentCompanyNoButtonLayoutTest.class,
    CommonUtilsTest.class,ErrorCodeUtilTest.class,ValidationUtilTest.class,FormulaLookupTest.class,CommonUtilTest.class})
public class TestSuite {
    
}
