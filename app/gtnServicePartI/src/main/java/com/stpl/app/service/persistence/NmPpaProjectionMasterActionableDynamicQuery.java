package com.stpl.app.service.persistence;

import com.stpl.app.model.NmPpaProjectionMaster;
import com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmPpaProjectionMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmPpaProjectionMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NmPpaProjectionMasterLocalServiceUtil.getService());
        setClass(NmPpaProjectionMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
