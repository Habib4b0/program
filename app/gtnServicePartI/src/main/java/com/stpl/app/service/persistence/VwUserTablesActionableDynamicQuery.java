package com.stpl.app.service.persistence;

import com.stpl.app.model.VwUserTables;
import com.stpl.app.service.VwUserTablesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwUserTablesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwUserTablesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(VwUserTablesLocalServiceUtil.getService());
        setClass(VwUserTables.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("uniqueId");
    }
}
