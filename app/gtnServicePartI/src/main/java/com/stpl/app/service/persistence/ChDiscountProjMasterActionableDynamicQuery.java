package com.stpl.app.service.persistence;

import com.stpl.app.model.ChDiscountProjMaster;
import com.stpl.app.service.ChDiscountProjMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChDiscountProjMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChDiscountProjMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ChDiscountProjMasterLocalServiceUtil.getService());
        setClass(ChDiscountProjMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionDetailsSid");
    }
}
