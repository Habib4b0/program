package com.stpl.app.service.persistence;

import com.stpl.app.model.HistItemGroupDetails;
import com.stpl.app.service.HistItemGroupDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HistItemGroupDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HistItemGroupDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(HistItemGroupDetailsLocalServiceUtil.getService());
        setClass(HistItemGroupDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.itemGroupDetailsSid");
    }
}
