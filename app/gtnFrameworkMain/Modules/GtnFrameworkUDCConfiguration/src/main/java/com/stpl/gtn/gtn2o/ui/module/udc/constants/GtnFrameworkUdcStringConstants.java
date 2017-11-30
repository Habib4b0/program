package com.stpl.gtn.gtn2o.ui.module.udc.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkUdcStringConstants {

	private static final Object[] UDC_TABLE_COLUMNS = new Object[] { "description", "udcCategory" };

	private static final String[] UDC_TABLE_HEADER = new String[] { "Description", "Category" };

	private static final Class<?>[] UDC_TABLE_COLUMN_TYPE = new Class<?>[] {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING };

	public static Object[] getUdcTableColumns() {
		return UDC_TABLE_COLUMNS.clone();
	}

	public static String[] getUdcTableHeader() {
		return UDC_TABLE_HEADER.clone();
	}

	public static Class<?>[] getUdcTableColumnType() {
		return UDC_TABLE_COLUMN_TYPE.clone();
	}
}
