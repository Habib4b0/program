/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Lokeshwari
 */
public class SPRChart {

    List<CommonLevelDto> commonLevelDtoList = new ArrayList<CommonLevelDto>();
    VerticalLayout mainLayout = new VerticalLayout();
    Chart chart ;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SPRChart.class);

    private int fromDate = 0;
    private int toDate = 0;
    private int projId=0;
    private String projType;
    private String freqToShow = StringUtils.EMPTY;
    private boolean isStack;
     List<String> xasis = new ArrayList<String>();
     ChartType chartType;
     String freqValue=StringUtils.EMPTY;
     
    //ProjectionSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public SPRChart(int projId,String projType) {
        this.projId = projId;
        this.projType=projType;

       
    }
    
    public SPRChart(int projId,String projType, List<CommonLevelDto> list, List<String> xasis,ChartType chartType,boolean isStack) {
        this.projId = projId;
        this.projType=projType;
        this.commonLevelDtoList = list;
         this.xasis.addAll(xasis);
          this.chart = new Chart(chartType);
          this.chartType=chartType;
          this.isStack = isStack;
    }
    
    public Component getPieChart(Configuration configuration,Chart chart){
        configuration.setTitle("Sales");
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
        String salesArgu=" Isnull(Sum(FD.CONTRACT_SALES_ACTUALS),0)+Isnull(Sum(FD.CONTRACT_SALES_PROJECTED),0)";
        List<Object[]> dataList = logic.getPieChart(projId,freqValue,salesArgu);
        String year = "";
        List<String> categoryNamesList = new ArrayList<String>();
        List<Double> valuesList = new ArrayList<Double>();

        for (Object[] obj : dataList) {
            System.out.println("String.valueOf(obj[1]):::::::::======>"+String.valueOf(obj[1]));
            System.out.println("String.valueOf(obj[0]):::::::::======>"+String.valueOf(obj[0]));
            String yearValue = String.valueOf(obj[1]);
            if (CommonUtils.SEMI_ANNUAL.equals(freqToShow)) {
                yearValue = "S" + String.valueOf(obj[2]) + " " + String.valueOf(obj[1]);
            } else if (CommonUtils.QUARTERLY.equals(freqToShow)) {
                yearValue = "Q" + String.valueOf(obj[2]) + " " + String.valueOf(obj[1]);
            } else if (CommonUtils.MONTHLY.equals(freqToShow)) {
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

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart(int from, int to) {
        LOGGER.info("Entering getChart method "+chartType);
        //  List<CommonLevelDto> commonLevelDtoList = new ArrayList<CommonLevelDto>();
        chart = new Chart(chartType);

         final Configuration conf = chart.getConfiguration();
          conf.setExporting(Boolean.TRUE);
         if("pie".equalsIgnoreCase(String.valueOf(chartType))){
         return getPieChart(conf,chart);
         }
         else{
        Set<Object> keySet = new HashSet<Object>();
        List<String> xSet = new ArrayList<String>();
        ListSeries listSeries1 = new ListSeries();
        ListSeries listSeries2 = new ListSeries();
        ListSeries listSeries3 = new ListSeries();
        ListSeries listSeries4 = new ListSeries();
        ListSeries listSeries5 = new ListSeries();
        ListSeries listSeries6 = new ListSeries();
//        keySet = commonLevelDtoList.get(0).getProperties().keySet();
//        List<String> keyValue=(List<String>)commonLevelDtoList.get(0).getxAxis();
        List<String> keyList = new ArrayList<String>(xasis);
//        for (Object key : keySet) {
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
//            } else if(from==0&&to==0) {
//                keyList.add(String.valueOf(key));
//            }

//        }

//        Collections.sort(keyList);

        for (String key : keyList) {
            System.out.println("SPR Key:=======================>"+key);
            xSet.add(String.valueOf(key));
        }
            System.out.println("commonLevelDtoList:===========================>"+commonLevelDtoList.size());
        if(commonLevelDtoList.size()>0){
        for (Object key : xSet) {
//            if ("Mandated".equalsIgnoreCase(projType)) {
                listSeries1.addData(Double.valueOf(!"null".equalsIgnoreCase((String)commonLevelDtoList.get(0).getPropertyValue(key)) ? (String) commonLevelDtoList.get(0).getPropertyValue(key) : "0"));
                listSeries2.addData(Double.valueOf(!"null".equalsIgnoreCase((String) commonLevelDtoList.get(1).getPropertyValue(key)) ? (String) commonLevelDtoList.get(1).getPropertyValue(key) : "0"));
                listSeries3.addData(Double.valueOf(!"null".equalsIgnoreCase((String) commonLevelDtoList.get(2).getPropertyValue(key)) ? (String) commonLevelDtoList.get(2).getPropertyValue(key) : "0"));
                listSeries4.addData(Double.valueOf(!"null".equalsIgnoreCase((String) commonLevelDtoList.get(3).getPropertyValue(key)) ? (String) commonLevelDtoList.get(3).getPropertyValue(key) : "0"));
//            }
//            else if ("Non Mandated".equalsIgnoreCase(projType)) {
//                listSeries1.addData(Double.valueOf(!"null".equalsIgnoreCase((String) commonLevelDtoList.get(1).getPropertyValue(key)) ? (String) commonLevelDtoList.get(1).getPropertyValue(key) : "0"));
//                listSeries2.addData(Double.valueOf(!"null".equalsIgnoreCase((String) commonLevelDtoList.get(2).getPropertyValue(key)) ? (String) commonLevelDtoList.get(2).getPropertyValue(key) : "0"));
//                listSeries3.addData(Double.valueOf(!"null".equalsIgnoreCase((String) commonLevelDtoList.get(3).getPropertyValue(key)) ? (String) commonLevelDtoList.get(3).getPropertyValue(key) : "0"));
//                listSeries4.addData(Double.valueOf(!"null".equalsIgnoreCase((String) commonLevelDtoList.get(4).getPropertyValue(key)) ? (String) commonLevelDtoList.get(4).getPropertyValue(key) : "0"));
//            }
//            listSeries5.addData(Double.valueOf((String) commonLevelDtoList.get(4).getPropertyValue(key)));
//            listSeries6.addData(Double.valueOf((String) commonLevelDtoList.get(5).getPropertyValue(key)));

        }
        }
     
        
        listSeries1.setName("GTS Actuals");
        listSeries2.setName("GTS Projected");
        listSeries3.setName("Contract Sales Actuals");
        listSeries4.setName("Contract Sales Projected");
//        listSeries5.setName("Contract Units Actuals");
//        listSeries6.setName("Contract Units Projected");


       
        conf.setTitle("Sales");
        conf.disableCredits();
     

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
//        conf.addSeries(listSeries5);
//        conf.addSeries(listSeries6);

        conf.addxAxis(xAxis);

        chart.drawChart(conf);

        LOGGER.info("End of getChart method ");

        return chart;
         }
    }

    
    public Component configureFields() {
try{
        final CommonLogic logic = new CommonLogic();
        final ChartCommonLogic chartlogic = new ChartCommonLogic();
//        commonLevelDtoList = logic.getProjectionResultChartData(projId,projType,"ANNUAL");

        HorizontalLayout subLayout = new HorizontalLayout();
        final ComboBox fromPeriod = new ComboBox();
        final ComboBox toPeriod = new ComboBox();
        final ComboBox frequency = new ComboBox();
        fromPeriod.addItem("-Select One-");
        fromPeriod.setNullSelectionItemId("-Select One-");
        toPeriod.addItem("-Select One-");
        toPeriod.setNullSelectionItemId("-Select One-");
        
        frequency.addItem("-Select One-");
        frequency.setNullSelectionItemId("-Select One-");
        frequency.addItem(CommonUtils.ANNUALLY);
        frequency.addItem(CommonUtils.SEMI_ANNUALLY);
        frequency.addItem(CommonUtils.QUARTERLY);
        frequency.addItem(CommonUtils.MONTHLY);
        frequency.select(CommonUtils.ANNUALLY);//did here
        frequency.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    String frequency = String.valueOf(event.getProperty().getValue());
                    if(CommonUtils.ANNUALLY.equals(frequency)) {
                        freqToShow = CommonUtils.ANNUAL;
                        freqValue=CommonUtils.ANNUAL;
                    } else if(CommonUtils.SEMI_ANNUALLY.equals(frequency)) {
                        freqToShow = CommonUtils.SEMI_ANNUAL;
                        freqValue="SEMI_ANNUAL";
                    } else if(CommonUtils.QUARTERLY.equals(frequency)) {
                        freqToShow = CommonUtils.QUARTERLY;
                        freqValue="QUARTER";
                    } else {
                        freqToShow = CommonUtils.MONTHLY;
                        freqValue="MONTH";
                    }
//                    commonLevelDtoList = logic.getProjectionResultChartData(projId,projType,freqToShow);
                    List list=chartlogic.getResults(projId,freqToShow,projType);
                    
                    if("Non Mandated".equals(projType)){
                    commonLevelDtoList = (List<CommonLevelDto>) list.get(1);//did here
                    xasis=(List<String>)list.get(2);
                    }else{
                     commonLevelDtoList = (List<CommonLevelDto>) list.get(0);//did here
                    xasis=(List<String>)list.get(1);
                    }
                     
                } 
             
                mainLayout.removeComponent(chart);
                mainLayout.addComponent(getChart(fromDate, toDate));
            }
        });
        freqValue=CommonUtils.ANNUAL;
//
//        fromPeriod.addValueChangeListener(new Property.ValueChangeListener() {
//
//            @Override
//            public void valueChange(Property.ValueChangeEvent event) {
//                if (event.getProperty().getValue() != null) {
//                    fromDate = Integer.parseInt(String.valueOf(event.getProperty().getValue()));
//                } else {
//                    fromDate = 0;
//                }
//                if (toDate != 0) {
//                    if (fromDate > toDate) {
//                        fromPeriod.select("-Select One-");
//                    }
//                }
//
//                mainLayout.removeComponent(chart);
//                mainLayout.addComponent(getChart(fromDate, toDate));
//
//            }
//        });
//        toPeriod.addValueChangeListener(new Property.ValueChangeListener() {
//
//            @Override
//            public void valueChange(Property.ValueChangeEvent event) {
//                if (event.getProperty().getValue() != null) {
//                    toDate = Integer.parseInt(String.valueOf(event.getProperty().getValue()));
//                } else {
//                    toDate = 0;
//                }
//                if (toDate != 0) {
//                    if (toDate < fromDate) {
//                        toPeriod.select("-Select One-");
//                    }
//                }
//
//                mainLayout.removeComponent(chart);
//                mainLayout.addComponent(getChart(fromDate, toDate));
//            }
//        });
        
//        Label fromLabel = new Label("From Period : ");
//        CommonUtils.formatLabel(fromLabel);
//        Label toLabel = new Label("To Period : ");
//        CommonUtils.formatLabel(toLabel);
        Label frequencyLabel = new Label("Frequency : ");
        CommonUtils.formatLabel(frequencyLabel);
        
//        subLayout.addComponent(fromLabel);
//        subLayout.addComponent(fromPeriod);
//        subLayout.addComponent(toLabel);
//        subLayout.addComponent(toPeriod);
        subLayout.addComponent(frequencyLabel);
        subLayout.addComponent(frequency);
        subLayout.setSpacing(true);
        subLayout.setMargin(true);

        mainLayout.addComponent(subLayout);
        mainLayout.addComponent(getChart(fromDate, toDate));
//        Set<Object> keySet = commonLevelDtoList.get(0).getProperties().keySet();
//        List<Integer> keyList = new ArrayList<Integer>();
        
//       for (Object key : keySet) {
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
//        
        }catch(Exception e)
        {
         LOGGER.info(e);
        }
        return mainLayout;
    }
}
