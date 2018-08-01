package com.stpl.gtn.gtn2o.ui.framework.component.grid;

import java.util.Collections;
import java.util.List;

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

public class GtnUIFrameworkGridComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkGridComponent.class);

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
					componentConfig.getGtnUIFrameWorkActionConfigList());
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Exception in postcreate of Grid", e);
		}
	}

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		Grid<GtnWsRecordBean> grid = new Grid<>();
		grid.setEnabled(componentConfig.isEnable());
		grid.setWidth(componentConfig.getComponentWidth());
		grid.setCaption(componentConfig.getComponentName());
		grid.setHeight(componentConfig.getComponentHight());
		loadStylesForGrid(grid, componentConfig.getComponentStyle());
		generateColumns(grid, componentConfig.getGtnUIFrameWorkGridConfig());
		setComponentData(grid, componentConfig);
		return grid;
	}

	private void loadStylesForGrid(Grid<GtnWsRecordBean> currentGrid, List<String> stylesForGrid) {
		if (stylesForGrid != null) {
			for (String style : stylesForGrid) {
				currentGrid.addStyleName(style);
			}
		}		
	}

	private void generateColumns(Grid<GtnWsRecordBean> grid,
			GtnUIFrameworkGridComponentConfig gridComponentConfig) {
		boolean isTheHeaderNameAvailable = gridComponentConfig.getColumnHeadersName().length > 0;
		int i = 0;

		for (String propId : gridComponentConfig.getColumnHeadersId()) {
			Column<GtnWsRecordBean, Object> col = grid
					.addColumn(recordBean -> recordBean.getPropertyValue(propId));
			if (isTheHeaderNameAvailable) {
				col.setCaption(gridComponentConfig.getColumnHeadersName()[i++]);
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
		Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) baseComponent.getComponent();
		grid.setItems(Collections.emptyList());
		postCreateComponent(grid, componentConfig);
	}

}
