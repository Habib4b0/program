package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class CfpContractDetailsFinderUtil {
    private static CfpContractDetailsFinder _finder;

    public static java.lang.Boolean saveCfpDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().saveCfpDetailsAttached(input, future);
    }

    public static java.util.List getCompaniesList(
        java.lang.String searchField, java.lang.String searchVal,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.lang.Object future1, java.lang.Object future2) {
        return getFinder()
                   .getCompaniesList(searchField, searchVal, filterMap, start,
            offset, column, orderBy, future1, future2);
    }

    public static CfpContractDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (CfpContractDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CfpContractDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(CfpContractDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CfpContractDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CfpContractDetailsFinderUtil.class,
            "_finder");
    }
}
