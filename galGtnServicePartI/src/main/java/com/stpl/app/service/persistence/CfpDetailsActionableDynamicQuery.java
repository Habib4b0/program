package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpDetails;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CfpDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CfpDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CfpDetailsLocalServiceUtil.getService());
        setClass(CfpDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cfpDetailsSid");
    }
}
