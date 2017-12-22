/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author lokeshwari
 */
public class ChannelSPRChart1 {
    /**
     * The annual.
     */
    public static final String annual = "Annual";
    /**
     * The semi annual.
     */
    public static final String semiAnnual = "semi-Annual";
    /**
     * The month.
     */
    public static final String month = "monthly";
    /**
     * The quarter.
     */
    public static final String quarter = "quarterly";
    /**
     * The dto.
     */
    private List<SalesProjectionResultsDTO> dto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    private static String history;

    protected CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SPRChart.class);
    protected ProjectionSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public ChannelSPRChart1(final List<SalesProjectionResultsDTO> dto, CustomTableHeaderDTO rightDto, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("Entering SPRChart method ");
        this.frequency = projSelDTO.getFrequency();
        ChannelSPRChart1.history = projSelDTO.getHistory();
        this.dto = dto;
        this.rightDto = rightDto;
        this.projSelDTO = projSelDTO;
        LOGGER.debug("End of SPRChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        LOGGER.debug("Entering getChart method ");
        final Chart chart = new Chart(ChartType.COLUMN);
        List<String> visibleColumns = new ArrayList<>(CommonUtils.objectListToStringList(rightDto.getSingleColumns()));
        visibleColumns.remove("levelValue");
        List<String> visibleHeaders = new ArrayList<>(rightDto.getSingleHeaders());
        visibleHeaders.remove("Level Name");
        String[] visHeaders = visibleHeaders.toArray(new String[visibleHeaders.size()]);
        final Configuration conf = chart.getConfiguration();

        conf.setTitle("Sales Allocation Results");
        conf.setSubTitle(projSelDTO.getActualsOrProjections());
        conf.disableCredits();
        conf.setExporting(Boolean.TRUE);

        final XAxis xAxis = new XAxis();
    
        final YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle("Values");
        conf.addyAxis(yAxis);

        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(NumericConstants.HUNDRED);
        legend.setY(NumericConstants.NINETY);
        legend.setFloating(true);
        legend.setShadow(true);

        conf.setLegend(legend);

        final Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+'$ '+ this.y ");

        conf.setTooltip(tooltip);

        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(NumericConstants.DOUBLE_ZERO_TWO);
        plot.setBorderWidth(0);
        if (projSelDTO.getPivotView().equals(Constant.PERIOD)) {

            xAxis.setCategories(visHeaders);
      
           
            if (dto != null && dto.size() > 0) {
                for (SalesProjectionResultsDTO pDto : dto) {
                    ListSeries listSeries = new ListSeries();
                    listSeries.setName(pDto.getLevelValue());
                    for (Object header : visibleColumns) {

                        String value = String.valueOf(pDto.getPropertyValue(header));
                        listSeries.addData(((pDto.getPropertyValue(header) != null) && !(pDto.getPropertyValue(header).toString().equals("..."))) ? 
                                Double.valueOf(value.replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) : 0);
                    }
                    conf.addSeries(listSeries);
                }
            }
        } else {
            List<String> periodList = new ArrayList<>();
            for (SalesProjectionResultsDTO pDto : dto) {
                if (projSelDTO.getPeriodListMap().containsValue(pDto.getLevelValue())) {
                    periodList.add(pDto.getLevelValue());
                }
            }
            String[] visheader = CommonUtils.stringListToStringArray(periodList);
            xAxis.setCategories(visheader);
            int i = 0;
            for (Object header : visibleColumns) {
                ListSeries listSeries = new ListSeries();
                listSeries.setName(visibleHeaders.get(i));
                for (SalesProjectionResultsDTO pDto : dto) {
                    if (periodList.contains(pDto.getLevelValue())) {
                        Object value = pDto.getPropertyValue(header);
                        if (value != null) {
                            if (!value.toString().equals("...")) {
                                listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
                            } else {
                                listSeries.addData(0);
                            }
                        } else {
                            listSeries.addData(0);
                        }
                    }
                }
                conf.addSeries(listSeries);
                i++;
            }
        }
        conf.addxAxis(xAxis);
        chart.drawChart(conf);
        LOGGER.debug("End of getChart method ");
        return chart;
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<SalesProjectionResultsDTO> getDto() {
        return dto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<SalesProjectionResultsDTO> dto) {
        this.dto = dto;
    }

    /**
     * Gets the frequency.
     *
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency.
     *
     * @param frequency the new frequency
     */
    public void setFrequency(final String frequency) {
        this.frequency = frequency;
    }

    /**
     * Gets the history.
     *
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * Sets the history.
     *
     * @param history the new history
     */
    public void setHistory(final String history) {
        ChannelSPRChart1.history = history;
    }
}
