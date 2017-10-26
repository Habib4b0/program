/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.logic.ForeCastVsActualsLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class ForecastVsActuals extends VerticalLayout implements View {

    ForeCastVsActualsLogic logic = new ForeCastVsActualsLogic();
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastVsActuals.class);
    String[] yearsList;
    VerticalLayout mainLayout = new VerticalLayout();
    List<Object[]> commonLevelDtoList = new ArrayList<Object[]>();
    Chart chart = new Chart();
    TabSheet tabSheet = new TabSheet();
    VerticalLayout salesLayout = new VerticalLayout();
    VerticalLayout discountLayout = new VerticalLayout();
    VerticalLayout resultsLayout = new VerticalLayout();
    private String fType = CommonUtils.COMMERCIAL;
    private String frequency = CommonUtils.QUARTER;
    private String chartOption = CommonUtils.BAR_CHART;
    private String tabName = CommonUtils.SALES;
    @Override
    public String getDescription() {
        return "Basic Line With Data Labels";
    }

    public ForecastVsActuals() {
        init();
    }

    private void init() {
//        setSpacing(true);
//        MarginInfo margininfo = new MarginInfo(false, true, false, true);
//        setMargin(margininfo);
        Panel panel = new Panel();
        panel.setContent(configureFields());
        addComponent(panel);
        addStyleName("mainLayout");
    }

    
    protected Component getChart(String forecastType, String selectedTab) {
        
        Set<String> uniqueYear = new HashSet<String>();
        chart = new Chart();
//        chart.setHeight("450px");
//        chart.setWidth("60%");

        Configuration configuration = chart.getConfiguration();
        configuration.setExporting(Boolean.TRUE);
         if(selectedTab.equalsIgnoreCase(CommonUtils.SALES)){
            configuration.setTitle(CommonUtils.SALES);
        }else if(selectedTab.equalsIgnoreCase(CommonUtils.DISCOUNT)){
                configuration.setTitle(CommonUtils.DISCOUNT);
        }else if(selectedTab.equalsIgnoreCase("Net Sales & Profitability")){
             configuration.setTitle("Net Sales & Profitability");
        }
         if(CommonUtils.LINE_CHART.equals(chartOption)){
        configuration.getChart().setType(ChartType.LINE);
         }else if(CommonUtils.BAR_CHART.equals(chartOption)){
             configuration.getChart().setType(ChartType.COLUMN);  
         }else{
             configuration.getChart().setType(ChartType.PIE);  
         }
//        configuration.getChart().setMarginRight(130);
//        configuration.getChart().setMarginBottom(25);
        setSpacing(Boolean.TRUE);
//        configuration.getSubTitle().setText("Source: WorldClimate.com");
        if(!CommonUtils.PIE_CHART.equals(chartOption)){
        yearsList = getYears();
        if(frequency.equals(CommonUtils.QUARTER) || frequency.equals(CommonUtils.ANNUAL1)  ){
        configuration.getxAxis().setCategories("Q1", "Q2", "Q3", "Q4");
         configuration.getxAxis().setMax(3);
        }else if(frequency.equals(CommonUtils.SEMIANNUAL)){
         configuration.getxAxis().setCategories("S1", "S2");
          configuration.getxAxis().setMax(1);
        
        }else if(frequency.equals(CommonUtils.MONTH)){
         configuration.getxAxis().setCategories("Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec");
          configuration.getxAxis().setMax(11);
        }
        Axis yAxis = configuration.getyAxis();
        yAxis.setMin(-5d);
        if(tabName.equals(CommonUtils.DISCOUNT)){
        yAxis.setTitle(new Title("Discount($)"));
        }else{
          yAxis.setTitle(new Title("Amount($)"));  
        }

        yAxis.getTitle().setVerticalAlign(VerticalAlign.HIGH);


        final XAxis xAxis = configuration.getxAxis();
       
         if(frequency.equals(CommonUtils.QUARTER) || frequency.equals(CommonUtils.ANNUAL1)  ){
         xAxis.setTitle("Quarters");
        }else if(frequency.equals(CommonUtils.SEMIANNUAL)){
         xAxis.setTitle("Semi-Annuals");
        }else if(frequency.equals(CommonUtils.MONTH)){
          xAxis.setTitle("Months");
        }
        }else{
            PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        configuration.setPlotOptions(plotOptions);
        chart.setHeight(489,Unit.PIXELS);
       
        }
        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(2);
        if(!CommonUtils.PIE_CHART.equals(chartOption)){
        tooltip.setPointFormat("{series.name}: $ {point.y:%.2f}");
        }else{
        tooltip.setPointFormat("{point.percentage:%.2f}%");
        }
        configuration.setTooltip(tooltip);
          if(!CommonUtils.PIE_CHART.equals(chartOption)){
        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);
        legend.setShadow(true);

//        PlotOptionsLine po = new PlotOptionsLine();
//        po.setDashStyle(DashStyle.SHORTDASH);s
        configuration.setLegend(legend);
//        configuration.setPlotOptions(po);
        
        ListSeries ls = new ListSeries();
        List<Object[]> dataList = logic.getResults(yearsList,forecastType);
        List<Number> data = new ArrayList<Number>();
        String year="";
        for (int i = 0; i < dataList.size(); i++) {
            Object obj[] = dataList.get(i);
            year=obj[2].toString();
            if (uniqueYear.add(obj[2].toString())) {
                if (!data.isEmpty()) {
                    ls.setData(data);
                    ls.setName(dataList.get(i-1)[2].toString());
                    configuration.addSeries(ls);
                    ls = new ListSeries();
                    data = new ArrayList();
                    data.add(Double.valueOf(obj[0].toString()));
                } else {
                    data.add(Double.valueOf(obj[0].toString()));
                }

            } else {
                data.add(Double.valueOf(obj[0].toString()));
            }
        }
        if (!data.isEmpty()) {
            ls.setData(data);
            ls.setName(year);
            configuration.addSeries(ls);
            ls = new ListSeries();
            data = new ArrayList();
        }
         chart.setHeight(489, Unit.PIXELS);
         chart.drawChart(configuration);
          }else{
        List<Object[]> dataList = logic.getResults(yearsList,forecastType);
         String year="";
         List<String> categoryNamesList = new ArrayList<String>();
        List<Double> valuesList = new ArrayList<Double>();

        for (Object[] obj : dataList) {
            categoryNamesList.add(String.valueOf(obj[1]));
            valuesList.add(new Double(String.valueOf(obj[0])));
        }
        dataList.add(0, categoryNamesList.toArray(new String[0]));
        dataList.add(1, valuesList.toArray(new Double[0]));
        DataSeries series = new DataSeries();
         for (int i = 0; i < dataList.size(); i++) {
             Object obj[] = dataList.get(i);
                year=obj[1].toString();
            if (uniqueYear.add(obj[1].toString())) {
        series.setData((String[]) dataList.get(0), (Double[]) dataList.get(1));
            }
         }
        Configuration conf = chart.getConfiguration();
        conf.setSeries(series);
        chart.drawChart(conf);
          }
       
        return chart;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    
    public Component configureFields(){
        HorizontalLayout subLayout = new HorizontalLayout();
        HorizontalLayout chartOptionLayout = new HorizontalLayout();
        HorizontalLayout subLayout3 = new HorizontalLayout();
        HorizontalLayout subLayout2 = new HorizontalLayout();
        VerticalLayout subLayout4 = new VerticalLayout();
        final ComboBox forecastType = new ComboBox();
        final Label label = new Label("Chart Options : ");
        final Label fromLabel = new Label("Forecast Type : ");
        final Label frequencyLabel = new Label("Frequency : ");
        final ComboBox forecastFrequency = new ComboBox();
        final OptionGroup chartType = new OptionGroup();
        chartType.addStyleName("horizontal");
        forecastType.setNullSelectionAllowed(false);
        forecastType.addItem(CommonUtils.COMMERCIAL);
        forecastType.addItem(CommonUtils.GOVERNMENT);
        forecastType.select(CommonUtils.COMMERCIAL);
        forecastFrequency.setNullSelectionAllowed(false);
        forecastFrequency.addItem(CommonUtils.SEMIANNUAL);
        forecastFrequency.addItem(CommonUtils.QUARTER);
        forecastFrequency.addItem(CommonUtils.MONTH);
        forecastFrequency.select(CommonUtils.QUARTER);
        chartType.addItems(CommonUtils.BAR_CHART,CommonUtils.LINE_CHART,CommonUtils.PIE_CHART);
        chartType.select(CommonUtils.BAR_CHART);
        CommonUtils.formatLabel(fromLabel);
        subLayout.addComponent(fromLabel);
        subLayout.addComponent(forecastType);
        CommonUtils.formatCustomLabel(frequencyLabel);
        subLayout3.addComponent(frequencyLabel);
        subLayout3.addComponent(forecastFrequency);
        subLayout2.addComponent(subLayout);
        subLayout2.addComponent(subLayout3);
        subLayout2.addStyleName("forecast-top-layout");
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        subLayout4.addComponent(subLayout2);
        subLayout.setSpacing(true);
        subLayout.setMargin(true);
        subLayout3.setSpacing(true);
        subLayout3.setMargin(true);
        mainLayout.setSpacing(false);
        mainLayout.setMargin(true);
        mainLayout.addComponent(subLayout4);
        
        salesLayout.addComponent(new Label(""));
        salesLayout.addComponent(getChart(fType,tabName));
        
        discountLayout.addComponent(new Label(""));
        discountLayout.addComponent(getChart(fType,tabName));

        tabSheet.addTab(salesLayout, "Sales");
        tabSheet.addTab(discountLayout, "Discount");
        tabSheet.addTab(resultsLayout, "Net Sales & Profitability");
        CommonUtils.formatLabel(label);
        chartOptionLayout.addComponent(label);
        chartOptionLayout.addComponent(chartType);
        chartOptionLayout.setSpacing(true);
        chartOptionLayout.setMargin(true);
        mainLayout.addComponent(tabSheet);
        mainLayout.addComponent(chartOptionLayout);
        
        tabSheet.addListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabName = tab.getCaption();
                logic.setTabName(tabName);
                salesLayout.removeAllComponents();
                discountLayout.removeAllComponents();
                resultsLayout.removeAllComponents();
                salesLayout.addComponent(getChart(fType,tabName));
                discountLayout.addComponent(getChart(fType,tabName));
                resultsLayout.addComponent(getChart(fType,tabName));
            }

        });
        forecastType.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    fType = String.valueOf(event.getProperty().getValue());
                } else {
                    fType = CommonUtils.COMMERCIAL;
                }
                salesLayout.removeAllComponents();
                discountLayout.removeAllComponents();
                resultsLayout.removeAllComponents();
                salesLayout.addComponent(getChart(fType,tabName));
                discountLayout.addComponent(getChart(fType,tabName));
                resultsLayout.addComponent(getChart(fType,tabName));
            }
        });
        forecastFrequency.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null && !"Annual".equals(String.valueOf(event.getProperty().getValue()))) {
                    frequency = String.valueOf(event.getProperty().getValue());
                } else {
                    frequency = CommonUtils.QUARTER;
                }
                logic.setFrequency(frequency);
                salesLayout.removeAllComponents();
                discountLayout.removeAllComponents();
                resultsLayout.removeAllComponents();
                salesLayout.addComponent(getChart(fType,tabName));
                discountLayout.addComponent(getChart(fType,tabName));
                resultsLayout.addComponent(getChart(fType,tabName));
            }
        });
        chartType.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    chartOption = String.valueOf(event.getProperty().getValue());
                } else {
                    chartOption = CommonUtils.BAR_CHART;
                }
                 logic.setChartType(chartOption);
                salesLayout.removeAllComponents();
                discountLayout.removeAllComponents();
                resultsLayout.removeAllComponents();
                if(CommonUtils.LINE_CHART.equals(chartOption)){
                salesLayout.addComponent(getChart(fType,tabName));
                discountLayout.addComponent(getChart(fType,tabName));
                resultsLayout.addComponent(getChart(fType,tabName));
                forecastFrequency.setEnabled(true);
                }else{
                    if(CommonUtils.PIE_CHART.equals(chartOption)){
                       forecastFrequency.select(CommonUtils.QUARTER);
                       forecastFrequency.setEnabled(false);
                       salesLayout.removeAllComponents();
                discountLayout.removeAllComponents();
                resultsLayout.removeAllComponents();
                salesLayout.addComponent(getChart(fType,tabName));
                discountLayout.addComponent(getChart(fType,tabName));
                resultsLayout.addComponent(getChart(fType,tabName));
                    }else{
                        forecastFrequency.setEnabled(true);
                        salesLayout.addComponent(getChart(fType,tabName));
                discountLayout.addComponent(getChart(fType,tabName));
                resultsLayout.addComponent(getChart(fType,tabName));
                    }
                
                }

            }
        });
       
        return mainLayout;
    }
    
    
    private String[] getYears() {
        Date date = new Date();
        int year = date.getYear();
        year = (year + 1900) - 2;
        String[] years = new String[4];
        for (int i = 0; i < 3; i++) {
            years[i] = String.valueOf(year);
            year++;
        }
        return years;
    }
    
        }
