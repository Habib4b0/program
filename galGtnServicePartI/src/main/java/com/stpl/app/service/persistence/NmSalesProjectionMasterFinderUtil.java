package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class NmSalesProjectionMasterFinderUtil {
    private static NmSalesProjectionMasterFinder _finder;

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

    public static NmSalesProjectionMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (NmSalesProjectionMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmSalesProjectionMasterFinder.class.getName());

            ReferenceRegistry.registerReference(NmSalesProjectionMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(NmSalesProjectionMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(NmSalesProjectionMasterFinderUtil.class,
            "_finder");
    }
}
