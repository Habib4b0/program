package com.stpl.app.service.persistence;

import com.stpl.app.model.InventoryWdActualMas;
import com.stpl.app.service.InventoryWdActualMasLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class InventoryWdActualMasActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public InventoryWdActualMasActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(InventoryWdActualMasLocalServiceUtil.getService());
        setClass(InventoryWdActualMas.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("inventoryWdActualMasSid");
    }
}
