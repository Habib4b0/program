package com.stpl.app.contract.bpm.persistance;

import com.stpl.app.contract.bpm.logic.WorkflowLogic;
import com.stpl.app.contract.bpm.persistance.provider.BasePersistanceProvider;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.ifs.util.constants.WorkflowConstants;
import java.sql.Timestamp;
import java.util.List;

public class WorkflowPersistance extends BasePersistanceProvider {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(WorkflowPersistance.class);

    /**
     * Method to insert in WORKFLOW_PROCESS_INFO table
     *
     * @param contractMasterSid
     * @param processInstanceId
     * @param roles
     * @return
     */
    public static boolean insertWFInstanceInfo(int contractMasterSid, long processInstanceId, Integer[] contractStructure) {
        try {
            String conStrut = WorkflowLogic.createContractStructure(contractStructure);
            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (CONTRACT_MASTER_SID,PROCESS_INSTANCE_ID,CONTRACT_STRUCTURE) "
                    + "VALUES(" + contractMasterSid + "," + processInstanceId + ",'" + conStrut + "')";
            LOGGER.debug("insert Query :" + customSql);
            return executeBulkUpdateQuery(customSql, null, null);
        } catch (Exception e) {

            LOGGER.error(e);
            return false;
        }
    }

    /**
     * method to get the process instance id of the corresponding contract
     *
     * @param contractMasterSid
     * @param conStrut
     * @return
     */
    public static int selectWFInstanceInfo(int contractMasterSid, String conStrut) {
        List obj = null;
        try {
            String customSql = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE CONTRACT_MASTER_SID=" + contractMasterSid + " and CONTRACT_STRUCTURE='" + conStrut + "'";
            obj = executeSelectQuery(customSql, null, null);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return (int) (obj == null || obj.isEmpty() || obj.get(0) == null ? 0 : obj.get(0));
    }

    /**
     * method to get the process instance id of the corresponding contract
     *
     * @param dto
     * @param isUpdate
     * @param contractStructure
     * @return
     */
    public static boolean addOrUpdateWorkflowMaster(WorkflowMaster dto, boolean isUpdate, Integer[] contractStructure, Boolean isNewSubmit, long processIntanceId) {
        String customSql = null;
        try {
            String conStrut = WorkflowLogic.createContractStructure(contractStructure);
            if (isUpdate) {
                customSql = "UPDATE WORKFLOW_MASTER SET WORKFLOW_STATUS_ID=" + dto.getWorkflowStatusId() + ",MODIFIED_BY=" + dto.getModifiedBy() + ",MODIFIED_DATE='"
                        + new Timestamp(dto.getModifiedDate().getTime()) + "',CONTRACT_STRUCTURE='" + conStrut + "'";
                if (WorkflowConstants.getApprovedStatus().equals(HelperListUtil.getInstance().getIdDescMap().get(dto.getWorkflowStatusId()))) {
                    customSql += ",APPROVED_BY=" + dto.getApprovedBy() + ",APPROVED_DATE='" + new Timestamp(dto.getModifiedDate().getTime()) + "'";
                }
                customSql += " where WORKFLOW_MASTER_SID=" + dto.getWorkflowMasterSid();

            }
            if (isNewSubmit != null && isNewSubmit) {
                customSql = "INSERT INTO WORKFLOW_MASTER (WORKFLOW_ID,WORKFLOW_STATUS_ID,CREATED_BY,CREATED_DATE,NO_OF_APPROVAL,APPROVAL_LEVEL,CONTRACT_MASTER_SID,CONTRACT_STRUCTURE,MODIFIED_DATE) "
                        + "VALUES ('" + dto.getWorkflowId() + "'," + dto.getWorkflowStatusId() + "," + dto.getCreatedBy() + ",'" + new Timestamp(dto.getCreatedDate().getTime()) + "',"
                        + dto.getNoOfApproval() + "," + dto.getApprovalLevel() + "," + dto.getContractMasterSid() + ",'" + conStrut + "','" + new Timestamp(dto.getModifiedDate().getTime()) + "')";
            } else if (isNewSubmit != null && !isNewSubmit) {
                customSql = "UPDATE WORKFLOW_MASTER SET WORKFLOW_STATUS_ID=" + dto.getWorkflowStatusId() + ",MODIFIED_BY=" + dto.getModifiedBy() + ",MODIFIED_DATE='" + new Timestamp(dto.getModifiedDate().getTime()) + "'"
                        + ",CONTRACT_STRUCTURE='" + conStrut + "', "
                        + "CREATED_BY= '" + dto.getApprovedBy() + "' ,    CREATED_DATE=getdate(), APPROVED_BY=null,  APPROVED_DATE=null where WORKFLOW_MASTER_SID=" + dto.getWorkflowMasterSid() + ";";

                customSql += "UPDATE WORKFLOW_PROCESS_INFO SET PROCESS_INSTANCE_ID=" + processIntanceId + " WHERE CONTRACT_STRUCTURE='" + conStrut + "' AND CONTRACT_MASTER_SID=" + dto.getContractMasterSid() + ";";
            }
            executeBulkUpdateQuery(customSql, null, null);
        } catch (Exception e) {
            LOGGER.error("Pbm while executing query :" + customSql);
            LOGGER.error(e);
            return false;
        }
        return true;
    }

}
