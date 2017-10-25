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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Nandhaa
 */
public class DPRUnitChart {

    List<CommonLevelDto> salesRowList = new ArrayList<CommonLevelDto>();
    VerticalLayout mainLayout = new VerticalLayout();
    Chart chart ;
    private String freq = CommonUtils.ANNUAL;
    CommonLogic logic = new CommonLogic();
    private List finalList;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SPRChart.class);

    private int fromDate = 0;
    private int toDate = 0;
    Set<String> keySet = new HashSet<String>();
    private int proId=0;
    private String projType;
    private boolean isStack;

    List<String> xasis = new ArrayList<>();
     String freqValue=StringUtils.EMPTY;
    ChartType chartType;
    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public DPRUnitChart(int proId,String projType, List<CommonLevelDto> list, List<String> xasis,ChartType chartType,boolean isStack) {
        LOGGER.info("Entering DPRChart method ");
        this.proId=proId;
        this.projType=projType;
        this.salesRowList.addAll(list);
        this.xasis.addAll(xasis);
        this.chart= new Chart(chartType);
        this.isStack=isStack;
        this.chartType=chartType;
//        this.dto = dto;
//        this.rightDto = rightDto;
        LOGGER.info("End of DPRChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart(int projectionId,String projType,String frequency) {
        LOGGER.info("Entering getChart method ");
        chart = new Chart(chartType);
        final Configuration conf = chart.getConfiguration();
        conf.setExporting(Boolean.TRUE);
          if("pie".equalsIgnoreCase(String.valueOf(chartType))){
         return getPieChart(conf,chart);
         }
         else{
        List<String> xSet = new ArrayList<String>();

        ListSeries listSeries1 = new ListSeries();
        ListSeries listSeries2 = new ListSeries();

        List<String> keyList = new ArrayList<String>();
        for (Object key : keySet) {

                keyList.add(String.valueOf(key));
        

        } 


        for (String key : xasis) {
            xSet.add(String.valueOf(key));
        }

        for (CommonLevelDto dto : salesRowList) {
            boolean flag = true;
            String rsName = dto.getGroup();
            listSeries1 = new ListSeries();
            listSeries2 = new ListSeries();

            listSeries1.setName(rsName + " Actual Rate");
            listSeries2.setName(rsName + " Projected Rate");
            String[] keys = {"-Actual Rate","-Projected Rate"};
            for (Object key1 : xSet) {
                String splitedValue[] = String.valueOf(key1).split("-");

                for (int i = 0; i < keys.length; i++) {
                    Object key = key1 + keys[i];
                    if (flag) {
                        String value = String.valueOf(dto.getPropertyValue(key));
                        System.out.println("key at DPRUnit chart is ======"+String.valueOf(key)+"=value ======="+value);
                        if (String.valueOf(key).contains("Actual Rate")) {
                            listSeries1.addData(Double.valueOf(!"null".equalsIgnoreCase(value) ? value : "0"));

                        } else if (String.valueOf(key).contains("Projected Rate")) {
                            listSeries2.addData(Double.valueOf(!"null".equalsIgnoreCase(value) ? value : "0"));

                        }
                    }
                }
            }

            conf.addSeries(listSeries1);
            conf.addSeries(listSeries2);
        }

        conf.setTitle("Discount");
        conf.disableCredits();
       

        final XAxis xAxis = new XAxis();
        xAxis.setTitle("Year");
        final YAxis yAxis = new YAxis();
//        yAxis.setReversed(Boolean.TRUE);
//        xAxis.setOpposite(Boolean.TRUE);

        yAxis.setMin(0);
        yAxis.setTitle("Rate");
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

        if(isStack){
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

    
    public Component getPieChart(Configuration configuration,Chart chart){
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
        String salesArgu=" Isnull(Sum(FD.ACTUAL_RATE),0)+Isnull(Sum(FD.PROJECTED_RATE),0) AS RATE";
        List<Object[]> dataList = logic.getPieChart(proId,freqValue,salesArgu);
        String year = "";
        List<String> categoryNamesList = new ArrayList<String>();
        List<Double> valuesList = new ArrayList<Double>();

        for (Object[] obj : dataList) {
            System.out.println("String.valueOf(obj[1]):::::::::======>"+String.valueOf(obj[1]));
            System.out.println("String.valueOf(obj[0]):::::::::======>"+String.valueOf(obj[0]));
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
        final ChartCommonLogic logic = new ChartCommonLogic();
//        finalList = logic.getDiscountChartData(proId,projType,freq);
//        salesRowList = (List<CommonLevelDto>) finalList.get(0);
//        keySet = (Set<String>) xasis;
        HorizontalLayout subLayout = new HorizontalLayout();
        final ComboBox frquency = new ComboBox();
        frquency.setNullSelectionAllowed(false);
        frquency.addItem(CommonUtils.ANNUALLY);
        frquency.addItem(CommonUtils.SEMI_ANNUALLY);
        frquency.addItem(CommonUtils.QUARTERLY);
        frquency.addItem(CommonUtils.MONTHLY);
        frquency.select(CommonUtils.ANNUALLY);

        frquency.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String frequency = String.valueOf(event.getProperty().getValue());
                if (CommonUtils.ANNUALLY.equals(frequency)) {
                    freq = CommonUtils.ANNUAL;
                    freqValue=CommonUtils.ANNUAL;
                } else if (CommonUtils.SEMI_ANNUALLY.equals(frequency)) {
                    freq = CommonUtils.SEMI_ANNUAL;
                      freqValue="SEMI_ANNUAL";
                } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                    freq = CommonUtils.QUARTERLY;
                     freqValue="QUARTER";
                } else {
                    freq = CommonUtils.MONTHLY;
                     freqValue="MONTH";
                }
                mainLayout.removeComponent(chart);
                List list = logic.getResults(proId, freq, projType);
                salesRowList = (List<CommonLevelDto>) list.get(0);
                xasis = (List<String>) list.get(2);
                mainLayout.addComponent(getChart(proId, projType, freq));

            }
        });

        Label freqLabel = new Label("Frequency : ");
         freqValue=CommonUtils.ANNUAL;
        CommonUtils.formatLabel(freqLabel);
        subLayout.addComponent(freqLabel);
        subLayout.addComponent(frquency);
        subLayout.setSpacing(true);
        subLayout.setMargin(true);

        mainLayout.addComponent(subLayout);
        mainLayout.addComponent(getChart(proId, projType, freq));

        return mainLayout;
    }
}
