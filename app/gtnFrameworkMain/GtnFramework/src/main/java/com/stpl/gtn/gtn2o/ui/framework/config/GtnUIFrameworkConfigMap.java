package com.stpl.gtn.gtn2o.ui.framework.config;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.response.authorization.GtnWsModuleAuthorizationGeneralResponse;
import com.vaadin.ui.AbstractComponent;

public class GtnUIFrameworkConfigMap {

	public GtnUIFrameworkConfigMap() {
		super();
	}

	private final Map<String, WeakReference<AbstractComponent>> vaadinComponentMap = new HashMap<>();
	/**
	 * The replicableViewConfigMap is to hold the replicable views and their
	 * config.
	 */
	private final Map<String, GtnUIFrameworkViewConfig> replicableViewConfigMap = new HashMap<>();

	private final Map<String, WeakReference<AbstractComponent>> fieldFactoryComponentMap = new HashMap<>();
	private final Map<String, GtnUIFrameworkDynamicClass> dynamicClassObjectMap = new HashMap<>();

	private final Map<String, GtnUIFrameworkBaseComponent> baseComponentMap = new HashMap<>();

	private GtnWsModuleAuthorizationGeneralResponse componentSecurityResponse;

	public Map<String, WeakReference<AbstractComponent>> getVaadinComponentMap() {
		return vaadinComponentMap;
	}

	public Map<String, GtnUIFrameworkViewConfig> getReplicableViewConfigMap() {
		return replicableViewConfigMap;
	}

	public void addVaadinComponent(String key, AbstractComponent component) {
		vaadinComponentMap.put(key, new WeakReference<>(component));
	}

	public void resetAllComponents(GtnUIFrameworkConfigMap frameworkConfigMap, String generatedViewId) {
		Map<String, WeakReference<AbstractComponent>> vaadinComponentMapForReset = frameworkConfigMap
				.getVaadinComponentMap();
		if (vaadinComponentMapForReset != null && !vaadinComponentMapForReset.isEmpty()) {
			for (Map.Entry<String, WeakReference<AbstractComponent>> entry : vaadinComponentMapForReset.entrySet()) {
				String componentId = entry.getKey();
				if (entry.getValue().get() != null) {
					GtnUIFrameworkBaseComponent wrrapedComponent = GtnUIFrameworkBaseComponent
							.returnBaseComponent(componentId, entry.getValue());
					GtnUIFrameworkComponentData componentData = wrrapedComponent.getComponentData();
					GtnUIFrameworkComponentConfig componentConfig = wrrapedComponent.getComponentConfig();
					if (componentConfig != null && generatedViewId.equals(componentData.getViewId())
							&& componentConfig.isResetToDefaultAllowed()) {
						GtnUIFrameworkComponentType componentType = componentConfig.getComponentType();
						GtnUIFrameworkComponent component = componentType.getGtnComponent();
						component.resetToDefault(componentId, componentConfig);

					}
				}
			}
		}
	}

	public void addFieldFactoryComponent(String key, AbstractComponent component) {
		fieldFactoryComponentMap.put(key, new WeakReference<>(component));
	}

	public Map<String, WeakReference<AbstractComponent>> getFieldFactoryComponentMap() {
		return fieldFactoryComponentMap;
	}

	public void addDynamicClassObject(String key, GtnUIFrameworkDynamicClass value) {
		dynamicClassObjectMap.put(key, value);
	}

	public GtnUIFrameworkDynamicClass getDynamicClass(String key) {
		return dynamicClassObjectMap.get(key);
	}

	public void addBaseComponent(String key, GtnUIFrameworkBaseComponent baseComponent) {
		baseComponentMap.put(key, baseComponent);
	}

	public Map<String, GtnUIFrameworkBaseComponent> getBaseComponentMap() {
		return baseComponentMap;
	}

	public GtnWsModuleAuthorizationGeneralResponse getComponentSecurityResponse() {
		return componentSecurityResponse;
	}

	public void setComponentSecurityResponse(GtnWsModuleAuthorizationGeneralResponse componentSecurityResponse) {
		this.componentSecurityResponse = componentSecurityResponse;
	}
}