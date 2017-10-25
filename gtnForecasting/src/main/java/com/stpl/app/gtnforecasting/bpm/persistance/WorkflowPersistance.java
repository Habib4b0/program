package com.stpl.app.gtnforecasting.bpm.persistance;

import com.stpl.app.gtnforecasting.bpm.persistance.provider.BasePersistanceProvider;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.ifs.util.QueryUtil;
import java.util.List;
import com.stpl.util.dao.orm.CustomSQLUtil;

public class WorkflowPersistance extends BasePersistanceProvider {
    
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(WorkflowPersistance.class);

    public static boolean insertWFInstanceInfo(int projectionId, long processInstanceId) {
        try {

            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (PROJECTION_MASTER_SID,PROCESS_INSTANCE_ID) VALUES(" + projectionId + "," + processInstanceId + ")";

            return executeBulkUpdateQuery(customSql, null, null);

        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    public static List selectWFInstanceInfo(int projectionId) {
        List obj = null;
        try {

            String customSql = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE PROJECTION_MASTER_SID=" + projectionId;

            obj = executeSelectQuery(customSql, null, null);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return obj;
      
    }
    
    public static  List<Object[]> getProjectionRecords(int projectionId,String userId,String sessionId,String screenName,SessionDTO sessionDto) {
        List<Object[]> obj = null;
        try {
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                String customSql = CustomSQLUtil.get("getProjectionRecordsForReturns");
                customSql = customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
                obj = executeSelectQuery(QueryUtil.replaceTableNames(customSql,sessionDto.getCurrentTableNames()), null, null);
            } else {
                String customSql =CustomSQLUtil.get("getProjectionRecords");
                customSql=customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
                customSql=customSql.replace("?USER_ID", String.valueOf(userId));
                customSql=customSql.replace("?SESSION_ID", String.valueOf(sessionId));
                obj = executeSelectQuery(customSql, null, null);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return obj;
        
        		
        		
    }
    
    public static  List<Object[]> getProjectionRecordsForAccrual(int projectionId,String userId,String sessionId) {
        List<Object[]> obj = null;
        try {
            String customSql =SQlUtil.getQuery("getProjectionRecordsForAccrual-ARP");
            customSql=customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
            customSql=customSql.replace("?USER_ID", String.valueOf(userId));
            customSql=customSql.replace("?SESSION_ID", String.valueOf(sessionId));
            obj = executeSelectQuery(customSql, null, null);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return obj;
    }
    
}
