/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener.GtnUIFieldFactoryBlurListener;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener.GtnUIFieldFactoryExtCustomBoxClickListener;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener.GtnUIFieldFactoryFocusListener;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener.GtnUIFieldFactoryTextClickListener;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener.GtnUIFieldFactoryValueChangeListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

/**
 *
 * @author Mohamed.Shahul
 */
public class GtnUIFrameworkCustomFieldFactory extends DefaultFieldFactory {

	private static final long serialVersionUID = 1L;

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCustomFieldFactory.class);

	private List<String> editableColumnList;

	private List<GtnUIFrameworkComponentConfig> editableComponentConfig;

	private final GtnUIFrameworkComponentData treeTableComponentData;

	private final Map<String, GtnUIFrameworkComboBoxConfig> propertyIdComboBoxMap = new HashMap<>();

	public GtnUIFrameworkCustomFieldFactory(List<String> editableColumnList,
			List<GtnUIFrameworkComponentConfig> editableComponentConfig,
			GtnUIFrameworkComponentData treeTableComponentData) {
		gtnLogger.debug("Initializing FieldFactory");
		this.editableColumnList = editableColumnList == null ? editableColumnList : new ArrayList<>(editableColumnList);
		this.editableComponentConfig = editableComponentConfig == null ? editableComponentConfig
				: new ArrayList<>(editableComponentConfig);
		this.treeTableComponentData = treeTableComponentData;
	}

	public GtnUIFrameworkCustomFieldFactory() {
		this.treeTableComponentData = null;
	}

	public List<String> getEditableColumnList() {
		return editableColumnList == null ? editableColumnList : Collections.unmodifiableList(editableColumnList);
	}

	public void setEditableColumnList(List<String> editableColumnList) {
		this.editableColumnList = new ArrayList<>(editableColumnList);
	}

	public List<GtnUIFrameworkComponentConfig> getEditableComponentConfig() {
		return editableComponentConfig == null ? editableComponentConfig
				: Collections.unmodifiableList(editableComponentConfig);
	}

	public void setEditableComponentConfig(List<GtnUIFrameworkComponentConfig> editableComponentConfig) {
		this.editableComponentConfig = new ArrayList<>(editableComponentConfig);
	}

	@Override
	public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
			final Component uiContext) {

		if (editableColumnList == null || propertyId == null) {
			return null;
		}
		int index = editableColumnList.indexOf(propertyId);
		GtnUIFrameworkComponentConfig vaadinComponentConfig = null;
		if (index >= 0) {
			vaadinComponentConfig = editableComponentConfig.get(index);
		}
		if (vaadinComponentConfig != null) {
			try {

				GtnUIFrameworkActionParameter actionparameter = new GtnUIFrameworkActionParameter();
				actionparameter.setTableComponentId(treeTableComponentData.getComponentIdInMap());
				generateComponentPrefix(container, itemId, actionparameter);
				actionparameter.setVaadinComponentId(actionparameter.getComponentIdPrefix() + propertyId);
				actionparameter.setItemId((GtnWsRecordBean) itemId);
				actionparameter.setPropertyId((String) propertyId);
				if (vaadinComponentConfig.getFieldFactoryComponentCreateActionConfig() != null) {
					// This action should attach a GtnUIFrameworkComponentConfig
					// to
					// GtnUIFrameWorkActionConfig.setComponentConfig(GtnUIFrameworkComponentConfig
					// vaadinComponentConfig)
					vaadinComponentConfig.getFieldFactoryComponentCreateActionConfig()
							.setComponentConfig(vaadinComponentConfig);
					GtnUIFrameworkActionExecutor.executeSingleAction(actionparameter.getVaadinComponentId(),
							vaadinComponentConfig.getFieldFactoryComponentCreateActionConfig());
					vaadinComponentConfig = vaadinComponentConfig.getFieldFactoryComponentCreateActionConfig()
							.getComponentConfig();
				}

				AbstractComponent alreadyCreated = getAlreadyCreatedComponent(vaadinComponentConfig, actionparameter);

				if (alreadyCreated != null && ((GtnUIFrameworkComponentData) alreadyCreated.getData())
						.getActionParameter().getItemId().equals(itemId)) {
					alreadyCreated.setParent(null);
					return (Field<?>) alreadyCreated;
				}
				if (vaadinComponentConfig != null) {
					actionparameter
							.setOldValue(actionparameter.getItemId().getPropertyValue(actionparameter.getPropertyId()));
					AbstractField<?> field = generateField(propertyId, vaadinComponentConfig);

					GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
					componentData.setActionParameter(actionparameter);
					actionparameter.setVaadinComponentConfig(vaadinComponentConfig);
					componentData.setViewId(treeTableComponentData.getViewId());
					componentData.setCurrentViewConfig(treeTableComponentData.getCurrentViewConfig());
					componentData.setCurrentComponentConfig(vaadinComponentConfig);
					field.setData(componentData);
					actionparameter.setVaadinComponentId(
							GtnUIFrameworkGlobalUI.addVaadinComponent(actionparameter.getVaadinComponentId(), field));
					componentData = (GtnUIFrameworkComponentData) field.getData();
					componentData.setComponentIdInMap(actionparameter.getVaadinComponentId());
					vaadinComponentConfig.postCreateComponent(field);
					addValueChangeListener(vaadinComponentConfig, field);
					addFocusListener(vaadinComponentConfig, field);
					addBlurListener(vaadinComponentConfig, field);
					addItemClickListener(vaadinComponentConfig, field);
					return field;
				}
			} catch (GtnFrameworkGeneralException ex) {

				gtnLogger.error("Field Factory Error", ex);
			}
		}
		return null;
	}

	private AbstractComponent getAlreadyCreatedComponent(GtnUIFrameworkComponentConfig vaadinComponentConfig,
			GtnUIFrameworkActionParameter actionparameter) {
		if (vaadinComponentConfig != null) {
			return vaadinComponentConfig.isRebuild() ? null
					: GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionparameter.getVaadinComponentId())
							.getComponent();
		}
		return null;

	}

	private AbstractField<?> generateField(final Object propertyId, GtnUIFrameworkComponentConfig vaadinComponentConfig)
			throws GtnFrameworkGeneralException {
		AbstractField<?> field;
		if (vaadinComponentConfig.getComponentType().equals(GtnUIFrameworkComponentType.COMBOBOX)) {
			GtnUIFrameworkComboBoxConfig comboBoxConfigForListLoad = propertyIdComboBoxMap
					.get(String.valueOf(propertyId));
			if (comboBoxConfigForListLoad != null) {
				GtnUIFrameworkComponentConfig clonedFieldFirstComboBox = new GtnUIFrameworkComponentConfig(
						vaadinComponentConfig);
				clonedFieldFirstComboBox.setGtnComboboxConfig(comboBoxConfigForListLoad);
				field = (AbstractField<?>) clonedFieldFirstComboBox.returnVadinComponent();
			} else {
				field = (AbstractField<?>) vaadinComponentConfig.returnVadinComponent();
				generateComoboxConfigForListLoad(vaadinComponentConfig, (ComboBox) field);
				propertyIdComboBoxMap.put(String.valueOf(propertyId),
						generateComoboxConfigForListLoad(vaadinComponentConfig, (ComboBox) field));
			}
		} else {
			field = (AbstractField<?>) vaadinComponentConfig.returnVadinComponent();
		}
		return field;
	}

	private GtnUIFrameworkComboBoxConfig generateComoboxConfigForListLoad(
			GtnUIFrameworkComponentConfig vaadinComponentConfig, ComboBox firstRowComboBox) {
		GtnUIFrameworkComboBoxConfig comboBoxConfig = vaadinComponentConfig.getGtnComboboxConfig();
		comboBoxConfig.setLoadingUrl(null);
		Collection<?> itemIds = firstRowComboBox.getItemIds();
		comboBoxConfig.setItemValues(new ArrayList<>(itemIds));
		List<String> captionList = new ArrayList<>();
		for (Iterator iterator = itemIds.iterator(); iterator.hasNext();) {
			Object itemId = iterator.next();
			String caption = firstRowComboBox.getItemCaption(itemId);
			captionList.add(caption);
		}
		comboBoxConfig.setItemCaptionValues(captionList);
		return comboBoxConfig;
	}

	private void generateComponentPrefix(final Container container, final Object itemId,
			GtnUIFrameworkActionParameter actionparameter) {
		if (container instanceof IndexedContainer) {
			int rowNumber = ((IndexedContainer) container).indexOfId(itemId);
			actionparameter.setComponentIdPrefix(treeTableComponentData.getComponentIdInMap() + rowNumber);
		} else {
			actionparameter.setComponentIdPrefix(treeTableComponentData.getComponentIdInMap() + itemId.hashCode());

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addValueChangeAction(AbstractField abstractField) {
		gtnLogger.debug("Adding value change action for " + abstractField);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractField.getData();
		GtnUIFrameworkActionParameter actionparameter = componentData.getActionParameter();
		abstractField.setValue(actionparameter.getOldValue());
		abstractField.addValueChangeListener(GtnUIFieldFactoryValueChangeListener.getListener());
	}

	private void addFocusAction(final FieldEvents.FocusNotifier field) {
		field.addFocusListener(GtnUIFieldFactoryFocusListener.getListener());
	}

	private void addBlurAction(final FieldEvents.BlurNotifier field) {

		field.addBlurListener(GtnUIFieldFactoryBlurListener.getListener());
	}

	private void addClickListener(final CustomTextField field) {
		field.addClickListener(GtnUIFieldFactoryTextClickListener.getListener());
	}

	private void addClickListener(final ExtCustomCheckBox field) {
		field.addClickListener(GtnUIFieldFactoryExtCustomBoxClickListener.getListener());
	}

	private void addItemClickListener(GtnUIFrameworkComponentConfig vaadinComponentConfig, AbstractField<?> field) {
		if (vaadinComponentConfig.getGtnUIFrameWorkItemClickActionConfigList() != null
				&& !vaadinComponentConfig.getGtnUIFrameWorkItemClickActionConfigList().isEmpty()) {
			if (field instanceof ExtCustomCheckBox) {
				addClickListener((ExtCustomCheckBox) field);
			}

			if (field instanceof CustomTextField) {
				addClickListener((CustomTextField) field);
			}
		}
	}

	private void addBlurListener(GtnUIFrameworkComponentConfig vaadinComponentConfig, AbstractField<?> field) {
		if (vaadinComponentConfig.getGtnUIFrameWorkBlurActionConfigList() != null
				&& !vaadinComponentConfig.getGtnUIFrameWorkBlurActionConfigList().isEmpty()
				&& (field instanceof FieldEvents.BlurNotifier)) {
			addBlurAction((FieldEvents.BlurNotifier) field);
		}
	}

	private void addFocusListener(GtnUIFrameworkComponentConfig vaadinComponentConfig, AbstractField<?> field) {
		if (vaadinComponentConfig.getGtnUIFrameWorkFocusActionConfigList() != null
				&& !vaadinComponentConfig.getGtnUIFrameWorkFocusActionConfigList().isEmpty()
				&& (field instanceof FieldEvents.FocusNotifier)) {
			addFocusAction((FieldEvents.FocusNotifier) field);
		}
	}

	private void addValueChangeListener(GtnUIFrameworkComponentConfig vaadinComponentConfig, AbstractField<?> field) {
		if (vaadinComponentConfig.getGtnUIFrameWorkValueChangeActionConfigList() != null
				&& !vaadinComponentConfig.getGtnUIFrameWorkValueChangeActionConfigList().isEmpty()) {
			addValueChangeAction(field);
		}
	}

}
