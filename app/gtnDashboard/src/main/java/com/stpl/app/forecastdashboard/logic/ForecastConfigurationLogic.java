
package com.stpl.app.forecastdashboard.logic;

import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.dao.impl.ForecastDashboardDAOImpl;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.portal.kernel.util.StringUtil;
import com.vaadin.server.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.HEAD;
import org.apache.commons.lang.StringUtils;
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
    
  public String getActivatedFileName(){
       List<Object> activatedFiles=new ArrayList<Object>();
       String activatedFileName= StringUtils.EMPTY;
      String fileType ="Select DESCRIPTION from HELPER_TABLE where LIST_NAME= 'FILE_TYPE'";

        List<Object> fileList = HelperTableLocalServiceUtil.executeSelectQuery(fileType);
        for(int i=0;i<fileList.size();i++){
        String activated = "SELECT TOP 1 FT.FORECAST_NAME\n"
                + "FROM   FILE_MANAGEMENT FT\n"
                + "       INNER JOIN HELPER_TABLE HT\n"
                + "               ON HT.HELPER_TABLE_SID = FT.FILE_TYPE\n"
                + "WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())\n"
                + "         AND FT.FROM_PERIOD IS NOT NULL )\n"
                + "       AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())\n"
                + "              OR FT.TO_PERIOD IS NULL )\n"
                + "       AND HT.LIST_NAME = 'FILE_TYPE'\n"
                + "       AND HT.DESCRIPTION = '"+ String.valueOf(fileList.get(i))+"'"
                + "ORDER  BY FT.FROM_PERIOD DESC ";
         List<Object> fileList1 = HelperTableLocalServiceUtil.executeSelectQuery(activated);
         if(fileList1.size()!=0){
         activatedFiles.add(fileList1.get(0));
         }else{
         activatedFiles.add(StringUtils.EMPTY);  
         }
           
             if(fileList1.size()!=0 && !String.valueOf(activatedFiles.get(i)).equals(StringUtils.EMPTY) ){
         activatedFileName+= String.valueOf(fileList.get(i)) +" - "+ String.valueOf(activatedFiles.get(i));
         if(i!=fileList.size()-1){
        activatedFileName+=" , ";
         }
         }
        }
         return activatedFileName;
    }
  
}
