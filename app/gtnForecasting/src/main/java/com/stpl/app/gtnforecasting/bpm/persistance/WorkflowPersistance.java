package com.stpl.app.gtnforecasting.bpm.persistance;

import com.stpl.app.gtnforecasting.bpm.persistance.provider.BasePersistanceProvider;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.ifs.util.QueryUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowPersistance extends BasePersistanceProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowPersistance.class);

    public static boolean insertWFInstanceInfo(int projectionId, long processInstanceId) {
        try {

            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (PROJECTION_MASTER_SID,PROCESS_INSTANCE_ID) VALUES(" + projectionId + "," + processInstanceId + ")";

            return executeBulkUpdateQuery(customSql, null, null);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public static List selectWFInstanceInfo(int projectionId) {
        List obj = null;
        try {

            String customSql = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE PROJECTION_MASTER_SID=" + projectionId;

            obj = executeSelectQuery(customSql, null, null);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return obj;
      
    }
    
    public static  List<Object[]> getProjectionRecords(int projectionId,String userId,String sessionId,String screenName,SessionDTO sessionDto) {
        List<Object[]> obj = null;
        try {
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                String customSql = SQlUtil.getQuery(WorkflowPersistance.class,"getProjectionRecordsForReturns");
                customSql = customSql.replace(Constant.QUESTION_PROJECTION_ID, String.valueOf(projectionId));
                obj = executeSelectQuery(QueryUtil.replaceTableNames(customSql,sessionDto.getCurrentTableNames()), null, null);
            } else {
                String customSql =SQlUtil.getQuery(WorkflowPersistance.class,"getProjectionRecords");
                customSql=customSql.replace(Constant.QUESTION_PROJECTION_ID, String.valueOf(projectionId));
                customSql=customSql.replace("?USER_ID", String.valueOf(userId));
                customSql=customSql.replace("?SESSION_ID", String.valueOf(sessionId));
                obj = executeSelectQuery(customSql, null, null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return obj;
        
        		
        		
    }
    
    public static  List<Object[]> getProjectionRecordsForAccrual(int projectionId,String userId,String sessionId) {
        List<Object[]> obj = null;
        try {
            String customSql =SQlUtil.getQuery("getProjectionRecordsForAccrual-ARP");
            customSql=customSql.replace(Constant.QUESTION_PROJECTION_ID, String.valueOf(projectionId));
            customSql=customSql.replace("?USER_ID", String.valueOf(userId));
            customSql=customSql.replace("?SESSION_ID", String.valueOf(sessionId));
            obj = executeSelectQuery(customSql, null, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return obj;
    }
    
}
