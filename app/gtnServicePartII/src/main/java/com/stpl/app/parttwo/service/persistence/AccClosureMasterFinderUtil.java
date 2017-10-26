package com.stpl.app.parttwo.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class AccClosureMasterFinderUtil {
    private static AccClosureMasterFinder _finder;

    public static java.lang.Object executeSelectQuery(java.util.List input,
        java.lang.String queryName, java.lang.String quaryName2) {
        return getFinder().executeSelectQuery(input, queryName, quaryName2);
    }

    public static java.lang.Boolean executeUpdateQuery(java.util.List input,
        java.lang.String queryName) {
        return getFinder().executeUpdateQuery(input, queryName);
    }

    public static java.lang.String getQuery(java.util.List input,
        java.lang.String queryName) {
        return getFinder().getQuery(input, queryName);
    }

    public static AccClosureMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (AccClosureMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    AccClosureMasterFinder.class.getName());

            ReferenceRegistry.registerReference(AccClosureMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(AccClosureMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(AccClosureMasterFinderUtil.class,
            "_finder");
    }
}
