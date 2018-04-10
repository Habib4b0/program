/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkAliasConfirmAddAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAliasConfirmAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<String> parameters = gtnUIFrameWorkActionConfig.getFieldValues();
			GtnWsRecordBean bean = new GtnWsRecordBean();
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(6));
			bean.setRecordHeader(tableBaseComponent.getTableRecordHeader());
			GtnUIFrameworkBaseComponent tradingBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(0));
			String fieldValue = tradingBaseComponent.getStringFromField();
			if (!fieldValue.isEmpty()) {
				bean.addProperties(parameters.get(0), fieldValue);
				GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
						.getDashboardSessionBean(componentId);
				Integer contId = processDataBean.getProcessBean().getContractId();
				Integer tpId = 0;

				if (tradingBaseComponent.getComponentData().getCustomDataList() != null) {
					tpId = Integer.valueOf(
							String.valueOf(tradingBaseComponent.getComponentData().getCustomDataList().get(0)));
				}
				GtnWsRecordBean.addProperties(7, tpId, bean.getProperties());
				GtnWsRecordBean.addProperties(8, contId, bean.getProperties());
			}
			fieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1)).getStringFromField();
			if (!fieldValue.isEmpty()) {
				bean.addProperties(parameters.get(1), fieldValue);
			}
			fieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2)).getStringFromField();
			if (!fieldValue.isEmpty()) {
				bean.addProperties(parameters.get(2), fieldValue);
			}
			GtnUIFrameworkBaseComponent typeBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(3));
			bean.addProperties(parameters.get(3), typeBaseComponent.getCaptionFromComboBox());
			GtnWsRecordBean.addProperties(6, typeBaseComponent.getIntegerFromField(), bean.getProperties());
			bean.addProperties(parameters.get(4),
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4)).getDateFromDateField());
			bean.addProperties(parameters.get(5),
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5)).getDateFromDateField());
			GtnWsRecordBean.addProperties(9, null, bean.getProperties());
			tableBaseComponent.addItemToDataTable(bean);
			if (tradingBaseComponent.getComponentData() != null) {
				tradingBaseComponent.getComponentData().setCustomDataList(null);
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkAliasConfirmAddAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
