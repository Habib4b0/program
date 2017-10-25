package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmPpaProjectionMaster;
import com.stpl.app.service.StNmPpaProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNmPpaProjectionMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNmPpaProjectionMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StNmPpaProjectionMasterLocalServiceUtil.getService());
        setClass(StNmPpaProjectionMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionDetailsSid");
    }
}
