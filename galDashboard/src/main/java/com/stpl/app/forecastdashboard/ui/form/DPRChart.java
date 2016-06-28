/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui.form;

/**
 *
 * @author abhiram
 */
import com.stpl.app.forecastdashboard.logic.CommonLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jboss.logging.Logger;

/**
 *
 * @author Nandhaa
 */
public class DPRChart {

    List<CommonLevelDto> salesRowList = new ArrayList<CommonLevelDto>();
    VerticalLayout mainLayout = new VerticalLayout();
    Chart chart = new Chart(ChartType.COLUMN);
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SPRChart.class);

    private int fromDate = 0;
    private int toDate = 0;
    Set<String> keySet = new HashSet<String>();
    private int proId=0;
    private String projType;
    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public DPRChart(int proId,String projType) {
        LOGGER.info("Entering DPRChart method ");
        this.proId=proId;
        this.projType=projType;
//        this.dto = dto;
//        this.rightDto = rightDto;
        LOGGER.info("End of DPRChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart(int from, int to) {
        LOGGER.info("Entering getChart method ");
        chart = new Chart(ChartType.COLUMN);
        final Configuration conf = chart.getConfiguration();

        List<String> xSet = new ArrayList<String>();

        ListSeries listSeries1 = new ListSeries();
        ListSeries listSeries2 = new ListSeries();
        ListSeries listSeries3 = new ListSeries();
        ListSeries listSeries4 = new ListSeries();

        List<Integer> keyList = new ArrayList<Integer>();
        for (Object key : keySet) {

            int tempValue = Integer.parseInt(String.valueOf(key));
            if (from != 0 && to != 0) {
                if (tempValue >= from && tempValue <= to) {
                    keyList.add(Integer.parseInt(String.valueOf(key)));
                }
            } else if (from != 0 && to == 0) {
                if (tempValue >= from) {
                    keyList.add(Integer.parseInt(String.valueOf(key)));
                }

            } else if (from == 0 && to != 0) {
                if (tempValue <= to) {
                    keyList.add(Integer.parseInt(String.valueOf(key)));
                }

            } else {
                keyList.add(Integer.parseInt(String.valueOf(key)));
            }

        }

        Collections.sort(keyList);

        for (Integer key : keyList) {
            xSet.add(String.valueOf(key));
        }

        for (CommonLevelDto dto : salesRowList) {
            boolean flag = false;
            String rsName = dto.getGroup();
            listSeries1 = new ListSeries();
            listSeries2 = new ListSeries();
            listSeries3 = new ListSeries();
            listSeries4 = new ListSeries();

            listSeries1.setName(rsName + " Actual Amount");
            listSeries2.setName(rsName + " Actual Rate");
            listSeries3.setName(rsName + " Projected Amount");
            listSeries4.setName(rsName + " Projected Rate");
            for (Object key : dto.getProperties().keySet()) {
                String splitedValue[] = String.valueOf(key).split("-");
                int year = Integer.parseInt(splitedValue[0]);
                if (from != 0 && to != 0) {
                    if (year >= from && year <= to) {

                        flag = true;
                    }
                } else if (from != 0 && to == 0) {
                    if (year >= from) {

                        flag = true;
                    }

                } else if (from == 0 && to != 0) {
                    if (year <= to) {

                        flag = true;
                    }

                } else if (from == 0 && to == 0) {

                    flag = true;
                }

                if (flag) {
                    String value = String.valueOf(dto.getPropertyValue(key));
                    if (String.valueOf(key).contains("Actual Amount")) {
                        listSeries1.addData(Double.valueOf(value.toString()));
//                        System.out.println("List1");

                    } else if (String.valueOf(key).contains("Actual Rate")) {
                        listSeries2.addData(Double.valueOf(value.toString()));

                    } else if (String.valueOf(key).contains("Projected Amount")) {
                        listSeries3.addData(Double.valueOf(value.toString()));

                    } else if (String.valueOf(key).contains("Projected Rate")) {
                        listSeries4.addData(Double.valueOf(value.toString()));

                    }
                    flag = false;
                }
            }

            conf.addSeries(listSeries1);
            conf.addSeries(listSeries2);
            conf.addSeries(listSeries3);
            conf.addSeries(listSeries4);
        }

        conf.setTitle("Discount Projection Results");
        conf.disableCredits();
        conf.setExporting(Boolean.TRUE);

        final XAxis xAxis = new XAxis();
        xAxis.setTitle("Year");
        final YAxis yAxis = new YAxis();
//        yAxis.setReversed(Boolean.TRUE);
//        xAxis.setOpposite(Boolean.TRUE);

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
//        tooltip.setFormatter("this.x +': '+'$ '+ this.y ");
//        tooltip.setPointFormat("$ {point.y:%.2f}");
        tooltip.setPointFormat("{point.y:,.2f}");

        conf.setTooltip(tooltip);

        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(10);

        xAxis.setCategories(Arrays.copyOf(xSet.toArray(), xSet.toArray().length, String[].class));

        conf.addxAxis(xAxis);
        chart.drawChart(conf);

        LOGGER.info("End of getChart method ");
        return chart;
    }

    public Component configureFields() {

        CommonLogic logic = new CommonLogic();
        List finalList = logic.getDiscountChartData(proId,projType);
        salesRowList = (List<CommonLevelDto>) finalList.get(0);
        keySet = (Set<String>) finalList.get(1);
        HorizontalLayout subLayout = new HorizontalLayout();
        final ComboBox fromPeriod = new ComboBox();
        final ComboBox toPeriod = new ComboBox();
        fromPeriod.addItem("-Select One-");
        fromPeriod.setNullSelectionItemId("-Select One-");
        toPeriod.addItem("-Select One-");
        toPeriod.setNullSelectionItemId("-Select One-");

        fromPeriod.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if (event.getProperty().getValue() != null) {
                    fromDate = Integer.parseInt(String.valueOf(event.getProperty().getValue()));
                } else {
                    fromDate = 0;
                }
                if (toDate != 0) {
                    if (fromDate > toDate) {
                        fromPeriod.select("-Select One-");
                    }
                }

                mainLayout.removeComponent(chart);
                mainLayout.addComponent(getChart(fromDate, toDate));

            }
        });
        toPeriod.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if (event.getProperty().getValue() != null) {
                    toDate = Integer.parseInt(String.valueOf(event.getProperty().getValue()));
                } else {
                    toDate = 0;
                }
                if (toDate != 0) {
                    if (toDate < fromDate) {
                        toPeriod.select("-Select One-");
                    }
                }

                mainLayout.removeComponent(chart);
                mainLayout.addComponent(getChart(fromDate, toDate));
            }
        });
        Label fromLabel = new Label("From Period : ");
        CommonUtils.formatLabel(fromLabel);
        Label toLabel = new Label("To Period : ");
        CommonUtils.formatLabel(toLabel);
        
        subLayout.addComponent(fromLabel);
        subLayout.addComponent(fromPeriod);
        subLayout.addComponent(toLabel);
        subLayout.addComponent(toPeriod);
        subLayout.setSpacing(true);
        subLayout.setMargin(true);

        mainLayout.addComponent(subLayout);
        mainLayout.addComponent(getChart(fromDate, toDate));

        List<Integer> keyList = new ArrayList<Integer>();

        for (Object key : keySet) {
            keyList.add(Integer.parseInt(String.valueOf(key)));
        }
        Collections.sort(keyList);

        for (Integer key : keyList) {

            fromPeriod.addItem(key);
            toPeriod.addItem(key);
        }

        return mainLayout;
    }
}
