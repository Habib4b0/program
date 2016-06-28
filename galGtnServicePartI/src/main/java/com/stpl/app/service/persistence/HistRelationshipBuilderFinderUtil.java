package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class HistRelationshipBuilderFinderUtil {
    private static HistRelationshipBuilderFinder _finder;

    public static java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName) {
        return getFinder().findByTableName(tableName, columnName);
    }

    public static java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName, java.util.List hierListValues) {
        return getFinder().findByTableName(tableName, columnName, hierListValues);
    }

    public static java.util.List findFilteredLevelValues(java.lang.String query) {
        return getFinder().findFilteredLevelValues(query);
    }

    public static java.util.List executeQuery(java.lang.String query) {
        return getFinder().executeQuery(query);
    }

    public static HistRelationshipBuilderFinder getFinder() {
        if (_finder == null) {
            _finder = (HistRelationshipBuilderFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistRelationshipBuilderFinder.class.getName());

            ReferenceRegistry.registerReference(HistRelationshipBuilderFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(HistRelationshipBuilderFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(HistRelationshipBuilderFinderUtil.class,
            "_finder");
    }
}
