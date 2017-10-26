package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpModel;
import com.stpl.app.service.CfpModelLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CfpModelActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CfpModelActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CfpModelLocalServiceUtil.getService());
        setClass(CfpModel.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cfpModelSid");
    }
}
