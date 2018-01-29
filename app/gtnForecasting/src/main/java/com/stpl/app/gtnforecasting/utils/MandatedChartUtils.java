/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sooriya.lakshmanan
 */
public class MandatedChartUtils {

    /**
     * The annual.
     */
    public static final String ANNUAL = "Annual";
    /**
     * The semi annual.
     */
    public static final String SEMI_ANNUAL = "semi-Annual";
    /**
     * The month.
     */
    public static final String MONTH = "monthly";
    /**
     * The quarter.
     */
    public static final String QUARTER = "quarterly";
    /**
     * The dto.
     */
    private List<?> dto;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(MandatedChartUtils.class);

    protected String screenName;

    protected ProjectionSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public MandatedChartUtils(final List<?> dto, final String frequency, final String history, CustomTableHeaderDTO rightDto, String screenName, ProjectionSelectionDTO projectionDTO) {
        LOGGER.debug("Entering SPRChart method ");
        this.frequency = frequency;
        this.history = history;
        this.dto = dto == null ? dto : new ArrayList<>(dto);
        this.rightDto = rightDto;
        this.screenName = screenName;
        this.projSelDTO = projectionDTO;
        LOGGER.debug("End of SPRChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
//        LOGGER.debug("Entering getChart method ");
//        final Chart chart = new Chart(ChartType.COLUMN);
//        List<String> visibleColumns = new ArrayList<>(CommonUtils.objectListToStringList(rightDto.getSingleColumns()));
//        visibleColumns.remove("relationshipLevelName");
//        List<String> visibleHeaders = new ArrayList<>(rightDto.getSingleHeaders());
//        visibleHeaders.remove(Constant.CUSTOMER_SMALL);
//        visibleHeaders.remove(Constant.GROUPFCAPS);
//        String[] visHeaders = visibleHeaders.toArray(new String[visibleHeaders.size()]);
//        final Configuration conf = chart.getConfiguration();
//
//        conf.setTitle(screenName);
//        conf.setSubTitle(projSelDTO.getActualsOrProjections());
//        conf.disableCredits();
//        conf.setExporting(Boolean.TRUE);
//
//        final XAxis xAxis = new XAxis();
//        final YAxis yAxis = new YAxis();
//        yAxis.setMin(0);
//        yAxis.setTitle("Values");
//        conf.addyAxis(yAxis);
//
//        final Legend legend = new Legend();
//        legend.setLayout(LayoutDirection.VERTICAL);
//        legend.setBackgroundColor("#FFFFFF");
//        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
//        legend.setVerticalAlign(VerticalAlign.BOTTOM);
//
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
//        if ("Projection Variance".equals(screenName)) {
//            xAxis.setCategories(rightDto.getDoubleProjectedColumns().toArray(new String[rightDto.getDoubleProjectedColumns().size()]));
//
//            if (dto != null && !dto.isEmpty()) {
//                List<ProjectionVarianceDTO> pvdto = (List<ProjectionVarianceDTO>) dto;
//                for (ProjectionVarianceDTO pDto : pvdto) {
//                    ListSeries listSeries = new ListSeries();
//                    listSeries.setName(pDto.getGroup());
//
//                    for (Object header : visibleColumns) {
//
//                        if ((pDto.getPropertyValue(header) != null) && !(pDto.getPropertyValue(header).toString().equals(DASH))
//                                && !StringUtils.EMPTY.equals(pDto.getPropertyValue(header).toString()) && !"-".equals(pDto.getPropertyValue(header).toString())) {
//                            listSeries.addData(Double.valueOf(pDto.getPropertyValue(header).toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//
//                        } else {
//                            listSeries.addData(0);
//                        }
//
//                    }
//                    conf.addSeries(listSeries);
//
//                }
//
//            }
//        } else if ("PMPYCalculator".equals(screenName)) {
//
//            xAxis.setCategories(rightDto.getSingleColumns().toArray(new String[rightDto.getSingleColumns().size()]));
//            if (dto != null && !dto.isEmpty()) {
//                List<MPmpyDTO> pmpyDTOList = (List<MPmpyDTO>) dto;
//
//                for (MPmpyDTO pmpyDTO : pmpyDTOList) {
//                    ListSeries listSeries = new ListSeries();
//                    listSeries.setName(pmpyDTO.getFirstColumn());
//
//                    for (Object header : visibleColumns) {
//                        if ((pmpyDTO.getPropertyValue(header) != null) && !(pmpyDTO.getPropertyValue(header).toString().equals(DASH))
//                                && !StringUtils.EMPTY.equals(pmpyDTO.getPropertyValue(header).toString()) && !"-".equals(pmpyDTO.getPropertyValue(header).toString())) {
//                            listSeries.addData(Double.valueOf(pmpyDTO.getPropertyValue(header).toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                        } else {
//                            listSeries.addData(0);
//                        }
//                    }
//                    conf.addSeries(listSeries);
//                }
//            }
//        } else {
//
//            if (Constant.PERIOD.equals(projSelDTO.getPivotView())) {
//
//                xAxis.setCategories(visHeaders);
//
//                if (dto != null && !dto.isEmpty()) {
//                    if (Constant.SALES_PROJECTION_RESULTS.equals(screenName)) {
//                        List<SalesProjectionResultsDTO> salesdto = (List<SalesProjectionResultsDTO>) dto;
//
//                        for (SalesProjectionResultsDTO pDto : salesdto) {
//                            ListSeries listSeries = new ListSeries();
//                            listSeries.setName(pDto.getRelationshipLevelName());
//                            for (Object header : visibleColumns) {
//
//                                String value = String.valueOf(pDto.getPropertyValue(header));
//                                listSeries.addData(((pDto.getPropertyValue(header) != null) && !(pDto.getPropertyValue(header).toString().equals(DASH)))
//                                        ? Double.valueOf(value.replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) : 0);
//                            }
//                            conf.addSeries(listSeries);
//                        }
//                    } else if (Constant.PROJECTION_RESULTS.equals(screenName)) {
//                        List<ProjectionResultsDTO> prdto = (List<ProjectionResultsDTO>) dto;
//                        for (ProjectionResultsDTO pDto : prdto) {
//                            ListSeries listSeries = new ListSeries();
//                            listSeries.setName(pDto.getGroup());
//
//                            for (Object header : visibleColumns) {
//                                Object value = pDto.getPropertyValue(header);
//                                if (value != null) {
//                                    if (!value.toString().equals("- - -")) {
//                                        listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                                    } else {
//                                        listSeries.addData(0);
//                                    }
//                                } else {
//                                    listSeries.addData(0);
//                                }
//                            }
//                            conf.addSeries(listSeries);
//
//                        }
//                    }
//                }
//            } else {
//                List<String> periodList = new ArrayList<>();
//                if (Constant.SALES_PROJECTION_RESULTS.equals(screenName)) {
//                    List<SalesProjectionResultsDTO> salesdto = (List<SalesProjectionResultsDTO>) dto;
//                    for (SalesProjectionResultsDTO pDto : salesdto) {
//                        if (projSelDTO.getPeriodListMap().containsValue(pDto.getLevelValue())) {
//                            periodList.add(pDto.getLevelValue());
//                        }
//                    }
//                } else if (Constant.PROJECTION_RESULTS.equals(screenName)) {
//                    List<ProjectionResultsDTO> prDto = (List<ProjectionResultsDTO>) dto;
//                    for (ProjectionResultsDTO pDto : prDto) {
//                        if (projSelDTO.getPeriodListMap().containsValue(pDto.getGroup())) {
//                            periodList.add(pDto.getGroup());
//                        }
//                    }
//                }
//                String[] visheader = CommonUtils.stringListToStringArray(periodList);
//                xAxis.setCategories(visheader);
//                int i = 0;
//                for (Object header : visibleColumns) {
//                    ListSeries listSeries = new ListSeries();
//                    listSeries.setName(visibleHeaders.get(i));
//                    if (Constant.SALES_PROJECTION_RESULTS.equals(screenName)) {
//                        List<SalesProjectionResultsDTO> salesdto = (List<SalesProjectionResultsDTO>) dto;
//                        for (SalesProjectionResultsDTO pDto : salesdto) {
//                            if (periodList.contains(pDto.getLevelValue())) {
//                                Object value = pDto.getPropertyValue(header);
//                                if (value != null) {
//                                    if (!value.toString().equals("...")) {
//                                        listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                                    } else {
//                                        listSeries.addData(0);
//                                    }
//                                } else {
//                                    listSeries.addData(0);
//                                }
//                            }
//                        }
//                    } else if (Constant.PROJECTION_RESULTS.equals(screenName)) {
//                        List<ProjectionResultsDTO> prDto = (List<ProjectionResultsDTO>) dto;
//                        for (ProjectionResultsDTO pDto : prDto) {
//                            if (periodList.contains(pDto.getGroup())) {
//                                Object value = pDto.getPropertyValue(header);
//                                if (value != null) {
//                                    if (!value.toString().equals("- - -")) {
//                                        listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                                    } else {
//                                        listSeries.addData(0);
//                                    }
//                                } else {
//                                    listSeries.addData(0);
//                                }
//                            }
//                        }
//                    }
//                    i++;
//                    conf.addSeries(listSeries);
//                }
//            }
//        }
//        conf.addxAxis(xAxis);
//        chart.drawChart(conf);
//        LOGGER.debug("End of getChart method ");
        return new Label();
                        }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<?> getDto() {
        return dto == null ? dto : new ArrayList<>(dto);
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<?> dto) {
        this.dto = dto == null ? dto : new ArrayList<>(dto);
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
