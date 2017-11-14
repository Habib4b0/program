package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GntWsItemPricingBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemMasterPricingAttachAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		if (gtnUIFrameWorkActionConfig.getActionParameterList().get(1) instanceof List) {

			@SuppressWarnings("unchecked")
			List<Object> componentList = (List<Object>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
			GntWsItemPricingBean pricingBean = new GntWsItemPricingBean();
			pricingBean.setItemPricingQualifierSid(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) componentList.get(0), componentId).getIntegerFromField());

			pricingBean.setItemPrice(Double.parseDouble(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) componentList.get(1), componentId).getStringFromField()));

			pricingBean.setPricingCodeStatus(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) componentList.get(2), componentId).getIntegerFromField());

			pricingBean.setItemUom(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) componentList.get(3), componentId).getIntegerFromField());

			pricingBean.setStartDate(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) componentList.get(4), componentId).getDateFromDateField());

			pricingBean.setEndDate(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) componentList.get(5), componentId).getDateFromDateField());

			pricingBean.setEntityCode(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) componentList.get(6), componentId).getStringFromField());

			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnItemMasterRequestBuilder()
					.getItemMasterWebServiceRequest();
			gtnRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().setGntWsItemPricingBean(pricingBean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
							+ GtnWsItemMasterContants.GTN_WS_PRICING_INSERT_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response.getGtnWsItemMasterResponse() == null) {
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE)
						.getLogicFromPagedDataTable().startSearchProcess(new ArrayList<String>(), true);
				GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
			} else {

				throw new GtnFrameworkValidationFailedException(
						response.getGtnWsItemMasterResponse().getGtnWsValidationBean().getValidationMsg(), componentId);
			}

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
