package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyMaster;
import com.stpl.app.parttwo.service.VwCompanyMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwCompanyMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwCompanyMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(VwCompanyMasterLocalServiceUtil.getService());
        setClass(VwCompanyMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyMasterSid");
    }
}
