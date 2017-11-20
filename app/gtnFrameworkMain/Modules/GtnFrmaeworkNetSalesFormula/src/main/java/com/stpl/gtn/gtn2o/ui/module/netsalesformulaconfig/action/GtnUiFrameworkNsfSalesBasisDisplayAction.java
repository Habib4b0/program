/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFMessageConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfSalesBasisDisplayAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		GtnWsRecordBean availableContractBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "availableContractTable").getValueFromPagedDataTable();
		if (availableContractBean == null) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			alertActionConfig.setActionParameterList(
					Arrays.asList(new Object[] { GtnFrameworkNSFMessageConstants.GTN_NSF_DISPLAY_BUTTON_MSG_HEADER,
							GtnFrameworkNSFMessageConstants.GTN_NSF_DISPLAY_BUTTON_MSG_BODY }));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException("Display Error ");
		}
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(viewId + "availableCustomersTable");
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		String sysId = String.valueOf(availableContractBean.getProperties().get(12));
		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();

		GtnWebServiceSearchCriteria systemIdCriteria = new GtnWebServiceSearchCriteria();
		GtnWebServiceSearchCriteria rowNumberCriteria = new GtnWebServiceSearchCriteria();
		additioanlSearchCriteriaList.add(systemIdCriteria);
		additioanlSearchCriteriaList.add(rowNumberCriteria);

		systemIdCriteria.setFieldId(GtnFrameworkNSFConstants.getSystemid());
		systemIdCriteria.setExpression("EQUALS");
		systemIdCriteria.setFilterValue1(sysId);

		rowNumberCriteria.setFieldId(GtnFrameworkNSFConstants.getRowNumber());
		rowNumberCriteria.setExpression("EQUALS");
		rowNumberCriteria.setFilterValue1("1");

		tableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);

		tableLogic.startSearchProcess(new ArrayList<String>(), true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
