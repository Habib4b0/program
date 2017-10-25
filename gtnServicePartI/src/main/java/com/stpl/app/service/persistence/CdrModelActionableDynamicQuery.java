package com.stpl.app.service.persistence;

import com.stpl.app.model.CdrModel;
import com.stpl.app.service.CdrModelLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CdrModelActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CdrModelActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CdrModelLocalServiceUtil.getService());
        setClass(CdrModel.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cdrModelSid");
    }
}
