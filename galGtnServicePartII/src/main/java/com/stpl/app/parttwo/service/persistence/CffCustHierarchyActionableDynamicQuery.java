package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffCustHierarchyActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffCustHierarchyActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffCustHierarchyLocalServiceUtil.getService());
        setClass(CffCustHierarchy.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffCustHierarchySid");
    }
}
