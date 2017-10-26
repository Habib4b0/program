package com.stpl.app.service.persistence;

import com.stpl.app.model.VwIvldInventoryWdActualProjMas;
import com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwIvldInventoryWdActualProjMasActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwIvldInventoryWdActualProjMasActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwIvldInventoryWdActualProjMasLocalServiceUtil.getService());
        setClass(VwIvldInventoryWdActualProjMas.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldInventoryWdActualProjMasSid");
    }
}
