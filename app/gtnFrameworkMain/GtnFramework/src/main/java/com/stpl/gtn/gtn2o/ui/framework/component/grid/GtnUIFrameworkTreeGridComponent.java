package com.stpl.gtn.gtn2o.ui.framework.component.grid;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameworkTreeGridComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {

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

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {

	}

}
