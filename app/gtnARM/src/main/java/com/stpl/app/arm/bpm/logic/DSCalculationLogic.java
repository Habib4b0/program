package com.stpl.app.arm.bpm.logic;

import com.liferay.portal.kernel.model.User;
import com.stpl.ifs.util.DroolsProperties;
import java.util.List;
import java.util.Properties;

public class DSCalculationLogic {

    private DSCalculationLogic() {
        /*
        Empty Constructor
         */
    }

    /**
     * The Constant LOGGER.
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DSCalculationLogic.class);
    private static final Properties properties = DroolsProperties.getPropertiesData();

    public static boolean isValidWorkflowUser(User userModel, List<String> roleList, long processIntanceId) {
        boolean returnflag = false;
//        TaskSummary taskSummary = null;
        try {

            LOGGER.debug("userName :" + userModel.getScreenName());
//            taskSummary = BPMProcessBean.getAvailableTask(processIntanceId);
//            if (taskSummary == null) {
//                return true;
//            }

//            LOGGER.debug(CommonConstant.TASK_SUMMARY + taskSummary.getName());
//            List<String> userRoles = BPMProcessBean.getPotentialOwners(taskSummary.getId(), roleList);
//            LOGGER.debug("userRoles :" + userRoles);
//            List<Role> roles = RoleLocalServiceUtil.getUserRoles(userModel.getUserId());
//            if (userRoles == null || userRoles.isEmpty()) {
//                return returnflag;
//            }
//            for (Role role : roles) {
//                if (userRoles.contains(role.getName())) {
//                    returnflag = true;
//                    break;
//                }
//            }
        } catch (Exception e) {
            LOGGER.error("Error in isValidWorkflowUser :" + e);
        }

        return returnflag;
    }

//    public static ProcessInstance startWorkflow() {
//        ProcessInstance processInstance = null;
//        try {
//            String workflowId = properties.getProperty("ARMWorkflow_WorkflowId", "ARMWorkflow.ARMWorkflow");
////            processInstance = BPMProcessBean.startProcess(workflowId, null);
//        } catch (Exception e) {
//            LOGGER.error("Error in startWorkflow :" + e);
//        }
//        return processInstance;
//    }
//
//    public static TaskSummary startAndCompleteTask(User userModel, int projectionId, long processInstanceId) {
//        TaskSummary taskSummary = null;
//        try {
//            LOGGER.debug("userId :" + userModel.getUserId());
//            LOGGER.debug("userName :" + userModel.getScreenName());
////            taskSummary = BPMProcessBean.getAvailableTask(processInstanceId);
//            LOGGER.debug(CommonConstant.TASK_SUMMARY + taskSummary.getName());
//            LOGGER.debug(CommonConstant.TASK_SUMMARY + taskSummary.getId());
//            BPMProcessBean.startTask(taskSummary.getId(), userModel.getScreenName());
//            BPMProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null);
//            WorkflowPersistance.insertWFInstanceInfo(projectionId, processInstanceId);
//        } catch (Exception e) {
//
//            LOGGER.error("Error in startAndCompleteTask :" + e);
//        }
//        return taskSummary;
//    }

}
