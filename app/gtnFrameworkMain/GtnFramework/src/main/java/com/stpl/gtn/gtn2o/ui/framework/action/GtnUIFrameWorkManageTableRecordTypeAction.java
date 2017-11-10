/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import org.asi.container.ExtContainer;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractSelect;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkManageTableRecordTypeAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		GtnUIFrameworkComponentData tableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) params.get(0), componentId);
		AbstractSelect grid = (AbstractSelect) tableComponentData.getCustomData();
		ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) grid.getContainerDataSource();
		for (GtnWsRecordBean record : tableComponentData.getDataTableRecordList()) {
			manageTableRecordType(record, container);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void manageTableRecordType(GtnWsRecordBean record, ExtContainer<GtnWsRecordBean> container) {
		List<Object> recordHeader = container.getRecordHeader();
		for (int i = 0; i < recordHeader.size(); i++) {
			Object propertyId = recordHeader.get(i);
			Class<?> type = container.getColumnProperty(propertyId);
			if (type != null) {
				Object value = record.getPropertyValueByIndex(i);
				value = GtnUIFrameworkGlobalUI.getConvertedPropertyValue(type, value);
				GtnWsRecordBean.addProperties(i, value, record.getProperties());
			}
		}
	}
}