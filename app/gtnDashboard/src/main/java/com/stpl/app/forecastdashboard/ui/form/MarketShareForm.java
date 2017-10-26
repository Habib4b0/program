package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.logic.MarketShareLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.stpl.portal.kernel.exception.SystemException;
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
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class MarketShareForm extends CustomComponent implements View {

    /* The Logic class */
    MarketShareLogic logic = new MarketShareLogic();

    ComboBox yearDdlb = new ComboBox();

    Chart segmentationChart = new Chart(ChartType.PIE);
    Chart chart = new Chart();
    String chartType = "Bar Chart";
    VerticalLayout layout = new VerticalLayout();
    VerticalLayout chartLayout = new VerticalLayout();
    /* The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(MarketShareLogic.class);
    private final OptionGroup chartoptions = new OptionGroup();
    HorizontalLayout chartOptionLayout = new HorizontalLayout();
    final Label label = new Label("Chart Options : ");

    public MarketShareForm() throws SystemException, Exception {
        super();
        init();
        configureFields();
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws SystemException, Exception {
        setCompositionRoot(addToContent());
    }

    /**
     * Adds the to content.
     *
     * @return the component
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private Component addToContent() {
        LOGGER.info("Entering MS addToContent ");
        Panel panel = new Panel();

        yearDdlb.addItem("All Years");
        yearDdlb.setNullSelectionAllowed(false);
        yearDdlb.addItems(logic.getYearList());
        yearDdlb.select("All Years");
        chartoptions.setMultiSelect(false);
        chartoptions.setImmediate(true);
        chartoptions.addStyleName("horizontal");
        chartoptions.addItems("Bar Chart", "Line Chart", "Pie Chart");
        chartoptions.select("Bar Chart");

        HorizontalLayout hLayout = new HorizontalLayout();

        Label yearLabel = new Label("Year :");
        CommonUtils.formatLabel(yearLabel);

        hLayout.addComponent(yearLabel);
        hLayout.addComponent(yearDdlb);

        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(hLayout);
        chartLayout.addComponent(getChart());
        layout.addComponent(chartLayout);
        CommonUtils.formatLabel(label);
        chartOptionLayout.addComponent(label);
        chartOptionLayout.addComponent(chartoptions);
        chartOptionLayout.setSpacing(true);
        chartOptionLayout.setMargin(true);
        layout.addComponent(chartOptionLayout);
        panel.setContent(layout);

        final VerticalLayout content = new VerticalLayout();
        content.addStyleName("mainLayout");
        content.addComponent(panel);

        LOGGER.info("Ending MS addToContent");
        return content;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Adds the Market Share Chart.
     *
     * @return the panel
     */
    private Component addMarketShareChart() {
        LOGGER.info("Entering MS addMarketShareChart");

        Configuration conf = segmentationChart.getConfiguration();
        conf.setExporting(Boolean.TRUE);
        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(2);
        tooltip.setPointFormat("{point.percentage:%.2f}%");
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        conf.setPlotOptions(plotOptions);
        segmentationChart.setHeight(531, Unit.PIXELS);
        drawChart(0);
        conf.setTitle("Sales");

        LOGGER.info("Ending MS addMarketShareChart");
        return segmentationChart;

    }

    /**
     * Configure fields.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void configureFields() throws SystemException, Exception {

        yearDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String selectedYear = String.valueOf(event.getProperty().getValue());
                if ("All Years".equals(selectedYear)) {
                    selectedYear = "0";
                }
                if ("Pie Chart".equals(chartType)) {
                    drawChart(Integer.valueOf(selectedYear));
                } else {
                    yearDdlb.setEnabled(false);
                    chartLayout.removeAllComponents();
                    chartLayout.addComponent(getChart());
                }
            }
        });

        chartoptions.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                chartType = String.valueOf(event.getProperty().getValue());

                if ("Pie Chart".equals(chartType)) {
                    String selectedYear = String.valueOf(yearDdlb.getValue());
                    if ("All Years".equals(selectedYear)) {
                        selectedYear = "0";
                    }
                    chartLayout.removeAllComponents();
                    chartLayout.addComponent(addMarketShareChart());
                    drawChart(Integer.valueOf(selectedYear));
                    yearDdlb.setEnabled(true);
                } else {
                    yearDdlb.select("All Years");
                    yearDdlb.setEnabled(false);
                    chartLayout.removeAllComponents();
                    chartLayout.addComponent(getChart());
                }

            }
        });
    }

    private void drawChart(int year) {

        
        List datalist = logic.getActualsData(year, chartType);
        DataSeries series = new DataSeries();
        series.setData((String[]) datalist.get(0), (BigDecimal[]) datalist.get(1));
        Configuration conf = segmentationChart.getConfiguration();
        conf.setExporting(Boolean.TRUE);

        conf.setSeries(series);
        conf.setTitle("Sales");
        segmentationChart.drawChart(conf);

    }

    protected Component getChart() {
        
        Set<String> uniqueContract = new HashSet<String>();
        List<String> uniqueContract2 = new ArrayList<String>();
        chart = new Chart();
        Configuration configuration = chart.getConfiguration();
        configuration.setExporting(Boolean.TRUE);
        if ("Line Chart".equals(chartType)) {

            configuration.getChart().setType(ChartType.LINE);
        } else {
            configuration.getChart().setType(ChartType.COLUMN);
        }
        configuration.setTitle("Sales");

        List<String> yearsList = logic.getYearList();
        for (int i = 0; i < yearsList.size(); i++) {
            uniqueContract2.add(String.valueOf(yearsList.get(i)));
        }
        configuration.getxAxis().setCategories(Arrays.copyOf(uniqueContract2.toArray(), uniqueContract2.toArray().length, String[].class));
        configuration.getxAxis().setMax(uniqueContract2.size() - 1);

        Axis yAxis = configuration.getyAxis();
        yAxis.setMin(-5d);
        yAxis.setTitle(new Title("Amount($)"));

        yAxis.getTitle().setVerticalAlign(VerticalAlign.HIGH);

        final XAxis xAxis = configuration.getxAxis();
        xAxis.setTitle("Years");

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
        List<Object[]> dataList = logic.getActualsData(0, chartType);
        List<Number> data = new ArrayList<Number>();
        String uniqueContract1 = new String();

        for (int i = 0; i < dataList.size(); i++) {
            Object obj[] = dataList.get(i);
            if (uniqueContract.add(obj[0].toString())) {
                uniqueContract1 = String.valueOf(obj[0]);
                if (!data.isEmpty()) {
                    ls.setData(data);
                    ls.setName(dataList.get(i - 1)[0].toString());
                    configuration.addSeries(ls);
                    ls = new ListSeries();
                    data = new ArrayList();
                    data.add(Double.valueOf(obj[1].toString()));
                } else {
                    data.add(Double.valueOf(obj[1].toString()));
                }

            } else {
                ls.setName(uniqueContract1);
                data.add(Double.valueOf(obj[1].toString()));
            }
        }
        if (!data.isEmpty()) {
            ls.setData(data);
            ls.setName(uniqueContract1);
            configuration.addSeries(ls);
            ls = new ListSeries();
            data = new ArrayList();
        }

        chart.setHeight(531, Unit.PIXELS);
        chart.drawChart(configuration);
        return chart;
    }

}
