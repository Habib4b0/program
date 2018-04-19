package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkLoadDataTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;

public class GtnFrameworkItemMasterIdentifierQualifierSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkItemMasterIdentifierQualifierSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkItemMasterIdentifierQualifierSaveAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
			List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();
			GtnWsRecordBean selectedId = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameter.get(GtnWsNumericConstants.ONE).toString())
					.getValueFromDataTable();

			final Integer selectedSid = selectedId == null ? Integer.valueOf(0)
					: (Integer) selectedId.getPropertyValueByIndex(selectedId.getProperties().size() - 2);

			final String qualifier = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.ZERO)).getStringFromField();
			final String qualifierName = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.ONE)).getStringFromField();
			final String editListEffectiveDates = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.TWO)).getStringFromField();
			final String editListEntityCode = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.THREE)).getStringFromField();
			boolean isIdentifierTab = "Identifier".equals(actionParameter.get(GtnWsNumericConstants.TWO).toString());
			if (isIdentifierTab) {
				saveIdentifierTabDetails(selectedSid, qualifier, qualifierName, editListEffectiveDates,
						editListEntityCode, fieldValues.get(GtnWsNumericConstants.FOUR));
			} else {
				savePricingTabDetails(selectedSid, qualifier, qualifierName, editListEffectiveDates, editListEntityCode,
						fieldValues.get(GtnWsNumericConstants.FOUR));
			}
			refreshResultTable(actionParameter.get(GtnWsNumericConstants.ONE).toString());
			refreshComponenent(componentId, fieldValues);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void savePricingTabDetails(Integer selectedSid, String qualifier, String qualifierName,
			String editListEffectiveDates, String editListEntityCode, String notes) {
		GtnWsItemPricingQualifierBean indenBean = new GtnWsItemPricingQualifierBean();
		indenBean.setItemPricingQualifierSid(selectedSid);
		indenBean.setPricingQualifier(qualifier);
		indenBean.setItemPricingQualifierName(qualifierName);
		indenBean.setEffectiveDates(editListEffectiveDates);
		indenBean.setSpecificEntityCode(editListEntityCode);
		indenBean.setNotes(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(notes).getStringFromField());

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		imRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		imRequest.setGtnWsItemPricingQualifierBean(indenBean);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_PRICING_QUALIFER_SAVE_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	private void saveIdentifierTabDetails(Integer selectedSid, String qualifier, String qualifierName,
			String editListEffectiveDates, String editListEntityCode, String notes) {

		GtnWsItemQualifierBean indenBean = new GtnWsItemQualifierBean();
		indenBean.setItemQualifierSid(selectedSid);
		indenBean.setItemQualifierValue(qualifier);
		indenBean.setItemQualifierName(qualifierName);
		indenBean.setEffectiveDates(editListEffectiveDates);
		indenBean.setSpecificEntityCode(editListEntityCode);
		indenBean.setNotes(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(notes).getStringFromField());

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		imRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		imRequest.setGtnWsItemQualifierBean(indenBean);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_INDENTIFIER_QUALIFER_SAVE_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	private void refreshResultTable(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig refreshActionConfig = new GtnUIFrameWorkActionConfig();
		refreshActionConfig.addActionParameter(componentId);
		GtnUIFrameWorkLoadDataTableAction loadDataTableAction = new GtnUIFrameWorkLoadDataTableAction();
		loadDataTableAction.doAction(componentId, refreshActionConfig);
	}

	private void refreshComponenent(String componentId, List<String> fieldValues) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig imQualifierEditListRefreshAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		imQualifierEditListRefreshAction
				.addActionParameter(GtnFrameworkItemMasterIdEditListRefreshAction.class.getName());
		imQualifierEditListRefreshAction.setFieldValues(fieldValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, imQualifierEditListRefreshAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
