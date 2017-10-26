package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class MasterDataAttributeFinderUtil {
    private static MasterDataAttributeFinder _finder;

    public static java.util.List getTotalLives(java.lang.Object[] inputs) {
        return getFinder().getTotalLives(inputs);
    }

    public static java.util.List getLives(java.lang.Object[] inputs) {
        return getFinder().getLives(inputs);
    }

    public static MasterDataAttributeFinder getFinder() {
        if (_finder == null) {
            _finder = (MasterDataAttributeFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MasterDataAttributeFinder.class.getName());

            ReferenceRegistry.registerReference(MasterDataAttributeFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(MasterDataAttributeFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(MasterDataAttributeFinderUtil.class,
            "_finder");
    }
}
