package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldBestPrice;
import com.stpl.app.service.IvldBestPriceLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldBestPriceActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldBestPriceActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldBestPriceLocalServiceUtil.getService());
        setClass(IvldBestPrice.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldBestPriceSid");
    }
}
