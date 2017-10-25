package com.stpl.app.service.persistence;

import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.service.RebateTierFormulaLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RebateTierFormulaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RebateTierFormulaActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RebateTierFormulaLocalServiceUtil.getService());
        setClass(RebateTierFormula.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("rebateTierFormulaSid");
    }
}
