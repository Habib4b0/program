package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class MSalesProjectionMasterFinderUtil {
    private static MSalesProjectionMasterFinder _finder;

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return getFinder().executeSelectQuery(query, udc1, udc2);
    }

    public static java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2) {
        return getFinder().executeUpdateQuery(queryList, obj1, obj2);
    }

    public static java.util.List executeUpdateQuery(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2) {
        return getFinder().executeUpdateQuery(query, obj1, obj2);
    }

    public static java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName) {
        return getFinder().getAssumptionResult(input, queryName);
    }

    public static java.lang.Object executeUpdateSQL(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2) {
        return getFinder().executeUpdateSQL(query, obj1, obj2);
    }

    public static java.lang.Object executeUpdateQuery(
        java.util.List<?> nmSalesList, java.lang.Object udc1,
        java.lang.Object udc2, java.lang.Object udc3) {
        return getFinder().executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }

    public static MSalesProjectionMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (MSalesProjectionMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MSalesProjectionMasterFinder.class.getName());

            ReferenceRegistry.registerReference(MSalesProjectionMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(MSalesProjectionMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(MSalesProjectionMasterFinderUtil.class,
            "_finder");
    }
}
