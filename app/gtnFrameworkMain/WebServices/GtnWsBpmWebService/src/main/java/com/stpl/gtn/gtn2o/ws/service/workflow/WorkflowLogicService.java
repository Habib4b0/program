package com.stpl.gtn.gtn2o.ws.service.workflow;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.gtn.gtn2o.ws.bpm.properties.DroolsProperties;
import com.stpl.gtn.gtn2o.ws.bpm.service.BpmProcessBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractWorkflowBean;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.projectionmaster.ProjectionMaster;
import com.stpl.gtn.gtn2o.ws.entity.role.Role;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsReturnsDatabaseService;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;
import com.stpl.gtn.gtn2o.ws.util.xmlparser.SQLUtility;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowMasterBean;

/**
 *
 * @author STPL
 */
@Service
@Scope("singleton")
public class WorkflowLogicService {

    private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(WorkflowLogicService.class);
    @Autowired
    private GtnWsReturnsDatabaseService databaseService;

    @Autowired
    private GtnWsUserRoleService gtnWsUserRoleService;

    @Autowired
    private BpmProcessBean bpmProcessBean;

    public WorkflowLogicService() {
        super();
    }

    public WorkflowLogicService(GtnWsReturnsDatabaseService databaseService, GtnWsUserRoleService gtnWsUserRoleService,
            BpmProcessBean bpmProcessBean) {
        super();
        this.databaseService = databaseService;
        this.gtnWsUserRoleService = gtnWsUserRoleService;
        this.bpmProcessBean = bpmProcessBean;
    }

    @SuppressWarnings("unchecked")
    public String getWorkflowStatus(int projectionId, final String moduleName) {
        String workflowStatus = "";
        String workflowStatusQuery = "";
        if (moduleName.equals(GtnWsBpmCommonConstants.FORECAST_RETURNS)) {
            workflowStatusQuery = "Select IS_APPROVED from PROJECTION_MASTER where PROJECTION_MASTER_SID=? AND FORECASTING_TYPE='Returns'";
        }
        if (moduleName.equals(GtnWsBpmCommonConstants.CONTRACT_MASTER)) {
            workflowStatusQuery = "select HT.DESCRIPTION FROM WORKFLOW_MASTER WM LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID AND WM.CONTRACT_MASTER_SID=?";

        }
        try {
            Object[] workFlowStatusQueryParams = {projectionId};
            List<Object[]> statusList = databaseService.executeQuery(workflowStatusQuery, workFlowStatusQueryParams);
            if (statusList != null && statusList.isEmpty()) {
                Object[] statusArray = statusList.get(0);
                workflowStatus = String.valueOf(statusArray[0]);
            }
        } catch (GtnFrameworkGeneralException ex) {
            LOGGER.error("Error while getting the workflow status .", ex);
        }
        return workflowStatus;
    }

    public ProcessInstance startWorkflow(String moduleKey, String defaultValue, String moduleName) {
        ProcessInstance processInstance = null;
        try {
            Properties properties = DroolsProperties.getPropertiesData();
            String workflowId = properties.getProperty(moduleKey, defaultValue);
            processInstance = bpmProcessBean.startProcess(workflowId, null, moduleName);
        } catch (Exception e) {
            LOGGER.error("Exception while starting workFlow.", e);
        }
        return processInstance;
    }

