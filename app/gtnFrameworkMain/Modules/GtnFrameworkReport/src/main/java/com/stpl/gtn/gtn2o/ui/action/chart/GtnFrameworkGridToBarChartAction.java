package com.stpl.gtn.gtn2o.ui.action.chart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.stpl.addons.vaadin.chartjs.ChartJs;
import com.stpl.addons.vaadin.chartjs.config.BarChartConfig;
import com.stpl.addons.vaadin.chartjs.data.BarDataset;
import com.stpl.addons.vaadin.chartjs.utils.ColorUtils;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.chart.GtnUIFrameworkBarChart;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.TreeData;

public class GtnFrameworkGridToBarChartAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkBarChart.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		ChartJs barChart = (ChartJs) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		String gridId = String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1));
		String parentViewId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
		PagedTreeGrid treePagedGrid = ((PagedTreeGrid) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gridId, parentViewId).getComponentData().getCustomData());

		BarChartConfig chartConfig = getBarChartConfigBasedOnGrid(treePagedGrid);
		barChart.configure(chartConfig);
	}

	private BarChartConfig getBarChartConfigBasedOnGrid(PagedTreeGrid dataGrid) {
		Map<String, String[]> headerToIdMap = new LinkedHashMap<>();
		headerToIdMap.putAll(getRightColumnString(dataGrid));
		int start = dataGrid.getColumnPageNumber() == 1 ? 0
				: dataGrid.getColumnPageNumber() * dataGrid.getColumnsPerPage();
		int end = start + dataGrid.getColumnsPerPage();
		List<Map.Entry<String, String[]>> availableColumns = headerToIdMap.entrySet().stream().skip(start)
				.limit(end - 1L).collect(Collectors.toList());
		LOGGER.info(availableColumns.toString());
		TreeData<GtnWsRecordBean> treeData = dataGrid.getGrid().getTreeData();
		List<GtnWsRecordBean> dataInGrid = new ArrayList<>();
		getAllDataInTreeGrid(treeData, null, dataInGrid);
		return buildBarCharConfig(availableColumns, dataInGrid);
	}

	private BarChartConfig buildBarCharConfig(List<Entry<String, String[]>> availableColumns,
			List<GtnWsRecordBean> dataInGrid) {
		Pattern p = Pattern.compile(".*[0-9].*");
		BarChartConfig barConfig = new BarChartConfig();
		List<String[]> header = availableColumns.stream().map(Entry::getValue).collect(Collectors.toList());
		barConfig.data().labelsAsStringArray(header);
		barConfig.options().maintainAspectRatio(true).responsive(true);

		for (GtnWsRecordBean dataInGridTemp : dataInGrid) {
			String ccpName = dataInGridTemp.getStringProperty("levelValue");
			if (!"levelValue".equals(ccpName)) {
				boolean isNeedToBeAdded = false;
				BarDataset dataset = new BarDataset().backgroundColor(ColorUtils.randomColor(0.7));

				List<Double> dataSet = new ArrayList<>();
				for (Entry<String, String[]> temp : availableColumns) {
					String dataForColumnId = dataInGridTemp.getStringProperty(temp.getKey());
					if (p.matcher(dataForColumnId).matches()) {
						isNeedToBeAdded = true;
						String perCentRemovedIf = dataForColumnId.replace("%", "");
						String dollarRemoved = perCentRemovedIf.replace("$", "");
						dataSet.add(Double.valueOf(dollarRemoved));
					} else {
						dataSet.add(0d);
					}
				}
				dataset.dataAsList(dataSet);
				dataset.label(ccpName);

				if (isNeedToBeAdded) {
					barConfig.data().addDataset(dataset);
				}
			}
		}
		return barConfig;
	}

	private void getAllDataInTreeGrid(TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean parent,
			List<GtnWsRecordBean> dataInGrid) {
		if (treeData.getChildren(parent) == null) {
			return;
		}
		for (GtnWsRecordBean temp : treeData.getChildren(parent)) {
			dataInGrid.add(temp);
			getAllDataInTreeGrid(treeData, temp, dataInGrid);
		}

	}

	private Map<String, String[]> getRightColumnString(PagedTreeGrid dataGrid) {

		Map<String, String[]> headerToIdMap = new LinkedHashMap<>();

		Iterator<String> thirdHeaderIterator = dataGrid.getTableConfig().getRightTableTripleVisibleHeaders().iterator();
		Iterator<String> secondHeaderIterator = dataGrid.getTableConfig().getRightTableDoubleVisibleHeaders()
				.iterator();
		Iterator<String> firstHeaderIterator = Arrays.asList(dataGrid.getTableConfig().getRightTableVisibleHeader())
				.iterator();

		for (Object thirdColumnId : dataGrid.getTableConfig().getRightTableTripleHeaderVisibleColumns()) {
			String thirdHeader = thirdHeaderIterator.next();
			for (Object secondColumniD : dataGrid.getTableConfig().getRightTableTripleHeaderMap().get(thirdColumnId)) {
				String secondHeader = secondHeaderIterator.next();
				for (Object singleColumnId : dataGrid.getTableConfig().getRightTableDoubleHeaderMap()
						.get(secondColumniD)) {
					headerToIdMap.put(String.valueOf(singleColumnId),
							new String[] { thirdHeader, secondHeader, firstHeaderIterator.next() });
				}
			}
		}

		return headerToIdMap;
	}

}
