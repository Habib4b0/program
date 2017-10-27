package com.stpl.app.forecastdashboard.ui;

import com.stpl.app.forecastdashboard.ui.form.ForecastConfigurationForm;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class ForecastConfigurationUI extends UI {

    /* The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ForecastConfigurationUI.class);

    @Override
    protected void init(VaadinRequest request) {

        Navigator navigator;
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute("sessionId", sessionId);
        VaadinSession.getCurrent().setAttribute("userId", userId);
        navigator = new Navigator(this, this);
        try {
            navigator.addView("", new ForecastConfigurationForm());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
//    @Override
//    protected void init(VaadinRequest request) {
//        final VerticalLayout layout = new VerticalLayout();
//        layout.setMargin(true);
//        setContent(layout);
//
//        ForecastConfig forecastConfig = getTimePeriod();
//        Date startDate = forecastConfig.getFromDate();
//        Date endDate = forecastConfig.getToDate();
//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String startDateString = formatter.format(startDate);
//        String endDateString = formatter.format(endDate);
//
//        final String serverUrl = Page.getCurrent().getLocation().toString();
//        String[] splittedUrl = serverUrl.split("/", 0);
//        splittedUrl[splittedUrl.length - 1] = "forecasting-configuration";
//
//        String url = "";
//        for (String s : splittedUrl) {
//            if (!url.isEmpty()) {
//                url += "\\";
//            }
//            url+=s;
//        }
//        System.out.println(" url " + url);
//        Label forecastLabel = new Label("", ContentMode.HTML);
//        forecastLabel.setValue("<div style=\"background: dodgerblue;color: white;text-align:center\"> Forecast Configuration Period : " 
//                + startDateString + " to " + endDateString + "&nbsp;&nbsp;<b><a href=\"" + url + "\" style=\"color: yellow;\">Click Here to change </a></b></div> ");
//        System.out.println(" url --" + url);
//
//        layout.addComponent(forecastLabel);
//        layout.setComponentAlignment(forecastLabel, Alignment.MIDDLE_CENTER);
//
//    }
//
//    public ForecastConfig getTimePeriod() {
//        List<ForecastConfig> resultList = null;
//        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
//        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", "Non Mandated"));
//        dynamicQuery.addOrder(OrderFactoryUtil.desc("versionNo"));
//        try {
//            resultList = ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
//        } catch (SystemException ex) {
//            Logger.getLogger(ForecastDashBoardUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ForecastConfig forecastConfig = null;
//        if (resultList != null && !resultList.isEmpty()) {
//            forecastConfig = (ForecastConfig) resultList.get(0);
//        }
//        return forecastConfig;
//    }

}
