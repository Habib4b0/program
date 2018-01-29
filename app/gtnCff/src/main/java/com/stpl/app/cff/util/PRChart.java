/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

/**
 *
 * @author abhiram
 */
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import static com.stpl.app.cff.util.Constants.LabelConstants.DASH;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERIOD;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
//import com.vaadin.addon.charts.Chart;
//import com.vaadin.addon.charts.model.ChartType;
//import com.vaadin.addon.charts.model.Configuration;
//import com.vaadin.addon.charts.model.HorizontalAlign;
//import com.vaadin.addon.charts.model.LayoutDirection;
//import com.vaadin.addon.charts.model.Legend;
//import com.vaadin.addon.charts.model.ListSeries;
//import com.vaadin.addon.charts.model.PlotOptionsColumn;
//import com.vaadin.addon.charts.model.Tooltip;
//import com.vaadin.addon.charts.model.VerticalAlign;
//import com.vaadin.addon.charts.model.XAxis;
//import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lokeshwari
 */
public class PRChart {

    /**
     * The dto.
     */
    private List<ProjectionResultsDTO> dto;

    private CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PRChart.class);
    private ProjectionSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
//    public PRChart(final List<ProjectionResultsDTO> dto, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO rightDto) {
//        LOGGER.debug("Entering SPRChart method ");
//        this.dto = dto;
//        this.rightDto = rightDto;
//        this.projSelDTO = projSelDTO;
//        LOGGER.debug("End of SPRChart method ");
//    }
//
//    /**
//     * Gets the chart.
//     *
//     * @return the chart
//     */
//    public Component getChart() {
//        LOGGER.debug("Entering getChart method ");
//        final Chart chart = new Chart(ChartType.COLUMN);
//        List<String> visibleColumns = new ArrayList<>(CommonUtils.objectListToStringList(rightDto.getSingleColumns()));
//        visibleColumns.remove("group");
//        List<String> visibleHeaders = new ArrayList<>(rightDto.getSingleHeaders());
//        visibleHeaders.remove("Group");
//        String[] visHeaders = visibleHeaders.toArray(new String[visibleHeaders.size()]);
//        final Configuration conf = chart.getConfiguration();
//
//        conf.setTitle("Projection Results");
//        conf.setSubTitle(projSelDTO.getActualsOrProjections());
//        conf.disableCredits();
//        conf.setExporting(Boolean.TRUE);
//
//        final XAxis xAxis = new XAxis();
//
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
//        if (projSelDTO.getPivotView().equals(PERIOD.getConstant())) {
//            xAxis.setCategories(visHeaders);
//            if (dto != null && dto.size() > 0) {
//                for (ProjectionResultsDTO pDto : dto) {
//                    ListSeries listSeries = new ListSeries();
//                    listSeries.setName(pDto.getGroup());
//
//                    for (Object header : visibleColumns) {
//                        Object value = pDto.getPropertyValue(header);
//                        if (value != null) {
//
//                           if (!value.toString().equals(DASH.getConstant()) && !value.toString().equals("...") && !value.toString().equals("") && !value.toString().equals("---")) {
//                                    listSeries.addData(Double.valueOf(value.toString().replace("$", "").replace("%", "").replace(",", "")));
//                             } else {
//                                listSeries.addData(0);
//                            }
//                        } else {
//                            listSeries.addData(0);
//                        }
//                    }
//                    conf.addSeries(listSeries);
//
//                }
//            }
//        } else {
//            List<String> periodList = new ArrayList<>();
//            for (ProjectionResultsDTO pDto : dto) {
//                if (projSelDTO.getPeriodListMap().containsValue(pDto.getGroup())) {
//                    periodList.add(pDto.getGroup());
//                }
//            }
//            String[] visheader = stringListToStringArray(periodList);
//            xAxis.setCategories(visheader);
//            for (Object header : visibleColumns) {
//                ListSeries listSeries = new ListSeries();
//                listSeries.setName(rightDto.getSingleHeader(header));
//                for (ProjectionResultsDTO pDto : dto) {
//                    if (periodList.contains(pDto.getGroup())) {
//                        Object value = pDto.getPropertyValue(header);
//                        if (value != null) {
//
//                             if (!value.toString().equals(DASH.getConstant())&& !value.toString().equals("...") && !value.toString().equals("")&& !value.toString().equals("---")) {
//                                listSeries.addData(Double.valueOf(value.toString().replace("$", "").replace("%", "").replace(",", "")));
//                            } else {
//                                listSeries.addData(0);
//                            }
//                        } else {
//                            listSeries.addData(0);
//                        }
//                    }
//                }
//
//                conf.addSeries(listSeries);
//            }
//
//        }
//
//        conf.addxAxis(xAxis);
//
//        chart.drawChart(conf);
//
//        LOGGER.debug("End of getChart method ");
//        return chart;
//    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<ProjectionResultsDTO> getDto() {
        return dto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<ProjectionResultsDTO> dto) {
        this.dto = dto;
    }

    public CustomTableHeaderDTO getRightDto() {
        return rightDto;
    }

    public void setRightDto(CustomTableHeaderDTO rightDto) {
        this.rightDto = rightDto;
    }

    public ProjectionSelectionDTO getProjSelDTO() {
        return projSelDTO;
    }

    public void setProjSelDTO(ProjectionSelectionDTO projSelDTO) {
        this.projSelDTO = projSelDTO;
    }

    public static String[] stringListToStringArray(List<String> stringList) {
        String[] stringArray = {};
        if (stringList != null) {
            stringArray = new String[stringList.size()];
            stringArray = stringList.toArray(stringArray);
        }
        return stringArray;
    }

    }
