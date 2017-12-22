/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
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
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PVChart extends Window {

    /**
     * The annual.
     */
    protected static String annual = "Annual";
    /**
     * The semi annual.
     */
    protected static String semiAnnual = "semi-Annual";
    /**
     * The month.
     */
    protected static String month = "monthly";
    /**
     * The quarter.
     */
    protected static String quarter = "quarterly";
    /**
     * The dto.
     */
    private List<ProjectionVarianceDTO> dto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    protected String history;

    protected CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PPAChart.class);
    /**
     * projSelDTO;
     */
    protected PVSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public PVChart(final List<ProjectionVarianceDTO> dto, final String frequency, final String history, CustomTableHeaderDTO rightDto, PVSelectionDTO projSelDTO) {
        LOGGER.debug("Entering PPAChart method ");
        this.frequency = frequency;
        this.history = history;
        this.dto = dto;
        this.rightDto = rightDto;
        this.projSelDTO = projSelDTO;
        setContent(getChart());
        LOGGER.debug("End of PPAChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        LOGGER.debug("Entering getChart method ");
        try {
            final Chart chart = new Chart(ChartType.COLUMN);

            List<String> visibleColumns = new ArrayList<>(CommonUtils.objectListToStringList(rightDto.getSingleColumns()));
            visibleColumns.remove("relationshipLevelName");
            List<String> visibleHeaders = new ArrayList<>(rightDto.getSingleHeaders());
            visibleHeaders.remove(Constant.CUSTOMER_SMALL);
            visibleHeaders.remove(Constant.GROUPFCAPS);
            String[] visHeaders = visibleHeaders.toArray(new String[visibleHeaders.size()]);
            final Configuration conf = chart.getConfiguration();

            conf.setTitle("Projection Variance");
            conf.setSubTitle(Constant.PROJECTIONS);
            conf.disableCredits();
            conf.setExporting(Boolean.TRUE);

            final XAxis xAxis = new XAxis();
            final YAxis yAxis = new YAxis();
            yAxis.setMin(0);
            yAxis.setTitle("Discount Dollar");
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
            plot.setPointPadding(NumericConstants.DOUBLE_ZERO_TWO);
            plot.setBorderWidth(0);

            if (projSelDTO.getPivotView().equals(Constant.PERIOD)) {
                xAxis.setCategories(visHeaders);
                if (dto != null && !dto.isEmpty()) {
                    for (ProjectionVarianceDTO pDto : dto) {
                        ListSeries listSeries = new ListSeries();
                        listSeries.setName(pDto.getGroup());

                        for (Object header : visibleColumns) {
                            Object value = pDto.getPropertyValue(header);
                            if (value != null) {
                                if (!(pDto.getPropertyValue(header).toString().equals(DASH))
                                        && !StringUtils.EMPTY.equals(pDto.getPropertyValue(header).toString()) && !"-".equals(pDto.getPropertyValue(header).toString())) {
                                    listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
                                } else {
                                    listSeries.addData(0);
                                }
                            } else {
                                listSeries.addData(0);
                            }
                        }
                        conf.addSeries(listSeries);

                    }
                }
            } else {
                List<String> periodList = new ArrayList<>();
                for (ProjectionVarianceDTO pDto : dto) {
                    if (projSelDTO.getPeriodListMap().containsValue(pDto.getGroup())) {
                        periodList.add(pDto.getGroup());
                    }
                }
                String[] visheader = CommonUtils.stringListToStringArray(periodList);
                xAxis.setCategories(visheader);
                for (Object header : visibleColumns) {
                    ListSeries listSeries = new ListSeries();
                    listSeries.setName(header.toString());
                    for (ProjectionVarianceDTO pDto : dto) {
                        if (periodList.contains(pDto.getGroup())) {
                            Object value = pDto.getPropertyValue(header);
                            if (value != null) {
                                if (!value.toString().equals("-")) {
                                    listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", DASH)));
                                } else {
                                    listSeries.addData(0);
                                }
                            } else {
                                listSeries.addData(0);
                            }
                        }
                    }

                    conf.addSeries(listSeries);
                }

            }
            conf.addxAxis(xAxis);

            chart.drawChart(conf);

            LOGGER.debug("End of getChart method ");
            return chart;
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            return null;
        }
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<ProjectionVarianceDTO> getDto() {
        return dto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<ProjectionVarianceDTO> dto) {
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
