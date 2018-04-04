package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
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
public class GtnFrameworkItemGrpDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCGrpRemoveAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkWebserviceRequest itemGrpDeleteWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnUIFrameworkGlobalUI.addSessionProperty("itemGroupSid", 0);
			GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);

			GtnWsGeneralRequest itemGrpDeleteGeneralWSRequest = new GtnWsGeneralRequest();
			itemGrpDeleteGeneralWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			itemGrpDeleteGeneralWSRequest
					.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			itemGrpDeleteWebserviceRequest.setGtnWsGeneralRequest(itemGrpDeleteGeneralWSRequest);

			GtnWsRecordBean itemGrpDeleteWsRecordBean = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemGrpsearchResultTable").getValueFromPagedDataTable();
			if (itemGrpDeleteWsRecordBean != null) {
				Integer systemId = (Integer) itemGrpDeleteWsRecordBean.getPropertyValueByIndex(8);
				if (systemId != null) {
					GtnWsItemGroupBean bean = new GtnWsItemGroupBean();
					GtnItemGrpInformationBean infoBean = new GtnItemGrpInformationBean();
					infoBean.setItemGrpSid(systemId);
					bean.setItemGrpInfoBean(infoBean);
					GtnWsItemGroupRequest itemGrpRequest = new GtnWsItemGroupRequest();
					itemGrpRequest.setGtnWsItemGroupBean(bean);
					itemGrpDeleteWebserviceRequest.setGtnWsItemGroupRequest(itemGrpRequest);
				}
			}
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE + GtnWsItemGrpContants.GTN_WS_ITEM_GRP_DELETE_SERVICE,
					itemGrpDeleteWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemGrpsearchResultTable").getLogicFromPagedDataTable();

			logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
