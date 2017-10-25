package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class GcmGlobalDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GcmGlobalDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GcmGlobalDetailsLocalServiceUtil.getService());
        setClass(GcmGlobalDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("gcmGlobalDetailsSid");
    }
}
