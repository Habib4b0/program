/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.util;

import com.stpl.app.adminconsole.util.QueryReader;
import com.stpl.app.adminconsole.util.SysDataSourceConnection;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Jayaram
 */
public class CommonUtil {
    
    public static String workFlowQuery(int start, int offset,boolean count, boolean scheduler,String orderByColumn,boolean orderBy) throws SQLException {
        String query;
        String query1 = "?";

        if (count) {
            query = QueryReader.getQuery("processSchedulerLoadingProcessCount");
        } else {
            query = QueryReader.getQuery("processSchedulerLoadingProcess");
        }
        if (scheduler) {
            try (Connection connection = SysDataSourceConnection.getConnection()) {
                query += " LEFT JOIN " + query1 + ".dbo.User_ USR ON USR.userId = WP.MODIFIED_BY WHERE WP.MODIFIED_BY LIKE '%' AND HTF.DESCRIPTION LIKE 'Automatic' OR HTF.DESCRIPTION LIKE 'Both' OR HTF.DESCRIPTION LIKE '-Select One-' OR HTF.DESCRIPTION is null";
                query = query.replace("?", connection.getCatalog());
            }

        } else {
            query += " WHERE HTF.DESCRIPTION LIKE 'Manual' OR HTF.DESCRIPTION LIKE 'Both' OR HTF.DESCRIPTION LIKE '-Select One-' OR HTF.DESCRIPTION is null AND WP.PROCESS_NAME not in (SELECT DESCRIPTION from HELPER_TABLE where LIST_NAME like 'File_Type')";
        }
        if (!count) {
            if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                query += " ORDER BY WP.PROCESS_NAME ";
            } else {
                query += " ORDER BY " + orderByColumn + (!orderBy ? " ASC " : " DESC ");
            }
            query += "OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        }

        return query;
    }

    public static String processNameDDlbQuery() {
        String query;
        query = "SELECT WP.PROCESS_SID, WP.PROCESS_DISPLAY_NAME \n"
                + ",WP.EMAIL_NOTIFICATION_SUCCESS_TO ,WP.EMAIL_NOTIFICATION_SUCCESS_CC"
                + " ,WP.EMAIL_NOTIFICATION_FAILURE_TO,WP.EMAIL_NOTIFICATION_FAILURE_CC"
                + " ,WP.SUCCESS_MAIL_SUBJECT,WP.SUCCESS_MAIL_BODY "
                + " ,WP.FAILURE_MAIL_SUBJECT,WP.FAILURE_MAIL_BODY "
                + " FROM WORKFLOW_PROFILE WP \n"
              ;

        return query;
    }
    /**
     * To get the Hierarchy definition outbound process table single and double columns based on total level parameter
     * @param tableHeaderDTO
     * @param totalLevel
     * @return 
     */
       public static CustomTableHeaderDTO getHdOutboundCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, int totalLevel) {
        Object[] singleCol = {"hierarchyName", "hierarchyType", "hierarchyCategory", "nooflevels", "version"};
        Object[] hierarchyName = {"hierarchyName"};
        Object[] hierarchyType = {"hierarchyType"};
        Object[] hierarchyCategory = {"hierarchyCategory"};
        Object[] nooflevels = {"nooflevels"};
        Object[] version = {"version"};
        tableHeaderDTO.addSingleColumn(singleCol[0], "Hierarchy Name", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[0], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[0], hierarchyName);

        tableHeaderDTO.addSingleColumn(singleCol[1], "Hierarchy Type", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[1], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[1], hierarchyType);

        tableHeaderDTO.addSingleColumn(singleCol[NumericConstants.TWO], "Hierarchy Category", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[NumericConstants.TWO], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[NumericConstants.TWO], hierarchyCategory);

        tableHeaderDTO.addSingleColumn(singleCol[NumericConstants.THREE], "No of levels", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[NumericConstants.THREE], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[NumericConstants.THREE], nooflevels);
        tableHeaderDTO.addSingleColumn(singleCol[NumericConstants.FOUR], "Version", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[NumericConstants.FOUR], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[NumericConstants.FOUR], version);

        for (int i = 1; i <= totalLevel; i++) {
            String commonHeader = "Level " + i; 
            String commonColumn = "level" + i; 

            List<Object> dmap = new ArrayList<>();

            Object singleColumn = commonColumn + "LevelName";
            dmap.add(singleColumn);

            tableHeaderDTO.addSingleColumn(singleColumn, "Level Name", String.class);

            Object singleColumnlvr = commonColumn + "LevelValueReference";
            dmap.add(singleColumnlvr);

            tableHeaderDTO.addSingleColumn(singleColumnlvr, "Level Value Reference", String.class);

            Object singleColumntn = commonColumn + "TableName";
            dmap.add(singleColumntn);

            tableHeaderDTO.addSingleColumn(singleColumntn, "Table Name", String.class);

            Object singleColumnfn = commonColumn + "FieldName";
            dmap.add(singleColumnfn);

            tableHeaderDTO.addSingleColumn(singleColumnfn, "Field Name", String.class);

            Object singleColumnIrt = commonColumn + "InclusionRuleType";
            dmap.add(singleColumnIrt);

            tableHeaderDTO.addSingleColumn(singleColumnIrt, "Inclusion Rule Type", String.class);

            Object singleColumnIr = commonColumn + "InclusionRule";
            dmap.add(singleColumnIr);

            tableHeaderDTO.addSingleColumn(singleColumnIr, "Inclusion Rule", String.class);

            Object singleColumnIc = commonColumn + "InclusionCondition";
            dmap.add(singleColumnIc);

            tableHeaderDTO.addSingleColumn(singleColumnIc, "Inclusion Condition", String.class);

            Object singleColumnErt = commonColumn + "ExclusionRuleType";
            dmap.add(singleColumnErt);

            tableHeaderDTO.addSingleColumn(singleColumnErt, "Exclusion Rule Type", String.class);

            Object singleColumnEr = commonColumn + "ExclusionRule";
            dmap.add(singleColumnEr);

            tableHeaderDTO.addSingleColumn(singleColumnEr, "Exclusion Rule", String.class);

            Object singleColumnEc = commonColumn + "ExclusionCondition";
            dmap.add(singleColumnEc);

            tableHeaderDTO.addSingleColumn(singleColumnEc, "Exclusion Condition", String.class);

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }


        return tableHeaderDTO;
    }
}
