package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemIdentifier;
import com.stpl.app.parttwo.service.VwItemIdentifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwItemIdentifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwItemIdentifierActionableDynamicQuery() throws SystemException {
        setBaseLocalService(VwItemIdentifierLocalServiceUtil.getService());
        setClass(VwItemIdentifier.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemIdentifierSid");
    }
}
