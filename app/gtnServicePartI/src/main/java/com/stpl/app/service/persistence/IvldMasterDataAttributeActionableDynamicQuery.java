package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldMasterDataAttribute;
import com.stpl.app.service.IvldMasterDataAttributeLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldMasterDataAttributeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldMasterDataAttributeActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldMasterDataAttributeLocalServiceUtil.getService());
        setClass(IvldMasterDataAttribute.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldMasterDataAtbteSid");
    }
}
