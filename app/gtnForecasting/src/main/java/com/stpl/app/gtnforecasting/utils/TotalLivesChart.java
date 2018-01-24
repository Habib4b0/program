/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.vaadin.ui.Window;
import com.vaadin.v7.ui.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maheshj
 */
public class TotalLivesChart extends Window {

    public static final String QUARTER = "quarterly";
    /**
     * The dto.
     */
    /**
     * The unit dto.
     */
    /**
     * The frequency.
     */
    /**
     * The history.
     */
    private String history;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TotalLivesChart.class);

    private final SessionDTO session;

    /**
     * The Constructor.
     *
     * @param salesDto the sales dto
     * @param unitDto the unit dto
     * @param frequency the frequency
     */
    public TotalLivesChart(SessionDTO dto) {
//        super("Total Lives");
        this.session = dto;
//        setClosable(true);
//        setModal(true);
//        center();
//        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
//        setHeight(NumericConstants.HUNDRED, Unit.PERCENTAGE);
//
//        addStyleName(Constant.BOOTSTRAP_UI);
//        addStyleName(Constant.BOOTSTRAP);
//        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
//        setContent(getChart());
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public HorizontalLayout getChart() {
//        LOGGER.debug("Entering getChart method ");
        final HorizontalLayout layout = new HorizontalLayout();
//        final Chart chart = new Chart(ChartType.COLUMN);
//
//        final Configuration conf = chart.getConfiguration();
//
//        conf.setTitle(" Total Lives ");
//        conf.setSubTitle(Constant.PROJECTIONS);
//        conf.disableCredits();
//        conf.setExporting(Boolean.TRUE);
//
//        final XAxis xAxis = new XAxis();
//        final YAxis yAxis = new YAxis();
//        yAxis.setMin(0);
//        yAxis.setTitle(Constant.LIVES);
//        conf.addyAxis(yAxis);
//
//        final Legend legend = new Legend();
//        legend.setLayout(LayoutDirection.VERTICAL);
//        legend.setBackgroundColor("#FFFFFF");
//        legend.setHorizontalAlign(HorizontalAlign.LEFT);
//        legend.setVerticalAlign(VerticalAlign.TOP);
//        legend.setX(NumericConstants.HUNDRED);
//        legend.setY(NumericConstants.SEVENTY);
//        legend.setFloating(true);
//        legend.setShadow(true);
//        conf.setLegend(legend);
//
//        final Tooltip tooltip = new Tooltip();
//        tooltip.setFormatter("this.x +': '+' '+ this.y ");
//        conf.setTooltip(tooltip);
//
//        final PlotOptionsColumn plot = new PlotOptionsColumn();
//        plot.setPointPadding(NumericConstants.DOUBLE_ZERO_TWO);
//        plot.setBorderWidth(0);
//
//        Map<Integer, Double> values = new HashMap<>();
//        ListSeries listSeries = null;
//
//        Map<String, Map<Integer, Double>> finalMap = new HashMap<>();
//        SalesProjectionLogic logic = new SalesProjectionLogic();
//        Object[] inputs = new Object[NumericConstants.THREE];
//        inputs[0] = session.getProjectionId();
//        inputs[1] = STRING_EMPTY;
//        inputs[NumericConstants.TWO] = session;
//        try {
//            finalMap = logic.getLivesForSelectedCustomers(inputs);
//            List<String> xSet = new ArrayList<>();
//            Set<String> treeSet = new TreeSet<>();
//            int endPeriod = Integer.parseInt(session.getForecastDTO().getForecastEndYear() + StringUtils.EMPTY + logic.getQuator(session.getForecastDTO().getForecastEndMonth()));
//            int tempNum = 0;
//            int startYear = session.getForecastDTO().getHistoryStartYear();
//            int startQuator = logic.getQuator(session.getForecastDTO().getHistoryStartMonth());
//            List<Integer> allKeyList = new ArrayList<>();
//            while (tempNum != endPeriod) {
//                tempNum = Integer.parseInt(startYear + StringUtils.EMPTY + startQuator);
//                allKeyList.add(tempNum);
//                if (startQuator == NumericConstants.FOUR) {
//                    startQuator = 1;
//                    startYear = startYear + 1;
//                } else {
//                    startQuator = startQuator + 1;
//                }
//
//            }
//
//            for (String companyKey : finalMap.keySet()) {
//
//                values = finalMap.get(companyKey);
//                values.putAll(values);
//                listSeries = new ListSeries();
//                List<Integer> keyList = new ArrayList<>();
//                for (Integer key : values.keySet()) {
//                    keyList.add(key);
//
//                }
//                Collections.sort(keyList);
//                Collections.sort(allKeyList);
//                Double lastValue = 0.0;
//
//                for (Integer key : allKeyList) {
//
//                    if (values.containsKey(key)) {
//                        lastValue = values.get(key);
//                        listSeries.addData(lastValue);
//                    } else {
//
//                        if (key < endPeriod) {
//                            listSeries.addData(lastValue);
//                        } else {
//                            listSeries.addData(0.0);
//                        }
//
//                    }
//                    xSet.add(Constant.Q + String.valueOf(key).charAt(NumericConstants.FOUR) + " " + String.valueOf(key).substring(0, NumericConstants.FOUR));
//                    treeSet.add(Constant.Q + String.valueOf(key).charAt(NumericConstants.FOUR) + " " + String.valueOf(key).substring(0, NumericConstants.FOUR));
//                }
//
//                listSeries.setName(companyKey);
//                conf.addSeries(listSeries);
//
//            }
//
//            xAxis.setCategories(Arrays.copyOf(xSet.toArray(), xSet.toArray().length, String[].class));
//            conf.addxAxis(xAxis);
//
//            chart.drawChart(conf);
//
//            chart.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
//            chart.setHeight(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
//
//            layout.setHeight(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
//            layout.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
//            layout.addComponent(chart);
//
//        } catch (Exception e) {
//
//            LOGGER.error(e.getMessage());
//        }
//        LOGGER.debug("End of getChart method ");
        return layout;
    }

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

}
