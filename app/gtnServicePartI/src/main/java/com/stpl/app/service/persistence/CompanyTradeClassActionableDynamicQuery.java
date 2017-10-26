package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CompanyTradeClassActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CompanyTradeClassActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CompanyTradeClassLocalServiceUtil.getService());
        setClass(CompanyTradeClass.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyTradeClassSid");
    }
}
