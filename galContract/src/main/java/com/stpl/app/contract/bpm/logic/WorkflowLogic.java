/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.bpm.logic;

import com.stpl.app.contract.bpm.dto.WorkflowMasterDTO;
import com.stpl.app.contract.bpm.persistance.WorkflowPersistance;
import com.stpl.app.contract.bpm.util.BPIWorkFlowGeneratorXML;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.dao.WorkFlowLogicDao;
import com.stpl.app.contract.dao.impl.ContractDashboardLogicDAOImpl;
import com.stpl.app.contract.dao.impl.WorkFlowLogicDaoImpl;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.domain.contract.contractdashboard.ContractDashboardDAO;
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
import java.util.Map;
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
    private final String SUCCESS = Constants.SUCCESS;
    private final WorkFlowLogicDao workFlowLogicDao = new WorkFlowLogicDaoImpl();
    private final ContractDashboardDAO dataselectionLogicDao = new ContractDashboardLogicDAOImpl();
    private final Map<Integer, String> idDescMap = HelperListUtil.getInstance().getIdDescMap();
    /**
     * 0==="Approved", 1==="Cancelled", 2==="Pending", 3==="Rejected"
     */
    private static final String[] STATUS = new String[]{"Approved", "Cancelled", "Pending", "Rejected"};

