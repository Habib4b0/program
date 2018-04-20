package com.stpl.app.arm.bpm.persistance;

import com.stpl.app.arm.bpm.persistance.provider.BasePersistanceProvider;
import java.util.List;

public class WorkflowPersistance extends BasePersistanceProvider {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WorkflowPersistance.class);

    public static boolean insertWFInstanceInfo(int projectionId, long processInstanceId) {
        try {

            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (PROJECTION_MASTER_SID,PROCESS_INSTANCE_ID) VALUES(" + projectionId + "," + processInstanceId + ")";

            return executeBulkUpdateQuery(customSql, null, null);

        } catch (Exception e) {
            LOGGER.error("Error in insertWFInstanceInfo" + e);
            return false;
        }
    }

    public static List selectWFInstanceInfo(int projectionId) {
        List obj = null;
        try {

            String customSql = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE PROJECTION_MASTER_SID=" + projectionId;

            obj = executeSelectQuery(customSql, null, null);

        } catch (Exception e) {
            LOGGER.error("Error in selectWFInstanceInfo" + e);
        }
        return obj;

    }

    public static List<Object[]> getProjectionRecordsForAccrual(int projectionId, String userId, String sessionId) {
        List<Object[]> obj = null;
//        try {
//            String customSql = CustomSQLUtil.get("getProjectionRecordsForAccrual");
//            customSql = customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
//            customSql = customSql.replace("?USER_ID", String.valueOf(userId));
//            customSql = customSql.replace("?SESSION_ID", String.valueOf(sessionId));
//            obj = executeSelectQuery(customSql, null, null);
//        } catch (Exception e) {
//            LOGGER.error("Error in getProjectionRecordsForAccrual" + e);
//        }
        return obj;
    }

}
