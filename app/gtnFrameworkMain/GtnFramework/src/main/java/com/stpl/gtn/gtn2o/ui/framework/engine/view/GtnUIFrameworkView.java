package com.stpl.gtn.gtn2o.ui.framework.engine.view;

import java.util.List;
import java.util.UUID;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.authorization.GtnUIFrameworkAuthorization;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

public class GtnUIFrameworkView extends CustomComponent implements View {

	private static final long serialVersionUID = 1L;

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkView.class);

	private final GtnUIFrameworkViewConfig gtnViewConfig;

	private final Layout rootLayout = new VerticalLayout();

	private String generatedViewId;

	public GtnUIFrameworkView(GtnUIFrameworkViewConfig gtnViewConfig) {
		this.gtnViewConfig = gtnViewConfig;
		GtnUIFrameworkComponentData viewComponentData = new GtnUIFrameworkComponentData();
		viewComponentData.setViewId(gtnViewConfig.getViewId());
		this.generatedViewId = viewComponentData.getViewId();
		viewComponentData.setCurrentViewConfig(gtnViewConfig);
		this.setData(viewComponentData);
		GtnUIFrameworkGlobalUI.addVaadinViewComponent(gtnViewConfig.getViewId(), this);
		loadStyles(rootLayout, gtnViewConfig.getComponentStyle());
	}

	public GtnUIFrameworkView(String replicableViewId, String sourceComponentId) {

		this.gtnViewConfig = GtnUIFrameworkGlobalUI.getGtnReplicableViewConfig(replicableViewId);
		GtnUIFrameworkComponentData viewComponentData = new GtnUIFrameworkComponentData();
		viewComponentData.setViewId(UUID.randomUUID().toString());
		this.generatedViewId = viewComponentData.getViewId();

		GtnUIFrameworkComponentData sourceComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(sourceComponentId);
		viewComponentData.setParentViewId(sourceComponentData.getViewId());
		viewComponentData.setCurrentViewConfig(gtnViewConfig);
		sourceComponentData.setCurrentChildViewId(generatedViewId);

		this.setData(viewComponentData);
		GtnUIFrameworkGlobalUI.addVaadinViewComponent(this.generatedViewId, this);
		
		loadStyles(rootLayout, gtnViewConfig.getComponentStyle());
	}

	public String getViewName() {
		return gtnViewConfig.getViewName();
	}

	public String getViewId() {
		return gtnViewConfig.getViewId();
	}

	public boolean isDefaultView() {
		return gtnViewConfig.isDefaultView();
	}

	public Layout getRootLayout() {
		return rootLayout;
	}

	public String getVaadinViewName() {
		if (gtnViewConfig.isDefaultView()) {
			return "";
		}
		return gtnViewConfig.getViewId();
	}

	public List<GtnUIFrameworkComponentConfig> getGtnComponentList() {
		return gtnViewConfig.getGtnComponentList();
	}

	public GtnUIFrameworkViewConfig getGtnViewConfig() {
		return gtnViewConfig;
	}

	public String getGeneratedViewId() {
		return generatedViewId;
	}

	public void setGeneratedViewId(String generatedViewId) {
		this.generatedViewId = generatedViewId;
	}

	public boolean buildScreen() throws GtnFrameworkGeneralException {
		boolean toBuild = rootLayout.getComponentCount() == 0;
		if (toBuild) {
			addErrorBannerLabel();
			addComponentList(gtnViewConfig.getGtnComponentList());
		}
		clearErrorPanel();
		if (gtnViewConfig.isResetAllowed()) {
			GtnUIFrameworkComponentData uiComponentData = GtnUIFrameworkGlobalUI.getGlobalComponentData();
			GtnUIFrameworkConfigMap frameworkConfigMap = uiComponentData.getFrameworkConfigMap();
			frameworkConfigMap.resetAllComponents(frameworkConfigMap, this.generatedViewId);
		}
		if (gtnViewConfig.getViewActionList() != null && !gtnViewConfig.getViewActionList().isEmpty()) {
			gtnLogger.debug("gtnViewConfig.getViewActionList() is going to execute for " + getViewName());
			GtnUIFrameworkActionExecutor.executeActionList(generatedViewId,
					gtnViewConfig.getViewActionList());
		}
		return toBuild;
	}

	private void clearErrorPanel() {
		GtnUIFrameworkComponentType.ERROR_BANNER.getGtnComponent().resetToDefault(
				this.generatedViewId + GtnFrameworkCommonStringConstants.ERROR_BANNER, getErrorLableConfig());
	}

	public void buildScreen(String sourceComponentId) throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentData sourceComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(sourceComponentId);
		GtnUIFrameworkComponentData viewComponentData = (GtnUIFrameworkComponentData) this.getData();
		viewComponentData.setParentViewId(sourceComponentData.getViewId());
		if (buildScreen()) {
			return;
		}
		setParentViewId(viewComponentData.getParentViewId(), gtnViewConfig.getGtnComponentList());
	}

	private void setParentViewId(String parentViewId, List<GtnUIFrameworkComponentConfig> componentConfigList) {
		for (GtnUIFrameworkComponentConfig componentConfig : componentConfigList) {
			GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(componentConfig.getComponentId(), this.generatedViewId);
			componentData.setParentViewId(parentViewId);
		}
	}

	public void addComponentList(List<GtnUIFrameworkComponentConfig> componentConfigList)
			throws GtnFrameworkGeneralException {

		for (GtnUIFrameworkComponentConfig componentConfig : componentConfigList) {
			addVaadinComponent(componentConfig);
		}
	}

	public void addErrorBannerLabel() throws GtnFrameworkGeneralException {
		addVaadinComponent(getErrorLableConfig());
	}

	public GtnUIFrameworkComponentConfig getErrorLableConfig() {
		GtnUIFrameworkComponentConfig errorBannerConfig = new GtnUIFrameworkComponentConfig();
		errorBannerConfig.setComponentType(GtnUIFrameworkComponentType.ERROR_BANNER);
		errorBannerConfig.setComponentId(this.generatedViewId + GtnFrameworkCommonStringConstants.ERROR_BANNER);
		errorBannerConfig.setAddToParent(false);
		return errorBannerConfig;
	}

	public AbstractComponent addVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		try {
			componentConfig.setSourceViewId(this.generatedViewId);
			AbstractComponent component = componentConfig.returnVadinComponent();
			component.setId(componentConfig.getComponentId());

			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
			if (component.getData() == null) {
				componentData = new GtnUIFrameworkComponentData();
			}
			componentData.setViewId(this.generatedViewId);

			if (gtnViewConfig.isReplicable()) {
				GtnUIFrameworkComponentData viewComponentData = (GtnUIFrameworkComponentData) this.getData();
				componentData.setParentViewId(viewComponentData.getParentViewId());
			}

			componentData.setCurrentViewConfig(this.getGtnViewConfig());
			componentData.setCurrentComponentConfig(componentConfig);
			component.setData(componentData);

			// Code for Authorization
			GtnUIFrameworkAuthorization.setAuthorizationToComponent(componentConfig, component);

			String componentId = GtnUIFrameworkGlobalUI.addVaadinComponent(componentConfig.getComponentId(), component);

			componentData.setComponentIdInMap(componentId);

			componentConfig.postCreateComponent(component);

			this.addToParentComponent(componentConfig, component);
			return component;
		} catch (Exception exception) {
			gtnLogger.error("Exception in addVaadinComponent for " + componentConfig.getComponentId(), exception);
			throw new GtnFrameworkGeneralException(
					"Exception in addVaadinComponent for " + componentConfig.getComponentId(), exception);
		}
	}

	private void addToParentComponent(GtnUIFrameworkComponentConfig componentConfig, AbstractComponent component) {

		if (componentConfig.isTabComponent()) {
			return;
		}
		if (!componentConfig.isAddToParent()) {
			rootLayout.addComponent(component);
			return;
		}
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();

		AbstractComponent parentComponent;
		if (componentData.getCurrentViewConfig().isReplicable()) {
			parentComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponent(componentData.getViewId() + "_" + componentConfig.getParentComponentId());
		} else {
			parentComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(componentConfig.getParentComponentId());
		}

		if (parentComponent instanceof AbstractLayout) {
			AbstractLayout gtnLayout = (AbstractLayout) parentComponent;
			gtnLayout.addComponent(component);
			return;
		}

		if (parentComponent instanceof Panel) {
			Panel gtnPanel = (Panel) parentComponent;
			gtnPanel.setContent(component);
			return;
		}
		if (parentComponent instanceof AbstractSplitPanel) {
			AbstractSplitPanel gtnSplitPanel = (AbstractSplitPanel) parentComponent;
			if (gtnSplitPanel.getFirstComponent() == null) {
				gtnSplitPanel.setFirstComponent(component);
				return;
			}
			gtnSplitPanel.setSecondComponent(component);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		gtnLogger.info("Inside Current UI enter method");
		Boolean restrictReloadFlag = (Boolean) GtnUIFrameworkGlobalUI.getSessionProperty("restrictReloadFlag");
		/**
		 * restricting reload of UI
		 */
		gtnLogger.info("restrictReloadFlag :" + restrictReloadFlag);
		if (restrictReloadFlag != null && restrictReloadFlag) {
			GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.FALSE);
			return;
		}
		try {
			buildScreen();
		} catch (GtnFrameworkGeneralException exception) {
			gtnLogger.error("Exception while building the screen", exception);
		}
		setCompositionRoot(rootLayout);
	}

	private void loadStyles(Component component, List<String> styles) {
		
		if (styles != null) {
			for (String style : styles) {
				component.addStyleName(style);
			}
		}
	}
}
