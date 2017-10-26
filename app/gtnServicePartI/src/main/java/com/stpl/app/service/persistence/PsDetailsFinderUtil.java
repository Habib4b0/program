package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class PsDetailsFinderUtil {
    private static PsDetailsFinder _finder;

    public static java.util.List getItemAndPricingForPs(int psSystemId) {
        return getFinder().getItemAndPricingForPs(psSystemId);
    }

    public static java.util.List getPsSearchList(java.lang.String psId,
        java.lang.String psNo, java.lang.String psName, int psStatus,
        int psType, java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int end, java.lang.String column, java.lang.String orderBy,
        boolean isCount) {
        return getFinder()
                   .getPsSearchList(psId, psNo, psName, psStatus, psType,
            itemId, itemNo, itemName, filterMap, start, end, column, orderBy,
            isCount);
    }

    public static PsDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (PsDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    PsDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(PsDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(PsDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(PsDetailsFinderUtil.class, "_finder");
    }
}
