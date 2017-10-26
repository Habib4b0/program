package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemPricingQualifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemPricingQualifierActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ItemPricingQualifierLocalServiceUtil.getService());
        setClass(ItemPricingQualifier.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemPricingQualifierSid");
    }
}
