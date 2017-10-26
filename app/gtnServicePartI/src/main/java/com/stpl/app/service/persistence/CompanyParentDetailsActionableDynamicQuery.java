package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.service.CompanyParentDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CompanyParentDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CompanyParentDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(CompanyParentDetailsLocalServiceUtil.getService());
        setClass(CompanyParentDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyParentDetailsSid");
    }
}
