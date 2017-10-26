package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmCompanyDetails;
import com.stpl.app.service.GcmCompanyDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class GcmCompanyDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GcmCompanyDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GcmCompanyDetailsLocalServiceUtil.getService());
        setClass(GcmCompanyDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("gcmCompanyDetailsSid");
    }
}
