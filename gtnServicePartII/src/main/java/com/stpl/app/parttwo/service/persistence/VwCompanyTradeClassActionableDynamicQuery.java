package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyTradeClass;
import com.stpl.app.parttwo.service.VwCompanyTradeClassLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwCompanyTradeClassActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwCompanyTradeClassActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwCompanyTradeClassLocalServiceUtil.getService());
        setClass(VwCompanyTradeClass.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyTradeClassSid");
    }
}
