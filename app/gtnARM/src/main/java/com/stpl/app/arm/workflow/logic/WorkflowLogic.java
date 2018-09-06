/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.workflow.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.app.arm.bpm.logic.DataSourceConnection;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.BPIWorkFlowGeneratorXML;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.arm.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.model.DocDetails;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.app.utils.VariableConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author asha
 */
public class WorkflowLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowLogic.class);
    private final String success = ARMUtils.SUCCESS;

    /**
     * Method to Save the data in WorkflowMaster
     *
     * @param workflowMasterDTO
     * @return Status as Saved or Not Saved
     */
    public String saveWorkflowMaster(WorkflowMasterDTO workflowMasterDTO) {
        WorkflowMaster workflowMaster = WorkflowMasterLocalServiceUtil.createWorkflowMaster(0);
        workflowMaster.setWorkflowId(workflowMasterDTO.getWorkflowId());

        workflowMaster.setWorkflowStatusId(workflowMasterDTO.getWorkflowStatus());
        workflowMaster.setProjectionMasterSid(workflowMasterDTO.getProjectionId());
        workflowMaster.setCreatedBy(workflowMasterDTO.getCreatedBy());
        workflowMaster.setCreatedDate(workflowMasterDTO.getCreatedDate());
        workflowMaster.setNotes(workflowMasterDTO.getNotes());
        workflowMaster.setNoOfApproval(workflowMasterDTO.getNoOfApprovals());
        workflowMaster.setApprovalLevel(workflowMasterDTO.getApprovalLevel());
        workflowMaster.setFileName(workflowMasterDTO.getFileName());
        workflowMaster.setWorkflowDescrption(workflowMasterDTO.getWorkflowDescription());
        workflowMaster.setModifiedDate(workflowMasterDTO.getModifiedDate());
        try {
            WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
        } catch (Exception ex) {
            LOGGER.error("Error in saveWorkflowMaster :" , ex);
            return ARMUtils.WORKFLOW_NOT_SAVED;
        }
        return workflowMaster.getWorkflowId();
    }

    /**
     * Method to Save the data in WorkflowMaster based on projectionId & User ID
     *
     * @param projectionId
     * @param userId
     * @return Status as Saved or Not Saved
     * @throws IOException
     */
    public String saveWorkflow(int projectionId, String userId, String notes, int noOfLevels, String screenName, List<NotesDTO> getUploadedData, String description) {
        java.util.Properties path = getPropertyFile(System.getProperty(GtnFrameworkCommonStringConstants.GTNFRAMEWORK_BASE_PATH_PROPERTY));
        String filePath = path.getProperty("Workflowpath");
        String moduleName = StringUtils.EMPTY;
        if (screenName.equals(ARMConstants.getPipelineAccrual())) {
            moduleName = "ARM_TRX1";
        } else if (screenName.equals(ARMConstants.getDemandAccrual())) {
            moduleName = "ARM_TRX2";
        } else if (screenName.equals(ARMConstants.getPipelineInventoryTrueUp())) {
            moduleName = "ARM_TRX3";
        } else if (screenName.equals(ARMConstants.getDemandPaymentsRecon())) {
            moduleName = "ARM_TRX4";
        } else if (screenName.equals(ARMConstants.getDemandReforecastTrueUp())) {
            moduleName = "ARM_TRX5";
        } else if (screenName.equals(ARMConstants.getTransaction6())) {
            moduleName = "ARM_TRX6";
        } else if (screenName.equals(ARMConstants.getTransaction7())) {
            moduleName = "ARM_TRX7";
        } else if (screenName.equals(ARMConstants.getTransaction8())) {
            moduleName = "ARM_TRX8";
        }

        String workflowId = new BPIWorkFlowGeneratorXML().generateId(filePath, moduleName);
        String docDetailsSid = saveDocDetails(getUploadedData);
        WorkflowMasterDTO workflowMasterDTO = setWorkflowMasterDTO(projectionId, workflowId, userId, notes, noOfLevels, docDetailsSid, description);
        return saveWorkflowMaster(workflowMasterDTO);
    }

    /**
     * Method to updateWorkflowMaster From Forecast
     *
     * @param projectionId
     * @return
     */
    public String updateWorkflowFromForecast(int projectionId, String notes, String userId) {
        String workflowId = ARMUtils.WORKFLOW_NOT_SAVED;
        WorkflowMaster workflowMaster = getWorkflowMasterByProjectionId(projectionId);
        workflowMaster.setWorkflowStatusId(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), ARMUtils.WORKFLOW_STATUS));
        workflowMaster.setNotes(notes);
        workflowMaster.setModifiedDate(new Date());
        workflowMaster.setModifiedBy(Integer.parseInt(userId));
        try {
            WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
            workflowId = workflowMaster.getWorkflowId();
        } catch (Exception ex) {
            LOGGER.error("Error in updateWorkflowFromForecast :" , ex);
        }
        return workflowId;
    }

    /**
     * Method to set the va;lues in WF based on projectionId,workflowId,userId
     *
     * @param projectionId
     * @param workflowId
     * @param userId
     * @return WorkflowMasterDTO Object
     */
    public WorkflowMasterDTO setWorkflowMasterDTO(int projectionId, String workflowId, String userId) {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();

        int userIdInt = Integer.parseInt(userId);
        workflowMasterDTO.setWorkflowId(workflowId);

        workflowMasterDTO.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), ARMUtils.WORKFLOW_STATUS));
        workflowMasterDTO.setCreatedBy(userIdInt);
        workflowMasterDTO.setCreatedDate(new Date());
        workflowMasterDTO.setProjectionId(projectionId);
        return workflowMasterDTO;
    }

    /**
     * Method to set the parameters to display
     *
     * @param projectionId - Projection ID
     * @param workflowId - Workflow ID
     * @param userIdInt - User credential
     * @param workflowStatus - status of workflow
     * @return DTO object.
     */
    public WorkflowMasterDTO setWorkflowMasterDTO(int projectionId, int workflowId, int userIdInt, String workflowStatus, String notes, int approvalLevel) {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();
        workflowMasterDTO.setProjectionId(projectionId);
        workflowMasterDTO.setWorkflowMasterSystemId(workflowId);
        workflowMasterDTO.setWorkflowStatusStr(workflowStatus);
        workflowMasterDTO.setModifiedBy(userIdInt);
        workflowMasterDTO.setModifiedDate(new Date());
        if (WorkflowConstants.getApprovedStatus().equalsIgnoreCase(workflowStatus)) {
            workflowMasterDTO.setApprovedBy(userIdInt);
            workflowMasterDTO.setApprovedDate(new Date());
        }
        workflowMasterDTO.setNotes(notes);
        workflowMasterDTO.setApprovalLevel(approvalLevel);
        return workflowMasterDTO;
    }

    /**
     * Method to set the va;lues in WF based on projectionId,workflowId,userId
     *
     * @param projectionId
     * @param workflowId
     * @param userId
     * @param notes
     * @param noOfLevels
     * @param getUploadedData
     * @param description
     * @return WorkflowMasterDTO Object
     */
    public WorkflowMasterDTO setWorkflowMasterDTO(int projectionId, String workflowId, String userId, String notes, int noOfLevels, String getUploadedData, String description) {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();
        int userIdInt = Integer.parseInt(userId);
        workflowMasterDTO.setWorkflowId(workflowId);
        workflowMasterDTO.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), ARMUtils.WORKFLOW_STATUS));
        workflowMasterDTO.setCreatedBy(userIdInt);
        workflowMasterDTO.setCreatedDate(new Date());
        workflowMasterDTO.setProjectionId(projectionId);
        workflowMasterDTO.setNotes(notes);
        workflowMasterDTO.setNoOfApprovals(noOfLevels);
        workflowMasterDTO.setApprovalLevel(1);
        workflowMasterDTO.setFileName(getUploadedData);
        workflowMasterDTO.setWorkflowDescription(description);
        workflowMasterDTO.setModifiedDate(new Date());
        return workflowMasterDTO;
    }

    /**
     * Method to retrieve the Workflow Master data based on
     * workflowMasterSystemId
     *
     * @param workflowMasterSystemId
     * @return Workflow Master Object
     */
    public WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) {
        WorkflowMaster workflowMaster = null;
        try {
            workflowMaster = WorkflowMasterLocalServiceUtil.getWorkflowMaster(workflowMasterSystemId);
            return workflowMaster;
        } catch (Exception ex) {
            LOGGER.error("Error in getWorkflowMaster :" , ex);
        }
        return null;
    }

    /**
     * Method to retrieve the Workflow Master data based on Projection ID
     *
     * @param projectionId
     * @return - Workflow Master Object
     */
    public WorkflowMaster getWorkflowMasterByProjectionId(int projectionId) {
        DynamicQuery workflowMasterDynamicQuery = DynamicQueryFactoryUtil
                .forClass(WorkflowMaster.class);
        workflowMasterDynamicQuery.add(RestrictionsFactoryUtil.eq(ARMUtils.PROJECTION_MASTER_SID,
                projectionId));
        List<WorkflowMaster> resultList;
        try {
            resultList = WorkflowMasterLocalServiceUtil.dynamicQuery(workflowMasterDynamicQuery);
            return resultList.get(0);
        } catch (Exception ex) {
            LOGGER.error("Error in getWorkflowMasterByProjectionId :" , ex);
        }
        return null;
    }

    /**
     * Method to update Workflow Master
     *
     * @param workflowMasterDTO - parameters to be updated
     * @return - Status as Saved or Not Saved
     */
    public String updateWorkflow(WorkflowMasterDTO workflowMasterDTO) {
        WorkflowMaster workflowMaster = getWorkflowMaster(workflowMasterDTO.getWorkflowMasterSystemId());

        try {
            if (workflowMaster != null) {
                workflowMaster.setWorkflowStatusId(getCodeFromHelperTable(workflowMasterDTO.getWorkflowStatusStr(), ARMUtils.WORKFLOW_STATUS));
                workflowMaster.setModifiedBy(workflowMasterDTO.getModifiedBy());
                workflowMaster.setModifiedDate(workflowMasterDTO.getModifiedDate());
                if (WorkflowConstants.getApprovedStatus().equalsIgnoreCase(workflowMasterDTO.getWorkflowStatusStr())) {
                    workflowMaster.setApprovedBy(workflowMasterDTO.getApprovedBy());
                    workflowMaster.setApprovedDate(workflowMasterDTO.getApprovedDate());
                }
                workflowMaster.setNotes(workflowMasterDTO.getNotes());
                workflowMaster.setApprovalLevel(workflowMasterDTO.getApprovalLevel());

                WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
                String projectionUpdated = updateProjectionMaster(workflowMasterDTO);
                if (!projectionUpdated.equals(success)) {
                    return ARMUtils.WORKFLOW_NOT_SAVED;
                }
            } else {
                LOGGER.error("workflowMaster not created");
            }
            LOGGER.debug(workflowMaster.getFileName());
        } catch (Exception ex) {
            LOGGER.error("Error in updateWorkflow :" , ex);
            return ARMUtils.WORKFLOW_NOT_SAVED;
        }
        return workflowMaster == null ? null : workflowMaster.getWorkflowId();
    }

    /**
     * Method to set the parameters to display
     *
     * @param projectionId - Projection ID
     * @param workflowId - Workflow ID
     * @param userIdInt - User credential
     * @param workflowStatus - status of workflow
     * @return DTO object.
     */
    public WorkflowMasterDTO setWorkflowMasterDTO(int projectionId, int workflowId, int userIdInt, String workflowStatus) {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();
        workflowMasterDTO.setProjectionId(projectionId);
        workflowMasterDTO.setWorkflowMasterSystemId(workflowId);
        workflowMasterDTO.setWorkflowStatusStr(workflowStatus);
        workflowMasterDTO.setModifiedBy(userIdInt);
        workflowMasterDTO.setModifiedDate(new Date());
        workflowMasterDTO.setApprovedBy(userIdInt);
        return workflowMasterDTO;
    }

    /**
     * Method to Update the Projection Master for the submitted workflows
     *
     * @param workflowMasterDTO - Parameters to be passed to update as DTO
     * objectt
     * @return - Status as Saved or Not Saved
     */
    public String updateProjectionMaster(WorkflowMasterDTO workflowMasterDTO) {
        ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        try {
            String isApproved = StringUtils.EMPTY;
            if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getRejectedStatus())) {
                isApproved = ARMUtils.R;
            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getApprovedStatus())) {
                isApproved = "A";
            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getCancelledStatus())) {
                isApproved = ARMUtils.INDICATOR_LOGIC_CUSTOMER_HIERARCHY;
            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getWithdrawnStatus())) {
                isApproved = "W";
            }
            QueryUtils.itemUpdate(Arrays.asList(new String[]{isApproved, String.valueOf(workflowMasterDTO.getModifiedBy()), String.valueOf(workflowMasterDTO.getProjectionId())}), QueryUtils.QueryName.UPDATE_PROJECTION_MASTER_APPROVE_FLAG);
            return success;
        } catch (Exception ex) {
            LOGGER.error("Error in updateProjectionMaster :" , ex);
            return ARMUtils.WORKFLOW_NOT_SAVED;
        }
    }

    /**
     * Method to archive email for workflow
     *
     * @param workflowId - workflowId to be sent
     * @param approveORsubmit - Approve or Rejected Status
     */
    public void mailNotificationArchive(String workflowId, String approveORsubmit) throws NamingException, SQLException {
        Connection con = getConnection();
        String toEmailId = null;
        String ccEmailId = null;
        String subject = null;
        String body = null;
        int helperId;
        DynamicQuery mailDynamicQuery = DynamicQueryFactoryUtil.forClass(MailNotificationMaster.class);
        mailDynamicQuery.add(RestrictionsFactoryUtil.ilike("notificationModule", ARMUtils.BUSINESS_PROCESS_TYPE_ARM));
        helperId = getCodeFromHelperTable(approveORsubmit, "MailNotificationCategory");

        mailDynamicQuery.add(RestrictionsFactoryUtil.eq("notificationCategoryId", helperId));

        List<MailNotificationMaster> resultList = null;
        try {
            resultList = WorkflowMasterLocalServiceUtil.dynamicQuery(mailDynamicQuery);
        } catch (Exception e) {
            LOGGER.error(VariableConstants.ERROR_IN_MAIL_NOTIFICATION_ARCHIVE, e);
        }
        for (MailNotificationMaster object : resultList) {

            toEmailId = object.getToMailIds();

            ccEmailId = object.getCcMailIds();

            subject = object.getSubject();
            body = object.getBody();
        }
        PreparedStatement st = null;
        String tableBody = null;
        if (approveORsubmit.equals(ARMUtils.WORKFLOW_APPROVAL)) {
            tableBody = "<HTML><BODY>" + body + "<br/>" + "Approved WorkFlowId is : " + workflowId + " </BODY></HTML>";
        } else {
            tableBody = "<HTML><BODY>" + body + "<br/>" + "Submitted WorkFlowId is : " + workflowId + " </BODY></HTML>";
        }

        try {
            st = con.prepareCall("{call UspDbSendMail(?,?,?,?,?,?)}");
            st.setString(1, toEmailId);
            st.setString(NumericConstants.TWO, ccEmailId);
            st.setString(NumericConstants.THREE, null);
            st.setString(NumericConstants.FOUR, subject);
            st.setString(NumericConstants.FIVE, tableBody);
            st.setString(NumericConstants.SIX, null);
            st.execute();
        } catch (SQLException ex) {
            LOGGER.error(VariableConstants.ERROR_IN_MAIL_NOTIFICATION_ARCHIVE , ex);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                LOGGER.error(VariableConstants.ERROR_IN_MAIL_NOTIFICATION_ARCHIVE, e);
            }
            try {
                con.close();
            } catch (Exception e) {
                LOGGER.error(VariableConstants.ERROR_IN_MAIL_NOTIFICATION_ARCHIVE, e);
            }
        }

    }

    /**
     * Method to get DB connection .
     *
     * @return
     */
    public Connection getConnection() throws NamingException, SQLException {
        DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();

        return dataSourceConnection.getConnection();
    }

    /**
     * method to transfor the data form projectionData to DataModel
     *
     * @param projectionId
     */
    public static int getCodeFromHelperTable(String description, String listName) {
        int helperTableId = 0;
        String query = "SELECT HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME LIKE '" + listName.trim() + "' AND DESCRIPTION LIKE '" + description.trim() + ARMUtils.SINGLE_QUOTES;
        List resultList;
        try {
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);

            if (!resultList.isEmpty()) {
                helperTableId = (Integer) resultList.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getCodeFromHelperTable :" , ex);
        }
        return helperTableId;
    }

    /**
     * Method to Save the data in WorkflowMaster
     *
     * @param getUploadedData
     * @param workflowMasterDTO
     * @return Status as Saved or Not Saved
     */
    public String saveDocDetails(List<NotesDTO> getUploadedData) {
        LOGGER.debug("saveDocDetails");
        StringBuilder docdetailsSids = new StringBuilder();
        try {
            for (NotesDTO attached : getUploadedData) {
                DocDetails docDetails = DocDetailsLocalServiceUtil.createDocDetails(0);
                String fileName = attached.getDocumentName();
                if (fileName.indexOf('.') == -1) {
                    docDetails.setFileName(fileName);
                    docDetails.setFileType(StringUtils.EMPTY);
                } else {
                    docDetails.setFileName(fileName.substring(0, fileName.indexOf('.')));
                    docDetails.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1));
                }
                docDetails.setUploadedDate(new Date());
                docDetails.setUploadedBy(attached.getUserName());

                DocDetails id = DocDetailsLocalServiceUtil.addDocDetails(docDetails);
                docdetailsSids.append(id.getDocDetailsId()).append(ARMUtils.COMMA_CHAR);

            }

            if (docdetailsSids.length() > 0) {
                docdetailsSids.deleteCharAt(docdetailsSids.length() - 1);
            }
            LOGGER.debug("Doc details Id's --->>{}", docdetailsSids.toString());
        } catch (Exception ex) {
            LOGGER.error("Error in saveDocDetails :" , ex);
            return ARMUtils.WORKFLOW_NOT_SAVED;
        }
        return String.valueOf(docdetailsSids);
    }

    public static java.util.Properties getPropertyFile(String bpiPropLoc) {
        LOGGER.debug("getPropertyFile===================>starts");
        java.util.Properties prop = new java.util.Properties();
        try {
            FileInputStream fileIS = null;
            fileIS = GtnFileUtil.getFileInputStream(bpiPropLoc);
            prop.load(fileIS);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("getPropertyFile===================>ends");
        return prop;

    }
}
