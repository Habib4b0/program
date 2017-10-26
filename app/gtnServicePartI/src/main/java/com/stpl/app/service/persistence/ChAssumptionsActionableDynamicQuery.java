package com.stpl.app.service.persistence;

import com.stpl.app.model.ChAssumptions;
import com.stpl.app.service.ChAssumptionsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChAssumptionsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChAssumptionsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ChAssumptionsLocalServiceUtil.getService());
        setClass(ChAssumptions.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("chAssumptionsSid");
    }
}
