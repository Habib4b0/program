package com.stpl.app.gtnforecasting.bpm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;

/**
 *
 * @author arulmurugan
 */
public class BPMProcessBean {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(BPMProcessBean.class);

    public static ProcessInstance startProcess(String processName, Map<String, Object> params)  {
        KieSession ksession = BPMManagerBean.getEngine().getRuntimeEngine().getKieSession();
        ProcessInstance processInstance = null;
        try {
            processInstance = ksession.startProcess(processName, params);
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
        return processInstance;
    }

    public static void startTask(Long taskId, String userId) {
        TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
        taskService.start(taskId, userId);
    }

    public static void completeTask(Long taskId, String userID, Map<String, Object> map)  {
        TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
        taskService.complete(taskId, userID, map);
    }

    public static void claimTask(Long taskId, String actualUserId, String targetUserId)  {
        TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
        taskService.delegate(taskId, actualUserId, targetUserId);
    }

    public static TaskSummary getAvailableTask(Long processInstanceId) {
        TaskSummary taskSummary = null;
        TaskService taskService = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService();
        List<Status> status = new ArrayList<>();
        try {
            status.add(Status.Ready);
            status.add(Status.Reserved);
            status.add(Status.InProgress);
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
        String variableValue = StringUtils.EMPTY;
        AuditService auditLogService = BPMManagerBean.getEngine().getRuntimeEngine().getAuditService();
        List<VariableInstanceLog> instances = (List<VariableInstanceLog>) auditLogService.findVariableInstances(processInstanceId, variableName);
        if (instances != null && !instances.isEmpty()) {
            variableValue = instances.get(instances.size() - 1).getValue();
        }
        return variableValue;
    }

    public static List<String> getPotentialOwners(long taskId, List<String> roleList) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        List<OrganizationalEntity> list = BPMManagerBean.getEngine().getRuntimeEngine().getTaskService().getTaskById(taskId).getPeopleAssignments().getPotentialOwners();
        LOGGER.debug("OrganizationalEntity list  :" + list);
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        } else {
            for (OrganizationalEntity organizationalEntity : list) {
                roleList.add(organizationalEntity.getId());
            }
            return roleList;
        }
    }

}
