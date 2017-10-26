package com.stpl.app.service.persistence;

import com.stpl.app.model.RsDetailsFr;
import com.stpl.app.service.RsDetailsFrLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RsDetailsFrActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RsDetailsFrActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RsDetailsFrLocalServiceUtil.getService());
        setClass(RsDetailsFr.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rsDetailsFrSid");
    }
}
