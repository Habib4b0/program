package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkResetRelationValuesAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;

public class GtnFrameworkReturnProjectionTabGenerateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private List<String> checkedPeriods = null;

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkResetRelationValuesAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" info GtnFrameworkReturnProjectionTabGenerateAction ");

		checkedPeriods = new ArrayList<>();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		List<Object> list = gtnUIFrameWorkActionConfig.getActionParameterList();

		ComboBox forecastTabStartPeriod = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent(String.valueOf(list.get(1)), componentId);
		ComboBox forecastTabEndPeriod = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent(String.valueOf(list.get(2)), componentId);
		ComboBox massupdateStartPeriod = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent(String.valueOf(list.get(8)), componentId);
		ComboBox massupdateEndPeriod = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(list.get(9)),
				componentId);
		ComboBox levelFilter = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(list.get(10)),
				componentId);

		forecastTabStartPeriod.removeAllItems();
		forecastTabEndPeriod.removeAllItems();

		massupdateStartPeriod.removeAllItems();
		massupdateEndPeriod.removeAllItems();

		levelFilter.select(null);

		GtnWsForecastRequest requestForRightHeader = new GtnWsForecastRequest();
		this.setData(requestForRightHeader, gtnUIFrameWorkActionConfig, componentId);
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(list.get(7)),
				componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();

		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);

		requestForRightHeader.getGtnForecastBean().setFrequency(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(list.get(3)), componentId).getCaptionFromComboBox());
		requestForRightHeader.getGtnForecastBean().setActualOrProjection(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(list.get(4)), componentId).getStringFromField());
		requestForRightHeader.getGtnForecastBean().setSelectedHistory(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(list.get(5)), componentId).getStringFromField());
		requestForRightHeader.getGtnForecastBean()
				.setAscending(gtnUIFrameWorkActionConfig.getFieldValues().get(0).equals(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(String.valueOf(list.get(6)), componentId).getStringFromField()));
		gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(requestForRightHeader);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse responseForRightHeader = client.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, gtnUIFrameworkWebserviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsPagedTreeTableResponse rightTableHeaders = responseForRightHeader.getGtnWSPagedTreeTableResponse();
		loadPeriodDdlbs(forecastTabStartPeriod, rightTableHeaders.getEditablePeriods());
		loadPeriodDdlbs(forecastTabEndPeriod, rightTableHeaders.getEditablePeriods());
		loadPeriodDdlbs(massupdateStartPeriod, rightTableHeaders.getEditablePeriods());
		loadPeriodDdlbs(massupdateEndPeriod, rightTableHeaders.getEditablePeriods());
		addListeners(resultsTable);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadPeriodDdlbs(ComboBox ddlb, List<String> periods) {
		int periodSize = periods.size();

		ddlb.setNullSelectionAllowed(Boolean.TRUE);
		ddlb.setNullSelectionItemId(0);
		ddlb.addItem(0);
		ddlb.setItemCaption(0, GtnFrameworkForecastConstantCommon.SELECT_ONE);

		for (int i = 0; i < periodSize; i++) {
			ddlb.addItem(i + 1);
			ddlb.setItemCaption(i + 1, periods.get(i));
		}
	}

	private void setData(GtnWsForecastRequest request, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			String componentId) {
		AbstractComponent abstractComponent = (AbstractComponent) GtnUIFrameworkGlobalUI
				.getVaadinComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId);
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = (GtnUIFrameworkComponentData) abstractComponent
				.getData();
		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
		request.setGtnForecastBean(gtnForecastBean);
	}

	protected void configureTableFieldFactory(FreezePagedTreeTable resultsTable) {

		final ExtCustomTable.ColumnCheckListener checkListener = new ExtCustomTable.ColumnCheckListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
				try {
					event.isChecked();

				} catch (Exception ex) {
					gtnLogger.error(ex.getMessage());
				}
			}
		};
		ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
		leftTable.addColumnCheckListener(checkListener);

	}

	private void addListeners(FreezePagedTreeTable resultsTable) {
		final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
		rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
				if (event.isChecked()) {
					checkedPeriods.add(event.getPropertyId().toString());
				} else {
					checkedPeriods.remove(event.getPropertyId().toString());
				}

			}
		});

	}
}
