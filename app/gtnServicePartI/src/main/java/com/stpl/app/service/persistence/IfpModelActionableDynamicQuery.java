package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpModel;
import com.stpl.app.service.IfpModelLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IfpModelActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IfpModelActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IfpModelLocalServiceUtil.getService());
        setClass(IfpModel.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ifpModelSid");
    }
}
