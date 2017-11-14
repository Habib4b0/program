package com.stpl.gtn.gtn2o.ui.framework.authorization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentName;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleAuthorizationBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleComponentBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsTablePropertyBean;
import com.stpl.gtn.gtn2o.ws.authorization.constants.GtnWsModuleAuthorizationConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.authorization.GtnWsModuleAuthorizationGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.authorization.GtnWsModuleAuthorizationGeneralResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.HorizontalLayout;

public class GtnUIFrameworkAuthorization {

	private static final String TABLE = "Table";

	private GtnUIFrameworkAuthorization() {
		return;
	}

	public static void setAuthorization(GtnUIFrameworkRootConfig rootConfig, String subModuleName) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralRequest gtnWsModuleSecurityGeneralRequest = new GtnWsModuleAuthorizationGeneralRequest();
		GtnWsModuleAuthorizationBean authorizationBean = new GtnWsModuleAuthorizationBean();
		authorizationBean.setModuleName(subModuleName);
		GtnWsGeneralRequest gtnSecurityGeneralRequest = new GtnWsGeneralRequest();
		gtnSecurityGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsModuleSecurityGeneralRequest.setGtnWsModuleSecurityBean(authorizationBean);
		serviceRequest.setGtnWsGeneralRequest(gtnSecurityGeneralRequest);
		serviceRequest.setGtnWsModuleAuthorizationGeneralRequest(gtnWsModuleSecurityGeneralRequest);

		if (isModuleUpdate(serviceRequest)) {
			updateComponentAuthorization(rootConfig, subModuleName);
			updateModuleUpdateFlag(serviceRequest);
		}
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
						+ GtnWsModuleAuthorizationConstants.GTN_GET_MODULEWISE_SECURITY_DETAILS_URI,
				serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsModuleAuthorizationGeneralResponse securityResponse = response
				.getGtnWsModuleAuthorizationGeneralResponse();

