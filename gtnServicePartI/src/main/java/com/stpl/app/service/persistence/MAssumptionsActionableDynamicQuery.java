package com.stpl.app.service.persistence;

import com.stpl.app.model.MAssumptions;
import com.stpl.app.service.MAssumptionsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MAssumptionsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MAssumptionsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(MAssumptionsLocalServiceUtil.getService());
        setClass(MAssumptions.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("mAssumptionsSid");
    }
}
