package com.stpl.app.contract.bpm.logic;

import java.util.List;
import java.util.Properties;

import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;

import com.stpl.app.contract.bpm.persistance.WorkflowPersistance;
import com.stpl.app.contract.bpm.service.BPMProcessBean;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.ifs.util.DroolsProperties;
import com.stpl.portal.model.Role;
import com.stpl.portal.model.User;
import com.stpl.portal.service.RoleLocalServiceUtil;
import java.util.Map;
import org.kie.api.task.model.Status;

public class BPMLogic {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(BPMLogic.class);
    private static final Properties properties = DroolsProperties.getPropertiesData();
    static String notiMsg = "";

    public static boolean isValidWorkflowUser(User userModel, List<String> roleList, long processIntanceId) {
        boolean returnflag = false;
        TaskSummary taskSummary = null;
        try {

            LOGGER.debug("userName :" + userModel.getScreenName());
            taskSummary = BPMProcessBean.getAvailableTask(processIntanceId);
            if(taskSummary == null){
                LOGGER.debug("taskSummary id:" + taskSummary.getId());                        
                return true;
            }
            LOGGER.debug("taskSummary :" + taskSummary.getName());
            List<String> userRoles = BPMProcessBean.getPotentialOwners(taskSummary.getId(), roleList);
            LOGGER.debug("userRoles :" + userRoles);
            List<Role> roles = RoleLocalServiceUtil.getUserRoles(userModel.getUserId());
            if (userRoles == null || userRoles.isEmpty()) {
                return returnflag;
            }
            for (Role role : roles) {
                if (userRoles.contains(role.getName())) {
                    returnflag = true;
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return returnflag;
    }

    public static ProcessInstance startWorkflow() {
        ProcessInstance processInstance = null;
        try {
//			Contract_WorkflowId : ForecastingWorkflow.ContractSubmissionWorkflow
            String workflowId = properties.getProperty("Contract_WorkflowId", "ForecastingWorkflow.ContractSubmissionWorkflow");
            processInstance = BPMProcessBean.startProcess(workflowId, null);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return processInstance;
    }

    public static TaskSummary startAndCompleteTask(User userModel, int projectionId, long processInstanceId, Integer[] contractStrut, boolean isNewEntry) {
        TaskSummary taskSummary = null;
        try {
            LOGGER.debug("userId :" + userModel.getUserId());
            LOGGER.debug("userName :" + userModel.getScreenName());
            taskSummary = BPMProcessBean.getAvailableTask(processInstanceId);
            LOGGER.debug("taskSummary :" + taskSummary.getName());
            LOGGER.debug("taskSummary :" + taskSummary.getId());
            BPMProcessBean.startTask(taskSummary.getId(), userModel.getScreenName());
            BPMProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null);
            if (isNewEntry) {
                WorkflowPersistance.insertWFInstanceInfo(projectionId, processInstanceId, contractStrut);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return taskSummary;
    }
    public static void submitWorkflow(final User user, final Long processInstanceId, final Map<String, Object> params, final String state) {

        try {
            TaskSummary task = null;
            task = BPMProcessBean.getAvailableTask(processInstanceId);
            LOGGER.debug("taskSummary :" + task.getName());
            LOGGER.debug("taskSummary :" + task.getId());
            if (task.getActualOwnerId() != null && !task.getActualOwnerId().equals(user.getScreenName())) {
                BPMProcessBean.claimTask(task.getId(), task.getActualOwnerId(), user.getScreenName());
                LOGGER.debug("Claiming the " + task.getActualOwnerId() + " to :" + user.getScreenName());
            }
            if (task.getStatus().equals(Status.InProgress)) {
                BPMProcessBean.completeTask(task.getId(), user.getScreenName(), params);
            } else {
                BPMProcessBean.startTask(task.getId(), user.getScreenName());
                BPMProcessBean.completeTask(task.getId(), user.getScreenName(), params);
            }
        } catch (Exception e) {
            switch (state) {
                case "submit":
                    notiMsg = "Something went wrong while submitting projection. Are you sure you want to proceed with submit?";
                    break;
                case "approve":
                    notiMsg = "Something went wrong while approving projection. Are you sure you want to proceed with approve?";
                    break;

                case "reject":
                    notiMsg = "Something went wrong while rejecting projection. Are you sure you want to proceed with reject?";
                    break;

                case "cancel":
                    notiMsg = "Something went wrong while cancelling projection. Are you sure you want to proceed with cancel?";
                    break;
          
                case "withdraw":
                    notiMsg = "Something went wrong while withdrawing projection. Are you sure you want to proceed with withdraw?";
                    break;
            }
            new AbstractNotificationUtils() {                
                @Override
                public void noMethod() {
                    
                }
                @Override
                public void yesMethod() {  
                    submitWorkflow(user, processInstanceId, params,"submit");
                }
                
            }.getConfirmationMessage("Error", notiMsg);
            LOGGER.error(e);
        } 
    }
    
}
