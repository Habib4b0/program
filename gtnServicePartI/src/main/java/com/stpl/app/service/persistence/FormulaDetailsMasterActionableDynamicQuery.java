package com.stpl.app.service.persistence;

import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class FormulaDetailsMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public FormulaDetailsMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(FormulaDetailsMasterLocalServiceUtil.getService());
        setClass(FormulaDetailsMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("formulaDetailsMasterSid");
    }
}
