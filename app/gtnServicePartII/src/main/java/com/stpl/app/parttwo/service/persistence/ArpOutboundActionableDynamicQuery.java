package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.ArpOutbound;
import com.stpl.app.parttwo.service.ArpOutboundLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ArpOutboundActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ArpOutboundActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ArpOutboundLocalServiceUtil.getService());
        setClass(ArpOutbound.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.companyMasterSid");
    }
}
