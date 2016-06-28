package com.stpl.app.service.persistence;

import com.stpl.app.model.PsContractDetails;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class PsContractDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PsContractDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PsContractDetailsLocalServiceUtil.getService());
        setClass(PsContractDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("psContractDetailsSid");
    }
}
