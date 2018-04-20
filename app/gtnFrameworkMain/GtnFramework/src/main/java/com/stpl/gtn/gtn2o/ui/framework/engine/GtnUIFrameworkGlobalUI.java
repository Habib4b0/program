package com.stpl.gtn.gtn2o.ui.framework.engine;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.session.GtnUIFrameworkSessionBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleAuthorizationBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCheckAllUpdateRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.UI;

public class GtnUIFrameworkGlobalUI {
	private GtnUIFrameworkGlobalUI() {
		/**
		 * empty constructor
		 */
	}

	public static void initGlobalUI(String userId) {

		GtnUIFrameworkConfigMap frameworkConfigMap = new GtnUIFrameworkConfigMap();
		GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
		componentData.setFrameworkConfigMap(frameworkConfigMap);

		UI.getCurrent().setData(componentData);
		int sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		addUserToSession(userId, sessionId);
		addSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID, sessionId);
	}

	private static void addUserToSession(String userId, int sessionId) {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		uiComponentData.setCurrentSesssionBean(new GtnUIFrameworkSessionBean());
		uiComponentData.getCurrentSesssionBean().setUserId("0");
		uiComponentData.getCurrentSesssionBean().setUserName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		if (userId != null) {
			uiComponentData.getCurrentSesssionBean().setUserId(Integer.valueOf(userId).toString());
		}
		uiComponentData.getCurrentSesssionBean().setSessionId(String.valueOf(sessionId));

	}

	public static String getCurrentUser() {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		return uiComponentData.getCurrentSesssionBean().getUserId();
	}

	private static String getCurrentUserNameFromId(String userId) {
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(userId);
		generalWSRequest.setSessionId(String
				.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		String userName = response.getGtnWsGeneralResponse().getUserName();
		GtnUIFrameworkGlobalUI.addSessionProperty("userName", userName);
		return userName;

	}

	public static String getCurrentUserName() {

		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		String userName = uiComponentData.getCurrentSesssionBean().getUserName();
		if (GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(userName) || userName == null) {
			uiComponentData.getCurrentSesssionBean().setUserName(getCurrentUserNameFromId(getCurrentUser()));
		}
		return uiComponentData.getCurrentSesssionBean().getUserName();
	}

	public static GtnUIFrameworkSessionBean getCurrentSessionBean() {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		return uiComponentData.getCurrentSesssionBean();
	}

	public static void addSessionProperty(String key, Object value) {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		uiComponentData.getCurrentSesssionBean().addSessionProperty(key, value);
	}

	public static Object getSessionProperty(String key) {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		return uiComponentData.getCurrentSesssionBean().getSessionProperty(key);
	}

	public static GtnUIFrameworkComponentData getGlobalComponentData() {
		return (GtnUIFrameworkComponentData) UI.getCurrent().getData();
	}

	public static String addVaadinComponent(String componentId, AbstractComponent component) {
		String id = componentId;
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		GtnUIFrameworkConfigMap frameworkConfigMap = uiComponentData.getFrameworkConfigMap();
		if (componentData.getCurrentViewConfig().isReplicable()) {
			id = componentData.getViewId() + "_" + id;
		}
		frameworkConfigMap.addVaadinComponent(id, component);
		return id;
	}

	public static GtnWsModuleAuthorizationBean getSecurityBean(String componentId) {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		GtnUIFrameworkConfigMap frameworkConfigMap = uiComponentData.getFrameworkConfigMap();
		return frameworkConfigMap.getComponentSecurityResponse().getModuleWiseComponentMap(componentId);
	}

	public static List<GtnWsModuleAuthorizationBean> getDisabledTablePropertyIdList() {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		GtnUIFrameworkConfigMap frameworkConfigMap = uiComponentData.getFrameworkConfigMap();
		return frameworkConfigMap.getComponentSecurityResponse().getModuleWiseTablePropertyList();
	}

	public static void addVaadinViewComponent(String componentId, AbstractComponent component) {

		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		GtnUIFrameworkConfigMap frameworkConfigMap = uiComponentData.getFrameworkConfigMap();
		frameworkConfigMap.addVaadinComponent(componentId, component);
	}

	public static String getGeneratedComponentIdFromView(String componentId, String viewId) {
		if (getGtnReplicableViewConfig(viewId) == null) {
			return componentId;
		}
		return viewId + "_" + componentId;
	}

	public static String getGeneratedComponentId(String componentId, String sourceComponentId) {
		GtnUIFrameworkComponentData sourceComponentData = getVaadinComponentData(sourceComponentId);
		if (sourceComponentData.getCurrentViewConfig().isReplicable()) {
			return sourceComponentData.getViewId() + "_" + componentId;
		}
		return componentId;
	}

	public static GtnUIFrameworkBaseComponent getVaadinBaseComponent(String componentId) {
		WeakReference<AbstractComponent> weakReferanceComponent = getVaadinComponentFromMap(componentId);
		return GtnUIFrameworkBaseComponent.returnBaseComponent(componentId, weakReferanceComponent);
	}

	private static WeakReference<AbstractComponent> getVaadinComponentFromMap(String componentId) {
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		GtnUIFrameworkConfigMap vaadinComponentMap = uiComponentData.getFrameworkConfigMap();
		return vaadinComponentMap.getVaadinComponentMap().get(componentId);

	}

	public static GtnUIFrameworkBaseComponent getVaadinBaseComponent(String componentId, String sourceComponentId) {
		String id = componentId;
		GtnUIFrameworkComponentData sourceComponentData = getVaadinComponentData(sourceComponentId);
		if (sourceComponentData.getCurrentViewConfig().isReplicable()) {
			id = sourceComponentData.getViewId() + "_" + componentId;
		}
		return getVaadinBaseComponent(id);
	}

	public static GtnUIFrameworkViewConfig getGtnReplicableViewConfig(String viewId) {
		GtnUIFrameworkComponentData uiComponentData = GtnUIFrameworkGlobalUI.getGlobalComponentData();
		Map<String, GtnUIFrameworkViewConfig> replicableViewConfigMap = uiComponentData.getFrameworkConfigMap()
				.getReplicableViewConfigMap();
		return replicableViewConfigMap.get(viewId);
	}

	public static GtnUIFrameworkBaseComponent getVaadinBaseComponentFromParent(String componentId,
			String sourceComponentId) {
		GtnUIFrameworkComponentData sourceComponentData = getVaadinComponentData(sourceComponentId);
		if (sourceComponentData.getParentViewId() == null) {
			return getVaadinBaseComponent(componentId);
		}

		GtnUIFrameworkComponentData viewComponentData = getVaadinComponentData(sourceComponentData.getParentViewId());
		if (viewComponentData.getCurrentViewConfig().isReplicable()) {
			return getVaadinBaseComponent(sourceComponentData.getParentViewId() + "_" + componentId);
		}

		return getVaadinBaseComponent(componentId);
	}

	public static GtnUIFrameworkBaseComponent getVaadinBaseComponentFromChild(String componentId,
			String sourceComponentId) {
		GtnUIFrameworkComponentData sourceComponentData = getVaadinComponentData(sourceComponentId);
		if (sourceComponentData.getCurrentChildViewId() == null) {
			return getVaadinBaseComponent(componentId);
		}

		GtnUIFrameworkComponentData viewComponentData = getVaadinComponentData(
				sourceComponentData.getCurrentChildViewId());
		if (viewComponentData.getCurrentViewConfig().isReplicable()) {
			return getVaadinBaseComponent(sourceComponentData.getCurrentChildViewId() + "_" + componentId);
		}

		return getVaadinBaseComponent(componentId);
	}

	public static GtnUIFrameworkComponentData addFieldFactoryComponent(String componentId,
			AbstractComponent component) {
		GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
		GtnUIFrameworkComponentData uiComponentData = getGlobalComponentData();
		GtnUIFrameworkConfigMap frameworkConfigMap = uiComponentData.getFrameworkConfigMap();
		frameworkConfigMap.addFieldFactoryComponent(componentId, component);
		return componentData;
	}

	public static GtnUIFrameworkBaseComponent getVaadinBaseComponentFromView(String componentId, String viewId) {
		if (getGtnReplicableViewConfig(viewId) == null) {
			return getVaadinBaseComponent(componentId);
		}
		return getVaadinBaseComponent(viewId + "_" + componentId);
	}

	public static GtnUIFrameworkComponentData getVaadinFieldFactoryComponentData(String componentId) {
		return getVaadinBaseComponent(componentId).getComponentData();
	}

	public static GtnUIFrameworkComponentData getVaadinComponentData(String componentId) {
		GtnUIFrameworkBaseComponent baseComponent = getVaadinBaseComponent(componentId);
		return baseComponent.getComponentData();
	}

	public static GtnUIFrameworkComponentData getVaadinComponentData(String componentId, String sourceComponentId) {
		GtnUIFrameworkBaseComponent baseComponent = getVaadinBaseComponent(componentId, sourceComponentId);
		if (baseComponent == null) {
			return null;
		}
		return baseComponent.getComponentData();
	}

	public static GtnUIFrameworkComponentData getVaadinViewComponentData(String sourceComponentId) {
		GtnUIFrameworkBaseComponent sourceComponent = getVaadinBaseComponent(sourceComponentId);
		GtnUIFrameworkBaseComponent baseComponent = getVaadinBaseComponent(
				sourceComponent.getComponentData().getViewId());
		if (baseComponent == null) {
			return null;
		}
		return baseComponent.getComponentData();
	}

	public static AbstractComponent getVaadinComponent(String componentId) {
		GtnUIFrameworkBaseComponent baseComponent = getVaadinBaseComponent(componentId);
		return baseComponent.getComponent();
	}

	public static AbstractComponent getVaadinComponent(String componentId, String sourceComponentId) {
		GtnUIFrameworkBaseComponent baseComponent = getVaadinBaseComponent(componentId, sourceComponentId);
		if (baseComponent == null) {
			return null;
		}
		return baseComponent.getComponent();
	}

	public static AbstractComponent getVaadinComponentForReplicableView(String componentId, String sourceComponentId) {
		GtnUIFrameworkBaseComponent baseComponent = getVaadinBaseComponent(componentId, sourceComponentId);
		if (baseComponent == null) {
			return null;
		}
		return baseComponent.getComponent();
	}

	public static GtnWsSecurityToken getGtnWsSecurityToken() {

		GtnWsSecurityToken token = new GtnWsSecurityToken();
		token.setUserId(getCurrentUser());
		token.setSessionId(getCurrentSessionBean().getSessionId());
		return token;

	}

	public static void addChildComponent(String parentComponentId, List<GtnUIFrameworkComponentConfig> componentConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = getVaadinComponentData(parentComponentId);
		GtnUIFrameworkView gtnUIFrameworkView = (GtnUIFrameworkView) getVaadinComponent(componentData.getViewId());
		removeAllChildComponent(getVaadinComponent(parentComponentId));
		gtnUIFrameworkView.addComponentList(componentConfig);

	}

	public static void removeAllChildComponent(AbstractComponent component) {
		if (component instanceof AbstractLayout) {
			AbstractLayout gtnLayout = (AbstractLayout) component;
			gtnLayout.removeAllComponents();
		}
	}

	public static void setVisibleFlagForComponent(boolean visible, List<String> componentList,
			String sourceComponentId) {
		for (String componentId : componentList) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).setVisible(visible);
		}
	}

	public static void setEnableFlagForComponent(boolean enable, List<String> componentList, String sourceComponentId) {
		for (String componentId : componentList) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).setEnable(enable);
		}

	}

	public static void showMessageBox(String componentId, GtnUIFrameworkActionType actionType, String header,
			String message) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(actionType);
		confirmActionConfig.addActionParameter(header);
		confirmActionConfig.addActionParameter(message);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
	}

	public static Object getConvertedPropertyValue(Class<?> type, Object value) {
		Object propertyValue = value;

		if (String.class.isAssignableFrom(type)) {
			propertyValue = (propertyValue == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY
					: String.valueOf(propertyValue).trim());
		}
		if (propertyValue != null && Date.class.isAssignableFrom(type)
				&& Long.class.isAssignableFrom(propertyValue.getClass())) {
			propertyValue = new Date((Long) propertyValue);
		}
		return propertyValue;
	}

	public static String getBasePath() {
		String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		return path != null ? path : "";
	}

	public static List<List<String>> getPageParameterList() {
		List<List<String>> paramList = new ArrayList<>();
		paramList.add(new ArrayList<String>());
		paramList.add(new ArrayList<String>());
		String pageParameters = Page.getCurrent().getLocation().getQuery();
		if (pageParameters != null) {
			String[] parameters = pageParameters.split("&");
			for (String para : parameters) {
				String[] paraStr = para.split("=");
				paramList.get(0).add(paraStr[0]);
				paramList.get(1).add(paraStr.length > 1 ? paraStr[1] : "");
			}
		}
		return paramList;
	}

	public static void windowClose() {
		Page.getCurrent().getJavaScript().execute("window.close()");
	}

	public static void addViewToNavigator(String viewId, View view) {
		UI.getCurrent().getNavigator().addView(viewId, view);
	}

	public static void removeViewFromNavigator(String viewId) {
		UI.getCurrent().getNavigator().removeView(viewId);
	}

	public static void loadNotesTab(List<Object> result, String notesTabComponentId) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent(notesTabComponentId.trim());
		notesTab.setNotesTabData(result);
	}

	public static void addDynamicClassObjects(String key, GtnUIFrameworkDynamicClass value) {
		GtnUIFrameworkConfigMap frameworkConfigMap = getGlobalComponentData().getFrameworkConfigMap();
		frameworkConfigMap.addDynamicClassObject(key, value);
	}

	public static void updateFieldByMassUpdate(GtnWsCheckAllUpdateBean updateBean, String url) {
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String
				.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
		GtnWsCheckAllUpdateRequest checkAllUpdateRequest = new GtnWsCheckAllUpdateRequest();
		checkAllUpdateRequest.setUpdateBean(updateBean);
		updateRequest.setGtnWsCheckAllUpdateRequest(checkAllUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(url, updateRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	/***
	 * Validates(null,Empty,0 for Combo) fields based on component type and
	 * returns error message
	 */
	public static StringBuilder validateFields(String[] componentIds, StringBuilder errorMessageBuilder)
			throws GtnFrameworkValidationFailedException {
		String appender = " ";
		for (int i = 0; i < componentIds.length; i++) {
			GtnUIFrameworkBaseComponent field = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIds[i]);
			GtnUIFrameworkComponentType type = field.getComponentConfig().getComponentType();
			Object value;
			if (GtnUIFrameworkComponentType.COMBOBOX == type) {
				value = field.getIntegerFromField();
			} else if (GtnUIFrameworkComponentType.DATEFIELD == type) {
				value = field.getDateFromDateField();
			} else {
				value = field.getStringFromField();
			}
			if (value == null || value instanceof String && String.valueOf(value).isEmpty()
					|| value instanceof Integer && Integer.parseInt(String.valueOf(value)) == 0) {
				errorMessageBuilder.append(appender).append(field.getCaption());
				appender = " , ";

			}
		}

		return errorMessageBuilder;
	}

	/***
	 * Get item list for combo box based on list type and returns comboBox
	 * returns combo Box response
	 */

	public static GtnUIFrameworkWebserviceComboBoxResponse getComboBoxItemListFromService(String type) {
		GtnUIFrameworkWebserviceComboBoxResponse response;
		GtnUIFrameworkWebServiceClient frameworkWebServiceClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setComboBoxType(type);

		gtnUIFrameworkWebserviceRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		response = frameworkWebServiceClient.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX,
				gtnUIFrameworkWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
				.getGtnUIFrameworkWebserviceComboBoxResponse();

		return response;
	}

	public static void addSearchCriteriaList(List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList,
			String tableId) {
		Collection<Container.Filter> filterSet = getVaadinBaseComponent(tableId).getFiltersFromPagedDataTable();
		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
				if (filter instanceof SimpleStringFilter) {
					searchCriteria.setFieldId(((SimpleStringFilter) filter).getPropertyId().toString());
					searchCriteria.setFilterValue1(((SimpleStringFilter) filter).getFilterString());
					searchCriteria.setExpression(GtnFrameworkCommonStringConstants.LIKE);
				}
				if (filter instanceof Between) {
					searchCriteria.setFieldId(((Between) filter).getPropertyId().toString());
					searchCriteria.setFilterValue1(((Between) filter).getStartValue().toString());
					searchCriteria.setFilterValue2(((Between) filter).getEndValue().toString());
					searchCriteria.setExpression(GtnFrameworkCommonStringConstants.BETWEEN);
				}
				if (filter instanceof Compare) {
					Compare stringFilter = (Compare) filter;
					Compare.Operation operation = stringFilter.getOperation();
					searchCriteria.setFieldId(stringFilter.getPropertyId().toString());
					searchCriteria.setFilterValue1(stringFilter.getValue().toString());
					searchCriteria.setExpression(operation.name());
				}
				gtnWebServiceSearchCriteriaList.add(searchCriteria);
			}
		}

	}

	public static List<GtnWebServiceSearchCriteria> addCurrentSearchCriteria(List<String> vaadinFieldValues) {
		List<GtnWebServiceSearchCriteria> currentSearchCriteriaList = new ArrayList<>();
		if (vaadinFieldValues != null) {
			for (String componentId : vaadinFieldValues) {
				GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
				String currentValue = getVaadinBaseComponent(componentId).getStringFromField();
				if (currentValue != null && !GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(currentValue)) {
					searchCriteria.setFieldId(componentId);
					searchCriteria.setFilterValue1(currentValue);
					searchCriteria.setExpression(
							currentValue.contains("*") ? GtnFrameworkCommonStringConstants.LIKE : "EQUALS");
					currentSearchCriteriaList.add(searchCriteria);
				}
			}
		}
		return currentSearchCriteriaList;
	}

	public static GtnWsRecordBean getValueFromTable(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) actionParamList.get(1);
		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof Boolean) {
			itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).getValueFromPagedDataTable();
		}
		GtnWsRecordBean selectedRecord = (GtnWsRecordBean) itemId;
		if (selectedRecord == null) {
			throw new GtnFrameworkGeneralException("Selected Table Record is Null");
		}
		return selectedRecord;
	}

	public static String regexValidation(String[] componentIds) {
		StringBuilder msg = new StringBuilder();
		for (int i = 0; i < componentIds.length; i++) {
			GtnUIFrameworkComponentConfig currentComponentConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentIds[i]).getComponentConfig();
			String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIds[i]).getStringFromField();
			if (currentComponentConfig.getGtnUIFrameworkValidationConfig() != null
					&& currentComponentConfig.getGtnUIFrameworkValidationConfig().isAttachRegxValidatior()
					&& !value.matches(currentComponentConfig.getGtnUIFrameworkValidationConfig().getFormatString())) {
				msg.append(GtnFrameworkCommonConstants.BR
						+ currentComponentConfig.getGtnUIFrameworkValidationConfig().getRegxValidationMessage());
			}

		}
		return msg.toString();
	}

	public static void tablePostCreateAction(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		if (componentConfig.getGtnPagedTableConfig().getPostCreationActionConfigList() != null
				&& !componentConfig.getGtnPagedTableConfig().getPostCreationActionConfigList().isEmpty()) {
			for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnPagedTableConfig()
					.getPostCreationActionConfigList()) {
				final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
				action.configureParams(actionConfig);
				action.doAction(componentConfig.getComponentId(), actionConfig);
			}
		}
	}
}