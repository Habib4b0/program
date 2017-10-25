package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ItemGroupFinderUtil {
    private static ItemGroupFinder _finder;

    public static java.util.List getItemGroupMaster(
        java.lang.String itemGroupName) {
        return getFinder().getItemGroupMaster(itemGroupName);
    }

    public static java.util.List getItemGroupDetails(
        java.lang.String itemType, java.lang.String itemDesc,
        java.lang.String brand, java.lang.String strength,
        java.lang.String itemNoCriteria, java.lang.String therapeuticCriteria,
        java.lang.String formCriteria, java.lang.String selectedItemIds) {
        return getFinder()
                   .getItemGroupDetails(itemType, itemDesc, brand, strength,
            itemNoCriteria, therapeuticCriteria, formCriteria, selectedItemIds);
    }

    public static java.util.List getAvailableSearchResults(
        java.lang.String finalCriteria) {
        return getFinder().getAvailableSearchResults(finalCriteria);
    }

    public static ItemGroupFinder getFinder() {
        if (_finder == null) {
            _finder = (ItemGroupFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemGroupFinder.class.getName());

            ReferenceRegistry.registerReference(ItemGroupFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ItemGroupFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ItemGroupFinderUtil.class, "_finder");
    }
}
