package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemGroupDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemGroupDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ItemGroupDetailsLocalServiceUtil.getService());
        setClass(ItemGroupDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemGroupDetailsSid");
    }
}
