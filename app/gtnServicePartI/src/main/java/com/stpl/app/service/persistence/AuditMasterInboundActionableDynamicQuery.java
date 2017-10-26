package com.stpl.app.service.persistence;

import com.stpl.app.model.AuditMasterInbound;
import com.stpl.app.service.AuditMasterInboundLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AuditMasterInboundActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AuditMasterInboundActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AuditMasterInboundLocalServiceUtil.getService());
        setClass(AuditMasterInbound.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("auditInboundSid");
    }
}
