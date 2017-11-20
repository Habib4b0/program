/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkTransactioneRecordTypeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) params.get(1));
			GtnUIFrameworkComponentData tableComponentData = tableBaseComponent.getComponentData();
			GtnWSTransactionTableCheckAllBean checkBean = (GtnWSTransactionTableCheckAllBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) params.get(2)).getComponentData().getSharedPopupData();
			for (GtnWsRecordBean record : tableComponentData.getDataTableRecordList()) {
				manageTableRecordType(record, tableBaseComponent, checkBean, (String) params.get(1));
			}
		} catch (GtnFrameworkValidationFailedException e) {
			throw new GtnFrameworkGeneralException("Error in doAction", e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void manageTableRecordType(GtnWsRecordBean record, GtnUIFrameworkBaseComponent tableBaseComponent,
			GtnWSTransactionTableCheckAllBean checkBean, String tableId) throws GtnFrameworkGeneralException {
		try {
			List<Object> recordHeader = tableBaseComponent.getTableRecordHeader();
			for (int i = 0; i < recordHeader.size(); i++) {
				Object propertyId = recordHeader.get(i);
				Object value = record.getPropertyValueByIndex(i);
				Class<?> type = tableBaseComponent.getTableColumnProperty(propertyId.toString());
				value = GtnUIFrameworkGlobalUI.getConvertedPropertyValue(type, value);
				GtnWsRecordBean.addProperties(i, value, record.getProperties());

			}
			if (checkBean.isCheckAll()) {
				GtnWsRecordBean.addProperties(0,
						checkBean.isCheckAll() || checkBean.getCheckedIdSet()
								.contains(record.getIntegerPropertyByIndex(record.getProperties().size() - 1)),
						record.getProperties());
			} else {
				GtnWsRecordBean.addProperties(0,
						!checkBean.getUnCheckedIdSet().isEmpty() && !checkBean.getUnCheckedIdSet()
								.contains(record.getIntegerPropertyByIndex(record.getProperties().size() - 1)),
						record.getProperties());
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).setPagedTableHeaderCheckBox(checkBean.isCheckAll(),
					GtnTransactionUIConstants.CHECK_RECORD);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error in doAction", e);
		}
	}

}