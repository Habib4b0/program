/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.PMPYRowDto;
import com.stpl.ifs.ui.util.NumericConstants;
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
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class PMPYTradingPartnerHistoryChart.
 *
 * @author lokeshwari
 */
public class PMPYTradingPartnerHistoryChart {

    /**
     * The dto.
     */
    private final List<PMPYRowDto> dto;

    /**
     * The chart.
     */
    protected Chart chart = new Chart(ChartType.COLUMN);
    /**
     * The conf.
     */
    protected Configuration conf = chart.getConfiguration();
    /**
     * The chart1.
     */
    protected Chart chart1 = new Chart(ChartType.COLUMN);
    /**
     * The conf1.
     */
    protected Configuration conf1 = chart1.getConfiguration();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PMPYTradingPartnerHistoryChart.class);

    private String tpName = StringUtils.EMPTY;

    protected List<Object> headers = null;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     */
    public PMPYTradingPartnerHistoryChart(final List<PMPYRowDto> dto, String tpName, List<Object> headers) {
        LOGGER.debug("Entering PMPYTradingPartnerHistoryChart method ");
        this.tpName = tpName;
        this.dto = dto;
        this.headers = headers;
        LOGGER.debug("End of PMPYTradingPartnerHistoryChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getCharts() {
        LOGGER.debug("Entering getChart method ");
        final VerticalLayout lay = new VerticalLayout();
        conf.setTitle("PMPY Calculator");
        conf.setSubTitle(" ");
        conf.disableCredits();
        conf.setExporting(Boolean.TRUE);
        final XAxis xAxis = new XAxis();
        final YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(Constant.ACTUAL_SALES);
        conf.addyAxis(yAxis);
        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor(Constant.FF_BG_COLOR);
        legend.setHorizontalAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(NumericConstants.HUNDRED);
        legend.setY(NumericConstants.SEVENTY);
        legend.setFloating(true);
        legend.setShadow(true);
        conf.setLegend(legend);
        final Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+ this.y +' $'");
        conf.setTooltip(tooltip);
        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(NumericConstants.DOUBLE_ZERO_TWO);
        plot.setBorderWidth(0);

        Map<String, String> values = null;
        ListSeries listSeries = new ListSeries(tpName);
        if (dto != null && !dto.isEmpty()) {
            final PMPYRowDto pDto = dto.get(0);
            values = pDto.getProperties();
            int i = 0;

            String temp[] = new String[headers.size()];
            for (Object key : headers) {

                temp[i] = String.valueOf(key);
                i++;
            }

            xAxis.setCategories(temp);
            for (String key : values.keySet()) {
                if (!key.equals("type")) {
                    listSeries.addData(Double.valueOf(values.get(key).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY)));
                }
            }

            conf.addSeries(listSeries);

        }

        conf.addxAxis(xAxis);
        chart.drawChart(conf);

        conf1.setTitle(StringUtils.EMPTY);
        conf1.setSubTitle(StringUtils.EMPTY);
        conf1.disableCredits();
        conf1.setExporting(Boolean.TRUE);
        final XAxis xAxis1 = new XAxis();
        final YAxis yAxis1 = new YAxis();
        yAxis1.setMin(0);
        yAxis1.setTitle(Constant.UNIT_VOLUME);
        conf1.addyAxis(yAxis1);
        final Legend legend1 = new Legend();
        legend1.setLayout(LayoutDirection.VERTICAL);
        legend1.setBackgroundColor(Constant.FF_BG_COLOR);
        legend1.setHorizontalAlign(HorizontalAlign.LEFT);
        legend1.setVerticalAlign(VerticalAlign.TOP);
        legend1.setX(NumericConstants.HUNDRED);
        legend1.setY(NumericConstants.SEVENTY);
        legend1.setFloating(true);
        legend1.setShadow(true);
        conf1.setLegend(legend1);
        final Tooltip tooltip1 = new Tooltip();
        tooltip1.setFormatter("this.x +': '+ this.y +' '");
        conf1.setTooltip(tooltip1);
        final PlotOptionsColumn plot1 = new PlotOptionsColumn();
        plot1.setPointPadding(0.2);
        plot1.setBorderWidth(0);

        Map<String, String> values1 = null;
        ListSeries listSeries1 = new ListSeries(tpName);

        if (dto != null && !dto.isEmpty()) {
            final PMPYRowDto pDto = dto.get(1);
            values1 = pDto.getProperties();
            int i = 0;

            String temp[] = new String[headers.size()];
            for (Object key : headers) {

                temp[i] = String.valueOf(key);
                i++;
            }

            xAxis1.setCategories(temp);
            for (String key : values1.keySet()) {
                if (!key.equals("type")) {
                    listSeries1.addData(Double.valueOf(values1.get(key).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY)));
                }
            }

            conf1.addSeries(listSeries1);

        }

        conf1.addxAxis(xAxis1);

        chart1.drawChart(conf1);

        final Chart chart2 = new Chart(ChartType.COLUMN);

        final Configuration conf2 = chart2.getConfiguration();

        conf2.setTitle(StringUtils.EMPTY);
        conf2.setSubTitle(StringUtils.EMPTY);
        conf2.disableCredits();
        conf2.setExporting(Boolean.TRUE);

        final XAxis xAxis2 = new XAxis();
        final YAxis yAxis2 = new YAxis();
        yAxis2.setMin(0);
        yAxis2.setTitle(Constant.LIVES);
        conf2.addyAxis(yAxis2);

        final Legend legend2 = new Legend();
        legend2.setLayout(LayoutDirection.VERTICAL);
        legend2.setBackgroundColor(Constant.FF_BG_COLOR);
        legend2.setHorizontalAlign(HorizontalAlign.LEFT);
        legend2.setVerticalAlign(VerticalAlign.TOP);
        legend2.setX(NumericConstants.HUNDRED);
        legend2.setY(NumericConstants.SEVENTY);
        legend2.setFloating(true);
        legend2.setShadow(true);
        conf2.setLegend(legend2);

        final Tooltip tooltip2 = new Tooltip();
        tooltip2.setFormatter("this.x +': '+ this.y +' '");
        conf2.setTooltip(tooltip2);

        final PlotOptionsColumn plot2 = new PlotOptionsColumn();
        plot2.setPointPadding(0.2);
        plot2.setBorderWidth(0);

        Map<String, String> values2 = null;
        ListSeries listSeries2 = new ListSeries();
        if (dto != null && !dto.isEmpty()) {
            final PMPYRowDto pDto = dto.get(NumericConstants.TWO);
            values2 = pDto.getProperties();
            int i = 0;
            String temp[] = new String[headers.size()];
            for (Object key : headers) {

                temp[i] = String.valueOf(key);
                i++;
            }

            xAxis2.setCategories(temp);
            for (String key : values2.keySet()) {
                if (!key.equals("type")) {
                    listSeries2.addData(Double.valueOf(values2.get(key).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY)));
                }
            }
            listSeries2.setName(tpName);
            conf2.addSeries(listSeries2);

        }

        conf2.addxAxis(xAxis2);
        chart2.drawChart(conf2);

        lay.addComponent(chart);
        lay.addComponent(chart1);
        lay.addComponent(chart2);

        LOGGER.debug("End of getChart method ");
        return lay;
    }

}
