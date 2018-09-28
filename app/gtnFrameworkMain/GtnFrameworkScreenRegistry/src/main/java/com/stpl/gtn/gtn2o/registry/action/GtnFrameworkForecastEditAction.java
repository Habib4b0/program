package com.stpl.gtn.gtn2o.registry.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;

public class GtnFrameworkForecastEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastEditAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String gridComponentId = actionParamsList.get(1).toString();

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(gridComponentId, componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		Set<GtnWsRecordBean> rows = componentData.getPagedGrid().getValue();
		GtnWsRecordBean selectedRow = rows.isEmpty() ? null : rows.iterator().next();

		String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		String sessionId = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId"));

		try {
			GtnFrameworkForecastInputBean inputBean = formForecastInputBean(selectedRow, actionParamsList);
			inputBean.setUserId(userId);
			inputBean.setSessionId(sessionId);
		} catch (ParseException ex) {
			Logger.getLogger(GtnFrameworkForecastEditAction.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private GtnFrameworkForecastInputBean formForecastInputBean(GtnWsRecordBean selectedRow,
			List<Object> actionParamsList) throws ParseException {
		GtnFrameworkForecastInputBean inputBean = new GtnFrameworkForecastInputBean();
		try {
			inputBean.setProjectionName((String) selectedRow.getPropertyValueByIndex(0));
			inputBean.setProjectionDescription((String) selectedRow.getPropertyValueByIndex(1));
			inputBean.setCustomerHierarchyName((String) selectedRow.getPropertyValueByIndex(2));
			inputBean.setCustomerHierarchyLevel((int) selectedRow.getPropertyValueByIndex(3));
			inputBean.setProductHierarchyName((String) selectedRow.getPropertyValueByIndex(4));
			inputBean.setProductHierarchyLevel((int) selectedRow.getPropertyValueByIndex(5));
			inputBean.setCreatedBy(String.valueOf(selectedRow.getPropertyValueByIndex(6)));
			inputBean.setCompanyName((String) selectedRow.getPropertyValueByIndex(9));
			inputBean.setBusinessUnitName((String) selectedRow.getPropertyValueByIndex(10));
			inputBean.setCustomViewMasterSid((int) selectedRow.getPropertyValueByIndex(11));
			inputBean.setProjectionMasterSid((int) selectedRow.getPropertyValueByIndex(12));
			inputBean.setCustomerHierarchySid((int) selectedRow.getPropertyValueByIndex(13));
			inputBean.setProductHierarchySid((int) selectedRow.getPropertyValueByIndex(14));
			inputBean.setCompany((int) selectedRow.getPropertyValueByIndex(15));
			int customerGroupSid = selectedRow.getPropertyValueByIndex(16) != null
					? selectedRow.getIntegerPropertyByIndex(16) : 0;
			int productGroupSid = selectedRow.getPropertyValueByIndex(18) != null
					? selectedRow.getIntegerPropertyByIndex(18) : 0;
			String customerGroupName = selectedRow.getPropertyValueByIndex(17) != null
					? selectedRow.getStringPropertyByIndex(17) : "";
			String productGroupName = selectedRow.getPropertyValueByIndex(19) != null
					? selectedRow.getStringPropertyByIndex(19) : "";
			inputBean.setCustomerGroupSid(customerGroupSid);
			inputBean.setCustomerGroupName(customerGroupName);
			inputBean.setProductGroupSid(productGroupSid);
			inputBean.setProductGroupName(productGroupName);
			inputBean.setCustomerRelationLevel((int) selectedRow.getPropertyValueByIndex(20));
			inputBean.setProductRelationLevel((int) selectedRow.getPropertyValueByIndex(21));
			inputBean.setCustomerRelationSid((int) selectedRow.getPropertyValueByIndex(22));
			inputBean.setProductRelationSid((int) selectedRow.getPropertyValueByIndex(23));
			inputBean.setDiscountType((int) selectedRow.getPropertyValueByIndex(24));
			inputBean.setBusinessUnit((int) selectedRow.getPropertyValueByIndex(25));
			inputBean.setFromPeriod((String) selectedRow.getPropertyValueByIndex(26));
			inputBean.setToPeriod((String) selectedRow.getPropertyValueByIndex(27));
			inputBean.setCustomerRelationVersionNo((int) selectedRow.getPropertyValueByIndex(28));
			inputBean.setProductRelationVersionNo((int) selectedRow.getPropertyValueByIndex(29));
			inputBean.setCustomerHierarchyVersion((int) selectedRow.getPropertyValueByIndex(30));
			inputBean.setProductHierarchyVersion((int) selectedRow.getPropertyValueByIndex(31));
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date forecastEligibleDate = dateFormatter.parse((String) selectedRow.getPropertyValueByIndex(32));
			inputBean.setForecastEligibleDate(forecastEligibleDate);
			inputBean.setDeductionRelationVersion((int) selectedRow.getPropertyValueByIndex(33));
			inputBean.setDeductionLevel(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(3).toString()).getCaptionFromV8ComboBox());
			inputBean.setDeductionValue(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(3).toString()).getStringCaptionFromV8ComboBox());
			inputBean.setModeOption("Search");
			inputBean.setFrequency(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(4).toString())
					.getStringCaptionFromV8ComboBox());

		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return inputBean;
	}

}
