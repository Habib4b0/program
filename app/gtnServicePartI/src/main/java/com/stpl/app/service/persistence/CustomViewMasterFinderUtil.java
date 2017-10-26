package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class CustomViewMasterFinderUtil {
    private static CustomViewMasterFinder _finder;

    public static java.util.List getHierarchyLevelsForDiscount(
        int projectionId, java.lang.String hierarchyIndicator, int levelNo,
        int hierarchyLevelDefId) {
        return getFinder()
                   .getHierarchyLevelsForDiscount(projectionId,
            hierarchyIndicator, levelNo, hierarchyLevelDefId);
    }

    public static CustomViewMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (CustomViewMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CustomViewMasterFinder.class.getName());

            ReferenceRegistry.registerReference(CustomViewMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CustomViewMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CustomViewMasterFinderUtil.class,
            "_finder");
    }
}
