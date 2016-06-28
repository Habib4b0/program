/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import com.stpl.app.galforecasting.dto.LivesDTO;
import com.stpl.app.galforecasting.logic.SalesProjectionLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import static com.stpl.app.galforecasting.utils.Constant.STRING_EMPTY;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Maheshj
 */
public class TotalLivesChart extends Window {

    public static String quarter = "quarterly";
    /**
     * The dto.
     */
    private List<LivesDTO> dto;
    /**
     * The unit dto.
     */
    private List<LivesDTO> unitDto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    public String history;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TotalLivesChart.class);

    private SessionDTO session;

    /**
     * The Constructor.
     *
     * @param salesDto the sales dto
     * @param unitDto the unit dto
     * @param frequency the frequency
     */
    public TotalLivesChart(SessionDTO dto) {
        super("Total Lives");
        this.session = dto;
        setClosable(true);
        setModal(true);
        center();
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);

        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(getChart());
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public HorizontalLayout getChart() {
        LOGGER.info("Entering getChart method ");
        final HorizontalLayout layout = new HorizontalLayout();
        final Chart chart = new Chart(ChartType.COLUMN);

        final Configuration conf = chart.getConfiguration();

        conf.setTitle(" Total Lives ");
        conf.setSubTitle(Constant.PROJECTIONS);
        conf.disableCredits();
        conf.setExporting(Boolean.TRUE);

        final XAxis xAxis = new XAxis();
        final YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(Constant.LIVES);
        conf.addyAxis(yAxis);

        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(100);
        legend.setY(70);
        legend.setFloating(true);
        legend.setShadow(true);
        conf.setLegend(legend);

        final Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+' '+ this.y ");
        conf.setTooltip(tooltip);

        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);

        List<Double> data = new ArrayList<Double>();
        Map<Integer, Double> values = new HashMap<Integer, Double>();
        ListSeries listSeries = null;

        Map<String, Map<Integer, Double>> finalMap = new HashMap<String, Map<Integer, Double>>();
        SalesProjectionLogic logic = new SalesProjectionLogic();
        Object[] inputs = new Object[3];
        inputs[0] = session.getProjectionId();
        inputs[1] = STRING_EMPTY;
        inputs[2] = session;
        try {
            finalMap = logic.getLivesForSelectedCustomers(inputs);
            List<String> xSet = new ArrayList<String>();
            Set<String> treeSet = new TreeSet<String>();
            int startPeriod = Integer.parseInt(session.getForecastDTO().getHistoryStartYear() + StringUtils.EMPTY + logic.getQuator(session.getForecastDTO().getHistoryStartMonth()));
            int endPeriod = Integer.parseInt(session.getForecastDTO().getForecastEndYear() + StringUtils.EMPTY + logic.getQuator(session.getForecastDTO().getForecastEndMonth()));
            int tempNum = 0;
            int startYear = session.getForecastDTO().getHistoryStartYear();
            int startQuator = logic.getQuator(session.getForecastDTO().getHistoryStartMonth());
            List<Integer> allKeyList = new ArrayList<Integer>();
            while (tempNum != endPeriod) {
                tempNum = Integer.parseInt(startYear + StringUtils.EMPTY + startQuator);
                allKeyList.add(tempNum);
                if (startQuator == 4) {
                    startQuator = 1;
                    startYear = startYear + 1;
                } else {
                    startQuator = startQuator + 1;
                }

            }

            for (String companyKey : finalMap.keySet()) {

                values = finalMap.get(companyKey);
                values.putAll(values);
                listSeries = new ListSeries();
                List<Integer> keyList = new ArrayList<Integer>();
                for (Integer key : values.keySet()) {
                    keyList.add(key);

                }
                Collections.sort(keyList);
                Collections.sort(allKeyList);
                Double lastValue = 0.0;

                for (Integer key : allKeyList) {

                    if (values.containsKey(key)) {
                        lastValue = values.get(key);
                        listSeries.addData(lastValue);
                    } else {

                        if (key < endPeriod) {
                            listSeries.addData(lastValue);
                        } else {
                            listSeries.addData(0.0);
                        }

                    }
                    xSet.add(Constant.Q + String.valueOf(key).charAt(4) + " " + String.valueOf(key).substring(0, 4));
                    treeSet.add(Constant.Q + String.valueOf(key).charAt(4) + " " + String.valueOf(key).substring(0, 4));
                }

                listSeries.setName(companyKey);
                conf.addSeries(listSeries);

            }

            xAxis.setCategories(Arrays.copyOf(xSet.toArray(), xSet.toArray().length, String[].class));
            conf.addxAxis(xAxis);

            chart.drawChart(conf);

            chart.setWidth(100, UNITS_PERCENTAGE);
            chart.setHeight(100, UNITS_PERCENTAGE);

            layout.setHeight(100, UNITS_PERCENTAGE);
            layout.setWidth(100, UNITS_PERCENTAGE);
            layout.addComponent(chart);

        } catch (Exception e) {

            LOGGER.error(e);
        }
        LOGGER.info("End of getChart method ");
        return layout;
    }

}
