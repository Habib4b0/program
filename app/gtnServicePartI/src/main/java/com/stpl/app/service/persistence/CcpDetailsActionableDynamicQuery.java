package com.stpl.app.service.persistence;

import com.stpl.app.model.CcpDetails;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CcpDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CcpDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CcpDetailsLocalServiceUtil.getService());
        setClass(CcpDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ccpDetailsSid");
    }
}
