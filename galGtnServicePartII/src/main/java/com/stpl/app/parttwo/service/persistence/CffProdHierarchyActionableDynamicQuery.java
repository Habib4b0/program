package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffProdHierarchyActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffProdHierarchyActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffProdHierarchyLocalServiceUtil.getService());
        setClass(CffProdHierarchy.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffProdHierarchySid");
    }
}
