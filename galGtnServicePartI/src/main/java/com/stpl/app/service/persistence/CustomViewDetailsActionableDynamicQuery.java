package com.stpl.app.service.persistence;

import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CustomViewDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CustomViewDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CustomViewDetailsLocalServiceUtil.getService());
        setClass(CustomViewDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("customViewDetailsSid");
    }
}
