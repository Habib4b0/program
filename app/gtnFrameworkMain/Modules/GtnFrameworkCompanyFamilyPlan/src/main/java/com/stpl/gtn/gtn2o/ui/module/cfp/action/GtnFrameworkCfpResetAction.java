package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.v7.data.Property;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.TabSheet;

public class GtnFrameworkCfpResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("Entering GtnFrameworkCfpResetAction doAction ");
		TabSheet tabsheet = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpTabSheet").getAsTabSheet();
		int position = tabsheet.getTabPosition(tabsheet.getTab(tabsheet.getSelectedTab()));
		try {
			Integer cfpModelSid = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("cfpModelSid");

			GtnCFamilyPlan cfpBean = new GtnCFamilyPlan();
			GtnCFamilyPlanInformation cfpInfoBean = new GtnCFamilyPlanInformation();
			cfpBean.setCfpInfo(cfpInfoBean);
			if (cfpModelSid == null || cfpModelSid == 0) {
				addModeReset(cfpBean, componentId, position);
				if (position == 1 || position == 2) {
					GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
					GtnUIFrameworkGlobalUI.addSessionProperty("cfpModelSid", 0);
					GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
					generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
					generalWSRequest
							.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
					gtnRequest.setGtnWsGeneralRequest(generalWSRequest);

					new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
							GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
									+ GtnWsCFamilyPlanContants.GTN_WS_CFP_TEMP_DELETE_SERVICE,
							gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				}
			} else {
				GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
				cfpInfoBean.setCfpSid(cfpModelSid);
				GtnWsCfpRequest cfpRequest = new GtnWsCfpRequest();
				cfpRequest.setGtnCFamilyPlan(cfpBean);
				gtnRequest.setGtnWsCfpRequest(cfpRequest);
				GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
				generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
				generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
				gtnRequest.setGtnWsGeneralRequest(generalWSRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
								+ GtnWsCFamilyPlanContants.GTN_WS_CFP_FETCH_INFORMATION_SERVICE,
						gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

				switch (position) {
				case 0:
					setValueToComponents(response.getGtnWsCfpReponse().getGtnCFamilyPlan(), componentId, true);
					break;
				case 1:

					ExtPagedTable<?> pricingTable = (ExtPagedTable<?>) GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE)
							.getExtCustomTable();
					pricingTable.resetFilters();
					((ExtContainer<?>) pricingTable.getContainerDataSource()).removeAllContainerFilters();
					GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE)
							.getLogicFromPagedDataTable();
					cfpCaTabRightTablelogic.startSearchProcess(null, Boolean.TRUE);

					ExtPagedTable<?> selectedTable = (ExtPagedTable<?>) GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent("CFPleftResultTable").getExtCustomTable();
					selectedTable.getContainerDataSource().removeAllItems();
					selectedTable.resetFilters();
					((ExtContainer<?>) selectedTable.getContainerDataSource()).removeAllContainerFilters();
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPCompanyAdditionSearchField", componentId)
							.loadComboBoxComponentValue(null);

					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPCompanyAdditionSearchValue", componentId)
							.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
					break;
				case 2:
					ExtPagedTable<?> resultTable = (ExtPagedTable<?>) GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
							.getExtCustomTable();
					resultTable.resetFilters();
					((ExtContainer<?>) resultTable.getContainerDataSource()).removeAllContainerFilters();
					GtnUIFrameworkPagedTableLogic cfpCompaniesTabTablelogic = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
							.getLogicFromPagedDataTable();
					cfpCompaniesTabTablelogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(),
							Boolean.TRUE);
					OptionGroup massCheck = (OptionGroup) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpMassCheck")
							.getComponent();
					massCheck.setValue(GtnFrameworkCommonStringConstants.DISABLE);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDateFeild", componentId)
							.loadDateValue(null);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassField", componentId)
							.loadComboBoxComponentValue(null);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDropDown", componentId)
							.loadComboBoxComponentValue(null);
					break;
				case 3:
					loadNotesTab(response.getGtnWsCfpReponse().getGtnCFamilyPlan(), componentId);
					break;
				default:
					break;
				}

			}

		} catch (GtnFrameworkValidationFailedException | UnsupportedOperationException | Property.ReadOnlyException e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkCfpResetAction ", e);
		} finally {
			gtnLogger.info("Exit GtnFrameworkCfpResetAction doAction ");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnCFamilyPlan bean, String componentId, boolean isEditMode) {
		GtnCFamilyPlanInformation info = bean.getCfpInfo();
		try {

			GtnUIFrameWorkActionConfig cfpResetDefaultActionConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
			if (isEditMode) {
				info.setCreatedDate(new Date());
				info.setModifiedDate(null);
				info.setCreatedBy(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				info.setModifiedBy(GtnFrameworkCommonStringConstants.STRING_EMPTY);

			}
			List<Object> values = Arrays.asList(new Object[] { info.getCfpId(), info.getCfpNo(), info.getCfpName(),
					info.getCfpId(), info.getCfpNo(), info.getCfpName(), info.getModifiedBy(), info.getParentCfpId(),
					info.getParentCfpName(), info.getCfpStatus(), info.getCfpType(), info.getCfpCategory(),
					info.getCfpTradeClass(), info.getCfpDesignation(), info.getSalesInclusion(), info.getCfpStartDate(),
					info.getCfpEndDate(), info.getCreatedDate(), info.getModifiedDate() });
			cfpResetDefaultActionConfig.addActionParameter(GtnFrameworkCfpStringContants.CFP_FIELDS);
			cfpResetDefaultActionConfig.addActionParameter(values);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cfpResetDefaultActionConfig);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPId", componentId)
					.setComponentReadOnly(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPName", componentId)
					.setComponentReadOnly(true);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}

	}

	private void loadNotesTab(GtnCFamilyPlan bean, String componentId) {

		try {
			GtnUIFrameWorkActionConfig cfpResetNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.LOAD_NOTES_TAB);
			cfpResetNotesTabLoadAction.addActionParameter(bean.getNotesTabList());
			cfpResetNotesTabLoadAction.addActionParameter(bean.getCfpInfo().getInternalNotes());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cfpResetNotesTabLoadAction);
		} catch (Exception e) {
			gtnLogger.error("Exception in loading Notes Tab", e);
		}
	}

	private void addModeReset(GtnCFamilyPlan cfpBean, String componentId, int position) {
		switch (position) {
		case 0:
			setValueToComponents(cfpBean, componentId, false);
			break;
		case 1:
			ExtPagedTable<?> pricingTable = (ExtPagedTable<?>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE).getExtCustomTable();
			pricingTable.resetFilters();
			pricingTable.getContainerDataSource().removeAllItems();
			((ExtContainer<?>) pricingTable.getContainerDataSource()).removeAllContainerFilters();
			ExtPagedTable<?> selectedTable = (ExtPagedTable<?>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("CFPleftResultTable").getExtCustomTable();
			selectedTable.getContainerDataSource().removeAllItems();
			selectedTable.resetFilters();
			((ExtContainer<?>) selectedTable.getContainerDataSource()).removeAllContainerFilters();
			ExtPagedTable<?> resultTable = (ExtPagedTable<?>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
					.getExtCustomTable();
			resultTable.getContainerDataSource().removeAllItems();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPCompanyAdditionSearchField", componentId)
					.loadComboBoxComponentValue(null);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPCompanyAdditionSearchValue", componentId)
					.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			break;
		case 2:
			ExtPagedTable<?> table2 = (ExtPagedTable<?>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
					.getExtCustomTable();
			table2.getContainerDataSource().removeAllItems();
			table2.resetFilters();
			((ExtContainer<?>) table2.getContainerDataSource()).removeAllContainerFilters();
			ExtCustomTable rightTable = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE).getExtCustomTable();
			rightTable.getContainerDataSource().removeAllItems();
			OptionGroup massCheck = (OptionGroup) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpMassCheck")
					.getComponent();
			massCheck.setValue(GtnFrameworkCommonStringConstants.DISABLE);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDateFeild", componentId)
					.loadDateValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassField", componentId)
					.loadComboBoxComponentValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDropDown", componentId)
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
	}
}
