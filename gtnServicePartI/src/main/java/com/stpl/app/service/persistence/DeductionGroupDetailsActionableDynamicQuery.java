package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionGroupDetails;
import com.stpl.app.service.DeductionGroupDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class DeductionGroupDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DeductionGroupDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(DeductionGroupDetailsLocalServiceUtil.getService());
        setClass(DeductionGroupDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("deductionGroupDetailsSid");
    }
}
