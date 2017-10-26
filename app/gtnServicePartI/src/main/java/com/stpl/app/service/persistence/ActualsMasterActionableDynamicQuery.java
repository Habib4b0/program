package com.stpl.app.service.persistence;

import com.stpl.app.model.ActualsMaster;
import com.stpl.app.service.ActualsMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ActualsMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ActualsMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ActualsMasterLocalServiceUtil.getService());
        setClass(ActualsMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("actualsMasterSid");
    }
}
