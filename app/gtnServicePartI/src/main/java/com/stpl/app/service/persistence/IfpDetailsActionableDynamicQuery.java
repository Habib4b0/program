package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpDetails;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IfpDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IfpDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IfpDetailsLocalServiceUtil.getService());
        setClass(IfpDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ifpDetailsSid");
    }
}
