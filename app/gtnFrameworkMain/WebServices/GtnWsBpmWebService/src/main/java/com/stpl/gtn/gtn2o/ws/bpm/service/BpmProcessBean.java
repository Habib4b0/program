package com.stpl.gtn.gtn2o.ws.bpm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import org.springframework.stereotype.Service;

/**
 *
 * @author STPL
 */
@Service
public class BpmProcessBean {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(BpmProcessBean.class);
	@Autowired
	private BpmManagerBean bpmManagerBean;

	public BpmProcessBean() {
		super();
	}

	public BpmProcessBean(BpmManagerBean bpmManagerBean) {
		super();
		this.bpmManagerBean = bpmManagerBean;
	}

	public BpmManagerBean getBpmManagerBean() {
		return bpmManagerBean;
	}


	public ProcessInstance startProcess(String processName, Map<String, Object> params, String moduleName) {
		ProcessInstance processInstance = null;
		KieSession  ksession=null;
		 ksession = bpmManagerBean.getRuntimeEngine(moduleName).getKieSession();
	
		try {
			processInstance = ksession.startProcess(processName, params);
		} catch (Exception e) {
			LOGGER.error("Exception in startProcess()", e);
			throw e;
		}
		return processInstance;
	}

	public void startTask(Long taskId, String userId, String moduleName) {
		TaskService taskService = bpmManagerBean.getRuntimeEngine(moduleName).getTaskService();
		taskService.start(taskId, userId);
	}

	public void completeTask(Long taskId, String userID, Map<String, Object> map, String moduleName) {
		TaskService taskService = bpmManagerBean.getRuntimeEngine(moduleName).getTaskService();
		taskService.complete(taskId, userID, map);
	}

	public void claimTask(Long taskId, String actualUserId, String targetUserId, String moduleName) {
		TaskService taskService = bpmManagerBean.getRuntimeEngine(moduleName).getTaskService();
		taskService.delegate(taskId, actualUserId, targetUserId);
	}

	public TaskSummary getAvailableTask(Long processInstanceId, String moduleName) {
		TaskSummary taskSummary = null;
		TaskService taskService = bpmManagerBean.getRuntimeEngine(moduleName).getTaskService();
		List<Status> status = new ArrayList<>();
		try {
			status.add(Status.Ready);
			status.add(Status.Reserved);
			status.add(Status.InProgress);
			List<TaskSummary> tasks = taskService.getTasksByStatusByProcessInstanceId(processInstanceId, status,
					"en-UK");
			if (tasks != null && !tasks.isEmpty()) {
				taskSummary = tasks.get(0);

			}
		} catch (Exception e) {
			LOGGER.error("Exception in getAvailableTask()", e);
		}
		return taskSummary;
	}

	@SuppressWarnings("unchecked")
	public String getProcessVariableLog(Long processInstanceId, String variableName, String moduleName) {
		String variableValue = "";
		AuditService auditLogService = bpmManagerBean.getRuntimeEngine(moduleName).getAuditService();
		List<VariableInstanceLog> instances = (List<VariableInstanceLog>) auditLogService
				.findVariableInstances(processInstanceId, variableName);
		if (instances != null && !instances.isEmpty()) {
			variableValue = instances.get(instances.size() - 1).getValue();
		}
		return variableValue;
	}

	public List<String> getPotentialOwners(long taskId, List<String> roleList, String moduleName) {
		List<String> rolesNameList = roleList!= null ? new ArrayList<>(roleList) : roleList;
		List<OrganizationalEntity> list = bpmManagerBean.getRuntimeEngine(moduleName).getTaskService()
				.getTaskById(taskId).getPeopleAssignments().getPotentialOwners();
		LOGGER.info("OrganizationalEntity list  :" + list);
		if (list == null || list.isEmpty()) {
			return Collections.emptyList();
		} else {
                    if(rolesNameList != null)
                    {
			for (OrganizationalEntity organizationalEntity : list) {
				rolesNameList.add(organizationalEntity.getId());
			}
                    }
			return rolesNameList;
		}
	}

}
