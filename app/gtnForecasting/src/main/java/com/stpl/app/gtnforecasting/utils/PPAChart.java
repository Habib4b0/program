/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.List;
import org.asi.ui.headers.TableHeaderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class PPAChart {

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
    private List<PPAProjectionResultsDTO> dto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    protected String history;

    protected ProjectionSelectionDTO selection;
    protected TableHeaderDTO fullHeader;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PPAChart.class);

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public PPAChart(final List<PPAProjectionResultsDTO> dto, final String frequency, final String history, ProjectionSelectionDTO selection, TableHeaderDTO rightDto) {
        LOGGER.debug("Entering PPAChart method ");
        this.frequency = frequency;
        this.history = history;
        this.dto = dto;
        this.selection = selection;
        this.fullHeader = rightDto;
        LOGGER.debug("End of PPAChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        LOGGER.debug("Entering getChart method ");
//        final Chart chart = new Chart(ChartType.COLUMN);
//        final List visibleColumns = fullHeader.getSingleColumns();
//        visibleColumns.remove(0);
//        final Configuration conf = chart.getConfiguration();
//
//        conf.setTitle("PPA Projection Results");
//        conf.setSubTitle(Constant.PROJECTIONS);
//        conf.disableCredits();
//        conf.setExporting(Boolean.TRUE);
//
//        final XAxis xAxis = new XAxis();
//        final YAxis yAxis = new YAxis();
//        yAxis.setMin(0);
//        yAxis.setTitle("Discount Dollar");
//        conf.addyAxis(yAxis);
//
//        final Legend legend = new Legend();
//        legend.setLayout(LayoutDirection.VERTICAL);
//        legend.setBackgroundColor("#FFFFFF");
//        legend.setHorizontalAlign(HorizontalAlign.LEFT);
//        legend.setVerticalAlign(VerticalAlign.TOP);
//        legend.setX(NumericConstants.HUNDRED);
//        legend.setY(NumericConstants.NINETY);
//        legend.setFloating(true);
//        legend.setShadow(true);
//
//        conf.setLegend(legend);
//
//        final Tooltip tooltip = new Tooltip();
//        tooltip.setFormatter("this.x +': '+'$ '+ this.y ");
//
//        conf.setTooltip(tooltip);
//
//        final PlotOptionsColumn plot = new PlotOptionsColumn();
//        plot.setPointPadding(NumericConstants.DOUBLE_ZERO_TWO);
//        plot.setBorderWidth(0);
//
//        if (dto != null && !dto.isEmpty()) {
//
//            if (selection.getPivotView().equals(Constant.PERIOD)) {
//                List<String> xcat = new ArrayList<>();
//
//                for (PPAProjectionResultsDTO pDto : dto) {
//                    ListSeries listSeries = new ListSeries();
//                    listSeries.setName(pDto.getGroup());
//                    for (int i = 0; i < visibleColumns.size(); i++) {
//                        xcat.add(String.valueOf(fullHeader.getSingleHeader(visibleColumns.get(i))));
//                        if (pDto.getPropertyValue(visibleColumns.get(i)) != null) {
//                            listSeries.addData(Double.valueOf(pDto.getPropertyValue(visibleColumns.get(i)).toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                        } else {
//                            listSeries.addData(0);
//                        }
//
//
//                    }
//
//                    conf.addSeries(listSeries);
//
//                }
//                xAxis.setCategories(xcat.toArray(new String[xcat.size()]));
//            } else {
//                List<String> xcat = new ArrayList<>();
//                ListSeries discountDollorListSeries = new ListSeries();
//                discountDollorListSeries.setName("Discount $ Per Unit");
//                ListSeries discountPercentListSeries = new ListSeries();
//                discountPercentListSeries.setName(CommonUtils.VAR_DIS_RATE);
//                ListSeries unitVolumeListSeries = new ListSeries();
//                unitVolumeListSeries.setName(Constant.UNIT_VOLUME);
//                ListSeries totalDiscountListSeries = new ListSeries();
//                totalDiscountListSeries.setName("Total Discount Amount");
//                for (PPAProjectionResultsDTO pDto : dto) {
//                    xcat.add(pDto.getGroup());
//                    if ((pDto.getDiscountPerUnitProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
//                        discountDollorListSeries.addData(Double.valueOf(pDto.getDiscountPerUnitProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                    } else {
//                        discountDollorListSeries.addData(0);
//                    }
//                    if ((pDto.getDiscountPercentProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
//                        discountPercentListSeries.addData(Double.valueOf(pDto.getDiscountPercentProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                    } else {
//                        discountPercentListSeries.addData(0);
//                    }
//                    if ((pDto.getUnitVolumeProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
//                        unitVolumeListSeries.addData(Double.valueOf(pDto.getUnitVolumeProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                    } else {
//                        unitVolumeListSeries.addData(0);
//                    }
//                    if ((pDto.getTotalDiscountProjections() != null) && !pDto.getDiscountPerUnitProjections().equals(StringUtils.EMPTY)) {
//                        totalDiscountListSeries.addData(Double.valueOf(pDto.getTotalDiscountProjections().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                    } else {
//                        totalDiscountListSeries.addData(0);
//                    }
//                }
//                xAxis.setCategories(xcat.toArray(new String[xcat.size()]));
//                conf.addSeries(discountDollorListSeries);
//                conf.addSeries(discountPercentListSeries);
//                conf.addSeries(unitVolumeListSeries);
//                conf.addSeries(totalDiscountListSeries);
//            }
//        }
//
//        conf.addxAxis(xAxis);
//
//        chart.drawChart(conf);
//
//        LOGGER.debug("End of getChart method ");
        return new Label();
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
