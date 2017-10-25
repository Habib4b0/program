package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpContract;
import com.stpl.app.service.CfpContractLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CfpContractActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CfpContractActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CfpContractLocalServiceUtil.getService());
        setClass(CfpContract.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cfpContractSid");
    }
}
