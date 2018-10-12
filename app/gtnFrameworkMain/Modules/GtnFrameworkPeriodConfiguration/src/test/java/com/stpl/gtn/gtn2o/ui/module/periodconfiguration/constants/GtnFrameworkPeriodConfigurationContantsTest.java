package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.constants;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkPeriodConfigurationContantsTest {

	@Test
	public void testGetFromFields_1()
		throws Exception {

		String[] result = GtnFrameworkPeriodConfigurationContants.getFromFields();
		assertNotNull(result);
		assertEquals(10, result.length);
		assertEquals("modeFromComponent", result[0]);
		assertEquals("defaultModeFromComponent", result[1]);
		assertEquals("frequencyFromComponent", result[2]);
		assertEquals("defaultFrequencyFrom", result[3]);
		assertEquals("periodFrom", result[4]);
		assertEquals("defaultPeriodFrom", result[5]);
		assertEquals("periodFromTextBox", result[6]);
		assertEquals("defaultPeriodFromTextBox", result[7]);
		assertEquals("dateFrom", result[8]);
		assertEquals("defaultDateFrom", result[9]);
	}


	public void testGetPeriodConfigurationDoubleHeaderVisibleColumns_1()
		throws Exception {

		String[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationDoubleHeaderVisibleColumns();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals("group1", result[0]);
		assertEquals("group2", result[1]);
		assertEquals("group3", result[2]);
	}

	@Test
	public void testGetPeriodConfigurationDoubleHeaderVisibleHeader_1()
		throws Exception {

		String[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationDoubleHeaderVisibleHeader();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals("", result[0]);
		assertEquals("From Period", result[1]);
		assertEquals("To Period", result[2]);
	}


	@Test
	public void testGetPeriodConfigurationGroup1_1()
		throws Exception {

		Object[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationGroup1();

		assertNotNull(result);
		assertEquals(8, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
	}

	@Test
	public void testGetPeriodConfigurationGroup2_1()
		throws Exception {

		Object[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationGroup2();

		assertNotNull(result);
		assertEquals(8, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
	}

	@Test
	public void testGetPeriodConfigurationGroup3_1()
		throws Exception {

		Object[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationGroup3();

		assertNotNull(result);
		assertEquals(8, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
		assertNotNull(result[4]);
		assertNotNull(result[5]);
		assertNotNull(result[6]);
		assertNotNull(result[7]);
	}


	@Test
	public void testGetPeriodConfigurationTableColumnType_1()
		throws Exception {

		Class[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableColumnType();

		assertNotNull(result);
		assertEquals(24, result.length);
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
		assertEquals("class java.lang.String", result[7].toString());
		assertEquals(17, result[7].getModifiers());
		assertEquals(false, result[7].isInterface());
		assertEquals(false, result[7].isArray());
		assertEquals(false, result[7].isPrimitive());
		assertEquals(null, result[7].getComponentType());
		assertEquals("java.lang.String", result[7].getName());
		assertEquals(false, result[7].desiredAssertionStatus());
		assertEquals("java.lang.String", result[7].getCanonicalName());
		assertEquals(null, result[7].getClassLoader());
		assertEquals(null, result[7].getDeclaringClass());
		assertEquals(null, result[7].getEnclosingClass());
		assertEquals(null, result[7].getEnclosingConstructor());
		assertEquals(null, result[7].getEnclosingMethod());
		assertEquals(null, result[7].getEnumConstants());
		assertEquals(null, result[7].getSigners());
		assertEquals("String", result[7].getSimpleName());
		assertEquals("java.lang.String", result[7].getTypeName());
		assertEquals(false, result[7].isAnnotation());
		assertEquals(false, result[7].isAnonymousClass());
		assertEquals(false, result[7].isEnum());
		assertEquals(false, result[7].isLocalClass());
		assertEquals(false, result[7].isMemberClass());
		assertEquals(false, result[7].isSynthetic());
		assertEquals("", result[7].newInstance());
		assertEquals("public final class java.lang.String", result[7].toGenericString());
		assertNotNull(result[8]);
		assertEquals("class java.lang.String", result[8].toString());
		assertEquals(17, result[8].getModifiers());
		assertEquals(false, result[8].isInterface());
		assertEquals(false, result[8].isArray());
		assertEquals(false, result[8].isPrimitive());
		assertEquals(null, result[8].getComponentType());
		assertEquals("java.lang.String", result[8].getName());
		assertEquals(false, result[8].desiredAssertionStatus());
		assertEquals("java.lang.String", result[8].getCanonicalName());
		assertEquals(null, result[8].getClassLoader());
		assertEquals(null, result[8].getDeclaringClass());
		assertEquals(null, result[8].getEnclosingClass());
		assertEquals(null, result[8].getEnclosingConstructor());
		assertEquals(null, result[8].getEnclosingMethod());
		assertEquals(null, result[8].getEnumConstants());
		assertEquals(null, result[8].getSigners());
		assertEquals("String", result[8].getSimpleName());
		assertEquals("java.lang.String", result[8].getTypeName());
		assertEquals(false, result[8].isAnnotation());
		assertEquals(false, result[8].isAnonymousClass());
		assertEquals(false, result[8].isEnum());
		assertEquals(false, result[8].isLocalClass());
		assertEquals(false, result[8].isMemberClass());
		assertEquals(false, result[8].isSynthetic());
		assertEquals("", result[8].newInstance());
		assertEquals("public final class java.lang.String", result[8].toGenericString());
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
		assertEquals("class java.util.Date", result[11].toString());
		assertEquals(1, result[11].getModifiers());
		assertEquals(false, result[11].isInterface());
		assertEquals(false, result[11].isArray());
		assertEquals(false, result[11].isPrimitive());
		assertEquals(null, result[11].getComponentType());
		assertEquals("java.util.Date", result[11].getName());
		assertEquals(false, result[11].desiredAssertionStatus());
		assertEquals("java.util.Date", result[11].getCanonicalName());
		assertEquals(null, result[11].getClassLoader());
		assertEquals(null, result[11].getDeclaringClass());
		assertEquals(null, result[11].getEnclosingClass());
		assertEquals(null, result[11].getEnclosingConstructor());
		assertEquals(null, result[11].getEnclosingMethod());
		assertEquals(null, result[11].getEnumConstants());
		assertEquals(null, result[11].getSigners());
		assertEquals("Date", result[11].getSimpleName());
		assertEquals("java.util.Date", result[11].getTypeName());
		assertEquals(false, result[11].isAnnotation());
		assertEquals(false, result[11].isAnonymousClass());
		assertEquals(false, result[11].isEnum());
		assertEquals(false, result[11].isLocalClass());
		assertEquals(false, result[11].isMemberClass());
		assertEquals(false, result[11].isSynthetic());
		assertEquals("public class java.util.Date", result[11].toGenericString());
		assertNotNull(result[12]);
		assertEquals("class java.lang.String", result[12].toString());
		assertEquals(17, result[12].getModifiers());
		assertEquals(false, result[12].isInterface());
		assertEquals(false, result[12].isArray());
		assertEquals(false, result[12].isPrimitive());
		assertEquals(null, result[12].getComponentType());
		assertEquals("java.lang.String", result[12].getName());
		assertEquals(false, result[12].desiredAssertionStatus());
		assertEquals("java.lang.String", result[12].getCanonicalName());
		assertEquals(null, result[12].getClassLoader());
		assertEquals(null, result[12].getDeclaringClass());
		assertEquals(null, result[12].getEnclosingClass());
		assertEquals(null, result[12].getEnclosingConstructor());
		assertEquals(null, result[12].getEnclosingMethod());
		assertEquals(null, result[12].getEnumConstants());
		assertEquals(null, result[12].getSigners());
		assertEquals("String", result[12].getSimpleName());
		assertEquals("java.lang.String", result[12].getTypeName());
		assertEquals(false, result[12].isAnnotation());
		assertEquals(false, result[12].isAnonymousClass());
		assertEquals(false, result[12].isEnum());
		assertEquals(false, result[12].isLocalClass());
		assertEquals(false, result[12].isMemberClass());
		assertEquals(false, result[12].isSynthetic());
		assertEquals("", result[12].newInstance());
		assertEquals("public final class java.lang.String", result[12].toGenericString());
		assertNotNull(result[13]);
		assertEquals("class java.lang.String", result[13].toString());
		assertEquals(17, result[13].getModifiers());
		assertEquals(false, result[13].isInterface());
		assertEquals(false, result[13].isArray());
		assertEquals(false, result[13].isPrimitive());
		assertEquals(null, result[13].getComponentType());
		assertEquals("java.lang.String", result[13].getName());
		assertEquals(false, result[13].desiredAssertionStatus());
		assertEquals("java.lang.String", result[13].getCanonicalName());
		assertEquals(null, result[13].getClassLoader());
		assertEquals(null, result[13].getDeclaringClass());
		assertEquals(null, result[13].getEnclosingClass());
		assertEquals(null, result[13].getEnclosingConstructor());
		assertEquals(null, result[13].getEnclosingMethod());
		assertEquals(null, result[13].getEnumConstants());
		assertEquals(null, result[13].getSigners());
		assertEquals("String", result[13].getSimpleName());
		assertEquals("java.lang.String", result[13].getTypeName());
		assertEquals(false, result[13].isAnnotation());
		assertEquals(false, result[13].isAnonymousClass());
		assertEquals(false, result[13].isEnum());
		assertEquals(false, result[13].isLocalClass());
		assertEquals(false, result[13].isMemberClass());
		assertEquals(false, result[13].isSynthetic());
		assertEquals("", result[13].newInstance());
		assertEquals("public final class java.lang.String", result[13].toGenericString());
		assertNotNull(result[14]);
		assertEquals("class java.lang.String", result[14].toString());
		assertEquals(17, result[14].getModifiers());
		assertEquals(false, result[14].isInterface());
		assertEquals(false, result[14].isArray());
		assertEquals(false, result[14].isPrimitive());
		assertEquals(null, result[14].getComponentType());
		assertEquals("java.lang.String", result[14].getName());
		assertEquals(false, result[14].desiredAssertionStatus());
		assertEquals("java.lang.String", result[14].getCanonicalName());
		assertEquals(null, result[14].getClassLoader());
		assertEquals(null, result[14].getDeclaringClass());
		assertEquals(null, result[14].getEnclosingClass());
		assertEquals(null, result[14].getEnclosingConstructor());
		assertEquals(null, result[14].getEnclosingMethod());
		assertEquals(null, result[14].getEnumConstants());
		assertEquals(null, result[14].getSigners());
		assertEquals("String", result[14].getSimpleName());
		assertEquals("java.lang.String", result[14].getTypeName());
		assertEquals(false, result[14].isAnnotation());
		assertEquals(false, result[14].isAnonymousClass());
		assertEquals(false, result[14].isEnum());
		assertEquals(false, result[14].isLocalClass());
		assertEquals(false, result[14].isMemberClass());
		assertEquals(false, result[14].isSynthetic());
		assertEquals("", result[14].newInstance());
		assertEquals("public final class java.lang.String", result[14].toGenericString());
		assertNotNull(result[15]);
		assertEquals("class java.util.Date", result[15].toString());
		assertEquals(1, result[15].getModifiers());
		assertEquals(false, result[15].isInterface());
		assertEquals(false, result[15].isArray());
		assertEquals(false, result[15].isPrimitive());
		assertEquals(null, result[15].getComponentType());
		assertEquals("java.util.Date", result[15].getName());
		assertEquals(false, result[15].desiredAssertionStatus());
		assertEquals("java.util.Date", result[15].getCanonicalName());
		assertEquals(null, result[15].getClassLoader());
		assertEquals(null, result[15].getDeclaringClass());
		assertEquals(null, result[15].getEnclosingClass());
		assertEquals(null, result[15].getEnclosingConstructor());
		assertEquals(null, result[15].getEnclosingMethod());
		assertEquals(null, result[15].getEnumConstants());
		assertEquals(null, result[15].getSigners());
		assertEquals("Date", result[15].getSimpleName());
		assertEquals("java.util.Date", result[15].getTypeName());
		assertEquals(false, result[15].isAnnotation());
		assertEquals(false, result[15].isAnonymousClass());
		assertEquals(false, result[15].isEnum());
		assertEquals(false, result[15].isLocalClass());
		assertEquals(false, result[15].isMemberClass());
		assertEquals(false, result[15].isSynthetic());
		assertEquals("public class java.util.Date", result[15].toGenericString());
		assertNotNull(result[16]);
		assertEquals("class java.lang.String", result[16].toString());
		assertEquals(17, result[16].getModifiers());
		assertEquals(false, result[16].isInterface());
		assertEquals(false, result[16].isArray());
		assertEquals(false, result[16].isPrimitive());
		assertEquals(null, result[16].getComponentType());
		assertEquals("java.lang.String", result[16].getName());
		assertEquals(false, result[16].desiredAssertionStatus());
		assertEquals("java.lang.String", result[16].getCanonicalName());
		assertEquals(null, result[16].getClassLoader());
		assertEquals(null, result[16].getDeclaringClass());
		assertEquals(null, result[16].getEnclosingClass());
		assertEquals(null, result[16].getEnclosingConstructor());
		assertEquals(null, result[16].getEnclosingMethod());
		assertEquals(null, result[16].getEnumConstants());
		assertEquals(null, result[16].getSigners());
		assertEquals("String", result[16].getSimpleName());
		assertEquals("java.lang.String", result[16].getTypeName());
		assertEquals(false, result[16].isAnnotation());
		assertEquals(false, result[16].isAnonymousClass());
		assertEquals(false, result[16].isEnum());
		assertEquals(false, result[16].isLocalClass());
		assertEquals(false, result[16].isMemberClass());
		assertEquals(false, result[16].isSynthetic());
		assertEquals("", result[16].newInstance());
		assertEquals("public final class java.lang.String", result[16].toGenericString());
		assertNotNull(result[17]);
		assertEquals("class java.lang.String", result[17].toString());
		assertEquals(17, result[17].getModifiers());
		assertEquals(false, result[17].isInterface());
		assertEquals(false, result[17].isArray());
		assertEquals(false, result[17].isPrimitive());
		assertEquals(null, result[17].getComponentType());
		assertEquals("java.lang.String", result[17].getName());
		assertEquals(false, result[17].desiredAssertionStatus());
		assertEquals("java.lang.String", result[17].getCanonicalName());
		assertEquals(null, result[17].getClassLoader());
		assertEquals(null, result[17].getDeclaringClass());
		assertEquals(null, result[17].getEnclosingClass());
		assertEquals(null, result[17].getEnclosingConstructor());
		assertEquals(null, result[17].getEnclosingMethod());
		assertEquals(null, result[17].getEnumConstants());
		assertEquals(null, result[17].getSigners());
		assertEquals("String", result[17].getSimpleName());
		assertEquals("java.lang.String", result[17].getTypeName());
		assertEquals(false, result[17].isAnnotation());
		assertEquals(false, result[17].isAnonymousClass());
		assertEquals(false, result[17].isEnum());
		assertEquals(false, result[17].isLocalClass());
		assertEquals(false, result[17].isMemberClass());
		assertEquals(false, result[17].isSynthetic());
		assertEquals("", result[17].newInstance());
		assertEquals("public final class java.lang.String", result[17].toGenericString());
		assertNotNull(result[18]);
		assertEquals("class java.lang.String", result[18].toString());
		assertEquals(17, result[18].getModifiers());
		assertEquals(false, result[18].isInterface());
		assertEquals(false, result[18].isArray());
		assertEquals(false, result[18].isPrimitive());
		assertEquals(null, result[18].getComponentType());
		assertEquals("java.lang.String", result[18].getName());
		assertEquals(false, result[18].desiredAssertionStatus());
		assertEquals("java.lang.String", result[18].getCanonicalName());
		assertEquals(null, result[18].getClassLoader());
		assertEquals(null, result[18].getDeclaringClass());
		assertEquals(null, result[18].getEnclosingClass());
		assertEquals(null, result[18].getEnclosingConstructor());
		assertEquals(null, result[18].getEnclosingMethod());
		assertEquals(null, result[18].getEnumConstants());
		assertEquals(null, result[18].getSigners());
		assertEquals("String", result[18].getSimpleName());
		assertEquals("java.lang.String", result[18].getTypeName());
		assertEquals(false, result[18].isAnnotation());
		assertEquals(false, result[18].isAnonymousClass());
		assertEquals(false, result[18].isEnum());
		assertEquals(false, result[18].isLocalClass());
		assertEquals(false, result[18].isMemberClass());
		assertEquals(false, result[18].isSynthetic());
		assertEquals("", result[18].newInstance());
		assertEquals("public final class java.lang.String", result[18].toGenericString());
		assertNotNull(result[19]);
		assertEquals("class java.util.Date", result[19].toString());
		assertEquals(1, result[19].getModifiers());
		assertEquals(false, result[19].isInterface());
		assertEquals(false, result[19].isArray());
		assertEquals(false, result[19].isPrimitive());
		assertEquals(null, result[19].getComponentType());
		assertEquals("java.util.Date", result[19].getName());
		assertEquals(false, result[19].desiredAssertionStatus());
		assertEquals("java.util.Date", result[19].getCanonicalName());
		assertEquals(null, result[19].getClassLoader());
		assertEquals(null, result[19].getDeclaringClass());
		assertEquals(null, result[19].getEnclosingClass());
		assertEquals(null, result[19].getEnclosingConstructor());
		assertEquals(null, result[19].getEnclosingMethod());
		assertEquals(null, result[19].getEnumConstants());
		assertEquals(null, result[19].getSigners());
		assertEquals("Date", result[19].getSimpleName());
		assertEquals("java.util.Date", result[19].getTypeName());
		assertEquals(false, result[19].isAnnotation());
		assertEquals(false, result[19].isAnonymousClass());
		assertEquals(false, result[19].isEnum());
		assertEquals(false, result[19].isLocalClass());
		assertEquals(false, result[19].isMemberClass());
		assertEquals(false, result[19].isSynthetic());
		assertEquals("public class java.util.Date", result[19].toGenericString());
		assertNotNull(result[20]);
		assertEquals("class java.lang.String", result[20].toString());
		assertEquals(17, result[20].getModifiers());
		assertEquals(false, result[20].isInterface());
		assertEquals(false, result[20].isArray());
		assertEquals(false, result[20].isPrimitive());
		assertEquals(null, result[20].getComponentType());
		assertEquals("java.lang.String", result[20].getName());
		assertEquals(false, result[20].desiredAssertionStatus());
		assertEquals("java.lang.String", result[20].getCanonicalName());
		assertEquals(null, result[20].getClassLoader());
		assertEquals(null, result[20].getDeclaringClass());
		assertEquals(null, result[20].getEnclosingClass());
		assertEquals(null, result[20].getEnclosingConstructor());
		assertEquals(null, result[20].getEnclosingMethod());
		assertEquals(null, result[20].getEnumConstants());
		assertEquals(null, result[20].getSigners());
		assertEquals("String", result[20].getSimpleName());
		assertEquals("java.lang.String", result[20].getTypeName());
		assertEquals(false, result[20].isAnnotation());
		assertEquals(false, result[20].isAnonymousClass());
		assertEquals(false, result[20].isEnum());
		assertEquals(false, result[20].isLocalClass());
		assertEquals(false, result[20].isMemberClass());
		assertEquals(false, result[20].isSynthetic());
		assertEquals("", result[20].newInstance());
		assertEquals("public final class java.lang.String", result[20].toGenericString());
		assertNotNull(result[21]);
		assertEquals("class java.lang.String", result[21].toString());
		assertEquals(17, result[21].getModifiers());
		assertEquals(false, result[21].isInterface());
		assertEquals(false, result[21].isArray());
		assertEquals(false, result[21].isPrimitive());
		assertEquals(null, result[21].getComponentType());
		assertEquals("java.lang.String", result[21].getName());
		assertEquals(false, result[21].desiredAssertionStatus());
		assertEquals("java.lang.String", result[21].getCanonicalName());
		assertEquals(null, result[21].getClassLoader());
		assertEquals(null, result[21].getDeclaringClass());
		assertEquals(null, result[21].getEnclosingClass());
		assertEquals(null, result[21].getEnclosingConstructor());
		assertEquals(null, result[21].getEnclosingMethod());
		assertEquals(null, result[21].getEnumConstants());
		assertEquals(null, result[21].getSigners());
		assertEquals("String", result[21].getSimpleName());
		assertEquals("java.lang.String", result[21].getTypeName());
		assertEquals(false, result[21].isAnnotation());
		assertEquals(false, result[21].isAnonymousClass());
		assertEquals(false, result[21].isEnum());
		assertEquals(false, result[21].isLocalClass());
		assertEquals(false, result[21].isMemberClass());
		assertEquals(false, result[21].isSynthetic());
		assertEquals("", result[21].newInstance());
		assertEquals("public final class java.lang.String", result[21].toGenericString());
		assertNotNull(result[22]);
		assertEquals("class java.lang.String", result[22].toString());
		assertEquals(17, result[22].getModifiers());
		assertEquals(false, result[22].isInterface());
		assertEquals(false, result[22].isArray());
		assertEquals(false, result[22].isPrimitive());
		assertEquals(null, result[22].getComponentType());
		assertEquals("java.lang.String", result[22].getName());
		assertEquals(false, result[22].desiredAssertionStatus());
		assertEquals("java.lang.String", result[22].getCanonicalName());
		assertEquals(null, result[22].getClassLoader());
		assertEquals(null, result[22].getDeclaringClass());
		assertEquals(null, result[22].getEnclosingClass());
		assertEquals(null, result[22].getEnclosingConstructor());
		assertEquals(null, result[22].getEnclosingMethod());
		assertEquals(null, result[22].getEnumConstants());
		assertEquals(null, result[22].getSigners());
		assertEquals("String", result[22].getSimpleName());
		assertEquals("java.lang.String", result[22].getTypeName());
		assertEquals(false, result[22].isAnnotation());
		assertEquals(false, result[22].isAnonymousClass());
		assertEquals(false, result[22].isEnum());
		assertEquals(false, result[22].isLocalClass());
		assertEquals(false, result[22].isMemberClass());
		assertEquals(false, result[22].isSynthetic());
		assertEquals("", result[22].newInstance());
		assertEquals("public final class java.lang.String", result[22].toGenericString());
		assertNotNull(result[23]);
		assertEquals("class java.util.Date", result[23].toString());
		assertEquals(1, result[23].getModifiers());
		assertEquals(false, result[23].isInterface());
		assertEquals(false, result[23].isArray());
		assertEquals(false, result[23].isPrimitive());
		assertEquals(null, result[23].getComponentType());
		assertEquals("java.util.Date", result[23].getName());
		assertEquals(false, result[23].desiredAssertionStatus());
		assertEquals("java.util.Date", result[23].getCanonicalName());
		assertEquals(null, result[23].getClassLoader());
		assertEquals(null, result[23].getDeclaringClass());
		assertEquals(null, result[23].getEnclosingClass());
		assertEquals(null, result[23].getEnclosingConstructor());
		assertEquals(null, result[23].getEnclosingMethod());
		assertEquals(null, result[23].getEnumConstants());
		assertEquals(null, result[23].getSigners());
		assertEquals("Date", result[23].getSimpleName());
		assertEquals("java.util.Date", result[23].getTypeName());
		assertEquals(false, result[23].isAnnotation());
		assertEquals(false, result[23].isAnonymousClass());
		assertEquals(false, result[23].isEnum());
		assertEquals(false, result[23].isLocalClass());
		assertEquals(false, result[23].isMemberClass());
		assertEquals(false, result[23].isSynthetic());
		assertEquals("public class java.util.Date", result[23].toGenericString());
	}


	@Test
	public void testGetPeriodConfigurationTableColumns_1()
		throws Exception {

		String[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableColumns();

		assertNotNull(result);
		assertEquals(24, result.length);
		assertEquals("moduleName", result[0]);
		assertEquals("buscinessProcessName", result[1]);
		assertEquals("companyName", result[2]);
		assertEquals("bucinsessUnitName", result[3]);
		assertEquals("periodViewName", result[4]);
		assertEquals("versionNo", result[5]);
		assertEquals("createdBy", result[6]);
		assertEquals("activeFlag", result[7]);
		assertEquals("fromModeName", result[8]);
		assertEquals("fromFrequencyName", result[9]);
		assertEquals("fromPeriodValue", result[10]);
		assertEquals("fromPeriodDate", result[11]);
		assertEquals("fromDefModeName", result[12]);
		assertEquals("fromDefFrequencyName", result[13]);
		assertEquals("fromDefPeriodValue", result[14]);
		assertEquals("fromDefPeriodDate", result[15]);
		assertEquals("toModeName", result[16]);
		assertEquals("toFrequencyName", result[17]);
		assertEquals("toPeriodValue", result[18]);
		assertEquals("toPeriodDate", result[19]);
		assertEquals("toDefModeName", result[20]);
		assertEquals("toDefFrequencyName", result[21]);
		assertEquals("toDefPeriodValue", result[22]);
		assertEquals("toDefPeriodDate", result[23]);
	}


	@Test
	public void testGetPeriodConfigurationTableFieldFactory_1()
		throws Exception {

		String[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableFieldFactory();

		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals("fromPeriodDate", result[0]);
		assertEquals("fromDefPeriodDate", result[1]);
		assertEquals("toPeriodDate", result[2]);
		assertEquals("toDefPeriodDate", result[3]);
	}


	@Test
	public void testGetPeriodConfigurationTableHeader_1()
		throws Exception {

		String[] result = GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableHeader();

		assertNotNull(result);
		assertEquals(24, result.length);
		assertEquals("Module", result[0]);
		assertEquals("Business Process", result[1]);
		assertEquals("Company", result[2]);
		assertEquals("Business Unit", result[3]);
		assertEquals("Period View", result[4]);
		assertEquals("Version", result[5]);
		assertEquals("Created By", result[6]);
		assertEquals("Active Flag", result[7]);
		assertEquals(" Mode", result[8]);
		assertEquals(" Frequency", result[9]);
		assertEquals(" Period", result[10]);
		assertEquals(" Date", result[11]);
		assertEquals(" Default Mode", result[12]);
		assertEquals(" Default Frequency", result[13]);
		assertEquals(" Default Period", result[14]);
		assertEquals(" Default Date", result[15]);
		assertEquals(" Mode", result[16]);
		assertEquals(" Frequency", result[17]);
		assertEquals(" Period", result[18]);
		assertEquals(" Date", result[19]);
		assertEquals(" Default Mode", result[20]);
		assertEquals(" Default Frequency", result[21]);
		assertEquals(" Default Period", result[22]);
		assertEquals(" Default Date", result[23]);
	}


	@Test
	public void testGetToFields_1()
		throws Exception {

		String[] result = GtnFrameworkPeriodConfigurationContants.getToFields();

		assertNotNull(result);
		assertEquals(10, result.length);
		assertEquals("modeTo", result[0]);
		assertEquals("defaultModeTo", result[1]);
		assertEquals("frequencyTo", result[2]);
		assertEquals("defaultFrequencyTo", result[3]);
		assertEquals("periodTo", result[4]);
		assertEquals("defaultPeriodTo", result[5]);
		assertEquals("periodToTextBox", result[6]);
		assertEquals("defaultPeriodToTextBox", result[7]);
		assertEquals("dateTo", result[8]);
		assertEquals("defaultDateTo", result[9]);
	}


}