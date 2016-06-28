package com.stpl.app.service.persistence;

import com.stpl.app.model.MSalesProjectionMaster;
import com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MSalesProjectionMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MSalesProjectionMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MSalesProjectionMasterLocalServiceUtil.getService());
        setClass(MSalesProjectionMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
