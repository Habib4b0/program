package com.stpl.gtn.gtn2o.ui.framework.component.tabsheet;

import java.util.List;
import java.util.ListIterator;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.themes.ValoTheme;

public class GtnUIFrameworkTabSheetComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTabSheetComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkTabSheetComponent");

		TabSheet tabsheet = new TabSheet();
		loadStyles(tabsheet, componentConfig.getComponentStyle());
		tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		tabsheet.setWidth(componentConfig.getComponentWidth());
		tabsheet.setHeight(componentConfig.getComponentHight());

		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkTabSheetComponent");

		return tabsheet;
	}

	private void loadStyles(final Component component, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {
				component.addStyleName(style);
			}
		}

	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void postCreateComponent(AbstractComponent component, final GtnUIFrameworkComponentConfig componentConfig) {
		try {
			TabSheet tabsheet = (TabSheet) component;
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
			GtnUIFrameworkView gtnUIFrameworkView = (GtnUIFrameworkView) GtnUIFrameworkGlobalUI
					.getVaadinComponent(componentData.getViewId());
			List<GtnUIFrameworkTabConfig> tabConfigList = componentConfig.getGtnTabSheetConfigList();
			ListIterator<GtnUIFrameworkTabConfig> tabConfigIterator = tabConfigList.listIterator();
			while (tabConfigIterator.hasNext()) {
				int tabIndex = tabConfigIterator.nextIndex();
				GtnUIFrameworkTabConfig currentTabConfig = tabConfigIterator.next();
				AbstractComponent currentComponent = gtnUIFrameworkView
						.addVaadinComponent(currentTabConfig.getTabLayoutComponentConfigList().get(0));
				tabsheet.addTab(currentComponent, currentTabConfig.getTabCaption());
				Tab currentTab = tabsheet.getTab(currentComponent);
				currentTab.setVisible(currentComponent.isVisible());
				currentTab.setEnabled(currentComponent.isEnabled());
				GtnUIFrameworkComponentData tabComponentData = (GtnUIFrameworkComponentData) currentComponent.getData();
				tabComponentData.setGtnUIFrameworkTabConfig(currentTabConfig);
				if (GtnUIFrameworkTabSheetLoadType.LAZY_LOAD != currentTabConfig.getTabloadingType()) {
					gtnUIFrameworkView.addComponentList(currentTabConfig.getTabLayoutComponentConfigList().subList(1,
							currentTabConfig.getTabLayoutComponentConfigList().size()));
					tabComponentData.setTabLoaded(true);
				}
				if (currentTabConfig.isDefaultTab()) {
					componentData.setDefaultTabsheetTabIndex(tabIndex);
				}
			}
			tabsheet.addSelectedTabChangeListener(new GtnUIFrameworkTabSheetEventListener());
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkBaseComponent tabSheetBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		tabSheetBaseComponent.getAsTabSheet()
				.setSelectedTab(tabSheetBaseComponent.getComponentData().getDefaultTabsheetTabIndex());
	}

}
