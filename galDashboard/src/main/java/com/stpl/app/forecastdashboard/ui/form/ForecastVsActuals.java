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
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author srithar
 */
public class ForecastVsActuals extends VerticalLayout implements View {

    ForeCastVsActualsLogic logic = new ForeCastVsActualsLogic();
    String[] yearsList;
    VerticalLayout mainLayout = new VerticalLayout();
    
    Chart chart = new Chart();
    private String fType = CommonUtils.COMMERCIAL;
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

    protected Component getChart(String forecastType) {
        
        Set<String> uniqueYear = new HashSet<String>();
        chart = new Chart();
//        chart.setHeight("450px");
//        chart.setWidth("60%");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.LINE);
//        configuration.getChart().setMarginRight(130);
//        configuration.getChart().setMarginBottom(25);
        setSpacing(Boolean.TRUE);
        configuration.setTitle("");
//        configuration.getSubTitle().setText("Source: WorldClimate.com");
        yearsList = getYears();
        configuration.getxAxis().setCategories("Q1", "Q2", "Q3", "Q4");
        configuration.getxAxis().setMax(3);

        Axis yAxis = configuration.getyAxis();
        yAxis.setMin(-5d);
        yAxis.setTitle(new Title("Amount($)"));

        yAxis.getTitle().setVerticalAlign(VerticalAlign.HIGH);


        final XAxis xAxis = configuration.getxAxis();
        xAxis.setTitle("Quarters");

        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(2);
        tooltip.setPointFormat("{series.name}: $ {point.y:%.2f}");
        configuration.setTooltip(tooltip);
        
        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);
        legend.setShadow(true);

//        PlotOptionsLine po = new PlotOptionsLine();
//        po.setDashStyle(DashStyle.SHORTDASH);
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

        chart.setHeight(360, Unit.PIXELS);
        chart.drawChart(configuration);
        return chart;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    
    public Component configureFields(){
        HorizontalLayout subLayout = new HorizontalLayout();
        final ComboBox forecastType = new ComboBox();
        forecastType.setNullSelectionAllowed(false);
        forecastType.addItem(CommonUtils.COMMERCIAL);
        forecastType.addItem(CommonUtils.GOVERNMENT);
        forecastType.addItem(CommonUtils.RETURNS);
        forecastType.addItem(CommonUtils.ACCRUAL_RATE_PROJECTION);
        forecastType.select(CommonUtils.COMMERCIAL);

        forecastType.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    fType = String.valueOf(event.getProperty().getValue());
                } else {
                    fType = CommonUtils.COMMERCIAL;
                }
                

                mainLayout.removeComponent(chart);
                mainLayout.addComponent(getChart(fType));

            }
        });
        Label fromLabel = new Label("Forecast Type : ");
        CommonUtils.formatLabel(fromLabel);
        subLayout.addComponent(fromLabel);
        subLayout.addComponent(forecastType);
        
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.addComponent(subLayout);
        mainLayout.addComponent(getChart(fType));
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
