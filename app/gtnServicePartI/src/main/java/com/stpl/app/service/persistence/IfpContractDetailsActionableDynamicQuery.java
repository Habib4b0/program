package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IfpContractDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IfpContractDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IfpContractDetailsLocalServiceUtil.getService());
        setClass(IfpContractDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ifpContractDetailsSid");
    }
}
