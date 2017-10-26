package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionDetails;
import com.stpl.app.service.DeductionDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class DeductionDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DeductionDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(DeductionDetailsLocalServiceUtil.getService());
        setClass(DeductionDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("deductionDetailsSid");
    }
}
