package com.stpl.app.service.persistence;

import com.stpl.app.model.StChAssumptions;
import com.stpl.app.service.StChAssumptionsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StChAssumptionsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StChAssumptionsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StChAssumptionsLocalServiceUtil.getService());
        setClass(StChAssumptions.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionDetailsSid");
    }
}
