package com.stpl.app.service.persistence;

import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.service.VwInventoryWdActualProjMasLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwInventoryWdActualProjMasActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwInventoryWdActualProjMasActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwInventoryWdActualProjMasLocalServiceUtil.getService());
        setClass(VwInventoryWdActualProjMas.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("inventoryWdActualProjMasSid");
    }
}
