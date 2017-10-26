package com.stpl.app.service.persistence;

import com.stpl.app.model.HistRelationshipLevelDefn;
import com.stpl.app.service.HistRelationshipLevelDefnLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HistRelationshipLevelDefnActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HistRelationshipLevelDefnActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(HistRelationshipLevelDefnLocalServiceUtil.getService());
        setClass(HistRelationshipLevelDefn.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.versionNo");
    }
}
