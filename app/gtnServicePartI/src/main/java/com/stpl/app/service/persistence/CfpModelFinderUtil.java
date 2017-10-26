package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class CfpModelFinderUtil {
    private static CfpModelFinder _finder;

    public static java.util.List findCfpModelV1(
        java.util.Map<java.lang.Object, java.lang.Object> cfp,
        java.lang.String orderByColumn, java.lang.Boolean sortOrder,
        java.lang.Object index, java.lang.Object next,
        java.util.Map<java.lang.Object, java.lang.Object> parameters,
        java.lang.String operation, java.lang.Object future,
        java.lang.Object future1) {
        return getFinder()
                   .findCfpModelV1(cfp, orderByColumn, sortOrder, index, next,
            parameters, operation, future, future1);
    }

    public static CfpModelFinder getFinder() {
        if (_finder == null) {
            _finder = (CfpModelFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CfpModelFinder.class.getName());

            ReferenceRegistry.registerReference(CfpModelFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CfpModelFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CfpModelFinderUtil.class, "_finder");
    }
}
