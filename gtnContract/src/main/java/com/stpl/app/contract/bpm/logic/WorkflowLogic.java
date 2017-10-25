/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.bpm.logic;

import com.stpl.app.contract.bpm.dto.WorkflowMasterDTO;
import com.stpl.app.contract.bpm.persistance.WorkflowPersistance;
import com.stpl.app.contract.bpm.util.BPIWorkFlowGeneratorXML;
import com.stpl.app.contract.dao.WorkFlowLogicDao;
import com.stpl.app.contract.dao.impl.WorkFlowLogicDaoImpl;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author santanukumar
 */
public class WorkflowLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(WorkflowLogic.class);
    private final WorkFlowLogicDao workFlowLogicDao = new WorkFlowLogicDaoImpl();
    /**
     * 0==="Approved", 1==="Cancelled", 2==="Pending", 3==="Rejected"
     */

    /**
     * Method to Save the data in WorkflowMaster
     *
     * @param workflowMasterDTO
     * @return Status as Saved or Not Saved
     */
    public String saveWorkflowMaster(WorkflowMasterDTO workflowMasterDTO, Integer[] contractStructure, boolean isNewSubmit, long processIntanceId,String userId) {

        WorkflowMaster workflowMaster = WorkflowMasterLocalServiceUtil.createWorkflowMaster(Constants.ZERO);
        workflowMaster.setWorkflowId(workflowMasterDTO.getWorkflowId());
        workflowMaster.setWorkflowStatusId(workflowMasterDTO.getWorkflowStatus());
        workflowMaster.setContractMasterSid(workflowMasterDTO.getContractMasterSid());
        workflowMaster.setCreatedBy(workflowMasterDTO.getCreatedBy());
        workflowMaster.setCreatedDate(workflowMasterDTO.getCreatedDate());
        workflowMaster.setNotes(workflowMasterDTO.getNotes());
        workflowMaster.setNoOfApproval(workflowMasterDTO.getNoOfApprovals());
        workflowMaster.setApprovalLevel(workflowMasterDTO.getApprovalLevel());
        workflowMaster.setModifiedBy(workflowMasterDTO.getCreatedBy());
        workflowMaster.setModifiedDate(workflowMasterDTO.getCreatedDate());
        workflowMaster.setWorkflowMasterSid(workflowMasterDTO.getWorkflowMasterSystemId());
        workflowMaster.setApprovedBy(Integer.valueOf(userId)); // This is for updating the user id for created by in update contract
        
        
        try {
            WorkflowPersistance.addOrUpdateWorkflowMaster(workflowMaster, false, contractStructure, isNewSubmit, processIntanceId);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Constants.WORKFLOW_NOT_SAVED;
        }
        return workflowMaster.getWorkflowId();
    }

    /**
     * Method to Save the data in WorkflowMaster based on projectionId & User ID
     *
     * @param contractMasterSid
     * @param userId
     * @param notes
     * @return Status as Saved or Not Saved
     * @throws IOException
     */
    public String saveWorkflow(int contractMasterSid, String userId, String notes, int noOfLevels, Integer[] contractStructure, boolean isNewSubmit, long processIntanceId,int workflowMasterSid, String submittedWorkflowId) throws IOException, SAXException, ParserConfigurationException, TransformerException, SystemException, PortalException {
        String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null
                ? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : StringUtils.EMPTY;
        String filePath1 = "/../../../WorkflowXML/BPIGeneratorIDs.xml";
        String moduleName = "CM";
        String workflowId = StringUtils.EMPTY;
        if (isNewSubmit) {
            workflowId = new BPIWorkFlowGeneratorXML().generateId(path + filePath1, moduleName);
        } else {
            workflowId = submittedWorkflowId;
        }
        WorkflowMasterDTO workflowMasterDTO = setWorkflowMasterDTO(contractMasterSid, workflowId, userId, notes, noOfLevels);
        workflowMasterDTO.setWorkflowMasterSystemId(workflowMasterSid);
        return saveWorkflowMaster(workflowMasterDTO, contractStructure, isNewSubmit, processIntanceId,userId);
    }

    /**
     * Method to updateWorkflowMaster From Forecast
     *
     * @param contractMasterSid
     * @return
     */
    public String updateWorkflowFromForecast(int contractMasterSid, String userId, Integer[] contractStructure) {
        String workflowId = Constants.WORKFLOW_NOT_SAVED;
        WorkflowMaster workflowMaster = getWorkflowMasterByContractSid(contractMasterSid, contractStructure);
        workflowMaster.setWorkflowStatusId(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), Constants.WORKFLOW_STATUS));
        workflowMaster.setModifiedDate(new Date());
        workflowMaster.setModifiedBy(Integer.valueOf(userId));
        try {
            WorkflowPersistance.addOrUpdateWorkflowMaster(workflowMaster, true, contractStructure, null,Constants.ZERO);
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
        //        
        int userIdInt = Integer.parseInt(userId);
        workflowMasterDTO.setWorkflowId(workflowId);
        workflowMasterDTO.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), Constants.WORKFLOW_STATUS));
        workflowMasterDTO.setCreatedBy(userIdInt);
        workflowMasterDTO.setCreatedDate(new Date());
        workflowMasterDTO.setProjectionId(projectionId);
        return workflowMasterDTO;
    }

    /**
     * Method to set the parameters to display
     *
     * @param contractMasterSid - Projection ID
     * @param workflowId - Workflow ID
     * @param userIdInt - User credential
     * @param workflowStatus - status of workflow
     * @param notes
     * @param approvalLevel
     * @return DTO object.
     */
    public WorkflowMasterDTO setWorkflowMasterDTO(int contractMasterSid, int workflowId, int userIdInt, String workflowStatus, String notes, int approvalLevel) {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();
        workflowMasterDTO.setContractMasterSid(contractMasterSid);
        workflowMasterDTO.setWorkflowMasterSystemId(workflowId);
        workflowMasterDTO.setWorkflowStatusStr(workflowStatus);
        workflowMasterDTO.setModifiedBy(userIdInt);
        workflowMasterDTO.setModifiedDate(new Date());
        if (WorkflowConstants.getApprovedStatus().equalsIgnoreCase(workflowStatus)) {
            workflowMasterDTO.setApprovedBy(userIdInt);
        }
        workflowMasterDTO.setNotes(notes);
        workflowMasterDTO.setApprovalLevel(approvalLevel);
        return workflowMasterDTO;
    }

    /**
     * Method to set the va;lues in WF based on projectionId,workflowId,userId
     *
     * @param contractMasterSid
     * @param workflowId
     * @param userId
     * @return WorkflowMasterDTO Object
     */
    public WorkflowMasterDTO setWorkflowMasterDTO(int contractMasterSid, String workflowId, String userId, String notes, int noOfLevels) throws SystemException {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();
        int userIdInt = Integer.parseInt(userId);
        workflowMasterDTO.setWorkflowId(workflowId);
        workflowMasterDTO.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), Constants.WORKFLOW_STATUS));
        workflowMasterDTO.setCreatedBy(userIdInt);
        workflowMasterDTO.setCreatedDate(new Date());
        workflowMasterDTO.setContractMasterSid(contractMasterSid);
        workflowMasterDTO.setNotes(notes);
        workflowMasterDTO.setNoOfApprovals(noOfLevels);
        workflowMasterDTO.setApprovalLevel(NumericConstants.ONE);
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
     * @param contractSid
     * @return - Workflow Master Object
     */
    public WorkflowMaster getWorkflowMasterByContractSid(int contractSid, Integer[] contractStructure) {
        DynamicQuery workflowMasterDynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowMaster.class);
        workflowMasterDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_MASTER_SID, contractSid));
        workflowMasterDynamicQuery.add(RestrictionsFactoryUtil.eq("contractStructure", WorkflowLogic.createContractStructure(contractStructure)));
        List<WorkflowMaster> resultList;
        try {
            resultList = workFlowLogicDao.getWorkflowMasterByContractSid(workflowMasterDynamicQuery);
            return resultList.isEmpty() ? null : resultList.get(Constants.ZERO);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    /**
     * Method to update Workflow Master
     *
     * @param workflowMasterDTO - parameters to be updated
     * @param contractStructure
     * @return - Status as Saved or Not Saved
     */
    public String updateWorkflow(WorkflowMasterDTO workflowMasterDTO, Integer[] contractStructure) {
        WorkflowMaster workflowMaster = getWorkflowMaster(workflowMasterDTO.getWorkflowMasterSystemId());

        try {
            if (workflowMaster != null) {
                workflowMaster.setWorkflowStatusId(getCodeFromHelperTable(workflowMasterDTO.getWorkflowStatusStr(), Constants.WORKFLOW_STATUS));
                workflowMaster.setModifiedBy(workflowMasterDTO.getModifiedBy());
                workflowMaster.setModifiedDate(workflowMasterDTO.getModifiedDate());
                if (WorkflowConstants.getApprovedStatus().equalsIgnoreCase(workflowMasterDTO.getWorkflowStatusStr())) {
                    workflowMaster.setApprovedBy(workflowMasterDTO.getApprovedBy());
                }
                workflowMaster.setNotes(workflowMasterDTO.getNotes());
                workflowMaster.setApprovalLevel(workflowMasterDTO.getApprovalLevel());

                if (!WorkflowPersistance.addOrUpdateWorkflowMaster(workflowMaster, true, contractStructure, null,Constants.ZERO)) {
                    return Constants.WORKFLOW_NOT_SAVED;
                }
            } else {
                LOGGER.debug("workflowMaster not created");
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            return Constants.WORKFLOW_NOT_SAVED;
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
    public static int getCodeFromHelperTable(String description, String listName) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        int helperTableId =Constants.ZERO;
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, description));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("listName", listName));

        List<HelperTable> resultList = new ArrayList();
        try {
            resultList = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (resultList.size() > Constants.ZERO) {
                HelperTable helperTable = resultList.get(Constants.ZERO);
                helperTableId = helperTable.getHelperTableSid();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return helperTableId;
    }

    /**
     * Submit the projection.
     *
     * @param contractMasterSid the projection id
     * @param userId
     * @param contractStructure
     * @return
     */
    public String submitContract(int contractMasterSid, String userId, Integer[] contractStructure, boolean isNewSubmit, long processIntanceId,int workflowMasterSid, String workflowId) {
        LOGGER.debug("Entering submitProjection method");
        try {
            LOGGER.debug("Ending submitProjection with save");
            return saveWorkflow(contractMasterSid, userId, StringUtils.EMPTY, 1, contractStructure, isNewSubmit, processIntanceId,workflowMasterSid,workflowId);

        } catch (Exception e) {
            LOGGER.error(e);
            return "Not Saved";
        }

    }

    /**
     **Creating structure the CfpSystemId, IfpSystemId, PsSystemId and
     * RsSystemId
     *
     * @param contractCombo
     * @return
     */
    public static String createContractStructure(Integer[] contractCombo) {
        return "C" + contractCombo[Constants.ZERO] + "|I" + contractCombo[NumericConstants.ONE] + "|P" + contractCombo[NumericConstants.TWO] + "|R" + contractCombo[NumericConstants.THREE];
    }

    /**
     *
     * splitting the CfpSystemId, IfpSystemId, PsSystemId and RsSystemId
     *
     * @param contractCombo
     * @return
     */
    public static Integer[] siplitContractStructure(String contractCombo) {
        Integer[] val = new Integer[NumericConstants.FOUR];
        if (contractCombo == null || StringUtils.EMPTY.equals(contractCombo.trim())) {
            val[Constants.ZERO] = val[NumericConstants.ONE] = val[NumericConstants.TWO] = val[NumericConstants.THREE] = Constants.ZERO;
        } else {
            String[] raw = contractCombo.split("\\|");
            val[Constants.ZERO] = Integer.valueOf(raw[Constants.ZERO].replace("C", StringUtils.EMPTY));
            val[NumericConstants.ONE] = Integer.valueOf(raw[NumericConstants.ONE].replace("I", StringUtils.EMPTY));
            val[NumericConstants.TWO] = Integer.valueOf(raw[NumericConstants.TWO].replace("P", StringUtils.EMPTY));
            val[NumericConstants.THREE] = Integer.valueOf(raw[NumericConstants.THREE].replace("R", StringUtils.EMPTY));
        }
        return val;
    }

}
