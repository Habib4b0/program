package com.stpl.app.gtnforecasting.bpm.logic;

import com.stpl.app.gtnforecasting.ui.form.ForecastForm;
import java.util.Map;

public class VarianceCalculationLogic {

    static ForecastForm forecastForm;
    static String notiMsg = "";
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(VarianceCalculationLogic.class);

    public static void submitWorkflow(final String userId, final Long processInstanceId, final Map<String, Object> params) {
//        try {
//            User user = UserLocalServiceUtil.getUser(Long.parseLong(userId));
//
//            TaskSummary task = BPMProcessBean.getAvailableTask(processInstanceId);
//            LOGGER.info("task.getName() :" + task.getName());
//            LOGGER.info("task.getId() :" + task.getId());
//            LOGGER.info("task.getActualOwnerId() :" + task.getActualOwnerId());
//            LOGGER.info("user.getScreenName() : " + user.getScreenName());
//            if (task.getActualOwnerId() != null && !task.getActualOwnerId().equals(user.getScreenName())) {
//                BPMProcessBean.claimTask(task.getId(), task.getActualOwnerId(), user.getScreenName());
//                LOGGER.info("Claiming the " + task.getActualOwnerId() + " to :" + user.getScreenName());
//            }
//            if (task.getStatus().equals(Status.InProgress)) {
//                BPMProcessBean.completeTask(task.getId(), user.getScreenName(), params);
//            } else {
//                BPMProcessBean.startTask(task.getId(), user.getScreenName());
//                BPMProcessBean.completeTask(task.getId(), user.getScreenName(), params);
//            }
//        } catch (Exception e) {
//            LOGGER.error(e);
//        }
    }

}
