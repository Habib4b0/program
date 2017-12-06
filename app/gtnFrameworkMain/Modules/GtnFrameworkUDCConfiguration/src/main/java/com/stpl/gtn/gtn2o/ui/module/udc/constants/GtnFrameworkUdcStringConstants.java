package com.stpl.gtn.gtn2o.ui.module.udc.constants;

public class GtnFrameworkUdcStringConstants {

	private GtnFrameworkUdcStringConstants() {

	}

	private static final Object[] UDC_TABLE_COLUMNS = new Object[] { "description", "udcCategory" };

	private static final String[] UDC_TABLE_HEADER = new String[] { "Description", "Category" };

	private static final Class<?>[] UDC_TABLE_COLUMN_TYPE = new Class<?>[] { String.class, String.class };

	private static final Object[] UDC_BRANDTABLE_COLUMNS = new Object[] { "brandId", "brandName", "displayBrand",
			"category" };

	private static final String[] UDC_BRANDTABLE_HEADER = new String[] { "Brand ID", "Brand Name", "Display Brand",
			"Category" };

	private static final Class<?>[] UDC_BRANDTABLE_COLUMN_TYPE = new Class<?>[] { String.class, String.class,
			String.class, String.class };

	public static Object[] getUdcTableColumns() {
		return UDC_TABLE_COLUMNS.clone();
	}

	public static String[] getUdcTableHeader() {
		return UDC_TABLE_HEADER.clone();
	}

	public static Class<?>[] getUdcTableColumnType() {
		return UDC_TABLE_COLUMN_TYPE.clone();
	}

	public static Object[] getUdcBrandTableColumns() {
		return UDC_BRANDTABLE_COLUMNS.clone();
	}

	public static String[] getUdcBrandTableColumnHeader() {
		return UDC_BRANDTABLE_HEADER.clone();
	}

	public static Class<?>[] getUdcBrandTableColumnType() {
		return UDC_BRANDTABLE_COLUMN_TYPE.clone();
	}
}
