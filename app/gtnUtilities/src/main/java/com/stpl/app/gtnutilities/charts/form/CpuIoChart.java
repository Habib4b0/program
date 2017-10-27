/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.form;

import com.stpl.app.gtnutilities.charts.logic.SearchLogic;
import com.stpl.app.gtnutilities.util.CommonMethods;
import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.jboss.logging.Logger;

public class CpuIoChart extends VerticalLayout {

    PopupDateField selecteDate = new PopupDateField();
    Button runNowBtn = new Button("Run Now");
    Chart cpuChart;
    Chart ioChart;
    HorizontalLayout fieldLayout = new HorizontalLayout();
    SearchLogic searchLogic = SearchLogic.getInstance();
    CheckBox showAll = new CheckBox(Constants.SHOW_ALL);
    final ComboBox itemsPerPageSelect = new ComboBox();
    TextField showValuesGreaterThan = new TextField();
    Date lastSelectedDate = CommonMethods.getYesterdayDate();
    PlotOptionsColumn dropOptions = new PlotOptionsColumn();
        /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CpuIoChart.class);

    CpuIoChart() {
        addFields();
        addCharts();
    }

    void configure() {
        selecteDate.setResolution(Resolution.SECOND);
        selecteDate.setDateFormat(Constants.Hour_Date_Format);
        selecteDate.setValue(CommonMethods.getYesterdayDate());
        selecteDate.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                runNowBtn.setVisible(true);
                if (!DateUtils.isSameDay(selecteDate.getValue(), new Date())) {
                    runNowBtn.setVisible(false);
                    if (!DateUtils.isSameDay(selecteDate.getValue(), lastSelectedDate)) {
                        selecteDate.setValue(CommonMethods.changeTime(selecteDate.getValue(), NumericConstants.TWENTY_THREE));
                    }
                }
                refreshCharts(showValuesGreaterThan.getValue());
                lastSelectedDate = selecteDate.getValue();
            }
        });
        runNowBtn.setVisible(false);
        runNowBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                callJob("BPIGTN_GAL_APP_UNIT");
                refreshCharts(showValuesGreaterThan.getValue());
                searchLogic.selectedDate = selecteDate.getValue();
            }
        });
        showAll.setValue(Boolean.FALSE);
        Property.ValueChangeListener refreshOnValueChange = new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                refreshCharts(showValuesGreaterThan.getValue());
            }
        };
        showAll.addValueChangeListener(refreshOnValueChange);
        loadItemperPage();
        itemsPerPageSelect.addValueChangeListener(refreshOnValueChange);
        showValuesGreaterThan.setWidth("75px");
        showValuesGreaterThan.setValue(Constants.EMPTY);
        showValuesGreaterThan.setImmediate(true);
        showValuesGreaterThan.addTextChangeListener(new FieldEvents.TextChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                refreshCharts(event.getText());
            }
        });
        showValuesGreaterThan.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);

    }

    Chart getChart(String chartType) {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        conf.setTitle(chartType + "  Usage");
        conf.setSubTitle(String.valueOf(selecteDate.getValue()));
        if (chartType.equals(Constants.IO)) {
            Title title = conf.getTitle();
            Style style = new Style();
            style.setColor(SolidColor.LIMEGREEN);
            title.setStyle(style);
        }
        Tooltip tooltip = conf.getTooltip();
        tooltip.setPointFormat("{series.name}: {point.y} B€");
        YAxis yAxis = conf.getyAxis();
        yAxis.setTitle(chartType.equals(Constants.CPU) ? "Percentange" : "MB");
        return chart;
    }

    Configuration setChartData(Configuration conf, String type, String textValue) {
        conf.setSubTitle(String.valueOf(selecteDate.getValue()));
        XAxis xAxis = conf.getxAxis();
        List<Object[]> resultList = searchLogic.fetchCPUIODataFromDB(type, selecteDate.getValue());;
        String showValuesGreater = textValue;
        List<String> dbList = new ArrayList<>();
        List<BigDecimal> values = new ArrayList<>();
        for (int i = NumericConstants.ZERO; resultList != null && i < resultList.size(); i++) {
            if (!showAll.getValue() && itemsPerPageSelect.getValue() != null && Integer.valueOf(itemsPerPageSelect.getValue().toString()) == i) {
                break;
            }
            Object[] object = resultList.get(i);
            BigDecimal value = (object[NumericConstants.ONE] == null ? BigDecimal.ZERO : (BigDecimal) object[NumericConstants.ONE]);
            if (!showAll.getValue() && (showValuesGreater != null && !showValuesGreater.isEmpty() && Double.valueOf(showValuesGreater) > value.doubleValue())) {
                continue;
            }
            dbList.add(String.valueOf(object[ NumericConstants.ZERO]));
            values.add(value);
        }
        String[] sdbListArr = new String[dbList.size()];
        BigDecimal[] valuesListArr = new BigDecimal[values.size()];
        xAxis.setCategories(dbList.toArray(sdbListArr));

        ListSeries dataSeries = new ListSeries(type, values.toArray(valuesListArr));
        if (type.equals(Constants.IO)) {
            dropOptions.setColor(SolidColor.LIMEGREEN);
            dataSeries.setPlotOptions(dropOptions);
        }
        conf.setSeries(dataSeries);
        return conf;
    }

    void callJob(String database) {
        try (Connection connection = searchLogic.connectToDB(); Statement statment = connection.createStatement();) {
            String sqlDate = new SimpleDateFormat(Constants.yyyy_MM_dd_HH_mm_ss).format(selecteDate.getValue());
            String query = String.format("EXEC [PRC_PERFORMANCE_DASHBOARD] '%s', '%s'", database, sqlDate);
            statment.executeUpdate(query);
        } catch (Exception ex) {
            LOGGER.debug(ex);
        }
    }

    private void addFields() {
        addComponent(new Label(Constants.SPACE));
        fieldLayout.addComponent(new Label(Constants.SELECT_TIME));
        fieldLayout.addComponent(selecteDate);
        fieldLayout.addComponent(runNowBtn);
        fieldLayout.addComponent(showAll);
        fieldLayout.addComponent(new Label(" Show Top"));
        fieldLayout.addComponent(itemsPerPageSelect);
        fieldLayout.addComponent(new Label("Show Values >"));
        fieldLayout.addComponent(showValuesGreaterThan);
        addComponent(fieldLayout);
        configure();
    }

    private void addCharts() {
        cpuChart = getChart(Constants.CPU);
        setChartData(cpuChart.getConfiguration(), Constants.CPU, Constants.EMPTY);
        addComponent(cpuChart);
        ioChart = getChart(Constants.IO);
        setChartData(ioChart.getConfiguration(), Constants.IO, Constants.EMPTY);
        addComponent(ioChart);
    }

    private void refreshCharts(String textValue) {
        setChartData(cpuChart.getConfiguration(), Constants.CPU, textValue);
        setChartData(ioChart.getConfiguration(), Constants.IO, textValue);
        cpuChart.drawChart();
        ioChart.drawChart();
    }

    private void loadItemperPage() {

        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.select(NumericConstants.FIFTEEN);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth("70px");
        for (int i = NumericConstants.FIVE; i <= NumericConstants.HUNDRED; i = i + NumericConstants.FIVE) {
            itemsPerPageSelect.addItem(i);
        }
        itemsPerPageSelect.select(NumericConstants.TWENTY);
    }

}
