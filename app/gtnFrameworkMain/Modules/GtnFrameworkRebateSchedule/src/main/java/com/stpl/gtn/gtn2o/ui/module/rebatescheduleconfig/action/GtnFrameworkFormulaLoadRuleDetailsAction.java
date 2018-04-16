/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.List;
import org.asi.container.ExtContainer;

public class GtnFrameworkFormulaLoadRuleDetailsAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean beanDto = new GtnWsRecordBean();
		List<Object> detailParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableResultId = detailParameterList.get(1).toString();
		String detailsTableId = detailParameterList.get(3).toString();

		GtnUIFrameworkBaseComponent baseTableComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableResultId,
				componentId);
		GtnWsRecordBean resultBean = (GtnWsRecordBean) baseTableComponent.getValueFromComponent();
		Object paramObject = resultBean
				.getPropertyValueByIndex(Integer.parseInt(String.valueOf(detailParameterList.get(2))));

		ExtContainer<GtnWsRecordBean> recordContainer = (ExtContainer<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(detailsTableId).getExtCustomTable().getContainerDataSource();
		recordContainer.removeAllItems();
		beanDto.setRecordHeader(recordContainer.getRecordHeader());

		beanDto.addProperties("formula", paramObject);
		recordContainer.addBean(beanDto);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
