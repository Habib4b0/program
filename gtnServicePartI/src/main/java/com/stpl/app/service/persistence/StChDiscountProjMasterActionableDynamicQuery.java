package com.stpl.app.service.persistence;

import com.stpl.app.model.StChDiscountProjMaster;
import com.stpl.app.service.StChDiscountProjMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StChDiscountProjMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StChDiscountProjMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StChDiscountProjMasterLocalServiceUtil.getService());
        setClass(StChDiscountProjMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionDetailsSid");
    }
}
