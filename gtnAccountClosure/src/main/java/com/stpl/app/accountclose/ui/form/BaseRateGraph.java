/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Maheshj
 */
public class BaseRateGraph extends CustomWindow {

    List<BaseRateDTO> dataList = new ArrayList<BaseRateDTO>();
    List<Object> columns = new ArrayList<Object>();
    List<String> headers = new ArrayList<String>();
    String frequency = StringUtils.EMPTY;

    public BaseRateGraph(List<BaseRateDTO> dataList, List<Object> columns, List<String> headers) {
        this.dataList = dataList;
        this.frequency = frequency;
        this.columns = columns;
        this.headers = headers;
        center();
        setWidth(100, Sizeable.Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-fe");
        setCaption("History/Projections");
        setClosable(true);
        setModal(true);
        inIt();
    }

    public void inIt() {

        final HorizontalLayout layout = new HorizontalLayout();
        final Chart chart = new Chart(ChartType.COLUMN);

        final Configuration conf = chart.getConfiguration();

        conf.setTitle(" History/Projections ");
        conf.disableCredits();
        conf.setExporting(Boolean.TRUE);

        final XAxis xAxis = new XAxis();

        final YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle("Values");
        conf.addyAxis(yAxis);

        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(100);
        legend.setY(70);
        legend.setFloating(true);
        legend.setShadow(true);
        conf.setLegend(legend);

        final Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+' '+ this.y ");
        conf.setTooltip(tooltip);

        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(1);
        plot.setBorderWidth(2);

        conf.addxAxis(xAxis);
        ListSeries listSeries1 = new ListSeries();
        ListSeries listSeries2 = new ListSeries();
        ListSeries listSeries3 = new ListSeries();
        ListSeries listSeries4 = new ListSeries();
        ListSeries listSeries5 = new ListSeries();
        ListSeries listSeries6 = new ListSeries();
        ListSeries listSeries7 = new ListSeries();
        ListSeries listSeries8 = new ListSeries();
        ListSeries listSeries9 = new ListSeries();
        ListSeries listSeries10 = new ListSeries();
        ListSeries listSeries11 = new ListSeries();
        ListSeries listSeries12 = new ListSeries();

        listSeries1.setName("Actual GTS");
        listSeries2.setName("Projected GTS");
        listSeries3.setName("Actual Payments");
        listSeries4.setName("Actual Rate");
        listSeries5.setName("Projected Payment");
        listSeries6.setName("Projected Rate");
        listSeries7.setName("Accrual");
        listSeries8.setName("Auto Accruals");
        listSeries9.setName("Manual Adjustments");
        listSeries10.setName("Payment True-Up");
        listSeries11.setName("Other");
        listSeries12.setName("Accrual Rate");

        String value = "0";

        List<String> finalHeader = new ArrayList<String>();
        for (Object obj : headers) {
            if (!String.valueOf(obj).equals(StringUtils.EMPTY)) {
                finalHeader.add(String.valueOf(obj));
                String asd = String.valueOf(obj).replace(" ", StringUtils.EMPTY);
                value = (String) dataList.get(0).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries1.addData(Double.valueOf(value));
                value = (String) dataList.get(1).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries2.addData(Double.valueOf(value));
                value = (String) dataList.get(2).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries3.addData(Double.valueOf(value));
                value = (String) dataList.get(3).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries4.addData(Double.valueOf(value));
                value = (String) dataList.get(4).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries5.addData(Double.valueOf(value));
                value = (String) dataList.get(5).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries6.addData(Double.valueOf(value));
                value = (String) dataList.get(6).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries7.addData(Double.valueOf(value));
                value = (String) dataList.get(7).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries8.addData(Double.valueOf(value));
                value = (String) dataList.get(8).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries9.addData(Double.valueOf(value));
                value = (String) dataList.get(9).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries10.addData(Double.valueOf(value));
                value = (String) dataList.get(10).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);
                listSeries11.addData(Double.valueOf(value));
                value = (String) dataList.get(11).getProperties().get(asd);
                value = value.replace('$', ' ');
                value = value.replace('%', ' ');
                value = value.replace(",", StringUtils.EMPTY);

                listSeries12.addData(Double.valueOf(value));

            }
        }
        xAxis.setCategories(finalHeader.toArray(new String[finalHeader.size()]));
        conf.addSeries(listSeries1);
        conf.addSeries(listSeries2);
        conf.addSeries(listSeries3);
        conf.addSeries(listSeries4);
        conf.addSeries(listSeries5);
        conf.addSeries(listSeries6);
        conf.addSeries(listSeries7);
        conf.addSeries(listSeries8);
        conf.addSeries(listSeries9);
        conf.addSeries(listSeries10);
        conf.addSeries(listSeries11);
        conf.addSeries(listSeries12);
        chart.drawChart(conf);

        setContent(chart);

    }
}
