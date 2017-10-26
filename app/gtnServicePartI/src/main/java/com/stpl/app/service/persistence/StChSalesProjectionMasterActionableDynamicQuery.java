package com.stpl.app.service.persistence;

import com.stpl.app.model.StChSalesProjectionMaster;
import com.stpl.app.service.StChSalesProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StChSalesProjectionMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StChSalesProjectionMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StChSalesProjectionMasterLocalServiceUtil.getService());
        setClass(StChSalesProjectionMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionDetailsSid");
    }
}
