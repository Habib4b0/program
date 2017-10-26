package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class FcpActualsFinderUtil {
    private static FcpActualsFinder _finder;

    public static java.lang.Object executeSelectQuery(java.lang.String query) {
        return getFinder().executeSelectQuery(query);
    }

    public static java.lang.Object executeBulkUpdateQuery(
        java.lang.String query) {
        return getFinder().executeBulkUpdateQuery(query);
    }

    public static java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList) {
        return getFinder().executeUpdateQuery(queryList);
    }

    public static java.lang.Object executeUpdateQuery(java.lang.String query) {
        return getFinder().executeUpdateQuery(query);
    }

    public static FcpActualsFinder getFinder() {
        if (_finder == null) {
            _finder = (FcpActualsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    FcpActualsFinder.class.getName());

            ReferenceRegistry.registerReference(FcpActualsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(FcpActualsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(FcpActualsFinderUtil.class,
            "_finder");
    }
}
