package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkItemMasterDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkItemMasterDeleteAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsItemMasterBean imBean = new GtnWsItemMasterBean();
		GtnWsItemMasterInfoBean infoBean = new GtnWsItemMasterInfoBean();
		imBean.setGtnWsItemMasterInfoBean(infoBean);
		infoBean.setItemMasterSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("itemMasterSid"));
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		imRequest.setGtnWsItemMasterBean(imBean);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		try {
			GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
							+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_DELETE_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			if (reponse.getGtnWsItemMasterResponse().isItemPresentInContract()) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_DELETE_001;
				alertActionConfig.addActionParameter("Cannot Delete");
				alertActionConfig.addActionParameter(msg);
				GtnUIFrameworkGlobalUI.addSessionProperty("cfpModelSid", 0);
				throw new GtnFrameworkValidationFailedException("Delete Error :" + msg);
			}
			if (reponse.getGtnWsItemMasterResponse().isItemPresentInIfp()) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_DELETE_002;
				alertActionConfig.addActionParameter("Cannot Delete");
				alertActionConfig.addActionParameter(msg);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
				GtnUIFrameworkGlobalUI.addSessionProperty("cfpModelSid", 0);
				throw new GtnFrameworkValidationFailedException("Delete Error :" + msg);
			}
			GtnUIFrameWorkActionConfig dropTempTableAction = new GtnUIFrameWorkActionConfig();
			dropTempTableAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			dropTempTableAction.addActionParameter(GtnFrameworkItemMasterPricingTempTableClearAction.class.getName());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, dropTempTableAction);
			String message = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabItemName")
					.getStringFromField() + " has been deleted successfully";
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig();
			notificationConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			notificationConfig.addActionParameter(message);
			notificationConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			notificationConfig.addActionParameter(0);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationConfig);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkItemMasterDeleteAction();
	}

}
