/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesProjectionResultsDTO;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.SPRDASH;
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
 * @author Lokeshwari
 */
public class SPRChart {

    /**
     * The annual.
     */
    public static String annual = "Annual";
    /**
     * The semi annual.
     */
    public static String semiAnnual = "semi-Annual";
    /**
     * The month.
     */
    public static String month = "monthly";
    /**
     * The quarter.
     */
    public static String quarter = "quarterly";
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
    public String history;

    CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SPRChart.class);
    ProjectionSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public SPRChart(final List<SalesProjectionResultsDTO> dto, CustomTableHeaderDTO rightDto, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering SPRChart method ");
        this.frequency = projSelDTO.getFrequency();
        this.history = projSelDTO.getHistory();
        this.dto = dto;
        this.rightDto = rightDto;
        this.projSelDTO = projSelDTO;
        LOGGER.info("End of SPRChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        LOGGER.info("Entering getChart method ");
        final Chart chart = new Chart(ChartType.COLUMN);
        List<String> visibleColumns = new ArrayList<String>(CommonUtils.objectListToStringList(rightDto.getSingleColumns()));
        visibleColumns.remove("levelValue");
        List<String> visibleHeaders = new ArrayList<String>(rightDto.getSingleHeaders());
        visibleHeaders.remove("Level Name");
        String[] visHeaders = visibleHeaders.toArray(new String[visibleHeaders.size()]);
        final Configuration conf = chart.getConfiguration();
        conf.setTitle("Sales Projection Results");
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
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);

        legend.setShadow(true);

        conf.setLegend(legend);

        final Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+'$ '+ this.y ");

        conf.setTooltip(tooltip);

        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);
        if (projSelDTO.getPivotView().equals(PERIOD.getConstant())) {
            xAxis.setCategories(visHeaders);
            if (dto != null && dto.size() > 0) {
                for (SalesProjectionResultsDTO pDto : dto) {
                    ListSeries listSeries = new ListSeries();
                    listSeries.setName(pDto.getLevelValue());
                    for (Object header : visibleColumns) {

                        String value = String.valueOf(pDto.getPropertyValue(header));
                        listSeries.addData(((pDto.getPropertyValue(header) != null) && !(pDto.getPropertyValue(header).toString().equals(SPRDASH.getConstant())))
                                ? (Double.valueOf(value.replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY))) : 0);
                    }
                    conf.addSeries(listSeries);
                }
            }
        } else {
            List<String> periodList = new ArrayList<String>();
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
        LOGGER.info("End of getChart method ");
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
        this.history = history;
    }
}
