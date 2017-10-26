package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdItemPriceRebateDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdItemPriceRebateDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImtdItemPriceRebateDetailsLocalServiceUtil.getService());
        setClass(ImtdItemPriceRebateDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdItemPriceRebateSid");
    }
}
