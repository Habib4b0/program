package com.stpl.app.arm.bpm.logic;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Map;

public class VarianceCalculationLogic {

    private VarianceCalculationLogic() {
        /*
        Empty Constructor
         */
    }

    /**
     * The Constant LOGGER.
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VarianceCalculationLogic.class);

    public static void submitWorkflow(final String userId, final Long processInstanceId, final Map<String, Object> params) {
        int count = 1;
        for (int i = 0; i < NumericConstants.FIVE && i < count; i++) {
            try {
                User user = UserLocalServiceUtil.getUser(Long.parseLong(userId));

//                TaskSummary task = BPMProcessBean.getAvailableTask(processInstanceId);
//                LOGGER.debug("task.getName() :" + task.getName());
//                LOGGER.debug("task.getId() :" + task.getId());
//                LOGGER.debug("task.getActualOwnerId() :" + task.getActualOwnerId());
//                LOGGER.debug("user.getScreenName() : " + user.getScreenName());
//                if (task.getActualOwnerId() != null && !task.getActualOwnerId().equals(user.getScreenName())) {
//                    BPMProcessBean.claimTask(task.getId(), task.getActualOwnerId(), user.getScreenName());
//                    LOGGER.debug("Claiming the " + task.getActualOwnerId() + " to :" + user.getScreenName());
//                }
//                if (task.getStatus().equals(Status.InProgress)) {
//                    BPMProcessBean.completeTask(task.getId(), user.getScreenName(), params);
//                } else {
//                    BPMProcessBean.startTask(task.getId(), user.getScreenName());
//                    BPMProcessBean.completeTask(task.getId(), user.getScreenName(), params);
//                }
                count = 0;
            } catch (Exception e) {
                count++;
                LOGGER.error("Error in submitWorkflow :" + e);
            }
        }
    }

}
