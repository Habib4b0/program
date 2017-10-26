package com.stpl.app.service.persistence;

import com.stpl.app.model.BestPriceMaster;
import com.stpl.app.service.BestPriceMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class BestPriceMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public BestPriceMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(BestPriceMasterLocalServiceUtil.getService());
        setClass(BestPriceMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("bestPriceMasterSid");
    }
}
