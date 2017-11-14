/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.config.commonpopup.commonaction;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnUIFrameworkItemClickEnableDisableAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable,	GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> selectParam = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) selectParam.get(1);
		String idComponent = (String) selectParam.get(2);
		GtnWsRecordBean itemId = gtnUIFrameWorkActionConfig.getActionParameter().getItemId();
		boolean isEnable = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId).getExtPagedTable().isSelected(itemId);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(idComponent).setEnable(!isEnable);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
