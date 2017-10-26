package com.stpl.app.service.persistence;

import com.stpl.app.model.CdrDetails;
import com.stpl.app.service.CdrDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CdrDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CdrDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CdrDetailsLocalServiceUtil.getService());
        setClass(CdrDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cdrDetailsSid");
    }
}
