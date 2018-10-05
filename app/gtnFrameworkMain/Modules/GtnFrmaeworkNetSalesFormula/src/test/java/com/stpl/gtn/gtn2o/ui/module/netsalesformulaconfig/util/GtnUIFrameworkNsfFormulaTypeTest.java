package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnUIFrameworkNsfFormulaTypeTest {
	
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}
	
	@Test
	public void testGetFormulaTypeValue_1()
		throws Exception {
		
		GtnUIFrameworkNsfFormulaType fixture = GtnUIFrameworkNsfFormulaType.getInstance();
		fixture.setFormulaTypeValue("");
		fixture.setChangeAllowed(true);
		String result = fixture.getFormulaTypeValue();
		assertNotNull(result);
	}

	@Test
	public void testGetInstance_1()
		throws Exception {
		
		GtnUIFrameworkNsfFormulaType result = GtnUIFrameworkNsfFormulaType.getInstance();
		assertNotNull(result);
	}

	@Test
	public void testIsChangeAllowed_1()
		throws Exception {
		
		GtnUIFrameworkNsfFormulaType fixture = GtnUIFrameworkNsfFormulaType.getInstance();
		fixture.setFormulaTypeValue("");
		fixture.setChangeAllowed(true);
		boolean result = fixture.isChangeAllowed();
		assertTrue(result);
	}

	@Test
	public void testIsChangeAllowed_2()
		throws Exception {
		
		GtnUIFrameworkNsfFormulaType fixture = GtnUIFrameworkNsfFormulaType.getInstance();
		fixture.setFormulaTypeValue("");
		fixture.setChangeAllowed(true);
		boolean result = fixture.isChangeAllowed();
		assertTrue(result);
	}

	
	@Test
	public void testSetChangeAllowed_1()
		throws Exception {
		
		GtnUIFrameworkNsfFormulaType fixture = GtnUIFrameworkNsfFormulaType.getInstance();
		fixture.setFormulaTypeValue("");
		fixture.setChangeAllowed(true);
		boolean changeAllowed = true;
		fixture.setChangeAllowed(changeAllowed);
	}

	@Test
	public void testSetFormulaTypeValue_1()
		throws Exception {
		
		GtnUIFrameworkNsfFormulaType fixture = GtnUIFrameworkNsfFormulaType.getInstance();
		fixture.setFormulaTypeValue("");
		fixture.setChangeAllowed(true);
		String formulaTypeValue = "";
		fixture.setFormulaTypeValue(formulaTypeValue);
	}

}