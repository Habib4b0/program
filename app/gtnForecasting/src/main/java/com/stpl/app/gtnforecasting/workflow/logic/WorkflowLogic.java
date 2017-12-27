/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.workflow.logic;

import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.WorkFlowLogicDao;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.WorkFlowLogicDaoImpl;
import com.stpl.app.gtnforecasting.logic.DataSourceConnection;
import com.stpl.app.gtnforecasting.utils.BPIWorkFlowGeneratorXML;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.model.DocDetails;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import com.vaadin.server.VaadinService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class WorkflowLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(WorkflowLogic.class);
    private final String SUCCESS = Constant.SUCCESS;
    WorkFlowLogicDao workFlowLogicDao = new WorkFlowLogicDaoImpl();
    DataSelectionDAO dataselectionLogicDao = new DataSelectionDAOImpl();

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
            workFlowLogicDao.addWorkflowMaster(workflowMaster);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return CommonUtils.WORKFLOW_NOT_SAVED;
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
        String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null
                ? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : StringUtils.EMPTY;
        String filePath1 = "/../../../WorkflowXML/BPIGeneratorIDs.xml";
        String moduleName = StringUtils.EMPTY;
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            moduleName = Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY;
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            moduleName = "G";
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION)) {
            moduleName = "AR";
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
            moduleName = "RE";
        }
        String workflowId = new BPIWorkFlowGeneratorXML().generateId(path + filePath1, moduleName);
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
    public String updateWorkflowFromForecast(int projectionId, String notes, String userId)  {
        String workflowId = CommonUtils.WORKFLOW_NOT_SAVED;
        WorkflowMaster workflowMaster = getWorkflowMasterByProjectionId(projectionId);
        workflowMaster.setWorkflowStatusId(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), CommonUtils.WORKFLOW_STATUS));
        workflowMaster.setNotes(notes);
        workflowMaster.setModifiedDate(new Date());
        workflowMaster.setModifiedBy(Integer.parseInt(userId));
        try {
            workFlowLogicDao.updateWorkflowMaster(workflowMaster);
            workflowId = workflowMaster.getWorkflowId();
        } catch (Exception ex) {
            LOGGER.error(ex);
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

        workflowMasterDTO.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), CommonUtils.WORKFLOW_STATUS));
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
        workflowMasterDTO.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), CommonUtils.WORKFLOW_STATUS));
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
            workflowMaster = workFlowLogicDao.getWorkflowMaster(workflowMasterSystemId);
            return workflowMaster;
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        DynamicQuery workflowMasterDynamicQuery = WorkflowMasterLocalServiceUtil.dynamicQuery();
        workflowMasterDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID,
                projectionId));
        List<WorkflowMaster> resultList;
        try {
            resultList = workFlowLogicDao.getWorkflowMasterByProjectionId(workflowMasterDynamicQuery);
            return resultList.get(0);
        } catch (Exception ex) {
            LOGGER.error(ex);
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
                workflowMaster.setWorkflowStatusId(getCodeFromHelperTable(workflowMasterDTO.getWorkflowStatusStr(), CommonUtils.WORKFLOW_STATUS));
                workflowMaster.setModifiedBy(workflowMasterDTO.getModifiedBy());
                workflowMaster.setModifiedDate(workflowMasterDTO.getModifiedDate());
                if (WorkflowConstants.getApprovedStatus().equalsIgnoreCase(workflowMasterDTO.getWorkflowStatusStr())) {
                    workflowMaster.setApprovedBy(workflowMasterDTO.getApprovedBy());
                    workflowMaster.setApprovedDate(workflowMasterDTO.getApprovedDate());
                }
                workflowMaster.setNotes(workflowMasterDTO.getNotes());
                workflowMaster.setApprovalLevel(workflowMasterDTO.getApprovalLevel());

                workFlowLogicDao.updateWorkflowMaster(workflowMaster);
                String projectionUpdated = updateProjectionMaster(workflowMasterDTO);
                if (!projectionUpdated.equals(SUCCESS)) {
                    return CommonUtils.WORKFLOW_NOT_SAVED;
                }
            } else {
                LOGGER.error("workflowMaster not created");
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            return CommonUtils.WORKFLOW_NOT_SAVED;
        }
        return workflowMaster.getWorkflowId();
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
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        try {
            projectionMaster = dataselectionLogicDao
                    .getProjectionMaster(workflowMasterDTO.getProjectionId());
            if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getRejectedStatus())) {
                projectionMaster.setIsApproved(Constant.R);
            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getApprovedStatus())) {
                projectionMaster.setIsApproved("A");
            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getCancelledStatus())) {
                projectionMaster.setIsApproved(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getWithdrawnStatus())) {
                projectionMaster.setIsApproved("W");
            }
            dataselectionLogicDao.updateProjectionMaster(projectionMaster);
            return SUCCESS;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return CommonUtils.WORKFLOW_NOT_SAVED;
        }
    }

    /**
     * Method to archive email for workflow
     *
     * @param workflowId - workflowId to be sent
     * @param approveORsubmit - Approve or Rejected Status
     */
    public void mailNotificationArchive(String workflowId, String approveORsubmit) throws NamingException,SQLException  {
        Connection con = getConnection();
        String toEmailId = null;
        String ccEmailId = null;
        String subject = null;
        String body = null;
        int helperId;
        DynamicQuery mailDynamicQuery = MailNotificationMasterLocalServiceUtil.dynamicQuery();
        mailDynamicQuery.add(RestrictionsFactoryUtil.ilike("notificationModule", CommonUtils.FORECAST_NON_MANDATED));
        helperId = getCodeFromHelperTable(approveORsubmit, "MailNotificationCategory");

        mailDynamicQuery.add(RestrictionsFactoryUtil.eq("notificationCategoryId", helperId));

        List<MailNotificationMaster> resultList = null;
        try {
            resultList = workFlowLogicDao.getMailNotificationMaster(mailDynamicQuery);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        for (MailNotificationMaster object : resultList) {

            toEmailId = object.getToMailIds();

            ccEmailId = object.getCcMailIds();

            subject = object.getSubject();
            body = object.getBody();
        }
        PreparedStatement st = null;
        String tableBody = null;
        if (approveORsubmit.equals(CommonUtils.WORKFLOW_APPROVAL)) {
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
            LOGGER.error(ex);
        } finally {
            try {
                st.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                con.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }

    }

    /**
     * Method to get DB connection .
     *
     * @return
     */
    public Connection getConnection() throws NamingException, SQLException{
        DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();

        return dataSourceConnection.getConnection();
    }

    /**
     * method to transfor the data form projectionData to DataModel
     *
     * @param projectionId
     */

    public static int getCodeFromHelperTable(String description, String listName) {
        DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        int helperTableId = 0;
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.DESCRIPTION,
                description));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.LIST_NAME,
                listName));

        List<HelperTable> resultList = new ArrayList<>();
        try {
            resultList = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (!resultList.isEmpty()) {
                HelperTable helperTable = resultList.get(0);
                helperTableId = helperTable.getHelperTableSid();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
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

            try {
                DocDetails id = workFlowLogicDao.addDocDetails(docDetails);
                docdetailsSids.append(id.getDocDetailsId()).append(",");
            } catch (Exception ex) {
                LOGGER.error(ex);
                return CommonUtils.WORKFLOW_NOT_SAVED;
            }
        }

        if (docdetailsSids.length() > 0) {
            docdetailsSids.deleteCharAt(docdetailsSids.length() - 1);
        }
        return String.valueOf(docdetailsSids);
    }
}
