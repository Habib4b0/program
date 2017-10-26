package com.stpl.app.service.persistence;

import com.stpl.app.model.TransactionModuleMaster;
import com.stpl.app.service.TransactionModuleMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class TransactionModuleMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public TransactionModuleMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(TransactionModuleMasterLocalServiceUtil.getService());
        setClass(TransactionModuleMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("transactionModuleMasterSid");
    }
}
