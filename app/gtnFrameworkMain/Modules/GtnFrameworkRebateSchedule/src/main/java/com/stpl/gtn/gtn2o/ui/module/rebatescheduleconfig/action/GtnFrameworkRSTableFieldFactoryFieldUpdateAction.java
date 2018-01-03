package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.fieldfactory.GtnFrameworkRSValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameworkPopUpBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCheckAllUpdateRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class GtnFrameworkRSTableFieldFactoryFieldUpdateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkRSTableFieldFactoryFieldUpdateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		final GtnWsRecordBean itemId = actionParam.getItemId();
		final String propertyId = actionParam.getPropertyId();
		if (GtnFrameworkRSValueChangeManager.isValueChangeAllowed()
				&& !GtnFrameworkRSConstants.getPopUpTextFieldProperties().contains(propertyId)) {
			updateCheckRecord(propertyId, actionParam, itemId);
		} else if (GtnFrameworkRSConstants.getPopUpTextFieldProperties().contains(propertyId)) {
			try {
				final String popUpComponentId = componentId;
				CustomWindow popUpWindow = new CustomWindow(GtnFrameworkCommonStringConstants.STRING_EMPTY);

				popUpWindow.addCloseListener(new Window.CloseListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void windowClose(Window.CloseEvent e) {
						CustomTextField textField = (CustomTextField) GtnUIFrameworkGlobalUI
								.getVaadinComponent(popUpComponentId);
						GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
								.getVaadinComponentData(getPopupBeanType(propertyId).getViewName());
						GtnWsRecordBean dto = (GtnWsRecordBean) componentData.getCustomData();
						Object newValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
						if (dto != null && dto.getPropertyValue(GtnFrameworkCommonConstants.SYSTEM_ID) != null) {
							newValue = dto.getPropertyValue(GtnFrameworkCommonConstants.SYSTEM_ID);
						} else if (dto != null && dto.getPropertyValue(GtnFrameworkCommonConstants.SYSTEM_ID) == null) {
							newValue = dto.getPropertyValueByIndex(dto.getProperties().size() - 1);
						}
						updateValues(itemId, dto, newValue, propertyId, textField);
					}
				});

				addWindow(propertyId, popUpWindow);
			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage());
			}
		}
		if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParam.getTableComponentId())
					.getLogicFromPagedDataTable().handleCheckBoxOnItem(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
							Boolean.parseBoolean(String.valueOf(actionParam.getCurrentValue())));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void configWindowStyles(CustomWindow window, String propertyId) {
		window.addStyleName("valo-theme-customwindow");
		window.addStyleName("bootstrap-ui");
		window.addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		window.addStyleName("bootstrap-forecast bootstrap-nm");
		window.setCaption(getPopupBeanType(propertyId).getCaption());
		window.setWidth("70%");
		window.setHeight("70%");
		window.center();
		window.setModal(false);
		window.setClosable(true);

	}

	public void updateField(String column, Object value, int systemId) {
		gtnLogger.debug("Updating for field + " + column + " with value " + value);
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsCheckAllUpdateBean checkAllUpdateBean = new GtnWsCheckAllUpdateBean();

		checkAllUpdateBean.setPropertyId(column);
		checkAllUpdateBean.setValue(value);
		checkAllUpdateBean.setMasterSid(systemId);
		checkAllUpdateBean.setCheckAll(false);

		GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = new GtnWsCheckAllUpdateRequest();
		gtnWsPSUpdateRequest.setUpdateBean(checkAllUpdateBean);
		updateRequest.setGtnWsCheckAllUpdateRequest(gtnWsPSUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.RS_SERVICE + "/" + GtnWsCDRContants.RS_UPDATE_SERVICE, updateRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	GtnUIFrameworkPopUpBean getPopupBeanType(String propertyId) {
		GtnUIFrameworkPopUpBean popUpBean = new GtnUIFrameworkPopUpBean(
				GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW);
		if (propertyId.equals("deductionNo")) {
			setPopupBean(popUpBean, GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW,
					"dcPopUpSearchResultTable", GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD,
					GtnFrameworkRSConstants.DEDUCTION_CALENDAR_NAME, GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD,
					"dcPopUpViewSelectButton");
			popUpBean.setCaption("Deduction Calendar LookUp");
		} else if (propertyId.equals("evaluationRuleName")) {
			setPopupBeanForRuleNames(popUpBean);
			popUpBean.setRuleTypeId(1705);
			popUpBean.setCaption("Evaluation Rule  LookUp ");
		} else if (propertyId.equals("calculationRuleName")) {
			setPopupBeanForRuleNames(popUpBean);
			popUpBean.setRuleTypeId(1706);
			popUpBean.setCaption("Calculation Rule Lookup");
		} else if (propertyId.equals("netSalesRuleName")) {
			setPopupBeanForRuleNames(popUpBean);
			popUpBean.setRuleTypeId(1704);
			popUpBean.setCaption("Net Sales Rule  LookUp");
		} else if (propertyId.equals(GtnFrameworkCommonConstants.FORMULA_NO)) {
			setPopupBean(popUpBean, "FormulaPopUpSearchSearchView", "FormulaPopUpsearchResultTable",
					GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD, GtnFrameworkCommonConstants.FORMULA_NAME,
					GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD, "FormulaPopUpSearchSearchViewAddButton");
			popUpBean.setCaption("Formula No Lookup");
		} else if (propertyId.equals(GtnFrameworkRSConstants.REBATE_PLAN_NO)) {
			setPopupBean(popUpBean, "rebatePlanPopUpSearchView", "rpPopUpSearchResultTable",
					GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD, GtnFrameworkRSConstants.REBATE_PLAN_NO,
					GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD, "rebatePlanPopUpViewSelectButton");
			popUpBean.setCaption("Rebate Plan No Lookup");
		} else {
			setPopupBean(popUpBean, "netSalesFormulaPopUpView", "netSalesSearchResultTable",
					GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD, GtnFrameworkCommonConstants.FORMULA_NAME,
					GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD, "netSalesFormulaPopUpViewAddButton");
			popUpBean.setCaption("Net Sales Formula Lookup ");

		}
		return popUpBean;

	}

	private void setPopupBeanForRuleNames(GtnUIFrameworkPopUpBean popUpBean) {
		setPopupBean(popUpBean, GtnFrameworkRSConstants.RS_NS_RULE_VIEW,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE, GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD,
				GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_SELECT_BUTTON);

	}

	private void setPopupBean(GtnUIFrameworkPopUpBean popUpBean, String viewName, String sourceTableName,
			String destinationComponentId, String sourcePropertyId, String destinaComponentId, String selectButtonId) {
		popUpBean.setViewName(viewName);
		popUpBean.setSourceTableName(sourceTableName);
		popUpBean.setDestinationComponentId(destinationComponentId);
		popUpBean.setSourcePropertyIdList(Arrays.asList(sourcePropertyId));
		popUpBean.setDestinaComponentIdList(Arrays.asList(destinaComponentId));
		popUpBean.setSelectButtonId(selectButtonId);

	}

	public Map<String, Object> initializeMap(String propertyId) {
		Map<String, Object> defaultDataMap = new HashMap<>();
		if (getPopupBeanType(propertyId).getViewName().equals(GtnFrameworkRSConstants.RS_NS_RULE_VIEW)) {
			defaultDataMap.put("ruleType", getPopupBeanType(propertyId).getRuleTypeId());
			defaultDataMap.put("ruleNo", GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put(GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put("ruleCategory", null);
			defaultDataMap.put(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE,
					GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put("cDRRulePopUpruleDetailsattachResultTable", null);

		} else if (getPopupBeanType(propertyId).getViewName()
				.equals(GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW)) {

			defaultDataMap.put("deductionCalendarNo", GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put(GtnFrameworkRSConstants.DEDUCTION_CALENDAR_NAME,
					GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put("customerGroupDesc", GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put("category", null);
			defaultDataMap.put("dcPopUpSearchResultTable", null);

		} else if (getPopupBeanType(propertyId).getViewName().equals("netSalesFormulaPopUpView")) {

			defaultDataMap.put(GtnFrameworkCommonConstants.FORMULA_NO, GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put(GtnFrameworkCommonConstants.FORMULA_NAME,
					GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put("formulaId", GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put("formulaType", null);
			defaultDataMap.put("netSalesSearchResultTable", null);

		} else if (getPopupBeanType(propertyId).getViewName().equals("FormulaPopUpSearchSearchView")) {
			defaultDataMap.put("formulaType", null);
			defaultDataMap.put(GtnFrameworkCommonConstants.FORMULA_NO, GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put(GtnFrameworkCommonConstants.FORMULA_NAME,
					GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
			defaultDataMap.put("FormulaPopUpsearchResultTable", null);
			defaultDataMap.put("FormulaPopUpformulaDetailsattachResultTable", null);

		}
		return defaultDataMap;
	}

	public void updateValues(GtnWsRecordBean itemId, GtnWsRecordBean dto, Object newValue, String propertyId,
			CustomTextField textField) {
		GtnWsRecordBean rowdto = itemId;
		if (rowdto != null && dto != null) {
			int sysId = Integer.parseInt(String.valueOf(newValue));
			String name;
			String tempProperty;
			if (propertyId.endsWith("RuleName")) {
				name = dto.getPropertyValue(GtnFrameworkCommonConstants.RULE_NAME).toString();
				tempProperty = propertyId.replace("Name", GtnFrameworkRSConstants.SYS_ID);
				updateField(tempProperty, sysId, Integer
						.parseInt(String.valueOf(rowdto.getProperties().get(rowdto.getRecordHeader().size() - 1))));
			} else if (propertyId.equals("deductionNo")) {
				name = dto.getPropertyValue(GtnFrameworkRSConstants.DEDUCTION_CALENDAR_NAME).toString();
				tempProperty = propertyId.replace("No", GtnFrameworkRSConstants.SYS_ID);
				updateField(tempProperty, sysId, Integer
						.parseInt(String.valueOf(rowdto.getProperties().get(rowdto.getRecordHeader().size() - 1))));

			} else if (propertyId.equals(GtnFrameworkRSConstants.REBATE_PLAN_NO)) {
				name = dto.getPropertyValue("secondaryRebatePlanNo").toString();
				String rpname = dto.getPropertyValue("secondaryRebatePlanName").toString();
				GtnUIFrameworkBaseComponent itemsTable = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("psRebateSetupTabResultDataTable");
				itemsTable.setContainerProperty(itemId, GtnFrameworkRSConstants.REBATE_PLAN_NO, name);
				itemsTable.setContainerProperty(itemId, "rebatePlanName", rpname);
				tempProperty = propertyId.replace("No", GtnFrameworkRSConstants.SYS_ID);
				updateField(tempProperty, sysId, Integer
						.parseInt(String.valueOf(rowdto.getProperties().get(rowdto.getRecordHeader().size() - 2))));
			} else if ("formulaNo".equals(propertyId)) {
				name = dto.getPropertyValue("formulaNo").toString();
				tempProperty = propertyId.replace("No", GtnFrameworkRSConstants.SYS_ID);
				updateField(tempProperty, sysId, Integer
						.parseInt(String.valueOf(rowdto.getProperties().get(rowdto.getRecordHeader().size() - 1))));
			} else {
				name = dto.getPropertyValue(GtnFrameworkCommonConstants.FORMULA_NAME).toString();
				tempProperty = propertyId.replace("Name", GtnFrameworkRSConstants.SYS_ID);
				updateField(tempProperty, sysId, Integer
						.parseInt(String.valueOf(rowdto.getProperties().get(rowdto.getRecordHeader().size() - 1))));
			}
			textField.setValue(name);
		}
	}

	public void addWindow(String propertyId, CustomWindow popUpWindow) throws GtnFrameworkGeneralException {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkView view = (GtnUIFrameworkView) GtnUIFrameworkGlobalUI
				.getVaadinComponent(getPopupBeanType(propertyId).getViewName());
		view.buildScreen();
		GtnUIFrameworkComponentConfig selectButtonComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(getPopupBeanType(propertyId).getSelectButtonId()).getComponentConfig();

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DATA_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(getPopupBeanType(propertyId).getSourceTableName());
		actionParameter.add(getPopupBeanType(propertyId).getViewName());

		selectAction.setActionParameterList(actionParameter);
		selectActionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(getPopupBeanType(propertyId).getViewName());
		selectActionConfigList.add(closeAction);

		selectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

		configWindowStyles(popUpWindow, propertyId);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.addActionParameter(initializeMap(propertyId));
		GtnUIFrameWorkAction action = setDefaultActionConfig.getActionType().getGtnUIFrameWorkAction().createInstance();
		action.configureParams(setDefaultActionConfig);
		GtnUIFrameworkComponentData viewComponentData = (GtnUIFrameworkComponentData) view.getData();
		viewComponentData.setCustomWindow(popUpWindow);

		popUpWindow.setContent(view.getRootLayout());
		UI.getCurrent().removeWindow(popUpWindow);
		UI.getCurrent().addWindow(popUpWindow);
		GtnUIFrameworkGlobalUI.addSessionProperty(getPopupBeanType(propertyId).getViewName(), popUpWindow);
	}

	private void updateCheckRecord(String propertyId, GtnUIFrameworkActionParameter actionParam,
			GtnWsRecordBean itemId) {
		Object value = actionParam.getCurrentValue();
		if ("checkRecordId".equals(propertyId)) {
			value = ((Boolean) value) ? 1 : 0;
		}
		updateField(actionParam.getPropertyId(), value,
				Integer.parseInt(String.valueOf(itemId.getPropertyValue(GtnFrameworkCommonConstants.SYSTEM_ID))));

	}
}
