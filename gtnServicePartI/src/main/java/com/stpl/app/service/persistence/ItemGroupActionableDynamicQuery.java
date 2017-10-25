package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemGroup;
import com.stpl.app.service.ItemGroupLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemGroupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemGroupActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ItemGroupLocalServiceUtil.getService());
        setClass(ItemGroup.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemGroupSid");
    }
}
