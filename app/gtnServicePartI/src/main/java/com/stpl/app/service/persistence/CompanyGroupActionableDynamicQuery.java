package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyGroup;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CompanyGroupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CompanyGroupActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CompanyGroupLocalServiceUtil.getService());
        setClass(CompanyGroup.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyGroupSid");
    }
}
