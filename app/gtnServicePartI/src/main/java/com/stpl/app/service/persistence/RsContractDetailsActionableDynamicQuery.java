package com.stpl.app.service.persistence;

import com.stpl.app.model.RsContractDetails;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RsContractDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RsContractDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RsContractDetailsLocalServiceUtil.getService());
        setClass(RsContractDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rsContractDetailsSid");
    }
}
