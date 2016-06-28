
package com.stpl.app.forecastdashboard.logic;

import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.dao.impl.ForecastDashboardDAOImpl;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.stpl.app.model.ForecastConfig;
import com.vaadin.server.Page;
import java.util.Date;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class ForecastConfigurationLogic {

    /* The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ForecastConfigurationLogic.class);
    
    /**
     * Data Access Service object
     */
    ForecastDashboardDAO forecastDashboardService = new ForecastDashboardDAOImpl();
    
    public String getForecastConfigurationModuleURL() {
        LOGGER.info("Entering FC getForecastConfigurationModuleURL");
        final String serverUrl = Page.getCurrent().getLocation().toString();
        String[] splittedUrl = serverUrl.split("/", 0);
        splittedUrl[splittedUrl.length - 1] = "forecasting-configuration";

        String url = "";
        for (String s : splittedUrl) {
            if (!url.isEmpty()) {
                url += "/";
            }
            url+=s;
        }
        LOGGER.info("Ending FC getForecastConfigurationModuleURL"+url);
        return url;
    }
    /**
     * Method returns the Forecast Configuration From and To dates of all the forecast Flavors
     * @return all the dates in array format.
     */
    public Date[] getForecastConfigurationDates(){
        LOGGER.info("Entering FC getForecastConfigurationDates");
            ForecastConfig commercialConfig = forecastDashboardService.getForecastConfigurationTimePeriod(forecastDashboardService.getBusinessProcessTypeHelperTableSid(CommonUtils.COMMERCIAL));
            ForecastConfig governmentConfig = forecastDashboardService.getForecastConfigurationTimePeriod(forecastDashboardService.getBusinessProcessTypeHelperTableSid(CommonUtils.GOVERNMENT));
            ForecastConfig returnsConfig = forecastDashboardService.getForecastConfigurationTimePeriod(forecastDashboardService.getBusinessProcessTypeHelperTableSid(CommonUtils.RETURNS));
            ForecastConfig naConfig = forecastDashboardService.getForecastConfigurationTimePeriod(forecastDashboardService.getBusinessProcessTypeHelperTableSid(CommonUtils.NATIONAL_ASSUMPTIONS));
            Date[] configDate = new Date[4*2];
                configDate[0]=commercialConfig.getFromDate();
                configDate[1]=commercialConfig.getToDate();
                configDate[2]=governmentConfig.getFromDate();
                configDate[3]=governmentConfig.getToDate();
                configDate[4]=returnsConfig.getFromDate();
                configDate[5]=returnsConfig.getToDate();
                configDate[6]=naConfig.getFromDate();
                configDate[7]=naConfig.getToDate();
        LOGGER.info("Endin FC getForecastConfigurationDates");
        return configDate;
    }
}
