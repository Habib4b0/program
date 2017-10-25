package com.stpl.app.service.persistence;

import com.stpl.app.model.GlBalanceMaster;
import com.stpl.app.service.GlBalanceMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class GlBalanceMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GlBalanceMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GlBalanceMasterLocalServiceUtil.getService());
        setClass(GlBalanceMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("glBalanceMasterSid");
    }
}
