package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.service.ItemIdentifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemIdentifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemIdentifierActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ItemIdentifierLocalServiceUtil.getService());
        setClass(ItemIdentifier.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemIdentifierSid");
    }
}
