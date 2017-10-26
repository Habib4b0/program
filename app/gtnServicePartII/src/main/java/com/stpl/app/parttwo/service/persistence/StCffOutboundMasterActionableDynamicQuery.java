package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StCffOutboundMaster;
import com.stpl.app.parttwo.service.StCffOutboundMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StCffOutboundMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StCffOutboundMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StCffOutboundMasterLocalServiceUtil.getService());
        setClass(StCffOutboundMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.cffDetailsSid");
    }
}
