package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemPricing;
import com.stpl.app.service.ItemPricingLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemPricingActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemPricingActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ItemPricingLocalServiceUtil.getService());
        setClass(ItemPricing.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemPricingSid");
    }
}
