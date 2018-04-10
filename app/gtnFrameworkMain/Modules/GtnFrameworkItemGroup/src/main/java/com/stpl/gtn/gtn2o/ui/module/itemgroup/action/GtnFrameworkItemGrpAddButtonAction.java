package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGrpDataBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public class GtnFrameworkItemGrpAddButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpAddButtonAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			Set<GtnWsRecordBean> dtoSet = (Set<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemGrpAvailablesearchResultTable").getValuesFromPagedDataTable();
			if (dtoSet == null || dtoSet.isEmpty()) {

				MessageBox.showPlain(Icon.ERROR, GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_ADD_MSG,
						GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_ADD_MSG_BODY,
						new MessageBoxListener() {

							@Override
							public void buttonClicked(final ButtonId buttonId) {
                                                            return;

							}
						}, ButtonId.OK);

				return;
			}
			List<GtnWsItemGrpDataBean> beanList = new ArrayList<>();
			for (GtnWsRecordBean GtnWsRecordBean : dtoSet) {
				GtnWsItemGrpDataBean bean = new GtnWsItemGrpDataBean();
				bean.setItemMasterSid((Integer) GtnWsRecordBean.getPropertyValueByIndex(64));
				bean.setVersionNo(1);
				beanList.add(bean);
			}
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest gRequest = new GtnWsGeneralRequest();
			gRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			gRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(gRequest);
			GtnWsItemGroupRequest itemGrpRequest = new GtnWsItemGroupRequest();
			itemGrpRequest.setGtnWsItemGrpDataBeanList(beanList);
			request.setGtnWsItemGroupRequest(itemGrpRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE + GtnWsItemGrpContants.GTN_WS_ITEM_GRP_ADD_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemGrpSelectedResultTable").getLogicFromPagedDataTable();
			logic.startSearchProcess(null, true);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents() throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkSetter gtnUIFrameworkSetter = new GtnUIFrameworkSetter();
		GtnCompanyGrpInformationBean info = new GtnCompanyGrpInformationBean();
		gtnUIFrameworkSetter.loadFieldValue("itemGrpInformationItemGrpName", info.getCompanyGrpName());
		gtnUIFrameworkSetter.loadFieldValue("itemGrpInformationItemGrpNo", info.getCompanyGrpNo());
		gtnUIFrameworkSetter.loadFieldValue("itemGrpInformationItemGrpDesc", info.getCompanyGrpDesc());
		GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid", 0);
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);

		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpName", true);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpNo", true);
		gtnUIFrameworkSetter.setComponentEnable("itemGrpInformationItemGrpDesc", true);
	}
}
