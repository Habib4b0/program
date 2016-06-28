package com.stpl.app.service.persistence;

import com.stpl.app.model.MasterDataAttribute;
import com.stpl.app.service.MasterDataAttributeLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MasterDataAttributeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MasterDataAttributeActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MasterDataAttributeLocalServiceUtil.getService());
        setClass(MasterDataAttribute.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("masterDataAttributeSid");
    }
}
