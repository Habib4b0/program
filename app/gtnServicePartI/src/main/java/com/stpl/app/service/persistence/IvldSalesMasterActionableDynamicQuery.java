package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldSalesMaster;
import com.stpl.app.service.IvldSalesMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldSalesMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldSalesMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldSalesMasterLocalServiceUtil.getService());
        setClass(IvldSalesMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldSalesMasterSid");
    }
}
