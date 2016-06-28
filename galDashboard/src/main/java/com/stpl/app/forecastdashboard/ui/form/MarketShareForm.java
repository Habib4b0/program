package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.logic.MarketShareLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.util.List;
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

    /* The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(MarketShareLogic.class);

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
        yearDdlb.setNullSelectionItemId("All Years");
        yearDdlb.addItems(logic.getYearList());
        
        HorizontalLayout hLayout = new HorizontalLayout();
        
        Label yearLabel = new Label("Year :");
        CommonUtils.formatLabel(yearLabel);
        
        hLayout.addComponent(yearLabel);
        hLayout.addComponent(yearDdlb);
        
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(hLayout);
        layout.addComponent(addMarketShareChart());
        panel.setContent(layout);
        
        final VerticalLayout content = new VerticalLayout();
//        content.setSpacing(true);
//        MarginInfo margininfo = new MarginInfo(false, true, false, true);
//        content.setMargin(margininfo);
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
        conf.setTitle("");

        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(2);
        tooltip.setPointFormat("{point.percentage:%.2f}%");
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        conf.setPlotOptions(plotOptions);
        segmentationChart.setHeight(290,Unit.PIXELS);
        drawChart(0);
        
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
                if("null".equals(selectedYear)){
                    selectedYear = "0";
                }
                drawChart(Integer.valueOf(selectedYear));
            }
        });
    }

    private void drawChart(int year) {
        
        List datalist = logic.getActualsData(year);
        DataSeries series = new DataSeries();
        series.setData((String[]) datalist.get(0), (BigDecimal[]) datalist.get(1));
        Configuration conf = segmentationChart.getConfiguration();
        conf.setSeries(series);
        segmentationChart.drawChart(conf);
        
    }

}
