package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.vaadin.server.Page;
import java.util.HashMap;
import java.util.Map;


public class GtnFrameworkPsResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").getTabSheetSelectedTabIndex();
		Integer rsSystemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("systemId");
		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		if (mode == null || mode == GtnUIFrameworkModeType.ADD) {
			rsSystemId = 0;
		}
		GtnUIFrameWorkPSInfoBean priceScheduleInfoBean = new GtnUIFrameWorkPSInfoBean();
		if (rsSystemId == null || rsSystemId == 0) {
			switch (position) {
			case 0:
				resetPsInfoTab(componentId, priceScheduleInfoBean);
				break;
			case 1:
				resetItemAddtionTab(componentId);
				break;
			case 2:
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPricingTabTabMassField", componentId)
						.setPropertyValue(null);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpPricingTabMassCheck", componentId)
						.setPropertyValue(GtnFrameworkCommonStringConstants.DISABLE);
				break;
			case 3:
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPriceProtectionTabMassField", componentId)
						.setPropertyValue(null);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpPriceProtectionMassCheck", componentId)
						.setPropertyValue(GtnFrameworkCommonStringConstants.DISABLE);
				break;
			case 4:
				resetNotesTab();
				break;
			default:
				break;
			}

		} else {
			GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
			editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			List<Object> parameters = new ArrayList<>();
			parameters.add(GtnUIFrameWorkPSLoadAction.class.getName());
			parameters.add("psSearchResultTable");
			parameters.add("");
			parameters.add(Boolean.TRUE);
			parameters.add(0);
			editActionConfig.setActionParameterList(parameters);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, editActionConfig);

			switch (position) {
			case 1:
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psItemAdditionSearchField", componentId)
						.setPropertyValue(null);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psItemAdditionSearchValue", componentId)
						.setPropertyValue("");
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPleftResultTable", componentId).resetTable();
				break;
			case 2:
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPricingTabTabMassField", componentId)
						.setPropertyValue(null);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpPricingTabMassCheck", componentId)
						.setPropertyValue(GtnFrameworkCommonStringConstants.DISABLE);
				break;
			case 3:
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPriceProtectionTabMassField", componentId)
						.setPropertyValue(null);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpPriceProtectionMassCheck", componentId)
						.setPropertyValue(GtnFrameworkCommonStringConstants.DISABLE);
				break;
            default:
            	Page.getCurrent().reload();
				break;
			}
		}
	}

	private void resetItemAddtionTab(String componentId) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psItemAdditionSearchField", componentId).setPropertyValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psItemAdditionSearchValue", componentId).setPropertyValue("");
                loadDataFromService();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPleftResultTable", componentId).resetTable();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPrightResultTable", componentId).resetTable();                
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPricingTabResultDataTable", componentId).resetTable();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPriceProtectionTabResultDataTable", componentId).resetTable();
	}
        
        private void loadDataFromService() throws GtnFrameworkValidationFailedException {
            GtnWsRecordBean gtnWsRecordBean = null;
			gtnWsRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPleftResultTable")
					.getValueFromComponent();
            String ifpId = gtnWsRecordBean.getPropertyValueByIndex(8).toString();
            Map<String, String> inputValueMap = new HashMap<>();
			inputValueMap.put("ifpId", ifpId);
			inputValueMap.put(GtnFrameworkCommonConstants.SESSION_ID,
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
			inputValueMap.put("userId", GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputValueMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);

		wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.DELETE_ON_RESET, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void resetPsInfoTab(String componentId, GtnUIFrameWorkPSInfoBean priceScheduleInfoBean) {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleId1", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNo1", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleName1", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleStatus1", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleStartDate", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleEndDate", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsEndDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleDesignation", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsDesignation());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleID", componentId)
				.setPropertyValue(priceScheduleInfoBean.getParentPsId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleName", componentId).setPropertyValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleType1", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsType());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleCategory", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsCategory());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleTradeClass", componentId)
				.setPropertyValue(priceScheduleInfoBean.getPsTradeClass());
	}

	private void resetNotesTab() {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.refreshNotesTab();
	}

}
