/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.pparesults.dto.PPAProjectionResultsDTO;
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
import org.asi.ui.headers.TableHeaderDTO;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PPAChart {

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
    private List<PPAProjectionResultsDTO> dto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    public String history;

    ProjectionSelectionDTO selection;
    TableHeaderDTO fullHeader;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PPAChart.class);

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public PPAChart(final List<PPAProjectionResultsDTO> dto, final String frequency, final String history, ProjectionSelectionDTO selection, TableHeaderDTO rightDto) {
        LOGGER.info("Entering PPAChart method ");
        this.frequency = frequency;
        this.history = history;
        this.dto = dto;
        this.selection = selection;
        this.fullHeader = rightDto;
        LOGGER.info("End of PPAChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        LOGGER.info("Entering getChart method ");
        final Chart chart = new Chart(ChartType.COLUMN);
        final List visibleColumns = fullHeader.getSingleColumns();
        visibleColumns.remove(0);
        final Configuration conf = chart.getConfiguration();

        conf.setTitle("PPA Projection Results");
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
        legend.setHorizontalAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(100);
        legend.setY(90);
        legend.setFloating(true);
        legend.setShadow(true);

        conf.setLegend(legend);

        final Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+'$ '+ this.y ");

        conf.setTooltip(tooltip);

        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);

        if (dto != null && dto.size() > 0) {

            if (selection.getPivotView().equals(Constant.PERIOD)) {
                List<String> xcat = new ArrayList<String>();

                for (PPAProjectionResultsDTO pDto : dto) {
                    ListSeries listSeries = new ListSeries();
                    listSeries.setName(pDto.getGroup());
                    for (int i = 0; i < visibleColumns.size(); i++) {
                        xcat.add(String.valueOf(fullHeader.getSingleHeader(visibleColumns.get(i))));
                        if ((pDto.getPropertyValue(visibleColumns.get(i)) != null)) {
                            listSeries.addData(Double.valueOf(pDto.getPropertyValue(visibleColumns.get(i)).toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
                        } else {
                            listSeries.addData(0);
                        }


                    }

                    conf.addSeries(listSeries);

                }
                xAxis.setCategories(xcat.toArray(new String[xcat.size()]));
            } else {
                List<String> xcat = new ArrayList<String>();
                ListSeries discountDollorListSeries = new ListSeries();
                discountDollorListSeries.setName("Discount $ Per Unit");
                ListSeries discountPercentListSeries = new ListSeries();
                discountPercentListSeries.setName(CommonUtils.VAR_DIS_RATE);
                ListSeries unitVolumeListSeries = new ListSeries();
                unitVolumeListSeries.setName(Constant.UNIT_VOLUME);
                ListSeries totalDiscountListSeries = new ListSeries();
                totalDiscountListSeries.setName("Total Discount Amount");
                for (PPAProjectionResultsDTO pDto : dto) {
                    xcat.add(pDto.getGroup());
                    if ((pDto.getDiscountPerUnitProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
                        discountDollorListSeries.addData(Double.valueOf(pDto.getDiscountPerUnitProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
                    } else {
                        discountDollorListSeries.addData(0);
                    }
                    if ((pDto.getDiscountPercentProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
                        discountPercentListSeries.addData(Double.valueOf(pDto.getDiscountPercentProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
                    } else {
                        discountPercentListSeries.addData(0);
                    }
                    if ((pDto.getUnitVolumeProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
                        unitVolumeListSeries.addData(Double.valueOf(pDto.getUnitVolumeProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
                    } else {
                        unitVolumeListSeries.addData(0);
                    }
                    if ((pDto.getTotalDiscountProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
                        totalDiscountListSeries.addData(Double.valueOf(pDto.getTotalDiscountProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
                    } else {
                        totalDiscountListSeries.addData(0);
                    }
                }
                xAxis.setCategories(xcat.toArray(new String[xcat.size()]));
                conf.addSeries(discountDollorListSeries);
                conf.addSeries(discountPercentListSeries);
                conf.addSeries(unitVolumeListSeries);
                conf.addSeries(totalDiscountListSeries);
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
    public List<PPAProjectionResultsDTO> getDto() {
        return dto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<PPAProjectionResultsDTO> dto) {
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
