package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldFormulaDetails;
import com.stpl.app.service.IvldFormulaDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldFormulaDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldFormulaDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldFormulaDetailsLocalServiceUtil.getService());
        setClass(IvldFormulaDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldFormulaDetailsSid");
    }
}