//    Map<Integer, String> idDescMap=HelperListUtil.getInstance().getIdDescMap();
    /**
     * Method to Save the data in WorkflowMaster
     *
     * @param workflowMasterDTO
     * @return Status as Saved or Not Saved
     */
    public String saveWorkflowMaster(WorkflowMasterDTO workflowMasterDTO, Integer[] contractStructure, boolean isNewSubmit, long processIntanceId,String userId) {

        WorkflowMaster workflowMaster = WorkflowMasterLocalServiceUtil.createWorkflowMaster(0);
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
//            workFlowLogicDao.addWorkflowMaster(workflowMaster);
            WorkflowPersistance.addOrUpdateWorkflowMaster(workflowMaster, false, contractStructure, isNewSubmit, processIntanceId);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
        String path = (VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null
                ? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : StringUtils.EMPTY);
        String filePath1 = "/../../../WorkflowXML/BPIGeneratorIDs.xml";
        String moduleName = "CM";
//        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
//            moduleName = "C";
//        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
//            moduleName = "G";
//        }
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
    public String updateWorkflowFromForecast(int contractMasterSid, String userId, Integer[] contractStructure) throws Exception {
        String workflowId = Constants.WORKFLOW_NOT_SAVED;
        WorkflowMaster workflowMaster = getWorkflowMasterByContractSid(contractMasterSid, contractStructure);
        workflowMaster.setWorkflowStatusId(getCodeFromHelperTable(WorkflowConstants.getPendingStatus(), Constants.WORKFLOW_STATUS));
        workflowMaster.setModifiedDate(new Date());
        workflowMaster.setModifiedBy(Integer.valueOf(userId));
        try {
//            workFlowLogicDao.updateWorkflowMaster(workflowMaster);
            WorkflowPersistance.addOrUpdateWorkflowMaster(workflowMaster, true, contractStructure, null, 0);
            workflowId = workflowMaster.getWorkflowId();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
        //workflowMasterDTO.setWorkflowName(workflowName);
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
        workflowMasterDTO.setApprovalLevel(1);
        workflowMasterDTO.setModifiedDate(new Date());
//        workflowMasterDTO.setProjectionId();
//        workflowMasterDTO.setFileName(getUploadedData);
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
            LOGGER.error(ex.getMessage());
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
            return resultList.isEmpty() ? null : resultList.get(0);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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

//                workFlowLogicDao.updateWorkflowMaster(workflowMaster);
//                String projectionUpdated = updateContractMaster(workflowMasterDTO);
                if (!WorkflowPersistance.addOrUpdateWorkflowMaster(workflowMaster, true, contractStructure, null, 0)) {
                    return Constants.WORKFLOW_NOT_SAVED;
                }
            } else {
                LOGGER.info("workflowMaster not created");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
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
//    public String updateContractMaster(WorkflowMasterDTO workflowMasterDTO) {
//        ContractMaster contractMaster;
//        try {
//            contractMaster = dataselectionLogicDao.getContractMaster(workflowMasterDTO.getContractMasterSid());
//            if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getRejectedStatus())) {
//                contractMaster.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getRejectedStatus(), Constants.WORKFLOW_STATUS));
//            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getApprovedStatus())) {
////                contractMaster.setIsApproved("A");
//                contractMaster.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getApprovedStatus(), Constants.WORKFLOW_STATUS));
//            } else if (workflowMasterDTO.getWorkflowStatusStr().equals(WorkflowConstants.getCancelledStatus())) {
////                contractMaster.setIsApproved("C");
//                contractMaster.setWorkflowStatus(getCodeFromHelperTable(WorkflowConstants.getCancelledStatus(), Constants.WORKFLOW_STATUS));
//            }
//            dataselectionLogicDao.updateContractMaster(contractMaster);
//            return SUCCESS;
//        } catch (Exception ex) {
//            LOGGER.error(ex.getMessage());
//            return Constants.WORKFLOW_NOT_SAVED;
//        }
//    }
    public static int getCodeFromHelperTable(String description, String listName) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        int helperTableId = 0;
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, description));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("listName", listName));

        List<HelperTable> resultList = new ArrayList();
        try {
            resultList = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

            if (resultList.size() > 0) {
                HelperTable helperTable = resultList.get(0);
                helperTableId = helperTable.getHelperTableSid();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
        LOGGER.info("Entering submitProjection method");
        try {
//            ContractMaster contractMaster = dataselectionLogicDao.getContractMaster(contractMasterSid);
//            if (contractMaster.getWorkflowStatus() != 0 && WorkflowConstants.getApprovedStatus().equals(idDescMap.get(contractMaster.getWorkflowStatus()))
//                    || WorkflowConstants.getRejectedStatus().equals(idDescMap.get(contractMaster.getWorkflowStatus()))) {
//                workflowStatus = "RC";
//            }
//            contractMaster.setWorkflowStatus(getCodeFromHelperTable(STATUS[2], Constants.WORKFLOW_STATUS));
//            contractMaster.setModifiedDate(new Date());
//            contractMaster.setModifiedBy(Integer.valueOf(userId));
//            dataselectionLogicDao.updateContractMaster(contractMaster);

//            }
//            if (workflowStatus.equals("RC")) {
//                LOGGER.info("Ending submitProjection with update");
//                return updateWorkflowFromForecast(contractMasterSid, userId, contractStructure);
//            } else {
            LOGGER.info("Ending submitProjection with save");
            return saveWorkflow(contractMasterSid, userId, StringUtils.EMPTY, 1, contractStructure, isNewSubmit, processIntanceId,workflowMasterSid,workflowId);
//            }

        } catch (Exception e) {
            LOGGER.error(new Date() + e.getMessage());
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
        return "C" + contractCombo[0] + "|I" + contractCombo[1] + "|P" + contractCombo[2] + "|R" + contractCombo[3];
    }

    /**
     *
     * splitting the CfpSystemId, IfpSystemId, PsSystemId and RsSystemId
     *
     * @param contractCombo
     * @return
     */
    public static Integer[] siplitContractStructure(String contractCombo) {
        Integer[] val = new Integer[4];
        if (contractCombo == null || StringUtils.EMPTY.equals(contractCombo.trim())) {
            val[0] = val[1] = val[2] = val[3] = 0;
        } else {
            String[] raw = contractCombo.split("\\|");
            val[0] = Integer.valueOf(raw[0].replace("C", StringUtils.EMPTY));
            val[1] = Integer.valueOf(raw[1].replace("I", StringUtils.EMPTY));
            val[2] = Integer.valueOf(raw[2].replace("P", StringUtils.EMPTY));
            val[3] = Integer.valueOf(raw[3].replace("R", StringUtils.EMPTY));
        }
        return val;
    }

}
