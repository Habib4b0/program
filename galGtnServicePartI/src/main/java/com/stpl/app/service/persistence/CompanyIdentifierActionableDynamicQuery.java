package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.service.CompanyIdentifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CompanyIdentifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CompanyIdentifierActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CompanyIdentifierLocalServiceUtil.getService());
        setClass(CompanyIdentifier.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyIdentifierSid");
    }
}
