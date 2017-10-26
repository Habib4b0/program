package com.stpl.app.service.persistence;

import com.stpl.app.model.RsDetails;
import com.stpl.app.service.RsDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RsDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RsDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RsDetailsLocalServiceUtil.getService());
        setClass(RsDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rsDetailsSid");
    }
}
