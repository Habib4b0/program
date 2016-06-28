package com.stpl.app.service.persistence;

import com.stpl.app.model.ChSalesProjectionMaster;
import com.stpl.app.service.ChSalesProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChSalesProjectionMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChSalesProjectionMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ChSalesProjectionMasterLocalServiceUtil.getService());
        setClass(ChSalesProjectionMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
