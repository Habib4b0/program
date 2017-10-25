package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmContractDetails;
import com.stpl.app.service.GcmContractDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class GcmContractDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GcmContractDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GcmContractDetailsLocalServiceUtil.getService());
        setClass(GcmContractDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("gcmContractDetailsSid");
    }
}
