package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CompanyMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CompanyMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CompanyMasterLocalServiceUtil.getService());
        setClass(CompanyMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyMasterSid");
    }
}
