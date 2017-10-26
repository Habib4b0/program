package com.stpl.app.service.persistence;

import com.stpl.app.model.RsContractDetailsFr;
import com.stpl.app.service.RsContractDetailsFrLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RsContractDetailsFrActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RsContractDetailsFrActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(RsContractDetailsFrLocalServiceUtil.getService());
        setClass(RsContractDetailsFr.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rsContractDetailsFrSid");
    }
}
