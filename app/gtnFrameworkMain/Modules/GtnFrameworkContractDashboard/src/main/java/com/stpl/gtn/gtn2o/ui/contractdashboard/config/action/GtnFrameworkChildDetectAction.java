/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkChildDetectAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean recordBean = (GtnWsRecordBean) gtnUIFrameWorkActionConfig.getEventParameter();
		int totalChild = 0;
		int startIndex = 6 + recordBean.getIntegerPropertyByIndex(4);
		for (int i = startIndex; i < 11; i++) {
			totalChild = totalChild + recordBean.getIntegerPropertyByIndex(i);
		}
		recordBean.getProperties().set(11, totalChild);
		recordBean.setParentFlag(totalChild != 0);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkChildDetectAction();
	}

}
