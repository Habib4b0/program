package com.stpl.app.service.persistence;

import com.stpl.app.model.StMAssumptions;
import com.stpl.app.service.StMAssumptionsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StMAssumptionsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StMAssumptionsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StMAssumptionsLocalServiceUtil.getService());
        setClass(StMAssumptions.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projYear");
    }
}
