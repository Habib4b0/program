package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldAccrualInbound;
import com.stpl.app.parttwo.service.IvldAccrualInboundLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldAccrualInboundActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldAccrualInboundActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldAccrualInboundLocalServiceUtil.getService());
        setClass(IvldAccrualInbound.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldAccrualInboundSid");
    }
}
