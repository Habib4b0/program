/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.lookups;

/**
 *
 * @author Karthik.Raja
 */
public class Query {

	private Query() {
		super();
	}

	private static final String HIERARCHY_LEVEL_DEFINITION_NEWLINE = "                            HIERARCHY_LEVEL_DEFINITION h\n";

	private static final String DBO_HELPER_TABLE_HT_NEWLINE = "                            dbo.HELPER_TABLE ht\n";

	private static final String HT_HELPER_TABLE_SID_NEWLINE = "                            ht.HELPER_TABLE_SID\n";

	private static final String FROM_NEWLINE = "                        FROM\n";

	private static final String SELECT_NEWLINE = "                        SELECT\n";

	private static final String WHERE_NEWLINE = "                        WHERE\n";

	private static final String CLOSING_NEWLINE = "                    )\n";

	public static final String GET_COUNT_PRODUCT_HIERARCHY = "SELECT\n"
			+ "                    Count(distinct c.HIERARCHY_DEFINITION_SID) AS HIERARCHY_DEFINITION_SID\n"
			+ "                FROM\n" + "                    dbo.HIERARCHY_LEVEL_DEFINITION A,\n"
			+ "                    HIERARCHY_LEVEL_DEFINITION b,\n" + "                    dbo.HIERARCHY_DEFINITION C\n"
			+ "                WHERE\n"
			+ "                    a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n"
			+ "                    AND a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n"
			+ "                    AND c.HIERARCHY_CATEGORY IN(\n" + SELECT_NEWLINE + HT_HELPER_TABLE_SID_NEWLINE
			+ FROM_NEWLINE + DBO_HELPER_TABLE_HT_NEWLINE + WHERE_NEWLINE
			+ "                            ht.LIST_NAME = 'HIERARCHY_CATEGORY'\n"
			+ "                            AND ht.DESCRIPTION LIKE 'Product Hierarchy'\n" + CLOSING_NEWLINE
			+ "                    AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n"
			+ "                    AND a.LEVEL_NO IN(\n" + SELECT_NEWLINE
			+ "                            MAX(h.LEVEL_NO)\n" + FROM_NEWLINE + HIERARCHY_LEVEL_DEFINITION_NEWLINE
			+ WHERE_NEWLINE + "                            h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID\n"
			+ CLOSING_NEWLINE + "                    AND b.LEVEL_NO IN(\n" + SELECT_NEWLINE
			+ "                            MIN(h.LEVEL_NO)\n" + FROM_NEWLINE + HIERARCHY_LEVEL_DEFINITION_NEWLINE
			+ WHERE_NEWLINE + "                            h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n"
			+ CLOSING_NEWLINE + "                    AND c.HIERARCHY_NAME LIKE '?'\n"
			+ "                    AND c.HIERARCHY_TYPE IN(\n" + SELECT_NEWLINE + HT_HELPER_TABLE_SID_NEWLINE
			+ FROM_NEWLINE + DBO_HELPER_TABLE_HT_NEWLINE + WHERE_NEWLINE
			+ "                            ht.DESCRIPTION LIKE '?'\n" + "                    )@filter\n";

	public static final String GET_DATA_PRODUCT_HIERARCHY = "SELECT\n" + "                	distinct\n"
			+ "                    c.HIERARCHY_NAME as hierName,\n"
			+ "                    a.LEVEL_NO as highestLevel,\n" + "                    b.LEVEL_NO as lowestLevel,\n"
			+ "                    c.CREATED_DATE as createdDate,\n"
			+ "                    c.MODIFIED_DATE as modifiedDate,\n"
			+ "                    c.HIERARCHY_DEFINITION_SID\n" + "                FROM\n"
			+ "                    dbo.HIERARCHY_LEVEL_DEFINITION A,\n"
			+ "                    HIERARCHY_LEVEL_DEFINITION b,\n" + "                    dbo.HIERARCHY_DEFINITION C\n"
			+ "                WHERE\n"
			+ "                    a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n"
			+ "                    AND a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n"
			+ "                    AND c.HIERARCHY_CATEGORY IN(\n" + SELECT_NEWLINE + HT_HELPER_TABLE_SID_NEWLINE
			+ FROM_NEWLINE + DBO_HELPER_TABLE_HT_NEWLINE + WHERE_NEWLINE
			+ "                            ht.LIST_NAME = 'HIERARCHY_CATEGORY'\n"
			+ "                            AND ht.DESCRIPTION LIKE 'Product Hierarchy'\n" + CLOSING_NEWLINE
			+ "                    AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n"
			+ "                    AND a.LEVEL_NO IN(\n" + SELECT_NEWLINE
			+ "                            MAX(h.LEVEL_NO)\n" + FROM_NEWLINE + HIERARCHY_LEVEL_DEFINITION_NEWLINE
			+ WHERE_NEWLINE + "                            h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID\n"
			+ CLOSING_NEWLINE + "                    AND b.LEVEL_NO IN(\n" + SELECT_NEWLINE
			+ "                            MIN(h.LEVEL_NO)\n" + FROM_NEWLINE + HIERARCHY_LEVEL_DEFINITION_NEWLINE
			+ WHERE_NEWLINE + "                            h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n"
			+ CLOSING_NEWLINE + "                    AND c.HIERARCHY_NAME LIKE '?'\n"
			+ "                    AND c.HIERARCHY_TYPE IN(\n" + SELECT_NEWLINE + HT_HELPER_TABLE_SID_NEWLINE
			+ FROM_NEWLINE + DBO_HELPER_TABLE_HT_NEWLINE + WHERE_NEWLINE
			+ "                            ht.DESCRIPTION LIKE '?'\n" + "                    )@filter\n" +
			// " ?\n" +
			// " ?\n" +
			// " ? ? ?\n" +
			"                    ORDER BY hierName OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
}
