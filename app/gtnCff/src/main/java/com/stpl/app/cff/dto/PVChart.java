/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
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
 * @author mohamed.hameed
 */
public class PVChart extends Window {

    /**
     * The annual.
     */
    protected static final String ANNUAL = "Annual";
    /**
     * The semi annual.
     */
    protected static final String SEMI_ANNUAL = "semi-Annual";
    /**
     * The MONTH.
     */
    protected static final String MONTH = "monthly";
    /**
     * The QUARTER.
     */
    protected static final String QUARTER = "quarterly";
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
    private String history;
    private final CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PVChart.class);
    /**
     * projSelDTO;
     */
    private final PVSelectionDTO projSelDTO;

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
            visibleHeaders.remove(Constants.CommonConstantsForChannels.CUSTOMER);
            visibleHeaders.remove("Group");
            String[] visHeaders = visibleHeaders.toArray(new String[visibleHeaders.size()]);
            final Configuration conf = chart.getConfiguration();
            conf.setTitle("Projection Variance");
            conf.setSubTitle("Projections");
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
            if (projSelDTO.getPivotView().equals("Period")) {
                xAxis.setCategories(visHeaders);
                if (dto != null && !dto.isEmpty()) {
                    for (ProjectionVarianceDTO pDto : dto) {
                        ListSeries listSeries = new ListSeries();
                        listSeries.setName(pDto.getGroup());

                        for (Object header : visibleColumns) {
                            Object value = pDto.getPropertyValue(header);
                            if (value != null) {
                                if (!(pDto.getPropertyValue(header).toString().equals("0"))
                                        && !StringUtils.EMPTY.equals(pDto.getPropertyValue(header).toString()) && !"-".equals(pDto.getPropertyValue(header).toString())) {
                                    listSeries.addData(Double.valueOf(value.toString().replace("$", "").replace("%", "").replace(",", "")));
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
                                    listSeries.addData(Double.valueOf(value.toString().replace("$", "").replace("%", "").replace(",", "").replace("-", "0")));
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
        } catch (Exception e) {
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
