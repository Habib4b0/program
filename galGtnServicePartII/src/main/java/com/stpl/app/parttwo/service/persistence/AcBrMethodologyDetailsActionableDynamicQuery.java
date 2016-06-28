package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcBrMethodologyDetails;
import com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AcBrMethodologyDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AcBrMethodologyDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(AcBrMethodologyDetailsLocalServiceUtil.getService());
        setClass(AcBrMethodologyDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("acBrMethodologyDetailsSid");
    }
}
