package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class NaProjMasterFinderUtil {
    private static NaProjMasterFinder _finder;

    public static java.util.List getAvailableProducts(
        java.lang.Object companyValue, java.lang.Object therapeuticClassValue,
        java.lang.Object productGroupValue, java.lang.Object future3) {
        return getFinder()
                   .getAvailableProducts(companyValue, therapeuticClassValue,
            productGroupValue, future3);
    }

    public static NaProjMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (NaProjMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NaProjMasterFinder.class.getName());

            ReferenceRegistry.registerReference(NaProjMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(NaProjMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(NaProjMasterFinderUtil.class,
            "_finder");
    }
}
