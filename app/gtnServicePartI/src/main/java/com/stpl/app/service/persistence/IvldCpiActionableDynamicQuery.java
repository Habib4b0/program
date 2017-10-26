package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldCpi;
import com.stpl.app.service.IvldCpiLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldCpiActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldCpiActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldCpiLocalServiceUtil.getService());
        setClass(IvldCpi.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldCpiSid");
    }
}
