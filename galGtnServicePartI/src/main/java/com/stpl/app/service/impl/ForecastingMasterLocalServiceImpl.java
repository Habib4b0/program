package com.stpl.app.service.impl;

import com.stpl.app.service.base.ForecastingMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ForecastingMasterFinderUtil;
import java.util.List;

/**
 * The implementation of the forecasting master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ForecastingMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ForecastingMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.ForecastingMasterLocalServiceUtil
 */
public class ForecastingMasterLocalServiceImpl
    extends ForecastingMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ForecastingMasterLocalServiceUtil} to access the forecasting master local service.
     */
    public java.util.List getResults(java.lang.String fileType,
            java.lang.String country, java.lang.String fileName,
            java.lang.String type, java.lang.String version,
            java.lang.String forecastYear, java.lang.String fromDate,
            java.lang.String toDate) {
        return ForecastingMasterFinderUtil.getResults(fileType, country, fileName, type, version, forecastYear, fromDate, toDate);
    }

    public java.util.List getDetailsResults(java.lang.String fileName,
            java.lang.String version, java.lang.String fileType,
            java.lang.String country, int year) {
        return ForecastingMasterFinderUtil.getDetailsResults(fileName, version, fileType, country, year);
    }

    public java.lang.Object executeSelectQuery(java.lang.String query,
            java.lang.Object udc1, java.lang.Object udc2) {
        return ForecastingMasterFinderUtil.executeSelectQuery(query, udc1, udc2);
    }

    public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {

        return ForecastingMasterFinderUtil.executeBulkUpdateQuery(query, udc1, udc2);
    }

    public Object executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {

        return ForecastingMasterFinderUtil.executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }
    
    public  java.util.List getFileSearchResults(java.lang.String query) {
        return ForecastingMasterFinderUtil.getFileSearchResults(query);
    }
}
