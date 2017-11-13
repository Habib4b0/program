/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardProcessBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractWorkflowBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkProcessAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkProcessAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		try {
			GtnUIFrameworkBaseComponent viewBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(2).toString());
			GtnWsContractDashboardSessionBean sharedBean = new GtnWsContractDashboardSessionBean();
			viewBaseComponent.getComponentData().setSharedPopupData(sharedBean);
			GtnWsRecordBean selectedTableValue = null;
			boolean fromWorkFlow = parameters.get(1) instanceof List;
			if (fromWorkFlow) {
				sharedBean.setViewMode(true);
				selectedTableValue = getWorkFlowProcessBeanByWorkFlow(sharedBean,
						(List<List<String>>) parameters.get(1));
				GtnUIFrameWorkActionConfig workflowBtnActionConfig = new GtnUIFrameWorkActionConfig();
				workflowBtnActionConfig.addActionParameter(GtnFrameworkContractWorkflowButtonAction.class.getName());
				GtnUIFrameworkActionExecutor.executeCustomAction(componentId, workflowBtnActionConfig);
			} else {
				GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(parameters.get(1).toString());
				selectedTableValue = (GtnWsRecordBean) tableBaseComponent.getValueFromComponent();
				sharedBean.setViewMode(selectedTableValue.getBooleanPropertyByIndex(14));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_CLOSE_BTN)
						.setVisible(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_REJECT_BTN)
						.setVisible(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_APPROVE_BTN)
						.setVisible(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_CANCEL_BTN)
						.setVisible(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_WITHDRAW_BTN)
						.setVisible(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_BACK_BTN)
						.setVisible(true);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_SUBMIT_BTN)
						.setVisible(true);
			}
			sharedBean.setProcessBean(getProcessBean(selectedTableValue));
			doProcessAction(fromWorkFlow, sharedBean, parameters, componentId);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkProcessAction", e);
		}
	}

	private void doProcessAction(boolean fromWorkFlow, GtnWsContractDashboardSessionBean sharedBean,
			List<Object> parameters, String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent tabSheetBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(3).toString());
		List<GtnUIFrameworkTabConfig> tabConfigList = tabSheetBaseComponent.getComponentData()
				.getCurrentComponentConfig().getGtnTabSheetConfigList();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_SUBMIT_BTN)
				.setEnable(!sharedBean.isViewMode());

		processContractInfoToSession(sharedBean);

		if (fromWorkFlow) {
			if (sharedBean.getHasPermission() == null) {
				GtnUIFrameWorkActionConfig permissionActionConfig = new GtnUIFrameWorkActionConfig();
				permissionActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
				permissionActionConfig.addActionParameter(GtnUIFrameworkPermissionCheckAction.class.getName());
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, permissionActionConfig);
			}
		} else {
			getWorkFlowProcessBean(sharedBean, sharedBean.getProcessBean().getContractId(), 0);
		}
		loadDatainTab(tabConfigList.get(0));
		manageTabVisibility(sharedBean, tabSheetBaseComponent, tabConfigList);
	}

	private void manageTabVisibility(GtnWsContractDashboardSessionBean sharedBean,
			GtnUIFrameworkBaseComponent tabSheetBaseComponent, List<GtnUIFrameworkTabConfig> tabConfigList)
			throws GtnFrameworkGeneralException {
		for (int tabIndex = 2; tabIndex < tabConfigList.size() - 1; tabIndex++) {
			GtnUIFrameworkTabConfig currentTabConfig = tabConfigList.get(tabIndex);
			boolean visible = sharedBean.getProcessBean().getVisibleTabIndexSet().contains(String.valueOf(tabIndex));
			tabSheetBaseComponent.setTabVisible(currentTabConfig.getComponentId(), visible);
			if (visible) {
				loadDatainTab(currentTabConfig);
			}
		}
	}

	private void loadDatainTab(GtnUIFrameworkTabConfig currentTabConfig) throws GtnFrameworkGeneralException {
		List<GtnUIFrameWorkActionConfig> contractTabActionList = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(currentTabConfig.getComponentId()).getCurrentComponentConfig()
				.getGtnUIFrameWorkActionConfigList();
		if (contractTabActionList != null) {
			GtnUIFrameworkActionExecutor.executeActionList(currentTabConfig.getComponentId(), contractTabActionList);

		}

	}

	private void processContractInfoToSession(GtnWsContractDashboardSessionBean sharedBean) {
		if (sharedBean.getProcessBean().getCfpContractId() != 0 || sharedBean.getProcessBean().getIfpContractId() != 0
				|| sharedBean.getProcessBean().getPsContractId() != 0
				|| sharedBean.getProcessBean().getRsContractId() != 0) {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
			generalWSRequest.setUserId(sharedBean.getProcessBean().getUserId());
			generalWSRequest.setSessionId(sharedBean.getProcessBean().getSessionId());
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsContractDashboardRequest(cdRequest);
			request.setGtnWsGeneralRequest(generalWSRequest);
			cdRequest.setContractId(sharedBean.getProcessBean().getContractId());
			cdRequest.setSessionDate(sharedBean.getProcessBean().getSessionDate());
			cdRequest.setCfpContractId(sharedBean.getProcessBean().getCfpContractId());
			cdRequest.setIfpContractId(sharedBean.getProcessBean().getIfpContractId());
			cdRequest.setPsContractId(sharedBean.getProcessBean().getPsContractId());
			cdRequest.setRsContractId(sharedBean.getProcessBean().getRsContractId());
			wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.PROCESS_CONTRACT_INFO_TO_SESSION,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private GtnWsContractDashboardProcessBean getProcessBean(GtnWsRecordBean selectedTableValue) {
		GtnWsContractDashboardProcessBean processBean = new GtnWsContractDashboardProcessBean();
		processBean.setSessionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		processBean.setSessionId(String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND)));
		GtnUIFrameworkGlobalUI.getCurrentSessionBean().setSessionId(processBean.getSessionId());
		GtnUIFrameworkGlobalUI.addSessionProperty("sessionId", processBean.getSessionId());
		processBean.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		processBean.setProcessBean(selectedTableValue);
		processBean.setVisibleTabIndexSet(new TreeSet<String>());
		processBean.getVisibleTabIndexSet().add("0");
		processBean.getVisibleTabIndexSet().add("1");
		processBean.getVisibleTabIndexSet().add("8");
		String memberLevel = processBean.getProcessBean().getStringPropertyByIndex(5);
		String[] splitedLevel = memberLevel.split("-");
		processBean.setHierarchyNo(splitedLevel);
		for (String level : splitedLevel) {
			int index = Integer.parseInt(level);
			int value = processBean.getProcessBean().getIntegerPropertyByIndex(5 + index);
			switch (index) {
			case 2:
				processBean.setCfpContractId(value);
				processBean.getVisibleTabIndexSet().add("2");
				processBean.getVisibleTabIndexSet().add("3");
				break;
			case 3:
				processBean.setIfpContractId(value);
				processBean.getVisibleTabIndexSet().add("4");
				processBean.getVisibleTabIndexSet().add("5");
				break;
			case 4:
				processBean.setPsContractId(value);
				processBean.getVisibleTabIndexSet().add("4");
				processBean.getVisibleTabIndexSet().add("6");
				break;
			case 5:
				processBean.setRsContractId(value);
				processBean.getVisibleTabIndexSet().add("4");
				processBean.getVisibleTabIndexSet().add("7");
				break;
			default:
				processBean.setContractId(value);
			}
		}
		return processBean;
	}

	private void getWorkFlowProcessBean(GtnWsContractDashboardSessionBean sharedBean, int contractId, int workflowId) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		cdRequest.setContractId(contractId);
		cdRequest.setWorkflowMasterId(workflowId);
		cdRequest.setContractStructure(createContractStructure(sharedBean.getProcessBean()));
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE
						+ GtnWsContractDashboardContants.GET_WORKFLOW_INFO,
				GtnFrameworkCommonStringConstants.GTN_BPM, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsContractDashboardResponse cdResponse = response.getGtnWsContractDashboardResponse();
		sharedBean.setWorkflowBean(cdResponse.getWorkflowBean());
		if (sharedBean.getWorkflowBean() == null) {

			GtnWsContractWorkflowBean workflowMaster = new GtnWsContractWorkflowBean();
			workflowMaster.setCreatedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
			workflowMaster.setCreatedDate(new Date());
			workflowMaster.setNotes("");
			workflowMaster.setNoOfApprovals(1);
			workflowMaster.setApprovalLevel(1);
			workflowMaster.setContractId(contractId);
			workflowMaster.setContractStructure(cdRequest.getContractStructure());
			sharedBean.setWorkflowBean(workflowMaster);

		} else {
			boolean isPending = GtnFrameworkCommonStringConstants.PENDNG
					.equalsIgnoreCase(sharedBean.getWorkflowBean().getWorkflowStatusValue());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_SUBMIT_BTN)
					.setEnable(!isPending && !sharedBean.isViewMode());
		}
	}

	private String createContractStructure(GtnWsContractDashboardProcessBean processBean) {
		if (processBean == null) {
			return "";
		}
		return "C" + processBean.getCfpContractId() + "|I" + processBean.getIfpContractId() + "|P"
				+ processBean.getPsContractId() + "|R" + processBean.getRsContractId();
	}

	private GtnWsRecordBean getWorkFlowProcessBeanByWorkFlow(GtnWsContractDashboardSessionBean sharedBean,
			List<List<String>> workflowList) {
		List<String> workflowKeyList = workflowList.get(0);
		List<String> workflowValueList = workflowList.get(1);
		getWorkFlowProcessBean(sharedBean,
				Integer.parseInt(workflowValueList.get(workflowKeyList.indexOf("contractMasterSid"))),
				Integer.parseInt(workflowValueList.get(workflowKeyList.indexOf("workflowId"))));
		sharedBean.getWorkflowBean().setUserType(workflowValueList.get(workflowKeyList.indexOf("userType")));
		GtnWsRecordBean bean = new GtnWsRecordBean();
		bean.setProperties(new ArrayList<>());
		int[] str = siplitContractStructure(sharedBean.getWorkflowBean().getContractStructure());
		GtnWsRecordBean.addProperties(0, "", bean.getProperties());
		GtnWsRecordBean.addProperties(1, "", bean.getProperties());
		GtnWsRecordBean.addProperties(2, "", bean.getProperties());
		GtnWsRecordBean.addProperties(3, "", bean.getProperties());
		GtnWsRecordBean.addProperties(4, ((Integer) getProcessLevel(str)).toString(), bean.getProperties());
		GtnWsRecordBean.addProperties(5, getMemberLevel(str), bean.getProperties());
		GtnWsRecordBean.addProperties(6, sharedBean.getWorkflowBean().getContractId(), bean.getProperties());
		GtnWsRecordBean.addProperties(7, str[0], bean.getProperties());
		GtnWsRecordBean.addProperties(8, str[1], bean.getProperties());
		GtnWsRecordBean.addProperties(9, str[2], bean.getProperties());
		GtnWsRecordBean.addProperties(10, str[3], bean.getProperties());
		GtnWsRecordBean.addProperties(11, 0, bean.getProperties());
		GtnWsRecordBean.addProperties(12, new Date(), bean.getProperties());
		GtnWsRecordBean.addProperties(13, new Date(), bean.getProperties());
		GtnWsRecordBean.addProperties(14, "1", bean.getProperties());
		GtnWsRecordBean.addProperties(15, 0, bean.getProperties());
		GtnWsRecordBean.addProperties(16, 0, bean.getProperties());
		GtnWsRecordBean.addProperties(17, 0, bean.getProperties());
		GtnWsRecordBean.addProperties(18, 0, bean.getProperties());
		return bean;
	}

	private int[] siplitContractStructure(String contractStructure) {
		int[] val = new int[4];
		if (contractStructure == null || contractStructure.trim().isEmpty()) {
			val[0] = val[1] = val[2] = val[3] = 0;
		} else {
			String[] raw = contractStructure.split("\\|");
			val[0] = Integer.parseInt(raw[0].replace("C", ""));
			val[1] = Integer.parseInt(raw[1].replace("I", ""));
			val[2] = Integer.parseInt(raw[2].replace("P", ""));
			val[3] = Integer.parseInt(raw[3].replace("R", ""));
		}
		return val;
	}

	private String getMemberLevel(int[] contractStructure) {

		StringBuilder stringBuilder = new StringBuilder("1");
		for (int i = 0; i < 4; i++) {
			if (contractStructure[i] != 0) {
				stringBuilder.append("-");
				stringBuilder.append((i + 2));
			}
		}
		return stringBuilder.toString();
	}

	private int getProcessLevel(int[] contractStructure) {
		int level = 1;
		for (int i = 3; i >= 0; i--) {
			if (contractStructure[i] != 0) {
				level = i + 2;
				break;
			}
		}
		return level;
	}
}
