package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class StChDiscountProjMasterFinderUtil {
    private static StChDiscountProjMasterFinder _finder;

    public static java.util.List executeQuery(java.lang.String query) {
        return getFinder().executeQuery(query);
    }

    public static int updateQuery(java.lang.String query) {
        return getFinder().updateQuery(query);
    }

    public static StChDiscountProjMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (StChDiscountProjMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StChDiscountProjMasterFinder.class.getName());

            ReferenceRegistry.registerReference(StChDiscountProjMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(StChDiscountProjMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(StChDiscountProjMasterFinderUtil.class,
            "_finder");
    }
}
