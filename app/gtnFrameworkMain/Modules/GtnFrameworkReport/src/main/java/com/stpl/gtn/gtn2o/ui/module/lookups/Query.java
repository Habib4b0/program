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
   public static   final String getCountProductHierarchy="SELECT\n" +
"                    Count(distinct c.HIERARCHY_DEFINITION_SID) AS HIERARCHY_DEFINITION_SID\n" +
"                FROM\n" +
"                    dbo.HIERARCHY_LEVEL_DEFINITION A,\n" +
"                    HIERARCHY_LEVEL_DEFINITION b,\n" +
"                    dbo.HIERARCHY_DEFINITION C\n" +
"                WHERE\n" +
"                    a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n" +
"                    AND a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n" +
"                    AND c.HIERARCHY_CATEGORY IN(\n" +
"                        SELECT\n" +
"                            ht.HELPER_TABLE_SID\n" +
"                        FROM\n" +
"                            dbo.HELPER_TABLE ht\n" +
"                        WHERE\n" +
"                            ht.LIST_NAME = 'HIERARCHY_CATEGORY'\n" +
"                            AND ht.DESCRIPTION LIKE 'Product Hierarchy'\n" +
"                    )\n" +
"                    AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n" +
"                    AND a.LEVEL_NO IN(\n" +
"                        SELECT\n" +
"                            MAX(h.LEVEL_NO)\n" +
"                        FROM\n" +
"                            HIERARCHY_LEVEL_DEFINITION h\n" +
"                        WHERE\n" +
"                            h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID\n" +
"                    )\n" +
"                    AND b.LEVEL_NO IN(\n" +
"                        SELECT\n" +
"                            MIN(h.LEVEL_NO)\n" +
"                        FROM\n" +
"                            HIERARCHY_LEVEL_DEFINITION h\n" +
"                        WHERE\n" +
"                            h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n" +
"                    )\n" +
"                    AND c.HIERARCHY_NAME LIKE '?'\n" +
"                    AND c.HIERARCHY_TYPE IN(\n" +
"                        SELECT\n" +
"                            ht.HELPER_TABLE_SID\n" +
"                        FROM\n" +
"                            dbo.HELPER_TABLE ht\n" +
"                        WHERE\n" +
"                            ht.DESCRIPTION LIKE '?'\n" +
"                    )\n" ;
//"                    ? ? ? ? ? ";
    
    
  public static final  String  getDataProductHierarchy="SELECT\n" +
"                	distinct\n" +
"                    c.HIERARCHY_NAME as hierName,\n" +
"                    a.LEVEL_NO as highestLevel,\n" +
"                    b.LEVEL_NO as lowestLevel,\n" +
"                    c.CREATED_DATE as createdDate,\n" +
"                    c.MODIFIED_DATE as modifiedDate,\n" +
"                    c.HIERARCHY_DEFINITION_SID\n" +
"                FROM\n" +
"                    dbo.HIERARCHY_LEVEL_DEFINITION A,\n" +
"                    HIERARCHY_LEVEL_DEFINITION b,\n" +
"                    dbo.HIERARCHY_DEFINITION C\n" +
"                WHERE\n" +
"                    a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n" +
"                    AND a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n" +
"                    AND c.HIERARCHY_CATEGORY IN(\n" +
"                        SELECT\n" +
"                            ht.HELPER_TABLE_SID\n" +
"                        FROM\n" +
"                            dbo.HELPER_TABLE ht\n" +
"                        WHERE\n" +
"                            ht.LIST_NAME = 'HIERARCHY_CATEGORY'\n" +
"                            AND ht.DESCRIPTION LIKE 'Product Hierarchy'\n" +
"                    )\n" +
"                    AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID\n" +
"                    AND a.LEVEL_NO IN(\n" +
"                        SELECT\n" +
"                            MAX(h.LEVEL_NO)\n" +
"                        FROM\n" +
"                            HIERARCHY_LEVEL_DEFINITION h\n" +
"                        WHERE\n" +
"                            h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID\n" +
"                    )\n" +
"                    AND b.LEVEL_NO IN(\n" +
"                        SELECT\n" +
"                            MIN(h.LEVEL_NO)\n" +
"                        FROM\n" +
"                            HIERARCHY_LEVEL_DEFINITION h\n" +
"                        WHERE\n" +
"                            h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID\n" +
"                    )\n" +
"                    AND c.HIERARCHY_NAME LIKE '?'\n" +
"                    AND c.HIERARCHY_TYPE IN(\n" +
"                        SELECT\n" +
"                            ht.HELPER_TABLE_SID\n" +
"                        FROM\n" +
"                            dbo.HELPER_TABLE ht\n" +
"                        WHERE\n" +
"                            ht.DESCRIPTION LIKE '?'\n" +
"                    )\n" +
//"                    ?\n" +
//"                    ?\n" +
//"                    ? ? ?\n" +
"                    ORDER BY HIERARCHY_NAME OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
}
