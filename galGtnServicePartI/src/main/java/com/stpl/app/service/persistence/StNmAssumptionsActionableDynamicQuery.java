package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmAssumptions;
import com.stpl.app.service.StNmAssumptionsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNmAssumptionsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNmAssumptionsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StNmAssumptionsLocalServiceUtil.getService());
        setClass(StNmAssumptions.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionPeriod");
    }
}
