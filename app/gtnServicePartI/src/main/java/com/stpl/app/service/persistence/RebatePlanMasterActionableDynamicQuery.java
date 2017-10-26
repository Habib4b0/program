package com.stpl.app.service.persistence;

import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RebatePlanMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RebatePlanMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RebatePlanMasterLocalServiceUtil.getService());
        setClass(RebatePlanMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rebatePlanMasterSid");
    }
}
