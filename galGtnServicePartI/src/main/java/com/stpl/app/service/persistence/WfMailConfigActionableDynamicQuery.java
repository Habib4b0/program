package com.stpl.app.service.persistence;

import com.stpl.app.model.WfMailConfig;
import com.stpl.app.service.WfMailConfigLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class WfMailConfigActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public WfMailConfigActionableDynamicQuery() throws SystemException {
        setBaseLocalService(WfMailConfigLocalServiceUtil.getService());
        setClass(WfMailConfig.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("wfMailConfigSid");
    }
}
