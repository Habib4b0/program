package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class PsContractDetailsFinderUtil {
    private static PsContractDetailsFinder _finder;

    public static java.lang.Boolean savePsDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().savePsDetailsAttached(input, future);
    }

    public static PsContractDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (PsContractDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    PsContractDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(PsContractDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(PsContractDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(PsContractDetailsFinderUtil.class,
            "_finder");
    }
}
