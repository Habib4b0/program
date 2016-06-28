/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.util;

import com.stpl.app.adminconsole.util.QueryReader;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jayaram
 */
public class CommonUtil {
    
    public static String workFlowQuery(int start, int offset,boolean count, boolean scheduler) {
        String query;
   
            query = QueryReader.getQuery("processSchedulerLoadingProcess");
                if(scheduler) {
                   query += " WHERE HTF.DESCRIPTION LIKE 'Automatic' OR HTF.DESCRIPTION LIKE 'Both' OR HTF.DESCRIPTION LIKE '-Select One-' OR HTF.DESCRIPTION is null \n";
                } else {
                   query += " WHERE HTF.DESCRIPTION LIKE 'Manual' OR HTF.DESCRIPTION LIKE 'Both' OR HTF.DESCRIPTION LIKE '-Select One-' OR HTF.DESCRIPTION is null";
                }
                if(!count){
                   query += "  ORDER BY WP.PROCESS_NAME OFFSET "+start+" ROWS FETCH NEXT "+offset+" ROWS ONLY"; 
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

        tableHeaderDTO.addSingleColumn(singleCol[2], "Hierarchy Category", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[2], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[2], hierarchyCategory);

        tableHeaderDTO.addSingleColumn(singleCol[3], "No of levels", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[3], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[3], nooflevels);
        tableHeaderDTO.addSingleColumn(singleCol[4], "Version", String.class);
        tableHeaderDTO.addDoubleColumn(singleCol[4], " ");
        tableHeaderDTO.addDoubleHeaderMap(singleCol[4], version);

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
