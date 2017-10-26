package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyIdentifier;
import com.stpl.app.parttwo.service.VwCompanyIdentifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwCompanyIdentifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwCompanyIdentifierActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwCompanyIdentifierLocalServiceUtil.getService());
        setClass(VwCompanyIdentifier.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyIdentifierSid");
    }
}