    public boolean isValidWorkflowUser(User userModel, List<String> roleList, long processIntanceId,
            String moduleName) {
        boolean returnflag = false;
        TaskSummary taskSummary = null;
        try {

            LOGGER.info(GtnFrameworkWebserviceConstant.USER_NAME + userModel.getScreenName());
            taskSummary = bpmProcessBean.getAvailableTask(processIntanceId, moduleName);
            if (taskSummary == null) {
                return true;
            }

            LOGGER.debug(GtnFrameworkWebserviceConstant.TASK_SUMMARY + taskSummary.getName());
            List<String> userRoles = bpmProcessBean.getPotentialOwners(taskSummary.getId(), roleList, moduleName);
            LOGGER.debug("userRoles :" + userRoles);
            if (userRoles == null || userRoles.isEmpty()) {
                return returnflag;
            }
            List<Role> roles = gtnWsUserRoleService.getUserRoles(userModel.getUserId());
            for (Role role : roles) {
                if (userRoles.contains(role.getName())) {
                    returnflag = true;
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception in isValidWorkflowUser() method." + e);
        }

        return returnflag;
    }

    public TaskSummary startAndCompleteTask(User userModel, int projectionId, long processInstanceId,
            String moduleName) {
        TaskSummary taskSummary = null;
        try {
            LOGGER.debug("userId :" + userModel.getUserId());
            LOGGER.debug(GtnFrameworkWebserviceConstant.USER_NAME + userModel.getScreenName());
            taskSummary = bpmProcessBean.getAvailableTask(processInstanceId, moduleName);
            LOGGER.debug(GtnFrameworkWebserviceConstant.TASK_SUMMARY + taskSummary.getName());
            LOGGER.debug(GtnFrameworkWebserviceConstant.TASK_SUMMARY + taskSummary.getId());
            bpmProcessBean.startTask(taskSummary.getId(), userModel.getScreenName(), moduleName);
            bpmProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null, moduleName);
            insertWFInstanceInfo(projectionId, processInstanceId);
        } catch (Exception e) {
            LOGGER.error("Exception in startAndCompleteTask() method." + e);
        }
        return taskSummary;
    }

    public TaskSummary startAndCompleteContractTask(User userModel, int contractId, long processInstanceId,
            String contractStructure, String moduleName) {
        TaskSummary taskSummary = null;
        try {
            LOGGER.debug("userId :" + userModel.getUserId());
            LOGGER.debug(GtnFrameworkWebserviceConstant.USER_NAME + userModel.getScreenName());
            taskSummary = bpmProcessBean.getAvailableTask(processInstanceId, moduleName);
            LOGGER.debug(GtnFrameworkWebserviceConstant.TASK_SUMMARY + taskSummary.getName());
            LOGGER.debug(GtnFrameworkWebserviceConstant.TASK_SUMMARY + taskSummary.getId());
            bpmProcessBean.startTask(taskSummary.getId(), userModel.getScreenName(), moduleName);
            bpmProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null, moduleName);
            insertContractWFInstanceInfo(contractId, processInstanceId, contractStructure);
        } catch (Exception e) {
            LOGGER.error("Exception in startAndCompleteContractTask() method." + e);
        }
        return taskSummary;
    }

    public boolean insertWFInstanceInfo(int projectionId, long processInstanceId) {
        try {

            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (PROJECTION_MASTER_SID,PROCESS_INSTANCE_ID) VALUES(?,?)";
            Object[] queryParams = {projectionId, processInstanceId};
            return databaseService.executeUpdate(customSql, queryParams) > 0;

        } catch (Exception e) {
            LOGGER.error("Exception in insertWFInstanceInfo() method." + e);
            return false;
        }
    }

    public boolean insertContractWFInstanceInfo(int contractId, long processInstanceId, String contractStructure) {
        try {

            String customSql = "INSERT INTO WORKFLOW_PROCESS_INFO (CONTRACT_MASTER_SID,PROCESS_INSTANCE_ID,CONTRACT_STRUCTURE) "
                    + "VALUES(?,?,?)";
            Object[] queryParams = {contractId, processInstanceId, contractStructure};
            return databaseService.executeUpdate(customSql, queryParams) > 0;

        } catch (Exception e) {
            LOGGER.error("Exception in insertContractWFInstanceInfo() method." + e);
            return false;
        }
    }

    @SuppressWarnings("rawtypes")
    public List selectWFInstanceInfo(int projectionId) {
        List obj = null;
        try {

            String customSql = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE PROJECTION_MASTER_SID=?";

            Object[] customSqlParams = {projectionId};
            obj = databaseService.executeQuery(customSql, customSqlParams);

        } catch (Exception e) {
            LOGGER.error("Exception in selectWFInstanceInfo() method." + e);
        }
        return obj;

    }

    @SuppressWarnings("rawtypes")
    public List selectContractWFInstanceInfo(int contractId) {
        List obj = null;
        try {

            String customSql = "SELECT PROCESS_INSTANCE_ID FROM WORKFLOW_PROCESS_INFO WHERE CONTRACT_MASTER_SID="
                    + contractId;
            Object[] cmProcessInstanceIdParams = {contractId};
            obj = databaseService.executeQuery(customSql, cmProcessInstanceIdParams);

        } catch (Exception e) {
            LOGGER.error("Exception in selectContractWFInstanceInfo() method." + e);
        }
        return obj;

    }

