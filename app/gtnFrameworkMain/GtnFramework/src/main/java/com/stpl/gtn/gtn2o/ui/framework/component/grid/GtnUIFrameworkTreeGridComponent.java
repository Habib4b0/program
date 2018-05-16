package com.stpl.gtn.gtn2o.ui.framework.component.grid;

import java.util.Collections;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameworkTreeGridComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkTreeGridComponent.class);

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
					componentConfig.getGtnUIFrameWorkActionConfigList());
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Exception in postcreate of TreeGrid", e);
		}
	}

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		TreeGrid<GtnWsRecordBean> grid = new TreeGrid<>();
		grid.setEnabled(componentConfig.isEnable());
		grid.setWidth(componentConfig.getComponentWidth());
		grid.setCaption(componentConfig.getComponentName());
		grid.setHeight(componentConfig.getComponentHight());
		generateColumns(grid, componentConfig.getGtnUIFrameWorkGridConfig());
		setComponentData(grid, componentConfig);
		return grid;
	}

	private void generateColumns(Grid<GtnWsRecordBean> grid,
			GtnUIFrameworkGridComponentConfig gtnUIFrameworkGridComponentConfig) {
		boolean isHeaderNameAvailable = gtnUIFrameworkGridComponentConfig.getColumnHeadersName().length > 0;
		int index = 0;
		for (String propertyId : gtnUIFrameworkGridComponentConfig.getColumnHeadersId()) {
			Column<GtnWsRecordBean, Object> column = grid
					.addColumn(recordBean -> recordBean.getPropertyValue(propertyId));
			if (isHeaderNameAvailable) {
				column.setCaption(gtnUIFrameworkGridComponentConfig.getColumnHeadersName()[index++]);
			}
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		// Not yet needed
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		TreeGrid<GtnWsRecordBean> grid = (TreeGrid<GtnWsRecordBean>) baseComponent.getComponent();
		grid.setItems(Collections.emptyList());
		postCreateComponent(grid, componentConfig);
	}

}
