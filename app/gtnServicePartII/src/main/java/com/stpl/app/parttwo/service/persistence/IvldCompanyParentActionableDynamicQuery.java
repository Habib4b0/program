package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyParent;
import com.stpl.app.parttwo.service.IvldCompanyParentLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldCompanyParentActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldCompanyParentActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldCompanyParentLocalServiceUtil.getService());
        setClass(IvldCompanyParent.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldCompanyParentSid");
    }
}
