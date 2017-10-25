package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemPricing;
import com.stpl.app.parttwo.service.IvldItemPricingLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldItemPricingActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldItemPricingActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldItemPricingLocalServiceUtil.getService());
        setClass(IvldItemPricing.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldItemPricingSid");
    }
}
