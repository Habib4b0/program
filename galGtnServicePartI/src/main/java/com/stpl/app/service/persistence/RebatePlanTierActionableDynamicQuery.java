package com.stpl.app.service.persistence;

import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.service.RebatePlanTierLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RebatePlanTierActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RebatePlanTierActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RebatePlanTierLocalServiceUtil.getService());
        setClass(RebatePlanTier.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rebatePlanTierSid");
    }
}
