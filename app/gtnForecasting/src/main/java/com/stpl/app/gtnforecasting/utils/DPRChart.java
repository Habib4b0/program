/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

/**
 *
 * @author abhiram
 */
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
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
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Nandhaa
 */
public class DPRChart {

    /**
     * The dto.
     */
    private List<DiscountProjectionResultsDTO> dto;

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
    public DPRChart(final List<DiscountProjectionResultsDTO> dto, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO rightDto) {
        LOGGER.debug("Entering SPRChart method ");
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
//        LOGGER.debug("Entering getChart method ");
//        final Chart chart = new Chart(ChartType.COLUMN);
//        List<String> visibleColumns = new ArrayList<>(CommonUtils.objectListToStringList(rightDto.getSingleColumns()));
//        visibleColumns.remove(Constant.GROUP);
//        List<String> visibleHeaders = new ArrayList<>(rightDto.getSingleHeaders());
//        visibleHeaders.remove(Constant.GROUPFCAPS);
//        String[] visHeaders = visibleHeaders.toArray(new String[visibleHeaders.size()]);
//        final Configuration conf = chart.getConfiguration();
//
//        conf.setTitle(Constant.DISCOUNT_PROJECTION_RESULTS);
//
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
//        if (projSelDTO.getPivotView().equals(PERIOD.getConstant())) {
//            xAxis.setCategories(visHeaders);
//            if (dto != null && dto.size() > 0) {
//                for (DiscountProjectionResultsDTO pDto : dto) {
//                    ListSeries listSeries = new ListSeries();
//                    listSeries.setName(pDto.getGroup());
//
//                    for (Object header : visibleColumns) {
//                        Object value = pDto.getPropertyValue(header);
//                        if (value != null) {
//                            if (!value.toString().equals(DASH.getConstant())) {
//                                listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                            } else {
//                                listSeries.addData(0);
//                            }
//                        } else {
//                            listSeries.addData(0);
//                        }
//                    }
//                    conf.addSeries(listSeries);
//                }
//
//            }
//        } else {
//            List<String> periodList = new ArrayList<>();
//            for (DiscountProjectionResultsDTO pDto : dto) {
//                if (projSelDTO.getPeriodListMap().containsValue(pDto.getGroup())) {
//                    periodList.add(pDto.getGroup());
//                }
//            }
//            String[] visheader = CommonUtils.stringListToStringArray(periodList);
//            xAxis.setCategories(visheader);
//            for (Object header : visibleColumns) {
//                ListSeries listSeries = new ListSeries();
//                listSeries.setName(header.toString());
//                for (DiscountProjectionResultsDTO pDto : dto) {
//                    if (periodList.contains(pDto.getGroup())) {
//                        Object value = pDto.getPropertyValue(header);
//                        if (value != null) {
//                            if (!value.toString().equals(DASH.getConstant())) {
//                                listSeries.addData(Double.valueOf(value.toString().replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)));
//                            } else {
//                                listSeries.addData(0);
//                            }
//                        } else {
//                            listSeries.addData(0);
//                        }
//                    }
//                }
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
        return new Label();
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<DiscountProjectionResultsDTO> getDto() {
        return dto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<DiscountProjectionResultsDTO> dto) {
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

}
