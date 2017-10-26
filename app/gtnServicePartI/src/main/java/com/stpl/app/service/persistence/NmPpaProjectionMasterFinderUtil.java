package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class NmPpaProjectionMasterFinderUtil {
    private static NmPpaProjectionMasterFinder _finder;

    public static java.util.List getPPAProjectionList(
        java.lang.Integer projectionId, int levelNo, java.lang.String parent,
        boolean last, int startIndex, int endIndex, boolean isCount,
        java.lang.String levelName) {
        return getFinder()
                   .getPPAProjectionList(projectionId, levelNo, parent, last,
            startIndex, endIndex, isCount, levelName);
    }

    public static void setPPAProjectionMassUpdate(java.lang.Object priceCap,
        int startQuater, int endQuater, int startYear, int endYear,
        int projectionId, java.lang.String parent, java.lang.String levelValue) {
        getFinder()
            .setPPAProjectionMassUpdate(priceCap, startQuater, endQuater,
            startYear, endYear, projectionId, parent, levelValue);
    }

    public static java.util.List getPPAResults(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount, java.util.List<java.lang.String> input,
        java.lang.String levelName) {
        return getFinder()
                   .getPPAResults(projectionId, levelNo, parent, last,
            startIndex, endIndex, isCount, input, levelName);
    }

    public static java.util.List getLevelValues(int projectionId, int levelNo,
        java.lang.String parent) {
        return getFinder().getLevelValues(projectionId, levelNo, parent);
    }

    public static java.util.List getProductHierarchyLevel(int projectionId,
        int levelNo, java.lang.String parent) {
        return getFinder()
                   .getProductHierarchyLevel(projectionId, levelNo, parent);
    }

    public static NmPpaProjectionMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (NmPpaProjectionMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmPpaProjectionMasterFinder.class.getName());

            ReferenceRegistry.registerReference(NmPpaProjectionMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(NmPpaProjectionMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(NmPpaProjectionMasterFinderUtil.class,
            "_finder");
    }
}
