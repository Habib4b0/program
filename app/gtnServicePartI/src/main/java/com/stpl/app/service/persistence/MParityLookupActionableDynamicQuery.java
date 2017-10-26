package com.stpl.app.service.persistence;

import com.stpl.app.model.MParityLookup;
import com.stpl.app.service.MParityLookupLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MParityLookupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MParityLookupActionableDynamicQuery() throws SystemException {
        setBaseLocalService(MParityLookupLocalServiceUtil.getService());
        setClass(MParityLookup.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("mParityLookupSid");
    }
}
