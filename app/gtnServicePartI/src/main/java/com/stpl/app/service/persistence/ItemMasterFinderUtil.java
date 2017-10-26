package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ItemMasterFinderUtil {
    private static ItemMasterFinder _finder;

    public static java.util.List findItemMaster(java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName,
        java.lang.String itemStatus, java.lang.String itemType,
        java.lang.String itemDesc, java.lang.String manufacturerId,
        int identifierType, java.lang.String start, java.lang.String offset,
        java.lang.String orderByColumn, java.lang.Boolean sortOrder,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .findItemMaster(itemId, itemNo, itemName, itemStatus,
            itemType, itemDesc, manufacturerId, identifierType, start, offset,
            orderByColumn, sortOrder, parameters);
    }

    public static ItemMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (ItemMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemMasterFinder.class.getName());

            ReferenceRegistry.registerReference(ItemMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ItemMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ItemMasterFinderUtil.class,
            "_finder");
    }
}
