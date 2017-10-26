package com.stpl.app.service.persistence;

import com.stpl.app.model.RsContract;
import com.stpl.app.service.RsContractLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RsContractActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RsContractActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RsContractLocalServiceUtil.getService());
        setClass(RsContract.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rsContractSid");
    }
}
