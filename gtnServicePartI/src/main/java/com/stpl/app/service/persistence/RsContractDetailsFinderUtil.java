package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class RsContractDetailsFinderUtil {
    private static RsContractDetailsFinder _finder;

    public static java.lang.Boolean saveRsDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().saveRsDetailsAttached(input, future);
    }

    public static void saveFormulaToContractRs(int contRsdSid, int rsdSid,
        int itemSid) {
        getFinder().saveFormulaToContractRs(contRsdSid, rsdSid, itemSid);
    }

    public static RsContractDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (RsContractDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsContractDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(RsContractDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(RsContractDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(RsContractDetailsFinderUtil.class,
            "_finder");
    }
}
