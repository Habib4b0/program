package com.stpl.gtn.gtn2o.ui.module.udc.constants;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkUdcStringConstantsTest {

	@Test
	public void testGetUdcBrandTableColumnHeader_1()
		throws Exception {
		String[] result = GtnFrameworkUdcStringConstants.getUdcBrandTableColumnHeader();
		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals("Brand ID", result[0]);
		assertEquals("Brand Name", result[1]);
		assertEquals("Display Brand", result[2]);
		assertEquals("Category", result[3]);
	}


	@Test
	public void testGetUdcBrandTableColumnType_1()
		throws Exception {

		Class<Object>[] result = (Class<Object>[]) GtnFrameworkUdcStringConstants.getUdcBrandTableColumnType();

		// add additional test code here
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

	/**
	 * Run the Object[] getUdcBrandTableColumns() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/19/18 3:57 PM
	 */
	@Test
	public void testGetUdcBrandTableColumns_1()
		throws Exception {

		Object[] result = GtnFrameworkUdcStringConstants.getUdcBrandTableColumns();

		// add additional test code here
		assertNotNull(result);
		assertEquals(4, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
		assertNotNull(result[3]);
	}

	/**
	 * Run the String[] getUdcFileTypeTableColumnHeader() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/19/18 3:57 PM
	 */
	@Test
	public void testGetUdcFileTypeTableColumnHeader_1()
		throws Exception {

		String[] result = GtnFrameworkUdcStringConstants.getUdcFileTypeTableColumnHeader();

		// add additional test code here
		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals("Description", result[0]);
		assertEquals("Category", result[1]);
		assertEquals("Alias Name", result[2]);
	}

	/**
	 * Run the Class<Object>[] getUdcFileTypeTableColumnType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/19/18 3:57 PM
	 */
	@Test
	public void testGetUdcFileTypeTableColumnType_1()
		throws Exception {

		Class<Object>[] result = (Class<Object>[]) GtnFrameworkUdcStringConstants.getUdcFileTypeTableColumnType();

		// add additional test code here
		assertNotNull(result);
		assertEquals(3, result.length);
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
	}

	@Test
	public void testGetUdcFileTypeTableColumns_1()
		throws Exception {
		Object[] result = GtnFrameworkUdcStringConstants.getUdcFileTypeTableColumns();
		assertNotNull(result);
		assertEquals(3, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
	}

	@Test
	public void testGetUdcTableColumnType_1()
		throws Exception {

		Class<Object>[] result = (Class<Object>[]) GtnFrameworkUdcStringConstants.getUdcTableColumnType();
		assertNotNull(result);
		assertEquals(2, result.length);
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
	}

	@Test
	public void testGetUdcTableColumns_1()
		throws Exception {
		Object[] result = GtnFrameworkUdcStringConstants.getUdcTableColumns();
		assertNotNull(result);
		assertEquals(2, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
	}

	@Test
	public void testGetUdcTableHeader_1()
		throws Exception {
		String[] result = GtnFrameworkUdcStringConstants.getUdcTableHeader();
		assertNotNull(result);
		assertEquals(2, result.length);
		assertEquals("Description", result[0]);
		assertEquals("Category", result[1]);
	}

}