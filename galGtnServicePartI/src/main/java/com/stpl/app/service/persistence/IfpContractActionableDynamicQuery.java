package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpContract;
import com.stpl.app.service.IfpContractLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IfpContractActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IfpContractActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IfpContractLocalServiceUtil.getService());
        setClass(IfpContract.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ifpContractSid");
    }
}
