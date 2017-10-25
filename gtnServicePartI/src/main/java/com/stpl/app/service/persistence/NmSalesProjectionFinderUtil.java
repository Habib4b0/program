package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class NmSalesProjectionFinderUtil {
    private static NmSalesProjectionFinder _finder;

    public static java.util.List getSalesResult(java.lang.Object[] inputs) {
        return getFinder().getSalesResult(inputs);
    }

    public static java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName) {
        return getFinder().getAssumptionResult(input, queryName);
    }

    public static java.util.List getSalesProjectionResults(
        java.lang.Object[] inputs) {
        return getFinder().getSalesProjectionResults(inputs);
    }

    public static java.util.List getSalesProjectionResultLevels(
        java.lang.Object[] inputs) {
        return getFinder().getSalesProjectionResultLevels(inputs);
    }

    public static java.util.List getVarianceSales(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String actualsOrProjections, java.lang.String parentName,
        java.lang.String year, int levelNo, java.lang.String sales) {
        return getFinder()
                   .getVarianceSales(projectionId, frequency,
            startAndEndPeriods, actualsOrProjections, parentName, year,
            levelNo, sales);
    }

    public static NmSalesProjectionFinder getFinder() {
        if (_finder == null) {
            _finder = (NmSalesProjectionFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmSalesProjectionFinder.class.getName());

            ReferenceRegistry.registerReference(NmSalesProjectionFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(NmSalesProjectionFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(NmSalesProjectionFinderUtil.class,
            "_finder");
    }
}
