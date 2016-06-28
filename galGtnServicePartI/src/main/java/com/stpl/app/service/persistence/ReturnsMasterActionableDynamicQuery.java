package com.stpl.app.service.persistence;

import com.stpl.app.model.ReturnsMaster;
import com.stpl.app.service.ReturnsMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ReturnsMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ReturnsMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ReturnsMasterLocalServiceUtil.getService());
        setClass(ReturnsMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("returnsMasterSid");
    }
}
