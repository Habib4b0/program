package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ProjectionCustHierarchyFinderUtil {
    private static ProjectionCustHierarchyFinder _finder;

    public static void insert(java.util.List list, java.lang.String string1,
        java.lang.String string2) {
        getFinder().insert(list, string1, string2);
    }

    public static java.util.List retrive(java.util.List list,
        java.lang.String string1, java.lang.String string2) {
        return getFinder().retrive(list, string1, string2);
    }

    public static java.util.List getComparisonSearch(
        java.lang.String workflowStatus, java.lang.String marketType,
        java.lang.String brand, java.lang.String projName,
        java.lang.String contHldr, java.lang.String ndcNo,
        java.lang.String ndcName, java.lang.String desc,
        java.lang.String contract, java.lang.String from, java.lang.String to) {
        return getFinder()
                   .getComparisonSearch(workflowStatus, marketType, brand,
            projName, contHldr, ndcNo, ndcName, desc, contract, from, to);
    }

    public static ProjectionCustHierarchyFinder getFinder() {
        if (_finder == null) {
            _finder = (ProjectionCustHierarchyFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ProjectionCustHierarchyFinder.class.getName());

            ReferenceRegistry.registerReference(ProjectionCustHierarchyFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ProjectionCustHierarchyFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ProjectionCustHierarchyFinderUtil.class,
            "_finder");
    }
}
