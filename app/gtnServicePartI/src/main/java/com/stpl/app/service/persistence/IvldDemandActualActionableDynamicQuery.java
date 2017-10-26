package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldDemandActual;
import com.stpl.app.service.IvldDemandActualLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldDemandActualActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldDemandActualActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldDemandActualLocalServiceUtil.getService());
        setClass(IvldDemandActual.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldDemandActualSid");
    }
}
