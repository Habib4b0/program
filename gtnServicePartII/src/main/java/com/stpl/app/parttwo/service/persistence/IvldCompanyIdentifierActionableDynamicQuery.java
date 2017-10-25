package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyIdentifier;
import com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldCompanyIdentifierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldCompanyIdentifierActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldCompanyIdentifierLocalServiceUtil.getService());
        setClass(IvldCompanyIdentifier.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldCompanyIdentifierSid");
    }
}
