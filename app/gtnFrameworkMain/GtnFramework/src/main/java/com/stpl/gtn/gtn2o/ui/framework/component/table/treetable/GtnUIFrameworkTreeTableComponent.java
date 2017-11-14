package com.stpl.gtn.gtn2o.ui.framework.component.table.treetable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnUIFrameworkTreeTableComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTreeTableComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkTreeTableComponent");
		VerticalLayout resultLayout = new VerticalLayout();

		TreeTable treeTable = new TreeTable();
		ExtTreeContainer<GtnWsRecordBean> container = new ExtTreeContainer<>(GtnWsRecordBean.class,
				ExtContainer.DataStructureMode.LIST);
		GtnUIFrameworkPagedTableConfig config = componentConfig.getGtnPagedTableConfig();
		List<Object> visibleColumnList = Arrays.asList(config.getTableColumnMappingId());
		container.setRecordHeader(visibleColumnList);
		container.setColumnProperties(getProperties(visibleColumnList, config.getTableColumnDataType()));
		treeTable.setCaption(componentConfig.getComponentName());
		treeTable.setContainerDataSource(container);
		treeTable.setVisibleColumns(config.getTableColumnMappingId());
		treeTable.setColumnHeaders(config.getTableVisibleHeader());
		treeTable.setHeight(componentConfig.getComponentHight());
		treeTable.setWidth(componentConfig.getComponentWidth());
		treeTable.setSelectable(config.isSelectable());
		GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
		componentData.setCustomData(treeTable);
		resultLayout.addComponent(treeTable);
		resultLayout.setData(componentData);

		gtnLogger.info("Exiting into the buildVaadinComponent() of GtnUIFrameworkTreeTableComponent");
		return resultLayout;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {

		try {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			baseComponent.setTableValue(null);
			baseComponent.removeAllGridItems();
		} catch (GtnFrameworkValidationFailedException ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}

	}

	@SuppressWarnings("rawtypes")
	private Map<Object, Class> getProperties(final List visibleColumnsList,
			final Class<?>[] visibleColumnsDataTypeArray) {
		Map<Object, Class> properties = new HashMap<>();
		for (int i = 0; i < visibleColumnsList.size(); i++) {
		
				properties.put(visibleColumnsList.get(i), visibleColumnsDataTypeArray[i]);
			
		}
		return properties;
	}

}
