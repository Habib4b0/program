package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemIdentifier;
import com.stpl.app.parttwo.service.IvldItemIdentifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldItemIdentifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldItemIdentifierActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldItemIdentifierLocalServiceUtil.getService());
        setClass(IvldItemIdentifier.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldItemIdentifierSid");
    }
}
