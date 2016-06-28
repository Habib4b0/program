package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class RelationshipBuilderFinderUtil {
    private static RelationshipBuilderFinder _finder;

    public static java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName) {
        return getFinder().findByTableName(tableName, columnName);
    }

    public static java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName, java.util.List hierListValues) {
        return getFinder().findByTableName(tableName, columnName, hierListValues);
    }

    public static RelationshipBuilderFinder getFinder() {
        if (_finder == null) {
            _finder = (RelationshipBuilderFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RelationshipBuilderFinder.class.getName());

            ReferenceRegistry.registerReference(RelationshipBuilderFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(RelationshipBuilderFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(RelationshipBuilderFinderUtil.class,
            "_finder");
    }
}
