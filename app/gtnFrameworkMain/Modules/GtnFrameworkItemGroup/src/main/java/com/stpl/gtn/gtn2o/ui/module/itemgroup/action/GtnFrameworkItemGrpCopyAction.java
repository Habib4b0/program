package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkItemGrpCopyAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpCopyAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) actionParamList.get(1);
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId)
				.getValueFromPagedDataTable();
		if (gtnWsRecordBean == null) {
			return;
		}
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnItemGrpInformationBean itemGrpInfoBean = new GtnItemGrpInformationBean();

		GtnWsItemGroupBean itemGrpBean = new GtnWsItemGroupBean();
		itemGrpBean.setItemGrpInfoBean(itemGrpInfoBean);
		int systemId = (Integer) gtnWsRecordBean.getPropertyValueByIndex(8);
		if (systemId == 0) {
			return;
		}
		itemGrpInfoBean.setItemGrpSid(systemId);
                itemGrpInfoBean.setVersionNo((Integer) gtnWsRecordBean.getPropertyValueByIndex(4));
		GtnWsItemGroupRequest itemGrpRequest = new GtnWsItemGroupRequest();
		itemGrpRequest.setGtnWsItemGroupBean(itemGrpBean);
		gtnRequest.setGtnWsItemGroupRequest(itemGrpRequest);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		try {
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE + GtnWsItemGrpContants.GTN_WS_ITEM_GRP_COPY_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			boolean isEditable = (boolean) actionParamList.get(3);

			setValueToComponents(isEditable, componentId);

			GtnUIFrameworkPagedTableLogic itemGrpSelectedResultTableLogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemGrpSelectedResultTable").getLogicFromPagedDataTable();
			itemGrpSelectedResultTableLogic.startSearchProcess(null, true);
			GtnUIFrameworkGlobalUI.addSessionProperty("itemGroupSid", 0);
			GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(boolean isEditable, String sourceComponentId) {

		gtnLogger.debug("Setting values for Item Group Copy ");

		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpName", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpNo", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpDesc = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpDesc", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpCompany = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpCompany", sourceComponentId);

		itemGrpInformationItemGrpName.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		itemGrpInformationItemGrpNo.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		itemGrpInformationItemGrpDesc.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		itemGrpInformationItemGrpCompany.loadFieldValue(null);

		itemGrpInformationItemGrpName.setComponentEnable(true);
		itemGrpInformationItemGrpNo.setComponentEnable(true);
		itemGrpInformationItemGrpDesc.setComponentEnable(true);
		itemGrpInformationItemGrpCompany.setComponentEnable(true);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabCustomerType", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationIGrpDesc", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabCustomerNo", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabBrand", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabStrength", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabTherapeuticClass", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabForm", sourceComponentId).loadFieldValue(null);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAvailablesearchResultTable").getExtPagedTable()
				.removeAllItems();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedResultTable").getExtPagedTable().removeAllItems();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAddSaveButton").setCaption("SAVE");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpCustomerSearchPanel", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpsearchButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAvailableResultPanel", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("availableTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedTablegtnRemove01layout", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedTablegtnRemoveAll01layout", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAddSaveButton", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ItemGrpADDResetButton", sourceComponentId)
				.setComponentVisible(true);

	}
}
