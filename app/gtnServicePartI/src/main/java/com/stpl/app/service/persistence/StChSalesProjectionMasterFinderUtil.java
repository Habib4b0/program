package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class StChSalesProjectionMasterFinderUtil {
    private static StChSalesProjectionMasterFinder _finder;

    public static java.util.List executeQuery(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().executeQuery(parameters);
    }

    public static StChSalesProjectionMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (StChSalesProjectionMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StChSalesProjectionMasterFinder.class.getName());

            ReferenceRegistry.registerReference(StChSalesProjectionMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(StChSalesProjectionMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(StChSalesProjectionMasterFinderUtil.class,
            "_finder");
    }
}
