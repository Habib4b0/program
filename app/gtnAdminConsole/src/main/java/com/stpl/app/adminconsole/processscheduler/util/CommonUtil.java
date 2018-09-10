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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram
 */
public class CommonUtil {
    private static final Object[] SINGLE_COLUMN = {"hierarchyName", "hierarchyType", "hierarchyCategory", "nooflevels", "version"};
    private static final Object[] HIERARCHY_NAME_ARY = {"hierarchyName"};
    private static final Object[] HIERARCHY_TYPE_ARY = {"hierarchyType"};
    private static final Object[] HIERARCHY_CATEGORY = {"hierarchyCategory"};
    private static final Object[] NO_OF_LEVELS = {"nooflevels"};
    private static final Object[] VERSION = {"version"};
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
    private CommonUtil() {
        LOGGER.debug("Entering CommonUtil ");
    }
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
        tableHeaderDTO.addSingleColumn(SINGLE_COLUMN[0], "Hierarchy Name", String.class);
        tableHeaderDTO.addDoubleColumn(SINGLE_COLUMN, " ");
        tableHeaderDTO.addDoubleHeaderMap(SINGLE_COLUMN, HIERARCHY_NAME_ARY);

        tableHeaderDTO.addSingleColumn(SINGLE_COLUMN[1], "Hierarchy Type", String.class);
        tableHeaderDTO.addDoubleColumn(SINGLE_COLUMN[1], " ");
        tableHeaderDTO.addDoubleHeaderMap(SINGLE_COLUMN[1], HIERARCHY_TYPE_ARY);

        tableHeaderDTO.addSingleColumn(SINGLE_COLUMN[NumericConstants.TWO], "Hierarchy Category", String.class);
        tableHeaderDTO.addDoubleColumn(SINGLE_COLUMN[NumericConstants.TWO], " ");
        tableHeaderDTO.addDoubleHeaderMap(SINGLE_COLUMN[NumericConstants.TWO], HIERARCHY_CATEGORY);

        tableHeaderDTO.addSingleColumn(SINGLE_COLUMN[NumericConstants.THREE], "No of levels", String.class);
        tableHeaderDTO.addDoubleColumn(SINGLE_COLUMN[NumericConstants.THREE], " ");
        tableHeaderDTO.addDoubleHeaderMap(SINGLE_COLUMN[NumericConstants.THREE], NO_OF_LEVELS);
        tableHeaderDTO.addSingleColumn(SINGLE_COLUMN[NumericConstants.FOUR], "Version", String.class);
        tableHeaderDTO.addDoubleColumn(SINGLE_COLUMN[NumericConstants.FOUR], " ");
        tableHeaderDTO.addDoubleHeaderMap(SINGLE_COLUMN[NumericConstants.FOUR], VERSION);
        List<Object> dmap = new ArrayList<>();

        for (int i = 1; i <= totalLevel; i++) {
            String commonHeader = "Level " + i; 
            String commonColumn = "level" + i; 

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
