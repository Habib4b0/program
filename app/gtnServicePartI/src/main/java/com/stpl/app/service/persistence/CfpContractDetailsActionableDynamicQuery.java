package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CfpContractDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CfpContractDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CfpContractDetailsLocalServiceUtil.getService());
        setClass(CfpContractDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cfpContractDetailsSid");
    }
}
