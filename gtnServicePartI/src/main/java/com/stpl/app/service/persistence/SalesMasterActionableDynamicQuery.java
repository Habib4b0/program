package com.stpl.app.service.persistence;

import com.stpl.app.model.SalesMaster;
import com.stpl.app.service.SalesMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class SalesMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public SalesMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(SalesMasterLocalServiceUtil.getService());
        setClass(SalesMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("salesMasterSid");
    }
}
