package com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemGrpNameValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpNameValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkItemGrpNameValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		String name = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpName")
				.getStringFromField();
		String no = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpNo").getStringFromField();
		String desc = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpDesc")
				.getStringFromField();
		Integer company = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpCompany")
				.getIntegerFromField();

		String appender = "  ";

		StringBuilder feildMsg = new StringBuilder();

		if (name.isEmpty()) {
			feildMsg.append(appender).append("Item Group Name");
			appender = " , ";
		}
		if (no.isEmpty()) {
			feildMsg.append(appender).append("Item Group No");
			appender = " , ";
		}
		if (desc.isEmpty()) {
			feildMsg.append(appender).append("Item Group Description");
			appender = " , ";
		}
		if (company == null || company == 0) {
			feildMsg.append(appender).append("Company");
		}

		if (feildMsg.length() > 0) {
			String msg = GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_SAVE + feildMsg.toString();
			alertActionConfig.setActionParameterList(
					Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.ERROR, msg }));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
		}
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemGroupRequest itemGrpRequest = new GtnWsItemGroupRequest();
		GtnWsItemGroupBean itemGrpBean = new GtnWsItemGroupBean();
		GtnItemGrpInformationBean itemGrpInfoBean = new GtnItemGrpInformationBean();
		itemGrpInfoBean.setItemGrpName(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpName").getStringFromField());
		itemGrpInfoBean.setItemGrpSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("itemGroupSid"));
		itemGrpBean.setItemGrpInfoBean(itemGrpInfoBean);
		itemGrpRequest.setGtnWsItemGroupBean(itemGrpBean);
		request.setGtnWsItemGroupRequest(itemGrpRequest);
		GtnUIFrameworkWebserviceResponse reponse = getResponse(request);
		boolean itemGrpName = reponse.getGtnWsItemGroupResponse().getGtnWsItemGrpValidationBean().isGroupNameExists();

		if (itemGrpName) {

			alertActionConfig
					.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.ERROR,
							GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_DUPLICATION_NAME }));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error :"
					+ GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_DUPLICATION_NAME);
		}
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE
						+ GtnWsItemGrpContants.GTN_WS_ITEM_GRP_NAME_DUPLICATE_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