		GtnUIFrameworkComponentData uiComponentData = GtnUIFrameworkGlobalUI.getGlobalComponentData();
		GtnUIFrameworkConfigMap frameworkConfigMap = uiComponentData.getFrameworkConfigMap();
		frameworkConfigMap.setComponentSecurityResponse(securityResponse);
	}

	private static boolean isModuleUpdate(GtnUIFrameworkWebserviceRequest serviceRequest) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
						+ GtnWsModuleAuthorizationConstants.GTN_UPDATE_MODULE_FLAGE,
				serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsModuleAuthorizationGeneralResponse securityResponse = response
				.getGtnWsModuleAuthorizationGeneralResponse();
		return securityResponse.isModuleUpdate();
	}

	private static void updateModuleUpdateFlag(GtnUIFrameworkWebserviceRequest serviceRequest) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		wsclient.callGtnWebServiceUrl(
				"/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
						+ GtnWsModuleAuthorizationConstants.UPDATE_MODULE_UPDATE_FLAGE,
				serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	public static void setAuthorizationToComponent(GtnUIFrameworkComponentConfig componentConfig,
			AbstractComponent component) {
		GtnWsModuleAuthorizationBean authorizationBean = GtnUIFrameworkGlobalUI
				.getSecurityBean(componentConfig.getComponentId());
		if (authorizationBean != null) {
			if (authorizationBean.getComponentType().contains("Field")) {
				setAuthorizationPropertyForField(componentConfig, component, authorizationBean);
			}
			if (authorizationBean.getComponentType().contains(TABLE)) {
				setAuthorizationProperty(componentConfig, component, authorizationBean);
				GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
				setAuthorizationForTableProperty(componentConfig, componentData);
			} else {
				setAuthorizationProperty(componentConfig, component, authorizationBean);
			}
		}
	}

	private static void setAuthorizationForTableProperty(GtnUIFrameworkComponentConfig componentConfig,
			GtnUIFrameworkComponentData componentData) {
		if (componentConfig.getGtnNewTableConfig() != null) {
			List<Object> visibleColumnList = new ArrayList<>(
					Arrays.asList(componentConfig.getGtnNewTableConfig().getTableColumnMappingId()));
			List<String> visibleHeaderList = new ArrayList<>(
					Arrays.asList(componentConfig.getGtnNewTableConfig().getTableVisibleHeader()));
			setAuthorizedPropertyList(componentConfig, visibleColumnList, visibleHeaderList);
			ExtFilterTreeTable resultTable = (ExtFilterTreeTable) componentData.getCustomData();
			resultTable.setVisibleColumns(visibleColumnList.toArray());
			resultTable.setColumnHeaders(visibleHeaderList.toArray(new String[visibleHeaderList.size()]));
			return;
		}

		if (componentConfig.getGtnPagedTableConfig() != null) {
			List<Object> visibleColumnList = new ArrayList<>(
					Arrays.asList(componentConfig.getGtnPagedTableConfig().getTableColumnMappingId()));
			List<String> visibleHeaderList = new ArrayList<>(
					Arrays.asList(componentConfig.getGtnPagedTableConfig().getTableVisibleHeader()));
			setAuthorizedPropertyList(componentConfig, visibleColumnList, visibleHeaderList);
			ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
			resultTable.setVisibleColumns(visibleColumnList.toArray());
			resultTable.setColumnHeaders(visibleHeaderList.toArray(new String[visibleHeaderList.size()]));
			return;
		}
	}

	private static void setAuthorizationPropertyForField(GtnUIFrameworkComponentConfig componentConfig,
			AbstractComponent component, GtnWsModuleAuthorizationBean authorizationBean) {
		if (componentConfig.getParentComponentId() != null) {
			GtnUIFrameworkBaseComponent parentBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentConfig.getParentComponentId());
			AbstractComponent parentComponent = parentBaseComponent.getComponent();
			if (parentComponent instanceof HorizontalLayout) {
				int size = ((HorizontalLayout) parentComponent).getComponentCount();
				if (size > 1) {
					setAuthorizationProperty(componentConfig, component, authorizationBean);
					return;
				}
				setAuthorizationProperty(parentBaseComponent.getComponentConfig(), parentBaseComponent.getComponent(),
						authorizationBean);
				return;
			}
		}

		setAuthorizationProperty(componentConfig, component, authorizationBean);
	}

	private static void setAuthorizationProperty(GtnUIFrameworkComponentConfig componentConfig,
			AbstractComponent component, GtnWsModuleAuthorizationBean authorizationBean) {
		component.setVisible(authorizationBean.getIsVisible() && component.isVisible());
		component.setEnabled(authorizationBean.getIsEditable() && component.isEnabled());
		componentConfig.setVisible(authorizationBean.getIsVisible() && component.isVisible());
		componentConfig.setEnable(authorizationBean.getIsEditable() && component.isEnabled());
	}

	private static void setAuthorizedPropertyList(GtnUIFrameworkComponentConfig componentConfig,
			List<Object> visibleColumnList, List<String> visibleHeaderList) {
		for (GtnWsModuleAuthorizationBean propertyBean : GtnUIFrameworkGlobalUI.getDisabledTablePropertyIdList()) {
			if (componentConfig.getComponentId().equals(propertyBean.getComponentId())
					&& !propertyBean.getIsVisible()) {
				int index = visibleColumnList.indexOf(propertyBean.getTablePropertyId());
				visibleColumnList.remove(index);
				visibleHeaderList.remove(index);
			}
		}
	}

	private static void updateComponentAuthorization(GtnUIFrameworkRootConfig rootConfig, String subModuleName) {
		List<GtnWsModuleComponentBean> componentList = new ArrayList<>();
		for (GtnUIFrameworkViewConfig gtnUIFrameworkViewConfig : rootConfig.getGtnViewConfigList()) {
			for (GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig : gtnUIFrameworkViewConfig
					.getGtnComponentList()) {
				if (gtnUIFrameworkComponentConfig.isAuthorizationIncluded()) {
					GtnWsModuleComponentBean componentBean = new GtnWsModuleComponentBean();
					componentBean.setScreenName(gtnUIFrameworkViewConfig.getViewName());
					componentBean.setComponentId(gtnUIFrameworkComponentConfig.getComponentId());
					componentBean.setModuleName(subModuleName);
					componentBean.setComponentDesc(gtnUIFrameworkComponentConfig.getComponentName());
					componentBean.setComponentType(GtnUIFrameworkComponentName
							.valueOf(gtnUIFrameworkComponentConfig.getComponentType().name()).getComponentName());
					if (GtnUIFrameworkComponentType.TABSHEET.equals(gtnUIFrameworkComponentConfig.getComponentType())) {
						updateTabAuthorization(componentList, gtnUIFrameworkComponentConfig.getGtnTabSheetConfigList(),
								subModuleName, gtnUIFrameworkViewConfig.getViewName());
					}
					if (componentBean.getComponentType().contains(TABLE)) {
						setTablePropertyAuthorization(componentBean, gtnUIFrameworkComponentConfig, subModuleName);
					}
					componentList.add(componentBean);
				}

			}
		}

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest componentUpdateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = new GtnWsModuleAuthorizationGeneralRequest();
		moduleSecurityGeneralRequest.setGtnWsModuleComponentBeanList(componentList);
		componentUpdateRequest.setGtnWsModuleAuthorizationGeneralRequest(moduleSecurityGeneralRequest);
		GtnWsGeneralRequest gtnSecurityGeneralRequest = new GtnWsGeneralRequest();
		gtnSecurityGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		componentUpdateRequest.setGtnWsGeneralRequest(gtnSecurityGeneralRequest);

		wsclient.callGtnWebServiceUrl(
				"/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
						+ GtnWsModuleAuthorizationConstants.GTN_SECURITY_SAVE_COMPONENT_URI,
				componentUpdateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	private static void updateTabAuthorization(List<GtnWsModuleComponentBean> componentList,
			List<GtnUIFrameworkTabConfig> tabConfigList, String subModuleName, String screenName) {
		for (GtnUIFrameworkTabConfig gtnUIFrameworkTabConfig : tabConfigList) {
			GtnWsModuleComponentBean tabBean = new GtnWsModuleComponentBean();
			tabBean.setScreenName(screenName);
			tabBean.setComponentId(gtnUIFrameworkTabConfig.getTabLayoutComponentConfigList().get(0).getComponentId());
			tabBean.setModuleName(subModuleName);
			tabBean.setComponentDesc(gtnUIFrameworkTabConfig.getTabCaption());
			tabBean.setComponentType("Tab");
			componentList.add(tabBean);
			for (int i = 1; i < gtnUIFrameworkTabConfig.getTabLayoutComponentConfigList().size(); i++) {
				GtnUIFrameworkComponentConfig tabComponentConfig = gtnUIFrameworkTabConfig
						.getTabLayoutComponentConfigList().get(i);
				if (tabComponentConfig.isAuthorizationIncluded()) {
					GtnWsModuleComponentBean tabComponentBean = new GtnWsModuleComponentBean();
					tabComponentBean.setScreenName(gtnUIFrameworkTabConfig.getTabCaption());
					tabComponentBean.setComponentId(tabComponentConfig.getComponentId());
					tabComponentBean.setModuleName(subModuleName);
					tabComponentBean.setComponentDesc(tabComponentConfig.getComponentName());
					tabComponentBean.setComponentType(GtnUIFrameworkComponentName
							.valueOf(tabComponentConfig.getComponentType().name()).getComponentName());
					if (GtnUIFrameworkComponentType.TABSHEET.equals(tabComponentConfig.getComponentType())) {
						updateTabAuthorization(componentList, tabComponentConfig.getGtnTabSheetConfigList(),
								subModuleName, gtnUIFrameworkTabConfig.getTabCaption());
					}
					if (tabComponentBean.getComponentType().contains(TABLE)) {
						setTablePropertyAuthorization(tabComponentBean, tabComponentConfig, subModuleName);
					}
					componentList.add(tabComponentBean);
				}
			}
		}
	}

	private static void setTablePropertyAuthorization(GtnWsModuleComponentBean componentBean,
			GtnUIFrameworkComponentConfig componentConfig, String subModuleName) {
		if (componentConfig.getGtnNewPagedTableConfig() != null) {
			updateNewTablePropertyAuthorization(componentBean, componentConfig.getGtnNewTableConfig(), subModuleName);
			return;
		}
		if (componentConfig.getGtnPagedTableConfig() != null) {
			updateTablePropertyAuthorization(componentBean, componentConfig.getGtnPagedTableConfig(), subModuleName);
			return;
		}

	}

	private static void updateTablePropertyAuthorization(GtnWsModuleComponentBean componentBean,
			GtnUIFrameworkPagedTableConfig gtnPagedTableConfig, String subModuleName) {
		List<GtnWsTablePropertyBean> propertyList = new ArrayList<>();
		List<String> header = Arrays.asList(gtnPagedTableConfig.getTableVisibleHeader());
		List<Object> singelVisibleColumn = Arrays.asList(gtnPagedTableConfig.getTableColumnMappingId());
		if (gtnPagedTableConfig.getTableDoubleHeaderMap() != null
				&& !gtnPagedTableConfig.getTableDoubleHeaderMap().isEmpty()) {
			for (int i = 0; i < gtnPagedTableConfig.getTableDoubleHeaderVisibleColumns().length; i++) {
				Object[] visibleColumn = gtnPagedTableConfig.getTableDoubleHeaderMap()
						.get(gtnPagedTableConfig.getTableDoubleHeaderVisibleColumns()[i]);
				for (int j = 0; j < visibleColumn.length; j++) {
					GtnWsTablePropertyBean propertyBean = new GtnWsTablePropertyBean();
					propertyBean.setModuleName(subModuleName);
					propertyBean.setDoubleHeaderVisibleColumns(
							String.valueOf(gtnPagedTableConfig.getTableDoubleHeaderVisibleColumns()[i]));
					propertyBean
							.setDoubleHeaderVisibleHeaders(gtnPagedTableConfig.getTableDoubleHeaderVisibleHeaders()[i]);
					propertyBean.setSingleHeaderVisibleColumns(String.valueOf(visibleColumn[j]));
					propertyBean
							.setSingleHeaderVisibleHeaders(header.get(singelVisibleColumn.indexOf(visibleColumn[j])));
					propertyList.add(propertyBean);
				}
			}
		} else {
			for (int i = 0; i < gtnPagedTableConfig.getTableColumnMappingId().length; i++) {
				GtnWsTablePropertyBean propertyBean = new GtnWsTablePropertyBean();
				propertyBean.setModuleName(subModuleName);
				propertyBean.setSingleHeaderVisibleColumns(
						String.valueOf(gtnPagedTableConfig.getTableColumnMappingId()[i]));
				propertyBean.setSingleHeaderVisibleHeaders(gtnPagedTableConfig.getTableVisibleHeader()[i]);
				propertyList.add(propertyBean);
			}
		}
		componentBean.setTablePropertyList(propertyList);
	}

	private static void updateNewTablePropertyAuthorization(GtnWsModuleComponentBean componentBean,
			GtnUIFrameworkNewTableConfig gtnNewTableConfig, String subModuleName) {
		List<GtnWsTablePropertyBean> propertyList = new ArrayList<>();
		for (int i = 0; i < gtnNewTableConfig.getTableColumnMappingId().length; i++) {
			GtnWsTablePropertyBean propertyBean = new GtnWsTablePropertyBean();
			propertyBean.setModuleName(subModuleName);
			propertyBean.setSingleHeaderVisibleColumns(String.valueOf(gtnNewTableConfig.getTableColumnMappingId()[i]));
			propertyBean.setSingleHeaderVisibleHeaders(gtnNewTableConfig.getTableVisibleHeader()[i]);
			propertyList.add(propertyBean);
		}
		componentBean.setTablePropertyList(propertyList);
	}
}
