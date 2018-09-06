package com.stpl.app.arm.bpm.persistance;

import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import java.util.List;

public class WorkflowPersistance {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WorkflowPersistance.class);

    public static boolean insertWFInstanceInfo(int projectionId, long processInstanceId) {
        try {

            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (PROJECTION_MASTER_SID,PROCESS_INSTANCE_ID) VALUES(" + projectionId + ARMUtils.COMMA_CHAR + processInstanceId + ARMUtils.CLOSE_BRACES;
            int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(customSql);
            return count > 0;

        } catch (Exception e) {
            LOGGER.error("Error in insertWFInstanceInfo", e);
            return false;
        }
    }

    public static List selectWFInstanceInfo(int projectionId) {
        List obj = null;
        try {

            String customSql = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE PROJECTION_MASTER_SID=" + projectionId;

            obj = HelperTableLocalServiceUtil.executeSelectQuery(customSql);

        } catch (Exception e) {
            LOGGER.error("Error in selectWFInstanceInfo", e);
        }
        return obj;

    }

    public static List<Object[]> getProjectionRecordsForAccrual(int projectionId, String userId, String sessionId) {
        List<Object[]> obj = null;
        try {
            String customSql = SQlUtil.getQuery("getProjectionRecordsForAccrual");
            customSql = customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
            customSql = customSql.replace("?USER_ID", String.valueOf(userId));
            customSql = customSql.replace("?SESSION_ID", String.valueOf(sessionId));
            obj = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error("Error in getProjectionRecordsForAccrual", e);
        }
        return obj;
    }

}
