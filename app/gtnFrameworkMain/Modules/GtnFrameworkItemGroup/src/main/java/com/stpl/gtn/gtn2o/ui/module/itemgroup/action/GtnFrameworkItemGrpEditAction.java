package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkItemGrpEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpEditAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getValueFromTable(gtnUIFrameWorkActionConfig);
		if (gtnWsRecordBean == null) {
			return;
		}
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", true);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnItemGrpInformationBean itemGrpInfoBean = new GtnItemGrpInformationBean();

		GtnWsItemGroupBean itemGrpBean = new GtnWsItemGroupBean();
		itemGrpBean.setItemGrpInfoBean(itemGrpInfoBean);
		int systemId = (Integer) gtnWsRecordBean.getPropertyValueByIndex(8);
		itemGrpInfoBean.setItemGrpSid(systemId);
		itemGrpInfoBean.setVersionNo((Integer) gtnWsRecordBean.getPropertyValueByIndex(4));
		GtnUIFrameworkGlobalUI.addSessionProperty("itemGroupSid", systemId);
		GtnWsItemGroupRequest itemGrpRequest = new GtnWsItemGroupRequest();
		itemGrpRequest.setGtnWsItemGroupBean(itemGrpBean);
		gtnRequest.setGtnWsItemGroupRequest(itemGrpRequest);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		try {
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE + GtnWsItemGrpContants.GTN_WS_ITEM_GRP_FETCH_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			boolean isEditable = (boolean) actionParamList.get(3);
			setValueToComponents(response.getGtnWsItemGroupResponse().getGtnItemGrpInformationBean(), isEditable,
					componentId);

			GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemGrpSelectedResultTable").getLogicFromPagedDataTable();
			cfpCaTabRightTablelogic.startSearchProcess(null, Boolean.TRUE);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnItemGrpInformationBean info, boolean isEditable, String sourceComponentId) {

		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpName", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpNo", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpDesc = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpDesc", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpCompany = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpCompany", sourceComponentId);

		itemGrpInformationItemGrpName.loadFieldValue(info.getItemGrpName());
		itemGrpInformationItemGrpNo.loadFieldValue(info.getItemGrpNo());
		itemGrpInformationItemGrpDesc.loadFieldValue(info.getItemGrpDesc());
		itemGrpInformationItemGrpCompany.loadComboBoxComponentValue(info.getCompanyMasterSid());

		GtnUIFrameworkGlobalUI.addSessionProperty("itemGroupSid", info.getItemGrpSid());
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", info.getVersionNo());

		itemGrpInformationItemGrpName.setComponentEnable(false);
		itemGrpInformationItemGrpNo.setComponentEnable(false);
		itemGrpInformationItemGrpDesc.setComponentEnable(false);
		itemGrpInformationItemGrpCompany.setComponentEnable(false);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabCustomerType", sourceComponentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationIGrpDesc", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabCustomerNo", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabBrand", sourceComponentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabStrength", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabTherapeuticClass", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabForm", sourceComponentId).loadFieldValue(null);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAvailablesearchResultTable", sourceComponentId)
				.getExtPagedTable().removeAllItems();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedResultTable", sourceComponentId)
				.getExtPagedTable().removeAllItems();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAddSaveButton", sourceComponentId).setCaption("UPDATE");

		GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(isEditable,
				Arrays.asList("itemGrpCustomerSearchPanel", "itemGrpsearchButtonlayout", "itemGrpAvailableResultPanel",
						"availableTableActionButtonlayout", "itemGrpSelectedTablegtnRemove01layout",
						"itemGrpSelectedTablegtnRemoveAll01layout", "itemGrpAddSaveButton", "ItemGrpADDResetButton"),
				sourceComponentId);
	}

}
