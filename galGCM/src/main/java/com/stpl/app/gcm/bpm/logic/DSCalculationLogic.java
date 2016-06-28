package com.stpl.app.gcm.bpm.logic;

import java.util.List;
import java.util.Properties;

import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;

import com.stpl.app.gcm.bpm.persistance.WorkflowPersistance;
import com.stpl.app.gcm.bpm.service.BPMProcessBean;
import com.stpl.app.utils.DroolsProperties;
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

			LOGGER.info("userName :" + userModel.getScreenName());
			taskSummary = BPMProcessBean.getAvailableTask(processIntanceId, userModel.getScreenName());
                        if(taskSummary == null){
//                           String notiMsg = "Something went wrong while submitting this projection. Please submit again";
//                           NotificationUtils.getAlertNotification("Error", notiMsg);                           
                           LOGGER.info("taskSummary id:" + taskSummary.getId());                        
                           return true;
                        }

			LOGGER.info("taskSummary :" + taskSummary.getName());
			List<String> userRoles = BPMProcessBean.getPotentialOwners(taskSummary.getId(), roleList);
			LOGGER.info("userRoles :" + userRoles);
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
                    LOGGER.error(e.getMessage());
		}
		return returnflag;
	}

	public static ProcessInstance startWorkflow() {
		ProcessInstance processInstance = null;
		try {
			String workflowId = properties.getProperty("Forecasting_WorkflowId", "ForecastingWorkflow.SubmissionWorkflow");
			processInstance = BPMProcessBean.startProcess(workflowId, null);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return processInstance;
	}

    public static ProcessInstance startGCMWorkflow() throws Exception {
        String workflowId = properties.getProperty("Forecasting_WorkflowId", "ForecastingWorkflow.SubmissionWorkflow");
        ProcessInstance processInstance = BPMProcessBean.startProcess(workflowId, null);
        return processInstance;
    }
        
	public static TaskSummary startAndCompleteTask(User userModel, int projectionId, long processInstanceId) {
		TaskSummary taskSummary = null;
		try {
			LOGGER.info("userId :" + userModel.getUserId());
			LOGGER.info("userName :" + userModel.getScreenName());
			taskSummary = BPMProcessBean.getAvailableTask(processInstanceId, userModel.getScreenName());
			LOGGER.info("taskSummary :" + taskSummary.getName());
			LOGGER.info("taskSummary :" + taskSummary.getId());
			BPMProcessBean.startTask(taskSummary.getId(), userModel.getScreenName());
			BPMProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null);
			WorkflowPersistance.insertWFInstanceInfo(projectionId, processInstanceId);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return taskSummary;
	}
			}
