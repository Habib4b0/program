package com.stpl.app.service.persistence;

import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ContractAliasMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContractAliasMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ContractAliasMasterLocalServiceUtil.getService());
        setClass(ContractAliasMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("contractAliasMasterSid");
    }
}
