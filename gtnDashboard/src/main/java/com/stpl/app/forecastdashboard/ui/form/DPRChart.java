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
import com.stpl.app.forecastdashboard.logic.ChartCommonLogic;
import com.stpl.app.forecastdashboard.logic.CommonLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import static com.stpl.app.forecastdashboard.utils.CommonUtils.getMonthForInt;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Property;
import com.vaadin.server.Sizeable;
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
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Nandhaa
 */
public class DPRChart {

    List<CommonLevelDto> salesRowList = new ArrayList<>();
    List<String> xasis = new ArrayList<>();
    VerticalLayout mainLayout = new VerticalLayout();
    private String freq = CommonUtils.ANNUAL;
    Chart chart;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SPRChart.class);

    private int fromDate = 0;
    private int toDate = 0;
    Set<String> keySet = new HashSet<String>();
    private int proId = 0;
    private String projType;
    private boolean isStack;
    String freqValue = StringUtils.EMPTY;
    ChartType chartType;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public DPRChart(int proId, String projType) {
        LOGGER.info("Entering DPRChart method ");
        this.proId = proId;
        this.projType = projType;
//        this.dto = dto;
//        this.rightDto = rightDto;
        LOGGER.info("End of DPRChart method ");
    }

    public DPRChart(int proId, String projType, List<CommonLevelDto> list, List<String> xasis, ChartType chartType, boolean isStack) {
        LOGGER.info("Entering DPRChart method ");
        this.proId = proId;
        this.projType = projType;
        this.salesRowList.addAll(list);
        this.xasis.addAll(xasis);
        this.chart = new Chart(chartType);
        this.chartType = chartType;
        this.isStack = isStack;
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
        chart = new Chart(chartType);
        final Configuration conf = chart.getConfiguration();
        if ("pie".equalsIgnoreCase(String.valueOf(chartType))) {
            return getPieChart(conf, chart);
        } else {
            List<String> xSet = new ArrayList<String>();

            ListSeries listSeries1 = new ListSeries();
            ListSeries listSeries2 = new ListSeries();
            ListSeries listSeries3 = new ListSeries();
            ListSeries listSeries4 = new ListSeries();

//        List<String> keyList = new ArrayList<String>();
            CommonLogic logic = new CommonLogic();
//        List finalList = logic.getDiscountChartData(proId,projType, freq);
//        salesRowList = (List<CommonLevelDto>)finalList.get(0);
//        keySet = (Set<String>) finalList.get(1);
            List<String> keyList = !xasis.isEmpty() ? new ArrayList<String>(xasis) : new ArrayList<String>();
//        for (Object key : keySet) {
//
//            int tempValue = Integer.parseInt(String.valueOf(key));
//            if (from != 0 && to != 0) {
//                if (tempValue >= from && tempValue <= to) {
//                    keyList.add(Integer.parseInt(String.valueOf(key)));
//                }
//            } else if (from != 0 && to == 0) {
//                if (tempValue >= from) {
//                    keyList.add(Integer.parseInt(String.valueOf(key)));
//                }
//
//            } else if (from == 0 && to != 0) {
//                if (tempValue <= to) {
//                    keyList.add(Integer.parseInt(String.valueOf(key)));
//                }
//
//            } else {
//                keyList.add(String.valueOf(key));
//            }
//
//        }

//        Collections.sort(keyList);
            for (String key : keyList) {
                xSet.add(String.valueOf(key));
            }
            String[] keys = {"-Projected Amount", "-Actual Amount"};
            for (CommonLevelDto dto : salesRowList) {
                boolean flag = false;
                String rsName = dto.getGroup();
                listSeries1 = new ListSeries();
                listSeries2 = new ListSeries();
                listSeries3 = new ListSeries();
                listSeries4 = new ListSeries();

                listSeries1.setName(rsName + " Actual Amount");
//            listSeries2.setName(rsName + " Actual Rate");
                listSeries3.setName(rsName + " Projected Amount");
//            listSeries4.setName(rsName + " Projected Rate");
                for (Object key1 : keyList) {
                    String splitedValue[] = String.valueOf(key1).split("-");
                    for (int i = 0; i < keys.length; i++) {
                        Object key = key1 + keys[i];
//                int year = Integer.parseInt(splitedValue[0]);
//                if (from != 0 && to != 0) {
//                    if (year >= from && year <= to) {
//
//                        flag = true;
//                    }
//                } else if (from != 0 && to == 0) {
//                    if (year >= from) {
//
//                        flag = true;
//                    }
//
//                } else if (from == 0 && to != 0) {
//                    if (year <= to) {
//
//                        flag = true;
//                    }
//
//                } else 
                        if (from == 0 && to == 0) {

                            flag = true;
                        }

                        if (flag) {
                            String value = String.valueOf(dto.getPropertyValue(key));
                            if (String.valueOf(key).contains("Actual Amount")) {
                                listSeries1.addData(Double.valueOf(!"null".equalsIgnoreCase(value) ? value : "0"));
//                        System.out.println("List1");

                            } else if (String.valueOf(key).contains("Actual Rate")) {
                                listSeries2.addData(Double.valueOf(!"null".equalsIgnoreCase(value) ? value : "0"));

                            } else if (String.valueOf(key).contains("Projected Amount")) {
                                listSeries3.addData(Double.valueOf(!"null".equalsIgnoreCase(value) ? value : "0"));

                            } else if (String.valueOf(key).contains("Projected Rate")) {
                                listSeries4.addData(Double.valueOf(!"null".equalsIgnoreCase(value) ? value : "0"));

                            }
                            flag = false;
                        }
                    }
                }

                conf.addSeries(listSeries1);
//            conf.addSeries(listSeries2);
                conf.addSeries(listSeries3);
//            conf.addSeries(listSeries4);
            }

            conf.setTitle("Discount");
            conf.disableCredits();
            conf.setExporting(Boolean.TRUE);

            final XAxis xAxis = new XAxis();
            xAxis.setTitle("Year");
            final YAxis yAxis = new YAxis();
//        yAxis.setReversed(Boolean.TRUE);
//        xAxis.setOpposite(Boolean.TRUE);

            yAxis.setMin(0);
            yAxis.setTitle("Amount");
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

            if (isStack) {
                final PlotOptionsColumn plot = new PlotOptionsColumn();
                plot.setStacking(Stacking.NORMAL);
                //        plot.setPointPadding(0.2);
                //        plot.setBorderWidth(10);
                conf.setPlotOptions(plot);
            }

            xAxis.setCategories(Arrays.copyOf(xSet.toArray(), xSet.toArray().length, String[].class));

            conf.addxAxis(xAxis);
            chart.drawChart(conf);

            LOGGER.info("End of getChart method ");
            return chart;
        }
    }

    public Component getPieChart(Configuration configuration, Chart chart) {
        final CommonLogic logic = new CommonLogic();
        configuration.setTitle("Discount");
        Set<String> uniqueYear = new HashSet<String>();
        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        configuration.setPlotOptions(plotOptions);
        chart.setHeight(400, Sizeable.Unit.PIXELS);
        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(2);
        tooltip.setPointFormat("{point.percentage:%.2f}%");
        configuration.setTooltip(tooltip);
        String salesArgu = " Isnull(Sum(FD.ACTUAL_AMOUNT),0)+Isnull(Sum(FD.PROJECTED_AMOUNT),0) AS AMOUNT";
        List<Object[]> dataList = logic.getPieChart(proId, freqValue, salesArgu);
        String year = "";
        List<String> categoryNamesList = new ArrayList<String>();
        List<Double> valuesList = new ArrayList<Double>();

        for (Object[] obj : dataList) {
            String yearValue = String.valueOf(obj[1]);
            if (CommonUtils.SEMI_ANNUAL.equals(freq)) {
                yearValue = "S" + String.valueOf(obj[2]) + " " + String.valueOf(obj[1]);
            } else if (CommonUtils.QUARTERLY.equals(freq)) {
                yearValue = "Q" + String.valueOf(obj[2]) + " " + String.valueOf(obj[1]);
            } else if (CommonUtils.MONTHLY.equals(freq)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[2]) - 1);
                yearValue = monthName + " " + String.valueOf(obj[1]);
            }

            categoryNamesList.add(yearValue);
            valuesList.add(new Double(String.valueOf(obj[0])));
        }
        dataList.add(0, categoryNamesList.toArray(new String[0]));
        dataList.add(1, valuesList.toArray(new Double[0]));
        DataSeries series = new DataSeries();
        for (int i = 0; i < dataList.size(); i++) {
            Object obj[] = dataList.get(i);
            if (uniqueYear.add(obj[1].toString())) {
                series.setData((String[]) dataList.get(0), (Double[]) dataList.get(1));
            }
        }
        Configuration conf = chart.getConfiguration();
        conf.setExporting(Boolean.TRUE);
        conf.setSeries(series);
        chart.drawChart(conf);
        return chart;
    }

    public Component configureFields() {

//        CommonLogic logic = new CommonLogic();
        final ChartCommonLogic logic = new ChartCommonLogic();
//        List finalList = logic.getDiscountChartData(proId,projType,freq);
//        salesRowList = (List<CommonLevelDto>) finalList.get(0);
//        keySet = (Set<String>) finalList.get(1);
        HorizontalLayout subLayout = new HorizontalLayout();
        final ComboBox frequency = new ComboBox();
        frequency.addItem("-Select One-");
        frequency.setNullSelectionItemId("-Select One-");
        frequency.addItem(CommonUtils.ANNUALLY);
        frequency.addItem(CommonUtils.SEMI_ANNUALLY);
        frequency.addItem(CommonUtils.QUARTERLY);
        frequency.addItem(CommonUtils.MONTHLY);
        frequency.select(CommonUtils.ANNUALLY);// did here
        frequency.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    String frequency = String.valueOf(event.getProperty().getValue());
                    if (CommonUtils.ANNUALLY.equals(frequency)) {
                        freq = CommonUtils.ANNUAL;
                        freqValue = CommonUtils.ANNUAL;
                    } else if (CommonUtils.SEMI_ANNUALLY.equals(frequency)) {
                        freq = CommonUtils.SEMI_ANNUAL;
                        freqValue = "SEMI_ANNUAL";
                    } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                        freq = CommonUtils.QUARTERLY;
                        freqValue = "QUARTER";
                    } else {
                        freq = CommonUtils.MONTHLY;
                        freqValue = "MONTH";
                    }
                    List list = logic.getResults(proId, freq, projType);
                    salesRowList = (List<CommonLevelDto>) list.get(0);
                    xasis = (List<String>) list.get(2);
                }

                mainLayout.removeComponent(chart);
                mainLayout.addComponent(getChart(fromDate, toDate));
            }
        });
        freqValue = CommonUtils.ANNUAL;
        Label freqLabel = new Label("Frequency : ");
        CommonUtils.formatLabel(freqLabel);
        subLayout.addComponent(freqLabel);
        subLayout.addComponent(frequency);
        subLayout.setSpacing(true);
        subLayout.setMargin(true);

        mainLayout.addComponent(subLayout);
        mainLayout.addComponent(getChart(fromDate, toDate));

        return mainLayout;
    }
}