    public List<ForecastingRulesDTO> getProjectionValues(int projectionId, String screenName,String userId, String sessionId) {
        List<ForecastingRulesDTO> list = new ArrayList<>();
        try {

            List<Object[]> returnList = getProjectionRecords(projectionId, screenName, userId,  sessionId);// ,
            for (int i = 0; i < returnList.size(); i++) {
                Object[] obj = returnList.get(i);
                if (screenName.equals(GtnWsBpmCommonConstants.FORECAST_RETURNS)) {
                    ForecastingRulesDTO retRate = new ForecastingRulesDTO("Projected_Return_Percent");
                    retRate.setAmountLowest(Double.valueOf(String.valueOf(obj[0])));
                    retRate.setAmountGreatest(Double.valueOf(String.valueOf(obj[1])));
                    retRate.setPercentLowest(Double.valueOf(String.valueOf(obj[2])));
                    retRate.setPercentGreatest(Double.valueOf(String.valueOf(obj[3])));
                    list.add(retRate);
                }else if(screenName.equals(GtnWsBpmCommonConstants.FORECAST_COMMERCIAL)){
                 ForecastingRulesDTO sales = new ForecastingRulesDTO("Projected_Contract_Sales_Dollars");
                    sales.setAmountLowest(Double.valueOf(String.valueOf(obj[0])));
                    sales.setAmountGreatest(Double.valueOf(String.valueOf(obj[1])));
                    sales.setPercentLowest(Double.valueOf(String.valueOf(obj[12])));
                    sales.setPercentGreatest(Double.valueOf(String.valueOf(obj[13])));

                    ForecastingRulesDTO units = new ForecastingRulesDTO("Projected_Contract_Sales_Units");
                    units.setAmountLowest(Double.valueOf(String.valueOf(obj[2])));
                    units.setAmountGreatest(Double.valueOf(String.valueOf(obj[3])));
                    units.setPercentLowest(Double.valueOf(String.valueOf(obj[14])));
                    units.setPercentGreatest(Double.valueOf(String.valueOf(obj[15])));

                    ForecastingRulesDTO discount = new ForecastingRulesDTO("Projected_Discount_Dollars");
                    discount.setAmountLowest(Double.valueOf(String.valueOf(obj[4])));
                    discount.setAmountGreatest(Double.valueOf(String.valueOf(obj[5])));
                    discount.setPercentLowest(Double.valueOf(String.valueOf(obj[16])));
                    discount.setPercentGreatest(Double.valueOf(String.valueOf(obj[17])));

                    ForecastingRulesDTO rate = new ForecastingRulesDTO("Projected_Discount_Rate");
                    rate.setAmountLowest(Double.valueOf(String.valueOf(obj[6])));
                    rate.setAmountGreatest(Double.valueOf(String.valueOf(obj[7])));
                    rate.setPercentLowest(Double.valueOf(String.valueOf(obj[18])));
                    rate.setPercentGreatest(Double.valueOf(String.valueOf(obj[19])));

                    ForecastingRulesDTO netSales = new ForecastingRulesDTO("Net_Sales");
                    netSales.setAmountLowest(Double.valueOf(String.valueOf(obj[8])));
                    netSales.setAmountGreatest(Double.valueOf(String.valueOf(obj[9])));
                    netSales.setPercentLowest(Double.valueOf(String.valueOf(obj[20])));
                    netSales.setPercentGreatest(Double.valueOf(String.valueOf(obj[21])));

                    ForecastingRulesDTO netProfit = new ForecastingRulesDTO("Net_Profit");
                    netProfit.setAmountLowest(Double.valueOf(String.valueOf(obj[10])));
                    netProfit.setAmountGreatest(Double.valueOf(String.valueOf(obj[11])));
                    netProfit.setPercentLowest(Double.valueOf(String.valueOf(obj[22])));
                    netProfit.setPercentGreatest(Double.valueOf(String.valueOf(obj[23])));
                    list.add(sales);
                    list.add(units);
                    list.add(discount);
                    list.add(rate);
                    list.add(netSales);
                    list.add(netProfit);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Exception in getProjectionValues() method." + e);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getProjectionRecords(int projectionId, String screenName, String userId, String sessionId) {
        List<Object[]> obj = null;
        try {
            if (screenName.equals(GtnWsBpmCommonConstants.FORECAST_RETURNS)) {
                String projectionRecordsQuery = SQLUtility.getQuery("getProjectionRecordsForReturns");
                Object[] projectionRecordsQueryParams = {projectionId};
                obj = databaseService.executeQuery(projectionRecordsQuery, projectionRecordsQueryParams);

            } else if (screenName.equals(GtnWsBpmCommonConstants.FORECAST_COMMERCIAL)) {
                String projectionRecordsQuery = SQLUtility.getQuery("getProjectionRecords");
                Object[] projectionRecordsQueryParams = {projectionId, userId, sessionId};
                obj = databaseService.executeQuery(projectionRecordsQuery,projectionRecordsQueryParams);
            }
        } catch (Exception e) {
            LOGGER.error("Exception in selectWFInstanceInfo() method." + e);
        }
        return obj;
    }

    public void updateTaskInBpm(final String userId, final Long processInstanceId, final Map<String, Object> params,
            String moduleName) {
        // method
        try {
            User user = gtnWsUserRoleService.getUser(Long.parseLong(userId));

            TaskSummary task = bpmProcessBean.getAvailableTask(processInstanceId, moduleName);
            LOGGER.info("task.getName() :" + task.getName());
            LOGGER.info("task.getId() :" + task.getId());
            LOGGER.info("task.getActualOwnerId() :" + task.getActualOwnerId());
            LOGGER.info("user.getScreenName() : " + user.getScreenName());
            if (task.getActualOwnerId() != null && !task.getActualOwnerId().equals(user.getScreenName())) {
                bpmProcessBean.claimTask(task.getId(), task.getActualOwnerId(), user.getScreenName(), moduleName);
                LOGGER.info("Claiming the " + task.getActualOwnerId() + " to :" + user.getScreenName());
            }
            if (task.getStatus().equals(Status.InProgress)) {
                bpmProcessBean.completeTask(task.getId(), user.getScreenName(), params, moduleName);
            } else {
                bpmProcessBean.startTask(task.getId(), user.getScreenName(), moduleName);
                bpmProcessBean.completeTask(task.getId(), user.getScreenName(), params, moduleName);
            }
        } catch (Exception e) {
            LOGGER.error("Exception in submitWorkflow() method." + e);
        }
    }

    @SuppressWarnings("unchecked")
    public String submitProjection(GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean,
            GtnWsGeneralRequest gtnWsGeneralRequest, String noOfUsers) {
        LOGGER.debug("Entering submitProjection method");
        String moduleName = forecastProjectionSubmitBean.getModuleName();
        Session session = databaseService.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String returnValue;
        String workflowStatus = "";
        List<ProjectionMaster> resultList;
        try {
            Criteria cr = session.createCriteria(ProjectionMaster.class);
            if (moduleName.equals(GtnWsBpmCommonConstants.FORECAST_RETURNS)) {
                cr.add(Restrictions.eq("forecastingType", GtnWsBpmCommonConstants.FORECAST_RETURNS));
                cr.add(Restrictions.eq("projectionMasterSid", forecastProjectionSubmitBean.getProjectionId()));
            }
            resultList = cr.list();

            for (ProjectionMaster pm : resultList) {
                ProjectionMaster projMaster = pm;
                if (projMaster.getIsApproved() != null && (projMaster.getIsApproved().equals("R")
                        || projMaster.getIsApproved().equals("C") || projMaster.getIsApproved().equals("W"))) {
                    workflowStatus = "RC";
                }
                projMaster.setIsApproved("Y");
                projMaster.setModifiedDate(new Date());
                session.saveOrUpdate(projMaster);
            }

            if (workflowStatus.equals("RC")) {
                LOGGER.debug("Ending submitProjection ");
                return updateWorkflowFromForecast(forecastProjectionSubmitBean, gtnWsGeneralRequest, session);
            } else {
                LOGGER.debug("Ending submitProjection ");
                returnValue = saveWorkflow(forecastProjectionSubmitBean, gtnWsGeneralRequest, noOfUsers, session);
            }
            tx.commit();
        } catch (HibernateException | FileNotFoundException | JAXBException e) {
            LOGGER.error("Exception in submitProjection() method." + e);
            tx.rollback();
            returnValue = GtnFrameworkWebserviceConstant.NOT_SAVED;
        } finally {
            session.close();
        }
        return returnValue;
    }

    public String saveWorkflow(GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean,
            GtnWsGeneralRequest gtnWsGeneralRequest, String noOfUsers, Session session)
            throws FileNotFoundException, JAXBException {
        String path = forecastProjectionSubmitBean.getWorkflowIdGeneratorXmlPath();
        int projectionId = forecastProjectionSubmitBean.getProjectionId();
        String userId = gtnWsGeneralRequest.getUserId();
        int noOfLevels = Integer.parseInt(noOfUsers);
        String notes = forecastProjectionSubmitBean.getNotes();
        String description = forecastProjectionSubmitBean.getDescription();
        String moduleName = "";
        if (forecastProjectionSubmitBean.getModuleName().equals(GtnWsBpmCommonConstants.FORECAST_RETURNS)) {
            moduleName = "RE";
        }
        String workflowId = new GtnWorkFlowIdGeneratorService().generateId(path, moduleName);
        String docDetailsSid = "";
        GtnWsWorkflowMasterBean workflowMasterBean = new GtnWsWorkflowMasterBean(projectionId, workflowId);
        setWorkflowMasterBean(workflowMasterBean, userId, notes, noOfLevels, docDetailsSid, description, session);
        return saveWorkflowMaster(workflowMasterBean, session);
    }

    public GtnWsWorkflowMasterBean setWorkflowMasterBean(GtnWsWorkflowMasterBean workflowMasterBean, String userId,
            String notes, int noOfLevels, String getUploadedData, String description, Session session) {

        int userIdInt = Integer.parseInt(userId);
        workflowMasterBean.setWorkflowStatus(databaseService
                .getHelperTableByDescription("Pending", GtnFrameworkWebserviceConstant.WORK_FLOW_STATUS, session)
                .getHelperTableSid());
        workflowMasterBean.setCreatedBy(userIdInt);
        workflowMasterBean.setCreatedDate(new Date());
        workflowMasterBean.setNotes(notes);
        workflowMasterBean.setNoOfApprovals(noOfLevels);
        workflowMasterBean.setApprovalLevel(1);
        workflowMasterBean.setFileName(getUploadedData);
        workflowMasterBean.setWorkflowDescription(description);
        workflowMasterBean.setModifiedDate(new Date());
        return workflowMasterBean;
    }

    public String saveWorkflowMaster(GtnWsWorkflowMasterBean workflowMasterBean, Session session) {
        WorkflowMaster workflowMaster = new WorkflowMaster();
        workflowMaster.setWorkflowId(workflowMasterBean.getWorkflowId());
        workflowMaster.setWorkflowStatusId(workflowMasterBean.getWorkflowStatus());
        workflowMaster.setProjectionMaster(
                databaseService.getProjectionMaster(workflowMasterBean.getProjectionId(), session));
        workflowMaster.setCreatedBy(workflowMasterBean.getCreatedBy());
        workflowMaster.setCreatedDate(workflowMasterBean.getCreatedDate());
        workflowMaster.setNotes(workflowMasterBean.getNotes());
        workflowMaster.setNoOfApproval(workflowMasterBean.getNoOfApprovals());
        workflowMaster.setApprovalLevel(workflowMasterBean.getApprovalLevel());
        workflowMaster.setFileName(workflowMasterBean.getFileName());
        workflowMaster.setWorkflowDescrption(workflowMasterBean.getWorkflowDescription());
        workflowMaster.setModifiedDate(workflowMasterBean.getModifiedDate());
        session.saveOrUpdate(workflowMaster);
        return workflowMaster.getWorkflowId();
    }

    public String submitContract(GtnWsCommonWorkflowRequest workflowRequest, GtnUIFrameworkWebserviceRequest generalWSRequest) throws GtnFrameworkGeneralException {
        LOGGER.debug("Entering submitContract method");
        Session session = databaseService.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String returnValue;
        try {
            returnValue = saveContractWorkflow(workflowRequest, session, generalWSRequest.getGtnWsGeneralRequest());
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("Exception in submitContract() method." + e);
            tx.rollback();
            returnValue = GtnFrameworkWebserviceConstant.NOT_SAVED;
        } finally {
            session.close();
        }
        return returnValue;
    }

    public String updateContract(GtnWsCommonWorkflowRequest workflowRequest) {
        LOGGER.debug("Entering updateContract method");
        Session session = databaseService.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String returnValue;
        try {
            WorkflowMaster workflowMaster = (WorkflowMaster) session.load(WorkflowMaster.class,
                    workflowRequest.getContractBean().getWorkflowMasterSystemId());
            workflowRequest.getContractBean()
                    .setWorkflowStatus(databaseService
                            .getHelperTableByDescription(workflowRequest.getContractBean().getWorkflowStatusValue(),
                                    GtnFrameworkWebserviceConstant.WORK_FLOW_STATUS, session)
                            .getHelperTableSid());
            workflowMaster.setWorkflowStatusId(workflowRequest.getContractBean().getWorkflowStatus());
            workflowMaster.setModifiedBy(workflowRequest.getContractBean().getModifiedBy());
            workflowMaster.setModifiedDate(new Date());
            workflowMaster.setApprovedBy(workflowRequest.getContractBean().getApprovedBy());
            workflowMaster.setApprovedDate(workflowRequest.getContractBean().getApprovedDate());
            session.saveOrUpdate(workflowMaster);
            tx.commit();
            returnValue = workflowRequest.getContractBean().getWorkflowId();
        } catch (HibernateException e) {
            LOGGER.error("Exception in updateContract() method." + e);
            tx.rollback();
            returnValue = GtnFrameworkWebserviceConstant.NOT_SAVED;
        } finally {
            session.close();
        }
        return returnValue;
    }

    public String saveContractWorkflow(GtnWsCommonWorkflowRequest workflowRequest, Session session, GtnWsGeneralRequest generalWSRequest)
            throws GtnFrameworkGeneralException {
        WorkflowMaster workflowMaster = new WorkflowMaster();
        int userId = Integer.parseInt(generalWSRequest.getUserId().trim());
        try {
            GtnWsContractWorkflowBean contractBean = workflowRequest.getContractBean();
            workflowMaster = getWorkFlowMasterBasedOnContract(contractBean.getWorkflowMasterSystemId(),
                    contractBean.getContractId(), contractBean.getContractStructure(), session);
            if (workflowMaster == null) {
                workflowMaster = new WorkflowMaster();
                contractBean.setWorkflowId(new GtnWorkFlowIdGeneratorService()
                        .generateId(workflowRequest.getWorkflowGeneratorPath(), workflowRequest.getModuleName()));
            } else {
                contractBean.setWorkflowId(workflowMaster.getWorkflowId());
            }
            contractBean.setWorkflowStatus(databaseService
                    .getHelperTableByDescription("Pending", GtnFrameworkWebserviceConstant.WORK_FLOW_STATUS, session)
                    .getHelperTableSid());
            workflowMaster.setWorkflowId(contractBean.getWorkflowId());
            workflowMaster.setWorkflowStatusId(contractBean.getWorkflowStatus());
            workflowMaster.setCreatedBy(userId);
            workflowMaster.setCreatedDate(new Date());
            workflowMaster.setNotes(contractBean.getNotes());
            workflowMaster.setNoOfApproval(contractBean.getNoOfApprovals());
            workflowMaster.setApprovalLevel(contractBean.getApprovalLevel());
            workflowMaster.setFileName(contractBean.getFileName());
            workflowMaster.setWorkflowDescrption(contractBean.getWorkflowDescription());
            workflowMaster.setContractMasterSid(contractBean.getContractId());
            workflowMaster.setContractStructure(contractBean.getContractStructure());
            workflowMaster.setApprovedBy(null);
            workflowMaster.setApprovedDate(null);

            workflowMaster.setModifiedDate(new Date());
            session.saveOrUpdate(workflowMaster);
        } catch (Exception e) {
            LOGGER.error("Exception in saveContractWorkflow() method." + e);
            throw new GtnFrameworkGeneralException("Exception in saveContractWorkflow ", e);
        }
        return workflowMaster.getWorkflowId();
    }

    @SuppressWarnings("unchecked")
    public WorkflowMaster getWorkFlowMasterBasedOnContract(int workflowMasterSid, int contractId,
            String contractStructure, Session session) {
        WorkflowMaster workflowMaster = null;
        if (workflowMasterSid != 0) {
            workflowMaster = (WorkflowMaster) session.load(WorkflowMaster.class, workflowMasterSid);
        } else {
            Criteria cr = session.createCriteria(WorkflowMaster.class)
                    .add(Restrictions.eq("contractMasterSid", contractId))
                    .add(Restrictions.like("contractStructure", contractStructure));

            List<WorkflowMaster> workFlowList = cr.list();
            if (workFlowList != null && !workFlowList.isEmpty()) {
                workflowMaster = workFlowList.get(0);
            }
        }
        return workflowMaster;
    }

    public GtnWsContractWorkflowBean getGtnWsContractWorkflowBean(GtnWsContractDashboardRequest cdRequest)
            throws GtnFrameworkGeneralException {
        GtnWsContractWorkflowBean bean = null;
        Session session = databaseService.getSessionFactory().openSession();
        try {
            WorkflowMaster workflowMaster = getWorkFlowMasterBasedOnContract(cdRequest.getWorkflowMasterId(),
                    cdRequest.getContractId(), cdRequest.getContractStructure(), session);
            if (workflowMaster != null) {
                bean = new GtnWsContractWorkflowBean();
                bean.setWorkflowMasterSystemId(workflowMaster.getWorkflowMasterSid());
                bean.setWorkflowId(workflowMaster.getWorkflowId());
                bean.setApprovalLevel(
                        workflowMaster.getApprovalLevel() == null ? 0 : workflowMaster.getApprovalLevel());
                bean.setApprovedDate(workflowMaster.getApprovedDate());
                bean.setContractId(
                        workflowMaster.getContractMasterSid() == null ? 0 : workflowMaster.getContractMasterSid());
                bean.setContractStructure(workflowMaster.getContractStructure());
                bean.setCreatedBy(workflowMaster.getCreatedBy() == null ? 0 : workflowMaster.getCreatedBy());
                bean.setCreatedDate(workflowMaster.getCreatedDate());
                bean.setFileName(workflowMaster.getFileName());
                bean.setNoOfApprovals(workflowMaster.getNoOfApproval() == null ? 0 : workflowMaster.getNoOfApproval());
                bean.setNotes(workflowMaster.getNotes());
                bean.setWorkflowDescription(workflowMaster.getWorkflowDescrption());
                bean.setWorkflowStatus(
                        workflowMaster.getWorkflowStatusId() == null ? 0 : workflowMaster.getWorkflowStatusId());
                bean.setWorkflowStatusValue(
                        ((HelperTable) session.load(HelperTable.class, workflowMaster.getWorkflowStatusId()))
                                .getDescription());
                bean.setApprovedBy(workflowMaster.getApprovedBy() == null ? 0 : workflowMaster.getApprovedBy());
            }
        } catch (Exception e) {
            throw new GtnFrameworkGeneralException("Exception in getGtnWsContractWorkflowBean ", e);
        } finally {
            session.close();
        }
        return bean;
    }

    public GtnWsWorkflowMasterBean setWorkflowMasterBean(int projectionId, int workflowId, int userIdInt,
            String workflowStatus, String notes, int approvalLevel) {

        GtnWsWorkflowMasterBean workflowMasterBean = new GtnWsWorkflowMasterBean();
        workflowMasterBean.setProjectionId(projectionId);
        workflowMasterBean.setWorkflowMasterSystemId(workflowId);
        workflowMasterBean.setWorkflowStatusStr(workflowStatus);
        workflowMasterBean.setModifiedBy(userIdInt);
        workflowMasterBean.setModifiedDate(new Date());
        if (GtnWsBpmCommonConstants.APPROVED_STATUS.equalsIgnoreCase(workflowStatus)) {
            workflowMasterBean.setApprovedBy(userIdInt);
            workflowMasterBean.setApprovedDate(new Date());
        }
        workflowMasterBean.setNotes(notes);
        workflowMasterBean.setApprovalLevel(approvalLevel);
        return workflowMasterBean;
    }

    @SuppressWarnings("unchecked")
    public WorkflowMaster getWorkflowMasterByProjectionId(int projectionId) {
        Session session = databaseService.getSessionFactory().openSession();
        try {
            ProjectionMaster projectionMaster = databaseService.getProjectionMaster(projectionId, session);
            Criteria cr = session.createCriteria(WorkflowMaster.class)
                    .add(Restrictions.eq("projectionMaster", projectionMaster));
            List<WorkflowMaster> resultList;
            resultList = cr.list();
            return resultList.get(0);
        } catch (Exception ex) {
            LOGGER.error("Error in Workflow Master Projection Logic", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public String updateWorkflow(GtnWsWorkflowMasterBean workflowMasterBean) throws GtnFrameworkGeneralException {
        WorkflowMaster workflowMaster = getWorkflowMaster(workflowMasterBean.getWorkflowMasterSystemId());
        Session session = databaseService.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            if (workflowMaster != null) {
                workflowMaster.setWorkflowStatusId(
                        databaseService.getHelperTableByDescription(workflowMasterBean.getWorkflowStatusStr(),
                                GtnWsBpmCommonConstants.WORKFLOW_STATUS, session).getHelperTableSid());
                workflowMaster.setModifiedBy(workflowMasterBean.getModifiedBy());
                workflowMaster.setModifiedDate(workflowMasterBean.getModifiedDate());
                if (GtnWsBpmCommonConstants.APPROVED_STATUS
                        .equalsIgnoreCase(workflowMasterBean.getWorkflowStatusStr())) {
                    workflowMaster.setApprovedBy(workflowMasterBean.getApprovedBy());
                    workflowMaster.setApprovedDate(workflowMasterBean.getApprovedDate());
                }
                workflowMaster.setNotes(workflowMasterBean.getNotes());
                workflowMaster.setApprovalLevel(workflowMasterBean.getApprovalLevel());

                session.saveOrUpdate(workflowMaster);
                String projectionUpdated = updateProjectionMaster(workflowMasterBean, session);
                tx.commit();
                if (!projectionUpdated.equals(GtnWsBpmCommonConstants.SUCCESS)) {
                    return GtnWsBpmCommonConstants.NOT_SAVED;
                }
                return workflowMaster.getWorkflowId();
            }

        } catch (Exception ex) {
            tx.rollback();
            return GtnWsBpmCommonConstants.NOT_SAVED;
        } finally {
            session.close();
        }
        return GtnWsBpmCommonConstants.NOT_SAVED;
    }

    public String updateProjectionMaster(GtnWsWorkflowMasterBean workflowMasterBean, Session session) {
        ProjectionMaster projectionMaster = databaseService.getProjectionMaster(workflowMasterBean.getProjectionId(),
                session);

        if (workflowMasterBean.getWorkflowStatusStr().equals(GtnWsBpmCommonConstants.REJECTED_STATUS)) {
            projectionMaster.setIsApproved("R");
        } else if (workflowMasterBean.getWorkflowStatusStr().equals(GtnWsBpmCommonConstants.APPROVED_STATUS)) {
            projectionMaster.setIsApproved("A");
        } else if (workflowMasterBean.getWorkflowStatusStr().equals(GtnWsBpmCommonConstants.CANCELLED_STATUS)) {
            projectionMaster.setIsApproved("C");
        } else if (workflowMasterBean.getWorkflowStatusStr().equals(GtnWsBpmCommonConstants.WITHDRAWN_STATUS)) {
            projectionMaster.setIsApproved("W");
        }
        session.saveOrUpdate(projectionMaster);
        return GtnWsBpmCommonConstants.SUCCESS;

    }

    @SuppressWarnings("unchecked")
    private WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) throws GtnFrameworkGeneralException {
        Session session = databaseService.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(WorkflowMaster.class)
                    .add(Restrictions.eq("workflowMasterSid", workflowMasterSystemId));
            List<WorkflowMaster> results = cr.list();
            if (results != null && !results.isEmpty()) {
                return results.get(0);
            }
        } catch (Exception exception) {
            throw new GtnFrameworkGeneralException("Exception in getGtnWsContractWorkflowBean ", exception);
        } finally {
            session.close();
        }
        return null;
    }

    public String updateWorkflowFromForecast(GtnWsForecastProjectionSubmitBean workflowMasterBean,
            GtnWsGeneralRequest gtnWsGeneralRequest, Session session) {

        WorkflowMaster workflowMaster = getWorkflowMasterByProjectionId(workflowMasterBean.getProjectionId());
        workflowMaster
                .setWorkflowStatusId(databaseService.getHelperTableByDescription(GtnWsBpmCommonConstants.PENDING_STATUS,
                        GtnWsBpmCommonConstants.WORKFLOW_STATUS, session).getHelperTableSid());
        workflowMaster.setNotes(workflowMasterBean.getNotes());
        workflowMaster.setModifiedDate(new Date());
        workflowMaster.setModifiedBy(Integer.parseInt(gtnWsGeneralRequest.getUserId()));
        session.saveOrUpdate(workflowMaster);
        return workflowMaster.getWorkflowId();

    }

    public String getProcessVariable(long processId, String variable, String moduleName) {
        return bpmProcessBean.getProcessVariableLog(processId, variable,
                moduleName);
    }

}
