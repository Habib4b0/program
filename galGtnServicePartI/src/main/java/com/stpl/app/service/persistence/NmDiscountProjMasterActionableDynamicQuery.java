package com.stpl.app.service.persistence;

import com.stpl.app.model.NmDiscountProjMaster;
import com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmDiscountProjMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmDiscountProjMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NmDiscountProjMasterLocalServiceUtil.getService());
        setClass(NmDiscountProjMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
