package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.vaadin.ui.AbstractComponent;

public class GtnReportingComparisonBreakdownSubmitAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingComparisonBreakdownSubmitAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Submit Action Configure Params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Map<String, Integer> periodAndYearMap;
		try {
			AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(
					"comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent", componentId);

			GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();

			List<Object[]> reportComparisonBreakdownLookupBeanList = (List<Object[]>) gridComponent.getCustomData();

			Set<Object[]> inputSet = new LinkedHashSet<>(reportComparisonBreakdownLookupBeanList);

			List<Object[]> gtnReportComparisonBreakdownLookupBeanList = new ArrayList<>(inputSet);

			List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownLookupBeanSaveList = new ArrayList<>();
			for (int i = 0; i < gtnReportComparisonBreakdownLookupBeanList.size(); i++) {

				periodAndYearMap = getPeriodAndYear(gtnReportComparisonBreakdownLookupBeanList.get(i)[1].toString());
				GtnReportComparisonBreakdownLookupBean comparisonBreakdownLookupBean = new GtnReportComparisonBreakdownLookupBean();
				comparisonBreakdownLookupBean.setMasterSid((int) gtnReportComparisonBreakdownLookupBeanList.get(i)[2]);
				comparisonBreakdownLookupBean.setPeriod(periodAndYearMap.get(GtnFrameworkReportStringConstants.PERIOD));
				comparisonBreakdownLookupBean.setYear(periodAndYearMap.get("year"));
				comparisonBreakdownLookupBean.setSelectedVariable(
						Integer.parseInt((String) gtnReportComparisonBreakdownLookupBeanList.get(i)[0]));
				comparisonBreakdownLookupBean.setRowCount((int) gtnReportComparisonBreakdownLookupBeanList.get(i)[5]);
				comparisonBreakdownLookupBean
						.setProperty(gtnReportComparisonBreakdownLookupBeanList.get(i)[3].toString());
				comparisonBreakdownLookupBean
						.setComponentId(gtnReportComparisonBreakdownLookupBeanList.get(i)[6].toString());
				comparisonBreakdownLookupBean
						.setColumnId(gtnReportComparisonBreakdownLookupBeanList.get(i)[1].toString());
				comparisonBreakdownLookupBeanSaveList.add(comparisonBreakdownLookupBean);
			}
			String sourceParentComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId)
					.getParentViewId();
			String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(sourceParentComponentId)
					.getViewId();
			GtnWsReportDataSelectionBean gtnWsReportDataSelectionBeanSave = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
			gtnWsReportDataSelectionBeanSave.setComparisonBreakdownSaveList(comparisonBreakdownLookupBeanSaveList);
			GtnWsReportRequest gtnWsReportRequestSave = new GtnWsReportRequest();
			gtnWsReportRequestSave.setDataSelectionBean(gtnWsReportDataSelectionBeanSave);
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequestSave = new GtnUIFrameworkWebserviceRequest();
			gtnUIFrameworkWebserviceRequestSave.setGtnWsReportRequest(gtnWsReportRequestSave);
			GtnUIFrameworkWebServiceClient clientSave = new GtnUIFrameworkWebServiceClient();
			clientSave.callGtnWebServiceUrl(GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_SAVE_SERVICE,
					GtnFrameworkReportStringConstants.REPORT, gtnUIFrameworkWebserviceRequestSave,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public static Map<String, Integer> getPeriodAndYear(String s) throws ParseException {
		Map<String, Integer> periodAndYearMap = new HashMap<>();

		if (Pattern.matches("[0-9]{4}", s)) {
			periodAndYearMap.put(GtnFrameworkReportStringConstants.PERIOD, 1);
			periodAndYearMap.put("year", Integer.valueOf(s));
		} else if (s.startsWith("Q") || s.startsWith("S") && !s.startsWith("Sep")) {
			periodAndYearMap.put(GtnFrameworkReportStringConstants.PERIOD,
					Integer.parseInt(String.valueOf(s.charAt(1))));
			periodAndYearMap.put("year", Integer.valueOf(s.substring(3)));
		} else {
			Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(s.substring(0, 3));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			periodAndYearMap.put(GtnFrameworkReportStringConstants.PERIOD, ++month);
			periodAndYearMap.put("year", Integer.valueOf(s.substring(4)));
		}

		return periodAndYearMap;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
