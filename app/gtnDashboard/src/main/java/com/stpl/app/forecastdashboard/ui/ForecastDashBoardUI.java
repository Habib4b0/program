package com.stpl.app.forecastdashboard.ui;

import com.stpl.app.model.ForecastConfig;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Group;
import com.stpl.portal.model.Organization;
import com.stpl.portal.model.User;
import com.stpl.portal.service.GroupLocalServiceUtil;
import com.stpl.portal.service.OrganizationLocalServiceUtil;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForecastDashBoardUI extends UI {

    /**
     * This method is used to register the navigations for different views.
     *
     * @param request the request
     */
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        ForecastConfig forecastConfig = getTimePeriod();
        Date startDate = forecastConfig.getFromDate();
        Date endDate = forecastConfig.getToDate();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String startDateString = formatter.format(startDate);
        String endDateString = formatter.format(endDate);

        final String serverUrl = Page.getCurrent().getLocation().toString();
        String[] splittedUrl = serverUrl.split("/", 0);
        splittedUrl[splittedUrl.length - 1] = "forecasting-configuration";

        String url = "";
        for (String s : splittedUrl) {
            if (!url.isEmpty()) {
                url += "\\";
            }
            url += s;
        }
//        System.out.println(" url " + url);
        Label forecastLabel = new Label("", ContentMode.HTML);
        forecastLabel.setValue("<div style=\"background: dodgerblue;color: white;text-align:center\"> Forecast Configuration Period : "
                + startDateString + " to " + endDateString + "&nbsp;&nbsp;<b><a href=\"" + url + "\" style=\"color: yellow;\">Click Here to change </a></b></div> ");
        Label urlLabel = new Label(url);

        Chart segmentationChart = new Chart(ChartType.PIE);
        Configuration conf = segmentationChart.getConfiguration();
        conf.setExporting(Boolean.TRUE);
        conf.setTitle("Segmentation by market share");

        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(1);
        tooltip.setPointFormat("{point.percentage:%.2f}%");
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
//        Labels dataLabels = new Labels();
//        dataLabels.setEnabled(true);
        conf.setPlotOptions(plotOptions);

        DataSeries series = new DataSeries();
//        chrome.setSliced(true);
//        chrome.setSelected(true);

        Map<String, BigDecimal> chartDataMap = getActualsData();
        List<String> categoryNamesList = new ArrayList<String>();
        List<BigDecimal> valuesList = new ArrayList<BigDecimal>();

        for (String key : chartDataMap.keySet()) {
            categoryNamesList.add(key);
            valuesList.add(chartDataMap.get(key));
        }
        series.setData(categoryNamesList.toArray(new String[0]), valuesList.toArray(new BigDecimal[0]));
        conf.setSeries(series);
        segmentationChart.drawChart(conf);

        layout.addComponent(urlLabel);
        layout.setComponentAlignment(urlLabel, Alignment.MIDDLE_CENTER);
        layout.addComponent(forecastLabel);
        layout.addComponent(segmentationChart);

    }

    public ForecastConfig getTimePeriod() {
        List<ForecastConfig> resultList = null;
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", "Non Mandated"));
        dynamicQuery.addOrder(OrderFactoryUtil.desc("versionNo"));
        try {
            resultList = ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            Logger.getLogger(ForecastDashBoardUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        ForecastConfig forecastConfig = null;
        if (resultList != null && !resultList.isEmpty()) {
            forecastConfig = (ForecastConfig) resultList.get(0);
        }
        return forecastConfig;
    }

    /**
     * Builds the url for portlet.
     *
     * @param layoutFriendlyName the layout friendly name
     * @return the string
     */
    /**
     * Gets the user info.
     *
     * @param userId the user id
     * @return the user info
     */
    public static User getUserInfo(final long userId) {
        DynamicQuery userSearchDynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        userSearchDynamicQuery.add(RestrictionsFactoryUtil.eq("userId", userId));
        List<User> resultList;
        try {
            resultList = UserLocalServiceUtil.dynamicQuery(userSearchDynamicQuery);
            if (resultList != null && resultList.size() > 0) {
                return resultList.get(0);
            }
        } catch (SystemException ex) {
            Logger.getLogger(ForecastDashBoardUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Map<String, BigDecimal> getActualsData() {
        Map<String, BigDecimal> actualsMap = new HashMap<String, BigDecimal>();

        List<Object[]> resultList = (List<Object[]>) RsModelLocalServiceUtil.executeSelectQuery(getActualsQuery(), null, null);
        if (resultList != null && !resultList.isEmpty()) {
            for (Object[] obj : resultList) {
//                System.out.println(" Market Type - " + obj[0]);
//                System.out.println(" Sales Amount - " + obj[1]);
                actualsMap.put(String.valueOf(obj[0]), new BigDecimal(String.valueOf(obj[1])));
            }
        }
        return actualsMap;
    }

    private String getActualsQuery() {
        String query = "";

        query = "SELECT MT.DESCRIPTION, SUM(AM.AMOUNT) from \n"
                + "dbo.ACTUALS_MASTER AM JOIN dbo.CONTRACT_MASTER CM ON AM.CONTRACT_ID=CM.CONTRACT_ID\n"
                + "JOIN dbo.HELPER_TABLE MT ON CM.CONTRACT_TYPE=MT.HELPER_TABLE_SID \n"
                + "GROUP BY MT.DESCRIPTION";

        return query;
    }
}
