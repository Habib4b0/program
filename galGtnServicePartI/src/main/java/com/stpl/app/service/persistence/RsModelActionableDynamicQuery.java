package com.stpl.app.service.persistence;

import com.stpl.app.model.RsModel;
import com.stpl.app.service.RsModelLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RsModelActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RsModelActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RsModelLocalServiceUtil.getService());
        setClass(RsModel.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rsModelSid");
    }
}
