package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

public class GtnFrameworkItemGrpSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkCGrpRemoveAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemGroupBean itemGrpBean = new GtnWsItemGroupBean();
		GtnItemGrpInformationBean infoBean = new GtnItemGrpInformationBean();
		infoBean.setItemGrpName(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_ITEM_GRP_NAME)
				.getStringFromField());
		infoBean.setItemGrpNo(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_ITEM_GRP_NAME)
				.getStringFromField());
		infoBean.setItemGrpDesc(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpDesc").getStringFromField());
		infoBean.setCompanyMasterSid((int) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpCompany").getIntegerFromField());
		itemGrpBean.setItemGrpInfoBean(infoBean);
		GtnWsItemGroupRequest itemGrpRequest = new GtnWsItemGroupRequest();
		itemGrpRequest.setGtnWsItemGroupBean(itemGrpBean);
		request.setGtnWsItemGroupRequest(itemGrpRequest);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		request.setGtnWsGeneralRequest(generalWSRequest);
		Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("itemGroupSid");
		infoBean.setItemGrpSid(systemId);
		Integer versionId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("versionId");
		String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		infoBean.setModifiedBy(Integer.valueOf(userId));
		if (versionId != null && versionId > 0) {
			infoBean.setVersionNo(versionId + 1);
		}
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE + GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SAVE_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameworkGlobalUI.addSessionProperty("itemGroupSid",
				response.getGtnWsItemGroupResponse().getGtnItemGrpInformationBean().getItemGrpSid());
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);
		GtnUIFrameworkSetter gtnUIFrameworkSetter = new GtnUIFrameworkSetter();
		gtnUIFrameworkSetter.setComponentEnable(GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_ITEM_GRP_NAME, false);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpNo", false);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpDesc", false);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpCompany", false);
		Component component = GtnUIFrameworkGlobalUI.getVaadinComponent("itemGrpAddSaveButton");
		component.setCaption("UPDATE");

		final Notification notif = new Notification(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_ITEM_GRP_NAME)
				.getStringFromField() + " has been successfully saved", Notification.Type.HUMANIZED_MESSAGE);

		notif.setPosition(Position.TOP_CENTER);
		notif.setStyleName(GtnFrameworkCssConstants.MY_STYLE);
		notif.setDelayMsec(3000);
		notif.show(Page.getCurrent());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
