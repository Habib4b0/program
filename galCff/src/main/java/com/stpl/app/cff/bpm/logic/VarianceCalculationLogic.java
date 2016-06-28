package com.stpl.app.cff.bpm.logic;

import com.stpl.app.cff.bpm.service.BPMProcessBean;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import java.util.Map;

import org.kie.api.task.model.TaskSummary;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import org.kie.api.task.model.Status;

public class VarianceCalculationLogic {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(VarianceCalculationLogic.class);
    static String notiMsg = "";

    public static void submitWorkflow(final String userId, final Long processInstanceId, final Map<String, Object> params, final String state) {

        try {
            User user = UserLocalServiceUtil.getUser(Long.parseLong(userId));

            TaskSummary task = null;
            task = BPMProcessBean.getAvailableTask(processInstanceId, user.getScreenName());
            LOGGER.info("taskSummary :" + task.getName());
            LOGGER.info("taskSummary :" + task.getId());
            if (task.getActualOwnerId() != null && !task.getActualOwnerId().equals(user.getScreenName())) {
                BPMProcessBean.claimTask(task.getId(), task.getActualOwnerId(), user.getScreenName());
                LOGGER.info("Claiming the " + task.getActualOwnerId() + " to :" + user.getScreenName());
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
            }
            new AbstractNotificationUtils() {                
                @Override
                public void noMethod() {
                    
                }
                @Override
                public void yesMethod() {  
                    submitWorkflow(userId, processInstanceId, params,"submit");
                }
                
            }.getConfirmationMessage("Error", notiMsg);
            LOGGER.error(e.getMessage());
        } 
    }
}
