package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class IfpContractDetailsFinderUtil {
    private static IfpContractDetailsFinder _finder;

    public static java.lang.Boolean saveIfpDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().saveIfpDetailsAttached(input, future);
    }

    public static java.util.List findIFP(java.lang.Object field,
        java.lang.Object value, java.util.List<java.lang.Integer> future,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int end, java.lang.String column, java.lang.String orderBy,
        java.lang.Object future1) {
        return getFinder()
                   .findIFP(field, value, future, filterMap, start, end,
            column, orderBy, future1);
    }

    public static IfpContractDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (IfpContractDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IfpContractDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(IfpContractDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(IfpContractDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(IfpContractDetailsFinderUtil.class,
            "_finder");
    }
}
