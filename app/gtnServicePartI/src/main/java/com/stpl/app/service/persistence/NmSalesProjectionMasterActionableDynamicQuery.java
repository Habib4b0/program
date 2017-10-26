package com.stpl.app.service.persistence;

import com.stpl.app.model.NmSalesProjectionMaster;
import com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmSalesProjectionMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmSalesProjectionMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NmSalesProjectionMasterLocalServiceUtil.getService());
        setClass(NmSalesProjectionMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
