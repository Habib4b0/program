package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemQualifier;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemQualifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemQualifierActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ItemQualifierLocalServiceUtil.getService());
        setClass(ItemQualifier.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemQualifierSid");
    }
}
