package com.stpl.gtn.gtn2o.ui.framework.component.setter;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.Panel;
import java.util.List;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

public class GtnUIFrameworkSetter {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkSetter.class);

	@SuppressWarnings({ "unchecked" })
	public void setPropertyValue(String componentId, Object newValue) {
		Property<Object> vaadinProperty = (Property<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		boolean isReadOnly = vaadinProperty.isReadOnly();
		vaadinProperty.setReadOnly(false);
		vaadinProperty.setValue(newValue);
		vaadinProperty.setReadOnly(isReadOnly);
	}

	@SuppressWarnings({ "unchecked" })
	public void loadComboBoxComponentValue(String componentId, Integer newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinField.setValue(newValue);
	}

	@SuppressWarnings({ "unchecked" })
	public void loadFieldValue(String componentId, Object newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinField.setValue(getString(newValue));
	}

	@SuppressWarnings({ "unchecked" })
	public void loadDateValue(String componentId, Object newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinField.setValue(newValue);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> getRecordHeader(String componentId) {
		GtnUIFrameworkComponentData componentData;
		List<Object> recordHeaders = null;
		try {
			componentData = (GtnUIFrameworkComponentData) getComponent(componentId).getData();

			ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
			recordHeaders = ((ExtContainer) resultTable.getContainerDataSource()).getRecordHeader();
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
		return recordHeaders;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadContainer(String componentId, List<GtnWsRecordBean> resultLiist) {

		GtnUIFrameworkComponentData componentData;
		try {
			componentData = (GtnUIFrameworkComponentData) getComponent(componentId).getData();
			ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
			ExtContainer container = ((ExtContainer) resultTable.getContainerDataSource());
			container.removeAllItems();
			container.addAll(resultLiist);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	public void setTableColumnHeader(String componentId, Object propertyId, String header) {
		try {
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId)
					.getData();
			ExtCustomTable resultTable = (ExtCustomTable) componentData.getCustomData();
			resultTable.setColumnHeader(propertyId, header);
		} catch (GtnFrameworkGeneralException e) {
                        logger.error(e.getMessage());
		}
	}

	public void setTableColumns(String componentId, Object[] propertyIds) {
		try {
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId)
					.getData();
			ExtCustomTable resultTable = (ExtCustomTable) componentData.getCustomData();
			resultTable.setVisibleColumns(propertyIds);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	public void setTableColumnHeaders(String componentId, String[] headers) {
		try {
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId)
					.getData();
			ExtCustomTable resultTable = (ExtCustomTable) componentData.getCustomData();
			resultTable.setColumnHeaders(headers);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	public void loadNotesTab(List<Object> result) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent(GtnFrameworkCommonConstants.NOTES_TAB);

		notesTab.setNotesTabData(result);

	}

	public void setNotesTabEnable(boolean isEditMode) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent(GtnFrameworkCommonConstants.NOTES_TAB);
		notesTab.removeAndDisablingComponents(!isEditMode);
	}

	private boolean isNull(Object value) {
		return value == null || "null".equals(String.valueOf(value));
	}

	private String getString(Object value) {
		if (isNull(value)) {
			return "";
		}
		return String.valueOf(value);
	}

	private static AbstractComponent getComponent(String componentId) throws GtnFrameworkGeneralException {
		try {
			return GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		} catch (Exception typeException) {
			throw new GtnFrameworkGeneralException(componentId + " not found", typeException);
		}
	}

	@SuppressWarnings("unchecked")
	public void setComponentReadOnly(String componentId, boolean newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinField.setReadOnly(newValue);
	}

	public void setComponentVisible(String componentId, boolean newValue) {
		Object vaadinField = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		if (vaadinField instanceof Field) {
			((Field<?>) vaadinField).setVisible(newValue);
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

	public void setComponentEnable(String componentId, boolean newValue) {
		Object vaadinField = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		if (vaadinField instanceof Field) {
			((Field<?>) vaadinField).setEnabled(newValue);
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

	public void refreshNotesTab() {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent(GtnFrameworkCommonConstants.NOTES_TAB);
		notesTab.resetAddMode();
		notesTab.removeAndDisablingComponents(false);
	}

	public void setComponentCustomData(String componentId, Object customData) throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) getComponent(componentId).getData();
		componentData.setCustomData(customData);
	}

	@SuppressWarnings("unchecked")
	public void setTableValue(String componentId, Object newValue) {
		Field<Object> vaadinField = (Field<Object>) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getComponentData().getCustomData();
		vaadinField.setValue(newValue);
	}

	public void showMessageBox(String componentId, GtnUIFrameworkActionType actionType, String header, String message)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
			confirmActionConfig.setActionType(actionType);
			confirmActionConfig.addActionParameter(header);
			confirmActionConfig.addActionParameter(message);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);

		} catch (Exception typeException) {
			throw new GtnFrameworkGeneralException(componentId + " not found", typeException);
		}
	}

}
