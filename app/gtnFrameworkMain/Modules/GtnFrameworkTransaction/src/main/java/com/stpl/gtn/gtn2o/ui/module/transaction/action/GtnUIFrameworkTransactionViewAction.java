package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import java.math.BigDecimal;

public class GtnUIFrameworkTransactionViewAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTransactionViewAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("----------Inside doAction ---------------");

		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		boolean isInvalid = (Boolean) actionParamList.get(8);
		String tableName = (String) actionParamList.get(2);
		List<Object> componentList = null;
		String wsViewName = tableName;
		String demandTypeColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String demandTypeColumnValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String inventoryLevelColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String inventoryLevelColumnValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		List<String> inventoryType = new ArrayList<>();

		if (wsViewName.contains("InventoryWdActualProjMas")) {
			List<String> viewColumnList = (List<String>) actionParamList.get(1);
			List<Object> columnList = (List<Object>) actionParamList.get(4);
			List<String> defaultViewColumnList = (List<String>) actionParamList.get(5);
			defaultViewColumnList.remove("transactionViewLayoutcheckRecord");
			GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(false, defaultViewColumnList, componentId);
			componentList = new ArrayList<>(columnList);
			GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(true, viewColumnList, componentId);
			inventoryType.add(GtnTransactionUIConstants.INVENTORY_TYPE);
			inventoryType.add(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnTransactionUIConstants.INVENTORY_TYPE)
					.getValueFromComponent().toString());
			inventoryLevelColumnName = GtnTransactionUIConstants.INVENTORY_LEVEL;
			inventoryLevelColumnValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnTransactionUIConstants.INVENTORY_LEVEL).getValueFromComponent()
					.toString();
		} else if (wsViewName.contains(GtnTransactionUIConstants.DEMAND)) {
			demandTypeColumnName = GtnTransactionUIConstants.DEMAND_TYPE_SID;
			demandTypeColumnValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnTransactionUIConstants.DEMAND_TYPE_SID).getValueFromComponent()
					.toString();
			gtnLogger.info(demandTypeColumnValue);
			String layoutName = "transactionViewLayout";
			String demandTypeCaption = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("adjustedDemandForecastId")
					.getCaptionFromComboBox();
			String forecastTypeCaption = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnTransactionUIConstants.DEMAND_TYPE_SID).getCaptionFromComboBox();
			List<Object> viewModeColumnList = (List<Object>) actionParamList.get(4);
			List<GtnWSTransactionColumnBean> viewModeComponents = (List<GtnWSTransactionColumnBean>) actionParamList
					.get(6);
			List<GtnWSTransactionColumnBean> viewModeOrderComponents = (List<GtnWSTransactionColumnBean>) actionParamList
					.get(7);
			List<Object> viewModeOrderColumnList = new ArrayList<>();
			for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : viewModeOrderComponents) {
				viewModeOrderColumnList.add(gtnWSTransactionColumnBean.getColumnID());
			}

			if (GtnTransactionUIConstants.ADJUSTED_DEMAND.equals(demandTypeCaption)) {

				componentList = getComponentList(forecastTypeCaption, viewModeOrderComponents, viewModeComponents,
						layoutName, viewModeOrderColumnList, viewModeColumnList);
			} else if (GtnTransactionUIConstants.DEMAND.equals(demandTypeCaption)) {

				componentList = getComponentList(forecastTypeCaption, viewModeOrderComponents, viewModeComponents,
						layoutName, viewModeOrderColumnList, viewModeColumnList);
				wsViewName = getTableName(tableName);
			}
		} else {
			componentList = (List<Object>) actionParamList.get(1);
		}
		List<String> helpercomponentList = (List<String>) actionParamList.get(3);
		int systemId = getSystemId(isInvalid, componentId, actionParamList);
		try {
			if ("VwInventoryWdActualProjMas".equalsIgnoreCase(wsViewName)) {
				loadDataFromService(componentList, wsViewName, helpercomponentList, systemId, inventoryLevelColumnName,
						inventoryLevelColumnValue, inventoryType);
			} else {
				loadDataFromService(componentList, wsViewName, helpercomponentList, systemId, demandTypeColumnName,
						demandTypeColumnValue, null);
			}
			gtnLogger.info("----------Ending doAction ---------------");
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error in doAction", e);
		}
	}

	private void loadDataFromService(List<Object> componentList, String tableName, List<String> helpercomponentList,
			int systemId, String demandTypeColumnName, String demandTypeColumnValue, List<String> inventoryType)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("--------Inside loadDataFromService----------");

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsTransactionRequest gtnWsTransactionRequest = new GtnWsTransactionRequest();
		gtnWsTransactionRequest.setTableName(tableName);
		gtnWsTransactionRequest.setProjectionColumns(componentList);
		gtnWsTransactionRequest.setPrimayColumnValue(systemId);
		gtnWsTransactionRequest.setHelpercomponentList(helpercomponentList);
		if ("VwInventoryWdActualProjMas".equalsIgnoreCase(tableName)) {
			gtnWsTransactionRequest.setInventoryLevelColumnName(demandTypeColumnName);
			gtnWsTransactionRequest.setInventoryLevelColumnValue(
					demandTypeColumnValue.isEmpty() ? 0 : Integer.parseInt(demandTypeColumnValue));
			gtnWsTransactionRequest.setInventoryTypeColumnName(inventoryType.get(0));
			gtnWsTransactionRequest.setInventoryTypeColumnValue(Integer.parseInt(inventoryType.get(1)));
		} else if ("Demand".equalsIgnoreCase(tableName)) {
			gtnWsTransactionRequest.setDemandTypeColumnName(demandTypeColumnName);
			gtnWsTransactionRequest.setDemandTypeColumnValue(
					demandTypeColumnValue.isEmpty() ? 0 : Integer.parseInt(demandTypeColumnValue));
		}
		request.setGtnWsTransactionRequest(gtnWsTransactionRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
						+ GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETVIEWRESULTS_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		Object[] resultArray = response.getGtnWsTransactionResponse().getViewResults();

		setValuesToComponents(componentList, resultArray);

		gtnLogger.info("--------Ending loadDataFromService----------");

	}

	private List<GtnUIFrameworkComponentConfig> addComponentsForView(
			List<GtnWSTransactionColumnBean> viewModeComponents, String layoutName) {
		List<GtnUIFrameworkComponentConfig> componentConfigList = new ArrayList<>();

		for (GtnWSTransactionColumnBean component : viewModeComponents) {
			if (component != null && component.isViewFlag()) {
				if (GtnTransactionUIConstants.JSON_DATEFIELD.equals(component.getComponentType())) {

					gtnLogger.info("----date---" + component.getColumnName());
					addDateField(componentConfigList, component, layoutName);
				} else {
					addTextField(componentConfigList, component, layoutName);

				}
			}

		}
		return componentConfigList;
	}

	private List<GtnWSTransactionColumnBean> getSortedColumnBean(List<GtnWSTransactionColumnBean> viewModeComponents) {
		GtnWSTransactionColumnBean[] viewFieldArray = new GtnWSTransactionColumnBean[viewModeComponents.size()];
		for (GtnWSTransactionColumnBean listViewComponent : viewModeComponents) {
			viewFieldArray[listViewComponent.getViewModeIndex() - 1] = listViewComponent;
		}
		return Arrays.asList(viewFieldArray);
	}

	private List<GtnWSTransactionColumnBean> getSortedOrderColumnBean(
			List<GtnWSTransactionColumnBean> viewModeComponents) {
		GtnWSTransactionColumnBean[] viewFieldArray = new GtnWSTransactionColumnBean[viewModeComponents.size()];
		for (GtnWSTransactionColumnBean listViewComponent : viewModeComponents) {
			viewFieldArray[listViewComponent.getViewModeOrder() - 1] = listViewComponent;
		}
		return Arrays.asList(viewFieldArray);
	}

	private void addTextField(List<GtnUIFrameworkComponentConfig> componentList, GtnWSTransactionColumnBean component,
			String layoutName) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(layoutName + component.getColumnID());
		gtnLayout.setParentComponentId(layoutName);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkTextBoxConfig gtnUIFrameworkTextBoxConfig = new GtnUIFrameworkTextBoxConfig();
		gtnUIFrameworkTextBoxConfig.setEnable(false);

		GtnUIFrameworkComponentConfig companyIdConfig = new GtnUIFrameworkComponentConfig();
		companyIdConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentId(GtnTransactionUIConstants.TRANSACTION_VIEW + component.getColumnID());
		companyIdConfig.setComponentName(component.getColumnName());
		companyIdConfig.setParentComponentId("transactionViewLayout" + component.getColumnID());
		companyIdConfig.setAddToParent(true);
		companyIdConfig.setGtnTextBoxConfig(gtnUIFrameworkTextBoxConfig);
		componentList.add(companyIdConfig);

	}

	private void addDateField(List<GtnUIFrameworkComponentConfig> componentList, GtnWSTransactionColumnBean component,
			String layoutName) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(layoutName + component.getColumnID());
		gtnLayout.setParentComponentId(layoutName);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		companyStatus.setComponentId(GtnTransactionUIConstants.TRANSACTION_VIEW + component.getColumnID());
		companyStatus.setComponentName(component.getColumnName());
		companyStatus.setParentComponentId(layoutName + component.getColumnID());
		companyStatus.setAddToParent(true);
		companyStatus.setEnable(false);
		componentList.add(companyStatus);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private Date parseDate(String d) {

		if (d != null) {
			for (String parse : GtnTransactionUIConstants.getDateFormat()) {
				SimpleDateFormat sdf = new SimpleDateFormat(parse);
				try {
					sdf.setLenient(false);
					return sdf.parse(d);
				} catch (ParseException e) {
					continue;
				}
			}
		}
		return null;
	}

	private String getTableName(String tableName) {
		String wsViewName = "";
		if (tableName.contains("Ivld")) {
			wsViewName = "VwIvldDemandForecastActual";
		} else {
			wsViewName = "VwDemandForecastActual";
		}
		return wsViewName;
	}

	private List<Object> getComponentList(String forecastTypeCaption,
			List<GtnWSTransactionColumnBean> viewModeOrderComponents,
			List<GtnWSTransactionColumnBean> viewModeComponents, String layoutName,
			List<Object> viewModeOrderColumnList, List<Object> viewModeColumnList) throws GtnFrameworkGeneralException {
		List<Object> componentList;

		if (GtnTransactionUIConstants.ACTUALS.equals(forecastTypeCaption)) {
			List<GtnWSTransactionColumnBean> viewModeOrderComp = getSortedOrderColumnBean(viewModeOrderComponents);
			List<GtnUIFrameworkComponentConfig> componentConfigListView = addComponentsForView(viewModeOrderComp,
					layoutName);
			GtnUIFrameworkGlobalUI.addChildComponent(layoutName, componentConfigListView);
			componentList = new ArrayList<>(viewModeOrderColumnList);
		} else {
			List<GtnWSTransactionColumnBean> viewModeOrderComp = getSortedColumnBean(viewModeComponents);
			List<GtnUIFrameworkComponentConfig> componentConfigListView = addComponentsForView(viewModeOrderComp,
					layoutName);
			GtnUIFrameworkGlobalUI.addChildComponent(layoutName, componentConfigListView);
			componentList = new ArrayList<>(viewModeColumnList);
		}

		return componentList;
	}

	private int getSystemId(boolean isInvalid, String componentId, List<Object> actionParamList) {
		int systemId;
		if (isInvalid) {
			GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
					.getVaadinFieldFactoryComponentData(componentId);
			GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
			GtnWsRecordBean recordBean = actionParam.getItemId();
			systemId = (Integer) recordBean.getPropertyValueByIndex(recordBean.getProperties().size() - 1);
		} else {
			GtnWsRecordBean extListDto = (GtnWsRecordBean) actionParamList.get(actionParamList.size() - 1);

			systemId = (Integer) extListDto.getPropertyValueByIndex(extListDto.getProperties().size() - 1);
		}

		return systemId;
	}

	private void setValuesToComponents(List<Object> componentList, Object[] resultArray)
			throws GtnFrameworkGeneralException {

		Object value = null;

		for (int i = 0; i < componentList.size(); i++) {
			if (componentList.get(i) != null && resultArray[i] != null) {
				value = getValuesForComponent(i,componentList,resultArray);

				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								GtnTransactionUIConstants.TRANSACTION_VIEW + String.valueOf(componentList.get(i)))
						.loadDateValue(value);
			}
		}

	}

	private Object getValuesForComponent(int i,List<Object> componentList, Object[] resultArray)
			throws GtnFrameworkGeneralException {
            Object componentId = componentList.get(i);
            Object componentValue =  resultArray[i];
		Object value = null;
		try {
			List<String> dateColumn = Arrays.asList("firstReturn", "lastReturn", "origSaleMonth", "maxExpiredMonth",
					"maxExpiredMonsPluscutoff");
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			boolean isDate = (String.valueOf(componentId).contains("Date")
					|| dateColumn.contains(String.valueOf(componentId))) && componentValue instanceof java.lang.String;
			
                        if (isDate) {
				value = sdf1.parse(sdf1.format(parseDate(String.valueOf(componentValue))));
			} else if (String.valueOf(componentId).equals("baselineAmp") && String.valueOf(componentId) != null) {
				value = "$" + new BigDecimal(String.valueOf(componentValue)).setScale(6, BigDecimal.ROUND_DOWN).toString();
			}
                        else if (String.valueOf(componentId).equals("baseCpi") && String.valueOf(componentId) != null) {
				value = new BigDecimal(String.valueOf(componentValue)).setScale(3, BigDecimal.ROUND_DOWN).toString();
			} 
                        else if (String.valueOf(componentId).equals("itemPrice") && String.valueOf(componentId)!= null) {
                            
                        value = "$" + callDecimalFormatForItemPrice(componentValue,String.valueOf(resultArray[4]));
			} 
                        else {
				value = componentValue instanceof java.lang.Long ? new Date((Long) componentValue)
						: String.valueOf(componentValue);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error in loadDataFromService", e);
		}
		return value;
	}
        
        private Object callDecimalFormatForItemPrice(Object componentValue,String qualifierName){
            
            try {
                
                 Object value = null;
                if("AMP".equalsIgnoreCase(qualifierName) || "BP".equalsIgnoreCase(qualifierName)){
                
                    value = new BigDecimal(String.valueOf(componentValue)).setScale(6, BigDecimal.ROUND_DOWN).toString();

                }
                else if("CPIURA".equalsIgnoreCase(qualifierName) || "CPI (Alt) URA".equalsIgnoreCase(qualifierName)){
                
                    value = new BigDecimal(String.valueOf(componentValue)).setScale(3, BigDecimal.ROUND_DOWN).toString();

                }
                else if("URA".equalsIgnoreCase(qualifierName)){
                
                    value = new BigDecimal(String.valueOf(componentValue)).setScale(4, BigDecimal.ROUND_DOWN).toString();

                }
                else{
                	
                	value = Double.parseDouble(String.valueOf(componentValue));
                	
                }
                
               return value;
               
            } catch (Exception ex) {
               gtnLogger.error("Exception in getValuesForComponent() method",ex);
            }
            
            return null;
            
        }

}
