package com.stpl.app.arm.bpm.logic;

import com.stpl.app.arm.bpm.persistance.WorkflowPersistance;
import com.stpl.app.arm.bpm.service.BPMProcessBean;
import com.stpl.ifs.util.DroolsProperties;
import java.util.List;
import java.util.Properties;

import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;

import com.stpl.portal.model.Role;
import com.stpl.portal.model.User;
import com.stpl.portal.service.RoleLocalServiceUtil;

public class DSCalculationLogic {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DSCalculationLogic.class);
    private static final Properties properties = DroolsProperties.getPropertiesData();

    public static boolean isValidWorkflowUser(User userModel, List<String> roleList, long processIntanceId) {
        boolean returnflag = false;
        TaskSummary taskSummary = null;
        try {

            LOGGER.debug("userName :" + userModel.getScreenName());
            taskSummary = BPMProcessBean.getAvailableTask(processIntanceId);
            if (taskSummary == null) {
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
            String workflowId = properties.getProperty("ARMWorkflow_WorkflowId", "ARMWorkflow.ARMWorkflow");
            processInstance = BPMProcessBean.startProcess(workflowId, null);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return processInstance;
    }

    public static TaskSummary startAndCompleteTask(User userModel, int projectionId, long processInstanceId) {
        TaskSummary taskSummary = null;
        try {
            LOGGER.debug("userId :" + userModel.getUserId());
            LOGGER.debug("userName :" + userModel.getScreenName());
            taskSummary = BPMProcessBean.getAvailableTask(processInstanceId);
            LOGGER.debug("taskSummary :" + taskSummary.getName());
            LOGGER.debug("taskSummary :" + taskSummary.getId());
            BPMProcessBean.startTask(taskSummary.getId(), userModel.getScreenName());
            BPMProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null);
            WorkflowPersistance.insertWFInstanceInfo(projectionId, processInstanceId);
        } catch (Exception e) {

            LOGGER.error(e);
        }
        return taskSummary;
    }

}
