/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui.form;

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
 * @author porchelvi.g
 */
public class CFFSalesChart {
        
        List<CommonLevelDto> commonLevelDtoList = new ArrayList<CommonLevelDto>();
        private String freq = CommonUtils.ANNUAL;
        VerticalLayout mainLayout = new VerticalLayout();
        private int proId = 0;
        private String projType;
        CommonLogic logic = new CommonLogic();
         /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CFFSalesChart.class);
    private int fromDate = 0;
    private int toDate = 0;
    private boolean isStack;
    Chart chart;
    List<String> xasis=new ArrayList<>();

      String freqValue=StringUtils.EMPTY;
    ChartType chartType;
    
    
    public CFFSalesChart(int proId,String projType,ChartType chartType) {
        LOGGER.info("Entering CFFSalesChart method ");
        this.proId = proId;
        this.projType=projType;
        this.chart= new Chart(chartType);
        this.chartType=chartType;
        LOGGER.info("End of SPRChart method ");
    }
    
    public CFFSalesChart(int proId,String projType, List<CommonLevelDto> list, List<String> xasis,ChartType chartType,boolean isStack) {
        LOGGER.info("Entering CFFSalesChart method ");
        this.proId = proId;
        this.projType=projType;
        this.chart= new Chart(chartType);
        this.chartType=chartType;
        this.commonLevelDtoList=list;
        this.xasis=xasis;
        this.isStack=isStack;
        LOGGER.info("End of SPRChart method ");
    }
    
      /**
     * Gets the chart.
     *
     * @return the chart
     */
   public Component getChart(int projId,String projType,String freq) {
        LOGGER.info("Entering getChart method ");
        chart = new Chart(chartType);
          final Configuration conf = chart.getConfiguration();
         if("pie".equalsIgnoreCase(String.valueOf(chartType))){
         return getPieChart(conf,chart);
         }
         else{
//        commonLevelDtoList = logic.getProjectionResultChartData(projId,projType,freq);
        //  List<CommonLevelDto> commonLevelDtoList = new ArrayList<CommonLevelDto>();
        chart = new Chart(chartType);
        Set<Object> keySet = new HashSet<Object>();
        List<String> xSet = new ArrayList<String>();
        ListSeries listSeries1 = new ListSeries();
        ListSeries listSeries2 = new ListSeries();
        ListSeries listSeries3 = new ListSeries();
        ListSeries listSeries4 = new ListSeries();
        
         ListSeries listSeries5 = new ListSeries();
        ListSeries listSeries6 = new ListSeries();
        ListSeries listSeries7 = new ListSeries();
        ListSeries listSeries8 = new ListSeries();
//        keySet = commonLevelDtoList.get(0).getProperties().keySet();
//        List<String> keyList = !xasis.isEmpty() ? 
//                new ArrayList<String>(xasis) : new ArrayList<String>();
//        for (Object key : keySet) {
//                keyList.add(String.valueOf(key));           
//        }

         List<String> keyList = new ArrayList<String>(xasis);

        for (String key : keyList) {
            xSet.add(String.valueOf(key));
        }
        for (Object key : xSet) {
            listSeries1.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(0).getPropertyValue(key))) ? (String) commonLevelDtoList.get(0).getPropertyValue(key) : "0"));
            listSeries2.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(1).getPropertyValue(key))) ? (String) commonLevelDtoList.get(1).getPropertyValue(key) : "0"));
            listSeries3.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(2).getPropertyValue(key))) ? (String) commonLevelDtoList.get(2).getPropertyValue(key) : "0"));
            listSeries4.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(3).getPropertyValue(key))) ? (String) commonLevelDtoList.get(3).getPropertyValue(key) : "0"));

            listSeries5.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(6).getPropertyValue(key))) ? (String) commonLevelDtoList.get(6).getPropertyValue(key) : "0"));
            listSeries6.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(7).getPropertyValue(key))) ? (String) commonLevelDtoList.get(7).getPropertyValue(key) : "0"));
            listSeries7.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(8).getPropertyValue(key))) ? (String) commonLevelDtoList.get(8).getPropertyValue(key) : "0"));
            listSeries8.addData(Double.valueOf(!"null".equals(String.valueOf(commonLevelDtoList.get(9).getPropertyValue(key))) ? (String) commonLevelDtoList.get(9).getPropertyValue(key) : "0"));
        
        }

     
        
        listSeries1.setName("GTS Actuals");
        listSeries2.setName("GTS Projected");
        listSeries3.setName("Contract Sales Actuals");
        listSeries4.setName("Contract Sales Projected");

        listSeries5.setName("Cogs Actuals");
        listSeries6.setName("Cogs Projected");
        listSeries7.setName("Net Profit Actuals");
        listSeries8.setName("Net Profit Projected");
      

        conf.setTitle("Results");
        conf.disableCredits();
        conf.setExporting(Boolean.TRUE);

        final XAxis xAxis = new XAxis();
        xAxis.setTitle("Year");
        
        final YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle("Sales");
        conf.addyAxis(yAxis);

        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);
        legend.setShadow(true);

        conf.setLegend(legend);

        final Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(2);
