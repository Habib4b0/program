package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemPricing;
import com.stpl.app.parttwo.service.VwItemPricingLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwItemPricingActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwItemPricingActionableDynamicQuery() throws SystemException {
        setBaseLocalService(VwItemPricingLocalServiceUtil.getService());
        setClass(VwItemPricing.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemPricingSid");
    }
}
