package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ForecastingViewMasterFinderUtil {
    private static ForecastingViewMasterFinder _finder;

    public static java.util.List findViewByName(java.lang.String viewName,
        java.lang.String forecastType, java.lang.String userId,
        java.lang.String viewType) {
        return getFinder()
                   .findViewByName(viewName, forecastType, userId, viewType);
    }

    public static java.util.List findViewByNameForChannels(
        java.lang.String viewName, java.lang.String forecastType,
        java.lang.String userId, java.lang.String viewType) {
        return getFinder()
                   .findViewByNameForChannels(viewName, forecastType, userId,
            viewType);
    }

    public static ForecastingViewMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (ForecastingViewMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ForecastingViewMasterFinder.class.getName());

            ReferenceRegistry.registerReference(ForecastingViewMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ForecastingViewMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ForecastingViewMasterFinderUtil.class,
            "_finder");
    }
}