//        tooltip.setFormatter("this.x +': $ '+ this.y ");
        tooltip.setPointFormat(" {point.y:,.2f}");
        conf.setTooltip(tooltip);

        if(isStack){
            final PlotOptionsColumn plot = new PlotOptionsColumn();
            plot.setStacking(Stacking.NORMAL);
    //        plot.setPointPadding(0.2);
    //        plot.setBorderWidth(10);
            conf.setPlotOptions(plot);
        }
        xAxis.setCategories(Arrays.copyOf(xSet.toArray(), xSet.toArray().length, String[].class));

        conf.addSeries(listSeries1);
        conf.addSeries(listSeries2);
        conf.addSeries(listSeries3);
        conf.addSeries(listSeries4);

        
        conf.addSeries(listSeries5);
        conf.addSeries(listSeries6);
        conf.addSeries(listSeries7);
        conf.addSeries(listSeries8);
        
        conf.addxAxis(xAxis);

        chart.drawChart(conf);

        LOGGER.info("End of getChart method ");

        return chart;
         }
    }
    
   
   public Component getPieChart(Configuration configuration,Chart chart){
         final CommonLogic logic = new CommonLogic();
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
        String salesArgu=" Isnull(Sum(FD.CONTRACT_SALES_ACTUALS),0)+Isnull(Sum(FD.CONTRACT_SALES_PROJECTED),0)+Isnull(Sum(FD.COGS_PROJECTED),0)+Isnull(Sum(FD.COGS_ACTUAL),0)";
        List<Object[]> dataList = logic.getPieChart(proId,freqValue,salesArgu);
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
        conf.setSeries(series);
        chart.drawChart(conf);
    return chart;
    }
       public Component configureFields() {
        final ChartCommonLogic chartlogic = new ChartCommonLogic();
        CommonLogic logic = new CommonLogic();
//        commonLevelDtoList = logic.getProjectionResultChartData(proId,projType, "ANNUAL");

        HorizontalLayout subLayout = new HorizontalLayout();
        final ComboBox frequency = new ComboBox();
        final ComboBox fromPeriod = new ComboBox();
        final ComboBox toPeriod = new ComboBox();
        frequency.setNullSelectionAllowed(false);
        frequency.addItem(CommonUtils.ANNUALLY);
        frequency.addItem(CommonUtils.SEMI_ANNUALLY);
        frequency.addItem(CommonUtils.QUARTERLY);
        frequency.addItem(CommonUtils.MONTHLY);
        frequency.select(CommonUtils.ANNUALLY);
        frequency.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    String frequency = String.valueOf(event.getProperty().getValue());
                    if(CommonUtils.ANNUALLY.equals(frequency)) {
                        freq = CommonUtils.ANNUAL;
                           freqValue=CommonUtils.ANNUAL;
                    } else if(CommonUtils.SEMI_ANNUALLY.equals(frequency)) {
                        freq = CommonUtils.SEMI_ANNUAL;
                         freqValue="SEMI_ANNUAL";
                    } else if(CommonUtils.QUARTERLY.equals(frequency)) {
                        freq = CommonUtils.QUARTERLY;
                         freqValue="QUARTER";
                    } else {
                        freq = CommonUtils.MONTHLY;
                         freqValue="MONTH";
                } 
                    List list=chartlogic.getResults(proId,freq,projType);
                commonLevelDtoList = (List<CommonLevelDto>) list.get(0);//did here
                xasis=(List<String>)list.get(1);//did here   
                } 
               mainLayout.removeComponent(chart);
               mainLayout.addComponent(getChart(proId, projType, freq));
            }
        });

      freqValue=CommonUtils.ANNUAL;

        Label freqLabel = new Label("Frequency : ");
        CommonUtils.formatLabel(freqLabel);
        subLayout.addComponent(freqLabel);
        subLayout.addComponent(frequency);
        subLayout.setSpacing(true);
        subLayout.setMargin(true);

        mainLayout.addComponent(subLayout);
        mainLayout.addComponent(getChart(proId, projType, freq));

//        Set<Object> keySet = commonLevelDtoList.get(0).getProperties().keySet();
//        List<Integer> keyList = new ArrayList<Integer>();

//        for (Object key : keySet) {
//            keyList.add(Integer.parseInt(String.valueOf(key)));
//
//        }
//        Collections.sort(keyList);
//
//        for (Integer key : keyList) {
//
//            fromPeriod.addItem(key);
//            toPeriod.addItem(key);
//        }
        return mainLayout;
    }
    
}
