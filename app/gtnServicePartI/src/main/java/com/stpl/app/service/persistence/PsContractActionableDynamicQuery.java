package com.stpl.app.service.persistence;

import com.stpl.app.model.PsContract;
import com.stpl.app.service.PsContractLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class PsContractActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PsContractActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PsContractLocalServiceUtil.getService());
        setClass(PsContract.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("psContractSid");
    }
}
