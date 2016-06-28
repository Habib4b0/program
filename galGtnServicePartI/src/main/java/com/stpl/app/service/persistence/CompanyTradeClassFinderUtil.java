package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class CompanyTradeClassFinderUtil {
    private static CompanyTradeClassFinder _finder;

    public static java.util.List getTradeClassDetails(
        java.lang.String companySystemId, java.lang.String tradeClassSysId) {
        return getFinder().getTradeClassDetails(companySystemId, tradeClassSysId);
    }

    public static java.util.List getCompanyparentDetails(
        java.lang.String companySystemId, java.lang.String parentSysId) {
        return getFinder().getCompanyparentDetails(companySystemId, parentSysId);
    }

    public static CompanyTradeClassFinder getFinder() {
        if (_finder == null) {
            _finder = (CompanyTradeClassFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CompanyTradeClassFinder.class.getName());

            ReferenceRegistry.registerReference(CompanyTradeClassFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CompanyTradeClassFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CompanyTradeClassFinderUtil.class,
            "_finder");
    }
}
