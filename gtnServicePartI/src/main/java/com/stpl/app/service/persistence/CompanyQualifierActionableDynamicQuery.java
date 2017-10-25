package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.service.CompanyQualifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CompanyQualifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CompanyQualifierActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CompanyQualifierLocalServiceUtil.getService());
        setClass(CompanyQualifier.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyQualifierSid");
    }
}
