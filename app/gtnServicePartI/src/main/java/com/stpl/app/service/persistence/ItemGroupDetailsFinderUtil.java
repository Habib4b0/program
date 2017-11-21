package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ItemGroupDetailsFinderUtil {
    private static ItemGroupDetailsFinder _finder;

    public static java.util.List getProductGroups(
        java.lang.String productGroupNo, java.lang.String productGroupName,
        java.lang.String company, java.lang.String segment, int startIndex,
        int offset, java.util.Set<com.vaadin.v7.data.Container.Filter> filters,
        java.util.List<org.asi.ui.extfilteringtable.paged.logic.SortByColumn> sortByColumns) {
        return getFinder()
                   .getProductGroups(productGroupNo, productGroupName, company,
            segment, startIndex, offset, filters, sortByColumns);
    }

    public static java.util.List getItemMasterRecords(java.lang.String[] query) {
        return getFinder().getItemMasterRecords(query);
    }

    public static ItemGroupDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (ItemGroupDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemGroupDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(ItemGroupDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ItemGroupDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ItemGroupDetailsFinderUtil.class,
            "_finder");
    }
}
