package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
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

public class GtnFrameworkItemGrpResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering GtnFrameworkCfpResetAction doAction ");
		try {
			Integer itemGroupSid = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("itemGroupSid");

			GtnItemGrpInformationBean itemGrpInfoBean = new GtnItemGrpInformationBean();

			GtnWsItemGroupBean itemGrpBean = new GtnWsItemGroupBean();
			itemGrpBean.setItemGrpInfoBean(itemGrpInfoBean);
			if (itemGroupSid == null || itemGroupSid == 0) {
				setValueToComponents(itemGrpInfoBean, true);
			} else {
				itemGrpInfoBean.setItemGrpSid(itemGroupSid);
				GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
				GtnWsItemGroupRequest itemGrpRequest = new GtnWsItemGroupRequest();
				itemGrpRequest.setGtnWsItemGroupBean(itemGrpBean);
				gtnRequest.setGtnWsItemGroupRequest(itemGrpRequest);
				GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
				generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
				generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
				gtnRequest.setGtnWsGeneralRequest(generalWSRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE
								+ GtnWsItemGrpContants.GTN_WS_ITEM_GRP_FETCH_SERVICE,
						gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				setValueToComponents(response.getGtnWsItemGroupResponse().getGtnItemGrpInformationBean(), false);

				GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("itemGrpSelectedResultTable").getLogicFromPagedDataTable();
				cfpCaTabRightTablelogic.startSearchProcess(null, true);
			}

		} catch (Exception e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkCfpResetAction ", e);
		} finally {
			gtnLogger.info("Exit GtnFrameworkCfpResetAction doAction ");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnItemGrpInformationBean info, boolean isAdd)
			 {
		GtnUIFrameworkSetter gtnUIFrameworkSetter = new GtnUIFrameworkSetter();
		gtnUIFrameworkSetter.loadFieldValue("itemGrpInformationItemGrpName", info.getItemGrpName());
		gtnUIFrameworkSetter.loadFieldValue("itemGrpInformationItemGrpNo", info.getItemGrpNo());
		gtnUIFrameworkSetter.loadFieldValue("itemGrpInformationItemGrpDesc", info.getItemGrpDesc());
		gtnUIFrameworkSetter.loadComboBoxComponentValue("itemGrpInformationItemGrpCompany", info.getCompanyMasterSid());
		GtnUIFrameworkGlobalUI.addSessionProperty("itemGroupSid", info.getItemGrpSid());
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", info.getVersionNo());

		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpName", isAdd);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpNo", isAdd);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpDesc", isAdd);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpCompany", isAdd);

		gtnUIFrameworkSetter.loadComboBoxComponentValue("iGrpInformationTabCustomerType", null);
		gtnUIFrameworkSetter.loadFieldValue("iGrpInformationIGrpDesc", GtnFrameworkCommonStringConstants.STRING_EMPTY);
		gtnUIFrameworkSetter.loadFieldValue("iGrpInformationTabCustomerNo",
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		gtnUIFrameworkSetter.loadComboBoxComponentValue("iGrpInformationTabBrand", null);
		gtnUIFrameworkSetter.loadFieldValue("iGrpInformationTabStrength",
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		gtnUIFrameworkSetter.loadComboBoxComponentValue("iGrpInformationTabTherapeuticClass", null);
		gtnUIFrameworkSetter.loadComboBoxComponentValue("iGrpInformationTabForm", null);

		 GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpSelectedResultTable").removeAllItemsFromTable();		
		 GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpAvailablesearchResultTable").removeAllItemsFromTable();
		
	}
}
