package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsContractDetailsFr;
import com.stpl.app.service.ImtdRsContractDetailsFrLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdRsContractDetailsFrActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdRsContractDetailsFrActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImtdRsContractDetailsFrLocalServiceUtil.getService());
        setClass(ImtdRsContractDetailsFr.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdRsContractDetailsFrSid");
    }
}
