package com.stpl.app.service.persistence;

import com.stpl.app.model.AccrualDetails;
import com.stpl.app.service.AccrualDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AccrualDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AccrualDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AccrualDetailsLocalServiceUtil.getService());
        setClass(AccrualDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accrualDetailsSid");
    }
}
