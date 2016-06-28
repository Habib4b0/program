package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class HelperTableFinderUtil {
    private static HelperTableFinder _finder;

    public static java.util.List executeSelectQuery(java.lang.String query) {
        return getFinder().executeSelectQuery(query);
    }

    public static void executeUpdateQuery(java.lang.String query) {
        getFinder().executeUpdateQuery(query);
    }

    public static int executeUpdateQueryCount(java.lang.String query) {
        return getFinder().executeUpdateQueryCount(query);
    }

    public static HelperTableFinder getFinder() {
        if (_finder == null) {
            _finder = (HelperTableFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HelperTableFinder.class.getName());

            ReferenceRegistry.registerReference(HelperTableFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(HelperTableFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(HelperTableFinderUtil.class,
            "_finder");
    }
}
