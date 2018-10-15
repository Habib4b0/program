/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.pagedgrid;

import java.util.Collections;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkPagedGridComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedGridComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {

		VerticalLayout resultLayout = new VerticalLayout();
		GtnUIFrameworkPagedTableConfig tableConfig = componentConfig.getGtnPagedTableConfig();

		PagedGrid pagedGrid = new PagedGrid(tableConfig, componentConfig);
		pagedGrid.getGrid().setId(componentConfig.getComponentId());
		resultLayout.setSizeFull();
		resultLayout.addComponent(pagedGrid.getGrid());
		pagedGrid.getGrid().setWidth(componentConfig.getComponentWidth());
		pagedGrid.getGrid().setHeight(componentConfig.getComponentHight());
		resultLayout.setComponentAlignment(pagedGrid.getGrid(), Alignment.MIDDLE_CENTER);

		GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
		componentData.setTableConfig(tableConfig);
		componentData.setPagedGrid(pagedGrid);
		componentData.setCurrentPageGridLogic(pagedGrid.getPagedTableLogic());
		componentData.setCustomData(pagedGrid);

		if (!tableConfig.isPaginationOff()) {
			VerticalLayout controls = new VerticalLayout();
			controls.addComponents(pagedGrid.getControlLayout());
			controls.setMargin(false);
			controls.setSpacing(false);
			controls.setWidth("100%");
			controls.setHeightUndefined();
			controls.setId(componentConfig.getComponentId() + "itemsPerPageLayout");
			if (tableConfig.isItemsPerPageAlignCentre()) {
				controls.setComponentAlignment(pagedGrid.getControlLayout(), Alignment.MIDDLE_CENTER);
			}
			resultLayout.addComponent(controls);
		}
		pagedGrid.getGrid().getEditor().setEnabled(false);
		if (tableConfig.getSelectionListener()) {
			addSelectionListener(componentConfig, pagedGrid);
		}

		resultLayout.setData(componentData);

		return resultLayout;
	}
        
        private void resetGridFilter(PagedGrid baseComponent)
        {
             int count = baseComponent.getGrid().getHeaderRowCount();
              List<Component> componentList =  new ArrayList<>(baseComponent.getGrid().getHeaderRow(count-1).getComponents());
              for(Component component : componentList){
                 HorizontalLayout horizontalComponent = (HorizontalLayout) component;
                 Component comp = horizontalComponent.getComponent(0);
                  if(comp instanceof TextField){
                      ((TextField) comp).clear();
                      ((TextField) comp).setPlaceholder(GtnFrameworkCssConstants.SHOW_ALL);
                  }
                   if(comp instanceof DateField){
                      ((DateField) comp).clear();
                      ((DateField) comp).setPlaceholder(GtnFrameworkCssConstants.SHOW_ALL);
                  }
                   if(comp instanceof ComboBox){
                      ((ComboBox) comp).clear();
                      ((ComboBox) comp).setPlaceholder(GtnFrameworkCssConstants.SHOW_ALL);
                  }
              }
        }

	private void addSelectionListener(GtnUIFrameworkComponentConfig componentConfig, PagedGrid pagedGrid) {
		if (componentConfig.getGtnPagedTableConfig().getItemClickActionConfigList() != null) {
			pagedGrid.getGrid().addSelectionListener(event -> {
				for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnPagedTableConfig()
						.getItemClickActionConfigList()) {
					try {
						final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
						action.configureParams(actionConfig);
						action.doAction(componentConfig.getComponentId(), actionConfig);
					} catch (Exception e) {
						gtnLogger.error("message" + e);
					}

				}

			});
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		PagedGrid baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentData()
				.getPagedGrid();
                baseComponent.getGrid().clearSortOrder();
                resetGridFilter(baseComponent);
		baseComponent.getGrid().setItems(Collections.emptyList());
		if (!componentConfig.getGtnPagedTableConfig().isPaginationOff()){
		HorizontalLayout controlLayout = baseComponent.getControlLayout();
		HorizontalLayout horizontalLayoutForPage = (HorizontalLayout) controlLayout.getComponent(2);
		HorizontalLayout horizontalLayoutpageNoFieldText = (HorizontalLayout) horizontalLayoutForPage.getComponent(3);
		TextField textField = (TextField) horizontalLayoutpageNoFieldText.getComponent(0);
		textField.setValue("1");
		gtnLogger.info("inside resetToDefault ");
		HorizontalLayout horizontalLayoutForComboBox = (HorizontalLayout) controlLayout.getComponent(1);
		ComboBox comboBox = (ComboBox) horizontalLayoutForComboBox.getComponent(0);
		comboBox.setSelectedItem(10);
		Label horizontalLayoutpageCountLabel = (Label) horizontalLayoutForPage.getComponent(5);
		horizontalLayoutpageCountLabel.setValue("1");
		}
		postCreateComponent(baseComponent.getGrid(), componentConfig);
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		return;
	}

}