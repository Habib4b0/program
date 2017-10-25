package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ForecastingMasterFinderUtil {
    private static ForecastingMasterFinder _finder;

    public static java.util.List getResults(java.lang.String fileType,
        java.lang.String country, java.lang.String fileName,
        java.lang.String type, java.lang.String version,
        java.lang.String forecastYear, java.lang.String fromDate,
        java.lang.String toDate) {
        return getFinder()
                   .getResults(fileType, country, fileName, type, version,
            forecastYear, fromDate, toDate);
    }

    public static java.util.List getDetailsResults(java.lang.String fileName,
        java.lang.String version, java.lang.String fileType,
        java.lang.String country, int year) {
        return getFinder()
                   .getDetailsResults(fileName, version, fileType, country, year);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return getFinder().executeSelectQuery(query, udc1, udc2);
    }

    public static java.lang.Object executeBulkUpdateQuery(
        java.lang.String query, java.lang.Object udc1, java.lang.Object udc2) {
        return getFinder().executeBulkUpdateQuery(query, udc1, udc2);
    }

    public static java.lang.Object executeUpdateQuery(
        java.util.List<?> nmSalesList, java.lang.Object udc1,
        java.lang.Object udc2, java.lang.Object udc3) {
        return getFinder().executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }

    public static java.util.List getFileSearchResults(java.lang.String query) {
        return getFinder().getFileSearchResults(query);
    }

    public static ForecastingMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (ForecastingMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ForecastingMasterFinder.class.getName());

            ReferenceRegistry.registerReference(ForecastingMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ForecastingMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ForecastingMasterFinderUtil.class,
            "_finder");
    }
}
