package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StArpOutbound;
import com.stpl.app.parttwo.service.StArpOutboundLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StArpOutboundActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StArpOutboundActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StArpOutboundLocalServiceUtil.getService());
        setClass(StArpOutbound.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.companyMasterSid");
    }
}
