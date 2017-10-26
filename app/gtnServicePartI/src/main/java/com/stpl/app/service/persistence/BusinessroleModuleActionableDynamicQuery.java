package com.stpl.app.service.persistence;

import com.stpl.app.model.BusinessroleModule;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class BusinessroleModuleActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public BusinessroleModuleActionableDynamicQuery() throws SystemException {
        setBaseLocalService(BusinessroleModuleLocalServiceUtil.getService());
        setClass(BusinessroleModule.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("businessroleModuleSid");
    }
}
