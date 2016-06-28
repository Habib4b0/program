package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmCompanyLink;
import com.stpl.app.service.GcmCompanyLinkLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class GcmCompanyLinkActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GcmCompanyLinkActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GcmCompanyLinkLocalServiceUtil.getService());
        setClass(GcmCompanyLink.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("gcmCompanyLinkSid");
    }
}
