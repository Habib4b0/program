package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldGlBalance;
import com.stpl.app.service.IvldGlBalanceLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldGlBalanceActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldGlBalanceActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldGlBalanceLocalServiceUtil.getService());
        setClass(IvldGlBalance.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldGlBalanceSid");
    }
}
