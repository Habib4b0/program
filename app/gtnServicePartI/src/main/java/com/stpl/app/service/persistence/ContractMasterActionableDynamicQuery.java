package com.stpl.app.service.persistence;

import com.stpl.app.model.ContractMaster;
import com.stpl.app.service.ContractMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ContractMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContractMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContractMasterLocalServiceUtil.getService());
        setClass(ContractMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("contractMasterSid");
    }
}
