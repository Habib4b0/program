package com.stpl.app.accountclose.bpm.persistance;

import java.util.List;

import com.stpl.app.accountclose.bpm.persistance.provider.BasePersistanceProvider;
import com.stpl.util.dao.orm.CustomSQLUtil;
import org.jboss.logging.Logger;

public class WorkflowPersistance extends BasePersistanceProvider {
    
    public static final Logger LOGGER = Logger.getLogger(WorkflowPersistance.class);

    public static int insertWFInstanceInfo(int projectionId, long processInstanceId) {
        try {
            LOGGER.info("insertWFInstanceInfo projectionId: "+projectionId+" processInstanceId: "+processInstanceId);
            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (ACC_CLOSURE_MASTER_SID,PROCESS_INSTANCE_ID) VALUES(" + projectionId + "," + processInstanceId + ")";

            return executeUpdateQuery(customSql);

        } catch (Exception e) {
            LOGGER.error(e);
            return 0;
        }
    }

    public static List selectWFInstanceInfo(int projectionId) {
        List obj = null;
        try {
            LOGGER.info("insertWFInstanceInfo projectionId: "+projectionId);

            String customSql = "SELECT DISTINCT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE ACC_CLOSURE_MASTER_SID=" + projectionId;

            obj = executeSelectQuery(customSql, null, null);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return obj;
    }
    
    public static  List<Object[]> getProjectionRecords(int projectionId,String userId,String sessionId) {
        List<Object[]> obj = null;
        try {
            String customSql =CustomSQLUtil.get("getProjectionRecords");
            customSql=customSql.replace("[$PROJECTIONID]", String.valueOf(projectionId));
            obj = executeSelectQuery(customSql, null, null);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return obj;
    }
}
