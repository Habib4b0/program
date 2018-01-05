/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.setter;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.Panel;
import java.util.List;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

public class GtnUIFrameworkComponentValueSetter {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkComponentValueSetter.class);

	@SuppressWarnings({ "unchecked" })
	public void setPropertyValue(String sourceComponentId, String componentId, Object newValue) {
		Property<Object> vaadinProperty = (Property<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId,
				sourceComponentId);
		vaadinProperty.setValue(newValue);
	}

	@SuppressWarnings({ "unchecked" })
	public void loadComboBoxComponentValue(String sourceComponentId, String componentId, Integer newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId,
				sourceComponentId);
		vaadinField.setValue(newValue);
	}

	@SuppressWarnings({ "unchecked" })
	public void loadFieldValue(String sourceComponentId, String componentId, Object newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId,
				sourceComponentId);
		vaadinField.setValue(getString(newValue));
	}

	@SuppressWarnings({ "unchecked" })
	public void loadDateValue(String sourceComponentId, String componentId, Object newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId,
				sourceComponentId);
		vaadinField.setValue(newValue);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> getRecordHeader(String sourceComponentId, String componentId) {
		GtnUIFrameworkComponentData componentData;
		List<Object> recordHeaders = null;
		try {
			componentData = (GtnUIFrameworkComponentData) getComponent(componentId, sourceComponentId).getData();

			ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
			recordHeaders = ((ExtContainer) resultTable.getContainerDataSource()).getRecordHeader();
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
		return recordHeaders;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadContainer(String sourceComponentId, String componentId, List<GtnWsRecordBean> resultLiist) {

		GtnUIFrameworkComponentData componentData;
		try {
			componentData = (GtnUIFrameworkComponentData) getComponent(componentId, sourceComponentId).getData();
			ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
			ExtContainer container = ((ExtContainer) resultTable.getContainerDataSource());
			container.removeAllItems();
			container.addAll(resultLiist);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	public void setTableColumnHeader(String sourceComponentId, String componentId, Object propertyId, String header) {
		try {
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId,
					sourceComponentId).getData();
			ExtCustomTable resultTable = (ExtCustomTable) componentData.getCustomData();
			resultTable.setColumnHeader(propertyId, header);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	public void setTableColumns(String sourceComponentId, String componentId, Object[] propertyIds) {
		try {
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId,
					sourceComponentId).getData();
			ExtCustomTable resultTable = (ExtCustomTable) componentData.getCustomData();
			resultTable.setVisibleColumns(propertyIds);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	public void setTableColumnHeaders(String sourceComponentId, String componentId, String[] headers) {
		try {
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId,
					sourceComponentId).getData();
			ExtCustomTable resultTable = (ExtCustomTable) componentData.getCustomData();
			resultTable.setColumnHeaders(headers);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	public void loadNotesTab(String sourceComponentId, List<Object> result) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.NOTES_TAB, sourceComponentId).getComponent();

		notesTab.setNotesTabData(result);

	}

	public void setNotesTabEnable(String sourceComponentId, boolean isEditMode) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.NOTES_TAB, sourceComponentId).getComponent();
		notesTab.removeAndDisablingComponents(!isEditMode);
	}

	private boolean isNull(Object value) {
		return value == null;
	}

	private String getString(Object value) {
		if (isNull(value)) {
			return "";
		}
		return String.valueOf(value);
	}

	private static AbstractComponent getComponent(String sourceComponentId, String componentId)
			throws GtnFrameworkGeneralException {
		try {
			return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getComponent();
		} catch (Exception typeException) {
			throw new GtnFrameworkGeneralException(componentId + " not found", typeException);
		}
	}

	public void setComponentReadOnly(String sourceComponentId, String componentId, boolean newValue) {
		@SuppressWarnings("unchecked")
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId,
				sourceComponentId);
		vaadinField.setReadOnly(newValue);
	}

	@SuppressWarnings("rawtypes")
	public void setComponentVisible(String sourceComponentId, String componentId, boolean newValue) {
		Object vaadinField = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId);
		if (vaadinField instanceof Field) {
			((Field) vaadinField).setVisible(newValue);
		} else if (vaadinField instanceof Component) {
			((Component) vaadinField).setVisible(newValue);
		} else if (vaadinField instanceof Panel) {
			((Panel) vaadinField).setVisible(newValue);
		} else if (vaadinField instanceof AbstractOrderedLayout) {
			((AbstractOrderedLayout) vaadinField).setVisible(newValue);
		} else if (vaadinField instanceof CssLayout) {
			((CssLayout) vaadinField).setVisible(newValue);
		}

	}

	@SuppressWarnings("rawtypes")
	public void setComponentEnable(String sourceComponentId, String componentId, boolean newValue) {
		Object vaadinField = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId);
		if (vaadinField instanceof Field) {
			((Field) vaadinField).setEnabled(newValue);
		} else if (vaadinField instanceof Component) {
			((Component) vaadinField).setEnabled(newValue);
		} else if (vaadinField instanceof Panel) {
			((Panel) vaadinField).setEnabled(newValue);
		} else if (vaadinField instanceof AbstractOrderedLayout) {
			((AbstractOrderedLayout) vaadinField).setEnabled(newValue);
		} else if (vaadinField instanceof CssLayout) {
			((CssLayout) vaadinField).setEnabled(newValue);
		}
	}

	public void refreshNotesTab(String sourceComponentId) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.NOTES_TAB, sourceComponentId).getComponent();
		notesTab.resetAddMode();
		notesTab.removeAndDisablingComponents(false);
	}

	public void setComponentCustomData(String sourceComponentId, String componentId, Object customData)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId,
				sourceComponentId).getData();
		componentData.setCustomData(customData);
	}

	@SuppressWarnings({ "unchecked" })
	public void setTableValue(String sourceComponentId, String componentId, Object newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId, sourceComponentId).getComponentData().getCustomData();
		vaadinField.setValue(newValue);
	}

	public void showMessageBox(String componentId, GtnUIFrameworkActionType actionType, String header, String message)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameWorkAction action = actionType.getGtnUIFrameWorkAction();
			GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
			confirmActionConfig.addActionParameter(header);
			confirmActionConfig.addActionParameter(message);
			action.configureParams(confirmActionConfig);
			action.doAction(componentId, confirmActionConfig);
		} catch (Exception typeException) {
			throw new GtnFrameworkGeneralException(componentId + " not found", typeException);
		}
	}
}
