package com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTable.ColumnGenerator;

public class GtnUIFrameworkGeneratedColumn implements ColumnGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GtnUIFrameworkComponentConfig generatedColumnConfig;

	private final GtnUIFrameworkComponentData treeTableComponentData;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkGeneratedColumn.class);

	public GtnUIFrameworkGeneratedColumn(GtnUIFrameworkComponentConfig generatedColumnConfig,
			GtnUIFrameworkComponentData treeTableComponentData) {
		logger.debug("Initialising Generated Column");
		this.generatedColumnConfig = generatedColumnConfig;
		this.treeTableComponentData = treeTableComponentData;
	}

	public GtnUIFrameworkComponentConfig getGeneratedColumnConfig() {
		return generatedColumnConfig;
	}

	public void setGeneratedColumnConfig(GtnUIFrameworkComponentConfig generatedColumnConfig) {
		this.generatedColumnConfig = generatedColumnConfig;
	}

	public GtnUIFrameworkComponentData getTreeTableComponentData() {
		return treeTableComponentData;
	}

	@Override
	public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {
		logger.debug("Generating cell for columnId " + columnId);
		if (generatedColumnConfig != null) {
			try {
				GtnUIFrameworkActionParameter actionparameter = new GtnUIFrameworkActionParameter();
				actionparameter.setTableComponentId(treeTableComponentData.getComponentIdInMap());
				actionparameter.setComponentIdPrefix(treeTableComponentData.getComponentIdInMap() + itemId.hashCode());
				actionparameter.setVaadinComponentId(actionparameter.getComponentIdPrefix() + columnId);
				actionparameter.setItemId((GtnWsRecordBean) itemId);
				actionparameter.setPropertyId((String) columnId);

				AbstractComponent component = generatedColumnConfig.returnVadinComponent();
				GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
				componentData.setActionParameter(actionparameter);
				actionparameter.setVaadinComponentConfig(generatedColumnConfig);
				componentData.setViewId(treeTableComponentData.getViewId());
				componentData.setCurrentViewConfig(treeTableComponentData.getCurrentViewConfig());
				componentData.setCurrentComponentConfig(generatedColumnConfig);
				component.setData(componentData);
				generatedColumnConfig.postCreateComponent(component);
				actionparameter.setVaadinComponentId(
						GtnUIFrameworkGlobalUI.addVaadinComponent(actionparameter.getVaadinComponentId(), component));
				componentData.setComponentIdInMap(actionparameter.getVaadinComponentId());
				GtnUIFrameworkActionExecutor.executeSingleAction(actionparameter.getVaadinComponentId(),
						generatedColumnConfig.getGtnUIFrameWorkColumnGeneratorConfig());
				return component;
			} catch (GtnFrameworkGeneralException e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}

}
