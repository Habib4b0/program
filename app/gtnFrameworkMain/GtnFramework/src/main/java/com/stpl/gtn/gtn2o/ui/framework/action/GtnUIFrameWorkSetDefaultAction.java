/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameWorkSetDefaultAction implements GtnUIFrameWorkAction {

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
		Map<String, Object> setDefaultDataMapTemp = null;
		List<String> setDefaultComponentIdListTemp = null;
		List<Object> setDefaultComponentValueListTemp = null;
		if (params.get(0) instanceof List) {
			setDefaultComponentIdListTemp = (List<String>) params.get(0);
			setDefaultComponentValueListTemp = (List<Object>) params.get(1);
		} else {
			setDefaultDataMapTemp = (Map<String, Object>) params.get(0);
		}
		Map<String, Object> setDefaultDataMap = setDefaultDataMapTemp;
		List<String> setDefaultComponentIdList = setDefaultComponentIdListTemp;
		List<Object> setDefaultComponentValueList = setDefaultComponentValueListTemp;
		if (setDefaultDataMap != null) {
			for (Map.Entry<String, Object> propertyId : setDefaultDataMap.entrySet()) {
				Component component = GtnUIFrameworkGlobalUI.getVaadinComponent(propertyId.getKey(), componentId);
				resetComponent(component, propertyId, componentId);
			}
			return;
		}

		if (setDefaultComponentIdList != null) {
			for (int i = 0; i < setDefaultComponentIdList.size(); i++) {
				Component component = GtnUIFrameworkGlobalUI.getVaadinComponent(setDefaultComponentIdList.get(i),
						componentId);
				if (component instanceof Field) {

					setFieldValue(setDefaultComponentValueList.get(i), component);
				}
				if (component instanceof VerticalLayout) {

					GtnUIFrameworkComponentData componentData;

					componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(setDefaultComponentIdList.get(i),
							componentId);

					resetComponentList(componentData);
				}

			}
			return;
		}

	}

	/**
	 * @param resetComponentValueList
	 * @param i
	 * @param component
	 */
	private void setFieldValue(Object resetComponentValue, Component component) {
		@SuppressWarnings("unchecked")
		Field<Object> vaadinField = ((Field<Object>) component);
		Object fieldValue;
		if (component instanceof TextField) {
			fieldValue = resetComponentValue == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY
					: String.valueOf(resetComponentValue);
		} else {
			fieldValue = resetComponentValue;
		}
		if (vaadinField.isReadOnly()) {
			vaadinField.setReadOnly(false);
			vaadinField.setValue(fieldValue);
			vaadinField.setReadOnly(true);
		} else {
			vaadinField.setValue(fieldValue);
		}
	}

	private void resetComponent(Component component, Entry<String, Object> propertyId, String componentId)
			throws GtnFrameworkValidationFailedException {
		if (component instanceof Field) {
			setFieldValue(propertyId.getValue(), component);
		}

		if (component instanceof VerticalLayout) {
			resetTable(propertyId.getKey(), componentId);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkSetDefaultAction();
	}

	public void resetTable(String propertyId, String componentId) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData componentData;

		componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(propertyId, componentId);

		if (componentData.getCustomData() instanceof ExtCustomTable) {
			ExtCustomTable table = (ExtCustomTable) componentData.getCustomData();
			table.removeAllItems();
		}
		if (componentData.getCustomData() instanceof ExtPagedTable) {
			ExtPagedTable<?> resultsTable = (ExtPagedTable<?>) componentData.getCustomData();
			GtnUIFrameworkPagedTableLogic logic = (GtnUIFrameworkPagedTableLogic) resultsTable.getContainerLogic();
			logic.startSearchProcess(null, null, Boolean.FALSE);
		}
	}

	public void resetComponentList(GtnUIFrameworkComponentData componentData)
			throws GtnFrameworkValidationFailedException {
		if (componentData.getCustomData() instanceof ExtCustomTable) {
			ExtCustomTable table = (ExtCustomTable) componentData.getCustomData();
			table.removeAllItems();
		}
		if (componentData.getCustomData() instanceof ExtPagedTable) {
			ExtPagedTable<?> resultsTable = (ExtPagedTable<?>) componentData.getCustomData();
			GtnUIFrameworkPagedTableLogic logic = (GtnUIFrameworkPagedTableLogic) resultsTable.getContainerLogic();
			logic.startSearchProcess(null, null, Boolean.FALSE);
		}
	}
}