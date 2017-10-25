package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyTradeClass;
import com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldCompanyTradeClassActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldCompanyTradeClassActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldCompanyTradeClassLocalServiceUtil.getService());
        setClass(IvldCompanyTradeClass.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldCompanyTradeClassSid");
    }
}
