package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmDiscountProjMaster;
import com.stpl.app.service.StNmDiscountProjMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNmDiscountProjMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNmDiscountProjMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StNmDiscountProjMasterLocalServiceUtil.getService());
        setClass(StNmDiscountProjMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
