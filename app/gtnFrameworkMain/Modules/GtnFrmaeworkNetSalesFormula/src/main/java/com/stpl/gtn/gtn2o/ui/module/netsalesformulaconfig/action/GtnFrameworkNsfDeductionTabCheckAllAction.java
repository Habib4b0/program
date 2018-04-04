/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnFrameworkNsfValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;

/**
 *
 * @author Deepika.KrishnaKumar
 */
public class GtnFrameworkNsfDeductionTabCheckAllAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkNsfDeductionTabCheckAllAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();

		try {
			nsfInfoBean.setCheckAll(true);
			GtnUIFrameworkBaseComponent nsfTableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId);
			nsfInfoBean.setColumnId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
			Object value = nsfTableBaseComponent
					.getTableColumnCheckboxValue(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
			nsfInfoBean.setValue(value);
			updateField(componentId);

			GtnFrameworkNsfValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameworkPagedTableLogic pagedLogic = nsfTableBaseComponent.getLogicFromPagedDataTable();
			pagedLogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
			GtnFrameworkNsfValueChangeManager.setValueChangeAllowed(true);
		} catch (GtnFrameworkValidationFailedException ex) {
			LOGGER.error("Exception in GtnFramework Nsf Deduction Tab CheckAll Action", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void updateField(String componentId) {
		GtnUIFrameworkWebserviceRequest updateCheckReocrdRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateCheckReocrdRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnUIFrameworkNsfInfoBean nsfBean = new GtnUIFrameworkNsfInfoBean();
		nsfBean.setCheckAll(true);
		nsfBean.setColumnId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);

		nsfBean.setValue(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getTableColumnCheckboxValue(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		GtnWsNetSalesFormulaGeneralRequest gtnWsNsfRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsNsfRequest.setnSfInfoBean(nsfBean);
		updateCheckReocrdRequest.setGtnWsNetSalesGeneralRequest(gtnWsNsfRequest);
		updateCheckReocrdRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + GtnWsCDRContants.GTN_WS_NSF_CHECK_ALL_SERVICE,
				updateCheckReocrdRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

}
