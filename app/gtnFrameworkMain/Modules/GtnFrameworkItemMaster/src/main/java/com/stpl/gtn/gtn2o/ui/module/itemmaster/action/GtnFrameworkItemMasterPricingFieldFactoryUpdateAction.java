package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

public class GtnFrameworkItemMasterPricingFieldFactoryUpdateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		GtnUIFrameworkComponentData componentData = baseComponent.getComponentData();
		GtnUIFrameworkActionParameter actionParameter = componentData.getActionParameter();

		if (actionParameter.getItemId().getBooleanProperty("isFocused")
				|| actionParameter.getPropertyId().equalsIgnoreCase(GtnFrameworkItemMasterStringContants.START_DATE)
				|| actionParameter.getPropertyId().equalsIgnoreCase("endDate")) {
			validateEndDate(actionParameter, componentId);
			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnItemMasterRequestBuilder()
					.getItemMasterWebServiceRequest();
			gtnRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
					.setPopulateField(actionParameter.getPropertyId());
			gtnRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
					.setPopulateValue(String.valueOf(actionParameter.getCurrentValue()));
			gtnRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
					.setPopulateIdentityId(
							String.valueOf(actionParameter.getItemId().getPropertyValue("itemPricingSid")));

			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
							+ GtnWsItemMasterContants.GTN_WS_PRICING_UPDATE_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			actionParameter.getItemId().addBooleanProperties("isFocused", false);

		}

	}

	private void validateEndDate(GtnUIFrameworkActionParameter actionParameter, String componentId)
			throws GtnFrameworkGeneralException {
		if (actionParameter.getPropertyId().contains("Date")) {
			Date startDate;
			Date endDate;
			if (actionParameter.getPropertyId().equalsIgnoreCase(GtnFrameworkItemMasterStringContants.START_DATE)) {
				startDate = (Date) actionParameter.getCurrentValue();
				endDate = (Date) actionParameter.getItemId().getPropertyValue("endDate");
			} else {
				startDate = (Date) actionParameter.getItemId().getPropertyValue(GtnFrameworkItemMasterStringContants.START_DATE);
				endDate = (Date) actionParameter.getCurrentValue();
			}

			if (startDate == null || endDate == null || (startDate.compareTo(endDate) < 0)) {
				GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
			} else {
				String errorMessage = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_END;
				if (startDate.compareTo(endDate) == 0) {
					errorMessage = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_START;
				}
				throw new GtnFrameworkValidationFailedException(errorMessage, componentId);

			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
