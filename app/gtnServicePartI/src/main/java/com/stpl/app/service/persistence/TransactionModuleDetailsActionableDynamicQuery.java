package com.stpl.app.service.persistence;

import com.stpl.app.model.TransactionModuleDetails;
import com.stpl.app.service.TransactionModuleDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class TransactionModuleDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public TransactionModuleDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(TransactionModuleDetailsLocalServiceUtil.getService());
        setClass(TransactionModuleDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("transactionModuleDetailsSid");
    }
}
