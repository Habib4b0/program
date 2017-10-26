package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class HierarchyDefinitionFinderUtil {
    private static HierarchyDefinitionFinder _finder;

    public static java.util.List getHierarchyGroup(
        java.lang.String hierarchyName, java.lang.String hierarchyType,
        java.lang.String customerOrProduct, java.lang.String action) {
        return getFinder()
                   .getHierarchyGroup(hierarchyName, hierarchyType,
            customerOrProduct, action);
    }

    public static java.util.List getLevelsFromHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getLevelsFromHierarchy(parameters);
    }

    public static java.util.List getHierarchySystemId(int relationshipLevelId) {
        return getFinder().getHierarchySystemId(relationshipLevelId);
    }

    public static HierarchyDefinitionFinder getFinder() {
        if (_finder == null) {
            _finder = (HierarchyDefinitionFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HierarchyDefinitionFinder.class.getName());

            ReferenceRegistry.registerReference(HierarchyDefinitionFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(HierarchyDefinitionFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(HierarchyDefinitionFinderUtil.class,
            "_finder");
    }
}
