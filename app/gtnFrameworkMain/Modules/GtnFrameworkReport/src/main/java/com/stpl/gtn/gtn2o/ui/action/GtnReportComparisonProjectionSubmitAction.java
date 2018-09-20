package com.stpl.gtn.gtn2o.ui.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnReportComparisonProjectionSubmitAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportComparisonProjectionSubmitAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			submitAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (ParseException e) {
			logger.error(e + "");
		}
	}

	private void submitAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException, ParseException {
		logger.info("Inside Comparison Projection Submit Action");
		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
				gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId).getComponentData();
		GtnReportComparisonProjectionBean comparisonProjectionBean;
		List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList = new ArrayList<>();
		GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString());
		PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		if (grid.getDataProvider() instanceof ListDataProvider) {
			ListDataProvider<GtnWsRecordBean> selectedTableItems = (ListDataProvider<GtnWsRecordBean>) grid
					.getDataProvider();
			List<GtnWsRecordBean> selectedRecords = (List<GtnWsRecordBean>) selectedTableItems.getItems();
			for (GtnWsRecordBean recordBean : selectedRecords) {
				comparisonProjectionBean = new GtnReportComparisonProjectionBean();
				comparisonProjectionBean
						.setProjectionName(String.valueOf(recordBean.getPropertyValue("projectionName")));
				comparisonProjectionBean
						.setProjectionDescription(String.valueOf(recordBean.getPropertyValue("description")));
				comparisonProjectionBean.setMarketType(String.valueOf(recordBean.getPropertyValue("marketType")));
				comparisonProjectionBean
						.setContractHolder(String.valueOf(recordBean.getPropertyValue("contractHolder")));
				comparisonProjectionBean.setContract(String.valueOf(recordBean.getPropertyValue("contract")));
				comparisonProjectionBean.setBrand(String.valueOf(recordBean.getPropertyValue("brand")));
				comparisonProjectionBean.setItemNo(String.valueOf(recordBean.getPropertyValueByIndex(9)));
				String createdDate = recordBean.getStringPropertyByIndex(6);

				comparisonProjectionBean.setCreatedDate(StringUtils.isBlank(createdDate) ? null
						: new SimpleDateFormat("MM/dd/yyyy").parse(createdDate));

				comparisonProjectionBean.setItemName(String.valueOf(recordBean.getPropertyValueByIndex(10)));
				comparisonProjectionBean
						.setProjectionMasterSid(Integer.parseInt(recordBean.getStringPropertyByIndex(11)));
				comparisonProjectionBean.setUserId(String.valueOf(recordBean.getStringPropertyByIndex(7)));
				comparisonProjectionBean.setCreatedBy(Integer.parseInt(recordBean.getStringPropertyByIndex(8)));
				comparisonProjectionBean.setProjectionType(String.valueOf(recordBean.getAdditionalPropertyByIndex(0)));
				comparisonProjectionBeanList.add(comparisonProjectionBean);
			}
			Collections.sort(comparisonProjectionBeanList, new GtnReportComparisonProjectionBean());
			idComponentData.setCustomData(comparisonProjectionBeanList);
			if (comparisonProjectionBeanList.isEmpty()) {
				validateTableRecords(componentId);
			} else {
				String displayValue = getRecordDisplayValue(selectedRecords);
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(
								gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId)
						.setV8PopupFieldValue(displayValue);

				GtnUIFrameworkActionExecutor.executeSingleAction(componentId,
						(GtnUIFrameWorkActionConfig) gtnUIFrameWorkActionConfig.getActionParameterList().get(3));
			}
		} else {
			validateTableRecords(componentId);
		}
		if (componentId.equals("dashboardComparisonLookup_submitButton")) {
			int initialCapacity = 4 + comparisonProjectionBeanList.size();
			List<String> inputForComparisonBasisList = new ArrayList<>(initialCapacity);
			inputForComparisonBasisList.add("Actuals");
			inputForComparisonBasisList.add("Accruals");
			inputForComparisonBasisList.add("Current Projection");
			comparisonProjectionBeanList.stream().forEach(comparisonProjectionBeans -> inputForComparisonBasisList
					.add(comparisonProjectionBeans.getProjectionName()));
			List idList = IntStream.range(1, initialCapacity).boxed().collect(Collectors.toList());
			GtnUIFrameworkComboBoxConfig comparisonBasisComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent("reportingDashboard_displaySelectionTabComparisonBasis",
							componentId)
					.getComponentConfig().getGtnComboboxConfig();
			comparisonBasisComboboxConfig.setItemCaptionValues(inputForComparisonBasisList);
			comparisonBasisComboboxConfig.setItemValues(idList);

			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponentFromParent("reportingDashboard_displaySelectionTabComparisonBasis", componentId,
					Arrays.asList(""));

		}
	}

	private String getRecordDisplayValue(List<GtnWsRecordBean> selectedRecords) {
		if (selectedRecords.size() > 1) {
			return "MULTIPLE";
		} else {
			return selectedRecords.get(0).getPropertyValue("projectionName").toString();
		}
	}

	private void validateTableRecords(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("Error");
		alertAction.addActionParameter("No Data is available to submit");
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
