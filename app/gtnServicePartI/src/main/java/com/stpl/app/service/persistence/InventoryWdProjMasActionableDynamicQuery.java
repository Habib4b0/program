package com.stpl.app.service.persistence;

import com.stpl.app.model.InventoryWdProjMas;
import com.stpl.app.service.InventoryWdProjMasLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class InventoryWdProjMasActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public InventoryWdProjMasActionableDynamicQuery() throws SystemException {
        setBaseLocalService(InventoryWdProjMasLocalServiceUtil.getService());
        setClass(InventoryWdProjMas.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("inventoryWdProjMasSid");
    }
}
