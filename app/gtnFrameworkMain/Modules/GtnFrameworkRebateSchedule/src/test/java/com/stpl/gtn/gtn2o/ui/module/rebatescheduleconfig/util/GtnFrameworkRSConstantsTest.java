package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class GtnFrameworkRSConstantsTest {
	
	@Test
	public void testGeRsLookUpHeader_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.geRsLookUpHeader();

		assertNotNull(result);
		assertEquals(12, result.length);
		assertEquals("System ID", result[0]);
		assertEquals("Rebate Schedule ID", result[1]);
		assertEquals("Rebate Schedule No", result[2]);
		assertEquals("Rebate Schedule Name", result[3]);
		assertEquals("Rebate Schedule Type", result[4]);
		assertEquals("Rebate Schedule Status", result[5]);
		assertEquals("Rebate Schedule Category", result[6]);
		assertEquals("Start Date", result[7]);
		assertEquals("End Date", result[8]);
		assertEquals("Rebate Schedule Designation", result[9]);
		assertEquals("Parent ID", result[10]);
		assertEquals("Parent Name", result[11]);
	}

	
	@Test
	public void testGetAddEditEnableField_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getAddEditEnableField();

		assertNotNull(result);
		assertEquals(47, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
		assertNotNull(result[15]);
		assertNotNull(result[16]);
		assertNotNull(result[17]);
		assertNotNull(result[18]);
		assertNotNull(result[19]);
		assertNotNull(result[20]);
		assertNotNull(result[21]);
		assertNotNull(result[22]);
		assertNotNull(result[23]);
		assertNotNull(result[24]);
		assertNotNull(result[25]);
		assertNotNull(result[26]);
		assertNotNull(result[27]);
		assertNotNull(result[28]);
		assertNotNull(result[29]);
		assertNotNull(result[30]);
		assertNotNull(result[31]);
		assertNotNull(result[32]);
		assertNotNull(result[33]);
		assertNotNull(result[34]);
		assertNotNull(result[35]);
		assertNotNull(result[36]);
		assertNotNull(result[37]);
		assertNotNull(result[38]);
		assertNotNull(result[39]);
		assertNotNull(result[40]);
		assertNotNull(result[41]);
		assertNotNull(result[42]);
		assertNotNull(result[43]);
		assertNotNull(result[44]);
		assertNotNull(result[45]);
		assertNotNull(result[46]);
	}

	
	@Test
	public void testGetAddEnableField_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getAddEnableField();

		assertNotNull(result);
		assertEquals(47, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
		assertNotNull(result[15]);
		assertNotNull(result[16]);
		assertNotNull(result[17]);
		assertNotNull(result[18]);
		assertNotNull(result[19]);
		assertNotNull(result[20]);
		assertNotNull(result[21]);
		assertNotNull(result[22]);
		assertNotNull(result[23]);
		assertNotNull(result[24]);
		assertNotNull(result[25]);
		assertNotNull(result[26]);
		assertNotNull(result[27]);
		assertNotNull(result[28]);
		assertNotNull(result[29]);
		assertNotNull(result[30]);
		assertNotNull(result[31]);
		assertNotNull(result[32]);
		assertNotNull(result[33]);
		assertNotNull(result[34]);
		assertNotNull(result[35]);
		assertNotNull(result[36]);
		assertNotNull(result[37]);
		assertNotNull(result[38]);
		assertNotNull(result[39]);
		assertNotNull(result[40]);
		assertNotNull(result[41]);
		assertNotNull(result[42]);
		assertNotNull(result[43]);
		assertNotNull(result[44]);
		assertNotNull(result[45]);
		assertNotNull(result[46]);
	}

	
	@Test
	public void testGetAddViewDisableField_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getAddViewDisableField();

		assertNotNull(result);
		assertEquals(53, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
		assertNotNull(result[15]);
		assertNotNull(result[16]);
		assertNotNull(result[17]);
		assertNotNull(result[18]);
		assertNotNull(result[19]);
		assertNotNull(result[20]);
		assertNotNull(result[21]);
		assertNotNull(result[22]);
		assertNotNull(result[23]);
		assertNotNull(result[24]);
		assertNotNull(result[25]);
		assertNotNull(result[26]);
		assertNotNull(result[27]);
		assertNotNull(result[28]);
		assertNotNull(result[29]);
		assertNotNull(result[30]);
		assertNotNull(result[31]);
		assertNotNull(result[32]);
		assertNotNull(result[33]);
		assertNotNull(result[34]);
		assertNotNull(result[35]);
		assertNotNull(result[36]);
		assertNotNull(result[37]);
		assertNotNull(result[38]);
		assertNotNull(result[39]);
		assertNotNull(result[40]);
		assertNotNull(result[41]);
		assertNotNull(result[42]);
		assertNotNull(result[43]);
		assertNotNull(result[44]);
		assertNotNull(result[45]);
		assertNotNull(result[46]);
		assertNotNull(result[47]);
		assertNotNull(result[48]);
		assertNotNull(result[49]);
		assertNotNull(result[50]);
		assertNotNull(result[51]);
		assertNotNull(result[52]);
	}

	
	@Test
	public void testGetAddViewVisibleFields_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getAddViewVisibleFields();

		assertNotNull(result);
		assertEquals(5, result.length);
		assertEquals("rsAddSaveButton", result[0]);
		assertEquals("rebateScheduleAddViewAAddResetButton", result[1]);
		assertEquals("priceScheduleAddViewAAddDeleteButton", result[2]);
		assertEquals("RSItemAdditionInformationLayout", result[3]);
		assertEquals("rebateSetupmassUpdatePanelLayout", result[4]);
	}


	@Test
	public void testGetDateFieldProperties_1()
		throws Exception {

		List<String> result = GtnFrameworkRSConstants.getDateFieldProperties();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains("rsStartDate"));
		assertTrue(result.contains("rsEndDate"));
	}

	
	@Test
	public void testGetListNameArray_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getListNameArray();

		assertNotNull(result);
		assertEquals(23, result.length);
		assertEquals("RS_TYPE", result[0]);
		assertEquals("STATUS", result[1]);
		assertEquals("REBATE_PROGRAM_TYPE", result[2]);
		assertEquals("RS_CATEGORY", result[3]);
		assertEquals("RS_TRADE_CLASS", result[4]);
		assertEquals("REBATE_FREQUENCY", result[5]);
		assertEquals("RS_CALENDAR", result[6]);
		assertEquals("CALCULATION_TYPE", result[7]);
		assertEquals("REBATE_PLAN_LEVEL", result[8]);
		assertEquals("REBATE_RULE_TYPE", result[9]);
		assertEquals("Payment_Level", result[10]);
		assertEquals("PAYMENT_TERMS", result[11]);
		assertEquals("PAYMENT_METHOD", result[12]);
		assertEquals("PAYMENT_FREQUENCY", result[13]);
		assertEquals("INTEREST_BEARING_INDICATOR", result[14]);
		assertEquals("INTEREST_BEARING_BASIS", result[15]);
		assertEquals("RS_DESIGNATION", result[16]);
		assertEquals("RS_UDC1", result[17]);
		assertEquals("RS_UDC2", result[18]);
		assertEquals("RS_UDC3", result[19]);
		assertEquals("RS_UDC4", result[20]);
		assertEquals("RS_UDC5", result[21]);
		assertEquals("RS_UDC6", result[22]);
	}

	
	@Test
	public void testGetNonEditableList_1()
		throws Exception {

		List<String> result = GtnFrameworkRSConstants.getNonEditableList();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains("formulaName"));
		assertTrue(result.contains("rebatePlanName"));
	}

	
	@Test
	public void testGetNsFilterPropertyIds_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getNsFilterPropertyIds();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals("formulaType", result[0]);
		assertEquals("createdBy", result[1]);
		assertEquals("modifiedBy", result[2]);
	}

	
	@Test
	public void testGetNsListNameArray_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getNsListNameArray();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals("NS_FORMULA_TYPE", result[0]);
		assertEquals("userIdName", result[1]);
		assertEquals("userIdName", result[2]);
	}


	@Test
	public void testGetPagedTableEnableField_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getPagedTableEnableField();

		assertNotNull(result);
		assertEquals(46, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
		assertNotNull(result[15]);
		assertNotNull(result[16]);
		assertNotNull(result[17]);
		assertNotNull(result[18]);
		assertNotNull(result[19]);
		assertNotNull(result[20]);
		assertNotNull(result[21]);
		assertNotNull(result[22]);
		assertNotNull(result[23]);
		assertNotNull(result[24]);
		assertNotNull(result[25]);
		assertNotNull(result[26]);
		assertNotNull(result[27]);
		assertNotNull(result[28]);
		assertNotNull(result[29]);
		assertNotNull(result[30]);
		assertNotNull(result[31]);
		assertNotNull(result[32]);
		assertNotNull(result[33]);
		assertNotNull(result[34]);
		assertNotNull(result[35]);
		assertNotNull(result[36]);
		assertNotNull(result[37]);
		assertNotNull(result[38]);
		assertNotNull(result[39]);
		assertNotNull(result[40]);
		assertNotNull(result[41]);
		assertNotNull(result[42]);
		assertNotNull(result[43]);
		assertNotNull(result[44]);
		assertNotNull(result[45]);
	}

	
	@Test
	public void testGetPagedTableVisibleFields_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getPagedTableVisibleFields();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals("rsAddSaveButton", result[0]);
		assertEquals("rebateScheduleAddViewAAddResetButton", result[1]);
		assertEquals("priceScheduleAddViewAAddDeleteButton", result[2]);
	}

	@Test
	public void testGetPopUpTextFieldProperties_1()
		throws Exception {

		List<String> result = GtnFrameworkRSConstants.getPopUpTextFieldProperties();

		assertNotNull(result);
		assertEquals(7, result.size());
		assertTrue(result.contains("deductionName"));
		assertTrue(result.contains("evaluationRuleName"));
		assertTrue(result.contains("calculationRuleName"));
		assertTrue(result.contains("netSalesRuleName"));
		assertTrue(result.contains("formulaNo"));
		assertTrue(result.contains("netSalesFormulaName"));
		assertTrue(result.contains("rebatePlanNo"));
	}

	@Test
	public void testGetPropertyIds_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getPropertyIds();

		assertNotNull(result);
		assertEquals(23, result.length);
		assertEquals("rebateScheduleType", result[0]);
		assertEquals("rebateScheduleStatus", result[1]);
		assertEquals("rebateScheduleProgramType", result[2]);
		assertEquals("rebateScheduleCategory", result[3]);
		assertEquals("rebateScheduleTradeClass", result[4]);
		assertEquals("rebateScheduleFrequency", result[5]);
		assertEquals("rebateCalendar", result[6]);
		assertEquals("rebateCalculationType", result[7]);
		assertEquals("rebateCalculationLevel", result[8]);
		assertEquals("rebateRuleType", result[9]);
		assertEquals("rebatePaymentLevel", result[10]);
		assertEquals("paymentTerms", result[11]);
		assertEquals("paymentMethod", result[12]);
		assertEquals("paymentFrequency", result[13]);
		assertEquals("interestBearingIndicator", result[14]);
		assertEquals("interestBearingBasis", result[15]);
		assertEquals("rebateScheduleDesignation", result[16]);
		assertEquals("rsUDC1", result[17]);
		assertEquals("rsUDC2", result[18]);
		assertEquals("rsUDC3", result[19]);
		assertEquals("rsUDC4", result[20]);
		assertEquals("rsUDC5", result[21]);
		assertEquals("rsUDC6", result[22]);
	}

	
	@Test
	public void testGetRpListNameArray_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRpListNameArray();

		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals("RULE_TYPE", result[0]);
		assertEquals("RULE_CATEGORY", result[1]);
		assertEquals("userIdName", result[2]);
		assertEquals("userIdName", result[3]);
	}

	
	@Test
	public void testGetRpPopupPropertyIds_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRpPopupPropertyIds();

		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals("ruleType", result[0]);
		assertEquals("ruleCategory", result[1]);
		assertEquals("createdBy", result[2]);
		assertEquals("modifiedBy", result[3]);
	}

	@Test
	public void testGetRsLookUpColumn_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsLookUpColumn();

		assertNotNull(result);
		assertEquals(12, result.length);
		assertEquals("systemId", result[0]);
		assertEquals("rsId", result[1]);
		assertEquals("rsNo", result[2]);
		assertEquals("rsName", result[3]);
		assertEquals("rsType", result[4]);
		assertEquals("rsStatus", result[5]);
		assertEquals("rsCategory", result[6]);
		assertEquals("startDate", result[7]);
		assertEquals("endDate", result[8]);
		assertEquals("rsDesignation", result[9]);
		assertEquals("parentRsId", result[10]);
		assertEquals("parentRsName", result[11]);
	}

	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetRsLookUpColumnType_1()
		throws Exception {

		Class<Object>[] result = (Class<Object>[]) GtnFrameworkRSConstants.getRsLookUpColumnType();

		assertNotNull(result);
		assertEquals(12, result.length);
		assertNotNull(result[0]);
		assertEquals("class java.lang.String", result[0].toString());
		assertEquals(17, result[0].getModifiers());
		assertEquals(false, result[0].isInterface());
		assertEquals(false, result[0].isArray());
		assertEquals(false, result[0].isPrimitive());
		assertEquals(null, result[0].getComponentType());
		assertEquals("java.lang.String", result[0].getName());
		assertEquals(false, result[0].desiredAssertionStatus());
		assertEquals("java.lang.String", result[0].getCanonicalName());
		assertEquals(null, result[0].getClassLoader());
		assertEquals(null, result[0].getDeclaringClass());
		assertEquals(null, result[0].getEnclosingClass());
		assertEquals(null, result[0].getEnclosingConstructor());
		assertEquals(null, result[0].getEnclosingMethod());
		assertEquals(null, result[0].getEnumConstants());
		assertEquals(null, result[0].getSigners());
		assertEquals("String", result[0].getSimpleName());
		assertEquals("java.lang.String", result[0].getTypeName());
		assertEquals(false, result[0].isAnnotation());
		assertEquals(false, result[0].isAnonymousClass());
		assertEquals(false, result[0].isEnum());
		assertEquals(false, result[0].isLocalClass());
		assertEquals(false, result[0].isMemberClass());
		assertEquals(false, result[0].isSynthetic());
		assertEquals("", result[0].newInstance());
		assertEquals("public final class java.lang.String", result[0].toGenericString());
		assertNotNull(result[1]);
		assertEquals("class java.lang.String", result[1].toString());
		assertEquals(17, result[1].getModifiers());
		assertEquals(false, result[1].isInterface());
		assertEquals(false, result[1].isArray());
		assertEquals(false, result[1].isPrimitive());
		assertEquals(null, result[1].getComponentType());
		assertEquals("java.lang.String", result[1].getName());
		assertEquals(false, result[1].desiredAssertionStatus());
		assertEquals("java.lang.String", result[1].getCanonicalName());
		assertEquals(null, result[1].getClassLoader());
		assertEquals(null, result[1].getDeclaringClass());
		assertEquals(null, result[1].getEnclosingClass());
		assertEquals(null, result[1].getEnclosingConstructor());
		assertEquals(null, result[1].getEnclosingMethod());
		assertEquals(null, result[1].getEnumConstants());
		assertEquals(null, result[1].getSigners());
		assertEquals("String", result[1].getSimpleName());
		assertEquals("java.lang.String", result[1].getTypeName());
		assertEquals(false, result[1].isAnnotation());
		assertEquals(false, result[1].isAnonymousClass());
		assertEquals(false, result[1].isEnum());
		assertEquals(false, result[1].isLocalClass());
		assertEquals(false, result[1].isMemberClass());
		assertEquals(false, result[1].isSynthetic());
		assertEquals("", result[1].newInstance());
		assertEquals("public final class java.lang.String", result[1].toGenericString());
		assertNotNull(result[2]);
		assertEquals("class java.lang.String", result[2].toString());
		assertEquals(17, result[2].getModifiers());
		assertEquals(false, result[2].isInterface());
		assertEquals(false, result[2].isArray());
		assertEquals(false, result[2].isPrimitive());
		assertEquals(null, result[2].getComponentType());
		assertEquals("java.lang.String", result[2].getName());
		assertEquals(false, result[2].desiredAssertionStatus());
		assertEquals("java.lang.String", result[2].getCanonicalName());
		assertEquals(null, result[2].getClassLoader());
		assertEquals(null, result[2].getDeclaringClass());
		assertEquals(null, result[2].getEnclosingClass());
		assertEquals(null, result[2].getEnclosingConstructor());
		assertEquals(null, result[2].getEnclosingMethod());
		assertEquals(null, result[2].getEnumConstants());
		assertEquals(null, result[2].getSigners());
		assertEquals("String", result[2].getSimpleName());
		assertEquals("java.lang.String", result[2].getTypeName());
		assertEquals(false, result[2].isAnnotation());
		assertEquals(false, result[2].isAnonymousClass());
		assertEquals(false, result[2].isEnum());
		assertEquals(false, result[2].isLocalClass());
		assertEquals(false, result[2].isMemberClass());
		assertEquals(false, result[2].isSynthetic());
		assertEquals("", result[2].newInstance());
		assertEquals("public final class java.lang.String", result[2].toGenericString());
		assertNotNull(result[3]);
		assertEquals("class java.lang.String", result[3].toString());
		assertEquals(17, result[3].getModifiers());
		assertEquals(false, result[3].isInterface());
		assertEquals(false, result[3].isArray());
		assertEquals(false, result[3].isPrimitive());
		assertEquals(null, result[3].getComponentType());
		assertEquals("java.lang.String", result[3].getName());
		assertEquals(false, result[3].desiredAssertionStatus());
		assertEquals("java.lang.String", result[3].getCanonicalName());
		assertEquals(null, result[3].getClassLoader());
		assertEquals(null, result[3].getDeclaringClass());
		assertEquals(null, result[3].getEnclosingClass());
		assertEquals(null, result[3].getEnclosingConstructor());
		assertEquals(null, result[3].getEnclosingMethod());
		assertEquals(null, result[3].getEnumConstants());
		assertEquals(null, result[3].getSigners());
		assertEquals("String", result[3].getSimpleName());
		assertEquals("java.lang.String", result[3].getTypeName());
		assertEquals(false, result[3].isAnnotation());
		assertEquals(false, result[3].isAnonymousClass());
		assertEquals(false, result[3].isEnum());
		assertEquals(false, result[3].isLocalClass());
		assertEquals(false, result[3].isMemberClass());
		assertEquals(false, result[3].isSynthetic());
		assertEquals("", result[3].newInstance());
		assertEquals("public final class java.lang.String", result[3].toGenericString());
		assertNotNull(result[4]);
		assertEquals("class java.lang.String", result[4].toString());
		assertEquals(17, result[4].getModifiers());
		assertEquals(false, result[4].isInterface());
		assertEquals(false, result[4].isArray());
		assertEquals(false, result[4].isPrimitive());
		assertEquals(null, result[4].getComponentType());
		assertEquals("java.lang.String", result[4].getName());
		assertEquals(false, result[4].desiredAssertionStatus());
		assertEquals("java.lang.String", result[4].getCanonicalName());
		assertEquals(null, result[4].getClassLoader());
		assertEquals(null, result[4].getDeclaringClass());
		assertEquals(null, result[4].getEnclosingClass());
		assertEquals(null, result[4].getEnclosingConstructor());
		assertEquals(null, result[4].getEnclosingMethod());
		assertEquals(null, result[4].getEnumConstants());
		assertEquals(null, result[4].getSigners());
		assertEquals("String", result[4].getSimpleName());
		assertEquals("java.lang.String", result[4].getTypeName());
		assertEquals(false, result[4].isAnnotation());
		assertEquals(false, result[4].isAnonymousClass());
		assertEquals(false, result[4].isEnum());
		assertEquals(false, result[4].isLocalClass());
		assertEquals(false, result[4].isMemberClass());
		assertEquals(false, result[4].isSynthetic());
		assertEquals("", result[4].newInstance());
		assertEquals("public final class java.lang.String", result[4].toGenericString());
		assertNotNull(result[5]);
		assertEquals("class java.lang.String", result[5].toString());
		assertEquals(17, result[5].getModifiers());
		assertEquals(false, result[5].isInterface());
		assertEquals(false, result[5].isArray());
		assertEquals(false, result[5].isPrimitive());
		assertEquals(null, result[5].getComponentType());
		assertEquals("java.lang.String", result[5].getName());
		assertEquals(false, result[5].desiredAssertionStatus());
		assertEquals("java.lang.String", result[5].getCanonicalName());
		assertEquals(null, result[5].getClassLoader());
		assertEquals(null, result[5].getDeclaringClass());
		assertEquals(null, result[5].getEnclosingClass());
		assertEquals(null, result[5].getEnclosingConstructor());
		assertEquals(null, result[5].getEnclosingMethod());
		assertEquals(null, result[5].getEnumConstants());
		assertEquals(null, result[5].getSigners());
		assertEquals("String", result[5].getSimpleName());
		assertEquals("java.lang.String", result[5].getTypeName());
		assertEquals(false, result[5].isAnnotation());
		assertEquals(false, result[5].isAnonymousClass());
		assertEquals(false, result[5].isEnum());
		assertEquals(false, result[5].isLocalClass());
		assertEquals(false, result[5].isMemberClass());
		assertEquals(false, result[5].isSynthetic());
		assertEquals("", result[5].newInstance());
		assertEquals("public final class java.lang.String", result[5].toGenericString());
		assertNotNull(result[6]);
		assertEquals("class java.lang.String", result[6].toString());
		assertEquals(17, result[6].getModifiers());
		assertEquals(false, result[6].isInterface());
		assertEquals(false, result[6].isArray());
		assertEquals(false, result[6].isPrimitive());
		assertEquals(null, result[6].getComponentType());
		assertEquals("java.lang.String", result[6].getName());
		assertEquals(false, result[6].desiredAssertionStatus());
		assertEquals("java.lang.String", result[6].getCanonicalName());
		assertEquals(null, result[6].getClassLoader());
		assertEquals(null, result[6].getDeclaringClass());
		assertEquals(null, result[6].getEnclosingClass());
		assertEquals(null, result[6].getEnclosingConstructor());
		assertEquals(null, result[6].getEnclosingMethod());
		assertEquals(null, result[6].getEnumConstants());
		assertEquals(null, result[6].getSigners());
		assertEquals("String", result[6].getSimpleName());
		assertEquals("java.lang.String", result[6].getTypeName());
		assertEquals(false, result[6].isAnnotation());
		assertEquals(false, result[6].isAnonymousClass());
		assertEquals(false, result[6].isEnum());
		assertEquals(false, result[6].isLocalClass());
		assertEquals(false, result[6].isMemberClass());
		assertEquals(false, result[6].isSynthetic());
		assertEquals("", result[6].newInstance());
		assertEquals("public final class java.lang.String", result[6].toGenericString());
		assertNotNull(result[7]);
		assertEquals("class java.util.Date", result[7].toString());
		assertEquals(1, result[7].getModifiers());
		assertEquals(false, result[7].isInterface());
		assertEquals(false, result[7].isArray());
		assertEquals(false, result[7].isPrimitive());
		assertEquals(null, result[7].getComponentType());
		assertEquals("java.util.Date", result[7].getName());
		assertEquals(false, result[7].desiredAssertionStatus());
		assertEquals("java.util.Date", result[7].getCanonicalName());
		assertEquals(null, result[7].getClassLoader());
		assertEquals(null, result[7].getDeclaringClass());
		assertEquals(null, result[7].getEnclosingClass());
		assertEquals(null, result[7].getEnclosingConstructor());
		assertEquals(null, result[7].getEnclosingMethod());
		assertEquals(null, result[7].getEnumConstants());
		assertEquals(null, result[7].getSigners());
		assertEquals("Date", result[7].getSimpleName());
		assertEquals("java.util.Date", result[7].getTypeName());
		assertEquals(false, result[7].isAnnotation());
		assertEquals(false, result[7].isAnonymousClass());
		assertEquals(false, result[7].isEnum());
		assertEquals(false, result[7].isLocalClass());
		assertEquals(false, result[7].isMemberClass());
		assertEquals(false, result[7].isSynthetic());
		assertEquals("public class java.util.Date", result[7].toGenericString());
		assertNotNull(result[8]);
		assertEquals("class java.util.Date", result[8].toString());
		assertEquals(1, result[8].getModifiers());
		assertEquals(false, result[8].isInterface());
		assertEquals(false, result[8].isArray());
		assertEquals(false, result[8].isPrimitive());
		assertEquals(null, result[8].getComponentType());
		assertEquals("java.util.Date", result[8].getName());
		assertEquals(false, result[8].desiredAssertionStatus());
		assertEquals("java.util.Date", result[8].getCanonicalName());
		assertEquals(null, result[8].getClassLoader());
		assertEquals(null, result[8].getDeclaringClass());
		assertEquals(null, result[8].getEnclosingClass());
		assertEquals(null, result[8].getEnclosingConstructor());
		assertEquals(null, result[8].getEnclosingMethod());
		assertEquals(null, result[8].getEnumConstants());
		assertEquals(null, result[8].getSigners());
		assertEquals("Date", result[8].getSimpleName());
		assertEquals("java.util.Date", result[8].getTypeName());
		assertEquals(false, result[8].isAnnotation());
		assertEquals(false, result[8].isAnonymousClass());
		assertEquals(false, result[8].isEnum());
		assertEquals(false, result[8].isLocalClass());
		assertEquals(false, result[8].isMemberClass());
		assertEquals(false, result[8].isSynthetic());
		assertEquals("public class java.util.Date", result[8].toGenericString());
		assertNotNull(result[9]);
		assertEquals("class java.lang.String", result[9].toString());
		assertEquals(17, result[9].getModifiers());
		assertEquals(false, result[9].isInterface());
		assertEquals(false, result[9].isArray());
		assertEquals(false, result[9].isPrimitive());
		assertEquals(null, result[9].getComponentType());
		assertEquals("java.lang.String", result[9].getName());
		assertEquals(false, result[9].desiredAssertionStatus());
		assertEquals("java.lang.String", result[9].getCanonicalName());
		assertEquals(null, result[9].getClassLoader());
		assertEquals(null, result[9].getDeclaringClass());
		assertEquals(null, result[9].getEnclosingClass());
		assertEquals(null, result[9].getEnclosingConstructor());
		assertEquals(null, result[9].getEnclosingMethod());
		assertEquals(null, result[9].getEnumConstants());
		assertEquals(null, result[9].getSigners());
		assertEquals("String", result[9].getSimpleName());
		assertEquals("java.lang.String", result[9].getTypeName());
		assertEquals(false, result[9].isAnnotation());
		assertEquals(false, result[9].isAnonymousClass());
		assertEquals(false, result[9].isEnum());
		assertEquals(false, result[9].isLocalClass());
		assertEquals(false, result[9].isMemberClass());
		assertEquals(false, result[9].isSynthetic());
		assertEquals("", result[9].newInstance());
		assertEquals("public final class java.lang.String", result[9].toGenericString());
		assertNotNull(result[10]);
		assertEquals("class java.lang.String", result[10].toString());
		assertEquals(17, result[10].getModifiers());
		assertEquals(false, result[10].isInterface());
		assertEquals(false, result[10].isArray());
		assertEquals(false, result[10].isPrimitive());
		assertEquals(null, result[10].getComponentType());
		assertEquals("java.lang.String", result[10].getName());
		assertEquals(false, result[10].desiredAssertionStatus());
		assertEquals("java.lang.String", result[10].getCanonicalName());
		assertEquals(null, result[10].getClassLoader());
		assertEquals(null, result[10].getDeclaringClass());
		assertEquals(null, result[10].getEnclosingClass());
		assertEquals(null, result[10].getEnclosingConstructor());
		assertEquals(null, result[10].getEnclosingMethod());
		assertEquals(null, result[10].getEnumConstants());
		assertEquals(null, result[10].getSigners());
		assertEquals("String", result[10].getSimpleName());
		assertEquals("java.lang.String", result[10].getTypeName());
		assertEquals(false, result[10].isAnnotation());
		assertEquals(false, result[10].isAnonymousClass());
		assertEquals(false, result[10].isEnum());
		assertEquals(false, result[10].isLocalClass());
		assertEquals(false, result[10].isMemberClass());
		assertEquals(false, result[10].isSynthetic());
		assertEquals("", result[10].newInstance());
		assertEquals("public final class java.lang.String", result[10].toGenericString());
		assertNotNull(result[11]);
		assertEquals("class java.lang.String", result[11].toString());
		assertEquals(17, result[11].getModifiers());
		assertEquals(false, result[11].isInterface());
		assertEquals(false, result[11].isArray());
		assertEquals(false, result[11].isPrimitive());
		assertEquals(null, result[11].getComponentType());
		assertEquals("java.lang.String", result[11].getName());
		assertEquals(false, result[11].desiredAssertionStatus());
		assertEquals("java.lang.String", result[11].getCanonicalName());
		assertEquals(null, result[11].getClassLoader());
		assertEquals(null, result[11].getDeclaringClass());
		assertEquals(null, result[11].getEnclosingClass());
		assertEquals(null, result[11].getEnclosingConstructor());
		assertEquals(null, result[11].getEnclosingMethod());
		assertEquals(null, result[11].getEnumConstants());
		assertEquals(null, result[11].getSigners());
		assertEquals("String", result[11].getSimpleName());
		assertEquals("java.lang.String", result[11].getTypeName());
		assertEquals(false, result[11].isAnnotation());
		assertEquals(false, result[11].isAnonymousClass());
		assertEquals(false, result[11].isEnum());
		assertEquals(false, result[11].isLocalClass());
		assertEquals(false, result[11].isMemberClass());
		assertEquals(false, result[11].isSynthetic());
		assertEquals("", result[11].newInstance());
		assertEquals("public final class java.lang.String", result[11].toGenericString());
	}

	
	@Test
	public void testGetRsLookUpHeader_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsLookUpHeader();

		assertNotNull(result);
		assertEquals(12, result.length);
		assertEquals("System ID", result[0]);
		assertEquals("Rebate Schedule ID", result[1]);
		assertEquals("Rebate Schedule No", result[2]);
		assertEquals("Rebate Schedule Name", result[3]);
		assertEquals("Rebate Schedule Type", result[4]);
		assertEquals("Rebate Schedule Status", result[5]);
		assertEquals("Rebate Schedule Category", result[6]);
		assertEquals("Start Date", result[7]);
		assertEquals("End Date", result[8]);
		assertEquals("Rebate Schedule Designation", result[9]);
		assertEquals("Parent ID", result[10]);
		assertEquals("Parent Name", result[11]);
	}

	
	@Test
	public void testGetRsSetUPTabCalculationTypeFormulaVisibleColumns_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetUPTabCalculationTypeFormulaVisibleColumns();

		assertNotNull(result);
		assertEquals(16, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
		assertNotNull(result[15]);
	}

	
	@Test
	public void testGetRsSetUPTabCalculationTypeFormulaVisibleColumnsforView_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetUPTabCalculationTypeFormulaVisibleColumnsforView();

		assertNotNull(result);
		assertEquals(15, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
	}

	
	@Test
	public void testGetRsSetUPTabCalculationTypeRebatePlanVisibleColumns_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetUPTabCalculationTypeRebatePlanVisibleColumns();

		assertNotNull(result);
		assertEquals(16, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
		assertNotNull(result[15]);
	}

	
	@Test
	public void testGetRsSetUPTabCalculationTypeRebatePlanVisibleColumnsforView_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetUPTabCalculationTypeRebatePlanVisibleColumnsforView();

		assertNotNull(result);
		assertEquals(15, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
		assertNotNull(result[13]);
		assertNotNull(result[14]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypeDeductionCalendarTableHeaders_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeDeductionCalendarTableHeaders();

		assertNotNull(result);
		assertEquals(13, result.length);
		assertEquals(" ", result[0]);
		assertEquals("Item No", result[1]);
		assertEquals("Item Name", result[2]);
		assertEquals("RS Status", result[3]);
		assertEquals("RS Start Date", result[4]);
		assertEquals("RS End Date", result[5]);
		assertEquals("Deduction Calendar No", result[6]);
		assertEquals("Deduction Calendar Name", result[7]);
		assertEquals("Evaluation Rule", result[8]);
		assertEquals("Evaluation Rule Bundle", result[9]);
		assertEquals("Calculation Rule", result[10]);
		assertEquals("Calculation Rule bundle", result[11]);
		assertEquals("Attached Date", result[12]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypeDeductionCalendarTableHeadersforView_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeDeductionCalendarTableHeadersforView();

		assertNotNull(result);
		assertEquals(12, result.length);
		assertEquals("Item No", result[0]);
		assertEquals("Item Name", result[1]);
		assertEquals("RS Status", result[2]);
		assertEquals("RS Start Date", result[3]);
		assertEquals("RS End Date", result[4]);
		assertEquals("Deduction Calendar No", result[5]);
		assertEquals("Deduction Calendar Name", result[6]);
		assertEquals("Evaluation Rule", result[7]);
		assertEquals("Evaluation Rule Bundle", result[8]);
		assertEquals("Calculation Rule", result[9]);
		assertEquals("Calculation Rule bundle", result[10]);
		assertEquals("Attached Date", result[11]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypeDeductionCalendarVisibleColumns_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeDeductionCalendarVisibleColumns();

		assertNotNull(result);
		assertEquals(13, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
		assertNotNull(result[12]);
	}


	@Test
	public void testGetRsSetUpTabCalculationTypeDeductionCalendarVisibleColumnsforView_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeDeductionCalendarVisibleColumnsforView();

		assertNotNull(result);
		assertEquals(12, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
		assertNotNull(result[8]);
		assertNotNull(result[9]);
		assertNotNull(result[10]);
		assertNotNull(result[11]);
	}


	@Test
	public void testGetRsSetUpTabCalculationTypeFormulaTableHeaders_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeFormulaTableHeaders();

		assertNotNull(result);
		assertEquals(16, result.length);
		assertEquals(" ", result[0]);
		assertEquals("Item No", result[1]);
		assertEquals("Item Name", result[2]);
		assertEquals("RS Status", result[3]);
		assertEquals("RS Start Date", result[4]);
		assertEquals("RS End Date", result[5]);
		assertEquals("Formula Type", result[6]);
		assertEquals("Formula No", result[7]);
		assertEquals("Formula Name", result[8]);
		assertEquals("Net Sales Formula", result[9]);
		assertEquals("Net Sales Rule", result[10]);
		assertEquals("Evaluation Rule", result[11]);
		assertEquals("Evaluation Rule Bundle", result[12]);
		assertEquals("Calculation Rule", result[13]);
		assertEquals("Calculation Rule bundle", result[14]);
		assertEquals("Attached Date", result[15]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypeFormulaTableHeadersforView_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeFormulaTableHeadersforView();

		assertNotNull(result);
		assertEquals(15, result.length);
		assertEquals("Item No", result[0]);
		assertEquals("Item Name", result[1]);
		assertEquals("RS Status", result[2]);
		assertEquals("RS Start Date", result[3]);
		assertEquals("RS End Date", result[4]);
		assertEquals("Formula Type", result[5]);
		assertEquals("Formula No", result[6]);
		assertEquals("Formula Name", result[7]);
		assertEquals("Net Sales Formula", result[8]);
		assertEquals("Net Sales Rule", result[9]);
		assertEquals("Evaluation Rule", result[10]);
		assertEquals("Evaluation Rule Bundle", result[11]);
		assertEquals("Calculation Rule", result[12]);
		assertEquals("Calculation Rule bundle", result[13]);
		assertEquals("Attached Date", result[14]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypePriceProtectionTableHeaders_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypePriceProtectionTableHeaders();

		assertNotNull(result);
		assertEquals(6, result.length);
		assertEquals(" ", result[0]);
		assertEquals("Item No", result[1]);
		assertEquals("Item Name", result[2]);
		assertEquals("RS Status", result[3]);
		assertEquals("RS Start Date", result[4]);
		assertEquals("RS End Date", result[5]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypePriceProtectionVisibleColumns_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypePriceProtectionVisibleColumns();

		assertNotNull(result);
		assertEquals(6, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypeRebatePlanTableHeaders_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeRebatePlanTableHeaders();

		assertNotNull(result);
		assertEquals(16, result.length);
		assertEquals("", result[0]);
		assertEquals("Item No", result[1]);
		assertEquals("Item Name", result[2]);
		assertEquals("RS Status", result[3]);
		assertEquals("RS Start Date", result[4]);
		assertEquals("RS End Date", result[5]);
		assertEquals("Bundle No", result[6]);
		assertEquals("Rebate Plan No", result[7]);
		assertEquals("Rebate Plan Name", result[8]);
		assertEquals("Net Sales Formula", result[9]);
		assertEquals("Net Sales Rule", result[10]);
		assertEquals("Evaluation Rule", result[11]);
		assertEquals("Evaluation Rule Bundle", result[12]);
		assertEquals("Calculation Rule", result[13]);
		assertEquals("Calculation Rule bundle", result[14]);
		assertEquals("Attached Date", result[15]);
	}

	
	@Test
	public void testGetRsSetUpTabCalculationTypeRebatePlanTableHeadersforView_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeRebatePlanTableHeadersforView();

		assertNotNull(result);
		assertEquals(15, result.length);
		assertEquals("Item No", result[0]);
		assertEquals("Item Name", result[1]);
		assertEquals("RS Status", result[2]);
		assertEquals("RS Start Date", result[3]);
		assertEquals("RS End Date", result[4]);
		assertEquals("Bundle No", result[5]);
		assertEquals("Rebate Plan No", result[6]);
		assertEquals("Rebate Plan Name", result[7]);
		assertEquals("Net Sales Formula", result[8]);
		assertEquals("Net Sales Rule", result[9]);
		assertEquals("Evaluation Rule", result[10]);
		assertEquals("Evaluation Rule Bundle", result[11]);
		assertEquals("Calculation Rule", result[12]);
		assertEquals("Calculation Rule bundle", result[13]);
		assertEquals("Attached Date", result[14]);
	}

	
	@Test
	public void testGetRsSetupTabTableHeaders_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetupTabTableHeaders();

		assertNotNull(result);
		assertEquals(7, result.length);
		assertEquals("", result[0]);
		assertEquals("Item No", result[1]);
		assertEquals("Item Name", result[2]);
		assertEquals("RS Status", result[3]);
		assertEquals("RS Start Date", result[4]);
		assertEquals("RS End Date", result[5]);
		assertEquals("RS Status", result[6]);
	}

	
	@Test
	public void testGetRsSetupTabVisibleColumns_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetupTabVisibleColumns();

		assertNotNull(result);
		assertEquals(7, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
	}

	@Test
	public void testGetRsSetupTabVisibleColumnsView_1()
		throws Exception {

		Object[] result = GtnFrameworkRSConstants.getRsSetupTabVisibleColumnsView();

		assertNotNull(result);
		assertEquals(5, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
	}

	@Test
	public void testGetRsSetupTabVisibleHeadersView_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRsSetupTabVisibleHeadersView();

		assertNotNull(result);
		assertEquals(5, result.length);
		assertEquals("Item No", result[0]);
		assertEquals("Item Name", result[1]);
		assertEquals("RS Status", result[2]);
		assertEquals("RS Start Date", result[3]);
		assertEquals("RS End Date", result[4]);
	}

	@Test
	public void testGetRuleDetailsLookUpColumn_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRuleDetailsLookUpColumn();

		assertNotNull(result);
		assertEquals(7, result.length);
		assertEquals("lineType", result[0]);
		assertEquals("itemGroupAsso", result[1]);
		assertEquals("keyword", result[2]);
		assertEquals("keyOperator", result[3]);
		assertEquals("value", result[4]);
		assertEquals("comparison", result[5]);
		assertEquals("compOperator", result[6]);
	}

	
	@Test
	public void testGetRuleDetailsLookUpColumnType_1()
		throws Exception {

		Class<Object>[] result = (Class<Object>[]) GtnFrameworkRSConstants.getRuleDetailsLookUpColumnType();

		assertNotNull(result);
		assertEquals(7, result.length);
		assertNotNull(result[0]);
		assertEquals("class java.lang.String", result[0].toString());
		assertEquals(17, result[0].getModifiers());
		assertEquals(false, result[0].isInterface());
		assertEquals(false, result[0].isArray());
		assertEquals(false, result[0].isPrimitive());
		assertEquals(null, result[0].getComponentType());
		assertEquals("java.lang.String", result[0].getName());
		assertEquals(false, result[0].desiredAssertionStatus());
		assertEquals("java.lang.String", result[0].getCanonicalName());
		assertEquals(null, result[0].getClassLoader());
		assertEquals(null, result[0].getDeclaringClass());
		assertEquals(null, result[0].getEnclosingClass());
		assertEquals(null, result[0].getEnclosingConstructor());
		assertEquals(null, result[0].getEnclosingMethod());
		assertEquals(null, result[0].getEnumConstants());
		assertEquals(null, result[0].getSigners());
		assertEquals("String", result[0].getSimpleName());
		assertEquals("java.lang.String", result[0].getTypeName());
		assertEquals(false, result[0].isAnnotation());
		assertEquals(false, result[0].isAnonymousClass());
		assertEquals(false, result[0].isEnum());
		assertEquals(false, result[0].isLocalClass());
		assertEquals(false, result[0].isMemberClass());
		assertEquals(false, result[0].isSynthetic());
		assertEquals("", result[0].newInstance());
		assertEquals("public final class java.lang.String", result[0].toGenericString());
		assertNotNull(result[1]);
		assertEquals("class java.lang.String", result[1].toString());
		assertEquals(17, result[1].getModifiers());
		assertEquals(false, result[1].isInterface());
		assertEquals(false, result[1].isArray());
		assertEquals(false, result[1].isPrimitive());
		assertEquals(null, result[1].getComponentType());
		assertEquals("java.lang.String", result[1].getName());
		assertEquals(false, result[1].desiredAssertionStatus());
		assertEquals("java.lang.String", result[1].getCanonicalName());
		assertEquals(null, result[1].getClassLoader());
		assertEquals(null, result[1].getDeclaringClass());
		assertEquals(null, result[1].getEnclosingClass());
		assertEquals(null, result[1].getEnclosingConstructor());
		assertEquals(null, result[1].getEnclosingMethod());
		assertEquals(null, result[1].getEnumConstants());
		assertEquals(null, result[1].getSigners());
		assertEquals("String", result[1].getSimpleName());
		assertEquals("java.lang.String", result[1].getTypeName());
		assertEquals(false, result[1].isAnnotation());
		assertEquals(false, result[1].isAnonymousClass());
		assertEquals(false, result[1].isEnum());
		assertEquals(false, result[1].isLocalClass());
		assertEquals(false, result[1].isMemberClass());
		assertEquals(false, result[1].isSynthetic());
		assertEquals("", result[1].newInstance());
		assertEquals("public final class java.lang.String", result[1].toGenericString());
		assertNotNull(result[2]);
		assertEquals("class java.lang.String", result[2].toString());
		assertEquals(17, result[2].getModifiers());
		assertEquals(false, result[2].isInterface());
		assertEquals(false, result[2].isArray());
		assertEquals(false, result[2].isPrimitive());
		assertEquals(null, result[2].getComponentType());
		assertEquals("java.lang.String", result[2].getName());
		assertEquals(false, result[2].desiredAssertionStatus());
		assertEquals("java.lang.String", result[2].getCanonicalName());
		assertEquals(null, result[2].getClassLoader());
		assertEquals(null, result[2].getDeclaringClass());
		assertEquals(null, result[2].getEnclosingClass());
		assertEquals(null, result[2].getEnclosingConstructor());
		assertEquals(null, result[2].getEnclosingMethod());
		assertEquals(null, result[2].getEnumConstants());
		assertEquals(null, result[2].getSigners());
		assertEquals("String", result[2].getSimpleName());
		assertEquals("java.lang.String", result[2].getTypeName());
		assertEquals(false, result[2].isAnnotation());
		assertEquals(false, result[2].isAnonymousClass());
		assertEquals(false, result[2].isEnum());
		assertEquals(false, result[2].isLocalClass());
		assertEquals(false, result[2].isMemberClass());
		assertEquals(false, result[2].isSynthetic());
		assertEquals("", result[2].newInstance());
		assertEquals("public final class java.lang.String", result[2].toGenericString());
		assertNotNull(result[3]);
		assertEquals("class java.lang.String", result[3].toString());
		assertEquals(17, result[3].getModifiers());
		assertEquals(false, result[3].isInterface());
		assertEquals(false, result[3].isArray());
		assertEquals(false, result[3].isPrimitive());
		assertEquals(null, result[3].getComponentType());
		assertEquals("java.lang.String", result[3].getName());
		assertEquals(false, result[3].desiredAssertionStatus());
		assertEquals("java.lang.String", result[3].getCanonicalName());
		assertEquals(null, result[3].getClassLoader());
		assertEquals(null, result[3].getDeclaringClass());
		assertEquals(null, result[3].getEnclosingClass());
		assertEquals(null, result[3].getEnclosingConstructor());
		assertEquals(null, result[3].getEnclosingMethod());
		assertEquals(null, result[3].getEnumConstants());
		assertEquals(null, result[3].getSigners());
		assertEquals("String", result[3].getSimpleName());
		assertEquals("java.lang.String", result[3].getTypeName());
		assertEquals(false, result[3].isAnnotation());
		assertEquals(false, result[3].isAnonymousClass());
		assertEquals(false, result[3].isEnum());
		assertEquals(false, result[3].isLocalClass());
		assertEquals(false, result[3].isMemberClass());
		assertEquals(false, result[3].isSynthetic());
		assertEquals("", result[3].newInstance());
		assertEquals("public final class java.lang.String", result[3].toGenericString());
		assertNotNull(result[4]);
		assertEquals("class java.lang.String", result[4].toString());
		assertEquals(17, result[4].getModifiers());
		assertEquals(false, result[4].isInterface());
		assertEquals(false, result[4].isArray());
		assertEquals(false, result[4].isPrimitive());
		assertEquals(null, result[4].getComponentType());
		assertEquals("java.lang.String", result[4].getName());
		assertEquals(false, result[4].desiredAssertionStatus());
		assertEquals("java.lang.String", result[4].getCanonicalName());
		assertEquals(null, result[4].getClassLoader());
		assertEquals(null, result[4].getDeclaringClass());
		assertEquals(null, result[4].getEnclosingClass());
		assertEquals(null, result[4].getEnclosingConstructor());
		assertEquals(null, result[4].getEnclosingMethod());
		assertEquals(null, result[4].getEnumConstants());
		assertEquals(null, result[4].getSigners());
		assertEquals("String", result[4].getSimpleName());
		assertEquals("java.lang.String", result[4].getTypeName());
		assertEquals(false, result[4].isAnnotation());
		assertEquals(false, result[4].isAnonymousClass());
		assertEquals(false, result[4].isEnum());
		assertEquals(false, result[4].isLocalClass());
		assertEquals(false, result[4].isMemberClass());
		assertEquals(false, result[4].isSynthetic());
		assertEquals("", result[4].newInstance());
		assertEquals("public final class java.lang.String", result[4].toGenericString());
		assertNotNull(result[5]);
		assertEquals("class java.lang.String", result[5].toString());
		assertEquals(17, result[5].getModifiers());
		assertEquals(false, result[5].isInterface());
		assertEquals(false, result[5].isArray());
		assertEquals(false, result[5].isPrimitive());
		assertEquals(null, result[5].getComponentType());
		assertEquals("java.lang.String", result[5].getName());
		assertEquals(false, result[5].desiredAssertionStatus());
		assertEquals("java.lang.String", result[5].getCanonicalName());
		assertEquals(null, result[5].getClassLoader());
		assertEquals(null, result[5].getDeclaringClass());
		assertEquals(null, result[5].getEnclosingClass());
		assertEquals(null, result[5].getEnclosingConstructor());
		assertEquals(null, result[5].getEnclosingMethod());
		assertEquals(null, result[5].getEnumConstants());
		assertEquals(null, result[5].getSigners());
		assertEquals("String", result[5].getSimpleName());
		assertEquals("java.lang.String", result[5].getTypeName());
		assertEquals(false, result[5].isAnnotation());
		assertEquals(false, result[5].isAnonymousClass());
		assertEquals(false, result[5].isEnum());
		assertEquals(false, result[5].isLocalClass());
		assertEquals(false, result[5].isMemberClass());
		assertEquals(false, result[5].isSynthetic());
		assertEquals("", result[5].newInstance());
		assertEquals("public final class java.lang.String", result[5].toGenericString());
		assertNotNull(result[6]);
		assertEquals("class java.lang.String", result[6].toString());
		assertEquals(17, result[6].getModifiers());
		assertEquals(false, result[6].isInterface());
		assertEquals(false, result[6].isArray());
		assertEquals(false, result[6].isPrimitive());
		assertEquals(null, result[6].getComponentType());
		assertEquals("java.lang.String", result[6].getName());
		assertEquals(false, result[6].desiredAssertionStatus());
		assertEquals("java.lang.String", result[6].getCanonicalName());
		assertEquals(null, result[6].getClassLoader());
		assertEquals(null, result[6].getDeclaringClass());
		assertEquals(null, result[6].getEnclosingClass());
		assertEquals(null, result[6].getEnclosingConstructor());
		assertEquals(null, result[6].getEnclosingMethod());
		assertEquals(null, result[6].getEnumConstants());
		assertEquals(null, result[6].getSigners());
		assertEquals("String", result[6].getSimpleName());
		assertEquals("java.lang.String", result[6].getTypeName());
		assertEquals(false, result[6].isAnnotation());
		assertEquals(false, result[6].isAnonymousClass());
		assertEquals(false, result[6].isEnum());
		assertEquals(false, result[6].isLocalClass());
		assertEquals(false, result[6].isMemberClass());
		assertEquals(false, result[6].isSynthetic());
		assertEquals("", result[6].newInstance());
		assertEquals("public final class java.lang.String", result[6].toGenericString());
	}

	
	@Test
	public void testGetRuleDetailsLookUpHeader_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRuleDetailsLookUpHeader();

		assertNotNull(result);
		assertEquals(7, result.length);
		assertEquals("Line Type", result[0]);
		assertEquals("Item/Group/Association", result[1]);
		assertEquals("Keyword", result[2]);
		assertEquals("Operator", result[3]);
		assertEquals("Value", result[4]);
		assertEquals("Comparison", result[5]);
		assertEquals("Operator", result[6]);
	}

	
	@Test
	public void testGetRuleLookUpColumn_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRuleLookUpColumn();

		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals("ruleType", result[0]);
		assertEquals("ruleNo", result[1]);
		assertEquals("ruleName", result[2]);
		assertEquals("ruleCategory", result[3]);
	}

	
	@Test
	public void testGetRuleLookUpHeader_1()
		throws Exception {

		String[] result = GtnFrameworkRSConstants.getRuleLookUpHeader();

		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals("Rule Type", result[0]);
		assertEquals("Rule No", result[1]);
		assertEquals("Rule Name", result[2]);
		assertEquals("Rule Category", result[3]);
	}

	
	@Test
	public void testGetTextFieldProperties_1()
		throws Exception {

		List<String> result = GtnFrameworkRSConstants.getTextFieldProperties();

		assertNotNull(result);
		assertEquals(8, result.size());
		assertTrue(result.contains("deductionNo"));
		assertTrue(result.contains("itemNo"));
		assertTrue(result.contains("itemName"));
		assertTrue(result.contains("evaluationRuleBundle"));
		assertTrue(result.contains("calculationRuleBundle"));
		assertTrue(result.contains("formulaName"));
		assertTrue(result.contains("rsBundleNo"));
		assertTrue(result.contains("rebatePlanName"));
	}

	
	@Test
	public void testGetruleLookUpColumnType_1()
		throws Exception {

		Class<Object>[] result = (Class<Object>[]) GtnFrameworkRSConstants.getruleLookUpColumnType();

		assertNotNull(result);
		assertEquals(4, result.length);
		assertNotNull(result[0]);
		assertEquals("class java.lang.String", result[0].toString());
		assertEquals(17, result[0].getModifiers());
		assertEquals(false, result[0].isInterface());
		assertEquals(false, result[0].isArray());
		assertEquals(false, result[0].isPrimitive());
		assertEquals(null, result[0].getComponentType());
		assertEquals("java.lang.String", result[0].getName());
		assertEquals(false, result[0].desiredAssertionStatus());
		assertEquals("java.lang.String", result[0].getCanonicalName());
		assertEquals(null, result[0].getClassLoader());
		assertEquals(null, result[0].getDeclaringClass());
		assertEquals(null, result[0].getEnclosingClass());
		assertEquals(null, result[0].getEnclosingConstructor());
		assertEquals(null, result[0].getEnclosingMethod());
		assertEquals(null, result[0].getEnumConstants());
		assertEquals(null, result[0].getSigners());
		assertEquals("String", result[0].getSimpleName());
		assertEquals("java.lang.String", result[0].getTypeName());
		assertEquals(false, result[0].isAnnotation());
		assertEquals(false, result[0].isAnonymousClass());
		assertEquals(false, result[0].isEnum());
		assertEquals(false, result[0].isLocalClass());
		assertEquals(false, result[0].isMemberClass());
		assertEquals(false, result[0].isSynthetic());
		assertEquals("", result[0].newInstance());
		assertEquals("public final class java.lang.String", result[0].toGenericString());
		assertNotNull(result[1]);
		assertEquals("class java.lang.String", result[1].toString());
		assertEquals(17, result[1].getModifiers());
		assertEquals(false, result[1].isInterface());
		assertEquals(false, result[1].isArray());
		assertEquals(false, result[1].isPrimitive());
		assertEquals(null, result[1].getComponentType());
		assertEquals("java.lang.String", result[1].getName());
		assertEquals(false, result[1].desiredAssertionStatus());
		assertEquals("java.lang.String", result[1].getCanonicalName());
		assertEquals(null, result[1].getClassLoader());
		assertEquals(null, result[1].getDeclaringClass());
		assertEquals(null, result[1].getEnclosingClass());
		assertEquals(null, result[1].getEnclosingConstructor());
		assertEquals(null, result[1].getEnclosingMethod());
		assertEquals(null, result[1].getEnumConstants());
		assertEquals(null, result[1].getSigners());
		assertEquals("String", result[1].getSimpleName());
		assertEquals("java.lang.String", result[1].getTypeName());
		assertEquals(false, result[1].isAnnotation());
		assertEquals(false, result[1].isAnonymousClass());
		assertEquals(false, result[1].isEnum());
		assertEquals(false, result[1].isLocalClass());
		assertEquals(false, result[1].isMemberClass());
		assertEquals(false, result[1].isSynthetic());
		assertEquals("", result[1].newInstance());
		assertEquals("public final class java.lang.String", result[1].toGenericString());
		assertNotNull(result[2]);
		assertEquals("class java.lang.String", result[2].toString());
		assertEquals(17, result[2].getModifiers());
		assertEquals(false, result[2].isInterface());
		assertEquals(false, result[2].isArray());
		assertEquals(false, result[2].isPrimitive());
		assertEquals(null, result[2].getComponentType());
		assertEquals("java.lang.String", result[2].getName());
		assertEquals(false, result[2].desiredAssertionStatus());
		assertEquals("java.lang.String", result[2].getCanonicalName());
		assertEquals(null, result[2].getClassLoader());
		assertEquals(null, result[2].getDeclaringClass());
		assertEquals(null, result[2].getEnclosingClass());
		assertEquals(null, result[2].getEnclosingConstructor());
		assertEquals(null, result[2].getEnclosingMethod());
		assertEquals(null, result[2].getEnumConstants());
		assertEquals(null, result[2].getSigners());
		assertEquals("String", result[2].getSimpleName());
		assertEquals("java.lang.String", result[2].getTypeName());
		assertEquals(false, result[2].isAnnotation());
		assertEquals(false, result[2].isAnonymousClass());
		assertEquals(false, result[2].isEnum());
		assertEquals(false, result[2].isLocalClass());
		assertEquals(false, result[2].isMemberClass());
		assertEquals(false, result[2].isSynthetic());
		assertEquals("", result[2].newInstance());
		assertEquals("public final class java.lang.String", result[2].toGenericString());
		assertNotNull(result[3]);
		assertEquals("class java.lang.String", result[3].toString());
		assertEquals(17, result[3].getModifiers());
		assertEquals(false, result[3].isInterface());
		assertEquals(false, result[3].isArray());
		assertEquals(false, result[3].isPrimitive());
		assertEquals(null, result[3].getComponentType());
		assertEquals("java.lang.String", result[3].getName());
		assertEquals(false, result[3].desiredAssertionStatus());
		assertEquals("java.lang.String", result[3].getCanonicalName());
		assertEquals(null, result[3].getClassLoader());
		assertEquals(null, result[3].getDeclaringClass());
		assertEquals(null, result[3].getEnclosingClass());
		assertEquals(null, result[3].getEnclosingConstructor());
		assertEquals(null, result[3].getEnclosingMethod());
		assertEquals(null, result[3].getEnumConstants());
		assertEquals(null, result[3].getSigners());
		assertEquals("String", result[3].getSimpleName());
		assertEquals("java.lang.String", result[3].getTypeName());
		assertEquals(false, result[3].isAnnotation());
		assertEquals(false, result[3].isAnonymousClass());
		assertEquals(false, result[3].isEnum());
		assertEquals(false, result[3].isLocalClass());
		assertEquals(false, result[3].isMemberClass());
		assertEquals(false, result[3].isSynthetic());
		assertEquals("", result[3].newInstance());
		assertEquals("public final class java.lang.String", result[3].toGenericString());
	}

	
}