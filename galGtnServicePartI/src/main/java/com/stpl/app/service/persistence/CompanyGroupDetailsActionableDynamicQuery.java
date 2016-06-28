package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CompanyGroupDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CompanyGroupDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(CompanyGroupDetailsLocalServiceUtil.getService());
        setClass(CompanyGroupDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyGroupDetailsSid");
    }
}
