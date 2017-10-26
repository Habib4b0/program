package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldInventoryWdProjMas;
import com.stpl.app.service.IvldInventoryWdProjMasLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldInventoryWdProjMasActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldInventoryWdProjMasActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldInventoryWdProjMasLocalServiceUtil.getService());
        setClass(IvldInventoryWdProjMas.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldInventoryWdProjMasSid");
    }
}
