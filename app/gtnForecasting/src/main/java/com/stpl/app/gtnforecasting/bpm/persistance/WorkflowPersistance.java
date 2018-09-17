package com.stpl.app.gtnforecasting.bpm.persistance;

import com.stpl.app.gtnforecasting.bpm.persistance.provider.BasePersistanceProvider;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowPersistance extends BasePersistanceProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowPersistance.class);

    public static boolean insertWFInstanceInfo(int projectionId, long processInstanceId) {
        try {

            String customSqlWF = "INSERT INTO WORKFLOW_PROCESS_INFO (PROJECTION_MASTER_SID,PROCESS_INSTANCE_ID) VALUES(" + projectionId + "," + processInstanceId + ")";

            return executeBulkUpdateQuery(customSqlWF);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return false;
        }
    }

    public static List selectWFInstanceInfo(int projectionId) {
        List object = null;
        try {

            String customSqlWFInfo = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE PROJECTION_MASTER_SID=" + projectionId;

            object = executeSelectQuery(customSqlWFInfo);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return object;
      
    }
}
