package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldInventoryWdActualMas;
import com.stpl.app.service.IvldInventoryWdActualMasLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldInventoryWdActualMasActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldInventoryWdActualMasActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldInventoryWdActualMasLocalServiceUtil.getService());
        setClass(IvldInventoryWdActualMas.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldInventoryWdActualMasSid");
    }
}
