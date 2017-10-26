package com.stpl.app.service.persistence;

import com.stpl.app.model.PsModel;
import com.stpl.app.service.PsModelLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class PsModelActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PsModelActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PsModelLocalServiceUtil.getService());
        setClass(PsModel.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("psModelSid");
    }
}
