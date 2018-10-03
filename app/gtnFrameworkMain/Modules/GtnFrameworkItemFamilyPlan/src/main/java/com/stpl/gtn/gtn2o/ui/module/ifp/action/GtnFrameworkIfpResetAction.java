package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkIfpResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering GtnFrameworkCfpResetAction doAction ");

		int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifptabSheet").getTabSheetSelectedTabIndex();
		try {
			Integer cfpModelSid = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid");
			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
			GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
			GtnIFamilyPlanInformationBean ifpInfoBean = new GtnIFamilyPlanInformationBean();
			ifpBean.setIfpInfo(ifpInfoBean);
                          GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			if (cfpModelSid == null || cfpModelSid == 0) {
				switch (position) {
				case 0:
					setValueToComponents(ifpBean, componentId);
					break;
				case 1:
                                   
				generalWSRequest.setUserId(String
						.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
				generalWSRequest.setSessionId(String.valueOf(
						GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
				gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
                                    new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
							GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
									+ GtnWsIFamilyPlanContants.GTN_WS_IFP_TEMP_DELETE_SERVICE,
							gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
					GtnUIFrameworkBaseComponent pricingTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE);
					pricingTable.resetFilter();
					pricingTable.removeAllContainerFilters();
					pricingTable.removeAllItemsFromTable();

					GtnUIFrameworkBaseComponent selectedTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent("ifpleftResultTable");
					selectedTable.removeAllGridItems();
					selectedTable.resetFilter();
					selectedTable.removeAllContainerFilters();
					GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
							.removeAllGridItems();
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionSearchField", componentId)
							.loadComboBoxComponentValue(null);

					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionSearchValue", componentId)
							.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);

					break;
				case 2:
                                 
				generalWSRequest.setUserId(String
						.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
				generalWSRequest.setSessionId(String.valueOf(
						GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
				gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
                                    new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
							GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
									+ GtnWsIFamilyPlanContants.GTN_WS_IFP_TEMP_DELETE_SERVICE,
							gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
					GtnUIFrameworkBaseComponent itemsTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE);
					itemsTable.removeAllGridItems();
					itemsTable.resetFilter();
					itemsTable.removeAllContainerFilters();
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
							.removeAllItemsFromTable();
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpMassCheck")
							.setPropertyValue(GtnFrameworkCommonStringConstants.DISABLE);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild", componentId)
							.loadDateValue(null);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField", componentId)
							.loadComboBoxComponentValue(null);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown", componentId)
							.loadComboBoxComponentValue(null);

					break;
				case 3:
					GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
							.getVaadinComponent("notesTab");
					notesTab.refreshNotesTab();
					break;
				default:
					break;
				}
			} else {				
				generalWSRequest.setUserId(String
						.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
				generalWSRequest.setSessionId(String.valueOf(
						GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
				gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
				GtnWsIfpRequest ifpRequest = new GtnWsIfpRequest();
				ifpRequest.setGtnIFamilyPlan(ifpBean);
				ifpInfoBean.setIfpSid(cfpModelSid);
				gtnRequest.setGtnWsIfpRequest(ifpRequest);                              
				GtnUIFrameworkWebserviceResponse response = getResetResponse(gtnRequest);
				switch (position) {
				case 0:
					setValueToComponents(response.getGtnWsIfpReponse().getGtnIFamilyPlan(), componentId);
					break;
				case 1:
					GtnUIFrameworkBaseComponent pricingTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE);

					pricingTable.resetFilter();
					pricingTable.removeAllContainerFilters();
					GtnUIFrameworkBaseComponent selectedTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent("ifpleftResultTable");
					selectedTable.resetFilter();
					selectedTable.removeAllContainerFilters();
					GtnUIFrameworkPagedTableLogic ifpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
							.getLogicFromPagedDataTable();
					ifpCaTabRightTablelogic.startSearchProcess(null, true);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionSearchField", componentId)
							.loadComboBoxComponentValue(null);

					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionSearchValue", componentId)
							.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
					break;
				case 2:
					GtnUIFrameworkBaseComponent table2 = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE);
					table2.resetFilter();
					table2.removeAllContainerFilters();
					GtnUIFrameworkPagedTableLogic ifpItemsTabTablelogic = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
							.getLogicFromPagedDataTable();
					ifpItemsTabTablelogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpMassCheck")
							.setPropertyValue(GtnFrameworkCommonStringConstants.DISABLE);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild", componentId)
							.loadDateValue(null);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField", componentId)
							.loadComboBoxComponentValue(null);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown", componentId)
							.loadComboBoxComponentValue(null);
					break;
				case 3:
					GtnUIFrameWorkActionConfig ifpResetNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
							GtnUIFrameworkActionType.LOAD_NOTES_TAB);
					ifpResetNotesTabLoadAction
							.addActionParameter(response.getGtnWsIfpReponse().getGtnIFamilyPlan().getNotesTabList());
					ifpResetNotesTabLoadAction.addActionParameter(
							response.getGtnWsIfpReponse().getGtnIFamilyPlan().getIfpInfo().getInternalNotes());
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, ifpResetNotesTabLoadAction);
					break;
				default:
					break;
				}
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifptabSheet").setSelectedTabByPostion(0);

			}
		} catch (Exception e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkCfpResetAction ", e);
		} finally {
			gtnLogger.info("Exit GtnFrameworkCfpResetAction doAction ");
		}

	}

	/**
	 * @param gtnRequest
	 * @return
	 */
	public GtnUIFrameworkWebserviceResponse getResetResponse(GtnUIFrameworkWebserviceRequest gtnRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_FETCH_INFORMATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkIfpResetAction();
	}

	public void setValueToComponents(GtnIFamilyPlanBean bean, String componentId) throws GtnFrameworkGeneralException {
		GtnIFamilyPlanInformationBean ifpInfoBean = bean.getIfpInfo();
		List<Object> ifpFieldValues = Arrays.asList(ifpInfoBean.getIfpId(), ifpInfoBean.getIfpNo(),
				ifpInfoBean.getIfpName(), ifpInfoBean.getIfpId(), ifpInfoBean.getIfpNo(), ifpInfoBean.getIfpName(),
				ifpInfoBean.getIfpStatus(), ifpInfoBean.getIfpStartDate(), ifpInfoBean.getIfpEndDate(),
				ifpInfoBean.getIfpType(), ifpInfoBean.getIfpCategory(), ifpInfoBean.getIfpDesignation(),
				ifpInfoBean.getParentIfpId(), ifpInfoBean.getParentIfpName(), ifpInfoBean.getCreatedDate(),
				ifpInfoBean.getModifiedDate(), ifpInfoBean.getModifiedBy());

		GtnUIFrameWorkActionConfig ifpResetDefaultValueActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		ifpResetDefaultValueActionConfig.addActionParameter(GtnFrameworkIfpStringContants.IFP_FIELDS);
		ifpResetDefaultValueActionConfig.addActionParameter(ifpFieldValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, ifpResetDefaultValueActionConfig);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabCreatedBy", componentId)
				.loadFieldValue(ifpInfoBean.getCreatedBy());

		GtnUIFrameWorkActionConfig fpAddModeDisableAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DISABLE_ACTION);
		fpAddModeDisableAction.setActionParameterList(GtnFrameworkIfpStringContants.DISABLED_IFP_FIELDS);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, fpAddModeDisableAction);

	}
}
