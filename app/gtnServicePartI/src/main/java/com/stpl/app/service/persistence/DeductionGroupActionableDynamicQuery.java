package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionGroup;
import com.stpl.app.service.DeductionGroupLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class DeductionGroupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DeductionGroupActionableDynamicQuery() throws SystemException {
        setBaseLocalService(DeductionGroupLocalServiceUtil.getService());
        setClass(DeductionGroup.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("deductionGroupSid");
    }
}
