package com.stpl.app.adminconsole.bpm.service;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;

/**
 *
 * @author arulmurugan
 */
public class BPMProcessBean {

    private static final Logger LOGGER = Logger.getLogger(BPMProcessBean.class);

    public static long startProcess(String processName, Map<String, Object> params) {

        KieSession ksession = BPMManagerBean.getEngine().getRuntimeEngine().getKieSession();

        long processInstanceId = -1;

        try {

            ProcessInstance processInstance = ksession.startProcess(processName, params);

            processInstanceId = processInstance.getId();

            LOGGER.debug("Process started ... : processInstanceId = " + processInstanceId);

        } catch (Exception e) {
            LOGGER.error(e);

            throw e;
        }

        return processInstanceId;
    }

    public static List<TaskSummary> getTaskList(String userID) {
        List<TaskSummary> summary = new ArrayList<>();
        try {
            TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
            summary = taskService.getTasksAssignedAsPotentialOwner(userID, "en-UK");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return summary;
    }

    public static void startTask(Long taskId, String userId) {
        try {
            TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
            taskService.start(taskId, userId);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static void completeTask(Long taskId, String userID, Map<String, Object> map) {
        try {
            TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
            taskService.complete(taskId, userID, map);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static TaskSummary getAvailableTask(Long processInstanceId) {
        TaskSummary taskSummary = null;
        TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
        List<Status> status = new ArrayList<>();
        try {
            status.add(Status.Ready);
            status.add(Status.Reserved);
            List<TaskSummary> tasks = taskService.getTasksByStatusByProcessInstanceId(processInstanceId, status, "en-UK");
            if (tasks != null && !tasks.isEmpty()) {
                taskSummary = tasks.get(0);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return taskSummary;
    }

    @SuppressWarnings("unchecked")
    public static String getProcessVariableLog(Long processInstanceId, String variableName) {
        String variableValue = ConstantsUtils.EMPTY;
        AuditService auditLogService = BPMManagerBean.getEngine().getRuntimeEngine().getAuditService();
        List<VariableInstanceLog> instances = (List<VariableInstanceLog>) auditLogService.findVariableInstances(processInstanceId, variableName);
        if (instances != null && !instances.isEmpty()) {
            variableValue = instances.get(instances.size() - 1).getValue();
        }
        return variableValue;
    }
}
